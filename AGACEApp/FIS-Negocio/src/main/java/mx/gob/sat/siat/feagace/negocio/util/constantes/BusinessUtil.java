package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public final class BusinessUtil {

    private BusinessUtil() {
    }

    public static String getTipoPromocionOrdenPorMetodo(final BigDecimal idMetodo) {
        String textoTipoPromocion;

        switch (idMetodo.intValueExact()) {
            case 1:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            case 2:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_DOCUMENTACION_REQUERIDA;
                break;
            case Constantes.ENTERO_TRES:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            case Constantes.ENTERO_CUATRO:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            case Constantes.ENTERO_CINCO:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            default:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_DOCUMENTACION_REQUERIDA;
                break;
        }

        return textoTipoPromocion;
    }

    public static String getTipoPromocionPorTipoOficioMetodo(BigDecimal idTipoOficio) {
        String textoTipoPromocion = Constantes.TIPO_PROMOCION_DOCUMENTACION_REQUERIDA;
        TiposOficiosOrdenesEnum tipoOficio = TiposOficiosOrdenesEnum.parse(idTipoOficio.intValue());
        if (tipoOficio != null) {
            switch (tipoOficio) {
                case OBSERVACIONES_REVISION_ESCRITORIO:
                case SEGUNDA_UNICA_CARTA_INVITACION:
                case SEGUNDA_UNICA_CARTA_INVITACION_MASIVA:
                    textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                    break;
                default:
                    break;
            }
        }
        return textoTipoPromocion;
    }

    public static String obtenerImagenSemaforo(final Integer semaforo) {

        String textoImagenSemaforo;

        switch (semaforo) {
            case 1:
                textoImagenSemaforo = Constantes.SEMAFORO_VERDE;
                break;
            case 2:
                textoImagenSemaforo = Constantes.SEMAFORO_AMARILLO;
                break;
            case Constantes.ENTERO_TRES:
                textoImagenSemaforo = Constantes.SEMAFORO_NARANJA;
                break;
            case Constantes.ENTERO_CUATRO:
                textoImagenSemaforo = Constantes.SEMAFORO_ROJO;
                break;
            case Constantes.ENTERO_CINCO:
                textoImagenSemaforo = Constantes.SEMAFORO_CAFE;
                break;
            case Constantes.ENTERO_SEIS:
                textoImagenSemaforo = Constantes.SEMAFORO_AZUL;
                break;
            case Constantes.ENTERO_SIETE:
                textoImagenSemaforo = Constantes.SEMAFORO_GRIS;
                break;
            case Constantes.ENTERO_OCHO:
                textoImagenSemaforo = Constantes.SEMAFORO_NEGRO;
                break;
            case Constantes.ENTERO_NUEVE:
                textoImagenSemaforo = Constantes.SEMAFORO_BEIGE;
                break;
            case Constantes.ENTERO_DIEZ:
                textoImagenSemaforo = Constantes.SEMAFORO_BLANCO;
                break;
            case Constantes.ENTERO_ONCE:
                textoImagenSemaforo = Constantes.SEMAFORO_MORADO;
                break;
            default:
                textoImagenSemaforo = Constantes.SEMAFORO_VERDE;
                break;
        }

        return textoImagenSemaforo;
    }
    
    public static String obtenerDescripcionSemaforo(final Integer semaforo) {
        String textoSemaforo;

        switch (semaforo) {
            case 1:
                textoSemaforo = Constantes.SEMAFORO_VERDE_DESC;
                break;
            case 2:
                textoSemaforo = Constantes.SEMAFORO_AMARILLO_DESC;
                break;
            case Constantes.ENTERO_TRES:
                textoSemaforo = Constantes.SEMAFORO_NARANJA_DESC;
                break;
            case Constantes.ENTERO_CUATRO:
                textoSemaforo = Constantes.SEMAFORO_ROJO_DESC;
                break;
            case Constantes.ENTERO_CINCO:
                textoSemaforo = Constantes.SEMAFORO_CAFE_DESC;
                break;
            case Constantes.ENTERO_SEIS:
                textoSemaforo = Constantes.SEMAFORO_AZUL_DESC;
                break;
            case Constantes.ENTERO_SIETE:
                textoSemaforo = Constantes.SEMAFORO_GRIS_DESC;
                break;
            case Constantes.ENTERO_OCHO:
                textoSemaforo = Constantes.SEMAFORO_NEGRO_DESC;
                break;
            case Constantes.ENTERO_ONCE:
                textoSemaforo = Constantes.SEMAFORO_MORADO_INSUMO_DESC;
                break;
            case Constantes.ENTERO_NUEVE:
            case Constantes.ENTERO_DIEZ:
                textoSemaforo = Constantes.SEMAFORO_BLANCO_DESC;
                break;
            default:
                textoSemaforo = "";
                break;
        }

        return textoSemaforo;
    }
    
    
    public static String obtenerDescripcionSemaforoInsumo(final Integer semaforo) {
        String textoSemaforo;
        switch (semaforo) {
            case 1:
                textoSemaforo = Constantes.SEMAFORO_VERDE_INSUMO_DESC;
                break;
            case 2:
                textoSemaforo = Constantes.SEMAFORO_AMARILLO_INSUMO_DESC;
            break;
            case Constantes.ENTERO_TRES:
                textoSemaforo = Constantes.SEMAFORO_NARANJA_INSUMO_DESC;
                break;
            case Constantes.ENTERO_CUATRO:
                textoSemaforo = Constantes.SEMAFORO_ROJO_INSUMO_DESC;
                break;
            case Constantes.ENTERO_CINCO:
                textoSemaforo = Constantes.SEMAFORO_CAFE_INSUMO_DESC;
                break;
            case Constantes.ENTERO_SEIS:
                textoSemaforo = Constantes.SEMAFORO_AZUL_INSUMO_DESC;
                break;
            case Constantes.ENTERO_SIETE:
                textoSemaforo = Constantes.SEMAFORO_GRIS_INSUMO_DESC;
                break;
            case Constantes.ENTERO_OCHO:
                textoSemaforo = Constantes.SEMAFORO_NEGRO_INSUMO_DESC;
                break;
            case Constantes.ENTERO_NUEVE:
                textoSemaforo = Constantes.SEMAFORO_BEIGE_INSUMO_DESC;
                break;
            case Constantes.ENTERO_DIEZ:
                textoSemaforo = Constantes.SEMAFORO_BLANCO_INSUMO_DESC;
            break;
            case Constantes.ENTERO_ONCE:
                textoSemaforo = Constantes.SEMAFORO_MORADO_INSUMO_DESC;
                break;
            default:
                textoSemaforo = "";
                break;
        }
        
        return textoSemaforo;
    }

    @Deprecated
    public static String tiempoRestantePeriodoProcesado(final Integer totalDias) {
        Integer anios = totalDias / Constantes.ENTERO_DIAS_ANIO;
        Double meses = (totalDias - (anios * Constantes.ENTERO_DIAS_ANIO)) / Constantes.DIAS_MES;
        Double dias = totalDias - ((anios * Constantes.ENTERO_DIAS_ANIO) + (Math.round(meses) * Constantes.DIAS_MES));

        StringBuilder textoPlazoRestante = new StringBuilder();

        if (anios > 0) {
            textoPlazoRestante.append(anios).append(" ").append(Constantes.PLAZO_RESTANTE_YEAR);
        }

        if (meses.intValue() > 0) {
            textoPlazoRestante.append(" ").append(meses.intValue()).append(" ").append(Constantes.PLAZO_RESTANTE_MONTHS);
        }

        if (dias > 0) {
            textoPlazoRestante.append(" ").append(Math.round(dias)).append(" ").append(Constantes.PLAZO_RESTANTE_DAYS);
        }

        if (textoPlazoRestante.toString().equals("")) {
            textoPlazoRestante.append(Constantes.PLAZO_RESTANTE_VENCIDO);
        }

        return textoPlazoRestante.toString();
    }

    public static boolean validarFechaMenorOIgualAFechaActual(final Date fecha) {
        return (fecha.compareTo(new Date()) == 1) || fecha.before(new Date());
    }

    public static boolean validarFechaPeriodoREE(final Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DATE, -Constantes.PLAZO_MAXIMO_CARGA_DOCUMENTOS_CONTRIBUYENTE_REE);
        calendario.set(Calendar.HOUR_OF_DAY, DateUtil.HORAS);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        return (fecha.compareTo(calendario.getTime()) == 0);
    }
}
