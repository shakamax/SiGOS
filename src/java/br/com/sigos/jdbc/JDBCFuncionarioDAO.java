/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.dao.FuncionarioDAO;
import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Funcionario;
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
 * @author 04341474197
 */
public class JDBCFuncionarioDAO implements FuncionarioDAO{
    Connection conexao;
    
    public JDBCFuncionarioDAO(){
        conexao = ConnectionFactory.getConnection();
    };

    @Override
    public void inserir(Funcionario funcionario) {
      try {
        String query = "INSERT INTO funcionario (nome, email, senha, funcao) VALUES (?, ?, ?, ?);";
                PreparedStatement ps = conexao.prepareStatement(query);
                
                ps.setString(1, funcionario.getNome());
                ps.setString(2, funcionario.getEmail());
                ps.setString(3, funcionario.getSenha());
                ps.setString(4, funcionario.getFuncao());
                
                ps.executeUpdate();
                
                ps.close();
                conexao.close();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @Override
    public void deletar(int id) {
        try {
            String query = "DELETE FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao deletar" + ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Funcionario funcionario) {
        try {
            String query = "UPDATE funcionario SET nome = ?, email = ?, senha = ?, funcao = ? WHERE id_funcionario = ?;";
            PreparedStatement ps = conexao.prepareStatement(query);
                
                
                ps.setString(1, funcionario.getNome());
                ps.setString(2, funcionario.getEmail());
                ps.setString(3, funcionario.getSenha());       
                ps.setString(4, funcionario.getFuncao());
                ps.setInt(5, funcionario.getId());
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Funcionario exibir(int id) {
        Funcionario funcionario = new Funcionario();
        try {
            
            String query = "SELECT * FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            funcionario.setId(rs.getInt("id_funcionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setFuncao(rs.getString("funcao"));
            
            
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao exibir" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
            String query = "SELECT * FROM funcionario";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            Funcionario fun = new Funcionario();
            
            fun.setId(rs.getInt("id_funcionario"));
            fun.setNome(rs.getString("nome"));
            fun.setEmail(rs.getString("email"));
            fun.setSenha(rs.getString("senha"));
            fun.setFuncao(rs.getString("funcao"));
            
            
            funcionarios.add(fun);
            }
            return funcionarios;
            
        } catch (SQLException ex) {
           Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           throw new RuntimeException("Erro ao listar Funcionarios", ex);
        }
    }
    
}
