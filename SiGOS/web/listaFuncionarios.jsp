<%-- 
    Document   : listaOS
    Created on : 29/04/2019, 14:24:18
    Author     : Geovane
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />

<div class="container-fluid">
  
    <div class="card shadow mb-4">
        <div class="card-header align-content-center align-items-center bg-gradient-info">
          <h1 class="h3 mb-4 text-gray-100">Quadro de Funcionários</h1>
        </div>
        <div class="card-body">
            
            
         <jsp:useBean id="funcionario" scope="session" class="br.com.sigos.model.Funcionario"/>   
          <div class="row">
            <div class="col-3">
                <div class="card shadow mb-4">
                    
                    <div class="card-header bg-gradient-success text-gray-100" align="center"><h5><strong>${funcionario.funcao}</strong></h5></div>
                        
                    <div class="card-body">
                        <strong>Nome: </strong>${funcionario.nome}
                    </div>
                      
                    
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item" row="2"> <strong>Email: </strong>${funcionario.email}</li>
                      </ul>
                    
                    <%-- DIV QUE FECHA A CARD OS --%>
                </div>
                
            <%-- DIV QUE FECHA A COL --%>
            </div>
            <%-- DIV QUE FECHA A ROW --%>
          </div>
          
            <%--DIV QUE FECHA O BODY CARD PRINCIPAL --%>
        </div>
        
        <%-- Div que fecha a card principal--%>
    </div>    
<%-- Div que fecha o container--%>
</div>
</div>
    
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
