package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.negocio.common.CommonServices;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;

@Component(value = "commonServices")
public class CommonServicesImpl extends BaseBusinessServices implements CommonServices {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private StreamedContent getDescargaArchivoCommon(final String path, final String nombreDescarga) throws NegocioException {
        StreamedContent archivo = null;
        try {
            archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtil.limpiarPathArchivo(path)),
                    CargaArchivoUtil.obtenContentTypeArchivo(nombreDescarga), CargaArchivoUtil.aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            throw new NegocioException("No se encontr\u00f3 el documento seleccionado", e);
        }
        return archivo;
    }

    @Override
    public StreamedContent getDescargaArchivo(String path, String nombreDescarga) throws NegocioException {
        return getDescargaArchivoCommon(path, nombreDescarga);
    }

    @Override
    @PistaAuditoria
    public StreamedContent getDescargaFormatoCarga(String path, String nombreDescarga) throws NegocioException {
        return getDescargaArchivoCommon(path, nombreDescarga);
    }

    @PistaAuditoria
    public StreamedContent getDescargaFormatoCargaCarta(final String path, final String nombreDescarga) throws NegocioException {
        return getDescargaArchivoCommon(path, nombreDescarga);
    }

    @Override
    @PistaAuditoria
    public StreamedContent getDescargaFormatoCargaInsumos(String path, String nombreDescarga) throws NegocioException {
        return getDescargaArchivoCommon(path, nombreDescarga);
    }

}
