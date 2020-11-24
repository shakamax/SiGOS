/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Servico;
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
 * @author Guilherme
 */
public class JDBCServico {
    
    Connection conexao;

//    public JDBCServico() {
//        conexao = ConnectionFactory.getConnection();        
//    }
    
    
    public void inserir(Servico serv){
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "INSERT INTO servico (nome, descricao, valor) VALUES (?, ?, ?);";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, serv.getNome());
            ps.setString(2, serv.getDescricao());
            ps.setDouble(3, serv.getValor());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir novo serviço" + ex.getMessage(), ex);
        }
    }
    
    
    public void deletar(int id){
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "DELETE FROM servico WHERE id_servico = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            conexao.close(); 
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir serviço" + ex.getMessage(), ex);
        }
        
        
    }

    public List<Servico> listar() {
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "SELECT * FROM servico;";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            List<Servico> servicos = new ArrayList<>();
            
            while(rs.next()){
                Servico serv = new Servico();
                
                serv.setID(rs.getInt("id_servico"));
                serv.setNome(rs.getString("nome"));
                serv.setDescricao(rs.getString("descricao"));
                serv.setValor(rs.getDouble("valor"));
                
                servicos.add(serv);
                
            }
            
            ps.close();
            conexao.close();
            
            return servicos;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new  RuntimeException("Erro ao listat serviços " + ex.getMessage(), ex);
        }
    }

    public Servico exibir(int id) {
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "SELECT * FROM servico WHERE id_servico = ?";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Servico serv = new Servico();
            
            
            if(rs.next()){
                serv.setID(rs.getInt("id_servico"));
                serv.setNome(rs.getString("nome"));
                serv.setValor(rs.getDouble("valor"));
                serv.setDescricao(rs.getString("descricao"));
            }
            
            
            ps.close();
            conexao.close();
            return serv;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao selecionar no banco de dados " + ex.getMessage(), ex);
        }
        
    }
    
    public void alterar(Servico serv){
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "UPDATE servico SET nome = ?, descricao = ?, valor = ? WHERE id_servico = ?;";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, serv.getNome());
            ps.setString(2, serv.getDescricao());
            ps.setDouble(3, serv.getValor());
            ps.setInt(4, serv.getID());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao atualizar serviço" + ex.getMessage(), ex);
        }
    }
    
    
}
