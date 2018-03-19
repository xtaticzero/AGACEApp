package mx.gob.sat.siat.feagace.modelo.dao.reportes.sql;

public interface ReportesSQL {

    String FROM_FECET_INSUMO = " FROM FECET_INSUMO insumo\n";
    String JOIN_FECET_CONTRIBUYENTE = " INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (insumo.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE)\n";
    String JOIN_FECEC_ESTATUS = " INNER JOIN FECEC_ESTATUS estatus ON (insumo.ID_ESTATUS=estatus.ID_ESTATUS)\n";
    String LEFT_FECET_RECHAZO_INSUMO = " LEFT JOIN FECET_RECHAZO_INSUMO rechazo ON (insumo.ID_INSUMO=rechazo.ID_INSUMO)\n";
    String JOIN_FECEC_SECTOR = " INNER JOIN FECEC_SECTOR sector ON (insumo.ID_SECTOR=sector.ID_SECTOR)\n";
    String JOIN_FECEC_SUBPROGRAMA = " INNER JOIN FECEC_SUBPROGRAMA subprograma ON (insumo.ID_SUBPROGRAMA=subprograma.ID_SUBPROGRAMA)\n";
    String JOIN_FECET_DETALLE_CONTRIBUYENTE = " LEFT JOIN FECET_DETALLE_CONTRIBUYENTE dc ON (contribuyente.RFC=dc.RFC_CONTRIBUYENTE)\n";
    String JOIN_FECEC_EMPLEADO = " INNER JOIN FECEC_EMPLEADO empleado ON (empleado.RFC=insumo.RFC_CREACION)\n";
    String JOIN_FECET_DETALLE_EMPLEADO = " INNER JOIN FECET_DETALLE_EMPLEADO empleadodet ON (empleadodet.ID_EMPLEADO=empleado.ID_EMPLEADO)\n";

    String REPORTE_INSUMOS_GERENCIAL = "SELECT insumo.ID_REGISTRO,insumo.FECHA_CREACION AS FECHA_REGISTRO,insumo.FECHA_INICIO_PERIODO ,insumo.FECHA_FIN_PERIODO,\n"
            + "contribuyente.RFC, contribuyente.NOMBRE,contribuyente.ENTIDAD,estatus.DESCRIPCION AS ESTATUS,\n"
            + "rechazo.FECHA_RECHAZO ,rechazo.DESCRIPCION AS MOTIVO_RECHAZO,\n"
            + "sector.DESCRIPCION AS SECTOR,subprograma.DESCRIPCION AS SUBPROGRAMA,contribuyente.ACTIVIDAD_PREPONDERANTE,\n"
            + "dc.RFC_CONTRIBUYENTE,dc.FECHA_CONSULTA,dc.AMPARADO,dc.PPEE\n"
            + FROM_FECET_INSUMO
            + JOIN_FECET_CONTRIBUYENTE
            + JOIN_FECEC_ESTATUS
            + LEFT_FECET_RECHAZO_INSUMO
            + JOIN_FECEC_SECTOR
            + JOIN_FECEC_SUBPROGRAMA
            + JOIN_FECET_DETALLE_CONTRIBUYENTE
            + JOIN_FECEC_EMPLEADO
            + JOIN_FECET_DETALLE_EMPLEADO
            + " WHERE TRUNC(insumo.FECHA_INICIO_PERIODO) >= ? AND TRUNC(insumo.FECHA_FIN_PERIODO) <= ?";

    String REPORTE_INSUMOS_EJECTUTIVO_CERO = "SELECT COUNT(insumo.ID_REGISTRO) AS TOTAL\n"
            + FROM_FECET_INSUMO
            + JOIN_FECET_CONTRIBUYENTE
            + JOIN_FECEC_ESTATUS
            + " LEFT JOIN FECET_RECHAZO_INSUMO rechazo ON (insumo.ID_INSUMO=rechazo.ID_INSUMO)\n"
            + LEFT_FECET_RECHAZO_INSUMO
            + JOIN_FECEC_SUBPROGRAMA
            + JOIN_FECET_DETALLE_CONTRIBUYENTE
            + JOIN_FECEC_EMPLEADO
            + JOIN_FECET_DETALLE_EMPLEADO
            + " WHERE TRUNC(insumo.FECHA_INICIO_PERIODO) >= ? AND TRUNC(insumo.FECHA_FIN_PERIODO) <= ?"
            + " AND empleadodet.ID_CENTRAL=?";

    String REPORTE_INSUMOS_EJECTUTIVO_UNO = "SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy') mes, total\n"
            + " FROM (SELECT to_char(insumo.FECHA_CREACION,'yyyy-mm') MONTH,count(insumo.ID_REGISTRO) total\n"
            + FROM_FECET_INSUMO
            + JOIN_FECET_CONTRIBUYENTE
            + JOIN_FECEC_ESTATUS
            + LEFT_FECET_RECHAZO_INSUMO
            + JOIN_FECEC_SECTOR
            + JOIN_FECEC_SUBPROGRAMA
            + JOIN_FECET_DETALLE_CONTRIBUYENTE
            + JOIN_FECEC_EMPLEADO
            + JOIN_FECET_DETALLE_EMPLEADO
            + "   WHERE  TRUNC(insumo.FECHA_INICIO_PERIODO) >= ? AND TRUNC(insumo.FECHA_FIN_PERIODO) <= ?\n"
            + "   AND empleadodet.ID_CENTRAL=?\n"
            + " GROUP BY to_char(insumo.FECHA_CREACION,'yyyy-mm')\n"
            + " ORDER BY to_char(insumo.FECHA_CREACION,'yyyy-mm') ) mm";

    String REPORTE_INSUMOS_EJECTUTIVO_DOS = FROM_FECET_INSUMO
            + JOIN_FECET_CONTRIBUYENTE
            + JOIN_FECEC_ESTATUS
            + LEFT_FECET_RECHAZO_INSUMO
            + JOIN_FECEC_SECTOR
            + JOIN_FECEC_SUBPROGRAMA
            + JOIN_FECET_DETALLE_CONTRIBUYENTE
            + JOIN_FECEC_EMPLEADO
            + JOIN_FECET_DETALLE_EMPLEADO
            + " WHERE TRUNC(insumo.FECHA_INICIO_PERIODO) >= ? AND TRUNC(insumo.FECHA_FIN_PERIODO) <= ?\n"
            + "      AND empleadodet.ID_CENTRAL=?\n";

    String REPORTE_INSUMOS_EJECTUTIVO_TRES = " FROM (SELECT ";
    String REPORTE_INSUMOS_EJECTUTIVO_TRES_SIGUIENTE = ",to_char(insumo.FECHA_CREACION,'yyyy-mm') MONTH,count(insumo.ID_REGISTRO) total\n"
            + FROM_FECET_INSUMO
            + JOIN_FECET_CONTRIBUYENTE
            + JOIN_FECEC_ESTATUS
            + LEFT_FECET_RECHAZO_INSUMO
            + JOIN_FECEC_SECTOR
            + JOIN_FECEC_SUBPROGRAMA
            + JOIN_FECET_DETALLE_CONTRIBUYENTE
            + JOIN_FECEC_EMPLEADO
            + JOIN_FECET_DETALLE_EMPLEADO
            + " WHERE  TRUNC(insumo.FECHA_INICIO_PERIODO) >= ? AND TRUNC(insumo.FECHA_FIN_PERIODO)<= ?\n"
            + " AND empleadodet.ID_CENTRAL=?\n";
}
