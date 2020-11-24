<%-- 
    Document   : minhasOrdens
    Created on : 06/08/2019, 20:14:31
    Author     : 03728827142
--%>

<%@page import="br.com.sigos.model.Produto"%>
<%@page import="br.com.sigos.model.ordemServico"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Content/LayoutC/Header.jsp" />
<jsp:include page="Content/LayoutC/Menu.jsp" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />
<jsp:useBean id="os" scope="session" class="br.com.sigos.model.ordemServico" />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="container">
    <c:if test="${msg != ''}">
        <div id="alerta" class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    <div class="card">
        <div class="card-header text-gray-100 shadow-lg bg-gradient-info"> <h1> Aqui estão as suas Ordens</h1></div>
        <div class="card-body">
            
            <c:forEach items="${ordens}" var="os">
                <div class="card">
                    <div class="card-header text-gray-100 <c:if test="${os.status && !os.cancelada}" > bg-gradient-warning </c:if><c:if test="${!os.status && !os.cancelada}" > bg-gradient-success </c:if><c:if test="${os.cancelada}" > bg-gradient-danger </c:if>">
                        <strong> Número OS : 
                                <badge class="badge badge-danger"> ${os.numOS}  Valor Total : R$ <fmt:formatNumber value="${os.valorTotal}" minFractionDigits="2" /> </badge>
                        
                        <c:if test="${os.status && !os.cancelada}" > <h5> <span class="badge badge-light float-right">Aberta</span> </h5> </c:if>
                        <c:if test="${!os.status && !os.cancelada}" > <h5> <span class="badge badge-success float-right">Fechada</span> </h5> </c:if>
                        <c:if test="${os.cancelada}" > <h5> <span class="badge badge-danger float-right">Cancelada</span> </h5> </c:if>
                        </strong>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-7"> 
                                <div class="row">
                                    <div class="col"> Equipamento : <strong> ${os.listaEquipamentos.equipamento} </strong> </div>
                                    <div class="col"> Defeito : <strong> ${os.listaEquipamentos.defeito} </strong> </div>
                                </div>
                                <div class="row">
                                    <div class="col">  <label> Data de abertura  : </label><strong><fmt:formatDate value="${os.dtAbertura}" pattern="dd/MM/yyyy HH:MM" /> </strong> </div>
                                    <div class="col">  <label>Data de fechamento  : </label><strong><fmt:formatNumber value="${os.dtFechamento}" pattern="dd/MM/yyyy HH:MM" /> </strong></div>
                                </div>
                                <div class="row">
                                    <div class="col"> <label> Prazo :</label> <strong> <fmt:formatDate value="${os.prazoEntrega}" pattern="dd/MM/yyyy" /> </strong> </div>
                                    
                                    
                                    <div class="col"> <label> Garantia :</label> <strong> ${os.garantia} </strong> </div>
                                    
                                </div>

                            </div>
                            <div class="col-md-5">         
                                <table class="table table-striped table-hover">
                                    <thead class="thead-dark">Últimas Atualização</thead>
                                    <tbody>
                                        <tr>
                                            <th>Data</th>
                                            <th>Descrição</th>
                                        </tr>
                                        <tr>
                                            <td> <fmt:formatDate value="${os.log.dataHora}" pattern="dd/MM/yyyy HH:MM" /></td>
                                            <td> ${os.log.descricao}  </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <a class="btn btn-success float-right" href="AreaClienteServlet?acao=minhaOrdem&id=${os.numOS}">Ver Detalhes</a>
                            </div>
                        </div>
                    </div>

                </div>
                
            </c:forEach>
        </div>
    </div>
</div>
    

<jsp:include page="Content/LayoutC/Footer.jsp" />
     
       
