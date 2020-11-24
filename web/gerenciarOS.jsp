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

<c:if test="${!os.status || os.cancelada}">
    <c:redirect url="ordemServicoServlet?acao=listar" />
</c:if>
<c:set var="parcial" value="${os.valorTotal}" />

<div class="container-fluid">
 
    <a href="ordemServicoServlet?acao=listar" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
    <br>
    <br>
    
<div class="card shadow mb-4">
 <div class="card-header justify-content-between align-items-lg-center flex-row bg-gradient-info">
     <button class="btn btn-danger bg-gradient-primary float-right" data-toggle="modal" data-target="#modalFinalizar" type="button" > Finalizar O.S <i class="fas fa-check-double"></i></button>
    <h1 class="h3 mb-4 text-gray-100"> Gerenciando sua O.S : </h1>
    <h1 class="h3 mb-4 text-gray-100"> O.S. ${os.numOS} </h1>
 </div>
    <div class="card-body">
    <form class="form-group" method="post" action="ordemServicoServlet?acao=salvar">
        
<!--ROW FORM-->

<!--ROW FORM-->
        
        <div class="form-row">
            <div class="col-6">
            <label for="Cliente">Cliente : </label>
            <input type="hidden" name="idos" value="${os.numOS}" />
            <input type="hidden" name="clienteid" id="clienteid" value="${os.cliente.id}" />
            <input id="Cliente" name="clienteNome" value="${os.cliente.nome}" disabled class="form-control" value="Cliente Tal"/>
            </div>
            <div class="col-6">
            <label for="listaequipamentos"> Equipamento do Cliente : </label>
            <select name="ListaEquipamentos" disabled class="form-control">
                <option value="${os.listaEquipamentos.id}" selected> ${os.listaEquipamentos.equipamento} </option>
            </select>
            </div>
        </div>


    <!--ROW FORM-->
        <div class="form-row">
            <div class="col-6">
               <label for="servicos"> Produtos </label>
               <select class="js-example-basic-multiple form-control" name="prods[]" multiple="multiple">
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
                <select class="js-example-basic-multiple form-control" name="serv[]" multiple="multiple">
                    <c:forEach items="${servs}" var="serv">
                        <option value="${serv.ID}" > ${serv.nome} </option>
                    </c:forEach>
                        
                    <c:forEach items="${os.servicos}" var="serv">
                        
                        <option value="${serv.ID}" selected="selected" > ${serv.nome} </option>
                        
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
                <input class="form-control" type="Date" value="<fmt:formatDate value="${os.prazoEntrega}"  pattern="yyyy-MM-dd"/>" name="dtPrazo" id="dtPrazo" />
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
            <div class="col-md-8">
                
            <div class="row">
                <div class="col">
                    <label for="os.Defeito"> Defeito : </label>
                    <textarea name="defeito" class="form-control" id="defeito" rows="4" maxlength="170">
${os.listaEquipamentos.defeito}</textarea>
                </div>
                <div class="col">
                    <label for="garantia"> Garantia : </label>
                    <input id="garantia" class="form-control" type="text" value="${os.garantia}" name="garantia" placeholder="Ex: 3 Meses" maxlength="100" />
                </div>
            </div>
            <div class="row">
            <div class="col">
                <label>Produtos da OS</label>
                <textarea  class="form-control" name="ProdutosInstalados" rows="8" readonly="readonly">
<c:forEach items="${os.produtos}" var="prod">${prod.nome} : R$ <fmt:formatNumber value="${prod.valor}" minFractionDigits="2" /><c:if test="${!prod.autorizado}"><c:set var="parcial" value="${parcial + prod.valor}" /></c:if>
</c:forEach>
                </textarea>
            </div>
            <div class="col">
                <label>Serviços Prestados</label>
                <textarea  class="form-control" name="servicosprestados" rows="8" readonly="readonly">
<c:forEach items="${os.servicos}" var="serv"><c:if test="${serv.status}">${serv.nome} : R$ <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" /></c:if>
</c:forEach>
<c:forEach items="${os.servicos}" var="serv"><c:if test="${!serv.status}">${serv.nome}(Não realizado) : R$ <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" /><c:set var="parcial" value="${parcial + serv.valor}" /></c:if>
</c:forEach>

                </textarea>
            </div>
                
                
            </div>
                <br>
            <div class="row">
                <div class="col">
                    <label> <h4><strong> Valor Parcial : </strong> </h4></label>
                </div>
                <div class="col">
                    <label><h4><strong style="color: #826c04;"> R$ <fmt:formatNumber value="${parcial}" minFractionDigits="2" /> </strong> </h4></label>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label> <h2><strong> Valor Total : </strong> </h2></label>
                </div>
                <div class="col">
                    <label><h2><strong style="color: #268795;"> R$ <fmt:formatNumber value="${os.valorTotal}" minFractionDigits="2" /> </strong> </h2></label>
                </div>
            </div>
                
            </div>
            
            <div class="col">
                <label for="logOS">Ultimas alterações</label>
                <textarea class="form-control" name="logOS" rows="15" readonly="readonly">
                    <c:forEach items="${os.logOS}" var="log" >
<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${log.dataHora}" /> : ${log.descricao}
                    </c:forEach>
                </textarea>
            </div>

        </div>
        <div class="form-row">
        </div>        
                
                
<br>     
                
<button type="submit" id="disbtn" class="btn bg-gradient-success float-right shadow mb-4" style="color: white;" onclick="return confirm('Deseja realmente fazer essas alterações?')"> Salvar Alterações O.S.</button>
<button type="button" data-toggle="modal" data-target="#modalCancelar" class="btn btn-danger bg-gradient-danger float-left"> Cancelar <i class="fas fa-window-close"></i> </button>



    </form>
    </div>
 </div>            
  
 
<!--          AQUI COMEÇA O MODAL      -->
                
<div class="modal fade" id="modalCancelar" tabindex="-1" role="dialog" aria-labelledby="Cancelar" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" > Cancelar O.S </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          Deseja realmente <span class="badge badge-danger bg-gradient-danger"> CANCELAR </span> a Ordem de Serviço ${os.numOS} ?<br>
          <h5><strong>Não será mais possível modificá-la após esta ação.</strong></h5>
      </div>
      <div class="modal-footer">
          <a href="ordemServicoServlet?acao=cancelar&id=${os.numOS}" class="btn btn-danger bg-gradient-danger float-left">Confirmar <i class="fas fa-window-close"></i></a>
        <button type="button" class="btn btn-primary bg-gradient-primary w-50" data-dismiss="modal">Não</button>
      </div>
    </div>
  </div>
</div>
      
      
<div class="modal fade" id="modalFinalizar" tabindex="-1" role="dialog" aria-labelledby="Cancelar" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" > Finalizando O.S </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          Deseja realmente <span class="badge badge-success bg-gradient-success"> FINALIZAR </span> a Ordem de Serviço ${os.numOS} ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary bg-gradient-warning" data-dismiss="modal">Ainda não.</button>
        <a href="ordemServicoServlet?acao=finalizar&id=${os.numOS}" class="btn btn-success bg-gradient-success"> Confirmar <i class="fas fa-check-double"></i></a>
      </div>
    </div>
  </div>
</div>
                
                
                
             
</div>
</div>
</div>
<jsp:include page="Content/Layout/Footer.jsp" />