<%-- 
    Document   : verProdutos
    Created on : 07/08/2019, 14:14:17
    Author     : Guilherme
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Content/LayoutC/Header.jsp" />
<jsp:include page="Content/LayoutC/Menu.jsp" />

<jsp:useBean id="produto" scope="session" class="br.com.sigos.model.Produto" />
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
        <th>Disponibilidade</th>
        <th>Valor</th>
     </thead>
    <c:forEach var="produto" items="${prods}" >
        <tr>
            <td>${produto.codigo}</td>
            <td>${produto.nome}</td>
            <td>${produto.descricao}</td>
            <td>${produto.categorias}</td>
            <td align="center"> <h3> <span class="badge badge-light"> <c:if test="${produto.quantidade > 4}" >Alta</c:if><c:if test="${produto.quantidade < 4 && produto.quantidade > 0}" >Baixa</c:if><c:if test="${produto.quantidade == 0 || produto.quantidade < 0}" >Indisponível</c:if> </span></h3></td>
            <td>R$ <fmt:formatNumber value="${produto.valor}" minFractionDigits="2" /></td>

         </tr>
    </c:forEach>


 </table>
 
 </div>
   
</div>
    

<jsp:include page="Content/LayoutC/Footer.jsp" />
     
       