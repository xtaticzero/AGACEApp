package mx.gob.sat.siat.feagace.negocio.util.constantes;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.CONTENT_TYPE_WORD;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTercero;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContAudit;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesArchivosUtil;

public abstract class CargaArchivoUtil extends ArchivoAgaceUtil {

    private static final Logger LOG = Logger.getLogger(CargaArchivoUtil.class);

    public static void guardarArchivoAnexoProrrogaOrden(final FecetAnexosProrrogaOrden anexo) throws IOException {
        if (anexo.getArchivo() != null) {
            crearDestino(anexo.getRutaArchivo());
            StringBuilder rutaAnexo = new StringBuilder();
            rutaAnexo.append(anexo.getRutaArchivo());
            rutaAnexo.append(anexo.getNombreArchivo());
            guardarArchivo(anexo.getArchivo(), rutaAnexo.toString());
        }
    }
    
    public static void guardarArchivoCifras(final FecetDocCifraDTO documento) throws IOException {
            String rutaArchivo = documento.getRutaArchivo() +
                    documento.getNombre();
            crearDestino(documento.getRutaArchivo());
            guardarArchivo(documento.getDocumento(), rutaArchivo);        
    }

    public static void guardarArchivoAnexoPruebasPericiales(final FecetAnexoPruebasPericiales anexo) throws IOException {
        if (anexo.getArchivo() != null) {
            crearDestino(anexo.getRutaArchivo());
            StringBuilder rutaAnexo = new StringBuilder();
            rutaAnexo.append(anexo.getRutaArchivo());
            rutaAnexo.append(anexo.getNombreArchivo());
            guardarArchivo(anexo.getArchivo(), rutaAnexo.toString());
        }
    }

    public static void guardarArchivoAnexoProrrogaOficio(final FecetAnexosProrrogaOficio anexo) throws IOException {
        if (anexo.getArchivo() != null) {
            crearDestino(anexo.getRutaArchivo());
            StringBuilder rutaAnexo = new StringBuilder();
            rutaAnexo.append(anexo.getRutaArchivo());
            rutaAnexo.append(anexo.getNombreArchivo());
            guardarArchivo(anexo.getArchivo(), rutaAnexo.toString());
        }
    }

    public static void guardarArchivoOrden(final AgaceOrden orden) throws IOException {
        if (orden.getArchivoOrden() != null) {
            String rutaDestino = RutaArchivosUtil.armarRutaDestinoOrden(orden);
            crearDestino(rutaDestino);
            String rutaOrden = rutaDestino + getNombreArchivoOrden(orden);
            guardarArchivo(orden.getArchivoOrden(), rutaOrden);
        }
    }

    public static void subirArchivoPromocionServidor(FecetPromocion promocion) throws IOException {
        String rutaDestino = promocion.getRutaArchivo() + promocion.getNombreArchivo();

        if (promocion.getArchivo() != null) {
            if (!verificarExistenciaDirectorio(promocion.getRutaArchivo())) {
                crearDestino(promocion.getRutaArchivo());
            }

            guardarArchivo(promocion.getArchivo(), rutaDestino);
        }
    }

    public static boolean verificarExistenciaDirectorio(final String rutaDestino) {
        boolean existeCarpeta = false;

        File carpetaPromocion = new File(rutaDestino);
        if (carpetaPromocion.isDirectory()) {
            existeCarpeta = true;
        }
        return existeCarpeta;
    }

    public static void crearRutaDestino(final String destino) {
        File folder = new File(destino);
        boolean check = folder.mkdirs();
        if (check) {
            LOG.info("MKDIR OK");
        }
    }

    private static void crearDestino(final String destino) {
        File folder = new File(destino);
        boolean check = folder.mkdirs();
        if (check) {
            LOG.info("MKDIR OK");
        }
    }

    private static void guardarArchivo(InputStream origen, String destino) throws IOException {
        OutputStream salida = null;
        final int numByte = 1024;
        byte[] buffer = new byte[numByte];
        int len;
        try {
            salida = new FileOutputStream(destino);
            while ((len = origen.read(buffer)) > 0) {
                salida.write(buffer, 0, len);
            }
        } finally {
            try {
                if (salida != null) {
                    salida.close();
                }
                if (origen != null) {
                    origen.close();
                }
            } catch (IOException e) {
                LOG.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
            }

        }
    }

