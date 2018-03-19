
PrimeFaces.locales['es'] = {
    closeText : 'Cerrar',
    prevText : 'Anterior',
    nextText : 'Siguiente',
    currentText : 'Inicio',
    monthNames : ['Enero', 'Febrero', 'Marzo', 'Abril',
                'Mayo', 'Junio', 'Julio', 'Agosto',
                'Septiembre', 'Octubre', 'Noviembre',
                'Diciembre'],
    monthNamesShort : ['Ene', 'Feb', 'Mar', 'Abr', 'May',
                'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
    dayNames : ['Domingo', 'Lunes', 'Martes', 'Miércoles',
                'Jueves', 'Viernes', 'Sábado'],
    dayNamesShort : ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin : ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
    weekHeader : 'Semana',
    firstDay : 1,
    isRTL : false,
    showMonthAfterYear : false,
    yearSuffix : '',
    timeOnlyTitle : 'Sólo hora',
    timeText : 'Tiempo',
    hourText : 'Hora',
    minuteText : 'Minuto',
    secondText : 'Segundo',
    currentText : 'Fecha actual',
    ampm : false,
    month : 'Mes',
    week : 'Semana',
    day : 'Día',
    allDayText : 'Todo el día'
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
    setTimeout(function() { $('[id$=' + idComponente + ']').hide(1000); }, 10000);
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

