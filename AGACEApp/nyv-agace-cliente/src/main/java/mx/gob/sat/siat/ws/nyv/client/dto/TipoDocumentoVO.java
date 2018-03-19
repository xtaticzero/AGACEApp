/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;
import mx.gob.sat.siat.ws.nyv.v1.bean.TipoDocumentoV1VOXml;
import mx.gob.sat.siat.ws.nyv.v2.bean.TipoDocumentoV2VOXml;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class TipoDocumentoVO implements Serializable {

    private static final long serialVersionUID = -4119149423931167420L;

    private long id;
    private String cveDocumentoCte;
    private String tipoDocumento;
    private boolean resolucion;

    public TipoDocumentoVO() {
    }

    public TipoDocumentoVO(TipoDocumentoV1VOXml tipoDoc) {
        if (tipoDoc != null) {
            id = tipoDoc.getId();
            tipoDocumento = tipoDoc.getTipoDocumento();
            resolucion = tipoDoc.isResolucion();
        }
    }

    public TipoDocumentoVO(TipoDocumentoV2VOXml tipoDoc) {
        if (tipoDoc != null) {
            id = tipoDoc.getId();
            tipoDocumento = tipoDoc.getTipoDocumento();
            resolucion = tipoDoc.isResolucion();
            cveDocumentoCte = tipoDoc.getCveDocumentoCte();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCveDocumentoCte() {
        return cveDocumentoCte;
    }

    public void setCveDocumentoCte(String cveDocumentoCte) {
        this.cveDocumentoCte = cveDocumentoCte;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public boolean isResolucion() {
        return resolucion;
    }

    public void setResolucion(boolean resolucion) {
        this.resolucion = resolucion;
    }

    @Override
    public String toString() {
        return "TipoDocumentoVO{" + "id=" + id + ", cveDocumentoCte=" + cveDocumentoCte + ", tipoDocumento=" + tipoDocumento + ", resolucion=" + resolucion + '}';
    }

}
