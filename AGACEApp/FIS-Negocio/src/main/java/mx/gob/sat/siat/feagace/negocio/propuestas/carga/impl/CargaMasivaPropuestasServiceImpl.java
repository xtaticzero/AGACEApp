package mx.gob.sat.siat.feagace.negocio.propuestas.carga.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.model.Ubicacion;
import com.softtek.idc.service.IDCService;

import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececConceptoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.ValidarContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.ValidaRFCCargasMasivasHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.sise.service.SiseService;

@Service("cargaMasivaPropuestasService")
public class CargaMasivaPropuestasServiceImpl extends PropuestasServiceAbstract
        implements CargaMasivaPropuestasService {

    private static final long serialVersionUID = 4353453453496605001L;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FececSectorDao fececSectorDao;
    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient IDCService idcService;
    @Autowired
    private transient SiseService siseService;
    @Autowired
    private ValidaRFCCargasMasivasHelper validaRfcCargasMasivasHelper;
    @Autowired
    private transient BuzonTributarioService buzonTributarioService;
    @Autowired
    private ConsultaAntecedentesPropuestasService consultaAntecedentesService;
    @Autowired
    private NotificacionService notificacion;
    @Autowired
    private transient FececConceptoDao fececConceptoDao;
    @Autowired
    private ValidarContribuyenteService validarContribuyenteService;
    @Autowired
    private RegistrarPropuestaIndividualService registrarPropuestaIndividualService;
    @Autowired
    private transient FececPrioridadDao fececPrioridadDao;

    private String secciones[] = {IDCConstants.IDENTIFICACION, IDCConstants.UBICACION,
        IDCConstants.REPRESENTANTES_LEGALES};

    private transient List<MedioComunicacion> mediosComunicacion;

    @Override
    @PistaAuditoria
    public CargaMasivaPropuestasDTO validaAntecedentes(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        List<String> warnings = new ArrayList<String>();
        List<FecetPropuesta> listPropuesta;
        List<FecetInsumo> listaInsumos;
        try {
            listPropuesta = consultaAntecedentesService.consultaAGACEPropuestas(
                    cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta());

            if (listPropuesta != null && !listPropuesta.isEmpty()) {
                warnings.add(Constantes.COINCIDENCIAS_AGACE);
            }

            listPropuesta = consultaAntecedentesService.consultaAGAFF(
                    cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta().getFechaInicioPeriodo(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta().getFechaFinPeriodo());

            if (listPropuesta != null && !listPropuesta.isEmpty()) {
                warnings.add(Constantes.COINCIDENCIAS_AGAFF);
            }

            listPropuesta = consultaAntecedentesService.consultaSICSEP(
                    cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta().getFechaInicioPeriodo(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta().getFechaFinPeriodo());

            if (listPropuesta != null && !listPropuesta.isEmpty()) {
                warnings.add(Constantes.COINCIDENCIAS_SICSEP);
            }

            listPropuesta = consultaAntecedentesService.consultaSUIEFI(
                    cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta().getFechaInicioPeriodo(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta().getFechaFinPeriodo());

            if (listPropuesta != null && !listPropuesta.isEmpty()) {
                warnings.add(Constantes.COINCIDENCIAS_SUIEFI);
            }

            listaInsumos = consultaAntecedentesService.consultaAGACEInsumos(
                    cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc(),
                    cargaMasivaPropuestasDTO.getFecetPropuesta());

            if (listaInsumos != null && !listaInsumos.isEmpty()) {
                warnings.add(Constantes.COINCIDENCIAS_INSUMOS);
            }

        } catch (NegocioException e) {
            logger.error(e.getMessage());
            cargaMasivaPropuestasDTO.setDescripcionError(e.getMessage());
        }
        cargaMasivaPropuestasDTO.setAdvertencias(warnings);
        cargaMasivaPropuestasDTO.setValida(true);
        return cargaMasivaPropuestasDTO;
    }

    @Override
    public CargaMasivaPropuestasDTO validaRFC(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        cargaMasivaPropuestasDTO.setValida(true);
        validaRfcCargasMasivasHelper.validaLongitud(cargaMasivaPropuestasDTO);
        if (cargaMasivaPropuestasDTO.isValida()) {
            validaRfcCargasMasivasHelper.validaPersonaMoralFisica(cargaMasivaPropuestasDTO);
            if (cargaMasivaPropuestasDTO.isValida()) {
                FecetContribuyente fecetContribuyente = obtenerIformacionContribuente(
                        cargaMasivaPropuestasDTO.getRfcContribuyente(), cargaMasivaPropuestasDTO);
                if (fecetContribuyente != null) {
                    cargaMasivaPropuestasDTO.setFecetContribuyente(fecetContribuyente);
                    cargaMasivaPropuestasDTO.setValida(true);
                    if (cargaMasivaPropuestasDTO.isValida()) {
                        obtenerMediosContacto(cargaMasivaPropuestasDTO);
                        if (cargaMasivaPropuestasDTO.isValida()) {
                            // Cambiar cuando se habiliten los servicios
                            if (mediosComunicacion.get(0).getAmparado() == 0) {
                                // Cambiar cuando se habiliten los servicios
                                if (validaPPEE(cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc()) == 0) {
                                    cargaMasivaPropuestasDTO.setDescripcionError(Constantes.MENSAJE_CONTRIBUYENTE_PPEE);
                                    cargaMasivaPropuestasDTO.setValida(false);
                                    return cargaMasivaPropuestasDTO;
                                }
                            } else {
                                cargaMasivaPropuestasDTO.setDescripcionError(Constantes.MESANJE_CONTRIBUYENTE_AMPARADO);
                                cargaMasivaPropuestasDTO.setValida(false);
                                return cargaMasivaPropuestasDTO;
                            }
                        } else {
                            return cargaMasivaPropuestasDTO;
                        }
                    }
                }
            }
        }
        return cargaMasivaPropuestasDTO;
    }

    @Override
    public BigDecimal validaSubprograma(String subprograma) throws NegocioException {

        List<FececSubprograma> list = fececSubprogramaDao.findWhereClaveEquals(subprograma);
        if (list.size() > 0) {
            return list.get(0).getIdSubprograma();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_SUBPROGRAMA);
        }

    }

    @Override
    public BigDecimal validaCausaProgramacion(BigDecimal causaProgramacion) throws NegocioException {

        List<FececCausaProgramacion> list = fececCausaProgramacionDao
                .findWhereIdCausaProgramacionEquals(causaProgramacion);
        if (list.size() > 0) {
            return list.get(0).getIdCausaProgramacion();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_PROGRAMACION);
        }

    }

    @Override
    public BigDecimal validaCausaProgramacion(String causaProgramacion) throws NegocioException {

        List<FececCausaProgramacion> list = fececCausaProgramacionDao.findWhereDescripcionEquals(causaProgramacion);
        if (list.size() > 0) {
            return list.get(0).getIdCausaProgramacion();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_PROGRAMACION);
        }

    }

    @Override
    public BigDecimal validaMetodo(String metodo) throws NegocioException {

        List<FececMetodo> list = feceaMetodoDao.findWhereAbreviaturaEquals(metodo);
        if (list.size() > 0) {
            return list.get(0).getIdMetodo();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_SUBPROGRAMA);
        }

    }

    @Override
    public BigDecimal validaTipoPropuesta(String tipoPropuestas) throws NegocioException {

        List<FececTipoPropuesta> list = fececTipoPropuestaDao.findWhereDescripcionEquals(tipoPropuestas);
        if (list.size() > 0) {
            return list.get(0).getIdTipoPropuesta();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_PROPUESTA);
        }

    }

    @Override
    public BigDecimal validaSector(BigDecimal sector) throws NegocioException {

        List<FececSector> list = fececSectorDao.findWhereIdSectorEquals(sector);
        if (list.size() > 0) {
            return list.get(0).getIdSector();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_REVISION);
        }

    }

    @Override
    public BigDecimal validaSector(String sector) throws NegocioException {

        List<FececSector> list = fececSectorDao.findWhereDescripcionEquals(sector);
        if (list.size() > 0) {
            return list.get(0).getIdSector();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_SECTOR);
        }

    }

    @Override
    public BigDecimal validaRevision(String revision) throws NegocioException {
        List<FececRevision> list = fececRevisionDao.findWhereDescripcionEquals(revision);
        if (list.size() > 0) {
            return list.get(0).getIdRevision();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_REVISION);
        }

    }

    @Override
    public BigDecimal validaImpuesto(String impuesto) throws NegocioException {

        List<FececTipoImpuesto> list = fececTipoImpuestoDao.findWhereAbreviaturaEquals(impuesto);
        if (list.size() > 0) {
            return list.get(0).getIdTipoImpuesto();
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_SECTOR);
        }

    }

    @Override
    public BigDecimal insertContribuyente(FecetContribuyente fecetContribuyente) {
        return fecetContribuyenteDao.insert(fecetContribuyente).getIdContribuyente();
    }

    @Override
    @Transactional
    public BigDecimal insertPropuesta(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, EmpleadoDTO fececEmpleado) {

        try {

            BigDecimal idContribuyente = fecetContribuyenteDao.insert(cargaMasivaPropuestasDTO.getFecetContribuyente())
                    .getIdContribuyente();

            cargaMasivaPropuestasDTO.getFecetPropuesta().setIdContribuyente(idContribuyente);
            String folio = "";
            cargaMasivaPropuestasDTO.getFecetPropuesta().setIdArace(cargaMasivaPropuestasDTO.getUnidadAdministrativa());
            String novenoDoceavoDigito = consecutivoPropuesta(cargaMasivaPropuestasDTO);
            String terceroCuartoDigito = claveOperativa(cargaMasivaPropuestasDTO.getFecetPropuesta().getIdArace());

            String quintoOctavoDigito = new SimpleDateFormat("yyyy").format(new Date());

            folio = "E" + cargaMasivaPropuestasDTO.getSegundoCaracter() + terceroCuartoDigito + quintoOctavoDigito
                    + novenoDoceavoDigito;

            cargaMasivaPropuestasDTO.getFecetPropuesta().setIdRegistro(folio);
            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaCreacion(new Date());

            if (cargaMasivaPropuestasDTO.getFecetPropuesta().getPrioridad() == null) {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridad(false);
            }
            cargaMasivaPropuestasDTO.getFecetPropuesta().setIdEstatus(ConstantesPropuestas.PROPUESTA_REGISTRADA);

            cargaMasivaPropuestasDTO.getFecetPropuesta().setIdPropuesta(null);

            asignarProgramador(cargaMasivaPropuestasDTO);
            fecetPropuestaDao.insert(cargaMasivaPropuestasDTO.getFecetPropuesta());
            if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdPropuesta() != null) {
                cargaMasivaPropuestasDTO.getFecetPropuesta()
                        .setIdPropuesta(cargaMasivaPropuestasDTO.getFecetPropuesta().getIdPropuesta());
                for (FecetImpuesto fecetImpuesto : cargaMasivaPropuestasDTO.getFecetImpuestos()) {
                    fecetImpuesto.setIdPropuesta(cargaMasivaPropuestasDTO.getFecetPropuesta().getIdPropuesta());
                    fecetImpuestoDao.insert(fecetImpuesto);
                }

                for (FecetDocExpediente fecetDocExpediente : cargaMasivaPropuestasDTO.getFecetPropuesta()
                        .getListaDocumentos()) {
                    fecetDocExpediente.setIdPropuesta(cargaMasivaPropuestasDTO.getFecetPropuesta().getIdPropuesta());
                    fecetDocExpedienteDao.insert(fecetDocExpediente);
                }
                enviarNotificaciones(cargaMasivaPropuestasDTO, fececEmpleado,
                        LeyendasPropuestasEnum.PROPUESTA_ASIGNADA_VALIDACION.getIdLeyenda());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return cargaMasivaPropuestasDTO.getFecetPropuesta().getIdPropuesta();

    }

    @PistaAuditoria
    public BigDecimal insertPropuestaMasiva(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            EmpleadoDTO fececEmpleado) {
        return insertPropuesta(cargaMasivaPropuestasDTO, fececEmpleado);
    }

    @Override
    public boolean validaTipoRespuesta(String tipoRespuesta) throws NegocioException {
        return false;
    }

    @Override
    public boolean validaTransferencia(String tranferencia) throws NegocioException {
        return false;
    }

    @Override
    public FececSubprograma getSubprogramaById(BigDecimal idSubprograma) {
        return fececSubprogramaDao.findByPrimaryKey(idSubprograma);
    }

    @Override
    public FececMetodo getMetodoById(BigDecimal idMetodo) throws NegocioException {
        try {
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public List<CargaMasivaPropuestasDTO> procesaCargaMasivaPropuestas(InputStream inputStream) {
        return Collections.emptyList();
    }

    @Override
    public boolean insertarDocumento(FecetDocExpediente dto) {

        if (null != fecetDocExpedienteDao.insert(dto)) {
            return true;
        }
        return false;

    }

    @Override
    @PistaAuditoria
    public String insertaResumen(ResumenCargaMasivaDTO resumenDTO) throws NegocioException {
        String identificador = "";
        try {
            identificador = fecetPropuestaDao.insertaResumenMasiva(resumenDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return identificador;
    }

    @PistaAuditoria
    public String insertaResumenCarta(ResumenCargaMasivaDTO resumenDTO) throws NegocioException {
        String identificador = "";
        try {
            identificador = fecetPropuestaDao.insertaResumenMasiva(resumenDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return identificador;
    }

    public void setValidaRfcCargasMasivasHelper(ValidaRFCCargasMasivasHelper validaRfcCargasMasivasHelper) {
        this.validaRfcCargasMasivasHelper = validaRfcCargasMasivasHelper;
    }

    public ValidaRFCCargasMasivasHelper getValidaRfcCargasMasivasHelper() {
        return validaRfcCargasMasivasHelper;
    }

    public List<MedioComunicacion> getMediosComunicacion() {
        return mediosComunicacion;
    }

    public void setMediosComunicacion(List<MedioComunicacion> mediosComunicacion) {
        this.mediosComunicacion = mediosComunicacion;
    }

    private int validaPPEE(String rfc) {
        logger.info("--- va a validar ppee");
        int info;
        try {
            info = siseService.verInformacion(rfc);
        } catch (Exception e) {
            logger.error("No se pudo acceder al servicio SISE:" + e);
            return -1;
        }
        logger.info("--- PPEE:" + info);
        return info;
    }

    private void obtenerMediosContacto(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        try {

            setMediosComunicacion(buzonTributarioService
                    .obtenerMediosComunicacion(cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc()));
            validarContribuyenteService.procesaStatusMediosContacto(getMediosComunicacion(),
                    cargaMasivaPropuestasDTO.getFecetContribuyente().getRfc());
            String mediosContacto = validarContribuyenteService.getEstatusDeContacto();

            if (mediosContacto.equals(ConstantesPropuestas.SIN_MEDIOS)) {
                cargaMasivaPropuestasDTO.setValida(false);
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestas.SIN_MEDIOS_CONTACTO);
            } else {
                cargaMasivaPropuestasDTO.setValida(true);
            }
        } catch (BuzonNoDisponibleException e) {
            cargaMasivaPropuestasDTO.setValida(false);
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestas.SIN_MEDIOS_CONTACTO_ERROR_CONSULTA);
        }
    }

    private FecetContribuyente obtenerIformacionContribuente(String rfc,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        FecetContribuyente contribuyente = new FecetContribuyente();
        IdCInterno contribuyenteIDC = null;

        contribuyenteIDC = idcService.obtenerInformacionContribuyente(rfc, secciones);

        if (contribuyenteIDC.getIdentificacion() != null) {
            String nombreCompleto = contribuyenteIDC.getIdentificacion().getNombre();
            String apellidoPaterno = contribuyenteIDC.getIdentificacion().getAp_Paterno();
            String apellidoMaterno = contribuyenteIDC.getIdentificacion().getAp_Materno();

            if (nombreCompleto == null && apellidoPaterno == null && apellidoMaterno == null) {
                contribuyente.setNombre("Sin nombre definido");
            } else {
                contribuyente.setNombre(nombreCompleto + " " + apellidoPaterno + " " + apellidoMaterno);
            }

            contribuyente.setRfc(rfc.toUpperCase());
            if (contribuyenteIDC.getIdentificacion() != null) {
                contribuyente.setSituacion(contribuyenteIDC.getIdentificacion().getD_Sit_Cont() != null
                        ? contribuyenteIDC.getIdentificacion().getD_Sit_Cont() : null);
                contribuyente.setDomicilioFiscal(generaDireccionContribuyente(contribuyenteIDC.getUbicacion()));
                contribuyente.setSituacionDomicilio(contribuyenteIDC.getIdentificacion().getD_Sit_Dom() != null
                        ? contribuyenteIDC.getIdentificacion().getD_Sit_Dom() : null);

                contribuyente.setTipo(contribuyenteIDC.getIdentificacion().getT_persona().trim().equals("M")
                        ? "PERSONA MORAL" : "PERSONA FISICA");
            }
            contribuyente.setActividadPreponderante(contribuyenteIDC.getActividades() != null
                    ? contribuyenteIDC.getActividades().get(0).getD_Actividad().trim() : "Sin actividad preponderante");
            contribuyente.setEntidad((contribuyenteIDC.getUbicacion() != null)
                    ? contribuyenteIDC.getUbicacion().getD_Ent_Fed() : "Sin Entidad");
            contribuyente.setRegimen(contribuyenteIDC.getRegimenes() != null
                    ? contribuyenteIDC.getRegimenes().get(0).getC_Regimen().trim() : "Sin regimen");
        } else {
            cargaMasivaPropuestasDTO.setValida(false);
            cargaMasivaPropuestasDTO.setDescripcionError(
                    "No se encuentra informaci\u00F3n registrado para el RFC <%RFC%>, favor de verificar."
                    .replaceAll("<%RFC%>", rfc));
            contribuyente = null;
        }
        return contribuyente;
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

    private void asignarProgramador(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto().getDetalleEmpleado().get(0).getCentral()
                .getIdArace().equals(Constantes.ACPPCE.intValue())) {

            if (!cargaMasivaPropuestasDTO.getFecetPropuesta().getIdArace().equals(Constantes.ACAOCE)
                    && !cargaMasivaPropuestasDTO.getFecetPropuesta().getIdArace().equals(Constantes.ACOECE)) {

                cargaMasivaPropuestasDTO.getFecetPropuesta()
                        .setProgramadorId(registrarPropuestaIndividualService
                                .asignarPropuesta(Constantes.USUARIO_PROGRAMADOR,
                                        cargaMasivaPropuestasDTO.getFecetPropuesta().getIdArace())
                                .get(0).getIdEmpleado());
            } else {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setProgramadorId(null);
            }

        }
    }

    private BigDecimal consecutivoPropuestaAnio(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        BigDecimal consecutivoActual;
        BigDecimal consecutivoNuevo;
        try {
            consecutivoActual = fecetPropuestaDao.getConsecutivoAnio(this.obtenerFechaInicial(),
                    this.obtenerFechaFinal());
            if (consecutivoActual != null) {
                logger.debug("Consecutivo Actual: [{}]", consecutivoActual);
                consecutivoNuevo = consecutivoActual.add(ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA);
            } else {
                consecutivoNuevo = ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA;
            }
            cargaMasivaPropuestasDTO.getFecetPropuesta().setConsecutivoAnio(consecutivoNuevo);
        } catch (Exception e) {
            consecutivoNuevo = ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA;
            logger.error("No se pudo obtener el consecutivo [{}] ", e);
        }
        logger.debug("Nuevo Consecutivo: [{}]", consecutivoNuevo);
        return consecutivoNuevo;
    }

    private Date obtenerFechaInicial() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
        Date fechaInicio = new Date();
        String fechaInicioStr = "01/01/".concat(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
        try {
            fechaInicio = formatoFecha.parse(fechaInicioStr);
        } catch (ParseException e) {
            logger.error("No se pudo pasear la fecha inicial [{}] ", fechaInicioStr);
        }
        return fechaInicio;
    }

    private Date obtenerFechaFinal() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
        Date fechaFinal = new Date();
        String fechaFinalStr = "31/12/".concat(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
        try {
            fechaFinal = formatoFecha.parse(fechaFinalStr);
        } catch (ParseException e) {
            logger.error("No se pudo pasear la fecha final [{}] ", fechaFinalStr);
        }
        return fechaFinal;
    }

    private void enviarNotificaciones(CargaMasivaPropuestasDTO cargaMasivaPropuestas, EmpleadoDTO fececEmpleado,
            BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();

        Map<String, String> data = notificacion.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), cargaMasivaPropuestas.getFecetPropuesta().getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), cargaMasivaPropuestas.getFecetPropuesta().getIdRegistro());

        destinatarios.addAll(obtenerToCorreos(cargaMasivaPropuestas.getUnidadesCorreo()));
        destinatarios.addAll(obtenerCorreoProgramador(cargaMasivaPropuestas));
        notificacion.obtenerCorreoCentralAcppce(fececEmpleado.getRfc(), TipoEmpleadoEnum.PROGRAMADOR.getId(),
                cargaMasivaPropuestas.getFecetPropuesta().getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);

        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {

        try {
            notificacion.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }

    }

    private List<String> obtenerToCorreos(Map<TipoEmpleadoEnum, BigDecimal> empleados) {
        List<String> correos = new ArrayList<String>();
        try {
            for (Map.Entry<TipoEmpleadoEnum, BigDecimal> e : empleados.entrySet()) {
                correos.addAll(getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(e.getKey(), e.getValue(),
                        ClvSubModulosAgace.PROPUESTAS));
            }
        } catch (Exception e) {
            logger.error("Error al obtener el correo del auditor {}: {}", e.getMessage(), e);
        }
        return correos;
    }

    private List<String> obtenerCorreoProgramador(CargaMasivaPropuestasDTO cargaMasivaPropuestas) {

        List<String> correoProgramador = new ArrayList<String>();
        BigDecimal idProgramador = cargaMasivaPropuestas.getFecetPropuesta().getProgramadorId();

        try {
            if (cargaMasivaPropuestas.getFecetPropuesta().getIdMetodo().equals(Constantes.MCA)) {
                if (idProgramador != null) {
                    correoProgramador.add(getEmpleadoCompleto(idProgramador.intValue()).getCorreo());
                }
            } else {
                if (idProgramador != null) {
                    correoProgramador.add(getEmpleadoCompleto(idProgramador.intValue()).getCorreo());
                    correoProgramador
                            .add(getEmpleadoCompleto(cargaMasivaPropuestas.getFecetPropuesta().getRfcCreacion())
                                    .getCorreo());
                } else {
                    correoProgramador
                            .add(getEmpleadoCompleto(cargaMasivaPropuestas.getFecetPropuesta().getRfcCreacion())
                                    .getCorreo());
                }
            }
        } catch (Exception e) {
            logger.error("Error al obtener el correo del programador {}: {}", e.getMessage(), e);
        }

        return correoProgramador;
    }

    private String consecutivoPropuesta(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        BigDecimal consecutivo;
        String claveFinal = null;
        String str3CerosIzq = "%04d";
        Formatter fmt = null;
        try {
            fmt = new Formatter();
            consecutivo = this.consecutivoPropuestaAnio(cargaMasivaPropuestasDTO);
            claveFinal = fmt.format(str3CerosIzq, Long.parseLong(consecutivo.toString())).toString();
        } catch (Exception e) {
            logger.error("No se pudo obtener el consecutivo [{}] ", e);
        }
        return claveFinal;
    }

    private String claveOperativa(BigDecimal unidadAdmon) {
        String claveCompleta = "";
        if (unidadAdmon.toString().length() > 1) {
            claveCompleta = unidadAdmon.toString();
        } else {
            claveCompleta = "0" + unidadAdmon.toString();
        }
        return claveCompleta;
    }

    @Override
    public boolean validaConceptoImpuesto(String tipoImpuesto, String concepto) {
        List<FececConcepto> lista = fececConceptoDao.findByTipoImpuestoName(tipoImpuesto);

        for (FececConcepto list : lista) {
            if (concepto.equals(list.getDescripcion())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BigDecimal getidConcepto(String descripcionConcepto) {

        List<FececConcepto> lista = fececConceptoDao.findByNombreConcepto(descripcionConcepto);
        BigDecimal idConcepto = BigDecimal.ZERO;

        if (lista != null && lista.size() > 0) {
            idConcepto = BigDecimal.valueOf(lista.get(0).getIdConcepto());
        }

        return idConcepto;
    }

    @Override
    @PistaAuditoria
    public HSSFSheet obtenerHojaExcel(HSSFWorkbook workbook) {
        return workbook.getSheetAt(0);
    }

    @Override
    @PistaAuditoria
    public HSSFSheet obtenerHojaExcelCartas(HSSFWorkbook workbook) {
        return workbook.getSheetAt(0);
    }

    @Override
    public List<FececPrioridad> validaPrioridad(String prioridad) {
        return fececPrioridadDao.findPrioridadByEquals(prioridad);
    }
}
