package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsignarSuplenciaAFirmanteModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplenciaDTO;

public interface AsignarSuplenciaAFirmanteDAO {

    void insertar(FecetSuplenciaDTO dto);

    void cancelarSuplencia(String estadoCancelado, String idSuplencia);

    void cancelarSuplenciaScheduler();

    void activarSuplenciaScheduler();

    void terminarSuplenciaScheduler();

    BigDecimal buscarSuplenciaRegistradaFirmanteSuplente(BigDecimal idEmpleado, Date fechaInicio, Date fechaFinl);

    BigDecimal buscarSuplenciaRegistradaFirmanteASuplir(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel);

    FecetSuplenciaDTO findSuplencia(BigDecimal idSuplencia);

    List<FecetSuplenciaDTO> findListaSuplencia(StringBuffer valor);

    BigDecimal cargaFirmantesSuplentesDisponibles(BigDecimal idEmpleado, Date fechaInicio, Date fechaFin);

}
