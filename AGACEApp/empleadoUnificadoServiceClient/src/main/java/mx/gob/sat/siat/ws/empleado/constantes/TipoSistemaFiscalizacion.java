/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class TipoSistemaFiscalizacion {

    private final Integer idSistemaFiscalizacion;
    private final String descripcion;

    private TipoSistemaFiscalizacion(Integer idSistemaFiscalizacion, String descripcion) {
        this.idSistemaFiscalizacion = idSistemaFiscalizacion;
        this.descripcion = descripcion;
    }

    public Integer getIdSistemaFiscalizacion() {
        return idSistemaFiscalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoSistemaFiscalizacion getTipoSistemaFiscalizacion(Integer idSistemaFiscalizacion, String descripcion) {
        if (idSistemaFiscalizacion != null && descripcion != null) {
            return new TipoSistemaFiscalizacion(idSistemaFiscalizacion, descripcion);
        }
        return null;
    }

    public static TipoSistemaFiscalizacion getTipoSistemaFiscalizacion(Integer idSistemaFiscalizacion) {
        if (idSistemaFiscalizacion != null) {
            return new TipoSistemaFiscalizacion(idSistemaFiscalizacion, "Sistema Integral de Fiscalización");
        }
        return null;
    }

}
