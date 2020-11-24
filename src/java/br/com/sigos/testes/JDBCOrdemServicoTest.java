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
        os = jos.exibe(3559);
        
        oldos = jos.exibe(3559);
        
        if(os == null){
            System.out.println("Deu ruim");
        } else{
            //ESTE CODIGO ESTA VERIFICANDO SE AS INFORMAÇÕES ESTÃO CERTAS E SE O CODIGO TA PUXANDO A ORDEM MESMO
            System.out.println(os.getCliente().getNome() + " e id = " + os.getCliente().getId() );
            System.out.println(os.getNumOS() + " NUMERO OS // Data de abertura = " + os.getDtAbertura());
            //System.out.println(os.getLog().getDescricao() + " ----- Ultima att");
            System.out.println(os.isStatus() + " ---- e seu equip  ------" + os.getListaEquipamentos().getEquipamento());
            System.out.println("Serviços da OS ||: ");
            System.out.println("Serviços da OS \\/: ");
            //ESTE DAQUI SÓ TA DIZENDO OS SERVICOS QUE TEM NA OS ANTES DE QUALQUER COISA.
            for(Servico s: os.getServicos()){
                System.out.println(s.getNome() + " id # " + s.getID());
            }
            System.out.println("--------------------");
            Servico e = new Servico();
            Servico e2 = new Servico();
            
            e = js.exibir(1);
            for(Servico s: os.getServicos()){
                System.out.println("SERVIÇOS ANTERIORES = " + s.getNome());
            }
            //ESTE AQUI FALA TODOS OS SERVIÇOS QUE TEM AINDA NA OS DEPOIS DE RETIRAR.
            //RETIRAR TESTADO.
            //os.retirarServico(e);
            os.getServicos().add(e);
            
            //AQUI VERIFICA SE A O.S ADICIONOU, REMOVEU OU NÃO ALTEROU OS SERVIÇOS
            if(os.getServicos().size() > oldos.getServicos().size()){
                System.out.println("Um serviço foi adicionado na OS.");
                //CRIO ESTA LISTA PARA MANIPULAR ITENS A SEREM RETIRADOS DA LISTA DA OS.
                List<Servico> listaRet = new ArrayList<>();
                //FAÇO UM LOOP DOS ITENS QUE PRECISO RETIRAR
                for(int i = 0; i < oldos.getServicos().size(); i++ ){
                    Servico se = new Servico();
                    se = oldos.getServicos().get(i);
                    //CRIEI UM SERVIÇO QUE RECEBE O ITEM DA INDEX
                    int id = se.getID();
                    //PEGO O ID PQ ID É MAIS SIMPLES DE COMPARAR
                    
                    for(Servico s: os.getServicos()){
                        //ESTE LOOP É FEITO PARA PEGAR TODOS DA LISTA DA OS
                        //E COMPARAR COM O ID PEGO PELA OS QUE TEM MENOS SERVIÇO
                        
                        int id2 = s.getID();
                        if(id == id2){
                            //SE O ID DA OS QUE TEM MENOS SERVIÇO FOR IGUAL A QUE TEM MAIS ELA VAI SER ADICIONADA NUMA LISTA
                            listaRet.add(s);
                        }
                    }
                    
                }
//                NESTE LOOP, ELE VAI PEGAR TODOS ITENS QUE VIERAM DA LISTA LA ENCIMA
//                E RETIRAR TODOS DA LISTA MAIOR
//                BÁSICAMENTE ELE VAI TIRAR TODOS SERVIÇOS DA OS ANTIGA DA OS NOVA
//                E SÓ FICARÁ O SERVIÇO QUE FOI ADICIONADO
                for(Servico s: listaRet){
                    os.retirarServico(s);
                }
//                AQUI ELE PRINTA O SERVIÇO ADICIONADO, QUE FORAM OS SERVIÇOS QUE SOBRARAM NA LISTA DA OS QUE TINHA MAIS SERVIÇOS
//                OU SEJA NA NOVA OS.
//                É A HORA QUE SERIA FEITA O LOG DA OS.
                for(Servico s: os.getServicos()){
                    System.out.println("Serviço adicionado foi : " + s.getNome());
                }
                
            }else if(os.getServicos().size() < oldos.getServicos().size()){
                //ESTE VERIFICA SE A OS TEVE SERVIÇO RETIRADO
                
                System.out.println("Um serviço foi retirado da ordem de serviço");
                //ESSA LISTA TEVE QUE SER CRIADA, POIS NÃO DA PRA RETIRAR SERVIÇOS
                //ENQUANTO VOCÊ ESTA FAZENDO UM LOOP EM SERVIÇOS
                List<Servico> lista = new ArrayList<>();
                //MUDEI O FOR POR QUE O OUTRO ESTAVA COM DEFEITO NA PARTE DA INTERAÇÃO
                //POIS RETIRAR SERVIÇOS NO OUTRO DEU PROBLEMA
                for(int i = 0; i < os.getServicos().size(); i++){
                    Servico se = new Servico();
                    se = os.getServicos().get(i);
                    int id = se.getID();
                    for(Servico s: oldos.getServicos()){
                        int id2 = s.getID();
                        if(id == id2){
                            System.out.println(id2);
                            lista.add(s);
                        }
                    }
                  
                }
                //ESTE LOOP É PARA TIRAR TODOS OS SERVIÇOS DA OS NOVA DA ANTIGA OS.
                //NESTE CASO A OLDOS SÓ FICARÁ COM O SERVIÇO RETIRADO, ENTÃO PODERÁ SER REGISTRADO
                //O SERVIÇO RETIRADO PODERÁ IR PARA A LOGOS
                for(Servico serv: lista){
                    oldos.retirarServico(serv);
                }
                System.out.println("===================================================");
                for(Servico s: oldos.getServicos()){
                    System.out.println("Item retirado foi " + s.getNome());
                }
            }else {
                System.out.println("Nenhum serviço foi modificado");
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
