package mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PruebasPericialesDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.helper.CargaDocumentosHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPericialesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service.FirmaPruebasPericialesContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Service("firmaPruebasPericialesContribuyenteService")
public class FirmaPruebasPericialesContribuyenteServiceImpl extends FirmaServiceAbstract implements
            FirmaPruebasPericialesContribuyenteService {
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;
    
    @Autowired
    private transient FecetDocPruebasPericialesDao fecetDocPruebasPericialesDao;
    
    @Autowired
    private transient CargarFirmaPruebasPericialesService cargarFirmaPruebasPericialesService;
    
    private transient CargaDocumentosHelper helper = new CargaDocumentosHelper();
    
    @Override
    public FirmaDAO getFirmaDAO() {
        return fecetPruebasPericialesDao;
    }
    
    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        PruebasPericialesDocsVO docs = (PruebasPericialesDocsVO) obj;
        for (FecetDocPruebasPericiales doc : docs.getListaDocsPruebasPericiales()) {
            doc.setIdPruebasPericiales(docs.getPruebasPericiales().getIdPruebasPericiales());
            doc.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPruebasPericiales(docs.getPruebasPericiales().getIdPruebasPericiales(),
                    docs.getOrdenSeleccionado()));
            try {
                CargaArchivoUtil.guardarArchivoPruebasPericiales(doc);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            if (doc.getRutaArchivo() != null && doc.getRutaArchivo().indexOf(doc.getNombreArchivo()) < 0) {
                doc.setRutaArchivo(doc.getRutaArchivo().concat(doc.getNombreArchivo()));
            }
            fecetDocPruebasPericialesDao.insert(doc);
        }
        docs.getPruebasPericiales().setOrden(docs.getOrdenSeleccionado());
        getAuditoriaService().cargarPruebasPericialesOficio(docs.getOrdenSeleccionado());
        cargarFirmaPruebasPericialesService.enviarCorreoPruebasPericiales(docs.getPruebasPericiales(), Constantes.CONTRIBUYENTE, "NOTIFICADA");        
    }
    
    protected void preparaPrecondiciones(FirmaDTO firmaDTO) {
        PruebasPericialesDocsVO docs = (PruebasPericialesDocsVO) firmaDTO.getObjectoFirma();
        helper.llenaPruebaPericial(firmaDTO, docs.getPruebasPericiales(), fecetPruebasPericialesDao.getIdFecetPruebasPericialesPathDirectorio(), docs);        
    }
    
    @Override
    public File generaAcuse(Object obj) {
        PruebasPericialesDocsVO docs = (PruebasPericialesDocsVO) obj;
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        String cadenaContribuyente
                = docs.getPruebasPericiales().getCadenaContribuyente() == null ? "" : docs.getPruebasPericiales().getCadenaContribuyente();
        
        acuse = helper.llenaAcusePruebaPericial(acuse, docs.getPruebasPericiales(), docs, getRespuestaSelladora(cadenaContribuyente));
        
        acuse.setTituloGeneral(Constantes.TITULO_GENERAL);
        byte[] archivoPDF = CargaArchivoUtil.getArregloReportePruebasPericialesPdf(acuse);
        return CargaArchivoUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }
    
    private String getRespuestaSelladora(String cadena) {
        return getBuzonSelladoraService().getSelloDigital(cadena);
    }

}
