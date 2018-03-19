package mx.gob.sat.siat.feagace.vista.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@ManagedBean(name = "actualizarFechasManagedBean")
@SessionScoped
public class ActualizarFechasManagedBean extends ActualizarManagedBeanAbstract implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static final String MENSAJE_EXITO = "Se actualiz\u00f3 correctamente";

    public void selectRadioComponente(ValueChangeEvent event){
        String tipoFecha = (String) event.getNewValue();
        limpiarComponente();
        if(tipoFecha.equals("orden")){
            setVisualizarFechaOrden(true);
            setVisualizarFechaOficio(false);
            setVisualizarFechaInsumo(false);
        }
        if(tipoFecha.equals("oficio")){
            setVisualizarFechaOrden(false);
            setVisualizarFechaOficio(true);
            setVisualizarFechaInsumo(false);
        }
        if(tipoFecha.equals("insumo")) {
            setVisualizarFechaInsumo(true);
            setVisualizarFechaOrden(false);
            setVisualizarFechaOficio(false);
        }
    }
    
    
    public void selectComponenteFechaOrden(ValueChangeEvent event){        
        setTipoFechaOrden((Integer) event.getNewValue());        
        setMostrarFechaInicio(false);
        setMostrarFechaFin(false);
        setMostrarFechaBaja(false);
        setNumeroOrden("");
        setMostrarBuscar(true);
    }
    
    public void selectComponenteFechaOficio(ValueChangeEvent event){
        setTipoFechaOrden((Integer) event.getNewValue());        
        setMostrarFechaInicio(false);
        setMostrarFechaFin(false);
        setMostrarFechaBaja(false);
        setIdOficio(null);
        setMostrarBuscar(true);
    }
    
    
    
    public void obtenerFechaOrden(){
        if (!getNumeroOrden().trim().equals("")) {
            setOrden(obtenerOrden(getNumeroOrden()));            
            if(getOrden()==null){
                setMostrarFechaInicio(false);
                setMostrarFechaFin(false);
                setMostrarFechaBaja(false);                
                addErrorMessage("No se encontro la orden");
            }else{            
                switch (getTipoFechaOrden()) {
                case Constantes.ENTERO_UNO:
                    if(getOrden().getFecetDetalleNyV()!=null){
                        setFechaInicio(getOrden().getFecetDetalleNyV().getFecSurteEfectosNyV());
                        setMostrarFechaInicio(true);
                        setMostrarFechaFin(false);
                        setMostrarFechaBaja(false);                        
                    }else{
                        addErrorMessage("La orden no tiene detalle NYV");
                    }
                break;
                    
                case Constantes.ENTERO_DOS:
                    setFechaInicio(getOrden().getFechaIntegraExp());
                    setMostrarFechaInicio(true);
                    setMostrarFechaFin(false);
                    setMostrarFechaBaja(false);
               break;
                    
               case Constantes.ENTERO_TRES:
                   setSuspension (getActualizarFechaService().getAcByIdOrden(getOrden().getIdOrden()));
                   if(getSuspension()!=null){
                       setFechaInicio(getSuspension().getFechaInicioSuspension());
                       setFechaFin(getSuspension().getFechaFinSuspension());
                       setFechaBaja(getSuspension().getFechaBaja());
                       setMostrarFechaInicio(true);
                       setMostrarFechaFin(true);
                       setMostrarFechaBaja(true);
                   }else{
                       addErrorMessage("La orden no tiene acuerdo conclusivo");
                   }
               break;
                
               default:
                    
               break;
                } 
            }
        }
    }
    
    public void obtenerFechaOficio(){
                        
            setOficio(obtenerOficio(getIdOficio()));
            
            if(getOficio()==null){
                setMostrarFechaInicio(false);
                setMostrarFechaFin(false);
                setMostrarFechaBaja(false);
                addErrorMessage("No se encontro el oficio");
            }else{            
                switch (getTipoFechaOrden()) {
                case Constantes.ENTERO_UNO:
                    if(getOficio().getFecetDetalleNyV()!=null){
                        setFechaInicio(getOficio().getFecetDetalleNyV().getFecSurteEfectosNyV());
                        setMostrarFechaInicio(true);
                        setMostrarFechaFin(false);
                        setMostrarFechaBaja(false);
                    }else{
                        addErrorMessage("El oficio no tiene detalle NYV");
                    }
                break;
                    
                case Constantes.ENTERO_DOS:
                    setFechaInicio(getOficio().getFechaFirma());
                    setMostrarFechaInicio(true);
                    setMostrarFechaFin(false);
                    setMostrarFechaBaja(false);
               break;
                    
                case Constantes.ENTERO_TRES:
                   setSuspension (getActualizarFechaService().getSuspensionByIdOficio(getOficio()));
                   if(getSuspension()!=null){
                       setFechaInicio(getSuspension().getFechaInicioSuspension());
                       setFechaFin(getSuspension().getFechaFinSuspension());
                       setFechaBaja(getSuspension().getFechaBaja());
                       setMostrarFechaInicio(true);
                       setMostrarFechaFin(true);
                       setMostrarFechaBaja(true);
                   }else{
                       addErrorMessage("El oficio no tiene suspension");
                   }
               break;
                
               default:
                    
               break;
                } 
            }
        
    }
    
    
    public void actualizarFechaOrden(){
        if(getOrden()!=null){
            switch (getTipoFechaOrden()) {
            case Constantes.ENTERO_UNO:
                getOrden().getFecetDetalleNyV().setFecSurteEfectosNyV(getFechaInicio());
                getActualizarFechaService().actualizarFechaSurteEfectos(getOrden().getFecetDetalleNyV());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
            
            case Constantes.ENTERO_DOS:
                getOrden().setFechaIntegraExp(getFechaInicio());
                getActualizarFechaService().actualizarIntegracionExpediente(getOrden(), getOrden().getFechaIntegraExp());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
                
            case Constantes.ENTERO_TRES:
                getSuspension().setFechaInicioSuspension(getFechaInicio());
                getSuspension().setFechaFinSuspension(getFechaFin());
                getSuspension().setFechaBaja(getFechaBaja());
                getActualizarFechaService().actualizarSuspension(getSuspension());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
            
            default:
                
            break;
            
            }
        }
    }
    
    public void actualizarFechaOficio(){
        if(getOficio()!=null){
            switch (getTipoFechaOrden()) {
            case Constantes.ENTERO_UNO:
                getOficio().getFecetDetalleNyV().setFecSurteEfectosNyV(getFechaInicio());                
                getActualizarFechaService().actualizarFechaSurteEfectos(getOficio().getFecetDetalleNyV());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
            
            case Constantes.ENTERO_DOS:
                getOficio().setFechaFirma(getFechaInicio());
                getActualizarFechaService().actualizarFechaFirma(getOficio());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
                
            case Constantes.ENTERO_TRES:
                getSuspension().setFechaInicioSuspension(getFechaInicio());
                getSuspension().setFechaFinSuspension(getFechaFin());
                getSuspension().setFechaBaja(getFechaBaja());
                getActualizarFechaService().actualizarSuspension(getSuspension());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
            
            default:
                
            break;
            
            }
        }
    }
    
    public void selectComponenteFechaInsumo(ValueChangeEvent event) {
        setTipoFechaInsumo((Integer) event.getNewValue());
        setMostrarFechaInicio(false);
        setMostrarFechaFin(false);
        setRegistroInsumo("");
    }

    public void obtenerFechaInsumo() {
        if (!getRegistroInsumo().trim().equals("")) {
            setInsumo(getActualizarFechaService().getInsumoByIdRegistro(getRegistroInsumo()));
            setSupensionInsumo(getActualizarFechaService().getSuspensionInsumoByIdRegistro(getRegistroInsumo()));

            if (getInsumo() == null && getSupensionInsumo() == null) {
                setMostrarFechaInicio(false);
                setMostrarFechaFin(false);
                setMostrarFechaBaja(false);
                addErrorMessage("No se encontro el insumo");
            } else {
                switch (getTipoFechaInsumo()) {
                case Constantes.ENTERO_UNO:
                    setFechaInicio(getSupensionInsumo().getFechaInicioSuspension());
                    setFechaFin(getSupensionInsumo().getFechaFinSuspension());
                    setMostrarFechaInicio(true);
                    setMostrarFechaFin(true);
                    setMostrarFechaBaja(false);
                    break;

                case Constantes.ENTERO_DOS:
                    setFechaInicio(getInsumo().getFechaInicioPlazo());
                    setMostrarFechaInicio(true);
                    setMostrarFechaFin(false);
                    setMostrarFechaBaja(false);
                    break;
                default:

                    break;
                }
            }
        }
    }
    
    public void actualizarFechaInsumo() {
        if(getInsumo() != null || getSupensionInsumo() != null){
            switch (getTipoFechaInsumo()) {
            case Constantes.ENTERO_UNO:
                getSupensionInsumo().setFechaInicioSuspension(getFechaInicio());
                getSupensionInsumo().setFechaFinSuspension(getFechaFin());
                getActualizarFechaService().actualizarSuspensionInsumo(getSupensionInsumo());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
            
            case Constantes.ENTERO_DOS:
                getInsumo().setFechaInicioPlazo(getFechaInicio());
                getActualizarFechaService().actualizarPlazoInsumo(getInsumo());
                limpiarFormulario();
                addMessage(MENSAJE_EXITO);
            break;
            
            default:
                
            break;
            
            }
        }
    }

    private AgaceOrden obtenerOrden(String numeroOrden) {
        AgaceOrden agaceOrden = getActualizarFechaService().getOrdenByNumeroOrden(numeroOrden);                 
        if (agaceOrden != null) {
            getConsultaRegistraNyVService().asignarFechaNotificable(agaceOrden);
        }
        return agaceOrden;
    }

    private FecetOficio obtenerOficio(BigDecimal idOficio) {
        FecetOficio fecetOficio = getActualizarFechaService().getOficioByIdOficio(idOficio);                
        if (fecetOficio != null) {
            getConsultaRegistraNyVService().asignarFechaNotificable(fecetOficio);
        }
        return fecetOficio;
    }
    
    public String regresaPaginaPrincipal() {
        limpiarFormulario();
        return "indexDocumentacion?faces-redirect=true";
    }

}