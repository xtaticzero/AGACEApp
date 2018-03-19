package mx.gob.sat.siat.feagace.vista.ordenes;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ContadorOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenPropuestasFirmante;
import mx.gob.sat.siat.feagace.negocio.ordenes.ContadorOrdenesValidarFirmarService;
import mx.gob.sat.siat.feagace.vista.FirmanteSuplenteMB;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;

/**
 * Clase back en donde se atienden las peticiones de la pagina inicial para la
 * validacion y la firma de documentos electronicos.
 *
 * @author Luis Rodriguez
 * @version 1.1
 */
@ManagedBean(name = "contadorOrdenesValidarFirmar")
@SessionScoped
public class ContadorOrdenesValidarFirmarMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = -447256369484573540L;

    @ManagedProperty(value = "#{contadorOrdenesValidarFirmarService}")
    private transient ContadorOrdenesValidarFirmarService contadorOrdenesValidarFirmarService;

    @ManagedProperty(value = "#{firmanteSuplente}")
    private transient FirmanteSuplenteMB firmanteSuplente;

    private List<ContadorOrdenes> listaTotalOrdenesValidarFirmar;
    private ResumenPropuestasFirmante listaResumenFirmantePropuestas;

    /**
     * Metodo con que se obtiene el numero de ordenes que son para validar y
     * firmar correspondiente a cada metodo
     */
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        try {
            if (firmanteSuplente.isSuplente()) {
                session.setAttribute("suplente", firmanteSuplente.getSuplenteSeleccionado());
                session.setAttribute("essuplente", true);
                String rfcSuplente = null;
                for (FecetSuplencia fs : firmanteSuplente.getSuplentes()) {
                    if (fs.getIdFirmanteASuplir()
                            .compareTo(new BigDecimal(firmanteSuplente.getSuplenteSeleccionado())) == 0) {
                        rfcSuplente = fs.getRfcSuplente();
                        session.setAttribute("rfcSuplente", rfcSuplente);
                        break;
                    }
                }
                listaTotalOrdenesValidarFirmar = contadorOrdenesValidarFirmarService.ordenesPorValidarFirmar(
                        new BigDecimal(firmanteSuplente.getSuplenteSeleccionado().toString()), rfcSuplente);
                listaResumenFirmantePropuestas = contadorOrdenesValidarFirmarService
                        .obtenerResumenPropuestasFirmante(rfcSuplente);
            } else {
                UserProfileDTO userProfileDTO = (UserProfileDTO) session.getAttribute("userProfile");
                session.setAttribute("essuplente", false);
                BigDecimal idEmpleado = null;
                listaTotalOrdenesValidarFirmar = contadorOrdenesValidarFirmarService.ordenesPorValidarFirmar(idEmpleado,
                        userProfileDTO.getRfc());
                listaResumenFirmantePropuestas = contadorOrdenesValidarFirmarService
                        .obtenerResumenPropuestasFirmante(userProfileDTO.getRfc());
            }
        } catch (Exception e) {
            logger.error("Error al consultar las ordenes ", e);
        }
    }

    /**
     * Metodo setContadorOrdenesValidarFirmarService
     *
     * @param contadorOrdenesValidarFirmarService
     */
    public void setContadorOrdenesValidarFirmarService(
            ContadorOrdenesValidarFirmarService contadorOrdenesValidarFirmarService) {
        this.contadorOrdenesValidarFirmarService = contadorOrdenesValidarFirmarService;
    }

    /**
     * Metodo getContadorOrdenesValidarFirmarService
     *
     * @return
     */
    public ContadorOrdenesValidarFirmarService getContadorOrdenesValidarFirmarService() {
        return contadorOrdenesValidarFirmarService;
    }

    /**
     * Metodo setListaTotalOrdenesValidarFirmar
     *
     * @param listaTotalOrdenesValidarFirmar
     */
    public void setListaTotalOrdenesValidarFirmar(List<ContadorOrdenes> listaTotalOrdenesValidarFirmar) {
        this.listaTotalOrdenesValidarFirmar = listaTotalOrdenesValidarFirmar;
    }

    /**
     * Metodo getListaTotalOrdenesValidarFirmar
     *
     * @return List
     */
    public List<ContadorOrdenes> getListaTotalOrdenesValidarFirmar() {
        return listaTotalOrdenesValidarFirmar;
    }

    public ResumenPropuestasFirmante getListaResumenFirmantePropuestas() {
        return listaResumenFirmantePropuestas;
    }

    public void setListaResumenFirmantePropuestas(ResumenPropuestasFirmante listaResumenFirmantePropuestas) {
        this.listaResumenFirmantePropuestas = listaResumenFirmantePropuestas;
    }

    public final FirmanteSuplenteMB getFirmanteSuplente() {
        return firmanteSuplente;
    }

    public final void setFirmanteSuplente(FirmanteSuplenteMB firmanteSuplente) {
        this.firmanteSuplente = firmanteSuplente;
    }
}
