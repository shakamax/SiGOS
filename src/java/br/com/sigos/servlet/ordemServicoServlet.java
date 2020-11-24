/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.servlet;

import br.com.sigos.jdbc.JDBCCliente;
import br.com.sigos.jdbc.JDBCEquipamento;
import br.com.sigos.jdbc.JDBCFuncionario;
import br.com.sigos.jdbc.JDBCOrdemServico;
import br.com.sigos.jdbc.JDBCProduto;
import br.com.sigos.jdbc.JDBCServico;
import br.com.sigos.model.Cliente;
import br.com.sigos.model.Email;
import br.com.sigos.model.Execucao;
import br.com.sigos.model.Funcionario;
import br.com.sigos.model.ListaEquipamento;
import br.com.sigos.model.Produto;
import br.com.sigos.model.Servico;
import br.com.sigos.model.LogOs;
import br.com.sigos.model.ordemServico;
import br.com.sigos.model.vendaProduto;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 03728827142
 */
public class ordemServicoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException {
        //Criação de todas as classes necessárias
        Cliente cliente = new Cliente();
        ListaEquipamento lista = new ListaEquipamento();
        ordemServico os = new ordemServico();
        Servico serv = new Servico();
        ordemServico oldos = new ordemServico();
        JDBCCliente jc = new JDBCCliente();
        JDBCServico js = new JDBCServico();
        JDBCProduto jp = new JDBCProduto();
        JDBCFuncionario jf = new JDBCFuncionario();
        JDBCEquipamento jl = new JDBCEquipamento();
        JDBCOrdemServico jos = new JDBCOrdemServico();
        vendaProduto vendProd = new vendaProduto();
        Execucao exec = new Execucao();
        LogOs log = new LogOs();
        Funcionario func = new Funcionario();
        Email em = new Email();
        
        
        Boolean error = false;
        String msg = "";
        String tipo = "";
        
        HttpSession sessao = request.getSession();

        
        String acao = request.getParameter("acao");
        
