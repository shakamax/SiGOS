<%-- 
    Document   : dadosCliente
    Created on : 06/08/2019, 10:57:06
    Author     : Guilherme
--%>


<jsp:include page="Content/LayoutC/Header.jsp" />
<jsp:include page="Content/LayoutC/Menu.jsp" />
<jsp:useBean id="cliente" scope="session" class="br.com.sigos.model.Cliente" />
 <div class="container">
    <a href="AreaClienteServlet?acao=minhaConta" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
     <br>
     <br>
    <div class="card shadow mb-4">
        <div class="card-header bg-gradient-info">
            <h1 class="h3 mb-4 text-gray-100">Atualizar seus Dados</h1>
        </div>
        <div class="card-body">
            <form class="form-group" method="post" action="AreaClienteServlet?acao=salvarMudancas">
                   <div class="form-row">
                     <div class="col">
                           <label for="nome">Nome:</label>
                           <input type="text" class="form-control" name="nome" id="nome" value="${cliente.nome}" />
                           <input type="hidden" name="id" id="id" value="${cliente.id}" />
                     </div>
                     <div class="col">
                           <label for="cpf">CPF:</label>
                           <input type="text" class="form-control CPF" name="cpf" id="cpf" value="${cliente.CPF}">
                     </div>
                     <div class="col">
                           <label for="dataNascimento">Data de Nascimento:</label>
                           <input type="date" class="form-control" value="${cliente.dtNascimento}" name="dataNascimento" id="dataNascimento" >
                     </div>
                   </div>
                   <div class="form-row">

                     <div class="col">
                            <label for="email">Email Alternativo:</label>
                            <input type="email" class="form-control" name="emailAlt" id="email" value="${cliente.emailAlternativo}">
                     </div>
                      <div class="col">
                            <label for="telefone">Telefone:</label>
                            <input type="text" class="form-control telefone" name="telefone" id="telefone" value="${cliente.telefone}">
                      </div>
                   </div>
                      <br>
                    <div class="card-header" align="center"> Endereço </div>
                    <div class="form-row">
                        <div class="col-2">
                          <label for="cep">CEP:</label>
                          <input type="text" class="form-control CEP" name="cep" id="cep" value="${cliente.CEP}">
                        </div>
                      </div>

                   <div class="form-row">
                    <div class="col-1">
                      <label for="uf">UF:</label>
                      <input type="text" class="form-control" name="uf" id="uf" value="${cliente.estado}">
                    </div>
                     <div class="col-3">
                         
                       <label for="cidade">Cidade:</label>
                       <input type="text" class="form-control" name="cidade" id="localidade" value="${cliente.cidade}">
                     </div>
                     <div class="col">
                       <label for="logradouro">Logradouro:</label>
                       <input type="text" class="form-control" name="logradouro" id="logradouro" value="${cliente.logradouro}">
                     </div>
                   </div>
                   <div class="form-row">
                     <div class="col">
                       <label for="bairro">Bairro:</label>
                       <input type="text" class="form-control" name="bairro" id="bairro" value="${cliente.bairro}">
                     </div>

                     <div class="col">
                       <label for="complemento">Complemento:</label>
                       <input type="text" class="form-control" name="complemento" id="complemento" value="${cliente.complemento}">
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
                
      
<jsp:include page="Content/LayoutC/Footer.jsp" />
     
       
