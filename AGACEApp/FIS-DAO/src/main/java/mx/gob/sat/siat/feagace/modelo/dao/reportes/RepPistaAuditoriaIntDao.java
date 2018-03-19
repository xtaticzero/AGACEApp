package mx.gob.sat.siat.feagace.modelo.dao.reportes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;

public interface RepPistaAuditoriaIntDao {

    /**
     * Metodo que obtiene informacion referente a los movimientos realizados por
     * el empleado dentro del aplicativo
     *
     * @param pistaDTO
     * @return
     */
    List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaDTO);

    List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaDTO, BigDecimal idEmpleado, BigDecimal idRegistroBuscar);

}
