/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.exception;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class NyVClientException extends Exception implements Serializable {

    private static final long serialVersionUID = -4106263954566788750L;

    public NyVClientException(Throwable throwable) {
        super(throwable);
    }

    public NyVClientException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public NyVClientException(String string) {
        super(string);
    }

    public NyVClientException() {
        super();
    }
}
