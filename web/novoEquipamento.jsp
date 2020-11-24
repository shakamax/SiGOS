<%-- 
    Document   : novoEquipamento
    Created on : 09/05/2019, 11:59:47
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<jsp:useBean id="cliente" class="br.com.sigos.model.Cliente" scope="session" />
<jsp:useBean id="lista" scope="session" class="br.com.sigos.model.ListaEquipamento" />


<div class="container">
    <a href="EquipamentosServlet?acao=listar&id=${cliente.id}" class="btn btn-primary bg-gradient-warning" title="Retornar a página anterior">
      <i class="fas fa-arrow-circle-left fa-1x "></i> Voltar
    </a>
     <br>
     <br>
    <div class="card shadow mb-4">
        <div class="card-header bg-gradient-info text-gray-100">
            Novo equipamento de ${cliente.nome}
        </div>
        <div class="card-body">
            <form action="EquipamentosServlet?acao=salvar" method="POST">
                    <div class="form-row">
                        <div class="col">
                            <label for="equipamento">Equipamento</label>
                            <input type="text" name="equipamento" required class="form-control" value="${lista.equipamento}">
                            <input name="id" id="id" type="hidden" value="${lista.id}" />
                        </div>
                    </div>
                        
                    <div class="form-row">
                        <div class="col">
                            <label for="observacao">Observação</label>
                            <input text="text" name="observacao"  id="observacao" value="${lista.observacao}" class="form-control">
                        </div>
                        <div class="col">
                            <label for="acessorios">Acessórios</label>
                            <input type="text" name="acessorios" class="form-control" value="${lista.acessório}" >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="defeito">Defeito</label>
                            <textarea class="form-control" name="defeito" id="defeito" rows="3" >${lista.defeito}</textarea>
                         </div>
<!--                     <div class="col">
                            <label for="exemplo">Foto</label>
                            <input  type="file" class="form-control-file" id="exemplo">
                        </div>-->
                    </div>
                <br>
                
                
                <input type="hidden" value="${cliente.id}" name="fk_cliente" />
                <input type="hidden" value="${lista.fk_cliente}" name="fk" />
                
                
                    <button type="submit" class="btn btn-primary bg-gradient-success float-right" > Salvar </button>
            
                    
            </form>
        </div>   
<!--        div card-->
    </div>
        
</div>


</div>
        
        
<jsp:include page="Content/Layout/Footer.jsp" />
