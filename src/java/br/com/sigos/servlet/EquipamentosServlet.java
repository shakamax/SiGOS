/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCEquipamento;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.ListaEquipamento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme
 */
public class EquipamentosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ListaEquipamento lista = new ListaEquipamento();
        String acao = request.getParameter("acao");
        Cliente cliente = new Cliente();
        //cliente.setId(Integer.parseInt(request.getParameter("id")));
        List<ListaEquipamento> equipamentos = new ArrayList<>();
        JDBCEquipamento je = new JDBCEquipamento();
        JDBCCliente jc = new JDBCCliente();
                
        
        if (acao.equals("listar")){
            cliente.setId(Integer.parseInt(request.getParameter("id")));
            equipamentos = je.Listar(cliente.getId());
            cliente = jc.exibir(cliente.getId());
            
            request.setAttribute("listas", equipamentos);
            request.setAttribute("cliente", cliente);
            
            request.getRequestDispatcher("listaEquipamentos.jsp").forward(request, response);
            
        } else if (acao.equals("salvar")) {
        
            lista.setEquipamento(request.getParameter("equipamento"));
            lista.setAcessório(request.getParameter("acessorios"));
            lista.setObservacao(request.getParameter("observacao"));
            lista.setDefeito(request.getParameter("defeito"));
            if (request.getParameter("id").equals("0")){
                lista.setFk_cliente(Integer.parseInt(request.getParameter("fk_cliente")));
                je.inserir(lista);
                response.sendRedirect("EquipamentosServlet?acao=listar&id=" + lista.getFk_cliente());
            } else {
                lista.setFk_cliente(Integer.parseInt(request.getParameter("fk")));
                lista.setId(Integer.parseInt(request.getParameter("id")));
                je.alterar(lista);
                
                
                response.sendRedirect("EquipamentosServlet?acao=listar&id=" + lista.getFk_cliente());
            }
            
           //response.sendRedirect("EquipamentosServlet?acao=listar&id=" + lista.getFk_cliente());
            
        } else if (acao.equals("novo")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            cliente = jc.exibir(id);
            
            
            request.setAttribute("cliente", cliente);
            
            
            request.getRequestDispatcher("novoEquipamento.jsp").forward(request, response);
        } else if (acao.equals("deletar")) {
            lista.setId(Integer.parseInt(request.getParameter("id")));
            int id = je.deletar(lista.getId());
            System.out.println(id);
            
            response.sendRedirect("EquipamentosServlet?acao=listar&id=" + id);
        } else if(acao.equals("editar")) {
            int id = (Integer.parseInt(request.getParameter("id")));
            
            lista = je.exibir(id);
            cliente = jc.exibir(lista.getFk_cliente());
            
            request.setAttribute("cliente", cliente);
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("novoEquipamento.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
