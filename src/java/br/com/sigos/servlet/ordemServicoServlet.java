/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCEquipamento;
import br.com.sigos.jdbc.JDBCFuncionario;
import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.jdbc.JDBCProduto;
import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Execucao;
import br.com.sigos.model.Funcionario;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.ordemServico;
import br.com.sigos.model.vendaProduto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 03728827142
 */
public class ordemServicoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException {
        //Criação de todas as classes necessárias
        Cliente cliente = new Cliente();
        ListaEquipamento lista = new ListaEquipamento();
        ordemServico os = new ordemServico();
        Servico serv = new Servico();
        ordemServico oldos = new ordemServico();
        JDBCCliente jc = new JDBCCliente();
        JDBCServico js = new JDBCServico();
        JDBCProduto jp = new JDBCProduto();
        JDBCFuncionario jf = new JDBCFuncionario();
        JDBCEquipamento jl = new JDBCEquipamento();
        JDBCOrdemServico jos = new JDBCOrdemServico();
        vendaProduto vendProd = new vendaProduto();
        Execucao exec = new Execucao();
        LogOs log = new LogOs();
        Funcionario func = new Funcionario();
        
        HttpSession sessao = request.getSession();
        
        
        String acao = request.getParameter("acao");
        
        if(acao.equals("nova")){
            Cliente clientes = new Cliente();
            List<Servico> servicos = new ArrayList<>();
            List<Produto> produtos = new ArrayList<>();
            List<ListaEquipamento> listaEq = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            cliente = jc.exibir(id);
            listaEq = jl.Listar(id);
            servicos = js.listar();
            produtos = jp.listar();
            
            request.setAttribute("listas", listaEq);
            request.setAttribute("cliente", cliente);
            request.setAttribute("servicos", servicos);
            request.setAttribute("produtos", produtos);
            
            request.getRequestDispatcher("NovaOS.jsp").forward(request, response);
            
        }else if (acao.equals("clientes")){
            List<Cliente> clientes = new ArrayList<>();
            clientes = jc.Listar();
            
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("clienteParaOs.jsp").forward(request, response);            
        } else if (acao.equals("mudarLista")){
             
            int idLista = Integer.parseInt(request.getParameter("id"));
            lista = jl.exibir(idLista);
            
            String defeito = lista.getDefeito();
            if (defeito == null){
                defeito = "Nenhum";
            }
            try (PrintWriter out = response.getWriter()) {
                out.print(defeito);
            }
            
            
            
            // --------------CRIAR OS -----------------
        } else if (acao.equals("criarOS")) {
        

            // Cliente
            int idcliente = Integer.parseInt(request.getParameter("idcliente"));
            cliente = jc.exibir(idcliente);
            os.setCliente(cliente);
            
            //Lista de equipamentos
            int idlista = Integer.parseInt(request.getParameter("ListaEquipamentos"));
            lista = jl.exibir(idlista);
            //DEFEITO será salvo e alterado já.
            lista.setDefeito(request.getParameter("defeito"));
            jl.alterar(lista);
            os.setListaEquipamentos(lista);
            int intOs = jos.inserir(os);
            
            vendProd.setIdOs(intOs);
            
            String[] prod = request.getParameterValues("Produtos[]");
            
            if (prod != null) {
                
                for(int a = 0; a < prod.length; a++){
                    Produto produto = new Produto();
                    produto = jp.exibir(Integer.parseInt(prod[a]));
                    
                    vendProd.setProduto(produto);
                    vendProd.setQtd(1);
                    vendProd.setValor(produto.getValor());
                    jos.inserirProd(vendProd);
                }
            }
            
            exec.setIdOs(intOs);
            String[] servs = request.getParameterValues("Servicos[]");
            
            if (servs != null) {
                
                for (int a = 0; a < servs.length; a++) {
                    exec.setIdServ(Integer.parseInt(servs[a]));
                    exec.setIdTecnico(1);
                    
                    jos.inserirExec(exec);
                    
                }
                
            }
            
            log.setIdOs(intOs);
            
            //int id = Integer.parseInt(request.getParameter("idfunc"));
            //func = jf.exibir(id);
            func = (Funcionario) sessao.getAttribute("user");
            
            log.setDescricao("OS criada por " + func.getNome());
            
            jos.inserirLog(log);
            
            
            
            response.sendRedirect("ordemServicoServlet?acao=listar");
            
            
            //-------BUSCA NO BANCO DE DADOS----------
        } else if (acao.equals("buscar")) {
            String coluna = request.getParameter("coluna");
            String busca = request.getParameter("busca");
            
            //busca = "'%"+busca+"%'";
            System.out.println(coluna);
            System.out.println(busca);
            System.out.println(coluna);
            
            List<Cliente> clientes = jc.buscar(coluna, busca);
            
            
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("clienteParaOs.jsp").forward(request, response); 
            
            
            //-----------NOVO EQUIPAMENTO------------
        } else if (acao.equals("novoEq")) {
            
            
            lista.setEquipamento(request.getParameter("equipamento"));
            lista.setAcessório(request.getParameter("acessorios"));
            lista.setObservacao(request.getParameter("observacao"));
            lista.setDefeito(request.getParameter("defeito"));
            lista.setFk_cliente(Integer.parseInt(request.getParameter("fk_cliente")));
            jl.inserir(lista);
            response.sendRedirect("ordemServicoServlet?acao=nova&id=" + lista.getFk_cliente());
            
            //---------NOVO SERVIÇO---------
        } else if (acao.equals("novoServ")) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            serv.setNome(request.getParameter("nome"));
            serv.setValor(Double.parseDouble(request.getParameter("valor").replace(".", "").replace("," , ".")));
            serv.setDescricao(request.getParameter("descricao"));
                
            js.inserir(serv);
            response.sendRedirect("ordemServicoServlet?acao=nova&id=" + id);

            
            
            
            
            
            //--------LISTAR ORDENS DE SERVIÇOS----------
        } else if ((acao.equals("")) || acao.equals("listar")) {
            
            List<ordemServico> listaOS = new ArrayList<>();
            
            listaOS = jos.listar();
            os = listaOS.get(0);
            System.out.println(os.getLog().getDataHora());
            
            
            request.setAttribute("listaOS", listaOS);
            
            request.getRequestDispatcher("listaOS.jsp").forward(request, response);
            
            
            
            
            //-----------------GERENCIAR ORDEM DE SERVICO-----------------------
        } else if (acao.equals("gerenciar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            os = jos.exibe(id);
            List<Produto> prods = new ArrayList<>();
            prods = jp.listar();
            List<Servico> servs = new ArrayList<>();
            servs = js.listar();
            
            
            request.setAttribute("servs", servs);
            request.setAttribute("prods", prods);
            request.setAttribute("os", os);
            request.getRequestDispatcher("gerenciarOS.jsp").forward(request, response);
            
            
            
            //-----------------------FECHAMENTO?----------------------- 
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
            Logger.getLogger(ordemServicoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ordemServicoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
