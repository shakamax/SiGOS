<%-- 
    Document   : listaEquipamentos
    Created on : 09/05/2019, 12:13:55
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />

<jsp:useBean id="lista" scope="session" class="br.com.sigos.model.ListaEquipamento" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />


<div class="container-fluid">
    
    <h1 class="h3 mb-4 text-gray-800">Lista de Equipamentos de <strong> ${cliente.nome} </strong></h1>
 <div class="card shadow mb-4">
 <table class="table table-hover table-striped">  
     <thead class="card-header text-gray-100 bg-gradient-info">
        <th>Equipamento</th>
        <th>Defeito</th>
        <th>Acessórios</th>
        <th>Observação</th>
        <th>Ações</th>
     </thead>
     <tr>
         <td>${lista.equipamento}</td>
         <td>${lista.defeito}</td>
         <td>${lista.acessório}</td>
         <td>${lista.observacao}</td>
        <td>
            <button class="btn btn-primary bg-gradient-success" action="#">
                <i class="fas fa-clipboard-list"></i>
            </button>
            <button class="btn btn-primary bg-gradient-warning" action="#">
                <i class="fas fa-pencil-alt"></i>
            </button>
            <button class="btn btn-primary bg-gradient-danger" action="#">
                <i class="fas fa-trash-alt"></i>
            </button>
        </td>
         
     </tr>


 </table>
 
 </div>
   
</div>  

    
<jsp:include page="Content/Layout/Footer.jsp" />
