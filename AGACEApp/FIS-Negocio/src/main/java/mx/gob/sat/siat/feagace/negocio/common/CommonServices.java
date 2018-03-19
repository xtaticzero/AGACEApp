package mx.gob.sat.siat.feagace.negocio.common;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CommonServices {
    StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_PROPUESTA_DESCARGA_FORMATO)
    StreamedContent getDescargaFormatoCarga(final String path, final String nombreDescarga) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_CARTAS_DESCARGA_FORMATO)
    StreamedContent getDescargaFormatoCargaCarta(final String path, final String nombreDescarga) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_INSUMOS_DESCARGA_FORMATO)
    StreamedContent getDescargaFormatoCargaInsumos(final String path, final String nombreDescarga) throws NegocioException;
}
