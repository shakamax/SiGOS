/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.dao;

import br.com.sigos.model.Cliente;
import java.util.List;

/**
 *
 * @author 04341474197
 */
public interface ClienteDAO {
    public void inserir(Cliente cliente);
    public void deletar(int id);
    public void alterar (Cliente cliente);
    public Cliente exibir (int id);
    public List<Cliente> Listar ();
}
