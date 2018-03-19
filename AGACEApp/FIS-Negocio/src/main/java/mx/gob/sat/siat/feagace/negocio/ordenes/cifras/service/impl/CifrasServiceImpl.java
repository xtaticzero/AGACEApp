package mx.gob.sat.siat.feagace.negocio.ordenes.cifras.service.impl;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececConceptoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FeceaTipoCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececTipoParcialidadDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FeceaCifraImpuestoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecehDocCifraHistoricoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecehHistoricoCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecehParcialidadHistoricoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecetCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecetDocCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecetParcialidadCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.TotalCifrasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.cifras.service.CifrasService;
import mx.gob.sat.siat.feagace.negocio.reportes.impl.ReportesCifrasHelper;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;

@Component("cifrasService")
public class CifrasServiceImpl extends BaseBusinessServices implements CifrasService {

    private static final long serialVersionUID = 1L;

    private static final int UNO = 1;
    private static final int DOS = 2;
    private static final int TRES = 3;
    private static final int CUATRO = 4;

    @Autowired
    private transient FeceaTipoCifraDAO feceaTipoCifraDAO;

    @Autowired
    private transient FececTipoParcialidadDAO fececTipoParcialidadDAO;

    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;

    @Autowired
    private transient FececConceptoDao fececConceptoDao;

    @Autowired
    private transient FecetCifraDAO fecetCifraDAO;

    @Autowired
    private transient FeceaCifraImpuestoDAO feceaCifraImpuestoDAO;

    @Autowired
    private transient FecetDocCifraDAO fecetDocCifraDAO;

    @Autowired
    private transient FecetParcialidadCifraDAO fecetParcialidadCifraDAO;

    @Autowired
    private transient FecehHistoricoCifraDAO fecehHistoricoCifraDAO;

    @Autowired
    private transient FecehParcialidadHistoricoDAO fecehParcialidadHistoricoDAO;

    @Autowired
    private transient FecehDocCifraHistoricoDAO fecehDocCifraHistoricoDAO;

    @Override
    public List<FeceaCifraTipoCifraDTO> obtenerCifrasPorTipo(int tipoCifra) {
        return feceaTipoCifraDAO.obtenerCifrasPorTipo(tipoCifra);
    }

    @Override
    public List<FececTipoImpuesto> getCatalogoImpuesto() {
        return fececTipoImpuestoDao.findAll();
    }

    @Override
    public List<FececConcepto> getConceptoByImpuesto(BigDecimal idImpuesto) {
        return fececConceptoDao.findByIdTipoImpuesto(idImpuesto);
    }

    @Override
    public List<FececTipoParcialidadDTO> obtenerParcialidades() {
        return fececTipoParcialidadDAO.obtenerTiposParcialidad();
    }

