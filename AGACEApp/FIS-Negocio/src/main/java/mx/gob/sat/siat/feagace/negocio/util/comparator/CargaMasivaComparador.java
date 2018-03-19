package mx.gob.sat.siat.feagace.negocio.util.comparator;

import java.io.Serializable;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

public class CargaMasivaComparador implements Comparator<CargaMasivaPropuestasDTO>, Serializable {

    private static final long serialVersionUID = 7929386770180220436L;

    @Override
    public int compare(CargaMasivaPropuestasDTO o1, CargaMasivaPropuestasDTO o2) {
        int respuesta = 0;
        if (o1 != null && o2 != null) {
            try {

                respuesta = respuesta
                        + (o1.getFecetContribuyente().getRfc().compareTo(o2.getFecetContribuyente().getRfc()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getUnidadAdministrativa().compareTo(o2.getUnidadAdministrativa()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta
                        + (o1.getFecetPropuesta().getIdMetodo().compareTo(o2.getFecetPropuesta().getIdMetodo()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getFecetPropuesta().getIdSubprograma()
                        .compareTo(o2.getFecetPropuesta().getIdSubprograma()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getFecetPropuesta().getIdTipoPropuesta()
                        .compareTo(o2.getFecetPropuesta().getIdTipoPropuesta()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getFecetPropuesta().getIdCausaProgramacion()
                        .compareTo(o2.getFecetPropuesta().getIdCausaProgramacion()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta
                        + (o1.getFecetPropuesta().getIdSector().compareTo(o2.getFecetPropuesta().getIdSector()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getFecetPropuesta().getFechaInicioPeriodo()
                        .compareTo(o2.getFecetPropuesta().getFechaInicioPeriodo()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getFecetPropuesta().getFechaFinPeriodo()
                        .compareTo(o2.getFecetPropuesta().getFechaFinPeriodo()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta + (o1.getFecetPropuesta().getPrioridadSugerida()
                        .compareTo(o2.getFecetPropuesta().getPrioridadSugerida()));
                if (respuesta != 0) {
                    return respuesta;
                }
                respuesta = respuesta
                        + (o1.getFecetContribuyente().getRfc().compareTo(o2.getFecetContribuyente().getRfc()));
                if (respuesta != 0) {
                    return respuesta;
                }

                boolean[] condicionesFechaInforme = new boolean[] {
                        (respuesta == 0 && o1.getFecetPropuesta().getFechaInforme() != null
                                && o2.getFecetPropuesta().getFechaInforme() != null) };
                boolean[] condicionesFechaPresentacion = new boolean[] {
                        (respuesta == 0 && o1.getFecetPropuesta().getFechaPresentacion() != null
                                && o2.getFecetPropuesta().getFechaPresentacion() != null) };

                if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condicionesFechaInforme)) {
                    respuesta = respuesta + (o1.getFecetPropuesta().getFechaInforme()
                            .compareTo(o2.getFecetPropuesta().getFechaInforme()));
                }

                if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condicionesFechaPresentacion)) {
                    respuesta = respuesta + (o1.getFecetPropuesta().getFechaPresentacion()
                            .compareTo(o2.getFecetPropuesta().getFechaPresentacion()));
                }

                return respuesta;

            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaComparador.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }

        }
        return -1;
    }

}
