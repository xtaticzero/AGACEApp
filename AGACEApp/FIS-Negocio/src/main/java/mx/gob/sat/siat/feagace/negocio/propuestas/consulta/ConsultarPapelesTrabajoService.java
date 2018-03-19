package mx.gob.sat.siat.feagace.negocio.propuestas.consulta;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;

public interface ConsultarPapelesTrabajoService {

    void insertaPapelesTrabajo(PapelesTrabajo dto);

    List<PapelesTrabajo> getPapelesByIdPropuesta(BigDecimal idPropuesta);

    List<PapelesTrabajo> getPapelesByIdPropuestaOrIdOrden(BigDecimal idPropuesta, BigDecimal idOrden);

    List<PapelesTrabajo> getPapelesByIdOfcio(BigDecimal idOrden, BigDecimal idTipoOficio);

    List<PapelesTrabajo> getPapelesOnlyIdOfcio(BigDecimal idOficio);

    void guardarPapelTrabajo(DocumentoOrdenModel papelTrabajo) throws IOException;

    void actualizarPapelTrabajo(PapelesTrabajo papelTrabajo);
}
