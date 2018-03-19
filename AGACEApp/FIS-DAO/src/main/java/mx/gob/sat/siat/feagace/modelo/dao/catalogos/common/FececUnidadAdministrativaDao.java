package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;

public interface FececUnidadAdministrativaDao {

    List<FececUnidadAdministrativa> findAll();

    List<FececUnidadAdministrativa> findUnidadesAdministrativasActivas();

    List<FececUnidadAdministrativa> findUnidadesAdministrativasPropuestas();

    List<FececUnidadAdministrativa> findUnidadesByAuditor(String condicion);

    List<FececUnidadAdministrativa> getUnidadesParaAsignarPropuesta();

    ClaveFolioOficioDTO obtenerClavesFolioOficio(BigDecimal idArace);

    List<FececUnidadAdministrativa> findWhereIdAraceEquals(BigDecimal idArace);

    List<FececUnidadAdministrativa> findAllReportes();
}
