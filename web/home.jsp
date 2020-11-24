<%-- 
    
    Document   : index
    Created on : 24/04/2019, 19:48:25
    Author     : 03728827142
--%>
<jsp:useBean id="rd" scope="session" class="br.com.sigos.model.relatorioDash" />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <h4 class="small font-weight-bold">O.S Abertas <span class="float-right"><fmt:formatNumber value="${rd.porcAbertas}" maxFractionDigits="0" />%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-warning" role="progressbar" style="width: ${rd.porcAbertas}%" aria-valuenow="${rd.qtdAbertas}" aria-valuemin="0" aria-valuemax="${rd.qtdMax}"></div>
                  </div>
                    <h4 class="small font-weight-bold">O.S canceladas <span class="float-right"><fmt:formatNumber value="${rd.porcCanceladas}" maxFractionDigits="0" />%</span></h4>
                  <div class="progress mb-4">
                      <div class="progress-bar bg-danger" role="progressbar" style="width: ${rd.porcCanceladas}%" aria-valuenow="${rd.qtdCanceladas}" aria-valuemin="0" aria-valuemax="${rd.qtdMax}"></div>
                  </div>
                      <h4 class="small font-weight-bold">O.S Concluídas <span class="float-right"><fmt:formatNumber value="${rd.porcFechadas}" maxFractionDigits="0" />%</span></h4>
                  <div class="progress">
                      <div class="progress-bar bg-success" role="progressbar" style="width: ${rd.porcFechadas}%" aria-valuenow="${rd.qtdFechadas}" aria-valuemin="0" aria-valuemax="${rd.qtdMax}"></div>
                  </div>
                  
                </div>
              </div>
            
    </div>
</div>
    
</div>
</div>

<jsp:include page="Content/Layout/Footer.jsp" />
     
       