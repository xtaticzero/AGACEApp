package mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.helper.CargaDocumentosHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.FirmaProrrogaOrdenContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("firmaProrrogaOrdenContribuyenteService")
public class FirmaProrrogaOrdenContribuyenteServiceImpl extends FirmaServiceAbstract implements
        FirmaProrrogaOrdenContribuyenteService {
    
    @SuppressWarnings("compatibility:-4542521885488180323")
    private static final long serialVersionUID = 1L;
    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    
    @Autowired
    private transient FecetDocProrrogaOrdenDao fecetDocProrrogaOrdenDao;
    
    @Autowired
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;
    
    private transient CargaDocumentosHelper helper = new CargaDocumentosHelper();
    
    @Override
    public FirmaDAO getFirmaDAO() {
        return fecetProrrogaOrdenDao;
    }
    
    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        ProrrogaDocsVO docs = (ProrrogaDocsVO) obj;
        for (FecetDocProrrogaOrden doc : docs.getListaDocsProrrogasOrden()) {
            doc.setIdProrrogaOrden(docs.getProrrogaOrden().getIdProrrogaOrden());
            doc.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoProrroga(docs.getProrrogaOrden().getIdProrrogaOrden(),
                    docs.getOrdenSeleccionado()));
            try {
                CargaArchivoUtil.guardarArchivoProrroga(doc);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            if (doc.getRutaArchivo() != null && doc.getRutaArchivo().indexOf(doc.getNombreArchivo()) < 0) {
                doc.setRutaArchivo(doc.getRutaArchivo().concat(doc.getNombreArchivo()));
            }
            fecetDocProrrogaOrdenDao.insert(doc);
        }
        docs.getProrrogaOrden().setOrden(docs.getOrdenSeleccionado());
        getAuditoriaService().cargarProrrogaOrden(docs.getOrdenSeleccionado());
        cargarFirmaProrrogaService.enviarCorreoProrroga(docs.getProrrogaOrden(), Constantes.CONTRIBUYENTE, "NOTIFICADA", null, null);       
        
    }
    
    protected void preparaPrecondiciones(FirmaDTO firmaDTO) {
        ProrrogaDocsVO docs = (ProrrogaDocsVO) firmaDTO.getObjectoFirma();
        helper.llenaProrroga(firmaDTO, docs.getProrrogaOrden(), fecetProrrogaOrdenDao.getIdFecetProrrogaOrdenPathDirectorio(), docs);        
    }
    
    @Override
    public File generaAcuse(Object obj) {
        ProrrogaDocsVO docs = (ProrrogaDocsVO) obj;
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        String cadenaContribuyente
                = docs.getProrrogaOrden().getCadenaContribuyente() != null ? "" : docs.getProrrogaOrden().getCadenaContribuyente();
        
        acuse = helper.llenaAcuseProrroga(acuse, docs.getProrrogaOrden(), docs, getRespuestaSelladora(cadenaContribuyente));
        
        acuse.setTituloGeneral(Constantes.TITULO_GENERAL);
        byte[] archivoPDF = CargaArchivoUtil.getArregloReporteProrrogaPdf(acuse);
        return CargaArchivoUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }
    
    private String getRespuestaSelladora(String cadena) {
        return getBuzonSelladoraService().getSelloDigital(cadena);
    }
}
