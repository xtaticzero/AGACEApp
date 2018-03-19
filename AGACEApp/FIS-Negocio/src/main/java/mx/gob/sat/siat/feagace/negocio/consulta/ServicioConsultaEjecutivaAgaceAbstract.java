/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ServicioConsultaEjecutivaAgaceAbstract extends ServiceAbstract {

    private static final long serialVersionUID = -4804127901359593312L;

    @SuppressWarnings("unchecked")
    protected void fillUnidadAdministrativa(List<?> lstDtoAgace) {
        if (lstDtoAgace != null) {
            for (Object dtoAgace : lstDtoAgace) {
                if (dtoAgace instanceof FecetInsumo) {
                    List<FecetInsumo> listaRegistros = (List<FecetInsumo>) lstDtoAgace;
                    for (FecetInsumo fecetInsumo : listaRegistros) {
                        fecetInsumo.setFececUnidadAdministrativa(fillUnidadAdministrativa(fecetInsumo.getIdUnidadAdministrativa().intValue()));
                    }
                    break;
                } else if (dtoAgace instanceof FecetPropuesta) {
                    List<FecetPropuesta> lstPropuestas = (List<FecetPropuesta>) lstDtoAgace;
                    for (FecetPropuesta propuesta : lstPropuestas) {
                        AraceDTO unidadAdministrativa = MAP_UNIDAD_ADMINISTRATIVA.get(propuesta.getIdArace().intValue());
                        if (unidadAdministrativa != null) {
                            propuesta.setFececArace(getAraceFromAraceDTO(unidadAdministrativa));
                        }
                    }
                    break;
                }
                break;
            }
        }
    }

    protected EmpleadoDTO obtenerEmpleadoTipo(String rfc, TipoEmpleadoEnum tipoEmpleado) {
        EmpleadoDTO resultado = null;
        try {
            EmpleadoDTO empleadoAux = getEmpleadoCompleto(rfc);
            if (validarExistenciaTipoEmpleado(empleadoAux, tipoEmpleado)) {
                resultado = empleadoAux;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener el empleado. ", e);
        }

        return resultado;
    }
}
