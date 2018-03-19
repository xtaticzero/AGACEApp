package mx.gob.sat.siat.feagace.negocio.reportes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.TipoReportesDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.reportes.ReportesServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.reportes.ValidarEmpleadoReportesService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;

/**
 * Servicio para validar el tipo de empleado Ejecutivo/Gerencial
 *
 * @author Domingo Fernandez
 * @version 1.0
 *
 */
@Service("validarEmpleadoReportesService")
public class ValidarEmpleadoReportesServiceImpl extends ReportesServiceAbstract implements ValidarEmpleadoReportesService {

    private static final long serialVersionUID = 1L;

    private static final String ERROR_EMPLEADO_NO_EXISTE = "empleado.no.existente";

    private EmpleadoDTO empleadoEjecutivo;
    private EmpleadoDTO empleadoGerencial;

    @Override
    public TipoReportesDTO validarEmpleado(String rfc) throws NoExisteEmpleadoException {
        TipoReportesDTO reporteEmpleado = new TipoReportesDTO();
        EmpleadoDTO empleadoDTO;
        this.setEmpleadoEjecutivo(null);
        this.setEmpleadoGerencial(null);
        try {
            empleadoDTO = getEmpleadoCompleto(rfc);
            if (empleadoDTO != null && validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.GENERADOR_REPORTES_EJECUTIVO)) {
                this.setEmpleadoEjecutivo(empleadoDTO);
            }

            if (empleadoDTO != null && validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.GENERADOR_REPORTES_GERENCIAL)) {
                this.setEmpleadoGerencial(empleadoDTO);
            }

            if (empleadoDTO != null && (empleadoEjecutivo != null || empleadoGerencial != null)) {
                if (this.getEmpleadoEjecutivo() != null && this.getEmpleadoEjecutivo().getIdEmpleado() != null) {
                    reporteEmpleado.setMostrarEjecutivo(true);
                    reporteEmpleado.setTituloReporte("lbl.reporte.titulo.ejecutivo");
                    reporteEmpleado.setArchivoJasper(ConstantesReportes.REPORTE_EJECTIVO);
                    reporteEmpleado.setFececEmpleado(this.getEmpleadoEjecutivo());
                } else {
                    reporteEmpleado.setMostrarEjecutivo(false);
                }
                if (this.getEmpleadoGerencial() != null && this.getEmpleadoGerencial().getIdEmpleado() != null) {
                    reporteEmpleado.setMostrarGerencial(true);
                    reporteEmpleado.setTituloReporte("lbl.reporte.titulo.gerencial");
                    reporteEmpleado.setArchivoJasper(ConstantesReportes.REPORTE_GERENCIAL);
                    reporteEmpleado.setFececEmpleado(this.getEmpleadoGerencial());
                } else {
                    reporteEmpleado.setMostrarGerencial(false);
                }
            } else {
                throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE);
            }
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }
        return reporteEmpleado;
    }

    @Override
    public List<EmpleadoDTO> obtenerProgramadores(EmpleadoDTO empleado, BigDecimal tipoEmpleado) {
        if (empleado != null && empleado.getDetalleEmpleado() != null) {
            for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                try {
                    return getEmpleadosXUnidadAdmva(det.getCentral().getIdArace(), tipoEmpleado.intValue(), ClvSubModulosAgace.PROPUESTAS);
                } catch (EmpleadoServiceException ex) {
                    logger.error(ex.getMessage(), ex);
                    return new ArrayList<EmpleadoDTO>();
                }
            }
        }
        return new ArrayList<EmpleadoDTO>();
    }

    public void setEmpleadoEjecutivo(EmpleadoDTO empleadoEjecutivo) {
        this.empleadoEjecutivo = empleadoEjecutivo;
    }

    public EmpleadoDTO getEmpleadoEjecutivo() {
        return empleadoEjecutivo;
    }

    public void setEmpleadoGerencial(EmpleadoDTO empleadoGerencial) {
        this.empleadoGerencial = empleadoGerencial;
    }

    public EmpleadoDTO getEmpleadoGerencial() {
        return empleadoGerencial;
    }

    @Override
    public List<String> obtenerLstRfcUsuario(TipoEmpleadoEnum tipoEmpleado, TipoAraceEnum unidadAdministrativa) {
        try {
            List<EmpleadoDTO> empleados = getEmpleadosXUnidadAdmva((int) unidadAdministrativa.getId(), (int) tipoEmpleado.getId(), ClvSubModulosAgace.INSUMOS);
            if (empleados != null) {
                List<String> lstRfc = new ArrayList<String>();
                for (EmpleadoDTO emp : empleados) {
                    lstRfc.add(emp.getRfc());
                }
                return lstRfc;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<String>();
        }

        return null;
    }
}
