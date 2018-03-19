package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececTipoInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FecetDocumentoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocExpInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FoliosProcesadosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidarDuplicidadCrearInsumoBO;
import mx.gob.sat.siat.feagace.negocio.ServiceCatGrupoDeUnidadAdmin;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.CrearInsumoHelper;
import mx.gob.sat.siat.feagace.negocio.insumos.CrearInsumoService;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarDuplicidadCrearInsumoRule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Service("crearInsumoService")
public class CrearInsumoServiceImpl extends ServicioInsumosAbstract implements CrearInsumoService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1317292650496822448L;

    /**
     * strCerosIzq.
     */
    private static final BigDecimal INSUMO_SIN_ADMINISTRADOR = new BigDecimal(148);
    private static final String MENSAJE_ERROR_ELIMINAR = "No se pudo borrar el archivo. ";
    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient FececSectorDao fececSectorDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;
    @Autowired
    private transient FecetDocExpInsumoDao fecetDocExpInsumoDao;
    @Autowired
    private transient FececTipoInsumoDao fececTipoInsumoDao;
    @Autowired
    private ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin;
    @Autowired
    private transient FecetDocumentoDao fecetDocumentoDao;

    @Override
    public List<FececSubprograma> getCatalogoSubprograma(BigDecimal idGeneral) {
        return fececSubprogramaDao.findActivos(idGeneral);
    }

    @Override
    public List<FececSector> getCatalogoSector(BigDecimal idGeneral) {
        return fececSectorDao.findActivos(idGeneral);
    }

    @Override
    public List<FececTipoInsumo> getCatalogoTipoInsumo(BigDecimal idGeneral) {
        return fececTipoInsumoDao.findActivos(idGeneral);
    }

    @Override
    public RegistroInsumosDto guardarInsumos(final List<FecetInsumo> listaInsumos)
            throws NegocioException {
        RegistroInsumosDto insumosProcesados = new RegistroInsumosDto();
        FoliosProcesadosDto actualUnidad;
        Map<BigDecimal, FoliosProcesadosDto> foliosProcesados = new HashMap<BigDecimal, FoliosProcesadosDto>();
        String rutaActual, rfcs, folioInsumo;
        try {
            for (FecetInsumo insumo : listaInsumos) {
                if (insumo.getRfcAdministrador() == null) {
                    EmpleadoDTO empleadoAdministrador = obtenerAdministrador(insumo.getIdUnidadAdministrativa(), Constantes.USUARIO_ASIGNADOR_INSUMOS, insumo.getFecetContribuyente().getEntidad());
                    if (empleadoAdministrador == null) {
                        insumo.setIdEstatus(INSUMO_SIN_ADMINISTRADOR);
                        this.fecetInsumoDao.actualizarInsumoRegistro(insumo);
                        insumosProcesados.getInsumosNoRegistrados().add(insumo);
                        continue;
                    } else {
                        insumo.setRfcAdministrador(empleadoAdministrador.getRfc());
                    }
                }
                rutaActual = RutaArchivosUtil.armarRutaDestinoInsumo(insumo);
                folioInsumo = CrearInsumoHelper.getIdRegistro(insumo.getIdUnidadAdministrativa(), fecetInsumoDao.getFolioDisponible());
                actualUnidad = foliosProcesados.get(insumo.getIdUnidadAdministrativa());
                if (actualUnidad == null) {
                    actualUnidad = new FoliosProcesadosDto();
                    actualUnidad.setFoliosAdministrador(new HashMap<String, List<String>>());
                }
                actualUnidad.getFolios().add(folioInsumo);
                rfcs = String.format("%s-%s", insumo.getRfcCreacion(), insumo.getRfcAdministrador());
                if (actualUnidad.getFoliosAdministrador().get(rfcs) == null) {
                    actualUnidad.getFoliosAdministrador().put(rfcs, new ArrayList<String>());
                }
                actualUnidad.getFoliosAdministrador().get(rfcs).add(folioInsumo);
                insumosProcesados.getInsumosRegistrados().add(insumo);
                insumo.setIdEstatus(Constantes.INSUMO_CREADO);
                insumo.setIdRegistro(folioInsumo);
                this.fecetInsumoDao.actualizarInsumoRegistro(insumo);
                foliosProcesados.put(insumo.getIdUnidadAdministrativa(), actualUnidad);
                for (FecetDocExpInsumo documento : insumo.getListaDocumentos()) {
                    documento.setIdInsumo(insumo.getIdInsumo());
                    documento.setFechaCreacion(new Date());
                    StringBuilder rutaArchivo = new StringBuilder();
                    rutaArchivo.append(RutaArchivosUtil.armarRutaDestinoInsumo(insumo));
                    rutaArchivo.append(documento.getNombre());
                    documento.setRutaArchivo(rutaArchivo.toString());
                    this.fecetDocExpInsumoDao.update(documento.createPk(), documento);
                    CrearInsumoHelper.actualizaArchivo(rutaActual, RutaArchivosUtil.armarRutaDestinoInsumo(insumo), documento.getNombre());
                }
            }
            insumosProcesados.setFolios(foliosProcesados);
            return insumosProcesados;
        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    @Override
    public boolean validaExistenciaTemporal(FecetInsumo fecetInsumoReferencia,
            FecetInsumo fecetInsumo) {
        ValidarDuplicidadCrearInsumoBO validarDuplicidadCrearInsumoBO = new ValidarDuplicidadCrearInsumoBO();
        validarDuplicidadCrearInsumoBO.setFecetInsumoReferencia(fecetInsumoReferencia);
        validarDuplicidadCrearInsumoBO.setFecetInsumo(fecetInsumo);
        validarDuplicidadCrearInsumoBO.setRule(ValidarDuplicidadCrearInsumoRule.VALIDA_RFC);
        while (validarDuplicidadCrearInsumoBO.getRule().process(validarDuplicidadCrearInsumoBO)) {
            logger.info("Validando existencia temporal.");
        }
        return validarDuplicidadCrearInsumoBO.isState();
    }

    @Override
    public List<FececUnidadAdministrativa> getUnidadesAdministritativas(EmpleadoDTO empleadoDTO) {
        List<FececUnidadAdministrativa> registros = new ArrayList<FececUnidadAdministrativa>();
        List<GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral;
        try {
            grupoUnidadesAdminXGeneral = serviceCatGrupoDeUnidadAdmin.getLstGruposXGeneralXRegla(empleadoDTO, ReglaEnum.RNA037);
            if (grupoUnidadesAdminXGeneral != null && !grupoUnidadesAdminXGeneral.isEmpty()) {
                for (GrupoUnidadesAdminXGeneral grupoUnidadesAdmin : grupoUnidadesAdminXGeneral) {
                    FececUnidadAdministrativa fececUnidadAdministrativa = new FececUnidadAdministrativa();
                    fececUnidadAdministrativa.setIdUnidadAdministrativa(grupoUnidadesAdmin.getGrupo().getIdGrupo());
                    fececUnidadAdministrativa.setNombre(grupoUnidadesAdmin.getGrupo().getNombre());
                    fececUnidadAdministrativa.setDescripcion(grupoUnidadesAdmin.getGrupo().getDescripcion());
                    fececUnidadAdministrativa.setCentral(grupoUnidadesAdmin.getGrupo().getCentral());
                    registros.add(fececUnidadAdministrativa);
                }
            }
        } catch (CatalogosServiceException ex) {
            logger.error("No se puede cargar unidades administrativas", ex);
        }
        return registros;
    }

    @Override
    public RegistroInsumosDto agregaInsumo(FecetInsumo insumo) throws NegocioException {
        String folio = null;
        RegistroInsumosDto insumosProcesados = new RegistroInsumosDto();
        try {
            folio = "SIN FOLIO";
            insumo.setPrioridad(false);
            insumo.setIdContribuyente(this.fecetContribuyenteDao.insert(insumo.getFecetContribuyente()).getIdContribuyente());
            insumo.setIdRegistro(folio);
            insumo.setFechaCreacion(new Date());
            insumo.setIdEstatus(Constantes.INSUMO_AGREGADO);

            EmpleadoDTO empleadoAdministrador = obtenerAdministrador(insumo.getIdArace(), Constantes.USUARIO_ASIGNADOR_INSUMOS, insumo.getFecetContribuyente().getEntidad());
            if (empleadoAdministrador == null) {
                insumosProcesados.getInsumosNoRegistrados().add(insumo);
            } else {
                insumo.setRfcAdministrador(empleadoAdministrador.getRfc());
                insumosProcesados.getInsumosRegistrados().add(insumo);
            }
            insumo.setIdInsumo(null);
            insumo.setIdInsumo(this.fecetInsumoDao.insert(insumo).getIdInsumo());

            for (FecetDocExpInsumo documento : insumo.getListaDocumentos()) {
                documento.setIdInsumo(insumo.getIdInsumo());
                documento.setFechaCreacion(new Date());
                documento.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoInsumo(insumo));
                documento.setBlnActivo(true);
                this.fecetDocExpInsumoDao.insert(documento);
                CargaArchivoUtil.guardarArchivo(documento.getArchivo(), documento.getRutaArchivo(), documento.getNombre());
            }
            if (insumo.getListaDocumentoJustificacion() != null && !insumo.getListaDocumentoJustificacion().isEmpty()) {
                for (FecetDocumento documentoJus : insumo.getListaDocumentoJustificacion()) {
                    documentoJus.setIdInsumo(insumo.getIdInsumo());
                    documentoJus.setFechaCreacion(new Date());
                    documentoJus.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoInsumoJustificacion(insumo));
                    fecetDocumentoDao.insertar(documentoJus);
                    CargaArchivoUtil.guardarArchivo(documentoJus.getArchivo(), documentoJus.getRutaArchivo(), documentoJus.getNombre());
                }
            }
            return insumosProcesados;
        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    @Override
    public RegistroInsumosDto actualizaInsumo(FecetInsumo insumo, List<FecetDocExpInsumo> documentosBorrar, List<FecetDocumento> documentoBorrarJustificacion) throws NegocioException {
        RegistroInsumosDto insumosProcesados = new RegistroInsumosDto();
        try {
            EmpleadoDTO empleadoAdministrador = obtenerAdministrador(insumo.getIdArace(), Constantes.USUARIO_ASIGNADOR_INSUMOS, insumo.getFecetContribuyente().getEntidad());
            if (empleadoAdministrador == null) {
                insumosProcesados.getInsumosNoRegistrados().add(insumo);
            } else {
                insumosProcesados.getInsumosRegistrados().add(insumo);
                insumo.setRfcAdministrador(empleadoAdministrador.getRfc());
                this.fecetInsumoDao.update(insumo.createPk(), insumo);
                for (FecetDocExpInsumo documento : insumo.getListaDocumentos()) {
                    if (documento.getIdInsumo() == null) {
                        documento.setIdInsumo(insumo.getIdInsumo());
                        documento.setFechaCreacion(new Date());
                        documento.setBlnActivo(true);
                        documento.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoInsumo(insumo));
                        this.fecetDocExpInsumoDao.insert(documento);
                        CargaArchivoUtil.guardarArchivo(documento.getArchivo(), documento.getRutaArchivo(), documento.getNombre());
                    }
                }
                for (FecetDocExpInsumo fecetDocExpInsumo : documentosBorrar) {
                    File file = new File(new StringBuilder().append(fecetDocExpInsumo.getRutaArchivo()).append(fecetDocExpInsumo.getNombre()).toString());
                    if (!file.delete()) {
                        logger.error(MENSAJE_ERROR_ELIMINAR.concat(file.getAbsolutePath()));
                    }
                    fecetDocExpInsumo.setBlnActivo(false);
                    fecetDocExpInsumoDao.update(fecetDocExpInsumo.createPk(), fecetDocExpInsumo);
                }
                if (documentoBorrarJustificacion != null && !documentoBorrarJustificacion.isEmpty()) {
                    for (FecetDocumento documento : documentoBorrarJustificacion) {
                        if (documento.getIdInsumo() == null) {
                            documento.setIdInsumo(insumo.getIdInsumo());
                            documento.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoInsumoJustificacion(insumo));
                            fecetDocumentoDao.insertar(documento);
                            CargaArchivoUtil.guardarArchivo(documento.getArchivo(), documento.getRutaArchivo(), documento.getNombre());
                        } else {
                            File file = new File(new StringBuilder().append(documento.getRutaArchivo()).append(documento.getNombre()).toString());
                            if (!file.delete()) {
                                logger.error(MENSAJE_ERROR_ELIMINAR.concat(file.getAbsolutePath()));
                            }
                            documento.setFechaBaja(new Date());
                            documento.setRutaArchivo(documento.getRutaArchivo()+documento.getNombre());
                            fecetDocumentoDao.update(documento);
                        }
                    }
                }

            }
            return insumosProcesados;
        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    @Override
    public void eliminarInsumo(FecetInsumo insumo) {
        this.fecetInsumoDao.update(insumo.createPk(), insumo);
        for (FecetDocExpInsumo fecetDocExpInsumo : insumo.getListaDocumentos()) {
            File file = new File(new StringBuilder().append(fecetDocExpInsumo.getRutaArchivo()).append(fecetDocExpInsumo.getNombre()).toString());
            if (!file.delete()) {
                logger.error(MENSAJE_ERROR_ELIMINAR.concat(file.getAbsolutePath()));
            }
            fecetDocExpInsumo.setBlnActivo(false);
            fecetDocExpInsumo.setFechaFin(new Date());
            fecetDocExpInsumoDao.update(fecetDocExpInsumo.createPk(), fecetDocExpInsumo);
        }
        if (insumo.getListaDocumentoJustificacion() != null && !insumo.getListaDocumentoJustificacion().isEmpty()) {
            for (FecetDocumento fecetDocumento : insumo.getListaDocumentoJustificacion()) {
                File file = new File(new StringBuilder().append(fecetDocumento.getRutaArchivo()).append(fecetDocumento.getNombre()).toString());
                if (!file.delete()) {
                    logger.error(MENSAJE_ERROR_ELIMINAR.concat(file.getAbsolutePath()));
                }
                fecetDocumento.setFechaBaja(new Date());
                fecetDocumentoDao.update(fecetDocumento);

            }
        }

    }

    @Override
    public String insertarRegistrosMasivos(FecetInsumo insumo, RegistroInsumosDto resultado) {
        EmpleadoDTO empleadoDTO = obtenerAdministrador(insumo.getIdArace(), Constantes.USUARIO_ASIGNADOR_INSUMOS, insumo.getFecetContribuyente().getEntidad());
        if (empleadoDTO == null) {
            insumo.setIdRegistro("SIN FOLIO");
            resultado.getInsumosNoRegistrados().add(insumo);
        } else {
            insumo.setPrioridad(false);
            insumo.setIdContribuyente(this.fecetContribuyenteDao.insert(insumo.getFecetContribuyente()).getIdContribuyente());
            insumo.setIdRegistro(CrearInsumoHelper.getIdRegistro(insumo.getIdUnidadAdministrativa(), fecetInsumoDao.getFolioDisponible()));
            insumo.setFechaCreacion(new Date());
            insumo.setIdEstatus(Constantes.INSUMO_CREADO);
            insumo.setRfcAdministrador(empleadoDTO.getRfc());
            insumo.setIdInsumo(null);
            insumo.setIdInsumo(this.fecetInsumoDao.insert(insumo).getIdInsumo());
            String rutaDestino = RutaArchivosUtil.armarRutaDestinoInsumo(insumo);

            for (FecetDocExpInsumo documento : insumo.getListaDocumentos()) {
                documento.setIdInsumo(insumo.getIdInsumo());
                documento.setFechaCreacion(new Date());
                documento.setBlnActivo(true);
                try {
                    CrearInsumoHelper.actualizaArchivo(documento.getRutaArchivo(), rutaDestino, documento.getNombre());
                } catch (IOException e) {
                    logger.error("Error al guardar los documentos del insumo. ", e);
                    continue;
                }
                documento.setRutaArchivo(rutaDestino);
                this.fecetDocExpInsumoDao.insert(documento);

            }
            FoliosProcesadosDto actualUnidad = resultado.getFolios().get(insumo.getIdUnidadAdministrativa());
            if (actualUnidad == null) {
                actualUnidad = new FoliosProcesadosDto();
                actualUnidad.setFoliosAdministrador(new HashMap<String, List<String>>());
            }
            actualUnidad.getFolios().add(insumo.getIdRegistro());
            String rfcs = String.format("%s-%s", insumo.getRfcCreacion(), insumo.getRfcAdministrador());
            if (actualUnidad.getFoliosAdministrador().get(rfcs) == null) {
                actualUnidad.getFoliosAdministrador().put(rfcs, new ArrayList<String>());
            }
            actualUnidad.getFoliosAdministrador().get(rfcs).add(insumo.getIdRegistro());
            resultado.getFolios().put(insumo.getIdUnidadAdministrativa(), actualUnidad);
            resultado.getInsumosRegistrados().add(insumo);
        }
        return insumo.getIdRegistro();
    }

    private EmpleadoDTO obtenerAdministrador(BigDecimal idArace, BigDecimal tipoEmpleado, String estado) {
        EmpleadoDTO resultado = null;
        try {
            List<BigDecimal> idEmpleados = fecetInsumoDao.getNumEmpleadosUnidadEstadoTipoEmpleado(idArace, estado, tipoEmpleado);
            if (idEmpleados != null && !idEmpleados.isEmpty()) {
                resultado = getEmpleadoCompleto(idEmpleados.get(0).intValue());
            }
        } catch (EmpleadoServiceException e) {
            logger.error("No se pudo validar la existencia de administradores", e);
        }
        return resultado;
    }

    @Override
    public List<FecetDocumento> bucarDocumentoJustificacionById(BigDecimal idInsumo) {
        FecetInsumo insumo = new FecetInsumo();
        insumo.setIdInsumo(idInsumo);
        return obtenerDocumentosByIdInsumo(insumo);                        
    }

}
