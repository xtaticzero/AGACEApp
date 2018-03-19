package mx.gob.sat.siat.feagace.negocio.helper;

import java.util.Date;

import mx.gob.sat.siat.base.helper.BaseHelper;

import org.springframework.stereotype.Component;


@Component
public class FechasReportesHelper extends BaseHelper{

    @SuppressWarnings("compatibility:-806307056970653139")
    private static final long serialVersionUID = 1L;
    
    public Date crearFecha(Date periodo, Date meses){
        Date nuevaFecha=null;
        if(periodo!=null && meses==null){
            nuevaFecha=periodo;
        }
        if(periodo==null && meses!=null){
            nuevaFecha=meses;
        }
        return nuevaFecha;
    }
}
