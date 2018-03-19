package mx.gob.sat.siat.feagace.negocio.common.plazos.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.feagace.modelo.dao.common.FececLeyendaDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.plazos.PlazosDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.helper.PlazosHelper;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClasificTiemposEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.service.TiemposService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service.SuspensionService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

public class PlazosService extends ServiceAbstract {

    protected static final Logger LOG = Logger.getLogger(PlazosService.class);

    private static final long serialVersionUID = 156546546654356654L;

    public static final int DIAS_HABILES_DILIGENCIA = 3;
    public static final int CERO = 0;
    public static final int TRES = 3;
    public static final int CINCO = 5;
    public static final int SEIS = 6;
    public static final int SIETE = 7;
    public static final int OCHO = 8;
    public static final int NUEVE = 9;
    public static final int DIEZ = 10;
    public static final int ONCE = Constantes.ENTERO_ONCE;

    public static final int CIEN = 100;
    public static final int CIEN_UNO = 101;
    public static final int CIEN_DOS = 102;
    public static final int CIEN_TRES = 103;
    public static final int CIEN_CUATRO = 104;
    public static final int CIEN_CINCO = 105;
    public static final int CIEN_SEIS = 106;
    public static final int CIEN_SIETE = 107;
    public static final int CIEN_OCHO = 108;
    public static final int CIEN_SEIS_UNO = 161;
    public static final int CIEN_SEIS_DOS = 162;
    public static final int CIEN_SEIS_TRES = 163;

    public static final BigDecimal TIPO_CORREO_ENPLAZO = new BigDecimal(14);
    public static final BigDecimal TIPO_CORREO_VENCIMIENTO = new BigDecimal(15);
    public static final BigDecimal TIPO_CORREO_APROBADO = new BigDecimal(16);
    public static final BigDecimal TIPO_CORREO_RECHAZADO = new BigDecimal(17);

    public static final int DIAS_RESOLVER_NYV = -20;

    @Autowired
    private transient TiemposService tiemposService;

    @Autowired
    private transient SuspensionService suspensionService;

    @Autowired
    private transient PlazosHelper plazosHelper;

    @Autowired
    private transient TiemposHelper tiemposHelper;

    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    private transient FececLeyendaDao fececLeyendaDao;
    
    @Autowired
    private transient PlazosDAO plazosDAO;

    protected int regresaDiasRestantes(Date fechaLimite, TiempoDTO tiempo) {
        return regresaDiasRestantes(fechaLimite, new Date(), tiempo);
    }

