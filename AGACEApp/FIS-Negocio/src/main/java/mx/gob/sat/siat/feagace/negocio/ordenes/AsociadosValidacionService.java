package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public interface AsociadosValidacionService {
    
    boolean validaVistaApoderadoLegal(BigDecimal idTipoAsociado);
    
    boolean validaVistaRepresentanteLegal(BigDecimal idTipoAsociado);
    
    boolean validaVistaApoderadoLegalRL(BigDecimal idTipoAsociado);
    
    boolean validaVistaAgenteAduanual(BigDecimal idTipoAsociado);
    
    boolean validaOrdenMetodo(AgaceOrden orden);
    
    boolean validaMetodoProrrogaOrden(AgaceOrden orden);
    
    boolean validaProrrogaEstatus(List<FecetProrrogaOrden> prorrogas, AgaceOrden orden);
    
    boolean validaDocResProrroga(FecetProrrogaOrden prorroga);
    
    boolean validaDocResPruebasPericiales(FecetPruebasPericiales pruebasPericiales);
    
    boolean validaPruebasPericialesEstatus(List<FecetPruebasPericiales> pruebasPericiales, AgaceOrden orden);
    
    boolean validaMetodoPruebasPericiales(AgaceOrden orden);

}
