/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ResponseConsultaVO implements Serializable {

    private static final long serialVersionUID = 1264529579753672871L;
    private String folioActo;
    private String respuesta;
    private String estatus;
    private Date fechaSolicitudDiligencia;
    private Date fechaSms;
    private Date fechaEmail;
    private Date fechaNotificacion;
    private Date fechaNotificacionEfectiva;
    private String urlAvisoNotificacion;
    private String urlAcuseNotificacion;
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private List<BitacoraVO> bitacora;

    public ResponseConsultaVO() {
    }

    public ResponseConsultaVO(mx.gob.sat.siat.ws.nyv.v1.bean.ResponseConsulta responseConsulta) {
        if (responseConsulta != null) {
            folioActo = responseConsulta.getFolioActo();
            respuesta = responseConsulta.getRespuesta();
            estatus = responseConsulta.getEstatus();
            fechaSolicitudDiligencia
                    = responseConsulta.getFechaSolicitudDiligencia() != null
                            ? responseConsulta.getFechaSolicitudDiligencia().toGregorianCalendar().getTime() : null;
            fechaSms
                    = responseConsulta.getFechaSms() != null
                            ? responseConsulta.getFechaSms().toGregorianCalendar().getTime() : null;
            fechaEmail
                    = responseConsulta.getFechaEmail() != null
                            ? responseConsulta.getFechaEmail().toGregorianCalendar().getTime() : null;
            fechaNotificacion
                    = responseConsulta.getFechaNotificacion() != null
                            ? responseConsulta.getFechaNotificacion().toGregorianCalendar().getTime() : null;
            fechaNotificacionEfectiva
                    = responseConsulta.getFechaNotificacionEfectiva() != null
                            ? responseConsulta.getFechaNotificacionEfectiva().toGregorianCalendar().getTime() : null;
            urlAvisoNotificacion = responseConsulta.getUrlAvisoNotificacion();
            urlAcuseNotificacion = responseConsulta.getUrlAcuseNotificacion();
            codigoRespuesta = responseConsulta.getCodigoRespuesta();
            mensajeRespuesta = responseConsulta.getMensajeRespuesta();
            bitacora = convert(responseConsulta.getBitacora());
        }
    }

    public ResponseConsultaVO(mx.gob.sat.siat.ws.nyv.v2.bean.ResponseConsulta responseConsulta) {
        if (responseConsulta != null) {
            folioActo = responseConsulta.getFolioActo();
            respuesta = responseConsulta.getRespuesta();
            estatus = responseConsulta.getEstatus();
            fechaSolicitudDiligencia
                    = responseConsulta.getFechaSolicitudDiligencia() != null
                            ? responseConsulta.getFechaSolicitudDiligencia().toGregorianCalendar().getTime() : null;
            fechaSms
                    = responseConsulta.getFechaSms() != null
                            ? responseConsulta.getFechaSms().toGregorianCalendar().getTime() : null;
            fechaEmail
                    = responseConsulta.getFechaEmail() != null
                            ? responseConsulta.getFechaEmail().toGregorianCalendar().getTime() : null;
            fechaNotificacion
                    = responseConsulta.getFechaNotificacion() != null
                            ? responseConsulta.getFechaNotificacion().toGregorianCalendar().getTime() : null;
            fechaNotificacionEfectiva
                    = responseConsulta.getFechaNotificacionEfectiva() != null
                            ? responseConsulta.getFechaNotificacionEfectiva().toGregorianCalendar().getTime() : null;
            urlAvisoNotificacion = responseConsulta.getUrlAvisoNotificacion();
            urlAcuseNotificacion = responseConsulta.getUrlAcuseNotificacion();
            codigoRespuesta = responseConsulta.getCodigoRespuesta();
            mensajeRespuesta = responseConsulta.getMensajeRespuesta();
            bitacora = convert2(responseConsulta.getBitacora());
        }
    }

    private List<BitacoraVO> convert(List<mx.gob.sat.siat.ws.nyv.v1.bean.BitacoraVOXml> bitacora) {
        List<BitacoraVO> lstBit = new ArrayList<BitacoraVO>();
        if (bitacora != null) {
            for (mx.gob.sat.siat.ws.nyv.v1.bean.BitacoraVOXml bitXml : bitacora) {
                lstBit.add(new BitacoraVO(bitXml));
            }
        }
        return lstBit;
    }

    private List<BitacoraVO> convert2(List<mx.gob.sat.siat.ws.nyv.v2.bean.BitacoraVOXml> bitacora) {
        List<BitacoraVO> lstBit = new ArrayList<BitacoraVO>();
        if (bitacora != null) {
            for (mx.gob.sat.siat.ws.nyv.v2.bean.BitacoraVOXml bitXml : bitacora) {
                lstBit.add(new BitacoraVO(bitXml));
            }
        }
        return lstBit;
    }

    public String getFolioActo() {
        return folioActo;
    }

    public void setFolioActo(String folioActo) {
        this.folioActo = folioActo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaSolicitudDiligencia() {
        return fechaSolicitudDiligencia;
    }

    public void setFechaSolicitudDiligencia(Date fechaSolicitudDiligencia) {
        this.fechaSolicitudDiligencia = fechaSolicitudDiligencia;
    }

    public Date getFechaSms() {
        return fechaSms;
    }

    public void setFechaSms(Date fechaSms) {
        this.fechaSms = fechaSms;
    }

    public Date getFechaEmail() {
        return fechaEmail;
    }

    public void setFechaEmail(Date fechaEmail) {
        this.fechaEmail = fechaEmail;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaNotificacionEfectiva() {
        return fechaNotificacionEfectiva;
    }

    public void setFechaNotificacionEfectiva(Date fechaNotificacionEfectiva) {
        this.fechaNotificacionEfectiva = fechaNotificacionEfectiva;
    }

    public String getUrlAvisoNotificacion() {
        return urlAvisoNotificacion;
    }

    public void setUrlAvisoNotificacion(String urlAvisoNotificacion) {
        this.urlAvisoNotificacion = urlAvisoNotificacion;
    }

    public String getUrlAcuseNotificacion() {
        return urlAcuseNotificacion;
    }

    public void setUrlAcuseNotificacion(String urlAcuseNotificacion) {
        this.urlAcuseNotificacion = urlAcuseNotificacion;
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

    public List<BitacoraVO> getBitacora() {
        return bitacora;
    }

    public void setBitacora(List<BitacoraVO> bitacora) {
        this.bitacora = bitacora;
    }

    @Override
    public String toString() {
        return "ResponseConsultaVO{" + "folioActo=" + folioActo + ", respuesta=" + respuesta + ", estatus=" + estatus + ", fechaSolicitudDiligencia=" + fechaSolicitudDiligencia + ", fechaSms=" + fechaSms + ", fechaEmail=" + fechaEmail + ", fechaNotificacion=" + fechaNotificacion + ", fechaNotificacionEfectiva=" + fechaNotificacionEfectiva + ", urlAvisoNotificacion=" + urlAvisoNotificacion + ", urlAcuseNotificacion=" + urlAcuseNotificacion + ", codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + ", bitacora=" + bitacora + '}';
    }

}
