/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class TipoProcesoVO implements Serializable {

    private static final long serialVersionUID = 7709552132839591234L;

    private long id;
    private String descripcion;

    public TipoProcesoVO() {
    }

    public TipoProcesoVO(mx.gob.sat.siat.ws.nyv.v1.bean.TipoProcesoVOXml tipoProcesoV1) {
        if (tipoProcesoV1 != null) {
            id = tipoProcesoV1.getId();
            descripcion = tipoProcesoV1.getDescripcion();
        }
    }

    public TipoProcesoVO(mx.gob.sat.siat.ws.nyv.v2.bean.TipoProcesoVOXml tipoProcesoV2) {
        if (tipoProcesoV2 != null) {
            id = tipoProcesoV2.getId();
            descripcion = tipoProcesoV2.getDescripcion();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
