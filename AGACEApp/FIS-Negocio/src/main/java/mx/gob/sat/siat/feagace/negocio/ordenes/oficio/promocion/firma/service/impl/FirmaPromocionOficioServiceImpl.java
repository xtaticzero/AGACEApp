package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.promocion.firma.service.impl;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.helper.CargaDocumentosHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.promocion.firma.service.FirmaPromocionOficioService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;

import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("firmaPromocionOficioService")
public class FirmaPromocionOficioServiceImpl extends FirmaServiceAbstract implements FirmaPromocionOficioService {

    @SuppressWarnings("compatibility:4200163301023009629")
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPromocionOficioDao fecetPromocionOficioDao;

    @Autowired
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;

    @Autowired
    private transient FecetAlegatoOficioDao fecetAlegatoOficioDao;

    private transient CargaDocumentosHelper helper = new CargaDocumentosHelper();

    @Override
    public FirmaDAO getFirmaDAO() {
        return fecetPromocionOficioDao;
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        PromocionDocsVO docs = (PromocionDocsVO)obj;
        guardaPromocionOficioSystem(docs.getPromocionOficioSeleccionado());
        if (!docs.getListaPruebasAlegatosOficioCargadas().isEmpty()) {
            guardaPruebasAlegatosOficio(docs.getPromocionOficioSeleccionado(),
                                        docs.getListaPruebasAlegatosOficioCargadas());
        }
        getAuditoriaService().cargarPromocionOficio(docs.getOficioSeleccionado());
        cargarFirmaPruebasPromoService.enviarCorreoPromocionOficio(docs.getIdOrdenAuditadaCompulsa() != null ?
                                                                   docs.getIdOrdenAuditadaCompulsa() :
                                                                   docs.getOficioSeleccionado().getIdOrden(),
                                                                   docs.getPromocionOficioSeleccionado());
    }

    private void guardaPromocionOficioSystem(FecetPromocionOficio promocionOficio) {
        try {
            CargaArchivoUtil.guardarArchivoPromocionOficio(promocionOficio);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void guardaPruebasAlegatosOficio(FecetPromocionOficio promocionOficio, List<FecetAlegatoOficio> lista) {
        for (FecetAlegatoOficio pruebasAlegatosOficio : lista) {
            pruebasAlegatosOficio.setIdPromocionOficio(promocionOficio.getIdPromocionOficio());
            pruebasAlegatosOficio.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPruebasAlegatosOficio(promocionOficio));
            pruebasAlegatosOficio.setRutaArchivo(pruebasAlegatosOficio.getRutaArchivo() +
                                                 pruebasAlegatosOficio.getNombreArchivo());
            try {
                CargaArchivoUtil.guardarArchivoPruebasAlegatosOficio(pruebasAlegatosOficio);
                fecetAlegatoOficioDao.insert(pruebasAlegatosOficio);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
    }

    @Override
    protected void preparaPrecondiciones(FirmaDTO firmaDTO) {
        PromocionDocsVO docs = (PromocionDocsVO)firmaDTO.getObjectoFirma();
        helper.llenaPromocionOficio(firmaDTO, docs.getPromocionOficioSeleccionado(), docs,
                                    fecetPromocionOficioDao.getIdPromocionOficio());
    }

    private String getRespuestaSelladora(String cadena) {
        return getBuzonSelladoraService().getSelloDigital(cadena);
    }

    @Override
    public File generaAcuse(Object obj) {
        PromocionDocsVO docs = (PromocionDocsVO)obj;
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        acuse =
                helper.llenaAcuseOficio(acuse, docs.getPromocionOficioSeleccionado(), docs.getSizeOficio(), docs, getRespuestaSelladora(docs.getPromocionOficioSeleccionado().getCadenaOriginal()));
        acuse.setTituloGeneral(Constantes.TITULO_GENERAL);

        byte[] archivoPDF = CargaArchivoUtil.getArregloReportePdf(acuse);
        return CargaArchivoUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }
}