        if(acao.equals("nova")){

            Cliente clientes = new Cliente();
            List<Servico> servicos = new ArrayList<>();
            List<Produto> produtos = new ArrayList<>();
            List<ListaEquipamento> listaEq = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            cliente = jc.exibir(id);
            listaEq = jl.Listar(id);
            servicos = js.listar();
            produtos = jp.listar();
            
            msg = request.getParameter("msg");
            if(msg != null){
                if(msg.equals("servico")){
                   msg = "Serviço cadastrado com sucesso."; 
                   tipo = "alert-success";
                   request.setAttribute("msg", msg);
                   request.setAttribute("tipo", tipo);
                }else if(msg.equals("equipamento")){
                   msg = "Equipamento cadastrado com sucesso."; 
                   tipo = "alert-success";
                   request.setAttribute("msg", msg);
                   request.setAttribute("tipo", tipo);
                }
            }
            
            request.setAttribute("listas", listaEq);
            request.setAttribute("cliente", cliente);
            request.setAttribute("servicos", servicos);
            request.setAttribute("produtos", produtos);
            
            request.getRequestDispatcher("NovaOS.jsp").forward(request, response);
            
        }else if (acao.equals("clientes")){

            List<Cliente> clientes = new ArrayList<>();
            clientes = jc.Listar();
            
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("clienteParaOs.jsp").forward(request, response);            
        } else if (acao.equals("mudarLista")){
             
            int idLista = Integer.parseInt(request.getParameter("id"));
            lista = jl.exibir(idLista);
            
            String defeito = lista.getDefeito();
            if (defeito == null){
                defeito = "Nenhum";
            }
            try (PrintWriter out = response.getWriter()) {
                out.print(defeito);
            }
            
            
            
            // --------------CRIAR OS -----------------
        } else if (acao.equals("criarOS")) {
            
            double v = 0;
            // Cliente
            int idcliente = Integer.parseInt(request.getParameter("idcliente"));
            cliente = jc.exibir(idcliente);
            os.setCliente(cliente);
            
            //Lista de equipamentos
            int idlista = Integer.parseInt(request.getParameter("ListaEquipamentos"));
            lista = jl.exibir(idlista);
            //DEFEITO será salvo e alterado já.
            lista.setDefeito(request.getParameter("defeito"));
            jl.alterar(lista);
            os.setListaEquipamentos(lista);
            int intOs = jos.inserir(os);
            
            vendProd.setIdOs(intOs);
            
            String[] prod = request.getParameterValues("Produtos[]");
            
            if (prod != null) {
                
                for(int a = 0; a < prod.length; a++){
                    Produto produto = new Produto();
                    produto = jp.exibir(Integer.parseInt(prod[a]));
                    if(cliente.getStatus()){
                        vendProd.setAutorizo(false);
                    } else {
                        vendProd.setAutorizo(true);
                    }
                    vendProd.setProduto(produto);
                    vendProd.setQtd(1);
                    vendProd.setValor(produto.getValor());
                    jos.inserirProd(vendProd);
                }
            }
            
            exec.setIdOs(intOs);
            String[] servs = request.getParameterValues("Servicos[]");
            
            if (servs != null) {
                
                for (int a = 0; a < servs.length; a++) {
                    exec.setIdServ(Integer.parseInt(servs[a]));
                    if(cliente.getStatus()){
                        exec.setAutorizado(false);
                    } else {
                        exec.setAutorizado(true);
                    }
                    
                    jos.inserirExec(exec);
                    
                }
                
            }
            
            log.setIdOs(intOs);
            
            //int id = Integer.parseInt(request.getParameter("idfunc"));
            //func = jf.exibir(id);
            func = (Funcionario) sessao.getAttribute("user");
            
            log.setDescricao("OS criada por " + func.getNome());
            
            jos.inserirLog(log);
            //envio de notificação
//            em.setDestinatario(cliente.getEmail());
//            em.setAssunto("SiGOS Cloud - OS Criada");
//            em.setTexto("<h1>Olá " + cliente.getNome() + "</h1> <br> <strong> Sua ordem de Serviço fora criada com sucesso, visite nosso site para maiores informações.</strong> <br> <p>" + log.getDescricao() +"</p>" );
//            em.enviarGmail();
            
            msg = "oscriada";
            
            os = jos.exibe(intOs);
            for(Servico se: os.getServicos()){
                if(se.isStatus()){
                    v += se.getValor();
                }
            }
            for(Produto pr: os.getProdutos()){
                if(pr.isAutorizado()){
                    v += pr.getValor();
                }
            }
            os.setValorTotal(v);
            jos.atualizarValor(os);
            
            
            
            response.sendRedirect("ordemServicoServlet?acao=listar&msg=" + msg);
            
            
            //-------BUSCA NO BANCO DE DADOS----------
        } else if (acao.equals("buscar")) {
            String coluna = request.getParameter("coluna");
            String busca = request.getParameter("busca");
            
            
            //busca = "'%"+busca+"%'";
            System.out.println(coluna);
            System.out.println(busca);
            System.out.println(coluna);
            
            List<Cliente> clientes = jc.buscar(coluna, busca);
            
            
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("clienteParaOs.jsp").forward(request, response); 
            
            
            //-----------NOVO EQUIPAMENTO------------
        } else if (acao.equals("novoEq")) {

            
            lista.setEquipamento(request.getParameter("equipamento"));
            lista.setAcessório(request.getParameter("acessorios"));
            lista.setObservacao(request.getParameter("observacao"));
            lista.setDefeito(request.getParameter("defeito"));
            lista.setFk_cliente(Integer.parseInt(request.getParameter("fk_cliente")));
            jl.inserir(lista);
            
            response.sendRedirect("ordemServicoServlet?acao=nova&id=" + lista.getFk_cliente());
            
            //---------NOVO SERVIÇO---------
        } else if (acao.equals("novoServ")) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            sessao.removeAttribute("tipo");
            sessao.removeAttribute("msg");
            
            serv.setNome(request.getParameter("nome"));
            serv.setValor(Double.parseDouble(request.getParameter("valor").replace(".", "").replace("," , ".")));
            serv.setDescricao(request.getParameter("descricao"));
                
