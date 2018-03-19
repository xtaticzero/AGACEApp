package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;

public interface DetalleOrdenService {

    AgaceOrden obtieneIdOrden(BigDecimal idOrden);

    FecetContribuyente obtieneContribuyentes(BigDecimal idContribuyente);

    List<FececMetodo> obtieneMetodo(BigDecimal idMetodo);
    
    List<FecetAsociado> obtieneColaborador(BigDecimal idTipoAsociado, BigDecimal idOrden);
}
