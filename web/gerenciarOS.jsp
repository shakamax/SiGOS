<%-- 
    Document   : gerenciarOS
    Created on : 02/05/2019, 19:20:15
    Author     : 03728827142
--%>
<% 
    String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date());

%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="os" scope="session" class="br.com.sigos.model.ordemServico" />
<jsp:useBean id="lista" scope="session" class="br.com.sigos.model.ListaEquipamento" />
<jsp:useBean id="prod" scope="session" class="br.com.sigos.model.Produto" />
<jsp:useBean id="serv" scope="session" class="br.com.sigos.model.Servico" />
<jsp:useBean id="log" scope="session" class="br.com.sigos.model.LogOs" />




<div class="container-fluid">
 
<div class="card shadow mb-4">
 <div class="card-header justify-content-between align-items-lg-center flex-row bg-gradient-info">
    <h1 class="h3 mb-4 text-gray-100"> Gerenciando sua O.S : </h1>
    <h1 class="h3 mb-4 text-gray-100"> O.S. ${os.numOS} </h1>
 </div>
    <div class="card-body">
    <form class="form-group" method="post" action="emConstrucao.jsp">
        
<!--ROW FORM-->

<!--ROW FORM-->
        
        <div class="form-row">
            <div class="col-6">
            <label for="Cliente">Cliente : </label>
            <input type="hidden" name="clientid" id="clienteid" value="${os.cliente.id}" />
            <input id="Cliente" name="clienteNome" value="${os.cliente.nome}" disabled class="form-control" value="Cliente Tal"/>
            </div>
            <div class="col-6">
            <label for="listaequipamentos"> Equipamento do Cliente : </label>
            <select name="os.ListaEquipamentos" disabled class="form-control">
                <option value="${os.listaEquipamentos.id}" selected> ${os.listaEquipamentos.equipamento} </option>
            </select>
            </div>
        </div>


    <!--ROW FORM-->
        <div class="form-row">
            <div class="col-6">
               <label for="servicos"> Produtos </label>
               <select class="js-example-basic-multiple form-control" name="Produtos[]" multiple="multiple">
                   <c:forEach items="${prods}" var="prod">
                       <option value="${prod.ID}" > ${prod.nome} </option>
                   </c:forEach>
                   <c:forEach items="${os.produtos}" var="prod">
                       <option value="${prod.ID}" selected="selected" > ${prod.nome} </option>
                   </c:forEach>
               </select>

            </div>
            <div class="col-6">
                <label for="servicos"> Serviços </label>
                <select class="js-example-basic-multiple form-control" name="Servicos[]" multiple="multiple">
                    <c:forEach items="${servs}" var="serv">
                        <option value="${serv.ID}" > ${serv.nome} </option>
                    </c:forEach>
                    <c:forEach items="${os.servicos}" var="serv">
                        <option value="${serv.ID}" selected="selected" disabled> ${serv.nome} </option>
                    </c:forEach>
                    
                 </select>
            </div>
            
<!--            <div class="col-1">
                <button type="button" data-toggle="modal" data-target="#modalServico" class="btn btn-info bg-gradient-success" title="Novo Serviço?" style="margin-top: 25%" > 
                   <i class="fas fa-plus-square"> </i></button>
            </div>-->
            
            
        </div>
            
            
            <!--ROW FORM-->
            <!--ROW FORM-->
            <!--ROW FORM-->
            
        <div class="form-row">
            
            <div class="col"> 
                <label>Data de Abertura :</label>
                <input class="form-control" name="dtAbertura" id="DtAbertura" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${os.dtAbertura}" />"  disabled />
            </div>
            <div class="col">
                <label for="dtPrazo"> Prazo de entrega :</label>
                <input class="form-control" type="Date" value="${os.prazoEntrega}" name="dtPrazo" id="dtPrazo" />
            </div>
            <div class="col">
                <label for="dtfechamento"> Data de fechamento :</label>
                <input class="form-control" type="Date" id="dtFechamento" name="DtFechamento" disabled />
            </div>
            
        </div>
            
        
<!--        FORM ROW        -->
<!--        FORM ROW        -->
<!--        FORM ROW        -->
        <div class="form-row">
            
            <div class="col">
                <label for="os.Defeito"> Defeito : </label>
                <textarea name="os.Defeito" class="form-control" id="defeito" rows="4">
