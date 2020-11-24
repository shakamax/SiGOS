/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.testes;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.ordemServico;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 03728827142
 */
public class ServleteTestOS {
    
    public static void main(String[] args) {
        //ordemServico os = new ordemServico();
        ordemServico oldos = new ordemServico();
        JDBCOrdemServico jos = new JDBCOrdemServico();
        JDBCCliente jc = new JDBCCliente();
        ListaEquipamento lista = new ListaEquipamento();
        List<ordemServico> listaOs = jc.listarOs(1);
        
        for(ordemServico os : listaOs){
            System.out.println(os.getNumOS());
        }
        
        
//        String[] servs = {"Aqui", "Ali"};
//        if(servs.length > -1){
//            System.out.println(servs[0]);
//            System.out.println(servs[1]);
//        }else{
//            System.out.println("Ta vázio");
//        }
        
        
//        List<LogOs> logs = jos.buscarLog(os.getNumOS());
//        
//        LogOs log = logs.get(0);
//        System.out.println(log.getDescricao());
        
//        String prazo = "2019-06-21";
//        if(!prazo.equals("")){
//
//            try {
//                System.out.println("Comparação diferente de vazio");
//                Date data = new SimpleDateFormat("yyyy-MM-dd").parse(prazo);
//                os.setPrazoEntrega(data);
//                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
//                
//                System.out.println("Prazo de entrega foi atualizado para : " + fmt.format(os.getPrazoEntrega()) );
//            } catch (ParseException ex) {
//                Logger.getLogger(ServleteTestOS.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            
//        }else{
//            System.out.println("Comparação igual a vazio");
//        }
            
//        lista = os.getListaEquipamentos();
//        //os.getListaEquipamentos().setDefeito("Defeito diferente");
//        //lista.setDefeito("Defeito diferente");
//        String comp1 = lista.getDefeito();
//        String comp2 = oldos.getListaEquipamentos().getDefeito();
//        
//        if(!comp1.equals(comp2)){
//            System.out.println("Pode fazer o log pangaré");
//            System.out.println(os.getListaEquipamentos().getDefeito());
//            System.out.println(oldos.getListaEquipamentos().getDefeito());
//            
//        }else{
//            System.out.println("Não mudou!");
//        }
        
    }
    
    
    
    
}
