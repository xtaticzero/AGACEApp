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
public class ResponseRegistroVO implements Serializable {

    private static final long serialVersionUID = 4460921201225965052L;

    private String codigoRespuesta;
    private String mensajeRespuesta;
    private String folio;

    public ResponseRegistroVO() {
    }

    public ResponseRegistroVO(mx.gob.sat.siat.ws.nyv.v1.bean.ResponseRegistro response) {
        if (response != null) {
            codigoRespuesta = response.getCodigoRespuesta();
            mensajeRespuesta = response.getMensajeRespuesta();
            folio = response.getFolio();
        }
    }

    public ResponseRegistroVO(mx.gob.sat.siat.ws.nyv.v2.bean.ResponseRegistro response) {
        if (response != null) {
            codigoRespuesta = response.getCodigoRespuesta();
            mensajeRespuesta = response.getMensajeRespuesta();
            folio = response.getFolio();
        }
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    @Override
    public String toString() {
        return "ResponseRegistroVO{" + "codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + ", folio=" + folio + '}';
    }

}
