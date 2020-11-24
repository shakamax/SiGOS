<%-- 
    Document   : listaOS
    Created on : 29/04/2019, 14:24:18
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="os" scope="session" class="br.com.sigos.model.ordemServico" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
  
    <c:if test="${msg != ''}">
        <div id="alerta" class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    
    <div class="card shadow mb-4">
        <div class="card-header align-content-center align-items-center bg-gradient-info">
            
            <span class="float-right"> <a href="ordemServicoServlet?acao=clientes" title="Nova Ordem de Serviço" class="btn btn-primary bg-gradient-primary">Nova <i class="fas fa-plus-circle"></i> </a></span>
            <h1 class="h3 mb-4 text-gray-100 float-left">Ordens de Serviço</h1>&nbsp; <button class="btn btn-primary bg-gradient-info" data-toggle="modal" data-target="#busca" type="button">Buscar <i class="fas fa-search fa-sm"></i></button>
        </div>
        <div class="card-body">
            
        <div class="row">
        <c:forEach items="${listaOS}" var="os">
            <div class="col-3">
                <div class="card">
                    <div class="card-header text-gray-100 <c:if test="${os.status}" > bg-gradient-warning </c:if> <c:if test="${os.cancelada}" > bg-gradient-danger </c:if> <c:if test="${!os.status}">bg-gradient-success</c:if>"> <strong>OS </strong> : ${os.numOS} </div>
                    <div class="card-body">
                        <strong> Cliente : </strong> ${os.cliente.nome}
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item" style="height: 80px;"> <strong>Equipamento :</strong>  ${os.listaEquipamentos.equipamento} </li>
                          <li class="list-group-item"> <strong> Status :</strong> <c:if test="${os.status}"> <span class="badge badge-warning"> Aberta </span></c:if> <c:if test="${!os.status}"> <span class="badge-success" > Fechado </span></c:if>  </li>
                          <li class="list-group-item" style="height: 130px;"><strong>Última atualização <fmt:formatDate value="${os.log.dataHora}" type="both"  pattern="dd/MM/yyyy HH:mm" /> : </strong> ${os.log.descricao}</li>
                      </ul> 
                    <div class="card-body">
                        <c:if test="${os.status && !os.cancelada}" >
                            <a title="Gerenciar O.S." class="btn bg-gradient-info float-right" style="color: white;" href="ordemServicoServlet?acao=gerenciar&id=${os.numOS}" >
                                Gerenciar O.S <i class="fas fa-list-alt"></i>
                            </a>
                        </c:if>
                        <c:if test="${!os.status || os.cancelada}" >
                            <a title="Gerenciar O.S." class="btn bg-gradient-info float-right" style="color: white;" href="ordemServicoServlet?acao=visualizar&id=${os.numOS}" >
                                Visualizar <i class="fas fa-list-alt"></i>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
            <br>
          </c:forEach>    
          
        <%-- DIV QUE FECHA A ROW --%>
        </div>
        
          
            <%--DIV QUE FECHA O BODY CARD PRINCIPAL --%>
        </div>
        
        <%-- Div que fecha a card principal--%>
    </div>    
<%-- Div que fecha o container--%>
</div>

<!-- Modal troca de email-->
  <div class="modal fade" id="busca" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">O que você procura?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
            <form method="post" class="form-group" name="busca"  action="ordemServicoServlet?acao=buscarOS" >
                <div class="form-row">
                    <div class="col">
                    <select name="coluna" id="coluna" class="form-control">
                            <option value="numOS" > Número da O.S </option>
                            <option value="cliente" > Cliente </option>
                            <option value="equipamento" > Equipamento </option>
                    </select>
                    </div>
                    <div class="col">
                        <input type="text" name="busca" class="form-control" />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-check">
                        <div class="col">
                            <input type="radio" id="check1" value="aberta" name="status" onclick="checarClick()" class="form-check-input" /><label class="form-check-label"> Aberta</label>
                        </div>
                        <div class="col">
                            <input type="radio" id="check2" value="Fechada" name="status" onclick="checarClick()" class="form-check-input" /><label class="form-check-label"> Fechada</label>
                        </div>
                        <div class="col">
                            <input type="radio" id="check3" value="Cancelada" name="status" onclick="checarClick()" class="form-check-input" /><label class="form-check-label"> Cancelada</label>
                        </div>
                    </div>
                </div>
                <div class="form-row" style="display: none;" id="dtab">
                    <div class="col">
                        <label> Data de abertura </label>
                        <input type="date" name="dataAber" id="dataAbertura" class="form-control" />
                    </div>
                </div>
                <div class="form-row" id="dtfc" style="display: none;">
                    <div class="col">
                        <label> Data de Fechamento </label>
                        <input type="date" name="dataFecha" class="form-control" />
                    </div>
                </div>
                
        </div>
        <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <button class="btn btn-primary" type="submit" > <i class="fas fa-search"></i> </button>
            </form>
        </div>
      </div>
    </div>
  </div>

<jsp:include page="Content/Layout/Footer.jsp" />
