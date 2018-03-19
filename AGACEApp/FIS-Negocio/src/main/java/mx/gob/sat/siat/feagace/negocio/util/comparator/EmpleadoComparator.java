/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.comparator;

import java.io.Serializable;
import java.util.Comparator;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoComparator implements Comparator<EmpleadoDTO>, Serializable {
    private static final long serialVersionUID = 3859907180926049421L;

    @Override
    public int compare(EmpleadoDTO empleadoBase, EmpleadoDTO empleadoComparado) {
        return empleadoBase.getRfc().compareTo(empleadoComparado.getRfc());
    }
}
