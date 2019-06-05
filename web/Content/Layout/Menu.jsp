<%-- 
    Document   : Menu
    Created on : 25/04/2019, 20:06:25
    Author     : 03728827142
--%>

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
        <a class="nav-link" href="home.jsp">
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

            <a class="collapse-item" href="listaOS.jsp">Todas Ordens de Serviço</a>
            <a class="collapse-item" href="ordemServicoServlet?acao=nova">Nova Ordem de Serviço</a>
            <a class="collapse-item" href="tecnicoOS.jsp">Suas Ordens de Serviço</a>

            
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
            <a class="collapse-item" href="ProdutoServlet?acao=listar">Todos Produtos</a>
            <a class="collapse-item" href="ProdutoServlet?acao=novoProduto">Cadastrar Produtoo</a>
          </div>
        </div>
      </li>
      
            <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseClients" aria-expanded="true" aria-controls="collapseClients">
          <i class="fas fa-users-cog"></i>
          <span>Clientes</span>
        </a>
        <div id="collapseClients" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Menu Clientes</h6>
            <a class="collapse-item" href="ClienteServlet?acao=listar">Todos Clientes</a>
            <a class="collapse-item" href="ClienteServlet?acao=nova">Cadastrar Clientes</a>
          </div>
        </div>
      </li>
      
      
      
      
            <!-- Nav Item - FUNCIONÀRIOS MENU -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFunc" aria-expanded="true" aria-controls="collapseClients">
          <i class="fas fa-user-astronaut"></i>
          <span>Funcionários</span>
        </a>
        <div id="collapseFunc" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header"> Menu Funcionários </h6>
            <a class="collapse-item" href="FuncionarioServlet?acao=listar">Todos Funcionários</a>
            <a class="collapse-item" href="FuncionarioServlet?acao=nova">Novo Funcionário</a>
          </div>
        </div>
      </li>
      
      
      
      
            <!-- Nav Item - Serviços MENU -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseServ" aria-expanded="true" aria-controls="collapseClients">
          <i class="fas fa-tools"></i>
          <span>Serviços</span>
        </a>
        <div id="collapseServ" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header"> Menu Serviços </h6>
            <a class="collapse-item" href="ServicoServlet?acao=listar"> Lista Serviços </a>
            <a class="collapse-item" href="ServicoServlet?acao=novoServico"> Novo Serviço </a>
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

          <!-- Topbar Search -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Procurar..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary bg-gradient-info" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>







          <!-- MENU DO TOPO!!!!! -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
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
              </div>
            </li>



            <!-- Nav Item - ALERTAS DE ATIVIDADES -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-exclamation-triangle"></i>
                <!-- CONTADOR DE ALERTAS DE ATIVIDADES -->
                <span class="badge badge-danger badge-counter">2</span>
              </a>
              <!-- Dropdown - ALERTAS DE ATIVIDADES -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  ALERTAS DE ATIVIDADES
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">24/04/2019</div>
                    <span class="font-weight-bold">Nova atividade no trello, por favor se apressas!</span>
                  </div>
                </a>

                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-warning">
                      <i class="fas fa-exclamation-triangle text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">25/04/2019</div>
                    Serviço foi autorizado pelo Cliente.
                  </div>
                </a>
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>




            <!-- MENSSAGENS DE CLIENTES -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>

                <!-- CONTADOR DE MENSAGEM -->
                <span class="badge badge-danger badge-counter">1</span>
              </a>

              <!-- Dropdown DAS MENSAGEM -->
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
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - USUÁRIO LOGADO -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valdimares Técnico</span>
                <img class="img-profile rounded-circle" src="https://www.rj.senac.br/wp-content/uploads/2017/05/tecnico-em-informatica-2-377x377.jpg">
              </a>
              <!-- Dropdown - INFORMAÇÕES DO USUÁRIO LOGADO -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Sua conta
                </a>

                <a class="dropdown-item" href="#">
                  <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                  Atividades
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