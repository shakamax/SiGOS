/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import br.com.sigos.jdbc.JDBCFuncionario;
import br.com.sigos.model.Funcionario;
import br.com.sigos.model.Usuario;

/**
 *
 * @author Guilherme
 */
public class LogarTest {
    
    public static void main(String[] args) {
        
        String email = "guilherme@email.com";
        String senha = "admin";
        Usuario user = new Usuario();
        String senhac = user.criptografar(senha);
        
        JDBCFuncionario jf = new JDBCFuncionario();
        Funcionario f = jf.logar(email, senhac);
        
        System.out.println(f.getNome() + " " + f.getEmail());
        
        
        
    }
    
    
}
