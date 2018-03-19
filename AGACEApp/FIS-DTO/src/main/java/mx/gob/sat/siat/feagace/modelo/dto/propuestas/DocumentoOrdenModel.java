package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

public class DocumentoOrdenModel implements Serializable{
    private static final long serialVersionUID = 3537651436660278518L;
    
    private FecetDocOrden documentoOrden;
    private boolean isEliminar;
    private transient InputStream input;
    private PapelesTrabajo papelesTrabajo;
    private FecetOficio oficio;
    private BigDecimal idDocumentoTemporal;
    
    public void setDocumentoOrden(FecetDocOrden documentoOrden) {
        this.documentoOrden = documentoOrden;
    }

    public FecetDocOrden getDocumentoOrden() {
        return documentoOrden;
    }

    public void setIsEliminar(boolean isEliminar) {
        this.isEliminar = isEliminar;
    }

    public boolean isIsEliminar() {
        return isEliminar;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public InputStream getInput() {
        return input;
    }

    public void setPapelesTrabajo(PapelesTrabajo papelesTrabajo) {
        this.papelesTrabajo = papelesTrabajo;
    }

    public PapelesTrabajo getPapelesTrabajo() {
        return papelesTrabajo;
    }

    public void setOficio(FecetOficio oficio) {
        this.oficio = oficio;
    }

    public FecetOficio getOficio() {
        return oficio;
    }

    public BigDecimal getIdDocumentoTemporal() {
        return idDocumentoTemporal;
    }

    public void setIdDocumentoTemporal(BigDecimal idDocumentoTemporal) {
        this.idDocumentoTemporal = idDocumentoTemporal;
    }
    
    
}
