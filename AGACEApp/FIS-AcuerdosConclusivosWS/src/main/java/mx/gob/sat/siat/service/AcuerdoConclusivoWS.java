package mx.gob.sat.siat.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.RespuestaOrdenAcuerdoConclusivoDTO;
import mx.gob.sat.siat.model.AcuerdoConclusivoDTO;


@WebService
@SOAPBinding(style = Style.RPC)
public interface AcuerdoConclusivoWS {

    @WebMethod
    Integer notificaCreacionAcuerdoConclusivo(AcuerdoConclusivoDTO acuerdoConclusivoDTO);

    @WebMethod
    Integer notificaCierreAcuerdoConclusivo(AcuerdoConclusivoDTO acuerdoConclusivoDTO);

    @WebMethod
    RespuestaOrdenAcuerdoConclusivoDTO getOrden(String numeroOrden);
}