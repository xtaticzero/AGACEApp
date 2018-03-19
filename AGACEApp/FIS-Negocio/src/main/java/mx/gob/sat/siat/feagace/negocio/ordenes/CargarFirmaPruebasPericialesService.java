package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public interface CargarFirmaPruebasPericialesService {

    List<FecetPruebasPericiales> getPruebasPericialesContadorDocs(AgaceOrden orden);

    void enviarCorreoPruebasPericiales(FecetPruebasPericiales pruebaPericial, String remitente, String tipo);
    
   

}
