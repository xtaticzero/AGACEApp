package mx.gob.sat.siat.feagace.negocio.common.tiempos.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.tiempos.dao.TiemposDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClasificTiemposEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.service.TiemposService;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.client.EmpleadoUnitedServiceClient;
import mx.gob.sat.siat.ws.empleado.exception.EmpleadoServiceClientException;

@Service("tiemposService")
public class TiemposServiceImpl extends BaseBusinessServices implements TiemposService {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(TiemposServiceImpl.class.getName());

    private static final int DIASNOTIFICACIONNYV = 3;

    private Map<String, List<Date>> mapaDiasInhabiles = new HashMap<String, List<Date>>();

    private static final String YYYY = "yyyy";

    private static final double DIA_MILISEGUNDOS = 86400000d;

    @Autowired
    private transient TiemposDAO tiemposDAO;

    @Autowired
    private transient TiemposHelper tiemposHelper;

    @Autowired
    @Qualifier("empleadoUnitedServiceClient")
    private transient EmpleadoUnitedServiceClient empleadoUnitedClient;

    public TiemposServiceImpl() {
        super();
    }

    @Override
    public int obtenerTiempoNotificacionNyV() {
        LOG.debug("[obtenerTiempoNotificacionNyV]");
        // TODO Sustituir el valor real por el del servicio NyV y cnoncer su reglas para saber si son dias o meses
        return DIASNOTIFICACIONNYV;
    }

    @Override
    public TiempoDTO obtenerTiempoPlazo(final BigDecimal idMetodo, final TiemposClaveEnum cve) {
        return tiemposDAO.obtenerTiempoPlazo(idMetodo, cve.getClave(), cve.getClaveTipoTiempo());
    }

    @Override
    public TiempoDTO obtenerTiempoPlazoOficio(final BigDecimal idMetodo, final BigDecimal idTipoOficio, final TiemposClaveEnum cve) {
        LOG.debug("[obtenerTiempoPlazoOficio]");
        tiemposHelper.validarObtenerTiempoPlazoOficio(idMetodo, idTipoOficio, cve.getClave(), cve.getClaveTipoTiempo());
        return tiemposDAO.obtenerTiempoPlazoOficio(idMetodo, idTipoOficio, cve.getClave(), cve.getClaveTipoTiempo());
    }

    @Override
    public Date sumarTiempo(final TiempoDTO plazo, final Date fecha) {
        if (plazo != null && fecha!=null) {
            return sumarRestarAniosMesesDias(plazo.getIdTipoPlazo(), plazo.getTiempo(), fecha);
        }
        return fecha;
    }

    @Override
    public Date restarTiempo(final TiempoDTO plazo, final Date fecha) {
        final int tiempoResta = -plazo.getTiempo();
        Date fechaModificada = null;
        TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(plazo.getIdTipoPlazo().intValue());
        switch (tipoTiempo) {
        case DIA:
            fechaModificada = restarDiasHabiles(fecha, tiempoResta);
            break;
        default:
            fechaModificada = tiemposHelper.sumarRestarAniosMeses(fecha, tiempoResta, tipoTiempo);
        }
        return fechaModificada;
    }

    @Override
    public List<TiempoDTO> obtenerValoresDeSemaforo(int idMetodo) {
        return tiemposDAO.obtenerValoresDeSemaforo(idMetodo);
    }

