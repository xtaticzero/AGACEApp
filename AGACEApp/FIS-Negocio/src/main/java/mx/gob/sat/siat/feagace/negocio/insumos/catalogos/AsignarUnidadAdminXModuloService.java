/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.insumos.catalogos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface AsignarUnidadAdminXModuloService {
    List<FececMetodo> getLstModulos() throws CatalogosServiceException;
}
