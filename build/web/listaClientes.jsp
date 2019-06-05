<%-- 
    Document   : Clientes
    Created on : 29/04/2019, 21:34:31
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
    String status;
    if ( request.getParameter("status") != "") {
 status = "vazio";
} else {
    status = "false";
}
%>
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />

<div class="container-fluid">
    
    
    <div class="card shadow mb-4">
        <table class="table table-hover table-striped">
            <thead class="card-header bg-gradient-info text-gray-100">
                
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>CPF</th>
            <th>Cidade</th>
            <th>Status</th>
            <th>Ações</th>
            </thead>
            <tbody>
                <tr>
                    <% int contador = 1; %>
                    <c:forEach items="${clientes}" var="cliente">
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.CPF}</td>
                    <td>${cliente.cidade}</td>
                    <td> 
                        <c:if test="${cliente.status}" > Autenticado </c:if> <c:if test="${!cliente.status}" > Não Atenticado </c:if>
                    </td>
                    <td>
                        <a href="EquipamentosServlet?acao=listar&id=${cliente.id}"class="btn btn-primary bg-gradient-primary">
                            <i class="fas fa-laptop-medical"></i>
                        </a>
                        
                        <a href="ClienteServlet?acao=editar&id=${cliente.id}" class="btn btn-primary bg-gradient-warning" data-toggle="tooltip" data-placement="top" title="Editar">
                            <i class="fas fa-user-edit"></i>
                        </a>

                        <a href="ClienteServlet?acao=deletar&id=${cliente.id}" onclick="return confirm ('Deseja realmente excluir este cliente?')"  class="btn btn-primary bg-gradient-danger" data-toggle="tooltip" data-placement="top" title="Deletar">
                            <i class="fas fa-trash"></i>
                        </a>

                    </td>
                </tr>
                
                <% contador++; %>
            </c:forEach>
            </tbody>
        </table>   
        
    <%-- Fechando a card --%>
    </div>
    
    <%-- Fechando Container --%>
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
     
