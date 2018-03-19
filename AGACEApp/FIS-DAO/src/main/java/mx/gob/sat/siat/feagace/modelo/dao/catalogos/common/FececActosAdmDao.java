/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;

/**
 * @author sergio.vaca
 *
 */
public interface FececActosAdmDao {

    FececActosAdm obtenerByPk(Long idActoAdmin);

    FececActosAdm obtenerIdNyv(Long idMetodo, Long idTipoOficio, Long unidadAdministrativa, boolean isActivoNyv, boolean isFaseII);
}
