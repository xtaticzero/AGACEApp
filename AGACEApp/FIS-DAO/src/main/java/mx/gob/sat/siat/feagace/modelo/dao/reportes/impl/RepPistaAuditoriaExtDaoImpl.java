package mx.gob.sat.siat.feagace.modelo.dao.reportes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.RepPistaAuditoriaExtDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.RepPistaAuditExternaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;

@Repository("repPistaAuditoriaExtDao")
public class RepPistaAuditoriaExtDaoImpl extends BaseJDBCDao<ReportePistaAuditoriaExternaDTO> implements RepPistaAuditoriaExtDao {

    @SuppressWarnings("compatibility:-7696495863977542794")
    private static final long serialVersionUID = -2988211474864062853L;

    private static final String SQL_QUERY = "b.id_usuario = ? and b.id_empleado is null and TRUNC(b.fecha) BETWEEN TRUNC(?) and TRUNC(?) ORDER BY b.fecha ASC";

    private static final String SQL_SELECT_PISTA_EXT_BASE = "select distinct b.id_registro, b.fecha, \n";
    private static final String SQL_SELECT_PISTA_EXT_BASE2 = "select distinct b.id_registro, b.fecha, con.rfc, \n";
    private static final String SQL_SELECT_APO_LEGAL = "b.id_usuario APO_LEGAL,\n";
    private static final String SQL_SELECT_REP_LEGAL = "b.id_usuario REP_LEGAL,\n";
    private static final String SQL_SELECT_APO_LEGAL_REP_LEGAL = "b.id_usuario APO_LEGAL_REP_LEGAL,\n";
    private static final String SQL_SELECT_AGE_ADUANAL = "b.id_usuario AGE_ADUANAL,\n";
    private static final String SQL_SELECT_CONTRIBUYENTE = "b.id_usuario RFC,\n";

    private static final String SQL_SELECT_PISTA_EXT_3 = " b.ipmaquina, b.nombremaquina, m.nombre modulo, ac.nombre operacion \n"
            + "from \n"
            + "fecet_bitacora b \n"
            + "inner join fecea_operaciones o on b.id_operacion = o.id_operacion \n"
            + "inner join FECET_CONTRIBUYENTE con on b.id_usuario = con.rfc \n"
            + "inner join fecec_modulos m on o.id_modulo = m.id_modulo \n"
            + "inner join fecec_acciones ac on o.id_accion = ac.id_accion \n"
            + "where \n";

    private static final String SQL_SELECT_PISTA_EXT_4 = "b.ipmaquina, b.nombremaquina, m.nombre modulo, ac.nombre operacion from \n"
            + "fecet_bitacora b "
            + "inner join fecea_operaciones o on b.id_operacion = o.id_operacion \n"
            + "inner join FECET_CONTRIBUYENTE con on b.id_usuario = con.rfc \n"
            + "inner join fecec_modulos m on o.id_modulo = m.id_modulo \n"
            + "inner join fecec_acciones ac on o.id_accion = ac.id_accion \n"
            + "where \n";

    private static final String INICIO_REGISTRO_PROPUESTA = "EP";
    private static final String INICIO_REGISTRO_PROPUESTA2 = "EI";
    private static final String MODULO_INSUMO = "1 ";
    private static final String MODULO_PROPUESTA = "2 ";
    private static final String MODULO_SEGUIMIENTO = "3 ";

    public RepPistaAuditoriaExtDaoImpl() {
        super();
    }

    @Override
    public List<ReportePistaAuditoriaExternaDTO> buscaPistaAuditoriaExterna(ReportePistaAuditoriaExternaDTO pistaExterDTO, BigDecimal idRegistroBucar) {

        List<ReportePistaAuditoriaExternaDTO> list = new ArrayList<ReportePistaAuditoriaExternaDTO>();
        StringBuilder sqlSelect = new StringBuilder();

        if (pistaExterDTO.getIdRegistro() != null && idRegistroBucar != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE2);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_3);

            if (pistaExterDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA) || pistaExterDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA2)) {
                sqlSelect.append(" m.id_modulo =  ").append(MODULO_PROPUESTA);
            } else {
                sqlSelect.append(" m.id_modulo =  ").append(MODULO_INSUMO);
            }

            sqlSelect.append(" and (b.id_registro = ? or b.id_registro = ? ) and b.id_empleado is null ORDER BY b.fecha ASC");
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getIdRegistro(), idRegistroBucar.toString());
        }

        if (pistaExterDTO.getNumOreden() != null && idRegistroBucar != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE2);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_3);
            sqlSelect.append(" m.id_modulo =  ").append(MODULO_SEGUIMIENTO);
            sqlSelect.append(" and (b.id_registro = ? or b.id_registro = ? ) and b.id_empleado is null ORDER BY b.fecha ASC");
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getNumOreden(), idRegistroBucar.toString());
        }

        if (pistaExterDTO.getRfcAgentAduanal() != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE);
            sqlSelect.append(SQL_SELECT_AGE_ADUANAL);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_4);
            sqlSelect.append(SQL_QUERY);
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getRfcAgentAduanal(),
                    pistaExterDTO.getFechaBusqInicio(), pistaExterDTO.getFechaBusqFin());
        }

        if (pistaExterDTO.getRfcApodLegal() != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE);
            sqlSelect.append(SQL_SELECT_APO_LEGAL);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_4);
            sqlSelect.append(SQL_QUERY);
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getRfcApodLegal(), pistaExterDTO.getRfcApodLegal(),
                    pistaExterDTO.getFechaBusqInicio(), pistaExterDTO.getFechaBusqFin());
        }

        if (pistaExterDTO.getRfcApodLegalRepLegal() != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE);
            sqlSelect.append(SQL_SELECT_APO_LEGAL_REP_LEGAL);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_4);
            sqlSelect.append(SQL_QUERY);
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getRfcApodLegalRepLegal(), pistaExterDTO.getRfcApodLegalRepLegal(),
                    pistaExterDTO.getFechaBusqInicio(), pistaExterDTO.getFechaBusqFin());
        }

        if (pistaExterDTO.getRfcContribuyente() != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE);
            sqlSelect.append(SQL_SELECT_CONTRIBUYENTE);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_4);
            sqlSelect.append(SQL_QUERY);
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getRfcContribuyente(),
                    pistaExterDTO.getFechaBusqInicio(), pistaExterDTO.getFechaBusqFin());
        }

        if (pistaExterDTO.getRfcRepLegal() != null) {
            sqlSelect.append(SQL_SELECT_PISTA_EXT_BASE);
            sqlSelect.append(SQL_SELECT_REP_LEGAL);
            sqlSelect.append(SQL_SELECT_PISTA_EXT_4);
            sqlSelect.append(SQL_QUERY);
            list = getJdbcTemplateBase().query(sqlSelect.toString(), new RepPistaAuditExternaMapper(), pistaExterDTO.getRfcRepLegal(),
                    pistaExterDTO.getFechaBusqInicio(), pistaExterDTO.getFechaBusqFin());
        }
        return list;
    }
}
