/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import br.com.sigos.jdbc.JDBCProduto;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Categoria;
import java.util.List;

/**
 *
 * @author 03728827142
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JDBCProduto prod =  new JDBCProduto();
        List<Produto> produtos = prod.listar();

        String msg = "ok";
        if(msg == "ok"){
            System.out.println("Certo.");
        }
    }
    
}