    @Override
    public Date obtenerDiasHabiles(final Date fechaInicio, int dias) {
        String year = new SimpleDateFormat(YYYY).format(fechaInicio);
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(fechaInicio);
        tiemposHelper.reseteaHoraFecha(inicio);
        int diasHabiles = 0;
        List<Date> listaDiasHabiles = new ArrayList<Date>();
        List<Date> diasInhabiles = obtenerDiasInhabilesPorAnio(year);
        Date fechaAValidar = inicio.getTime();
        while (diasHabiles < dias) {
            String newYear = new SimpleDateFormat(YYYY).format(fechaAValidar);
            if (!newYear.equals(year)) {
                diasInhabiles = obtenerDiasInhabilesPorAnio(newYear);
                year = newYear;
            }
            if (!tiemposHelper.verificarExisteEnLista(diasInhabiles, fechaAValidar)) {
                diasHabiles++;
                listaDiasHabiles.add(fechaAValidar);
            }
            fechaAValidar = tiemposHelper.sumarRestarDiasFecha(fechaAValidar, 1);
        }
        return !listaDiasHabiles.isEmpty() ? listaDiasHabiles.get(listaDiasHabiles.size() - 1) : new Date();
    }

    public List<Date> obtenerDiasInhabilesPorAnio(String year) {
        List<Date> diasInhabiles = mapaDiasInhabiles.get(year);
        if (diasInhabiles == null) {
            Date fechaFin = regresaUltimoDia(year);
            Date fechaInicio = regresaPrimerDia(year);
            if (fechaInicio != null && fechaFin != null) {
                try {
                    diasInhabiles = new ArrayList<Date>();
                    List<FechasHabileseInhabiles> diasHabiles = empleadoUnitedClient.getCatalogoDiasHabilesInhabiles(fechaInicio, fechaFin);
                    for (FechasHabileseInhabiles dia : diasHabiles) {
                        diasInhabiles.add(dia.getFecha());
                    }
                    mapaDiasInhabiles.put(year, diasInhabiles);
                } catch (EmpleadoServiceClientException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
            diasInhabiles = mapaDiasInhabiles.get(year);
        }
        return diasInhabiles;
    }

    public Date regresaPrimerDia(String year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(year + "/01/01");
        } catch (ParseException e) {
            LOG.error("Error al convertir la fecha", e);
        }
        return null;
    }

    public Date regresaUltimoDia(String year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(year + "/12/31");
        } catch (ParseException e) {
            LOG.error("Error al convertir la fecha", e);
        }
        return null;
    }

    public Date sumarRestarAniosMesesDias(final BigDecimal idTipoPlazo, int cantidad, final Date fecha) {
        Date fechaModificada = null;
        TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(idTipoPlazo.intValue());
        switch (tipoTiempo) {
        case DIA:
            fechaModificada = obtenerDiasHabiles(fecha, cantidad);
            break;
        default:
            fechaModificada = tiemposHelper.sumarRestarAniosMeses(fecha, cantidad, tipoTiempo);
        }
        return fechaModificada;
    }

    public int obtenerDiasRestantesHabiles(Date fechaInicio, Date fechaFinParam) {
        Date fechaFin = fechaFinParam;
        int diasHabiles = 0;
        List<Date> listaDiasHabiles = new ArrayList<Date>();
        String year = new SimpleDateFormat(YYYY).format(fechaInicio);
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(fechaInicio);
        tiemposHelper.reseteaHoraFecha(inicio);
        Calendar fin = Calendar.getInstance();
        fin.setTime(fechaFin);
        tiemposHelper.reseteaHoraFecha(fin);
        List<Date> diasInhabiles = obtenerDiasInhabilesPorAnio(year);
        Date fechaAValidar = inicio.getTime();
        fechaFin = fin.getTime();
        while (fechaAValidar.before(fechaFin) || fechaAValidar.equals(fechaFin)) {
            String newYear = new SimpleDateFormat(YYYY).format(fechaAValidar);
            if (!newYear.equals(year)) {
                diasInhabiles = obtenerDiasInhabilesPorAnio(newYear);
                year = newYear;
            }
            if (!tiemposHelper.verificarExisteEnLista(diasInhabiles, fechaAValidar)) {
                diasHabiles++;
                listaDiasHabiles.add(fechaAValidar);
            }
            fechaAValidar = tiemposHelper.sumarRestarDiasFecha(fechaAValidar, 1);
        }
        return diasHabiles;
    }

