/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaInsumosBO;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum ConsultaEjecutivaInsumosRule implements Rule<ConsultaEjecutivaInsumosBO> {

    ES_ACIACE {
                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    boolean flgRule;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            flgRule = detalleEmp.getCentral() != null && detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ACIACE.getId();
                            if (flgRule) {
                                consultaBO.setIsAciace(flgRule);
                                return flgRule;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            },
    ES_CENTRAL_ACPPCE {
                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    boolean flgRule;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            flgRule = detalleEmp.getCentral() != null && detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ACPPCE.getId();
                            if (flgRule && (TipoEmpleadoEnum.CONSULTOR_INSUMOS.equals(detalleEmp.getTipoEmpleado())
                            || (TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(detalleEmp.getTipoEmpleado()) && detalleEmp.getLstAraces().isEmpty()))) {
                                consultaBO.setCentralACPPCE(flgRule);
                                consultaBO.setAdministradorACPPCE(
                                        TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(detalleEmp.getTipoEmpleado()) && detalleEmp.getLstAraces().isEmpty());
                                return flgRule;
                            }
                        }
                    }
                    return false;
                }
            },
    ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_INSUMOS {
                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    boolean flgRule;
                    TipoEmpleadoEnum tipoEmpleadoTmp;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            tipoEmpleadoTmp = detalleEmp.getTipoEmpleado();
                            flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS));
                            consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.CONSULTOR_INSUMOS : tipoEmpleadoTmp);
                            if (flgRule) {
                                obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.CONSULTOR_INSUMOS, TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                                validarTipoEmpleado(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS, consultaBO.getLstSubordinados());
                                return setUnidadDesahogo(consultaBO, detalleEmp);
                            }
                            flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS));
                            consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.ASIGNADOR_INSUMOS : tipoEmpleadoTmp);
                            if (flgRule) {
                                obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.ASIGNADOR_INSUMOS, TipoEmpleadoEnum.VALIDADOR_INSUMOS);
                                validarTipoEmpleado(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.VALIDADOR_INSUMOS, consultaBO.getLstSubordinados());
                                return setUnidadDesahogo(consultaBO, detalleEmp);
                            }
                            flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS));
                            consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.VALIDADOR_INSUMOS : tipoEmpleadoTmp);
                            validarTipoEmpleado(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.USUARIO_INSUMOS, consultaBO.getLstSubordinados());
                            if (flgRule) {
                                consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
                                return setUnidadDesahogo(consultaBO, detalleEmp);
                            }
                            flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.USUARIO_INSUMOS));
                            consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.USUARIO_INSUMOS : tipoEmpleadoTmp);
                            if (flgRule) {
                                consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
                                return setUnidadDesahogo(consultaBO, detalleEmp);
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            },
    UNIDADES_ADMINISTRATIVAS_DESAHOGO {
                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    return (consultaBO.getMapGruposUnidades() != null && !consultaBO.getMapGruposUnidades().isEmpty());
                }

            },
    ES_UNA_UNIDAD_ADMINISTRATIVA_VALIDA {
                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    for (DetalleEmpleadoDTO det : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_NORTE_CENTRO.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_NOROESTE.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_OCCIDENTE.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_CENTRO.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_SUR.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ACPPCE.getId()) {
                            return true;
                        }
                    }
                    return false;
                }

            },
    ESTATUS_DISPONIBLES_CONSULTA_INSUMOS {

                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    consultaBO.setLstEstatusValidos(new ArrayList<TipoEstatusEnum>());
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.INSUMO_REGISTRADO);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.ADMINISTRADOR_INSUMO_ASIGNADOS_SUBADMINISTRADOR);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADO_ADMINISTRADOR);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.ACIACE_INSUMO_RETROALIMENTADO);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_ACEPTADOS);
                    consultaBO.getLstEstatusValidos().add(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_NO_APROBADOS);
                    return true;
                }

            },
    ASIGNA_USUARIO_ACIACE_ADMINISTRADOR {
                @Override
                public boolean process(ConsultaEjecutivaInsumosBO consultaBO) {
                    if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace() && TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(consultaBO.getRolEmpleado())) {
                        obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.ASIGNADOR_INSUMOS, TipoEmpleadoEnum.USUARIO_INSUMOS);
                    }
                    return false;
                }
            };

    private static boolean setUnidadDesahogo(ConsultaEjecutivaInsumosBO consultaBO, DetalleEmpleadoDTO detalleEmp) {
        consultaBO.setRule(ES_CENTRAL_ACPPCE);
        boolean isCentralACPPCE = consultaBO.getRule().process(consultaBO);
        consultaBO.setRule(ES_ACIACE);
        boolean flgRule = consultaBO.getRule().process(consultaBO);
        if (flgRule || isCentralACPPCE) {
            consultaBO.setRule(UNIDADES_ADMINISTRATIVAS_DESAHOGO);
            return consultaBO.getRule().process(consultaBO);
        } else {
            setUnidadAdministrativa(consultaBO, detalleEmp);
            consultaBO.setRule(ES_UNA_UNIDAD_ADMINISTRATIVA_VALIDA);
            return consultaBO.getRule().process(consultaBO);
        }
    }

    private static void setUnidadAdministrativa(ConsultaEjecutivaInsumosBO consultaBO, DetalleEmpleadoDTO detalle) {

    }

    private static void obtenerListSubordinado(ConsultaEjecutivaInsumosBO consultaBO, TipoEmpleadoEnum jefeTipo, TipoEmpleadoEnum subordinadoTipo) {
        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> mapJefe = consultaBO.getEmpleadoConsulta().getSubordinados();
        boolean flgResult = ((mapJefe != null) && (!mapJefe.isEmpty()));
        if (flgResult && mapJefe.containsKey(jefeTipo)) {
            Map<TipoEmpleadoEnum, List<EmpleadoDTO>> mapSubordinado = mapJefe.get(jefeTipo);
            if (mapSubordinado.containsKey(subordinadoTipo)) {
                List<EmpleadoDTO> lstSubordinados = mapSubordinado.get(subordinadoTipo);
                consultaBO.setLstSubordinados(lstSubordinados);
            } else {
                consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
            }
        } else {
            consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
        }
    }

    private static boolean validarTipoEmpleado(EmpleadoDTO empleado, TipoEmpleadoEnum tipoEmpleado, List<EmpleadoDTO> subordinados) {
        boolean resultado = false;
        if (empleado != null && empleado.getDetalleEmpleado() != null && !empleado.getDetalleEmpleado().isEmpty()) {
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                if (detalle.getTipoEmpleado().equals(tipoEmpleado) && subordinados != null) {
                    subordinados.add(empleado);
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }
}
