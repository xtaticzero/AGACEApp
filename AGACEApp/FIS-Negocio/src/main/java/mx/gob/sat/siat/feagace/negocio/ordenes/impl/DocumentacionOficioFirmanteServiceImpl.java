package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.RutaUtil;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.DocumentacionOficioFirmanteService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Service("documentacionOficioFirmanteService")
public class DocumentacionOficioFirmanteServiceImpl extends BaseBusinessServices implements DocumentacionOficioFirmanteService {

    private static final long serialVersionUID = 1987654L;
    @Autowired
    private transient FecetPromocionOficioDao fecetPromocionOficioDao;
    @Autowired
    private transient FecetAlegatoOficioDao fecetAlegatoOficioDao;
    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;
    @Autowired
    private transient FecetAnexosProrrogaOficioDao fecetAnexosProrrogaOficioDao;
    @Autowired
    private transient FecetDocProrrogaOficioDao fecetDocProrrogaOficioDao;
    @Autowired
    private transient FecetFlujoProrrogaOficioDao fecetFlujoProrrogaOficioDao;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;
    @Autowired
    private transient FececEstatusDao fecetEstatusDao;

    private static final String RUTA_DOC_RECHAZO_PRORROGA = RutaUtil.getOrigenRuta().concat("/fece/archivosCargadosOficio/");
    private static final String TIPO_OFICIO = "1";

    /**
     * <b>Obtiene Prorrogas Firmadas</b>
     *
     * @param idOficio
     * @return List
     */
    @Override
    public List<FecetProrrogaOficio> getProrrogasFirmadas(BigDecimal idOficio) {
        List<FecetProrrogaOficio> prorrogasAprobadas = fecetProrrogaOficioDao.getProrrogaPorOficioFirmadas(idOficio);
        for (FecetProrrogaOficio prorrogaOficio : prorrogasAprobadas) {
            prorrogaOficio.getFecetFlujoProrrogaOficio().setFececEstatus(fecetEstatusDao.findWhereIdEstatusEquals(prorrogaOficio.getFecetFlujoProrrogaOficio().getIdEstatus()).get(0));
        }
        return prorrogasAprobadas;
    }

    /**
     * <b>Obtiene las prorrogas pendientes de firma</b>
     *
     * @param idOficio
     * @return List FecetProrrogaOficio Pendientes
     */
    @Override
    public List<FecetProrrogaOficio> getProrrogasPendientes(BigDecimal idOficio) {
        List<FecetProrrogaOficio> prorrogasPendientes = fecetProrrogaOficioDao.getProrrogaPorOficioPendientes(idOficio);
        for (FecetProrrogaOficio prorrogaOficio : prorrogasPendientes) {
            prorrogaOficio.getFecetFlujoProrrogaOficio().setFececEstatus(fecetEstatusDao.findWhereIdEstatusEquals(prorrogaOficio.getFecetFlujoProrrogaOficio().getIdEstatus()).get(0));
        }
        return prorrogasPendientes;
    }

