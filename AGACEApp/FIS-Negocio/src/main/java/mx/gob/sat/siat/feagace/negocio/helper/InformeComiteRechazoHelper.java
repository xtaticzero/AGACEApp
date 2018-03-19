package mx.gob.sat.siat.feagace.negocio.helper;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.springframework.stereotype.Component;

@Component
public class InformeComiteRechazoHelper extends BaseHelper{
    private static final long serialVersionUID = -625348542428194647L;
    
    private List<String> cabeceraExcel = new ArrayList<String>();


    public List<BigDecimal> listaIdAraceRegional() {
        List<BigDecimal> listaAdaceRegional = new ArrayList<BigDecimal>();
        listaAdaceRegional.add(Constantes.ARACE_PACIFICO_NORTE);
        listaAdaceRegional.add(Constantes.ARACE_NORTE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_NORESTE);
        listaAdaceRegional.add(Constantes.ARACE_OCCIDENTE);
        listaAdaceRegional.add(Constantes.ARACE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_SUR);    
       return listaAdaceRegional;
    }

    public List<String> crearCabeceraExcelRechazos() {

        cabeceraExcel = new ArrayList<String>();
        cabeceraExcel.add("ID Registro");
        cabeceraExcel.add("RFC");
        cabeceraExcel.add("Nombre/Raz\u00f3n Social");
        cabeceraExcel.add("Estatus");
        cabeceraExcel.add("R\u00e9gimen");
        cabeceraExcel.add("Subprograma");
        cabeceraExcel.add("Entidad");
        cabeceraExcel.add("Tipo de Contribuyente");
        cabeceraExcel.add("Actividad Preponderante");
        cabeceraExcel.add("Fecha de Informe a Comit\u00e9");
        cabeceraExcel.add("Programador/Subadministrador");
        cabeceraExcel.add("Causa/Motivo");
        return cabeceraExcel;
    }

    public List<String> crearCabeceraExcelInformeComite() {
        cabeceraExcel = crearCabeceraExcelRechazos();
        cabeceraExcel.remove("Estatus");
        cabeceraExcel.remove("Causa/Motivo");
        cabeceraExcel.add("Expediente Electronico");
        return cabeceraExcel;
    }

    
    public int insertaCabecera(HSSFSheet sheet, final int count, HSSFCellStyle style, List<String> cabeceraExcel) {
        Row fila = sheet.createRow(count);
        Cell celda;
        int numCelda = Constantes.ENTERO_CERO;
        for (String cabecera : cabeceraExcel) {
            celda = fila.createCell(numCelda);
            celda.setCellValue(cabecera);
            celda.setCellStyle(style);
            numCelda++;
        }
        return count + Constantes.ENTERO_UNO;
    }

    public void insertarListaExcel(List<ConsultaInformeComiteRechazoPropuesta> listaInformeComiteConRechazosPropuesta,
                                   int conteo, HSSFSheet sheet, boolean tipoClase) {
        
        int conteoInterno = conteo;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Row fila = sheet.createRow(conteoInterno);
        Cell celda;
        List<String> registro = new ArrayList<String>();


        for (ConsultaInformeComiteRechazoPropuesta informeComiteConRechazo : listaInformeComiteConRechazosPropuesta) {
            registro.clear();
            registro.add(informeComiteConRechazo.getIdRegistro());
            registro.add(informeComiteConRechazo.getRfc());
            registro.add(informeComiteConRechazo.getNombre());

            if (tipoClase) {
                registro.add(informeComiteConRechazo.getEstatus());
            }
            registro.add(informeComiteConRechazo.getRegimen());
            registro.add(informeComiteConRechazo.getSubprograma());
            registro.add(informeComiteConRechazo.getEntidad());
            registro.add(informeComiteConRechazo.getTipoContribuyente());
            registro.add(informeComiteConRechazo.getActividadPreponderante());
            registro.add(informeComiteConRechazo.getFechaInforme() != null ?
                         dateFormat.format(informeComiteConRechazo.getFechaInforme()) : "");
            registro.add(informeComiteConRechazo.getRfcCreacion());
            if (tipoClase) {
                registro.add(informeComiteConRechazo.getCausaMotivo());
            } else {
                
                StringBuilder documentos = new StringBuilder();

                
                for(FecetDocExpediente listaExpediente : informeComiteConRechazo.getListaExpediente()){
                    documentos.append(listaExpediente.getNombre());
                    documentos.append(" | ");
                    
                }
                                    
                  registro.add(documentos.toString());              
            }

            int numCelda = Constantes.ENTERO_CERO;
            for (String campo : registro) {
                celda = fila.createCell(numCelda);
                celda.setCellValue(campo);
                numCelda++;
            }

            conteoInterno = conteoInterno + Constantes.ENTERO_UNO;
            fila = sheet.createRow(conteoInterno);
        }
    }
}
