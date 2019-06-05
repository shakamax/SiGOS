<%-- 
    
    Document   : index
    Created on : 24/04/2019, 19:48:25
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />

<div class="container">
    <div class="card">
        <div class="card-header bg-gradient-info">
            <h1 class="h3 mb-4 text-gray-100">Bem vindo</h1>
            <h2 class="h4 mb-4 text-gray-100"> Situação Ordem de serviço </h2>
        </div>
        <div class="card-body">
                <!-- GRÁFICOS DE BARRA -->
                <div class="card-body">
                  <h4 class="small font-weight-bold">O.S Recem Abertas <span class="float-right">10%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-danger" role="progressbar" style="width: 10%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
                  </div>
                  <h4 class="small font-weight-bold">O.S Em andamento <span class="float-right">10%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-warning" role="progressbar" style="width: 10%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
                  </div>
                  
                  <h4 class="small font-weight-bold">O.S Concluídas <span class="float-right">80%</span></h4>
                  <div class="progress">
                    <div class="progress-bar bg-success" role="progressbar" style="width: 80%" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"></div>
                  </div>
                </div>
              </div>
            
    </div>
</div>
    
</div>
</div>

<jsp:include page="Content/Layout/Footer.jsp" />
     
       