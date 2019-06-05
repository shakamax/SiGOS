<%-- 
    Document   : listaEquipamentos
    Created on : 09/05/2019, 12:13:55
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="listas" scope="session" class="br.com.sigos.model.ListaEquipamento" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />


<div class="container-fluid">
    
    <h1 class="h3 mb-4 text-gray-800">Lista de Equipamentos de <strong> ${cliente.nome} </strong> <span class="float-right"> 
            <a href="EquipamentosServlet?acao=novo&id=${cliente.id}" title="Novo Equipamento" class="btn btn-primary bg-gradient-primary"> <i class="fas fa-plus-circle"></i> Adicionar Equipamento </a>
        </span>   </h1>
 <div class="card shadow mb-4">
 <table class="table table-hover table-striped">  
     <thead class="card-header text-gray-100 bg-gradient-info">
        <th>Equipamento</th>
        <th>Defeito</th>
        <th>Acessórios</th>
        <th>Observação</th>
        <th style="text-align: right" > Ações</th>
     </thead>
     <c:forEach var="lista" items="${listas}" >
        <tr>
            <td>${lista.equipamento}</td>
            <td>${lista.defeito}</td>
            <td>${lista.acessório}</td>
            <td>${lista.observacao}</td>
           <td align="right">
<!--               <button class="btn btn-primary bg-gradient-success" action="#">
                   <i class="fas fa-clipboard-list"></i>
               </button>-->
               <a class="btn btn-primary bg-gradient-warning" href="EquipamentosServlet?acao=editar&id=${lista.id}" title="Editar" >
                   <i class="fas fa-pencil-alt"></i>
               </a>
               <a class="btn btn-primary bg-gradient-danger" title="Deletar" href="EquipamentosServlet?acao=deletar&id=${lista.id}" onclick="return confirm('Deseja realmente deletar este equipamento?')">
                   <i class="fas fa-trash-alt"></i>
               </a>
           </td>

        </tr>
     </c:forEach>


 </table>
 
 </div>
   
</div>  

    
<jsp:include page="Content/Layout/Footer.jsp" />
