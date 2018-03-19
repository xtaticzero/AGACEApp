package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAsociacionOficiosEnum;

public interface FecetTipoOficioDao {

    BigDecimal insert(FecetTipoOficio dto);

    BigDecimal update(FecetTipoOficio dto);

    FecetTipoOficio getTipoOficioById(BigDecimal idOficio);

    List<FecetTipoOficio> getTipoOficioByIdDependiente(BigDecimal idOficio, BigDecimal idMetodo,
            TipoAsociacionOficiosEnum tipoAsociacion);

    List<FecetTipoOficio> getTipoOficioByIdMetodo(BigDecimal idMetodo);

    List<FecetTipoOficio> getTipoOficioByIdAgrupacionEstatus(int idAgrupacion, BigDecimal idOrden,
            BigDecimal estatus);

    List<FecetTipoOficio> findAll();

    List<FecetTipoOficio> findOficiosAdministrables();

    List<FecetTipoOficio> getOficiosAdminXActualizar(String condicion);

    List<FecetTipoOficio> getOficiosAdminByMetodo(BigDecimal idMetodo, String condicion1, String condicion2);

}
