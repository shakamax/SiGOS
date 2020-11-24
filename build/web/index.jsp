<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Login</title>

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
<!--                      ALERTAS -->
                    <c:if test="${msg}">
                      <span class="alert alert-danger" id="error" style="display:none;"> Usuário e/ou senha incorreto.</span>
                    </c:if>
                      
                    <h1 class="h4 text-gray-900 mb-4">Bem-Vindo!</h1>
                  </div>
                    <form class="user" method="post" action="LoginServlet?acao=login" name="formulario">
                    <div class="form-group">
                        <input type="email" class="form-control form-control-user" name="logemail" id="logemail" required="" aria-describedby="emailHelp" placeholder="Digite seu email">
                    </div>
                    <div class="form-group">
                        <input type="password" required="" class="form-control form-control-user" name="logpassword" id="logpassword" placeholder="Senha">
                    </div>
                    <div class="form-group">
<!--                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Quero me lembrar desse email</label>
                      </div>-->
                    </div>
                    <button id="btn_logar" type="submit" class="btn btn-info btn-user btn-block">
                      Entrar
                    </button>
                    <hr>
                  </form>
            
                  <hr>
<!--                  <div class="text-center">
                    <a class="small" href="redefinirSenha.jsp">Esqueceu a senha?</a>
                  </div>-->
                  <div class="text-center">
                      <button class="btn btn-primary text-gray-100 bg-gradient-info"  data-toggle="modal" data-target="#modalExemplo">Criar uma conta</button>

                    <!-- Modal -->
                    <div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog modal-xl" role="document">
                        <div class="modal-content">
                          <div class="modal-header bg-gradient-info">
                              <!--<div class="card-header bg-gradient-info"><h1 class="h3 mb-4 text-gray-100">Cadastrar Cliente</h1></div>-->
                            <h1 class="modal-title h1 mb-4 text-gray-100" id="exampleModalLabel">Cadastrar</h1>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                              <span aria-hidden="true">&times;</span>
                            </button>
                          </div>
                          <div class="modal-body" >
                              <jsp:include page="cadastroCliente.jsp" />
                          </div>
                              <%--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                            <button type="submit" class="btn btn-primary">Salvar mudanças</button>
                          </div>--%>
                        </div>
                      </div>
                    </div>
                        
<!--                        Fim modal-->
                    
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
</script>

  
  
</body>

</html>
