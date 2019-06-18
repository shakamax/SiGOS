/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCFuncionario;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Funcionario;
import br.com.sigos.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Guilherme
 */
public class LoginServlet extends HttpServlet {

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
        HttpSession sessao = request.getSession();
        String acao = request.getParameter("acao");
        
        if (acao.equals("login")){
            Funcionario user = new Funcionario();
            JDBCFuncionario jf = new JDBCFuncionario();
            JDBCCliente jc = new JDBCCliente();
            String email = request.getParameter("logemail");
            String senha = request.getParameter("logpassword");
            Usuario u = new Usuario();
            String senhac = u.criptografar(senha);
            
            user = jf.logar(email, senhac);
           
            if(user.getNome().equals("")){
                Cliente cliente = new Cliente();
                
                cliente = jc.logar(email, senhac);
                
                if (cliente.getNome().equals("")){
                    Boolean msg = true;
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                else {
                    sessao.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("homeCliente.jsp").forward(request, response);
                }
//                Boolean msg = true;
//                request.setAttribute("msg", msg);
//                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                
                sessao.setAttribute("user", user);
                request.getRequestDispatcher("home.jsp").forward(request, response);
                
            } 
           
        } else if(acao.equals("logout") || acao.equals("")){
            sessao.invalidate();
            response.sendRedirect("index.jsp");
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
