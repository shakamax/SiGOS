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
    
    
    <h1 class="h3" align="center"> Todos os Serviços</h1>
        

            <table class="table table-hover table-striped">
                <thead class="bg-gradient-info text-gray-100">
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th class="align-items-md-baseline">
                        Ações
                        <span class="float-right"> 
                            <a class="btn btn-primary bg-gradient-success" href="ServicoServlet?acao=novoServico" title="Adicionar novo produto" > 
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
                                <a class="btn btn-primary bg-gradient-warning" href="ServicoServlet?acao=editar&id=${servico.ID}">
                                    <i class="fas fa-pencil-alt"></i>
                                </a>
                                <a class="btn btn-primary bg-gradient-danger" href="ServicoServlet?acao=deletar&id=${servico.ID}" onclick="return confirm('Deseja realmente deletar este produto?')">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                
            </table>
            
        
    </div>
</div>
    
</div>

<jsp:include page="Content/Layout/Footer.jsp" />