package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.helper.InformeComiteRechazoHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.RechazoPropuestaService;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("rechazoPropuestaService")
public class RechazoPropuestaServiceImpl extends BaseBusinessServices implements RechazoPropuestaService {

    @Autowired
    private FececEstatusDao fececEstatusDao;
    @Autowired
    private FecetRechazoPropuestaDao fecetRechazoPropuestaDao;
    @Autowired
    private InformeComiteRechazoHelper helper;

    public List<FececEstatus> construyeComboEstatus() {
        return fececEstatusDao.findOnlyRechazosPropuestas();
    }

    public List<ConsultaInformeComiteRechazoPropuesta> buscarRechazosPropuesta(String rfc, String idEntidad,
                                                                               String idActividadPreponderante,
                                                                               BigDecimal idEstatus,
                                                                               FececEmpleado empleado) {
        StringBuilder condicion = new StringBuilder();

        if (helper.listaIdAraceRegional().contains(empleado.getFecetDetalleEmpleado().getIdCentral())) {
            condicion.append(" AND FECET_PROPUESTA.ID_ARACE = ");
            condicion.append("'");
            condicion.append(empleado.getFecetDetalleEmpleado().getIdCentral());
            condicion.append("' ");
        } else {
            condicion.append(" AND FECET_PROPUESTA.ID_ARACE IN (");
            condicion.append(Constantes.ACAOCE);
            condicion.append(", ");
            condicion.append(Constantes.ACOECE);
            condicion.append(") ");
        }
        return getFecetRechazoPropuestaDao().findAllRechazos(rfc, idEntidad, idActividadPreponderante, idEstatus,
                                                             condicion.toString());
    }

    public HSSFWorkbook exportarRechazosPropuesta(List<ConsultaInformeComiteRechazoPropuesta> listaRechazosPropuesta) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");
        boolean llamarExportarInforme = true;
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        int count = 0;
        count = helper.insertaCabecera(sheet, count, style, helper.crearCabeceraExcelRechazos());
        helper.insertarListaExcel(listaRechazosPropuesta, count, sheet, llamarExportarInforme);


        return workbook;
    }

    public void setFecetRechazoPropuestaDao(FecetRechazoPropuestaDao fecetRechazoPropuestaDao) {
        this.fecetRechazoPropuestaDao = fecetRechazoPropuestaDao;
    }

    public FecetRechazoPropuestaDao getFecetRechazoPropuestaDao() {
        return fecetRechazoPropuestaDao;
    }
}
