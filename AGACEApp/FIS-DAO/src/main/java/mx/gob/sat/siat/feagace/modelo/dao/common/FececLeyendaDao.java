package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FececLeyenda;

public interface FececLeyendaDao {

    List<FececLeyenda> obtenerLeyendaById(BigDecimal... idLeyenda);

}
