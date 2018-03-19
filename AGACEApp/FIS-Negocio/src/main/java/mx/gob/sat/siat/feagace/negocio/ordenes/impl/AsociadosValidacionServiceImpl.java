package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosValidacionService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoOrdenAsociadoRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoProrrogasRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoPruebasPericialesRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarProrrogasEstatusRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarPruebasPericialesEstatusRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarVistaAsociadoRule;

@Service("asociadosValidacionService")
public class AsociadosValidacionServiceImpl extends BaseBusinessServices implements AsociadosValidacionService{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private transient ValidarMetodoOrdenAsociadoRule validarMetodoOrdenAsociado;
    @Autowired
    private transient ValidarVistaAsociadoRule validarVistaAsociadoRule;    
    @Autowired
    private transient ValidarMetodoProrrogasRule validarMetodoProrrogasRule;
    @Autowired
    private transient ValidarProrrogasEstatusRule validarProrrogasEstatusRule;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient ValidarMetodoPruebasPericialesRule validarMetodoPruebasPericialesRule;
    @Autowired
    private transient ValidarPruebasPericialesEstatusRule validarPruebasPericialesEstatusRule;
    
    public boolean validaVistaApoderadoLegal(BigDecimal idTipoAsociado) {
        return validarVistaAsociadoRule.validaVistaApoderadoLegal(idTipoAsociado);
    }
    
    public boolean validaVistaRepresentanteLegal(BigDecimal idTipoAsociado) {
        return validarVistaAsociadoRule.validaVistaRepresentanteLegal(idTipoAsociado);
    }

    public boolean validaVistaApoderadoLegalRL(BigDecimal idTipoAsociado) {
        return validarVistaAsociadoRule.validaVistaApoderadoLegalRL(idTipoAsociado);
    }

    public boolean validaVistaAgenteAduanual(BigDecimal idTipoAsociado) {
        return validarVistaAsociadoRule.validaVistaAgenteAduanual(idTipoAsociado);
    }
    
    public boolean validaOrdenMetodo(AgaceOrden orden) {
       return validarMetodoOrdenAsociado.validaMetodo(orden);
    }
    
    public boolean validaMetodoProrrogaOrden(AgaceOrden orden) {
        return validarMetodoProrrogasRule.validaMetodoProrrogaOrden(orden);
    }
    
    public boolean validaProrrogaEstatus(List<FecetProrrogaOrden> prorrogas, AgaceOrden orden) {
        return (validarProrrogasEstatusRule.validarGenerarProrroga(prorrogas) 
                && plazosService.validarPlazoCrearProrrogaOrden(orden));
    }
    
    public boolean validaDocResProrroga(FecetProrrogaOrden prorroga) {
        return (validarProrrogasEstatusRule.validarDocResProrroga(prorroga));           
    }
    
    public boolean validaMetodoPruebasPericiales(AgaceOrden orden) {
        return validarMetodoPruebasPericialesRule.validaMetodo(orden);            
    }
    
    public boolean validaDocResPruebasPericiales(FecetPruebasPericiales pruebasPericiales) {
        return (validarPruebasPericialesEstatusRule.validarDocResPruebasPericiales(pruebasPericiales));           
    }
    
    public boolean validaPruebasPericialesEstatus(List<FecetPruebasPericiales> pruebasPericiales, AgaceOrden orden) {
        return (validarPruebasPericialesEstatusRule.validarGenerarPruebasPericiales(pruebasPericiales));
    }

}
