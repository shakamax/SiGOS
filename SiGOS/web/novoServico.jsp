<%-- 
    Document   : novoServico
    Created on : 07/05/2019, 21:11:14
    Author     : 03728827142
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
 
<div class="container">
    <div class="card">
        <div class="card-header bg-gradient-info btn-success"> Cadastrando Novo Serviço </div>
        <div class="card-body">
            <form action="ServicoServlet" class="form-group" method="POST">
                <div class="form-row">
                    <div class="col">
                        <label for="nome"> Nome : </label>
                        <input type="text" name="nome" id="nome" class="form-control" />
                    </div>
                    <div class="col">
                        <label for="valor"> Valor : </label>
                        <input type="text" id="valor" name="valor" class="form-control price" required />
                    </div>
                </div>
                <div class="form-row">
                    <div class="col">
                    <label for="descricao"> Descrição do Serviço </label>
                    <input class="form-control" id="descricao" name="descricao" type="text" />
                    
                    </div>
                </div>
                <br>
                <button class="btn btn-success bg-gradient-success float-right" type="submit" > Salvar </button>
            </form>
            
        </div>
        
    </div>
</div>

    
</div>

<jsp:include page="Content/Layout/Footer.jsp" />
