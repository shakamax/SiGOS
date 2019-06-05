<%-- 
    Document   : listaOS
    Created on : 29/04/2019, 14:24:18
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />

<div class="container-fluid">
  
    <div class="card shadow mb-4">
        <div class="card-header align-content-center align-items-center bg-gradient-info">
          <h1 class="h3 mb-4 text-gray-100">Ordens de Serviço</h1>
        </div>
        <div class="card-body">
            
            
            
          <div class="row">
            <div class="col-3">
                <div class="card">
                    <div class="card-header">OS 150 235</div>
                    <div class="card-body">
                        Cliente Tal
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item" row="2"> <b>Equipamento :</b>  Acer VX5, I5, 16GB RAM </li>
                          <li class="list-group-item"> <b>Técnico :</b> Valdimar </li>
                          <li class="list-group-item"> <b>Status :</b> Aberta </li>
                          <li class="list-group-item"><b>Última atualização :</b> Criada em 30/04/2019</li>
                      </ul>
                    <div class="card-body">
                        <a title="Gerenciar O.S." class="btn bg-gradient-info" style="color: white;" href="gerenciarOS.jsp">
                            <i class="fas fa-list-alt"></i>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="col-3">
                <div class="card">
                    <div class="card-header">OS 150 234</div>
                    <div class="card-body">
                        Fulano
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item"> <b>Equipamento :</b> Lenovo Ideapad 320, I7,  8GB RAM </li>
                          <li class="list-group-item"> <b>Técnico :</b> Guilherme </li>
                          <li class="list-group-item"> <b>Status :</b> Fechado </li>
                          <li class="list-group-item"><b>Última atualização :</b> Concluída dia 29/04/2019</li>
                      </ul>
                    <div class="card-body">
                        <button title="Gerenciar OS" class="btn bg-gradient-info" style="color: white;" action="#">
                            <i class="fas fa-list-alt"></i>
                        </button>
                    </div>
                    
            <%-- DIV QUE FECHA A CARD OS --%>
                </div>
                
            <%-- DIV QUE FECHA A COL --%>
            </div>
            
            <div class="col-3">
                <div class="card">
                    <div class="card-header">OS 150 233</div>
                    <div class="card-body">
                        Ciclano
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item"> <b>Equipamento :</b> Desktop 13, Positivo, 4GB RAM  </li>
                          <li class="list-group-item"> <b>Técnico :</b> Geovane </li>
                          <li class="list-group-item"> <b>Status :</b> Fechado </li>
                          <li class="list-group-item"><b>Última atualização :</b> Concluída dia 28/04/2019</li>
                      </ul>
                    <div class="card-body">
                        <button title="Gerenciar OS" class="btn bg-gradient-info" style="color: white;" action="#">
                            <i class="fas fa-list-alt"></i>
                        </button>
                    </div>
                    
            <%-- DIV QUE FECHA A CARD OS --%>
                </div>
                
            <%-- DIV QUE FECHA A COL --%>
            </div>
            
            
            <div class="col-3">
                <div class="card">
                    <div class="card-header">OS 150 232</div>
                    <div class="card-body">
                        Beltrano
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item"> <b>Equipamento :</b> Desktop 15, CoolerMaster, 8GB RAM  </li>
                          <li class="list-group-item"> <b>Técnico :</b> Valdimar </li>
                          <li class="list-group-item"> <b>Status :</b> Fechado </li>
                          <li class="list-group-item"><b>Última atualização :</b> Concluída dia 28/04/2019</li>
                      </ul>
                    <div class="card-body">
                        <button title="Gerenciar OS" class="btn bg-gradient-info" style="color: white;" action="#">
                            <i class="fas fa-list-alt"></i>
                        </button>
                    </div>
                    
            <%-- DIV QUE FECHA A CARD OS --%>
                </div>
                
            <%-- DIV QUE FECHA A COL --%>
            </div>
            
        <%-- DIV QUE FECHA A ROW --%>
        </div>
        
        <div class="row">
            
            <div class="col-3">
                <div class="card">
                    <div class="card-header">OS 150 231</div>
                    <div class="card-body">
                        Carlos Eduardo
                    </div>
                      <ul class="list-group list-group-flush">
                          <li class="list-group-item"> <b>Equipamento :</b> Impressora HP, Multifuncional Deskjet ADV 2136  </li>
                          <li class="list-group-item"> <b>Técnico :</b> Geovane </li>
                          <li class="list-group-item"> <b>Status :</b> Fechado </li>
                          <li class="list-group-item"><b>Última atualização :</b> Concluída dia 25/04/2019</li>
                      </ul>
                    <div class="card-body">
                        <button title="Gerenciar OS" class="btn bg-gradient-info" style="color: white;" action="#">
                            <i class="fas fa-list-alt"></i>
                        </button>
                    </div>
                    
            <%-- DIV QUE FECHA A CARD OS --%>
                </div>
                
            <%-- DIV QUE FECHA A COL --%>
            </div>
            
            
            <%-- DIV QUE FECHA A ROW --%>
          </div>
          
            <%--DIV QUE FECHA O BODY CARD PRINCIPAL --%>
        </div>
        
        <%-- Div que fecha a card principal--%>
    </div>    
<%-- Div que fecha o container--%>
</div>
</div>
    
</div>
    
<jsp:include page="Content/Layout/Footer.jsp" />