    protected Set<String> obtenerCorreosACIACE(BigDecimal idArace) {
        try {
            Set<String> correos = new TreeSet<String>();
            BigDecimal idAciace = BigDecimal.valueOf(TipoAraceEnum.ACIACE.getId());
            correos.addAll(getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.CONSULTOR_INSUMOS, idArace, ClvSubModulosAgace.INSUMOS));
            correos.addAll(getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.ASIGNADOR_INSUMOS, idAciace, ClvSubModulosAgace.INSUMOS));
            correos.addAll(getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.VALIDADOR_INSUMOS, idAciace, ClvSubModulosAgace.INSUMOS));
            return correos;
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
            return new TreeSet<String>();
        }
    }

    protected List<String> obtenerCorreoArace(FecetInsumo insumo) {
        List<String> correos = new ArrayList<String>();
        try {
            correos.addAll(getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.CONSULTOR_INSUMOS, insumo.getIdArace(), ClvSubModulosAgace.INSUMOS));
            if (insumo.getRfcAdministrador() != null) {
                correos.add(getEmpleadoCompleto(insumo.getRfcAdministrador()).getCorreo());
            }
            if (insumo.getRfcSubadministrador() != null) {
                correos.add(getEmpleadoCompleto(insumo.getRfcSubadministrador()).getCorreo());
            }
        } catch (Exception e) {
            LOG.error("[{1}]", e);
        }
        return correos;
    }

    protected void enviarNotificacionCorreos(Map<String, String> data,
            ReportType reportType, Set<String> destinatarios) {
        try {
            Calendar date = new GregorianCalendar();
            data.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
            data.put(Common.DAY.toString(),
                    "" + date.get(Calendar.DAY_OF_MONTH));
            data.put(Common.MONTH.toString(),
                    NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
            data.put(Common.HOUR.toString(),
                    new SimpleDateFormat("HH:mm").format(date.getTime()));
            notificacionService.enviarNotificacion(data, reportType, destinatarios);
        } catch (Exception ex) {
            LOG.error("Error al enviar correo. ", ex);
        }
    }

    protected String obtenerLeyendaCorreo(BigDecimal idLeyenda) {
        return fececLeyendaDao.obtenerLeyendaById(idLeyenda).get(Constantes.CERO).getDescripcion();
    }

    protected int regresaDiasRestantes(Date fechaLimite, Date fechaCalcular, TiempoDTO tiempo) {
        int diasRestantes;
        Calendar cal = Calendar.getInstance();
        Calendar hoy = Calendar.getInstance();
        hoy.setTime(fechaCalcular);
        tiemposHelper.reseteaHoraFecha(hoy);
        cal.setTime(fechaLimite);
        tiemposHelper.reseteaHoraFecha(cal);
        TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempo.getIdTipoPlazo().intValue());
        if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
            diasRestantes = tiemposService.obtenerDiasRestantesHabiles(hoy.getTime(), cal.getTime());
        } else {
            long restaDias = cal.getTimeInMillis() - hoy.getTimeInMillis();
            diasRestantes = (int) TimeUnit.MILLISECONDS.toDays(restaDias);
        }

        if (diasRestantes <= 0) {
            diasRestantes = 0;
        }
        return diasRestantes;
    }

    protected int obtenerColorSemaforo(Date fechaFin, BigDecimal idMetodo) {

        List<TiempoDTO> listClaveSemaforo = tiemposService.obtenerValoresDeSemaforo(idMetodo.intValue());

        TiempoDTO plazoSemAmarillo = null;
        TiempoDTO plazoSemNaranja = null;
        TiempoDTO plazoSemRojo = null;

        for (TiempoDTO list : listClaveSemaforo) {
            if (list.getIdTiempo().intValue() == CIEN || list.getIdTiempo().intValue() == CIEN_TRES || list.getIdTiempo().intValue() == CIEN_SEIS
                    || list.getIdTiempo().intValue() == CIEN_SEIS_UNO) {
                plazoSemAmarillo = list;
            }
            if (list.getIdTiempo().intValue() == CIEN_UNO || list.getIdTiempo().intValue() == CIEN_CUATRO || list.getIdTiempo().intValue() == CIEN_SIETE
                    || list.getIdTiempo().intValue() == CIEN_SEIS_DOS) {
                plazoSemNaranja = plazosHelper.colocaValorSemaforo(list);
            }
            if (list.getIdTiempo().intValue() == CIEN_DOS || list.getIdTiempo().intValue() == CIEN_CINCO || list.getIdTiempo().intValue() == CIEN_OCHO
                    || list.getIdTiempo().intValue() == CIEN_SEIS_TRES) {
                plazoSemRojo = plazosHelper.colocaValorSemaforo(list);
            }
        }

        Date fecSemAmarillo = tiemposService.restarTiempo(plazoSemAmarillo, fechaFin);
        Date fecSemNaranja = tiemposService.restarTiempo(plazoSemNaranja, fechaFin);
        Date fecSemRojo = tiemposService.restarTiempo(plazoSemRojo, fechaFin);

        return plazosHelper.cambioDeSemaforo(fecSemAmarillo, fecSemNaranja, fecSemRojo, plazoSemAmarillo, plazoSemNaranja, plazoSemRojo);
    }

    protected boolean cambioSemaforo(Date fechaFin, List<TiempoDTO> listClaveSemaforo) {

        boolean cambioSemaforo = false;
        TiempoDTO plazoSemAmarillo = null;
        TiempoDTO plazoSemNaranja = null;
        TiempoDTO plazoSemRojo = null;
        Calendar hoy = Calendar.getInstance();

        for (TiempoDTO list : listClaveSemaforo) {
            if (list.getIdTiempo().intValue() == CIEN || list.getIdTiempo().intValue() == CIEN_TRES || list.getIdTiempo().intValue() == CIEN_SEIS
                    || list.getIdTiempo().intValue() == CIEN_SEIS_UNO) {
                plazoSemAmarillo = list;
            }
            if (list.getIdTiempo().intValue() == CIEN_UNO || list.getIdTiempo().intValue() == CIEN_CUATRO || list.getIdTiempo().intValue() == CIEN_SIETE
                    || list.getIdTiempo().intValue() == CIEN_SEIS_DOS) {
                plazoSemNaranja = plazosHelper.colocaValorSemaforo(list);
            }
            if (list.getIdTiempo().intValue() == CIEN_DOS || list.getIdTiempo().intValue() == CIEN_CINCO || list.getIdTiempo().intValue() == CIEN_OCHO
                    || list.getIdTiempo().intValue() == CIEN_SEIS_TRES) {
                plazoSemRojo = plazosHelper.colocaValorSemaforo(list);
            }
        }
        Date fecSemAmarillo = tiemposService.restarTiempo(plazoSemAmarillo, fechaFin);
        Date fecSemNaranja = tiemposService.restarTiempo(plazoSemNaranja, fechaFin);
        Date fecSemRojo = tiemposService.restarTiempo(plazoSemRojo, fechaFin);

        if (tiemposHelper.comparaFechasSinTiempo(hoy.getTime(), fecSemAmarillo) == 0) {
            cambioSemaforo = true;
        } else if (tiemposHelper.comparaFechasSinTiempo(hoy.getTime(), fecSemNaranja) == 0) {
            cambioSemaforo = true;
        } else if (tiemposHelper.comparaFechasSinTiempo(hoy.getTime(), fecSemRojo) == 0) {
            cambioSemaforo = true;
        } else if (tiemposHelper.comparaFechasSinTiempo(hoy.getTime(), fechaFin) == 0) {
            cambioSemaforo = true;
        }

        return cambioSemaforo;
    }

    public SuspensionService getSuspensionService() {
        return suspensionService;
    }

    public TiemposService getTiemposService() {
        return tiemposService;
    }

    public PlazosHelper getPlazosHelper() {
        return plazosHelper;
    }

    public TiemposHelper getTiemposHelper() {
        return tiemposHelper;
    }

    public PlazosDAO getPlazosDAO() {
        return plazosDAO;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }
}
