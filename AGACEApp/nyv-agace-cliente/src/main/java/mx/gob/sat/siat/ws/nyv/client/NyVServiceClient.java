/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client;

import java.util.List;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoProcesoVO;
import mx.gob.sat.siat.ws.nyv.constantes.VersionNyVEnum;
import mx.gob.sat.siat.ws.nyv.exception.NyVClientException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface NyVServiceClient {

    String MSG_ERR_HTTPS = "Certificados Servicio HTTPS: ";
    String MSG_ERR_URL = "Error en la url del servicio NyV : ";
    String MSG_ERR_SERVICIO = "Error servicio NyV : ";
    String MSG_ERR_VERSION = "Version de servicio no valida";

    String MSG_SOAP_REQUEST_ACTOADMIN = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.sistema.nyv.siat.sat.gob.mx/\">\n"
            + "   <soapenv:Header/>\n"
            + "   <soapenv:Body>\n"
            + "      <web:registrarActoAdministrativo>\n"
            + "         <!--Optional:-->\n"
            + "         <registroActoAdministrativo>\n"
            + "            <claveUnidadAdmin>{claveUnidadAdmin}</claveUnidadAdmin>\n"
            + "            <claveActoAdmin>{claveActoAdmin}</claveActoAdmin>\n"
            + "            <procesoOrigen>{procesoOrigen}</procesoOrigen>\n"
            + "            <fechaLimiteDiligencia>{fechaLimiteDiligencia}</fechaLimiteDiligencia>\n"
            + "            <fechaLimiteDiligencia>{fechaLimiteDiligencia}</fechaLimiteDiligencia>\n"
            + "            <numeroReferencia>{numeroReferencia}</numeroReferencia>\n"
            + "            <numeroReferenciaOrigen>{numeroReferenciaOrigen}</numeroReferenciaOrigen>\n"
            + "            <rfcDestinatario>{rfcDestinatario}</rfcDestinatario>\n"
            + "            <numeroEmpleado>{numeroEmpleado}</numeroEmpleado>\n"
            + "            <!--1 or more repetitions:-->\n"
            + "            {DOCUMENTOS}\n"
            + "         </registroActoAdministrativo>\n"
            + "      </web:registrarActoAdministrativo>\n"
            + "   </soapenv:Body>\n"
            + "</soapenv:Envelope>";

    String SOAP_DOCS = "            <documentos>\n"
            + "               <estatusDocumento>{estatusDocumento}</estatusDocumento>\n"
            + "               <urlDocumento>{urlDocumento}</urlDocumento>\n"
            + "               <firma>{firma}</firma>\n"
            + "               <fechaDocumento>{fechaDocumento}</fechaDocumento>\n"
            + "               <cadenaOriginal>{cadenaOriginal}</cadenaOriginal>\n"
            + "               <tipoDocumento>{tipoDocumento}</tipoDocumento>\n"
            + "               <folioSifen>{folioSifen}</folioSifen>\n"
            + "               <rfcFirmante>{rfcFirmante}</rfcFirmante>\n"
            + "               <nombreFirmante>{nombreFirmante}</nombreFirmante>\n"
            + "               <puestoFirmante>{puestoFirmante}</puestoFirmante>\n"
            + "               <fechaVigenciaFiel>{fechaVigenciaFiel}</fechaVigenciaFiel>\n"
            + "            </documentos>";
    
    String STR_CVE_DOC_TIPO = "<cveDocumentoTipo>{cveDocumentoTipo}</cveDocumentoTipo>";
    String STR_TIPO_DOC = "<cveDocumentoTipo>{cveDocumentoTipo}</cveDocumentoTipo>";

    boolean fueraHorarioLaboral() throws NyVClientException;

    ConsultaNumeroReferenciaVO consultarNumeroReferencia(String numeroReferencia) throws NyVClientException;

    List<TipoProcesoVO> consultarListaTiposProceso(String unidadAdministrativa) throws NyVClientException;

    List<ActoAdministrativoVO> consultarListaActosAdmin(String unidadAdministrativa) throws NyVClientException;

    ResponseConsultaVO consultarActoAdministrativo(String folioActoAdministrativo) throws NyVClientException;

    ResponseRegistroVO registrarActoAdministrativo(RegistroActoAdministrativo registroActoAdministrativo) throws NyVClientException;

    String getUrlWsdlNyv();

    VersionNyVEnum getVrsionNyV();

}
