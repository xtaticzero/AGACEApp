package mx.gob.sat.siat.feagace.modelo.dao.reportes.sql;

public interface ReporteOrdenesSQL {

    String FROM_TABLE_NAME = "FROM FECET_ORDEN orden\n";
    String INNER_FECET_CONTRIBUYENTE = "INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (orden.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n";
    String INNER_FECEC_ESTATUS = "INNER JOIN FECEC_ESTATUS estatus ON orden.ID_ESTATUS = estatus.ID_ESTATUS AND estatus.ID_MODULO = 3\n";
    String INNER_FECEC_METODO = "INNER JOIN FECEC_METODO metodo ON (orden.ID_METODO=metodo.ID_METODO)\n";
    String INNER_FECET_PROPUESTA = "INNER JOIN FECET_PROPUESTA propuesta ON (orden.ID_PROPUESTA=propuesta.ID_PROPUESTA)\n";
    String INNER_FECEC_UNIDAD_ADMINISTRATIVA = "INNER JOIN FECEC_UNIDAD_ADMINISTRATIVA arace ON (propuesta.ID_ARACE=arace.ID_UNIDAD_ADMINISTRATIVA)\n";
    String REPORTE_GERENCIAL = "SELECT orden.NUMERO_ORDEN,orden.FECHA_CREACION, orden.ID_AUDITOR,orden.ID_FIRMANTE, metodo.NOMBRE NOMBRE_METODO,contribuyente.RFC, contribuyente.NOMBRE RAZON_SOCIAL,\n"
            + "estatus.DESCRIPCION NOMBRE_ESTATUS,\n"
            + "dnyv.FECHA_NOTIF_NYV FECHA_FIRMA,dnyv.FECHA_NOTIF_CONT FECHA_ENVIO_NOT,\n"
            + "subprograma.DESCRIPCION NOMBRE_SUBPROGRAMA,sector.DESCRIPCION NOMBRE_SECTOR,contribuyente.ACTIVIDAD_PREPONDERANTE,\n"
            + "propuesta.FECHA_INICIO_PERIODO ,propuesta.FECHA_FIN_PERIODO ,propuesta.ID_ARACE ID_UNIDAD_ADMINISTRATIVA, dc.RFC_CONTRIBUYENTE,dc.FECHA_CONSULTA,dc.AMPARADO,dc.PPEE\n"
            + FROM_TABLE_NAME
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + "LEFT JOIN FECET_DETALLE_CONTRIBUYENTE dc ON (contribuyente.RFC=dc.RFC_CONTRIBUYENTE)\n"
            + INNER_FECET_PROPUESTA
            + "INNER JOIN FECEC_SECTOR sector ON (propuesta.ID_SECTOR=sector.ID_SECTOR)\n"
            + "INNER JOIN FECEC_SUBPROGRAMA subprograma ON (propuesta.ID_SUBPROGRAMA=subprograma.ID_SUBPROGRAMA)\n"
            + "LEFT JOIN FECET_DETALLE_NYV dnyv ON (orden.ID_NYV = dnyv.ID_NYV)\n"
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) \n AND orden.NUMERO_ORDEN IS NOT NULL";

    String CUERPO_REPORTE_EJECUTIVO_FECHA = FROM_TABLE_NAME
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + INNER_FECET_PROPUESTA
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) AND orden.NUMERO_ORDEN IS NOT NULL \n";

    String SELECT_EJECTUTIVO_METODO_ENTIDAD_FECHA = "SELECT metodo.NOMBRE NOMBRE_METODO, contribuyente.ENTIDAD, COUNT(orden.ID_ORDEN) AS TOTAL \n";

    String SELECT_EJECTUTIVO_METODO_ESTATUS_FECHA = "SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, COUNT(orden.ID_ORDEN) AS TOTAL \n";

    String SELECT_EJECTUTIVO_METODO_UNIDAD_ADMINISTRATIVA_FECHA = "SELECT metodo.NOMBRE NOMBRE_METODO,propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, COUNT(orden.ID_ORDEN) AS TOTAL \n";

    String SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_FECHA = "SELECT estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, COUNT(orden.ID_ORDEN) AS TOTAL \n";

    String SELECT_EJECTUTIVO_ENTIDAD_UNIDAD_ADMINISTRATIVA_FECHA = "SELECT contribuyente.ENTIDAD,propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, COUNT(orden.ID_ORDEN) AS TOTAL \n";

    String SELECT_EJECTUTIVO_ESTATUS_UNIDAD_ADMINISTRATIVA_FECHA = "SELECT estatus.DESCRIPCION NOMBRE_ESTATUS, propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, COUNT(orden.ID_ORDEN) AS TOTAL \n";

    String CUERPO_REPORTE_EJECUTIVO_MES = FROM_TABLE_NAME
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + INNER_FECET_PROPUESTA
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) AND orden.NUMERO_ORDEN IS NOT NULL \n";

    String SELECT_EJECTUTIVO_METODO_ENTIDAD_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo,entidad, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO, contribuyente.ENTIDAD, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n";

    String SELECT_EJECTUTIVO_METODO_ESTATUS_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo, nombre_estatus, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n";

    String SELECT_EJECTUTIVO_METODO_UNIDAD_ADMINISTRATIVA_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo, unidad_administrativa, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO,propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n";

    String SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_estatus,entidad, total\n"
            + "FROM (SELECT estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n";

    String SELECT_EJECTUTIVO_ENTIDAD_UNIDAD_ADMINISTRATIVA_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,entidad,unidad_administrativa, total\n"
            + "FROM (SELECT contribuyente.ENTIDAD,propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n";

    String SELECT_EJECTUTIVO_ESTATUS_UNIDAD_ADMINISTRATIVA_MES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes, nombre_estatus,unidad_administrativa, total\n"
            + "FROM (SELECT estatus.DESCRIPCION NOMBRE_ESTATUS, propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n";

    String REPORTE_EJECTUTIVO_CERO = "SELECT COUNT(orden.ID_ORDEN) AS TOTAL\n"
            + FROM_TABLE_NAME
            + INNER_FECET_CONTRIBUYENTE
            + INNER_FECEC_ESTATUS
            + INNER_FECEC_METODO
            + INNER_FECET_PROPUESTA
            + INNER_FECEC_UNIDAD_ADMINISTRATIVA
            + "WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) AND orden.NUMERO_ORDEN IS NOT NULL \n";

    String REPORTE_EJECTUTIVO_UNO = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes, total\n"
            + "FROM (SELECT to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n"
            + "      FROM FECET_ORDEN orden\n"
            + "      INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (orden.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n"
            + "      INNER JOIN FECEC_ESTATUS estatus ON (orden.ID_ESTATUS=estatus.ID_ESTATUS)\n"
            + "      INNER JOIN FECEC_METODO metodo ON (orden.ID_METODO=metodo.ID_METODO)\n"
            + "      INNER JOIN FECET_PROPUESTA propuesta ON (orden.ID_PROPUESTA=propuesta.ID_PROPUESTA)\n"
            + "      INNER JOIN FECEC_UNIDAD_ADMINISTRATIVA arace ON (propuesta.ID_ARACE=arace.ID_UNIDAD_ADMINISTRATIVA)\n"
            + "      WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) AND orden.NUMERO_ORDEN IS NOT NULL \n";

    String REPORTE_EJECTUTIVO_DOS = "SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD,propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, COUNT(orden.ID_ORDEN) AS TOTAL \n"
            + FROM_TABLE_NAME
            + "      INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (orden.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n"
            + "      INNER JOIN FECEC_ESTATUS estatus ON orden.ID_ESTATUS = estatus.ID_ESTATUS AND estatus.ID_MODULO = 3\n"
            + "      INNER JOIN FECEC_METODO metodo ON (orden.ID_METODO=metodo.ID_METODO)\n"
            + "      INNER JOIN FECET_PROPUESTA propuesta ON (orden.ID_PROPUESTA=propuesta.ID_PROPUESTA)\n"
            + "      WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) AND orden.NUMERO_ORDEN IS NOT NULL \n";

    String REPORTE_EJECTUTIVO_TRES = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,nombre_metodo, nombre_estatus,entidad,unidad_administrativa, total\n"
            + "FROM (SELECT metodo.NOMBRE NOMBRE_METODO,estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD,propuesta.ID_ARACE UNIDAD_ADMINISTRATIVA, to_char(orden.FECHA_CREACION,'yyyy-mm') MONTH,count(orden.ID_ORDEN) total\n"
            + "      FROM FECET_ORDEN orden\n"
            + "      INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (orden.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n"
            + "      INNER JOIN FECEC_ESTATUS estatus ON orden.ID_ESTATUS = estatus.ID_ESTATUS AND estatus.ID_MODULO = 3\n"
            + "      INNER JOIN FECEC_METODO metodo ON (orden.ID_METODO=metodo.ID_METODO)\n"
            + "      INNER JOIN FECET_PROPUESTA propuesta ON (orden.ID_PROPUESTA=propuesta.ID_PROPUESTA)\n"
            + "      WHERE TRUNC(propuesta.FECHA_INICIO_PERIODO) >= TRUNC(?) AND TRUNC(propuesta.FECHA_FIN_PERIODO) <= TRUNC(?) AND orden.NUMERO_ORDEN IS NOT NULL \n";
}