    @Override
    public BigDecimal insertarCifra(FecetCifraDTO cifra, AgaceOrden orden) {
        BigDecimal idCifra = fecetCifraDAO.obtenerIdDetalleCifra(orden.getId(), cifra.getTipoCifra().getIdCifra());
        try {
            idCifra = idCifra != null ? idCifra : fecetCifraDAO.insertarCifra(cifra, orden.getId());
            if (idCifra != null) {
                cifra.setIdCifra(idCifra);
                for (FeceaCifraImpuestoDTO cifraImpuesto : cifra.getCifras()) {
                    BigDecimal idFeceaCifraDocumento = feceaCifraImpuestoDAO.insertaCifra(cifraImpuesto, idCifra);
                    if (idFeceaCifraDocumento != null) {
                        for (FecetDocCifraDTO documento : cifraImpuesto.getListaDocumentosCifra()) {
                            documento.setRutaArchivo(obtenerRuta(orden, cifra));
                            fecetDocCifraDAO.insertaDocumento(documento, idFeceaCifraDocumento);
                            CargaArchivoUtil.guardarArchivoCifras(documento);
                        }
                        if (cifraImpuesto.getParcialidad() != null) {
                            fecetParcialidadCifraDAO.insertaParcialidad(cifraImpuesto.getParcialidad(), idFeceaCifraDocumento);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifra;
    }

    private String obtenerRuta(AgaceOrden orden, FecetCifraDTO cifra) {
        StringBuilder sb = new StringBuilder();
        switch (cifra.getCifra()) {
            case CIFRAS_COBRADAS:
                sb.append(CifrasServiceImpl.armarRutaDestinoCifrasCobradas(cifra, orden));
                break;
            case CIFRAS_VIRTUALES:
                sb.append(CifrasServiceImpl.armarRutaDestinoCifrasVirtuales(cifra, orden));
                break;
            case CIFRAS_LIQUIDADAS:
                sb.append(CifrasServiceImpl.armarRutaDestinoCifrasLiquidadas(cifra, orden));
                break;
            default:
                break;
        }
        return sb.toString();
    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraImpuestoConcepto(BigDecimal idOrden, BigDecimal tipoCifra, BigDecimal impuesto,
            BigDecimal concepto) {
        List<FeceaCifraImpuestoDTO> cifras = null;
        try {
            cifras = feceaCifraImpuestoDAO.obtenerCifrasPorOrdenCifraImpuestoConcepto(idOrden, tipoCifra, impuesto, concepto);
            for (FeceaCifraImpuestoDTO cifra : cifras) {
                cifra.setListaDocumentosCifra(obtenerDocumentos(cifra.getIdCifraImpuesto()));
                cifra.setParcialidad(obtenerParcialidadCifraImpuesto(cifra.getIdCifraImpuesto()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return cifras;
    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraTipoCifra(BigDecimal idOrden, BigDecimal tipoCifraTipoCifra) {
        List<FeceaCifraImpuestoDTO> cifras = null;
        try {
            cifras = feceaCifraImpuestoDAO.obtenerCifrasPorOrdenCifraTipoCifra(idOrden, tipoCifraTipoCifra);
            for (FeceaCifraImpuestoDTO cifra : cifras) {
                cifra.setListaDocumentosCifra(obtenerDocumentos(cifra.getIdCifraImpuesto()));
                for (FecetDocCifraDTO documento : cifra.getListaDocumentosCifra()) {
                    try {
                        File initialFile = new File(documento.getRutaArchivo());
                        InputStream targetStream = new FileInputStream(initialFile);
                        documento.setDocumento(targetStream);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
                cifra.setParcialidad(obtenerParcialidadCifraImpuesto(cifra.getIdCifraImpuesto()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return cifras;
    }

    @Override
    public List<FecetCifraDTO> obtenerCifrasPorOrden(BigDecimal idOrden, BigDecimal tipoCifra) {
        List<FecetCifraDTO> cifras = null;
        try {
            cifras = fecetCifraDAO.obtenerCifrasPorOrden(idOrden, tipoCifra);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return cifras;
    }

    @Override
    public FecetParcialidadCifraDTO obtenerParcialidadCifraImpuesto(BigDecimal idCifraImpuesto) {
        return fecetParcialidadCifraDAO.obtenerParcialidadCifraImpuesto(idCifraImpuesto);
    }

    public FecetParcialidadCifraDTO obtenerParcialidadCifraImpuestoHist(BigDecimal idCifraImpuesto) {
        return fecehParcialidadHistoricoDAO.obtenerParcialidadCifraImpuesto(idCifraImpuesto);
    }

    @Override
    public BigDecimal actualizarCifra(AgaceOrden orden, FecetCifraDTO cifra) {
        try {
            FeceaCifraImpuestoDTO cifraActualizable = cifra.getCifras().get(0);
            List<FeceaCifraImpuestoDTO> detalleCifras = obtenerCifrasPorOrdenCifraTipoCifra(orden.getId(), cifra.getTipoCifra().getIdCifra());
            List<FecetDocCifraDTO> documentosActuales;
            FecetParcialidadCifraDTO parcialidadActual;
            for (FeceaCifraImpuestoDTO cifraImpuesto : detalleCifras) {
                cifraImpuesto.setListaDocumentosCifra(obtenerDocumentos(cifraImpuesto.getIdCifraImpuesto()));
                cifraImpuesto.setParcialidad(obtenerParcialidadCifraImpuesto(cifraImpuesto.getIdCifraImpuesto()));
                if (!cifraImpuesto.getIdCifraImpuesto().equals(cifraActualizable.getIdCifraImpuesto())) {
                    documentosActuales = cifraImpuesto.getListaDocumentosCifra();
                    parcialidadActual = cifraImpuesto.getParcialidad();
                } else {
                    documentosActuales = cifraActualizable.getListaDocumentosCifra();
                    cifra.setTipoCifra(cifraImpuesto.getTipoCifra());
                    cifra.setIdCifra(cifraImpuesto.getIdCifra());
                    for (FecetDocCifraDTO documento : documentosActuales) {
                        documento.setRutaArchivo(obtenerRuta(orden, cifra));
                        if (documento.getDocumento() != null) {
                            CargaArchivoUtil.guardarArchivoCifras(documento);
                        }
                    }
                    parcialidadActual = cifraActualizable.getParcialidad();
                }
                guardarHistorico(cifraImpuesto, documentosActuales, parcialidadActual);
            }
            feceaCifraImpuestoDAO.actualizarCifra(cifraActualizable);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public List<FecetDocCifraDTO> obtenerDocumentos(BigDecimal idCifraImpuesto) {
        return fecetDocCifraDAO.obtenerDocumentos(idCifraImpuesto);
    }

    public List<FecetDocCifraDTO> obtenerDocumentosHistorico(BigDecimal idCifraImpuesto) {
        return fecehDocCifraHistoricoDAO.obtenerDocumentos(idCifraImpuesto);
    }

    @Override
    public List<TotalCifrasDTO> obtenerEncabezadoCifras(BigDecimal idOrden) {
        return feceaTipoCifraDAO.obtenerSumatoriaPorTipoCifraIdOrden(idOrden);
    }

    @Override
    public boolean existeCifra(BigDecimal idOrden, BigDecimal tipoCifra, BigDecimal impuesto, int concepto) {
        return feceaCifraImpuestoDAO.existeCifra(idOrden, tipoCifra, impuesto, concepto);
    }

    private void guardarHistorico(FeceaCifraImpuestoDTO cifraImpuesto, List<FecetDocCifraDTO> documentosActuales, FecetParcialidadCifraDTO parcialidad) {
        BigDecimal consecutivo = fecehHistoricoCifraDAO.obtenerConsecutivo(cifraImpuesto.getIdCifraImpuesto());
        BigDecimal idHistoricoCifra = fecehHistoricoCifraDAO.insertarRegistro(cifraImpuesto, consecutivo);
        if (cifraImpuesto.getParcialidad() != null) {
            fecehParcialidadHistoricoDAO.insertarRegistro(cifraImpuesto.getIdCifraImpuesto(), idHistoricoCifra);
        }
        if (parcialidad != null && fecetParcialidadCifraDAO.actualizarParcialidad(parcialidad, cifraImpuesto.getIdCifraImpuesto()).intValue() == 0) {
            fecetParcialidadCifraDAO.insertaParcialidad(parcialidad, cifraImpuesto.getIdCifraImpuesto());
        }
        if (cifraImpuesto.getListaDocumentosCifra() != null && !cifraImpuesto.getListaDocumentosCifra().isEmpty()) {
            fecetDocCifraDAO.actualizarEstatusDocumento(cifraImpuesto.getIdCifraImpuesto(), BigDecimal.ZERO);
            for (FecetDocCifraDTO documento : documentosActuales) {
                fecetDocCifraDAO.insertaDocumento(documento, cifraImpuesto.getIdCifraImpuesto());
            }
            for (FecetDocCifraDTO documento : cifraImpuesto.getListaDocumentosCifra()) {
                fecehDocCifraHistoricoDAO.insertarRegistro(documento, idHistoricoCifra);
            }
        }
    }

    @Override
    public BigDecimal eliminaCifra(FecetCifraDTO cifra, BigDecimal idOrden) {
        BigDecimal idCifra = fecetCifraDAO.obtenerIdDetalleCifra(idOrden, cifra.getTipoCifra().getIdCifra());
        try {
            fecetCifraDAO.actualizarEstatusCifra(idCifra, Constantes.BIG_DECIMAL_CERO);
            feceaCifraImpuestoDAO.actualizarEstatusCifra(idCifra, Constantes.BIG_DECIMAL_CERO);
            List<FeceaCifraImpuestoDTO> cifras = obtenerCifrasPorOrdenCifraTipoCifra(idOrden, cifra.getTipoCifra().getIdCifra());
            for (FeceaCifraImpuestoDTO cifraImpuesto : cifras) {
                BigDecimal idCifraImpuesto = cifraImpuesto.getIdCifraImpuesto();
                fecetDocCifraDAO.actualizarEstatusDocumento(idCifraImpuesto, Constantes.BIG_DECIMAL_CERO);
                if (cifraImpuesto.getParcialidad() != null) {
                    fecetParcialidadCifraDAO.actualizarEstatusParcialidad(idCifraImpuesto, Constantes.BIG_DECIMAL_CERO);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifra;
    }

    @Override
    public BigDecimal eliminaImpuesto(FeceaCifraImpuestoDTO cifraImpuesto) {
        BigDecimal idCifraImpuesto = cifraImpuesto.getIdCifraImpuesto();
        try {
            feceaCifraImpuestoDAO.actualizarEstatusImpuesto(idCifraImpuesto, Constantes.BIG_DECIMAL_CERO);
            fecetDocCifraDAO.actualizarEstatusDocumento(idCifraImpuesto, Constantes.BIG_DECIMAL_CERO);
            if (cifraImpuesto.getParcialidad() != null) {
                fecetParcialidadCifraDAO.actualizarEstatusParcialidad(idCifraImpuesto, Constantes.BIG_DECIMAL_CERO);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifraImpuesto;
    }

    @Override
    public List<FecetCifraDTO> obtenerEncabezadoCifrasHistorico(BigDecimal idTipoCifra, BigDecimal idOrden) {
        return fecehHistoricoCifraDAO.obtenerEncabezadoHistorico(idTipoCifra, idOrden);
    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerImpuestosCifrasHistorico(BigDecimal idOrden, BigDecimal tipoCifraTipoCifra, BigDecimal consecutivo) {
        List<FeceaCifraImpuestoDTO> cifras = fecehHistoricoCifraDAO.obtenerCifrasPorOrdenCifraTipoCifraHist(idOrden, tipoCifraTipoCifra, consecutivo);
        for (FeceaCifraImpuestoDTO cifraImpuesto : cifras) {
            cifraImpuesto.setListaDocumentosCifra(obtenerDocumentosHistorico(cifraImpuesto.getIdHistoricoCifra()));
            FecetParcialidadCifraDTO parcialidad = obtenerParcialidadCifraImpuestoHist(cifraImpuesto.getIdHistoricoCifra());
            cifraImpuesto.setParcialidad(parcialidad == null ? new FecetParcialidadCifraDTO() : parcialidad);
        }
        return cifras;
    }

    public static String armarRutaDestinoCifrasCobradas(FecetCifraDTO cifra, AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(armarRutaDestinoOrden(orden));
        rutaDestino.append(DOCUMENTOS_COBRADAS);
        rutaDestino.append(cifra.getTipoCifra().getIdCifra());
        rutaDestino.append(DIAGONAL);
        rutaDestino.append(cifra.getIdCifra());
        rutaDestino.append(DIAGONAL);
        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCifrasLiquidadas(FecetCifraDTO cifra, AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(armarRutaDestinoOrden(orden));
        rutaDestino.append(DOCUMENTOS_LIQUIDADAS);
        rutaDestino.append(cifra.getTipoCifra().getIdCifra());
        rutaDestino.append(DIAGONAL);
        rutaDestino.append(cifra.getIdCifra());
        rutaDestino.append(DIAGONAL);
        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCifrasVirtuales(FecetCifraDTO cifra, AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(armarRutaDestinoOrden(orden));
        rutaDestino.append(DOCUMENTOS_VIRTUALES);
        rutaDestino.append(cifra.getTipoCifra().getIdCifra());
        rutaDestino.append(DIAGONAL);
        rutaDestino.append(cifra.getIdCifra());
        rutaDestino.append(DIAGONAL);
        return rutaDestino.toString();
    }

    public static String armarRutaDestinoOrden(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    @Override
    public FileInputStream getReporteConsulta(BigDecimal idOrden, BigDecimal tipoCifra) {
        FileInputStream reporte = null;
        List<FeceaCifraImpuestoDTO> listaCifraDetalles = new ArrayList<FeceaCifraImpuestoDTO>();
        List<FecetCifraDTO> listaCifraDetallesEncabezado = null;
        try {
            listaCifraDetallesEncabezado = obtenerCifrasPorOrden(idOrden, tipoCifra);
            for (FecetCifraDTO encabezadoCifra : listaCifraDetallesEncabezado) {
                listaCifraDetalles.addAll(obtenerCifrasPorOrdenCifraTipoCifra(idOrden,
                        encabezadoCifra.getTipoCifra().getIdCifra()));
            }
            Map<String, Object> parametros = ReportesCifrasHelper.getHeaderReporte(tipoCifra);
            Boolean[] visibles = ReportesCifrasHelper.configurarDetalle(tipoCifra.intValue());
            parametros.put("DATA_SOURCE_CIFRA", ReportesCifrasHelper.dataSourceBienes(listaCifraDetalles));
            parametros.put("ACTUALIZACIONES_VISIBLE", visibles[0]);
            parametros.put("MULTAS_VISIBLE", visibles[UNO]);
            parametros.put("RECARGOS_VISIBLE", visibles[DOS]);
            parametros.put("TOTAL_VISIBLE", visibles[TRES]);
            parametros.put("PARCIALIDAD_VISIBLE", visibles[CUATRO]);
            File jasperFile = ReportesCifrasHelper.crearArchivo("CIFRAS_COBRADAS", parametros, null);
            reporte = new FileInputStream(jasperFile);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return reporte;
    }

}
