package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ColaboradorVO extends BaseModel {

    private static final long serialVersionUID = 5435345345341L;
    
    private String rfc;
    private String nombre;
    private String correo;
    private boolean medioContactoBoolean;
    private boolean sinColaborador;
    private boolean deshabilitarSinColaborador;
    private boolean deshabilitarCampos;
    private boolean deshabilitarCorreo;
    private boolean visibleBusquedaColaborador;
    private boolean deshabilitarBotonBusquedaColaborador;
    private boolean deshabilitarComboColaborador;
    private boolean visibleConfirmarColaborador;
    
    private FecetAsociado asociado;
    
    private BigDecimal tipoAsociado;
    private String nombreTipoAsociado;
    
    private List<String> listaMediosContacto;


    public ColaboradorVO() {
        super();
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setMedioContactoBoolean(boolean medioContactoBoolean) {
        this.medioContactoBoolean = medioContactoBoolean;
    }

    public boolean isMedioContactoBoolean() {
        return medioContactoBoolean;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }


    public void setSinColaborador(boolean sinColaborador) {
        this.sinColaborador = sinColaborador;
    }

    public boolean isSinColaborador() {
        return sinColaborador;
    }

    public void setDeshabilitarCampos(boolean deshabilitarCampos) {
        this.deshabilitarCampos = deshabilitarCampos;
    }

    public boolean isDeshabilitarCampos() {
        return deshabilitarCampos;
    }

    public void setDeshabilitarCorreo(boolean deshabilitarCorreo) {
        this.deshabilitarCorreo = deshabilitarCorreo;
    }

    public boolean isDeshabilitarCorreo() {
        return deshabilitarCorreo;
    }

    public void setVisibleBusquedaColaborador(boolean visibleBusquedaColaborador) {
        this.visibleBusquedaColaborador = visibleBusquedaColaborador;
    }

    public boolean isVisibleBusquedaColaborador() {
        return visibleBusquedaColaborador;
    }

    public void setDeshabilitarBotonBusquedaColaborador(boolean deshabilitarBotonBusquedaColaborador) {
        this.deshabilitarBotonBusquedaColaborador = deshabilitarBotonBusquedaColaborador;
    }

    public boolean isDeshabilitarBotonBusquedaColaborador() {
        return deshabilitarBotonBusquedaColaborador;
    }

    public void setDeshabilitarComboColaborador(boolean deshabilitarComboColaborador) {
        this.deshabilitarComboColaborador = deshabilitarComboColaborador;
    }

    public boolean isDeshabilitarComboColaborador() {
        return deshabilitarComboColaborador;
    }


    public void setTipoAsociado(BigDecimal tipoAsociado) {
        this.tipoAsociado = tipoAsociado;
    }

    public BigDecimal getTipoAsociado() {
        return tipoAsociado;
    }

    public void setVisibleConfirmarColaborador(boolean visibleConfirmarColaborador) {
        this.visibleConfirmarColaborador = visibleConfirmarColaborador;
    }

    public boolean isVisibleConfirmarColaborador() {
        return visibleConfirmarColaborador;
    }

    public void setAsociado(FecetAsociado asociado) {
        this.asociado = asociado;
    }

    public FecetAsociado getAsociado() {
        return asociado;
    }

    public void setNombreTipoAsociado(String nombreTipoAsociado) {
        this.nombreTipoAsociado = nombreTipoAsociado;
    }

    public String getNombreTipoAsociado() {
        return nombreTipoAsociado;
    }

    public void setDeshabilitarSinColaborador(boolean deshabilitarSinColaborador) {
        this.deshabilitarSinColaborador = deshabilitarSinColaborador;
    }

    public boolean isDeshabilitarSinColaborador() {
        return deshabilitarSinColaborador;
    }

    public void setListaMediosContacto(List<String> listaMediosContacto) {
        this.listaMediosContacto = listaMediosContacto;
    }

    public List<String> getListaMediosContacto() {
        return listaMediosContacto;
    }
}
