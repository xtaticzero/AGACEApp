package mx.gob.sat.siat.feagace.modelo.dao.ordenes.plazos.impl;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.plazos.PlazosDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;

@Repository("plazosDAO")
public class PlazosDAOImpl extends BaseJDBCDao<AgaceOrden> implements PlazosDAO {

    /**
     *
     */
    private static final long serialVersionUID = -3239074198792470930L;

    public void actualizarEstatusNotificable(NotificableNyV notificableNyV) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(notificableNyV.getDescriptor().getNombreTabla());
        sql.append(" SET ");
        sql.append(notificableNyV.getDescriptor().getCampoEstatus());
        sql.append(" = ? WHERE ");
        sql.append(notificableNyV.getDescriptor().getId());
        sql.append(" = ?");
        getJdbcTemplateBase().update(sql.toString(),
                notificableNyV.getDescriptor().getEstadoNotificacionContribuyente(), notificableNyV.getId());
    }

    public void concluirNotificable(NotificableNyV notificableNyV) {
        if (notificableNyV.getDescriptor().getEstadoConcluido() > 0) {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(notificableNyV.getDescriptor().getNombreTabla());
            sql.append(" SET ");
            sql.append(notificableNyV.getDescriptor().getCampoEstatus());
            sql.append(" = ? WHERE ");
            sql.append(notificableNyV.getDescriptor().getId());
            sql.append(" = ?");
            getJdbcTemplateBase().update(sql.toString(), notificableNyV.getDescriptor().getEstadoConcluido(),
                    notificableNyV.getId());
        }
    }

    public void actualizaNotificableIdNyV(NotificableNyV notificableNyV) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(notificableNyV.getDescriptor().getNombreTabla());
        sql.append(" SET ID_NYV = ? WHERE ");
        sql.append(notificableNyV.getDescriptor().getId());
        sql.append(" = ? ");
        getJdbcTemplateBase().update(sql.toString(), notificableNyV.getFecetDetalleNyV().getIdNyV(),
                notificableNyV.getId());
    }
}
