package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;

public abstract class PropuestasServiceAbstract extends ServiceAbstract {

    private static final long serialVersionUID = -2108654863905117194L;

    public void fillUnidad(List<GraficaValoresDTO> ordenes) {
        for (GraficaValoresDTO reporteOrdenesDTO : ordenes) {
            if(reporteOrdenesDTO.getIdUnidad()!=null){            
                AraceDTO unidad = fillAraceDto(reporteOrdenesDTO.getIdUnidad().intValue());
                if (unidad != null) {
                    reporteOrdenesDTO.setUnidad(unidad.getNombre());
                }
            }
        }
    }
    
    public void fillUnidadReporte(List<ReporteOrdenesDTO> ordenes) {
        for (ReporteOrdenesDTO reporteOrdenesDTO : ordenes) {
            if(reporteOrdenesDTO.getIdUnidadAdministrativa()!=null){            
                AraceDTO unidad = fillAraceDto(reporteOrdenesDTO.getIdUnidadAdministrativa().intValue());
                if (unidad != null) {
                    reporteOrdenesDTO.setUnidadAdministrativa(unidad.getNombre());
                }
            }
        }
    }
    
}
