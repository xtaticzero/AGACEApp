/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;


/**
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public class ActoAdminVO {

    private String cadenaOriginal;
    private String firmaDigital;
    private String urlDocumento;
    private BigDecimal claveActoAdmin;
    private String claveUnidadAdmin;
    private String numeroEmpleado;
    private String numeroReferencia;
    private String numeroReferenciaOrigen;
    private String rfcDestinatario;

    public ActoAdminVO() {
        super();
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setClaveActoAdmin(BigDecimal claveActoAdmin) {
        this.claveActoAdmin = claveActoAdmin;
    }

    public BigDecimal getClaveActoAdmin() {
        return claveActoAdmin;
    }

    public void setClaveUnidadAdmin(String claveUnidadAdmin) {
        this.claveUnidadAdmin = claveUnidadAdmin;
    }

    public String getClaveUnidadAdmin() {
        return claveUnidadAdmin;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferenciaOrigen(String numeroReferenciaOrigen) {
        this.numeroReferenciaOrigen = numeroReferenciaOrigen;
    }

    public String getNumeroReferenciaOrigen() {
        return numeroReferenciaOrigen;
    }

    public void setRfcDestinatario(String rfcDestinatario) {
        this.rfcDestinatario = rfcDestinatario;
    }

    public String getRfcDestinatario() {
        return rfcDestinatario;
    }
}
