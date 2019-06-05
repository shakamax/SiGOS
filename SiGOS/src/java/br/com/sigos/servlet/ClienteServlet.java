/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.model.Cliente;
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
public class ClienteServlet extends HttpServlet {

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
            
                Cliente cliente = new Cliente();
                JDBCCliente jc = new JDBCCliente();
                
        String acao = request.getParameter("acao");
        if(acao.equals("nova"))
        {
            request.getRequestDispatcher("novoCliente.jsp").forward(request, response);
        }
        else if(acao.equals("listar") || acao.isEmpty())
        {

            List<Cliente> clientes = jc.Listar();

            request.setAttribute("clientes", clientes);

            request.getRequestDispatcher("listaClientes.jsp").forward(request, response);
        }
        else if(acao.equals("deletar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            jc.deletar(id);
            
            
            response.sendRedirect("ClienteServlet?acao=listar");
        }
        else if(acao.equals("editar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            cliente = jc.exibir(id);
            
            request.setAttribute("cliente", cliente);
            
            request.getRequestDispatcher("novoCliente.jsp").forward(request, response);
            
        }
        else if(acao.equals("salvar"))
        {
                    
            cliente.setNome(request.getParameter("nome"));
            cliente.setEmail(request.getParameter("email"));

            cliente.setTelefone(request.getParameter("telefone"));
            cliente.setCPF(request.getParameter("cpf"));
            cliente.setEmailAlternativo(request.getParameter("emailAlternativo"));
            //cliente.setDtCadastro("dtCadastro");
                
                //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                //String dataString = request.getParameter("dtNascimento");
                //if (dataString != null) {
                //cliente.setDtNascimento(formato.parse(dataString));
                //};
            cliente.setStatus(Boolean.FALSE);
            cliente.setCEP(request.getParameter("cep"));
            cliente.setEstado(request.getParameter("uf"));
            cliente.setCidade(request.getParameter("cidade"));
            cliente.setBairro(request.getParameter("bairro"));
            cliente.setComplemento(request.getParameter("complemento"));
            cliente.setLogradouro(request.getParameter("logradouro"));
            

            
            
            if(request.getParameter("id").equals("0"))
            {
                jc.inserir(cliente);
            }
            else
            {
                cliente.setId(Integer.parseInt(request.getParameter("id")));
                
                jc.alterar(cliente);
            }
            
            response.sendRedirect("ClienteServlet?acao=listar");
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
