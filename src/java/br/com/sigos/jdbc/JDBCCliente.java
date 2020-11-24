/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.jdbc;

import br.com.sigos.dao.ClienteDAO;
import br.com.sigos.factory.ConnectionFactory;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Execucao;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.ordemServico;
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
    
//    public JDBCCliente(){
//        conexao = ConnectionFactory.getConnection();
//    }

    @Override
    public void inserir(Cliente cliente) {
        conexao = ConnectionFactory.getConnection();
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
        conexao = ConnectionFactory.getConnection();
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
        conexao = ConnectionFactory.getConnection();
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
            
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao cadastrar cliente no BD" + ex.getMessage(), ex);
        }
    }

    @Override
    public Cliente exibir(int id) {
        conexao = ConnectionFactory.getConnection();
            
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
            cliente.setStatus(rs.getBoolean("status"));
            
            
            
            ps.close();
            conexao.close();
            
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao exibir" + ex.getMessage(), ex);
        }
 
    }

    @Override
    public List<Cliente> Listar() {
        conexao = ConnectionFactory.getConnection();
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
            
            ps.close();
            conexao.close();
            
            return clientes;
        } catch (SQLException ex) {
           Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
           throw new RuntimeException("Erro ao listar Clientes", ex);
        }
    }

    public List<Cliente> buscar(String coluna, String busca) {
        conexao = ConnectionFactory.getConnection();
        
        //String query = "SELECT * FROM cliente WHERE ? LIKE ? ORDER BY dtCadastro DESC;";
        //String query = "SELECT COUNT(cliente.id_cliente) AS qtd FROM cliente";
        try {
            String query = "SELECT * FROM cliente WHERE " + coluna + " LIKE ? ORDER BY dtCadastro DESC";
            PreparedStatement ps = conexao.prepareStatement(query);
            //ps.setString(1, coluna);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            List<Cliente> clientes = new ArrayList<>();


                
            while(rs.next()){
                Cliente c = new Cliente();
            
                c.setId(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setCPF(rs.getString("cpf"));
                c.setCidade(rs.getString("cidade"));
                c.setStatus(rs.getBoolean("status"));
                
                
                clientes.add(c);
                    
            }
           
            
            ps.close();
            conexao.close();
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar cliente..." + ex.getMessage(), ex);
        }
        

    }

    public Cliente logar(String email, String senhac) {
        conexao = ConnectionFactory.getConnection();
        
        String query = "SELECT * FROM cliente WHERE email = ? AND senha = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, senhac);
            ResultSet rs = ps.executeQuery();
            Cliente cliente = new Cliente();
            
            if(rs.next()){
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            }
                    
            
            ps.close();
            conexao.close();
            return cliente;
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Nenhum usuário encontrado" + ex.getMessage(), ex);
        }
        
    }

    public List<LogOs> buscarLogs(int id_cliente) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT logos.* from logos LEFT JOIN ordemservico ON ordemservico.Id_OS = logos.Id_OS\n" +
        "LEFT JOIN cliente ON cliente.id_cliente = ordemservico.fk_cliente WHERE cliente.id_cliente = ? ORDER by id_log DESC limit 10;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id_cliente);
            List<LogOs> logs = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LogOs lg = new LogOs();
                lg.setIdOs(rs.getInt("Id_OS"));
                lg.setIdLog(rs.getInt("id_log"));
                lg.setDescricao(rs.getString("descricao"));
                lg.setDataHora(rs.getTimestamp("dtModificacao"));
                logs.add(lg);
            }
            
            ps.close();
            conexao.close();
            return logs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar log" + ex.getMessage(), ex);
        }
        
    }

    public List<ordemServico> listarOs(int id) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT * FROM ordemservico \n" +
            "LEFT JOIN listaequipamentos ON listaequipamentos.id_lista = ordemservico.fk_equip\n" +
            "WHERE ordemservico.fk_cliente = ? order by Id_OS;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            List<ordemServico> ordens = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ordemServico os = new ordemServico();
                os.setNumOS(rs.getInt("ordemServico.Id_OS"));
                os.setDtAbertura(rs.getTimestamp("ordemServico.dtAbertura"));
                os.setDtFechamento(rs.getTimestamp("ordemServico.dataFechamento"));
                os.setGarantia(rs.getString("ordemServico.garantia"));
                os.setPrazoEntrega(rs.getDate("ordemServico.prazoEntrega"));
                os.setStatus(rs.getBoolean("status"));
                os.setCancelada(rs.getBoolean("cancelada"));
                Cliente c = this.exibir(id);
                os.setCliente(c);
                ListaEquipamento lista = new ListaEquipamento();
                lista.setEquipamento(rs.getString("listaequipamentos.equipamento"));
                lista.setAcessório(rs.getString("listaequipamentos.acessorios"));
                lista.setDefeito(rs.getString("listaequipamentos.defeito"));
                lista.setId(rs.getInt("listaequipamentos.id_lista"));
                lista.setObservacao(rs.getString("listaequipamentos.observacoes"));
                os.setListaEquipamentos(lista);
                os.setValorTotal(rs.getDouble("ordemservico.valorTotal"));
                JDBCOrdemServico jos = new JDBCOrdemServico();
                List<LogOs> logs = jos.buscarLog(os.getNumOS());
                LogOs log = logs.get(1);
                os.setLog(log);
                os.setLogOS(logs);
                List<Produto> prods = jos.buscarProdutos(os.getNumOS());
                os.setProdutos(prods);
                List<Servico> servs = jos.buscarServicos(os.getNumOS());
                os.setServicos(servs);
                
                ordens.add(os);
            }
            
            ps.close();
            conexao.close();
            return ordens;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível buscar lista de OS " + ex.getMessage(), ex);
        }
    }

    public void autorizaServico(int numOs, int idserv) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE execucao SET `autorizado` = 1 WHERE (fk_os = ? AND fk_servicos = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, numOs);
            ps.setInt(2, idserv);
            
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema ao autorizar Serviço " + ex.getMessage(), ex);
        }
        
    }

    public Execucao pegarExec(int numOs, int idserv) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT * FROM execucao WHERE fk_os = ? AND fk_servicos = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, numOs);
            ps.setInt(2, idserv);
            Execucao exc = new Execucao();
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                exc.setAutorizado(rs.getBoolean("autorizado"));
                exc.setStatus(rs.getBoolean("status"));
                exc.setIdOs(rs.getInt("fk_os"));
                exc.setIdServ(rs.getInt("fk_servicos"));
            }
            
            return exc;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro pegar execução. " + ex.getMessage(), ex);
        }
    }

    public void autorizaProduto(int numOs, int idprod) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE vendaproduto SET `autorizado` = 1 WHERE (fk_OS = ? AND fk_produto = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, numOs);
            ps.setInt(2, idprod);
            
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema ao autorizar Produto " + ex.getMessage(), ex);
        }
        
    }

    public Produto pegarVendaProd(int numOs, int idprod) {
        conexao = ConnectionFactory.getConnection();
        String query = "SELECT * FROM vendaproduto LEFT JOIN\n" +
                    "produto ON produto.id_produto = vendaproduto.fk_produto\n" +
                    "WHERE fk_OS = ? AND fk_produto = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, numOs);
            ps.setInt(2, idprod);
            Produto prod = new Produto();
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                prod.setID(rs.getInt("fk_produto"));    
                prod.setNome(rs.getString("nome"));
                prod.setAutorizado(rs.getBoolean("autorizado"));
                prod.setStatus(rs.getBoolean("status"));
                prod.setValor(rs.getDouble("vendaproduto.valor"));
                
            }
            
            
            return prod;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar venda de produto. " + ex.getMessage(), ex);
        }
    }

    public void desautorizaServico(int numOs, int idserv) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE execucao SET `autorizado` = 0 WHERE (fk_os = ? AND fk_servicos = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, numOs);
            ps.setInt(2, idserv);
            
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema ao autorizar Serviço " + ex.getMessage(), ex);
        }
    }

    public void desautorizarProd(int numOs, int idprod) {
        conexao = ConnectionFactory.getConnection();
        String query = "UPDATE vendaproduto SET `autorizado` = 0 WHERE (fk_OS = ? AND fk_produto = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, numOs);
            ps.setInt(2, idprod);
            
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema ao autorizar Produto " + ex.getMessage(), ex);
        }
    }

    public Boolean confirmarSenha(String email, String senha) {
        conexao = ConnectionFactory.getConnection();
        
        String query = "SELECT * FROM cliente WHERE email = ? AND senha = ?;";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            Boolean correta = false;
            
            if(rs.next()){
                correta = true;
            } else {
                correta = false;
            }
                    
            
            ps.close();
            conexao.close();
            return correta;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Nenhum usuário encontrado" + ex.getMessage(), ex);
        }
    }

    public void mudarSenha(String novaSenha, int id) {
        conexao = ConnectionFactory.getConnection();  
        String query = "UPDATE `sigosbd`.`cliente` SET `Senha` = ? WHERE (`id_cliente` = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, novaSenha);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao mudar a senha " + ex.getMessage(), ex);
        }
    }

    public void alterarEmail(Cliente cl) {
        conexao = ConnectionFactory.getConnection();  
        String query = "UPDATE cliente SET email = ?, emailAlternativo = ? WHERE (id_cliente = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, cl.getEmail());
            ps.setString(2, cl.getEmailAlternativo());
            ps.setInt(3, cl.getId());
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inverter e-mails " + ex.getMessage(), ex);
        } 
    }

    public void mudarEmail(int id, String emailAtual) {
        conexao = ConnectionFactory.getConnection();  
        String query = "UPDATE cliente SET email = ? WHERE (id_cliente = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, emailAtual);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inverter e-mails " + ex.getMessage(), ex);
        } 
    }
    
    public void cadastrarSenha(String novaSenha, int id) {
        conexao = ConnectionFactory.getConnection();  
        String query = "UPDATE `sigosbd`.`cliente` SET `Senha` = ?, `status` = '1' WHERE (`id_cliente` = ?);";
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, novaSenha);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao mudar a senha " + ex.getMessage(), ex);
        }
    }

    
   
}