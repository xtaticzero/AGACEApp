package mx.gob.sat.siat.feagace.vista.propuestas.carga;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedProperty;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentoElectronicoService;

public abstract class CargaDocumentoElectronicoMBAbstract extends BaseManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{cargaDocumentoElectronicoService}")
    private CargaDocumentoElectronicoService cargaDocumentoElectronicoService;

    private transient UploadedFile archivoCarga;
    private transient UploadedFile oficioCarga;

    private ColaboradorVO agenteAduanalVO;

    private boolean permiteOficio;
    private boolean muestraOficio;
    private EmpleadoDTO empleado;
    
    private boolean muestraAgenteAduanal;
    private boolean colaboradorGuardado;

    private static final long N_4196000L = 4194304L;
    private static final String ERROR_TIPO_ARCHIVO = "S\u00f3lo se aceptan archivos WORD 2007 o superior";
    private static final String ARCHIVO_DEMASIADO_GRANDE = "El archivo es demasiado grande, lo m\u00e1ximo permitido son 4MB.";
    private static final String ARCHIVO_INVALIDO = "Archivo inv\u00e1lido";
    private static final String ERROR_CARGAR_ARCHIVO = "Error al cargar el archivo.";
    private static final String ARCHIVO_DEMASIADO_CHICO = "El archivo es demasiado chico";
    private static final String ARCHIVOS_PDF = "S\u00f3lo se aceptan archivos pdf";

    public Boolean validaArchivoCargaPropuesta(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {

            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            addErrorMessage(null, ARCHIVO_INVALIDO, ERROR_TIPO_ARCHIVO);
        }

        return false;
    }

    public Boolean validaArchivoCarga(final UploadedFile archivo, final Long tipoDocumento) {
        if (tipoDocumento.equals(1L)) {
            if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
                return validaTamanoArchivo(archivo);
            } else {
                addErrorMessage(null, ARCHIVO_INVALIDO, ERROR_TIPO_ARCHIVO);
            }
        } else if (tipoDocumento.equals(2L)) {
            if (archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)) {
                return validaTamanoArchivo(archivo);
            } else {
                addErrorMessage(null, ARCHIVO_INVALIDO, ARCHIVOS_PDF);
            }
        }

        return false;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= N_4196000L) {
            return true;
        } else {
            if (archivo.getSize() >= N_4196000L) {
                addErrorMessage(null, ERROR_CARGAR_ARCHIVO, ARCHIVO_DEMASIADO_GRANDE);
            } else {
                addErrorMessage(null, ERROR_CARGAR_ARCHIVO, ARCHIVO_DEMASIADO_CHICO);
            }
        }

        return false;
    }

    public CargaDocumentoElectronicoService getCargaDocumentoElectronicoService() {
        return cargaDocumentoElectronicoService;
    }

    public void setCargaDocumentoElectronicoService(CargaDocumentoElectronicoService cargaDocumentoElectronicoService) {
        this.cargaDocumentoElectronicoService = cargaDocumentoElectronicoService;
    }

    public void validaFormularioContribuyente() {
        String formularioMostrar = "registroCentralProp.show();";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(formularioMostrar);
    }
    
    public boolean validarMostrarAgenteAduanal(BigDecimal idMetodo) {
        return idMetodo.longValue() == TipoMetodoEnum.EHO.getId(); 
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getOficioCarga() {
        return oficioCarga;
    }

    public void setOficioCarga(UploadedFile oficioCarga) {
        this.oficioCarga = oficioCarga;
    }

    public ColaboradorVO getAgenteAduanalVO() {
        return agenteAduanalVO;
    }

    public void setAgenteAduanalVO(ColaboradorVO agenteAduanalVO) {
        this.agenteAduanalVO = agenteAduanalVO;
    }

    public boolean isPermiteOficio() {
        return permiteOficio;
    }

    public void setPermiteOficio(boolean permiteOficio) {
        this.permiteOficio = permiteOficio;
    }

    public boolean isMuestraOficio() {
        return muestraOficio;
    }

    public void setMuestraOficio(boolean muestraOficio) {
        this.muestraOficio = muestraOficio;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public boolean isMuestraAgenteAduanal() {
        return muestraAgenteAduanal;
    }

    public void setMuestraAgenteAduanal(boolean muestraAgenteAduanal) {
        this.muestraAgenteAduanal = muestraAgenteAduanal;
    }

    public boolean isColaboradorGuardado() {
        return colaboradorGuardado;
    }

    public void setColaboradorGuardado(boolean colaboradorGuardado) {
        this.colaboradorGuardado = colaboradorGuardado;
    }
}
