package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.InformeComiteDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.helper.InformeComiteRechazoHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.InformeComiteService;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("informeComiteService")
public class InformeComiteServiceImpl extends BaseBusinessServices implements InformeComiteService {


    @Autowired
    private InformeComiteDao informeComiteDao;
    @Autowired
    private InformeComiteRechazoHelper helper;

    public List<ConsultaInformeComiteRechazoPropuesta> buscarInformeComite(String rfc, String idEntidad,
                                                                           String idActividadPreponderante,
                                                                           FececEmpleado empleado, Date fechaInicio, 
                                                                           Date fechaFin, String idRegistro) {
        List<ConsultaInformeComiteRechazoPropuesta> consultaInformeComitePropuesta;

        StringBuilder condicionAcppce = new StringBuilder();

        if (!empleado.getFecetDetalleEmpleado().getIdCentral().equals((Constantes.ACPPCE))) {
            condicionAcppce.append(" AND FECET_PROPUESTA.ID_ARACE = ");
            condicionAcppce.append("'");
            condicionAcppce.append(empleado.getFecetDetalleEmpleado().getIdCentral());
            condicionAcppce.append("' \n");
            condicionAcppce.append(" AND NOT (FECET_PROPUESTA.RFC_CREACION IN (");
            condicionAcppce.append(" SELECT RFC FROM FECEC_EMPLEADO ");            
            condicionAcppce.append(" INNER JOIN FECET_DETALLE_EMPLEADO ON FECEC_EMPLEADO.ID_EMPLEADO = FECET_DETALLE_EMPLEADO.ID_EMPLEADO");
            condicionAcppce.append(" WHERE FECET_DETALLE_EMPLEADO.ID_TIPO_EMPLEADO = ");
            condicionAcppce.append(Constantes.USUARIO_PROGRAMADOR); 
            condicionAcppce.append(" AND FECET_DETALLE_EMPLEADO.ID_CENTRAL = ");
            condicionAcppce.append(Constantes.ACPPCE).append(")");
            condicionAcppce.append(" AND FECET_PROPUESTA.ID_ESTATUS = ");
            condicionAcppce.append(Constantes.ESTATUS_REGISTRADA).append(") ");
        } else {
            condicionAcppce.append(" AND (FECET_PROPUESTA.ID_ARACE IN (");
            condicionAcppce.append(Constantes.ACAOCE);
            condicionAcppce.append(", ");
            condicionAcppce.append(Constantes.ACOECE);
            condicionAcppce.append(") ");
            condicionAcppce.append(" OR FECET_PROPUESTA.RFC_CREACION = '");
            condicionAcppce.append(empleado.getRfc());
            condicionAcppce.append("' ");
            condicionAcppce.append(")\n");
        }
        consultaInformeComitePropuesta =
                informeComiteDao.buscarInformeComite(rfc, idEntidad, idActividadPreponderante, condicionAcppce.toString(), fechaInicio, fechaFin, idRegistro);

        if (consultaInformeComitePropuesta != null && !consultaInformeComitePropuesta.isEmpty()) {
            obtenerPropuestasSinRepetir(consultaInformeComitePropuesta);
        }
        
        return consultaInformeComitePropuesta;

    }

    private List<ConsultaInformeComiteRechazoPropuesta> obtenerPropuestasSinRepetir(List<ConsultaInformeComiteRechazoPropuesta> listaPropuestas) {
        List<FecetDocExpediente> listaExpediente = new ArrayList<FecetDocExpediente>();

        for (int i = 0; i < listaPropuestas.size() ; i++) {
            listaExpediente = new ArrayList<FecetDocExpediente>();
            listaExpediente.add(listaPropuestas.get(i).getExpediente());
            listaPropuestas.get(i).setListaExpediente(listaExpediente);
            for (int j = i + 1;  j < listaPropuestas.size() ; j++) {
                if (listaPropuestas.get(i).getIdRegistro().equals(listaPropuestas.get(j).getIdRegistro())) {
                    if (listaPropuestas.get(i).getListaExpediente() == null) {
                        listaExpediente = new ArrayList<FecetDocExpediente>();
                    } else {
                        listaExpediente = listaPropuestas.get(i).getListaExpediente();
                    }
                    listaExpediente.add(listaPropuestas.get(j).getExpediente());
                    listaPropuestas.remove(j);
                    j = j - Constantes.ENTERO_UNO;
                }
            }
        }
        return listaPropuestas;
    }

    public HSSFWorkbook exportarInformeComitePropuesta(List<ConsultaInformeComiteRechazoPropuesta> listaInformeComite) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");
        boolean llamarExportarInforme = false;
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        int count = 0;
        count = helper.insertaCabecera(sheet, count, style, helper.crearCabeceraExcelInformeComite());
        helper.insertarListaExcel(listaInformeComite, count, sheet, llamarExportarInforme);
        return workbook;
    }
}
