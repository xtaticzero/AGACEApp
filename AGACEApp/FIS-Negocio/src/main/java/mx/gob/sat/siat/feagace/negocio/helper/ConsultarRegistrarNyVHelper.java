package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.FileHelperBase;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.ActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.CopiarDocumentosService;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.DocumentoGeneradoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoDocumentoVO;

@Component
public class ConsultarRegistrarNyVHelper extends FileHelperBase {

    private static final long serialVersionUID = 1L;
    private static final int BUFFER_SIZE_1024 = 1024;

    @Value("${service.nyv.activo}")
    private boolean activoNyv;

    private static final String NUMERO_REFERENCIA = "%s-%s";
    private static final String NUMERO_REFERENCIA_ORIGEN = "%s";
    private static final int TAMANIO_MAX_REFERENCIA = 33;

    private final SimpleDateFormat formatYMDHMS = new SimpleDateFormat("yyMMddHHmmsss");

    public Map<Long, ActoAdministrativo> llenarListaActoAdministrativo(List<ActoAdministrativoVO> respuestaConsulta) {
        Map<Long, ActoAdministrativo> listaActoAdministrativoDocumento = new HashMap<Long, ActoAdministrativo>();
        if (respuestaConsulta != null && !respuestaConsulta.isEmpty()) {
            for (ActoAdministrativoVO actoAdministrativoVO : respuestaConsulta) {
                listaActoAdministrativoDocumento.put(actoAdministrativoVO.getId(),
                        fillActoAdministrativoVO(actoAdministrativoVO));
            }
        }
        return listaActoAdministrativoDocumento;
    }

    public boolean validaFechasNyV(ResponseConsultaVO respConsulta, FecetDetalleNyV detalle) {
        Date fechaNotificacionEfectiva = respConsulta.getFechaNotificacionEfectiva();
        Date fechaNotificacion = respConsulta.getFechaNotificacion();
        Date fechaEmail = respConsulta.getFechaEmail();
        boolean validas = false;
        if (fechaNotificacionEfectiva != null) {
            detalle.setFecSurteEfectosNyV(fechaNotificacionEfectiva);
            validas = true;
        }
        if (fechaNotificacion != null) {
            detalle.setFecNotificacionNyV(fechaNotificacion);
        }
        if (fechaEmail != null) {
            detalle.setFecNotificacionContNyV(fechaEmail);
        }
        if (respConsulta.getUrlAcuseNotificacion() != null) {
            detalle.setRutaAcuseNyv(respConsulta.getUrlAcuseNotificacion());
        }
        return validas;
    }

    /**
     * Pasar este metodo a un helper y cambiarle el nombre por el amor de dios!
     *
     * @param notificable
     * @return
     */
    public RegistroActoAdministrativo fillActoAdministrativo(NotificableNyV notificable, Date fechaDiligencia) {
        RegistroActoAdministrativo registroVO = new RegistroActoAdministrativo();
        if (notificable == null || notificable.getDatosNotificable() == null) {
            throw new IllegalArgumentException("Error al obtener los valores para realizar el registro en NYV");
        }
        DatosNotificable datos = notificable.getDatosNotificable();
        if (activoNyv) {
            registroVO.setClaveUnidadAdmin(datos.getClaveUnidadAdmin());
            registroVO.setNumeroEmpleado(datos.getNumeroEmpleado());
        } else {
            registroVO.setClaveUnidadAdmin("110");
            registroVO.setNumeroEmpleado("00000138847");
        }
        registroVO.setClaveActoAdmin(datos.getIdActoAdmon());
        registroVO.setProcesoOrigen(datos.getDescActoAdmon());
        String referenciaOriginal = datos.getNumeroReferencia();
        if (referenciaOriginal.indexOf('-') != -1) {
            referenciaOriginal = referenciaOriginal.split("-")[0];
            referenciaOriginal += formatYMDHMS.format(new Date());
        }

        String referencia = String.format(NUMERO_REFERENCIA, datos.getFececActosAdm().getPrefijoReferencia(), referenciaOriginal);
        if (referencia.length() > TAMANIO_MAX_REFERENCIA) {
            registroVO.setNumeroReferencia(referencia.substring(0, TAMANIO_MAX_REFERENCIA));
        } else {
            registroVO.setNumeroReferencia(referencia);
        }

        String referenciaOrigen = String.format(NUMERO_REFERENCIA_ORIGEN, datos.getNumeroReferencia());
        if (referenciaOrigen.length() > TAMANIO_MAX_REFERENCIA) {
            registroVO.setNumeroReferenciaOrigen(referenciaOrigen.substring(0, TAMANIO_MAX_REFERENCIA));
        } else {
            registroVO.setNumeroReferenciaOrigen(referenciaOrigen);
        }
        registroVO.setRfcDestinatario(datos.getRfcContribuyente());

        if (!datos.getDocumentosNotificables().isEmpty()) {
            DocumentoGeneradoVO doctoRegistro = null;
            Calendar fechaDocto = Calendar.getInstance();
            Calendar fechaFIEL = Calendar.getInstance();
            registroVO.setDocumentos(new ArrayList<DocumentoGeneradoVO>());
            for (DocumentoNotificable documento : datos.getDocumentosNotificables()) {
                fechaDocto.setTime(documento.getFechaDocumento());
                fechaFIEL.setTime(documento.getFechaVigenciaFiel());
                doctoRegistro = new DocumentoGeneradoVO();
                doctoRegistro.setCadenaOriginal(documento.getCadenaOriginal());
                doctoRegistro.setEstatusDocumento(documento.getEstatusDocumento());
                doctoRegistro.setFechaDocumento((fechaDocto.getTime()));
                doctoRegistro.setFechaVigenciaFiel((fechaFIEL.getTime()));
                doctoRegistro.setFirma(documento.getFirma());
                doctoRegistro.setNombreFirmante(documento.getNombreFirmante());
                doctoRegistro.setPuestoFirmante(documento.getPuestoFirmante());
                doctoRegistro.setRfcFirmante(documento.getRfcFirmante());
                doctoRegistro.setTipoDocumento(documento.getTipoDocumento());
                doctoRegistro.setCveDocumentoTipo(documento.getCveTipoDocumento());
                doctoRegistro.setUrlDocumento(documento.getRutaDocumento());
                if (!activoNyv) {
                    validaExistencia(doctoRegistro.getUrlDocumento());
                }
                registroVO.getDocumentos().add(doctoRegistro);
            }
        }
        return registroVO;
    }

