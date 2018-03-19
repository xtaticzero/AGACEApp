package mx.gob.sat.siat.feagace.negocio.propuestas.carga;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CargaDocumentoElectronicoService {

    CargaDocumentoElectronicoDTO getContribuyente(String rfc, String idRegistro, BigDecimal idPropuesta)
            throws NegocioException;

    FecetContribuyente validarIDC(String rfc);

    CargaDocumentoElectronicoDTO previosPropuesta(String rfc, BigDecimal idPropuesta);

    CargaDocumentoElectronicoDTO validarIDCRepresent(String rfc);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.GENERAR_REVISION)
    BigDecimal actualizarPropuesta(BigDecimal idPropuesta, String rfc, EmpleadoDTO auditor, List<FecetDocOrden> listaDocumento,
            List<ColaboradorVO> colaboradores, List<FecetOficio> listaOficios);

    void obtenerDatosPreviosDePropuesta(CargaDocumentoElectronicoDTO cargaDocElectronicoDTO);

    CargaDocumentoElectronicoDTO updateDocParaPropuesta(String rfc);

    void cargaDocumento(String destino, InputStream is, String nombreArchivo) throws NegocioException;

    List<FecetDocExpediente> getExpedientePropuesta(final BigDecimal idPropuesta);

    void cargaColaborador(ColaboradorVO colaborador, AgaceOrden orden);

    boolean validarPermiteBusquedaAgenteAduanal(FecetPropuesta propuesta);

    void actualizarDatosCobtribuyente(final String rfc, final BigDecimal idPropuesta);

    boolean validarActualizacionDatosContribuyente(final String rfc, BigDecimal idPropuesta);

    List<FecetTipoOficio> getOficiosAdministrables(BigDecimal idMetodo, String condicion1, String condicion2);

    boolean validarCargaOficios();

    List<AraceDTO> getCatalogoUnidadAdministrativa();
  
}
