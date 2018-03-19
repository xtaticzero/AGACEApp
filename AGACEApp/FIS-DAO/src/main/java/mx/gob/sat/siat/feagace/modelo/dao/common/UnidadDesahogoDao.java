/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface UnidadDesahogoDao {

    List<AraceDTO> getLstUnidadesDesahogo(Integer idEmpleado, TipoEmpleadoEnum tipoEmpleado);
}
