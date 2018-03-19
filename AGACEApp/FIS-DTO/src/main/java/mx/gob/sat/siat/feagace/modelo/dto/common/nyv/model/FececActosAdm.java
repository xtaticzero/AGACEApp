/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author sergio.vaca
 *
 */
public class FececActosAdm extends BaseModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long idActoAdmin;
    private String descripcion;
    private Long idMetodo;
    private Long idTipoOficio;
    private Long idUnidadAdministrativa;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean blnActivo;
    private Long idNyv;
    private Long idDocumento;
    private String procesoOrigen;
    private String estatusDocumento;
    private String nombre;
    private String prefijoReferencia;
    private List<DocumentoActoAdministrativo> documentosActo;

    public Long getIdActoAdmin() {
        return idActoAdmin;
    }

    public void setIdActoAdmin(Long idActoAdmin) {
        this.idActoAdmin = idActoAdmin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(Long idMetodo) {
        this.idMetodo = idMetodo;
    }

    public Long getIdTipoOficio() {
        return idTipoOficio;
    }

    public void setIdTipoOficio(Long idTipoOficio) {
        this.idTipoOficio = idTipoOficio;
    }

    public Long getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(Long idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public boolean isBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Long getIdNyv() {
        return idNyv;
    }

    public void setIdNyv(Long idNyv) {
        this.idNyv = idNyv;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

	public String getProcesoOrigen() {
		return procesoOrigen;
	}

	public void setProcesoOrigen(String procesoOrigen) {
		this.procesoOrigen = procesoOrigen;
	}

	public String getEstatusDocumento() {
		return estatusDocumento;
	}

	public void setEstatusDocumento(String estatusDocumento) {
		this.estatusDocumento = estatusDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrefijoReferencia() {
		return prefijoReferencia;
	}

	public void setPrefijoReferencia(String prefijoReferencia) {
		this.prefijoReferencia = prefijoReferencia;
	}

	public List<DocumentoActoAdministrativo> getDocumentosActo() {
		return documentosActo;
	}

	public void setDocumentosActo(List<DocumentoActoAdministrativo> documentosActo) {
		this.documentosActo = documentosActo;
	}
}
