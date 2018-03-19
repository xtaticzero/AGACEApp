package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.flujo.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;

public interface FlujoProrrogaOrdenDAO {

    BigDecimal insert(FecetFlujoProrrogaOrden dto);

    void update(FecetFlujoProrrogaOrden dto);

    void updateFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOrden(BigDecimal idProrrogaOrden);

    void delete(FecetFlujoProrrogaOrden dto);

    FecetFlujoProrrogaOrden findByPrimaryKey(BigDecimal idFlujoProrrogaOrden);

    List<FecetFlujoProrrogaOrden> findAll();

    FecetFlujoProrrogaOrden findByPrimaryKey(FecetFlujoProrrogaOrden dto);

    List<FecetFlujoProrrogaOrden> findWhereFechaCreacionEquals(Date fechaCreacion);

    List<FecetFlujoProrrogaOrden> findWhereJustificacionEquals(String justificacion);

    List<FecetFlujoProrrogaOrden> findWhereJustificacionFirmanteEquals(String justificacionFirmante);

    List<FecetFlujoProrrogaOrden> findWhereAprobadaEquals(String aprobada);

    List<FecetFlujoProrrogaOrden> findWhereIdEstatusEquals(BigDecimal idEstatus);

    List<FecetFlujoProrrogaOrden> findWhereFechaRechazoFirmanteEquals(Date fechaRechazoFirmante);

    BigDecimal getIdFecetFlujoProrrogaOrdenPathDirectorio();

    Long findLastIdFecetFlujoProrrogaOrden(BigDecimal idProrrogaOrden);

}
