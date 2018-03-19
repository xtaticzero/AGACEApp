package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

public enum ConsultaEjecutivaOrdenesRule implements Rule<ConsultaEjecutivaOrdenesBO> {
    ES_ACPPCE {
        @Override
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
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
    ROL_EMPLEADO_MAYOR_JERARQUIA_CONSULTA_ORDENES {
        @Override
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
            boolean flgRule;
            TipoEmpleadoEnum tipoEmpleadoTmp;
            if (consultaBO != null && consultaBO.getEmpleadoConsulta() != null) {
                for (DetalleEmpleadoDTO detalleEmp : consultaBO.getEmpleadoConsulta().getDetalleEmpleado()) {
                    consultaBO.setRule(ES_ACPPCE);
                    if (!consultaBO.getRule().process(consultaBO)) {
                        tipoEmpleadoTmp = detalleEmp.getTipoEmpleado();
                        flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS));
                        consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.CONSULTOR_INSUMOS : tipoEmpleadoTmp);
                        if (flgRule) {
                            return setUnidadDesahogo(consultaBO, detalleEmp);
                        }
                        flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.FIRMANTE));
                        consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.FIRMANTE : tipoEmpleadoTmp);
                        if (flgRule) {
                            return setUnidadDesahogo(consultaBO, detalleEmp);
                        }
                        flgRule = (tipoEmpleadoTmp != null && detalleEmp.getTipoEmpleado().equals(TipoEmpleadoEnum.AUDITOR));
                        consultaBO.setRolEmpleado(flgRule ? TipoEmpleadoEnum.AUDITOR : tipoEmpleadoTmp);
                        if (flgRule) {
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
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
            if (consultaBO.getLstUnidadesAdministrativasDesahogo() != null && !consultaBO.getLstUnidadesAdministrativasDesahogo().isEmpty()) {
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
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
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
    ESTATUS_DISPONIBLES_CONSULTA_ORDENES {
        @Override
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
            List<TipoEstatusEnum> lstEstatus = new ArrayList<TipoEstatusEnum>();
            consultaBO.setGruposDeEstatusValidos(new EnumMap<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>>(AgrupadorEstatusOrdenesEnum.class));
            // Solictud de Orden
            lstEstatus.add(TipoEstatusEnum.CRON_ERROR_AL_NOTIFICAR_AL_CONTRIBUYENTE);
            lstEstatus.add(TipoEstatusEnum.SUBADMINISTRADOR_PPCE_ORDEN_CREADA_PENDIENTE_FIRMA);
            consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusOrdenesEnum.SOLICITUD_ORDEN, lstEstatus);
            // Orden Emitida
            lstEstatus = new ArrayList<TipoEstatusEnum>();
            lstEstatus.add(TipoEstatusEnum.FIRMANTE_REGISTRO_FIRMADO_ENVIADO_NOTIFICACION_CONTRIBUYENTE);
            lstEstatus.add(TipoEstatusEnum.CRON_REGISTRO_NOTIFICADO_AL_CONTRIBUYENTE);
            consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA, lstEstatus);
            // Orden en Proceso
            lstEstatus = new ArrayList<TipoEstatusEnum>();
            lstEstatus.add(TipoEstatusEnum.CRON_REGISTRO_NOTIFICADO_AL_CONTRIBUYENTE);
            consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO, lstEstatus);
            // Orden Concluida por Cambio de Metodo
            lstEstatus = new ArrayList<TipoEstatusEnum>();
            lstEstatus.add(TipoEstatusEnum.FIRMANTE_ORDEN_CONCLUIDA_POR_CAMBIO_METODO);
            consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusOrdenesEnum.ORDEN_CONCLUIDA_CAMBIO_METODO, lstEstatus);
            // Orden Concluida
            lstEstatus = new ArrayList<TipoEstatusEnum>();
            lstEstatus.add(TipoEstatusEnum.FIRMANTE_ORDEN_CONCLUIDA);
            consultaBO.getGruposDeEstatusValidos().put(AgrupadorEstatusOrdenesEnum.ORDEN_CONCLUIDA, lstEstatus);
            return true;
        }
    },
    METODOS_DISPONIBLES_CONSULTA_ORDENES {
        @Override
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
            consultaBO.setLstMetodosValidos(new ArrayList<TipoMetodoEnum>());
            consultaBO.getLstMetodosValidos().add(TipoMetodoEnum.ORG);
            consultaBO.getLstMetodosValidos().add(TipoMetodoEnum.UCA);
            consultaBO.getLstMetodosValidos().add(TipoMetodoEnum.REE);
            consultaBO.getLstMetodosValidos().add(TipoMetodoEnum.EHO);
            consultaBO.getLstMetodosValidos().add(TipoMetodoEnum.MCA);
            return true;
        }
    },
    SEMAFOROS_DISPONIBLES_CONSULTA_ORDENES {
        @Override
        public boolean process(ConsultaEjecutivaOrdenesBO consultaBO) {
            consultaBO.setLstSemaforosValidos(new ArrayList<SemaforoEnum>());
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_VERDE);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_AMARILLO);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_NARANJA);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_ROJO);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_CAFE);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_AZUL);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_NEGRO);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_GRIS);
            consultaBO.getLstSemaforosValidos().add(SemaforoEnum.SEMAFORO_BLANCO);
            return true;
        }
    };

    private static boolean setUnidadDesahogo(ConsultaEjecutivaOrdenesBO consultaBO, DetalleEmpleadoDTO detalleEmp) {
        setUnidadAdministrativa(consultaBO, detalleEmp);
        consultaBO.setRule(ES_UNA_UNIDAD_ADMINISTRATIVA_VALIDA);
        return consultaBO.getRule().process(consultaBO);
    }

    private static void setUnidadAdministrativa(ConsultaEjecutivaOrdenesBO consultaBO, DetalleEmpleadoDTO detalle) {
        consultaBO.setLstUnidadesAdministrativasDesahogo(new ArrayList<AraceDTO>());
        consultaBO.getLstUnidadesAdministrativasDesahogo().add(detalle.getCentral());
    }

}
