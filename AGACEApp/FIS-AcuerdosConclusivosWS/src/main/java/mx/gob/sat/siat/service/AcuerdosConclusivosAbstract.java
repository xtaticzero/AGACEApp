/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.service;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.RespuestaOrdenAcuerdoConclusivoDTO;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class AcuerdosConclusivosAbstract extends ServiceAbstract {

    private static final long serialVersionUID = 3268956279988874434L;

    @SuppressWarnings("unchecked")
    protected void fillUnidadAdministrativa(RespuestaOrdenAcuerdoConclusivoDTO acuerdoConclusivoDTO) {
        if (acuerdoConclusivoDTO != null) {
            init();
            AraceDTO unidad = MAP_UNIDAD_ADMINISTRATIVA.get(acuerdoConclusivoDTO.getIdUnidadAdministrativa());
            if (unidad != null) {
                acuerdoConclusivoDTO.setUnidadAdministrativa(unidad.getNombre());
            }
        }
    }

}
