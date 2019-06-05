/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.dao;

import br.com.sigos.model.Produto;
import java.util.List;

/**
 *
 * @author 03728827142
 */
public interface ProdutoDao {
    
    public int inserir(Produto produto);
    public void deletar(int id);
    public void alterar(Produto produto);
    public Produto exibir(int id);
    public List<Produto> listar();
    
}
