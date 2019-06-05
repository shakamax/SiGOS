/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

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
            
            System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
            
    
}
