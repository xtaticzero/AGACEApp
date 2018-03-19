package mx.gob.sat.siat.feagace.modelo.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.RutaUtil;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;

public final class Constantes {

    /**
     * Este atributo corresponde al path dónde están almacenados los archivos.
     */
    public static final String DIRECTORIO_ARCHIVOS_FEAGACE = RutaUtil.getOrigenRuta().concat("/fece/archivosCargados/");

    /**
     * Este atributo corresponde al path donde estan almacenados los archivos.
     */
    public static final String DIRECTORIO_ARCHIVOS_ORDENES = RutaUtil.getOrigenRuta().concat("/fece/archivosCargadosOrdenes/");

    /**
     * Este atributo corresponde al path dónde están almacenados los archivos de los insumos.
     */
    public static final String DIRECTORIO_ARCHIVOS_INSUMOS_PROPUESTAS_FEAGACE = RutaUtil.getOrigenRuta().concat("/fece/archivosInsumosPropuestas/");

    /**
     * Este atributo corresponde al path donde estan almacenados los archivos de las propuestas.
     */
    public static final String DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE = RutaUtil.getOrigenRuta().concat("/fece/archivosExpedientesPropuestas/");

    public static final String DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE_DOCUMENTO_ORDEN = "/documentoOrden/";

    /**
     * Este atributo corresponde al path donde estan almacenados los archivos de las propuestas.
     */
    public static final String DIRECTORIO_RECHAZO_ANEXO_OFICIO = RutaUtil.getOrigenRuta().concat("/fece/archivosCargadosOficio/");
    
    
    public static final String DIRECTORIO_ANEXOS_ACTO_ADM = RutaUtil.getOrigenRuta().concat("/anexos/");

    public static final String DIRECTORIO_TEMPORAL_NYV = RutaUtil.getOrigenRuta().concat("/archivosTempNyV/");    
    /**
     * Este atributo corresponde al tipo de extensión del archivo .DOCX
     */
    public static final String ARCHIVO_WORD_DESPUES_2007 = ".docx";

    /**
     * Este atributo corresponde al tipo de extensión del archivo .XLSX
     */
    public static final String ARCHIVO_EXCEL_DESPUES_2007 = ".xlsx";

    /**
     * Este atributo corresponde al tipo de extension del archivo .PDF
     */
    public static final String ARCHIVO_PDF = ".pdf";

    /**
     * Este atributo corresponde al tipo de extension del archivo .ZIP
     */
    public static final String ARCHIVO_ZIP = ".zip";
    
    /**
     * Este atributo corresponde al tipo de extensión del archivo .PDF
     */
    public static final int PLAZO_MAXIMO_CARGA_DOCUMENTOS_CONTRIBUYENTE = 17;

    public static final int PLAZO_MAXIMO_CARGA_DOCUMENTOS_CONTRIBUYENTE_REE = 15;

    /* Constantes para operaciones generales en BD */
    public static final String INSERT_INTO = "insert.into";
    public static final String UPDATE_TABLE = "update.table";
    public static final String SELECT_NEXTVAL_FROM_SEQUENCE = "select.nextval.from.sequence";
    public static final String SECUENCIA = ".secuencia";

    /* Constantes utilizadas en operaciones de concatenacion */
    public static final String ESPACIO = " ";
    public static final String COMA_ESPACIO = ", ";
    public static final String SET = "set";
    public static final String DOS_PUNTOS_ESPACIO = ": ";
    public static final String CADENA_VACIA = "";
    public static final Object CADENA_NULA = "null";
    public static final String PIPE = " | ";
    public static final String INTERROGACION = "?";
    public static final String PARENTESIS_ABRE = "(";
    public static final String PARENTESIS_CIERRE = ")";
    public static final String IGUAL = "=";
    public static final String DIAGONAL = "/";
    public static final CharSequence GUION_BAJO = "_";
    public static final String SALTO_DE_LINEA = "\n";

    /* Constantes para suprimir advertencias del compilador */
    public static final String UNUSED = "unused";
    public static final String UNCHECKED = "unchecked";
    public static final String ROWTYPES = "rowtypes";

    /* Constantes usadas en ManagedBean Contribuyente */
    public static final String CONSULTA_ORDENES = "Fallo la consulta de las ordenes: ";
    public static final String ARCHIVO_CARGADO = "Archivo cargado exitosamente";

    /* Contantes usadas en ManagedBean empleado */
    public static final String RFC_FORM_ORDENES = "formOrdenes:txtRFC";
    public static final String EXITO_GUARDADO = "exito.guardado";
    public static final String SELECCIONE_DOCUMENTO = "Debe seleccionar por lo menos un documento, verifique por favor.";

    public static final String MULTA = "multa";
    public static final String DOC_BAJA_PADRON = "bajaPadron";
    public static final String REQUERIMIENTO = "requerimiento";

    public static final String DOC_AVISO_CONTRIBUYENTE = "contribuyenteAuditado";
    public static final String DOC_COMPULSA_TERCEROS = "compulsaTercero";
    public static final String DOC_OTRAS_AUTORIDADES = "otrasAutoridades";
    public static final String DOC_RESOLUCION_DEFINITIVA = "resolucionDefinitiva";
    public static final String DOC_COMPULSA_INTERNACIONAL = "compulsaInternacional";

    public static final String DOC_CAMBIO_METODO = "cambioMetodo";
    public static final String DOC_ORDEN_CAMBIO_METODO = "ordenCambioMetodo";

    public static final String LABEL_REQUERIMIENTO = "Requerimiento";

    public static final String VERIFIQUE = "Verifique por favor";
    public static final String ADJUNTAR_OFICIO = "Debe adjuntar una archivo de oficio";
    public static final String ADJUNTAR_OFICIO_INFORMACION_COMPLEMENTARIA = "Debe adjuntar un oficio de Información Complementaria";
    public static final String ADJUNTAR_OFICIO_OBSERVACIONES = "Debe adjuntar un oficio de Observaciones";
    public static final String ADJUNTAR_OFICIO_CANCELACION = "Debe adjuntar un oficio de Cancelación de la Orden";
    public static final String ADJUNTAR_OFICIO_CONCLUSION = "Debe adjuntar un oficio de Conclusión";
    public static final String ADJUNTAR_OFICIO_MULTA = "Debe adjuntar un oficio de Multa";
    public static final String ADJUNTAR_OFICIO_BAJA_PADRON = "Debe adjuntar un oficio de Baja del Padrón";
    public static final String ADJUNTAR_OFICIO_REQUERIMIENTOS = "Debe adjuntar un oficio de Requerimientos";
    public static final String ADJUNTAR_OFICIO_AMPLIACION = "Debe adjuntar un oficio de Ampliación de Plazo";
    public static final String ADJUNTAR_OFICIO_RESOLUCION_DEFINITIVA = "Debe adjuntar un oficio de Resolución Definitiva";
    public static final String ADJUNTAR_OFICIO_CAMBIO_METODO = "Debe adjuntar un oficio de Cambio de M\u00e9 todo";
    public static final String ADJUNTAR_OFICIO_ORDEN_CAMBIO_METODO = "Debe adjuntar un oficio de Orden de Cambio de M\u00e9 todo";

    public static final String SEGUNDA_CARTA_INVITACION = "Segunda Carta Invitacion";
    public static final String SEGUNDO_REQUERIMIENTO = "Segundo Requerimiento";
    public static final String RESOLUCION_DEFINITIVA = "Resolucion Definitiva";
    public static final String COMPULSA_TERCEROS = "Compulsa con Terceros";
    public static final String COMPULSA_INTERNACIONAL = "Compulsa Internacionacional";
    public static final String CONCLUSION = "Conclusión";
    public static final String AMPLIACION_PLAZO = "Ampliación de Plazo";
    public static final String INFORMACION_COMPLEMENTARIA = "Informacion Complementaria";
    public static final String OBSERVACIONES = "Observaciones";
    public static final String CANCELACION_ORDEN = "Cancelacion de la Orden";
    public static final String BAJA_PADRON = "Baja del Padrón";
    public static final String OFICIO_MULTA = "oficio de Multa";
    public static final String SOLICITUD_AUTORIDADES = "Solicitud de Otras Autoridades";
    public static final String AVISO_CONTRIBUYENTE_AUDITADO = "Aviso al Contribuyente Auditado";

    public static final String FALLO_CONSULTA = "Fallo consulta";

    public static final String CAMPO_OBLIGATORIO = "*Campo obligatorio";

    public static final String NO_ENCONTRO_CONTRIBUYENTE = "No se encontr\u00f3 el Contribuyente";
    public static final String FORM_HOMOCLAVES = "formOrdenes:txtHomoClaves";
    public static final String LLENAR_CAMPOS_REQUERIDOS = "Favor de llenar los campos requeridos. Archivo no cargado.";
    public static final String SELECCIONAR_RESOLUCION_DEFINITIVA = "Debe seleccionar un oficio de resolucion definitiva";

    public static final String DEBE_SELECCIONAR_PROMOCION = "error.debe.seleccionar.archivo.promocion";

    public static final String NO_CONTENER_CARACTERES_ESPECIALES_RFC = "*El RFC no debe contener caracteres especiales";
    public static final String ULTIMO_DIGITO_NUMERICO_A = "El último digito debe ser num\u00e9 rico o la letra A";
    public static final String INFO = "INFO";
    public static final String IDC_IDENTIFICACION = "idc.secciones.identificacion";
    public static final String IDC_UBICACION = "idc.secciones.ubicacion";

    public static final String INYECTA_MENSAJE = "${mensaje}";
    public static final String INYECTA_METODO = "${metodo}";
    public static final String CORREO_DEVOLUCIONES_COMPENSACIONES = "dev.devoluciones_y_compensaciones@sat.gob.mx";
    public static final String ASTERISCOS = "********************: ";
    public static final String LOGO_SHCP = "<logoSHCP>";
    public static final String CONTENT_ID = "Content-ID";

