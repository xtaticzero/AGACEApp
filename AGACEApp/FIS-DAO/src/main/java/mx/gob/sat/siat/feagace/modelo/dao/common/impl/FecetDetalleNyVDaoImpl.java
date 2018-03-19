package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleNyVDAO;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetDetalleNyVMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables.DescriptorNotificables;

@Repository("fecetDetalleNyVDAO")
public class FecetDetalleNyVDaoImpl extends BaseJDBCDao<FecetDetalleNyV> implements FecetDetalleNyVDAO {

    private static final long serialVersionUID = 6679793991315611187L;

    private static final String SQL_SEQ = "SELECT FECEQ_DETALLE_NYV.NEXTVAL FROM DUAL";
    private static final String SQL_SELECT = "select ID_NYV, FOLIO_NYV, FOLIO_ACTO_ADMVO, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, FECHA_ALTA, RUTA_ACUSE_NYV from FECET_DETALLE_NYV";
    private static final String SQL_INSERT_FOLIO = "INSERT INTO FECET_DETALLE_NYV (ID_NYV, FOLIO_NYV, FOLIO_ACTO_ADMVO, ID_NYV_PADRE, RFC_NOTIFICA) VALUES(?,?,?,?,?)";
    private static final String UPDATE_FECHAS = "UPDATE FECET_DETALLE_NYV SET  FECHA_NOTIF_NYV = ?, FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, RUTA_ACUSE_NYV = ? WHERE ID_NYV = ? OR ID_NYV_PADRE = ?";
    private static final String UPDATE_FECHA_SUSRTE_EFECTOS = "UPDATE FECET_DETALLE_NYV SET FECHA_SURTE_EFECTOS = ? WHERE ID_NYV = ? ";
    private static final String UPDATE_DATOS_NYV = "UPDATE FECET_DETALLE_NYV SET FECHA_NOTIF_NYV = ?, FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, RUTA_ACUSE_NYV = ?  WHERE ID_NYV = ? ";

    public FecetDetalleNyVDaoImpl() {
        super();
    }

    public Long obtenIdNyV() {
        return getJdbcTemplateBase().queryForObject(SQL_SEQ, Long.class);
    }

    @Override
    public FecetDetalleNyV buscaDetalleNyVPorId(FecetDetalleNyV detalleNyV) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(SQL_SELECT);
        String sqlAdd = " where ID_NYV = ? ";
        List<FecetDetalleNyV> detalle = getJdbcTemplateBase().query(sqlSelect.append(sqlAdd).toString(),
                new FecetDetalleNyVMapper(), detalleNyV.getIdNyV());
        return detalle.size() == 0 ? null : detalle.get(0);
    }

    @Override
    public FecetDetalleNyV guardaFolioNyV(FecetDetalleNyV detalleNyV) {
        detalleNyV.setIdNyV(this.obtenIdNyV());
        getJdbcTemplateBase().update(SQL_INSERT_FOLIO, detalleNyV.getIdNyV(), detalleNyV.getFolioNyV(),
                detalleNyV.getFolioActoAdmvo(), detalleNyV.getIdNyVPadre(), detalleNyV.getRfcNotifica());
        return detalleNyV;
    }

    @Override
    public void guardaDetalleNyV(FecetDetalleNyV detalleNyV) {
        getJdbcTemplateBase().update(UPDATE_FECHAS, detalleNyV.getFecNotificacionNyV(),
                detalleNyV.getFecNotificacionContNyV(), detalleNyV.getFecSurteEfectosNyV(), detalleNyV.getRutaAcuseNyv(), detalleNyV.getIdNyV(),
                detalleNyV.getIdNyV());
    }

    @Override
    public void guardaDetalleNyVSurteEfectos(FecetDetalleNyV detalleNyV) {
        getJdbcTemplateBase().update(UPDATE_FECHA_SUSRTE_EFECTOS, detalleNyV.getFecSurteEfectosNyV(),
                detalleNyV.getIdNyV());
    }

    public List<FecetDetalleNyV> obtenerDetalleByIdPadre(FecetDetalleNyV detalleNyV) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(SQL_SELECT);
        sqlSelect.append(" where ID_NYV_PADRE = ? ");
        return getJdbcTemplateBase().query(sqlSelect.toString(), new FecetDetalleNyVMapper(), detalleNyV.getIdNyV());
    }

    public void actualizaNotificableRuta(NotificableNyV notificableNyV, String ruta) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        if (!DescriptorNotificables.ORDEN.equals(notificableNyV.getDescriptor())) {
            sql.append(notificableNyV.getDescriptor().getNombreTabla());
            sql.append(" SET RUTA_ACUSE_NYV = ? WHERE ");
            sql.append(notificableNyV.getDescriptor().getId());
            sql.append(" = ? ");
            sql.append(" AND RUTA_ACUSE_NYV IS NULL ");
            getJdbcTemplateBase().update(sql.toString(), ruta, notificableNyV.getId());
        }
    }

    @Override
    public void actualizaRuta(FecetDetalleNyV detalleNyV) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(" FECET_DETALLE_NYV ");
        sql.append(" SET RUTA_ACUSE_NYV = ? WHERE ");
        sql.append(" ID_NYV = ? AND RUTA_ACUSE_NYV IS NULL");
        getJdbcTemplateBase().update(sql.toString(), detalleNyV.getRutaAcuseNyv(), detalleNyV.getIdNyV());
    }

    @Override
    public void actualizaDatosNyv(FecetDetalleNyV detalleNyV) {
        StringBuilder sql = new StringBuilder(UPDATE_DATOS_NYV);
        getJdbcTemplateBase().update(sql.toString(), detalleNyV.getFecNotificacionNyV(),
                detalleNyV.getFecNotificacionContNyV(), detalleNyV.getFecSurteEfectosNyV(), detalleNyV.getIdNyV(), detalleNyV.getRutaAcuseNyv(),
                detalleNyV.getIdNyV());
    }

    @Override
    public List<FecetDetalleNyV> buscaAllDetalleNyV() {
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append("WHERE RUTA_ACUSE_NYV IS NULL AND FOLIO_NYV IS NOT NULL");
        return getJdbcTemplateBase().query(sql.toString(), new FecetDetalleNyVMapper());
    }

}
