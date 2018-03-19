/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper;

import java.util.List;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author erick.bernal
 */
@Component
public class AsignarPropuestaFirmanteHelper extends BaseManagedBean {

    private static final long serialVersionUID = 3935086756817672390L;

    public String crearMensaje1(int numeroRegs, String nombre) {
        String mensaje = getMessageResourceString("mensaje.dialog.asignar.firmante.msg1");

        return String.format(mensaje, numeroRegs, nombre);
    }

    public String crearMensaje2(int numeroRegs, String nombre) {
        String mensaje = getMessageResourceString("mensaje.dialog.asignar.firmante.msg2");

        return String.format(mensaje, numeroRegs, nombre);
    }

    public EmpleadoDTO obtenerFirmante(String rfcFirmanteSeleccionado, List<EmpleadoDTO> list) {
        EmpleadoDTO firmanteSeleccionado = null;
        for (EmpleadoDTO firmante : list) {
            if (firmante.getRfc().equals(rfcFirmanteSeleccionado)) {
                firmanteSeleccionado = firmante;
            }
        }
        return firmanteSeleccionado;
    }

    public String crearStringPrioridad(List<String> prioridades) {
        StringBuilder strPrioridad = new StringBuilder("");
        int cont = 0;
        for (String prioridad : prioridades) {
            strPrioridad.append(prioridad);
            if (cont++ < prioridades.size() - 1) {
                strPrioridad.append(",");
            }
        }
        return strPrioridad.toString();
    }

}
