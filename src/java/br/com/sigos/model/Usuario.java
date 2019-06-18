/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import br.com.sigos.testes.Seguranca;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class Usuario {
    
    public String criptografar(String senha){
        try {
            String s = senha;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            String senhac = new BigInteger(1,m.digest()).toString(16);
            
            return senhac;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro durante a criptografia de sua senha." + ex.getMessage(), ex);
        }
    }
    
}
