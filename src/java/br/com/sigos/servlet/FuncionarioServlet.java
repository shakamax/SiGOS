/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCFuncionario;
import br.com.sigos.model.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 03728827142
 */
public class FuncionarioServlet extends HttpServlet {

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
        
        JDBCFuncionario jf = new JDBCFuncionario();
        Funcionario funcionario = new Funcionario();
        
        String acao = request.getParameter("acao");
        if (acao.equals ("nova")) {
            
            request.getRequestDispatcher("novoFuncionario.jsp").forward(request, response);
        } else if(acao.equals("listar") || acao.isEmpty())
        {
            List<Funcionario> funcionarios = jf.listar();

            request.setAttribute("funcionarios", funcionarios);

            request.getRequestDispatcher("listaFuncionarios.jsp").forward(request, response);
        } else if(acao.equals("deletar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            jf.deletar(id);
            
            response.sendRedirect("FuncionarioServlet?acao=listar");
        } else if(acao.equals("editar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            funcionario = jf.exibir(id);
            
            request.setAttribute("funcionario", funcionario);
            
            request.getRequestDispatcher("novoFuncionario.jsp").forward(request, response);
            
        } else if(acao.equals("salvar"))
        {
                       
        funcionario.setNome(request.getParameter("nome"));
        funcionario.setFuncao(request.getParameter("funcao"));
        funcionario.setEmail(request.getParameter("email"));
        funcionario.setSenha(request.getParameter("senha"));
                
        
        if(request.getParameter("id").equals("0"))
            {
                jf.inserir(funcionario);
                
            }
            else
            {
                funcionario.setId(Integer.parseInt(request.getParameter("id")));
                
                jf.alterar(funcionario);
            }
        
            response.sendRedirect("FuncionarioServlet?acao=listar");
        
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
