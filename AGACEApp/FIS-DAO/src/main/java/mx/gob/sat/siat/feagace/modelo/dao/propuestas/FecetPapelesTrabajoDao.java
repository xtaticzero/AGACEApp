package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;

public interface FecetPapelesTrabajoDao {

    void insert(PapelesTrabajo dto);

    List<PapelesTrabajo> getPapelesByIdPropuesta(BigDecimal idPropuesta);

    List<PapelesTrabajo> getPapelesOfOrden(BigDecimal idPropuesta, BigDecimal idOrden);

    List<PapelesTrabajo> getPapelesOfOficio(BigDecimal idOrden, BigDecimal idTipoOficio);

    List<PapelesTrabajo> getPapelesOfOficioById(BigDecimal idOficio);

    void actualizar(PapelesTrabajo dto);

}
