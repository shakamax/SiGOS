<%-- 
    Document   : NovaOS
    Created on : 25/04/2019, 21:01:36
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />

<jsp:useBean id="produtos" scope="session" class="br.com.sigos.model.Produto" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
    
 <h1 class="h3 mb-4 text-gray-800" align="center">TODOS PRODUTOS</h1>
 <div class="card shadow mb-4">
 <table class="table table-hover table-striped">  
     <thead class="card-header text-gray-100 bg-gradient-info">
        <th>Cógigo de barras</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Categoria</th>
        <th>Localizacao</th>
        <th>Quantidade</th>
        <th>Valor</th>
        <th class="align-items-md-baseline">Ações <span class="float-right"> <a class="btn btn-primary bg-gradient-primary" href="ProdutoServlet?acao=novoProduto" title="Adicionar novo produto" > <i class="fas fa-plus-circle"></i> </a></span></th>
     </thead>
    <c:forEach var="produto" items="${produtos}" >
        <tr>
            <td>${produto.codigo}</td>
            <td>${produto.nome}</td>
            <td>${produto.descricao}</td>
            <td>${produto.categorias}</td>
            <td align="center">${produto.localizacao} </td>
            <td align="center">${produto.quantidade}</td>
            <td>R$ <fmt:formatNumber value="${produto.valor}" minFractionDigits="2" /></td>
            <td>
                <a class="btn btn-primary bg-gradient-success" href="ProdutoServlet?acao=exibir&id=${produto.ID}">
                    <i class="fas fa-clipboard-list"></i>
                </a>
                <a class="btn btn-primary bg-gradient-warning" href="ProdutoServlet?acao=editar&id=${produto.ID}">
                    <i class="fas fa-pencil-alt"></i>
                </a>
                <a class="btn btn-primary bg-gradient-danger" href="ProdutoServlet?acao=deletar&id=${produto.ID}" onclick="return confirm('Deseja realmente excluir este Produto?')" >
                    <i class="fas fa-trash-alt"></i>
                </a>
            </td>

         </tr>
    </c:forEach>


 </table>
 
 </div>
   
</div>  

    
<jsp:include page="Content/Layout/Footer.jsp" />