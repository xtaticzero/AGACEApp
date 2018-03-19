/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetSuspensionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetSuspensionInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;

/**
 * @author sergio.vaca
 *
 */
@Repository
public class FecetSuspensionInsumoDaoImpl extends BaseJDBCDao<FecetSuspensionInsumo> implements FecetSuspensionInsumoDao {

    private static final long serialVersionUID = 1L;

    @Override
    public void insert(FecetSuspensionInsumo dto) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO FECET_SUSPENSION_INSUMO (ID_SUSPENSION_INSUMO, ID_INSUMO, FECHA_INICIO_SUSPENSION, FECHA_CREACION) VALUES (?, ?, ?, ?) ");
        if (dto.getIdSuspensionInsumo() == null) {
            dto.setIdSuspensionInsumo(obtenerConsecutivo());
            dto.setFechaCreacion(new Date());
        }
        getJdbcTemplateBase().update(query.toString(), dto.getIdSuspensionInsumo(), dto.getIdInsumo(), dto.getFechaInicioSuspension(), new Date());
    }

    @Override
    public void actualizarFechaFinSuspensionInsumo(Date fechaFinSuspension, BigDecimal idSuspensionInsumo) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE FECET_SUSPENSION_INSUMO SET FECHA_FIN_SUSPENSION = ? WHERE ID_SUSPENSION_INSUMO = ? ");
        getJdbcTemplateBase().update(query.toString(), fechaFinSuspension, idSuspensionInsumo);
    }

    @Override
    public List<FecetSuspensionInsumo> obtenerSuspensionByIdInsumo(BigDecimal idInsumo) {
        StringBuilder query = new StringBuilder("SELECT ID_SUSPENSION_INSUMO, ID_INSUMO, FECHA_INICIO_SUSPENSION, FECHA_FIN_SUSPENSION, FECHA_CREACION ");
        query.append("\n FROM FECET_SUSPENSION_INSUMO ");
        query.append("\n WHERE ID_INSUMO = ? ");
        query.append("\n ORDER BY FECHA_CREACION ASC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetSuspensionInsumoMapper(), idInsumo);
    }

    @Override
    public FecetSuspensionInsumo obtenerUltimaSuspension(BigDecimal idInsumo) {
        StringBuilder query = new StringBuilder("SELECT ID_SUSPENSION_INSUMO, ID_INSUMO, FECHA_INICIO_SUSPENSION, FECHA_FIN_SUSPENSION, FECHA_CREACION ");
        query.append("\n FROM FECET_SUSPENSION_INSUMO ");
        query.append("\n WHERE ID_INSUMO = ? ");
        query.append("\n AND FECHA_FIN_SUSPENSION IS NULL ");
        List<FecetSuspensionInsumo> elementos = getJdbcTemplateBase().query(query.toString(), new FecetSuspensionInsumoMapper(), idInsumo);
        return elementos != null && !elementos.isEmpty() ? elementos.get(0) : null;
    }

    @Override
    public FecetSuspensionInsumo obtenerSuspensionByIdAndFechaInicio(BigDecimal idInsumo, Date fechaInicio) {
        StringBuilder query = new StringBuilder("SELECT ID_SUSPENSION_INSUMO, ID_INSUMO, FECHA_INICIO_SUSPENSION, FECHA_FIN_SUSPENSION, FECHA_CREACION ");
        query.append("\n FROM FECET_SUSPENSION_INSUMO ");
        query.append("\n WHERE ID_INSUMO = ? ");
        query.append("\n AND TRUNC(FECHA_INICIO_SUSPENSION) = TRUNC(?) ");
        List<FecetSuspensionInsumo> elementos = getJdbcTemplateBase().query(query.toString(), new FecetSuspensionInsumoMapper(), idInsumo, fechaInicio);
        return elementos != null && !elementos.isEmpty() ? elementos.get(0) : null;
    }

    private BigDecimal obtenerConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_SUSPENSION_INSUMO.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public List<FecetSuspensionInsumo> obtenerSuspensionByRegistroInsumo(String idRegistro) {
        StringBuilder query = new StringBuilder("SELECT SUS.ID_SUSPENSION_INSUMO, SUS.ID_INSUMO, SUS.FECHA_INICIO_SUSPENSION, SUS.FECHA_FIN_SUSPENSION, SUS.FECHA_CREACION ");
        query.append("\n FROM FECET_SUSPENSION_INSUMO SUS ");
        query.append("\n INNER JOIN FECET_INSUMO INS ON INS.ID_INSUMO = SUS.ID_INSUMO ");
        query.append("\n WHERE INS.ID_REGISTRO = ? ");
        query.append("\n ORDER BY ID_SUSPENSION_INSUMO DESC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetSuspensionInsumoMapper(), idRegistro);
    }

    @Override
    public void updateSuspensionInsumo(FecetSuspensionInsumo suspensionInsumo) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE FECET_SUSPENSION_INSUMO SET FECHA_INICIO_SUSPENSION = ?, ");
        query.append(" FECHA_FIN_SUSPENSION = ?,  FECHA_CREACION = ? WHERE ID_SUSPENSION_INSUMO = ?");

        getJdbcTemplateBase().update(query.toString(), suspensionInsumo.getFechaInicioSuspension(),
                suspensionInsumo.getFechaFinSuspension(), suspensionInsumo.getFechaInicioSuspension(),
                suspensionInsumo.getIdSuspensionInsumo());

    }
}
