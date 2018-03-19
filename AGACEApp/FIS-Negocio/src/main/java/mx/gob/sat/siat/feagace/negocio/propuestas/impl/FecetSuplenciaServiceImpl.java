package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetSuplenciaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetSuplenciaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;

/**
 * Implementacion de la interface <code>FecetSuplenciaService</code> para la
 * capa de servicio respecto a los datos de la suplencia de un firmante suplente
 * 
 * @author Luis Rodriguez
 * @version 1.0
 */
@Service("fecetSuplenciaService")
public class FecetSuplenciaServiceImpl extends PropuestasServiceAbstract implements FecetSuplenciaService {

    /**
     * 
     */
    private static final long serialVersionUID = 6042039698384302251L;
    @Autowired
    private transient FecetSuplenciaDao fecetSuplenciaDao;

    @Override
    public List<FecetSuplencia> obtenerSuplentesPorSuplente(BigDecimal suplente) {
        return obtieneRFCSuplente(fecetSuplenciaDao.obtenerSuplentesPorSuplente(suplente));
    }

    public List<FecetSuplencia> obtieneRFCSuplente(List<FecetSuplencia> listSuplente) {

        for (FecetSuplencia suplencia : listSuplente) {
            try {
                suplencia.setRfcSuplente(getEmpleadoCompleto(suplencia.getIdFirmanteASuplir().intValue()).getRfc());
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
        }

        return listSuplente;
    }

    public EmpleadoDTO getEmpleadoFirmante(final String rfc) throws NegocioException {

        EmpleadoDTO empleado = null;

        try {

            if (validarExistenciaTipoEmpleado(getEmpleadoCompleto(rfc), TipoEmpleadoEnum.FIRMANTE)) {
                empleado = getEmpleadoCompleto(rfc);
            } else {
                throw new NegocioException("No se pudo obtener la informacion del usuario logueado");
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            throw new NegocioException("No se pudo obtener la informacion del usuario logueado", e);
        }

        return empleado;

    }

}
