package mx.gob.sat.siat.feagace.vista.common.firma;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ActualizacionNumeroReferenciaService;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.helper.ConsultarRegistrarNyVHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.firma.FirmanteOrdenSuplenteMB;
import mx.gob.sat.siat.modelo.dto.AccesoUsr;

@ManagedBean(name = "firmaManagedBean")
@ViewScoped
public abstract class FirmaManagedBean extends BaseManagedBean {

    private static final long serialVersionUID = 8268898751649868751L;
    
    private static final Logger LOG = Logger.getLogger(FirmaManagedBean.class);
    
    private List<FirmaDTO> firmas;
    
    private File acuse;
    
    private String rfcFirma;
    
    @ManagedProperty(value = "#{consultarRegistrarNyVHelper}")
    private ConsultarRegistrarNyVHelper consultarRegistrarNyVHelper;
    
    @ManagedProperty(value = "#{actualizacionNumeroReferenciaService}")
    private ActualizacionNumeroReferenciaService actualizacionNumeroReferenciaService;
    
    @ManagedProperty(value = "#{firmanteSuplenteOrden}")
    private FirmanteOrdenSuplenteMB firmanteOrdenSuplenteMB;

    public void iniciarFirmas() {
        setFirmas(getFirmaHelper().armarCadena(getDatosFirma(), getRFCSession()));
    }

    public abstract Object getDatosFirma();

    public String guardarFirma() {
        if(!rfcFirma.equals(getRFCSession())){
            addErrorMessage("El RFC Logeado no coincide con el certificado ingresado");
            return null;
        }else{
            Map<String, FirmaDTO> mapaFirmas = procesarFirma();
            try {
                registarNumeroReferencia();
                acuse = getFirmaService().guardarFirma(mapaFirmas, getDatosFirma(), getRFCSession());
                consultarRegistrarNyVHelper.eliminarArchivoTemporal(getRFCSession());
                addMessage("Se firm\u00f3 correctamente");
                return regresar();
            } catch (IllegalArgumentException e) {
                registarNumeroReferencia();
                LOG.error(e.getMessage());
                addErrorMessage(e.getMessage());                
                return regresar();
            }
        }
    }

    public Map<String, FirmaDTO> procesarFirma() {
        Map<String, FirmaDTO> mapFirma = new HashMap<String, FirmaDTO>();
        String rfcFirmado = getRFCSession();
        String rfcSuplente = null;
        if (firmanteOrdenSuplenteMB.isSuplente()) {
            for (FecetSuplencia fs : firmanteOrdenSuplenteMB.getSuplentes()) {
                if (fs.getIdFirmanteASuplir().compareTo(new BigDecimal(firmanteOrdenSuplenteMB.getSuplenteSeleccionado())) == 0) {
                    rfcSuplente = fs.getRfcSuplente();
                    break;
                }
            }
        }
        AccesoUsr usuario = getUsuario();
        Long centroCosto = 0L;
        if (usuario != null && Pattern.matches("[0-9]+", usuario.getCentroCosto())) {
            centroCosto = Long.valueOf(usuario.getCentroCosto());
        }
        for (FirmaDTO firma : getFirmas()) {
            firma.setRfcFirmado(rfcFirmado);
            firma.setRfcSuplir(rfcSuplente);
            firma.setCentroCosto(centroCosto);
            firma.setNumeroEmpleado(
                    getUserProfile() != null && getUserProfile().getIdEmpleado() != null ? Long.valueOf(getUserProfile().getIdEmpleado().longValue()) : Long.valueOf("0"));
            mapFirma.put(firma.getId(), firma);
        }
        return mapFirma;
    }

    public final String getUrlFirmadoJS() {
        return getFirmaHelper().getUrlFirmadoJS();
    }

    public abstract String regresar();

    public List<FirmaDTO> getFirmas() {
        return firmas;
    }

    public void setFirmas(List<FirmaDTO> firmas) {
        this.firmas = firmas;
    }

    public abstract FirmaService getFirmaService();

    public abstract FirmaHelper getFirmaHelper();

    public File getAcuse() {
        return acuse;
    }

    public String getRfcFirma() {
        return rfcFirma;
    }

    public void setRfcFirma(String rfcFirma) {
        this.rfcFirma = rfcFirma;
    }

    public final FirmanteOrdenSuplenteMB getFirmanteOrdenSuplenteMB() {
        return firmanteOrdenSuplenteMB;
    }

    public final void setFirmanteOrdenSuplenteMB(FirmanteOrdenSuplenteMB firmanteOrdenSuplenteMB) {
        this.firmanteOrdenSuplenteMB = firmanteOrdenSuplenteMB;
    }
    
    protected AccesoUsr getUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        AccesoUsr accesoUsr = null;
        try {
            accesoUsr = (AccesoUsr) session.getAttribute("accesoEF");
            if (accesoUsr == null) {
                accesoUsr = (AccesoUsr) session.getAttribute("acceso");
            }
        } catch (Exception e) {
            logger.error("No se pudo obtener la sesion ", e);
        }
        return accesoUsr;
    }
    
	public ConsultarRegistrarNyVHelper getConsultarRegistrarNyVHelper() {
		return consultarRegistrarNyVHelper;
	}

	public void setConsultarRegistrarNyVHelper(ConsultarRegistrarNyVHelper consultarRegistrarNyVHelper) {
		this.consultarRegistrarNyVHelper = consultarRegistrarNyVHelper;
	}

	public ActualizacionNumeroReferenciaService getActualizacionNumeroReferenciaService() {
		return actualizacionNumeroReferenciaService;
	}

	public void setActualizacionNumeroReferenciaService(
			ActualizacionNumeroReferenciaService actualizacionNumeroReferenciaService) {
		this.actualizacionNumeroReferenciaService = actualizacionNumeroReferenciaService;
	}
    
    public void registarNumeroReferencia(){
    	List<String> listNumReferencia =  consultarRegistrarNyVHelper.leerArchivoTemporal(getRFCSession());
        if(listNumReferencia!=null && !listNumReferencia.isEmpty()){
        	for(String registro: listNumReferencia){
        		String registroArray[] = registro.split(":");
        		actualizacionNumeroReferenciaService.actualizaTabla(registroArray[Constantes.ENTERO_CERO],registroArray[Constantes.ENTERO_UNO], registroArray[Constantes.ENTERO_DOS]);
        	}     
        	consultarRegistrarNyVHelper.eliminarArchivoTemporal(getRFCSession());
        }
    }
}
