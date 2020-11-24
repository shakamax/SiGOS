<%-- 
    Document   : Menu
    Created on : 25/04/2019, 20:06:25
    Author     : 03728827142
--%>
<jsp:useBean id="user" scope="session" class="br.com.sigos.model.Funcionario" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />
<jsp:useBean id="log" scope="session" class="br.com.sigos.model.LogOs" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:if test="${empty cliente.nome}" >
    
    <jsp:forward page="../../index.jsp" />
    
</c:if>
  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="home.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laptop-medical"></i>
        </div>
        <div class="sidebar-brand-text mx-3"> SiGOS <sup>1.0</sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item">
        <a class="nav-link" href="AreaClienteServlet?acao=dash">
          <i class="fas fa-home"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Aqui começa realmente o MENU! -->



      <!-- Heading -->
      <div class="sidebar-heading">
        Ordem de Serviço
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Ordens de Serviços</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Menu O.S:</h6>

            <a class="collapse-item" href="AreaClienteServlet?acao=ordens">Suas Ordens de Serviço</a>
            <!--<a class="collapse-item" href="#">Solicitar Ordem de Serviço</a>-->

            
          </div>
        </div>
      </li>


      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProdutos" aria-expanded="true" aria-controls="collapseProdutos">
          <i class="fas fa-box-open"></i>
          <span>Produtos</span>
        </a>
        <div id="collapseProdutos" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Menu Produtos:</h6>
            <a class="collapse-item" href="AreaClienteServlet?acao=produtos">Todos Produtos</a>

          </div>
        </div>
      </li>
      
      
      

      <!-- Divider 
      <hr class="sidebar-divider">
      -->

      <!-- Nav Item - Charts -->
<!--      <li class="nav-item">
        <a class="nav-link" href="novoTecnico.jsp" >
          <i class="fas fa-user-astronaut"></i>
          <span>Novo Técnico</span></a>
      </li>-->

      <!-- ESSE É O DIVISOR DO MENU LATERAL SEM ELE LASCA TUDO -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- ESTE É O BOTÃO PARA EXPANDIR O SIDEBAR -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- FIM DO SIDE BAR -->











    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>
          <h2 class="text-gray-600"> Bem vindo ${cliente.nome} </h2>

<!--           Topbar Search 
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" method="GET" >
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Procurar..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary bg-gradient-info" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>-->







          <!-- MENU DO TOPO!!!!! -->
          <ul class="navbar-nav ml-auto">

              
<!--             Nav Item - Search Dropdown (Visible Only XS) 
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
               Dropdown - Messages 
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>-->
            </li>



            <!-- Nav Item - ALERTAS DE ATIVIDADES -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-exclamation-triangle"></i>
                <!-- CONTADOR DE ALERTAS DE ATIVIDADES -->
                
                <span class="badge badge-danger badge-counter">${logs.size()}</span>
              </a>
              <!-- Dropdown - ALERTAS DE ATIVIDADES -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  ALERTAS DE ATIVIDADES
                </h6>
                  
                  <c:forEach items="#{logs}" var="log" >
                    <a class="dropdown-item d-flex align-items-center" href="AreaClienteServlet?acao=minhaOrdem&id=${log.idOs}">
                      <div class="mr-3">
                        <div class="icon-circle bg-warning">
                          <i class="fas fa-tools text-white"></i>
                        </div>
                      </div>
                      <div>
                          <div class="small text-gray-500"> ${log.dataHora} </div>
                          <span class="font-weight-bold">${log.descricao}</span>
                      </div>
                    </a>
                  </c:forEach>
                  <div class="align-items-center    ">
                  <a href="AreaClienteServlet?acao=ordens" class="" >
                    <h6 class="dropdown-header bg-gradient-info" align="center">
                      Veja todos
                    </h6>
                  </a>
                  </div>
            </li>




            <!-- MENSSAGENS DE CLIENTES -->
<!--            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>

                 CONTADOR DE MENSAGEM 
                <span class="badge badge-danger badge-counter">1</span>
              </a>

               Dropdown DAS MENSAGEM 
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                <h6 class="dropdown-header">
                  Message Center
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="dropdown-list-image mr-3">
                    <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
                    <div class="status-indicator bg-success"></div>
                  </div>
                  <div class="font-weight-bold">
                    <div class="text-truncate">Oi, qual foi o tamanho do HD colocado?.</div>
                    <div class="small text-gray-500">Emília Flores</div>
                  </div>
                </a>
                
                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
              </div>
            </li>-->

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - USUÁRIO LOGADO -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small">${cliente.nome}</span>
                <img class="img-profile rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvuY4sbo2WwFGmBykiiKjq3qxG4sOBIpo6yXbRsYaWZlJM7Zk9">
              </a>
              <!-- Dropdown - INFORMAÇÕES DO USUÁRIO LOGADO -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="AreaClienteServlet?acao=minhaConta">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Sua conta
                </a>

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->