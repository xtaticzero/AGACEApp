package mx.gob.sat.siat.feagace.negocio.common.plazos.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.insumos.FecetSuspensionInsumoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.SolicitudRetroalimentacionInsumo;

@Service("plazosServiceInsumos")
public class PlazosServiceInsumosImpl extends PlazosService implements PlazosServiceInsumos {

    private static final long serialVersionUID = 156546546654356654L;

    private static final int DIAS_RESTAR = -2;

    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;

    @Autowired
    private transient FecetSuspensionInsumoService fecetSuspensionInsumoService;

    @Override
    public void inicializarInsumoConPlazos(List<FecetInsumo> insumos) {
        BigDecimal metodo = BigDecimal.valueOf(TipoMetodoEnum.INS.getId());
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(metodo, TiemposClaveEnum.PAINS_PLA);
        TiempoDTO tiempoPlazo1 = getTiemposService().obtenerTiempoPlazo(metodo, TiemposClaveEnum.PRINS_PLA);
        for (FecetInsumo insumo : insumos) {
            inicializarInsumoConPlazos(insumo, tiempoPlazo, tiempoPlazo1);
            insumo.setImagenSemaforo(BusinessUtil.obtenerImagenSemaforo(insumo.getSemaforo()));
            insumo.setDescripcionSemaforo(BusinessUtil.obtenerDescripcionSemaforoInsumo(insumo.getSemaforo()));
        }

    }

    @Override
    public void enviaNotificacionesCambioSemaforo() {
        List<FecetInsumo> insumos = fecetInsumoDao.obtenerInsumosANotificarCambioSemaforo();
        BigDecimal metodo = BigDecimal.valueOf(TipoMetodoEnum.INS.getId());
        List<TiempoDTO> listClaveSemaforo = getTiemposService().obtenerValoresDeSemaforo(metodo.intValue());
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(metodo, TiemposClaveEnum.PAINS_PLA);
        for (FecetInsumo insumo : insumos) {
            Date fechaFinPlazo = getTiemposService().sumarTiempo(tiempoPlazo, insumo.getFechaInicioPlazo());
            Date fechaHoy = Calendar.getInstance().getTime();
            List<FecetSuspensionInsumo> suspensiones = fecetSuspensionInsumoService.obtenerSuspensionByIdInsumo(insumo.getIdInsumo());
            if (existeSuspensionActiva(suspensiones)) {
                continue;
            }
            fechaFinPlazo = obtenerFechaFinSuspension(fechaFinPlazo, suspensiones);
            if (cambioSemaforo(fechaFinPlazo, listClaveSemaforo)) {
                insumo.setPlazoRestante(obtenerDiasRestantes(fechaFinPlazo, new Date(), tiempoPlazo));
                insumo.setDescripcionPlazoRestante(
                        getPlazosHelper().convertirDiasRestantesTexto(insumo.getPlazoRestante(), tiempoPlazo));
                envioCorreoInsumoCambioSemaforo(insumo, TIPO_CORREO_ENPLAZO);
            } else if (getTiemposHelper().comparaFechasSinTiempo(fechaFinPlazo, fechaHoy) < 0) {
                insumo.setDiasDespuesDePlazo(
                        (getTiemposHelper().calcularDiferenciaDias(fechaFinPlazo, Calendar.getInstance().getTime())));
                insumo.setDiasDespuesDePlazoDescripcion(getPlazosHelper().convertirDiasRestantesTextoSinVencimiento(insumo.getDiasDespuesDePlazo(), tiempoPlazo));
                Date fechaRechazo = fecetInsumoDao.obtenerFechaRechazoInsumo(insumo.getIdInsumo());
                Date fechaAprobacion = fecetInsumoDao.obtenerFechaAprobacionInsumo(insumo.getIdInsumo());
                if (fechaRechazo != null && getTiemposHelper().comparaFechasSinTiempo(fechaRechazo, fechaHoy) == 0) {
                    envioCorreoInsumoCambioSemaforo(insumo, TIPO_CORREO_RECHAZADO);
                } else if (fechaAprobacion != null
                        && getTiemposHelper().comparaFechasSinTiempo(fechaAprobacion, fechaHoy) == 0) {
                    envioCorreoInsumoCambioSemaforo(insumo, TIPO_CORREO_APROBADO);
                } else if (insumo.getDiasDespuesDePlazo() == Constantes.UNO) {
                    envioCorreoInsumoCambioSemaforo(insumo, TIPO_CORREO_VENCIMIENTO);
                }
            }
        }
    }

