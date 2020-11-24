/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Guilherme
 */
public class emailTwo {
    
    
    public static void main(String[] args) {
        
        String meuEmail = "sigoscloud@gmail.com";
        String minhaSenha = "sigosgjgv";

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);
        
        try{
            
            email.setFrom(meuEmail);
            email.setSubject("Teste do segundo email.");
            email.setMsg("Teste do segundo.");
            email.addTo("guilhermes.shaka@gmail.com");
            email.send();
            System.out.println("Email foi enviado.");
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
}
