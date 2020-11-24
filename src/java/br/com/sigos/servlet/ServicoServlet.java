/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.Servico;
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
 * @author 03728827142
 */
public class ServicoServlet extends HttpServlet {

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
            
        Servico serv = new Servico();
        JDBCServico js = new JDBCServico();
        String msg = "";
        String tipo = "";
        
        String acao = request.getParameter("acao");
        
        if (acao.equals("salvar")){
            serv.setNome(request.getParameter("nome"));
            serv.setValor(Double.parseDouble(request.getParameter("valor").replace(".", "").replace("," , ".")));
            serv.setDescricao(request.getParameter("descricao"));

            if(request.getParameter("id").equals("0")){
                
                js.inserir(serv);
                msg = "inserido";
                
                
                response.sendRedirect("ServicoServlet?acao=listar&msg=" + msg);
                
            } else {
                serv.setID(Integer.parseInt(request.getParameter("id")));
                
                js.alterar(serv);
                msg = "alterado";
                
                response.sendRedirect("ServicoServlet?acao=listar&msg=" + msg);
            } 

        } else if(acao.equals("listar")){
            
            List<Servico> servicos = new ArrayList<>();
            
            servicos = js.listar();
            
            msg = request.getParameter("msg");
            System.out.println(msg);
            if(msg != null){
                if(msg.equals("inserido")){
                    System.out.println("ta pegando AQUI SENHOR");
                    msg = "Serviço foi cadastrado com sucesso.";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("alterado")){
                    msg = "Serviço foi alterado com sucesso.";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("deletado")){
                    msg = "Serviço foi excluído com sucesso.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
            }
            
            request.setAttribute("servicos", servicos);
            request.getRequestDispatcher("listaServicos.jsp").forward(request, response);
        } else if (acao.equals("novoServico")) {
            
            
            request.getRequestDispatcher("novoServico.jsp").forward(request, response);
        } else if (acao.equals("editar")){
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            serv = js.exibir(id);
            
            request.setAttribute("servico", serv);
            request.getRequestDispatcher("novoServico.jsp").forward(request, response);
        } else if (acao.equals("deletar")){
            
            serv.setID(Integer.parseInt(request.getParameter("id")));
            
            js.deletar(serv.getID());
            msg = "deletado";
            
            response.sendRedirect("ServicoServlet?acao=listar&msg=" + msg);
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
