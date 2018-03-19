package mx.gob.sat.siat.feagace.negocio.propuestas.carga.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.model.Rep_legal;
import com.softtek.idc.model.Ubicacion;
import com.softtek.idc.service.IDCService;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAdminOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetTipoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.RepLegalVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DatosPropuestaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.CargaDocumentoElectronicoHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarDocumentoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.rules.CargaDocumentoElectronicoRules;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("cargaDocumentoElectronicoService")
public class CargaDocumentoElectronicoServiceImpl extends PropuestasServiceAbstract
        implements CargaDocumentoElectronicoService {

    private static final long serialVersionUID = -8342376609494165591L;

    @Autowired
    private transient IDCService idcService;
    @Autowired
    private CargaDocumentoElectronicoRules cargaDocumentoElectronicoRules;
    @Autowired
    private CargaDocumentoElectronicoHelper cargaDocumentoElectronicoHelper;
    @Autowired
    private NotificacionService notificacionService;
    @Autowired
    private AsignarDocumentoService asignarDocumentoService;
    @Autowired
    private transient FecetPropuestaDao propuestaDao;
    @Autowired
    private transient FecetContribuyenteDao contribuyenteDao;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;
    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecetTipoOficioDao fecetTipoOficioDao;
    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;
    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;
    @Autowired
    private transient FecetAdminOficioDao fecetAdminOficioDao;

    private static final String SIN_METODO_DE_REVISION = "Sin Metodo de Revision";
    private static final String SIN_SUBPROGRAMA = "Sin Subprograma";
    private static final String SEPARADOR_GUION_MEDIO = " - ";
    private static final String SIN_TIPO_PROPUESTA = "Sin Tipo Propuesta";
    private static final String SIN_PROGRAMACION = "Sin Programacion";
    private static final String SIN_TIPO_REVISION = "N/A";

    @Override
    public void actualizarDatosCobtribuyente(final String rfc, final BigDecimal idPropuesta) {

        FecetContribuyente contribuyenteIDC = validarIDC(rfc);

        FecetPropuesta fecetPropuesta = getPropuestaDao().findWhereIdPropuestaEquals(idPropuesta).get(0);
        FecetContribuyente fecetContribuyente = getContribuyenteDao()
                .findWhereIdContribuyenteEquals(fecetPropuesta.getIdContribuyente()).get(0);
        if (fecetContribuyente.getRfc().equals(contribuyenteIDC.getRfc())) {
            fecetContribuyente.setNombre(contribuyenteIDC.getNombre());
            fecetContribuyente.setRegimen(contribuyenteIDC.getRegimen());
            fecetContribuyente.setSituacion(contribuyenteIDC.getSituacion());
            fecetContribuyente.setTipo(contribuyenteIDC.getTipo());
            fecetContribuyente.setSituacionDomicilio(contribuyenteIDC.getSituacionDomicilio());
            fecetContribuyente.setDomicilioFiscal(contribuyenteIDC.getDomicilioFiscal());
            fecetContribuyente.setActividadPreponderante(contribuyenteIDC.getActividadPreponderante());
            fecetContribuyente.setEntidad(contribuyenteIDC.getEntidad());
            getContribuyenteDao().actualizaDatosContribuyente(fecetContribuyente);
        }
    }

    @Override
    public CargaDocumentoElectronicoDTO getContribuyente(String rfc, String idRegistro, BigDecimal idPropuesta)
            throws NegocioException {
        CargaDocumentoElectronicoDTO cargaDocumentoElectronicoDTO;
        cargaDocumentoElectronicoDTO = new CargaDocumentoElectronicoDTO();
        try {
            FecetPropuesta fecetPropuesta = getPropuestaDao().findWhereIdPropuestaEquals(idPropuesta).get(0);
            fecetPropuesta.getIdContribuyente();
            FecetContribuyente fecetContribuyente = getContribuyenteDao()
                    .findWhereIdContribuyenteEquals(fecetPropuesta.getIdContribuyente()).get(0);
            cargaDocumentoElectronicoDTO.setFecetContribuyente(fecetContribuyente);
            cargaDocumentoElectronicoDTO.setRfc(fecetContribuyente.getRfc());
            cargaDocumentoElectronicoDTO.setNombre(fecetContribuyente.getNombre());
            cargaDocumentoElectronicoDTO.setRegimen(fecetContribuyente.getRegimen());
            cargaDocumentoElectronicoDTO.setSituacion(fecetContribuyente.getSituacion());
            cargaDocumentoElectronicoDTO.setTipo(fecetContribuyente.getTipo());
            cargaDocumentoElectronicoDTO.setSituacionDomicilio(fecetContribuyente.getSituacionDomicilio());
            cargaDocumentoElectronicoDTO.setDomicilioFiscal(fecetContribuyente.getDomicilioFiscal());
            cargaDocumentoElectronicoDTO.setEntidad(fecetContribuyente.getEntidad());
            cargaDocumentoElectronicoDTO.setActividadPreponderante(fecetContribuyente.getActividadPreponderante());
            cargaDocumentoElectronicoDTO.setIdRegistro(idRegistro);
        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
        return cargaDocumentoElectronicoDTO;
    }

    public boolean validarActualizacionDatosContribuyente(final String rfc, BigDecimal idPropuesta) {
        FecetContribuyente contribuyenteIDC = validarIDC(rfc);
        FecetPropuesta fecetPropuesta = getPropuestaDao().findWhereIdPropuestaEquals(idPropuesta).get(0);
        FecetContribuyente fecetContribuyente = getContribuyenteDao()
                .findWhereIdContribuyenteEquals(fecetPropuesta.getIdContribuyente()).get(0);
        return !fecetContribuyente.equalsALL(contribuyenteIDC);
    }

    @Override
    public FecetContribuyente validarIDC(String rfc) {
        logger.debug("RFC// " + rfc);
        FecetContribuyente fecetContribuyente;
        fecetContribuyente = new FecetContribuyente();
        try {
            IdCInterno idCInterno = idcService.obtenerInformacionContribuyente(rfc,
                    new String[]{IDCConstants.IDENTIFICACION, IDCConstants.DATOS_COMPLEMENTARIOS,
                        IDCConstants.UBICACION, IDCConstants.REPRESENTANTES_LEGALES});
            fecetContribuyente.setRfc(rfc);
            String nombreCompleto = idCInterno.getIdentificacion().getNombre();
            String apellidoPaterno = idCInterno.getIdentificacion().getAp_Paterno();
            String apellidoMaterno = idCInterno.getIdentificacion().getAp_Materno();

            if (nombreCompleto == null && apellidoPaterno == null && apellidoMaterno == null) {
                fecetContribuyente.setNombre("Sin nombre definido");
            } else {
                fecetContribuyente.setNombre(nombreCompleto + " " + apellidoPaterno + " " + apellidoMaterno);
            }
            if (idCInterno.getRegimenes() == null) {
                fecetContribuyente.setRegimen("Sin regimen");
            } else {
                fecetContribuyente.setRegimen(idCInterno.getRegimenes().get(0).getD_Regimen() != null
                        ? idCInterno.getRegimenes().get(0).getD_Regimen().trim() : "");
            }
            fecetContribuyente.setSituacion(idCInterno.getIdentificacion().getD_Sit_Cont() != null
                    ? idCInterno.getIdentificacion().getD_Sit_Cont().trim() : "");
            fecetContribuyente.setTipo(idCInterno.getIdentificacion().getT_persona() != null
                    ? idCInterno.getIdentificacion().getT_persona().trim().equals("M") ? "PERSONA MORAL"
                            : "PERSONA FISICA"
                    : "");
            fecetContribuyente.setSituacionDomicilio(idCInterno.getIdentificacion().getD_Sit_Dom() != null
                    ? idCInterno.getIdentificacion().getD_Sit_Dom().trim() : "");
            if (idCInterno.getUbicacion() != null) {
                fecetContribuyente.setDomicilioFiscal(generaDireccionContribuyente(idCInterno.getUbicacion()));
            } else {
                fecetContribuyente.setDomicilioFiscal("Sin Domicilio");
            }
            fecetContribuyente.setEntidad(idCInterno.getUbicacion() != null
                    ? idCInterno.getUbicacion().getD_Ent_Fed().trim() : "Sin Ubicacion");
            fecetContribuyente.setActividadPreponderante(idCInterno.getActividades() != null
                    ? idCInterno.getActividades().get(0).getD_Actividad().trim() : "Sin actividad preponderante");
            fecetContribuyente.setRepLegal(obtenerListaRepLegal(idCInterno));
        } catch (Exception e) {
            logger.error("Error al validar IDC: " + e);
        }
        return fecetContribuyente;
    }

    private List<RepLegalVO> obtenerListaRepLegal(IdCInterno contribuyenteIDC) {
        List<RepLegalVO> repLegalLista;
        if (contribuyenteIDC.getRep_Legales() != null) {
            repLegalLista = new ArrayList<RepLegalVO>();
            for (Rep_legal repLegal : contribuyenteIDC.getRep_Legales()) {
                RepLegalVO repLegalVO = new RepLegalVO();
                repLegalVO.setCurp(repLegal.getCURP());
                repLegalVO.setFechaFin(repLegal.getF_Fin());
                repLegalVO.setFechaInicio(repLegal.getF_Inicio());
                repLegalVO.setNombre(repLegal.getNombre());
                repLegalVO.setRfc(repLegal.getRFC());
                repLegalVO.setTipo(repLegal.getTipo());
                repLegalLista.add(repLegalVO);
            }
        } else {
            repLegalLista = null;
        }
        return repLegalLista;

    }

    private String generaDireccionContribuyente(final Ubicacion ubicacion) {
        StringBuilder direccion = new StringBuilder();
        if (ubicacion != null) {
            direccion.append((ubicacion.getCalle() != null) ? ubicacion.getCalle().trim() : "").append(" # ");
            direccion.append((ubicacion.getN_Exterior() != null) ? ubicacion.getN_Exterior().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Colonia() != null) ? ubicacion.getD_Colonia().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Municipio() != null) ? ubicacion.getD_Municipio().trim() : "")
                    .append(", ");
            direccion.append((ubicacion.getD_Ent_Fed() != null) ? ubicacion.getD_Ent_Fed().trim() : "")
                    .append(", C.P. ");
            direccion.append((ubicacion.getCp() != null) ? ubicacion.getCp().trim() : "");
        }
        return direccion.toString();
    }

    public CargaDocumentoElectronicoDTO previosPropuesta(String rfc, BigDecimal idPropuesta) {
        CargaDocumentoElectronicoDTO cargaDocumentoElectronicoDTO = new CargaDocumentoElectronicoDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        FecetContribuyente fecetContribuyente = getContribuyenteDao().findWhereRfcEquals(rfc).get(0);
        FecetPropuesta fecetPropuesta = fecetPropuestaDao.findWhereIdPropuestaEquals(idPropuesta).get(0);
        fecetPropuesta.setFecetContribuyente(fecetContribuyente);
        cargaDocumentoElectronicoDTO.setFecetPropuesta(fecetPropuesta);

        if (fecetPropuesta.getIdRevision() == null || fecetPropuesta.getIdRevision().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setTipoRevision("Sin Revision");
        } else {
            FececRevision fececRevision = fececRevisionDao
                    .findWhereIdRevisionEquals(BigDecimal.valueOf(fecetPropuesta.getIdRevision().intValue())).get(0);
            cargaDocumentoElectronicoDTO.setTipoRevision(fececRevision.getIdRevision().intValue() != 0
                    ? fececRevision.getDescripcion() : SIN_METODO_DE_REVISION);
        }
        if (fecetPropuesta.getIdSubprograma() == null || fecetPropuesta.getIdSubprograma().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setSubprograma(SIN_SUBPROGRAMA);
        } else {
            FececSubprograma fecetSuprograma = fececSubprogramaDao
                    .findWhereIdSubprogramaEquals(BigDecimal.valueOf(fecetPropuesta.getIdSubprograma().intValue()))
                    .get(0);
            cargaDocumentoElectronicoDTO.setSubprograma(fecetSuprograma.getIdSubprograma().intValue() != 0
                    ? fecetSuprograma.getClave().concat(SEPARADOR_GUION_MEDIO).concat(fecetSuprograma.getDescripcion())
                    : SIN_SUBPROGRAMA);
        }
        if (fecetPropuesta.getIdTipoPropuesta() == null || fecetPropuesta.getIdTipoPropuesta().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setTipoPropuesta(SIN_TIPO_PROPUESTA);
        } else {
            FececTipoPropuesta fececTipoPropuesta = fececTipoPropuestaDao
                    .findWhereIdTipoPropuestaEquals(BigDecimal.valueOf(fecetPropuesta.getIdTipoPropuesta().intValue()))
                    .get(0);
            cargaDocumentoElectronicoDTO.setTipoPropuesta(fececTipoPropuesta.getIdTipoPropuesta().intValue() != 0
                    ? fececTipoPropuesta.getDescripcion() : SIN_TIPO_PROPUESTA);
        }
        if (fecetPropuesta.getIdCausaProgramacion() == null
                || fecetPropuesta.getIdCausaProgramacion().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setProgramacion(SIN_PROGRAMACION);
        } else {
            FececCausaProgramacion fececCausaProgramacion = fececCausaProgramacionDao
                    .findWhereIdCausaProgramacionEquals(
                            BigDecimal.valueOf(fecetPropuesta.getIdCausaProgramacion().intValue()))
                    .get(0);
            cargaDocumentoElectronicoDTO.setProgramacion(fececCausaProgramacion.getIdCausaProgramacion().intValue() != 0
                    ? fececCausaProgramacion.getDescripcion() : SIN_PROGRAMACION);
        }
        if (fecetPropuesta.getIdMetodo() == null || fecetPropuesta.getIdMetodo().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setRevision(SIN_METODO_DE_REVISION);
        } else {
            FececMetodo fececMetodo = feceaMetodoDao
                    .findWhereIdMetodo(BigDecimal.valueOf(fecetPropuesta.getIdMetodo().intValue())).get(0);
            cargaDocumentoElectronicoDTO
                    .setRevision(fececMetodo.getIdMetodo() != null ? fececMetodo.getNombre() : SIN_METODO_DE_REVISION);
        }
        cargaDocumentoElectronicoDTO.setPeriodoInicioPropuesta(sdf.format(fecetPropuesta.getFechaInicioPeriodo()));
        cargaDocumentoElectronicoDTO.setPeriodoFinPropuesa(sdf.format(fecetPropuesta.getFechaFinPeriodo()));
        cargaDocumentoElectronicoDTO.setPrioridad(fecetPropuesta.getPrioridad());
        cargaDocumentoElectronicoDTO.setIdRegistro(fecetPropuesta.getIdRegistro());

        List<FecetImpuesto> listaImpuestos = fecetImpuestoDao
                .findWhereIdPropuestaEquals(fecetPropuesta.getIdPropuesta());
        List<ImpuestoDTO> listaImpuestosDTO = new ArrayList<ImpuestoDTO>();
        for (FecetImpuesto imp : listaImpuestos) {
            ImpuestoDTO impuestoDTO = new ImpuestoDTO();
            impuestoDTO.setIdImpuesto(imp.getIdImpuesto());
            impuestoDTO.setMonto(nf.format(imp.getMonto().intValue()));
            impuestoDTO.setPeriodoInicio(sdf.format(imp.getPeriodoInicial()));
            impuestoDTO.setPeriodoFin(sdf.format(imp.getPeriodoFinal()));

            FececTipoImpuesto tipoImpuesto = fececTipoImpuestoDao.findWhereIdTipoImpuestoEquals(imp.getIdTipoImpuesto())
                    .get(0);
            StringBuilder desc = new StringBuilder();
            desc.append(tipoImpuesto.getAbreviatura()).append(SEPARADOR_GUION_MEDIO)
                    .append(tipoImpuesto.getDescripcion());
            impuestoDTO.setDescripcion(desc.toString());
            BigDecimal totalPresuntiva = asignarDocumentoService
                    .calcularPresuntivaDePropuesta(fecetPropuesta.getIdPropuesta());

            cargaDocumentoElectronicoDTO.setPresuntiva(nf.format(totalPresuntiva));
            listaImpuestosDTO.add(impuestoDTO);
        }
        cargaDocumentoElectronicoDTO.setListaImpuestos(listaImpuestosDTO);
        return cargaDocumentoElectronicoDTO;
    }

    public List<FecetPropuesta> obtieneArace(List<FecetPropuesta> propuesta) {
        for (FecetPropuesta lista : propuesta) {
            for (AraceDTO arace : getCatalogoUnidadAdministrativa()) {
                if (lista.getIdArace().intValue() == arace.getIdArace()) {
                    FececArace araces = new FececArace();
                    araces.setIdArace(new BigDecimal(arace.getIdArace()));
                    araces.setNombre(arace.getNombre());
                    lista.setFececArace(araces);
                }
            }
        }
        return propuesta;

    }

    public void obtenerDatosPreviosDePropuesta(CargaDocumentoElectronicoDTO cargaDocElectronicoDTO) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));

        FecetPropuesta fecetPropuesta = obtieneArace(fecetPropuestaDao.findWhereIdPropuestaEquals(cargaDocElectronicoDTO.getDatosPreviosPropuesta().getIdPropuesta())).get(0);
        cargaDocElectronicoDTO.setFecetPropuesta(fecetPropuesta);

        DatosPropuestaDTO datosDTO = new DatosPropuestaDTO();
        datosDTO.setIdPropuesta(cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta());
        if (fecetPropuesta.getIdRevision() == null || fecetPropuesta.getIdRevision().intValue() == 0) {
            datosDTO.setTipoRevision(SIN_TIPO_REVISION);
        } else {
            FececRevision fececRevision = fececRevisionDao
                    .findWhereIdRevisionEquals(BigDecimal.valueOf(fecetPropuesta.getIdRevision().intValue())).get(0);
            cargaDocElectronicoDTO.getFecetPropuesta().setFececRevision(fececRevision);
            datosDTO.setTipoRevision(
                    fececRevision.getIdRevision().intValue() != 0 ? fececRevision.getDescripcion() : "");
        }

        if (fecetPropuesta.getIdSubprograma() == null || fecetPropuesta.getIdSubprograma().intValue() == 0) {
            datosDTO.setSubprograma("");
        } else {
            FececSubprograma fecetSuprograma = fececSubprogramaDao
                    .findWhereIdSubprogramaEquals(BigDecimal.valueOf(fecetPropuesta.getIdSubprograma().intValue()))
                    .get(0);
            cargaDocElectronicoDTO.getFecetPropuesta().setFececSubprograma(fecetSuprograma);
            datosDTO.setSubprograma(
                    fecetSuprograma.getIdSubprograma().intValue() != 0 ? fecetSuprograma.getDescripcion() : "");
        }

        if (fecetPropuesta.getIdTipoPropuesta() == null || fecetPropuesta.getIdTipoPropuesta().intValue() == 0) {
            datosDTO.setTipoPropuesta(SIN_TIPO_PROPUESTA);

        } else {
            FececTipoPropuesta fececTipoPropuesta = fececTipoPropuestaDao
                    .findWhereIdTipoPropuestaEquals(BigDecimal.valueOf(fecetPropuesta.getIdTipoPropuesta().intValue()))
                    .get(0);
            cargaDocElectronicoDTO.getFecetPropuesta().setFececTipoPropuesta(fececTipoPropuesta);
            datosDTO.setTipoPropuesta(fececTipoPropuesta.getIdTipoPropuesta().intValue() != 0
                    ? fececTipoPropuesta.getDescripcion() : SIN_TIPO_PROPUESTA);

        }

        if (fecetPropuesta.getIdCausaProgramacion() == null
                || fecetPropuesta.getIdCausaProgramacion().intValue() == 0) {
            datosDTO.setProgramacion(SIN_PROGRAMACION);
        } else {

            FececCausaProgramacion fececCausaProgramacion = fececCausaProgramacionDao
                    .findWhereIdCausaProgramacionEquals(
                            BigDecimal.valueOf(fecetPropuesta.getIdCausaProgramacion().intValue()))
                    .get(0);
            datosDTO.setProgramacion(fececCausaProgramacion.getIdCausaProgramacion().intValue() != 0
                    ? fececCausaProgramacion.getDescripcion() : SIN_PROGRAMACION);
        }
        if (fecetPropuesta.getIdMetodo() == null || fecetPropuesta.getIdMetodo().intValue() == 0) {
            datosDTO.setRevision(SIN_METODO_DE_REVISION);
        } else {
            FececMetodo fececMetodo = feceaMetodoDao
                    .findWhereIdMetodo(BigDecimal.valueOf(fecetPropuesta.getIdMetodo().intValue())).get(0);
            cargaDocElectronicoDTO.getFecetPropuesta().setFeceaMetodo(fececMetodo);
            datosDTO.setRevision(fececMetodo.getIdMetodo() != null ? fececMetodo.getNombre() : "");
        }
        datosDTO.setSector(fecetPropuesta.getFececSector().getDescripcion());
        datosDTO.setArace(fecetPropuesta.getFececArace().getNombre());
        datosDTO.setPeriodoInicioPropuesta(sdf.format(fecetPropuesta.getFechaInicioPeriodo()));
        datosDTO.setPeriodoFinPropuesa(sdf.format(fecetPropuesta.getFechaFinPeriodo()));
        datosDTO.setPrioridadSugerida(fecetPropuesta.getPrioridadSugerida());
        datosDTO.setFechaPresentacion(fecetPropuesta.getFechaPresentacion());
        datosDTO.setFechaInforme(fecetPropuesta.getFechaInforme());

        List<FecetImpuesto> listaImpuestos = fecetImpuestoDao.getImpuestosPropuesta(fecetPropuesta.getIdPropuesta());

        List<ImpuestoDTO> listaImpuestosDTO = new ArrayList<ImpuestoDTO>();
        for (FecetImpuesto imp : listaImpuestos) {
            ImpuestoDTO impuestoDTO = new ImpuestoDTO();
            impuestoDTO.setIdImpuesto(imp.getIdImpuesto());
            impuestoDTO.setMonto(nf.format(imp.getMonto().intValue()));
            impuestoDTO.setDescripcion(imp.getFececTipoImpuesto().getDescripcion());
            impuestoDTO.setConcepto(imp.getFececConcepto().getDescripcion());
            listaImpuestosDTO.add(impuestoDTO);
        }
        BigDecimal totalPresuntiva = asignarDocumentoService
                .calcularPresuntivaDePropuesta(fecetPropuesta.getIdPropuesta());
        datosDTO.setListaImpuestos(listaImpuestosDTO);
        cargaDocElectronicoDTO.setDatosPreviosPropuesta(datosDTO);
        cargaDocElectronicoDTO.setPresuntiva(nf.format(totalPresuntiva));
    }

    @Override
    public CargaDocumentoElectronicoDTO validarIDCRepresent(String rfc) {

        logger.debug("RFC// " + rfc);

        CargaDocumentoElectronicoDTO datosRepresentante;
        datosRepresentante = new CargaDocumentoElectronicoDTO();

        String secciones[] = {IDCConstants.IDENTIFICACION, IDCConstants.PATENTE, IDCConstants.DATOS_COMPLEMENTARIOS};
        try {

            String nombre = null;
            String apellidoPaterno = null;
            String apellidoMaterno = null;
            IdCInterno idCInterno = idcService.obtenerInformacionContribuyente(rfc, secciones);
            if (idCInterno.getIdentificacion() != null) {
                nombre = idCInterno.getIdentificacion().getNombre();
                apellidoPaterno = idCInterno.getIdentificacion().getAp_Paterno();
                apellidoMaterno = idCInterno.getIdentificacion().getAp_Materno();

                datosRepresentante.setNombreRepresent(nombre + " " + apellidoPaterno + " " + apellidoMaterno);
            }

            if (idCInterno.getIdentificacion().getEmail() != null) {
                datosRepresentante.setEmailRepresent(idCInterno.getIdentificacion().getEmail());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return datosRepresentante;
    }

    @Override
    @PistaAuditoria
    public BigDecimal actualizarPropuesta(BigDecimal idPropuesta, String rfc, EmpleadoDTO auditor,
            List<FecetDocOrden> listaDocumento, List<ColaboradorVO> colaboradores, List<FecetOficio> listaOficios) {
        FecetPropuesta fecetPropuesta = fecetPropuestaDao.findWhereIdPropuestaEquals(idPropuesta).get(0);
        try {

            FecebAccionPropuesta accionHistoria = new FecebAccionPropuesta();
            logger.info("Acualizando estatus");
            propuestaDao.actualizarEstatus(BigDecimal.valueOf(fecetPropuesta.getIdPropuesta().intValue()),
                    Constantes.ESTATUS_PROPUESTA_ARCH_ADJ);
            AgaceOrden orden = agaceOrdenDao.findByIdPropuesta(idPropuesta);
            if (orden == null) {
                orden = crearNuevaOrden(fecetPropuesta, auditor);
            }
            guardaDocumentos(listaDocumento, orden);
            insertaOficios(listaOficios, orden);
            asociaColaboradores(rfc, colaboradores, orden);

            accionHistoria.setIdPropuesta(idPropuesta);
            accionHistoria.setIdDetalleAccion(null);
            accionHistoria.setIdAccion(AccionesFuncionarioEnum.VALIDACION_FIRMANTE.getIdAccion());
            accionHistoria.setFechaHora(new Date());
            accionHistoria.setIdAccionOrigen(null);
            accionHistoria.setIdEmpleado(auditor.getIdEmpleado());

            fecebAccionPropuestaDao.insert(accionHistoria);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(idPropuesta, accionHistoria.getIdAccion(), null);

            enviarCorreoNotificaion(fecetPropuesta,
                    LeyendasPropuestasEnum.ASIGNACION_DOCUMENTO_VALIDACION_FIRMANTE.getIdLeyenda());

        } catch (Exception e) {
            logger.error("Colaboradores" + e.toString());
            for (StackTraceElement er : e.getStackTrace()) {
                logger.error("Co " + er.getLineNumber() + " " + e.getClass());
            }

        }
        return idPropuesta;
    }

    private AgaceOrden crearNuevaOrden(FecetPropuesta fecetPropuesta, EmpleadoDTO auditor) {
        logger.info("Creando nueva Orden");

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> jefesInmediatos = auditor.getLstJefesInmediatos();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> auditores = jefesInmediatos.get(TipoEmpleadoEnum.AUDITOR);
        List<EmpleadoDTO> lstFirmantes = auditores.get(TipoEmpleadoEnum.FIRMANTE);

        AgaceOrden agaceOrden = new AgaceOrden();
        agaceOrden.setIdMetodo(fecetPropuesta.getIdMetodo());
        agaceOrden.setFechaCreacion(new Date());
        agaceOrden.setPrioridad(true);
        agaceOrden.setPrioridadSugerida(fecetPropuesta.getPrioridadSugerida());
        agaceOrden.setDiasHabiles(false);
        agaceOrden.setSuspencionPlazo(false);
        agaceOrden.setSemaforo(1);
        agaceOrden.setIdContribuyente(fecetPropuesta.getIdContribuyente());
        agaceOrden.setIdEstatus(BigDecimal.valueOf(Constantes.ESTATUS_PROPUESTA_ARCH_ADJ));
        agaceOrden.setIdAuditor(auditor.getIdEmpleado());

        for (EmpleadoDTO firmanteAsignado : lstFirmantes) {

            if (firmanteAsignado.getRfc().equals(fecetPropuesta.getRfcFirmante())) {
                agaceOrden.setIdFirmante(firmanteAsignado.getIdEmpleado());
            }

        }

        agaceOrden.setIdPropuesta(fecetPropuesta.getIdPropuesta());
        agaceOrden.setIdRegistroPropuesta(fecetPropuesta.getIdRegistro());
        agaceOrdenDao.insert(agaceOrden);
        logger.info("ORDEN " + agaceOrden.getIdOrden());
        return agaceOrden;
    }

    private void asociaColaboradores(String rfcContribuyente, List<ColaboradorVO> colaboradores, AgaceOrden orden) {
        logger.info("Asociando colaboradores");
        if (colaboradores != null) {
            for (ColaboradorVO colaborador : colaboradores) {
                if (colaborador.getNombre() != null && colaborador.getRfc() != null) {
                    FecetAsociado nuevoAsociado = cargaDocumentoElectronicoHelper.crearAsociado(rfcContribuyente,
                            colaborador, orden);
                    if (nuevoAsociado != null) {
                        logger.info("insertado " + nuevoAsociado);
                        fecetAsociadoDao.insertaAgenteAduanal(nuevoAsociado);
                    }
                }
            }
        }
    }

    private void insertaOficios(List<FecetOficio> listaOficios, AgaceOrden orden) {
        logger.error("Insertando Oficios");
        if (listaOficios != null && !listaOficios.isEmpty()) {
            for (FecetOficio oficio : listaOficios) {
                oficio.setIdOrden(orden.getId());
                oficio.setIdAdminOficio(fecetAdminOficioDao.getAdminOficiosActivos().get(0).getIdAdminOficio());
                fecetOficioDao.insert(oficio);
            }
        }
    }

    private void guardaDocumentos(List<FecetDocOrden> documentos, AgaceOrden orden) {
        logger.info("Guardando documentos");
        fecetDocOrdenDao.updateEstatusDocumento(orden.getIdOrden());
        for (FecetDocOrden imp : documentos) {
            FecetDocOrden documento = new FecetDocOrden();
            documento.setIdOrden(orden.getIdOrden());
            documento.setIdDocOrden(imp.getIdDocOrden());
            documento.setNombreArchivo(imp.getNombreArchivo());
            documento.setRutaArchivo(imp.getRutaArchivo().concat(imp.getNombreArchivo()));
            documento.setEstatus(Constantes.CADENA_UNO);
            documento.setFechaCreacion(new Date());
            documento.setDocumentoPdf(Constantes.CADENA_CERO);
            documento.setBlnActivo(Constantes.UNO);
            fecetDocOrdenDao.insert(documento);
        }
    }

    public CargaDocumentoElectronicoDTO updateDocParaPropuesta(String rfc) {

        CargaDocumentoElectronicoDTO cargaDocumentoElectronicoDTO = new CargaDocumentoElectronicoDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        FecetContribuyente fecetContribuyente = getContribuyenteDao().findWhereRfcEquals(rfc).get(0);
        BigDecimal idContibuyente = BigDecimal.valueOf(fecetContribuyente.getIdContribuyente().intValue());

        FecetPropuesta fecetPropuesta = fecetPropuestaDao.findWhereIdContribuyenteEquals(idContibuyente).get(0);
        if (fecetPropuesta.getIdRevision() == null || fecetPropuesta.getIdRevision().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setTipoRevision("Sin Revision");
        } else {
            FececRevision fececRevision = fececRevisionDao
                    .findWhereIdRevisionEquals(BigDecimal.valueOf(fecetPropuesta.getIdRevision().intValue())).get(0);
            cargaDocumentoElectronicoDTO.setTipoRevision(fececRevision.getIdRevision().intValue() != 0
                    ? fececRevision.getDescripcion() : SIN_METODO_DE_REVISION);
        }
        if (fecetPropuesta.getIdSubprograma() == null || fecetPropuesta.getIdSubprograma().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setSubprograma(SIN_SUBPROGRAMA);
        } else {
            FececSubprograma fecetSuprograma = fececSubprogramaDao
                    .findWhereIdSubprogramaEquals(BigDecimal.valueOf(fecetPropuesta.getIdSubprograma().intValue()))
                    .get(0);
            cargaDocumentoElectronicoDTO.setSubprograma(fecetSuprograma.getIdSubprograma().intValue() != 0
                    ? fecetSuprograma.getClave().concat(SEPARADOR_GUION_MEDIO).concat(fecetSuprograma.getDescripcion())
                    : SIN_SUBPROGRAMA);
        }
        if (fecetPropuesta.getIdTipoPropuesta() == null || fecetPropuesta.getIdTipoPropuesta().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setTipoPropuesta(SIN_TIPO_PROPUESTA);
        } else {
            FececTipoPropuesta fececTipoPropuesta = fececTipoPropuestaDao
                    .findWhereIdTipoPropuestaEquals(BigDecimal.valueOf(fecetPropuesta.getIdTipoPropuesta().intValue()))
                    .get(0);
            cargaDocumentoElectronicoDTO.setTipoPropuesta(fececTipoPropuesta.getIdTipoPropuesta().intValue() != 0
                    ? fececTipoPropuesta.getDescripcion() : SIN_TIPO_PROPUESTA);
        }

        if (fecetPropuesta.getIdCausaProgramacion() == null
                || fecetPropuesta.getIdCausaProgramacion().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setProgramacion(SIN_PROGRAMACION);
        } else {
            FececCausaProgramacion fececCausaProgramacion = fececCausaProgramacionDao
                    .findWhereIdCausaProgramacionEquals(
                            BigDecimal.valueOf(fecetPropuesta.getIdCausaProgramacion().intValue()))
                    .get(0);
            cargaDocumentoElectronicoDTO.setProgramacion(fececCausaProgramacion.getIdCausaProgramacion().intValue() != 0
                    ? fececCausaProgramacion.getDescripcion() : SIN_PROGRAMACION);
        }
        if (fecetPropuesta.getIdMetodo() == null || fecetPropuesta.getIdMetodo().intValue() == 0) {
            cargaDocumentoElectronicoDTO.setRevision(SIN_METODO_DE_REVISION);
        } else {
            FececMetodo fececMetodo = feceaMetodoDao
                    .findWhereIdMetodo(BigDecimal.valueOf(fecetPropuesta.getIdMetodo().intValue())).get(0);
            cargaDocumentoElectronicoDTO
                    .setRevision(fececMetodo.getIdMetodo() != null ? fececMetodo.getNombre() : SIN_METODO_DE_REVISION);
        }
        cargaDocumentoElectronicoDTO.setPeriodoInicioPropuesta(sdf.format(fecetPropuesta.getFechaInicioPeriodo()));
        cargaDocumentoElectronicoDTO.setPeriodoFinPropuesa(sdf.format(fecetPropuesta.getFechaFinPeriodo()));
        cargaDocumentoElectronicoDTO.setPrioridad(fecetPropuesta.getPrioridad());

        return cargaDocumentoElectronicoDTO;
    }

    @Override
    public void cargaDocumento(String destino, InputStream is, String nombreArchivo) throws NegocioException {
        if (is != null) {
            OutputStream out = null;
            try {
                File fileToDirectory = new File(destino);
                boolean flgCarpetaCreada = fileToDirectory.mkdirs();

                if (flgCarpetaCreada) {
                    logger.debug("La estructura de directorios fue creada");
                } else {
                    logger.error("La estructura de directorios ".concat(destino)
                            .concat(" no pudo ser creada correctamente :"));
                }

                out = new FileOutputStream(new File(destino + nombreArchivo));
                if (fileToDirectory.exists() && fileToDirectory.isDirectory()) {
                    byte bytes[] = new byte[Constantes.BYTE];
                    int len = 0;
                    while ((len = is.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_ARCHIVO + e.getCause(), e);

            } catch (IOException ioe) {
                throw new NegocioException(ConstantesError.ERROR_ESCRIBIR_ARCHIVO + ioe.getCause(), ioe);
            } finally {
                try {
                    if (out != null) {
                        IOUtils.closeQuietly(out);
                    }
                } catch (Exception e) {
                    logger.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
                }
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(ConstantesError.ERROR_CERRAR_INPUTSTREAM, e);
                }
            }
        }
    }

    public List<FecetDocExpediente> getExpedientePropuesta(final BigDecimal idPropuesta) {
        return fecetDocExpedienteDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    /**
     *
     * @param colaborador
     * @param orden
     * @return
     */
    public void cargaColaborador(ColaboradorVO colaborador, AgaceOrden orden) {
        cargaDocumentoElectronicoHelper.iniciarColaborador(colaborador,
                fecetAsociadoDao.getColaboradorContribuyente(colaborador.getTipoAsociado(), orden.getIdOrden()));
    }

    private void enviarCorreoNotificaion(FecetPropuesta fecetPropuesta, BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(fecetPropuesta.getRfcFirmante(), Constantes.USUARIO_FIRMANTE,
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(fecetPropuesta.getRfcAuditor(), Constantes.USUARIO_AUDITOR,
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(fecetPropuesta.getRfcCreacion(), Constantes.USUARIO_PROGRAMADOR,
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, fecetPropuesta.getIdArace(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoCentralAcppce(fecetPropuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getId(),
                fecetPropuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), fecetPropuesta.getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), fecetPropuesta.getIdRegistro());

        enviarNoticacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);

    }

    private void enviarNoticacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {

        try {
            notificacionService.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }

    }

    @Override
    public boolean validarPermiteBusquedaAgenteAduanal(FecetPropuesta propuesta) {
        return cargaDocumentoElectronicoRules.validarPermiteBusquedaAgenteAduanal(propuesta);
    }

    public FecetPropuestaDao getPropuestaDao() {
        return propuestaDao;
    }

    public void setPropuestaDao(FecetPropuestaDao propuestaDao) {
        this.propuestaDao = propuestaDao;
    }

    public FecetContribuyenteDao getContribuyenteDao() {
        return contribuyenteDao;
    }

    public void setContribuyenteDao(FecetContribuyenteDao contribuyenteDao) {
        this.contribuyenteDao = contribuyenteDao;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @Override
    public List<FecetTipoOficio> getOficiosAdministrables(BigDecimal idMetodo, String condicion1, String condicion2) {

        return fecetTipoOficioDao.getOficiosAdminByMetodo(idMetodo, condicion1, condicion2);
    }

    @Override
    public boolean validarCargaOficios() {

        List<FecetAdminOficio> oficios = fecetAdminOficioDao.getAdminOficiosActivos();

        if (oficios != null && !oficios.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public List<AraceDTO> getCatalogoUnidadAdministrativa() {

        try {
            List<AraceDTO> unidadesAdmin;
            unidadesAdmin = new ArrayList<AraceDTO>();
            List<AraceDTO> unidadAdminNoAplicable = new ArrayList<AraceDTO>();

            for (AraceDTO unidad : unidadesAdmin) {
                if (unidad.getIdArace().equals(Constantes.ACPPCE.intValue())
                        || unidad.getIdArace().equals(Constantes.ACIACE.intValue())
                        || unidad.getIdArace().equals(Constantes.PPCE.intValue())) {

                    unidadAdminNoAplicable.add(unidad);
                }
            }

            unidadesAdmin.removeAll(unidadAdminNoAplicable);

            return unidadesAdmin;
        } catch (Exception e) {
            logger.error("No se pudieron consultar las Unidades Administrativas");
            return null;
        }
    }

}
