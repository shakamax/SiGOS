/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.dao;

import br.com.sigos.model.Categoria;
import java.util.List;

/**
 *
 * @author 03728827142
 */
public interface CategoriaDao {
    
    public void inserir(Categoria categoria);
    public void deletar(int id);
    public void alterar(Categoria categoria);
    public Categoria exibir(int id);
    public List<Categoria> listar();
    
}
