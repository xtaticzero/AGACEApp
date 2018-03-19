/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;
import static mx.gob.sat.siat.feagace.negocio.ordenes.constants.FirmadoOrdenesConstants.EXTENSION_ARCHIVO_ORDEN_PDF;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.util.IOUtils;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.Plantillas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseCompulsaTercero;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ArchivoAgaceUtil {
    


    protected ArchivoAgaceUtil(){
    }

    private static final Logger LOG = Logger.getLogger(ArchivoAgaceUtil.class);

    private static final String ARCHIVO_REPORTES = Propiedades.get("jasper.ubicacion.reportes");
    private static final String ARCHIVO_ACUSE = "acuse.jasper";
    private static final String ARCHIVO_ACUSE_PRORROGA = "acuseProrroga.jasper";
    private static final String ARCHIVO_ACUSE_COMPULSA_TERCERO = "acuseCompulsaTercero.jasper";
    private static final String REPORTES = "REPORTES";
    private static final String CADENA_ORIGINAL = "Cadena Original: ";
    private static final String FIRMA = "Firma : ";
    private static final String SELLO_DIGITAL = "Sello Digital : ";
    private static final String ERROR = "Error: ";
    private static final int INT_QUINIENTOSNOVENTA = 590;
    private static final int INT_SESENTA = 60;
    private static final int INT_DOSCIENTOSTREINTA = 230;
    private static final int INT_SETENTA = 70;
    private static final int INT_DIEZ = 10;
    private static final int INT_CUATRO = 4;
    private static final String ARCHIVO_ACUSE_PRUEBAS_PERICIALES = "acusePruebaPericial.jasper";
    private static final String RUTA_IMAGENES = "RUTA_IMAGENES";
    private static final String JASPER_UBICACION_ACUSE_IMAGENES = "jasper.ubicacion.acuse.imagenes";

    public static String getNombreArchivoOrden(final AgaceOrden orden) {
        return (orden.getNumeroOrden().replace(DIAGONAL, "")).concat(ConstantesArchivosUtil.EXTENSION_ARCHIVO_ORDEN);
    }

    public static String getNombreArchivoOrdenPdf(final AgaceOrden orden) {
        return orden.getNumeroOrden().replace(DIAGONAL, "") + EXTENSION_ARCHIVO_ORDEN_PDF;
    }
    
   

    public static String procesaAlPdf(final AgaceOrden orden, String cadena, String firma,
            String selloDigital) throws IOException, DocumentException, DocumentoException {
        String rutaDestino = RutaArchivosUtil.armarRutaDestinoOrden(orden);
        String rutaOrden = rutaDestino + getNombreArchivoOrden(orden);
        // TODO: Descomentar esto cuando ya se este en el SAT
        File archivoPDF = conviertePDF(rutaOrden);
        procesaPDf(archivoPDF.getAbsolutePath(), cadena, firma, selloDigital);
        return archivoPDF.getAbsolutePath();

    }

    public static String procesaPdfFirmante(String rutaArchivo, String nombreArchivo, String cadena, String firma,
            String selloDigital, String numeroOrden) throws IOException,
            DocumentException,
            DocumentoException {
        File archivoPDF = conviertePDFFirmante(rutaArchivo, nombreArchivo, cadena, firma, selloDigital, numeroOrden);
        procesaPDf(archivoPDF.getAbsolutePath(), cadena, firma, selloDigital);
        return rutaArchivo.concat(archivoPDF.getName());
    }

    public static String procesaAlPdf(String rutaArchivo, String cadena, String firma,
            String selloDigital) throws IOException, DocumentException, DocumentoException {
        File archivoPDF = conviertePDF(rutaArchivo);
        return archivoPDF.getAbsolutePath();
    }

    public static String procesaAlPdf(final AgaceOrden orden, String cadena, String firma) throws IOException,
            DocumentException,
            //SelladoraException,
            DocumentoException {
        String rutaDestino = RutaArchivosUtil.armarRutaDestinoOrden(orden);
        String rutaOrden = rutaDestino + getNombreArchivoOrden(orden);
        //TODO: Descomentar esto cuando ya se este en el SAT
        File archivoPDF = conviertePDF(rutaOrden);
        procesaPDf(archivoPDF.getAbsolutePath(), cadena, firma);
        return archivoPDF.getAbsolutePath();

    }

    public static byte[] getArregloReportePdf(AcuseRevisionElectronica acuse) {
        byte[] reporte = null;
        Map<String, Object> parametros = new HashMap<String, Object>();

        List<AcuseRevisionElectronica> acuses = new ArrayList<AcuseRevisionElectronica>();
        acuses.add(acuse);

        try {
            parametros.put(RUTA_IMAGENES, Propiedades.get(JASPER_UBICACION_ACUSE_IMAGENES));
            parametros.put(REPORTES, ARCHIVO_REPORTES);
            File archivoReporte = new File(ARCHIVO_REPORTES + ARCHIVO_ACUSE);
            reporte
                    = JasperRunManager.runReportToPdf(archivoReporte.getPath(), parametros, new JRBeanCollectionDataSource(acuses));
        } catch (NoClassDefFoundError e) {
            LOG.error(ERROR + e);
        } catch (JRException e) {
            LOG.error(ERROR + e);
        } catch (Exception e) {
            LOG.error(ERROR + e);
        }
        return reporte;
    }

    public static byte[] getArregloReporteProrrogaPdf(AcuseRevisionElectronica acuse) {
        byte[] reporte = null;
        Map<String, Object> parametros = new HashMap<String, Object>();

        List<AcuseRevisionElectronica> acuses = new ArrayList<AcuseRevisionElectronica>();
        acuses.add(acuse);

        try {
            parametros.put(RUTA_IMAGENES, Propiedades.get(JASPER_UBICACION_ACUSE_IMAGENES));
            parametros.put(REPORTES, ARCHIVO_REPORTES);
            File archivoReporte = new File(ARCHIVO_REPORTES + ARCHIVO_ACUSE_PRORROGA);
            reporte
                    = JasperRunManager.runReportToPdf(archivoReporte.getPath(), parametros, new JRBeanCollectionDataSource(acuses));
        } catch (NoClassDefFoundError e) {
            LOG.error(ERROR + e);
        } catch (JRException e) {
            LOG.error(ERROR + e);
        } catch (Exception e) {
            LOG.error(ERROR + e);
        }
        return reporte;
    }
    
    public static byte[] getArregloReportePruebasPericialesPdf(AcuseRevisionElectronica acuse) {
        byte[] reporte = null;
        Map<String, Object> parametros = new HashMap<String, Object>();

        List<AcuseRevisionElectronica> acuses = new ArrayList<AcuseRevisionElectronica>();
        acuses.add(acuse);

        try {
            parametros.put(RUTA_IMAGENES, Propiedades.get(JASPER_UBICACION_ACUSE_IMAGENES));
            parametros.put(REPORTES, ARCHIVO_REPORTES);
            File archivoReporte = new File(ARCHIVO_REPORTES + ARCHIVO_ACUSE_PRUEBAS_PERICIALES);
            reporte
                    = JasperRunManager.runReportToPdf(archivoReporte.getPath(), parametros, new JRBeanCollectionDataSource(acuses));
        } catch (NoClassDefFoundError e) {
            LOG.error(ERROR + e);
        } catch (JRException e) {
            LOG.error(ERROR + e);
        } catch (Exception e) {
            LOG.error(ERROR + e);
        }
        return reporte;
    }

    public static byte[] getArregloAcuseCompulsaTercero(final AcuseCompulsaTercero acuse) {
        byte[] reporte = null;
        Map<String, Object> parametros = new HashMap<String, Object>();

        List<AcuseCompulsaTercero> acuses = new ArrayList<AcuseCompulsaTercero>();
        acuses.add(acuse);

        try {
            parametros.put(RUTA_IMAGENES, Propiedades.get(JASPER_UBICACION_ACUSE_IMAGENES));
            parametros.put(REPORTES, ARCHIVO_REPORTES);
            File archivoReporte = new File(ARCHIVO_REPORTES + ARCHIVO_ACUSE_COMPULSA_TERCERO);
            reporte
                    = JasperRunManager.runReportToPdf(archivoReporte.getPath(), parametros, new JRBeanCollectionDataSource(acuses));
        } catch (NoClassDefFoundError e) {
            LOG.error(ERROR + e);
        } catch (JRException e) {
            LOG.error(ERROR + e);
        } catch (Exception e) {
            LOG.error(ERROR + e);
        }
        return reporte;
    }

    public static String fileToBase64(File archivo) throws IOException {
        byte[] archivoByte = IOUtils.toByteArray(new FileInputStream(archivo));
        return Base64.encode(archivoByte);
    }

    public static byte[] fileToByte(File archivo) {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[ConstantesArchivosUtil.CUATRO_KILOS];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(archivo);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } catch (FileNotFoundException e) {
            LOG.error("El archivo " + archivo.getName() + " no existe");
        } catch (IOException e) {
            LOG.error("Error al convertir el archivo a un arreglo de bytes");
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e) {
                LOG.error("Error al cerrar el objeto de ByteArrayOutputStream");
            }

            try {
                if (ios != null) {
                    ios.close();
                }
            } catch (IOException e) {
                LOG.error("Error al cerrar el objeto FileInputStream");
            }
        }
        return ous.toByteArray();
    }

    public static File conviertePDF(String rutaWord) {
        StringBuilder archivoPDF = new StringBuilder();
        File filePDF = null;
        if (rutaWord != null) {
            try {
                Plantillas doc = new Plantillas();
                File archivoDoc = new File(rutaWord);
                String archivo64 = fileToBase64(archivoDoc);
                doc.setArchivoContenido(archivo64);

                archivoPDF.append(doc.getArchivoContenido());

                byte[] stringDoc = Base64.decode(archivoPDF.toString());
                String ruta = archivoDoc.getCanonicalPath();
                String rutaPDF
                        = new StringBuffer(ruta.substring(0, ruta.length() - INT_CUATRO)).append("pdf").toString();

                filePDF = byteToFile(rutaPDF, stringDoc);
            } catch (Exception e) {
                LOG.error(ConstantesError.ERROR_PLANTILLADOR + e.getCause(), e);
            }
        }
        return filePDF;
    }

    public static File conviertePDFFirmante(String rutaWord, String nombreArchivo, String cadena, String firma,
            String selloDigital, String numeroOrden) {
        StringBuilder archivoPDF = new StringBuilder();
        File filePDF = null;
        if (rutaWord != null) {
            try {

                String rutaPDF
                        = new StringBuffer(rutaWord).append(numeroOrden).append(EXTENSION_ARCHIVO_ORDEN_PDF).toString();
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaPDF));
                document.open();
                archivoPDF.append(new StringBuilder().append(CADENA_ORIGINAL).append(cadena).append(ConstantesArchivosUtil.SALTO_DE_LINEA));
                archivoPDF.append(new StringBuilder().append(FIRMA).append(firma).append(ConstantesArchivosUtil.SALTO_DE_LINEA));
                // TODO: Descomentar esta parte al estar en el SAT
                archivoPDF.append(new StringBuilder().append(SELLO_DIGITAL).append(selloDigital).append(ConstantesArchivosUtil.SALTO_DE_LINEA));
                document.add(new Paragraph(archivoPDF.toString()));
                document.close();
                writer.close();
                filePDF = new File(rutaPDF);
            } catch (Exception e) {
                LOG.error("Erro al crear el archivo pdf" + e.getCause(), e);
            }
        }
        return filePDF;
    }

    public static File convierteDocPdf(String rutaWord, String nombreArchivo, String cadena, String firma,
            String selloDigital, String numeroOrden) {
        StringBuilder archivoPDF = new StringBuilder();
        File filePDF = null;
        if (rutaWord != null) {
            try {
                File archivoDocx = new File(rutaWord + nombreArchivo);

                FileInputStream fis = new FileInputStream(archivoDocx);
                InputStream doc = fis;
                if (!nombreArchivo.contains(".docx") || !nombreArchivo.contains(".pdf")) {
                    String contenido = leerDoc(doc);
                    archivoPDF.append(contenido).append(ConstantesArchivosUtil.SALTO_DE_LINEA);
                }
                String rutaPDF
                        = new StringBuffer(rutaWord).append(nombreArchivo.substring(0, nombreArchivo.length() - INT_CUATRO)).append(EXTENSION_ARCHIVO_ORDEN_PDF).toString();
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaPDF));
                document.open();

                archivoPDF.append(new StringBuilder().append(CADENA_ORIGINAL).append(cadena).append(ConstantesArchivosUtil.SALTO_DE_LINEA));
                archivoPDF.append(new StringBuilder().append(FIRMA).append(firma).append(ConstantesArchivosUtil.SALTO_DE_LINEA));
                // TODO: Descomentar esta parte al estar en el SAT
                archivoPDF.append(new StringBuilder().append(SELLO_DIGITAL).append(selloDigital).append(ConstantesArchivosUtil.SALTO_DE_LINEA));
                document.add(new Paragraph(archivoPDF.toString()));
                document.close();
                writer.close();
                filePDF = new File(rutaPDF);
            } catch (Exception e) {
                LOG.error("Erro al crear el archivo pdf" + e.getCause(), e);
            }
        }
        return filePDF;
    }

    public static String leerDoc(InputStream doc) {
        String contenidoDocumento = "";
        WordExtractor we = null;
        try {
            we = new WordExtractor(doc);
            contenidoDocumento = we.getText();
        } catch (IOException e) {
            LOG.error("Error al leer contenido de archivo", e);
        } finally {
            if (we != null) {
                try {
                    we.close();
                } catch (IOException e) {
                    LOG.error("Error al liberar el registro.");
                }
            }
        }
        return contenidoDocumento;
    }

    public static File byteToFile(String ruta, byte[] archivo) {
        File file = new File(ruta);
        FileOutputStream fos = null;
        try {

            if (!file.exists()) {
                boolean check = file.createNewFile();
                if (check) {
                    LOG.info("FILE OK");
                }
            }
            fos = new FileOutputStream(file.getAbsolutePath());
            fos.flush();
            fos.write(archivo);

        } catch (FileNotFoundException ex) {
            LOG.error(ConstantesError.ERROR_ENCONTRAR_ARCHIVO, ex);
        } catch (IOException ex) {
            LOG.error(ConstantesError.ERROR_GENERAR_PDF, ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                LOG.error(ConstantesError.ERROR_GENERAR_PDF, ex);
            }
        }
        return file;
    }

    private static void procesaPDf(String rutaOrden, String cadena, String firma,
            String selloDigital) throws DocumentException, IOException, DocumentoException {
        StringBuilder imprimir = new StringBuilder();
        File fileRutaOrden = new File(rutaOrden);
        if (fileRutaOrden.canRead()) {
            PdfReader reader = new PdfReader(fileRutaOrden.getAbsolutePath());
            File archivoTemporal
                    = new File(fileRutaOrden.getAbsolutePath().replace(EXTENSION_ARCHIVO_ORDEN_PDF, "temp.pdf"));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(archivoTemporal));
            PdfContentByte over = stamper.getOverContent(reader.getNumberOfPages());

            imprimir.append(CADENA_ORIGINAL).append(cadena).append(ConstantesArchivosUtil.SALTO_DE_LINEA);
            imprimir.append(FIRMA).append(firma).append(ConstantesArchivosUtil.SALTO_DE_LINEA);
            // TODO: Descomentar esta parte al estar en el SAT
            imprimir.append(SELLO_DIGITAL).append(selloDigital).append(ConstantesArchivosUtil.SALTO_DE_LINEA);

            Font font = FontFactory.getFont("Times-Roman");
            font.setSize(INT_DIEZ);

            Phrase texto = new Phrase(imprimir.toString(), font);
            ColumnText columnText = new ColumnText(over);
            columnText.setSimpleColumn(INT_SESENTA, INT_DOSCIENTOSTREINTA, INT_QUINIENTOSNOVENTA, INT_SETENTA);
            columnText.setText(texto);
            columnText.go();
            stamper.close();
            reader.close();

            if (fileRutaOrden.delete()) {
                boolean check = archivoTemporal.renameTo(fileRutaOrden.getAbsoluteFile());
                if (check) {
                    LOG.info("RENAME OK");
                }
            } else {
                throw new DocumentoException(ConstantesError.ERROR_CONVERSION_ARCHIVO);
            }
        }
    }

    private static void procesaPDf(String rutaOrden, String cadena, String firma) throws DocumentException,
            IOException, DocumentoException {
        StringBuilder imprimir = new StringBuilder();
        File fileRutaOrden = new File(rutaOrden);
        PdfReader reader = new PdfReader(fileRutaOrden.getAbsolutePath());
        File archivoTemporal
                = new File(fileRutaOrden.getAbsolutePath().replace(EXTENSION_ARCHIVO_ORDEN_PDF, "temp.pdf"));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(archivoTemporal));
        PdfContentByte over = stamper.getOverContent(reader.getNumberOfPages());
        imprimir.append(CADENA_ORIGINAL).append(cadena).append(ConstantesArchivosUtil.SALTO_DE_LINEA);
        imprimir.append(FIRMA).append(firma).append(ConstantesArchivosUtil.SALTO_DE_LINEA);
        //TODO: Descomentar esta parte al estar en el SAT
        String respueta = "x";
        imprimir.append(SELLO_DIGITAL).append(respueta).append(ConstantesArchivosUtil.SALTO_DE_LINEA);

        Font font = FontFactory.getFont("Times-Roman");
        font.setSize(INT_DIEZ);

        Phrase texto = new Phrase(imprimir.toString(), font);
        ColumnText columnText = new ColumnText(over);
        columnText.setSimpleColumn(INT_SESENTA, INT_DOSCIENTOSTREINTA, INT_QUINIENTOSNOVENTA, INT_SETENTA);
        columnText.setText(texto);
        columnText.go();
        stamper.close();
        reader.close();
        if (fileRutaOrden.delete()) {
            boolean check = archivoTemporal.renameTo(fileRutaOrden.getAbsoluteFile());
            if (check) {
                LOG.info("RENAME OK");
            }
        } else {
            throw new DocumentoException(ConstantesError.ERROR_CONVERSION_ARCHIVO);
        }
    }
}
