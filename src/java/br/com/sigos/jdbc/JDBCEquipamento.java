/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.ListaEquipamento;
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
public class JDBCEquipamento {
    
    Connection conexao;

    public JDBCEquipamento() {
        conexao = ConnectionFactory.getConnection();
    }
    
    public List<ListaEquipamento> Listar(int id){
        
        try {
            List<ListaEquipamento> equipamentos = new ArrayList<>();
            String query = "SELECT * FROM listaequipamentos WHERE fk_cliente = ? ;";
            
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                ListaEquipamento equip = new ListaEquipamento();
                
                equip.setEquipamento(rs.getString("equipamento"));
                equip.setDefeito(rs.getString("defeito"));
                equip.setObservacao(rs.getString("observacoes"));
                equip.setAcessório(rs.getString("acessorios"));
                equip.setId(rs.getInt("id_lista"));
                equip.setFk_cliente(rs.getInt("fk_cliente"));
                
                equipamentos.add(equip);
                
            }
            
            
            
            
            return equipamentos;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCEquipamento.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar equipamentos" + ex.getMessage(), ex);
        }
    } 

    public void inserir(ListaEquipamento lista) {
        try {
            String query = "INSERT INTO listaequipamentos (fk_cliente, equipamento, acessorios, observacoes, defeito)" +
                    " VALUES (?, ?, ?, ?, ?);";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, lista.getFk_cliente());
            ps.setString(2, lista.getEquipamento());
            ps.setString(3, lista.getAcessório());
            ps.setString(4, lista.getObservacao());
            ps.setString(5, lista.getDefeito());
            
            ps.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCEquipamento.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir equipamento no banco de dados" + ex.getMessage(), ex);
        }
        
        
    }
    
    public int deletar(int id){
        
        try {
            String select = "SELECT fk_cliente FROM listaequipamentos WHERE id_lista = ?;";
            PreparedStatement pro = conexao.prepareStatement(select);
            pro.setInt(1, id);
            ResultSet rs = pro.executeQuery();
            
            rs.next();
            
            int id_cliente = rs.getInt("fk_cliente");
            
            
            String query = "DELETE FROM listaequipamentos WHERE id_lista = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, id);
            ps.executeUpdate();
            conexao.close();
            
            return id_cliente;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCEquipamento.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao deletar equipamento do seu banco de dados" + ex.getMessage(), ex);
        }
    }

    public ListaEquipamento exibir(int id) {
        try {
            String query = "SELECT * FROM listaequipamentos WHERE id_lista = ?;";
            PreparedStatement pro = conexao.prepareStatement(query);
            ListaEquipamento lista = new ListaEquipamento();
            pro.setInt(1, id);
            ResultSet rs = pro.executeQuery();
            
            rs.next();
            
            lista.setId(rs.getInt("id_lista"));
            lista.setFk_cliente(rs.getInt("fk_cliente"));
            lista.setEquipamento(rs.getString("equipamento"));
            lista.setAcessório(rs.getString("acessorios"));
            lista.setDefeito(rs.getString("defeito"));
            lista.setObservacao(rs.getString("observacoes"));
            
            conexao.close();
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCEquipamento.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao pegar equipamento do banco de dados" + ex.getMessage(), ex);
        }
    }

    public void alterar(ListaEquipamento lista) {
        
        try {
            String query = "UPDATE listaequipamentos SET equipamento = ?, acessorios =  ?, observacoes = ?, defeito = ? WHERE (`id_lista` = ?);";
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, lista.getEquipamento());
            ps.setString(2, lista.getAcessório());
            ps.setString(3, lista.getObservacao());
            ps.setString(4, lista.getDefeito());
            ps.setInt(5, lista.getId());
            
            ps.executeUpdate();
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCEquipamento.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao alterar equipamento" + ex.getMessage(), ex);
        }
        
                
        
    }
    
    
    
}
