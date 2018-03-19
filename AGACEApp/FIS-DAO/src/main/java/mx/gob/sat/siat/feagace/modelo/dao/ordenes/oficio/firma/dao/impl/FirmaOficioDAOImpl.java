package mx.gob.sat.siat.feagace.modelo.dao.ordenes.oficio.firma.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioContadorAnexosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.oficio.firma.dao.FirmaOficioDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;

import org.springframework.stereotype.Repository;

@Repository
public class FirmaOficioDAOImpl extends BaseJDBCDao<FirmaDTO> implements FirmaOficioDAO {

    private static final long serialVersionUID = 3289599135974940461L;

    private static final StringBuilder SELECT_OFICIOS_CON_RELACIONES
            = new StringBuilder(" O.ID_OFICIO, O.ID_TIPO_OFICIO, O.ID_OFICIO_PRINCIPAL, O.FECHA_CREACION, \n")
            .append("   O.FECHA_FIRMA, O.RUTA_ARCHIVO, O.DOCUMENTO_PDF, O.FOLIO_NYV, \n")
            .append("   O.FECHA_NOTIF_NYV, O.FECHA_NOTIF_CONT, O.FECHA_SURTE_EFECTOS, \n")
            .append("   O.DIAS_RESTANTES_PLAZO, O.DIAS_HABILES, O.SUSPENCION_PLAZO, O.DIAS_RESTANTES_DOCUMENTOS, \n")
            .append("   O.FECHA_INTEGRA_EXP, O.ID_ESTATUS, O.ID_ORDEN, O.CADENA_ORIGINAL, O.FIRMA_ELECTRONICA \n")
            .append("   , T.ID_TIPO_OFICIO, T.NOMBRE, T.DESCRIPCION, T.FECHA_CREACION \n")
            .append("   , (SELECT COUNT(0) FROM FECET_OFICIO_ANEXOS A WHERE A.ID_OFICIO = O.ID_OFICIO) TOTAL_ANEXOS_OFICIO \n")
            .append("   , (SELECT COUNT(0) FROM FECET_OFICIO  OD WHERE OD.ID_OFICIO_PRINCIPAL = O.ID_OFICIO AND OD.ID_ESTATUS = O.ID_ESTATUS) TOTAL_OFICIOS_DEPENDIENTES \n")
            .append("   FROM FECET_OFICIO O INNER JOIN FECEC_TIPO_OFICIO T \n")
            .append("   ON O.ID_TIPO_OFICIO = T.ID_TIPO_OFICIO \n");

    @Override
    public void guardarFirma(FirmaDTO firma) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE FECET_OFICIO ");
        sqlUpdate.append(" SET ");
        sqlUpdate.append(" FECHA_FIRMA = ? , CADENA_ORIGINAL = ? , FIRMA_ELECTRONICA = ?, ID_ESTATUS = ? ");
        sqlUpdate.append(" WHERE ID_OFICIO = ? ");
        getJdbcTemplateBase().update(sqlUpdate.toString(), new Date(), firma.getCadena(), firma.getFirma(), EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getIdEstatus(),
                firma.getId());
    }

    public List<FecetOficio> getOficiosDependientesPorIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus) {
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append("SELECT ");
        sqlWhere.append(SELECT_OFICIOS_CON_RELACIONES);
        sqlWhere.append(" WHERE ");
        sqlWhere.append(" O.ID_OFICIO_PRINCIPAL = ? \n ");
        sqlWhere.append(" AND O.ID_ESTATUS = ? \n ");
        return getJdbcTemplateBase().query(sqlWhere.toString(), new FecetOficioContadorAnexosMapper(), idOficio, idEstatus);
    }

    public void updateFirmante(FecetOficio dto) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE");
        sqlUpdate.append(" FECET_OFICIO ");
        sqlUpdate.append("SET");
        sqlUpdate.append(" ID_ESTATUS = ? , FECHA_FIRMA = ? , CADENA_ORIGINAL = ? , FIRMA_ELECTRONICA = ? ,RUTA_ARCHIVO = ?, NUMERO_OFICIO = ? ");
        sqlUpdate.append(" WHERE ID_OFICIO = ? ");
        getJdbcTemplateBase().update(sqlUpdate.toString(), dto.getIdEstatus(), dto.getFechaFirma(), dto.getCadenaOriginal(),
                dto.getFirmaElectronica(), dto.getRutaArchivo(), dto.getNumeroOficio(), dto.getIdOficio());

    }

}
