<%-- 
    Document   : novoEquipamento
    Created on : 09/05/2019, 11:59:47
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<% String cliente = request.getParameter("cliente"); %>

        
<div class="container">
    <div class="card shadow mb-4">
        <div class="card-header bg-gradient-info text-gray-100">
            Novo equipamento de <% out.print(cliente); %>
        </div>
        <div class="card-body">
            <form action="EquipamentosServlet" method="POST">
                    <div class="form-row">
                        <div class="col">
                            <label for="equipamento">Equipamento</label>
                            <input type="text" name="equipamento" class="form-control">
                        </div>
                    </div>
                        
                    <div class="form-row">
                        <div class="col">
                            <label for="observacao">Observação</label>
                            <input text="text" name="observacao" class="form-control">
                        </div>
                        <div class="col">
                            <label for="acessorios">Acessórios</label>
                            <input type="text" name="acessorios" class="form-control">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="defeito">Defeito</label>
                            <textarea class="form-control" id="defeito" rows="3"></textarea>
                         </div>
<!--                        <div class="col">
                            <label for="exemplo">Foto</label>
                            <input  type="file" class="form-control-file" id="exemplo">
                        </div>-->
                    </div>
                <br>
                
                
                <input type="hidden" value="<% out.print(cliente); %>" name="cliente" />
                
                
                    <button type="submit" class="btn btn-primary bg-gradient-success float-right" >Enviar</button>
            
                    
            </form>
        </div>   
<!--        div card-->
    </div>
        
</div>


</div>
        
        
<jsp:include page="Content/Layout/Footer.jsp" />