    public static final String STATIC_ACCESS = "static-access";
    public static final String PUNTO = ".";
    public static final String PROPIEDADES_PARAMETRO_INDEXADO = "{i}";
    public static final int INDICE_CERO = 0;
    public static final int INDICE_UNO = 1;
    public static final int INDICE_DOS = 2;
    public static final int INDICE_MENOS_UNO = -1;
    public static final String MAIL_REMITENTE = "mail.remitente";
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String MAIL_TRANSPORT_PROT = "mail.transport.protocol";
    public static final String MAIL_SMTP_MAIL_SENDER = "mail.smtp.mail.sender";
    public static final String MAIL_SMTP_USER = "mail.smtp.user";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_PASSWORD = "mail.password";
    public static final String MAIL_DEBUG = "mail.debug";
    public static final String CORREO_PANTILLA_1 = "correo1.html";
    public static final String CORREO_PANTILLA_2 = "correo2.html";
    public static final String CORREO_PANTILLA_3 = "correo3.html";
    public static final String CORREO_PANTILLA_4 = "correo4.html";
    public static final String CORREO_PANTILLA_5 = "correo5.html";
    public static final String CORREO_PANTILLA_6 = "correo6.html";
    public static final String CORREO_PANTILLA_INSUMOS = "correoInsumos.html";
    public static final String CORREO_PANTILLA_PROPUESTA = "correoPropuesta.html";

    public static final String CORREO_TITULO_TIPO_1 = "Aviso de Carga de Documentos para el Firmante.";
    public static final String CORREO_TITULO_TIPO_3 = "Aviso para Seguimiento del Auditor.";
    public static final String CORREO_TITULO_TIPO_4 = "Aviso del usuario que fue registrado en el Sistema para poder cargar pruebas y alegatos.";
    public static final String CORREO_TITULO_TIPO_6 = "Aviso de Solicitud.";
    public static final String CORREO_TITULO_TIPO_7_8_9_10_11_12_15_16 = "Aviso del env&iacute;o de Documentos.";
    public static final String CORREO_TITULO_TIPO_14 = "Aviso de cambio de m&eacute;todo.";

    public static final String CORREO_TITULO_CREAR_INSUMOS = "Aviso de Asignaci&oacute;n de Insumo.";
    public static final String CORREO_TITULO_RECHAZO_INSUMO = "Aviso de Rechazo de Insumo.";
    public static final String CORREO_TITULO_RETROALIMENTACION_INSUMO = "Aviso de Solicitud de Retroalimentaci&oacute;n de Insumo.";
    public static final String CORREO_TITULO_RETROALIMENTACION_INSUMO_ATENDIDA = "Aviso de Retroalimentaci&oacute;n de Insumo Atendida.";
    public static final String CORREO_TITULO_SEGUIMIENTO_INSUMO = "Aviso de Seguimiento de Insumo.";
    public static final String CORREO_TITULO_ASIGNACION_PROPUESTA = "Aviso de Asignación de Propuestas.";

    public static final String CORREO_MENSAJE_CREAR_INSUMOS = "Por medio del presente se le informa que la ACIACE ha registrado un insumo y le ha sido asignado para su "
            + "eventual programaci&oacute;n a trav&eacute;s del aplicativo de <strong>AUDITOR&Iacute;AS ELECTR&Oacute;NICAS DE COMERCIO EXTERIOR</strong> "
            + "correspondiente al N&uacute;mero de Oficio arriba citado.";
    public static final String CORREO_MENSAJE_RECHAZO_INSUMO = "Por medio del presente se le informa que ha sido rechazado el insumo registrado con n&uacute;mero de oficio antes mencionado.";
    public static final String CORREO_MENSAJE_RETROALIMENTACION_INSUMO = "Por medio del presente se le informa que ha sido solicitada la retroalimentaci&oacute;n del insumo registrado con el n&uacute;mero de oficio antes mencionado.";
    public static final String CORREO_MENSAJE_RETROALIMENTACION_INSUMO_ATENDIDA = "Por medio del presente se le informa que la ACIACE ha atendido la solicitud de retroalimentaci&oacute;n del insumo respecto del RFC antes mencionado.";
    public static final String CORREO_MENSAJE_SEGUIMIENTO_INSUMO = "Por medio del presente se le informa que le ha sido asignado un insumo procedente de la ACIACE para su seguimiento.";
    public static final String CORREO_MENSAJE_ASIGNACION_PROPUESTA = "Por medio del presente se le informa que le ha sido programada una propuesta de fiscalizaci&oacute;n,identificada con el n&uacute;mero ${numId} para su atenci&oacute;n.";

    public static final String CORREO_MENSAJE_TIPO_1 = "Por medio del presente se le informa que tiene un documento asignado para firmar en el aplicativo de <strong>"
            + "FISCALIZACI&Oacute;N ELECTR&Oacute;NICA</strong> correspondiente al N&uacute;mero de Orden arriba citado.";
    public static final String CORREO_MENSAJE_TIPO_2 = "Por medio del presente se le informa que el documento del <strong>M&eacute;todo: </strong> ${metodo}, fue rechazado por el firmante.";
    public static final String CORREO_MENSAJE_TIPO_3 = "Mediante el presente se le informa que el documento mencionado anteriormente correspondiente al <strong>M&eacute;todo"
            + "</strong> ${metodo} fue aceptado por el firmante y enviado para su notificaci&oacute;n al contribuyente, por tal motivo, "
            + "debera de dar seguimiento a la notificaci&oacute;n del mismo.";
    public static final String CORREO_MENSAJE_TIPO_4 = "Por medio del presente se le informa que Usted ha quedado Registrado en el Sistema de Fiscalizaci&oacute;n Electr&oacute;nica, "
            + "para actuar en representaci&oacute;n del Contribuyente arriba se&ntilde;alado como Apoderado Legal.";
    public static final String CORREO_MENSAJE_TIPO_6 = "Por medio del presente se le informa que el contribuyente arriba mencionado ha presentado un escrito relacionado "
            + "con: <%tipoEscrito%>, la cual se encuentra disponible en el m&oacute;dulo de seguimiento de la orden correspondiente, "
            + "lo anterior para llevar cabo su an&aacute;lisis y en su caso la aprobaci&oacute;n o rechazo correspondiente.";
    public static final String CORREO_MENSAJE_TIPO_7_8_9_10_11_12_15_16 = "Por medio del presente se le informa que tiene un documento asignado para firmar en el aplicativo "
            + "de <strong>FISCALIZACI&Oacute;N ELECTR&Oacute;NICA</strong> correspondiente al N&uacute;mero de Orden arriba citado, ";
    public static final String CORREO_MENSAJE_TIPO_7 = "en el que se le solicita al contribuyente la presentaci&oacute;n de la documentaci&oacute;n requerida en la orden "
            + "${numero_EH_ORG}, y se impone la multa correspondiente por la no presentaci&oacute;n de dicha documentaci&oacute;n.";
    public static final String CORREO_MENSAJE_TIPO_8 = "en el que se le solicita al contribuyente informaci&oacute;n complementaria.";
    public static final String CORREO_MENSAJE_TIPO_9 = "relacionado con el oficio de conclusi&oacute;n.";
    public static final String CORREO_MENSAJE_TIPO_10 = "relacionado con el oficio de observaciones.";
    public static final String CORREO_MENSAJE_TIPO_11 = "a trav&eacute;s del cual se determina la situaci&oacute;n juridica del <%nombreContribuyente%>.";
    public static final String CORREO_MENSAJE_TIPO_12 = "a trav&eacute;s del cual se informa al contribuyente la ampliaci&oacute;n del plazo para concluir la revisi&oacute;n.";
    public static final String CORREO_MENSAJE_TIPO_15 = "a trav&eacute;s del cual se informa al contribuyente que su solicitud de pr&oacute;rroga ha sido aceptada.";
    public static final String CORREO_MENSAJE_TIPO_16 = "a trav&eacute;s del cual se informa al contribuyente que su solicitud de pr&oacute;rroga ha sido rechazada.";
    public static final String CORREO_MENSAJE_TIPO_17 = "a trav&eacute;s del cual se determina la situaci&oacute;n jur&iacute;dica del <%nombreContribuyente%>.";
    public static final String CORREO_MENSAJE_TIPO_14 = "Por medio del presente se le informa que el auditor <%nombreAuditor%>, solicit&oacute; el cambio de m&eacute;todo de la orden "
            + "<%numeroMCA%> a <%metodo%>, por lo que usted podr&aacute; accesar al aplicativo de Fiscalizaci&oacute;n Electr&oacute;nica para "
            + "autorizar dicho cambio.";

    public static final String NOMBRE_ACUSE_RECIBO = "acuseRecibo.pdf";

    public static final String NOMBRE_ACUSE_RECIBO_COMPULSA_TERCERO = "acuseReciboCompulsaTercero.pdf";

    public static final String TITULO_GENERAL = "ACUSE DE RECEPCIÓN DE DOCUMENTACIÓN ELECTRÓNICA.";
    public static final String TITULO_ACUSE_PROMOCION_PRUEBAS = "ACUSE DE RECEPCIÓN DE DOCUMENTACIÓN REQUERIDA PARA REVISIÓN ELECTRÓNICA.";
    public static final String TITULO_ACUSE_PRORROGA = "ACUSE DE RECEPCIÓN DE PRÓRROGA PARA REVISIÓN ELECTRÓNICA.";

    public static final String CAMPO_OBLIGATORIO_IMPUESTO = "Campo Obligatorio *";
    public static final String CAMPO_PERIODO = "formInsumo:txtPeriodo";
    public static final String FORM_VALIDAR_INSUMO_CAMPO_PERIODO = "formValidarInsumo:txtPeriodo";
    public static final String CAMPO_OBLIGATORIO_CONCEPTO = "Campo Obligatorio *";

