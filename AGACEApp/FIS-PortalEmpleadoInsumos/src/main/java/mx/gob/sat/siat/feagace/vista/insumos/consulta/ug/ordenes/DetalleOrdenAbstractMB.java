package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOrdenSolicitudHelper;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOrdenUnifHelper;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOrdenVistaHelper;
import mx.gob.sat.siat.feagace.vista.model.ordenes.OrdenConsultaDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DetalleOrdenAbstractMB extends ReimprimirDocumentosAbstractMB {

    private static final long serialVersionUID = 6150223668956207860L;

    protected static final String CU_13 = "consultarPropuestasOrdenes";

    protected static final String ORIGEN_CONSULTA = "origenConsulta";
    protected static final String USER_PROFILE = "userProfile";
    protected static final String ORDEN_CONSULTA_SELECCIONADA = "ordenConsultaSeleccionada";

    private DetalleOrdenUnifHelper helper;
    private DetalleOrdenSolicitudHelper solHelper;
    private OrdenConsultaDTO ordenConsultaSeleccionada;
    private DetalleOrdenVistaHelper vistaHelper;

    protected AgaceOrden convertToAgaceOrden(OrdenConsultaDTO ordenConsultaDTO) {

        try {
            List<AgaceOrden> listaOrden = getConsultarReimprimirDocumentosService().buscarOrden(
                    ordenConsultaDTO.getNumeroOrden(), ordenConsultaDTO.getIdRegistro(), null, null, null);

            return listaOrden.get(0);
        } catch (Exception npe) {
            logger.error("No se pudo cargar la orden. ", npe);
            return null;
        }
    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    protected void tablasDatosAMostrar() {

        getVistaHelper().setPnlDocRequerida(true);
        getVistaHelper().setPnlOficios(true);
        getVistaHelper().setPnlSolContrib(true);
        getVistaHelper().setPnlRepreLegal(true);

        getVistaHelper().setPnlAgenteAdnl(true);
        getVistaHelper().setPnlPapelesTrab(true);
        
    }

    public EmpleadoDTO obtenerEmpleado() throws EmpleadoServiceException {
        return getEmpleadoService().getEmpleadoCompleto(getRFCSession());
    }

    public OrdenConsultaDTO getOrdenConsultaSeleccionada() {
        return ordenConsultaSeleccionada;
    }

    public void setOrdenConsultaSeleccionada(OrdenConsultaDTO ordenConsultaSeleccionada) {
        getHelper().setOrdenSeleccionada(convertToAgaceOrden(ordenConsultaSeleccionada));
        this.ordenConsultaSeleccionada = ordenConsultaSeleccionada;
    }

    public DetalleOrdenUnifHelper getHelper() {
        return helper;
    }

    public void setHelper(DetalleOrdenUnifHelper helper) {
        this.helper = helper;
    }

    public DetalleOrdenSolicitudHelper getSolHelper() {
        return solHelper;
    }

    public void setSolHelper(DetalleOrdenSolicitudHelper solHelper) {
        this.solHelper = solHelper;
    }

    public DetalleOrdenVistaHelper getVistaHelper() {
        return vistaHelper;
    }

    public void setVistaHelper(DetalleOrdenVistaHelper vistaHelper) {
        this.vistaHelper = vistaHelper;
    }
}
