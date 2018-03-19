package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.prorroga.firma.service.impl;

import java.io.File;
import java.io.IOException;

import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.helper.CargaDocumentosHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.prorroga.firma.service.FirmaProrrogaOficioContribuyenteService;

import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("firmaProrrogaOficioContribuyenteService")
public class FirmaProrrogaOficioContribuyenteServiceImpl extends FirmaServiceAbstract implements FirmaProrrogaOficioContribuyenteService {

    @SuppressWarnings("compatibility:444642105967681292")
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;

    @Autowired
    private transient FecetDocProrrogaOficioDao fecetDocProrrogaOficioDao;

    @Autowired
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;

    private transient CargaDocumentosHelper helper = new CargaDocumentosHelper();

    @Override
    public FirmaDAO getFirmaDAO() {
        return fecetProrrogaOficioDao;
    }

    protected void preparaPrecondiciones(FirmaDTO firmaDTO) {
        ProrrogaDocsVO docs = (ProrrogaDocsVO) firmaDTO.getObjectoFirma();
        helper.llenaProrrogaOficio(firmaDTO, docs.getProrrogaOficio(),
                fecetProrrogaOficioDao.getIdFecetProrrogaOficioPathDirectorio(), docs);
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        ProrrogaDocsVO docs = (ProrrogaDocsVO) obj;
        for (FecetDocProrrogaOficio doc : docs.getListaDocsProrrogasOficio()) {
            doc.setIdProrrogaOficio(docs.getProrrogaOficio().getIdProrrogaOficio());
            doc.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoProrrogaOficio(docs.getOficioSeleccionado(),
                    docs.getProrrogaOficio().getIdProrrogaOficio()));
            try {
                CargaArchivoUtil.guardarArchivoProrrogaOficio(doc);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            if (doc.getRutaArchivo() != null && doc.getRutaArchivo().indexOf(doc.getNombreArchivo()) < 0) {
                doc.setRutaArchivo(doc.getRutaArchivo().concat(doc.getNombreArchivo()));
            }
            fecetDocProrrogaOficioDao.insert(doc);

        }
        docs.getProrrogaOficio().setOrden(docs.getOrdenSeleccionado());
        getAuditoriaService().cargarProrrogaOficio(docs.getOficioSeleccionado());
        cargarFirmaProrrogaService.enviarCorreoProrroga(null, Constantes.CONTRIBUYENTE, "NOTIFICADA", docs.getOficioSeleccionado(), docs.getProrrogaOficio());
    }

    @Override
    public File generaAcuse(Object obj) {
        ProrrogaDocsVO docs = (ProrrogaDocsVO) obj;
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        acuse
                = helper.llenaAcuseProrrogaOficio(acuse, docs.getProrrogaOficio(), docs, getRespuestaSelladora(docs.getProrrogaOficio().getCadenaContribuyente()));
        acuse.setTituloGeneral(Constantes.TITULO_GENERAL);
        byte[] archivoPDF = CargaArchivoUtil.getArregloReporteProrrogaPdf(acuse);
        return CargaArchivoUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }

    private String getRespuestaSelladora(String cadena) {
        return getBuzonSelladoraService().getSelloDigital(cadena);
    }
}
