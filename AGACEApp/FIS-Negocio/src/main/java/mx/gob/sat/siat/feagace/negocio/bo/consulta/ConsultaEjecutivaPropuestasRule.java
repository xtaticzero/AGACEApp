/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum ConsultaEjecutivaPropuestasRule implements Rule<ConsultaEjecutivaPropuestasBO> {

    ES_ACPPCE {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    boolean flgAcppce;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            flgAcppce = detalleEmp.getCentral() != null && detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ACPPCE.getId();
                            if (flgAcppce) {
                                return flgAcppce;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }

            },
    ES_ACPPCE_REGIONAL {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    boolean flgAcppce;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            flgAcppce = (detalleEmp.getCentral() != null);
                            if (flgAcppce) {
                                boolean[] condicionesObligatorias
                                = {(consultaBO.isIsAcppce()),
                                    (detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_CENTRO.getId()),
                                    (detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_SUR.getId()),
                                    (detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_NOROESTE.getId()),
                                    (detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()),
                                    (detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_NORTE_CENTRO.getId()),
                                    (detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_OCCIDENTE.getId())};

                                return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesObligatorias);
                            }
                            return flgAcppce;
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            },
    ES_REGIONAL {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    boolean flgAcppce;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            flgAcppce = (detalleEmp.getCentral() != null);
                            if (flgAcppce) {
                                boolean[] condicionesObligatorias
                                = {esRegional(consultaBO)};
                                return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesObligatorias);
                            }
                            return flgAcppce;
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            },
    ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    boolean flgRule;
                    TipoEmpleadoEnum tipoEmpleadoTmp;
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            consultaBO.setRule(ES_ACPPCE);
                            if (consultaBO.getRule().process(consultaBO) || consultaBO.isProgramacion()) {
                                tipoEmpleadoTmp = detalleEmp.getTipoEmpleado();
                                flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS));
                                consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.CONSULTOR_INSUMOS : tipoEmpleadoTmp);
                                if (flgRule) {
                                    obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.CONSULTOR_INSUMOS, TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                                    if (validarTipoEmpleado(TipoEmpleadoEnum.ASIGNADOR_INSUMOS, consultaBO.getEmpleadoConsulta())) {
                                        consultaBO.getLstSubordinados().add(consultaBO.getEmpleadoConsulta());
                                    }
                                    return setUnidadDesahogo(consultaBO, detalleEmp);
                                }
                                flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS));
                                consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.ASIGNADOR_INSUMOS : tipoEmpleadoTmp);
                                if (flgRule) {
                                    obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.ASIGNADOR_INSUMOS, TipoEmpleadoEnum.VALIDADOR_INSUMOS);
                                    if (validarTipoEmpleado(TipoEmpleadoEnum.VALIDADOR_INSUMOS, consultaBO.getEmpleadoConsulta())) {
                                        consultaBO.getLstSubordinados().add(consultaBO.getEmpleadoConsulta());
                                    }
                                    return setUnidadDesahogo(consultaBO, detalleEmp);
                                }
                                flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS));
                                consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.VALIDADOR_INSUMOS : tipoEmpleadoTmp);
                                if (flgRule) {
                                    consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
                                    return setUnidadDesahogo(consultaBO, detalleEmp);
                                }

                            } else {
                                tipoEmpleadoTmp = detalleEmp.getTipoEmpleado();
                                flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS));
                                consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.CONSULTOR_INSUMOS : tipoEmpleadoTmp);
                                if (flgRule) {
                                    obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.CONSULTOR_INSUMOS, TipoEmpleadoEnum.FIRMANTE);
                                    if (validarTipoEmpleado(TipoEmpleadoEnum.FIRMANTE, consultaBO.getEmpleadoConsulta())) {
                                        consultaBO.getLstSubordinados().add(consultaBO.getEmpleadoConsulta());
                                    }
                                    return setUnidadDesahogo(consultaBO, detalleEmp);
                                }
                                flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.FIRMANTE));
                                consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.FIRMANTE : tipoEmpleadoTmp);
                                if (flgRule) {
                                    obtenerListSubordinado(consultaBO, TipoEmpleadoEnum.FIRMANTE, TipoEmpleadoEnum.AUDITOR);
                                    if (validarTipoEmpleado(TipoEmpleadoEnum.AUDITOR, consultaBO.getEmpleadoConsulta())) {
                                        consultaBO.getLstSubordinados().add(consultaBO.getEmpleadoConsulta());
                                    }
                                    return setUnidadDesahogo(consultaBO, detalleEmp);
                                }
                                flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.AUDITOR));
                                consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.AUDITOR : tipoEmpleadoTmp);
                                if (flgRule) {
                                    consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
                                    return setUnidadDesahogo(consultaBO, detalleEmp);
                                }
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
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    if (consultaBO.getLstUnidadesAdministrativasDesahogo() != null
                    && !consultaBO.getLstUnidadesAdministrativasDesahogo().isEmpty()) {
                        List<AraceDTO> lstUnidAdminDesahogo = new ArrayList<AraceDTO>();
                        for (AraceDTO col : consultaBO.getLstUnidadesAdministrativasDesahogo()) {
                            if (col.getIdArace() == (int) TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()) {
                                lstUnidAdminDesahogo.add(col);
                                continue;
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ADACE_NORTE_CENTRO.getId()) {
                                lstUnidAdminDesahogo.add(col);
                                continue;
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ADACE_NOROESTE.getId()) {
                                lstUnidAdminDesahogo.add(col);
                                continue;
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ADACE_OCCIDENTE.getId()) {
                                lstUnidAdminDesahogo.add(col);
                                continue;
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ADACE_CENTRO.getId()) {
                                lstUnidAdminDesahogo.add(col);
                                continue;
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ADACE_SUR.getId()) {
                                lstUnidAdminDesahogo.add(col);
                                continue;
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ACAOCE.getId()) {
                                lstUnidAdminDesahogo.add(col);
                            }
                            if (col.getIdArace() == (int) TipoAraceEnum.ACOECE.getId()) {
                                lstUnidAdminDesahogo.add(col);
                            }
                        }
                        consultaBO.setLstUnidadesAdministrativasDesahogo(!lstUnidAdminDesahogo.isEmpty() ? lstUnidAdminDesahogo : null);
                        return true;

                    }
                    return false;
                }

            },
    ES_UNA_UNIDAD_ADMINISTRATIVA_VALIDA {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
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
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ACAOCE.getId()) {
                            return true;
                        }
                        if (det.getCentral().getIdArace() == (int) TipoAraceEnum.ACOECE.getId()) {
                            return true;
                        }
                    }
                    return false;
                }

            },
    ESTATUS_DISPONIBLES_CONSULTA_PROPUESTAS {

                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    consultaBO.setGruposDeEstatusValidos(new EnumMap<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO>(AgrupadorEstatusPropuestasEnum.class));
                    generaEstatus(consultaBO);
                    return true;
                }
            },
    ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    consultaBO.setGruposDeEstatusValidos(new EnumMap<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO>(AgrupadorEstatusPropuestasEnum.class));
                    generaEstatusSuper(consultaBO);
                    return true;
                }
            },
    MOSTRAR_COMBOS {
                @Override
                public boolean process(ConsultaEjecutivaPropuestasBO consultaBO) {
                    if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                        consultaBO.setLstRolesEmpleado(new ArrayList<TipoEmpleadoEnum>());
                        for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                            if (ROLES_VALIDOS.contains(detalleEmp.getTipoEmpleado())) {
                                consultaBO.getLstRolesEmpleado().add(detalleEmp.getTipoEmpleado());
                            }
                        }
                        return consultaBO.getLstRolesEmpleado() != null ? consultaBO.getLstRolesEmpleado().size() > 1 : false;
                    }
                    return false;
                }
            };

    private static final List<TipoEmpleadoEnum> ROLES_VALIDOS = Arrays.asList(TipoEmpleadoEnum.CONSULTOR_INSUMOS, TipoEmpleadoEnum.ASIGNADOR_INSUMOS, TipoEmpleadoEnum.VALIDADOR_INSUMOS, TipoEmpleadoEnum.FIRMANTE, TipoEmpleadoEnum.AUDITOR);

    private static boolean setUnidadDesahogo(ConsultaEjecutivaPropuestasBO consultaBO, DetalleEmpleadoDTO detalleEmp) {
        consultaBO.setRule(ES_ACPPCE);
        boolean flgRule = consultaBO.getRule().process(consultaBO);
        if (flgRule) {
            consultaBO.setRule(UNIDADES_ADMINISTRATIVAS_DESAHOGO);
            return consultaBO.getRule().process(consultaBO);
        } else {
            setUnidadAdministrativa(consultaBO, detalleEmp);
            consultaBO.setRule(ES_UNA_UNIDAD_ADMINISTRATIVA_VALIDA);
            return consultaBO.getRule().process(consultaBO);
        }
    }

    private static void setUnidadAdministrativa(ConsultaEjecutivaPropuestasBO consultaBO, DetalleEmpleadoDTO detalle) {
        consultaBO.setLstUnidadesAdministrativasDesahogo(new ArrayList<AraceDTO>());
        consultaBO.getLstUnidadesAdministrativasDesahogo().add(detalle.getCentral());
    }

    private static void obtenerListSubordinado(ConsultaEjecutivaPropuestasBO consultaBO, TipoEmpleadoEnum jefeTipo, TipoEmpleadoEnum subordinadoTipo) {
        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> mapJefe = consultaBO.getEmpleadoConsulta().getSubordinados();
        boolean flgResult = ((mapJefe != null) && (!mapJefe.isEmpty()));
        if (flgResult && mapJefe.containsKey(jefeTipo)) {
            Map<TipoEmpleadoEnum, List<EmpleadoDTO>> mapSubordinado = mapJefe.get(jefeTipo);
            if (mapSubordinado.containsKey(subordinadoTipo)) {
                List<EmpleadoDTO> lstSubordinados = mapSubordinado.get(subordinadoTipo);
                if (validaRol(subordinadoTipo, lstSubordinados)) {
                    consultaBO.setLstSubordinados(lstSubordinados);
                } else {
                    consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
                }
            }
        } else {
            consultaBO.setLstSubordinados(new ArrayList<EmpleadoDTO>());
        }
    }

    private static boolean validaRol(TipoEmpleadoEnum tipoEsperado, List<EmpleadoDTO> lstEmpleados) {
        if (lstEmpleados != null) {
            for (EmpleadoDTO empleado : lstEmpleados) {
                for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                    if (tipoEsperado.equals(detalle.getTipoEmpleado())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void generaEstatusSuper(ConsultaEjecutivaPropuestasBO consultaBO) {
        if (consultaBO.isProgramacion()) {
            generarEstatusProgramacion(consultaBO);
        } else {
            generarEstatusOperacion(consultaBO);
        }
    }

    private static void generaEstatus(ConsultaEjecutivaPropuestasBO consultaBO) {
        if (consultaBO.isIsAcppce() || (esRegional(consultaBO) && consultaBO.isProgramacion())) {
            generarEstatusProgramacion(consultaBO);
        } else {
            generarEstatusOperacion(consultaBO);
        }
    }

    private static boolean esRegional(ConsultaEjecutivaPropuestasBO consultaBO) {
        EmpleadoDTO empleado = consultaBO.getEmpleadoConsulta();
        if (empleado != null && empleado.getDetalleEmpleado() != null && !empleado.getDetalleEmpleado().isEmpty()) {
            for (DetalleEmpleadoDTO detalleEmp : empleado.getDetalleEmpleado()) {
                boolean validacion1 = detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_CENTRO.getId()
                        || detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_SUR.getId()
                        || detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_NOROESTE.getId();
                boolean validacion2 = detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()
                        || detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_NORTE_CENTRO.getId()
                        || detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ADACE_OCCIDENTE.getId();
                return validacion1 || validacion2;

            }
        }
        return false;
    }

    private static void generarEstatusProgramacion(ConsultaEjecutivaPropuestasBO consultaBO) {
        List<TipoEstatusEnum> lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRADA);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.REGISTRADA, new ConsultaEstatusAccionesBO(lstEstatus));
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_ENVIADA_ADACE, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_PENDIENTE);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_PENDIENTE_POR_COMITE, new ConsultaEstatusAccionesBO(lstEstatus));
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_PENDIENTE_COMITE_DESCONCENTRADO, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_NO_APROBADA_COMITE, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_ASIGNADA_CENTRAL);
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL);
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_AUDITOR);
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_FIRMANTE);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_EN_SELECTOR, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_ABIERTA_ADACE, new ConsultaEstatusAccionesBO(lstEstatus));
    }

    private static void generarEstatusOperacion(ConsultaEjecutivaPropuestasBO consultaBO) {
        List<TipoEstatusEnum> lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_FIRMANTE);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_ASIGNADA_A_FIRMANTE, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_AUDITOR);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_ASIGNADA_A_AUDITOR, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.CANCELACION_PENDIENTE_DE_VALIDACION);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_CANCELADA_PENDIENTE_DE_VALIDACION,
                new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_CONCLUIDA);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTAS_CANCELADAS, new ConsultaEstatusAccionesBO(lstEstatus,
                TipoAccionPropuesta.APROBACION_CANCELACION, TipoAccionPropuesta.APROBACION_RECHAZO));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_RECHAZADA_PENDIENTE_DE_VALIDACION, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_PENDIENTE);
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_RECHAZADA, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.RETROALIMENTACION_PENDIENTE_DE_VALIDACION);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_RETROALIMENTADA_PENDIENTE_DE_VALIDACION, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_RETROALIMENTADA);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_EN_RETROALIMENTACION_AREA_OPERATIVA, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_EN_RETROALIMENTACION_A_PROGRAMACION, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_TRANSFERIDA_PENDIENTE_DE_VALIDACION, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_TRANSFERIDA);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_TRANSFERIDA, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_PENDIENTE_VALIDACION);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_PENDIENTE_DE_VALIDACION, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_ENVIADA_PARA_VERIFICACION_PROCEDENCIA);
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_SUBADMINISTRADOR_EMISION_ODENES);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.PROPUESTA_EN_SOLICITUD_DE_EMISION_DE_ORDEN, new ConsultaEstatusAccionesBO(lstEstatus));
       
        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_PENDIENTE_FIRMA_POR_FIRMANTE);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.ORDEN_EMITIDA, new ConsultaEstatusAccionesBO(lstEstatus));

        lstEstatus = new ArrayList<TipoEstatusEnum>();
        lstEstatus.add(TipoEstatusEnum.PROPUESTA_CONCLUIDA);
        consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusPropuestasEnum.ORDEN_EN_PROCESO, new ConsultaEstatusAccionesBO(lstEstatus,
                TipoAccionPropuesta.APROBACION_CANCELACION, TipoAccionPropuesta.APROBACION_RECHAZO));

    }

    private static boolean validarTipoEmpleado(TipoEmpleadoEnum tipoEmpleado, EmpleadoDTO empleado) {
        boolean resultado = false;
        if (empleado != null && empleado.getDetalleEmpleado() != null && !empleado.getDetalleEmpleado().isEmpty()) {
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                if (detalle.getTipoEmpleado().equals(tipoEmpleado)) {
                    return true;
                }
            }
        }
        return resultado;
    }

}
