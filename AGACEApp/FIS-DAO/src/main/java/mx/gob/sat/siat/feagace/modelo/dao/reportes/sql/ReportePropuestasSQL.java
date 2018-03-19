package mx.gob.sat.siat.feagace.modelo.dao.reportes.sql;

public interface ReportePropuestasSQL {

    String INNER_FECET_CONTRIBUYENTE = "INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (propuesta.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n";

    String INNER_FECEC_ESTATUS = "INNER JOIN FECEC_ESTATUS estatus ON (propuesta.ID_ESTATUS=estatus.ID_ESTATUS)\n";

    String INNER_FECEC_METODO = "INNER JOIN FECEC_METODO metodo ON (propuesta.ID_METODO=metodo.ID_METODO)\n";

    String REPORTE_GERENCIAL = "SELECT impuesto.IDPROPUESTA,impuesto.PREPODERANTE,propuesta.ID_PROPUESTA,\n"
            + "propuesta.ID_REGISTRO,propuesta.ID_ARACE, propuesta.RFC_FIRMANTE, propuesta.RFC_AUDITOR,propuesta.FECHA_INICIO_PERIODO ,propuesta.FECHA_FIN_PERIODO,\n"
            + "contribuyente.RFC, contribuyente.NOMBRE RAZON_SOCIAL,metodo.ABREVIATURA ABREVIATURA_METODO, metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS,\n"
            + "revision.DESCRIPCION TIPO_REVISION,contribuyente.REGIMEN,subprograma.DESCRIPCION NOMBRE_SUBPROGRAMA,sector.DESCRIPCION NOMBRE_SECTOR,\n"
            + "contribuyente.ACTIVIDAD_PREPONDERANTE,causa.DESCRIPCION CAUSA_PROGRAMACION,\n"
            + "dc.RFC_CONTRIBUYENTE,dc.FECHA_CONSULTA,dc.AMPARADO,dc.PPEE\n"
            + "FROM (SELECT propuesta.ID_PROPUESTA IDPROPUESTA,SUM(impuesto.MONTO) PREPODERANTE\n"
            + "      FROM FECET_IMPUESTO impuesto\n"
            + "      RIGHT JOIN FECET_PROPUESTA propuesta ON (propuesta.ID_PROPUESTA=impuesto.ID_PROPUESTA)\n"
            + "      GROUP BY propuesta.ID_PROPUESTA\n"
            + "      ) impuesto\n"
            + "INNER JOIN FECET_PROPUESTA propuesta ON (propuesta.ID_PROPUESTA=impuesto.IDPROPUESTA)\n "
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + "LEFT JOIN FECEC_REVISION revision ON (propuesta.ID_REVISION=revision.ID_REVISION)\n"
            + "INNER JOIN FECEC_SECTOR sector ON (propuesta.ID_SECTOR=sector.ID_SECTOR)\n"
            + "INNER JOIN FECEC_SUBPROGRAMA subprograma ON (propuesta.ID_SUBPROGRAMA=subprograma.ID_SUBPROGRAMA)\n"
            + "INNER JOIN FECEC_CAUSA_PROGRAMACION causa ON (propuesta.ID_CAUSA_PROGRAMACION=causa.ID_CAUSA_PROGRAMACION)\n"
            + "INNER JOIN FECEC_TIPO_PROPUESTA tipo ON (propuesta.ID_TIPO_PROPUESTA=tipo.ID_TIPO_PROPUESTA)\n"
            + "LEFT JOIN FECET_DETALLE_CONTRIBUYENTE dc ON (contribuyente.RFC=dc.RFC_CONTRIBUYENTE)\n"
            + "WHERE propuesta.ID_PROPUESTA=impuesto.IDPROPUESTA\n"
            + "AND TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? ) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) \n";

    String CUERPO_REPORTE_EJECUTIVO_FECHA = " FROM FECET_PROPUESTA propuesta\n"
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? ) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) ";

    String SELECT_EJECTUTIVO_METODO_ENTIDAD_FECHA = "SELECT metodo.NOMBRE NOMBRE_METODO, contribuyente.ENTIDAD, COUNT(propuesta.ID_PROPUESTA) AS TOTAL \n";

    String SELECT_EJECTUTIVO_METODO_ESTATUS_FECHA = "SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, COUNT(propuesta.ID_PROPUESTA) AS TOTAL \n";

    String SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_FECHA = "SELECT estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, COUNT(propuesta.ID_PROPUESTA) AS TOTAL \n";

    String CUERPO_REPORTE_EJECUTIVO_MES = "count(propuesta.ID_PROPUESTA) total\n"
            + "FROM FECET_PROPUESTA propuesta\n"
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? )  AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) \n";

    String SELECT_EJECTUTIVO_METODO_ENTIDAD_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo,entidad, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO, contribuyente.ENTIDAD, to_char(propuesta.FECHA_CREACION,'yyyy-mm') MONTH,\n";

    String SELECT_EJECTUTIVO_METODO_ESTATUS_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo, nombre_estatus, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS,  to_char(propuesta.FECHA_CREACION,'yyyy-mm') MONTH,\n";

    String SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes, nombre_estatus,entidad, total\n"
            + "FROM (SELECT estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, to_char(propuesta.FECHA_CREACION,'yyyy-mm') MONTH,\n";

    String REPORTE_EJECTUTIVO_CERO = "SELECT COUNT(propuesta.ID_REGISTRO) AS TOTAL\n"
            + "FROM (SELECT impuesto.ID_PROPUESTA IDPROPUESTA,SUM(impuesto.MONTO) PREPODERANTE\n"
            + "      FROM FECET_IMPUESTO impuesto\n"
            + "      GROUP BY impuesto.ID_PROPUESTA\n"
            + "      ) impuesto,FECET_PROPUESTA propuesta\n"
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + "WHERE propuesta.ID_PROPUESTA=impuesto.IDPROPUESTA\n"
            + "      AND TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? ) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) \n";

    String REPORTE_EJECTUTIVO_UNO = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes, total\n"
            + "FROM (SELECT to_char(propuesta.FECHA_CREACION,'yyyy-mm') MONTH,count(propuesta.ID_REGISTRO) total\n"
            + "      FROM FECET_PROPUESTA propuesta\n"
            + "      INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (propuesta.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n"
            + "      INNER JOIN FECEC_ESTATUS estatus ON (propuesta.ID_ESTATUS=estatus.ID_ESTATUS)\n"
            + "      INNER JOIN FECEC_METODO metodo ON (propuesta.ID_METODO=metodo.ID_METODO)\n"
            + "      WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? ) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) \n";

    String REPORTE_EJECTUTIVO_DOS = "SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, COUNT(propuesta.ID_PROPUESTA) AS TOTAL \n"
            + "FROM FECET_PROPUESTA propuesta\n"
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? ) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) \n";

    String REPORTE_EJECTUTIVO_TRES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo, nombre_estatus,entidad, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, to_char(propuesta.FECHA_CREACION,'yyyy-mm') MONTH,count(propuesta.ID_PROPUESTA) total\n"
            + "      FROM FECET_PROPUESTA propuesta\n"
            + "      INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (propuesta.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n"
            + "      INNER JOIN FECEC_ESTATUS estatus ON (propuesta.ID_ESTATUS=estatus.ID_ESTATUS)\n"
            + "      INNER JOIN FECEC_METODO metodo ON (propuesta.ID_METODO=metodo.ID_METODO)\n"
            + "      WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC( ? ) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC( ? ) \n";
}
