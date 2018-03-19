package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;

public interface ContribuyenteService {

    FecetContribuyente getContribuyenteIDC(final String rfcContribuyente) throws NoExisteContribuyenteException;

    FecetContribuyente obtenerContribuyente(BigDecimal idContribuyente) throws NoExisteContribuyenteException;
}
