package mx.gob.sat.siat.feagace.vista.ordenes.cifras.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;

public class CifrasAttributeHelper {
    private static final Logger LOGGER = Logger.getLogger(CifrasAttributeHelper.class);

    private static final int BYTE_1024 = 1024;

    private boolean panelCifrasCobradasVisible;

    private boolean panelCifrasVirtualesVisible;

    private boolean panelCifrasLiquidadasVisible;

    private boolean parcialidadesVisible;

    private boolean esActualizacion;

    private transient UploadedFile documentoCifras;

    private List<FecetDocCifraDTO> listaDocumentosCifra;

    private transient StreamedContent documento;

    private transient FecetDocCifraDTO documentoSeleccionado;

    private FeceaCifraTipoCifraDTO tipoCifra;

    private BigDecimal derivaAntecedente;

    private FececTipoImpuesto tipoImpuesto;

    private FececConcepto concepto;

    private Date fechaPago;

    private BigDecimal importeImpuesto;

    private BigDecimal actualizaciones;

    private BigDecimal multas;

    private BigDecimal recargos;

    private BigDecimal total;

    private BigDecimal pagoParcialidades;

    private FececTipoParcialidadDTO tipoParcialidad;

    private BigDecimal numeroParcialidades;

    private BigDecimal montoParcialidad;

    private String observaciones;

    private boolean existeCifra;

    private boolean tablaImpuestosVisible;

    private BigDecimal idCifraImpuestoConcepto;

    private boolean parcialidadesTablaVisible;    
    
    private transient StreamedContent archivoDescarga;

    public CifrasAttributeHelper() {
        listaDocumentosCifra = new ArrayList<FecetDocCifraDTO>();
        tipoCifra = new FeceaCifraTipoCifraDTO();
        tipoImpuesto = new FececTipoImpuesto();
        concepto = new FececConcepto();
        tipoParcialidad = new FececTipoParcialidadDTO();
        total = Constantes.BIG_DECIMAL_CERO;
    }
    
    public StreamedContent getArchivoDescarga() {
        return archivoDescarga;
    }

    public void setArchivoDescarga(StreamedContent archivoDescarga) {
        this.archivoDescarga = archivoDescarga;
    }

    public BigDecimal getIdCifraImpuestoConcepto() {
        return idCifraImpuestoConcepto;
    }

    public void setIdCifraImpuestoConcepto(BigDecimal idCifraImpuestoConcepto) {
        this.idCifraImpuestoConcepto = idCifraImpuestoConcepto;
    }

    public boolean isTablaImpuestosVisible() {
        return tablaImpuestosVisible;
    }

    public void setTablaImpuestosVisible(boolean tablaImpuestosVisible) {
        this.tablaImpuestosVisible = tablaImpuestosVisible;
    }

    public boolean isExisteCifra() {
        return existeCifra;
    }

    public void setExisteCifra(boolean existeCifra) {
        this.existeCifra = existeCifra;
    }

