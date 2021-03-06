<%-- 
    Document   : Servicos
    Created on : 07/05/2019, 21:47:41
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="servico" scope="session" class="br.com.sigos.model.Servico" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    
    <c:if test="${msg != ''}">
        <div id="alerta" class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    
    <h1 class="h3" align="center"> Todos os Servi�os</h1>
        

            <table class="table table-hover table-striped">
                <thead class="bg-gradient-info text-gray-100">
                    <th>Nome</th>
                    <th>Descri��o</th>
                    <th>Valor</th>
                    <th class="align-items-md-baseline">
                        A��es
                        <span class="float-right"> 
                            <a class="btn btn-primary bg-gradient-primary" href="ServicoServlet?acao=novoServico" title="Adicionar novo servi�o" > 
                                <i class="fas fa-plus-circle"></i> 
                            </a>
                        </span>
                    </th>
                </thead>
                <tbody>
                    <c:forEach var="servico" items="${servicos}">
                        <tr>
                            <td>${servico.nome}</td>
                            <td>${servico.descricao}</td>
                            <td>R$ <fmt:formatNumber value="${servico.valor}" minFractionDigits="2" /> </td>
                            <td align="right">
                                <a class="btn btn-primary bg-gradient-warning" title="Editar este Servi�o" href="ServicoServlet?acao=editar&id=${servico.ID}">
                                    <i class="fas fa-pencil-alt"></i>
                                </a>
                                <a class="btn btn-primary bg-gradient-danger" href="ServicoServlet?acao=deletar&id=${servico.ID}" title="Deletar este produto" onclick="return confirm('Deseja realmente deletar este Servi�o?')">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                
            </table>
            
        
    </div>
    

<jsp:include page="Content/Layout/Footer.jsp" />