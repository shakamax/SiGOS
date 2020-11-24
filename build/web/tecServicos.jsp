<%-- 
    Document   : tecServicos
    Created on : 08/07/2019, 09:45:08
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="os" scope="session" class="br.com.sigos.model.ordemServico" />
<jsp:useBean id="serv" scope="session" class="br.com.sigos.model.Servico" />

<div class="container">

    <c:if test="${msg != ''}">
        <div id="alerta" class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    
<div class="card">
        <div class="card-header bg-gradient-info text-gray-100">
            <h3>Serviços </h3>
        </div>
        <div class="card-body" >
        <c:forEach items="${ordens}" var="os">
                <c:if test="${os.servicos.size() > 0}">
            <div class="card">
                <div class="card-header bg-gradient-info text-gray-100">
                    <h4> ${os.numOS} de ${os.cliente.nome} : <span class="badge badge-light float-right">${os.listaEquipamentos.equipamento}</span> </h4>
                </div>    

                <div class="card-body" style="background-color: #f8f9fc; box-shadow: -4px 6px 7px 4px #0000000f;">
                        <div class="row">
                            <div class="col-sm-6">
                              <div class="card">
                                  <div class="card-header bg-gradient-success text-gray-100">
                                      <h5 class="card-title"> Serviços a serem prestados </h5>
                                  </div>
                                <div class="card-body">
                                <c:forEach items="${os.servicos}" var="serv" >


                                        <div class="row">
                                                <c:if test="${!serv.status}">
                                                  <div class="col">
                                                      ${serv.nome}
                                                  </div>
                                                  <div class="col-4">
                                                      <a href="TecnicoServlet?acao=realizarServico&id=${serv.ID}&idos=${os.numOS}" title="Realizar este serviço" onclick="return confirm('Deseja mudar o Status deste serviço para Realizado?')" class="btn btn-primary bg-gradient-success"> <i class="fas fa-check-circle"></i></a>
                                                      <a href="TecnicoServlet?acao=excluirServico&id=${serv.ID}&idos=${os.numOS}" title="Excluir este serviço" onclick="return confirm('Deseja realmente retirar este serviço?')" class="btn btn-primary bg-gradient-danger"> <i class="fas fa-ban"></i></i></a>
                                                  </div>
                                              </c:if>
                                        </div>
                                  <br>
                                </c:forEach>
                                </div>
                              </div>
                            </div>

                            <div class="col-sm-6">
                              <div class="card">
                                  <div class="card-header bg-gradient-success text-gray-100" >
                                    <h5 class="card-title"> Serviços já feitos </h5>
                                  </div>
                                <div class="card-body">
                                  <c:forEach items="${os.servicos}" var="serv">
                                    <c:if test="${serv.status}">
                                        <div class="row">
                                            <div class="col">
                                                ${serv.nome}
                                            </div>
                                            <div class="col-4">
                                                <h4> <span class="badge badge-primary bg-gradient-success"> Realizado <i class="fas fa-check-circle"></i></span></h4>
                                            </div>
                                        </div>
                                    </c:if>
                                  </c:forEach>
                                </div>
                              </div>
                            </div>
                         </div>
                        </div>
            </div>
                <br>
            </c:if>
        </c:forEach>
            </div>
    </div>
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
     
