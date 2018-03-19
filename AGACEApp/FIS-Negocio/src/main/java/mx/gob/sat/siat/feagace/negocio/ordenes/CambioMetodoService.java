package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodoPk;

public interface CambioMetodoService {

    FecetCambioMetodoPk guardarCambioMetodo(FecetCambioMetodo dto);

    void actualizarCambioMetodo(FecetCambioMetodo dto);

    void eliminarCambioMetodo(FecetCambioMetodoPk pk);

    FecetCambioMetodo getCambioMetodoId(BigDecimal idCambioMetodo);

    List<FecetCambioMetodo> getListaCambioMetodo();

}
