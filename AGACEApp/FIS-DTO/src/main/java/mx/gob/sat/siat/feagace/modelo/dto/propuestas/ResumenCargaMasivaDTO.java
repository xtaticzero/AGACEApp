package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ResumenCargaMasivaDTO extends BaseModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int totalRegistros;
    
    private Date fechaCarga;
    
    private int idOrigen;

    public ResumenCargaMasivaDTO() {
        super();
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Date getFechaCarga() {        
        return (fechaCarga != null) ? (Date)fechaCarga.clone() : null;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = (fechaCarga != null) ? (Date)fechaCarga.clone() : null;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }
}
