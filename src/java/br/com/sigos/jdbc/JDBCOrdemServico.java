/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Execucao;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.ordemServico;
import br.com.sigos.model.vendaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class JDBCOrdemServico {

    Connection conexao;
    
    public JDBCOrdemServico() {
        conexao = ConnectionFactory.getConnection();
    }
    
    public int inserir(ordemServico os) {
        String query = "INSERT INTO ordemservico (fk_cliente, fk_equip) VALUES (?, ?);";
        int chave = 0; 
        try {
            
            PreparedStatement ps = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, os.getCliente().getId());
            ps.setInt(2, os.getListaEquipamentos().getId());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                chave = rs.getInt(1);
            }
            
            
            
            return chave;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir Ordem de serviço no banco de dados." + ex.getMessage(), ex);
        }
        
    }
    
    public void inserirProd(vendaProduto vendProd){
        
        String query = "INSERT INTO vendaproduto (fk_produto, fk_OS, valor, Qtd) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, vendProd.getProduto().getID());
            ps.setInt(2, vendProd.getIdOs());
            ps.setDouble(3, vendProd.getValor());
            ps.setInt(4, vendProd.getQtd());
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível inserir produtos na OS" + ex.getMessage(), ex);
        }
        
        
    }

    public void inserirExec(Execucao exec) {
        String query = "INSERT INTO execucao (fk_os, fk_servicos, fk_funcionario) VALUES (?, ?, ?);";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, exec.getIdOs());
            ps.setInt(2, exec.getIdServ());
            ps.setInt(3, exec.getIdTecnico());
            
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir serviços a OS." + ex.getMessage(), ex);
        }
        
        
                
                
                
                
    }

    public List<ordemServico> listar() {
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico\n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS GROUP BY ordemservico.Id_OS order by ordemservico.status DESC, logos.dtModificacao DESC;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            List<ordemServico> ordens = new ArrayList<>();
            
            while(rs.next()) {
                ordemServico os = new ordemServico();
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("cliente.nome"));
                cliente.setId(rs.getInt("fk_cliente"));
                os.setNumOS(rs.getInt("Id_OS"));
                os.setCliente(cliente);
                os.setStatus(rs.getBoolean("status"));
                LogOs log = new LogOs();
                log.setIdOs(rs.getInt("logos.Id_OS"));
                log.setDescricao(rs.getString("logos.descricao"));
                log.setDataHora(rs.getTimestamp("dtModificacao"));

                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                
                ordens.add(os);
            }
            
            
            
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Ordens de serviço no banco de dados." + ex.getMessage(), ex);
        }
    }

    public void inserirLog(LogOs log) {
        String query = "INSERT INTO logos (Id_OS, descricao) VALUES (?, ?);"; 
        
        try {
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, log.getIdOs());
            ps.setString(2, log.getDescricao());
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível criação de LogOS." + ex.getMessage(), ex);
        }
        

    }

    public ordemServico exibe(int id) {
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.*, vendaproduto.*, execucao.* FROM ordemservico \n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS\n" +
        "LEFT JOIN vendaproduto ON ordemservico.Id_OS = vendaproduto.fk_OS\n" +
        "LEFT JOIN execucao ON execucao.fk_os = ordemservico.Id_OS\n" +
        "LEFT JOIN servico ON servico.id_servico = execucao.fk_servicos\n" +
        "LEFT JOIN produto on produto.id_produto = vendaproduto.fk_OS\n" +
        "WHERE ordemservico.Id_OS = ? GROUP BY ordemservico.Id_OS;";

        try {
            PreparedStatement ps = conexao.prepareCall(query);
            ps.setInt(1, id);
            ordemServico os = new ordemServico();
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                os.setNumOS(rs.getInt("ordemservico.Id_OS"));
                Cliente cl = new Cliente();
                cl.setNome(rs.getString("cliente.nome"));
                cl.setId(rs.getInt("listaequipamentos.fk_cliente"));
                os.setCliente(cl);
                os.setStatus(rs.getBoolean("ordemservico.status"));
                os.setDtAbertura(rs.getTimestamp("ordemservico.dtAbertura"));
                os.setPrazoEntrega(rs.getDate("ordemservico.prazoEntrega"));
                os.setDtFechamento(rs.getTimestamp("ordemservico.dataFechamento"));
                os.setGarantia(rs.getString("ordemservico.garantia"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setEquipamento(rs.getString("listaequipamentos.equipamento"));
                lista.setDefeito(rs.getString("listaequipamentos.defeito"));
                lista.setFk_cliente(rs.getInt("listaequipamentos.fk_cliente"));
                lista.setId(rs.getInt("listaequipamentos.id_lista"));
                os.setListaEquipamentos(lista);
                
                List<LogOs> logs = new ArrayList<>();
                logs = this.buscarLog(os.getNumOS());
                
                List<Servico> servs = new ArrayList<>();
                servs = this.buscarServicos(os.getNumOS());
                LogOs log = new LogOs();
                log.setIdOs(rs.getInt("logos.Id_OS"));
                log.setIdLog(rs.getInt("logos.id_log"));
                log.setDataHora(rs.getTimestamp("logos.dtModificacao"));
                log.setDescricao(rs.getString("logos.descricao"));
                os.setLog(log);
                //add log na lista de logs
                
                
                List<Produto> prods = new ArrayList<>();
                prods = this.buscarProdutos(os.getNumOS());
                
                //adicionando produto na lista de produtos
                os.setLogOS(logs);
                os.setServicos(servs);
                os.setProdutos(prods);
               
                
                
            }
                    
                    
                    
            return os;        
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar OS no banco de dados." + ex.getMessage(), ex);
        }
        
    }
    
    
    public List<LogOs> buscarLog(int IdOS){
        String query = "SELECT * FROM logos WHERE logos.Id_OS = ? group by id_log order by dtModificacao desc";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, IdOS);
            
            ResultSet rs = ps.executeQuery();
            
            List<LogOs> logs = new ArrayList<>();
            while(rs.next()){
                
                LogOs log1 = new LogOs();
                log1.setIdOs(rs.getInt("logos.Id_OS"));
                log1.setIdLog(rs.getInt("logos.id_log"));
                log1.setDataHora(rs.getTimestamp("logos.dtModificacao"));
                log1.setDescricao(rs.getString("logos.descricao"));
                logs.add(log1);
            }
            
            return logs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar registros de modificação" + ex.getMessage(), ex);
        }
        
    }
    
    public List<Servico> buscarServicos(int IdOs){
        String query = "SELECT servico.*, execucao.* FROM servico\n" +
        "INNER JOIN execucao ON execucao.fk_servicos = servico.id_servico\n" +
        "WHERE execucao.fk_os = ? group by execucao.fk_servicos;";
        try {
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, IdOs);
            ResultSet  rs = ps.executeQuery();
            List<Servico> servs = new ArrayList<>();
            
            while(rs.next()){
                Servico serv = new Servico();
                serv.setID(rs.getInt("id_servico"));
                serv.setNome(rs.getString("nome"));
                serv.setValor(rs.getDouble("valor"));
                serv.setDescricao(rs.getString("descricao"));
                servs.add(serv);
            }
            
            return servs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar servicos da OS." + ex.getMessage(), ex);
        }
        
    }
    
    public List<Produto> buscarProdutos(int IdOs){
        String query = "SELECT * FROM produto\n" +
        "LEFT JOIN vendaproduto ON produto.id_produto = vendaproduto.fk_produto\n" +
        "LEFT JOIN ordemservico ON ordemservico.Id_OS = vendaproduto.fk_OS\n" +
        "WHERE ordemservico.Id_OS = ? group by vendaproduto.id_venda;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, IdOs);
            ResultSet rs = ps.executeQuery();
            List<Produto> prods = new ArrayList<>();
            while(rs.next()){
                Produto prod = new Produto();
                prod.setID(rs.getInt("id_produto"));
                prod.setNome(rs.getString("nome"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setQuantidade(rs.getInt("vendaproduto.Qtd"));
                prod.setValor(rs.getDouble("produto.valor"));
                prods.add(prod);
            }
            
            return prods;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro a buscar produtos de OS." + ex.getMessage(), ex);
        }
        
    }
    
      
}
