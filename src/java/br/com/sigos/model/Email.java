/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Guilherme
 */
public class Email {
    
    private String login;
    private String senha;
    private String destinatario;
    private String assunto;
    private String texto;

    public Email() {
        this.login = "sigoscloud@gmail.com";
        this.senha = "sigosgjgv";
    }

    
    
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }


    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }


    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
    public void enviarGmail(){
        
        
        String meuEmail = this.login;
        String minhaSenha = this.senha;

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);
        
        try{
            
            email.setFrom(meuEmail);
            email.setSubject(this.assunto);
            email.setContent(this.texto, "text/html; charset=utf-8");
            email.addTo(this.destinatario);
            email.send();
            System.out.println("Email foi enviado.");
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    
//    public void enviarGmail(){
//    
//        //Propriedades para conectar ao gmail.
//        Properties props = new Properties();    
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", 
//        "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//        //Pra poder usar dentro do autenticador
//        //caso contrário ele vai referenciar o proprio autenticador com o this.
//        String log = this.login;
//        String sen = this.senha;
//
//        Session session = Session.getInstance(props,
//          new javax.mail.Authenticator() {
//               protected PasswordAuthentication getPasswordAuthentication() 
//               {
//                     return new PasswordAuthentication(log , sen);
//               }
//          });
//
//        /** Ativa Debug para sessão */
//        session.setDebug(true);
//
//        try {
//
//          Message message = new MimeMessage(session);
//          message.setFrom(new InternetAddress(this.login)); 
//          //Remetente
//
//          Address[] toUser = InternetAddress.parse(this.destinatario);  
//
//          message.setRecipients(Message.RecipientType.TO, toUser);
//          message.setSubject(this.assunto); //Assunto
//          message.setContent(this.getTexto(), "text/html; charset=utf-8");
//          /**Método para enviar a mensagem criada*/
//          Transport.send(message);
//
//          System.out.println("Email enviado");
//
//         } catch (MessagingException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
    
}
