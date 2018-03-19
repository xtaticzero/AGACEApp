/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaIndividualAttributeAbstract implements Serializable{
    private static final long serialVersionUID = 5113738085465683851L;
    
    private String mensajeDuplicidad;
    private String segundoDigito;
    private String terceroCuartoDigito;
    private String quintoOctavoDigito;
    private String novenoDoceavoDigito;
    private String mensajeAntecedentes;
    private String msjContactoAmparadoPPEE;
    private String unidadInsumo;
    private String correoDestino;
    private String estatusContacto;
    private String nombreAreaOrigen;
    private String estatusDeContacto;
    private String mnjestatusDeContacto;
    private String presentacionComite;
    private String informacionComite;

    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }

    public String getCorreoDestino() {
        return correoDestino;
    }
    
    public void setUnidadInsumo(String unidadInsumo) {
        this.unidadInsumo = unidadInsumo;
    }

    public String getUnidadInsumo() {
        return unidadInsumo;
    }
    
    public void setPresentacionComite(String presentacionComite) {
        this.presentacionComite = presentacionComite;
    }

    public String getPresentacionComite() {
        return presentacionComite;
    }

    public void setInformacionComite(String informacionComite) {
        this.informacionComite = informacionComite;
    }

    public String getInformacionComite() {
        return informacionComite;
    }

    public void setMnjestatusDeContacto(String mnjestatusDeContacto) {
        this.mnjestatusDeContacto = mnjestatusDeContacto;
    }

    public String getMnjestatusDeContacto() {
        return mnjestatusDeContacto;
    }
    
    public void setNombreAreaOrigen(String nombreAreaOrigen) {
        this.nombreAreaOrigen = nombreAreaOrigen;
    }

    public String getNombreAreaOrigen() {
        return nombreAreaOrigen;
    }

    public void setMensajeAntecedentes(final String mensajeAntecedentes) {
        this.mensajeAntecedentes = mensajeAntecedentes;
    }

    public String getMensajeAntecedentes() {
        return mensajeAntecedentes;
    }

    public void setMsjContactoAmparadoPPEE(String msjContactoAmparadoPPEE) {
        this.msjContactoAmparadoPPEE = msjContactoAmparadoPPEE;
    }

    public String getMsjContactoAmparadoPPEE() {
        return msjContactoAmparadoPPEE;
    }

    public void setMensajeDuplicidad(String mensajeDuplicidad) {
        this.mensajeDuplicidad = mensajeDuplicidad;
    }

    public String getMensajeDuplicidad() {
        return mensajeDuplicidad;
    }

    public void setSegundoDigito(String segundoDigito) {
        this.segundoDigito = segundoDigito;
    }

    public String getSegundoDigito() {
        return segundoDigito;
    }

    public void setTerceroCuartoDigito(String terceroCuartoDigito) {
        this.terceroCuartoDigito = terceroCuartoDigito;
    }

    public String getTerceroCuartoDigito() {
        return terceroCuartoDigito;
    }

    public void setQuintoOctavoDigito(String quintoOctavoDigito) {
        this.quintoOctavoDigito = quintoOctavoDigito;
    }

    public String getQuintoOctavoDigito() {
        return quintoOctavoDigito;
    }

    public void setNovenoDoceavoDigito(String novenoDoceavoDigito) {
        this.novenoDoceavoDigito = novenoDoceavoDigito;
    }

    public String getNovenoDoceavoDigito() {
        return novenoDoceavoDigito;
    }

    public void setEstatusDeContacto(final String estatusDeContacto) {
        this.estatusDeContacto = estatusDeContacto;
    }

    public String getEstatusDeContacto() {
        return estatusDeContacto;
    }
    
    public void setEstatusContacto(String estatusContacto) {
        this.estatusContacto = estatusContacto;
    }

    public String getEstatusContacto() {
        return estatusContacto;
    }
}
