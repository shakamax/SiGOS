<%-- 
    Document   : minhaOrdem
    Created on : 07/08/2019, 12:12:18
    Author     : Guilherme
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="Content/LayoutC/Header.jsp" />
<jsp:include page="Content/LayoutC/Menu.jsp" />
<jsp:useBean id="os" scope="session" class="br.com.sigos.model.ordemServico" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />
<jsp:useBean id="serv" scope="session" class="br.com.sigos.model.Servico" />
<jsp:useBean id="prod" scope="session" class="br.com.sigos.model.Produto" />


<div class="container-fluid">
    <a href="ClienteServlet?acao=listar" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
    <c:if test="${os.cliente.id != cliente.id}" >
        <c:redirect url="AreaClienteServlet?acao=ordens" />
    </c:if>   
    <c:if test="${msg != ''}">
        <div id="alerta" class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
<div class="card shadow mb-4">
 <div class="card-header justify-content-between align-items-lg-center flex-row bg-gradient-info">
     <c:if test="${!os.status && !os.cancelada}" >
        <h2>
        <span class="badge badge-danger bg-gradient-success float-right" > O.S Finalizada <i class="fas fa-check-double"></i> </span>
        </h2>
     </c:if>
     <c:if test="${os.status && !os.cancelada}" >
        <h2>
        <span class="badge badge-danger bg-gradient-success float-right" > O.S Aberta <i class="far fa-check-square"></i> </span>
        </h2>
     </c:if>
    <h1 class="h3 mb-4 text-gray-100"> Como esta a sua O.S : </h1>
    <h1 class="h3 mb-4 text-gray-100"> Número ${os.numOS} </h1>
 </div>
    <div class="card-body">
        <div class="row" >
            <div class="col-md-8">
                <div class="row">
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);">Cliente : </h3> <b> ${os.cliente.nome} </b>
                    </div>
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);">Equipamento : </h3> <b> ${os.listaEquipamentos.equipamento}</b>
                    </div>
                </div>
                    <br>
                <div class="row">
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);">Data de Abertura</h3> <fmt:formatDate pattern="dd/MM/yyyy hh:MM" value="${os.dtAbertura}" />
                    </div>
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);">Prazo de entrega : </h3>  <fmt:formatDate pattern="dd/MM/yyyy" value="${os.prazoEntrega}"/>
                    </div>
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);">Data de fechamento</h3> <fmt:formatDate pattern="dd/MM/yyyy hh:MM" value="${os.dtFechamento}" />
                    </div>
                </div>
                    <br>
                <div class="row">
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);">Defeito : </h3>  ${os.listaEquipamentos.defeito}
                    </div>
                    <div class="col">
                        <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);"> Garantia : </h3> <c:if test="${os.garantia != null}" > ${os.garantia} </c:if><c:if test="${os.garantia == null}" > Sem garantia </c:if> 
                    </div>
                </div>
                    <br>
                <div class="row">
                    <div class="col">
                    <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);"> Serviços : &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <button type="button" data-toggle="modal" data-target="#modalServico" class="btn btn-info bg-gradient-warning" title="Verificar Serviços" >Autorizar</button> </h3> <c:forEach items="${os.servicos}" var="serv">
                        ${serv.nome} R$: <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" /><br>  
                        </c:forEach> 
                    </div>
                    <div class="col">
                            <h3 class="text-gray-900" style="background-image: linear-gradient(180deg, #fdffff 10%, #dedede 100%);"> Produtos  : &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <button type="button" data-toggle="modal" data-target="#modalProduto" class="btn btn-info bg-gradient-warning" title="Verificar Serviços" >Autorizar</button></h3> <c:if test="${os.produtos.size() == 0}"> Nenhum Produto Foi Comprado</c:if> <c:forEach items="${os.produtos}" var="prod">
                        ${prod.nome} R$: <fmt:formatNumber value="${prod.valor}" minFractionDigits="2" /><br>
                        </c:forEach>
                    </div>
                </div>
                <br>
                <div class="row  bg-gradient-info text-gray-100">
                    <div class="col">
                        <h2> Valor total : </h2> 
                    </div>
                    <div class="col text-center" >
                        <h2><strong><span class="badge badge-success"> R$ <fmt:formatNumber value="${os.valorTotal}" minFractionDigits="2" /> </span> </strong> </h2>
                    </div>
                </div>
                    
                    
            </div>
        <div class="col">
            <div class="row"  style="overflow: auto; height: 500px;">

                <table class="table table-hover table-striped table-responsive-sm">
                    <thead class="thead-dark thead bg-gradient-info">
                        <tr>
                            <td colspan="2" align="center">
                                <h5 class="text-gray-100"> Log de alterações </h5>
                            </td>
                        </tr>
                    </thead>
                    <tbody >
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
        </div>
        <div class="row">
            <div class="col">
  
            </div>
        </div>
                
                
    <br>     
                
<!--<button type="button" data-toggle="modal" data-target="#modalCancelar" class="btn btn-danger bg-gradient-danger float-left"> Cancelar <i class="fas fa-window-close"></i> </button>-->
    </div>
 </div>  
 </div>  

