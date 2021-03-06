<%-- 
    Document   : contaFuncionario
    Created on : 19/08/2019, 08:48:15
    Author     : Guilherme
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="func" scope="session" class="br.com.sigos.model.Funcionario" />

<div class="container">
    <c:if test="${msg != ''}">
        <div class="alert ${tipo}" align="center">
            <h3> ${msg} </h3>
        </div>
    </c:if>
    <div class="card">
        <div class="card-header text-gray-100 shadow-lg bg-gradient-info"> <h1> Sua conta </h1></div>
        <div class="card-body">
            <div class="row">
                
                <div class="col">
                    <a href="FuncionarioServlet?acao=mudarSenha" class="btn">
                        <div class="card bg-gradient-success" style="height: 200px;">
                            <h1 class="text-gray-100" >&nbsp;&nbsp; Mudar sua Senha &nbsp;&nbsp;
                                <i class="fas fa-key fa-3x"></i> </h1> 
                        </div>
                    </a>
                </div>
                <div class="col">
                    <a href="#" class="btn" data-toggle="modal" data-target="#mudarEmail">
                        <div class="card bg-gradient-info" style="height: 200px;">
                            <h1 class="text-gray-100">&nbsp;&nbsp; Mudar seu Email &nbsp;&nbsp;&nbsp;&nbsp;
                                <i class="fas fa-at fa-3x"></i> </h1> 
                        </div>
                    </a>
                </div>

            </div>

        </div>
    </div>
</div>


  <!-- Modal troca de email-->
  <div class="modal fade" id="mudarEmail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Deseja mudar seu e-mail?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">�</span>
          </button>
        </div>
        <div class="modal-body">
            <form method="post" class="form-group" name="FormEmail" onsubmit="return validarEmail();" action="FuncionarioServlet?acao=mudarEmail" >

                <div class="form-row">
                    <label><h4>Confirme seu e-mail atual.</h4></label>
                    <input type="email" required name="email" class="form-control" />
                </div>
                <div class="form-row">
                    <label><h4>Seu novo e-mail.</h4> <span id="conf" class="badge badge-danger" style="display: none;">E-mails preicsam ser iguais</span></label>
                    <input type="email" class="form-control" required name="emailNovo" id="email" />
                </div>
                <div class="form-row">
                    <label><h4>Confirme seu novo e-mail.</h4></label>
                    <input type="email" class="form-control" required name="ConfemailNovo" id="email2" />
                </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <button class="btn btn-primary" onclick="return validarEmail();" >Mudar o e-mail</button>
            </form>
        </div>
      </div>
    </div>
  </div>


    

<jsp:include page="Content/Layout/Footer.jsp" />
