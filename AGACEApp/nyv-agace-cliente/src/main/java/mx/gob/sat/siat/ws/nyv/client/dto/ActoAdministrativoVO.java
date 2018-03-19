/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.ws.nyv.v1.bean.ActoAdministrativoV1VOXml;
import mx.gob.sat.siat.ws.nyv.v1.bean.TipoDocumentoV1VOXml;
import mx.gob.sat.siat.ws.nyv.v2.bean.ActoAdministrativoV2VOXml;
import mx.gob.sat.siat.ws.nyv.v2.bean.TipoDocumentoV2VOXml;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ActoAdministrativoVO implements Serializable {

    private static final long serialVersionUID = -5894930452719799376L;
    private long id;
    private String nombre;
    private String descripcion;
    private String prefijoReferencia;
    private List<TipoDocumentoVO> documento;

    public ActoAdministrativoVO() {
    }

    public ActoAdministrativoVO(ActoAdministrativoV1VOXml actoAdmin) {
        if (actoAdmin != null) {
            id = actoAdmin.getId();
            nombre = actoAdmin.getNombre();
            descripcion = actoAdmin.getDescripcion();
            prefijoReferencia = actoAdmin.getPrefijoReferencia();
            documento = convertV1(actoAdmin.getDocumento());
        }
    }

    public ActoAdministrativoVO(ActoAdministrativoV2VOXml actoAdmin) {
        if (actoAdmin != null) {
            id = actoAdmin.getId();
            nombre = actoAdmin.getNombre();
            descripcion = actoAdmin.getDescripcion();
            prefijoReferencia = actoAdmin.getPrefijoReferencia();
            documento = convertV2(actoAdmin.getDocumento());
        }
    }

    private List<TipoDocumentoVO> convertV1(List<TipoDocumentoV1VOXml> lstTipoDoc) {
        if (lstTipoDoc != null && !lstTipoDoc.isEmpty()) {
            List<TipoDocumentoVO> lstDocs = new ArrayList<TipoDocumentoVO>();

            for (TipoDocumentoV1VOXml tipoDoc : lstTipoDoc) {
                lstDocs.add(convertTipoDoc(tipoDoc));
            }

            return lstDocs;
        } else {
            return new ArrayList<TipoDocumentoVO>();
        }
    }

    private List<TipoDocumentoVO> convertV2(List<TipoDocumentoV2VOXml> lstTipoDoc) {
        if (lstTipoDoc != null && !lstTipoDoc.isEmpty()) {
            List<TipoDocumentoVO> lstDocs = new ArrayList<TipoDocumentoVO>();

            for (TipoDocumentoV2VOXml tipoDoc : lstTipoDoc) {
                lstDocs.add(convertTipoDoc(tipoDoc));
            }

            return lstDocs;
        } else {
            return new ArrayList<TipoDocumentoVO>();
        }
    }

    private TipoDocumentoVO convertTipoDoc(TipoDocumentoV1VOXml tipoDoc) {
        return new TipoDocumentoVO(tipoDoc);
    }

    private TipoDocumentoVO convertTipoDoc(TipoDocumentoV2VOXml tipoDoc) {
        return new TipoDocumentoVO(tipoDoc);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrefijoReferencia() {
        return prefijoReferencia;
    }

    public void setPrefijoReferencia(String prefijoReferencia) {
        this.prefijoReferencia = prefijoReferencia;
    }

    public List<TipoDocumentoVO> getDocumento() {
        return documento;
    }

    public void setDocumento(List<TipoDocumentoVO> documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "ActoAdministrativoVO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", prefijoReferencia=" + prefijoReferencia + ", documento=" + documento + '}';
    }

}
