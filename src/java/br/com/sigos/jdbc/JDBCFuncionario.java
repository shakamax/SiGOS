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
public class JDBCFuncionario implements FuncionarioDAO{
    Connection conexao;
    
//    public JDBCFuncionario(){
//        conexao = ConnectionFactory.getConnection();
//    };

    @Override
    public void inserir(Funcionario funcionario) {
        conexao = ConnectionFactory.getConnection();
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
                Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("Erro ao inserir funcionário " + ex.getMessage(), ex);
            }
        
    }

    @Override
    public void deletar(int id) {
        conexao = ConnectionFactory.getConnection();
        try {
            String query = "DELETE FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao deletar" + ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Funcionario funcionario) {
        conexao = ConnectionFactory.getConnection();
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
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Funcionario exibir(int id) {
        conexao = ConnectionFactory.getConnection();
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
            
            ps.close();
            conexao.close();
            
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao exibir" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Funcionario> listar() {
        conexao = ConnectionFactory.getConnection();
        
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
            
            ps.close();
            conexao.close();
            
            return funcionarios;
        } catch (SQLException ex) {
           Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
           throw new RuntimeException("Erro ao listar Funcionarios", ex);
        }
    }

    public Funcionario logar(String email, String senhac) {
        conexao = ConnectionFactory.getConnection();
        
        String query = "SELECT * FROM funcionario WHERE email = ? AND senha = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, senhac);
            ResultSet rs = ps.executeQuery();
            Funcionario usuario = new Funcionario();
            
            if(rs.next()){
            usuario.setId(rs.getInt("id_funcionario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setFuncao(rs.getString("funcao"));
            }
                    
            
            ps.close();
            conexao.close();
            
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Nenhum usuário encontrado" + ex.getMessage(), ex);
        }
        
    }

    public Boolean confirmarSenha(String senha, int id) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT * FROM funcionario WHERE id_funcionario = ? AND senha = ?;";
        Boolean senhaConfirmada = false;
        try {
            PreparedStatement ps = conexao.prepareStatement("query");
            ps.setInt(1, id);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                senhaConfirmada = true;
            }
            
            ps.close();
            conexao.close();
            return senhaConfirmada;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar confirmação no Banco de Dados. " + ex.getMessage(), ex);
        }
        
    }

    
    
    
    
}
