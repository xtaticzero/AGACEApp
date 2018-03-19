/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.AgaceOrdenConMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl.AgaceOrdenHelper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FirmanteSeguimientoDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento.EstatusOrdenes;

@Repository("firmanteSeguimientoDao")
public class FirmanteSeguimientoDaoImpl extends BaseJDBCDao<AgaceOrden> implements FirmanteSeguimientoDao {

    @SuppressWarnings("compatibility:-800967012874467725")
    private static final long serialVersionUID = 123453455675678678L;

    /**
     * Metodo getFirmarOrdenesSeguimiento
     *
     * @param idMetodo
     * @return List AgaceOrden
     * @
     */
    @Override
    public List<AgaceOrden> getFirmarOrdenesSeguimiento(
            final BigDecimal idEmpleado) {
        return getOrdenes(idEmpleado,
                EstatusOrdenes.ENVIADO_NOTIFICACION_CONTRIBUYENTE
                .getBigIdEstatus(),
                EstatusOrdenes.NOTIFICADO_AL_CONTRIBUYENTE.getBigIdEstatus());
    }

    /**
     * Metodo getOrdenes
     *
     * @param estadoDocumento
     * @param idMetodo
     * @return List AgaceOrden
     * @
     */
    private List<AgaceOrden> getOrdenes(final BigDecimal idEmpleado, BigDecimal... idsEstatus) {

        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append(" SELECT DISTINCT ORDEN.ID_ORDEN, ORDEN.ID_METODO, ORDEN.ID_REVISION, ORDEN.NUMERO_ORDEN, ORDEN.FECHA_CREACION, ");
        sqlSb.append(" ORDEN.FECHA_BAJA, ORDEN.PRIORIDAD, ORDEN.FOLIO_NYV, ");
        sqlSb.append(" ORDEN.CADENA_ORIGINAL, ORDEN.FIRMA_ELECTRONICA, ORDEN.FECHA_NOTIF_NYV, ORDEN.FECHA_NOTIF_CONT, ");
        sqlSb.append(" ORDEN.FECHA_SURTE_EFECTOS, ORDEN.DIAS_RESTANTES_PLAZO, ORDEN.DIAS_HABILES, ORDEN.SUSPENCION_PLAZO, ");
        sqlSb.append(" ORDEN.DIAS_RESTANTES_DOCUMENTOS, ORDEN.SEMAFORO, ORDEN.FECHA_INTEGRA_EXP, ORDEN.ID_CONTRIBUYENTE, ORDEN.ID_ESTATUS, ");
        sqlSb.append(" ORDEN.ID_AUDITOR, ORDEN.ID_FIRMANTE, ORDEN.ID_PROPUESTA, ORDEN.ID_REGISTRO_PROPUESTA, ORDEN.FOLIO_OFICIO, METODO.ABREVIATURA, ");
        sqlSb.append(" ORDEN.FECHA_REACTIVAR_PLAZO, ORDEN.FECHA_SUSPENCION_PLAZO, ORDEN.DIAS_RESOLUCION_DEFINITIVA, ORDEN.ID_NYV, ");
        sqlSb.append(" METODO.NOMBRE, ");
        sqlSb.append(" CONT.RFC, CONT.NOMBRE NOMBRE_CONTRIBUYENTE, CONT.ENTIDAD, CONT.DOMICILIO_FISCAL, CONT.ACTIVIDAD_PREPONDERANTE, ");
        sqlSb.append(" CONT.REGIMEN, CONT.SITUACION, CONT.SITUACION_DOMICILIO, CONT.TIPO, ESTATUS.MODULO, ESTATUS.DESCRIPCION, ORDEN.BLN_COMPULSA  ");
        sqlSb.append(" FROM ").append(AgaceOrdenHelper.getTableName()).append(" ORDEN ");
        sqlSb.append(" INNER JOIN FECEC_METODO METODO ON ORDEN.ID_METODO = METODO.ID_METODO ");
        sqlSb.append(" INNER JOIN FECET_CONTRIBUYENTE CONT ON ORDEN.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE ");
        sqlSb.append(" INNER JOIN FECEC_ESTATUS ESTATUS ON  ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS  ");
        sqlSb.append(" WHERE FECHA_BAJA IS NULL ");
        sqlSb.append(" AND ORDEN.BLN_COMPULSA = 0 ");
        sqlSb.append(" AND ORDEN.ID_ESTATUS IN (");
        int cont = 0;
        for (BigDecimal estatus : idsEstatus) {
            sqlSb.append(estatus);
            if (cont++ < idsEstatus.length - 1) {
                sqlSb.append(",");
            }
        }
        sqlSb.append(")");
        sqlSb.append(" AND ORDEN.ID_FIRMANTE = ? ");
        sqlSb.append(" ORDER BY ORDEN.FECHA_CREACION DESC");

        logger.debug("FirmanteSeguimientoDaoImp:getOrdenes [{}]", sqlSb.toString());
        return getJdbcTemplateBase().query(sqlSb.toString(), new AgaceOrdenConMetodoMapper(), idEmpleado);

    }
}
