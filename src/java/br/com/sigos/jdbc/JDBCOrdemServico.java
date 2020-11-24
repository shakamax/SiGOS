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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class JDBCOrdemServico {

    Connection conexao;
    
//    public JDBCOrdemServico() {
//        conexao = ConnectionFactory.getConnection();
//    }
    
    public int inserir(ordemServico os) {
        conexao = ConnectionFactory.getConnection();
        
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
            
            
            ps.close();
            conexao.close();
            return chave;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir Ordem de serviço no banco de dados." + ex.getMessage(), ex);
        }
        
    }
    
    public void inserirProd(vendaProduto vendProd){
        conexao = ConnectionFactory.getConnection();
        String query = "INSERT INTO vendaproduto (fk_produto, fk_OS, valor, Qtd, autorizado) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, vendProd.getProduto().getID());
            ps.setInt(2, vendProd.getIdOs());
            ps.setDouble(3, vendProd.getValor());
            ps.setInt(4, vendProd.getQtd());
            ps.setBoolean(5, vendProd.isAutorizo());
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível inserir produtos na OS" + ex.getMessage(), ex);
        }
        
        
    }

    public void inserirExec(Execucao exec) {
        conexao = ConnectionFactory.getConnection();
        String query = "INSERT INTO execucao (fk_os, fk_servicos, status, autorizado) VALUES (?, ?, ?, ?);";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, exec.getIdOs());
            ps.setInt(2, exec.getIdServ());
            ps.setBoolean(3, exec.isStatus());
            ps.setBoolean(4, exec.isAutorizado());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir serviços a OS." + ex.getMessage(), ex);
        }
        
        
                
                
                
                
    }

    public List<ordemServico> listar() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico\n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS "
        + "WHERE ordemservico.cancelada = 0 GROUP BY ordemservico.Id_OS order by ordemservico.status DESC, logos.dtModificacao DESC;";
        
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
                List<Servico> servs = this.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Ordens de serviço no banco de dados." + ex.getMessage(), ex);
        }
    }

    public void inserirLog(LogOs log) {
        conexao = ConnectionFactory.getConnection();
        String query = "INSERT INTO logos (Id_OS, descricao) VALUES (?, ?);"; 
        
        try {
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, log.getIdOs());
            ps.setString(2, log.getDescricao());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível criação de LogOS." + ex.getMessage(), ex);
        }
        

    }

    public ordemServico exibe(int id) {
        conexao = ConnectionFactory.getConnection();
        
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
                os.setValorTotal(rs.getDouble("ordemservico.valorTotal"));
                
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
                    
                    
            
            ps.close();
            conexao.close();        
            return os;        
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar OS no banco de dados." + ex.getMessage(), ex);
        }
        
    }
    
    
    public List<LogOs> buscarLog(int IdOS){
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT * FROM logos WHERE logos.Id_OS = ? order by id_log desc";
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
            
            ps.close();
            conexao.close();        
            return logs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar registros de modificação" + ex.getMessage(), ex);
        }
        
    }
    
    public List<Servico> buscarServicos(int IdOs){
        conexao = ConnectionFactory.getConnection();
        
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
                serv.setStatus(rs.getBoolean("status"));
                serv.setAutorizado(rs.getBoolean("autorizado"));
                servs.add(serv);
            }
            
            ps.close();
            conexao.close();
            return servs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar servicos da OS." + ex.getMessage(), ex);
        }
        
    }
    
    public List<Produto> buscarProdutos(int IdOs){
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT produto.*, vendaproduto.* FROM produto \n" +
                        "LEFT JOIN vendaproduto ON produto.id_produto = vendaproduto.fk_produto\n" +
                        "LEFT JOIN ordemservico ON ordemservico.Id_OS = vendaproduto.fk_OS\n" +
                        "WHERE ordemservico.Id_OS = ? group by vendaproduto.id_venda";
        
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
                prod.setStatus(rs.getBoolean("status"));
                prod.setAutorizado(rs.getBoolean("autorizado"));
                prods.add(prod);
            }
            
            ps.close();
            conexao.close();
            return prods;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro a buscar produtos de OS." + ex.getMessage(), ex);
        }
        
    }

    public void alterar(ordemServico os) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE ordemservico SET prazoEntrega = ?, garantia = ? WHERE (Id_OS = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            if(os.getPrazoEntrega() != null){
                ps.setDate(1, new java.sql.Date(os.getPrazoEntrega().getTime()));
            } else{
                ps.setDate(1, null);
            }
            ps.setString(2, os.getGarantia());
            ps.setInt(3, os.getNumOS());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Um erro ocorreu em atualizar a tabela de OS." + ex.getMessage(), ex);
        }
        
    }

    public void excluirTodasExec(int id) {
        conexao = ConnectionFactory.getConnection();
        String query = "DELETE FROM execucao WHERE (fk_os = ?);";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir serviços em execução. " + ex.getMessage(), ex);
        }
    }

    public void excluirExec(Execucao exec) {
        conexao = ConnectionFactory.getConnection();
        String query = "DELETE FROM execucao WHERE fk_os = ? AND fk_servicos = ?;"; 
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, exec.getIdOs());
            ps.setInt(2, exec.getIdServ());
            
            ps.executeUpdate();
            
            conexao.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível excluir execucao." + ex.getMessage(), ex);
        }
        
    }

    public void excluirProd(vendaProduto vendProd) {
        conexao = ConnectionFactory.getConnection();
        String query = "DELETE FROM vendaproduto WHERE fk_OS = ? AND fk_produto = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, vendProd.getIdOs());
            ps.setInt(2, vendProd.getProduto().getID());
            
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir produto de OS. " + ex.getMessage(), ex);
        }
        
        
        
    }

    public void cancelarOS(int idOs) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE ordemservico SET cancelada = 1 WHERE Id_OS = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, idOs);
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao cancelar OS. " + ex.getMessage(), ex);
        }
    }

    public void finalizar(int idOs) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE ordemservico SET dataFechamento = current_timestamp(), status = '0' WHERE Id_OS = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, idOs);
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao finalizar O.S. " + ex.getMessage(), ex);
        }
    }

    public List<ordemServico> listarComServ() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico\n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS "
        + "WHERE ordemservico.cancelada = 0 AND ordemservico.status = 1 GROUP BY ordemservico.Id_OS order by ordemservico.status DESC, logos.dtModificacao DESC;";
        
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
                List<Servico> servs = this.buscarServicosAutorizados(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Ordens de serviço no banco de dados." + ex.getMessage(), ex);
        }
    }
      
    
        public List<Servico> buscarServicosAutorizados(int IdOs){
        conexao = ConnectionFactory.getConnection();
        
        String query = "SELECT servico.*, execucao.* FROM servico\n" +
        "INNER JOIN execucao ON execucao.fk_servicos = servico.id_servico\n" +
        "WHERE execucao.fk_os = ? AND execucao.autorizado = 1 group by execucao.fk_servicos;";
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
                serv.setStatus(rs.getBoolean("status"));
                serv.setAutorizado(rs.getBoolean("autorizado"));
                servs.add(serv);
            }
            
            ps.close();
            conexao.close();
            return servs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar servicos da OS." + ex.getMessage(), ex);
        }
        
    }

    public void alterarExec(Execucao exec) {
        String query = "UPDATE execucao SET `status` = 1 WHERE (fk_os = ? AND fk_servicos = ?);";
        conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, exec.getIdOs());
            ps.setInt(2, exec.getIdServ());
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao atualizar execução. " + ex.getMessage(), ex);
        }
    }

    public void atualizarValor(ordemServico os) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE ordemservico SET valorTotal = ? WHERE Id_OS= ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setDouble(1, os.getValorTotal());
            ps.setInt(2, os.getNumOS());
            
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao atualizar valor total de OS." + ex.getMessage(), ex);
        }
        
    }

    public String pegarEmailOs(int idOs) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT cliente.email FROM ordemservico LEFT JOIN cliente ON ordemservico.fk_cliente = cliente.id_cliente WHERE Id_OS = ?;";
        String email = "";
        try {
            PreparedStatement ps = conexao.prepareCall(query);
            ps.setInt(1, idOs);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                email = rs.getString("email");
                
            }
            
            return email;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível enviar e-mail" + ex.getMessage(), ex);
        }
       
        
    }

    public int qtdMaxOs() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT count(Id_OS) as qtdMax FROM ordemservico;";
        try {
            PreparedStatement ps = conexao.prepareCall(query);
            
            ResultSet rs = ps.executeQuery();
            int qtdMax = 0;
            
            if(rs.next()){
                
                qtdMax = rs.getInt("qtdMax");
                
            }
            
            return qtdMax;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível solicitar qtd max " + ex.getMessage(), ex);
        }
    }

    public int qtdCanceladas() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT count(Id_OS) as qtdCanceladas FROM ordemservico WHERE cancelada = 1;";
        try {
            PreparedStatement ps = conexao.prepareCall(query);
            
            ResultSet rs = ps.executeQuery();
            int qtdcanceladas = 0;
            
            if(rs.next()){
                
                qtdcanceladas = rs.getInt("qtdCanceladas");
                
            }
            
            return qtdcanceladas;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível solicitar quantidade de canceladas " + ex.getMessage(), ex);
        }
    }

    public int qtdAbertas() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT count(Id_OS) as qtdAbertas FROM ordemservico WHERE `status` = 1 AND cancelada = 0;";
        try {
            PreparedStatement ps = conexao.prepareCall(query);
            
            ResultSet rs = ps.executeQuery();
            int qtdAbertas = 0;
            
            if(rs.next()){
                
                qtdAbertas = rs.getInt("qtdAbertas");
                
            }
            
            return qtdAbertas;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível solicitar quantidade de abertas " + ex.getMessage(), ex);
        }
    }

    public int qtdFechadas() {
        conexao = ConnectionFactory.getConnection();
        String query = " SELECT count(Id_OS) as qtdFechadas FROM ordemservico WHERE `status` = 0 AND cancelada = 0;";
        try {
            PreparedStatement ps = conexao.prepareCall(query);
            
            ResultSet rs = ps.executeQuery();
            int qtdFechadas = 0;
            
            if(rs.next()){
                
                qtdFechadas = rs.getInt("qtdFechadas");
                
            }
            
            return qtdFechadas;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível solicitar quantidade de fechadas " + ex.getMessage(), ex);
        }
    }

    public List<ordemServico> listarCanceladas() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico\n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS "
        + "WHERE ordemservico.cancelada = 1 GROUP BY ordemservico.Id_OS order by logos.dtModificacao DESC;";
        
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
                List<Servico> servs = this.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                os.setCancelada(rs.getBoolean("ordemservico.cancelada"));
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao pesquisar ordens canceladas." + ex.getMessage(), ex);
        }
    }

    public List<ordemServico> listarCanceladas(String palavraChave, String coluna) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico\n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS\n" +
        "WHERE "+ coluna +" = ? AND ordemservico.cancelada = 1 GROUP BY ordemservico.Id_OS order by logos.dtModificacao DESC;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, "%" + coluna + "%");
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
                List<Servico> servs = this.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                os.setCancelada(rs.getBoolean("ordemservico.cancelada"));
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao pesquisar ordens de serviço." + ex.getMessage(), ex);
        }
    }

    public List<ordemServico> listarAbertas() {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico " +
            "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip " +
            "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente " +
            "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS " +
            "WHERE ordemservico.status = 1 AND ordemservico.cancelada = 1 GROUP BY ordemservico.Id_OS order by logos.dtModificacao DESC;";
        
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
                List<Servico> servs = this.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                os.setCancelada(rs.getBoolean("ordemservico.cancelada"));
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao pesquisar ordens canceladas." + ex.getMessage(), ex);
        }
    }

    public List<ordemServico> listarAbertas(String date) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico\n" +
        "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente\n" +
        "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS\n" +
        "WHERE ordemservico.dtAbertura like ? AND ordemservico.status = 1 AND ordemservico.cancelada = 0 GROUP BY ordemservico.Id_OS order by logos.dtModificacao DESC;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, "%" + date + "%");
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
                List<Servico> servs = this.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                os.setCancelada(rs.getBoolean("ordemservico.cancelada"));
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao pesquisar ordens canceladas." + ex.getMessage(), ex);
        }
    }

    public void reabrirOS(int id) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE ordemservico SET dataFechamento = null, status = '1' WHERE Id_OS = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao finalizar O.S. " + ex.getMessage(), ex);
        }
    }

    public List<ordemServico> listarBusca(String palavraChave, String coluna, boolean b, String date, String colunaData) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT ordemservico.*, listaequipamentos.*, cliente.nome, logos.* FROM ordemservico " +
            "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip " +
            "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente " +
            "LEFT JOIN logos ON ordemservico.Id_OS = logos.Id_OS " +
            "WHERE " + colunaData + " like ? AND ordemservico.status = ? AND ordemservico.cancelada = 0 AND " + coluna + " like ? " +
            " GROUP BY ordemservico.Id_OS order by logos.dtModificacao DESC;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, "%" + date + "%");
            ps.setBoolean(2, b);
            ps.setString(3, "%" + palavraChave + "%");
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
                List<Servico> servs = this.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                List<LogOs> logs = new ArrayList<>();
                
                logs = this.buscarLog(os.getNumOS());
                
                LogOs log = new LogOs();
                log = logs.get(0);
                
                os.setLog(log);
                os.setDtAbertura(rs.getDate("dtAbertura"));
                ListaEquipamento lista = new ListaEquipamento();
                lista.setId(rs.getInt("id_lista"));
                lista.setEquipamento(rs.getString("equipamento"));
                os.setListaEquipamentos(lista);
                os.setCancelada(rs.getBoolean("ordemservico.cancelada"));
                
                ordens.add(os);
            }
            
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao pesquisar ordens canceladas." + ex.getMessage(), ex);
        }
    }
    
}

            