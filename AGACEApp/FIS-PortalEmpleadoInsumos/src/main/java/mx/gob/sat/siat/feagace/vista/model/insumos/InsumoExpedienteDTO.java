package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class InsumoExpedienteDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private FecetInsumo fecetInsumo;
    private List<FecetDocExpInsumo> fecetDocExpInsumos;
    private int numeroExpedientes;

    /**
     * @return the fecetInsumo
     */
    public FecetInsumo getFecetInsumo() {
        return fecetInsumo;
    }

    /**
     * @param fecetInsumo
     *            the fecetInsumo to set
     */
    public void setFecetInsumo(FecetInsumo fecetInsumo) {
        this.fecetInsumo = fecetInsumo;
    }

    /**
     * @return the fecetDocExpInsumos
     */
    public List<FecetDocExpInsumo> getFecetDocExpInsumos() {
        return fecetDocExpInsumos;
    }

    /**
     * @param fecetDocExpInsumos
     *            the fecetDocExpInsumos to set
     */
    public void setFecetDocExpInsumos(List<FecetDocExpInsumo> fecetDocExpInsumos) {
        this.fecetDocExpInsumos = fecetDocExpInsumos;
    }

    /**
     * @return the numeroExpedientes
     */
    public int getNumeroExpedientes() {
        return numeroExpedientes;
    }

    /**
     * @param numeroExpedientes
     *            the numeroExpedientes to set
     */
    public void setNumeroExpedientes(int numeroExpedientes) {
        this.numeroExpedientes = numeroExpedientes;
    }

}
