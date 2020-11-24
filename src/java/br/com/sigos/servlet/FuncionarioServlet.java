/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCFuncionario;
import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.model.Funcionario;
import br.com.sigos.model.Usuario;
import br.com.sigos.model.relatorioDash;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String msg = "";
        String tipo = "";
        
        String acao = request.getParameter("acao");
        if (acao.equals ("nova")) {
            
            request.getRequestDispatcher("novoFuncionario.jsp").forward(request, response);
        } else if(acao.equals("listar") || acao.isEmpty())
        {
            List<Funcionario> funcionarios = jf.listar();
            
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("inserido")){
                    msg = "Funcionário cadastrado com sucesso.";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("deletado")){
                    msg = "Funcionário excluído com sucesso.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("alterado")){
                    msg = "Funcionário alterado com sucesso.";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
            }
            
            request.setAttribute("funcionarios", funcionarios);

            request.getRequestDispatcher("listaFuncionarios.jsp").forward(request, response);
        } else if(acao.equals("deletar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            jf.deletar(id);
            msg = "deletado";
            
            response.sendRedirect("FuncionarioServlet?acao=listar&msg=" + msg);
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
        String senha = request.getParameter("senha");
        Usuario user = new Usuario();
        String senhac = user.criptografar(senha);
        funcionario.setSenha(senhac);
                
        
        if(request.getParameter("id").equals("0"))
            {
                jf.inserir(funcionario);
                msg = "inserido";
            }
            else
            {
                funcionario.setId(Integer.parseInt(request.getParameter("id")));
                
                jf.alterar(funcionario);
                msg = "alterado";
            }
        
            response.sendRedirect("FuncionarioServlet?acao=listar&msg=" + msg);
        
        } else if(acao.equals("dash")){
            relatorioDash rd = new relatorioDash();
            JDBCOrdemServico jos = new JDBCOrdemServico();
            rd.setQtdMax(jos.qtdMaxOs());
            rd.setQtdCanceladas(jos.qtdCanceladas());
            rd.setQtdAbertas(jos.qtdAbertas());
            rd.setQtdFechadas(jos.qtdFechadas());
            rd.calcularPorcentagem();
            
            request.setAttribute("rd", rd);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }else if(acao.equals("suaConta")){
            HttpSession s = request.getSession();
            funcionario = (Funcionario) s.getAttribute("user");
            
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("emailAlt")){
                    msg = "E-mail foi alterado com sucesso!";
                    tipo = "alert-success";
                }else if (msg.equals("emailError")){
                    msg = "Erro na confirmação de e-mail.";
                    tipo = "alert-danger";
                }else if (msg.equals("senhaTrocada")){
                    msg = "Sua senha foi alterada com sucesso.";
                    tipo = "alert-success";
                }
                request.setAttribute("msg", msg);
                request.setAttribute("tipo", tipo);
            }
            
            
            request.setAttribute("func", funcionario);
            request.getRequestDispatcher("contaFuncionario.jsp").forward(request, response);
            
        }else if(acao.equals("mudarSenha")){
            HttpSession s = request.getSession();
            funcionario = (Funcionario) s.getAttribute("user");
            
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("errorConfirm")){
                    msg = "Ops, parece que você errou sua senha, tente novamente.";
                    tipo = "alert-danger";
                }
                request.setAttribute("msg", msg);
                request.setAttribute("tipo", tipo);
            }

            request.setAttribute("func", funcionario);
            request.getRequestDispatcher("senhaFunc.jsp").forward(request, response);
        }else if(acao.equals("mudarEmail")){
            HttpSession s = request.getSession();
            funcionario = (Funcionario) s.getAttribute("user");
            
            Funcionario func = jf.exibir(funcionario.getId());
            
            if(func.getEmail() == funcionario.getEmail()){
                funcionario.setEmail(request.getParameter("emailNovo"));
                jf.alterar(funcionario);
                msg = "emailAlt";
            }else {
                
                msg = "emailError";
            }
            response.sendRedirect("FuncionarioServlet?acao=suaConta&msg=" + msg);
        } else if(acao.equals("password")){
            HttpSession s = request.getSession();
            funcionario = (Funcionario) s.getAttribute("user");
            
            
            String senhaAtual = request.getParameter("senhaAtual");
            Usuario u = new Usuario();
            String senha = u.criptografar(senhaAtual);
            
            Boolean confirmaSenha = jf.confirmarSenha(senha, funcionario.getId());
            if(confirmaSenha){
                String se = request.getParameter("senha");
                String novaSenha = u.criptografar(se);
                
                funcionario.setSenha(novaSenha);
                jf.alterar(funcionario);
                
                msg = "senhaTrocada";
                response.sendRedirect("FuncionarioServlet?acao=suaConta&msg=" + msg);
            } else {
                msg = "errorConfirm";
                response.sendRedirect("FuncionarioServlet?acao=mudarSenha&msg=" + msg);
            }
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
