package mx.gob.sat.siat.feagace.vista.consulta.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOrdenConsultaDTO;

public class ReimprimirDocumentosDTOAbstractHelper implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaSeleccionada;
    private ProrrogaOficioConsultaDTO prorrogaOficioConsultaSeleccionada;
    private FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTOSeleccionada;
    private FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTOSeleccionada;
    private OficioConsultaDTO oficioConsultaSeleccionado;
    private OficioConsultaDTO oficioConsultaDependeienteSeleccionado;
    private FecetContribuyente representante;
    private FecetContribuyente contribuyente;
    private ColaboradorDocumentoDTO colaboradoresDTO;

    public FecetContribuyente getRepresentante() {
        return representante;
    }

    public void setRepresentante(FecetContribuyente representante) {
        this.representante = representante;
    }

    public FlujoProrrogaOrdenConsultaDTO getFlujoProrrogaOrdenConsultaDTOSeleccionada() {
        return flujoProrrogaOrdenConsultaDTOSeleccionada;
    }

    public void setFlujoProrrogaOrdenConsultaDTOSeleccionada(FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTOSeleccionada) {
        this.flujoProrrogaOrdenConsultaDTOSeleccionada = flujoProrrogaOrdenConsultaDTOSeleccionada;
    }

    public ProrrogaOficioConsultaDTO getProrrogaOficioConsultaSeleccionada() {
        return prorrogaOficioConsultaSeleccionada;
    }

    public void setProrrogaOficioConsultaSeleccionada(ProrrogaOficioConsultaDTO prorrogaOficioConsultaSeleccionada) {
        this.prorrogaOficioConsultaSeleccionada = prorrogaOficioConsultaSeleccionada;
    }
    
    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public ProrrogaOrdenConsultaDTO getProrrogaOrdenConsultaSeleccionada() {
        return prorrogaOrdenConsultaSeleccionada;
    }

    public void setProrrogaOrdenConsultaSeleccionada(ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaSeleccionada) {
        this.prorrogaOrdenConsultaSeleccionada = prorrogaOrdenConsultaSeleccionada;
    }

    public FlujoProrrogaOficioConsultaDTO getFlujoProrrogaOficioConsultaDTOSeleccionada() {
        return flujoProrrogaOficioConsultaDTOSeleccionada;
    }

    public void setFlujoProrrogaOficioConsultaDTOSeleccionada(FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTOSeleccionada) {
        this.flujoProrrogaOficioConsultaDTOSeleccionada = flujoProrrogaOficioConsultaDTOSeleccionada;
    }
    
    public OficioConsultaDTO getOficioConsultaDependeienteSeleccionado() {
        return oficioConsultaDependeienteSeleccionado;
    }

    public void setOficioConsultaDependeienteSeleccionado(OficioConsultaDTO oficioConsultaDependeienteSeleccionado) {
        this.oficioConsultaDependeienteSeleccionado = oficioConsultaDependeienteSeleccionado;
    }
    
    public OficioConsultaDTO getOficioConsultaSeleccionado() {
        return oficioConsultaSeleccionado;
    }

    public void setOficioConsultaSeleccionado(OficioConsultaDTO oficioConsultaSeleccionado) {
        this.oficioConsultaSeleccionado = oficioConsultaSeleccionado;
    }

    public final ColaboradorDocumentoDTO getColaboradoresDTO() {
        return colaboradoresDTO;
    }

    public final void setColaboradoresDTO(ColaboradorDocumentoDTO colaboradoresDTO) {
        this.colaboradoresDTO = colaboradoresDTO;
    }
}
