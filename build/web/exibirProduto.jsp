<%-- 
    Document   : exibirProduto
    Created on : 29/05/2019, 12:51:37
    Author     : Guilherme
--%>

<jsp:include page="Content/Layout/Header.jsp" />
<jsp:include page="Content/Layout/Menu.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:useBean id="categoria" class="br.com.sigos.model.Categoria" scope="session" />
<jsp:useBean id="Produto" class="br.com.sigos.model.Produto" scope="session" />


<div class="container">
 
<div class="card shadow mb-4"> 
    <div class="card-header bg-gradient-info">
        <h1 class="h3 mb-4 text-gray-100">${Produto.nome} <spam class="float-right"> #${Produto.ID} </spam></h1>
    </div>
    
    <div class="card-body">
    <form class="form-group" name="cadastro" method="post" action="ProdutoServlet?acao=editar">
     <div class="form-row"> 
         <input type="hidden" name="id" id="id" value="${Produto.ID}" />
        <div class="col">   
            <label for="codigo"> <b>Código de barra </b> </label> 
            <input  type="text" name="codigo" class="form-control" value="${Produto.codigo}" disabled >
        </div>
<!--        <div class="col">
            <label for="nome"> <b> Nome </b></label>
            <input type="text" name="nome" class="form-control" value="${Produto.nome}" > 
        </div>-->
     </div>
     <div class="form-row">
        <div class="col-6">
            <div class="form-group">
                <label for="localizacao"> <b> Localização </b></label>
                <input type="text" name="localizacao" class="form-control" value="${Produto.localizacao}" disabled > 
            </div>
        </div>
            <div class="col">
                <label for="cat"><b> Categorias </b></label>
                <select disabled class="js-example-basic-multiple form-control" id="cat" name="cat[]" multiple="multiple">
                    
                    <%--<c:set var="contador" value="0" />--%>   
                    <c:forEach var="c" items="${categorias}">
                        
                        <option value="${c.ID}"  
                        <c:if test = "${fn:contains(c.produtos, Produto.ID)}">
                            selected="selected"
                        </c:if>        
                        > ${c.descricao} </option>
                        
                        <%--<c:set var="contador" value="${contador + 1}" /> --%>
                    </c:forEach>
                        
                        
                        
                 </select>
            </div>
<!--            <div class="col-1">
                 <button type="button" data-toggle="modal" data-target="#modalCategoria" class="btn btn-info bg-gradient-success" title="Novo Serviço?" style="margin-top: 40%" > 
                   <i class="fas fa-plus-square"> </i></button>
            </div>  
                 -->
     </div>
     <div class="form-row">

         <div class="col">
            <label> <b> Quantidade : </b></label>
            <input type="number" name="quantidade" class="form-control" value="${Produto.quantidade}" disabled required>
         </div>
         <div class="col">
             <label><b>Valor R$ : </b></label>
             <input type="text" name="valor" class="form-control price" value="<fmt:formatNumber value="${Produto.valor}" minFractionDigits="2" />" disabled required>
                
         </div>
     </div>
        <div class="form-row">
            <div class="col">
               <label for="descricao"><b>Descrição </b></label>
               <input type="text" name="descricao" id="descricao" class="form-control" value="${Produto.descricao}" disabled >
            </div>
        </div>
        <br>
            
        <div class="row">
            <div class="col-5"> &nbsp; </div>
            <div class="col-6">
                <c:if test="${user.funcao == 'Administrador'}">    
                <button type="submit" class="btn btn-primary bg-gradient-warning col-3">Editar</button>
                </c:if>
            </div>
            <div class="col-5"> &nbsp; </div>
        </div>
    </form>
        
   </div>   
</div>
    
</div>

<!--<div class="modal fade" id="modalCategoria" tabindex="-1" role="dialog" aria-labelledby="modalCategoria" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Nova Categoria</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <form action="ProdutoServlet?acao=novaCat" method="post">
          <div class="form-group">
            <label for="categoria.Descricao" class="col-form-label"> Descrição : </label>
            <input class="form-control" type="text" name="descricao" id="descricao" />
          </div>
          
      </div>
      <div class="modal-footer">
          <button type="button" class="btn bg-gradient-warning" data-dismiss="modal" style="color: white;">Cancelar</button>
        <button type="submit" class="btn bg-gradient-success" style="color: white;">Salvar</button>
        </form>
      </div>
    </div>
  </div>
</div>   -->

<jsp:include page="Content/Layout/Footer.jsp" />
     
       