    /**
     * <b>Obtiene pruebas y alegatos</b>
     *
     * @param oficio
     * @return List
     */
    @Override
    public List<FecetPromocionOficio> getPruebasAlegatosOficio(FecetOficio oficio) {
        List<FecetPromocionOficio> listaPromociones
                = fecetPromocionOficioDao.getPromocionContadorPruebasAlegatosOficio(oficio.getIdOficio());
        for (FecetPromocionOficio promocion : listaPromociones) {
            promocion.setDescripcionTipoPromocion(BusinessUtil.getTipoPromocionPorTipoOficioMetodo(oficio.getFecetTipoOficio().getIdTipoOficio()));
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio()) || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())) {
                promocion.setExtemporanea("0");
            } else {
                promocion.setExtemporanea(plazosService.esDocumentoExtemporaneoOficio(oficio, promocion.getFechaCarga()) ? "1" : "0");
            }

        }
        return listaPromociones;
    }

    /**
     * <b>Obtiene la Documentacion de Contribuyente</b>
     *
     * @param idPromocionOficio
     * @return List
     */
    @Override
    public List<FecetAlegatoOficio> getPruebasAlegatosPromocion(BigDecimal idPromocionOficio) {
        return fecetAlegatoOficioDao.findWhereIdPromocionEquals(idPromocionOficio);
    }

    /**
     * <b>Obtiene los anexos que cargo el Auditor a la prorroga</b>
     *
     * @param fecetProrrogaOficio
     * @return List
     */
    @Override
    public List<FecetAnexosProrrogaOficio> getAnexosProrrogaOficio(final FecetProrrogaOficio fecetProrrogaOficio) {
        return fecetAnexosProrrogaOficioDao.findWhereIdFlujoProrrogaOficioEquals(fecetProrrogaOficio.getFecetFlujoProrrogaOficio().getIdFlujoProrrogaOficio());
    }

    /**
     * <b>Obtiene los documentos que presento el contribuyente</b>
     *
     * @param idProrrogaOficio
     * @return List
     */
    @Override
    public List<FecetDocProrrogaOficio> getDocumentacionProrrogaContribuyenteOficio(BigDecimal idProrrogaOficio) {
        return fecetDocProrrogaOficioDao.findWhereIdProrrogaOficioEquals(idProrrogaOficio);
    }

    /**
     * <b>Rechaza Prorroga del Oficio</b>
     *
     * @param prorroga
     * @param status
     * @param listaDocProrroga
     */
    @Override
    @PistaAuditoria
    public BigDecimal rechazaFirmaProrrogaFirmante(FecetProrrogaOficio prorroga, String motivoRechazoFirmante,
            BigDecimal status, List<DocumentoVO> listaDocProrroga, FecetOficio oficio) {
        final String idFlujoProrrogaOficio
                = fecetFlujoProrrogaOficioDao.findLastIdFecetFlujoProrrogaOficio(prorroga.getIdProrrogaOficio());
        FecetFlujoProrrogaOficio flujoProrroga
                = fecetFlujoProrrogaOficioDao.findByPrimaryKey(new BigDecimal(idFlujoProrrogaOficio));
        flujoProrroga.setIdEstatus(status);
        flujoProrroga.setFechaRechazoFirmante(new Date());
        flujoProrroga.setJustificacionFirmante(motivoRechazoFirmante);
        fecetFlujoProrrogaOficioDao.update(flujoProrroga);

        final String rutaDocProroga
                = RutaArchivosUtil.armarRutaAnexosProrrogaRechazadaFirmante(RUTA_DOC_RECHAZO_PRORROGA,
                        new BigDecimal(idFlujoProrrogaOficio));
        for (DocumentoVO docCargado : listaDocProrroga) {
            FecetAnexosProrrogaOficio anexosProrroga = new FecetAnexosProrrogaOficio();
            anexosProrroga.setIdFlujoProrrogaOficio(new BigDecimal(idFlujoProrrogaOficio));
            anexosProrroga.setNombreArchivo(docCargado.getNombreArchivo());
            anexosProrroga.setArchivo(docCargado.getInputStream());
            anexosProrroga.setRutaArchivo(rutaDocProroga);
            anexosProrroga.setFechaCreacion(new Date());
            anexosProrroga.setTipoAnexo(TIPO_OFICIO);
            try {
                ArchivoOrdenUtil.guardarArchivoAnexoProrrogaOficio(anexosProrroga);
            } catch (IOException e) {
                logger.error("No se pudo crear el archivo Anexo Prorroga Oficio Rechazado [{}] ", e);
            }
            anexosProrroga.setRutaArchivo(rutaDocProroga.concat(anexosProrroga.getNombreArchivo()));
            fecetAnexosProrrogaOficioDao.insert(anexosProrroga);
            cargarFirmaProrrogaService.enviarCorreoProrroga(null, Constantes.FIRMANTE, "RECHAZO", oficio, prorroga);
        }
        
        return oficio.getIdOrden();
    }
}
