package mx.gob.sat.siat.feagace.vista.ordenes.helper;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaPorValidar;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class ValidarOrdenesHelper extends BaseHelper {

    /**
     * 
     */
    private static final long serialVersionUID = -3241735227130731999L;

    public FecebAccionPropuesta creaAccionPropuestaAprobar(PropuestaPorValidar propuesta, BigDecimal idEmpleado, FecetPropuesta propuestaOrigen) {
        FecebAccionPropuesta accionPropuesta = new FecebAccionPropuesta();
        if (propuestaOrigen.getRfcSubadministrador() != null) {
            accionPropuesta.setIdAccion(AccionesFuncionarioEnum.VALIDACION_EMISION_ORDENES_11.getIdAccion());
        } else {
            accionPropuesta.setIdAccion(AccionesFuncionarioEnum.APROBACION_VALIDACION.getIdAccion());   
        }
        accionPropuesta.setIdAccionOrigen(null);
        accionPropuesta.setIdPropuesta(new BigDecimal(propuesta.getIdPropuesta()));
        accionPropuesta.setIdDetalleAccion(null);
        accionPropuesta.setObservaciones(null);
        accionPropuesta.setFechaHora(new Date());
        accionPropuesta.setIdEmpleado(idEmpleado);
        return accionPropuesta;
    }

    public FecebAccionPropuesta creaAccionPropuestaNoAprobar(PropuestaPorValidar propuesta, String observaciones,
            BigDecimal idEmpleado) {
        FecebAccionPropuesta accionPropuesta = new FecebAccionPropuesta();
        accionPropuesta.setIdAccion(AccionesFuncionarioEnum.NO_APROBACION_VALIDACION.getIdAccion());
        accionPropuesta.setIdAccionOrigen(null);
        accionPropuesta.setIdPropuesta(new BigDecimal(propuesta.getIdPropuesta()));
        accionPropuesta.setIdDetalleAccion(null);
        accionPropuesta.setObservaciones(observaciones);
        accionPropuesta.setFechaHora(new Date());
        accionPropuesta.setIdEmpleado(idEmpleado);
        return accionPropuesta;
    }
    
    public FecetRechazoOrden creaRechazoOrdenDto(BigDecimal idOrden, BigDecimal idDocOrden, BigDecimal idEmpleado){
        FecetRechazoOrden rechazoOrden = new FecetRechazoOrden();
        rechazoOrden.setIdOrden(idOrden);
        rechazoOrden.setIdDocOrden(idDocOrden);
        rechazoOrden.setIdEmpleado(idEmpleado);
        rechazoOrden.setDescripcion(String.valueOf(Constantes.ENTERO_SIETE));
        rechazoOrden.setFechaRechazo(new Date());
        rechazoOrden.setFechaAtencion(null);
        rechazoOrden.setEstatus("0");
        return rechazoOrden;
    }
    
    public FecetRechazoOficio creaRechazoOficioDto(BigDecimal idOficio, BigDecimal idEmpleado){
        FecetRechazoOficio rechazoOficio = new FecetRechazoOficio();
        rechazoOficio.setIdOficio(idOficio);
        rechazoOficio.setIdEmpleado(idEmpleado);
        rechazoOficio.setDescripcion(String.valueOf(Constantes.ENTERO_SIETE));
        rechazoOficio.setFechaRechazo(new Date());
        rechazoOficio.setEstatus("0");
        return rechazoOficio;
    }

}