    @Override
    public boolean inicializarPlazo(BigDecimal idInsumo) {
        boolean resultado = false;
        BigDecimal metodo = BigDecimal.valueOf(TipoMetodoEnum.INS.getId());
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(metodo, TiemposClaveEnum.PAINS_PLA);
        resultado = fecetInsumoDao.actualizarFechaInicioPlazo(idInsumo, existePlazo(tiempoPlazo));
        return resultado;
    }

    @Override
    public void validarReactivacionPlazo(FecetInsumo fecetInsumo) {
        FecetSuspensionInsumo suspension = fecetSuspensionInsumoService
                .obtenerUltimaSuspension(fecetInsumo.getIdInsumo());
        if (suspension != null) {
            fecetSuspensionInsumoService.actualizarFechaFinSuspensionInsumo(new Date(),
                    suspension.getIdSuspensionInsumo());
        }
    }

    @Override
    public void validarSuspensionPlazoReasignar(FecetInsumo insumo) {
        if (insumo.getFechaInicioPlazo() != null) {
            funcionalidadSuspencionPlazo(insumo);
            fecetSuspensionInsumoService.insertReasignar(insumo);
        }
    }

    @Override
    public void validarSuspensionPlazoRetroalimentar(FecetInsumo insumo) {
        if (insumo.getFechaInicioPlazo() != null) {
            funcionalidadSuspencionPlazo(insumo);
            fecetSuspensionInsumoService.insertRetroalimentar(insumo);
        }
    }

