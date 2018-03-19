/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sergio.vaca
 *
 */
public class DatosNotificable implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String claveUnidadAdmin;
    private String rfcContribuyente;
    private String rfcFirmante;
    private String numeroEmpleado;
    private long idActoAdmon;
    private String descActoAdmon;
    private String numeroReferencia;
    private List<DocumentoNotificable> documentosNotificables;
    private boolean insertaNyV;
    private Long idNyvPadre;
    private FececActosAdm fececActosAdm;
    
    
    public String getRfcContribuyente() {
        return rfcContribuyente;
    }
    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }
    public String getRfcFirmante() {
        return rfcFirmante;
    }
    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }
    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }
    public long getIdActoAdmon() {
        return idActoAdmon;
    }
    public void setIdActoAdmon(long idActoAdmon) {
        this.idActoAdmon = idActoAdmon;
    }
    public String getDescActoAdmon() {
        return descActoAdmon;
    }
    public void setDescActoAdmon(String descActoAdmon) {
        this.descActoAdmon = descActoAdmon;
    }
    public List<DocumentoNotificable> getDocumentosNotificables() {
        if (documentosNotificables == null) {
            documentosNotificables = new ArrayList<DocumentoNotificable>();
        }
        return documentosNotificables;
    }
    public String getNumeroReferencia() {
        return numeroReferencia;
    }
    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
    public String getClaveUnidadAdmin() {
        return claveUnidadAdmin;
    }
    public void setClaveUnidadAdmin(String claveUnidadAdmin) {
        this.claveUnidadAdmin = claveUnidadAdmin;
    }
	public boolean isInsertaNyV() {
		return insertaNyV;
	}
	public void setInsertaNyV(boolean insertaNyV) {
		this.insertaNyV = insertaNyV;
	}
	public Long getIdNyvPadre() {
		return idNyvPadre;
	}
	public void setIdNyvPadre(Long idNyvPadre) {
		this.idNyvPadre = idNyvPadre;
	}
	public FececActosAdm getFececActosAdm() {
		return fececActosAdm;
	}
	public void setFececActosAdm(FececActosAdm fececActosAdm) {
		this.fececActosAdm = fececActosAdm;
	}
}