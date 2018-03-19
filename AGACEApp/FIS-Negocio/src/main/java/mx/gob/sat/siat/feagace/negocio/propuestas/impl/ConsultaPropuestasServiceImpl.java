package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstadosDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ConsultaPropuestasDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstados;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.ConsultaPropuestasHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.ConsultaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;

@Service("consultaPropuestasService")
public class ConsultaPropuestasServiceImpl extends PropuestasServiceAbstract implements ConsultaPropuestasService {

    /**
     *
     */
    private static final long serialVersionUID = 3983513274451469123L;
    @Autowired
    private transient ConsultaPropuestasDao consultaPropuestasDao;
    @Autowired
    private transient FececEstadosDao fececEstadosDao;
    @Autowired
    private ConsultaPropuestasHelper helper;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;

    @Override
    public List<AraceDTO> getFececUnidadesAdministrativas() {

        try {
            List<AraceDTO> unidadesAdmin;            
            unidadesAdmin = new ArrayList<AraceDTO>();
            List<AraceDTO> unidadAdminNoAplicable = new ArrayList<AraceDTO>();

            for (AraceDTO unidad : unidadesAdmin) {
                if (unidad.getIdArace().equals(Constantes.ACPPCE.intValue())
                        || unidad.getIdArace().equals(Constantes.ACIACE.intValue())
                        || unidad.getIdArace().equals(Constantes.PPCE.intValue())) {

                    unidadAdminNoAplicable.add(unidad);
                }
            }

            unidadesAdmin.removeAll(unidadAdminNoAplicable);

            return unidadesAdmin;
        } catch (Exception e) {
            logger.error("No se pudieron consultar las Unidades Administrativas");
            return null;
        }
    }

    @Override
    public List<FececEstados> construyeComboEntidad(EmpleadoDTO empleado) {
        return fececEstadosDao.obtenerEstadosPorIdEmpleado(
                new BigDecimal(empleado.getDetalleEmpleado().get(0).getCentral().getIdArace()));
    }

    @Override
    public List<FececEstados> traeTodasLasEntidades() {
        return fececEstadosDao.obtenerEstados();
    }

    public List<ConsultaPropuestas> empleadoIDprogramador(List<ConsultaPropuestas> listaPropuestas) {
        EmpleadoDTO empleado = null;
        for (ConsultaPropuestas lista : listaPropuestas) {
            try {
                if (lista.getIdProgramador() != null) {
                    empleado = getEmpleadoCompleto(lista.getIdProgramador().intValue());
                    lista.setRfcEmpleado(empleado.getRfc());
                }
            } catch (EmpleadoServiceException e) {
                logger.error("no encontro empleado" + e);
            }
        }
        return listaPropuestas;
    }

    @Override
    @PistaAuditoria
    public List<ConsultaPropuestas> cargarPropuestasPorArace(EmpleadoDTO empleado, boolean central, Object... args) {

        List<ConsultaPropuestas> listaPropuestas = empleadoIDprogramador(
                consultaPropuestasDao.cargarPropuestasPorArace(empleado, central, args));

        for (ConsultaPropuestas propuesta : listaPropuestas) {
            List<FecetDocExpediente> listaDocsExp = fecetDocExpedienteDao
                    .findWhereIdPropuestaEquals(propuesta.getIdPropuesta());
            propuesta.setListaExpediente(listaDocsExp);
        }

        return listaPropuestas;
    }

    @Override
    public HSSFWorkbook exportarConsultaPropuestas(List<ConsultaPropuestas> listaInformeComite) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        int count = 0;
        count = helper.insertaCabecera(sheet, count, style, helper.crearCabeceraExcel());
        helper.insertarListaExcel(listaInformeComite, count, sheet);
        return workbook;
    }

    @Override
    public EmpleadoDTO consultaEmpleado(String rfc) throws EmpleadoServiceException, NegocioException {
        EmpleadoDTO empleado = null;
        if (validarExistenciaTipoEmpleado(getEmpleadoCompleto(rfc), TipoEmpleadoEnum.PROGRAMADOR)) {
            empleado = getEmpleadoCompleto(rfc);
        } else {
            throw new NegocioException("No se pudo obtener la informacion del usuario logueado");
        }

        return empleado;
    }
}
