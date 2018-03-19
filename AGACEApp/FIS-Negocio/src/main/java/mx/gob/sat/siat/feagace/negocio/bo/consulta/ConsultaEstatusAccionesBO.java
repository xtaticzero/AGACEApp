/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

/**
 * @author sergio.vaca
 *
 */
public class ConsultaEstatusAccionesBO extends BaseModel {
    private static final long serialVersionUID = 1L;
    
    private List<TipoEstatusEnum> estatus;
    private List<TipoAccionPropuesta> acciones;
    
    public ConsultaEstatusAccionesBO() {
        super();
    }
    
    public ConsultaEstatusAccionesBO(List<TipoEstatusEnum> estatus, List<TipoAccionPropuesta> acciones) {
        this();
        this.estatus = estatus;
        this.acciones = acciones;
    }
    
    public ConsultaEstatusAccionesBO(List<TipoEstatusEnum> estatus, TipoAccionPropuesta... acciones) {
        this();
        this.estatus = estatus;
        if (acciones != null && acciones.length > 0) {
            this.acciones = Arrays.asList(acciones);
        } else {
            this.acciones = new ArrayList<TipoAccionPropuesta>();
        }
    }

    public List<TipoEstatusEnum> getEstatus() {
        return estatus;
    }
    public void setEstatus(List<TipoEstatusEnum> estatus) {
        this.estatus = estatus;
    }
    public List<TipoAccionPropuesta> getAcciones() {
        return acciones;
    }
    public void setAcciones(List<TipoAccionPropuesta> acciones) {
        this.acciones = acciones;
    }

}
