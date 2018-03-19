package mx.gob.sat.siat.feagace.vista.ordenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;

@ManagedBean(name = "ordenesValidarMB")
@SessionScoped
public class OrdenesValidarMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(OrdenesValidarMB.class);

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    private List<AgaceOrden> listaPorValidarSeguimiento;

    private EmpleadoDTO empleadoDTO;

    private List<String> prioridadRegistrada;

    @PostConstruct
    public void init() {
        try {
            this.listaPorValidarSeguimiento = new ArrayList<AgaceOrden>();
            getUsuarioAuditor();
            this.listaPorValidarSeguimiento = seguimientoOrdenesService.cargarListaPorValidar(this.empleadoDTO.getIdEmpleado());
            prioridadRegistrada = obtenerPrioridades();
        } catch (NegocioException e) {
            LOGGER.error("Error en el ManagedBean Seguimiento de Ordenes Por Validar ", e);
        } catch (NoExisteEmpleadoException e) {

            LOGGER.error("No se pudo cargar la informacion basica de la pagina", e);
            addErrorMessage(null, "No se cuentan con los permisos para ingresar al perfil solicitado", "");

        }
    }

    public List<String> obtenerPrioridades() {
        List<String> lstPrioridadRegistrada = new ArrayList<String>();
        if (listaPorValidarSeguimiento != null && !listaPorValidarSeguimiento.isEmpty()) {
            for (AgaceOrden registro : listaPorValidarSeguimiento) {
                if (!lstPrioridadRegistrada.contains(registro.getPrioridadSugerida())) {
                    lstPrioridadRegistrada.add(registro.getPrioridadSugerida());
                }
            }
            Collections.sort(lstPrioridadRegistrada);
        }
        return lstPrioridadRegistrada;
    }

    public List<String> getPrioridadRegistrada() {
        return prioridadRegistrada;
    }

    public void setPrioridadRegistrada(List<String> prioridadRegistrada) {
        this.prioridadRegistrada = prioridadRegistrada;
    }

    private void getUsuarioAuditor() throws NoExisteEmpleadoException {
        try {
            if (empleadoDTO == null || (!empleadoDTO.getRfc().equals(getRFCSession()))) {
                empleadoDTO = getEmpleadoService().getEmpleadoCompleto(getRFCSession());
                if (empleadoDTO != null && !getEmpleadoService().validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.AUDITOR)) {
                    empleadoDTO = null;
                    throw new NoExisteEmpleadoException("");

                }
            }
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }

    }

    public void setListaPorValidarSeguimiento(List<AgaceOrden> listaPorValidarSeguimiento) {
        this.listaPorValidarSeguimiento = listaPorValidarSeguimiento;
    }

    public List<AgaceOrden> getListaPorValidarSeguimiento() {
        return listaPorValidarSeguimiento;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

}
