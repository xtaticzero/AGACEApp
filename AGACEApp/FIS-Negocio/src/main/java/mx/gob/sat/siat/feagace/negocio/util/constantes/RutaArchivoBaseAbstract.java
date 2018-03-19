/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.math.BigDecimal;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesArchivosUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class RutaArchivoBaseAbstract {
    protected static final String RUTA_FLUJOS_PRORROGA = "flujosProrroga/";
    protected static final String FLUJO_PRORROGA_STRING = "flujoProrroga_";
    protected static final String RUTA_FLUJOS_PRUEBA_PERICIAL = "flujosPruebaPericial/";
    protected static final String FLUJO_PRUEBA_PERICIAL = "flujoPruebaPericial_";
    protected static final String RUTA_RESOLUCION_PRORROGA = "resolucion";
    protected static final String AUDITOR_DIAGONAL = "auditor/";
    protected static final String ANEXOS_RECHAZADA_DIAGONAL = "anexosRechazada/";
    
    
    public static String armarRutaDestinoOrden(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDocOrden(final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(orden.getIdRegistroPropuesta()).append(DIAGONAL);
        rutaDestino.append(orden.getFecetContribuyente().getRfc()).append(DIAGONAL);
        rutaDestino.append(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE_DOCUMENTO_ORDEN);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoTransferirPropuesta(final FecetPropuesta propuesta,
            final BigDecimal idRechazo) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc()).append(DIAGONAL).append("TransferenciasPropuesta").append(DIAGONAL).append("Transferencia_").append(idRechazo).append(DIAGONAL);
        return rutaDestino.toString();
    }

    public static String armarRutaDestinoRetroalimentarPropuesta(final FecetPropuesta propuesta,
            final BigDecimal idRetroalimentacion) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc());
        rutaDestino.append(DIAGONAL).append("RetroalimentacionesPropuesta").append(DIAGONAL).append("Retroalimentacion_").append(idRetroalimentacion).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoInsumo(final FecetInsumo insumo) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_INSUMOS_PROPUESTAS_FEAGACE);

        if ("SIN FOLIO".equals(insumo.getIdRegistro())) {
            rutaDestino.append(insumo.getIdInsumo()).append(DIAGONAL).append(insumo.getFecetContribuyente().getRfc()).append(DIAGONAL);
        } else {
            rutaDestino.append(insumo.getIdRegistro()).append(DIAGONAL).append(insumo.getFecetContribuyente().getRfc()).append(DIAGONAL);
        }

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoInsumoJustificacion(final FecetInsumo insumo) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_INSUMOS_PROPUESTAS_FEAGACE);

        if ("SIN FOLIO".equals(insumo.getIdRegistro())) {
            rutaDestino.append(insumo.getIdInsumo()).append(DIAGONAL).append(insumo.getFecetContribuyente().getRfc()).append(DIAGONAL).append("Justificacion").append(DIAGONAL);
        } else {
            rutaDestino.append(insumo.getIdRegistro()).append(DIAGONAL).append(insumo.getFecetContribuyente().getRfc()).append(DIAGONAL).append("Justificacion").append(DIAGONAL);
        }

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPropuesta(final FecetPropuesta propuesta) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc()).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinorRechazoPropuesta(final FecetPropuesta propuesta,
            final BigDecimal idRechazo) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc());
        rutaDestino.append(DIAGONAL).append("RechazosPropuesta").append(DIAGONAL).append("Rechazo_").append(idRechazo).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinorPendientePropuesta(final FecetPropuesta propuesta,
            final BigDecimal idPendiente) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc());
        rutaDestino.append(DIAGONAL).append("PendientePropuesta").append(DIAGONAL).append("Pendiente_").append(idPendiente).append(DIAGONAL);

        return rutaDestino.toString();
    }
    
    public static String armarRutaDestinoPropuesta(final FecetPropuesta propuesta,
            final BigDecimal idRechazo) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc());
        rutaDestino.append(DIAGONAL).append("RechazosPropuesta").append(DIAGONAL).append("Rechazo_").append(idRechazo).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinorRechazoRetroalimientacion(final FecetPropuesta propuesta,
            final BigDecimal idRetroalimentacion) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);

        rutaDestino.append(propuesta.getIdRegistro()).append(DIAGONAL).append(propuesta.getFecetContribuyente().getRfc());
        rutaDestino.append(DIAGONAL).append("RechazosRetroalimentacion").append(DIAGONAL);
        rutaDestino.append("Retroalimentacion_").append(idRetroalimentacion).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPromocion(final AgaceOrden orden, final BigDecimal idPromocion) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(armarRutaDestinoOrden(orden));
        rutaDestino.append("Promociones").append(DIAGONAL).append(ConstantesArchivosUtil.CARPETA_PROMOCION).append(idPromocion).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPromocionOficio(final FecetOficio oficio,
            final BigDecimal idPromocionOficio) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(oficio.getRutaArchivo().replace(oficio.getNombreArchivo(), ""));
        rutaDestino.append("Promociones").append(DIAGONAL).append(ConstantesArchivosUtil.CARPETA_PROMOCION_OFICIO).append(idPromocionOficio).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPromocionOficio(final String rutaArchivo,
            final BigDecimal idPromocionOficio) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(rutaArchivo);
        rutaDestino.append("Promociones").append(DIAGONAL).append(ConstantesArchivosUtil.CARPETA_PROMOCION_OFICIO).append(idPromocionOficio).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoProrroga(final BigDecimal idProrroga, final AgaceOrden orden) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(armarRutaDestinoOrden(orden));
        rutaDestino.append(ConstantesArchivosUtil.CARPETA_PRORROGA).append(DIAGONAL).append(ConstantesArchivosUtil.CARPETA_PRORRO_ID).append(idProrroga).append(DIAGONAL);
        return rutaDestino.toString();
    }

    public static String armarRutaDestinoProrrogaOficio(final FecetOficio oficio, final BigDecimal idProrrogaOficio) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(oficio.getRutaArchivo().replace(oficio.getNombreArchivo(), ""));
        rutaDestino.append(ConstantesArchivosUtil.CARPETA_PRORROGA);
        rutaDestino.append(DIAGONAL);
        rutaDestino.append(ConstantesArchivosUtil.CARPETA_PRORRO_ID).append(idProrrogaOficio).append(DIAGONAL);

        return rutaDestino.toString();
    }

    public static String armarRutaDestinoPruebasAlegatos(final FecetPromocion promocion) {
        StringBuilder rutaDestino = new StringBuilder();

        rutaDestino.append(promocion.getRutaArchivo().replace(promocion.getNombreArchivo(), ""));
        rutaDestino.append(ConstantesArchivosUtil.CARPETA_PRUEBAS_ALEGATOS).append(DIAGONAL);

        return rutaDestino.toString();
    }
}
