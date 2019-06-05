<%-- 
    
    Document   : index
    Created on : 24/04/2019, 19:48:25
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="funcionario" scope="session" class="br.com.sigos.model.Funcionario" />
 <div class="container">
     
     
    <div class="card shadow mb-4">
        <div class="card-header bg-gradient-info">
            <h1 class="h3 mb-4 text-gray-100">Cadastrar Funcionário</h1>
        </div>
        <div class="card-body">
            <form class="form-group" method="post" action="FuncionarioServlet?acao=salvar">
                <div class="form-row">
                      <div class="col-7">
                           <label for="nome">Nome:</label>
                           <input type="text" class="form-control" name="nome" value="${funcionario.nome}" id="nome" />
                           <input type="hidden" name="id" value="${funcionario.id}" id="id" />
                      </div>
                    <div class="col">
                        <label for="funcao">Função:</label>
                        <select class="form-control" id="funcao" name="funcao">
                             <option value="${funcionario.funcao}">Selecione</option>
                                <option value="Atendente" <c:if test="${funcionario.funcao == 'Atendente'}"  >
                                     selected="selected" </c:if>
                                     > Atendente </option>
                                <option value="Técnico"  <c:if test="${funcionario.funcao == 'Técnico'}" >
                                     selected="selected" </c:if>
                                     >Técnico</option>
                             </select>
                      </div>
                </div>
                  <div class="form-row">
                     <div class="col-7">
                           <label for="email">Email:</label>
                           <input type="email" class="form-control" name="email" value="${funcionario.email}" id="email">
                    </div>
                      <div class="col">
                            <label for="senha">Senha:</label>
                            <input type="password" class="form-control" name="senha" id="senha">
                      </div>
                  </div>
                <br>
                <button type="submit" class="btn bg-gradient-success float-right" style="color: white;" >
                Confirmar cadastro
                </button>
                
            </form>
        </div>
    </div>
</div>
      
<jsp:include page="Content/Layout/Footer.jsp" />
     
       