${os.listaEquipamentos.defeito}</textarea>
            </div>
            <div class="col">
                <label for="garantia"> Garantia : </label>e
                <input id="garantia" class="form-control" type="text" value="${os.garantia}" name="garantia" placeholder="Ex: 3 Meses"  />
            </div>
            <div class="col">
                <label for="logOS">Ultimas alterações</label>
                <textarea class="form-control" name="logOS" rows="5" readonly="readonly">
                    <c:forEach items="${os.logOS}" var="log" >
<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${log.dataHora}" /> : ${log.descricao}</c:forEach>
                </textarea>
            </div>

        </div>
        <div class="form-row">
            <div class="col-4">
                <label>Produtos Instalados</label>
                <textarea  class="form-control" name="ProdutosInstalados" rows="5" readonly="readonly">
<c:forEach items="${os.produtos}" var="serv">${prod.nome} : R$ <fmt:formatNumber value="${prod.valor}" minFractionDigits="2" />
</c:forEach>
                </textarea>
            </div>
            <div class="col-4">
                <label>Serviços Prestados</label>
                <textarea  class="form-control" name="servicosprestados" rows="5" readonly="readonly">
<c:forEach items="${os.servicos}" var="serv">${serv.nome} : R$ <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" />
</c:forEach>
                </textarea>
            </div>
            <div class="col-4"></div>
        </div>        
                
                
<br>     
                
<button type="submit" class="btn bg-gradient-success float-right shadow mb-4" style="color: white;" onclick="return confirm('Deseja realmente fazer essas alterações?')"> Salvar Alterações O.S.</button>

    </form>
    </div>
 </div>            
  
                
                
 
 

<!--Aqui começa o MODAL -->
                
                
             
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
        <form>
          <div class="form-group">
            <label for="ListaEquipamentos.Equipamento" class="col-form-label"> Equipamento : </label>
            <input id="ListaEquipamentos.Equipamento" name="ListaEquipamentos.Equipamento" placeholder="Ex : Marca, modelo, processador, memória..." class="form-control" />
          </div>
          <div class="form-group">
            <label for="ListaEquipamentos.Defeito" class="col-form-label">Defeito do equipamento : </label>
            <textarea class="form-control" name="ListaEquipamentos.Defeito" placeholder="Não inicializa" id="ListaEquipamentos.Defeito"></textarea>
          </div>
          <div class="form-group">
            <label for="ListaEquipamentos.Observacao" class="col-form-label"> Observação : </label>
            <textarea type="text" class="form-control" row="2" id="ListaEquipamentos.Observacao" placeholder="Arranhão na lateral" name="ListaEquipamentos.Observacao"></textarea>
          </div>
            <div class="form-group">
                <label for="ListaEquipamentos.Acessorios" class="col-form-label"> Acessórios </label>
                <input id="ListaEquipamentos.Acessosrios" name="ListaEquipamentos.Acessorios" placeholder="Fontes, mouses, cabos..." class="form-control" />
            </div>
        </form>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn bg-gradient-warning" data-dismiss="modal" style="color: white;">Cancelar</button>
        <button type="button" class="btn bg-gradient-success" style="color: white;">Salvar</button>
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
        <form>
          <div class="form-group">
            <label for="Servico.Nome" class="col-form-label"> Nome : </label>
            <input class="form-control" type="text" name="Servico.Nome" id="Servico.Nome" />
          </div>
          <div class="form-group">
            <label for="Servico.Valor" class="col-form-label"> Valor : </label>
            <input type="number" class="form-control" id="Servico.Valor" name="Servico.Valor" />
          </div>
            <div class="form-group">
                <label for="Servico.Descricao" class="col-form-label"> Descrição : </label>
                <textarea id="Servico.Descricao" row="2" name="Servico.Descricao" placeholder="Descreva o serviço..." class="form-control"></textarea>
            </div>
        </form>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn bg-gradient-warning" data-dismiss="modal" style="color: white;">Cancelar</button>
        <button type="button" class="btn bg-gradient-success" style="color: white;">Salvar</button>
      </div>
    </div>
  </div>
</div>           
             
             
</div>
</div>
</div>
<jsp:include page="Content/Layout/Footer.jsp" />