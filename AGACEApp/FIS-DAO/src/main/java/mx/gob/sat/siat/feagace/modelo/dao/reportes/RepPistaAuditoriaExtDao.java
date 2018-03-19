package mx.gob.sat.siat.feagace.modelo.dao.reportes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;

public interface RepPistaAuditoriaExtDao {

    /**
     * Metodo que obtiene informacion referente a los movimientos realizados por
     * el Contribuyente
     *
     * @param pistaExterDTO
     * @return
     */
    List<ReportePistaAuditoriaExternaDTO> buscaPistaAuditoriaExterna(ReportePistaAuditoriaExternaDTO pistaExterDTO, BigDecimal idRegistroBucar);
}
