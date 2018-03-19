package mx.gob.sat.siat.feagace.negocio.common.tiempos.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClasificTiemposEnum;

/**
 * Clase Helper para las operaciones de Tiempos/Plazos
 *
 * @author eolf
 */
@Component
public class TiemposHelper implements Serializable {

    @SuppressWarnings("compatibility:-5650301926939897892")
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(TiemposHelper.class.getName());

    private static final String EXTRAPLAZO = "plazo";
    
    private static final String EXTRATIPOTIEMPO = "tipo tiempo";
    
    private static final String ERROR = "no puede ser null.";

    private static final String ESPACIO = "\n";

    
    /**
     * Validacion de la clave
     *
     * @author eolf
     * @param cve
     * @param extra
     * @throws IllegalArgumentException
     */
    @Deprecated
    public void validarCve(final String cve, final String extra) {
        LOG.debug("[validarCve]");
        if (StringUtils.isEmpty(cve)) {
            throw new IllegalArgumentException("La clave '" + extra + "' " + ERROR);
        }
    }

    /**
     * Validacion de id
     *
     * @author eolf
     * @param id
     * @throws IllegalArgumentException
     */
    @Deprecated
    public void validarId(final BigDecimal id) {
        LOG.debug("[validarIdMetodo]");
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser null.");
        }
    }

    /**
     * Validacion de la fecha
     *
     * @author eolf
     * @param fecha
     * @throws IllegalArgumentException
     */
    @Deprecated
    public void validarFecha(final Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha " + ERROR);
        }
    }

    /**
     * Metodo para validar el plazo
     *
     * @author eolf
     * @param plazo
     * @throws IllegalArgumentException
     */
    @Deprecated
    public void validarPlazo(final TiempoDTO plazo) {
        LOG.debug("[validarPlazo]");
        if (plazo == null) {
            throw new IllegalArgumentException("El plazo " + ERROR);
        } else if (plazo.getIdTipoPlazo().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El tipo del plazo no puede ser cero");
        }
    }

    /**
     * Metodo para validar la compulsa
     *
     * @author eolf
     * @param compulsa
     * @throws IllegalArgumentException
     */
    public void validarCompulsa(final FecetCompulsas compulsa) {
        LOG.debug("[validarPlazo]");
        if (compulsa == null) {
            throw new IllegalArgumentException("La compulsa " + ERROR);
            // TODO Verificar si el atributo FechaCreacion es el que se llena cuando se autoriza por el firmante
        } else if (compulsa.getFechaCreacion() == null) {
            throw new IllegalArgumentException("La fecha de la compulsa " + ERROR);
        }
    }

    /**
     * Metodo para validar una lista de objetos
     *
     * @author eolf
     * @param lista
     * @throws IllegalArgumentException
     */
    public void validarLista(final List<?> lista, final String extra) {
        LOG.debug("[validarLista]");
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("La lista de '" + extra + "' " + ERROR);
        }
    }

    /**
     * Validacion del metodo ObtenerTiempoPlazoOficio
     *
     * @author eolf
     * @param idMetodo
     * @param idTipoOficio
     * @param cvePlazo
     * @param cveTipoTiempo
     */
    public void validarObtenerTiempoPlazoOficio(final BigDecimal idMetodo, final BigDecimal idTipoOficio,
            final String cvePlazo, final String cveTipoTiempo) {
        LOG.debug("[validarObtenerTiempoPlazoOficio]");
        validarId(idMetodo);
        validarId(idTipoOficio);
        validarCve(cvePlazo, EXTRAPLAZO);
        validarCve(cveTipoTiempo, EXTRATIPOTIEMPO);
    }

    /**
     * Metodo para obtener la diferencia de dias entre dos fechas
     *
     * @author eolf
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public int calcularDiferenciaDias(Date fechaInicial, Date fechaFinal) {
        LOG.debug("[calcularDiferenciaDias]");
        validarFecha(fechaInicial);
        validarFecha(fechaFinal);
        final int n24 = 24;
        final int n60 = 60;
        final int n1000 = 1000;
        long diferenciaDias = 0;
        // TODO: Aqui verificar si para el calculo deben ser solo dias habiles, en dicho caso se debe invocar al
        // servicio de dias inhabiles
        Calendar cInicial = Calendar.getInstance();
        Calendar cFinal = Calendar.getInstance();
        cInicial.setTime(fechaInicial);
        cFinal.setTime(fechaFinal);
        long diff = cFinal.getTimeInMillis() - cInicial.getTimeInMillis();
        diferenciaDias = diff / (n24 * n60 * n60 * n1000);
        if (LOG.isDebugEnabled()) {
            LOG.debug(ESPACIO + String.format("FECHA INICIAL [%s] FECHA FINAL [%s] DIFERENCIA DIAS [%s]", fechaInicial,
                    fechaFinal, (int) diferenciaDias));
        }
        return (int) diferenciaDias;
    }

    public Date sumarDiasHabiles(final Date fechaSumar, int diasSumar) {
        LOG.debug("[sumarDiasFecha]");
        return sumarRestarDiasFecha(fechaSumar, diasSumar);
    }

    /**
     * Metodo para sumar/restar dias a una fecha
     *
     * @author eolf
     * @param fecha
     * @param dias
     * @return
     */
    public Date sumarRestarDiasFecha(final Date fecha, int dias) {
        // TODO: Debe ir al servicio de diaHabil ya que actualmente solo suma/resta dias naturales
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_YEAR, dias);
        return c.getTime();
    }

    public int comparaFechasSinTiempo(Date fecha1, Date fecha2) {
        return DateUtils.truncatedCompareTo(fecha1, fecha2, Calendar.DAY_OF_MONTH);
    }

    /**
     * Metodo para sumar/restar anios meses a una fecha
     *
     * @author eolf
     * @param fecha
     * @param cantidad
     * @param idTipoPlazo
     * @return
     */
    public Date sumarRestarAniosMeses(final Date fecha, final int cantidad, TiemposClasificTiemposEnum tipoPlazo) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        if (TiemposClasificTiemposEnum.ANIO == tipoPlazo) {
            c.add(Calendar.YEAR, cantidad);
        } else if (TiemposClasificTiemposEnum.MES == tipoPlazo) {
            c.add(Calendar.MONTH, cantidad);
        }
        return c.getTime();
    }

    public void reseteaHoraFecha(Calendar fecha) {
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }

    public boolean verificarExisteEnLista(List<Date> dias, Date diaABuscar) {
        for (Date dia : dias) {
            if (dia.equals(diaABuscar)) {
                return true;
            }
        }
        return false;
    }

    public boolean esIgualFechaHoy(Date fecha) {
        boolean resultado = false;
        if (fecha != null) {
            Calendar fechaParam = Calendar.getInstance();
            fechaParam.setTime(fecha);
            reseteaHoraFecha(fechaParam);
            Calendar fechaHoy = Calendar.getInstance();
            fechaHoy.setTime(new Date());
            reseteaHoraFecha(fechaHoy);
            resultado = fechaHoy.getTime().compareTo(fechaParam.getTime()) == 0;
        }
        return resultado;
    }
}