    public static boolean verificarExistenciaCveOrden(final AgaceOrden orden) {
        File folder = new File(RutaArchivosUtil.armarRutaDestinoOrden(orden));

        return folder.exists();
    }

    public static boolean verificarExistenciaPromocion(final AgaceOrden orden) {
        StringBuilder ruta = new StringBuilder();
        ruta.append(RutaArchivosUtil.armarRutaDestinoOrden(orden));

        File folder = new File(ruta.toString());

        return folder.exists();
    }

    public static boolean borrarArchivoCargado(final AgaceOrden orden) {
        String rutaDestino = RutaArchivosUtil.armarRutaDestinoOrden(orden);
        File folder = new File(rutaDestino);

        if (folder.exists()) {
            File[] files = folder.listFiles();
            boolean check = false;

            for (File archivo : files) {
                check = archivo.delete();
            }
            if (check) {
                LOG.info("DELETE OK");
            }

            return folder.delete();
        }

        return false;
    }

    public static String obtenContentTypeArchivo(final String nombreArchivo) {
        String contentType = null;

        if (nombreArchivo.endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
            contentType = CONTENT_TYPE_WORD;
        } else if (nombreArchivo.endsWith(Constantes.ARCHIVO_PDF)) {
            contentType = ConstantesArchivosUtil.CONTENT_TYPE_PDF;
        }

        return contentType;
    }

