/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.ws.empleado.bean.DetalleEmpleado;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompleto;
import mx.gob.sat.siat.ws.empleado.bean.UnidadAdministrativa;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class EmpleadoServiceHelper extends BaseHelper {

    private static final long serialVersionUID = -4095517819518058707L;

    private EmpleadoServiceHelper() {
    }

    public static List<EmpleadoDTO> getLstEmpleadoDTOfromLstEmpleadoCompleto(List<EmpleadoCompleto> lstEmpleadoCompleto,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) {
        return null;
    }

    public static EmpleadoDTO getEmpleadoDTOfromEmpleadoCompleto(EmpleadoCompleto empleadoCompleto,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) {

        if (empleadoCompleto != null) {
            EmpleadoDTO empleadoDTO = getEmpleadoDTOfromEmpleado(empleadoCompleto.getEmpleado());
            if (empleadoDTO != null) {
                completarDetalleEmpleadoDto(empleadoDTO, empleadoCompleto, mapTipoEmpleado, mapUnidadesAdministrativas);
                return empleadoDTO;
            }
        }
        return null;
    }

    private static EmpleadoDTO getEmpleadoDTOfromEmpleado(Empleado empleado) {
        if (empleado == null) {
            return null;
        }
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setRfc(empleado.getRfc());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellidoPaterno(empleado.getApellidoPaterno());
        empleadoDTO.setApellidoMaterno(empleado.getApellidoMaterno());
        empleadoDTO.setCorreo(empleado.getCorreo());
        if (empleado.getEstatusEmpleado() != null) {
            empleadoDTO.setEstatusEmpleado(
                    empleado.getEstatusEmpleado().toUpperCase().equals(EstadoBooleanodeRegistroEnum.ACTIVO.getDescripcion().toUpperCase())
                            ? EstadoBooleanodeRegistroEnum.ACTIVO
                            : EstadoBooleanodeRegistroEnum.INACTIVO);
        }

        empleadoDTO.setIdEmpleado(new BigDecimal(empleado.getNumeroEmpleado()));
        empleadoDTO.setIdSuplencia(new BigDecimal(empleado.getIdSuplencia()));
        empleadoDTO.setFechaCreacion(empleado.getFechaCreacion());
        empleadoDTO.setFechaBaja(empleado.getFechaBaja());
        empleadoDTO.setIdAdmCentral(empleado.getIdAdmCentral());
        empleadoDTO.setAdmCentral(empleado.getAdmCentral());
        empleadoDTO.setIdAdmGral(empleado.getIdAdmGral());
        empleadoDTO.setAdmGral(empleado.getAdmGral());
        empleadoDTO.setIdSede(empleado.getIdSede());
        empleadoDTO.setSede(empleado.getSede());

        return empleadoDTO;
    }

    private static void completarDetalleEmpleadoDto(EmpleadoDTO empleadoDTO,
            EmpleadoCompleto empleadoBase,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) {

        if (empleadoBase.getListaDetalleEmpleado() != null && empleadoDTO != null) {
            empleadoDTO.setDetalleEmpleado(new ArrayList<DetalleEmpleadoDTO>());
            for (DetalleEmpleado detEmp : empleadoBase.getListaDetalleEmpleado()) {
                addDetallEmpleadoDto(empleadoDTO, detEmp, mapTipoEmpleado, mapUnidadesAdministrativas);
            }
        }
    }

    private static void addDetallEmpleadoDto(EmpleadoDTO empleadoDTO,
            DetalleEmpleado detEmp,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) {
        DetalleEmpleadoDTO detalleDto = new DetalleEmpleadoDTO();

        detalleDto.setCentral(detEmp.getIdCentral() != null ? mapUnidadesAdministrativas.get(detEmp.getIdUnidadAdmin()) : null);
        detalleDto.setTipoEmpleado(detEmp.getIdPerfil() != null ? mapTipoEmpleado.get(detEmp.getIdPerfil()) : null);
        detalleDto.setLstAraces(new ArrayList<AraceDTO>());

        if (!empleadoDTO.getDetalleEmpleado().contains(detalleDto)) {
            empleadoDTO.getDetalleEmpleado().add(detalleDto);
        }
    }

    public static List<AraceDTO> addAraceDtoToList(List<AraceDTO> lstAraces,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) {
        List<AraceDTO> lstAracesResult = new ArrayList<AraceDTO>();
        if (lstAraces != null) {
            for (AraceDTO arace : lstAraces) {
                lstAracesResult.add(mapUnidadesAdministrativas.get(arace.getIdArace()));
            }
        }
        return lstAracesResult;
    }

    public static DetalleEmpleadoDTO getDetalleDtoFromDetalle(DetalleEmpleado detalleEmpleado,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) {
        DetalleEmpleadoDTO detalleDto = new DetalleEmpleadoDTO();

        detalleDto.setCentral(mapUnidadesAdministrativas.get(detalleEmpleado.getIdCentral()));
        detalleDto.setTipoEmpleado(mapTipoEmpleado.get(detalleEmpleado.getIdPerfil()));

        return detalleDto;
    }

    public static List<AraceDTO> getLstAracesDtoFromLstUnidadesAdmin(List<UnidadAdministrativa> lstUnidadesAdmin) {
        if (lstUnidadesAdmin != null) {
            List<AraceDTO> lstAraces = new ArrayList<AraceDTO>();
            for (UnidadAdministrativa unidad : lstUnidadesAdmin) {
                lstAraces.add(getAraceDTOFromUnidadAdministrativa(unidad));
            }
            return lstAraces;
        }
        return new ArrayList<AraceDTO>();
    }

    public static Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(List<AraceDTO> lstUnidadesAdministrativas) {
        if (lstUnidadesAdministrativas != null) {
            Map<Integer, AraceDTO> mapUnidadesAdmin = new HashMap<Integer, AraceDTO>();
            for (AraceDTO unidad : lstUnidadesAdministrativas) {
                mapUnidadesAdmin.put(unidad.getIdArace(), unidad);
            }
            return mapUnidadesAdmin;
        }
        return new HashMap<Integer, AraceDTO>();
    }

    public static AraceDTO getAraceDTOFromUnidadAdministrativa(UnidadAdministrativa unidadAdmin) {
        AraceDTO araceDTO = new AraceDTO();
        araceDTO.setCentral((unidadAdmin.getCentral() != null && unidadAdmin.getCentral() ? EstadoBooleanoEnum.TRUE : EstadoBooleanoEnum.FALSE));
        araceDTO.setIdArace(unidadAdmin.getId());
        araceDTO.setNombre(unidadAdmin.getNombre());
        araceDTO.setSede(unidadAdmin.getSede());
        return araceDTO;
    }

    public static List<List<Empleado>> getLstXEmpleado(List<Empleado> lstEmpleadosWS) {
        List<List<Empleado>> lstsXEmpleados = new ArrayList<List<Empleado>>();
        if (lstEmpleadosWS != null && !lstEmpleadosWS.isEmpty()) {
            Collections.sort(lstEmpleadosWS, new Comparator<Empleado>() {
                @Override
                public int compare(Empleado empleadoBase, Empleado empleadoComparado) {
                    return empleadoBase.getRfc().compareTo(empleadoComparado.getRfc());
                }
            });
            List<Empleado> lstXEmpleado = new ArrayList<Empleado>();
            String rfcEmpleado;
            for (Empleado empleadoTmp : lstEmpleadosWS) {
                rfcEmpleado = empleadoTmp.getRfc();
                if (lstXEmpleado.isEmpty()) {
                    lstXEmpleado.add(empleadoTmp);
                } else {
                    if (lstXEmpleado.get(0).getRfc().equals(rfcEmpleado)) {
                        lstXEmpleado.add(empleadoTmp);
                    } else {
                        lstsXEmpleados.add(lstXEmpleado);
                        lstXEmpleado = new ArrayList<Empleado>();
                        lstXEmpleado.add(empleadoTmp);
                    }
                }
            }
            lstsXEmpleados.add(lstXEmpleado);
        }
        return lstsXEmpleados;
    }

    public static void addSubordinadoOJefe(Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> empleadoSubordinadosJefes,
            DetalleEmpleadoDTO detalleEmpleadoDTO,
            EmpleadoDTO subordinado) throws CloneNotSupportedException {
        if (empleadoSubordinadosJefes != null) {
            List<DetalleEmpleadoDTO> lstDetalleSubordinado;
            lstDetalleSubordinado = subordinado.getDetalleEmpleado() != null ? subordinado.getDetalleEmpleado() : new ArrayList<DetalleEmpleadoDTO>();

            for (DetalleEmpleadoDTO detalleSubordinado : lstDetalleSubordinado) {
                addElementoHasMap(empleadoSubordinadosJefes,
                        detalleEmpleadoDTO.getTipoEmpleado(),
                        detalleSubordinado.getTipoEmpleado(),
                        subordinado);
            }
        }
    }

    public static void addElementoHasMap(Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinados,
            TipoEmpleadoEnum tipoEmpleadoResponsable,
            TipoEmpleadoEnum tipoEmpleadoSubordinado,
            EmpleadoDTO subordinado) throws CloneNotSupportedException {

        EmpleadoDTO subordinadoTmp = subordinado.clone();

        List<DetalleEmpleadoDTO> lstDetalleValido = new ArrayList<DetalleEmpleadoDTO>();

        for (DetalleEmpleadoDTO detalle : subordinadoTmp.getDetalleEmpleado()) {
            if (detalle.getTipoEmpleado().equals(tipoEmpleadoSubordinado)) {
                lstDetalleValido.add(detalle);
            }
        }
        subordinadoTmp.setDetalleEmpleado(lstDetalleValido);

        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> mapTipoSub;

        if (subordinados.containsKey(tipoEmpleadoResponsable)) {
            mapTipoSub = subordinados.get(tipoEmpleadoResponsable);
            if (mapTipoSub.containsKey(tipoEmpleadoSubordinado)) {
                if (!mapTipoSub.get(tipoEmpleadoSubordinado).contains(subordinadoTmp)) {
                    mapTipoSub.get(tipoEmpleadoSubordinado).add(subordinadoTmp);
                }
            } else {
                List<EmpleadoDTO> lstSubordinados = new ArrayList<EmpleadoDTO>();
                removeDetalleSinRelacion(subordinadoTmp, tipoEmpleadoSubordinado);
                lstSubordinados.add(subordinadoTmp);
                mapTipoSub.put(tipoEmpleadoSubordinado, lstSubordinados);
            }
        } else {
            mapTipoSub = new EnumMap<TipoEmpleadoEnum, List<EmpleadoDTO>>(TipoEmpleadoEnum.class);
            List<EmpleadoDTO> lstSubordinados = new ArrayList<EmpleadoDTO>();
            removeDetalleSinRelacion(subordinadoTmp, tipoEmpleadoSubordinado);
            lstSubordinados.add(subordinadoTmp);
            mapTipoSub.put(tipoEmpleadoSubordinado, lstSubordinados);
            subordinados.put(tipoEmpleadoResponsable, mapTipoSub);
        }
    }

    public static void removeDetalleSinRelacion(EmpleadoDTO empleadoDTO, TipoEmpleadoEnum tipoRequerido) {
        List<DetalleEmpleadoDTO> lstDetalleDepurada = new ArrayList<DetalleEmpleadoDTO>();
        for (DetalleEmpleadoDTO detalle : empleadoDTO.getDetalleEmpleado()) {
            if (detalle.getTipoEmpleado().equals(tipoRequerido)) {
                lstDetalleDepurada.add(detalle);
            }
        }
        if (!lstDetalleDepurada.isEmpty()) {
            empleadoDTO.setDetalleEmpleado(lstDetalleDepurada);
        }
    }

}
