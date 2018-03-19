var numFirmas = 0;
var mandaError = true;

function firmarCad() {
	mandaError = true;
	$('#btnFirmar').disabled = true;
	PF('statusDialog').show();
	var cadenasOriginales = $("#panelFirma input:hidden");	
	
	for (var i = 0; i < cadenasOriginales.length; i++) {
		//si en el id contiene la palabra cadena
		if(cadenasOriginales[i].id.indexOf("cadena") > -1){
			numFirmas++;
			generaFirmaIndividual(
					[ {
						cadenaOriginal : cadenasOriginales[i].value
					} ],
					{
						validarRFCSession : $("#txtRFC").val()
					},
					function(error, resultado) {									
						//Se ejecuta al terminar el firmado
						PF('statusDialog').show();
		                console.log("error: " + error); 
		                console.log("resultado " + resultado.error);
		                numFirmas--;
		                if (error === 0 || error === undefined) {
			                if (resultado.length > 0) {
			                	var cadenasOriginales = $("#panelFirma input:hidden");
			                	var cont = 0;
			                	for (var i = 0; i < cadenasOriginales.length; i++) {
			                		if(cadenasOriginales[i].id.indexOf("cadena")>-1){
				                		if($("#cadena"+cont).val()==resultado[0].cadenaOriginal){
				                			$("#firma"+cont++).val(resultado[0].firmaDigital);
				                			break;
				                		}else{
				                			cont++;
				                		}
			                		}
			                	}
			                    
			                }
		                    if (numFirmas==0){          			
		            			abrirAction();
		            		}
		                }else{
			                var error = String(resultado.error);
			                if (error != null && mandaError){
			                	if (error === "22" || error === "25") {
			                		error = "Verifica tu contraseña";
			                	}
			                	if (error === "28") {
			                		error = "El certificado y la clave deben pertenecer al mismo RFC";
			                	}
			                	PF('growlWV').renderMessage({"summary":"Error",
		                             "detail":error,
		                             "severity":"error"});
			                	mandaError = false;
			                	PF('statusDialog').hide();
			                }
		                }
					});
		}
	}				
}

function abrirAction() {	
	$("#rfcFirma").val($("#txtRFC").val());
	$('#procesa').click();
}