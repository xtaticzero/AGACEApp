package mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.dao.SuspensionDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.mapper.SuspensionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.ContadorPropuestasDaoSql;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;

@Repository("suspensionDAO")
public class SuspensionDAOImpl extends BaseJDBCDao<FecetSuspensionDTO> implements SuspensionDAO {

    private static final long serialVersionUID = 8765037470260320987L;

    private static final String COUNT_FOLIO_ACUERDO = "SELECT COUNT(SUS.ID_SUSPENSION_TIEMPO) TOTAL FROM FECET_SUSPENSIONTIEMPOS SUS\n"
            + "WHERE SUS.NUMEROACUERDOCONCLUSIVO = ?";

    private static final String INSERT_SUSPENSION_X_ACUERDO = "INSERT INTO FECET_SUSPENSIONTIEMPOS \n"
            + "(ID_SUSPENSION_TIEMPO, \n" + "ID_ORDEN, \n" + "FECHA_INICIO_SUSPENSION, \n" + "FECHA_CREACION, \n"
            + "NUMEROACUERDOCONCLUSIVO) \n" + "VALUES(FECEQ_SUSPENSIONTIEMPOS.NEXTVAL, ?, ?, SYSDATE, ?)";

    private static final String FIN_SUSPENSION_X_ACUERDO = "UPDATE FECET_SUSPENSIONTIEMPOS SUS\n"
            + "SET SUS.FECHA_FIN_SUSPENSION = ?,\n" + "SUS.FECHA_BAJA = SYSDATE\n" + "WHERE SUS.ID_ORDEN = ?\n"
            + "AND SUS.ID_OFICIO IS NULL\n" + "AND SUS.ID_OBJETO IS NULL\n" + "AND SUS.NUMEROACUERDOCONCLUSIVO = ?";

    private static final String INSERT_SUSPENSION = "INSERT INTO FECET_SUSPENSIONTIEMPOS (ID_SUSPENSION_TIEMPO, ID_ORDEN, FECHA_INICIO_SUSPENSION, FECHA_FIN_SUSPENSION, FECHA_CREACION, ID_OBJETO,ID_OFICIO) "
            + "VALUES(FECEQ_SUSPENSIONTIEMPOS.NEXTVAL, ?, ?, ?, SYSDATE, ?,?)";
    private static final String SELECT_SUSPENSION_ORDEN = "SELECT FST.ID_SUSPENSION_TIEMPO ID_SUSPENSION_TIEMPO, FST.ID_ORDEN ID_ORDEN, FST.FECHA_INICIO_SUSPENSION FECHA_INICIO_SUSPENSION,\n"
            + "NVL(FST.FECHA_BAJA,NVL(FST.FECHA_FIN_SUSPENSION,SYSDATE+1)) FECHA_FIN_SUSPENSION, FST.FECHA_CREACION FECHA_CREACION, FST.FECHA_BAJA FECHA_BAJA, FST.ID_OBJETO ID_OBJETO,\n "
            + "TO_CHAR(FST.FECHA_INICIO_SUSPENSION,'DD/MM/YYYY ') F1, TO_CHAR(FST.FECHA_FIN_SUSPENSION,'DD/MM/YYYY') F2, FST.ID_OFICIO, FTO.NOMBRE ";
    private static final String SELECT_SUSPENSION = "SELECT ID_SUSPENSION_TIEMPO ID_SUSPENSION_TIEMPO, ID_ORDEN ID_ORDEN, FECHA_INICIO_SUSPENSION FECHA_INICIO_SUSPENSION, FECHA_FIN_SUSPENSION FECHA_FIN_SUSPENSION, FECHA_CREACION FECHA_CREACION, FECHA_BAJA FECHA_BAJA, ID_OBJETO ID_OBJETO, ID_OFICIO  "
            + "FROM FECET_SUSPENSIONTIEMPOS WHERE ID_ORDEN = ? ";
    private static final String UPDATE_FECHA_FIN_SUSPENSION = "UPDATE FECET_SUSPENSIONTIEMPOS SET FECHA_FIN_SUSPENSION = ? WHERE ID_ORDEN = ?  AND FECHA_BAJA IS NULL";