    public FececTipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(FececTipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public FececConcepto getConcepto() {
        return concepto;
    }

    public void setConcepto(FececConcepto concepto) {
        this.concepto = concepto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getMontoParcialidad() {
        return montoParcialidad;
    }

    public void setMontoParcialidad(BigDecimal montoParcialidad) {
        this.montoParcialidad = montoParcialidad;
    }

    public BigDecimal getNumeroParcialidades() {
        return numeroParcialidades;
    }

    public void setNumeroParcialidades(BigDecimal numeroParcialidades) {
        this.numeroParcialidades = numeroParcialidades;
    }

    public FececTipoParcialidadDTO getTipoParcialidad() {
        return tipoParcialidad;
    }

    public void setTipoParcialidad(FececTipoParcialidadDTO tipoParcialidad) {
        this.tipoParcialidad = tipoParcialidad;
    }

    public BigDecimal getPagoParcialidades() {
        return pagoParcialidades;
    }

    public void setPagoParcialidades(BigDecimal pagoParcialidades) {
        this.pagoParcialidades = pagoParcialidades;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getRecargos() {
        return recargos;
    }

    public void setRecargos(BigDecimal recargos) {
        this.recargos = recargos;
    }

    public BigDecimal getMultas() {
        return multas;
    }

    public void setMultas(BigDecimal multas) {
        this.multas = multas;
    }

    public BigDecimal getActualizaciones() {
        return actualizaciones;
    }

    public void setActualizaciones(BigDecimal actualizaciones) {
        this.actualizaciones = actualizaciones;
    }

    public BigDecimal getImporteImpuesto() {
        return importeImpuesto;
    }

    public void setImporteImpuesto(BigDecimal importeImpuesto) {
        this.importeImpuesto = importeImpuesto;
    }

    public Date getFechaPago() {
        return (fechaPago != null) ? (Date) fechaPago.clone() : null;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = (fechaPago != null) ? (Date) fechaPago.clone() : null;
    }

    public BigDecimal getDerivaAntecedente() {
        return derivaAntecedente;
    }

    public void setDerivaAntecedente(BigDecimal derivaAntecedente) {
        this.derivaAntecedente = derivaAntecedente;
    }

    public FeceaCifraTipoCifraDTO getTipoCifra() {
        return tipoCifra;
    }

    public void setTipoCifra(FeceaCifraTipoCifraDTO tipoCifra) {
        this.tipoCifra = tipoCifra;
    }

    public FecetDocCifraDTO getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(FecetDocCifraDTO documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public StreamedContent getDocumento() {
        byte[] archivoBytes = null;
        String nombreDocumento = "";
        try {
            archivoBytes = lecturaArchivo(getDocumentoSeleccionado().getDocumento());
            if (getDocumentoSeleccionado().getNombre() != null && 
                    !getDocumentoSeleccionado().getNombre().equals("")) {
                nombreDocumento = getDocumentoSeleccionado().getNombre();
            }
            else {
                nombreDocumento = getDocumentoCifras().getFileName();
            }
            setDocumento(new DefaultStreamedContent(new ByteArrayInputStream(archivoBytes),
                    CargaArchivoUtilOrdenes.obtenContentTypeArchivoPropuesta(nombreDocumento),
                    CargaArchivoUtilOrdenes.aplicarCodificacionTexto(nombreDocumento)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return documento;
    }

    private byte[] lecturaArchivo(InputStream archivoEntrada) {
        final ByteArrayOutputStream resultado = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[BYTE_1024];
        try {
            while ((nRead = archivoEntrada.read(data, 0, data.length)) != -1) {
                resultado.write(data, 0, nRead);
            }
            resultado.flush();
        } catch (IOException e) {
            LOGGER.error("Error al realizar la lectura de archivo para descarga", e);
        }
        return resultado.toByteArray();
    }

    public void setDocumento(StreamedContent documento) {
        this.documento = documento;
    }

    public List<FecetDocCifraDTO> getListaDocumentosCifra() {
        return listaDocumentosCifra;
    }

    public void setListaDocumentosCifra(List<FecetDocCifraDTO> listaDocumentosCifra) {
        this.listaDocumentosCifra = listaDocumentosCifra;
    }

    public UploadedFile getDocumentoCifras() {
        return documentoCifras;
    }

    public void setDocumentoCifras(UploadedFile documentoCifras) {
        this.documentoCifras = documentoCifras;
    }

    public boolean isPanelCifrasCobradasVisible() {
        return panelCifrasCobradasVisible;
    }

    public void setPanelCifrasCobradasVisible(boolean panelCifrasCobradasVisible) {
        this.panelCifrasCobradasVisible = panelCifrasCobradasVisible;
    }

    public boolean isPanelCifrasVirtualesVisible() {
        return panelCifrasVirtualesVisible;
    }

    public void setPanelCifrasVirtualesVisible(boolean panelCifrasVirtualesVisible) {
        this.panelCifrasVirtualesVisible = panelCifrasVirtualesVisible;
    }

    public boolean isPanelCifrasLiquidadasVisible() {
        return panelCifrasLiquidadasVisible;
    }

    public void setPanelCifrasLiquidadasVisible(boolean panelCifrasLiquidadasVisible) {
        this.panelCifrasLiquidadasVisible = panelCifrasLiquidadasVisible;
    }

    public boolean isParcialidadesVisible() {
        return parcialidadesVisible;
    }

    public void setParcialidadesVisible(boolean parcialidadesVisible) {
        this.parcialidadesVisible = parcialidadesVisible;
    }

    public boolean isEsActualizacion() {
        return esActualizacion;
    }

    public void setEsActualizacion(boolean esActualizacion) {
        this.esActualizacion = esActualizacion;
    }

    public boolean isParcialidadesTablaVisible() {
        return parcialidadesTablaVisible;
    }

    public void setParcialidadesTablaVisible(boolean parcialidadesTablaVisible) {
        this.parcialidadesTablaVisible = parcialidadesTablaVisible;
    }
    
}
