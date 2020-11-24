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
          <h5 class="modal-title" id="exampleModalLabel">Pronto para sair?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Deseja realmente realizar Logout?</div>
        <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-primary" href="LoginServlet?acao=logout">Logout</a>
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
  <script src="Content/js/select2.min.js" type="text/javascript"></script>
  
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
        
        $('#listaeq').change(function() {
    	var id = $(this).val();
    	$.ajax({
        	url: "ordemServicoServlet?acao=mudarLista&id=" + id,
        	type: 'GET',
        	//dataType: 'json',
        	beforeSend: function () {
                $('#loading').show();
                $('#defeito').hide();
        	},
        	success: function (data) {
                $('#loading').hide();
                $('#defeito').show();
                $('#defeito').val(data);
        	}
    	});
	});
        
        function validarSenha() {
            var senha = FormSenha.senha.value;
            var senha2 = FormSenha.senha2.value;
            
            if(senha == "" || senha.length < 5){
                alert('Senha deve conter pelo menos 5 caracteres.');
                FormSenha.senha.focus();
                return false;
            }
            if(senha2 == "" || senha2.length < 5){
                alert('Senha deve conter pelo menos 5 caracteres.');
                FormSenha.senha2.focus();
                return false;
            }
            if(senha != senha2){
                alert('Senhas estão diferentes, por favor verifique sua senha.');
                FormSenha.senha2.focus();
                $('#conf').show();
                return false;
            }
            
         }
         
        function validarEmail() {
            var email = FormEmail.email.value;
            var email2 = FormEmail.email2.value;
            
            if(email == ""){
                alert('Por favor, coloque seu e-mail.');
                FormEmail.senha.focus();
                return false;
            }
            if(email2 == ""){
                alert('Por favor, confirme seu e-mail.');
                FormEmail.senha2.focus();
                return false;
            }
            if(email != email2){
                alert('Confirmação de e-mail falhou, por favor tente novamente.');
                FormEmail.senha2.focus();
                $('#conf').show();
                return false;
            }
            
         }
         
</script>
<script>
    	setTimeout(function(){
            $('#alerta').fadeOut(2500);
	}, 5000);
</script>
<script>
    function checarClick(){
        if(document.getElementById("check1").checked==true){
            $('#dtab').show();
            $('#dtfc').hide();
            busca.dataAbertura.focus();
        }
        if(document.getElementById("check2").checked==true){
            $('#dtab').hide();
            $('#dtfc').show();
            busca.dataAbertura.focus();
        }
        if(document.getElementById("check3").checked==true){
            $('#dtab').hide();
            $('#dtfc').hide();
            busca.dataAbertura.focus();
        }
    }
</script>
  
</body>

</html>
