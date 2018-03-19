package mx.gob.sat.siat.feagace.vista.util;

import java.math.BigDecimal;

public interface ConstantesPropuestasMasivas {

    String RFC_INCORRECTO = "El campo deber\u00e1 de ser obligatorio";
    String ERROR_EN_IDC = "No se pudo consultar la informaci\u00f3n del Contribuyente";
    String NO_ENCONTRADO_EN_IDC = "No se encuentra informaci\u00f3n registrado para el RFC RFC_CONT, favor de verificar.";
    String MSJ_CAMPO_NO_VALIDO = "El campo no es un valor valido";
    String MSJ_ARCHIVO_NO_VALIDO = "El archivo no se encuentra en el folio de carga";
    String MSJ_CAMPO_OBLIGATORIO = "El campo es un valor obligatorio";
    String MSJ_CAMPO_ENTEROS = "El monto del impuesto \u00fanicamente permitir\u00e1 enteros";
    String MSJ_CAMPO_MAYOR_CERO = "El monto del impuesto debe ser mayor a cero";
    String MSJ_CAMPO_OBLIGATORIO_IMPUESTO = "El campo es un valor obligatorio verificar impuesto, monto, periodo inicio y periodo fin";
    String MSJ_PERIODO_INI_MENOR_PERIODO_PROPUESTOINI = "Periodo inicio no puede ser menor a la fecha del periodo propuesto inicio, favor de verificar.";
    String MSJ_PERIODO_FIN_MENOR_PERIODO_PROPUESTOFIN = "Periodo fin no puede ser menor a la fecha del periodo propuesto fin, favor de verificar.";
    String MSJ_CAMPO_IMPUESTO_DUPLICADO = "No se puede adjuntar un impuesto duplicado, favor de verificar";
    String MSJ_REGISTRO_DUPLICADO = "El registro esta duplicado en el formato de carga";
    String MSJ_ARCHIVO_GRANDE = "Error al cargar el archivo. El archivo es demasiado grande, lo m\u00E1ximo permitido son 4MB.";
    String MSJ_ERROR_ARCHIVO = "Error al cargar el archivo.";
    String MSJ_ERROR_ARCHIVO_INVALIDO = "Archivo inv\u00E1lido";
    String MSJ_ERROR_IMPUESTO_NA = "El campo debe contener un impuesto diferente a N/A";
    String MSJ_IMPUESTO_VALIDO = "Impuesto correcto";
    String MSJ_IMPUESTO_INVALIDO = "Impuesto incorrecto";
    String MSJ_ARCHIVO_DUPLICADO = "El archivo se encuentra duplicado.";
    
    
    //tipos de metodos
    String REE = "REE";
    String UCA = "UCA";
    String EHO = "EHO";
    String ORG = "ORG";
    String MCA = "MCA";
    
    //tipos de revision
    String CGA = "CGA";
    String COM = "COM";
    String GPF = "GPF";
    String GRM = "GRM";
    String GAD = "GAD";
    String GIF = "GIF";
    String GIM = "GIM";
    String GDV = "GDV";
    String GRF = "GRF";
    String CGE = "CGE";
    String CGR = "CGR";
    String GSD = "GSD";
    String CCG = "CCG";
   
    int CARGA_MASIVA_MCA = 3;
    int CARGA_MASIVA_MSV = 2;
    
    String DATE_PATTERN = "^(?:(?:0?[1-9]|1\\d|2[0-8])(\\/|-)(?:0?[1-9]|1[0-2]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:31(\\/|-)(?:0?[13578]|1[02]))|(?:(?:29|30)(\\/|-)(?:0?[1,3-9]|1[0-2])))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(29(\\/|-)0?2)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
    
    BigDecimal IMPUESTO_NA = new BigDecimal(18L);

}