            js.inserir(serv);
            response.sendRedirect("ordemServicoServlet?acao=nova&id=" + id);

            
            
            
            
            
            //--------LISTAR ORDENS DE SERVIÇOS----------
        } else if ((acao.equals("")) || acao.equals("listar")) {
            request.setCharacterEncoding("UTF-8");

            List<ordemServico> listaOS = new ArrayList<>();
            
            msg = request.getParameter("msg");
            if(msg != null){
                msg = request.getParameter("msg");
                tipo = request.getParameter("tipo");
                if(msg.equals("oscriada")){
                    msg = "Ordem de serviço foi criada com sucesso!";
                    tipo = "alert-success";
                }else if(msg.equals("alterada")){
                    msg = "Ordem de serviço foi alterada com sucesso!";
                    tipo = "alert-success";
                }else if(msg.equals("erroServ")){
                    msg = "Não foi possível excluir serviço, pois o mesmo já foi feito.";
                    tipo = "alert-danger";
                }else if(msg.equals("erroProd")){
                    msg = "Produto já instalado, contate o administrador para retirá-lo";
                    tipo = "alert-danger";
                }else if(msg.equals("osalterada")){
                    msg = "Ordem de serviço alterada com sucesso.";
                    tipo = "alert-success";
                }else if(msg.equals("osconcluida")){
                    msg = "Ordem de serviço foi finalizada com sucesso!";
                    tipo = "alert-success";
                }else if(msg.equals("ospendente")){
                    msg = "Há ainda algum serviço ou produto pendente na Ordem de Serviço, conclua estes antes.";
                    tipo = "alert-warning";
                }else if(msg.equals("oscancelada")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    msg = "Ordem de serviço " + id + " foi cancelada.";
                    tipo = "alert-danger";
                }else if(msg.equals("modIlegal")){
                    msg = "Ocorreu um erro na seleção da coluna.";
                    tipo = "alert-danger";
                }else if(msg.equals("naoAutorizado")){
                    msg = "Apenas administradores podem reabrir uma O.S.";
                    tipo = "alert-danger";
                }else if(msg.equals("osReaberta")){
                    msg = "Ordem de Serviço foi reaberta.";
                    tipo = "alert-warning";
                }
                request.setAttribute("msg", msg);   
                request.setAttribute("tipo", tipo);   
            }

            
            
            listaOS = jos.listar();
           //os = listaOS.get(0);
            //System.out.println(os.getLog().getDataHora());
            
            
            
            
            request.setAttribute("listaOS", listaOS);
            
            
            request.getRequestDispatcher("listaOS.jsp").forward(request, response);
            //-----------------GERENCIAR ORDEM DE SERVICO-----------------------
        } else if (acao.equals("gerenciar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            os = jos.exibe(id);
            List<Produto> prods = new ArrayList<>();
            prods = jp.listar();
            List<Servico> servs = new ArrayList<>();
            servs = js.listar();

            
            request.setAttribute("servs", servs);
            request.setAttribute("prods", prods);
            request.setAttribute("os", os);
            request.getRequestDispatcher("gerenciarOS.jsp").forward(request, response);
            
            
            
            //-----------------------FECHAMENTO?----------------------- 
        }else if(acao.equals("salvar")){

            Boolean mudado = false;
            em.setTexto("<h1>Olá " + cliente.getNome() + "<h1> <br> <strong> Sua ordem de serviço sofreu uma alteração.</strong> <br>" );
            
            
            int idos = Integer.parseInt(request.getParameter("idos"));
            int idc = Integer.parseInt(request.getParameter("clienteid"));
            cliente = jc.exibir(idc);
            os = jos.exibe(idos);
            oldos = jos.exibe(idos);
            func = (Funcionario) sessao.getAttribute("user");
            log.setIdOs(idos);
            //ASSIM QUE SE PEGA FUNCIONÁRIO PARA LOG
            //setar todas os objetos primeiramente.
            
            double v = 0;
            
            lista = os.getListaEquipamentos();
            lista.setDefeito(request.getParameter("defeito"));
            String comp1 = oldos.getListaEquipamentos().getDefeito();
            String comp2 = lista.getDefeito();
            if(!comp1.equals(comp2)){
                jl.alterar(lista);
                log.setDescricao("Defeito foi do equipamento foi alterado para " + lista.getDefeito() + " por " + func.getNome());
                jos.inserirLog(log);
                
                mudado = true;
                em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
            
            }
            //ESTE ESTOU FAZENDO ALTERAÇÕES DA PRIMEIRA COISA, QUE É DEFEITO DO EQUIPAMENTO
            String prazo = request.getParameter("dtPrazo");
            
            if(!prazo.equals("")){
                Date prazoEntrega = new SimpleDateFormat("yyyy-MM-dd").parse(prazo);
                os.setPrazoEntrega(prazoEntrega);
                
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                
                if(!os.getPrazoEntrega().equals(oldos.getPrazoEntrega())){
                    
                    log.setDescricao("Prazo de entrega foi atualizado para : " + fmt.format(os.getPrazoEntrega()) + " Por : " + func.getNome());
                    jos.inserirLog(log);
                    jos.alterar(os);
                    
                    mudado = true;
                    em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                    
                }
            }else{
                os.setPrazoEntrega(null);
            }
            if(!request.getParameter("garantia").equals("")){
                String gar = request.getParameter("garantia");
                os.setGarantia(gar);
            }
            String garantia = os.getGarantia();
            String garantia2 = oldos.getGarantia();
            
            System.out.println("DEBUG TIME ");
            System.out.println(garantia == null);
            
            if(garantia == null){
                garantia = "vazio";
            }
            if(garantia2 == null){
                garantia2 = "vazio";
            }
            System.out.println("DEBUG TIME 2");
            System.out.println(garantia);
            

            System.out.println("Tentando imprimir essa garantia ");
            System.out.println(!garantia2.equals(garantia));
            System.out.println(oldos.getGarantia());
            System.out.println(garantia2.equals(""));
            

            if(!garantia.equals(garantia2)){

                log.setDescricao("Garantia da O.S foi alterada para : \"" + garantia + "\"  Por  " + func.getNome() );
                jos.alterar(os);
                jos.inserirLog(log);
                
                mudado = true;
                em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
            }
 

//            TODOS ESSES CÓDIGOS SÃO TRATAMENTOS PARA O ATRIBUTO DE SERVIÇOS
            String[] s = request.getParameterValues("serv[]");
            if(s != null){
                
                List<Servico> servicos = new ArrayList<>();
                for(int i = 0; i < s.length; i++){
                    Servico servico = new Servico();
                    servico = js.exibir(Integer.parseInt(s[i]));
                    servicos.add(servico);
                }
                
                os.setServicos(servicos);
                oldos = jos.exibe(idos);

                if(os.getServicos().size() > oldos.getServicos().size()){
                    

                    
                    
                    //SE O OLDOS FOR DIFERENTE DE VAZIO QUER DIZER QUE ELE TEM OUTRO SERVIÇO DENTRO
                    if(!oldos.getServicos().isEmpty()){
                        System.out.println("Ai ele faz isso!");
                        //AQUI ELE CRIA UMA LISTA DE SERVIÇO.
                        List<Servico> servs = new ArrayList<>();
                        
                        for(Servico se: oldos.getServicos()){
                            servs.add(se);
                        }

                        //serviços iguais do servs serão retirados pra não dar conflito
                        for(Servico se: servs){
                            System.out.println(se.getNome());
                            os.retirarServico(se);
                        }
                    }
                    exec.setIdOs(idos);
                    log.setIdOs(idos);
                    
                    //===================================================================
                    //Toda a lógica de adição foi feita aqui, até fica mais fácil
                    for(Servico se: os.getServicos()){
                        exec.setIdServ(se.getID());
                        if(cliente.getStatus()){
                            exec.setAutorizado(false);
                        } else {
                            exec.setAutorizado(true);
                        }
                        exec.setStatus(false);
                        
                        jos.inserirExec(exec);
                        if(cliente.getStatus()){
                            log.setDescricao("Serviço \"" + se.getNome() + "\" foi solicitado para O.S por " + func.getNome());
                            System.out.println(log.getDescricao());
                            jos.inserirLog(log);
                            
                            mudado = true;
                            em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + " entre no site para aceitar o serviço</p> <br>" );
                        }else {
                            log.setDescricao("Serviço \"" + se.getNome() + "\" foi adicionado na O.S por " + func.getNome());
                            System.out.println(log.getDescricao());
                            jos.inserirLog(log);
                            
                            mudado = true;
                            em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                        }
                    }
                    
                    
                    System.out.println("Servico adicionado");
                    
                    //SE A OLDOS FOR MAIOR QUE A OS. ENTÃO UM SERVIÇO FOI RETIRADO.
                }else if(os.getServicos().size() < oldos.getServicos().size()){
                    System.out.println("Serviço retirado.");
                    
                    List<Servico> servs = new ArrayList<>();
                    
                    for(Servico se: os.getServicos()){
                        servs.add(se);
                    }
                    
                    for(Servico se: servs){
                        System.out.println("Retirando o serviço " + se.getNome());
                        oldos.retirarServico(se);
                    }

                    exec.setIdOs(idos);
                    log.setIdOs(idos);
                    for(Servico se: oldos.getServicos()){
                        if(se.isStatus()){
                            System.out.println("Serviço já foi prestado, não pode ser retirado");
                            msg = "erroServ";
                            tipo = "alert-danger";
                            error = true;
                        }else {
                            exec.setIdServ(se.getID());
                            jos.excluirExec(exec);
                            log.setDescricao("O Serviço \" " + se.getNome() + " \" foi retirado por " + func.getNome());
                            jos.inserirLog(log);
                            
                            mudado = true;
                            em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                        }
                    }
                
                }
                
            } else {
                    //este verifica se o oldos tava vazio
                    if(oldos.getServicos().isEmpty()){
                   
                        System.out.println("Não tinha nada antes e não tinha nada agora.");
                    }else {
                        
                        exec.setIdOs(idos);
                        for(int i = 0;i < oldos.getServicos().size(); i++){
                            Servico se = new Servico();
                            se = oldos.getServicos().get(i);
                            os.retirarServico(se);
                            //verifica se o serviço já foi prestado ou não. para poder retirar
                            
                            if(se.isStatus()){
                                System.out.println("Não é possível retirar serviço.");
                                msg = "erroServ";
                                tipo = "alert-danger";
                                error = true;
                            }else {
                                exec.setIdServ(se.getID());
                                
                                jos.excluirExec(exec);
                                log.setDescricao("O Serviço \" " + se.getNome() + " \" foi retirado por " + func.getNome());
                                jos.inserirLog(log);
                                
                                mudado = true;
                                em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                            }
                        }


                        System.out.println("Todos serviços foram retirados da OS.");
               }
            }
            
            
            
            String[] p = request.getParameterValues("prods[]");
            
            if(p != null){
                List<Produto> prods = new ArrayList<>();
                
                for(int i = 0; i < p.length; i++){
                    Produto pr = new Produto();
                    pr = jp.exibir(Integer.parseInt(p[i]));
                    prods.add(pr);
                }
                os.setProdutos(prods);
                oldos = jos.exibe(idos);
                
                
                //CASO DIFERENTE DE NULO, AQUI COMEÇA AS COMPARAÇÕES DE ATUALIZAÇÕES
                if(os.getProdutos().size() > oldos.getProdutos().size()){
                    System.out.println("Produto foi adicionado");
                    List<Produto> produtos = new ArrayList<>();
                    
                    for(Produto pr: oldos.getProdutos()){
                        System.out.println("Itens da antiga OS. " + pr.getNome());
                        produtos.add(pr);
                    }
                    for(Produto pr: produtos){
                        os.retirarProduto(pr);
                    }
                    
                    log.setIdOs(idos);
                    vendProd.setIdOs(idos);
                    //este é onde a inserção de tudo vai rolar.
                    for(Produto pr: os.getProdutos()){
                        System.out.println("Serviço adicionado. " + pr.getNome());
                        vendProd.setProduto(pr);
                        if(cliente.getStatus()){
                            vendProd.setAutorizo(false);
                            log.setDescricao("O Produto \" " + pr.getNome() + " \" foi solicitado por " + func.getNome());
                        } else {
                            vendProd.setAutorizo(true);
                            log.setDescricao("O Produto \" " + pr.getNome() + " \" foi adicionado por " + func.getNome());
                        }
                        jos.inserirProd(vendProd);
                        jos.inserirLog(log);
                        
                        mudado = true;
                        em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                    }
                    
                    
                } else if(os.getProdutos().size() < oldos.getProdutos().size()) {
                    System.out.println("Produto foi retirado.");
                    List<Produto> produtos = new ArrayList<>();
                    
                    for(Produto pr: os.getProdutos()){
                    produtos.add(pr);
                    }
                    for(Produto pr: produtos){
                        oldos.retirarProduto(pr);
                    }
                    
                    vendProd.setIdOs(idos);
                    log.setIdOs(idos);
                    for(Produto pr: oldos.getProdutos()){
                        System.out.println("Produto retirado foi este " + pr.getNome());
                        if(pr.isStatus()){
                            msg = "erroProd";
                        }else{
                            vendProd.setProduto(pr);
                            log.setDescricao("O Produto \" " + pr.getNome() + " \" foi retirado por " + func.getNome());
                            jos.excluirProd(vendProd);
                            jos.inserirLog(log);
                            
                            mudado = true;
                            em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                        }
                    }
                    
                    
                    
                } else {
                    System.out.println("Produtos continuam o mesmo.");
                }
                
                
            }else {
                
                if(oldos.getProdutos().isEmpty()){
                    System.out.println("Não tinha nada aqui, não tem nada de novo.");
                } else {
                    System.out.println("Todos os produtos foram excluídos");
                    vendProd.setIdOs(idos);
                    log.setIdOs(idos);
                    
                    for(Produto pr: oldos.getProdutos()){
                        System.out.println("Este produto será excluído... " + pr.getNome());
                        vendProd.setProduto(pr);
                        
                        if(pr.isStatus()){
                            System.out.println("Produto já foi instalado, Solicite o administrador para poder retira-lo.");
                            msg = "erroProd";
                            
                        } else {
                            System.out.println("Produto " + pr.getNome() + " Foi retirado com sucesso");
                            
                            log.setDescricao("O Produto \" " + pr.getNome() + " \" foi retirado por " + func.getNome());
                            jos.excluirProd(vendProd);
                            jos.inserirLog(log);
                            
                            mudado = true;
                            em.setTexto(em.getTexto() + "<p>" + log.getDescricao() + "</p> <br>" );
                        }
                        
                    }
                    
                }
            }
            
//            if(mudado){
//                //envio de notificação
//                em.setDestinatario(cliente.getEmail());
//                em.setAssunto("SiGOS Cloud - OS Atualizada");
//                em.setTexto(em.getTexto() + "<p> Nos agradecemos a preferência, qualquer dúvida não deixe de nos contatar. </p>" );
//                em.enviarGmail();
//            }

                if(!error){
                    msg = "osalterada";
                }

                os = jos.exibe(idos);
                for(Servico se: os.getServicos()){
                    if(se.isStatus()){
                        v += se.getValor();
                    }
                }
                for(Produto pr: os.getProdutos()){
                    if(pr.isAutorizado()){
                        v += pr.getValor();
                    }
                }
                os.setValorTotal(v);
                jos.atualizarValor(os);


                response.sendRedirect("ordemServicoServlet?acao=listar&msg=" + msg);
            
            } else if(acao.equals("cancelar")){

                func = (Funcionario) sessao.getAttribute("user");
                int idOs = Integer.parseInt(request.getParameter("id"));
                
                jos.cancelarOS(idOs);
                log.setIdOs(idOs);
                log.setDescricao("O.S. Nº " + idOs + "  foi CANCELADA  por " + func.getNome());
                jos.inserirLog(log);
                
                cliente.setEmail(jos.pegarEmailOs(idOs));
                
                //envio de notificação
                em.setDestinatario(cliente.getEmail());
                em.setAssunto("SiGOS Cloud - OS Cancelada");
                em.setTexto("<h1>"+ log.getDescricao() + "  <strong> :( </strong> </h1><p> Qualquer dúvida, se isso foi um engano ou algum erro, entre em contato. <br> Nos agradecemos a preferência. </p>" );
                //em.enviarGmail();
                
                tipo = "alert-warning";
                msg = "oscancelada";

                response.sendRedirect("ordemServicoServlet?acao=listar&msg=" + msg + "&id=" + idOs);
                
            } else if(acao.equals("finalizar")){
                
                double v = 0;
                
                func = (Funcionario) sessao.getAttribute("user");
                int idOs = Integer.parseInt(request.getParameter("id"));
                
                os = jos.exibe(idOs);
                boolean finalizar = true;
                for(Servico se: os.getServicos()){
                    if(!se.isAutorizado() || !se.isStatus()){
                        finalizar = false;
                    }
                }
                for(Produto pr: os.getProdutos()){
                    if(!pr.isAutorizado()){
                        finalizar = false;
                    }
                }
                
                if(finalizar){
                    jos.finalizar(idOs);
                    log.setIdOs(idOs);
                    log.setDescricao("O.S Foi finalizada por " + func.getNome());
                    jos.inserirLog(log);
                    
                    //envio de notificação
                    em.setDestinatario(cliente.getEmail());
                    em.setAssunto("SiGOS Cloud - OS Finalizada");
                    em.setTexto("<h1>"+ log.getDescricao() + "  <strong> :( </strong> </h1><p> Você pode buscarseu equipamento na Loja. <br> Nos agradecemos a preferência, e se caso ainda tenha alguma dúvida, entre em contato. </p>" );
                    //em.enviarGmail();
                    
                    msg = "osconcluida";
                }else{
                    msg = "ospendente";
                    tipo = "alert-warning";
                }
                
                
                os = jos.exibe(idOs);
                for(Servico se: os.getServicos()){
                    if(se.isStatus()){
                        v += se.getValor();
                    }
                }
                for(Produto pr: os.getProdutos()){
                    if(pr.isAutorizado()){
                        v += pr.getValor();
                    }
                }
                os.setValorTotal(v);
                jos.atualizarValor(os);
                

                response.sendRedirect("ordemServicoServlet?acao=listar&msg=" + msg);
            } else if(acao.equals("visualizar")){
                
                    int id = Integer.parseInt(request.getParameter("id"));
                    os = jos.exibe(id);
                    List<Produto> prods = new ArrayList<>();
                    prods = jp.listar();
                    List<Servico> servs = new ArrayList<>();
                    servs = js.listar();


                    request.setAttribute("servs", servs);
                    request.setAttribute("prods", prods);
                    request.setAttribute("os", os);
                    
                    
                    request.getRequestDispatcher("visualizarOs.jsp").forward(request, response);
            } else if(acao.equals("buscarOS")){
                List<ordemServico> listaOS = new VirtualFlow.ArrayLinkedList<>();
                String date;
                String coluna = request.getParameter("coluna");
                if(coluna.equals("numOS")){
                    coluna = "ordemservico.Id_Os";
                }else if(coluna.equals("cliente")){
                    coluna = "cliente.nome";
                }else if(coluna.equals("equipamento")){
                    coluna = "listaequipamentos.equipamento";
                }else {
                    response.sendRedirect("ordemServicoServlet?acao=listar&msg=modIlegal");
                }
                String palavraChave = request.getParameter("busca");
                String status = request.getParameter("status");
                if(status != null){
                    if(status.equals("aberta")){
                        String colunaData = "ordemservico.dtAbertura"; 
                        if(palavraChave.equals("")){
                            date = request.getParameter("dataAber");
                            if(date.equals("")){
                                listaOS = jos.listarAbertas();
                            } else {
                                listaOS = jos.listarAbertas(date);
                            }
                        } else {
                            date = request.getParameter("dataAber");
                            System.out.println(date);
                            if(date.equals("0")){
                                date = "";
                            }
                            listaOS = jos.listarBusca(palavraChave, coluna, true, date, colunaData);
                        }
                    }else if(status.equals("Fechada")){
                        date = request.getParameter("dataAber");
                        System.out.println(date);
                        if(date.equals("0")){
                            date = "";
                        }
                       String colunaData = "ordemservico.dataFechamento";
                       
                       listaOS = jos.listarBusca(palavraChave, coluna, false, date, colunaData);
                       
                    }else if(status.equals("Cancelada")){
                        if(palavraChave.equals("")){
                            listaOS = jos.listarCanceladas();
                        }else {
                            listaOS = jos.listarCanceladas(palavraChave, coluna);
                        }
                    }
                }

                
                System.out.println(listaOS.size());
                request.setAttribute("listaOS", listaOS);
                request.getRequestDispatcher("listaOS.jsp").forward(request, response);
            } else if(acao.equals("reabrir")){
                HttpSession s = request.getSession();
                Funcionario user = (Funcionario) s.getAttribute("user");
                if(user.getFuncao().equals("Administrador")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    jos.reabrirOS(id);
                    log.setIdOs(id);
                    log.setDescricao("Ordem de Serviço foi Reaberta por " + user.getNome());
                    jos.inserirLog(log);
                    msg = "osReaberta";
                }else {
                    msg = "naoAutorizado";
                }
                response.sendRedirect("ordemServicoServlet?acao=listar&msg=" + msg);
            }
            
            
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ordemServicoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ordemServicoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
