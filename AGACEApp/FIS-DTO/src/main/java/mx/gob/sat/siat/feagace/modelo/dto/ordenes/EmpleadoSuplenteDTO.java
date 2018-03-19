/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * @author sergio.vaca
 *
 */
public class EmpleadoSuplenteDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String nombreFirmante;
    private String nombreSuplente;
    private String motivo;
    private BigDecimal idFirmante;
    private BigDecimal idSuplente;

    public String getNombreFirmante() {
        return nombreFirmante;
    }
    public void setNombreFirmante(String nombreFirmante) {
        this.nombreFirmante = nombreFirmante;
    }
    public String getNombreSuplente() {
        return nombreSuplente;
    }
    public void setNombreSuplente(String nombreSuplente) {
        this.nombreSuplente = nombreSuplente;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }

    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    public void setIdSuplente(BigDecimal idSuplente) {
        this.idSuplente = idSuplente;
    }

    public BigDecimal getIdSuplente() {
        return idSuplente;
    }
}
