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
public class AsignarPropuestaAuditorHelper extends BaseManagedBean {

    private static final long serialVersionUID = 5994523268623053030L;

    public String crearMensaje1(int numeroRegs, String nombre) {
        String mensaje = getMessageResourceString("mensaje.dialog.asignar.auditor.msg1");

        return String.format(mensaje, numeroRegs, nombre);
    }

    public String crearMensaje2(int numeroRegs, String nombre) {
        String mensaje = getMessageResourceString("mensaje.dialog.asignar.auditor.msg2");

        return String.format(mensaje, numeroRegs, nombre);
    }

    public EmpleadoDTO obtenerAuditor(String rfcAuditorSeleccionado, List<EmpleadoDTO> listaAuditores) {
        EmpleadoDTO auditorSeleccionado = null;
        for (EmpleadoDTO auditor : listaAuditores) {
            if (auditor.getRfc().equals(rfcAuditorSeleccionado)) {
                auditorSeleccionado = auditor;
            }
        }
        return auditorSeleccionado;
    }

}
