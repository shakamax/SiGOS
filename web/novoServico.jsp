<%-- 
    Document   : novoServico
    Created on : 07/05/2019, 21:11:14
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="servico" scope="session" class="br.com.sigos.model.Servico" />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <a href="ServicoServlet?acao=listar" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
    <br>
    <br>
    <div class="card">
        <div class="card-header bg-gradient-info btn-success"> Cadastrando Novo Serviço </div>
        <div class="card-body">
            <form action="ServicoServlet?acao=salvar" class="form-group" method="POST">
                <div class="form-row">
                    <input name="id" id="id" type="hidden" value="${servico.ID}" />
                    <div class="col">
                        <label for="nome"> Nome : </label>
                        <input type="text" name="nome" id="nome" required value="${servico.nome}" class="form-control" />
                        
                    </div>
                    <div class="col">
                        <label for="valor"> Valor : </label>
                        <input type="text" id="valor" name="valor" required class="form-control price" value="<fmt:formatNumber value="${servico.valor}" minFractionDigits="2" /> " required />
                               
                    </div>
                </div>
                <div class="form-row">
                    <div class="col">
                    <label for="descricao"> Descrição do Serviço </label>
                    <input class="form-control" id="descricao" value="${servico.descricao}" name="descricao" type="text" />
                    
                    </div>
                </div>
                <br>
                <button class="btn btn-success bg-gradient-success float-right" type="submit" > Salvar </button>
            </form>
            
        </div>
        
    </div>
</div>

    

<jsp:include page="Content/Layout/Footer.jsp" />
