package mx.gob.sat.siat.feagace.modelo.dao.reportes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.RepPistaAuditoriaIntDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.RepPistaAuditInternaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;

@Repository("repPistaAuditoriaIntDao")
public class RepPistaAuditoriaIntDaoImpl extends BaseJDBCDao<ReportePistaAuditoriaInternaDTO> implements RepPistaAuditoriaIntDao {

    @SuppressWarnings("compatibility:-8078448536971961140")
    private static final long serialVersionUID = 4500471540742210598L;

    private static final String SQL_ID_EMPLEADO = "select id_empleado from fecec_empleado where rfc = ? ";

    private static final String SQL_SELECT_REP_INTERNO = " select b.fecha, b.ipmaquina, b.nombremaquina, m.nombre modulo, ac.nombre operacion, b.ID_REGISTRO, b.ID_EMPLEADO "
            + " from fecet_bitacora b, fecea_operaciones o, fecec_modulos m, fecec_acciones ac "
            + " where b.id_operacion = o.id_operacion and o.id_modulo = m.id_modulo "
            + " and o.id_accion = ac.id_accion ";

    private static final String SQL_SELECT_REP_INTERNO2 = " select b.fecha, b.ipmaquina, b.nombremaquina, m.nombre modulo, ac.nombre operacion, b.ID_REGISTRO, b.ID_EMPLEADO \n"
            + " from fecet_bitacora b \n"
            + " inner join fecea_operaciones o on b.id_operacion = o.id_operacion \n"
            + " inner join fecec_modulos m on o.id_modulo = m.id_modulo \n"
            + " inner join fecec_acciones ac on o.id_accion = ac.id_accion \n"
            + " where \n";

    private static final String INICIO_REGISTRO_PROPUESTA = "EP";
    private static final String INICIO_REGISTRO_PROPUESTA2 = "EI";
    private static final String MODULO_INSUMO = "1 ";
    private static final String MODULO_PROPUESTA = "2 ";
    private static final String MODULO_SEGUIMIENTO = "3 ";

    public RepPistaAuditoriaIntDaoImpl() {
        super();
    }

    @Override
    public List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaDTO) {

        List<ReportePistaAuditoriaInternaDTO> list = new ArrayList<ReportePistaAuditoriaInternaDTO>();
        String addSQL = null;
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(SQL_SELECT_REP_INTERNO);

        if (pistaDTO.getIdRegistro() != null) {
            addSQL = " and b.id_registro = ? ";
            list = getJdbcTemplateBase().query(sqlSelect.append(addSQL).toString(), new RepPistaAuditInternaMapper(), pistaDTO.getIdRegistro());
        }

        if (pistaDTO.getNumOreden() != null) {
            addSQL = " and b.id_registro = ? ";
            list = getJdbcTemplateBase().query(sqlSelect.append(addSQL).toString(), new RepPistaAuditInternaMapper(), pistaDTO.getNumOreden());
        }

        if (pistaDTO.getRfcUsuario() != null) {
            addSQL = " and b.id_usuario = ? and TRUNC(b.fecha) BETWEEN TRUNC(?) and TRUNC(?)";
            sqlSelect.append(addSQL);

            logger.info(sqlSelect.toString());

            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditInternaMapper(), pistaDTO.getRfcUsuario(),
                    pistaDTO.getFechaBusqInicio(), pistaDTO.getFechaBusqFin());
        }

        if (pistaDTO.getNomUsuario() != null) {
            addSQL = " and e.nombre like % ? % and b.fecha BETWEEN ? and ?";
            sqlSelect.append(addSQL);

            logger.info(sqlSelect.toString());
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditInternaMapper(), pistaDTO.getNomUsuario(),
                    pistaDTO.getFechaBusqInicio(), pistaDTO.getFechaBusqFin());
        }

        return list;
    }

    public Integer buscaIDEmpleado(ReportePistaAuditoriaInternaDTO pistaDTO) {
        return getJdbcTemplateBase().queryForInt(SQL_ID_EMPLEADO, pistaDTO.getRfcUsuario());
    }

    @Override
    public List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaDTO,
            BigDecimal idEmpleado, BigDecimal idRegistroBuscar) {

        List<ReportePistaAuditoriaInternaDTO> list = new ArrayList<ReportePistaAuditoriaInternaDTO>();
        String addSQL = null;
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(SQL_SELECT_REP_INTERNO2);

        if (pistaDTO.getIdRegistro() != null && idRegistroBuscar != null) {
            if (pistaDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA) || pistaDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA2)) {
                addSQL = " m.id_modulo =  " + MODULO_PROPUESTA + " and (b.id_registro = ? or b.id_registro = ?) and b.id_empleado is not null ORDER BY b.fecha ASC";
            } else {
                addSQL = " m.id_modulo =  " + MODULO_INSUMO + " and (b.id_registro = ? or b.id_registro = ?) and b.id_empleado is not null ORDER BY b.fecha ASC";
            }
            list = getJdbcTemplateBase().query(sqlSelect.append(addSQL).toString(), new RepPistaAuditInternaMapper(), pistaDTO.getIdRegistro(), idRegistroBuscar.toString());
        }

        if (pistaDTO.getNumOreden() != null && idRegistroBuscar != null) {
            addSQL = " m.id_modulo = " + MODULO_SEGUIMIENTO + " and (b.id_registro = ? or b.id_registro = ?) and b.id_empleado is not null ORDER BY b.fecha ASC";
            list = getJdbcTemplateBase().query(sqlSelect.append(addSQL).toString(), new RepPistaAuditInternaMapper(), pistaDTO.getNumOreden(), idRegistroBuscar.toString());
        }

        if (pistaDTO.getRfcUsuario() != null) {
            addSQL = " b.id_usuario = ? and b.id_empleado is not null and TRUNC(b.fecha) BETWEEN TRUNC(?) and TRUNC(?) ORDER BY b.fecha ASC";
            sqlSelect.append(addSQL);

            logger.info(sqlSelect.toString());

            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditInternaMapper(), pistaDTO.getRfcUsuario(),
                    pistaDTO.getFechaBusqInicio(), pistaDTO.getFechaBusqFin());
        }

        if (pistaDTO.getNomUsuario() != null) {
            addSQL = " b.id_empleado = ? and TRUNC(b.fecha) BETWEEN TRUNC(?) and TRUNC(?) ORDER BY b.fecha ASC";
            sqlSelect.append(addSQL);

            logger.info(sqlSelect.toString());
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditInternaMapper(), idEmpleado,
                    pistaDTO.getFechaBusqInicio(), pistaDTO.getFechaBusqFin());
        }

        return list;

    }
}
