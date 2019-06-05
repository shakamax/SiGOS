<%-- 
    Document   : Footer
    Created on : 24/04/2019, 20:39:38
    Author     : 03728827142
--%>



    </div>
    <!-- FIM DO CONTEÚDO PRINCIPAL! -->
     <!-- Footer -->
     <div class="wrapper">    
     <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; SiGOS - InfoTech 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->
    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Deseja realmente realizar Logout?</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          <a class="btn btn-primary" href="index.jsp">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  
<!--  <script src="Content/js/jquery.min.js" type="text/javascript"></script>-->
  
  <script src="Content/js/jquery-3.4.1.min.js" type="text/javascript"></script>
  
  <script src="Content/js/bootstrap.bundle.min.js" type="text/javascript"></script>

  <!-- Core plugin JavaScript-->
  <script src="Content/js/jquery.easing.min.js" type="text/javascript"></script>
  
  <!-- Custom scripts for all pages-->
  <script src="Content/js/sb-admin-2.min.js" type="text/javascript"></script>
  
  <!--  JavaScript para Máscaras-->
  <script src="Content/js/jquery.mask.min.js" type="text/javascript"></script>
  
  <script src="Content/js/jquery.priceformat.min.js" type="text/javascript"></script>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js" type="text/javascript"></script>
  
  
<!--  FORMATO PARA PREÇO-->
  <script type="text/javascript">
	// JavaScript
$('.price').priceFormat({
    prefix: '',
    centsSeparator: ',',
    thousandsSeparator: '.'
});
</script>


  <script>
      $(document).ready(function() {
        $('.js-example-basic-multiple').select2();
        });
        
  </script>
  <!-- MÁSCARA TELEFONE -->
<script type="text/javascript">
$(".telefone").mask("(99) 9.9999-9999");
</script>
  <!-- MÁSCARA CEP -->
<script type="text/javascript">
$(".CEP").mask("99999-999");
</script>
<!-- MÁSCARA CPF -->
<script>
    $(document).ready(function () { 
        var $seuCampoCpf = $(".CPF");
        $seuCampoCpf.mask('999.999.999-99');
    });
</script>

<script>
	$('#cep').change(function() {
    	var cep = $(this).val();
    	$.ajax({
        	url: "https://viacep.com.br/ws/" + cep + "/json/",
        	type: 'GET',
        	dataType: 'json',
        	beforeSend: function () {
            	$('#logradouro').val("...");
            	$('#bairro').val("...");
            	$('#localidade').val("...");
            	$('#uf').val("...");
        	},
        	success: function (data) {
            	$('#logradouro').val(data.logradouro);
            	$('#bairro').val(data.bairro);
            	$('#localidade').val(data.localidade);
            	$('#uf').val(data.uf);
        	}
    	});
	});
        
        $('#cliente_i').change(function() {
    	var id_cliente = $(this).val();
    	$.ajax({
        	url: "ordemServicoServlet?acao=mudarCliente&id_cliente=" + id,
        	type: 'POST',
        	dataType: 'json',
        	beforeSend: function () {
            	$('#listaeq').val("0");
                $('#loading').show();
        	},
        	success: function (data) {
                $('#loading').hide();
            	$('#logradouro').val(data.logradouro);
            	$('#bairro').val(data.bairro);
            	$('#localidade').val(data.localidade);
            	$('#uf').val(data.uf);
        	}
    	});
	});
</script>


  
</body>

</html>
