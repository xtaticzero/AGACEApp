PrimeFaces.locales['es'] = {
    closeText : 'Cerrar', prevText : 'Anterior', nextText : 'Siguiente', currentText : 'Inicio', monthNames : ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'], monthNamesShort : ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'], dayNames : ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'], dayNamesShort : ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'], dayNamesMin : ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'], weekHeader : 'Semana', firstDay : 1, isRTL : false, showMonthAfterYear : false, yearSuffix : '', timeOnlyTitle : 'Sólo hora', timeText : 'Tiempo', hourText : 'Hora', minuteText : 'Minuto', secondText : 'Segundo', currentText : 'Fecha actual', ampm : false, month : 'Mes', week : 'Semana', day : 'Día', allDayText : 'Todo el día'
};

function start() {
    statusDialog.show();
}

function stop() {
    statusDialog.hide();
}

/*
 * Ejemplo de poner el foco de un componente
 * 'formOrdenes:txtORG'
 * */
function setFocus(idComponente) {
    PrimeFaces.focus(idComponente);
}

function ocultaMsgAgace(idComponente) {
    setTimeout(function () {
        $('[id$=' + idComponente + ']').hide(1000);
    },
10000);
}

function ocultaComponente(idComponente) {
    $('[id$=' + idComponente + ']').hide();
}

/*
 * Para descargar un archivo
 * */
function start() {
    PF('statusDialog').show();
}

function stop() {
    PF('statusDialog').hide();
}

/**
 * FIEL Widget
 */
var objetResponse;

function cargaWidget() {
    console.log('cargaWidget ... ');
    var timer = setInterval(function () {
        console.log('No se ha cargado el Widget...');
        if (document.getElementById('btnEnviarForm') !== null) {
            console.log('Se carg� el Widget de Firmado');
            //A�adirle un evento               
            document.getElementById('btnEnviarForm').onclick = function () {
                btnEnviarFIELOnClick()
            };
            clearTimeout(timer);
        }
    },
200);
}
 function getMessage(error,detalle){

    var pmessage = '<div id="jqueryMessages" class="ui-messages ui-widget" aria-live="polite" data-global="false" data-summary="data-summary" data-detail="data-detail" data-severity="all,error" data-redisplay="true">'
                +'<div class="ui-messages-error ui-corner-all">'
                +'<a href="#" class="ui-messages-close" onclick="$(this).parent().slideUp();return false;">'
                +'<span class="ui-icon ui-icon-close"><\/span><\/a>'
                +'<span class="ui-messages-error-icon"><\/span>'
                +'<ul>'
		 +'<li>'			
		   +'<span class="ui-messages-error-summary">'+error+'<\/span>'
                   +'<span class="ui-messages-error-detail">'+detalle+'<\/span>'
                 +'<\/li>'
                +'<\/ul>'	
		+'<\/div>'	
            +'<\/div>';
            
            return pmessage;

}
/**
 * M�todo que maneja el evento onClick del bot�n enviar del widget FIEL
 * Se debe de definir obligatorio
 *
 * @returns {undefined}
 */

function btnEnviarFIELOnClick() {
    /*console.log('Entro al hacer click en el boton de enviar');*/
    start();
    var cadena = $('#cadena').val();
    var rfcSession = $('#rfc').val();
    //M�todo que FIRMA con la FIEL en el widget
    //Se debe de invocar obligatorio            
    generaFirmaIndividual([{cadenaOriginal : cadena}], //Par�metro 1: Cadena original
    {
        validarRFCSession : rfcSession 
    },
    //Par�metro 2: RFC sustituir por rfcSession
    function (error, resultado) {
        //Par�metro 3: Funci�n callback. Se ejecuta al terminar el firmado   
        // �xito 0               
        if (error === 0) {
            finalizo = true;
            console.log(error);                                    
            $('#cadena_original').val(resultado[0].cadenaOriginalGenerada);    
            $('#cadena_firmada').val(resultado[0].firmaDigital);    
        }else{         
            if(error === 22 || error === 25){                
                $("#msg").html(getMessage('Error','Verifique su contrase&ntildea'));                                                                                                           
            }else{
                $("#msg").html(getMessage('Error',resultado.error));                                                 
            }
            finalizo = false;
        }    
        console.log(finalizo);
        firma();
        stop();
    });

}

function validaNumeros(e){
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum === 0)||(keynum === 8) || (keynum === 9)){
        return true;
    }

    return /\d/.test(String.fromCharCode(keynum));
}

function validaAcentos(e) {
    alert('Llege');
    //var tecla = window.event ? window.event.keyCode : e.which;
    var tecla = (document.all) ? e.keyCode : e.which;
    alert('la tecla es :: ' + tecla);
    var minAcentos = (tecla >= 160 && tecla <= 163) || tecla == 130; //minusculas con acentos
    var mayAcentos = tecla == 181 || tecla == 144 || tecla == 214 || tecla == 224 || tecla == 233; //mayusculas con acentos
//    if (minAcentos || mayAcentos) {
//        alert('Entre');
//        return false;
//    }
}

function validaAlfaNumericos(e){
    tecla = (document.all) ? e.keyCode : e.which;
    patron =/[A-Za-z]/; //letras
    te = String.fromCharCode(tecla);
    noimpr = (tecla >= 0 && tecla <= 32); //caracteres no imprimibles
    enie = (tecla == 164 || tecla == 165) // Ã±
    return patron.test(te) || justNumbers(e) || noimpr || enie;
}
    
function validaAlfaNumericosConAcentos(e){
    tecla = (document.all) ? e.keyCode : e.which;
    minAcentos = (tecla >= 160 && tecla <= 163) || tecla == 130; //minusculas con acentos
    mayAcentos = tecla == 181 || tecla == 144 || tecla == 214 || tecla == 224 || tecla == 233; //mayusculas con acentos
    comaPunto = tecla == 40 || tecla == 41 || (tecla >= 44 && tecla <= 46) || tecla == 58 || tecla == 59;
    espacio = tecla == 160 || tecla == 127 || tecla == 32;

    return validaAlfaNumericos(e) || mayAcentos || minAcentos ||comaPunto || espacio;
}

function justNumbers(e) {
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum === 0) || (keynum === 8) || (keynum === 9)) {
        return true;
    }
    return /\d/.test(String.fromCharCode(keynum));
}

  