    public boolean comparaFechas(Date fecha) {
        if (fecha != null) {
            Calendar cal = Calendar.getInstance();
            return DateUtils.isSameDay(fecha, cal.getTime()) || fecha.before(cal.getTime());
        }
        return false;
    }

    public ActoAdministrativo fillActoAdministrativoVO(ActoAdministrativoVO registro) {
        ActoAdministrativo resultado = new ActoAdministrativo();
        resultado.setId(registro.getId());
        resultado.setDescripcion(registro.getDescripcion());
        resultado.setNombre(registro.getNombre());
        resultado.setPrefijoReferencia(registro.getPrefijoReferencia());
        List<DocumentoActoAdministrativo> documentos = new ArrayList<DocumentoActoAdministrativo>();
        for (TipoDocumentoVO tipoDocumento : registro.getDocumento()) {
            documentos.add(fillDocumentoActoAdministrativo(tipoDocumento));
        }
        resultado.setDocumento(documentos);
        return resultado;
    }

    private DocumentoActoAdministrativo fillDocumentoActoAdministrativo(TipoDocumentoVO tipoDocumento) {
        DocumentoActoAdministrativo documentoActoAdministrativo = new DocumentoActoAdministrativo();
        documentoActoAdministrativo.setCveDocumentoNyv(tipoDocumento.getId());
        documentoActoAdministrativo.setTipoDocumento(tipoDocumento.getTipoDocumento());
        documentoActoAdministrativo.setBlnResolucion(tipoDocumento.isResolucion());
        return documentoActoAdministrativo;
    }

    private void validaExistencia(String urlDocumento) {
        File archivo = new File(urlDocumento);
        if (!archivo.exists() && urlDocumento.contains(Constantes.DIRECTORIO_ANEXOS_ACTO_ADM)) {
            File anexo = new File(Constantes.DIRECTORIO_ANEXOS_ACTO_ADM.concat("anexo.pdf"));
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(anexo);
                out = new FileOutputStream(archivo);
                byte[] buf = new byte[BUFFER_SIZE_1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch (IOException e) {
                logger.error("Error al copiar archivo", e);
            } finally {
                if (in != null) {
                    IOUtils.closeQuietly(in);
                }
                if (out != null) {
                    IOUtils.closeQuietly(out);
                }
            }
        }
    }

    public void copiarAnexosDocumentos(CopiarDocumentosService copiarDocumentosService, NotificableNyV notificable, FececActosAdm actoBD, List<String> lstRutas, String rutaBaseDocumentos) {

        for (DocumentoNotificable docNotificable : notificable.getDatosNotificable().getDocumentosNotificables()) {
            for (DocumentoActoAdministrativo documentoActo : actoBD.getDocumentosActo()) {
                if (docNotificable.getRutaDocumento().contains(String.valueOf(documentoActo.getTipoDocumento()))) {
                    String urlOrigen = rutaBaseDocumentos + documentoActo.getTipoDocumento() + ".pdf";
                    String urlDestino = docNotificable.getRutaDocumento();
                    copiarDocumentosService.copiarAnexos(urlOrigen, urlDestino);
                    lstRutas.add(urlDestino);
                }
            }
        }
    }

    public void crearArchivoTempNyv(String rutaBaseDocumento, String nombreDoc, String tipoActoNyv, String id, String referencia) {
        String numeroReferencia = tipoActoNyv + ":" + id + ":" + referencia;

        if (cearArchivo(numeroReferencia, rutaBaseDocumento, nombreDoc)) {
            logger.debug("Se creó archivo temporal correctamente");
        } else {
            logger.error("Error al crear el archivo temporal");
        }

    }

    public List<String> leerArchivoTemporal(String rfcFirmante) {
        try {
            return leerArchivo(Constantes.DIRECTORIO_TEMPORAL_NYV, rfcFirmante.concat(".txt"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return new ArrayList<String>();
    }

    public void eliminarArchivoTemporal(String rfcFirmante) {
        try {
            eliminarArchivo(Constantes.DIRECTORIO_TEMPORAL_NYV, rfcFirmante.concat(".txt"));
        } catch (Exception e) {
            logger.error(e);
        }

    }

}
