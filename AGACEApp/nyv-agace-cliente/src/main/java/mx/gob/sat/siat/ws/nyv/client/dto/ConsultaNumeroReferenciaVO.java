/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaNumeroReferenciaVO implements Serializable {

    private static final long serialVersionUID = 8638625800405637009L;
    private String folioActoAdmin;
    private String rfcContribuyente;
    private String nombreContribuyente;
    private Date fechaNotificacion;
    private String estatusNotificacion;
    private String notificadoPor;

    public ConsultaNumeroReferenciaVO(mx.gob.sat.siat.ws.nyv.v1.bean.ResponseConsultaNumeroReferencia consultaNumeroV1) {
        if (consultaNumeroV1 != null) {
            folioActoAdmin = consultaNumeroV1.getFolioActoAdmin();
            rfcContribuyente = consultaNumeroV1.getRfcContribuyente();
            nombreContribuyente = consultaNumeroV1.getNombreContribuyente();
            fechaNotificacion = consultaNumeroV1.getFechaNotificacion() != null ? consultaNumeroV1.getFechaNotificacion().toGregorianCalendar().getTime() : null;
            estatusNotificacion = consultaNumeroV1.getEstatusNotificacion();
            notificadoPor = consultaNumeroV1.getNotificadoPor();
        }
    }

    public ConsultaNumeroReferenciaVO(mx.gob.sat.siat.ws.nyv.v2.bean.ResponseConsultaNumeroReferencia consultaNumeroV1) {
        if (consultaNumeroV1 != null) {
            folioActoAdmin = consultaNumeroV1.getFolioActoAdmin();
            rfcContribuyente = consultaNumeroV1.getRfcContribuyente();
            nombreContribuyente = consultaNumeroV1.getNombreContribuyente();
            fechaNotificacion = consultaNumeroV1.getFechaNotificacion() != null ? consultaNumeroV1.getFechaNotificacion().toGregorianCalendar().getTime() : null;
            estatusNotificacion = consultaNumeroV1.getEstatusNotificacion();
            notificadoPor = consultaNumeroV1.getNotificadoPor();
        }
    }

    public String getFolioActoAdmin() {
        return folioActoAdmin;
    }

    public void setFolioActoAdmin(String folioActoAdmin) {
        this.folioActoAdmin = folioActoAdmin;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getEstatusNotificacion() {
        return estatusNotificacion;
    }

    public void setEstatusNotificacion(String estatusNotificacion) {
        this.estatusNotificacion = estatusNotificacion;
    }

    public String getNotificadoPor() {
        return notificadoPor;
    }

    public void setNotificadoPor(String notificadoPor) {
        this.notificadoPor = notificadoPor;
    }

    @Override
    public String toString() {
        return "ConsultaNumeroReferenciaVO{" + "folioActoAdmin=" + folioActoAdmin + ", rfcContribuyente=" + rfcContribuyente + ", nombreContribuyente=" + nombreContribuyente + ", fechaNotificacion=" + fechaNotificacion + ", estatusNotificacion=" + estatusNotificacion + ", notificadoPor=" + notificadoPor + '}';
    }

}
