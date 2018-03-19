package mx.gob.sat.siat.feagace.negocio.helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class ConsultaPropuestasHelper extends BaseHelper {

    private static final long serialVersionUID = 1L;

    public List<BigDecimal> listaIdAraceConsultaPropuestas() {
        List<BigDecimal> listaAdaceInformeAcomite = new ArrayList<BigDecimal>();
        listaAdaceInformeAcomite.addAll(listaIdAraceRegional());
        listaAdaceInformeAcomite.add(Constantes.ACPPCE);

        return listaAdaceInformeAcomite;
    }

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

    public List<TipoFechasComiteEnum> obtenerListaFechasComiteSegunUsuario(EmpleadoDTO empleado) {
        List<TipoFechasComiteEnum> listaTipoFechasComite = new ArrayList<TipoFechasComiteEnum>();
        if (!empleado.getDetalleEmpleado().get(0).getCentral().getIdArace().equals(Constantes.ACPPCE.intValue())) {
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL);
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL);
        } else {
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_INFORME_COMITE);
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE);
        }
        return listaTipoFechasComite;
    }

    public List<String> crearCabeceraExcel() {
        List<String> cabeceraExcel = new ArrayList<String>();
        cabeceraExcel.add("ID Registro");
        cabeceraExcel.add("RFC del Contribuyente");
        cabeceraExcel.add("Nombre/Raz\u00f3n Social");
        cabeceraExcel.add("Estatus");
        cabeceraExcel.add("Entidad");
        cabeceraExcel.add("Tipo de Contribuyente");
        cabeceraExcel.add("Actividad Preponderante");
        cabeceraExcel.add("Fecha de Informe a Comit\u00e9");
        cabeceraExcel.add("Programador/Subadministrador");
        cabeceraExcel.add("Causa/Motivo");
        cabeceraExcel.add("R\u00e9gimen");
        cabeceraExcel.add("Subprograma");
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

    public void insertarListaExcel(List<ConsultaPropuestas> listaCosultaPropuestas, int conteo, HSSFSheet sheet) {

        int conteoInterno = conteo;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Row fila = sheet.createRow(conteoInterno);
        Cell celda;
        List<String> registro = new ArrayList<String>();

        for (ConsultaPropuestas propuesta : listaCosultaPropuestas) {
            registro.clear();
            registro.add(propuesta.getIdRegistro());
            registro.add(propuesta.getRfc());
            registro.add(propuesta.getNombreContribuyente());
            registro.add(propuesta.getEstatus());
            registro.add(propuesta.getEntidad());
            registro.add(propuesta.getTipoContribuyente());
            registro.add(propuesta.getActividadPreponderante());
            if (propuesta.getFechaInforme() != null || propuesta.getFechaPresentacion() != null) {
                registro.add(propuesta.getFechaInforme() != null ? dateFormat.format(propuesta.getFechaInforme())
                        : dateFormat.format(propuesta.getFechaPresentacion()));
            } else {
                registro.add("");
            }
            registro.add(propuesta.getRfcEmpleado());
            registro.add(propuesta.getCausaMotivo());
            registro.add(propuesta.getRegimen());
            registro.add(propuesta.getSubprograma());

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
