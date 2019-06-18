<%-- 
    Document   : clienteParaOs
    Created on : 05/06/2019, 21:30:59
    Author     : 03728827142
--%>

<%@page import="java.util.Date"%>
<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cliente" class="br.com.sigos.model.Cliente" scope="session" />


<div class="container">
 
    <div class="card shadow mb-4">
     <div class="card-header justify-content-between align-items-lg-center flex-row bg-gradient-info">
         <div class="row">
         <h1 class="h3 mb-4 text-gray-100"> <strong>   Clientes : </strong> </h1>
         <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="ordemServicoServlet?acao=buscar" method="POST" >
                <div class="form-row">
                    <div class="col">
                    <select name="coluna" id="coluna" class="form-control">
                            <option value="nome" > Procurar por nome </option>
                            <option value="email" > Procurar por email </option>
                            <option value="telefone" > Procurar por Telefone </option>
                            <option value="cpf" > Procurar por CPF </option>
                            <option value="CEP" > Procurar por CEP </option>
                            <option value="Cidade" > Procurar por Cidade </option>
                            <option value="Estado" > Procurar por Estado </option>
                    </select>
                    </div>
                    <!-- PESQUISA CLIENTE -->
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Procurar clientes" name="busca" aria-label="Search" aria-describedby="basic-addon2">
                     <div class="input-group-append">  
                         <button class="btn btn-primary bg-gradient-info" type="submit">
                         <i class="fas fa-search fa-sm"></i>
                       </button>
                     </div>
                    </div>
                </div>
             </form>
         </div>
     </div>
        <div class="card-body">
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
            <c:forEach items="${clientes}" var="cliente">
                <tr>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.CPF}</td>
                    <td>${cliente.cidade}</td>
                    <td> 
                        <c:if test="${cliente.status}" > Autenticado </c:if> <c:if test="${!cliente.status}" > Não Atenticado </c:if>
                    </td>
                    <td>
                        <a class="btn btn-primary bg-gradient-primary" href="ordemServicoServlet?acao=nova&id=${cliente.id}" > <i class="fas fa-fw fa-wrench"></i> Abrir O.S.</a> 
                    </td>
                </tr>
                
            </c:forEach>
            </tbody>
        </table>   
        </div>
    </div>            
             
             
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
