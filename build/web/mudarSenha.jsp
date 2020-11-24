<%-- 
    Document   : mudarSenha
    Created on : 10/08/2019, 23:34:03
    Author     : Guilherme
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Content/LayoutC/Header.jsp" />
<jsp:include page="Content/LayoutC/Menu.jsp" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />

<div class="container">
    <a href="AreaClienteServlet?acao=minhaConta" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
    <c:if test="${msg != ''}">
        <div class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    <div class="card">
        <div class="card-header text-gray-100 shadow-lg bg-gradient-info"> <h1> Mudar sua senha. </h1></div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form method="post" class="form-group" name="FormSenha" onsubmit="return validarSenha();" action="AreaClienteServlet?acao=password">
                        <div class="form-row">
                            <label><h4> Digite sua senha. </h4></label>
                            <input type="password" name="senhaAtual" required class="form-control" />
                        </div>
                        <div class="form-row">
                            <label><h4> Nova senha</h4></label>
                            <input type="password" class="form-control" required name="senha" id="senha" />
                        </div>
                        <div class="form-row">
                            <label><h4> Confirme a sua nova senha. </h4><span id="conf" class="badge badge-danger" style="display: none;">Senhas incompatíveis</span></label>
                            <input type="password" class="form-control" required name="senha2" id="senha2" />
                        </div>
                        
                        <br>
                        <div class="form-row">
                            <div class="col" align="center">
                                <button type="submit" class="btn bg-gradient-success" onclick="return validarSenha();" style="color: white;" >
                                    Confirmar cadastro
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </div>
</div>
    



<jsp:include page="Content/LayoutC/Footer.jsp" />
