<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
  <link href="Content/css/sb-admin-2.css" rel="stylesheet" type="text/css"/>

</head>

<body class="bg-gradient-info">
  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-6 d-none d-lg-block"><img src="Content/img/SiGOS logo 2.jpg" width="500px" style="margin-top: 10%" ></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Bem-Vindo!</h1>
                  </div>
                  <form class="user">
                    <div class="form-group">
                      <input type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Digite seu email">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Senha">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Quero me lembrar desse email</label>
                      </div>
                    </div>
                    <a href="home.jsp" class="btn btn-info btn-user btn-block">
                      Entrar
                    </a>
                    <hr>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="redefinirSenha.jsp">Esqueceu a senha?</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="cadastrar.jsp">Criar uma conta</a>
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

</body>

</html>
