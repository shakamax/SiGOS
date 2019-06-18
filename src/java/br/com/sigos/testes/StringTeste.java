/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

/**
 *
 * @author Guilherme
 */
public class StringTeste {
    
    public static void main(String[] args) {
        String busca = "joão";
        
        busca = "'%"+busca+"%'";
        
        System.out.println(busca);
        
        
    }
    
}