    public int obtenerDiasRestantesHabilesSinUltimoDia(Date fechaInicio, Date fechaFinParam) {
        Date fechaFin = fechaFinParam;
        int diasHabiles = 0;
        List<Date> listaDiasHabiles = new ArrayList<Date>();
        String year = new SimpleDateFormat(YYYY).format(fechaInicio);
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(fechaInicio);
        tiemposHelper.reseteaHoraFecha(inicio);
        Calendar fin = Calendar.getInstance();
        fin.setTime(fechaFin);
        tiemposHelper.reseteaHoraFecha(fin);
        List<Date> diasInhabiles = obtenerDiasInhabilesPorAnio(year);
        Date fechaAValidar = inicio.getTime();
        fechaFin = fin.getTime();
        while (fechaAValidar.before(fechaFin)) {
            String newYear = new SimpleDateFormat(YYYY).format(fechaAValidar);
            if (!newYear.equals(year)) {
                diasInhabiles = obtenerDiasInhabilesPorAnio(newYear);
                year = newYear;
            }
            if (!tiemposHelper.verificarExisteEnLista(diasInhabiles, fechaAValidar)) {
                diasHabiles++;
                listaDiasHabiles.add(fechaAValidar);
            }
            fechaAValidar = tiemposHelper.sumarRestarDiasFecha(fechaAValidar, 1);
        }
        return diasHabiles;
    }

    public Date restarDiasHabiles(Date fechaInicio, int diasRestarParam) {
        int diasRestar = diasRestarParam;
        String year = new SimpleDateFormat(YYYY).format(fechaInicio);
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(fechaInicio);
        tiemposHelper.reseteaHoraFecha(inicio);
        int diasHabiles = 0;
        List<Date> listaDiasHabiles = new ArrayList<Date>();
        List<Date> diasInhabiles = obtenerDiasInhabilesPorAnio(year);
        Date fechaAValidar = inicio.getTime();
        diasRestar = diasRestar < 0 ? diasRestar * -1 : diasRestar;
        while (diasHabiles < diasRestar) {
            String newYear = new SimpleDateFormat(YYYY).format(fechaAValidar);
            if (!newYear.equals(year)) {
                diasInhabiles = obtenerDiasInhabilesPorAnio(newYear);
                year = newYear;
            }
            if (!tiemposHelper.verificarExisteEnLista(diasInhabiles, fechaAValidar)) {
                diasHabiles++;
                listaDiasHabiles.add(fechaAValidar);
            }
            fechaAValidar = tiemposHelper.sumarRestarDiasFecha(fechaAValidar, -1);
        }
        return !listaDiasHabiles.isEmpty() ? listaDiasHabiles.get(listaDiasHabiles.size() - 1) : new Date();
    }

    @Override
    public int obtenerDiasRestantesNaturales(Date fechaInicio, Date fechaFin) {
        Calendar calendarInicio = Calendar.getInstance();
        Calendar calendarFin = Calendar.getInstance();
        calendarInicio.setTime(fechaInicio);
        calendarFin.setTime(fechaFin);
        tiemposHelper.reseteaHoraFecha(calendarInicio);
        tiemposHelper.reseteaHoraFecha(calendarFin);

        double diferencia = calendarFin.getTimeInMillis() - calendarInicio.getTimeInMillis();
        double dias = Math.floor(diferencia / DIA_MILISEGUNDOS);

        if (dias > 0) {
            dias = dias + 1;
        }

        return (int) dias;
    }

    @Override
    public Date obtenerDiasNaturales(Date inicio, int dias) {
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(inicio);
        tiemposHelper.reseteaHoraFecha(calendarInicio);
        calendarInicio.add(Calendar.DAY_OF_YEAR, dias - 1);

        if (dias > 0) {
            return calendarInicio.getTime();
        }

        return inicio;
    }

}
