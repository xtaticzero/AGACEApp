/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesArchivosUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class RutaArchivosUtil extends RutaArchivoBaseAbstract {

    public static String armarRutaDestinoPruebasAlegatosOficio(final FecetPromocionOficio promocionOficio) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(promocionOficio.getRutaArchivo().replace(promocionOficio.getNombreArchivo(), ""));
        rutaDestino.append(ConstantesArchivosUtil.CARPETA_PRUEBAS_ALEGATOS_OFICIO).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoResolucionDefinitiva(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/resolucionDefinitiva/");

        return rutaDestino.toString();
    }

    public static String armarRutaDestino(final AgaceOrden orden, String tipoRequerimento) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);
        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/").append(tipoRequerimento).append(DIAGONAL);
        return rutaDestino.toString();
    }

    public static String armarRutaAnexosOficio(final String pathOficio) {
        StringBuilder rutaDestino = new StringBuilder(pathOficio);

        rutaDestino.append("Anexos/");

        return rutaDestino.toString();
    }

    public static String armarRutaDestino2aCartaInvitacion(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/2aUnicaCartaInvitacion/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoMedidaApremio(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/medidaApremio/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestino2aCartaInvitacionMasiva(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/2aCartaInvitacionMasiva/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoConclusionUCAMCA(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/Conclusion/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCambioMetodoUCAMCA(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/cambioMetodo/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoSegundoRequerimiento(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/SegundoRequerimiento/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoMulta(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/Multa/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoBajaPadron(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/bajaPadron/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoResolucionDefinitiva(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/resolucionDefinitiva/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPruebasPericiales(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/pruebasPericiales/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoAvisoContribuyente(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/avisoContribuyente/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPruebasDesahogo(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/pruebasDesahogo/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoConclusionRevision(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/conclusionRevision/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCompulsaInternacional(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/compulsaInternacional/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCancelacionOrden(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/cancelacionOrden/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoSinObservaciones(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/conclusionSinObservaciones/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoConObservaciones(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/observacionesRevision/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoRequerimientoReincidencia(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/requerimientoReincidencia/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCambioMetodo(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/cambioMetodo/");

        return rutaDestino.toString();
    }

    public static String armarRutaDestinorRechazoAnexoOficio(final FecetOficio oficio,
            final BigDecimal idRechazoOficio) {
        StringBuilder rutaDestino = new StringBuilder(oficio.getRutaArchivo()).append("/oficioRechazado/");

        rutaDestino.append(oficio.getIdOficio().toString().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoOficioFirmado(final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(oficio.getRutaArchivo());
        rutaDestino.append(oficio.getNombreArchivo());

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoSegundoRequerimiento(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/segundoRequerimiento/");

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCompulsaTerceros(final AgaceOrden orden, final String cveCompulsa) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/compulsaTerceros/");
        rutaDestino.append(cveCompulsa.replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoAvisoContribuyenteCompTercero(final AgaceOrden orden,
            final String cveCompulsa) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/compulsaTerceros/");
        rutaDestino.append(cveCompulsa.replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("avisoContribuyente/");

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoAvisoContribuyenteCompInternacional(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/compulsaInternacional/avisoContribuyente/");

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoAvisoAutoridades(final AgaceOrden orden, final String cveCompulsa) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/compulsaTerceros/");
        rutaDestino.append(cveCompulsa.replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("avisoAutoridades/");

        return rutaDestino.toString();
    }

    public static String generarRutaArchivoValidaPropuestas(RutaArchivosEnum rutaBase, FecetPropuesta propuesta,
            String... directorios) {

        int numeroSubCarpetas = 0;

        StringBuilder rutaArchivoValida = new StringBuilder(rutaBase.getPathFile());

        rutaArchivoValida.append(propuesta.getFecetContribuyente().getRfc());

        rutaArchivoValida.append(DIAGONAL);
        rutaArchivoValida.append(propuesta.getIdRegistro());

        rutaArchivoValida.append(DIAGONAL);
        rutaArchivoValida.append(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
        rutaArchivoValida.append(DIAGONAL);

        for (String subDirectorio : directorios) {
            numeroSubCarpetas++;
            rutaArchivoValida.append(DIAGONAL);
            rutaArchivoValida.append(subDirectorio);
        }

        if (numeroSubCarpetas > 0) {
            rutaArchivoValida.append(DIAGONAL);
        }

        return rutaArchivoValida.toString();

    }

    public static String generarRutaArchivoValida(RutaArchivosEnum rutaBase,
            String... directorios) {

        int numeroSubCarpetas = 0;

        StringBuilder rutaArchivoValida = new StringBuilder(rutaBase.getPathFile());

        for (String subDirectorio : directorios) {
            if (numeroSubCarpetas == 0) {
                rutaArchivoValida.append(subDirectorio);
                numeroSubCarpetas++;
            } else {
                numeroSubCarpetas++;
                rutaArchivoValida.append(DIAGONAL);
                rutaArchivoValida.append(subDirectorio);
            }
        }

        if (numeroSubCarpetas > 0) {
            rutaArchivoValida.append(DIAGONAL);
        }

        return rutaArchivoValida.toString();

    }

    public static String armarRutaAnexosProrrogaAprobadaAuditor(final String pathProrroga,
            final BigDecimal idFlujoProrroga) {
        StringBuilder rutaDestino = new StringBuilder(pathProrroga);

        rutaDestino.append(RUTA_FLUJOS_PRORROGA);
        rutaDestino.append(FLUJO_PRORROGA_STRING).append(idFlujoProrroga).append(DIAGONAL);
        rutaDestino.append(AUDITOR_DIAGONAL);
        rutaDestino.append("anexosAprobada/");

        return rutaDestino.toString();
    }

    public static String armarRutaAnexosPruebaPericialAprobadaAuditor(final String pathPruebaPericial,
            final BigDecimal idFlujoPruebaPericial) {
        StringBuilder rutaDestino = new StringBuilder(pathPruebaPericial);

        rutaDestino.append(RUTA_FLUJOS_PRUEBA_PERICIAL);
        rutaDestino.append(FLUJO_PRUEBA_PERICIAL).append(idFlujoPruebaPericial).append(DIAGONAL);
        rutaDestino.append(AUDITOR_DIAGONAL);
        rutaDestino.append("anexosAprobada/");

        return rutaDestino.toString();
    }

    public static String armarRutaResolucionProrrogaAuditor(final String pathProrroga,
            final BigDecimal idFlujoProrroga) {
        StringBuilder rutaDestino = new StringBuilder(pathProrroga);
        rutaDestino.append(RUTA_RESOLUCION_PRORROGA).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaResolucionPruebaPericialAuditor(final String pathPruebaPericial,
            final BigDecimal idFlujoPruebaPericial) {
        StringBuilder rutaDestino = new StringBuilder(pathPruebaPericial);
        rutaDestino.append(RUTA_RESOLUCION_PRORROGA).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaAnexosProrrogaRechazadaAuditor(final String pathProrroga,
            final BigDecimal idFlujoProrroga) {
        StringBuilder rutaDestino = new StringBuilder(pathProrroga);

        rutaDestino.append(RUTA_FLUJOS_PRORROGA);
        rutaDestino.append(FLUJO_PRORROGA_STRING).append(idFlujoProrroga).append(DIAGONAL);
        rutaDestino.append(AUDITOR_DIAGONAL);
        rutaDestino.append(ANEXOS_RECHAZADA_DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaAnexosPruebasPericialesRechazadaAuditor(final String pathPruebasPericiales,
            final BigDecimal idFlujoPruebasPericiales) {
        StringBuilder rutaDestino = new StringBuilder(pathPruebasPericiales);

        rutaDestino.append(RUTA_FLUJOS_PRUEBA_PERICIAL);
        rutaDestino.append(FLUJO_PRUEBA_PERICIAL).append(idFlujoPruebasPericiales).append(DIAGONAL);
        rutaDestino.append(AUDITOR_DIAGONAL);
        rutaDestino.append(ANEXOS_RECHAZADA_DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaAnexosProrrogaAprobadaFirmante(final String pathProrroga,
            final BigDecimal idFlujoProrroga) {
        StringBuilder rutaDestino = new StringBuilder(pathProrroga);

        rutaDestino.append(RUTA_FLUJOS_PRORROGA);
        rutaDestino.append(FLUJO_PRORROGA_STRING).append(idFlujoProrroga).append(DIAGONAL);
        rutaDestino.append("firmante/");
        rutaDestino.append("anexosAprobada/");

        return rutaDestino.toString();
    }

    public static String armarRutaAnexosProrrogaRechazadaFirmante(final String pathProrroga,
            final BigDecimal idFlujoProrroga) {
        StringBuilder rutaDestino = new StringBuilder(pathProrroga);

        rutaDestino.append(RUTA_FLUJOS_PRORROGA);
        rutaDestino.append(FLUJO_PRORROGA_STRING).append(idFlujoProrroga).append(DIAGONAL);
        rutaDestino.append("firmante/");
        rutaDestino.append(ANEXOS_RECHAZADA_DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaAnexosPruebaPericialRechazadaFirmante(final String pathPruebaPericial,
            final BigDecimal idFlujoPruebaPericial) {
        StringBuilder rutaDestino = new StringBuilder(pathPruebaPericial);

        rutaDestino.append(RUTA_FLUJOS_PRUEBA_PERICIAL);
        rutaDestino.append(FLUJO_PRORROGA_STRING).append(idFlujoPruebaPericial).append(DIAGONAL);
        rutaDestino.append("firmante/");
        rutaDestino.append(ANEXOS_RECHAZADA_DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoCompulsaTercero(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/compulsaTercero/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoOtrasAutoridades(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/otrasAutoridades/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPruebasPericiales(final BigDecimal idPruebaPericial, final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(armarRutaDestinoOrden(orden));
        rutaDestino.append(ConstantesArchivosUtil.CARPETA_PRUEBAS_PERICIALES).append(DIAGONAL).append(ConstantesArchivosUtil.CARPETA_PRUEBA_PERI_ID).append(idPruebaPericial).append(DIAGONAL);
        return rutaDestino.toString();
    }

}
