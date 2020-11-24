/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.jdbc.JDBCProduto;
import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Execucao;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.Usuario;
import br.com.sigos.model.ordemServico;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 04341474197
 */
public class AreaClienteServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
            
        Cliente cliente = new Cliente();
            JDBCCliente jc = new JDBCCliente();
            JDBCServico js = new JDBCServico();
            JDBCOrdemServico jos = new JDBCOrdemServico();
            JDBCProduto jp = new JDBCProduto();
            String msg = "";
            String tipo = "";
            LogOs log = new LogOs();
            Servico serv = new Servico();
            Produto prod = new Produto();
                
        String acao = request.getParameter("acao");
        if(acao.equals("nova"))
        {
            request.getRequestDispatcher("cadastroAreaCliente.jsp").forward(request, response);
        } else if(acao.equals("salvar"))
        {
                    
                    try {
                        cliente.setNome(request.getParameter("nome"));
                        cliente.setEmail(request.getParameter("email"));
                        
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
                            jc.inserir(cliente);
                            msg = "cadastrado";
                            tipo = "alert-success";
                        }
                        else
                        {
                            cliente.setId(Integer.parseInt(request.getParameter("id")));
                            
                            jc.alterar(cliente);
                        }
                        response.sendRedirect("AreaClienteServlet?acao=index&msg=" + msg);
                    } catch (ParseException ex) {
                        Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException("Erro ao salvar" + ex.getMessage(), ex);
                    }
        } else if(acao.equals("atualizar")){
            HttpSession sessao = request.getSession();
            Cliente c = (Cliente) sessao.getAttribute("cliente");
            int id = c.getId();
            c = jc.exibir(id);
            List<LogOs> logs = jc.buscarLogs(id);
            
            request.setAttribute("logs", logs);
            request.setAttribute("cliente", c);
            request.getRequestDispatcher("dadosCliente.jsp").forward(request, response);
        } else if(acao.equals("salvarMudancas")){
            
            cliente.setNome(request.getParameter("nome"));
            cliente.setEmail(request.getParameter("email"));

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

            cliente.setId(Integer.parseInt(request.getParameter("id")));
            int id = Integer.parseInt(request.getParameter("id"));
            cliente.setCEP(request.getParameter("cep"));
            cliente.setEstado(request.getParameter("uf"));
            cliente.setCidade(request.getParameter("cidade"));
            cliente.setBairro(request.getParameter("bairro"));
            cliente.setComplemento(request.getParameter("complemento"));
            cliente.setLogradouro(request.getParameter("logradouro")); 
            cliente.setStatus(true);
            
            jc.alterar(cliente);
            cliente = jc.exibir(id);
            Cliente c = new Cliente();
            c.setId(cliente.getId());
            c.setNome(cliente.getNome());
            c.setSenha(cliente.getSenha());
            c.setEmail(cliente.getEmail());
            
            HttpSession sessao = request.getSession();
            sessao.setAttribute("cliente", c);
            
            msg = "Dados atualizados com sucesso.";
            tipo = "alert-success";
            
            response.sendRedirect("AreaClienteServlet?acao=dash&msg=" + msg + "&tipo=" + tipo);
        } else if(acao.equals("dash")){
            Cliente c = new Cliente();
            HttpSession sessao = request.getSession();
            c =  (Cliente) sessao.getAttribute("cliente");
            
            
            if(request.getParameter("msg") != ""){
                msg = request.getParameter("msg");
                tipo = request.getParameter("tipo");
                request.setAttribute("msg", msg);
                request.setAttribute("tipo", tipo);
            }
            
            
            
            List<LogOs> logs = jc.buscarLogs(c.getId());
            request.setAttribute("logs", logs);
            request.getRequestDispatcher("homeCliente.jsp").forward(request, response);
        } else if(acao.equals("ordens")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            List<ordemServico> ordens = new ArrayList<>();
            ordens = jc.listarOs(c.getId());
            System.out.println(c.getId());
            List<LogOs> logs = jc.buscarLogs(c.getId());
            
            request.setAttribute("logs", logs);
            
            request.setAttribute("ordens", ordens);
            request.getRequestDispatcher("minhasOrdens.jsp").forward(request, response);
        } else if(acao.equals("minhaOrdem")){
            Cliente c =  new Cliente();
            System.out.println("Aqui ele faz o cliente");
            HttpSession s = request.getSession();
            System.out.println("Aqui ele pega a Session");
            c = (Cliente) s.getAttribute("cliente");
            System.out.println("Aqui ele seta o cliente ocmo atributo que pegou da session");
            int idOs = Integer.parseInt(request.getParameter("id"));
            System.out.println("Aqui ele pega e parseia o id da os.");
            ordemServico os = jos.exibe(idOs);
            System.out.println("Aqui ele pega a os de acordo com o que ele pesquisa o id.");
            List<LogOs> logs = jc.buscarLogs(c.getId());
            System.out.println("Aqui ele pega os logs de novo do cliete.");
            
            System.out.println("AQUI EYU TENTO PEGAR MENSAGEM.  " + request.getParameter("msg"));
            if(request.getParameter("msg") != null){
                msg = request.getParameter("msg");
                
                if(msg.equals("ok")){
                    msg = "Serviço autorizado com Sucesso.";
                    tipo = "alert-success";
                    System.out.println(msg);
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                    
                } else if(msg.equals("falhaauto")){
                    msg = "Serviço já foi realizado, não é possível retirar sua autorização.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                   
                } else if(msg.equals("desautorizado")){
                    msg = "Serviço foi desautorizado com sucesso.";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                    
                } else if(msg.equals("pa")){
                    msg = "Produto autorizado com sucesso.";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                } else if(msg.equals("falhaautop")){
                    msg = "Produto já foi instalado, entre em contato com nossos atendentes.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                } else if(msg.equals("desautorizadoProd")){
                    msg = "Produto foi desautorizado com sucesso.";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
                
                System.out.println(msg);
                
            }

            
            
            request.setAttribute("logs", logs);
            request.setAttribute("os", os);
            request.getRequestDispatcher("minhaOrdem.jsp").forward(request, response);
        } else if(acao.equals("produtos")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            List<LogOs> logs = jc.buscarLogs(c.getId());
            List<Produto> produtos = jp.listar();
            
            request.setAttribute("logs", logs);
            request.setAttribute("prods", produtos);
            request.getRequestDispatcher("verProdutos.jsp").forward(request, response);
            
        } else if(acao.equals("detalhes")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            int id = Integer.parseInt(request.getParameter("id"));
            prod = jp.exibir(id);
            List<LogOs> logs = jc.buscarLogs(c.getId());
            
            request.setAttribute("prod", prod);
            request.setAttribute("logs", logs);
            request.getRequestDispatcher("detalhesProduto.jsp").forward(request, response);
            
        } else if(acao.equals("autorizarServ")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            int numOs = Integer.parseInt(request.getParameter("os"));
            int idserv = Integer.parseInt(request.getParameter("serv"));
            jc.autorizaServico(numOs, idserv);
            serv = js.exibir(idserv);
            log.setDescricao("Serviço " + serv.getNome() + " foi autorizado pelo cliente.");
            log.setIdOs(numOs);
            jos.inserirLog(log);
            msg = "ok";
            
            response.sendRedirect("AreaClienteServlet?acao=minhaOrdem&id=" + numOs + "&msg=" + msg);
            
        } else if(acao.equals("mudeiDeIdeia")){
            
            int numOs = Integer.parseInt(request.getParameter("os"));
            int idserv = Integer.parseInt(request.getParameter("serv"));
            Execucao exc = new Execucao();
            exc = jc.pegarExec(numOs, idserv);
            if(exc.isStatus()){
                msg = "falhaauto";
            }else{
                jc.desautorizaServico(numOs, idserv);
                serv = js.exibir(idserv);
                log.setDescricao("O serviço " + serv.getNome() + " foi desautorizado");
                log.setIdOs(numOs);
                jos.inserirLog(log);
                msg = "desautorizado";
            }
            
            response.sendRedirect("AreaClienteServlet?acao=minhaOrdem&id=" + numOs + "&msg=" + msg);
            
        } else if(acao.equals("autorizarProd")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            
            int numOs = Integer.parseInt(request.getParameter("os"));
            int idprod = Integer.parseInt(request.getParameter("prod"));
            
            jc.autorizaProduto(numOs, idprod);
            prod = jp.exibir(idprod);
            log.setDescricao("Produto " + prod.getNome() + " foi autorizado pelo cliente.");
            log.setIdOs(numOs);
            jos.inserirLog(log);
            msg = "pa";
            
            response.sendRedirect("AreaClienteServlet?acao=minhaOrdem&id=" + numOs + "&msg=" + msg);
        } else if(acao.equals("mudeiDeIdeiap")){
            
            int numOs = Integer.parseInt(request.getParameter("os"));
            int idprod = Integer.parseInt(request.getParameter("prod"));
            prod = jc.pegarVendaProd(numOs, idprod);
            
            if(prod.isStatus()){
                msg = "falhaautop";
            }else{
                jc.desautorizarProd(numOs, idprod);
                log.setDescricao("O Produto " + prod.getNome() + " foi desautorizado");
                log.setIdOs(numOs);
                jos.inserirLog(log);
                msg = "desautorizadoProd";
            }
            
            response.sendRedirect("AreaClienteServlet?acao=minhaOrdem&id=" + numOs + "&msg=" + msg);
            
        } else if(acao.equals("minhaConta")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");   
            List<LogOs> logs = jc.buscarLogs(c.getId());      
            
            msg = request.getParameter("msg");
            if(msg != null){
                
                if(msg.equals("senhaMudada")){
                    msg = "Sua senha foi alterada com sucesso.";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("senhaErrada")){
                    msg = "Ops, parece que você errou sua senha, tente novamente.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("emailAtt")){
                    msg = "E-mail foi atualizado com sucesso.";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("emailDif")){
                    msg = "Confirmação do e-mail falhou, por favor tente novamente.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("emailTrocado")){
                    msg = "Seus e-mails foram trocados.";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
                
            }
            
            Cliente cli = new Cliente();
            cli = jc.exibir(c.getId());
            if(cli.getEmailAlternativo() != null){
                request.setAttribute("email", cli.getEmailAlternativo());
            }
            
            
            request.setAttribute("logs", logs);
            request.getRequestDispatcher("minhaConta.jsp").forward(request, response);
        } else if(acao.equals("mudarSenha")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");   
            List<LogOs> logs = jc.buscarLogs(c.getId());           
            
            request.setAttribute("logs", logs);
            request.getRequestDispatcher("mudarSenha.jsp").forward(request, response);
            
        } else if(acao.equals("mudarEmail")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
//            Cliente cli = new Cliente();
//            cli = jc.exibir(c.getId());
            
            String antigoEmail = request.getParameter("email");
            if(c.getEmail() == antigoEmail){
                String emailAtual = request.getParameter("emailNovo");
                jc.mudarEmail(c.getId(), emailAtual);
                msg = "emailAtt";
            } else {
                msg = "emailDif";
            }
            
            response.sendRedirect("AreaClienteServlet?acao=minhaConta&msg=" + msg);
        } else if(acao.equals("password")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            
            Usuario user = new Usuario();
            String se = request.getParameter("senhaAtual");
            String senha = user.criptografar(se);
            Boolean senhaCorreta = jc.confirmarSenha(c.getEmail(), senha);
            
            if(senhaCorreta){
                String Sen = request.getParameter("senha");
                String novaSenha = user.criptografar(Sen);
                jc.mudarSenha(novaSenha, cliente.getId());
                
                msg = "senhaMudada";
            } else {
                msg = "senhaErrada";
            }
            
            response.sendRedirect("AreaClienteServlet?acao=minhaConta&msg=" + msg);
        } else if(acao.equals("emailPS")){
            Cliente c =  new Cliente();
            HttpSession s = request.getSession();
            c = (Cliente) s.getAttribute("cliente");
            List<LogOs> logs = jc.buscarLogs(c.getId());
            Cliente cl = new Cliente();
            cl = jc.exibir(c.getId());
            
            String email = cl.getEmail();
            String email2 = cl.getEmailAlternativo();
            
            cl.setEmail(email2);
            cl.setEmailAlternativo(email);
            
            jc.alterarEmail(cl);
            
            msg = "emailTrocado";
            
            response.sendRedirect("AreaClienteServlet?acao=minhaConta&msg=" + msg);
        }else if(acao.equals("autenticarCliente")){
            int id = Integer.parseInt(request.getParameter("id"));
            
            cliente = jc.exibir(id);
            
            if(cliente.getStatus()){
                response.sendRedirect("index.jsp");
            }else {
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("autenticSenha.jsp").forward(request, response);
            }
            
        }else if(acao.equals("autanticar")){
            int id = Integer.parseInt(request.getParameter("id"));
            String s = request.getParameter("senha");
            Usuario user = new Usuario();
            String senha = user.criptografar(s);
            jc.cadastrarSenha(senha, id);
            msg = "autenticado";
            response.sendRedirect("AreaClienteServlet?acao=index&msg=" + msg);
        }else if(acao.equals("index")){
            
            msg = request.getParameter("msg");
            if(msg.equals("cadastrado")){
                msg = "Um e-mail foi enviado para a finalização do seu cadastro!";
                tipo = "alert-success";
            }else if(msg.equals("autenticado")){
                msg = "Você foi autenticado, agora poderá já está pronto para logar no SiGOS.";
                tipo = "alert-success";
            }
            request.setAttribute("msg", msg);
            request.setAttribute("tipo", tipo);
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AreaClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AreaClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
