package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetCancelacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetCancelacionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocCancelacionMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCanceladaPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetCancelacionDao")
public class FecetCancelacionDaoImpl extends BaseJDBCDao<FecetPropCancelada> implements FecetCancelacionDao {

    private static final long serialVersionUID = 9205675900116283858L;

    private String getTableName() {
        return "FECET_CANCELACION";
    }

    @Override
    public FecetPropCanceladaPk insert(FecetPropCancelada dto) {
        if (dto.getIdCancelacionPropuesta() == null) {
            dto.setIdCancelacionPropuesta(getJdbcTemplateBase()
                    .queryForObject("SELECT FECEQ_CANCELACION.NEXTVAL FROM DUAL", BigDecimal.class));
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(
                " (ID_CANCELACION, ID_PROPUESTA, ID_CAUSA, OBSERVACIONES, FECHA_CREACION, RFC_CANCELACION, FECHA_CANCELACION, FECHA_RECHAZO, DESCRIPCION_RECHAZO, ID_EMPLEADO, ID_ESTATUS, BLN_ESTATUS ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdCancelacionPropuesta(), dto.getIdPropuesta(),
                dto.getIdCausa(), dto.getObservaciones(), dto.getFechaCreacion(), dto.getRfcCreacion(),
                dto.getFechaCancelacion(), dto.getFechaRechazo(), dto.getDescripcionRechazo(), dto.getIdEmpleado(),
                dto.getIdEstatus(), dto.getBlnEstatus());

        return dto.createPk();
    }

    @Override
    public FecetPropCanceladaPk insertDocCancelacion(FecetPropCancelada dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append("FECET_DOC_CANCELACION");
        query.append(
                " (ID_DOC_CANCELACION, ID_CANCELACION, RUTA_ARCHIVO, FECHA_CREACION, FECHA_FIN, BLN_ACTIVO ) VALUES (?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocCancelacion(), dto.getIdCancelacionPropuesta(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getFechaFin(), dto.getBlnActivo());

        return dto.createPk();
    }

    @Override
    public void updateEstatusCancelacion(BigDecimal idPopuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_CANCELACION SET BLN_ESTATUS = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPopuesta);
    }

    @Override
    public void updateEstatusDocCancelacion(BigDecimal idCancelacion) {
        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_CANCELACION SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_UNO);
        query.append(" WHERE ID_CANCELACION = ? ");

        getJdbcTemplateBase().update(query.toString(), idCancelacion);
    }

    public List<FecetCancelacion> obtenerJustificacionCancelacion(BigDecimal idPropuesta, TipoEstatusEnum idEstatus,
            BigDecimal estatusCancelacion) {
        StringBuilder query = new StringBuilder();

        query.append(
                "\n SELECT CANCELACION.ID_CANCELACION, CANCELACION.ID_PROPUESTA, CANCELACION.ID_CAUSA, CANCELACION.OBSERVACIONES, ");
        query.append(
                "\n CANCELACION.FECHA_CREACION, CANCELACION.RFC_CANCELACION, CANCELACION.FECHA_CANCELACION, CANCELACION.FECHA_RECHAZO, ");
        query.append(
                "\n CANCELACION.DESCRIPCION_RECHAZO, CANCELACION.ID_EMPLEADO, CANCELACION.ID_ESTATUS, CANCELACION.BLN_ESTATUS, ");
        query.append("\n MOTIVO.ID_MOTIVO, MOTIVO.DESCRIPCION, MOTIVO.TIPO_MOTIVO, ");
        query.append(
                "\n (SELECT COUNT(*) FROM FECET_DOC_CANCELACION WHERE ID_CANCELACION = CANCELACION.ID_CANCELACION AND BLN_ACTIVO = CANCELACION.BLN_ESTATUS) AS TOTAL_DOCUMENTOS ");
        query.append("\n FROM FECET_CANCELACION CANCELACION ");
        query.append("\n JOIN FECEC_MOTIVO MOTIVO ON MOTIVO.ID_MOTIVO = CANCELACION.ID_CAUSA ");
        query.append("\n WHERE CANCELACION.ID_PROPUESTA = ? ");
        query.append("\n AND CANCELACION.ID_ESTATUS = ? ");
        query.append("\n AND CANCELACION.BLN_ESTATUS = ? ");

        return getJdbcTemplateBase().query(query.toString(), new FecetCancelacionMapper(), idPropuesta,
                idEstatus.getId(), estatusCancelacion);
    }

    public List<FecetDocCancelacion> obtenerDoctosCancelacionById(BigDecimal idCancelacion, BigDecimal estatus) {
        StringBuilder query = new StringBuilder();
        query.append(
                "\n SELECT ID_DOC_CANCELACION, ID_CANCELACION, RUTA_ARCHIVO, FECHA_CREACION, FECHA_FIN, BLN_ACTIVO ");
        query.append("\n FROM FECET_DOC_CANCELACION ");
        query.append("\n WHERE ID_CANCELACION = ? ");
        query.append("\n AND BLN_ACTIVO = ?");

        return getJdbcTemplateBase().query(query.toString(), new FecetDocCancelacionMapper(), idCancelacion, estatus);
    }

    @Override
    public List<FecetDocCancelacion> obtenerDoctosAllCancelacionById(BigDecimal idCancelacion) {
        StringBuilder query = new StringBuilder();
        query.append(
                "\n SELECT ID_DOC_CANCELACION, ID_CANCELACION, RUTA_ARCHIVO, FECHA_CREACION, FECHA_FIN, BLN_ACTIVO ");
        query.append("\n FROM FECET_DOC_CANCELACION ");
        query.append("\n WHERE ID_CANCELACION = ? ");

        return getJdbcTemplateBase().query(query.toString(), new FecetDocCancelacionMapper(), idCancelacion);
    }

    @Override
    public BigDecimal getConsecutivoDocCancelacion() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_CANCELACION.NEXTVAL FROM DUAL", BigDecimal.class);
    }

}
