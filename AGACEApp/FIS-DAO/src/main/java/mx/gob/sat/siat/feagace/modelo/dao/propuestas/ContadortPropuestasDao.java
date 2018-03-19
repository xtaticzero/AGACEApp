/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ContadortPropuestasDao {

    Integer countPropuestasXRfcCreacionEstatus(FiltroPropuestas filtroDao);

    Integer countPropuestasXIdCreacionEstatus(FiltroPropuestas filtroDao);
}