    private static final String UPDATE_FECHA_BAJA_SUSPENSION = "UPDATE FECET_SUSPENSIONTIEMPOS SET FECHA_BAJA = SYSDATE, FECHA_FIN_SUSPENSION = SYSDATE WHERE ID_ORDEN = ? AND ID_OFICIO = ? ";

    private static final String UPDATE_FECHAS_SUSPENSION = "UPDATE FECET_SUSPENSIONTIEMPOS SET FECHA_INICIO_SUSPENSION = ?, FECHA_FIN_SUSPENSION = ?, FECHA_BAJA = ? WHERE ID_SUSPENSION_TIEMPO = ? ";

    @Override
    public List<FecetSuspensionDTO> buscarSuspension(final BigDecimal idOrden) {
        return getJdbcTemplateBase().query(SELECT_SUSPENSION, new SuspensionMapper(), idOrden);
    }

    @Override
    public int guardarSuspensionXAcuerdo(final BigDecimal idOrden, final Date fechaInicioSuspension,
            final Date fechaFinSuspension, final String numeroAcuerdo) {
        if (idOrden != null && numeroAcuerdo != null) {
            if (fechaInicioSuspension != null && fechaFinSuspension == null) {
                return getJdbcTemplateBase().update(INSERT_SUSPENSION_X_ACUERDO, idOrden, fechaInicioSuspension,
                        numeroAcuerdo);
            }
            if (fechaInicioSuspension == null && fechaFinSuspension != null) {
                return getJdbcTemplateBase().update(FIN_SUSPENSION_X_ACUERDO, fechaFinSuspension, idOrden,
                        numeroAcuerdo);
            }
        }

        return 0;

    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionReactivacion(final BigDecimal idOrden) {
        Object[] parameters = {idOrden};
        StringBuilder sql = new StringBuilder(SELECT_SUSPENSION_ORDEN);
        sql.append(" FROM FECET_SUSPENSIONTIEMPOS FST ");
        sql.append(" INNER JOIN FECET_OFICIO FOF ON FOF.ID_OFICIO = FST.ID_OFICIO ");
        sql.append(
                " INNER JOIN FECEC_TIPO_OFICIO FTO ON FTO.ID_TIPO_OFICIO = FOF.ID_TIPO_OFICIO AND (FTO.ID_TIPO_OFICIO IN (1, 2, 16, 8, 18) ");
        sql.append(" OR FTO.ID_AGRUPADOR_TIPO_OFICIO = 3) ");
        sql.append(" WHERE FST.ID_ORDEN = ? AND FST.FECHA_BAJA IS NULL ");
        sql.append(" ORDER BY F1, F2 ASC");
        return getJdbcTemplateBase().query(sql.toString(), new SuspensionMapper(), parameters);
    }

    @Override
    public void guardarSuspension(final BigDecimal idOrden, final Date fechaInicioSuspension,
            final Date fechaFinSuspension, final BigDecimal idOficio) {
        getJdbcTemplateBase().update(INSERT_SUSPENSION, idOrden, fechaInicioSuspension, fechaFinSuspension, 0,
                idOficio);
    }

    @Override
    public void guardarSuspension(final BigDecimal idOrden, final BigDecimal idObjeto, final Date fechaInicioSuspension,
            final Date fechaFinSuspension, final BigDecimal idOficio) {
        getJdbcTemplateBase().update(INSERT_SUSPENSION, idOrden, fechaInicioSuspension, fechaFinSuspension, idObjeto,
                idOficio);
    }

    @Override
    public void guardarFechaFinSuspension(final BigDecimal idOrden, final Date fechaFinSuspension) {
        getJdbcTemplateBase().update(UPDATE_FECHA_FIN_SUSPENSION, fechaFinSuspension, idOrden);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionPorIdTipoOficio(final BigDecimal idOrden,
            final BigDecimal idObjeto) {
        Object[] parameters = {idOrden, idObjeto};
        StringBuilder sql = new StringBuilder(SELECT_SUSPENSION);
        sql.append(" AND ID_OBJETO = ? AND FECHA_BAJA IS NULL").append(" ORDER BY FECHA_INICIO_SUSPENSION ASC ");
        return getJdbcTemplateBase().query(sql.toString(), new SuspensionMapper(), parameters);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionAllPorIdTipoOficio(final BigDecimal idOrden,
            final BigDecimal idObjeto) {
        Object[] parameters = {idOrden, idObjeto};
        StringBuilder sql = new StringBuilder(SELECT_SUSPENSION);
        sql.append(" AND ID_OBJETO = ? ").append(" ORDER BY FECHA_INICIO_SUSPENSION ASC ");
        return getJdbcTemplateBase().query(sql.toString(), new SuspensionMapper(), parameters);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionesPorId(final BigDecimal idOrden) {
        Object[] parameters = {idOrden};
        StringBuilder sql = new StringBuilder(SELECT_SUSPENSION_ORDEN);
        sql.append(" FROM FECET_SUSPENSIONTIEMPOS FST ");
        sql.append(" LEFT JOIN FECET_OFICIO FOF ON FOF.ID_OFICIO = FST.ID_OFICIO ");
        sql.append(" LEFT JOIN FECEC_TIPO_OFICIO FTO ON FTO.ID_TIPO_OFICIO = FOF.ID_TIPO_OFICIO ");
        sql.append(" WHERE FST.ID_ORDEN = ? ");
        sql.append(" ORDER BY FECHA_INICIO_SUSPENSION, FECHA_FIN_SUSPENSION ASC");
        return getJdbcTemplateBase().query(sql.toString(), new SuspensionMapper(), parameters);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionesReactivacion(final BigDecimal idOrden) {
        Object[] parameters = {idOrden};
        StringBuilder sql = new StringBuilder(SELECT_SUSPENSION_ORDEN);
        sql.append(" FROM FECET_SUSPENSIONTIEMPOS FST ");
        sql.append(" INNER JOIN FECET_OFICIO FOF ON FOF.ID_OFICIO = FST.ID_OFICIO ");
        sql.append(
                " INNER JOIN FECEC_TIPO_OFICIO FTO ON FTO.ID_TIPO_OFICIO = FOF.ID_TIPO_OFICIO AND (FTO.ID_TIPO_OFICIO IN (1, 2, 16, 8, 18)) ");
        sql.append(" WHERE FST.ID_ORDEN = ? AND FST.FECHA_BAJA IS NULL ");
        sql.append(" ORDER BY F1, F2 ASC");
        return getJdbcTemplateBase().query(sql.toString(), new SuspensionMapper(), parameters);
    }

    @Override
    public void reactivaPlazoOficio(final BigDecimal idOrden, final BigDecimal idOficio) {
        getJdbcTemplateBase().update(UPDATE_FECHA_BAJA_SUSPENSION, idOrden, idOficio);
    }

    @Override
    public Integer countFolioAcuerdoConclusivo(String numeroAcuerdoConclusivo) {
        if (numeroAcuerdoConclusivo != null && !numeroAcuerdoConclusivo.isEmpty()) {
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(COUNT_FOLIO_ACUERDO, numeroAcuerdoConclusivo);

            while (srs.next()) {
                return srs.getInt(ContadorPropuestasDaoSql.COLUM_TOTAL);
            }
        }
        return 0;
    }

    @Override
    public void actualizarFechas(FecetSuspensionDTO suspension) {
        getJdbcTemplateBase().update(UPDATE_FECHAS_SUSPENSION, suspension.getFechaInicioSuspension(),
                suspension.getFechaFinSuspension(), suspension.getFechaBaja(), suspension.getIdSuspensionTiempo());

    }

}
