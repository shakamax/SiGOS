<%-- 
    
    Document   : index
    Created on : 24/04/2019, 19:48:25
    Author     : 03728827142
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />
 <div class="container">
    <a href="ClienteServlet?acao=listar" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
     <br>
     <br>
     
    <div class="card shadow mb-4">
        <div class="card-header bg-gradient-info">
            <h1 class="h3 mb-4 text-gray-100">Cadastrar Cliente</h1>
        </div>
        <div class="card-body">
            <form class="form-group" method="post" action="ClienteServlet?acao=salvar">
                   <div class="form-row">
                     <div class="col">
                           <label for="nome">Nome:</label>
                           <input type="text" class="form-control" name="nome" id="nome" required value="${cliente.nome}" />
                           <input type="hidden" name="id" id="id" value="${cliente.id}" />
                     </div>
                     <div class="col">
                           <label for="cpf">CPF:</label>
                           <input type="text" class="form-control CPF" required name="cpf" id="cpf" value="${cliente.CPF}">
                     </div>
                     <div class="col">
                           <label for="dataNascimento">Data de Nascimento:</label>
                           <input type="date" class="form-control" value="${cliente.dtNascimento}" name="dataNascimento" id="dataNascimento" >
                     </div>
                   </div>
                   <div class="form-row">
                     <div class="col">
                        <label for="email">Email:</label>
                        <input type="email" required <c:if test="${cliente.status}" > disabled</c:if> class="form-control" name="email" id="email" value="${cliente.email}">
                     </div>
                     <div class="col">
                           <label for="email">Email Alternativo:</label>
                           <input type="email"  class="form-control" name="emailAlt" id="email" value="${cliente.emailAlternativo}">
                     </div>
                   </div>
                   <div class="form-row">
                      <div class="col">
                       <label for="telefone">Telefone:</label>
                       <input type="text" class="form-control telefone" required name="telefone" id="telefone" value="${cliente.telefone}">
                      </div>
<!--                 
                      <div class="col">
                        <label for="dataCadastro">Data de Cadastro:</label>
                        <input type="date" class="form-control" name="dataCadastro" id="dataCadastro" >
                      </div>-->
                     <div class="col">
                       <label for="cep">CEP:</label>
                       <input type="text" class="form-control CEP" required name="cep" id="cep" value="${cliente.CEP}">
                     </div>
                     <div class="col">
                       <label for="uf">UF:</label>
                       <input type="text" class="form-control" required name="uf" id="uf" value="${cliente.estado}">
                     </div>
                   </div>
                   <div class="form-row">
                     <div class="col-6">
                       <label for="logradouro">Logradouro:</label>
                       <input type="text" class="form-control" required name="logradouro" id="logradouro" value="${cliente.logradouro}">
                     </div>
                     <div class="col">
                       <label for="complemento">Complemento:</label>
                       <input type="text" class="form-control" required name="complemento" id="complemento" value="${cliente.complemento}">
                     </div>
                   </div>
                   <div class="form-row">
                     <div class="col">
                       <label for="cidade">Cidade:</label>
                       <input type="text" class="form-control" required name="cidade" id="localidade" value="${cliente.cidade}">
                     </div>
                     <div class="col">
                       <label for="bairro">Bairro:</label>
                       <input type="text" class="form-control" required name="bairro" id="bairro" value="${cliente.bairro}">
                     </div>
                   </div> 
                <br>
                <input type="hidden" value="false" name="status" id="status" />
                
                <button type="submit" class="btn bg-gradient-success float-right" style="color: white;" >
                Confirmar cadastro
                </button>                    
               </form>
        </div>
    </div>
</div>
      
<jsp:include page="Content/Layout/Footer.jsp" />
     
       