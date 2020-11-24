<%-- 
    
    Document   : home
    Created on : 05/08/2019, 19:48:25
    Author     : 03728827142
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Content/LayoutC/Header.jsp" />
<jsp:include page="Content/LayoutC/Menu.jsp" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />

<div class="container">
    <c:if test="${msg != ''}">
        <div class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    <div class="card">
        <div class="card-header text-gray-100 shadow-lg bg-gradient-info"> <h1> Bem vindo ${cliente.nome} </h1></div>
        <div class="card-body">
            <div class="row">
                
                <div class="col">
                    <a href="AreaClienteServlet?acao=ordens" class="btn">
                        <div class="card bg-gradient-success" style="height: 200px;">
                            <h1 class="text-gray-100" >Suas
                                Ordens de Serviço <i class="fas fa-tools fa-3x"></i> </h1> 
                        </div>
                    </a>
                </div>
                <div class="col">
                    <a href="AreaClienteServlet?acao=minhaConta" class="btn">
                        <div class="card bg-gradient-warning" style="height: 200px;">
                            <h1 class="text-gray-100"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Sua conta &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i class="fas fa-user-cog fa-3x"></i></i> </h1> 
                        </div>
                    </a>
                </div>

                
            </div>
            <div class="row">
                <div class="col-4"> &nbsp; </div>
                <div class="col-4"> 
                    <div class="col">
                        <a href="AreaClienteServlet?acao=produtos" class="btn">
                            <div class="card bg-gradient-info" style="height: 200px;">
                                <h1 class="text-gray-100">Produtos <i class="fas fa-box-open fa-3x"></i> </h1> 
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-4"> &nbsp; </div>
            </div>
            
        </div>
    </div>
</div>
    

<jsp:include page="Content/LayoutC/Footer.jsp" />
     
       