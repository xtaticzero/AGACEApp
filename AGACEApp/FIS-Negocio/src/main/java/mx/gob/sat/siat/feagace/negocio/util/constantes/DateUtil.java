package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;
import mx.gob.sat.siat.feagace.negocio.common.FestivosService;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;

public class DateUtil {

    private FestivosService dateService;

    public static final int HORAS = 24;
    
    private static final int MINUTOS = 60;
    private static final int SEGUNDOS = 60;
    private static final int MILISEGUNDOS = 1000;

    private static final int CIEN = 100;
    private static final int CUATROCIENTOS = 400;
    private static final int CUATRO = 4;

    /**
     *
     * @param date
     * @return
     */
    public static boolean esFinDeSemana(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return esFinDeSemana(calendar);
    }

    /**
     *
     * @param calendar
     * @return
     */
    public static boolean esFinDeSemana(Calendar calendar) {
        boolean esfindeSemana = false;
        int diaDeLaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaDeLaSemana == Calendar.SATURDAY || diaDeLaSemana == Calendar.SUNDAY) {
            esfindeSemana = true;
        }
        return esfindeSemana;
    }

    public static Date enesimoDiaHabil(Date fechaInicial, int enesimoDia, final List<FececFeriados> diasFestivos) {

        int diasHabiles = 0;
        Festivos festivos = new Festivos(diasFestivos);
        Calendar fechaEnesimoDia = Calendar.getInstance();
        fechaEnesimoDia.setTime(fechaInicial);

        while (diasHabiles < enesimoDia) {
            fechaEnesimoDia.add(Calendar.DAY_OF_MONTH, 1);
            if (!DateUtil.esFinDeSemana(fechaEnesimoDia) && !festivos.isFestivo(fechaEnesimoDia.getTime())) {
                diasHabiles++;
            }

        }

        return fechaEnesimoDia.getTime();

    }

    public static boolean periodoMaximoAlcanzado(Date fechaInicial, int enesimoDia,
            final List<FececFeriados> diasFestivos) {

        boolean vencido = false;

        Date fechaEnesimoDia = enesimoDiaHabil(fechaInicial, enesimoDia, diasFestivos);

        int diasEnEspera = diasDeDiferencia(fechaEnesimoDia, getSystemDate());
        if (diasEnEspera < 0) {
            vencido = true;
        }

        return vencido;
    }

    /**
     *
     * @return
     */
    public static java.util.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diasDeDiferencia(java.util.Date date1, java.util.Date date2) {
        return diferenciaEnFechas(date1, date2, Calendar.DAY_OF_YEAR);
    }

    /**
     *
     * @param date1
     * @param date2
     * @param field
     * @return
     */
    public static int diferenciaEnFechas(Date date1, Date date2, int field) {
        int difference;
        Date fechaPosterior;
        Date fechaAnterior;

        fechaPosterior = date1;
        fechaAnterior = date2;

        Calendar fechaInicial = Calendar.getInstance();
        Calendar fechaUltima = Calendar.getInstance();

        fechaInicial.setTime(fechaAnterior);
        fechaUltima.setTime(fechaPosterior);

        difference = fechaUltima.get(field) - fechaInicial.get(field);

        return difference;
    }

    public void setDateService(FestivosService dateService) {
        this.dateService = dateService;
    }

    public FestivosService getDateService() {
        return dateService;
    }

    public static Date sumarFechasMeses(Date fch, int meses) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.MONTH, meses);
        return new Date(cal.getTimeInMillis());
    }

    public static Date sumarFechasDias(Date fch, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    public static long difDiasEnFechas(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        long diff = milis2 - milis1;
        long diffDays = diff / (HORAS * MINUTOS * SEGUNDOS * MILISEGUNDOS);

        return diffDays;
    }

    public static boolean verificarSiEsFeriado(Date dia, List<FececFeriados> festivos) throws DocumentoException {
        boolean esFeriado = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        for (FececFeriados feriado : festivos) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(feriado.getFecha());
            if (cal.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
                    && cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                    && cal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                esFeriado = true;
                break;
            }
        }

        return esFeriado;
    }

    public static boolean verificarAnioBisiesto(int anio) {

        boolean esBisiesto = false;

        if (anio % CUATRO == 0 && anio % CIEN != 0 || anio % CUATROCIENTOS == 0) {
            esBisiesto = true;
        }

        return esBisiesto;

    }

    public static boolean verificarEsFechaAnteriorOIgual(Date fechaAComparar, Date fechaBase) {
        Calendar dateToCompare;
        Calendar dateBase;

        dateToCompare = GregorianCalendar.getInstance();
        dateBase = GregorianCalendar.getInstance();

        dateBase.setTime(fechaBase);
        dateToCompare.setTime(fechaAComparar);

        if ((dateToCompare.get(Calendar.YEAR) < dateBase.get(Calendar.YEAR))) {
            return true;
        } else {

            boolean flgYear = (dateToCompare.get(Calendar.YEAR) == dateBase.get(Calendar.YEAR));
            boolean flgDay = (dateToCompare.get(Calendar.DAY_OF_YEAR) <= dateBase.get(Calendar.DAY_OF_YEAR));

            return flgYear && flgDay;
        }
    }

    public static String getFormattedDate(FormatosFechasEnum typeFormat, Date unformattedDate) {
        if (unformattedDate != null) {
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat(typeFormat.getFormato());
            return dateFormat.format(unformattedDate);
        } else {
            return "";
        }
    }

    public static Date getDateToFormattedString(FormatosFechasEnum typeFormat, String stringDate) {
        if (stringDate != null && !stringDate.isEmpty()) {
            try {
                DateFormat dateFormat;
                dateFormat = new SimpleDateFormat(typeFormat.getFormato());
                return dateFormat.parse(stringDate);
            } catch (ParseException ex) {
                return null;
            }
        } else {
            return null;
        }
    }
}