    public static final String I = "I";
    public static final String P = "P";

    public static final BigDecimal REE = BigDecimal.valueOf(4L);
    public static final BigDecimal UCA = BigDecimal.valueOf(3L);
    public static final BigDecimal EHO = BigDecimal.ONE;
    public static final BigDecimal ORG = BigDecimal.valueOf(2L);
    public static final BigDecimal MCA = BigDecimal.valueOf(5L);

    /* Constantes usadas para las Administraciones CENTRALES Y ARACE */
    public static final BigDecimal ACPPCE = BigDecimal.ONE;
    public static final BigDecimal ACPPCENO = new BigDecimal(2);
    public static final BigDecimal ARACE = new BigDecimal(3);
    public static final BigDecimal ACIACE = BigDecimal.valueOf(17L);
    public static final BigDecimal ACOECE = BigDecimal.valueOf(19L);
    public static final BigDecimal ACAOCE = BigDecimal.valueOf(20L);
    public static final BigDecimal ARACE_PACIFICO_NORTE = BigDecimal.valueOf(41L);
    public static final BigDecimal ARACE_NORTE_CENTRO = BigDecimal.valueOf(51L);
    public static final BigDecimal ARACE_NORESTE = BigDecimal.valueOf(31L);
    public static final BigDecimal ARACE_OCCIDENTE = BigDecimal.valueOf(67L);
    public static final BigDecimal ARACE_CENTRO = BigDecimal.valueOf(12L);
    public static final BigDecimal ARACE_SUR = BigDecimal.valueOf(25L);
    public static final BigDecimal PPCE = BigDecimal.valueOf(98L);

    public static final List<BigDecimal> UNIDADES_PERMITIDAS_CONSULTA_ADM = new ArrayList<BigDecimal>();

