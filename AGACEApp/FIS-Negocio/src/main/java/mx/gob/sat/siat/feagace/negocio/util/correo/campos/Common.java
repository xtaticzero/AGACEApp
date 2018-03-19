package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum Common {
    DAY("day"),
    MONTH("month"),
    YEAR("year"),
    HOUR("hour"),
    SUBJECT("subject"),
    RECEIVER("receiver"),
    CAMPANIA_MEDIO_AMBIENTE("campania_medio_ambiente"),
    LEYENDA_CLASIFICACION("leyenda_clasificacion"),
    PIE_PAGINA("pie_pagina"),
    DESCRIPCION("descripcion"),
    NOMBRE_LEYENDA("nombre_leyenda"),
    ID_REGISTRO("id_registro"),
    ID_REGISTRO_ESPACIO(" Id_Registro"),
    ID_REGISTRO_MAYUSCULA("Id_Registro"),
    NUMERO_ORDEN("NumeroOrden"),
    ID_REGISTRO_MAYUS_ESPACIO("Id Registro"),
    NUMERO_DIAS_ESPACIO(" N\u00fameroD\u00edas ");    


    private String fieldName;

    private Common(String fieldName) {
        this.fieldName = fieldName;
    }


    public String toString() {
        return fieldName;
    }
}
