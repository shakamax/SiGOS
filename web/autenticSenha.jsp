<%-- 
    Document   : autenticSenha
    Created on : 20/08/2019, 20:27:51
    Author     : 03728827142
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cadastre sua senha</title>

  <!-- Custom fonts for this template-->
  <link href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" rel="stylesheet" type="text/css"/>
  <link href="Content/css/fontawesome/seila.css" rel="stylesheet" type="text/css">


  <!-- Custom styles for this template-->
  <link href="Content/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="Content/css/sb-admin-2.css" rel="stylesheet" type="text/css"/>

  
</head>

<body class="bg-gradient-info">
  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
        <c:if test="${msg != ''}">
            <div id="alerta" class="alert ${tipo}" align="center">
                <h3> ${msg} </h3>
            </div>
        </c:if>
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-6 d-none d-lg-block"><img src="Content/img/SiGOS logo 2.jpg" id="logo" width="500px" style="margin-top: 10%" >
                <img id="loading" src="Content/img/loading.gif" width="500px" height="200px" style="display: none;"/>
                </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">

                      
                    <h1 class="h4 text-gray-900 mb-4">Seja bem vindo ${cliente.nome}, por favor cadastre a sua senha!</h1>
                  </div>
                  <form class="user" method="post" name="FormSenha" action="AreaClienteServlet?acao=autanticar" onsubmit="return validarSenha();" name="formulario">
                    <div class="form-group">
                        <input type="password" class="form-control form-control-user" name="senha" id="senha" required="" aria-describedby="emailHelp" placeholder="Digite sua senha">
                    </div>
                        <span id="conf" class="badge badge-danger" style="display: none;">Senhas incompatíveis</span>
                    <div class="form-group"> 
                        <input type="password" required="" class="form-control form-control-user" name="senhaConfirm" id="senha2" placeholder="Confirme sua senha">
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="id" id="id" value="${cliente.id}" />
<!--                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Quero me lembrar desse email</label>
                      </div>-->
                    </div>
                    <button id="btn_logar" type="submit" onclick="return validarSenha();" class="btn btn-info btn-user btn-block">
                      Cadastrar Senha
                    </button>
                    <hr>
                  </form>
            
                  <hr>
<!--                  <div class="text-center">
                    <a class="small" href="redefinirSenha.jsp">Esqueceu a senha?</a>
                  </div>-->
                  <div class="text-center">

                  </div>
                    
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  
  <script src="Content/js/jquery.min.js" type="text/javascript"></script>
  
  <script src="Content/js/bootstrap.bundle.min.js" type="text/javascript"></script>

  <!-- Core plugin JavaScript-->
  <script src="Content/js/jquery.easing.min.js" type="text/javascript"></script>
  
  <!-- Custom scripts for all pages-->
  <script src="Content/js/sb-admin-2.min.js" type="text/javascript"></script>

   <!--  JavaScript para Máscaras-->
  <script src="Content/js/jquery.mask.min.js" type="text/javascript"></script>
  
  <script src="Content/js/jquery.priceformat.min.js" type="text/javascript"></script>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js" type="text/javascript"></script>
  
  
<script>
    $(document).ready(function () { 
        var $seuCampoCpf = $(".CPF");
        $seuCampoCpf.mask('999.999.999-99');
    });
    
    function validarSenha() {
            var senha = FormSenha.senha.value;
            var senha2 = FormSenha.senha2.value;
            
            if(senha == "" || senha.length < 5){
                alert('Senha deve conter pelo menos 5 caracteres.');
                FormSenha.senha.focus();
                return false;
            }
            if(senha2 == "" || senha2.length < 5){
                alert('Senha deve conter pelo menos 5 caracteres.');
                FormSenha.senha2.focus();
                return false;
            }
            if(senha != senha2){
                alert('Senhas estão diferentes, por favor verifique sua senha.');
                FormSenha.senha2.focus();
                $('#conf').show();
                return false;
            }
    
</script>

  
  
</body>

</html>
