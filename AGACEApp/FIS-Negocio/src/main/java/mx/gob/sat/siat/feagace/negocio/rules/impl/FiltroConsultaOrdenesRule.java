package mx.gob.sat.siat.feagace.negocio.rules.impl;

import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

public enum FiltroConsultaOrdenesRule implements Rule<FiltroConsultaOrdenes> {
    
    ES_FILTRO_VALIDO() {
        @Override
        public boolean process(FiltroConsultaOrdenes filtro) {

            boolean flgResult;

            flgResult = filtro != null && filtro.getUnidadAdmtvaDesahogoFiltro() != null && !filtro.getUnidadAdmtvaDesahogoFiltro().isEmpty();
            flgResult = flgResult && filtro.getEmpleadoConsultaFiltro() != null;

            return flgResult;
        }
    };

}