    public static String obtenContentTypeArchivoPropuesta(final String nombreArchivo) {
        String contentType = null;

        if (nombreArchivo.endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
            contentType = CONTENT_TYPE_WORD;
        } else if (nombreArchivo.endsWith(Constantes.ARCHIVO_PDF)) {
            contentType = ConstantesArchivosUtil.CONTENT_TYPE_PDF;
        } else if (nombreArchivo.endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {
            contentType = ConstantesArchivosUtil.CONTENT_TYPE_EXCEL;
        }

        return contentType;
    }

    public static String limpiarPathArchivo(String path) {
        String fileName;
        int idx = path.lastIndexOf('\\');
        if (idx >= 0) {
            fileName = path.substring(idx + 1);
        } else {
            fileName = path;
        }

        return fileName;
    }

    public static boolean verificarExistenciaOficio(final FecetOficio oficio) {
        File folder = new File(RutaArchivosUtil.armarRutaDestinoOficioFirmado(oficio));
        return folder.exists();
    }

    public static String getNombreArchivoOficio(final FecetOficio oficio) {
        String nombreArchivo = null;

        nombreArchivo = (oficio.getIdOficio().toString().replace(DIAGONAL, "")).concat(ConstantesArchivosUtil.EXTENSION_ARCHIVO_ORDEN);

        return nombreArchivo;
    }

    public static void guardarArchivoOficio(final FecetOficio oficio) throws IOException {
        if (oficio.getArchivo() != null) {
            String path = oficio.getRutaArchivo().replace(oficio.getNombreArchivo(), "");
            crearDestino(path);
            String rutaOficio = path + oficio.getNombreArchivo();
            guardarArchivo(oficio.getArchivo(), rutaOficio);

        }
    }

    public static void guardarArchivoOficioFirmado(final FecetOficio oficio) throws IOException {
        if (oficio.getArchivo() != null) {
            String path = oficio.getRutaArchivo().concat("/oficioFirmado/").concat(oficio.getIdOficio().toString().toUpperCase()).concat(DIAGONAL);
            crearDestino(path);
            String rutaOficio = path + oficio.getNombreArchivo();
            guardarArchivo(oficio.getArchivo(), rutaOficio);
        }
    }

    public static String procesaAlPdf(final FecetOficio oficio, String cadena, String firma) throws IOException, DocumentException, DocumentoException {
        String rutaDestino = RutaArchivosUtil.armarRutaDestinoOficioFirmado(oficio);
        LOG.info(rutaDestino);
        File archivoPDF = conviertePDF(rutaDestino);
        LOG.info(archivoPDF.exists());
        return archivoPDF.getName();

    }

    public static String aplicarCodificacionTexto(final String cadena) {
        String resultado;
        try {

            byte ptext[] = cadena.getBytes(ConstantesArchivosUtil.UTF_8);
            resultado = new String(ptext, ConstantesArchivosUtil.UTF_8);

        } catch (Exception e) {
            LOG.error("Error al codificar el texto", e);
            return cadena;
        }

        return resultado;
    }

    public static void guardarArchivoOficioRechazado(final FecetOficio oficio) throws IOException {
        if (oficio.getArchivo() != null) {
            String path = oficio.getRutaArchivo().concat("/oficioRechazado/").concat(oficio.getIdOficio().toString().toUpperCase()).concat(DIAGONAL);
            crearDestino(path);
            String rutaOficio = path + oficio.getNombreArchivo();

            guardarArchivo(oficio.getArchivo(), rutaOficio);
        }
    }

    public static void guardarArchivoAnexoOficioRechazado(final String rutaOficio, final DocumentoVO documentoCreado) throws IOException {
        if (documentoCreado != null) {
            String path = rutaOficio.concat("/oficioAnexoRechazado/");
            crearDestino(path);
            String pathOficio = path.concat(documentoCreado.getNombreArchivo());
            guardarArchivo(documentoCreado.getInputStream(), pathOficio);
        }
    }

    public static void guardarArchivoOficioAnexo(final FecetOficioAnexos anexo) throws IOException {
        if (anexo.getArchivo() != null) {
            String path = anexo.getRutaArchivo().replace(anexo.getNombreArchivo(), "");
            crearDestino(path);
            String rutaAnexo = path + anexo.getNombreArchivo();

            guardarArchivo(anexo.getArchivo(), rutaAnexo);
        }
    }

    public static void guardarArchivoAvisoContribuyente(final FecetContAudit avisoContribuyente) throws IOException {
        if (avisoContribuyente.getArchivo() != null) {
            String path = avisoContribuyente.getRutaArchivo().replace(avisoContribuyente.getNombreArchivo(), "");
            crearDestino(path);
            String rutaAvisoContribuyente = path + avisoContribuyente.getNombreArchivo();

            guardarArchivo(avisoContribuyente.getArchivo(), rutaAvisoContribuyente);
        }
    }

    public static void guardarArchivo(InputStream archivo, String rutaArchivo, String nombreArchivo) throws IOException {
        if (archivo != null) {
            String path = rutaArchivo.replace(nombreArchivo, "");
            crearDestino(path);
            String rutaRequerimiento = path + nombreArchivo;
            guardarArchivo(archivo, rutaRequerimiento);
        }
    }

    public static void guardarArchivoPromocion(final FecetPromocion promocion) throws IOException {
        if (promocion.getArchivo() != null) {
            String path = promocion.getRutaArchivo().replace(promocion.getNombreArchivo(), "");
            crearDestino(path);
            guardarArchivo(promocion.getArchivo(), promocion.getRutaArchivo());
        }
    }

    public static void guardarArchivoPromocionOficio(final FecetPromocionOficio promocionOficio) throws IOException {
        if (promocionOficio.getArchivo() != null) {
            String path = promocionOficio.getRutaArchivo().replace(promocionOficio.getNombreArchivo(), "");
            crearDestino(path);
            guardarArchivo(promocionOficio.getArchivo(), promocionOficio.getRutaArchivo());
        }
    }

    public static void guardarArchivoProrroga(final FecetProrrogaOrden prorroga) throws IOException {

        if (prorroga.getArchivoResolucion() != null) {
            String path = prorroga.getRutaResolucion().replace(prorroga.getNombreResolucion(), "");
            crearDestino(path);

            guardarArchivo(prorroga.getArchivoResolucion(), prorroga.getRutaResolucion());
        }
    }

    public static void guardarArchivoProrroga(final FecetDocProrrogaOrden prorroga) throws IOException {
        if (prorroga.getArchivo() != null) {
            String path = prorroga.getRutaArchivo().replace(prorroga.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(prorroga.getArchivo(), path.concat(prorroga.getNombreArchivo()));
        }
    }

    public static void guardarArchivoProrrogaOficio(final FecetDocProrrogaOficio prorroga) throws IOException {
        if (prorroga.getArchivo() != null) {
            String path = prorroga.getRutaArchivo().replace(prorroga.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(prorroga.getArchivo(), path.concat(prorroga.getNombreArchivo()));
        }
    }

    public static void guardarArchivoPruebasAlegatos(final FecetAlegato pruebaAlegato) throws IOException {
        if (pruebaAlegato.getArchivo() != null) {
            String path = pruebaAlegato.getRutaArchivo().replace(pruebaAlegato.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(pruebaAlegato.getArchivo(), pruebaAlegato.getRutaArchivo() + pruebaAlegato.getNombreArchivo());
        }
    }

    public static void guardarArchivoPruebasAlegatosOficio(final FecetAlegatoOficio pruebaAlegatoOficio) throws IOException {
        if (pruebaAlegatoOficio.getArchivo() != null) {
            String path = pruebaAlegatoOficio.getRutaArchivo().replace(pruebaAlegatoOficio.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(pruebaAlegatoOficio.getArchivo(), pruebaAlegatoOficio.getRutaArchivo());
        }
    }

    public static void guardarDocumentoCompulsaTercero(final FecetDocTercero documento) throws IOException {
        if (documento.getArchivo() != null) {
            String path = documento.getRutaArchivo().replace(documento.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(documento.getArchivo(), documento.getRutaArchivo());
        }
    }

    public static void guardarArchivoPropuestaRechazada(final FecetRechazoPropuesta propuestaRechazada) throws IOException {

        if (propuestaRechazada.getArchivo() != null) {
            String path = propuestaRechazada.getRutaArchivo().replace(propuestaRechazada.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(propuestaRechazada.getArchivo(), propuestaRechazada.getRutaArchivo());
        }
    }

    public static void guardarArchivoPropuestaPendiente(final FecetPropPendiente pecetPropPendiente) throws IOException {

        if (pecetPropPendiente.getArchivo() != null) {
            String path = pecetPropPendiente.getRutaArchivo().replace(pecetPropPendiente.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(pecetPropPendiente.getArchivo(), pecetPropPendiente.getRutaArchivo());
        }
    }

    public static void guardarArchivoTransferirPropuesta(final FecetTransferencia transferirPropuesta) throws IOException {

        if (transferirPropuesta.getArchivo() != null) {
            String path = transferirPropuesta.getRutaArchivo().replace(transferirPropuesta.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(transferirPropuesta.getArchivo(), transferirPropuesta.getRutaArchivo());
        }
    }

    public static void guardarArchivoRetroalimentarPropuesta(final FecetRetroalimentacion retroalimentarPropuesta) throws IOException {

        if (retroalimentarPropuesta.getArchivo() != null) {
            String path = retroalimentarPropuesta.getRutaArchivo().replace(retroalimentarPropuesta.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(retroalimentarPropuesta.getArchivo(), retroalimentarPropuesta.getRutaArchivo());
        }
    }

    public static void guardarArchivoPapelesTrabajoPropuesta(final DocumentoOrdenModel papelTrabajo) throws IOException {

        if (papelTrabajo.getInput() != null) {
            String path = papelTrabajo.getPapelesTrabajo().getRutaArchivo().replace(papelTrabajo.getPapelesTrabajo().getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(papelTrabajo.getInput(), papelTrabajo.getPapelesTrabajo().getRutaArchivo());
        }
    }

    public static void guardarArchivoRL(final FecetDocAsociado documento) throws IOException {

        if (documento.getArchivo() != null) {
            String path = documento.getRutaArchivo().replace(documento.getNombreArchivo(), "");
            crearDestino(path);
            guardarArchivo(documento.getArchivo(), documento.getRutaArchivo());
        }
    }

    public static void guardarArchivoAA(final FecetPromocionAgenteAduanal documento) throws IOException {

        if (documento.getArchivo() != null) {
            String path = documento.getRutaArchivo().replace(documento.getNombreArchivo(), "");
            crearDestino(path);
            guardarArchivo(documento.getArchivo(), documento.getRutaArchivo());
        }
    }

    public static void guardarArchivoAlegatoAA(final FecetAlegatoAgenteAduanal documento) throws IOException {

        if (documento.getArchivo() != null) {
            String path = documento.getRutaArchivo().replace(documento.getNombreArchivo(), "");
            crearDestino(path);
            guardarArchivo(documento.getArchivo(), documento.getRutaArchivo());
        }
    }

    public static void guardarArchivoCancelacion(final FecetPropCancelada cancelacionPropuesta) throws IOException {

        if (cancelacionPropuesta.getArchivo() != null) {
            String path = cancelacionPropuesta.getRutaArchivo().replace(cancelacionPropuesta.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(cancelacionPropuesta.getArchivo(), cancelacionPropuesta.getRutaArchivo());
        }
    }

    public static void duplicarDocumentoInsumoPropuesta(final FecetDocExpediente documento, final String rutaArchivo) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File inFile = new File(documento.getRutaArchivo() + documento.getNombre());
            File outFile = new File(rutaArchivo + documento.getNombre());

            in = new FileInputStream(inFile);

            if (!verificarExistenciaDirectorio(rutaArchivo)) {
                crearDestino(rutaArchivo);
            }

            out = new FileOutputStream(outFile);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            LOG.error(ConstantesError.ERROR_COPIAR_ARCHIVO);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOG.error(ConstantesError.ERROR_CERRAR_INPUTSTREAM, e);
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    LOG.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
                }
            }

        }
    }

    public static void duplicarDocumentoInsumoPropuesta(final FecetDocExpInsumo documento, final String rutaArchivo) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File inFile = new File(documento.getRutaArchivo() + documento.getNombre());
            File outFile = new File(rutaArchivo + documento.getNombre());

            in = new FileInputStream(inFile);

            if (!verificarExistenciaDirectorio(rutaArchivo)) {
                crearDestino(rutaArchivo);
            }

            out = new FileOutputStream(outFile);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            LOG.error(ConstantesError.ERROR_COPIAR_ARCHIVO);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOG.error(ConstantesError.ERROR_CERRAR_INPUTSTREAM, e);
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    LOG.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
                }
            }

        }
    }

    /**
     * @param contentType
     * @return boolean Solo se aceptan archivos tipo Word, Excel o PDF version 2007 o superior.
     */
    public static boolean validaContentType(final String contentType) {
        boolean contentTypeValido = false;
        if (contentType.equalsIgnoreCase(ConstantesArchivosUtil.CONTENT_TYPE_PDF)) {
            contentTypeValido = true;
        } else if (contentType.equalsIgnoreCase(ConstantesArchivosUtil.CONTENT_TYPE_WORD_X)) {
            contentTypeValido = true;
        } else if (contentType.equalsIgnoreCase(ConstantesArchivosUtil.CONTENT_TYPE_EXCEL_X)) {
            contentTypeValido = true;
        }
        return contentTypeValido;
    }

    public static String obtenerNombreArchivo(final String ruta) {
        String resultado = "";
        if (StringUtils.isNotBlank(ruta)) {
            if (ruta.indexOf('/') >= 0) {
                resultado = ruta.substring(ruta.lastIndexOf('/') + 1);
            } else if (ruta.indexOf('\\') >= 0) {
                resultado = ruta.substring(ruta.lastIndexOf('\\') + 1);
            } else {
                resultado = ruta;
            }

        }
        return resultado;
    }

    public static String getPathFromAbsolutePath(String pathFile) {
        String path = "";
        if (pathFile != null && !pathFile.trim().isEmpty()) {
            if (pathFile.indexOf('/') >= 0) {
                path = pathFile.substring(0, pathFile.lastIndexOf('/') + 1);
            } else if (pathFile.indexOf('\\') >= 0) {
                path = pathFile.substring(0, pathFile.lastIndexOf('\\') + 1);
            } else {
                path = pathFile;
            }

        }
        return path;
    }

    public static void guardarArchivoPruebasPericiales(final FecetDocPruebasPericiales pruebaPericial) throws IOException {
        if (pruebaPericial.getArchivo() != null) {
            String path = pruebaPericial.getRutaArchivo().replace(pruebaPericial.getNombreArchivo(), "");
            crearDestino(path);

            guardarArchivo(pruebaPericial.getArchivo(), path.concat(pruebaPericial.getNombreArchivo()));
        }
    }

}
