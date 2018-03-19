package mx.gob.sat.siat.feagace.modelo.dao.insumos.consultageneral.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.util.Log;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.consultageneral.ConsultaGeneralInsumosDAO;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.InsumoDetalleMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

@Repository("consultaGeneralInsumosDAO")
public class ConsultaGeneralInsumosDAOImpl extends BaseJDBCDao<FecetInsumo> implements ConsultaGeneralInsumosDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<FecetInsumo> consultarInsumosEstatusInicial(List<EmpleadoDTO> empleados, BigDecimal idUnidadAdmonRecepcion) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder rfcs = new StringBuilder();
            sb.append(DETALLE_INSUMO);
            if (empleados != null && !empleados.isEmpty()) {
                sb.append(DETALLE_INSUMO_FILTRO_CREADOR);
                sb.append("(");
                for (EmpleadoDTO empleado : empleados) {
                    rfcs.append("'");
                    rfcs.append(empleado.getRfc());
                    rfcs.append("'");
                    rfcs.append(",");
                }
                sb.append(rfcs.substring(0, rfcs.length() - 1));
                sb.append(") ");
            }
            if (idUnidadAdmonRecepcion.intValue() != -1) {
                sb.append(DETALLE_INSUMO_FILTRO_UNIDADADMON);
                sb.append(idUnidadAdmonRecepcion);
            }
            for (List<FecetInsumo> lstCont : getJdbcTemplateBase().query(sb.toString(), new InsumoDetalleMapper())) {
                orderLstInsumo(lstCont);
                return lstCont;
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return new ArrayList<FecetInsumo>();
    }

    private void orderLstInsumo(List<FecetInsumo> lstResult) {
        Collections.sort(lstResult, new Comparator<FecetInsumo>() {
            @Override
            public int compare(FecetInsumo insumo1, FecetInsumo insumo2) {
                return insumo2.getPrioridad().compareTo(insumo1.getPrioridad());
            }
        });
    }

    @Override
    public List<FecetInsumo> consultarInsumosEstatusAdmon(List<EmpleadoDTO> admons, String estatus, String condicion) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder rfcs = new StringBuilder();
            sb.append(DETALLE_INSUMO);
            sb.append(DETALLE_INSUMO_FILTRO_ADMINISTRADORES);
            sb.append("(");
            for (EmpleadoDTO empleado : admons) {
                rfcs.append("'");
                rfcs.append(empleado.getRfc());
                rfcs.append("'");
                rfcs.append(",");
            }
            sb.append(rfcs.substring(0, rfcs.length() - 1));
            sb.append(") ");
            sb.append(DETALLE_INSUMO_FILTRO_ESTATUS);
            sb.append(" ( ");
            sb.append(estatus);
            sb.append(")");
            sb.append(condicion);
            for (List<FecetInsumo> lstCont : getJdbcTemplateBase().query(sb.toString(), new InsumoDetalleMapper())) {
                orderLstInsumo(lstCont);
                return lstCont;
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return new ArrayList<FecetInsumo>();
    }

    @Override
    public List<FecetInsumo> consultarInsumosEstatusSubAdmon(EmpleadoDTO admon, List<EmpleadoDTO> subAdmons, String estatus, String condicion) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder rfcs = new StringBuilder();
            sb.append(DETALLE_INSUMO);
            sb.append(DETALLE_INSUMO_FILTRO_SUBADMINISTRADORES);
            sb.append("(");
            for (EmpleadoDTO empleado : subAdmons) {
                rfcs.append("'");
                rfcs.append(empleado.getRfc());
                rfcs.append("'");
                rfcs.append(",");
            }
            sb.append(rfcs.substring(0, rfcs.length() - 1));
            sb.append(") ");
            sb.append(DETALLE_INSUMO_FILTRO_SUB_ADMINISTRADORES);
            sb.append("'");
            sb.append(admon.getRfc());
            sb.append("'");
            sb.append(DETALLE_INSUMO_FILTRO_ESTATUS);
            sb.append(" ( ");
            sb.append(estatus);
            sb.append(")");
            sb.append(condicion);

            for (List<FecetInsumo> insumo : getJdbcTemplateBase().query(sb.toString(), new InsumoDetalleMapper())) {
                orderLstInsumo(insumo);
                return insumo;
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return new ArrayList<FecetInsumo>();
    }

}
