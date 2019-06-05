/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class ConnectionFactory {    
    
    public static Connection getConnection() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // link jdbc do localhost, banco de dados, usuário, e por fim senha.
            return  DriverManager.getConnection("jdbc:mysql://localhost/sigosbd", "root", "senac");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro com conexão do banco de dados.", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro, classe não encontrada: DRIVER MYSQL", ex);
        }
        
        
    }
    
}
