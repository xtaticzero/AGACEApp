package mx.gob.sat.siat.feagace.negocio.common.plazos.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClasificTiemposEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;

/**
 * Clase Helper para las operaciones de Plazos
 *
 * @author eolf
 */
@Component
public class PlazosHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int DAYS_OF_YEAR = 365;
    private static final int MESES_AL_ANIO = 12;

    private static final int UNO = 1;
    private static final int TRES = 3;
    private static final int CUATRO = 4;
    private static final int DOS = 2;

    private static final String SEMA = "SEMA";
    private static final String SEMN = "SEMN";
    private static final String SEMR = "SEMR";

    private static final String SEMINA = "SEMINA";
    private static final String SEMINN = "SEMINN";
    private static final String SEMINR = "SEMINR";

    @Autowired
    private TiemposHelper tiemposHelper;

    public String convertirDiasRestantesTexto(final Integer totalDias, TiempoDTO tiempoPlazo) {
        BigDecimal idTipoPlazo = tiempoPlazo.getIdTipoPlazo();
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        int diaMes = hoy.get(Calendar.DAY_OF_MONTH);
        Calendar maximoTiempoPlazo = Calendar.getInstance();
        maximoTiempoPlazo.add(Calendar.DAY_OF_YEAR, totalDias);
        int meses = 0;
        hoy.add(Calendar.MONTH, 1);
        if ((hoy.get(Calendar.DAY_OF_MONTH) != diaMes) && hoy.getActualMaximum(Calendar.DAY_OF_MONTH) >= diaMes) {
            hoy.set(Calendar.DAY_OF_MONTH, diaMes);
        }
        while (hoy.compareTo(maximoTiempoPlazo) < 0) {
            ++meses;
            hoy.add(Calendar.MONTH, 1);
            if ((hoy.get(Calendar.DAY_OF_MONTH) != diaMes) && hoy.getActualMaximum(Calendar.DAY_OF_MONTH) >= diaMes) {
                hoy.set(Calendar.DAY_OF_MONTH, diaMes);
            }
        }
        int anios = 0;
        int dias = 0;
        hoy.add(Calendar.MONTH, -1);
        if ((hoy.get(Calendar.DAY_OF_MONTH) != diaMes) && hoy.getActualMaximum(Calendar.DAY_OF_MONTH) >= diaMes) {
            hoy.set(Calendar.DAY_OF_MONTH, diaMes);
        }
        if (!((hoy.get(Calendar.YEAR) == maximoTiempoPlazo.get(Calendar.YEAR)) && (hoy.get(Calendar.MONTH) == maximoTiempoPlazo.get(Calendar.MONTH))
                && (hoy.get(Calendar.DAY_OF_MONTH) == maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH)))) {
            if (diaMes < maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH)) {
                dias = maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH) - diaMes;
            } else {
                int diaMesHoy = hoy.getActualMaximum(Calendar.DAY_OF_MONTH);
                dias = diaMesHoy - hoy.get(Calendar.DAY_OF_MONTH);
                dias = dias + maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH);
            }
        }
        StringBuilder textoPlazoRestante = new StringBuilder();

        if (idTipoPlazo.compareTo(BigDecimal.valueOf(TiemposClasificTiemposEnum.ANIO.getIdTipoPlazo())) == 0) {
            anios = meses / MESES_AL_ANIO;
            meses = meses % MESES_AL_ANIO;
            textoPlazoRestante = diasAAniosMesesDiasTxt(totalDias, anios, meses, dias, textoPlazoRestante);
        } else if (idTipoPlazo.compareTo(BigDecimal.valueOf(TiemposClasificTiemposEnum.MES.getIdTipoPlazo())) == 0) {
            textoPlazoRestante = diasAMesesDiasTxt(meses, dias, textoPlazoRestante);
        } else if (idTipoPlazo.compareTo(BigDecimal.valueOf(TiemposClasificTiemposEnum.DIA.getIdTipoPlazo())) == 0) {
            textoPlazoRestante = diasADiasTxt(totalDias, textoPlazoRestante);
        }
        if (textoPlazoRestante.toString().equals("") || totalDias == 0) {
            return Constantes.PLAZO_RESTANTE_VENCIDO;
        }
        return textoPlazoRestante.toString();
    }

    public String convertirDiasRestantesTextoSinVencimiento(final Integer totalDias, TiempoDTO tiempoPlazo) {
        BigDecimal idTipoPlazo = tiempoPlazo.getIdTipoPlazo();
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        int diaMes = hoy.get(Calendar.DAY_OF_MONTH);
        Calendar maximoTiempoPlazo = Calendar.getInstance();
        maximoTiempoPlazo.add(Calendar.DAY_OF_YEAR, totalDias);
        int meses = 0;
        hoy.add(Calendar.MONTH, 1);
        if ((hoy.get(Calendar.DAY_OF_MONTH) != diaMes) && hoy.getActualMaximum(Calendar.DAY_OF_MONTH) >= diaMes) {
            hoy.set(Calendar.DAY_OF_MONTH, diaMes);
        }
        while (hoy.compareTo(maximoTiempoPlazo) < 0) {
            ++meses;
            hoy.add(Calendar.MONTH, 1);
            if ((hoy.get(Calendar.DAY_OF_MONTH) != diaMes) && hoy.getActualMaximum(Calendar.DAY_OF_MONTH) >= diaMes) {
                hoy.set(Calendar.DAY_OF_MONTH, diaMes);
            }
        }
        int anios = 0;
        int dias = 0;
        hoy.add(Calendar.MONTH, -1);
        if ((hoy.get(Calendar.DAY_OF_MONTH) != diaMes) && hoy.getActualMaximum(Calendar.DAY_OF_MONTH) >= diaMes) {
            hoy.set(Calendar.DAY_OF_MONTH, diaMes);
        }
        if (!((hoy.get(Calendar.YEAR) == maximoTiempoPlazo.get(Calendar.YEAR)) && (hoy.get(Calendar.MONTH) == maximoTiempoPlazo.get(Calendar.MONTH))
                && (hoy.get(Calendar.DAY_OF_MONTH) == maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH)))) {
            if (diaMes < maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH)) {
                dias = maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH) - diaMes;
            } else {
                int diaMesHoy = hoy.getActualMaximum(Calendar.DAY_OF_MONTH);
                dias = diaMesHoy - hoy.get(Calendar.DAY_OF_MONTH);
                dias = dias + maximoTiempoPlazo.get(Calendar.DAY_OF_MONTH);
            }
        }
        StringBuilder textoPlazoRestante = new StringBuilder();

        if (idTipoPlazo.compareTo(BigDecimal.valueOf(TiemposClasificTiemposEnum.ANIO.getIdTipoPlazo())) == 0) {
            anios = meses / MESES_AL_ANIO;
            meses = meses % MESES_AL_ANIO;
            textoPlazoRestante = diasAAniosMesesDiasTxt(totalDias, anios, meses, dias, textoPlazoRestante);
        } else if (idTipoPlazo.compareTo(BigDecimal.valueOf(TiemposClasificTiemposEnum.MES.getIdTipoPlazo())) == 0) {
            textoPlazoRestante = diasAMesesDiasTxt(meses, dias, textoPlazoRestante);
        } else if (idTipoPlazo.compareTo(BigDecimal.valueOf(TiemposClasificTiemposEnum.DIA.getIdTipoPlazo())) == 0) {
            textoPlazoRestante = diasADiasTxt(totalDias, textoPlazoRestante);
        }
        if (textoPlazoRestante.toString().equals("") || totalDias == 0) {
            return Constantes.PLAZO_RESTANTE_VENCIDO_INSUMO;
        }
        return textoPlazoRestante.toString();
    }

    private StringBuilder diasAAniosMesesDiasTxt(Integer totalDias, int anios, int meses, int dias, StringBuilder textoPlazoRestante) {

        if ((totalDias % DAYS_OF_YEAR) == 0) {
            int aniosMod = totalDias / DAYS_OF_YEAR;
            textoPlazoRestante.append(aniosMod).append(" ").append(Constantes.PLAZO_RESTANTE_YEAR);
        } else {
            if (anios > 0) {
                textoPlazoRestante.append(anios).append(" ").append(Constantes.PLAZO_RESTANTE_YEAR);
            }
            if (meses > 0) {
                textoPlazoRestante.append(" ").append(meses).append(" ").append(Constantes.PLAZO_RESTANTE_MONTHS);
            }
            if (dias > 0) {
                textoPlazoRestante.append(" ").append(dias).append(" ").append(Constantes.PLAZO_RESTANTE_DAYS);
            }
        }
        return textoPlazoRestante;
    }

    private StringBuilder diasAMesesDiasTxt(int meses, int dias, StringBuilder textoPlazoRestante) {
        int sumaMeses = meses;
        if (sumaMeses > 0) {
            textoPlazoRestante.append(" ").append(sumaMeses).append(" ").append(Constantes.PLAZO_RESTANTE_MONTHS);
        }
        if (dias > 0) {
            textoPlazoRestante.append(" ").append(dias).append(" ").append(Constantes.PLAZO_RESTANTE_DAYS);
        }

        return textoPlazoRestante;
    }

    private StringBuilder diasADiasTxt(Integer totalDias, StringBuilder textoPlazoRestante) {
        if (totalDias > 0) {
            textoPlazoRestante.append(" ").append(totalDias).append(" ").append(Constantes.PLAZO_RESTANTE_DAYS);
        }
        return textoPlazoRestante;
    }

    public FecetOficio crearOficio(AgaceOrden orden, BigDecimal idTipoOficio) {
        FecetOficio oficio = new FecetOficio();
        oficio.setIdOrden(orden.getIdOrden());
        FecetTipoOficio tipoOficio = new FecetTipoOficio();
        tipoOficio.setIdTipoOficio(idTipoOficio);
        oficio.setFecetTipoOficio(tipoOficio);
        return oficio;
    }

    public int cambioDeSemaforo(Date semAmarillo, Date semNaranja, Date semRojo, TiempoDTO plazoSemAmarillo, TiempoDTO plazoSemNaranja,
            TiempoDTO plazoSemRojo) {

        Calendar fecActual = Calendar.getInstance();
        Date hoy = fecActual.getTime();

        if ((plazoSemAmarillo.getClave().equals(SEMA) || plazoSemAmarillo.getClave().equals(SEMINA)) && hoy.before(semNaranja) && hoy.after(semAmarillo)) {
            return DOS;
        }
        if ((plazoSemNaranja.getClave().equals(SEMN) || plazoSemNaranja.getClave().equals(SEMINN)) && hoy.before(semRojo) && hoy.after(semNaranja)) {
            return TRES;
        }
        if ((plazoSemRojo.getClave().equals(SEMR) || plazoSemRojo.getClave().equals(SEMINR)) && hoy.after(semRojo)) {
            return CUATRO;
        }

        return UNO;

    }

    public TiempoDTO colocaValorSemaforo(TiempoDTO valor) {
        TiempoDTO valorNuevo = new TiempoDTO();
        valorNuevo.setIdTiempo(valor.getIdTiempo());
        valorNuevo.setClave(valor.getClave());
        valorNuevo.setIdTipoTiempo(valor.getIdTipoTiempo());
        valorNuevo.setIdAgrupadorTiempo(valor.getIdAgrupadorTiempo());
        valorNuevo.setTiempo(valor.getTiempo());
        valorNuevo.setIdTipoPlazo(valor.getIdTipoPlazo());
        valorNuevo.setIdMetodo(valor.getIdMetodo());
        return valorNuevo;
    }

    public List<FecetSuspensionDTO> elimarFechasSuspensionRepetidas(List<FecetSuspensionDTO> suspensiones) {
        for (int i = 0; i < suspensiones.size(); i++) {
            for (int j = i + 1; j < suspensiones.size(); j++) {
                if (tiemposHelper.comparaFechasSinTiempo(suspensiones.get(i).getFechaInicioSuspension(), suspensiones.get(j).getFechaInicioSuspension()) >= 0
                        && tiemposHelper.comparaFechasSinTiempo(suspensiones.get(j).getFechaFinSuspension(),
                                suspensiones.get(i).getFechaFinSuspension()) >= 0) {
                    suspensiones.remove(suspensiones.get(i));
                    --i;
                    break;
                }
                if (tiemposHelper.comparaFechasSinTiempo(suspensiones.get(j).getFechaInicioSuspension(), suspensiones.get(i).getFechaInicioSuspension()) >= 0
                        && tiemposHelper.comparaFechasSinTiempo(suspensiones.get(i).getFechaFinSuspension(),
                                suspensiones.get(j).getFechaFinSuspension()) >= 0) {
                    suspensiones.remove(suspensiones.get(j));
                    --j;
                }
            }
        }
        restearFechaInicioSuspensiones(suspensiones);
        return suspensiones;
    }

    public Date obtenerUltimaSuspensionTerminada(List<FecetSuspensionDTO> suspensiones) {
        Date fechaFinSuspension = null;
        for (FecetSuspensionDTO suspension : suspensiones) {
            if (tiemposHelper.comparaFechasSinTiempo(new Date(), suspension.getFechaFinSuspension()) >= 0 && suspension.getIdObjeto() != null
                    && (fechaFinSuspension == null || tiemposHelper.comparaFechasSinTiempo(suspension.getFechaFinSuspension(), fechaFinSuspension) > 0)) {
                fechaFinSuspension = suspension.getFechaFinSuspension();
            }
        }
        return fechaFinSuspension;
    }

    public void eliminarTiempoSuspensionTerminada(List<FecetSuspensionDTO> suspensiones, Date fechaFinSuspension) {
        for (int i = 0; i < suspensiones.size(); i++) {
            if (tiemposHelper.comparaFechasSinTiempo(fechaFinSuspension, suspensiones.get(i).getFechaFinSuspension()) >= 0) {
                suspensiones.remove(i);
                i--;
            } else {
                if (tiemposHelper.comparaFechasSinTiempo(fechaFinSuspension, suspensiones.get(i).getFechaInicioSuspension()) > 0
                        && tiemposHelper.comparaFechasSinTiempo(fechaFinSuspension, suspensiones.get(i).getFechaFinSuspension()) <= 0) {
                    suspensiones.get(i).setFechaInicioSuspension(fechaFinSuspension);
                }
            }
        }
    }

    private List<FecetSuspensionDTO> restearFechaInicioSuspensiones(List<FecetSuspensionDTO> suspensiones) {
        for (int i = 1; i < suspensiones.size(); i++) {
            if (tiemposHelper.comparaFechasSinTiempo(suspensiones.get(i - 1).getFechaFinSuspension(), suspensiones.get(i).getFechaInicioSuspension()) > 0) {
                suspensiones.get(i).setFechaInicioSuspension(suspensiones.get(i - 1).getFechaFinSuspension());
            }
        }
        return suspensiones;
    }

    public Date sumaDiaNatural(Date fechaDiasRestantes, int dias) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaDiasRestantes);
        fecha.add(Calendar.DAY_OF_YEAR, dias);
        return fecha.getTime();
    }

}
