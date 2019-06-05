/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.dao.ClienteDAO;
import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author 04341474197
 */
public class JDBCCliente implements ClienteDAO{
    
    Connection conexao;
    
    public JDBCCliente(){
        conexao = ConnectionFactory.getConnection();
    }

    @Override
    public void inserir(Cliente cliente) {
        try {
            String sql = "INSERT INTO cliente (nome, email, telefone, cpf, emailAlternativo, status, dtNasc, CEP, Estado, Cidade, Bairro, Complemento, Logradouro)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEmail());
                ps.setString(3, cliente.getTelefone());
                ps.setString(4, cliente.getCPF());
                ps.setString(5, cliente.getEmailAlternativo());
                //ps.setString(6, cliente.getSenha());
                ps.setString(6, "0");
                //ps.setDate(8, new java.sql.Date(cliente.getDtCadastro().getTime()));
                ps.setDate(7, new java.sql.Date(cliente.getDtNascimento().getTime()));
                ps.setString(8, cliente.getCEP());
                ps.setString(9, cliente.getEstado());
                ps.setString(10, cliente.getCidade());
                ps.setString(11, cliente.getBairro());
                ps.setString(12, cliente.getComplemento());
                ps.setString(13, cliente.getLogradouro());
                
                ps.executeUpdate();
                ps.close();
                conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void deletar(int id) {
        try {
            String query = "DELETE FROM cliente WHERE id_cliente = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao deletar" + ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        try {
            String query = "UPDATE cliente SET nome = ?, "
                    + "email = ?, telefone = ?, cpf = ?, "
                    + "emailAlternativo = ?, dtNasc = ?, cep = ?, estado = ?, "
                    + "cidade = ?, bairro = ?, complemento = ?, "
                    + "logradouro = ? WHERE id_cliente = ?;";
            PreparedStatement ps = conexao.prepareStatement(query);
                
                
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEmail());
                ps.setString(3, cliente.getTelefone());       
                ps.setString(4, cliente.getCPF());
                ps.setString(5, cliente.getEmailAlternativo());
                //ps.setString(6, cliente.getSenha());
                //ps.setString(6, "0");
                //ps.setDate(8, new java.sql.Date(cliente.getDtCadastro().getTime()));
                ps.setDate(6, new java.sql.Date(cliente.getDtNascimento().getTime()));
                ps.setString(7, cliente.getCEP());
                ps.setString(8, cliente.getEstado());
                ps.setString(9, cliente.getCidade());
                ps.setString(10, cliente.getBairro());
                ps.setString(11, cliente.getComplemento());
                ps.setString(12, cliente.getLogradouro());
                ps.setInt(13, cliente.getId());
            
            ps.executeUpdate();
            
            //ps.close();
            //conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao cadastrar cliente no BD" + ex.getMessage(), ex);
        }
    }

    @Override
    public Cliente exibir(int id) {
            
        Cliente cliente = new Cliente();
        try {
            
            String query = "SELECT * FROM cliente WHERE id_cliente = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setCPF(rs.getString("cpf"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setEmailAlternativo(rs.getString("emailAlternativo"));
            cliente.setDtCadastro(rs.getDate("dtCadastro"));
            cliente.setDtNascimento(rs.getDate("dtNasc"));
            cliente.setCEP(rs.getString("CEP"));
            cliente.setEstado(rs.getString("Estado"));
            cliente.setCidade(rs.getString("Cidade"));
            cliente.setBairro(rs.getString("Bairro"));
            cliente.setLogradouro(rs.getString("Logradouro"));
            cliente.setComplemento(rs.getString("Complemento"));
            
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao exibir" + ex.getMessage(), ex);
        }
 
    }

    @Override
    public List<Cliente> Listar() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            String query = "SELECT * FROM cliente";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            Cliente cl = new Cliente();
            
            cl.setId(rs.getInt("id_cliente"));
            cl.setNome(rs.getString("nome"));
            cl.setEmail(rs.getString("email"));
            cl.setTelefone(rs.getString("telefone"));
            cl.setCPF(rs.getString("cpf"));
            cl.setCidade(rs.getString("cidade"));
            cl.setStatus(rs.getBoolean("status"));
            
            clientes.add(cl);
            }
            return clientes;
            
        } catch (SQLException ex) {
           Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
           throw new RuntimeException("Erro ao listar Clientes", ex);
        }
    }
   
}