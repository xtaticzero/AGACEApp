package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;

public interface CargarFirmaProrrogaService {

    List<FecetProrrogaOrden> getProrrogaContadorDocs(AgaceOrden orden);

    List<FecetProrrogaOficio> getHistoricoProrrogaOficio(final BigDecimal idOficio);

    List<FecetDocProrrogaOficio> getDocsProrrogaOficio(final BigDecimal idOficio);

    boolean validaMetodoProrrogaOficio(AgaceOrden orden, FecetOficio oficio);

    boolean validaProrrogaEstatus(List<FecetProrrogaOficio> prorrogas, FecetOficio oficio);
    
    void enviarCorreoProrroga(FecetProrrogaOrden prorroga, String remitente, String tipo, FecetOficio oficio, FecetProrrogaOficio prorrogaOficio);
    
    boolean validaDocResProrroga(FecetProrrogaOficio prorroga);
    
    boolean validaOficioAdministrable(FecetOficio oficio);
    
    boolean validaOficioConProrrogas(FecetOficio oficio);
    
    boolean validaOficioConPlazos(FecetOficio oficio);
    
    boolean validaMetodoProrrogaOrden(AgaceOrden orden);
    
    List<FecetProrrogaOficio> getHistoricoProrrogaOficioContribuyente(final BigDecimal idOficio);
}
