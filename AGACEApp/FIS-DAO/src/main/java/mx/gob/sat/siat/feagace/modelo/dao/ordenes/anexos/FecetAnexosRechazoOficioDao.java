package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;

public interface FecetAnexosRechazoOficioDao {

    FecetAnexosRechazoOficio insert(FecetAnexosRechazoOficio dto);

    void update(FecetAnexosRechazoOficio dto);

    List<FecetAnexosRechazoOficio> getAnexosRechazoOficioByIdRechazoOficio(final BigDecimal idRechazoOficio);
}
