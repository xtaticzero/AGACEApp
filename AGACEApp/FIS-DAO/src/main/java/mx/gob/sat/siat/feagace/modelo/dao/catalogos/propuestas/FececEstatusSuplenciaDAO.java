package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEstatusSuplencia;

public interface FececEstatusSuplenciaDAO {

    List<FececEstatusSuplencia> findAll();

    FececEstatusSuplencia findByPrimaryKey(BigDecimal idMotivo);
}
