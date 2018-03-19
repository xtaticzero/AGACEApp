package mx.gob.sat.siat.feagace.modelo.dao.common.tiempos.dao.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.tiempos.dao.TiemposDAO;
import mx.gob.sat.siat.feagace.modelo.dao.common.tiempos.mapper.TiemposMapper;
import mx.gob.sat.siat.feagace.modelo.dao.util.ConsultasUtil;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;

/**
 * Clase para acceso a bd para Tiempos/Plazos
 *
 * @auhtor eolf
 */
@Repository("tiemposDAO")
public class TiemposDAOImpl extends BaseJDBCDao<TiempoDTO> implements TiemposDAO {

    private static final long serialVersionUID = 1L;

    private static final String TABLE_NAME = "FECEC_TIEMPOS T";

    /**
     * Este atributo es un SELECT para seleccionadar las columnas de la tabla
     * FECEC_TIEMPOS
     */
    private static final StringBuilder SQL_SELECT = new StringBuilder(
            "SELECT T.ID_TIEMPO ID_TIEMPO, T.CLAVE CLAVE, T.ID_TIPO_TIEMPO ID_TIPO_TIEMPO, T.ID_AGRUPADOR_TIEMPO ID_AGRUPADOR_TIEMPO, T.TIEMPO TIEMPO, T.ID_TIPO_PLAZO ID_TIPO_PLAZO, T.ID_METODO ID_METODO, T.ID_OBJETO ID_OBJETO, T.FECHA_CREACION FECHA_CREACION ");

    private static final String SQL_FROM = " FROM ";
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_JOIN = " JOIN ";
    private static final String SQL_AND = " AND ";
    private static final String FECHA_BAJA = " T.FECHA_BAJA IS NULL ";
    private static final String SEMAFORO = " T.CLAVE  IN ('SEMA','SEMN','SEMR') ";
    private static final String SEMAFORO_INSUMOS = " T.CLAVE  IN ('SEMINA','SEMINN','SEMINR') ";

    public TiemposDAOImpl() {
        super();
    }

    @Override
    public TiempoDTO obtenerTiempoPlazo(final BigDecimal idMetodo, final String cvePlazo, final String cveTipoTiempo) {
        logger.debug("[obtenerTiempoPlazo]");
        List<TiempoDTO> plazos = Collections.emptyList();
        TiempoDTO plazo = null;
        Object[] parameters = {cveTipoTiempo, idMetodo, cvePlazo};
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(",TT.CLAVE TTCLAVE ").append(SQL_FROM).append(TABLE_NAME).append(SQL_JOIN)
                .append(" FECEC_TIPOTIEMPOS TT ON T.ID_TIPO_TIEMPO =TT.ID_TIPO_TIEMPO ").append(SQL_WHERE)
                .append(" TT.CLAVE= ? ").append(SQL_AND).append(" T.ID_METODO = ? ").append(SQL_AND)
                .append(" T.CLAVE= ? ").append(SQL_AND).append(FECHA_BAJA);
        logger.info(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        plazos = getJdbcTemplateBase().query(sql.toString(), new TiemposMapper(), parameters);
        if (!plazos.isEmpty()) {
            plazo = plazos.get(0);
        }
        return plazo;
    }

    @Override
    public List<TiempoDTO> obtenerTiempoPlazos(final String cvePlazo, final String cveTipoTiempo) {
        logger.debug("[obtenerTiempoPlazos]");
        Object[] parameters = {cvePlazo, cveTipoTiempo};
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(", TT.CLAVE TTCLAVE ").append(SQL_FROM).append(TABLE_NAME).append(SQL_JOIN)
                .append(" FECEC_TIPOTIEMPOS TT ON T.ID_TIPO_TIEMPO =TT.ID_TIPO_TIEMPO ").append(SQL_WHERE)
                .append(" T.CLAVE= ? ").append(SQL_AND).append(" TT.CLAVE= ? ").append(SQL_AND).append(FECHA_BAJA);
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        return getJdbcTemplateBase().query(sql.toString(), new TiemposMapper(), parameters);
    }

    @Override
    public TiempoDTO obtenerTiempoPlazoOficio(final BigDecimal idMetodo, final BigDecimal idTipoOficio,
            final String cvePlazo, final String cveTipoTiempo) {
        logger.debug("[obtenerTiempoPlazo]");
        List<TiempoDTO> plazos = Collections.emptyList();
        Object[] parameters = {cveTipoTiempo, idMetodo, cvePlazo, idTipoOficio};
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(",TT.CLAVE TTCLAVE ").append(SQL_FROM).append(TABLE_NAME).append(SQL_JOIN)
                .append(" FECEC_TIPOTIEMPOS TT ON T.ID_TIPO_TIEMPO = TT.ID_TIPO_TIEMPO ").append(SQL_WHERE)
                .append(" TT.CLAVE = ? ").append(SQL_AND).append(" T.ID_METODO = ? ").append(SQL_AND)
                .append(" T.CLAVE = ? ").append(SQL_AND).append(" T.ID_OBJETO = ? ").append(SQL_AND).append(FECHA_BAJA);
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        plazos = getJdbcTemplateBase().query(sql.toString(), new TiemposMapper(), parameters);
        if (!plazos.isEmpty()) {
            return plazos.get(0);
        }
        return null;
    }

    @Override
    public TiempoDTO obtenerTiempoPlazo(final String cvePlazo, final String cveTipoTiempo) {
        logger.debug("[obtenerTiempoPlazo]");
        List<TiempoDTO> plazos = Collections.emptyList();
        TiempoDTO plazo = new TiempoDTO();
        Object[] parameters = {cvePlazo, cveTipoTiempo};
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(", TT.CLAVE TTCLAVE ").append(SQL_FROM).append(TABLE_NAME).append(SQL_JOIN)
                .append(" FECEC_TIPOTIEMPOS TT ON T.ID_TIPO_TIEMPO = TT.ID_TIPO_TIEMPO ").append(SQL_WHERE)
                .append(" T.CLAVE = ? ").append(SQL_AND).append(" TT.CLAVE = ? ").append(SQL_AND).append(FECHA_BAJA);
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        plazos = getJdbcTemplateBase().query(sql.toString(), new TiemposMapper(), parameters);
        if (!plazos.isEmpty()) {
            plazo = plazos.get(0);
        }
        return plazo;
    }

    @Override
    public List<TiempoDTO> obtenerValoresDeSemaforo(final int idMetodo) {
        logger.debug("[obtenerValorSemaforo]");
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(SQL_FROM).append(TABLE_NAME).append(SQL_WHERE).append(FECHA_BAJA).append(SQL_AND)
                .append(idMetodo == TipoMetodoEnum.INS.getId() ? SEMAFORO_INSUMOS : SEMAFORO).append(SQL_AND)
                .append(" t.ID_METODO = ? ");
        return getJdbcTemplateBase().query(sql.toString(), new TiemposMapper(), idMetodo);

    }
}
