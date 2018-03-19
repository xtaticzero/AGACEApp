/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ServicioInsumosAbstract extends ServiceAbstract {

    private static final long serialVersionUID = -2900446441067971968L;

    protected void fillAdministrador(List<FecetInsumo> insumos, List<EmpleadoDTO> lstAdministradores) {
        if (insumos != null && lstAdministradores != null) {
            for (FecetInsumo insumo : insumos) {
                for (EmpleadoDTO administrador : lstAdministradores) {
                    String rfcAdmin = insumo.getRfcAdministrador();
                    if (rfcAdmin != null && administrador.getRfc().equalsIgnoreCase(rfcAdmin)) {
                        insumo.setEmpleado(administrador);
                    }
                }
            }
        }
    }

    public EmpleadoDTO obtenerEmpleadoAcceso(String rfc, TipoEmpleadoEnum tipoEmpleado) throws NoExisteEmpleadoException {
        try {
            EmpleadoDTO empleado = getEmpleadoCompleto(rfc);
            if (validarExistenciaTipoEmpleado(empleado, tipoEmpleado)) {
                return empleado;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener el empleado", e);
        }
        throw new NoExisteEmpleadoException("empleado.no.existente");

    }

    public Map<BigDecimal, GrupoUnidadesAdminXGeneral> obtenerMapGruposDeUnidades(EmpleadoDTO empleadoDto) {
        Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos = new HashMap<BigDecimal, GrupoUnidadesAdminXGeneral>();
        try {
            List<GrupoUnidadesAdminXGeneral> lstGrupos = getServiceCatGrupoDeUnidadAdmin().getLstGrupos(empleadoDto);
            if (lstGrupos != null && !lstGrupos.isEmpty()) {
                for (GrupoUnidadesAdminXGeneral grupo : lstGrupos) {
                    mapGrupos.put(grupo.getGrupo().getIdGrupo(), grupo);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return mapGrupos;
    }

    public void llenarDetalleGrupoUnidadAdmin(Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos, List<FecetInsumo> lstInsumos) {
        if (lstInsumos != null && !lstInsumos.isEmpty() && mapGrupos != null) {

            for (FecetInsumo insumo : lstInsumos) {
                FececArace newArace;
                FececUnidadAdministrativa newUmidadAdmin;
                try {
                    if (insumo.getFececArace() == null && insumo.getIdArace() != null) {
                        if (mapGrupos.containsKey(insumo.getIdArace())) {
                            newArace = new FececArace();
                            newUmidadAdmin = new FececUnidadAdministrativa();
                            insumo.setFececArace(newArace);
                            insumo.setFececUnidadAdministrativa(newUmidadAdmin);
                            insumo.getFececArace().setIdArace(insumo.getIdArace());
                            insumo.getFececUnidadAdministrativa().setIdUnidadAdministrativa(insumo.getIdArace());
                            insumo.getFececArace().setNombre(mapGrupos.get(insumo.getFececArace().getIdArace()).getGrupo().getNombre());
                            insumo.getFececUnidadAdministrativa().setNombre(mapGrupos.get(insumo.getFececArace().getIdArace()).getGrupo().getNombre());
                            insumo.getFececArace().setCentral(mapGrupos.get(insumo.getFececArace().getIdArace()).getGrupo().getCentral());
                        }
                    } else if (mapGrupos.containsKey(insumo.getFececArace().getIdArace())) {
                        insumo.getFececArace().setNombre(mapGrupos.get(insumo.getFececArace().getIdArace()).getGrupo().getNombre());
                        insumo.getFececArace().setCentral(mapGrupos.get(insumo.getFececArace().getIdArace()).getGrupo().getCentral());
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }

    public List<FecetDocumento> obtenerDocumentosByIdInsumo(FecetInsumo insumo) {
        try {
            if (insumo != null) {
                return getFecetDocumentoDao().obtenerDocumentosByIdInsumo(insumo.getIdInsumo());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<FecetDocumento>();
    }

}
