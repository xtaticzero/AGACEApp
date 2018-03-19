/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules.impl;

import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum FiltroConsultaPropuestasRule implements Rule<FiltroConsultaPropuestas> {

    ES_FILTRO_VALIDO() {
                @Override
                public boolean process(FiltroConsultaPropuestas filtro) {

                    boolean flgResult;

                    flgResult = filtro != null && filtro.getUnidadAdmtvaDesahogoFiltro() != null && !filtro.getUnidadAdmtvaDesahogoFiltro().isEmpty();
                    flgResult = flgResult && filtro.getEmpleadoConsultaFiltro() != null;

                    return flgResult;
                }
            };
}
