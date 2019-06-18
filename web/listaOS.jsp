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
  
    <div class="card shadow mb-4">
        <div class="card-header align-content-center align-items-center bg-gradient-info">
          <h1 class="h3 mb-4 text-gray-100">Ordens de Serviço</h1>
        </div>
        <div class="card-body">
            
        <div class="row">
        <c:forEach items="${listaOS}" var="os">
            <div class="col-3">
                <div class="card">
                    <div class="card-header text-gray-100 <c:if test="${os.status}" > bg-gradient-warning </c:if> <c:if test="${!os.status}">bg-gradient-success</c:if>"> <strong>OS </strong> : ${os.numOS} </div>
                    <div class="card-body">
                        <strong> Cliente : </strong> ${os.cliente.nome}
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item" style="height: 80px;"> <strong>Equipamento :</strong>  ${os.listaEquipamentos.equipamento} </li>
                          <li class="list-group-item"> <strong> Status :</strong> <c:if test="${os.status}"> <span class="badge badge-warning"> Aberta </span></c:if> <c:if test="${!os.status}"> <span class="badge-success" > Fechado </span></c:if>  </li>
                          <li class="list-group-item" style="height: 100px;"><strong>Última atualização <fmt:formatDate value="${os.log.dataHora}" type="both"  pattern="dd/MM/yyyy HH:mm" /> : </strong> ${os.log.descricao}</li>
                      </ul> 
                    <div class="card-body">
                        <a title="Gerenciar O.S." class="btn bg-gradient-info float-right" style="color: white;" href="ordemServicoServlet?acao=gerenciar&id=${os.numOS}" >
                            Gerenciar O.S <i class="fas fa-list-alt"></i>
                        </a>
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
</div>
    
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
