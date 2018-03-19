/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sergio.vaca
 *
 */
public enum AciaceEstatusEnum {
    INSUMO_REGISTRADO(TipoEstatusEnum.INSUMO_REGISTRADO, 
                TipoEstatusEnum.ACIACE_INSUMO_RETROALIMENTADO,
                TipoEstatusEnum.ADMINISTRADOR_INSUMO_ASIGNADOS_SUBADMINISTRADOR,
                TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADO_ADMINISTRADOR,
                TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_ACEPTADOS,
                TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_NO_APROBADOS),
    INSUMO_X_ATENDER_RETROALIMENTACION(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION),
    INSUMO_RECHAZADO(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO),
    INSUMO_ACEPTADO(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO);
    
    private final TipoEstatusEnum estatusPadre;
    private final List<TipoEstatusEnum> hijos;
    
    private AciaceEstatusEnum(TipoEstatusEnum estatusPadre, TipoEstatusEnum... hijos) {
        this.estatusPadre = estatusPadre;
        if (hijos != null) {
            this.hijos = Arrays.asList(hijos);
        } else {
            this.hijos = new ArrayList<TipoEstatusEnum>();
        }
    }

    public TipoEstatusEnum getEstatusPadre() {
        return estatusPadre;
    }

    public List<TipoEstatusEnum> getHijos() {
        return hijos;
    }
    
    public static List<TipoEstatusEnum> obtenerEstatusConsulta(TipoEstatusEnum estatus) {
        List<TipoEstatusEnum> listEstatus = new ArrayList<TipoEstatusEnum>();
        for (AciaceEstatusEnum entry : AciaceEstatusEnum.values()) {
            if (entry.estatusPadre.equals(estatus) || entry.getHijos().contains(estatus)) {
                listEstatus.addAll(obtenerEstatus(entry));
                break;
            }
        }
        return listEstatus;
    }
    
    public static TipoEstatusEnum obtenerEstatusPadre(long id) {
        return obtenerEstatusPadre(TipoEstatusEnum.getById(id));
    }
    
    public static TipoEstatusEnum obtenerEstatusPadre(TipoEstatusEnum estatus) {
        TipoEstatusEnum resultado = null;
        if (estatus != null) {
            for (AciaceEstatusEnum entry : AciaceEstatusEnum.values()) {
                if (entry.getEstatusPadre().equals(estatus) || entry.getHijos().contains(estatus)) {
                    resultado = entry.getEstatusPadre();
                    break;
                }
            }
        }
        return resultado;
    }
    
    public static List<TipoEstatusEnum> obtenerEstatus(AciaceEstatusEnum enumeracion) {
        List<TipoEstatusEnum> estatus = new ArrayList<TipoEstatusEnum>();
        estatus.add(enumeracion.getEstatusPadre());
        estatus.addAll(enumeracion.getHijos());
        return estatus;
    }
    
    public static List<TipoEstatusEnum> obtenerEstatusPadre() {
        List<TipoEstatusEnum> estatus = new ArrayList<TipoEstatusEnum>();
        for (AciaceEstatusEnum aux : AciaceEstatusEnum.values()) {
            estatus.add(aux.getEstatusPadre());
        }
        return estatus;
    }
    
    public static boolean isHijo(long id) {
        boolean resultado = false;
        TipoEstatusEnum registro = TipoEstatusEnum.getById(id);
        if (registro != null) {
            for (AciaceEstatusEnum entry : AciaceEstatusEnum.values()) {
                if (entry.getHijos().contains(registro)) {
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }
}

