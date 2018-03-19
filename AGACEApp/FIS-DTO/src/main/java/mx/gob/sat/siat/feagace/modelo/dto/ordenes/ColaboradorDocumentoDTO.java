/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;

/**
 * @author sergio.vaca
 *
 */
public class ColaboradorDocumentoDTO extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ColaboradorVO representanteLegal;
    private ColaboradorVO agenteAduanal;
    private ColaboradorVO apoderadoLegal;
    private ColaboradorVO apoderadoLegalRepresentanteLegal;
    private List<FecetDocAsociado> documentosRepresentante;
    private List<FecetDocAsociado> documentosALRL;
    private List<FecetDocAsociado> documentosAL;
    private List<FecetDocAsociado> documentos;
    private List<FecetPromocionAgenteAduanal> documentosAgente;
    private List<FecetAlegatoAgenteAduanal> alegatos;
    private FecetPromocionAgenteAduanal documentoAgenteSeleccionado;
    private FecetDocAsociado documentosSeleccionado;

    public final ColaboradorVO getRepresentanteLegal() {
        return representanteLegal;
    }

    public final void setRepresentanteLegal(ColaboradorVO representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public final ColaboradorVO getAgenteAduanal() {
        return agenteAduanal;
    }

    public final void setAgenteAduanal(ColaboradorVO agenteAduanal) {
        this.agenteAduanal = agenteAduanal;
    }

    public final List<FecetDocAsociado> getDocumentosRepresentante() {
        return documentosRepresentante;
    }

    public final void setDocumentosRepresentante(List<FecetDocAsociado> documentosRepresentante) {
        this.documentosRepresentante = documentosRepresentante;
    }

    public List<FecetPromocionAgenteAduanal> getDocumentosAgente() {
        return documentosAgente;
    }

    public void setDocumentosAgente(List<FecetPromocionAgenteAduanal> documentosAgente) {
        this.documentosAgente = documentosAgente;
    }

    public FecetPromocionAgenteAduanal getDocumentoAgenteSeleccionado() {
        return documentoAgenteSeleccionado;
    }

    public void setDocumentoAgenteSeleccionado(FecetPromocionAgenteAduanal documentoAgenteSeleccionado) {
        this.documentoAgenteSeleccionado = documentoAgenteSeleccionado;
    }

    public List<FecetAlegatoAgenteAduanal> getAlegatos() {
        return alegatos;
    }

    public void setAlegatos(List<FecetAlegatoAgenteAduanal> alegatos) {
        this.alegatos = alegatos;
    }

	public ColaboradorVO getApoderadoLegal() {
		return apoderadoLegal;
	}

	public void setApoderadoLegal(ColaboradorVO apoderadoLegal) {
		this.apoderadoLegal = apoderadoLegal;
	}

	public ColaboradorVO getApoderadoLegalRepresentanteLegal() {
		return apoderadoLegalRepresentanteLegal;
	}

	public void setApoderadoLegalRepresentanteLegal(ColaboradorVO apoderadoLegalRepresentanteLegal) {
		this.apoderadoLegalRepresentanteLegal = apoderadoLegalRepresentanteLegal;
	}

	public List<FecetDocAsociado> getDocumentosALRL() {
		return documentosALRL;
	}

	public void setDocumentosALRL(List<FecetDocAsociado> documentosALRL) {
		this.documentosALRL = documentosALRL;
	}

	public List<FecetDocAsociado> getDocumentosAL() {
		return documentosAL;
	}

	public void setDocumentosAL(List<FecetDocAsociado> documentosAL) {
		this.documentosAL = documentosAL;
	}

	public FecetDocAsociado getDocumentosSeleccionado() {
		return documentosSeleccionado;
	}

	public void setDocumentosSeleccionado(FecetDocAsociado documentosSeleccionado) {
		this.documentosSeleccionado = documentosSeleccionado;
	}

	public List<FecetDocAsociado> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<FecetDocAsociado> documentos) {
		this.documentos = documentos;
	}
}
