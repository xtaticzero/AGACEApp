package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.io.File;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseCompulsaTercero;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTercero;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;


public interface CompulsaContribuyenteService {

    File generaAcuseRecivoPDF(final AcuseCompulsaTercero acuse) throws NegocioException;

    BigDecimal guardarDocumentoCompulsaTercero(final FecetDocTercero documento);
}
