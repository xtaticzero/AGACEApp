package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FececLeyenda;

public interface FececLeyendaService {
    
    List<FececLeyenda> obtenerLeyendaByIdLeyenda(BigDecimal... idLeyenda);

}
