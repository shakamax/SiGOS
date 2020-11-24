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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            String msg = "";
            String tipo = "";
                
            String acao = request.getParameter("acao");
            if(acao.equals("nova"))
            {
                request.getRequestDispatcher("novoCliente.jsp").forward(request, response);
            }
            else if(acao.equals("listar") || acao.isEmpty())
            {

                List<Cliente> clientes = jc.Listar();
                
                msg = request.getParameter("msg");
                
                if(msg != null){
                    if(msg.equals("inserido")){
                        msg = "Cliente cadastrado com sucesso!";
                        tipo = "alert-success";
                        request.setAttribute("msg", msg);
                        request.setAttribute("tipo", tipo);
                    }else if(msg.equals("deletado")){
                        msg = "Cliente foi excluído com sucesso.";
                        tipo = "alert-danger";
                        request.setAttribute("msg", msg);
                        request.setAttribute("tipo", tipo);
                    }else if(msg.equals("alterado")){
                        msg = "Cadastro de cliente foi atualizado com sucesso.";
                        tipo = "alert-warning";
                        request.setAttribute("msg", msg);
                        request.setAttribute("tipo", tipo);
                    }else if(msg.equals("errordel")){
                        msg = "Clientes autenticados não podem ser excluídos.";
                        tipo = "alert-danger";
                        request.setAttribute("msg", msg);
                        request.setAttribute("tipo", tipo);  
                    }
                }


                request.setAttribute("clientes", clientes);

                request.getRequestDispatcher("listaClientes.jsp").forward(request, response);
            }
            else if(acao.equals("deletar"))
            {
                int id = Integer.parseInt(request.getParameter("id"));
                cliente = jc.exibir(id);
                if(cliente.getStatus()){
                    msg = "errordel";
                }else {
                    jc.deletar(id);
                    msg = "deletado";
                }

                response.sendRedirect("ClienteServlet?acao=listar&msg=" + msg);
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

                        try {
                            cliente.setNome(request.getParameter("nome"));


                            cliente.setTelefone(request.getParameter("telefone"));
                            cliente.setCPF(request.getParameter("cpf"));
                            cliente.setEmailAlternativo(request.getParameter("emailAlt"));
                            //cliente.setSenha(request.getParameter("senha"));

                            //Date formatar = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtCadastro"));

                            //if (formatar != null) {
                                //cliente.setDtCadastro(formatar);
                            //} else {
                                //formatar = new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01");
                                //cliente.setDtCadastro(formatar);
                                //};

                            String data = request.getParameter("dataNascimento");

                            if (!data.equals("")){
                              Date formato = new SimpleDateFormat("yyyy-MM-dd").parse(data);
                              cliente.setDtNascimento(formato);
                            } else {
                                Date formato = new SimpleDateFormat("yyyy-MM-dd").parse("1910-01-01");
                                cliente.setDtNascimento(formato);
                            }



                            /*if (formato != null) {
                                cliente.setDtNascimento(formato);
                            } else {
                                formato = new SimpleDateFormat("yyyy-MM-dd").parse("1910-01-01");
                                cliente.setDtNascimento(formato);
                                };*/


                            cliente.setStatus(Boolean.FALSE);
                            cliente.setCEP(request.getParameter("cep"));
                            cliente.setEstado(request.getParameter("uf"));
                            cliente.setCidade(request.getParameter("cidade"));
                            cliente.setBairro(request.getParameter("bairro"));
                            cliente.setComplemento(request.getParameter("complemento"));
                            cliente.setLogradouro(request.getParameter("logradouro"));




                            if(request.getParameter("id").equals("0"))
                            {
                                cliente.setEmail(request.getParameter("email"));
                                jc.inserir(cliente);
                                msg = "inserido";
                            }
                            else
                            {
                                cliente.setId(Integer.parseInt(request.getParameter("id")));

                                Cliente c2 = new Cliente();
                                c2 = jc.exibir(cliente.getId());
                                if(c2.getStatus()){
                                    cliente.setEmail(c2.getEmail());

                                }else{
                                    cliente.setEmail(request.getParameter("email"));
                                }

                                jc.alterar(cliente);
                                msg = "alterado";
                            }
                            response.sendRedirect("ClienteServlet?acao=listar&msg=" + msg);
                        } catch (ParseException ex) {
                            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                            throw new RuntimeException("Erro ao salvar" + ex.getMessage(), ex);
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
