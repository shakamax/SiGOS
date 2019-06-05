/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCProduto;
import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.OldOS;
import br.com.sigos.model.ordemServico;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
        OldOS oldos = new OldOS();
        JDBCCliente jc = new JDBCCliente();
        JDBCServico js = new JDBCServico();
        JDBCProduto jp = new JDBCProduto();
        
        String acao = request.getParameter("acao");
        
        if(acao.equals("nova")){
            List<Cliente> clientes = new ArrayList<>();
            List<Servico> servicos = new ArrayList<>();
            List<Produto> produtos = new ArrayList<>();
            clientes = jc.Listar();
            servicos = js.listar();
            produtos = jp.listar();
            
            
            request.setAttribute("clientes", clientes);
            request.setAttribute("servicos", servicos);
            request.setAttribute("produtos", produtos);
            
            request.getRequestDispatcher("NovaOS.jsp").forward(request, response);
            
        }else if (acao.equals("inserir")){
            
        }
        
       
        
        
        
        
        
        //ISSO É O FORMATO PRA PEGAR AS DATAS.
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dataString = request.getParameter("os.DataAbertura"); 
        Date data = formato.parse(dataString);
        os.setDtAbertura(data);
        
        
        
        // Cliente
        cliente.setNome(request.getParameter("os.Cliente"));
        os.setCliente(cliente);
        //Lista de equipamentos
        lista.setEquipamento(request.getParameter("os.ListaEquipamentos"));
        os.setListaEquipamentos(lista);
        //Defeito
        lista.setDefeito(request.getParameter("os.Defeito"));
        
        
        //Este sou eu tentando colocar essa porra de array pra funcionar
        
        //salvei numa string para salvar todos os nomes vindos do formulário pegando o tamanho do array
        String[] prodParam = new String[request.getParameter("Produtos[]").length()];
        //criei uma variável para o loop
        int a = 0;
        //if para ver se tem algo no array
            //While para o loop e atribuir tudo
            
        while( (a > prodParam.length) && (prodParam[a] != "")){
            Produto prod = new Produto(); 
            //ArrayList<Produto> = new ArrayList<Produto>;
            //Produto<ArrayList> prod = new Produto();
            //
            prod.setNome(prodParam[a]);
            //os.setProdutos(prod);


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
