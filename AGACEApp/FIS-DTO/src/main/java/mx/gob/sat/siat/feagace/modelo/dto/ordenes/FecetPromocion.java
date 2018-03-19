/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetPromocion extends BaseModel {

    @SuppressWarnings("compatibility:-3936880483656776863")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column IDPROMOCION in the FECET_PROMOCION table.
     */
    private BigDecimal idPromocion;

    /**
     * This attribute maps to the column IDORDEN in the FECET_PROMOCION table.
     */
    private BigDecimal idOrden;

    /**
     * This attribute maps to the column FECHACARGA in the FECET_PROMOCION table.
     */
    private Date fechaCarga;

    /**
     * This attribute maps to the column NOMBREARCHIVO in the FECET_PROMOCION table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTAARCHIVO in the FECET_PROMOCION table.
     */
    private String rutaArchivo;

    /**
     * Este atributo llevara el total de las pruebas y alegatos asignadas a una promocion,
     * NO ESTA MAPEADO A LA BASE DE DATOS.
     */
    private Long contadorPruebasAlegatos;

    /**
     * This attribute maps to the column ID_ASOCIADO_CARGA in the FECET_PROMOCION table.
     */
    private BigDecimal idAsociadoCarga;

    /**
     * This attribute maps to the column NOMBRE_ACUSE in the FECET_PROMOCION table.
     */
    private String nombreAcuse;

    /**
     * This attribute maps to the column RUTA_ACUSE in the FECET_PROMOCION table.
     */
    private String rutaAcuse;

    /**
     * este atributo contendrá el path de la ubicacion del acuse de recibo generado para la promocion.
     */
    private String acuseRecibo;

    /**
     * este atributo contendrá si es un documento extemporanea.
     */
    private String extemporanea;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;

    /**
     * este atributo contendra el tipo de promocion.
     * ESTE CAMPO NO ESTA MAPEADO A LA BASE DE DATOS
     */
    private String descripcionTipoPromocion;

    private FecetAsociado asociado;

    private String cadenaOriginal;

    private String firmaElectronica;
    
    private BigDecimal idArchivoTemp;
    
    private String leyenda;


    /**
     * Method 'FecetPromocion'
     *
     */
    public FecetPromocion() {
    }

    /**
     * Method 'createPk'
     *
     * @return FecetPromocionPk
     */
    public FecetPromocionPk createPk() {
        return new FecetPromocionPk(idPromocion);
    }

    /**
     * Method 'getIdPromocion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPromocion() {
        return idPromocion;
    }

    /**
     * Method 'setIdPromocion'
     *
     * @param idPromocion
     */
    public void setIdPromocion(final BigDecimal idPromocion) {
        this.idPromocion = idPromocion;
    }

    /**
     * Method 'getIdOrden'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdOrden() {
        return idOrden;
    }

    /**
     * Method 'setIdOrden'
     *
     * @param idOrden
     */
    public void setIdOrden(final BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * Method 'getFechaCarga'
     *
     * @return Date
     */
    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date)fechaCarga.clone() : null;
    }

    /**
     * Method 'setFechaCarga'
     *
     * @param fechaCarga
     */
    public void setFechaCarga(final Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
    }

    /**
     * Method 'getNombreArchivo'
     *
     * @return String
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Method 'setNombreArchivo'
     *
     * @param nombreArchivo
     */
    public void setNombreArchivo(final String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Method 'getRutaArchivo'
     *
     * @return String
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * Method 'setRutaArchivo'
     *
     * @param rutaArchivo
     */
    public void setRutaArchivo(final String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }


    /**
     * Metodo setNombreAcuse
     * @param nombreAcuse
     */
    public void setNombreAcuse(final String nombreAcuse) {
        this.nombreAcuse = nombreAcuse;
    }

    /**
     * Metodo getNombreAcuse
     * @return String
     */
    public String getNombreAcuse() {
        return nombreAcuse;
    }

    /**
     * Metodo setRutaAcuse
     * @param rutaAcuse
     */
    public void setRutaAcuse(final String rutaAcuse) {
        this.rutaAcuse = rutaAcuse;
    }

    /**
     * Metodo getRutaAcuse
     * @return String
     */
    public String getRutaAcuse() {
        return rutaAcuse;
    }

    public void setAcuseRecibo(String acuseRecibo) {
        this.acuseRecibo = acuseRecibo;
    }

    public String getAcuseRecibo() {
        return acuseRecibo;
    }

    /**
     * Metodo setContadorPruebasAlegatos
     * @param contadorPruebasAlegatos
     */
    public void setContadorPruebasAlegatos(final Long contadorPruebasAlegatos) {
        this.contadorPruebasAlegatos = contadorPruebasAlegatos;
    }

    /**
     * Metodo getContadorPruebasAlegatos
     * @return Long
     */
    public Long getContadorPruebasAlegatos() {
        return contadorPruebasAlegatos;
    }

    public void setExtemporanea(String extemporanea) {
        this.extemporanea = extemporanea;
    }

    public String getExtemporanea() {
        return extemporanea;
    }

    /**
     * Metodo setArchivo
     * @param archivo
     */
    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    /**
     * Metodo getArchivo
     * @return InputStream
     */
    public InputStream getArchivo() {
        return archivo;
    }

    public void setDescripcionTipoPromocion(String descripcionTipoPromocion) {
        this.descripcionTipoPromocion = descripcionTipoPromocion;
    }

    public String getDescripcionTipoPromocion() {
        return descripcionTipoPromocion;
    }

    public void setIdAsociadoCarga(BigDecimal idAsociadoCarga) {
        this.idAsociadoCarga = idAsociadoCarga;
    }

    public BigDecimal getIdAsociadoCarga() {
        return idAsociadoCarga;
    }


    public void setAsociado(FecetAsociado asociado) {
        this.asociado = asociado;
    }

    public FecetAsociado getAsociado() {
        return asociado;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setFirmaElectronica(String firmaElectronica) {
        this.firmaElectronica = firmaElectronica;
    }

    public String getFirmaElectronica() {
        return firmaElectronica;
    }

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }
}
