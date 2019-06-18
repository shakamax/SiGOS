/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.Servico;
import br.com.sigos.model.ordemServico;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class JDBCOrdemServicoTest {
    
    
    public static void main(String[] args) {
        
        JDBCOrdemServico jos = new JDBCOrdemServico();
        JDBCServico js = new JDBCServico();
        ordemServico os = new ordemServico();
        ordemServico oldos = new ordemServico();
        os = jos.exibe(3560);
        
        oldos = jos.exibe(3560);
        
        if(os == null){
            System.out.println("Deu ruim");
        } else{
        
            System.out.println(os.getCliente().getNome() + " e id = " + os.getCliente().getId() );
            System.out.println(os.getNumOS() + " NUMERO OS // Data de abertura = " + os.getDtAbertura());
            //System.out.println(os.getLog().getDescricao() + " ----- Ultima att");
            System.out.println(os.isStatus() + " ---- e seu equip  ------" + os.getListaEquipamentos().getEquipamento());
            Servico e = new Servico();
            Servico e1 = new Servico();
            //e = js.exibir(7);
            //e1 = js.exibir(5);
            //os.getServicos().add(e);
            //os.getServicos().add(e1);
            System.out.println(os.getServicos().size());
            os.getServicos().remove(0);
            
            if(os.getServicos().size() > oldos.getServicos().size()){
                System.out.println("Um serviço foi adicionado na OS");
                for(int x = 0; x < oldos.getServicos().size(); x++){
                    os.getServicos().remove(x);
                }
                for(Servico s: os.getServicos()){
                    System.out.println("Serviço restante : " + s.getNome());
                }
                
            } else if(os.getServicos().size() < oldos.getServicos().size()){
                System.out.println("Um serviço foi retirado da Ordem de Serviço");
                for(int x =0; x < os.getServicos().size(); x++){
                    Servico s = new Servico();
                    s = oldos.getServicos().get(x);
                    System.out.println("Serviço retirado : " + s.getNome());
                    oldos.getServicos().remove(x);
                }
                for(Servico s: oldos.getServicos()){
                    System.out.println("Serviços restantes.");
                    System.out.println(s.getNome());
                }
                if(oldos.getServicos().isEmpty()){
                    System.out.println("Todos os serviços foram retirados.");
                }
            } else {
                System.out.println("Ordem de serviço não teve alterações no serviço.");
            }
//            for(LogOs log: os.getLogOS()){
//                System.out.println(log.getIdLog() + " / " + log.getDataHora() + " : " + log.getDescricao());
//            }
//            
//            for(Servico serv: oldos.getServicos()){
//                for(Servico s: os.getServicos()){
//                    if(serv.getID() == s.getID()){
//                        System.out.println("Igual?" + " " + serv.getNome() + " é igual " + s.getNome());
//                    } else {
//                        System.out.println("Diferente?");
//                    }
//                }
//            }
            
            
//            if(os.getServicos() != null) {
//                for(Servico serv: os.getServicos()){
//                    System.out.println(serv.getID());
//                    JDBCServico js = new JDBCServico();
//                    serv = js.exibir(serv.getID());
//                    System.out.println(serv.getNome());
//                }
//            } else {
//                System.out.println("sem serviços?");
//            }
//            if (os.getLogOS()!= null){
//                for(LogOs log: os.getLogOS()){
//                    System.out.println(log.getDescricao());
//                }
//            }
        
        }
        /*List<ordemServico> ordens = new ArrayList<>();
        ordens = jos.listar();

        for(ordemServico os: ordens){
            System.out.println(os.getNumOS());
            //SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            System.out.println((os.getLog().getDataHora()));
        }*/
    
        
    }
    
}
