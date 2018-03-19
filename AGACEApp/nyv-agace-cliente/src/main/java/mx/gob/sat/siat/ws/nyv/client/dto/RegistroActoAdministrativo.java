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
import javax.xml.datatype.DatatypeConfigurationException;
import mx.gob.sat.siat.ws.nyv.client.NyVServiceClient;
import mx.gob.sat.siat.ws.nyv.client.impl.helper.NyVBeanHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class RegistroActoAdministrativo implements Serializable {

    private static final long serialVersionUID = 5161258918683373736L;

    /*
     V1
     */
    private String claveUnidadAdmin;
    private long claveActoAdmin;
    private String procesoOrigen;
    private Date fechaLimiteDiligencia;
    private String numeroReferencia;
    private String numeroReferenciaOrigen;
    private String rfcDestinatario;
    private String numeroEmpleado;
    private List<DocumentoGeneradoVO> documentos;

    /*
     V2
     */
    private int plazo;
    private String importe;
    private String niveldeServicio;
    private DestinatarioVO contribuyente;

    public String getClaveUnidadAdmin() {
        return claveUnidadAdmin;
    }

    public void setClaveUnidadAdmin(String claveUnidadAdmin) {
        this.claveUnidadAdmin = claveUnidadAdmin;
    }

    public long getClaveActoAdmin() {
        return claveActoAdmin;
    }

    public void setClaveActoAdmin(long claveActoAdmin) {
        this.claveActoAdmin = claveActoAdmin;
    }

    public String getProcesoOrigen() {
        return procesoOrigen;
    }

    public void setProcesoOrigen(String procesoOrigen) {
        this.procesoOrigen = procesoOrigen;
    }

    public Date getFechaLimiteDiligencia() {
        return fechaLimiteDiligencia;
    }

    public void setFechaLimiteDiligencia(Date fechaLimiteDiligencia) {
        this.fechaLimiteDiligencia = fechaLimiteDiligencia;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getNumeroReferenciaOrigen() {
        return numeroReferenciaOrigen;
    }

    public void setNumeroReferenciaOrigen(String numeroReferenciaOrigen) {
        this.numeroReferenciaOrigen = numeroReferenciaOrigen;
    }

    public String getRfcDestinatario() {
        return rfcDestinatario;
    }

    public void setRfcDestinatario(String rfcDestinatario) {
        this.rfcDestinatario = rfcDestinatario;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public List<DocumentoGeneradoVO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoGeneradoVO> documentos) {
        this.documentos = documentos;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getNiveldeServicio() {
        return niveldeServicio;
    }

    public void setNiveldeServicio(String niveldeServicio) {
        this.niveldeServicio = niveldeServicio;
    }

    public DestinatarioVO getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(DestinatarioVO contribuyente) {
        this.contribuyente = contribuyente;
    }

    public mx.gob.sat.siat.ws.nyv.v1.bean.RegistroActoAdministrativoVO getActoAdminV1() throws DatatypeConfigurationException {
        mx.gob.sat.siat.ws.nyv.v1.bean.RegistroActoAdministrativoVO acto = new mx.gob.sat.siat.ws.nyv.v1.bean.RegistroActoAdministrativoVO();

        acto.setClaveActoAdmin(claveActoAdmin);
        acto.setClaveUnidadAdmin(claveUnidadAdmin);
        acto.setFechaLimiteDiligencia(NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaLimiteDiligencia));
        acto.setNumeroEmpleado(numeroEmpleado);
        acto.setNumeroReferencia(numeroReferencia);
        acto.setNumeroReferenciaOrigen(numeroReferenciaOrigen);
        acto.setProcesoOrigen(procesoOrigen);
        acto.setRfcDestinatario(rfcDestinatario);
        acto.setDocumentos(documentos != null ? convert(documentos) : null);

        return acto;
    }

    private List<mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml> convert(List<DocumentoGeneradoVO> documentos) throws DatatypeConfigurationException {
        List<mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml> lstDoctos = new ArrayList<mx.gob.sat.siat.ws.nyv.v1.bean.DocumentoGeneradoVOXml>();
        if (documentos != null && !documentos.isEmpty()) {
            for (DocumentoGeneradoVO documento : documentos) {
                lstDoctos.add(documento.getDocumentoGeneradoVOXml());
            }
        }
        return lstDoctos;
    }

    public mx.gob.sat.siat.ws.nyv.v2.bean.RegistroActoAdministrativoVO getActoAdminV2() throws DatatypeConfigurationException {
        mx.gob.sat.siat.ws.nyv.v2.bean.RegistroActoAdministrativoVO acto = new mx.gob.sat.siat.ws.nyv.v2.bean.RegistroActoAdministrativoVO();

        acto.setClaveActoAdmin(claveActoAdmin);
        acto.setClaveUnidadAdmin(claveUnidadAdmin);
        acto.setFechaLimiteDiligencia(NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaLimiteDiligencia));
        acto.setNumeroEmpleado(numeroEmpleado);
        acto.setNumeroReferencia(numeroReferencia);
        acto.setNumeroReferenciaOrigen(numeroReferenciaOrigen);
        acto.setProcesoOrigen(procesoOrigen);
        acto.setRfcDestinatario(rfcDestinatario);
        acto.setImporte(importe);
        acto.setNiveldeServicio(niveldeServicio);
        acto.setPlazo(plazo);
        acto.setDocumentos(documentos != null ? convertV2(documentos) : null);
        acto.setContribuyente(contribuyente != null ? contribuyente.getDestinatarioVOXml() : null);

        return acto;
    }

    private List<mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml> convertV2(List<DocumentoGeneradoVO> documentos) throws DatatypeConfigurationException {
        List<mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml> lstDoctos = new ArrayList<mx.gob.sat.siat.ws.nyv.v2.bean.DocumentoGeneradoVOXml>();
        if (documentos != null && !documentos.isEmpty()) {
            for (DocumentoGeneradoVO documento : documentos) {
                lstDoctos.add(documento.getDocumentoGeneradoV2VOXml());
            }
        }
        return lstDoctos;
    }

    @Override
    public String toString() {
        String sb = NyVServiceClient.MSG_SOAP_REQUEST_ACTOADMIN;

        sb = sb.replace("{claveUnidadAdmin}", claveUnidadAdmin != null ? claveUnidadAdmin : "");
        sb = sb.replace("{importe}", importe != null ? importe : "");
        sb = sb.replace("{niveldeServicio}", niveldeServicio != null ? niveldeServicio : "");
        sb = sb.replace("{numeroEmpleado}", numeroEmpleado != null ? numeroEmpleado : "");
        sb = sb.replace("{numeroReferencia}", numeroReferencia != null ? numeroReferencia : "");
        sb = sb.replace("{numeroReferenciaOrigen}", numeroReferenciaOrigen != null ? numeroReferenciaOrigen : "");
        sb = sb.replace("{procesoOrigen}", procesoOrigen != null ? procesoOrigen : "");
        sb = sb.replace("{rfcDestinatario}", rfcDestinatario != null ? rfcDestinatario : "");
        sb = sb.replace("{claveActoAdmin}", String.valueOf(claveActoAdmin));
        try {
            sb = sb.replace("{fechaLimiteDiligencia}", NyVBeanHelper.getXMLGregorianCalendarFromDate(fechaLimiteDiligencia).toString());
        } catch (Exception ex) {
            sb = sb.replace("{fechaLimiteDiligencia}", "");
        }
        sb = sb.replace("{plazo}", String.valueOf(plazo));
        sb = sb.replace("{DOCUMENTOS}", docs(documentos));
        return sb;
    }

    private String docs(List<DocumentoGeneradoVO> documentos) {
        String plantilla = "";
        String sb = "";
        if (documentos != null && !documentos.isEmpty()) {
            for (DocumentoGeneradoVO documento : documentos) {
                sb = NyVServiceClient.SOAP_DOCS;
                sb = sb.replace("{estatusDocumento}", documento.getEstatusDocumento() != null ? documento.getEstatusDocumento() : "");
                sb = sb.replace("{urlDocumento}", documento.getUrlDocumento() != null ? documento.getUrlDocumento() : "");
                sb = sb.replace("{firma}", documento.getFirma() != null ? documento.getFirma() : "");
                try {
                    sb = sb.replace("{fechaDocumento}", NyVBeanHelper.getXMLGregorianCalendarFromDate(documento.getFechaDocumento()).toString());
                } catch (Exception ex) {
                    sb = sb.replace("{fechaDocumento}", "");
                }
                if (documento.getCveDocumentoTipo() != null && !documento.getCveDocumentoTipo().isEmpty()) {
                    sb = sb.replace(NyVServiceClient.STR_TIPO_DOC, NyVServiceClient.STR_CVE_DOC_TIPO);
                    sb = sb.replace("{cveDocumentoTipo}", documento.getCveDocumentoTipo()!=null?documento.getCveDocumentoTipo():"");
                } else {
                    sb = sb.replace("{tipoDocumento}", documento.getTipoDocumento()!=null?documento.getTipoDocumento():"");
                }
                sb = sb.replace("{folioSifen}", String.valueOf(documento.getFolioSifen()));
                sb = sb.replace("{rfcFirmante}", documento.getRfcFirmante() != null ? documento.getRfcFirmante() : "");
                sb = sb.replace("{nombreFirmante}", documento.getNombreFirmante() != null ? documento.getNombreFirmante() : "");
                sb = sb.replace("{puestoFirmante}", documento.getPuestoFirmante() != null ? documento.getPuestoFirmante() : "");

                try {
                    sb = sb.replace("{fechaVigenciaFiel}", NyVBeanHelper.getXMLGregorianCalendarFromDate(documento.getFechaVigenciaFiel()).toString());
                } catch (Exception ex) {
                    sb = sb.replace("{fechaVigenciaFiel}", "");
                }
                sb = sb.concat("\n");
                plantilla = plantilla.concat(sb);
            }
            return plantilla;
        }
        return "";
    }

}
