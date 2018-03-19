/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.exception;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoServiceClientException extends Exception implements Serializable {

    private static final long serialVersionUID = -4106263954566788750L;

    public EmpleadoServiceClientException(Throwable throwable) {
        super(throwable);
    }

    public EmpleadoServiceClientException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public EmpleadoServiceClientException(String string) {
        super(string);
    }

    public EmpleadoServiceClientException() {
        super();
    }
}
