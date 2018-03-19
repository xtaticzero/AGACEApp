package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.base.vista.BaseManagedBean;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;

@Component
public class ValidaAccionesHelper extends BaseManagedBean {

    private static final long serialVersionUID = -3517238578632157777L;

    public boolean validaPropuestaInformada(BigDecimal estatus, boolean isConsultaPropOrden) {

        boolean propuestaInformada = true;

        if (estatus.longValue() == TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_AUDITOR.getId()) {
            propuestaInformada = false;
        }

        if (estatus.longValue() == (TipoEstatusEnum.PROPUESTA_RETROALIMENTADA.getId())
                || estatus.longValue() == (TipoEstatusEnum.NO_APROBADA_LA_RETROALIMENTACION.getId())) {

            propuestaInformada = false;
        }

        if (estatus.longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR.getId()) {
            propuestaInformada = false;
        }

        if (estatus
                .longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO
                .getId()) {
            propuestaInformada = false;
        }

        if (estatus.longValue() == TipoEstatusEnum.NO_APROBADA_LA_CANCELACION.getId()) {
            propuestaInformada = false;
        }

        if (estatus.longValue() == (TipoEstatusEnum.FIRMANTE_TRANSFERENCIA_NO_APROBADA.getId())
                || estatus.longValue() == (TipoEstatusEnum.PROPUESTA_TRANSFERIDA.getId())) {
            propuestaInformada = false;
        }

        if (estatus.longValue() == TipoEstatusEnum.PROPUESTA_NO_APROBADO_EL_RECHAZO.getId()) {
            propuestaInformada = false;
        }

        if (isConsultaPropOrden) {
            propuestaInformada = true;
        }

        return propuestaInformada;
    }

