/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.serialization.arrays;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ArrayOfIntParameter extends ArrayOfint {

    public ArrayOfIntParameter() {
        super();
    }

    public ArrayOfIntParameter(Integer[] arrayInt) {
        super();
        _int = Arrays.asList(arrayInt);
    }

    public void setInt(List<Integer> _int) {
        this._int = _int;
    }

}
