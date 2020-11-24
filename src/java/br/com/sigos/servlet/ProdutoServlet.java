/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCategoria;
import br.com.sigos.jdbc.JDBCProduto;
import br.com.sigos.model.Categoria;
import br.com.sigos.model.Produto;
import br.com.sigos.model.ProdutosCategorias;
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
public class ProdutoServlet extends HttpServlet {

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
        
        int idProduto;
        JDBCProduto jp = new JDBCProduto();
        JDBCCategoria cp = new JDBCCategoria();
        Produto P = new Produto();
        Categoria cat = new Categoria();
        ProdutosCategorias prodCat = new ProdutosCategorias();
        String msg = "";
        String tipo = "";
        
        String acao = request.getParameter("acao");
        if (acao.equals("salvar")){
            
            P.setNome(request.getParameter("nome"));
            P.setCodigo(request.getParameter("codigo"));
            P.setDescricao(request.getParameter("descricao"));
            String qtd = request.getParameter("quantidade");
            P.setQuantidade(Integer.parseInt(qtd));
            P.setValor(Double.parseDouble(request.getParameter("valor").replace(".", "").replace("," , ".")));
            P.setLocalizacao(request.getParameter("localizacao"));
            
            
            if ( request.getParameter("id").equals("0")) {
                
                idProduto = jp.inserir(P);
                msg = "inserido";
                
                prodCat.setIdProduto(idProduto);

                String[] c = request.getParameterValues("cat[]");
                
                if (!c.equals("")){

                    for(int a = 0; a < c.length; a++)
                    {

                        prodCat.setIdCategoria(Integer.parseInt(c[a]));

                        jp.inserirCategoria(prodCat);
                    }

                }
                
                
            } else {
                
                P.setID(Integer.parseInt(request.getParameter("id")));
                
                jp.alterar(P);
                msg = "alterado";
                jp.excluirCat(P.getID());
                
                prodCat.setIdProduto(P.getID());

                String[] c = request.getParameterValues("cat[]");

                if (c != null){

                    for(int a = 0; a < c.length; a++)
                    {

                        prodCat.setIdCategoria(Integer.parseInt(c[a]));

                        jp.inserirCategoria(prodCat);
                    }

                } 
                
                
            }
            
            
            response.sendRedirect("ProdutoServlet?acao=listar&msg=" + msg);
        }else if(acao.equals("novaCat")){
            
            cat.setDescricao(request.getParameter("descricao"));
            int id = Integer.parseInt(request.getParameter("idProduto"));
            cp.inserir(cat);
            if(id == 0){
                msg = "catcad";
                response.sendRedirect("ProdutoServlet?acao=novoProduto&msg=" + msg);
            }else {
                msg = "catcad";
                response.sendRedirect("ProdutoServlet?acao=editar&id="+ id +"&msg=" + msg);
            }
            
        }else if (acao.equals("novoProduto")){
            
            List<Categoria> categorias = new ArrayList<>();
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("catcad")){
                    msg = "Categoria cadastrada com sucesso.";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
            }
            
            categorias = cp.listar();
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("novoProduto.jsp").forward(request, response);
            
        }else if (acao.equals("listar")) {
            
            List<Produto> produtos = jp.listar();            
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("alterado")){
                    msg = "Produto foi alterado com sucesso!";
                    tipo = "alert-warning";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("inserido")){
                    msg = "Produto foi cadastrado com sucesso!";
                    tipo = "alert-success";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }else if(msg.equals("deletado")){
                    msg = "Produto foi excluído com sucesso.";
                    tipo = "alert-danger";
                    request.setAttribute("msg", msg);
                    request.setAttribute("tipo", tipo);
                }
            }
            
            request.setAttribute("produtos", produtos);
            request.getRequestDispatcher("listaProdutos.jsp").forward(request, response);
        } else if (acao.equals("editar")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            P = jp.exibir(id);
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("catcad")){
                    msg = "Categoria cadastrada com sucesso.";
                    tipo = "alert-success";
                }
                request.setAttribute("msg", msg);
                request.setAttribute("tipo", tipo);
            }
            
            List<Categoria> categorias = new ArrayList<>();
            
            categorias = cp.listar();
            
            request.setAttribute("categorias", categorias);
            request.setAttribute("Produto", P);
            request.getRequestDispatcher("novoProduto.jsp").forward(request, response);
        } else if (acao.equals("deletar")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            jp.excluirCat(id);
            jp.deletar(id);
            msg = "deletado";
            
            response.sendRedirect("ProdutoServlet?acao=listar");
            
        } else if (acao.equals("exibir")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            P = jp.exibir(id);
            
            List<Categoria> categorias = new ArrayList<>();
            
            categorias = cp.listar();
            
            request.setAttribute("categorias", categorias);
            
            
            request.setAttribute("Produto", P);
            
            
            request.getRequestDispatcher("exibirProduto.jsp").forward(request, response);
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
