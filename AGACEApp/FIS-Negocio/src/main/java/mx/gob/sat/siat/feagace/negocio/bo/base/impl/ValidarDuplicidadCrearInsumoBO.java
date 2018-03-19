package mx.gob.sat.siat.feagace.negocio.bo.base.impl;

import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class ValidarDuplicidadCrearInsumoBO extends BO<ValidarDuplicidadCrearInsumoBO> {

    private FecetInsumo fecetInsumoReferencia;
    private FecetInsumo fecetInsumo;
    private boolean state;
    private String mensaje;

    public void setFecetInsumoReferencia(FecetInsumo fecetInsumoReferencia) {
        this.fecetInsumoReferencia = fecetInsumoReferencia;
    }

    public FecetInsumo getFecetInsumoReferencia() {
        return fecetInsumoReferencia;
    }

    public void setFecetInsumo(FecetInsumo fecetInsumo) {
        this.fecetInsumo = fecetInsumo;
    }

    public FecetInsumo getFecetInsumo() {
        return fecetInsumo;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
