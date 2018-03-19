package mx.gob.sat.siat.feagace.negocio.common.firma.service;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.buzonSelladora.service.BuzonSelladoraService;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececActosAdmDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentoelectronico.firma.FirmaDocumentoElectronicoDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public abstract class FirmaServiceAbstract extends BaseBusinessServices implements FirmaService {

    private static final long serialVersionUID = -5346615215996681495L;

    @Value("${service.nyv.activo}")
    private boolean activoNyv;

    @Value("${service.nyv.version.uno}")
    private String version;

    @Autowired
    private transient BuzonSelladoraService buzonSelladoraService;

    @Autowired
    private PlazosServiceOrdenes plazosService;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @Autowired
    private transient AuditoriaService auditoriaService;

    @Autowired
    private transient FececActosAdmDao fececActosAdmDao;

    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;

    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;

    @Autowired
    private transient FirmaDocumentoElectronicoDAO firmaDocumentoElectronicoDAO;

    @Autowired
    @Qualifier("notificacionService")
    private transient NotificacionService notificacionService;

    public abstract FirmaDAO getFirmaDAO();

    public abstract void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante);

    @Override
    public File guardarFirma(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {

        if (firmas != null && !firmas.isEmpty()) {

            for (Map.Entry<String, FirmaDTO> entry : firmas.entrySet()) {
                if (!entry.getKey().contains("|")) {
                    entry.getValue().setObjectoFirma(obj);
                    preparaPrecondiciones(entry.getValue());
                    getFirmaDAO().guardarFirma(entry.getValue());
                }
            }

            procesaPostCondiciones(firmas, obj, rfcFirmante);
            return generaAcuse(obj);
        }
        return null;
    }

    protected void preparaPrecondiciones(FirmaDTO firmaDTO) {

    }

    public File generaAcuse(Object obj) {
        return null;
    }

    public final BuzonSelladoraService getBuzonSelladoraService() {
        return buzonSelladoraService;
    }

    public final PlazosServiceOrdenes getPlazosService() {
        return plazosService;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public FececActosAdmDao getFececActosAdmDao() {
        return fececActosAdmDao;
    }

    public FecetContribuyenteDao getFecetContribuyenteDao() {
        return fecetContribuyenteDao;
    }

    public FecetAsociadoDao getFecetAsociadoDao() {
        return fecetAsociadoDao;
    }

    public final FirmaDocumentoElectronicoDAO getFirmaDocumentoElectronicoDAO() {
        return firmaDocumentoElectronicoDAO;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public boolean isActivoNyv() {
        return activoNyv;
    }

    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    public String getVersion() {
        if (version.equals("mock") || version.equals("true")) {
            return "true";
        }
        return "false";
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
