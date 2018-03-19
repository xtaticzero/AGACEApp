/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenPropuestasFirmante;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposEstatusPropuestaEnum;

/**
 * @author sergio.vaca
 *
 */
public interface ValidarFirmarDoctoDao {

    List<ResumenPropuestasFirmante> obtenerResumenPropuestasFirmante(String rfcFirmante);

    List<FecetPropuesta> obtenerInformadasValidar(String rfc, TiposEstatusPropuestaEnum estatusPropuesta);

    List<FecetRetroContador> obtenerRetroByIdPropuesta(BigDecimal idPropuesta, TipoEstatusEnum estatus, BigDecimal blnEstatus);

    List<DocumentoVista> obtenerDoctosRetroByIdRetro(BigDecimal idRetroalimentacion, BigDecimal blnActivo);

    void actualizarEstatus(BigDecimal idPropuesta, int estatusNuevoPropuesta, BigDecimal idArace);

    void apagarRetroalimentacion(BigDecimal idRetroalimentacion);

    void actualizarAuditorFirma(BigDecimal idPropuesta);

    void actualizarAdminSubadminOrdenes(BigDecimal idPropuesta);
}
