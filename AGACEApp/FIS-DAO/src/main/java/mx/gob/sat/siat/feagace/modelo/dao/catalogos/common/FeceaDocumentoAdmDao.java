package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;

public interface FeceaDocumentoAdmDao {

    List<DocumentoActoAdministrativo> obtenerDocumentosActoAdm(FececActosAdm actoAdministrativo);

}
