/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.insumos.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class ConsultaEjecutivaInsumosHelper {

    private ConsultaEjecutivaInsumosHelper() {
    }

    public static FececUnidadAdministrativa castAraceDtoToFececUnidadAdministrativa(AraceDTO arace) {
        if (arace != null) {
            FececUnidadAdministrativa unidadAdmin = new FececUnidadAdministrativa();
            unidadAdmin.setIdUnidadAdministrativa(new BigDecimal(arace.getIdArace()));
            unidadAdmin.setNombre(arace.getNombre());
            unidadAdmin.setDescripcion(arace.getSede());
            unidadAdmin.setBlnActivo(arace.getCentral().isValue());

            return unidadAdmin;
        }
        return null;
    }

    public static void setUnidadAdministrativa(List<FecetInsumo> lstInsumos,
            Map<Integer, FececUnidadAdministrativa> mapUnidadesAdministrativas) {
        if (lstInsumos != null && mapUnidadesAdministrativas != null) {
            for (FecetInsumo entry : lstInsumos) {
                entry.setFececUnidadAdministrativa(mapUnidadesAdministrativas.get(entry.getIdUnidadAdministrativa().intValue()));
            }
        }
    }
}
