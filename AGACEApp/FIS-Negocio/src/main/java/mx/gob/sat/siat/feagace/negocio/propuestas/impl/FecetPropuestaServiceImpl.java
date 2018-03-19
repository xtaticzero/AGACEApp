package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechPendientesValidacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechVerifProcedencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropTransferPendValidacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetPropuestaService;

/**
 * Clase que implementa la interface <code>FecetPropuestaService</code> para las consulta de las propuestas.
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
@Service("fecetPropuestaService")
public class FecetPropuestaServiceImpl extends BaseBusinessServices implements FecetPropuestaService {

    private static final long serialVersionUID = -7798302325883526721L;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetContribuyenteDao contribuyenteDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;

    /**
     * Metodo que obtiene las propuestas rechazadas en verificacion de procedencia de documento electronico
     * 
     * @param rfcFirmante
     *            registro federal del contribuyente
     * @return lista de objetos <code>FecetPropRechVerifProcedencia</code>
     */

    public List<FecetPropRechVerifProcedencia> obtenerPropRechVerifProc(String rfcFirmante) {
        return fecetPropuestaDao.obtenerPropRechVerifProc(
                new BigDecimal(TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO.getId()), rfcFirmante);
    }

    /**
     * Metodo que obtiene las propuestas rechazadas pendientes de validacion
     * 
     * @param rfcFirmante
     *            registro federal del contribuyente
     * @return lista de objetos <code>FecetPropRechPendientesValidacion</code>
     */

    public List<FecetPropRechPendientesValidacion> obtenerPropRechPendValid(String rfcFirmante) {
        return fecetPropuestaDao.obtenerPropRechPendValid(new BigDecimal(TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION.getId()), rfcFirmante);
    }

    /**
     * Metodo que obtiene las propuestas rechazadas pendientes de validacion
     * 
     * @param rfcFirmante
     *            registro federal del contribuyente
     * @return lista de objetos <code>FecetPropRechPendientesValidacion</code>
     */

    public List<FecetPropuesta> obtenerPropuestasRechazadasPendientesValid(String rfcFirmante) {
        return fecetPropuestaDao.obtenerPropuestasRechazadasPendientesValid(new BigDecimal(TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION.getId()),
                rfcFirmante);
    }

    /**
     * Metodo que obtiene las propuestas transferidas pendientes de validacion
     * 
     * @param rfcFirmante
     *            registro federal del contribuyente
     * @return lista de objetos <code>FecetPropRechPendientesValidacion</code>
     */

    public List<FecetPropTransferPendValidacion> obtenerPropTransferPendValid(String rfcFirmante) {
        return fecetPropuestaDao.obtenerPropTransferPendValid(new BigDecimal(TipoEstatusEnum.PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO.getId()),
                new BigDecimal(TipoEstatusEnum.PROPUESTA_REGISTRO_TRANSFERIDO.getId()), rfcFirmante);
    }

    public FecetPropuesta obtenerPropuestaByidPropuesta(BigDecimal idPropuesta) throws NegocioException {
        try {
            return fecetPropuestaDao.findWhereIdPropuestaEquals(idPropuesta).get(0);
        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
    }

    /**
     * Metodo que obtiene los datos del contribuyente
     * 
     * @param rfc
     *            registro federal del contribuyente
     * @return objeto de tipo FecetContribuyente
     * @throws NoExisteContribuyenteException
     */

    public FecetContribuyente getContribuyente(BigDecimal idContribuyente) throws NoExisteContribuyenteException {
        try {
            return contribuyenteDao.findByPrimaryKey(idContribuyente);
        } catch (Exception e) {
            logger.error("Error al consultar datos del contribuyente id {}: {}", idContribuyente, e.getCause());
            throw new NoExisteContribuyenteException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
    }

    /**
     *
     * @param idPropuesta
     * @return
     */

    public List<FecetImpuesto> getImpuestosByPropuesta(BigDecimal idPropuesta) throws NegocioException {
        try {
            return fecetImpuestoDao.getImpuestosPropuesta(idPropuesta);
        } catch (Exception e) {
            logger.error("Error al consultar datos de impuestos", e.getCause());
            throw new NegocioException(e.getCause().toString(), e);
        }
    }

}
