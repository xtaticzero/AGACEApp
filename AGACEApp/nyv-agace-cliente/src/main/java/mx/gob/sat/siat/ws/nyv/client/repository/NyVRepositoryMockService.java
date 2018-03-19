/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.repository;

import java.util.List;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoProcesoVO;
import mx.gob.sat.siat.ws.nyv.exception.NyVClientException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface NyVRepositoryMockService {

    boolean fueraHorarioLaboral();

    ConsultaNumeroReferenciaVO consultarNumeroReferencia(String numeroReferencia) throws NyVClientException;

    List<TipoProcesoVO> consultarListaTiposProceso(String unidadAdministrativa) throws NyVClientException;

    List<ActoAdministrativoVO> consultarListaActosAdmin(String unidadAdministrativa) throws NyVClientException;

    ResponseConsultaVO consultarActoAdministrativo(String folioActoAdministrativo) throws NyVClientException;

    ResponseRegistroVO registrarActoAdministrativo(RegistroActoAdministrativo registroActoAdministrativo) throws NyVClientException;

}