    private void funcionalidadSuspencionPlazo(FecetInsumo insumo) {
        BigDecimal metodo = BigDecimal.valueOf(TipoMetodoEnum.INS.getId());
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(metodo, TiemposClaveEnum.PAINS_PLA);
        TiempoDTO tiempoPlazo1 = getTiemposService().obtenerTiempoPlazo(metodo, TiemposClaveEnum.PRINS_PLA);
        inicializarInsumoConPlazos(insumo, tiempoPlazo, tiempoPlazo1);
        if (insumo.getPlazoRestante() <= 0) {
            return;
        }
        Set<String> destinatarios = new TreeSet<String>();
        if (insumo.getRfcSubadministrador() != null && !insumo.getRfcSubadministrador().isEmpty()) {
            getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, metodo, destinatarios, ClvSubModulosAgace.INSUMOS);
        }
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumo.getIdUnidadAdministrativa(), destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_SUSPENSION_PLAZO_INSUMO);
        data.put(Common.ID_REGISTRO_MAYUS_ESPACIO.toString(), insumo.getIdRegistro());
        data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO.toString(), insumo.getIdRegistro());
        data.put(Common.NUMERO_DIAS_ESPACIO.toString(), insumo.getDescripcionPlazoRestante());
        try {
            getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISOS_INSUMO_GENERICO, destinatarios);
        } catch (DocumentoException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e);
        } catch (Exception e) {
            logger.error("Error al enviar el correo [{}]", e);
        }
    }

    private void inicializarInsumoConPlazos(FecetInsumo insumo, TiempoDTO tiempoPlazo, TiempoDTO tiempoPlazo1) {
        if (insumo.getFechaInicioPlazo() != null) {
            Date fechaFinPlazo = getTiemposService().sumarTiempo(tiempoPlazo, insumo.getFechaInicioPlazo());
            List<FecetSuspensionInsumo> suspensiones = fecetSuspensionInsumoService.obtenerSuspensionByIdInsumo(insumo.getIdInsumo());
            boolean existeSuspension = existeSuspensionActiva(suspensiones);
            fechaFinPlazo = obtenerFechaFinSuspension(fechaFinPlazo, suspensiones);
            insumo.setPlazoRestante(obtenerDiasRestantes(fechaFinPlazo, new Date(), tiempoPlazo));
            insumo.setDescripcionPlazoRestante(getPlazosHelper().convertirDiasRestantesTexto(insumo.getPlazoRestante(), tiempoPlazo));
            insumo.setDiasDespuesDePlazo((getTiemposHelper().calcularDiferenciaDias(fechaFinPlazo, new Date())));
            insumo.setFechaFinPlazo(fechaFinPlazo);
            if (existeSuspension) {
                insumo.setSemaforo(Constantes.ENTERO_ONCE);
            } else {
                insumo.setSemaforo(calcularSemaforoInsumo(fechaFinPlazo, insumo));
            }
            insumo.setEsTransferible(verificarDisponibilidadTransferencia(insumo, tiempoPlazo, tiempoPlazo1));
            if (insumo.getPlazoRestante() == 0) {
                insumo.setDiasDespuesDePlazo(
                        (getTiemposHelper().calcularDiferenciaDias(fechaFinPlazo, Calendar.getInstance().getTime())));
            }
        } else {
            insumo.setSemaforo(DIEZ);
            insumo.setEsTransferible(true);
            if (existePlazo(tiempoPlazo)) {
                insumo.setDiasSinAtencion(getTiemposService().obtenerDiasRestantesHabilesSinUltimoDia(insumo.getFechaCreacion(), Calendar.getInstance().getTime()));
                insumo.setDiasDespuesDePlazoDescripcion(getPlazosHelper().convertirDiasRestantesTextoSinVencimiento(insumo.getDiasSinAtencion(), tiempoPlazo) + " sin atender");
            } else {
                insumo.setDiasSinAtencion(0);
                insumo.setDiasDespuesDePlazoDescripcion("");
            }
            insumo.setPlazoRestante(-1);
        }
    }

    private Date obtenerFechaFinSuspension(Date fechaFinPlazo, List<FecetSuspensionInsumo> suspensiones) {
        int diasSuspencion = obtenerDiasSuspension(suspensiones);
        Date fechaFinPlazoResult = fechaFinPlazo;
        if (diasSuspencion > 0) {
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(fechaFinPlazo);
            getTiemposHelper().reseteaHoraFecha(fecha);
            fecha.add(Calendar.DAY_OF_YEAR, 1);
            fechaFinPlazoResult = getTiemposService().obtenerDiasHabiles(fecha.getTime(), diasSuspencion);
        }
        return fechaFinPlazoResult;
    }

    private boolean existeSuspensionActiva(List<FecetSuspensionInsumo> suspensiones) {
        boolean existeSuspension = false;
        for (FecetSuspensionInsumo suspension : suspensiones) {
            if (suspension.getFechaFinSuspension() == null) {
                existeSuspension = true;
                break;
            }
        }
        return existeSuspension;
    }

    private int obtenerDiasSuspension(List<FecetSuspensionInsumo> suspensiones) {
        Calendar fechaFinSuspension = null;
        Calendar fechaInicioSuspension = Calendar.getInstance();
        Calendar fechaSuspensionSiguiente = Calendar.getInstance();
        int diasSuspencion = 0;
        for (FecetSuspensionInsumo suspension : suspensiones) {
            if (getTiemposHelper().esIgualFechaHoy(suspension.getFechaInicioSuspension())) {
                continue;
            } else if (suspension.getFechaFinSuspension() == null) {
                suspension.setFechaFinSuspension(getTiemposService().restarDiasHabiles(new Date(), DIAS_RESTAR));
            } else if (getTiemposHelper().esIgualFechaHoy(suspension.getFechaFinSuspension())) {
                suspension.setFechaFinSuspension(getTiemposService().restarDiasHabiles(suspension.getFechaFinSuspension(), DIAS_RESTAR));
            }

            if (fechaFinSuspension == null) {
                fechaFinSuspension = Calendar.getInstance();
                fechaFinSuspension.setTime(suspension.getFechaFinSuspension());
                getTiemposHelper().reseteaHoraFecha(fechaFinSuspension);
                diasSuspencion += getTiemposService().obtenerDiasRestantesHabiles(suspension.getFechaInicioSuspension(),
                        suspension.getFechaFinSuspension());
            } else {
                fechaInicioSuspension.setTime(suspension.getFechaInicioSuspension());
                fechaSuspensionSiguiente.setTime(suspension.getFechaFinSuspension());
                getTiemposHelper().reseteaHoraFecha(fechaInicioSuspension);
                getTiemposHelper().reseteaHoraFecha(fechaSuspensionSiguiente);
                while (fechaInicioSuspension.getTime().compareTo(fechaFinSuspension.getTime()) <= 0) {
                    fechaInicioSuspension.setTime(getTiemposService().obtenerDiasHabiles(fechaInicioSuspension.getTime(), 2));
                }
                if (fechaInicioSuspension.getTime().compareTo(fechaSuspensionSiguiente.getTime()) <= 0) {
                    diasSuspencion += getTiemposService().obtenerDiasRestantesHabiles(fechaInicioSuspension.getTime(), fechaSuspensionSiguiente.getTime());
                }
                fechaFinSuspension.setTime(fechaSuspensionSiguiente.getTime());
            }
        }
        return diasSuspencion;
    }

    private int calcularSemaforoInsumo(Date fechaDiasRestantes, FecetInsumo insumo) {
        int color = 0;
        Date fechaRechazo = fecetInsumoDao.obtenerFechaRechazoInsumo(insumo.getIdInsumo());
        Date fechaAprobacion = fecetInsumoDao.obtenerFechaAprobacionInsumo(insumo.getIdInsumo());
        if (insumo.getIdEstatus().intValue() == TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO.getId()) {
            if (fechaRechazo != null
                    && getTiemposHelper().comparaFechasSinTiempo(insumo.getFechaFinPlazo(), fechaRechazo) < 0) {
                insumo.setDiasDespuesDePlazo((getTiemposHelper().calcularDiferenciaDias(fechaDiasRestantes, fechaRechazo)));
                color = NUEVE;
            } else {
                color = CINCO;
            }
        } else if (insumo.getIdEstatus().intValue() == TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO.getId()) {
            if (fechaAprobacion != null
                    && getTiemposHelper().comparaFechasSinTiempo(insumo.getFechaFinPlazo(), fechaAprobacion) < 0) {
                insumo.setDiasDespuesDePlazo((getTiemposHelper().calcularDiferenciaDias(fechaDiasRestantes, fechaAprobacion)));
                color = SIETE;
            } else {
                color = SEIS;
            }
        } else if (insumo.getDiasDespuesDePlazo() > 0) {
            color = OCHO;
        } else {
            color = obtenerColorSemaforo(fechaDiasRestantes, BigDecimal.valueOf(TipoMetodoEnum.INS.getId()));
        }
        return color;
    }

    private void envioCorreoInsumoCambioSemaforo(FecetInsumo insumo, BigDecimal tipoCorreo) {
        Map<String, String> datos = new HashMap<String, String>();
        Set<String> destinatarios = obtenerCorreosACIACE(insumo.getIdArace());
        destinatarios.addAll(obtenerCorreoArace(insumo));
        datos.put("Id Registro", insumo.getIdRegistro());
        datos.put(Common.NUMERO_DIAS_ESPACIO.toString(), tipoCorreo.equals(TIPO_CORREO_ENPLAZO) ? insumo.getDescripcionPlazoRestante()
                : insumo.getDiasDespuesDePlazoDescripcion());
        datos.put("leyenda", obtenerLeyenda(obtenerLeyendaCorreo(tipoCorreo), datos));
        datos.put(Common.SUBJECT.toString(), "Aviso de cambio de sem\u00E1foro de insumo");
        enviarNotificacionCorreos(datos, ReportType.AVISO_INSUMO_CAMBIO_SEMAFORO, destinatarios);
    }

    private String obtenerLeyenda(String content, Map<String, String> data) {
        Iterator<Entry<String, String>> iterator = data.entrySet().iterator();
        String contenido = content;
        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            contenido = contenido.replaceAll("<%" + pair.getKey().toString() + "%>", pair.getValue().toString());
        }
        return contenido;
    }

    private Integer obtenerDiasRestantes(Date fecha, Date fechaHoy, TiempoDTO tiempoPlazo) {
        return regresaDiasRestantes(fecha, fechaHoy, tiempoPlazo);
    }

    private boolean verificarDisponibilidadTransferencia(FecetInsumo insumo, TiempoDTO tiempoPlazoAtencion,
            TiempoDTO tiempoPlazoReasignacion) {
        boolean resultado = false;
        if (tiempoPlazoReasignacion == null || tiempoPlazoReasignacion.getTiempo() == 0) {
            resultado = true;
        } else {
            resultado = tiempoPlazoAtencion.getTiempo() - insumo.getPlazoRestante() < tiempoPlazoReasignacion.getTiempo();
        }
        return resultado;
    }

    private boolean existePlazo(TiempoDTO tiempoPlazo) {
        return tiempoPlazo != null && tiempoPlazo.getTiempo() > 0;
    }
}
