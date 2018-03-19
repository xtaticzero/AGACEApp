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
public class DestinatarioVO implements Serializable {

    private static final long serialVersionUID = -8987525740354650428L;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String razonSocial;
    private String entidadFederativa;
    private String municipio;
    private String colonia;
    private String localidad;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String entreCalleUno;
    private String entreCalleDos;
    private String cp;
    private String cveAlr;
    private String cveCrh;
    private String cveUds;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getEntreCalleUno() {
        return entreCalleUno;
    }

    public void setEntreCalleUno(String entreCalleUno) {
        this.entreCalleUno = entreCalleUno;
    }

    public String getEntreCalleDos() {
        return entreCalleDos;
    }

    public void setEntreCalleDos(String entreCalleDos) {
        this.entreCalleDos = entreCalleDos;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCveAlr() {
        return cveAlr;
    }

    public void setCveAlr(String cveAlr) {
        this.cveAlr = cveAlr;
    }

    public String getCveCrh() {
        return cveCrh;
    }

    public void setCveCrh(String cveCrh) {
        this.cveCrh = cveCrh;
    }

    public String getCveUds() {
        return cveUds;
    }

    public void setCveUds(String cveUds) {
        this.cveUds = cveUds;
    }

    public mx.gob.sat.siat.ws.nyv.v2.bean.DestinatarioVOXml getDestinatarioVOXml() {
        mx.gob.sat.siat.ws.nyv.v2.bean.DestinatarioVOXml destinatario = new mx.gob.sat.siat.ws.nyv.v2.bean.DestinatarioVOXml();
        destinatario.setApellidoMaterno(apellidoMaterno);
        destinatario.setApellidoPaterno(apellidoPaterno);
        destinatario.setCalle(calle);
        destinatario.setColonia(colonia);
        destinatario.setCp(cp);
        destinatario.setCveAlr(cveAlr);
        destinatario.setCveCrh(cveCrh);
        destinatario.setCveUds(cveUds);
        destinatario.setEntidadFederativa(entidadFederativa);
        destinatario.setEntreCalleDos(entreCalleDos);
        destinatario.setEntreCalleUno(entreCalleUno);
        destinatario.setLocalidad(localidad);
        destinatario.setMunicipio(municipio);
        destinatario.setNombre(nombre);
        destinatario.setNumeroExterior(numeroExterior);
        destinatario.setNumeroInterior(numeroInterior);
        destinatario.setRazonSocial(razonSocial);

        return destinatario;
    }
}
