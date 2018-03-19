package mx.gob.sat.siat.feagace.vista.model.insumos;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;

public class SegInsumosDTO extends SegInsumosBasicAbstract {

    private static final long serialVersionUID = 1L;

    private EmpleadoDTO fececEmpleado;
    private FecetDocrechazoinsumo docrechazoCargadoSeleccionado;
    private FecetContadorInsumos documentoSeleccionadoRetro;
    private FecetContadorInsumos documentoSeleccionadoRetroRechazo;
    private FecetContadorInsumos contadorInsumoRetroPendiente;
    private FecetContadorInsumosRechazados documentoSeleccionadoRechazo;
    private FecetDocExpInsumo docSeleccionado;
    private FecetRechazoInsumo fecetRechazoInsumoSeleaccionado;
    private FecetRetroalimentacionInsumo retroalimentacionSeleaccionada;
    private FecetDocretroinsumo docretroInsumoCargadoSeleccionado;
    private FecetDocretroinsumo docretroInsumoCargadoSeleccionadoPendiente;
    
    public void setDocrechazoCargadoSeleccionado(FecetDocrechazoinsumo docrechazoCargadoSeleccionado) {
        this.docrechazoCargadoSeleccionado = docrechazoCargadoSeleccionado;
    }

    public FecetDocrechazoinsumo getDocrechazoCargadoSeleccionado() {
        return docrechazoCargadoSeleccionado;
    }

    public void setDocumentoSeleccionadoRetro(FecetContadorInsumos documentoSeleccionadoRetro) {
        this.documentoSeleccionadoRetro = documentoSeleccionadoRetro;
    }

    public FecetContadorInsumos getDocumentoSeleccionadoRetro() {
        return documentoSeleccionadoRetro;
    }

    public void setDocumentoSeleccionadoRetroRechazo(FecetContadorInsumos documentoSeleccionadoRetroRechazo) {
        this.documentoSeleccionadoRetroRechazo = documentoSeleccionadoRetroRechazo;
    }

    public FecetContadorInsumos getDocumentoSeleccionadoRetroRechazo() {
        return documentoSeleccionadoRetroRechazo;
    }

    public void setDocumentoSeleccionadoRechazo(FecetContadorInsumosRechazados documentoSeleccionadoRechazo) {
        this.documentoSeleccionadoRechazo = documentoSeleccionadoRechazo;
    }

    public FecetContadorInsumosRechazados getDocumentoSeleccionadoRechazo() {
        return documentoSeleccionadoRechazo;
    }

    public void setDocSeleccionado(FecetDocExpInsumo docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public FecetDocExpInsumo getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setContadorInsumoRetroPendiente(FecetContadorInsumos contadorInsumoRetroPendiente) {
        this.contadorInsumoRetroPendiente = contadorInsumoRetroPendiente;
    }

    public FecetContadorInsumos getContadorInsumoRetroPendiente() {
        return contadorInsumoRetroPendiente;
    }

    public void setFecetRechazoInsumoSeleaccionado(FecetRechazoInsumo fecetRechazoInsumoSeleaccionado) {
        this.fecetRechazoInsumoSeleaccionado = fecetRechazoInsumoSeleaccionado;
    }

    public FecetRechazoInsumo getFecetRechazoInsumoSeleaccionado() {
        return fecetRechazoInsumoSeleaccionado;
    }

    public void
            setRetroalimentacionSeleaccionada(FecetRetroalimentacionInsumo retroalimentacionSeleaccionada) {
        this.retroalimentacionSeleaccionada = retroalimentacionSeleaccionada;
    }

    public FecetRetroalimentacionInsumo getRetroalimentacionSeleaccionada() {
        return retroalimentacionSeleaccionada;
    }

    public void setDocretroInsumoCargadoSeleccionado(FecetDocretroinsumo docretroInsumoCargadoSeleccionado) {
        this.docretroInsumoCargadoSeleccionado = docretroInsumoCargadoSeleccionado;
    }

    public FecetDocretroinsumo getDocretroInsumoCargadoSeleccionado() {
        return docretroInsumoCargadoSeleccionado;
    }

    public void setDocretroInsumoCargadoSeleccionadoPendiente(
            FecetDocretroinsumo docretroInsumoCargadoSeleccionadoPendiente) {
        this.docretroInsumoCargadoSeleccionadoPendiente = docretroInsumoCargadoSeleccionadoPendiente;
    }

    public FecetDocretroinsumo getDocretroInsumoCargadoSeleccionadoPendiente() {
        return docretroInsumoCargadoSeleccionadoPendiente;
    }

    public EmpleadoDTO getFececEmpleado() {
        return fececEmpleado;
    }

    public void setFececEmpleado(EmpleadoDTO fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }
}
