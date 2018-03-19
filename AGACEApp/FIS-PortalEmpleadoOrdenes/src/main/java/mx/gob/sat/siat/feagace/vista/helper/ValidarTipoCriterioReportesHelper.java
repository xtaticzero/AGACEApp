package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

import org.springframework.stereotype.Component;


@Component
public class ValidarTipoCriterioReportesHelper implements Serializable{

    @SuppressWarnings("compatibility:-4866519500430560487")
    private static final long serialVersionUID = 1L;
    private boolean mostrarEstatus;
    private boolean mostrarEntidad;
    private boolean mostrarMetodo;
    private boolean mostrarUnidad;
    
    public int getTipoCriterio(ReportesVO vo){
        int tipoCriterio=-1;
        this.setMostrarEntidad(false);
        this.setMostrarEstatus(false);
        this.setMostrarMetodo(false);
        this.setMostrarUnidad(false);
        boolean[] condiciones;
        
        if(vo.getTipoGrafica().equals(FacesUtil.getMessageResourceString("lbl.reporte.tipo.grafica.barras"))){
            condiciones = new boolean[]{this.validarFechas(vo),(!vo.isMostrarEstatus() && !vo.isMostrarEntidad() && !vo.isMostrarMetodo() && !vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 0 Barras
                tipoCriterio=ConstantesReportes.N_0;
            }
            
            condiciones = new boolean[]{this.validarMeses(vo),(!vo.isMostrarEstatus() && !vo.isMostrarEntidad() && !vo.isMostrarMetodo() && !vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 1 Circular
                tipoCriterio=ConstantesReportes.N_1;
            }
            
            condiciones = new boolean[]{this.validarFechas(vo),(vo.isMostrarEstatus() || vo.isMostrarEntidad() || vo.isMostrarMetodo() || vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 2
                tipoCriterio=ConstantesReportes.N_2;
            }
            
            condiciones = new boolean[]{this.validarMeses(vo),(vo.isMostrarEstatus() || vo.isMostrarEntidad() || vo.isMostrarMetodo() || vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 3
                tipoCriterio=ConstantesReportes.N_3;
            }
        } else if (vo.getTipoGrafica().equals(FacesUtil.getMessageResourceString("lbl.reporte.tipo.grafica.circular"))){
            condiciones = new boolean[]{this.validarFechas(vo),(!vo.isMostrarEstatus() && !vo.isMostrarEntidad() && !vo.isMostrarMetodo() && !vo.isMostrarUnidad())};
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 0 Barras
                tipoCriterio=ConstantesReportes.N_0;
            }
            
            condiciones = new boolean[]{this.validarMeses(vo),(!vo.isMostrarEstatus() && !vo.isMostrarEntidad() && !vo.isMostrarMetodo() && !vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 1 Circular
                tipoCriterio=ConstantesReportes.N_1;
            }
            
            condiciones = new boolean[]{this.validarFechas(vo),(vo.isMostrarEstatus() || vo.isMostrarEntidad() || vo.isMostrarMetodo() || vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 4
                tipoCriterio=ConstantesReportes.N_4;
            }
            
            condiciones = new boolean[]{this.validarMeses(vo),(vo.isMostrarEstatus() || vo.isMostrarEntidad() || vo.isMostrarMetodo() || vo.isMostrarUnidad())};
            
            if(ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)){
                //Reporte 5
                tipoCriterio=ConstantesReportes.N_5;
            }
        }
        
        return tipoCriterio;
    }
    
    private boolean validarFechas(ReportesVO vo){
        boolean validarCampo=false;
        if(vo.getFechaInicioPeriodo()!=null && vo.getFechaFinPeriodo()!=null){
            validarCampo=true;
        }
        return validarCampo;
    }
    
    private boolean validarMeses(ReportesVO vo){
        boolean validarCampo=false;
        if(vo.getMesInicial()!=null && vo.getAnioInicial()!=null && vo.getMesFinal()!=null && vo.getAnioFinal()!=null){
            validarCampo=true;
        }
        return validarCampo;
    }
    

    public void setMostrarEstatus(boolean mostrarEstatus) {
        this.mostrarEstatus = mostrarEstatus;
    }

    public boolean isMostrarEstatus() {
        return mostrarEstatus;
    }

    public void setMostrarEntidad(boolean mostrarEntidad) {
        this.mostrarEntidad = mostrarEntidad;
    }

    public boolean isMostrarEntidad() {
        return mostrarEntidad;
    }

    public void setMostrarMetodo(boolean mostrarMetodo) {
        this.mostrarMetodo = mostrarMetodo;
    }

    public boolean isMostrarMetodo() {
        return mostrarMetodo;
    }

    public void setMostrarUnidad(boolean mostrarUnidad) {
        this.mostrarUnidad = mostrarUnidad;
    }

    public boolean isMostrarUnidad() {
        return mostrarUnidad;
    }
}
