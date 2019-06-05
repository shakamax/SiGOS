/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.dao.ProdutoDao;
import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Categoria;
import br.com.sigos.model.Produto;
import br.com.sigos.model.ProdutosCategorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 03728827142
 */
public class JDBCProduto implements ProdutoDao {

    Connection conexao;
    
    public JDBCProduto() {
        conexao = ConnectionFactory.getConnection();
    }

    
    
    @Override
    public int inserir(Produto produto) {
            int chave = 0;
        try {
            String query = "INSERT INTO produto (nome, localizacao, valor, codigoBarra, qtd, descricao) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getLocalizacao());
            ps.setDouble(3, produto.getValor());
            ps.setString(4, produto.getCodigo());
            ps.setInt(5, produto.getQuantidade());
            ps.setString(6, produto.getDescricao());
            
            ps.executeUpdate();
            
            ResultSet rs= ps.getGeneratedKeys();
			
            if(rs.next()){
               chave = rs.getInt(1);
            }    
            
            return chave;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir no banco de dados" + ex.getMessage(), ex);
        }
        
        
        
    }

    @Override
    public void deletar(int id) {
        
        try {
            String query = "DELETE FROM produto WHERE id_produto = ?";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao deletar produto " + ex.getMessage(), ex);
        }
        
        
    }

    @Override
    public void alterar(Produto produto) {
        
        try {
            String query = "UPDATE produto SET nome= ?, localizacao= ?, valor= ?, codigoBarra= ?, qtd= ?,"
                    + " descricao= ?\n" +
                    "WHERE id_produto = ? ";

            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getLocalizacao());
            ps.setDouble(3, produto.getValor());
            ps.setString(4, produto.getCodigo());
            ps.setInt(5, produto.getQuantidade());
            ps.setString(6, produto.getDescricao());
            ps.setInt(7, produto.getID());
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema al alterar produto" + ex.getMessage(), ex);
        }
        
        
    }

    @Override
    public Produto exibir(int id) {
            
        Produto prod = new Produto();
        
            String query = "SELECT produto.*, categoria.id_categoria, categoria.descricao as catDesc FROM produto " +
                    "LEFT JOIN produtoscategorias ON produto.id_produto = produtoscategorias.id_produto " +
                    "LEFT JOIN categoria ON categoria.id_categoria = produtoscategorias.id_categoria " +
                    "WHERE produto.id_produto = ?;";
            
        try {
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            prod.setID(rs.getInt("id_produto"));
            prod.setNome(rs.getString("nome"));
            prod.setCodigo(rs.getString("codigoBarra"));
            prod.setDescricao(rs.getString("produto.descricao"));
            prod.setLocalizacao(rs.getString("localizacao"));
            prod.setValor(rs.getDouble("valor"));
            prod.setQuantidade(rs.getInt("qtd"));
            List<Categoria> categorias = new ArrayList<>();
            
            while(rs.next()){
                Categoria cat = new Categoria();
                cat.setDescricao(rs.getString("catDesc"));
                cat.setID(rs.getInt("id_categoria"));
                categorias.add(cat);
            }
            
            prod.setCategoria(categorias);
            
            
            return prod;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema ao exibir no banco de dados" + " " + ex.getMessage(), ex);
            
        }
    }

    @Override
    public List<Produto> listar() {
        
        List<Produto> produtos = new ArrayList<>();
        int index = -1;
        List<Categoria> categorias = new ArrayList<>();
        
        try {
            
            String query = "SELECT produto.*, REPLACE(GROUP_CONCAT(categoria.descricao),\",\",\" | \") as descricoes_cat FROM produto \n" +
            "LEFT JOIN produtoscategorias ON produto.id_produto = produtoscategorias.id_produto \n" +
            "LEFT JOIN categoria ON produtoscategorias.id_categoria = categoria.id_categoria\n" +
            "group by produto.id_produto\n" +
            "ORDER BY PRODUTO.id_produto;";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            //int idAnterior = 0;
            
            
            while(rs.next()){
                
                Produto prod = new Produto();
                Categoria cat = new Categoria();
                
                //int idProximo = rs.getInt("id_produto");

                prod.setID(rs.getInt("id_produto"));
                prod.setCodigo(rs.getString("codigoBarra"));
                prod.setDescricao(rs.getString("produto.descricao"));
                prod.setLocalizacao(rs.getString("localizacao"));
                prod.setNome(rs.getString("nome"));
                prod.setQuantidade(rs.getInt("qtd"));
                prod.setValor(rs.getDouble("valor"));
                //String cate = rs.getString("descricoes_cat").replace(",", " /");
                prod.setCategorias(rs.getString("descricoes_cat"));
                
               
                produtos.add(prod);
                
               // prod.setCategoria(categoria);
                        
                //idAnterior = rs.getInt("id_produto");
                
            }
            
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema ao listar banco de dados" + ex.getMessage(), ex);
        }
    }
    
    
    
    
    
    public void inserirCategoria(ProdutosCategorias prodCat) {
        try {
            String query = "INSERT INTO produtoscategorias (id_produto, id_categoria) VALUES (?, ?);";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, prodCat.getIdProduto());
            ps.setInt(2, prodCat.getIdCategoria());
            
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir no banco de dados" + ex.getMessage(), ex);
        }
        
    }
    
    public void excluirCat(int id) {
        
        try {
            String query = "DELETE FROM produtoscategorias WHERE id_produto = ?;";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProduto.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao passo de exclusão da atualização de categorias" + ex.getMessage(), ex);
        }
        
        
        
    }
    
}
