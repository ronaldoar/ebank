	/**
	 *	Inicializa objetos javascritp
	 */
	$(document).ready(function(){
		validarLogin();
	}); 
	
	
	function validarLogin(){
		$('#btnLogin').click(function(evt){
			var login  = $('#login').val();
			var senha  = $('#senha').val();
			var cSenha = $('#csenha').val();
			
			if(login == '' | senha == '' | cSenha == ''){
				evt.preventDefault();
				$('#msgId').text('Informe os campos login e senha e confirma senha.');
			}else {
				$('#frmLogin').submit();
			}
		});
	}
	
	function validaSenha(){
		var senha  = $('#senha').val();
		var cSenha = $('#csenha').val();
		
		if(senha != cSenha){
			$('#msgId').text('As senhas não coincidem.');
			
		}else if(senha.length < 8 | senha.length > 8){
			$('#msgId').text('As senhas devem ter 8 caracteres.');
			
		}else if(cSenha.length < 8 | cSenha.length > 8){
			$('#msgId').text('As senhas devem ter 8 caracteres.');
		}
	}
