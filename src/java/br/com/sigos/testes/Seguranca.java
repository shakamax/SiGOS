/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import br.com.sigos.model.Usuario;
import java.security.MessageDigest;
import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 03728827142
 */
public class Seguranca {
    
    public static void main(String[] args) {
        
        try {
            String s = "admin";
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            String senha = new BigInteger(1,m.digest()).toString(16);
            
            Usuario user = new Usuario();
            String senhateste = user.criptografar("admin");
            
            System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
            System.out.println("Senha criptografada = " + senha );
            System.out.println("Senha criptografada = " + senhateste );
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro durante a sua senha." + ex.getMessage(), ex);
        }
        
        
    }
            
    
}
