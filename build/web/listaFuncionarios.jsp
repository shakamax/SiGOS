<%-- 
    Document   : listaOS
    Created on : 29/04/2019, 14:24:18
    Author     : Geovane
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="container-fluid">
  
    <div class="card shadow mb-4">
        <div class="card-header align-content-center align-items-center bg-gradient-info">
            <h1 class="h3 mb-4 text-gray-100">Quadro de Funcionários 
                <span class="float-right">
                    <a class="btn btn-primary bg-gradient-primary" href="FuncionarioServlet?acao=nova" >
                        <i class="fas fa-plus-circle"> </i>
                    </a>
                </span>
            </h1>
        </div>
        <div class="card-body">
            
            
         <jsp:useBean id="funcionarios" scope="session" class="br.com.sigos.model.Funcionario"/>
         
         
          <div class="row">
            <c:forEach items="${funcionarios}" var="funcionario">
            <div class="col-4">
                    
                <div class="card">
                    
                    <div class="card-header" align="center"><h5><strong>${funcionario.funcao}</strong></h5></div>
                    
                    
                    <div class="card-body">
                        <strong>Nome: </strong>${funcionario.nome}
                    </div>
                    
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item" row="2"> <strong>Email: </strong>${funcionario.email}</li>
                      </ul>
                      <div class="row">
                          <div class="col-8"></div>
                          <div class="col-2">
                        <a href="FuncionarioServlet?acao=editar&id=${funcionario.id}" class="btn btn-primary bg-gradient-warning" data-toggle="tooltip" data-placement="top" title="Editar">
                            <i class="fas fa-user-edit"></i>
                        </a>
                      </div>    
                            
                      <div class="col-2">  
                        <a href="FuncionarioServlet?acao=deletar&id=${funcionario.id}" onclick="return confirm ('Deseja realmente excluir este cliente?')"  class="btn btn-primary bg-gradient-danger" data-toggle="tooltip" data-placement="top" title="Deletar">
                            <i class="fas fa-trash"></i>
                        </a>
                      </div>
                        
                    <%-- DIV QUE ROW DA CARD --%>
                </div>
                
            <%-- DIV QUE FECHA A CARD --%>
            </div>
            <br>
            
            <%-- DIV QUE FECHA A COLUNA ANTES DA CARD --%>
          </div>
            
          
          </c:forEach>
          
            <%--DIV QUE FECHA O BODY CARD PRINCIPAL --%>
        </div>
        
        <%-- Div que fecha a card principal--%>
    </div>  
<%-- Div que fecha o container--%>
</div>
</div>
    
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
