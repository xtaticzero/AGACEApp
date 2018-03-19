package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechPendientesValidacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechVerifProcedencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropTransferPendValidacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;

/**
 * Interface para la capa de servicio en el que se consumen servicios para la
 * capa DAO para propuestas
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public interface FecetPropuestaService {

    List<FecetPropRechVerifProcedencia> obtenerPropRechVerifProc(String rfcFirmante);

    List<FecetPropRechPendientesValidacion> obtenerPropRechPendValid(String rfcFirmante);

    List<FecetPropuesta> obtenerPropuestasRechazadasPendientesValid(String rfcFirmante);

    List<FecetPropTransferPendValidacion> obtenerPropTransferPendValid(String rfcFirmante);

    FecetPropuesta obtenerPropuestaByidPropuesta(BigDecimal idPropuesta) throws NegocioException;

    FecetContribuyente getContribuyente(BigDecimal idContribuyente) throws NoExisteContribuyenteException;

    List<FecetImpuesto> getImpuestosByPropuesta(BigDecimal idPropuesta) throws NegocioException;

}
