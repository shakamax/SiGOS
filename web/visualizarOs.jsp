<%-- 
    Document   : Visualizar
    Created on : 10/07/2019, 19:20:15
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
     <h2>
     <span class="badge badge-danger bg-gradient-success float-right" > O.S Finalizada <i class="fas fa-check-double"></i> </span>
     </h2>
    <h1 class="h3 mb-4 text-gray-100"> Gerenciando sua O.S : </h1>
    <h1 class="h3 mb-4 text-gray-100"> O.S. ${os.numOS} </h1>
 </div>
    <div class="card-body">
        <div class="row">
            <div class="col-md-8">
                <div class="row">
                    <div class="col">
                        <h3>Cliente : </h3>  ${os.cliente.nome}
                    </div>
                    <div class="col">
                        <h3>Equipamento : </h3>  ${os.listaEquipamentos.equipamento}
                    </div>
                </div>
                    <br>
                <div class="row">
                    <div class="col">
                        <h3>Data de Abertura</h3> <fmt:formatDate pattern="dd/MM/yyyy hh:MM" value="${os.dtAbertura}" />
                    </div>
                    <div class="col">
                        <h3>Prazo de entrega : </h3>  <fmt:formatDate pattern="dd/MM/yyyy" value="${os.prazoEntrega}"/>
                    </div>
                    <div class="col">
                        <h3>Data de fechamento</h3> <fmt:formatDate pattern="dd/MM/yyyy hh:MM" value="${os.dtFechamento}" />
                    </div>
                </div>
                    <br>
                <div class="row">
                    <div class="col">
                        <h3>Defeito : </h3>  ${os.listaEquipamentos.defeito}
                    </div>
                    <div class="col">
                        <h3> Garantia : </h3> <c:if test="${os.garantia != null}" > ${os.garantia} </c:if><c:if test="${os.garantia == null}" > Sem garantia </c:if> 
                    </div>
                </div>
                    <br>
                <div class="row">
                    <div class="col">
                        <h3> Serviços : </h3> <c:forEach items="${os.servicos}" var="serv">
                            ${serv.nome} <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" />  
                        </c:forEach> 
                    </div>
                    <div class="col">
                        <h3> Produtos  : </h3> <c:if test="${os.produtos.size() == 0}"> Nenhum Produto Foi Comprado</c:if> <c:forEach items="${os.produtos}" var="prod">
                            ${prod.nome} <fmt:formatNumber value="${prod.valor}" minFractionDigits="2" />  
                        </c:forEach>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col">
                        <h2> Valor total : </h2> 
                    </div>
                    <div class="col" >
                        <h3><strong> R$ <fmt:formatNumber value="${os.valorTotal}" minFractionDigits="2" /> </strong> </h3>
                    </div>
                </div>
                    
                    
            </div>
        <div class="col">
                <table class="table table-hover table-striped">
                <thead class="thead-dark thead bg-gradient-info">
                    <tr>
                        <td colspan="2" align="center">
                            <h5 class="text-gray-100"> Log de alterações </h5>
                        </td>
                    </tr>
                </thead>
                <tbody>
            <c:forEach items="${os.logOS}" var="log">
                <tr>
                    <td> <fmt:formatDate value="${log.dataHora}" pattern="dd/MM/yyyy hh:MM" /> </td>
                    <td> ${log.descricao} </td>
                    
                </tr>
            </c:forEach>
                </tbody>
                </table>
        </div>
        </div>

                
                
    <br>     
    <c:if test="${user.funcao == 'Administrador'}">
        <button type="button" data-toggle="modal" data-target="#modalReabrir" class="btn btn-danger bg-gradient-danger float-left"> Reabrir OS. <i class="fas fa-lock-open"></i> </button>
    </c:if>    
    
    </div>
 </div>            
  
 
<!--          AQUI COMEÇA O MODAL      -->
                
<div class="modal fade" id="modalReabrir" tabindex="-1" role="dialog" aria-labelledby="Reabrir" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" > Reabrir Ordem de Serviço </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          Deseja realmente <span class="badge badge-danger bg-gradient-danger"> REABRIR </span> a Ordem de Serviço ${os.numOS} ?
      </div>
      <div class="modal-footer">
          <a href="ordemServicoServlet?acao=reabrir&id=${os.numOS}" class="btn btn-danger bg-gradient-danger float-left"> REABRIR <i class="fas fa-lock-open"></i></a>
        <button type="button" class="btn btn-primary bg-gradient-primary w-50" data-dismiss="modal">Cancelar</button>
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
<jsp:include page="Content/Layout/Footer.jsp" />