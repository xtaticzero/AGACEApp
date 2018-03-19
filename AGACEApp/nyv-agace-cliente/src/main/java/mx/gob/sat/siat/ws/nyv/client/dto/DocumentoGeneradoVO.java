/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;
import java.util.Date;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.gob.sat.siat.ws.nyv.client.impl.helper.NyVBeanHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DocumentoGeneradoVO implements Serializable {

    private static final long serialVersionUID = -1699929250062717632L;

    private String estatusDocumento;
    private String urlDocumento;
    private String firma;
    private Date fechaDocumento;
    private String cadenaOriginal;
    private String cveDocumentoTipo;
    private String tipoDocumento;
    private long folioSifen;
    private String rfcFirmante;
    private String nombreFirmante;
    private String puestoFirmante;
    private Date fechaVigenciaFiel;

    public DocumentoGeneradoVO() {
    }

    public DocumentoGeneradoVO(mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml docto) {
        if (docto != null) {
            estatusDocumento = docto.getEstatusDocumento();
            urlDocumento = docto.getUrlDocumento();
            firma = docto.getFirma();
            fechaDocumento
                    = docto.getFechaDocumento() != null
                            ? docto.getFechaDocumento().toGregorianCalendar().getTime() : null;
            cadenaOriginal = docto.getCadenaOriginal();
            tipoDocumento = docto.getTipoDocumento();
            folioSifen = docto.getFolioSifen();
            rfcFirmante = docto.getRfcFirmante();
            nombreFirmante = docto.getNombreFirmante();
            puestoFirmante = docto.getPuestoFirmante();
            fechaVigenciaFiel
                    = docto.getFechaVigenciaFiel() != null
                            ? docto.getFechaVigenciaFiel().toGregorianCalendar().getTime() : null;
        }
    }

    public DocumentoGeneradoVO(mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml docto) {
        if (docto != null) {
            estatusDocumento = docto.getEstatusDocumento();
            urlDocumento = docto.getUrlDocumento();
            firma = docto.getFirma();
            fechaDocumento
                    = docto.getFechaDocumento() != null
                            ? docto.getFechaDocumento().toGregorianCalendar().getTime() : null;
            cadenaOriginal = docto.getCadenaOriginal();
            folioSifen = docto.getFolioSifen();
            rfcFirmante = docto.getRfcFirmante();
            nombreFirmante = docto.getNombreFirmante();
            puestoFirmante = docto.getPuestoFirmante();
            fechaVigenciaFiel
                    = docto.getFechaVigenciaFiel() != null
                            ? docto.getFechaVigenciaFiel().toGregorianCalendar().getTime() : null;
        }
    }

    public String getEstatusDocumento() {
        return estatusDocumento;
    }

    public void setEstatusDocumento(String estatusDocumento) {
        this.estatusDocumento = estatusDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getCveDocumentoTipo() {
        return cveDocumentoTipo;
    }

    public void setCveDocumentoTipo(String cveDocumentoTipo) {
        this.cveDocumentoTipo = cveDocumentoTipo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public long getFolioSifen() {
        return folioSifen;
    }

    public void setFolioSifen(long folioSifen) {
        this.folioSifen = folioSifen;
    }

    public String getRfcFirmante() {
        return rfcFirmante;
    }

    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    public String getNombreFirmante() {
        return nombreFirmante;
    }

    public void setNombreFirmante(String nombreFirmante) {
        this.nombreFirmante = nombreFirmante;
    }

    public String getPuestoFirmante() {
        return puestoFirmante;
    }

    public void setPuestoFirmante(String puestoFirmante) {
        this.puestoFirmante = puestoFirmante;
    }

    public Date getFechaVigenciaFiel() {
        return fechaVigenciaFiel;
    }

    public void setFechaVigenciaFiel(Date fechaVigenciaFiel) {
        this.fechaVigenciaFiel = fechaVigenciaFiel;
    }

    public mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml getDocumentoGeneradoVOXml() throws DatatypeConfigurationException {
        mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml docto = new mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml();

        docto.setCadenaOriginal(cadenaOriginal);
        docto.setEstatusDocumento(estatusDocumento);
        docto.setFechaDocumento(NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaDocumento));
        docto.setFechaVigenciaFiel(NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaVigenciaFiel));
        docto.setFirma(firma);
        docto.setFolioSifen(folioSifen);
        docto.setNombreFirmante(nombreFirmante);
        docto.setPuestoFirmante(puestoFirmante);
        docto.setRfcFirmante(rfcFirmante);
        docto.setTipoDocumento(tipoDocumento);
        docto.setUrlDocumento(urlDocumento);

        return docto;
    }

    public mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml getDocumentoGeneradoV2VOXml() throws DatatypeConfigurationException {
        mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml docto = new mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml();

        docto.setCadenaOriginal(cadenaOriginal);
        docto.setEstatusDocumento(estatusDocumento);
        docto.setFechaDocumento(NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaDocumento));
        docto.setFechaVigenciaFiel(NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaVigenciaFiel));
        docto.setFirma(firma);
        docto.setFolioSifen(folioSifen);
        docto.setNombreFirmante(nombreFirmante);
        docto.setPuestoFirmante(puestoFirmante);
        docto.setRfcFirmante(rfcFirmante);
        docto.setUrlDocumento(urlDocumento);
        docto.setCveDocumentoTipo(cveDocumentoTipo);

        return docto;
    }

    @Override
    public String toString() {
        return "DocumentoGeneradoVO{" + "estatusDocumento=" + estatusDocumento + ", urlDocumento=" + urlDocumento + ", firma=" + firma + ", fechaDocumento=" + fechaDocumento + ", cadenaOriginal=" + cadenaOriginal + ", cveDocumentoTipo=" + cveDocumentoTipo + ", tipoDocumento=" + tipoDocumento + ", folioSifen=" + folioSifen + ", rfcFirmante=" + rfcFirmante + ", nombreFirmante=" + nombreFirmante + ", puestoFirmante=" + puestoFirmante + ", fechaVigenciaFiel=" + fechaVigenciaFiel + '}';
    }

}
