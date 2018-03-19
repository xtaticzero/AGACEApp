package mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOrdenConsultaDTO;

public class DetalleOrdenUnifHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5412284457687384060L;

    private UserProfileDTO userProfile;
    private PromocionConsultaDTO promocionConsultaSeleccionada;
    private OficioConsultaDTO oficioConsultaSeleccionado;
    private ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaSeleccionada;
    private FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTOSeleccionada;
    private OficioConsultaDTO oficioConsultaDependeienteSeleccionado;
    private PromocionOficioConsultaDTO promocionOficioConsultaSeleccionada;
    private ProrrogaOficioConsultaDTO prorrogaOficioConsultaSeleccionada;
    private FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTOSeleccionada;
    private ColaboradorDocumentoDTO colaboradoresDTO;

    private FecetContribuyente contribuyente;
    private FecetOficio ofDependienteSeleccionado;
    private FecetOficio oficioSeleccionado;
    private FecetOficio ofRechazadoSeleccionado;
    private FecetOficioAnexos anexoOficioSeleccionado;
    private DocumentoOrdenModel papelTrabajoSeleccionado;
    private AgaceOrden ordenSeleccionada;
    private FecetAnexosRechazoOficio anexoRechazoOficioSeleccionado;

    private List<FecetOficio> listaOficiosDependientes;
    private List<FecetOficio> listaOficiosFirmados;
    private List<FecetOficio> listaOficiosRechazados;
    private List<FecetOficioAnexos> listaAnexosOficio;
    private List<FecetOficioAnexos> listaOficioAnexosRechazo;
    private List<DocumentoOrdenModel> listaPapelesTrabajo;
    private List<FecetAnexosRechazoOficio> listaAnexosRechazoOficio;

    private String origenConsulta;
    private boolean mostrarBtnDocsAgenteAduanal;

    public UserProfileDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDTO userProfile) {
        this.userProfile = userProfile;
    }

    public PromocionConsultaDTO getPromocionConsultaSeleccionada() {
        return promocionConsultaSeleccionada;
    }

    public void setPromocionConsultaSeleccionada(PromocionConsultaDTO promocionConsultaSeleccionada) {
        this.promocionConsultaSeleccionada = promocionConsultaSeleccionada;
    }

    public OficioConsultaDTO getOficioConsultaSeleccionado() {
        return oficioConsultaSeleccionado;
    }

    public void setOficioConsultaSeleccionado(OficioConsultaDTO oficioConsultaSeleccionado) {
        this.oficioConsultaSeleccionado = oficioConsultaSeleccionado;
    }

    public ProrrogaOrdenConsultaDTO getProrrogaOrdenConsultaSeleccionada() {
        return prorrogaOrdenConsultaSeleccionada;
    }

    public void setProrrogaOrdenConsultaSeleccionada(ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaSeleccionada) {
        this.prorrogaOrdenConsultaSeleccionada = prorrogaOrdenConsultaSeleccionada;
    }

    public FlujoProrrogaOrdenConsultaDTO getFlujoProrrogaOrdenConsultaDTOSeleccionada() {
        return flujoProrrogaOrdenConsultaDTOSeleccionada;
    }

    public void setFlujoProrrogaOrdenConsultaDTOSeleccionada(
            FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTOSeleccionada) {
        this.flujoProrrogaOrdenConsultaDTOSeleccionada = flujoProrrogaOrdenConsultaDTOSeleccionada;
    }

    public OficioConsultaDTO getOficioConsultaDependeienteSeleccionado() {
        return oficioConsultaDependeienteSeleccionado;
    }

    public void setOficioConsultaDependeienteSeleccionado(OficioConsultaDTO oficioConsultaDependeienteSeleccionado) {
        this.oficioConsultaDependeienteSeleccionado = oficioConsultaDependeienteSeleccionado;
    }

    public PromocionOficioConsultaDTO getPromocionOficioConsultaSeleccionada() {
        return promocionOficioConsultaSeleccionada;
    }

    public void setPromocionOficioConsultaSeleccionada(PromocionOficioConsultaDTO promocionOficioConsultaSeleccionada) {
        this.promocionOficioConsultaSeleccionada = promocionOficioConsultaSeleccionada;
    }

    public ProrrogaOficioConsultaDTO getProrrogaOficioConsultaSeleccionada() {
        return prorrogaOficioConsultaSeleccionada;
    }

    public void setProrrogaOficioConsultaSeleccionada(ProrrogaOficioConsultaDTO prorrogaOficioConsultaSeleccionada) {
        this.prorrogaOficioConsultaSeleccionada = prorrogaOficioConsultaSeleccionada;
    }

    public FlujoProrrogaOficioConsultaDTO getFlujoProrrogaOficioConsultaDTOSeleccionada() {
        return flujoProrrogaOficioConsultaDTOSeleccionada;
    }

    public void setFlujoProrrogaOficioConsultaDTOSeleccionada(
            FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTOSeleccionada) {
        this.flujoProrrogaOficioConsultaDTOSeleccionada = flujoProrrogaOficioConsultaDTOSeleccionada;
    }

    public ColaboradorDocumentoDTO getColaboradoresDTO() {
        return colaboradoresDTO;
    }

    public void setColaboradoresDTO(ColaboradorDocumentoDTO colaboradoresDTO) {
        this.colaboradoresDTO = colaboradoresDTO;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetOficio getOfDependienteSeleccionado() {
        return ofDependienteSeleccionado;
    }

    public void setOfDependienteSeleccionado(FecetOficio ofDependienteSeleccionado) {
        this.ofDependienteSeleccionado = ofDependienteSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficioAnexos getAnexoOficioSeleccionado() {
        return anexoOficioSeleccionado;
    }

    public void setAnexoOficioSeleccionado(FecetOficioAnexos anexoOficioSeleccionado) {
        this.anexoOficioSeleccionado = anexoOficioSeleccionado;
    }

    public DocumentoOrdenModel getPapelTrabajoSeleccionado() {
        return papelTrabajoSeleccionado;
    }

    public void setPapelTrabajoSeleccionado(DocumentoOrdenModel papelTrabajoSeleccionado) {
        this.papelTrabajoSeleccionado = papelTrabajoSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public List<FecetOficio> getListaOficiosDependientes() {
        return listaOficiosDependientes;
    }

    public void setListaOficiosDependientes(List<FecetOficio> listaOficiosDependientes) {
        this.listaOficiosDependientes = listaOficiosDependientes;
    }

    public List<FecetOficioAnexos> getListaAnexosOficio() {
        return listaAnexosOficio;
    }

    public void setListaAnexosOficio(List<FecetOficioAnexos> listaAnexosOficio) {
        this.listaAnexosOficio = listaAnexosOficio;
    }

    public List<FecetOficio> getListaOficiosFirmados() {
        return listaOficiosFirmados;
    }

    public void setListaOficiosFirmados(List<FecetOficio> listaOficiosFirmados) {
        this.listaOficiosFirmados = listaOficiosFirmados;
    }

    public List<DocumentoOrdenModel> getListaPapelesTrabajo() {
        return listaPapelesTrabajo;
    }

    public void setListaPapelesTrabajo(List<DocumentoOrdenModel> listaPapelesTrabajo) {
        this.listaPapelesTrabajo = listaPapelesTrabajo;
    }

    public List<FecetOficio> getListaOficiosRechazados() {
        return listaOficiosRechazados;
    }

    public void setListaOficiosRechazados(List<FecetOficio> listaOficiosRechazados) {
        this.listaOficiosRechazados = listaOficiosRechazados;
    }

    public FecetOficio getOfRechazadoSeleccionado() {
        return ofRechazadoSeleccionado;
    }

    public void setOfRechazadoSeleccionado(FecetOficio ofRechazadoSeleccionado) {
        this.ofRechazadoSeleccionado = ofRechazadoSeleccionado;
    }

    public List<FecetOficioAnexos> getListaOficioAnexosRechazo() {
        return listaOficioAnexosRechazo;
    }

    public void setListaOficioAnexosRechazo(List<FecetOficioAnexos> listaOficioAnexosRechazo) {
        this.listaOficioAnexosRechazo = listaOficioAnexosRechazo;
    }

    public List<FecetAnexosRechazoOficio> getListaAnexosRechazoOficio() {
        return listaAnexosRechazoOficio;
    }

    public void setListaAnexosRechazoOficio(List<FecetAnexosRechazoOficio> listaAnexosRechazoOficio) {
        this.listaAnexosRechazoOficio = listaAnexosRechazoOficio;
    }

    public FecetAnexosRechazoOficio getAnexoRechazoOficioSeleccionado() {
        return anexoRechazoOficioSeleccionado;
    }

    public void setAnexoRechazoOficioSeleccionado(FecetAnexosRechazoOficio anexoRechazoOficioSeleccionado) {
        this.anexoRechazoOficioSeleccionado = anexoRechazoOficioSeleccionado;
    }

    public String getOrigenConsulta() {
        return origenConsulta;
    }

    public void setOrigenConsulta(String origenConsulta) {
        this.origenConsulta = origenConsulta;
    }

    public boolean isMostrarBtnDocsAgenteAduanal() {
        return mostrarBtnDocsAgenteAduanal;
    }

    public void setMostrarBtnDocsAgenteAduanal(boolean mostrarBtnDocsAgenteAduanal) {
        this.mostrarBtnDocsAgenteAduanal = mostrarBtnDocsAgenteAduanal;
    }

}
