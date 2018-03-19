package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececMotivoSuplencia;

public interface FececMotivoSuplenciaDAO {

    FececMotivoSuplencia findByPrimaryKey(BigDecimal idMotivo);

    List<FececMotivoSuplencia> findAll();
}