    static {
        // ACPPCE
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ACPPCE.getId()));
        // AGACE Centro
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ADACE_CENTRO.getId()));
        // AGACE Sur
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ADACE_SUR.getId()));
        // AGACE Noreste
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ADACE_NOROESTE.getId()));
        // AGACE Pacifico Norte
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()));
        // AGACE Norte Centro
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ADACE_NORTE_CENTRO.getId()));
        // AGACE Occidente
        UNIDADES_PERMITIDAS_CONSULTA_ADM.add(new BigDecimal(TipoAraceEnum.ADACE_OCCIDENTE.getId()));
    }

    public static final String PROPUESTA_INFORMADA_COMITE = "PIC";
    public static final String PROPUESTA_PRESENTADA_COMITE = "PPC";
    public static final String PROPUESTA_INFORMADA_COMITE_REGIONAL = "PICR";
    public static final String PROPUESTA_PRESENTADA_COMITE_REGIONAL = "PPCR";

    /* Constantes usadas en los tipos de empleados */
    public static final BigDecimal USUARIO_CONSULTOR_INSUMOS = BigDecimal.ONE;
    public static final BigDecimal USUARIO_ASIGNADOR_INSUMOS = BigDecimal.valueOf(2L);
    public static final BigDecimal USUARIO_PROGRAMADOR = BigDecimal.valueOf(3L);
    public static final BigDecimal USUARIO_VALIDADOR_INSUMOS = BigDecimal.valueOf(4L);
    public static final BigDecimal USUARIO_INSUMOS = BigDecimal.valueOf(5L);
    public static final BigDecimal USUARIO_AUDITOR = BigDecimal.valueOf(6L);
    public static final BigDecimal USUARIO_FIRMANTE = BigDecimal.valueOf(7L);
    public static final BigDecimal USUARIO_PROGRAMADOR_CONSULTA_ANTECEDENTES = BigDecimal.valueOf(8L);
    public static final BigDecimal USUARIO_PROGRAMACION = BigDecimal.valueOf(9L);
    public static final BigDecimal USUARIO_SUBADMINISTRADOR_AREA = BigDecimal.valueOf(10L);
    public static final BigDecimal USUARIO_JEFE_DEPARTAMENTO = BigDecimal.valueOf(15L);
    public static final BigDecimal USUARIO_ENLACE = BigDecimal.valueOf(16L);

    /* Constantes usadas en los ESTATUS de empleados */
    public static final BigDecimal EMPLEADO_ACTIVO = BigDecimal.ONE;

    /* Constantes usadas en los Medios de Contacto */
    public static final String CON_MEDIOS_CONTACTO_AMPARADO = "CMCA";
    public static final String CON_MEDIOS_CONTACTO_NO_AMPARADO = "CMCNA";
    public static final String SIN_MEDIOS_CONTACTO = "SMC";

    public static final String MENSAJE_CONTRIBUYENTE_PPEE = "El RFC del Contribuyente se encuentra marcado como PPEE por lo que no se podr\u00e1 continuar con el proceso.";
    public static final String MENSAJE_CONTRIBUYENTE_PPEE_AMPARADO = "El RFC del Contribuyente se encuentra marcado como PPEE y est\u00e1 Amparado por lo que no se podr\u00e1 continuar con el proceso.";
    public static final String MESANJE_CONTRIBUYENTE_AMPARADO = "El RFC del Contribuyente se encuentra amparado por lo que no se podr\u00e1 continuar con el proceso.";
    public static final String MESANJE_NO_MEDIOS_CONTACTO = "El RFC del Contribuyente no cuenta con medios de contacto por lo que no se podr\u00e1 registrar en el sistema.";
    public static final String MESANJE_ERROR_MEDIOS_CONTACTO = "No se pudo validar los Medios de Contacto, Amparado o PPEE dado que no hay conexi\u00F3n con el sistema; contacte a su administrador o int\u00E9ntelo nuevamente";

    /* Constantes usadas en Operaciones */
    public static final BigDecimal BITACORA_O_INSUMO_REGISTRADO = BigDecimal.ONE;
    public static final BigDecimal BITACORA_O_INSUMO_RETROALIMENTADO = new BigDecimal(2L);
    public static final BigDecimal BITACORA_O_INSUMO_ASIGNADO_SUBADMINISTRADOR = new BigDecimal(3L);
    public static final BigDecimal BITACORA_O_INSUMO_RECHAZADO = new BigDecimal(4L);
    public static final BigDecimal BITACORA_O_SOLICITUD_RETROALIMENTACION_INSUMO = new BigDecimal(5L);
    public static final BigDecimal BITACORA_O_INSUMO_REGISTRADO_PROPUESTA = new BigDecimal(6L);
    public static final BigDecimal BITACORA_O_PROPUESTA_REGISTRADA = new BigDecimal(7L);
    public static final BigDecimal BITACORA_O_CARGA_MASIVA_PROPUESTAS_REGISTRADA = new BigDecimal(8L);
    public static final BigDecimal BITACORA_O_PROPUESTA_APROBADA_COMITE = new BigDecimal(9L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RECHAZADA_COMITE = new BigDecimal(10L);
    public static final BigDecimal BITACORA_O_PROPUESTA_PENDIENTE_COMITE = new BigDecimal(11L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RETROALIMENTADA = new BigDecimal(12L);
    public static final BigDecimal BITACORA_O_NO_APROBADA_PROPUESTA_RETROALIMENTACION = new BigDecimal(13L);
    public static final BigDecimal BITACORA_O_PROPUESTA_TRANSFERIDA = new BigDecimal(14L);
    public static final BigDecimal BITACORA_O_PROPUESTA_REGISTRADA_NIVEL_REGIONAL = new BigDecimal(15L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RECHAZADA_NIVEL_REGIONAL = new BigDecimal(16L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RETROALIMENTADA_NIVEL_REGIONAL = new BigDecimal(17L);
    public static final BigDecimal BITACORA_O_REGISTRO_ASIGNADO_FIRMANTE = new BigDecimal(18L);
    public static final BigDecimal BITACORA_O_REGISTRO_ASIGNADO_AUDITOR = new BigDecimal(19L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION = new BigDecimal(20L);
    public static final BigDecimal BITACORA_O_PROPUESTA_TRANSFERIDA_PENDIENTE_VALIDACION = new BigDecimal(21L);
    public static final BigDecimal BITACORA_O_PROPUESTA_ENVIADA_RETROALIMENTACION = new BigDecimal(22L);
    public static final BigDecimal BITACORA_O_ORDEN_REGISTRADA = new BigDecimal(23L);
    public static final BigDecimal BITACORA_O_RECHAZO_DEL_RECHAZO_PROPUESTA = new BigDecimal(24L);
    public static final BigDecimal BITACORA_O_REGISTRO_TRANSFERIDO_PROPUESTAS_RECHAZADAS = new BigDecimal(25L);
    public static final BigDecimal BITACORA_O_REGISTRO_RETROALIMENTACION_PROGRAMACION_PROPUESTAS_RECHAZADAS = new BigDecimal(26L);
    public static final BigDecimal BITACORA_O_OFICIO_RETROALIMENTADO_VALIDACION = new BigDecimal(27L);
    public static final BigDecimal BITACORA_O_ENVIADA_PARA_VERIFICACION_PROCEDENCIA = new BigDecimal(28L);
    public static final BigDecimal BITACORA_O_RECHAZADA_RETROALIMENTACION_AUDITOR = new BigDecimal(29L);
    public static final BigDecimal BITACORA_O_ENVIADA_NOTIFICACION_CONTRIBUYENTE = new BigDecimal(30L);
    public static final BigDecimal BITACORA_O_CARTA_ENVIADA_NOTIFICACION_CONTRIBUYENTE = new BigDecimal(31L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RECHAZADA_FIRMANTE = new BigDecimal(32L);
    public static final BigDecimal BITACORA_O_NO_APROBADO_RECHAZO = new BigDecimal(33L);
    public static final BigDecimal BITACORA_O_TRANSFERENCIA_PROPUESTA_AUTORIZADA = new BigDecimal(34L);
    public static final BigDecimal BITACORA_O_TRANSFERENCIA_PROPUESTA_RECHAZADA = new BigDecimal(35L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RECHAZADA_VERIFICACION_PROCEDENCIA = new BigDecimal(36L);
    public static final BigDecimal BITACORA_O_ASIGNAR_ORDEN_SUBADMINISTRADOR_EMISION_ORDENES_PPCE = new BigDecimal(37L);
    public static final BigDecimal BITACORA_O_PROPUESTA_PENDIENTE_FIRMA_FIRMANTE = new BigDecimal(38L);
    public static final BigDecimal BITACORA_O_PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO = new BigDecimal(39L);
    public static final BigDecimal BITACORA_O_EXPORTACION_INFORMACION_EXITOSA = new BigDecimal(40L);
    public static final BigDecimal BITACORA_O_CARGA_MASIVA_CARTAS_INVITACION_MASIVAS_REGISTRADA = new BigDecimal(41L);
    public static final BigDecimal BITACORA_O_OFICIO_REQUERIMIENTO_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(42L);
    public static final BigDecimal BITACORA_O_OFICIO_INFORMACION_COMPLEMENTARIA_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(43L);
    public static final BigDecimal BITACORA_O_OFICIO_CONCLUSION_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(44L);
    public static final BigDecimal BITACORA_O_OFICIO_OBSERVACIONES_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(45L);
    public static final BigDecimal BITACORA_O_OFICIO_RESOLUCION_DEFINITIVA_VALIDACION_FIRMANTE = new BigDecimal(46L);
    public static final BigDecimal BITACORA_O_OFICIO_AMPLIACION_PLAZO_VALIDACION_FIRMANTE = new BigDecimal(47L);
    public static final BigDecimal BITACORA_O_SEGUNDA_UNICA_CARTA_INVITACION_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(48L);
    public static final BigDecimal BITACORA_O_SEGUNDA_CARTA_INVITACION_MASIVA_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(49L);
    public static final BigDecimal BITACORA_O_CAMBIO_METODO_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(50L);
    public static final BigDecimal BITACORA_O_OFICIO_COMPULSA_TERCEROS_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(51L);
    public static final BigDecimal BITACORA_O_OFICIO_COMPULSA_INTERNACIONAL_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(52L);
    public static final BigDecimal BITACORA_O_OFICIO_SEGUNDO_REQUERIMIENTO_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(53L);
    public static final BigDecimal BITACORA_O_CANCELACION_ORDEN_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(54L);
    public static final BigDecimal BITACORA_O_ACEPTACION_PRORROGA_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(55L);
    public static final BigDecimal BITACORA_O_RECHAZO_PRORROGA_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(56L);
    public static final BigDecimal BITACORA_O_ACEPTACION_EXTEMPORANEO_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(57L);
    public static final BigDecimal BITACORA_O_RECHAZO_EXTEMPORANEO_SEGUIMIENTO_VALIDACION_FIRMANTE = new BigDecimal(58L);

    public static final int RFC_LONGITUD = 13;
    // Carga documentos
    public static final String DIRECTORIO_CARGA_DOCUMENTOS = RutaUtil.getOrigenRuta().concat("/fece/archivosCargaMasiva/");
    public static final String DIRECTORIO_CARGA_DOCUMENTOS_INSUMOS = DIRECTORIO_CARGA_DOCUMENTOS.concat("insumos/");
    public static final String NUMERO_MAXIMO_DOC = "El número máximo de archivos a cargar se ha excedido";

    public static final String CARGA_EXITOSA = "Carga exitosa";
    public static final String MENSAJE_FOLIO = "Se ha generado el Folio de Carga ";

    public static final String UBICACION_LAYOUT = "/siat/fece/layout/";
    public static final String STR3CEROSIZQ = "%04d";

    // Gabinete
    public static final String CARGA_EXITOSA_DOC = "Archivo cargado exitosamente";
    public static final String FALLA_CARGA = "No se pudo cargar el archivo ";
    public static final String ASUNTO_MAIL = "email.notificacion.carga.documento";

    // Carga Masiva Propuestas
    public static final String RFC_INCORRECTO = "El valor correspondiente al RFC es incorrecto.";
    public static final String MEDIOS_CONTACTO = "El contribuyente no cuenta con Medios de Contacto.";
    public static final String FECHA_INICIAL_PERIODO_INCORRECTO = "El valor correspondiente a la fecha inicial del periodo del impuesto es incorrecta.";
    public static final String SUBPROGRAMA_INCORRECTO = "El valor correspondiente al Subprograma es incorrecto.";
    public static final String ARCHIVO_ACUSE = "reportePropuestasExito.jasper";

    public static final int RFC_CONTIBUYENTE = 0;
    public static final int UNIDAD_ADMINISTRATIVA = 1;
    public static final int METODO = 2;
    public static final int AREA = 2;
    public static final int TIPO_REVISION = 3;
    public static final int FECHA_PRESENTACION_COMITE = 4;
    public static final int FECHA_INFORME_COMITE = 5;
    public static final int FECHA_PRESENTACION_COMITE_REGIONAL = 6;
    public static final int FECHA_INFORME_COMITE_REGIONAL = 7;
    public static final int SUBPROGRAMA = 8;
    public static final int TIPO_PROPUESTA = 9;
    public static final int CAUSA_PROGRAMACION = 10;
    public static final int SECTOR = 11;
    public static final int FECHA_INICIO_PERIODO = 12;
    public static final int FECHA_FIN_PERIODO = 13;
    public static final int PRIORIDAD = 14;
    public static final int DOCUMENTOS = 15;

    public static final int FECHA_INFORME_COMITE_CI = 3;
    public static final int FECHA_INFORME_COMITE_REGIONAL_CI = 4;
    public static final int SUBPROGRAMA_CI = 5;
    public static final int TIPO_PROPUESTA_CI = 6;
    public static final int CAUSA_PROGRAMACION_CI = 7;
    public static final int SECTOR_CI = 8;
    public static final int FECHA_INICIO_PERIODO_CI = 9;
    public static final int FECHA_FIN_PERIODO_CI = 10;
    public static final int PRIORIDAD_CI = 11;
    public static final int DOCUMENTOS_CI = 12;

    public static final String LOGO_SHCP_JPG = "logoSHCP.jpg";
    public static final String LOGO_SAT_JPG = "logoSAT.jpg";

    public static final String METODO_MCA = "MCA";
    public static final String ARCHIVO_EXCEL = "xls";

    public static final BigDecimal BIG_DECIMAL_CERO = BigDecimal.ZERO;

    public static final int ENTERO_CERO = 0;
    public static final int ENTERO_UNO = 1;
    public static final int ENTERO_DOS = 2;
    public static final int ENTERO_TRES = 3;
    public static final int ENTERO_CUATRO = 4;
    public static final int ENTERO_CINCO = 5;
    public static final int ENTERO_SEIS = 6;
    public static final int ENTERO_SIETE = 7;
    public static final int ENTERO_OCHO = 8;
    public static final int ENTERO_NUEVE = 9;
    public static final int ENTERO_DIEZ = 10;
    public static final int ENTERO_ONCE = 11;
    public static final int ENTERO_DOCE = 12;
    public static final int ENTERO_TRECE = 13;
    public static final int ENTERO_CATORCE = 14;
    public static final int ENTERO_QUINCE = 15;
    public static final int ENTERO_DIECISEIS = 16;
    public static final int ENTERO_DIECISIETE = 17;
    public static final int ENTERO_DIECIOCHO = 18;
    public static final int ENTERO_DIECINUEVE = 19;
    public static final int ENTERO_VEINTE = 20;
    public static final int ENTERO_VEINTIUNO = 21;
    public static final int ENTERO_VEINTIDOS = 22;
    public static final int ENTERO_VEINTITRES = 23;
    public static final int ENTERO_VEINTICUATRO = 24;
    public static final int ENTERO_VEINTICINCO = 25;
    public static final int ENTERO_VEINTISEIS = 26;
    public static final int ENTERO_VEINTISIETE = 27;
    public static final int ENTERO_VEINTIOCHO = 28;
    public static final int ENTERO_VEINTINUEVE = 29;
    public static final int ENTERO_CUARENTAUNO = 41;
    public static final int ENTERO_CUARENTATRES = 43;
    public static final int ENTERO_CUARENTACUATRO = 44;
    public static final int ENTERO_CUARENTANUEVE = 49;
    public static final int ENTERO_CINCUENTATRES = 53;
    public static final int ENTERO_CINCUENTANUEVE = 59;
    public static final int ENTERO_SESENTASEIS = 66;
    public static final int ENTERO_NOVENTAOCHO = 98;
    public static final int ENTERO_CIENTODIECIOCHO = 118;
    public static final int ENTERO_DIAS_ANIO = 365;
    public static final double DIAS_MES = 30.5;
    public static final int ENTERO_CIENTO_TREINTAOCHO = 138;
    public static final int ENTERO_CIENTO_TREINTANUEVE = 139;
    public static final int ENTERO_CIENTO_CUARENTA = 140;
    public static final int ENTERO_CIENTO_UNO = 101;

    public static final Long RETROALIMENTAR_PROPUESTA = 31L;
    public static final Long RETROALIMENTAR_PROPUESTA_AUDITOR = 72L;
    public static final Long RETROALIMENTAR_REEMPLAZO_PROPUESTA_AUDITOR = 58L;
    public static final Long TRANSFERIR_PROPUESTA = 32L;
    public static final Long TRANSFERIR_PROPUESTA_ANALIZAR = 120L;
    public static final Long RECHAZO_PROPUESTA = 33L;
    public static final Long RECHAZO_PROPUESTA_AUDITOR = 62L;
    public static final Long RECHAZO_REMPLAZO_PROPUESTA_AUDITOR = 119L;
    public static final Long TRANSFERIR_REEMPLAZO_PROPUESTA = 73L;

    public static final Long LONG_TRES = 3L;
    public static final Long LONG_CINCO = 5L;
    public static final Long LONG_TREINTAYCUATRO = 34L;

    public static final int TIME_OUT = 7000;

    public static final long FILE_SIZE = 4196000L;

    public static final int BYTE = 1024;
    public static final BigDecimal INSUMO_CREADO = BigDecimal.ONE;
    public static final BigDecimal INSUMO_ACEPTADO = BigDecimal.valueOf(14);

    public static final String COMPONENTE_FORM = "formValidaRetroPropuesta";
    public static final String MENSAJE_PROBLEMA = "mensaje.problema";
    public static final String MENSAJE_PROBLEMA_CARGA = "No se pudo cargar la información de propuestas por validar";
    public static final String MENSAJE_PROBLEMA_ACTUALIZAR = "No se pudo actualizar la propuesta";
    public static final String VISIBLE = "VISIBLE";

    public static final String DOCUMENTO = "Documento";
    public static final String SIN_REGISTROS = "No se encontraron registros";
    public static final String CONTENT_TYPE_WORD = "application/msword";
    public static final String FECHA_REGISTRO = "Fecha de Registro";

    public static final String FORM_DOCUMENTACION_ORDEN_RFC = "formDocumentacionOrden:txtRfcTerceroComp";
    public static final String FORM_ORDENES_RFC = "formOrdenes:txtRfcApoderado";

    public static final String ARCHIVO_DUPLICADO = "error.archivo.duplicado";
    public static final String ENVIAR_CORREO_MB = "ENTRO A ENVIAR CORREO EN MB";
    public static final String RFC_INCORRECTO_OBLIGATORIO = "El campo deber\u00e1 de ser obligatorio";
    public static final String COINCIDENCIAS_AGACE = "Se encontraron coincidencias del RFC y periodo en los sistemas: AGACE";
    public static final String COINCIDENCIAS_AGAFF = "Se encontraron coincidencias del RFC y periodo en los sistemas: AGAFF";
    public static final String COINCIDENCIAS_SICSEP = "Se encontraron coincidencias del RFC y periodo en los sistemas: SICSEP";
    public static final String COINCIDENCIAS_SUIEFI = "Se encontraron coincidencias del RFC y periodo en los sistemas: SUIEFI";
    public static final String COINCIDENCIAS_INSUMOS = "Se encontraron coincidencias del RFC y periodo en los sistemas: INSUMOS";

    public static final String NO_VALIDA_AGAFF = "No se pudo validar la informaci\u00f3n en los sistemas AGAFF";
    public static final String NO_VALIDA_SICSEP = "No se pudo validar la informaci\u00f3n en los sistemas SICSEP";

    // Insumos retroalimentacion
    public static final int RETROALIMENTACION_POR_ATENDER = 0;
    public static final int RETROALIMENTACION_ATENDIDA = 1;
    public static final int INSUMO_TIPO_DOC_RETRO = 1;
    public static final int INUSMO_TIPO_DOC_RECHAZO = 2;

    // Reasignacion de Insumos
    public static final BigDecimal REASIGNACION_ESTATUS_ACTIVO = BigDecimal.ONE;
    public static final BigDecimal REASIGNACION_ESTATUS_ACEPTADA = new BigDecimal(3);
    public static final BigDecimal REASIGNACION_ESTATUS_RECHAZADA = new BigDecimal(2);
    public static final BigDecimal INSUMO_POR_REASIGNAR = new BigDecimal(5);
    public static final BigDecimal INSUMO_AGREGADO = new BigDecimal(142);
    public static final BigDecimal INSUMO_SIN_ADMINISTRADOR = new BigDecimal(148);
    public static final BigDecimal REASIGNADA_A_ADMINISTRADOR = new BigDecimal(5);
    public static final BigDecimal ACEPTACION_REASIGNACION_A_ADMINISTRADOR = new BigDecimal(6);
    public static final BigDecimal RECHAZO_REASIGNACION_A_ADMINISTRADOR = new BigDecimal(7);
    public static final BigDecimal INSUMO_POR_RETROALIMENTAR = new BigDecimal(12);
    public static final BigDecimal INSUMO_RECHAZADO = new BigDecimal(11);
    public static final BigDecimal INSUMO_RETROALIMENTADO = new BigDecimal(3);
    // Asignacion de insumos
    public static final BigDecimal INSUMO_ASIGNADO_A_SUBADMINISTRADOR = new BigDecimal(4);

    // Tiempo Restante Plazo
    public static final String PLAZO_RESTANTE_YEAR = "a\u00f1o";
    public static final String PLAZO_RESTANTE_MONTHS = "mes(es)";
    public static final String PLAZO_RESTANTE_DAYS = "d\u00eda(s)";
    public static final String PLAZO_RESTANTE_VENCIDO = "Plazo Vencido";
    public static final String PLAZO_RESTANTE_VENCIDO_INSUMO = "0 d\u00eda(s)";
    // (1000 * 60 * 60 * 24) 24 hours in milliseconds
    public static final long DIA_MILISEGUNDOS = 86400000;

    // tipo plazos
    public static final BigDecimal TIPO_PLAZO_DOC_ANIO = BigDecimal.ONE;
    public static final BigDecimal TIPO_PLAZO_DOC_MES = new BigDecimal(2);
    public static final BigDecimal TIPO_PLAZO_DOC_DIA = new BigDecimal(3);

    // estatus para calcular plazos ordenes y documentacion
    public static final BigDecimal ESTATUS_FIRMADO_ENVIADO_NOTIFICACION = new BigDecimal(102);
    public static final BigDecimal ESTATUS_NOTIFICADO_AL_CONTIBUYENTE = new BigDecimal(103);
    public static final BigDecimal ESTATUS_OFICIO_FIRMADO_ENVIADO_NOTIFACION = new BigDecimal(114);
    public static final BigDecimal ESTATUS_OFICIO_NOTIFICADO_AL_CONTRIBUYENTE = new BigDecimal(115);

    // tipo de oficios principal sobre los que se Calcula el plazo
    public static final BigDecimal ID_OFICIO_COMPULSA_TERCEROS = BigDecimal.ONE;
    public static final BigDecimal ID_OFICIO_COMPULSA_INTERNACIONAL = new BigDecimal(2);
    public static final BigDecimal ID_OFICIO_SEGUNDO_REQUERIMIENTO = new BigDecimal(3);
    public static final BigDecimal ID_OFICIO_REQUERIMIENTO_REINCIDENCIA = new BigDecimal(4);
    public static final BigDecimal ID_OFICIO_OBSERVACIONES_REVISION_ESC = new BigDecimal(5);
    public static final BigDecimal ID_OFICIO_SEGUNDA_UNICA_CARTA_INV = new BigDecimal(6);
    public static final BigDecimal ID_OFICIO_SEGUNDA_CARTA_INV_MASIVA = new BigDecimal(7);
    public static final BigDecimal ID_OFICIO_OTRAS_AUTORIDADES = new BigDecimal(16);

    // Estatus Ordenes
    public static final int ESTATUS_ORDEN_NOTIFICADA_CONTRIBUYENTE = 103;
    public static final BigDecimal ESTATUS_ORDEN_REACTIVADA_ACUERDO_CONCLUSIVO = new BigDecimal(106L);
    public static final BigDecimal ESTATUS_ORDEN_REACTIVADA_COMPULSAS_REE = new BigDecimal(107L);

    // Imagenes semaforo
    public static final String SEMAFORO_AMARILLO = "semaforo-amarillo";
    public static final String SEMAFORO_NARANJA = "semaforo-naranja";
    public static final String SEMAFORO_AZUL = "semaforo-azul";
    public static final String SEMAFORO_GRIS = "semaforo-gris";
    public static final String SEMAFORO_ROJO = "semaforo-rojo";
    public static final String SEMAFORO_VERDE = "semaforo-verde";
    public static final String SEMAFORO_CAFE = "semaforo-cafe";
    public static final String SEMAFORO_NEGRO = "semaforo-negro";
    public static final String SEMAFORO_BLANCO = "semaforo-blanco";
    public static final String SEMAFORO_BEIGE = "semaforo-beige";
    public static final String SEMAFORO_MORADO = "semaforo-morado";

    // Descripciones SEMAFORO
    public static final String SEMAFORO_VERDE_DESC = "Plazo iniciado";
    public static final String SEMAFORO_AMARILLO_DESC = "Plazo de la orden a la mitad";
    public static final String SEMAFORO_NARANJA_DESC = "Plazo avanzado";
    public static final String SEMAFORO_ROJO_DESC = "Plazo pr\u00f3ximo a vencer";
    public static final String SEMAFORO_CAFE_DESC = "Orden suspendida";
    public static final String SEMAFORO_AZUL_DESC = "Orden concluida";
    public static final String SEMAFORO_NEGRO_DESC = "Plazo vencido";
    public static final String SEMAFORO_GRIS_DESC = "UCA, MCA";
    public static final String SEMAFORO_BLANCO_DESC = "Órden sin firmar";

    public static final String SEMAFORO_BLANCO_INSUMO_DESC = "Insumo Creado";
    public static final String SEMAFORO_VERDE_INSUMO_DESC = "Plazo iniciado (10 -15 días restantes para concluir el plazo de atenci\u00f3n)";
    public static final String SEMAFORO_AMARILLO_INSUMO_DESC = "Plazo iniciado (5 - 10 d\u00edas restantes para concluir el plazo de atenci\u00f3n) ";
    public static final String SEMAFORO_NARANJA_INSUMO_DESC = "Plazo por concluir (3 - 5 d\u00edas restantes para concluir el plazo de atenci\u00f3n) ";
    public static final String SEMAFORO_CAFE_INSUMO_DESC = "Insumo no aprobado";
    public static final String SEMAFORO_ROJO_INSUMO_DESC = "Plazo por concluir (0 - 3 d\u00edas restantes para concluir el plazo de atenci\u00f3n)";
    public static final String SEMAFORO_AZUL_INSUMO_DESC = "Insumo aceptado";
    public static final String SEMAFORO_MORADO_INSUMO_DESC = "Plazo suspendido";
    public static final String SEMAFORO_NEGRO_INSUMO_DESC = "Plazo de atención terminado";
    public static final String SEMAFORO_GRIS_INSUMO_DESC = "Insumo aceptado despu\u00e9s de tiempo";
    public static final String SEMAFORO_BEIGE_INSUMO_DESC = "Insumo no aprobado despu\u00e9s de tiempo";

    // Tipos de Empleado
    public static final BigDecimal EMPLEADO_AUDITOR = new BigDecimal(6);

    // Tipos de Promocion
    public static final String TIPO_PROMOCION_PRUEBAS_ALEGATOS = "Pruebas y Alegatos";
    public static final String TIPO_PROMOCION_DOCUMENTACION_REQUERIDA = "Documentaci\u00f3n Requerida";

    // Estatus de Prorrogas
    public static final BigDecimal ESTATUS_PRORROGA_PENDIENTE_APROBACION = BigDecimal.valueOf(121L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRORROGA_APROBADA_AUDITOR = BigDecimal.valueOf(122L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRORROGA_RECHAZADA_AUDITOR = BigDecimal.valueOf(123L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE = BigDecimal.valueOf(124L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV = BigDecimal.valueOf(125L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRORROGA_CONCLUIDA_RECHAZADA_FIRMANTE = BigDecimal.valueOf(126L);
    public static final BigDecimal ESTATUS_PRORROGA_NOTIFICADA_CONTRIBUYENTE = BigDecimal.valueOf(127L);
    public static final BigDecimal ESTATUS_PRORROGA_ERROR_NOTIFICAR_CONTRIBUYENTE = BigDecimal.valueOf(128L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRORROGA_CONCLUIDA = BigDecimal.valueOf(129L);

    public static final String CONTRIBUYENTE_COMBO = "CONTRIBUYENTE";
    public static final String CADENA_CERO = "0";
    public static final String CADENA_UNO = "1";
    public static final String CADENA_DOS = "2";
    public static final String CADENA_TRES = "3";

    public static final String APODERADO_LEGAL = "Apoderado Legal";
    public static final String REPRESENTANTE_LEGAL = "Representante Legal";
    public static final String APODERADO_LEGAL_REPRESENTANTE_LEGAL = "Apoderado Legal del Representante Legal";
    public static final String AGENTE_ADUANAL = "Agente Aduanal";

    public static final String NUEVO_RFC_RL = "Agregar Nuevo RFC";
    public static final BigDecimal ID_CONTRIBUYENTE = BigDecimal.ZERO;
    public static final BigDecimal ID_APODERADO_LEGAL = BigDecimal.ONE;
    public static final BigDecimal ID_REPRESENTANTE_LEGAL = new BigDecimal(2);
    public static final BigDecimal ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL = new BigDecimal(3);
    public static final BigDecimal ID_AGENTE_ADUANAL = new BigDecimal(4);

    public static final String JSF_APODERADO_LEGAL = "AL";
    public static final String JSF_REPRESENTANTE_LEGAL = "RL";
    public static final String JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL = "ALRL";
    public static final String JSF_AGENTE_ADUANAL = "AA";

    public static final BigDecimal COMBO_SELECCIONA = new BigDecimal(-1);
    public static final String COMBO_SELECCIONA_CADENA = "-1";

    // Estatus de Oficios
    public static final BigDecimal ESTATUS_OFICIO_PENDIENTE_FIRMA = new BigDecimal(113L);
    public static final BigDecimal ESTATUS_OFICIO_FIRMADO_ENVIADO_NYV = new BigDecimal(114L);
    public static final BigDecimal ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE = new BigDecimal(115L);
    public static final BigDecimal ESTATUS_OFICIO_RECHAZADO = new BigDecimal(116L);

    // Oficios Firmados , Pendientes por firmar
    public static final String GET_OFICIOS_PENDIENTES = "OP";
    public static final String GET_OFICIOS_FIRMADOS = "OF";
    // Oficios Prorrogas Firmadas , Pendientes por firmar
    public static final String GET_PRORROGAS_PENDIENTES = "PP";
    public static final String GET_PRORROGAS_FIRMADAS = "PF";
    public static final String GET_PRUEBAS_PERICIALES_PENDIENTES = "PPP";
    public static final String GET_PRUEBAS_PERICIALES_FIRMADAS = "PPF";

    public static final BigDecimal ESTATUS_REGISTRO_ASIGNADO_FIRMANTE = new BigDecimal(70);
    public static final BigDecimal ESTATUS_REGISTRADA = new BigDecimal(68);
    public static final BigDecimal ESTATUS_CONFIRMADO_POR_REGIONAL = new BigDecimal(49);

    public static final int ESTATUS_PROPUESTA_ARCH_ADJ = 57;

    // Consulta Antecedentes
    public static final String CORREO = "CORREO";
    public static final String MSG_CON_MEDIO = "Con Medio de Contacto";
    public static final String MSG_SIN_MEDIO = "El RFC <{0}> no tiene registrado ning\u00fan Medio de Contacto, favor de verificar";
    public static final String BUZON_NO_DISPONIBLE = "Buz\u00f3n tributario no disponible, intentelo m\u00e1s tarde";
    public static final int CERO = 0;
    public static final int UNO = 1;
    public static final int DOS = 2;
    public static final int TRES = 3;
    public static final int CUATRO = 4;
    public static final int CINCO = 5;
    public static final int SEIS = 6;
    public static final int SIETE = 7;
    public static final int OCHO = 8;
    public static final int ID50 = 50;
    public static final int ID64 = 64;
    public static final int ID65 = 65;
    public static final int ID66 = 66;
    public static final int ID67 = 67;
    public static final String SUIEFI = "SUIEFI";
    public static final String SICSEP = "SICSEP";
    public static final String AGAFF = "AGAFF";
    public static final String AGACE = "AGACE";
    public static final String PROPUESTA = "PROPUESTA";
    public static final String RFC = "RFC";
    public static final String NUM_ORDEN_PROP = "NUMERO DE ORDEN/PROPUESTA";
    public static final String METODOSTR = "METODO";
    public static final String ORIGEN = "ORIGEN";
    public static final String PERIODO_INICIAL = "PERIODO INICIAL";
    public static final String PERIODO_FINAL = "PERIODO FINAL";
    public static final String FECHA_EMISION = "FECHA EMISION";
    public static final String ESTATUS = "ESTATUS";
    public static final String TIPO = "TIPO";
    public static final String FIRMANTE_NO_DISPONIBLE = " El firmante seleccionado no se encuentra disponible para suplencia. Favor seleccione otro firmante";

    // Catalogo de Oficios "FECET_TIPO_OFICIO"
    public static final BigDecimal OFICIO_COMPULSA_TERCERO = BigDecimal.ONE;
    public static final BigDecimal OFICIO_COMPULSA_INTERNACIONAL = new BigDecimal(2L);
    public static final BigDecimal OFICIO_SEGUNDO_REQ = new BigDecimal(3L);
    public static final BigDecimal OFICIO_REQUERIMIENTO_RESIDENCIA = new BigDecimal(4L);
    public static final BigDecimal OFICIO_OBSERVACIONES_REVISION_ESCRITORIO = new BigDecimal(5L);
    public static final BigDecimal OFICIO_SEGUNDA_UNICA_CARTA = new BigDecimal(6L);
    public static final BigDecimal OFICIO_SEGUNDA_CARTA_INVITACION_MASIVA = new BigDecimal(7L);
    public static final BigDecimal OFICIO_PRUEBAS_DESAHOGO = new BigDecimal(8L);
    public static final BigDecimal OFICIO_DE_MULTA = new BigDecimal(9L);
    public static final BigDecimal OFICIO_CONCLUSION_REVISION_ESCRITOS = new BigDecimal(10L);
    public static final BigDecimal OFICIO_CANCELACION_ORDEN = new BigDecimal(11L);
    public static final BigDecimal OFICIO_SUSPENSION_PADRON = new BigDecimal(12L);
    public static final BigDecimal OFICIO_AVISO_CONTRIBUYENTE = new BigDecimal(13L);
    public static final BigDecimal OFICIO_SIN_OBSERVACIONES = new BigDecimal(14L);
    public static final BigDecimal OFICIO_RESOLUCION_DEFINITIVA = new BigDecimal(15L);
    public static final BigDecimal OFICIO_OTRAS_AUTORIDADES = new BigDecimal(16L);
    public static final BigDecimal OFICIO_CONCLUSION = new BigDecimal(17L);
    public static final BigDecimal OFICIO_PRUEBAS_PERICIALES_DESAHOGO = new BigDecimal(18L);
    public static final BigDecimal OFICIO_CAMBIO_METODO = new BigDecimal(19L);
    public static final BigDecimal OFICIO_PRELIQUIDACION = new BigDecimal(20L);
    public static final BigDecimal OFICIO_AVISO_CIRCUNSTANCIAL = new BigDecimal(21L);

    public static final BigDecimal LEYENDA_CLASIFICACION = BigDecimal.ONE;
    public static final BigDecimal LEYENDA_PIE_PAGINA = new BigDecimal(2);
    public static final BigDecimal LEYENDA_CAMPANA_MEDIO_AMBIENTE = new BigDecimal(3);
    public static final BigDecimal LEYENDA_INSUMO_RECHAZADO = new BigDecimal(13);
    public static final BigDecimal LEYENDA_SOLICITUD_RETROALIMENTACION_INSUMO = new BigDecimal(7);
    public static final BigDecimal LEYENDA_SUSPENSION_PLAZO_INSUMO = BigDecimal.valueOf(64);

    public static final BigDecimal SUBMODULO_CONSULTAR_INSUMOS_ASIGNADOS_A_ADMINISTRADORES = BigDecimal.valueOf(19);
    public static final BigDecimal SUBMODULO_CONSULTAR_ASIGNAR_INSUMOS_SUBADMINISTRADORES = BigDecimal.valueOf(3);

    public static final BigDecimal LEYENDA_AMPARADO = BigDecimal.valueOf(4);
    public static final BigDecimal LEYENDA_ASIGNADO_CENTRAL = BigDecimal.valueOf(5);
    public static final BigDecimal RETRO_ATENDIDA = BigDecimal.valueOf(6);
    public static final BigDecimal LEYENDA_ASIGNADO_ADMIN = BigDecimal.valueOf(8);
    public static final BigDecimal LEYENDA_ASIGNADOS_CENTRAL = BigDecimal.valueOf(65);
    public static final BigDecimal LEYENDA_ASIGNADOS_ADMIN = BigDecimal.valueOf(66);
    public static final BigDecimal LEYENDA_ACUERDO_INICIO = BigDecimal.valueOf(67);
    public static final BigDecimal LEYENDA_ACUERDO_FIN = BigDecimal.valueOf(68);
    public static final String TABLA_SUPERIOR = "<table width='400px' border='1px;'><tr><td width='50%' align='center'>Id Registro</td></tr>";
    public static final String TABLA_UNO = "<tr><td width='50%' align='center'>";
    public static final String TABLA_DOS = "</td></tr>";
    public static final String TABLA_INFERIOR = "</table>";

    public static final int ID_CLASIFICACION = 1;
    public static final int ID_PIE_PAGINA = 2;
    public static final int ID_MEDIO_AMBIENTE = 3;

    public static final Long DOCUMENTO_CARGADO_WORD = 1L;
    public static final Long DOCUMENTO_CARGADO_PDF = 2L;
    public static final Long DOCUMENTO_CARGADO_EXCEL = 3L;
    public static final String ESTADO_ACTIVA = "ACTIVA";
    public static final String ESTADO_REGISTRADA = "REGISTRADA";
    public static final String ESTADO_SUPELNCIA_PENIENTE = "4";
    public static final String ESTADO_SUPLENCIA_CANCELADA = "3";
    public static final String MSJ_FIRMANTE_ACTIVO_SUPLENCIA = "El firmante a suplir cuenta con una suplencia asignada como firmante suplente. Favor de revisar la informaci\u00f3n ";
    public static final String MSJ_SUPLENCIA_YA_EXISTE = "Favor de revisar los datos ingresados, la suplencia que intenta dar de alta ya existe";
    public static final String MSJ_SUPLENCIA_REGISTRO_A_ALMACENADO = "El Firmante suplente ya tiene asignada una suplencia"
            + "como firmante suplente, en el periodo ingresado. " + "\u00EFDesea continuar con la asignaci\u00f3n?";
    public static final String MSJ_SUPLENCIA_ASIGNADA = "El Firmante a Suplir cuenta con una suplencia asignada como "
            + "\"Firmante Suplente\". Favor de revisar la informaci\u00f3n";
    /**
     * Este atributo corresponde al path donde estan almacenadas las plantillas de correo.
     */
    public static final String DIRECTORIO_PLANTILLA_CORREO = "/siat/fece/correos/plantillas/";

    /**
     * Constantes usadas en el Managed Bean COnsultar Propuestas Asignadas
     */
    public static final String ERROR = "Ocurrio un error";
    public static final String ERROR_SELECCIONAR_ARCHIVO = "error.debe.seleccionar.archivo";
    public static final String TIPO_ARCHIVO_INVALIDO = "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior";
    public static final BigDecimal ESTATUS_RECHAZO_ENTRANTE = new BigDecimal(61L);
    public static final String ERROR_DOCUMENTO_ADJUNTO = "No se pudo adjuntar el documento ";
    public static final String ERROR_GUARDAR_REGISTRO = "No se pudo guardar el registro.";
    public static final String REGISTRO_RECHAZADO = ", ha sido Rechazado y enviado al Firmante para su validaci\u00f3n";
    public static final String REGISTRO_TRANSFERIDO = ", ha sido enviado al Firmante para su validaci\u00f3n";
    public static final String REGISTRO_RETROALIMENTADO = ", ha sido enviado para su retroalimentaci\u00f3n.";
    public static final String REGISTRO_CANCELADO = ", ha sido Cancelado y enviado al Firmante para su validaci\u00f3n.";
    public static final String DEBE_SELECCIONAR_PROPUESTA = "Debes Seleccionar una propuesta";
    public static final String HEADER_ORDEN = "Hist\u00f3nrico";
    public static final String HEADER_EXPEDIENTE = "Expediente";

    public static final String EXITO_GUARDADO_ASOCIADO_1 = "Se agrega ";
    public static final String EXITO_GUARDADO_ASOCIADO_2 = " del Contribuyente ";

    public static final String LEYENDA_ASOCIADO_ALEGATO = "Pruebas y Alegatos por enviar";
    public static final String LEYENDA_ASOCIADO_DOC_REQ = "Documentaci\u00f3n Requerida";

    public static final String ASUNTO_CENTRAL_FIRMANTE = "Aviso de Aprobaci\u00f3n de Programaci\u00f3n  para asignar de central a firmante";

    public static final String ASUNTO_ATENCION_SOLICITUD_RETROALIMENTACION = "Aviso de Atenci\u00f3n a Solicitud de Retroalimentaci\u00f3n por parte del \u00c1rea de Programaci\u00f3n";

    public static final String ASUNTO_ATENCION_RECHAZO_RETROALIMENTACION = "Aviso de Atenci\u00f3n Y Rechazo a Solicitud de Retroalimentaci\u00f3n por parte del \u00c1rea de Programaci\u00f3n";

    public static final String ASUNTO_ATENCION_TRANSFERENCIA_PROPUESTA = "Aviso de Transferencia de Propuesta para su Asignaci\u00f3n a sus firmantes";

    public static final int DOCUMENTO_RECHAZO = 1;
    public static final int DOCUMENTO_TRANSFERENCIA = 2;
    public static final int DOCUMENTO_RETROALIMENTACION = 3;
    public static final int DOCUMENTO_ORDEN = 4;
    public static final int DOCUMENTO_EXPEDIENTE = 5;
    public static final String OPERACION_RETROALIMENTAR = "Retroalimentar";
    public static final String OPERACION_TRANSFERIR = "Transferir";

    public static final String AVISO_PROPUESTA_ASIGNADA_PARA_VALIDACION = "Aviso de Propuesta Asignada para su Validaci\u00f3n";

    public static final String AVISO_VALIDAR_RETROALIMENTACION = "Validar Retroalimentacion";
    public static final String AVISO_RECHAZO_RETROALIMENTACION = "Rechazo Retroalimentacion";
    public static final String AVISO_VALIDAR_TRANSFERENCIA = "Validar Transferencia";
    public static final String AVISO_RECHAZO_TRANSFERENCIA = "Rechazo Transferencia";

    public static final String ASUNTO_ATENCION_RECHAZO_PROPUESTA = "Aviso de Solicitud de validaci\u00f3n de Rechazo de Propuesta";
    public static final String ASUNTO_ATENCION_TRANSFERIR_PROPUESTA = "Aviso de Solicitud de validaci\u00f3n de Propuesta Transferida";
    public static final String ASUNTO_ATENCION_RETROALIMENTAR_PROPUESTA = "Aviso de Solicitud de validaci\u00f3n de Propuesta Retroalimentada";
    public static final String ASUNTO_ATENCION_RECHAZO_TRANSFERIR_PROPUESTA = "Aviso de validaci\u00f3n de transferencia de propuesta rechazada";
    public static final String ASUNTO_ATENCION_RECHAZO_RETROALIMENTAR_PROPUESTA = "Aviso de solicitud de retroalimentaci\u00f3n de propuesta rechazada";
    public static final String ASUNTO_ATENCION_DOCUMENTO_ORDEN = "Aviso de Asignaci\u00f3n de Documento para Validaci\u00f3n al Firmante";

    public static final String ASUNTO_ATENCION_PROPUESTA_INDIVIDUAL = "Aviso de Propuesta Asignada para su validaci\u00f3n";
    public static final String ASUNTO_ASIGNACION_VERIFICACION_PROCEDENCIA_DOCTO = "Aviso de Asignaci\u00f3n de Solicitud de Verificaci\u00f3n de Procedencia de Documento Electr\u00f3nico";
    public static final String ASUNTO_AVISO_ORDEN_FIRMA_DOCTO_ELCTRONICO = "Aviso de Orden Firma de documento electr\u00f3nico";
    public static final String ASUNTO_RECHAZO_PROCEDENCIA_DOCTO_ELECTRONICO = "Aviso de Propuesta Rechazada en Verificaci\u00f3n de Procedencia de Documento Electr\u00f3nico";

    public static final String ASUNTO_AVISO_CARGA_DOCUMENTACION_REQUERIDA = "Aviso de carga de documentaci\u00f3n de Promoci\u00f3n y Documentaci\u00f3n Requerida";
    public static final String ASUNTO_AVISO_DOCUMENTACION_PRUEBAS_ALEGATOS = "Aviso de carga de documentaci\u00f3n de Promoci\u00f3n y Pruebas y Alegatos";
    public static final String ASUNTO_AVISO_SOLICITUD_PRORROGA = "Aviso de solicitud de Pr\u00f3rroga";

    public static final String ASUNTO_AVISO_ASOCIADO_REGISTRADO = "Aviso de Asociado Registrado";
    public static final String ASUNTO_AVISO_ENVIO_OFICIO = "Aviso de Env\u00edo de Oficio";
    public static final String ASUNTO_AVISO_CAMBIO_METODO = "Aviso de Cambio de M\u00e9todo";
    public static final String ASUNTO_AVISO_OFICIO_COMPULSA_TERCEROS = "Aviso de Oficio de Compulsa con Terceros";
    public static final String ASUNTO_AVISO_RESOLUCION_PRORROGA = "Aviso de Resolucion de Pr\u00f3rroga";
    public static final String ASUNTO_AVISO_SEGUIMIENTO_OFICIOS = "Aviso de Seguimiento de Oficios (Auditor)";
    public static final String ASUNTO_AVISO_RECHAZO_OFICIOS = "Aviso de Rechazo de Oficios (Auditor)";

    public static final String ID_REGISTRO = "Id_Registro";
    public static final String REGISTRO = "El Registro ";

    /*
     * Estatus Cambio de Metodo
     */
    public static final BigDecimal ESTATUS_CAMBIO_METODO_REGISTRADO = new BigDecimal(130L);
    public static final BigDecimal ESTATUS_CAMBIO_METODO_APROBADO_FIRMANTE = new BigDecimal(131L);
    public static final BigDecimal ESTATUS_CAMBIO_METODO_RECHAZADO_FIRMANTE = new BigDecimal(132L);
    public static final BigDecimal ESTATUS_ORDEN_CONCLUIDA_CAMBIO_METODO = new BigDecimal(111L);
    // Variables utilizadas en envio de notificaciones

    public static final String FOLIO_CARGA = "folioCarga";
    public static final String METODO_ORDEN = "metodo";
    public static final String NUMERO_ORDEN = "numeroOrden";
    public static final String NOMBRE_CONTRIBUYENTE = "nombreContribuyente";
    public static final String RFC_CONTRIBUYENTE = "rfcContribuyente";
    public static final String DATOS_EXTRA = "datosExtra";
    public static final String FOLIO_OFICIO = "folioOficio";
    public static final String ASOCIADO = "Asociado";
    public static final String LISTA_OFICIOS_FIRMAR = "listaOficiosFirmar";
    public static final String NOMBRE_AUDITOR = "nombreAuditor";
    public static final String NOMBRE_TERCERO_COMPULSADO = "nombreTerceroCompulsado";
    public static final String NUMERO_ORDEN_COMPULSA = "numeroOrdenCompulsa";
    public static final String PRORROGA_APROBADA_RECHAZADA = "aprobadaRechazada";
    public static final String TIPO_OFICIO = "tipoOficio";
    public static final String MOTIVO_RECHAZO = "motivoRechazo";
    public static final String CONTENIDO_EXTRA = "<b>Nombre del Contribuyente:</b>%s<br><b>RFC del Contribuyente:</b>%s<br><b>Tipo de Oficio:</b>%s<br>";
    public static final String ARCHIVO_WORD_INVALIDO = "Solo se aceptan archivos WORD versi\u00f3n 2007 o superior";

    // ESTATUS COMPULSAS
    public static final BigDecimal ESTATUS_COMPULSA_PENDIENTE_FIRMA = new BigDecimal(135L);
    public static final BigDecimal ESTATUS_COMPULSA_APROBADA_FIRMANTE = new BigDecimal(136L);
    public static final BigDecimal ESTATUS_COMPULSA_RECHAZADA_FIRMANTE = new BigDecimal(137L);

    // EXPRESION PARA VALIDAR RFC
    public static final String PATRON_RFC = "^([A-Z\\u00D1\\u0026]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z|\\d]{3})$";
    public static final String MENSAJE_RFC_INCORRECTO = "El RFC introducido es incorrecto";

    // MENSAJES PARA REPRESENTANTE LEGAL ECU 11 y 12
    public static final String EXITO_GUARDADO_ASOCIADO_3 = "Se agrega el Representante Legal ";

    // MENSAJES PARA AGENTE ADUANAL ECU 11 y 12
    public static final String EXITO_GUARDADO_ASOCIADO_4 = "Se agrega el Agente Aduanal ";

    // MENSAJE PARA ACTUALIZAR DOCUMENTO ORDEN ECU 11
    public static final String MENSAJE_GENERAR_REVISION_INCORRECTA = "Es necesario realizar primero la actualizaci\u00f3n de la Orden";
    public static final String MENSAJE_NO_REPRESENTANTE_LEGAL = "No se agrega Representante Legal";

    // MENSAJE PARA ECU 9 y 10 DE PROPUESTAS
    public static final String VERIFICAR = "Favor de verificar ";

    // ETIQUETAS FECHAS A COMITE
    public static final String FECHA_INFORME_COMITE_DESCRIPCION = "Fecha de Informe a Comit\u00e9";
    public static final String FECHA_PRESENTACION_COMITE_DESCRIPCION = "Fecha de Presentaci\u00f3n a Comit\u00e9";
    public static final String FECHA_INFORME_COMITE_REGIONAL_DESCRIPCION = "Fecha de Informe a Comit\u00e9 Regional";
    public static final String FECHA_PRESENTACION_COMITE_REGIONAL_DESCRIPCION = "Fecha de Presentaci\u00f3n a Comit\u00e9 Regional";

    // CATALOGO ADMIN OFICIOS
    public static final BigDecimal ADMIN_OFICIO_000 = new BigDecimal(1L);
    public static final BigDecimal ADMIN_OFICIO_001 = new BigDecimal(2L);
    public static final BigDecimal ADMIN_OFICIO_010 = new BigDecimal(3L);
    public static final BigDecimal ADMIN_OFICIO_011 = new BigDecimal(4L);
    public static final BigDecimal ADMIN_OFICIO_100 = new BigDecimal(5L);
    public static final BigDecimal ADMIN_OFICIO_101 = new BigDecimal(6L);
    public static final BigDecimal ADMIN_OFICIO_110 = new BigDecimal(7L);
    public static final BigDecimal ADMIN_OFICIO_111 = new BigDecimal(8L);

    public static final String CARPETAPAPELTRABAJO = "PapelesTrabajo";
    public static final BigDecimal PAPELTRABAJOORDEN = new BigDecimal(1L);
    public static final BigDecimal PAPELTRABAJOOFICIO = new BigDecimal(2L);

    public static final BigDecimal ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV = BigDecimal.valueOf(151L);
    public static final BigDecimal ESTATUS_PRUEBAS_PERICIALES_PENDIENTE_APROBACION = BigDecimal.valueOf(153L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_APROBADA_AUDITOR = BigDecimal.valueOf(158L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_RECHAZADA_AUDITOR = BigDecimal.valueOf(149L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_RECHAZADA_FIRMANTE = BigDecimal.valueOf(150L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_CONCLUIDA_RECHAZADA_FIRMANTE = BigDecimal.valueOf(152L);
    public static final BigDecimal ESTATUS_PRUEBAS_PERICIALES_NOTIFICADA_CONTRIBUYENTE = BigDecimal.valueOf(154L);
    public static final BigDecimal ESTATUS_PRUEBAS_PERICIALES_ERROR_NOTIFICAR_CONTRIBUYENTE = BigDecimal.valueOf(155L);
    public static final BigDecimal ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_CONCLUIDA = BigDecimal.valueOf(156L);

    public static final String ASUNTO_AVISO_SOLICITUD_PRUEBAS_PERICIALES = "Aviso de solicitud de Prueba Pericial";

    public static final String MENSAJE_REASIGNAR_CONSULTA1 = "La propuesta asignada al ";
    public static final String MENSAJE_REASIGNAR_CONSULTA2 = " hay que reasignar, ya que no pertenece a la Administraci\u00f3n";
    public static final String CONJUNCION_REASIGNAR_CONSULTA = " y/o ";
    public static final String ENLACE = " Enlace ";
    public static final String JEFE_DEPARTAMENTO = " Jefe de Departamento ";
    public static final String ADJUNTAR_ARCHIVO = "Se adjunt\u00f3 correctamente el documento ";

    public static final String SIN_MEDIOS_CONTACTO_COMPULSA_TERCEROS = "El RFC del Contribuyente ingresado no cuenta con medios de contacto, por lo que la carga de Documentaci\u00f3n Requerida tendr\u00E1 que ser manual. ¿Desea continuar con la generación del oficio?";
    
    public static final String AUDITOR = "AUDITOR";
    public static final String FIRMANTE = "FIRMANTE";
    public static final String CONTRIBUYENTE = "CONTRIBUYENTE";
    
    public static final String NUMERO = "numero";
    public static final String NOMBRE_OFICIO = "nombreOficio";
    public static final String HEADER_SUBPROGRAMA = "SUBPROGRAMA";
    public static final String HEADER_IMPUESTO = "IMPUESTO ";
    public static final String HEADER_CONCEPTO_IMPUESTO = "CONCEPTO ";
    public static final String HEADER_MONTO_IMPUESTO = "MONTO ";
    public static final String HEADER_UNIDAD_INSUMO = "AREA DE CREACION";
    public static final String HEADER_UNIDAD_PROPUESTA = "AREA DE DESAHOGO";
    public static final String HEADER_NUM_PROPUESTA = "NUMERO DE PROPUESTA";
    public static final String HEADER_NUM_INSUMO = "NUMERO DE INSUMO";

    public static final int TAM_MAXIMO_ARCHIVOS = 50;
    
    private Constantes() {

    }
}
