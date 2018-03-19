/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.DocumentoVistaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ValidarFirmarDoctoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropuestaResumenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetRetroContadorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.ResumenPropuestasFirmanteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenPropuestasFirmante;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposEstatusPropuestaEnum;

/**
 * @author sergio.vaca
 *
 */
@Repository("validarFirmarDoctoDaoImpl")
public class ValidarFirmarDoctoDaoImpl extends BaseJDBCDao<FecetPropuesta> implements ValidarFirmarDoctoDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int NUM_16 = 16;

    @Override
    public List<ResumenPropuestasFirmante> obtenerResumenPropuestasFirmante(String rfcFirmante) {
        Object[] params = new Object[NUM_16];
        for (int i = 0; i < params.length; i++) {
            params[i] = rfcFirmante;
        }
        return getJdbcTemplateBase().query(PropuestasSQL.SQL_RESUMEN_PROPUESTAS_FIRMANTE, params,
                new ResumenPropuestasFirmanteMapper());
    }

    @Override
    public List<FecetPropuesta> obtenerInformadasValidar(String rfc, TiposEstatusPropuestaEnum estatusPropuesta) {
        List<FecetPropuesta> resultados = new ArrayList<FecetPropuesta>();
        List<Object> parametros = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_DETALLE_HEADER);
        switch (estatusPropuesta) {
            case RETROALIMENTADAS_INFORMADAS:
            case REVISION_INFORMADA:
                query.append(generaWhereResumenDetalle(estatusPropuesta.getEstatus()));
                break;
            default:
                if (estatusPropuesta.getIdAccionOrigen() == null) {
                    query.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_ACCION_NULL);
                } else {
                    query.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_ACCION);
                    parametros.add(estatusPropuesta.getIdAccionOrigen());
                }
                query.append(generaWhereResumenDetalle(estatusPropuesta.getEstatus()));
                break;
        }
        parametros.add(rfc);
        query.append("\n ORDER BY PROPUESTA.PRIORIDAD ASC, PROPUESTA.ID_METODO ASC, PROPUESTA.FECHA_CREACION ASC");
        resultados.addAll(
                getJdbcTemplateBase().query(query.toString(), new FecetPropuestaResumenMapper(), parametros.toArray()));
        return resultados;
    }

    public List<FecetRetroContador> obtenerRetroByIdPropuesta(BigDecimal idPropuesta, TipoEstatusEnum estatus,
            BigDecimal blnEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(
                " SELECT RETRO.ID_RETROALIMENTACION, RETRO.ID_PROPUESTA, RETRO.ID_MOTIVO, RETRO.DESCRIPCION DETALLE, RETRO.FECHA_RETROALIMENTACION, ");
        query.append(
                "\n (SELECT COUNT(*) FROM FECET_DOC_RETRO_PROPUESTA WHERE RETRO.ID_RETROALIMENTACION = ID_RETROALIMENTACION AND BLN_ACTIVO = RETRO.BLN_ESTATUS) AS TOTAL_DOCUMENTOS, ");
        query.append("\n MOTIVO.DESCRIPCION MOTIVO, MOTIVO.TIPO_MOTIVO ");
        query.append("\n FROM FECET_RETROALIMENTACION RETRO ");
        query.append("\n INNER JOIN FECEC_MOTIVO MOTIVO ON MOTIVO.ID_MOTIVO = RETRO.ID_MOTIVO ");
        query.append("\n WHERE RETRO.ID_PROPUESTA = ? ");
        query.append("\n AND RETRO.ID_ESTATUS = ? ");
        query.append("\n AND RETRO.BLN_ESTATUS = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroContadorMapper(), idPropuesta,
                estatus.getId(), blnEstatus);
    }

    public List<DocumentoVista> obtenerDoctosRetroByIdRetro(BigDecimal idRetroalimentacion, BigDecimal blnActivo) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT RUTA_ARCHIVO, FECHA_CREACION ");
        query.append("\n FROM FECET_DOC_RETRO_PROPUESTA ");
        query.append("\n WHERE ID_RETROALIMENTACION = ? ");
        query.append("\n AND BLN_ACTIVO = ? ");
        return getJdbcTemplateBase().query(query.toString(), new DocumentoVistaMapper(), idRetroalimentacion,
                blnActivo);
    }

    @Override
    public void actualizarEstatus(BigDecimal idPropuesta, int idEstatus, BigDecimal idArace) {
        List<Object> parametros = new ArrayList<Object>();
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE FECET_PROPUESTA SET ID_ESTATUS = ? ");
        parametros.add(idEstatus);
        if (idArace != null) {
            query.append(", ID_ARACE = ? ");
            parametros.add(idArace);
        }
        query.append(" WHERE ID_PROPUESTA = ? ");
        parametros.add(idPropuesta);
        getJdbcTemplateBase().update(query.toString(), parametros.toArray());

    }

    @Override
    public void actualizarAuditorFirma(BigDecimal idPropuesta) {
        List<Object> parametros = new ArrayList<Object>();
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE FECET_PROPUESTA SET RFC_AUDITOR = '' , RFC_FIRMANTE = '' ");
        query.append(" WHERE ID_PROPUESTA = ? ");
        parametros.add(idPropuesta);
        getJdbcTemplateBase().update(query.toString(), parametros.toArray());

    }

    private String generaWhereResumenDetalle(List<BigDecimal> estatus) {
        StringBuilder where = new StringBuilder("\n WHERE PROPUESTA.RFC_FIRMANTE = ? ");
        where.append("\n AND PROPUESTA.FECHA_BAJA IS NULL");
        where.append("\n AND PROPUESTA.ID_ESTATUS IN (");
        Iterator<BigDecimal> iterador = estatus.iterator();
        while (iterador.hasNext()) {
            where.append(iterador.next());
            if (iterador.hasNext()) {
                where.append(", ");
            }
        }
        where.append(" ) ");
        return where.toString();
    }

    @Override
    public void apagarRetroalimentacion(BigDecimal idRetroalimentacion) {
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE FECET_RETROALIMENTACION SET BLN_ESTATUS = 0 ");
        query.append(" WHERE ID_RETROALIMENTACION = ? ");
        getJdbcTemplateBase().update(query.toString(), idRetroalimentacion);
    }

    @Override
    public void actualizarAdminSubadminOrdenes(BigDecimal idPropuesta) {
        List<Object> parametros = new ArrayList<Object>();
        StringBuilder query = new StringBuilder();
        query.append(" UPDATE FECET_PROPUESTA SET RFC_ADMINISTRADOR = '' , RFC_SUBADMINISTRADOR = '' ");
        query.append(" WHERE ID_PROPUESTA = ? ");
        parametros.add(idPropuesta);
        getJdbcTemplateBase().update(query.toString(), parametros.toArray());

    }
}
