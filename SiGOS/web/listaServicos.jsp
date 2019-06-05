<%-- 
    Document   : Servicos
    Created on : 07/05/2019, 21:47:41
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="serv" scope="session" class="br.com.sigos.model.Servico" />


<div class="container">
    
    
    <h1 class="h3"> Todos os Serviços</h1>
        

            <table class="table table-hover table-striped">
                <thead class="bg-gradient-info text-gray-100">
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th class="text-right">Ações </th>
                </thead>
                <tbody>
                    <tr>
                        <td>${serv.nome}</td>
                        <td>${serv.descricao}</td>
                        <td>${serv.valor}</td>
                        <td align="right">
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
                </tbody>
                
            </table>
            
        
    </div>
</div>
    
</div>

<jsp:include page="Content/Layout/Footer.jsp" />