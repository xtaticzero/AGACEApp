package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sergio.vaca
 *
 */
public enum AciaceSemaforoEnum {
    VERDE(SemaforoEnum.SEMAFORO_VERDE, SemaforoEnum.SEMAFORO_AMARILLO, SemaforoEnum.SEMAFORO_NARANJA, SemaforoEnum.SEMAFORO_ROJO, SemaforoEnum.SEMAFORO_MORADO),
    CAFE(SemaforoEnum.SEMAFORO_CAFE),
    AZUL(SemaforoEnum.SEMAFORO_AZUL),
    GRIS(SemaforoEnum.SEMAFORO_GRIS),
    NEGRO(SemaforoEnum.SEMAFORO_NEGRO),
    BEIGE(SemaforoEnum.SEMAFORO_BEIGE),
    BLANCO(SemaforoEnum.SEMAFORO_BLANCO),
    MORADO(SemaforoEnum.SEMAFORO_MORADO);
    
    private final SemaforoEnum semaforoPadre;
    private final List<SemaforoEnum> hijos;
    
    private AciaceSemaforoEnum(SemaforoEnum semaforoPadre, SemaforoEnum... hijos) {
        this.semaforoPadre = semaforoPadre;
        if (hijos != null) {
            this.hijos = Arrays.asList(hijos);
        } else {
            this.hijos = new ArrayList<SemaforoEnum>();
        }
    }
    
    public static SemaforoEnum obtenerSemaforoById(int id) {
        SemaforoEnum resultado = null;
        if (id > 0) {
            SemaforoEnum busqueda = SemaforoEnum.obtenerSemaforoById(id);
            if (busqueda != null) {
                for (AciaceSemaforoEnum entry : AciaceSemaforoEnum.values()) {
                    if (entry.getHijos().contains(busqueda) || entry.getSemaforoPadre().equals(busqueda)) {
                        resultado = entry.getSemaforoPadre();
                        break;
                    }
                }
            }
        }
        return resultado;
    }

    public static AciaceSemaforoEnum obtenerEstatusPadre(SemaforoEnum estatus) {
        AciaceSemaforoEnum resultado = null;
        for (AciaceSemaforoEnum entry : AciaceSemaforoEnum.values()) {
            if (entry.getSemaforoPadre().equals(estatus)) {
                resultado = entry;
                break;
            }
        }
        if (resultado == null) {
            resultado = obtenerEstatusByHijo(estatus);
        }
        return resultado;
    }
    
    public static List<SemaforoEnum> obtenerEstatus(AciaceSemaforoEnum enumeracion) {
        List<SemaforoEnum> estatus = new ArrayList<SemaforoEnum>();
        estatus.add(enumeracion.getSemaforoPadre());
        estatus.addAll(enumeracion.getHijos());
        return estatus;
    }
    
    private static AciaceSemaforoEnum obtenerEstatusByHijo(SemaforoEnum estatus) {
        AciaceSemaforoEnum resultado = null;
        ORIGINAL:
        for (AciaceSemaforoEnum entry : AciaceSemaforoEnum.values()) {
            for (SemaforoEnum hijo : entry.getHijos()) {
                if (hijo.equals(estatus)) {
                    resultado = entry;
                    break ORIGINAL;
                }
            }
        }
        return resultado;
    }
    
    public static boolean isHijo(int id) {
        boolean resultado = false;
        SemaforoEnum registro = SemaforoEnum.obtenerSemaforoById(id);
        if (registro != null) {
            for (AciaceSemaforoEnum entry : AciaceSemaforoEnum.values()) {
                if (entry.getHijos().contains(registro)) {
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }

    public final SemaforoEnum getSemaforoPadre() {
        return semaforoPadre;
    }

    public final List<SemaforoEnum> getHijos() {
        return hijos;
    }
}