<!--                    MODAL SERVIÇOS-->
                    
<div class="modal fade bd-example-modal-lg" id="modalServico" tabindex="-1" role="dialog" aria-labelledby="modalServico" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"><h3 class="text-gray-900" align="center">Autorização de Serviço </h3></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="row">
              <div class="col-md-6">
                  <div class="row"> <div class="col bg-gradient-warning text-gray-900"> <h3> Serviços Não Autorizados</h3> </div> </div>
                   <br>
                    <c:forEach items="${os.servicos}" var="serv" >
                        <c:if test="${!serv.autorizado}" >
                            <div class="row">
                                <div class="col">
                                    ${serv.nome} R$ : <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" />
                                </div>
                                <div class="col">
                                    <a class="btn bg-gradient-success float-right" title="Autorizar" href="AreaClienteServlet?acao=autorizarServ&serv=${serv.ID}&os=${os.numOS}" style="color: white;" onclick="return confirm('Deseja realmente autorizar este Serviço?')"> <i class="fas fa-check-circle"></i> </a>
                                </div>
                            </div>
                                <br>
                        </c:if>
                    </c:forEach>
              </div>
              <div class="col-md-6">
                  <div class="row"> <div class="col bg-gradient-success text-gray-900"> <h3> Serviços Já Autorizados</h3> </div> </div>
                   <br>
                    <c:forEach items="${os.servicos}" var="serv" >
                        <c:if test="${serv.autorizado}" >
                            <div class="row">
                                <div class="col">
                                    ${serv.nome} R$ : <fmt:formatNumber value="${serv.valor}" minFractionDigits="2" />
                                </div>
                                <div class="col">
                                    <c:if test="${!serv.status}" >
                                     <a class="btn bg-gradient-danger float-right" title="Desautorizar" href="AreaClienteServlet?acao=mudeiDeIdeia&serv=${serv.ID}&os=${os.numOS}" style="color: white;" onclick="return confirm('Deseja realmente retirar a autorização deste Serviço?')"> <i class="far fa-times-circle"></i> </a>
                                    </c:if>
                                    <c:if test="${serv.status}" >
                                    <h4> <span class="badge badge-primary bg-gradient-success"> Realizado <i class="fas fa-check-circle"></i></span></h4>
                                    </c:if>
                                </div>
                            </div>
                                <br>
                        </c:if>
                    </c:forEach>
              </div>
          </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary bg-gradient-success" data-dismiss="modal">Ok</button>
            </div>
      </div>
    </div>
  </div>
</div>     
    


<!--        MODAL PRODUTO-->
<div class="modal fade bd-example-modal-lg" id="modalProduto" tabindex="-1" role="dialog" aria-labelledby="modalServico" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"><h3 class="text-gray-900" align="center">Autorização de Serviço </h3></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="row">
              <div class="col-md-6">
                  <div class="row"> <div class="col bg-gradient-warning text-gray-900"> <h3> Produtos Não Autorizados</h3> </div> </div>
                   <br>
                   <c:forEach items="${os.produtos}" var="prod" >
                        <c:if test="${!prod.autorizado}" >
                            <div class="row">
                                <div class="col">
                                    ${prod.nome} R$ : <fmt:formatNumber value="${prod.valor}" minFractionDigits="2" />
                                </div>
                                <div class="col">
                                    <a class="btn bg-gradient-success float-right" title="Autorizar" href="AreaClienteServlet?acao=autorizarProd&prod=${prod.ID}&os=${os.numOS}" style="color: white;" onclick="return confirm('Deseja realmente autorizar este Produto?')"> <i class="fas fa-check-circle"></i> </a>
                                </div>
                            </div>
                                <br>
                        </c:if>
                    </c:forEach>
              </div>
              <div class="col-md-6">
                  <div class="row"> <div class="col bg-gradient-success text-gray-900"> <h3> Produtos Já Autorizados</h3> </div> </div>
                   <br>
                   <c:forEach items="${os.produtos}" var="prod" >
                        <c:if test="${prod.autorizado}" >
                            <div class="row">
                                <div class="col">
                                    ${prod.nome} R$ : <fmt:formatNumber value="${prod.valor}" minFractionDigits="2" />
                                </div>
                                <div class="col">
                                    <c:if test="${!prod.status}" >
                                     <a class="btn bg-gradient-danger float-right" title="Desautorizar" href="AreaClienteServlet?acao=mudeiDeIdeiap&prod=${prod.ID}&os=${os.numOS}" style="color: white;" onclick="return confirm('Deseja realmente retirar a autorização este Produto?')"> <i class="far fa-times-circle"></i> </a>
                                    </c:if>
                                </div>
                            </div>
                                <br>
                        </c:if>
                    </c:forEach>
              </div>
          </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary bg-gradient-success" data-dismiss="modal">Ok</button>
            </div>
      </div>
    </div>
  </div>
</div>     
    

<jsp:include page="Content/LayoutC/Footer.jsp" />
     
       
