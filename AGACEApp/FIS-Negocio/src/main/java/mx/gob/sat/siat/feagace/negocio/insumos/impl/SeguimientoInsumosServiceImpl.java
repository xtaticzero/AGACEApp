package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocExpInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocrechazoinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocretroinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRechazoInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumoPk;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumoPk;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.insumos.SeguimientoInsumosService;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;

@Service("seguimientoInsumosService")
public class SeguimientoInsumosServiceImpl extends ServicioInsumosAbstract implements SeguimientoInsumosService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1011876200686738018L;
    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;
    @Autowired
    private transient FecetDocExpInsumoDao fecetDocExpInsumoDao;
    @Autowired
    private transient FecetRechazoInsumoDao fecetRechazoInsumoDao;
    @Autowired
    private transient FecetRetroalimentacionInsumoDao fecetRetroalimentacionInsumoDao;
    @Autowired
    private transient FecetDocretroinsumoDao fecetDocretroinsumoDao;
    @Autowired
    private transient FecetDocrechazoinsumoDao fecetDocrechazoinsumoDao;
    @Autowired
    private transient PlazosServiceInsumos plazosServiceInsumos;

    private static final BigDecimal INSUMO_CREADO = BigDecimal.ONE;
    private static final BigDecimal INSUMO_RECHAZADO = new BigDecimal(11);
    private static final BigDecimal INSUMO_RETROALIMENTADO = new BigDecimal(3);

    private List<FecetInsumo> getListaSeguimientoInsumoEstatus(BigDecimal estatus) throws NegocioException {

        return fecetInsumoDao.buscarSeguimientoInsumoPorEstatus(estatus);

    }

    @Override
    public List<FecetContadorInsumos> getInsumosPorRetroalimentar(final BigDecimal idInsumo, final BigDecimal tipoEmpleado) {

        return fecetRetroalimentacionInsumoDao.getContadorInsumosPorRetroalimentar(idInsumo, tipoEmpleado);

    }

    @Override
    public List<FecetContadorInsumosRechazados> getContadorRechazo(final BigDecimal idInsumo) {

        return fecetRechazoInsumoDao.getContadorRechazo(idInsumo);

    }

    @Override
    @PistaAuditoria
    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentados(final FecetInsumo fecetInsumo) {
        List<FecetRetroalimentacionInsumo> registros = fecetRetroalimentacionInsumoDao.getHistoricoRetroalimentacion(fecetInsumo);
        String nombreUnidad = "";
        if (fecetInsumo.getFececUnidadAdministrativa() != null) {
            nombreUnidad = fecetInsumo.getFececUnidadAdministrativa().getNombre();
        }
        for (FecetRetroalimentacionInsumo fecetRetroalimentacionInsumo : registros) {
            fecetRetroalimentacionInsumo.setIdRegistroInsumo(fecetInsumo.getIdRegistro());
            fecetRetroalimentacionInsumo.setNombreUnidadAdministrativa(nombreUnidad);
        }
        return registros;

    }

    @Override
    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosFlujoPrincipal(final FecetInsumo fecetInsumo) {
        return fecetRetroalimentacionInsumoDao.getHistoricoRetroalimentacion(fecetInsumo);
    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroalimentados(final BigDecimal idRetroalimentacion) {
        return fecetDocretroinsumoDao.findWhereIdRetroalimentacionInsumoEquals(idRetroalimentacion);
    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroRechazos(final BigDecimal idRetroalimentacion) {
        return fecetDocretroinsumoDao.findWhereIdRetroalimentacionInsumoEquals(idRetroalimentacion);
    }

    @Override
    public List<FecetDocrechazoinsumo> getDocumentosRechazados(final BigDecimal idRechazo) {
        return fecetDocrechazoinsumoDao.findWhereIdRechazoInsumoEquals(idRechazo);
    }

    @Override
    public List<FecetInsumo> getListaSeguimientoInsumoEstatusRFCCreacion(BigDecimal estatus, String rfc, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral) {
        List<FecetInsumo> insumos = fecetInsumoDao.buscarSeguimientoInsumoPorEstatusRFCCreacion(estatus, rfc);
        plazosServiceInsumos.inicializarInsumoConPlazos(insumos);
        try {
            llenarDetalleGrupoUnidadAdmin(grupoUnidadesAdminXGeneral, insumos);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return insumos;
    }

    @Override
    public List<FecetInsumo> getListaSeguimientoInsumoRFCCreacion(String rfc, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral, BigDecimal... estatus) {
        List<FecetInsumo> listaRegistros = fecetInsumoDao.buscarSeguimientoInsumoPorRFCCreacion(rfc, estatus);
        try {
            llenarDetalleGrupoUnidadAdmin(grupoUnidadesAdminXGeneral, listaRegistros);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return listaRegistros;
    }

    @Override
    public FecetRechazoInsumo getFecetRechazoByInsumo(BigDecimal idInsumo) {
        List<FecetRechazoInsumo> rechazoInsumo = fecetRechazoInsumoDao.findWhereIdInsumoEquals(idInsumo);
        return rechazoInsumo != null && !rechazoInsumo.isEmpty() ? rechazoInsumo.get(0) : null;
    }

    @Override
    public FecetRetroalimentacionInsumo getRetroalimentacionInsumo(final BigDecimal idInsumo) {
        List<FecetRetroalimentacionInsumo> retroalimentacionInsumo = fecetRetroalimentacionInsumoDao.getRetroalimentacionInsumo(idInsumo);
        return !retroalimentacionInsumo.isEmpty() ? retroalimentacionInsumo.get(0) : null;
    }

    public void actualizaInsumoEstatus(FecetInsumo insumoSeleccionado) throws NegocioException {
        try {
            insumoSeleccionado.setIdEstatus(INSUMO_RETROALIMENTADO);
            fecetInsumoDao.update(new FecetInsumoPk(insumoSeleccionado.getIdInsumo()), insumoSeleccionado);
        } catch (Exception e) {
            logger.error(Constantes.CONSULTA_ORDENES + e.getCause(), e);
            throw new NegocioException(e.getCause().toString(), e);
        }

    }

    @Override
    public void actualizaRechazoInsumo(FecetRechazoInsumo fecetRechazoInsumo) {
        fecetRechazoInsumoDao.update(new FecetRechazoInsumoPk(fecetRechazoInsumo.getIdRechazoInsumo()), fecetRechazoInsumo);
    }

    @Override
    @PistaAuditoria
    public String actualizaEstatusRetroalimentacionInsumo(final FecetInsumo insumo, final BigDecimal idRetroalimentacionInsumo, String motivoAciace,
            Date fechaRetro) {
        fecetRetroalimentacionInsumoDao.actualizaInsumoRetroAtendida(idRetroalimentacionInsumo, motivoAciace, fechaRetro);
        return insumo.getIdRegistro();
    }

    public void guardarDocumentosFaltantes(List<FecetDocretroinsumo> listaDocumento) throws NegocioException {
        try {
            for (FecetDocretroinsumo documento : listaDocumento) {
                if (documento.getIdDocretroinsumo() == null) {
                    this.fecetDocretroinsumoDao.insert(documento);
                    CargaArchivoUtil.guardarArchivo(documento.getArchivo(), documento.getRutaArchivo(), documento.getNombreArchivo());
                }
            }
        } catch (IOException e) {
            logger.error(Constantes.CONSULTA_ORDENES, e);
            throw new NegocioException(e.getCause().toString(), e);
        }
    }

    @Override
    public List<FecetInsumo> getListaInsumoCreado() throws NegocioException {
        return this.getListaSeguimientoInsumoEstatus(INSUMO_CREADO);
    }

    @Override
    public List<FecetInsumo> getListaInsumoPorRetroalimentar() throws NegocioException {
        return this.getListaSeguimientoInsumoEstatus(Constantes.INSUMO_POR_RETROALIMENTAR);
    }

    @Override
    public List<FecetInsumo> getListaInsumoRechazado() throws NegocioException {
        return this.getListaSeguimientoInsumoEstatus(INSUMO_RECHAZADO);
    }

    @Override
    public List<FecetDocExpInsumo> obtenerDocumentos(BigDecimal idInsumo) {

        return fecetDocExpInsumoDao.findWhereIdInsumoEquals(idInsumo);

    }

    @Override
    @PistaAuditoria
    public List<FecetDocExpInsumo> obtenerDocumentosAsignados(FecetInsumo insumo) {
        List<FecetDocExpInsumo> documentos = fecetDocExpInsumoDao.findWhereIdInsumoEquals(insumo.getIdInsumo());
        for (FecetDocExpInsumo fecetDocExpInsumo : documentos) {
            fecetDocExpInsumo.setIdRegistroInsumo(insumo.getIdRegistro());
        }
        return documentos;
    }

    @Override
    @PistaAuditoria
    public List<FecetDocExpInsumo> obtenerDocumentosRechazados(FecetInsumo insumo) {
        List<FecetDocExpInsumo> documentos = fecetDocExpInsumoDao.findWhereIdInsumoEquals(insumo.getIdInsumo());
        for (FecetDocExpInsumo fecetDocExpInsumo : documentos) {
            fecetDocExpInsumo.setIdRegistroInsumo(insumo.getIdRegistro());
        }
        return documentos;
    }

    @Override
    public List<FecetDocExpInsumo> obtenerDocumentosRtroalimentados(BigDecimal idInsumo) {
        return fecetDocExpInsumoDao.findWhereIdInsumoEquals(idInsumo);
    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroalimentadosEstatus(BigDecimal idRetroalimentacionInsumo, BigDecimal tipoEmpleado) {
        return fecetDocretroinsumoDao.obtenerRetroalimentacionByTipoEmpleado(idRetroalimentacionInsumo, tipoEmpleado);
    }

    @Override
    public boolean validarDuplicado(FecetInsumo insumoSeleccionado, String rfcContribuyente) {
        return fecetInsumoDao.existeRegistroDuplicado(insumoSeleccionado, rfcContribuyente);
    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroalimentadosByIdRetroInsumo(BigDecimal idRetroalimentacionInsumo, BigDecimal idTipoEmpleado) {
        return fecetDocretroinsumoDao.findByFecetRetroalimentacionInsumo(idRetroalimentacionInsumo, idTipoEmpleado);
    }

    @Override
    public void actualizaInsumo(FecetInsumo insumo) {
        plazosServiceInsumos.validarReactivacionPlazo(insumo);
        this.fecetInsumoDao.update(insumo.createPk(), insumo);
    }

    @Override
    public List<FecetDocumento> buscarDocumentoJustificacion(FecetInsumo insumo) {
        return obtenerDocumentosByIdInsumo(insumo);
    }
}
