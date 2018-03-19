package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececSubprogramaDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl.FecetContribuyenteDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.common.impl.FeceaMetodoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.InformeComiteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.ConsultaInformeComiteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("informeComiteDao")
public class InformeComiteDaoImpl extends BaseJDBCDao<ConsultaInformeComiteRechazoPropuesta>
        implements InformeComiteDao {

    private static final long serialVersionUID = -668588228895327829L;

    private static final StringBuilder SQL_CONDICION_UNO = new StringBuilder(
            "AND UPPER(FECET_PROPUESTA.ID_REGISTRO) LIKE '_I%' ");
    private static final StringBuilder SQL_CONDICION_DOS = new StringBuilder("AND FECET_PROPUESTA.ID_METODO IN (");
    private static final StringBuilder SQL_CONDICION_TRES = new StringBuilder("AND FECET_PROPUESTA.ID_ESTATUS IN (");
    private static final String SALTO_LINEA = "'\n";

    public List<ConsultaInformeComiteRechazoPropuesta> buscarInformeComite(String rfc, String idEntidad,
            String idActividadPreponderante, String condicionEmpleado, Date fechaInicio, Date fechaFin,
            String idRegistro) {
        StringBuilder sql = new StringBuilder();
        sql.append(sqlSelect());
        sql.append(SQL_CONDICION_UNO);
        sql.append(SQL_CONDICION_DOS);
        sql.append(Constantes.EHO);
        sql.append(", ");
        sql.append(Constantes.UCA);
        sql.append(", ");
        sql.append(Constantes.REE);
        sql.append(", ");
        sql.append(Constantes.MCA);
        sql.append(") \n");
        sql.append(SQL_CONDICION_TRES);
        sql.append(Constantes.ESTATUS_REGISTRADA);
        sql.append(", ");
        sql.append(Constantes.ESTATUS_CONFIRMADO_POR_REGIONAL);
        sql.append(") \n");
        sql.append(condicionEmpleado);
        sql.append(condicionRfc(rfc));
        sql.append(condicionEntidad(idEntidad));
        sql.append(condicionActividadPreponderante(idActividadPreponderante));
        sql.append(condicionRegistro(idRegistro));
        sql.append(condicionFechaInfComite(fechaInicio, fechaFin));
        return getJdbcTemplateBase().query(sql.toString(), new ConsultaInformeComiteMapper());
    }

    private String sqlSelect() {
        StringBuilder sql = new StringBuilder();

        sql.append(
                "Select FECET_PROPUESTA.ID_REGISTRO, FECET_CONTRIBUYENTE.RFC, FECET_CONTRIBUYENTE.NOMBRE, FECET_CONTRIBUYENTE.REGIMEN, \n");
        sql.append(
                "FECEC_SUBPROGRAMA.CLAVE SUBPROGRAMA_CLAVE, FECEC_SUBPROGRAMA.DESCRIPCION SUBPROGRAMA_DESCRIPCION, FECET_CONTRIBUYENTE.ENTIDAD, FECET_CONTRIBUYENTE.TIPO TIPO_CONTRIBUYENTE, \n");
        sql.append(
                "FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, FECET_PROPUESTA.FECHA_INFORME, FECET_PROPUESTA.RFC_CREACION, FECET_DOC_EXPEDIENTE.RUTA_ARCHIVO \n");
        sql.append("FROM ");
        sql.append(FecetContribuyenteDaoImpl.getTableName());
        sql.append("\nINNER JOIN ");
        sql.append(PropuestasSQL.NAME_TABLE);
        sql.append(" ON FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE = FECET_PROPUESTA.ID_CONTRIBUYENTE \n");
        sql.append("INNER JOIN ");
        sql.append(FececSubprogramaDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_SUBPROGRAMA = FECEC_SUBPROGRAMA.ID_SUBPROGRAMA \n");
        sql.append("INNER JOIN ");
        sql.append(FecetDocExpedienteDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_PROPUESTA = FECET_DOC_EXPEDIENTE.ID_PROPUESTA \n");
        sql.append("INNER JOIN ");
        sql.append(FeceaMetodoDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_METODO = FECEC_METODO.ID_METODO \n");
        sql.append("WHERE 1=1 ");

        return sql.toString();
    }

    private StringBuilder condicionRfc(String rfc) {
        StringBuilder sql = new StringBuilder();
        if (!(rfc == null || rfc.isEmpty())) {
            sql.append(" AND UPPER(FECET_CONTRIBUYENTE.RFC) = '");
            sql.append(rfc.toUpperCase());
            sql.append(SALTO_LINEA);
        }
        return sql;
    }

    private StringBuilder condicionEntidad(String idEntidad) {
        StringBuilder sql = new StringBuilder();

        if (!(idEntidad == null || idEntidad.equals(Constantes.COMBO_SELECCIONA_CADENA))) {
            sql.append(" AND UPPER(FECET_CONTRIBUYENTE.ENTIDAD) = '");
            sql.append(idEntidad.toUpperCase());
            sql.append(SALTO_LINEA);
        }
        return sql;
    }

    private StringBuilder condicionActividadPreponderante(String idActividadPreponderante) {
        StringBuilder sql = new StringBuilder();

        if (!(idActividadPreponderante == null || idActividadPreponderante.equals("-1"))) {
            sql.append(" AND FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE = '");
            sql.append(idActividadPreponderante);
            sql.append(SALTO_LINEA);
        }
        return sql;
    }

    private StringBuilder condicionRegistro(String idRegistro) {
        StringBuilder sql = new StringBuilder();

        if (!(idRegistro == null || idRegistro.isEmpty())) {
            sql.append(" AND FECET_PROPUESTA.ID_REGISTRO = '");
            sql.append(idRegistro);
            sql.append(SALTO_LINEA);
        }
        return sql;
    }

    private StringBuilder condicionFechaInfComite(Date fechaInicio, Date fechaFin) {
        StringBuilder sql = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (fechaInicio != null && fechaFin != null) {
            sql.append(" AND FECET_PROPUESTA.FECHA_INFORME BETWEEN TO_DATE('");
            sql.append(sdf.format(fechaInicio));
            sql.append("','YYYY-MM-DD') AND TO_DATE('");
            sql.append(sdf.format(fechaFin));
            sql.append("','YYYY-MM-DD')\n");
        } else if (fechaInicio != null) {
            sql.append(" AND FECET_PROPUESTA.FECHA_INFORME = TO_DATE('");
            sql.append(sdf.format(fechaInicio));
            sql.append("','YYYY-MM-DD')");
            sql.append("\n");
        } else if (fechaFin != null) {
            sql.append(" AND FECET_PROPUESTA.FECHA_INFORME = TO_DATE('");
            sql.append(sdf.format(fechaFin));
            sql.append("','YYYY-MM-DD')");
            sql.append("\n");
        }
        return sql;
    }

}
