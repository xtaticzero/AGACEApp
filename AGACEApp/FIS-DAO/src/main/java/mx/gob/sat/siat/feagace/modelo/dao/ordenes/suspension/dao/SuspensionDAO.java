package mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;

public interface SuspensionDAO {

    List<FecetSuspensionDTO> buscarSuspension(final BigDecimal idOrden);

    int guardarSuspensionXAcuerdo(final BigDecimal idOrden, final Date fechaInicioSuspension, final Date fechaFinSuspension, final String numeroAcuerdo);

    void guardarSuspension(final BigDecimal idOrden, final Date fechaInicioSuspension, final Date fechaFinSuspension, final BigDecimal idOficio);

    void guardarSuspension(final BigDecimal idOrden, final BigDecimal idObjeto, final Date fechaInicioSuspension, final Date fechaFinSuspension, final BigDecimal idOficio);

    void guardarFechaFinSuspension(final BigDecimal idOrden, final Date fechaFinSuspension);

    List<FecetSuspensionDTO> buscarSuspensionPorIdTipoOficio(final BigDecimal idOrden, final BigDecimal idObjeto);

    List<FecetSuspensionDTO> buscarSuspensionAllPorIdTipoOficio(final BigDecimal idOrden, final BigDecimal idObjeto);

    List<FecetSuspensionDTO> buscarSuspensionesPorId(final BigDecimal idOrden);

    void reactivaPlazoOficio(final BigDecimal idOrden, final BigDecimal idOficio);

    List<FecetSuspensionDTO> buscarSuspensionesReactivacion(final BigDecimal idOrden);

    List<FecetSuspensionDTO> buscarSuspensionReactivacion(final BigDecimal idOrden);

    Integer countFolioAcuerdoConclusivo(String numeroAcuerdoConclusivo);

    void actualizarFechas(FecetSuspensionDTO suspension);
}
