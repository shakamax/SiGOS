/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import br.com.sigos.model.Email;

/**
 *
 * @author Guilherme
 */
public class emailTest {

    public static void main(String[] args) {
        
        Email em = new Email();
        
        em.setDestinatario("guilhermes.shaka@gmail.com");
        em.setAssunto("SiGOS Cloud - OS Criada");
        em.setTexto("<h1>Olá Guilherme </h1> <br> <strong> Sua ordem de Serviço fora criada com sucesso, visite nosso site para maiores informações.</strong> <br> OS foi criada por João Guilherme Machado.");
        em.enviarGmail();
        
    }
    
    
}
