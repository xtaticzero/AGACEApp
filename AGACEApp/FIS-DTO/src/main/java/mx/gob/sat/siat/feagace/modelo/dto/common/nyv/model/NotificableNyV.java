package mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables.DescriptorNotificables;

public interface NotificableNyV {
    
    void setFecetDetalleNyV(FecetDetalleNyV fecetDetalleNyV);

    FecetDetalleNyV getFecetDetalleNyV();
    
    DescriptorNotificables getDescriptor();
    
    BigDecimal getId();
    
    BigDecimal getIdEstatus();
    
    void setIdEstatus(BigDecimal idEstatus);
    
    void setFececEstatus(FececEstatus fececEstatus);
    
    void setDatosNotificable(DatosNotificable datosNotificable);
    
    DatosNotificable getDatosNotificable();
}