    public boolean validaNombreArchivoCancelar(List<FecetPropCancelada> listaPropCancelacion, String nombreArchivo,
            List<FecetPropCancelada> listaDocCancelar) {

        for (FecetPropCancelada fecetPropCancelada : listaDocCancelar) {
            if (fecetPropCancelada.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        for (FecetPropCancelada fecetPropCancelada : listaPropCancelacion) {
            if (fecetPropCancelada.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        return false;
    }

    public boolean validaNombreArchivoTransferir(List<FecetTransferencia> listaTransferenciaPropuesta,
            String nombreArchivo, List<FecetTransferencia> listaDocTransferir) {

        for (FecetTransferencia fecetTransferenciaPropuesta : listaDocTransferir) {
            if (fecetTransferenciaPropuesta.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        for (FecetTransferencia fecetTransferenciaPropuesta : listaTransferenciaPropuesta) {
            if (fecetTransferenciaPropuesta.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        return false;
    }

    public boolean validaNombreArchivoRechazo(List<FecetRechazoPropuesta> listaRechazoPropuesta, String nombreArchivo,
            List<FecetRechazoPropuesta> listaDocumentosTablaRechazo) {

        for (FecetRechazoPropuesta fecetRechazoPropuesta : listaDocumentosTablaRechazo) {
            if (fecetRechazoPropuesta.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        for (FecetRechazoPropuesta fecetRechazoPropuesta : listaRechazoPropuesta) {
            if (fecetRechazoPropuesta.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        return false;
    }

    public boolean validaNombreArchivoRetroalimentar(List<FecetRetroalimentacion> listaRetroPropuesta,
            String nombreArchivo, List<FecetRetroalimentacion> listaDocRetroalimentar) {

        for (FecetRetroalimentacion fecetRetroPropuesta : listaDocRetroalimentar) {
            if (fecetRetroPropuesta.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        for (FecetRetroalimentacion fecetRetroPropuesta : listaRetroPropuesta) {
            if (fecetRetroPropuesta.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        return false;
    }

    public Boolean validaArchivoOrden(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {

            if ((validaTamanoArchivo(archivo))) {
                return true;
            }
        } else {
            addErrorMessage(null, "Archivo invalido",
                    "Solo se aceptan archivos WORD versi\u00f3n 2007 o superior");
        }

        return false;
    }

    public Boolean validaArchivoCarga(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {

            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            addErrorMessage(null, "Archivo invalido",
                    "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior");
        }

        return false;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= Constantes.FILE_SIZE) {
            return true;
        } else {
            if (archivo.getSize() >= Constantes.FILE_SIZE) {
                addErrorMessage(null, "Error al cargar el archivo.",
                        "El archivo es demasiado grande, lo m\u00e1ximo permitido son 4MB.");
            } else {
                addErrorMessage(null, "Error al cargar el archivo.", "El archivo es demasiado chico");
            }
        }

        return false;
    }

    public String validaFechaComite(BigDecimal idArace, Date fechaInforme, Date fechaPresentacion) {

        String tituloEtiqueta = "";

        if (idArace.longValue() == TipoAraceEnum.ACAOCE.getId()
                || idArace.longValue() == TipoAraceEnum.ACOECE.getId()) {

            if (fechaInforme != null) {
                tituloEtiqueta = "Fecha de Informe a Comit\u00e9";
            }

            if (fechaPresentacion != null) {
                tituloEtiqueta = "Fecha de Presentaci\u00f3n a Comit\u00e9";
            }
        } else {
            if (fechaInforme != null) {
                tituloEtiqueta = "Fecha de Informe a Comit\u00e9 Regional";
            }

            if (fechaPresentacion != null) {
                tituloEtiqueta = "Fecha de Presentaci\u00f3n a Comit\u00e9 Regional";
            }
        }

        return tituloEtiqueta;
    }

    public boolean validaNombrePapelTrabajo(String nombreArchivo, List<PapelesTrabajo> listaDocPapeles) {

        for (PapelesTrabajo papelesTrabajo : listaDocPapeles) {
            if (papelesTrabajo.getNombreArchivo().equals(nombreArchivo)) {
                return true;
            }
        }

        return false;
    }

    public boolean validaActualizarOrden(FecetPropuesta propuesta,
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {

        List<AgaceOrden> ordenAsociada = consultarPropuestasAsignadasService
                .getOrdenByIdPropuesta(propuesta.getIdPropuesta());

        if (ordenAsociada != null && !ordenAsociada.isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean isOrdenActualizable(int estatusDocumento) {

        return (estatusDocumento != 1);
    }

    public boolean permiteCargarOficios(BigDecimal idMetodo,
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {

        if (consultarPropuestasAsignadasService.metodoPermiteOficio(idMetodo)) {
            return true;
        }

        return false;
    }

    public String construyeHeaderPorEstatus(BigDecimal estatusPropuesta1, BigDecimal estatusPropuesta2) {
        String titulo = "";

        if (estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_AUDITOR.getId()) {
            titulo = "Asignadas por Firmante";
        } else if (estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR.getId()
                || estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_PENDIENTE_VALIDACION.getId()) {

            titulo = "En validaci\u00f3n de Firmante";
        } else if (estatusPropuesta1.longValue() == TipoEstatusEnum.NO_APROBADA_LA_CANCELACION.getId()
                || estatusPropuesta1.longValue() == TipoEstatusEnum.CANCELACION_PENDIENTE_DE_VALIDACION.getId()) {

            titulo = "Canceladas";
        } else if (estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO
                .getId()
                || estatusPropuesta1.longValue() == TipoEstatusEnum.FIRMANTE_TRANSFERENCIA_NO_APROBADA.getId()) {
            titulo = "Transferidas";
        } else if (estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_NO_APROBADO_EL_RECHAZO.getId()
                || estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION.getId()) {
            titulo = "Rechazadas";
        } else if (estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_RETROALIMENTADA.getId()
                || estatusPropuesta2.longValue() == TipoEstatusEnum.NO_APROBADA_LA_RETROALIMENTACION.getId()) {
            titulo = "Retroalimentadas";
        } else if (estatusPropuesta1.longValue() == TipoEstatusEnum.RETROALIMENTACION_PENDIENTE_DE_VALIDACION.getId()
                || estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION.getId()) {
            titulo = "Retroalimentadas";
        } else if (estatusPropuesta1
                .longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO
                .getId()
                || estatusPropuesta1.longValue() == TipoEstatusEnum.PROPUESTA_ENVIADA_PARA_VERIFICACION_PROCEDENCIA
                .getId()
                || estatusPropuesta2
                .longValue() == TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_SUBADMINISTRADOR_EMISION_ODENES
                .getId()) {
            titulo = "En validaci\u00f3n de emisi\u00f3n de \u00f3rdenes";
        } else {
            titulo = "Concluidas";
        }

        return titulo;
    }

    public boolean validaNombreOficio(List<FecetOficio> listaOficios, BigDecimal tipoOficio) {

        for (FecetOficio oficio : listaOficios) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(tipoOficio)) {
                return true;
            }
        }
        return false;
    }
}
