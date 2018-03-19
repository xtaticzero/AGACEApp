/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

/**
 * @author jose.aguilar
 *
 */
public final class AgaceOrdenHelper {

    private static final String SELECT_COUNT_FECEA_PROPUESTA_ACCION = "   (SELECT COUNT(0) FROM FECET_PROPUESTA P INNER JOIN FECEA_PROPUESTA_ACCION PA ON P.ID_PROPUESTA = PA.ID_PROPUESTA ";

    private AgaceOrdenHelper() {
        super();
    }

    public static String getTableName() {
        return "FECET_ORDEN";
    }

    public static StringBuilder getSqlSelectOrdenContribuyente() {
        StringBuilder sqlSelectOrdenContribuyente = new StringBuilder();

        sqlSelectOrdenContribuyente.append("SELECT FECET_ORDEN.ID_ORDEN, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_METODO, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_REVISION, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.NUMERO_ORDEN, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_CREACION, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_BAJA, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.PRIORIDAD, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FOLIO_NYV, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.CADENA_ORIGINAL, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FIRMA_ELECTRONICA, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_NOTIF_NYV, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_NOTIF_CONT, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_SURTE_EFECTOS, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.DIAS_RESTANTES_PLAZO, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.DIAS_HABILES, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.SUSPENCION_PLAZO, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.DIAS_RESTANTES_DOCUMENTOS, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.SEMAFORO, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_INTEGRA_EXP, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_CONTRIBUYENTE, ");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_ESTATUS,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_AUDITOR,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_FIRMANTE,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_PROPUESTA,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.ID_REGISTRO_PROPUESTA,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_REACTIVAR_PLAZO,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FECHA_SUSPENCION_PLAZO,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.DIAS_RESOLUCION_DEFINITIVA,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.FOLIO_OFICIO,");
        sqlSelectOrdenContribuyente.append("FECET_ORDEN.BLN_COMPULSA, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.RFC, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.NOMBRE, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.REGIMEN, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.SITUACION, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.TIPO, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.SITUACION_DOMICILIO, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.DOMICILIO_FISCAL, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, ");
        sqlSelectOrdenContribuyente.append("FECET_CONTRIBUYENTE.ENTIDAD ");
        sqlSelectOrdenContribuyente.append("FROM ");
        sqlSelectOrdenContribuyente.append(getTableName());
        sqlSelectOrdenContribuyente.append(" INNER JOIN FECET_CONTRIBUYENTE ON FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE = FECET_ORDEN.ID_CONTRIBUYENTE ");

        return sqlSelectOrdenContribuyente;
    }

    public static String getSqlSelectContadorOrdenes() {
        String sqlSelectContadorOrdenes = "SELECT   "
                + " (SELECT COUNT(0) FROM FECET_PROPUESTA P WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 59 AND P.ID_METODO = 1 AND P.RFC_FIRMANTE = ? ) TOTAL_FIRMAR_EHO, "
                + "   (SELECT COUNT(0) FROM FECET_PROPUESTA P WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 59 AND P.ID_METODO = 2 AND P.RFC_FIRMANTE = ? ) TOTAL_FIRMAR_ORG, "
                + "   (SELECT COUNT(0) FROM FECET_PROPUESTA P WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 59 AND P.ID_METODO = 3 AND P.RFC_FIRMANTE = ? ) TOTAL_FIRMAR_UCA, "
                + "   (SELECT COUNT(0) FROM FECET_PROPUESTA P WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 59 AND P.ID_METODO = 4 AND P.RFC_FIRMANTE = ? ) TOTAL_FIRMAR_REE, "
                + "   (SELECT COUNT(0) FROM FECET_PROPUESTA P WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 59 AND P.ID_METODO = 5 AND P.RFC_FIRMANTE = ? ) TOTAL_FIRMAR_MCA, "
                + SELECT_COUNT_FECEA_PROPUESTA_ACCION
                + "WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 57 AND PA.ID_ACCION_ORIGEN IS NULL AND P.ID_METODO = 1 AND P.RFC_FIRMANTE = ? ) TOTAL_VALIDAR_EHO, "
                + SELECT_COUNT_FECEA_PROPUESTA_ACCION
                + "WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 57 AND PA.ID_ACCION_ORIGEN IS NULL AND P.ID_METODO = 2 AND P.RFC_FIRMANTE = ? ) TOTAL_VALIDAR_ORG, "
                + SELECT_COUNT_FECEA_PROPUESTA_ACCION
                + "WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 57 AND PA.ID_ACCION_ORIGEN IS NULL AND P.ID_METODO = 3 AND P.RFC_FIRMANTE = ? ) TOTAL_VALIDAR_UCA, "
                + SELECT_COUNT_FECEA_PROPUESTA_ACCION
                + "WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 57 AND PA.ID_ACCION_ORIGEN IS NULL AND P.ID_METODO = 4 AND P.RFC_FIRMANTE = ? ) TOTAL_VALIDAR_REE, "
                + SELECT_COUNT_FECEA_PROPUESTA_ACCION
                + "WHERE P.FECHA_BAJA IS NULL AND P.ID_ESTATUS = 57 AND PA.ID_ACCION_ORIGEN IS NULL AND P.ID_METODO = 5 AND P.RFC_FIRMANTE = ? ) TOTAL_VALIDAR_MCA "
                + "FROM DUAL ";

        return sqlSelectContadorOrdenes;
    }
}
