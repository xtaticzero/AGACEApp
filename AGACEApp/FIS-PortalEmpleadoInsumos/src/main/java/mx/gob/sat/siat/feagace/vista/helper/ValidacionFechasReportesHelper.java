package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;

import org.springframework.stereotype.Component;


@Component
public class ValidacionFechasReportesHelper implements Serializable{

    @SuppressWarnings("compatibility:-5478362326744067077")
    private static final long serialVersionUID = 1L;
    
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");

    public Date sumarFecha(Date fch, int dias){
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }
    
    public Date sumarMeses(Date fch, int meses){
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.MONTH, meses);
        return new Date(cal.getTimeInMillis());
    }
    
    public Date crearFechaFinalbyInicial(Date fch, int anios){
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.YEAR, anios);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new Date(cal.getTimeInMillis());
    }
    
    public Date crearFecha(Integer mes,Integer anio, boolean esFechaFin){
        Calendar fecha= Calendar.getInstance();
        if(esFechaFin){
            fecha.set(Calendar.MONTH,mes - ConstantesReportes.N_1);
            fecha.set(Calendar.YEAR,anio);
            fecha.set(Calendar.DAY_OF_MONTH,fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
        }else{
            fecha.set(Calendar.MONTH,mes - ConstantesReportes.N_1);
            fecha.set(Calendar.YEAR,anio);
            fecha.set(Calendar.DAY_OF_MONTH,ConstantesReportes.N_1);
        }
        return fecha.getTime();
    }
    
    public Date validarFecha(Date periodo, Date meses){
        Date nuevaFecha=null;
        if(periodo!=null && meses==null){
            nuevaFecha=periodo;
        }
        if(periodo==null && meses!=null){
            nuevaFecha=meses;
        }
        return nuevaFecha;
    }

    public void setFormatoFecha(SimpleDateFormat formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }
}
