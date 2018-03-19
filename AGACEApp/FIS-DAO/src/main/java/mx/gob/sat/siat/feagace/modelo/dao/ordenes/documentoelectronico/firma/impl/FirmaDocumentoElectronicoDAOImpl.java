package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentoelectronico.firma.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.EmpleadoSuplenteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentoelectronico.firma.FirmaDocumentoElectronicoDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

@Repository("firmaDocumentoElectronicoDAO")
public class FirmaDocumentoElectronicoDAOImpl extends BaseJDBCDao<FirmaDTO> implements FirmaDocumentoElectronicoDAO {

    private static final long serialVersionUID = 9876569953231231L;

    private static final int ESTATUS_ACTIVO_SUPLENCIA = 1;

    @Override
    public void guardarFirma(FirmaDTO firma) {
        StringBuilder sql = new StringBuilder();
        String update = "UPDATE FECET_ORDEN SET CADENA_ORIGINAL = ?, FIRMA_ELECTRONICA = ? WHERE ID_ORDEN = ? ";
        sql.append(update);
        getJdbcTemplateBase().update(sql.toString(), firma.getCadena(), firma.getFirma(), firma.getId());
    }

    @Override
    public List<EmpleadoSuplenteDTO> obtenerDatosSuplente(final BigDecimal idFirmante, final BigDecimal idSuplir) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT SUPLENCIA.ID_FIRMANTE_SUPLENTE ID_SUPLENTE, SUPLENCIA.ID_FIRMANTE_A_SUPLIR ID_FIRMANTE, MOTIVO.DESCRIPCION MOTIVO");
        query.append("\n FROM FECET_SUPLENCIA SUPLENCIA");
        query.append("\n JOIN FECEC_MOTIVO_SUPLENCIA MOTIVO ON SUPLENCIA.ID_MOTIVO_SUPLENCIA = MOTIVO.ID_MOTIVO_SUPLENCIA");
        query.append("\n WHERE SUPLENCIA.ID_FIRMANTE_SUPLENTE = ? AND SUPLENCIA.ID_FIRMANTE_A_SUPLIR = ? AND SUPLENCIA.ID_ESTATUS_SUPLENCIA = ? ");

        return getJdbcTemplateBase().query(query.toString(), new EmpleadoSuplenteMapper(),
                idFirmante, idSuplir, ESTATUS_ACTIVO_SUPLENCIA);
    }

    @Override
    public void updateFirmante(FecetOficio dto) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE");
        sqlUpdate.append(" FECET_OFICIO ");
        sqlUpdate.append("SET");
        sqlUpdate.append(" ID_ESTATUS = ? , FECHA_FIRMA = ? , CADENA_ORIGINAL = ? , FIRMA_ELECTRONICA = ? ,RUTA_ARCHIVO = ? ");
        sqlUpdate.append(" WHERE ID_OFICIO = ? ");
        getJdbcTemplateBase().update(sqlUpdate.toString(), dto.getIdEstatus(), dto.getFechaFirma(), dto.getCadenaOriginal(),
                dto.getFirmaElectronica(), dto.getRutaArchivo(), dto.getIdOficio());

    }
}
