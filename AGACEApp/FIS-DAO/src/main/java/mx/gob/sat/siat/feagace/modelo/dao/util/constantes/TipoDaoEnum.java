/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.util.constantes;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum TipoDaoEnum {

    PROPUESTA_DAO(1, PropuestasSQL.NAME_TABLE, PropuestasSQL.NAME_ID_TABLE);

    private final int idTipo;
    private final String nombreTabla;
    private final String nombreIdTabla;

    private TipoDaoEnum(int idTipo, String nombreTabla, String nombreIdTabla) {
        this.idTipo = idTipo;
        this.nombreTabla = nombreTabla;
        this.nombreIdTabla = nombreIdTabla;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public String getNombreIdTabla() {
        return nombreIdTabla;
    }
}
