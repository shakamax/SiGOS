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
        Cliente cliente = new Cliente();
        //cliente.setId(Integer.parseInt(request.getParameter("id")));
        List<ListaEquipamento> equipamentos = new ArrayList<>();
        JDBCEquipamento je = new JDBCEquipamento();
        JDBCCliente jc = new JDBCCliente();
        String msg = "";
        String tipo = "";
                
        
        String acao = request.getParameter("acao");
        if (acao.equals("listar")){
            cliente.setId(Integer.parseInt(request.getParameter("id")));
            equipamentos = je.Listar(cliente.getId());
            cliente = jc.exibir(cliente.getId());
            
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("inserido")){
                    msg = "Equipamento cadastrado com sucesso!";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("alterado")){
                    msg = "Equipamento foi alterado com sucesso!";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("deletado")){
                    msg = "Equipamento foi excluído com sucesso.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
            }
            
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
                msg = "inserido";
                response.sendRedirect("EquipamentosServlet?acao=listar&id=" + lista.getFk_cliente() + "&msg=" + msg);
            } else {
                lista.setFk_cliente(Integer.parseInt(request.getParameter("fk")));
                lista.setId(Integer.parseInt(request.getParameter("id")));
                je.alterar(lista);
                msg = "alterado";
                
                response.sendRedirect("EquipamentosServlet?acao=listar&id=" + lista.getFk_cliente() + "&msg=" + msg);
            }
            
           //response.sendRedirect("EquipamentosServlet?acao=listar&id=" + lista.getFk_cliente());
            
        } else if (acao.equals("novo")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            cliente = jc.exibir(id);
            
            
            request.setAttribute("cliente", cliente);
            
            
            request.getRequestDispatcher("novoEquipamento.jsp").forward(request, response);
        } else if (acao.equals("deletar")) {
            lista.setId(Integer.parseInt(request.getParameter("id")));
            Boolean podeDeletar = je.verOrdens(lista.getId());
            
            if(podeDeletar){
                int id = je.deletar(lista.getId());
                msg = "deletado";
            }else {
                msg = "errodeletar";
            }
            lista = je.exibir(lista.getId());
            int id = lista.getFk_cliente();
            
            response.sendRedirect("EquipamentosServlet?acao=listar&id=" + id + "&msg=" + msg);
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
