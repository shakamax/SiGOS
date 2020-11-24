<%-- 
    Document   : NovaOS
    Created on : 25/04/2019, 21:01:36
    Author     : 03728827142
--%>

<% 
    String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date());

%>
<%@page import="java.util.Date"%>
<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="user" class="br.com.sigos.model.Funcionario" scope="session" />
<jsp:useBean id="cliente" class="br.com.sigos.model.Cliente" scope="session" />
<jsp:useBean id="produto" class="br.com.sigos.model.Produto" scope="session" />
<jsp:useBean id="servico" class="br.com.sigos.model.Servico" scope="session" />
<jsp:useBean id="lista" class="br.com.sigos.model.ListaEquipamento" scope="session" />
 



<div class="container">

    <a href="ordemServicoServlet?acao=listar" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
     <br>
     <br>
    
<div class="card shadow mb-4">
 <div class="card-header justify-content-between align-items-lg-center flex-row bg-gradient-info">
    <h1 class="h3 mb-4 text-gray-100"> Nova Ordem de Serviço : </h1>
 </div>
    <div class="card-body">
    <form class="form-group" method="post" action="ordemServicoServlet?acao=criarOS">
        <div class="form-row">
            <div class="col-6">
           
            <label for="Cliente">Cliente : </label>
            <input id="cliente" value="${cliente.nome}" class="form-control" disabled="" />
            <input type="hidden" value="${cliente.id}" name="idcliente" id="clienteid" />
            </div>
            <div class="col-5">
            <label for="listaequipamentos"> Equipamento do Cliente : </label>
            <select name="ListaEquipamentos" id="listaeq" class="form-control" required>
                <option value=""> Escolha o equipamento </option>
                <c:forEach items="${listas}" var="lista">
                    <option value="${lista.id}" > ${lista.equipamento} </option>
                </c:forEach>
            </select>
            </div>
            <div class="col-1">
                <button type="button" data-whatever="Nome do Cliente" data-toggle="modal" data-target="#modalequipamento" class="btn btn-info bg-gradient-success" title="Novo Equipamento?" style="margin-top: 25%" > 
                   <i class="fas fa-plus-square"> </i></button>
            </div>

        </div>
        <div class="form-row">
            <div class="col-6">
               <label for="servicos"> Produtos </label>
               <select class="js-example-basic-multiple form-control" name="Produtos[]" multiple="multiple">
                    <c:forEach items="${produtos}" var="produto">
                        <option value="${produto.ID}">${produto.nome}</option>
                        ...
                    </c:forEach>
               </select>

            </div>
            <div class="col-5">
                <label for="servicos"> Serviços </label>
                <select class="js-example-basic-multiple form-control" name="Servicos[]" multiple="multiple">
                    <c:forEach items="${servicos}" var="servico" >
                        <option value="${servico.ID}"> ${servico.nome}</option>
                    </c:forEach>
                 </select>
            </div>
            <div class="col-1">
                <button type="button" data-toggle="modal" data-target="#modalServico" class="btn btn-info bg-gradient-success" title="Novo Serviço?" style="margin-top: 25%" > 
                   <i class="fas fa-plus-square"> </i></button>
            </div>
        </div>
        <div class="form-row">
            <div class="col">
                <label for="defeito"> Defeito : </label>
                <img src="Content/img/loading.gif" width="150px" id="loading" height="150px" style="display: none;"/>
                <input id="defeito" class="form-control" name="defeito" value="" row="3" />
<!--                <textarea name="os.Defeito" id="defeito" class="form-control" rows="4">
                </textarea>-->
            </div>
            <div class="col"> 
                <label>Data de Abertura :</label>
                <input class="form-control" name="os.DataAbertura" id="DtAbertura" value="<% out.print(dataAtual); %>"  disabled />

            </div>

        </div>
                <br>
           <button type="submit" class="btn bg-gradient-success float-right shadow mb-4" style="color: white;"> Abrir O.S.</button>

    </form>
    </div>
 </div>            
    
             
<div class="modal fade" id="modalequipamento" tabindex="-1" role="dialog" aria-labelledby="modalequipamento" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Novo equipamento</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="ordemServicoServlet?acao=novoEq" class="form-group" method="post" >
          <div class="form-group">
            <input type="hidden" name="fk_cliente" id="fk_cliente" value="${cliente.id}" />
            <label for="equipamento" class="col-form-label"> Equipamento : </label>
            <input type="text" class="form-control" id="equipamento" placeholder="Computador, processador, memória, etc..." name="equipamento" />
          </div>
          <div class="form-group">
            <label for="defeito" class="col-form-label">Defeito do equipamento : </label>
            <textarea class="form-control" name="defeito" placeholder="Não inicializa" id="defeito"></textarea>
          </div>
            <div class="form-group">
          <div class="form-group">
            <label for="observacao" class="col-form-label"> Observação : </label>
            <textarea type="text" class="form-control" row="2" id="observacao" placeholder="Arranhão na lateral" name="observacao"></textarea>
          </div>
            <div class="form-group">
                <label for="acessorios" class="col-form-label"> Acessórios </label>
                <input id="acessosrios" name="acessorios" placeholder="Fontes, mouses, cabos..." class="form-control" />
            </div>
            <div class="form-group">
                <button type="button" class="btn bg-gradient-warning" data-dismiss="modal" style="color: white;">Cancelar</button>
                <button type="submit" class="btn bg-gradient-success" style="color: white;">Salvar</button>
            </div>
            </div>
        </form>
    </div>
  </div>
 </div>
</div>
            

<div class="modal fade" id="modalServico" tabindex="-1" role="dialog" aria-labelledby="modalServico" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Novo Serviço</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form class="form-group" method="post" action="ordemServicoServlet?acao=novoServ">
            <div class="form-group">
              <input type="hidden" value="${cliente.id}" name="id" />
              <label for="nome" class="col-form-label"> Nome : </label>
              <input class="form-control" type="text" name="nome" id="nome" />
            </div>
            <div class="form-group">
              <label for="valor" class="col-form-label"> Valor : </label>
              <input type="text" class="form-control price" id="valor" name="valor" />
            </div>
              <div class="form-group">
                  <label for="descricao" class="col-form-label"> Descrição : </label>
                  <textarea id="descricao" row="2" name="descricao" placeholder="Descreva o serviço..." class="form-control"></textarea>
              </div>
            <div class="modal-footer">
              <button type="button" class="btn bg-gradient-warning" data-dismiss="modal" style="color: white;">Cancelar</button>
              <button type="submit" class="btn bg-gradient-success" style="color: white;">Salvar</button>
            </div>
        </form>
      </div>
    </div>
  </div>
</div>

</div>

<jsp:include page="Content/Layout/Footer.jsp" />
