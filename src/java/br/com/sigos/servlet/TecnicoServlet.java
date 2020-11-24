/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;


import br.com.sigos.jdbc.JDBCEquipamento;
import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Email;
import br.com.sigos.model.Execucao;
import br.com.sigos.model.Funcionario;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.ordemServico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Guilherme
 */
@WebServlet(name = "TecnicoServlet", urlPatterns = {"/TecnicoServlet"})
public class TecnicoServlet extends HttpServlet {

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
        
        Funcionario func = new Funcionario();
        HttpSession sessao = request.getSession();
        func = (Funcionario) sessao.getAttribute("user");
        JDBCOrdemServico jos = new JDBCOrdemServico();
        JDBCServico js = new JDBCServico();
        Execucao exec = new Execucao();
        Servico se = new Servico();
        LogOs log = new LogOs();
        Email em = new Email();
        Cliente cliente = new Cliente();
        String msg = "";
        String tipo = "";
        
        String acao;
        acao = request.getParameter("acao");
        
        
        if(acao.equals("listar") || acao.equals("")){
            List<ordemServico> ordens = new ArrayList<>();
            
            
            msg = request.getParameter("msg");
            if(request.getParameter("msg") != null){
                if(msg.equals("statusRealizado")){
                    msg = "Status de serviço mudou para realizado!";
                    tipo = "alert-success";
                } else if(msg.equals("retirado")){
                    msg = "Serviço foi retirado da OS.";
                    tipo = "alert-warning";
                } else if(msg.equals("servicoError")){
                    msg = "Nao e possivel retirar um servico realizado.";
                    tipo = "alert-danger";
                }
                request.setAttribute("msg", msg);
                request.setAttribute("tipo", tipo);
            }
            
            
            ordens = jos.listarComServ();
            
            request.setAttribute("ordens", ordens);
            request.getRequestDispatcher("tecServicos.jsp").forward(request, response);
        } else if(acao.equals("realizarServico")){
            
            double v = 0;
            int idos = Integer.parseInt(request.getParameter("idos"));
            int idserv = Integer.parseInt(request.getParameter("id"));
            func = (Funcionario) sessao.getAttribute("user");
            se = js.exibir(idserv);
            
            exec.setIdOs(idos);
            exec.setIdServ(idserv);
            log.setIdOs(idos);
            log.setDescricao("O Serviço \" " + se.getNome() + " \" foi realizado por " + func.getNome());
            
            jos.alterarExec(exec);
            jos.inserirLog(log);
            
            cliente.setEmail(jos.pegarEmailOs(idos));
            //envio de notificação
            em.setDestinatario(cliente.getEmail());
            em.setAssunto("SiGOS Cloud - OS Atualizada");
            em.setTexto("<h1>"+ log.getDescricao() + "  <strong> :( </strong> </h1> <br> <p> Nos agradecemos a preferência, e se caso tenha alguma dúvida, entre em contato. </p>" );
            em.enviarGmail();
            
            msg = "statusRealizado";
            
            ordemServico os = jos.exibe(idos);
            for(Servico s: os.getServicos()){
                if(s.isStatus()){
                    v += s.getValor();
                }
            }
            for(Produto pr: os.getProdutos()){
                if(pr.isAutorizado()){
                    v += pr.getValor();
                }
            }
            os.setValorTotal(v);
            jos.atualizarValor(os);
            
            response.sendRedirect("TecnicoServlet?acao=listar&msg=" + msg);
        } else if(acao.equals("excluirServico")){
            int idos = Integer.parseInt(request.getParameter("idos"));
            int idserv = Integer.parseInt(request.getParameter("id"));
            func = (Funcionario) sessao.getAttribute("user");
            msg = "";
            tipo = "";
            se = js.exibir(idserv);
            if(!se.isStatus()){
                exec.setIdOs(idos);
                exec.setIdServ(idserv);

                jos.excluirExec(exec);
                log.setIdOs(idos);
                log.setDescricao("O Serviço \" " + se.getNome() + " \" foi retirado por " + func.getNome());
                jos.inserirLog(log);
                
                
                cliente.setEmail(jos.pegarEmailOs(idos));
                //envio de notificação
//                em.setDestinatario(cliente.getEmail());
//                em.setAssunto("SiGOS Cloud - OS Atualizada");
//                em.setTexto("<h1>"+ log.getDescricao() + "  <strong> :( </strong> </h1> <br> <p> Nos agradecemos a preferência, e se caso tenha alguma dúvida, entre em contato. </p>" );
//                em.enviarGmail();
                
                msg = "retirado";
            } else {
                msg = "servicoError";
            }
            response.sendRedirect("TecnicoServlet?acao=listar&msg=" + msg);
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
