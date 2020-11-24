<%-- 
    Document   : Clientes
    Created on : 29/04/2019, 21:34:31
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />

<div class="container-fluid">
    <c:if test="${msg != ''}">
        <div id="alerta" class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    
    <div class="card shadow mb-4">
        <table class="table table-hover table-striped">
            <thead class="card-header bg-gradient-info text-gray-100">
                
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>CPF</th>
            <th>Cidade</th>
            <th>Status</th>
            <th>Ações <span class="float-right"><a class="btn btn-primary bg-gradient-primary" href="ClienteServlet?acao=nova" title="Adicionar novo Cliente" > <i class="fas fa-plus-circle"></i> </a></span></th>
            </thead>
            <tbody>
                <tr>
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
                        <a href="EquipamentosServlet?acao=listar&id=${cliente.id}" title="Equipamentos deste cliente" class="btn btn-primary bg-gradient-primary">
                            <i class="fas fa-laptop-medical"></i>
                        </a>
                    <c:if test="${user.funcao != 'Técnico'}">
                            <a href="ClienteServlet?acao=editar&id=${cliente.id}" title="Editar este cliente" class="btn btn-primary bg-gradient-warning"  data-toggle="tooltip" data-placement="top" title="Editar">
                                <i class="fas fa-user-edit"></i>
                            </a>
                        <c:if test="${user.funcao != 'Atendente'}">
                            <a href="ClienteServlet?acao=deletar&id=${cliente.id}" title="Deletar este cliente" onclick="return confirm ('Deseja realmente excluir este cliente?')"  class="btn btn-primary bg-gradient-danger" data-toggle="tooltip" data-placement="top" title="Deletar">
                                <i class="fas fa-trash"></i>
                            </a>
                        </c:if>
                    </c:if>
                    </td>
                </tr>
                
            </c:forEach>
            </tbody>
        </table>   
        
    <%-- Fechando a card --%>
    </div>
    
    <%-- Fechando Container --%>
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
     
