/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.dao;

import br.com.sigos.model.Funcionario;
import java.util.List;

/**
 *
 * @author 04341474197
 */
public interface FuncionarioDAO {
    
    public void inserir (Funcionario funcionario);
    public void deletar (int id);
    public void alterar (Funcionario funcionario);
    public Funcionario exibir (int id);
    public List<Funcionario> listar();
            
}
