package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public interface CargaFirmaPruebasPericialesService {
    
    List<FecetPruebasPericiales> getPruebasPericialesContadorDocs(AgaceOrden orden);

}
