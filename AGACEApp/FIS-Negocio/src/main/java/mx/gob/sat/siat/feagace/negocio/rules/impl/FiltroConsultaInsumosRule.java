/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules.impl;

import mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro.FiltroConsultaInsumos;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum FiltroConsultaInsumosRule implements Rule<FiltroConsultaInsumos> {

    ES_FILTRO_VALIDO() {
                @Override
                public boolean process(FiltroConsultaInsumos filtro) {

                    boolean flgResult;

                    flgResult = filtro != null && filtro.getUnidadAdmtvaDesahogoFiltro() != null && !filtro.getUnidadAdmtvaDesahogoFiltro().isEmpty();
                    flgResult = flgResult && (filtro.getEstatusFiltro() != null && !filtro.getEstatusFiltro().isEmpty());
                    flgResult = flgResult && filtro.getEmpleadoConsultaFiltro()!=null;

                    return flgResult;
                }
            };
}
