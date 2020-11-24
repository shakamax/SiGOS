/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.dao.CategoriaDao;
import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 03728827142
 */
public class JDBCCategoria implements CategoriaDao {

    Connection conexao;
        
//    public JDBCCategoria() {
//        conexao = ConnectionFactory.getConnection();
//    }
    
    @Override
    public void inserir(Categoria categoria) {
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "INSERT INTO categoria (descricao) VALUES (?);";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, categoria.getDescricao());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();          

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCategoria.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir no abnco de dados." + ex, ex);
        }
        
        
        

    }

    @Override
    public void deletar(int id) {
        conexao = ConnectionFactory.getConnection();
        
        try {
            String query = "DELETE FROM categoria WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
            
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCategoria.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir categorias... " + ex.getMessage(), ex);
        }
        
        
    }

    @Override
    public void alterar(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria exibir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> listar() {
        conexao = ConnectionFactory.getConnection();
        
        List<Categoria> categorias = new ArrayList<Categoria>();
        
        try {
            
            String query = "select c.*, group_concat(id_produto) as produtos from categoria c\n" +
            "left join produtoscategorias pc on pc.id_categoria = c.id_categoria\n" +
            "group by c.id_categoria;";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Categoria c = new Categoria();
                
                c.setID(rs.getInt("id_categoria"));
                c.setDescricao(rs.getString("descricao"));
                c.setProdutos(rs.getString("produtos"));
                
                categorias.add(c);
                
            }
                     
            ps.close();
            conexao.close();
            
            return categorias;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCategoria.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao conectar com banco de dados." + ex, ex);
        }
        
        
    }
    
}
