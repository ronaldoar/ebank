
	
	/**
	 Inicializa objetos javascritp
	 */
	$(document).ready(function(){
		validarLogin();
	}); 
	
	
	function validarLogin(){
		$('#btnLogin').click(function(evt){
			var login = $('#login').val();
			var senha = $('#senha').val();
			
			if(login == '' | senha == ''){
				evt.preventDefault();
				$('#messageId').text('Informe os campos login e senha.');
			}else {
				$('#frmLogin').submit();
			}
		});
	}
