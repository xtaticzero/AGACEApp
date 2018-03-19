package mx.gob.sat.siat.feagace.negocio.ordenes.promocion.firma.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.promocion.firma.helper.FirmaPromocionHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.promocion.firma.service.FirmaPromocionService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;


@Service("firmaPromocionService")
public class FirmaPromocionServiceImpl extends FirmaServiceAbstract implements FirmaPromocionService {

    @SuppressWarnings("compatibility:26428487271120376")
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;

    @Autowired
    private transient FecetPromocionDao fecetPromocionDao;

    @Autowired
    private transient FecetAlegatoDao fecetAlegatoDao;

    @Autowired
    private FirmaPromocionHelper firmaPromocionHelper;

    public FirmaPromocionServiceImpl() {
        super();
    }

    @Override
    public FirmaDAO getFirmaDAO() {
        return fecetPromocionDao;
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        PromocionDocsVO docs = (PromocionDocsVO) obj;
        guardarPromocionOrden(docs.getPromocionSeleccionado());
        if (!docs.getListaPruebasAlegatosCargadas().isEmpty()) {
            guardarPromocionPruebasAlegatos(docs.getPromocionSeleccionado(),
                    docs.getListaPruebasAlegatosCargadas());
        }
        getAuditoriaService().cargarPromocionOrden(docs.getOrdenSeleccionado());
        cargarFirmaPruebasPromoService.enviarCorreoPromocion(docs.getPromocionSeleccionado());
        
    }

    private void guardarPromocionOrden(final FecetPromocion promocion) {
        try {
            CargaArchivoUtil.guardarArchivoPromocion(promocion);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

    }

    public void guardarPromocionPruebasAlegatos(FecetPromocion promocion, List<FecetAlegato> listaAlegatos) {
        try {
            for (FecetAlegato pruebasAlegatos : listaAlegatos) {
                pruebasAlegatos.setIdPromocion(promocion.getIdPromocion());
                pruebasAlegatos.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPruebasAlegatos(promocion));
                CargaArchivoUtil.guardarArchivoPruebasAlegatos(pruebasAlegatos);
                pruebasAlegatos.setRutaArchivo(pruebasAlegatos.getRutaArchivo() + pruebasAlegatos.getNombreArchivo());                
                fecetAlegatoDao.insert(pruebasAlegatos);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    protected void preparaPrecondiciones(FirmaDTO firmaDTO) {
        PromocionDocsVO docs = (PromocionDocsVO) firmaDTO.getObjectoFirma();
        firmaPromocionHelper.llenaPromocion(firmaDTO, docs.getPromocionSeleccionado(), docs,
                fecetPromocionDao.getIdPromocion());
    }

    private String getRespuestaSelladora(String cadena) {
        return getBuzonSelladoraService().getSelloDigital(cadena);
    }

    @Override
    public File generaAcuse(Object obj) {
        logger.info(" :::::::::::::::::::::::::::::   genera ACUSE    ::::::::::::::::::");
        PromocionDocsVO docs = (PromocionDocsVO) obj;
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        acuse = firmaPromocionHelper.llenaAcuse(acuse, docs.getPromocionSeleccionado(), docs.getSizeOrden(), docs,
                getRespuestaSelladora(docs.getPromocionSeleccionado().getCadenaOriginal()));
        acuse.setTituloGeneral(Constantes.TITULO_GENERAL);
        logger.info(" :::::::::::::::::::::::::::::   regresa ACUSE    ::::::::::::::::::");
        byte[] archivoPDF = CargaArchivoUtil.getArregloReportePdf(acuse);
        return CargaArchivoUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }

    public FecetPromocionDao getFecetPromocionDao() {
        return fecetPromocionDao;
    }

    public void setFecetPromocionDao(FecetPromocionDao fecetPromocionDao) {
        this.fecetPromocionDao = fecetPromocionDao;
    }

}
