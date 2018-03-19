package mx.gob.sat.siat.feagace.vista.insumos.validar.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public class ValidarProcedenciaInsumoAbstractDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1945562749504893164L;

    private FecetPropuesta propuesta;
    private FecetInsumo insumoSeleccionado;
    private FecetDocExpediente documentoSeleccionado;
    private FecetDocExpediente documentoSeleccionadoPropuesta;
    private ImpuestoVO impuestoVO;
    private ImpuestoVO impuestoSeleccionado;
    private FecetDocretroinsumo docretroInsumoSeleccionado;
    private FecetDocrechazoinsumo docrechazoInsumoSeleccionado;
    private FecetContadorInsumos documentoSeleccionadoRetro;
    private FecetDocretroinsumo docretroInsumoCargadoSeleccionado;
    private EmpleadoDTO empleadoDTO;
    private FecetRetroalimentacionInsumo retroalimentacionInsumo;
    
    private List<FecetDocrechazoinsumo> listaDocRechazoInsumo;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadoCargado;
    
    public void setDocumentoSeleccionadoPropuesta(FecetDocExpediente documentoSeleccionadoPropuesta) {
        this.documentoSeleccionadoPropuesta = documentoSeleccionadoPropuesta;
    }

    public FecetDocExpediente getDocumentoSeleccionadoPropuesta() {
        return documentoSeleccionadoPropuesta;
    }
    
    public void setDocrechazoInsumoSeleccionado(FecetDocrechazoinsumo docrechazoInsumoSeleccionado) {
        this.docrechazoInsumoSeleccionado = docrechazoInsumoSeleccionado;
    }

    public FecetDocrechazoinsumo getDocrechazoInsumoSeleccionado() {
        return docrechazoInsumoSeleccionado;
    }

    public void setImpuestoVO(ImpuestoVO impuestoVO) {
        this.impuestoVO = impuestoVO;
    }

    public ImpuestoVO getImpuestoVO() {
        return impuestoVO;
    }

    public void setImpuestoSeleccionado(ImpuestoVO impuestoSeleccionado) {
        this.impuestoSeleccionado = impuestoSeleccionado;
    }

    public ImpuestoVO getImpuestoSeleccionado() {
        return impuestoSeleccionado;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setInsumoSeleccionado(FecetInsumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public FecetInsumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setDocretroInsumoSeleccionado(FecetDocretroinsumo docretroInsumoSeleccionado) {
        this.docretroInsumoSeleccionado = docretroInsumoSeleccionado;
    }

    public FecetDocretroinsumo getDocretroInsumoSeleccionado() {
        return docretroInsumoSeleccionado;
    }
    
    public void setDocumentoSeleccionadoRetro(FecetContadorInsumos documentoSeleccionadoRetro) {
        this.documentoSeleccionadoRetro = documentoSeleccionadoRetro;
    }

    public FecetContadorInsumos getDocumentoSeleccionadoRetro() {
        return documentoSeleccionadoRetro;
    }
    
    public void setDocretroInsumoCargadoSeleccionado(FecetDocretroinsumo docretroInsumoCargadoSeleccionado) {
        this.docretroInsumoCargadoSeleccionado = docretroInsumoCargadoSeleccionado;
    }

    public FecetDocretroinsumo getDocretroInsumoCargadoSeleccionado() {
        return docretroInsumoCargadoSeleccionado;
    }

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }
    
    public FecetRetroalimentacionInsumo getRetroalimentacionInsumo() {
        return retroalimentacionInsumo;
    }

    public void setRetroalimentacionInsumo(FecetRetroalimentacionInsumo retroalimentacionInsumo) {
        this.retroalimentacionInsumo = retroalimentacionInsumo;
    }
    
    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadoCargado() {
        return insumosRetroalimentadoCargado;
    }

    public void setInsumosRetroalimentadoCargado(List<FecetRetroalimentacionInsumo> insumosRetroalimentadoCargado) {
        this.insumosRetroalimentadoCargado = insumosRetroalimentadoCargado;
    }
    
    public void setListaDocRechazoInsumo(List<FecetDocrechazoinsumo> listaDocRechazoInsumo) {
        this.listaDocRechazoInsumo = listaDocRechazoInsumo;
    }

    public List<FecetDocrechazoinsumo> getListaDocRechazoInsumo() {
        return listaDocRechazoInsumo;
    }
    
    public void setInsumosRetroalimentadosContador(List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador) {
        this.insumosRetroalimentadosContador = insumosRetroalimentadosContador;
    }

    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosContador() {
        return insumosRetroalimentadosContador;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }
}
