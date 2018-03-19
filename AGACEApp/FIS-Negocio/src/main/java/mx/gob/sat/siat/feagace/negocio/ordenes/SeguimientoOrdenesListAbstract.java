/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCambioMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosRechazoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.dao.SuspensionDAO;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.AsociadoFuncionarioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanalPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoProrrogasRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarOficioAdministrable;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class SeguimientoOrdenesListAbstract extends SeguimientoOrdenesAbstract {

    private static final long serialVersionUID = 583027086882230606L;

    protected static final String ERROR_NEGOCIO = "Negocio: ";

    protected static final String APROBADA = "APROBADA";

    protected static final String RECHAZADA = "RECHAZADA";

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FecetPromocionDao fecetPromocionDao;
    @Autowired
    private transient FecetAlegatoDao fecetAlegatoDao;
    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    @Autowired
    private transient FecetDocProrrogaOrdenDao fecetDocProrrogaOrdenDao;
    @Autowired
    private transient FecetAnexosProrrogaOrdenDao fecetAnexosProrrogaOrdenDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FecetPromocionOficioDao fecetPromocionOficioDao;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetAnexosRechazoOficioDao fecetAnexosRechazoOficioDao;
    @Autowired
    private transient FecetFlujoProrrogaOrdenDao fecetFlujoProrrogaOrdenDao;
    @Autowired
    private transient FececEstatusDao fececEstatusDao;
    @Autowired
    private transient FecetDocProrrogaOficioDao fecetDocProrrogaOficioDao;
    @Autowired
    private transient FecetAnexosProrrogaOficioDao fecetAnexosProrrogaOficioDao;
    @Autowired
    private transient FecetFlujoProrrogaOficioDao fecetFlujoProrrogaOficioDao;
    @Autowired
    private transient FecetCambioMetodoDao fecetCambioMetodoDao;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient SuspensionDAO suspensionDAO;
    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;
    @Autowired
    private transient FecetDocPruebasPericialesDao fecetDocPruebasPericialesDao;
    @Autowired
    private transient FecetFlujoPruebasPericialesDao fecetFlujoPruebasPericialesDao;
    @Autowired
    private transient FecetAnexoPruebasPericialesDao fecetAnexoPruebasPericialesDao;
    @Autowired
    private transient FecetPromocionAgenteAduanalDao fecetPromocionAgenteAduanalDao;
    @Autowired
    private transient FecetAlegatoAgenteAduanalDao fecetAlegatoAgenteAduanalDao;
    @Autowired
    private transient AsociadoFuncionarioDao asociadoFuncionarioDao;
    @Autowired
    private transient ValidarOficioAdministrable validarOficioAdministrable;
    @Autowired
    private transient ValidarMetodoProrrogasRule validarMetodoProrrogasRule;

    public AsociadoFuncionarioDao getAsociadoFuncionarioDao() {
        return asociadoFuncionarioDao;
    }

    public void setAsociadoFuncionarioDao(AsociadoFuncionarioDao asociadoFuncionarioDao) {
        this.asociadoFuncionarioDao = asociadoFuncionarioDao;
    }

    public List<FecetPromocion> getPromocionContadorPruebasAlegatos(AgaceOrden orden) {
        List<FecetPromocion> listaPromociones
                = fecetPromocionDao.getPromocionContadorPruebasAlegatos(orden.getIdOrden());

        for (FecetPromocion promocion : listaPromociones) {
            promocion.setDescripcionTipoPromocion(BusinessUtil.getTipoPromocionOrdenPorMetodo(orden.getIdMetodo()));
            promocion.setExtemporanea(plazosService.esDocumentoExtemporaneoOrden(orden, promocion.getFechaCarga()) ? "1" : "0");
        }
        return listaPromociones;
    }

    public List<FecetProrrogaOrden> getProrrogaPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden) {
        List<FecetProrrogaOrden> prorrogasValidar = fecetProrrogaOrdenDao.getProrrogaPorOrdenEstatusPendienteAuditor(idOrden);
        for (FecetProrrogaOrden prorroga : prorrogasValidar) {
            if (prorroga.getFecetFlujoProrrogaOrden() != null && prorroga.getFecetFlujoProrrogaOrden().getIdEstatus().equals(EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getBigIdEstatus())) {
                prorroga.getFececEstatus().setDescripcion(EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getNombre());
            } else if (prorroga.getFecetFlujoProrrogaOrden() != null && prorroga.getFecetFlujoProrrogaOrden().getIdEstatus().equals(EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getBigIdEstatus())) {
                prorroga.getFececEstatus().setDescripcion(EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getNombre());
            }
        }
        return prorrogasValidar;
    }

    public List<FecetProrrogaOficio> getProrrogaPorOficioEstatusPendienteAuditor(final BigDecimal idOficio) {
        List<FecetProrrogaOficio> prorrogasPendientes = getFecetProrrogaOficioDao().getProrrogaPorOficioEstatusPendienteAuditor(idOficio);
        for (FecetProrrogaOficio prorroga : prorrogasPendientes) {
            if (prorroga.getFecetFlujoProrrogaOficio() != null && prorroga.getFecetFlujoProrrogaOficio().getIdEstatus().equals(EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getBigIdEstatus())) {
                prorroga.getFececEstatus().setDescripcion(EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getNombre());
            } else if (prorroga.getFecetFlujoProrrogaOficio() != null && prorroga.getFecetFlujoProrrogaOficio().getIdEstatus().equals(EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getBigIdEstatus())) {
                prorroga.getFececEstatus().setDescripcion(EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getNombre());
            }
        }
        return prorrogasPendientes;
    }

    public List<FecetProrrogaOrden> getHistoricoProrrogaOrden(final BigDecimal idOrden) {
        List<FecetProrrogaOrden> historicoProrrogas = fecetProrrogaOrdenDao.getHistoricoProrrogaPorOrden(idOrden);

        for (FecetProrrogaOrden prorroga : historicoProrrogas) {
            if (prorroga.getIdEstatus().equals(Constantes.ESTATUS_PRORROGA_NOTIFICADA_CONTRIBUYENTE)) {
                prorroga.setDescripcionEstado(prorroga.getFececEstatus().getDescripcion());
            } else {
                prorroga.setDescripcionEstado(this.fececEstatusDao.findWhereIdEstatusEquals(prorroga.getFecetFlujoProrrogaOrden().getIdEstatus()).get(0).getDescripcion());
            }
        }

        return historicoProrrogas;
    }

    public List<FecetAlegato> getPruebasAlegatosPromocion(final BigDecimal idPromocion) {
        return fecetAlegatoDao.findWhereIdPromocionEquals(idPromocion);
    }

    public List<FecetDocPruebasPericiales> getDocumentacionPruebaPericialContribuyente(final BigDecimal idPruebasPericiales) {
        return fecetDocPruebasPericialesDao.findWhereIdPruebasPericialesEquals(idPruebasPericiales);
    }

    public List<FecetPruebasPericiales> getPruebaPericialPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden) {
        List<FecetPruebasPericiales> pruebasPericialesValidar = getFecetPruebasPericialesDao().getPruebasPericialesPorOrdenEstatusPendienteAuditor(idOrden);
        for (FecetPruebasPericiales pruebaPericial : pruebasPericialesValidar) {
            if (pruebaPericial.getFlujoPruebasPericiales() != null && pruebaPericial.getFlujoPruebasPericiales().getIdEstatus().equals(EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_APROBADA_FIRMANTE.getBigIdEstatus())) {
                pruebaPericial.getFececEstatus().setDescripcion(EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_APROBADA_FIRMANTE.getNombre());
            } else if (pruebaPericial.getFlujoPruebasPericiales() != null && pruebaPericial.getFlujoPruebasPericiales().getIdEstatus().equals(EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_RECHAZADA_FIRMANTE.getBigIdEstatus())) {
                pruebaPericial.getFececEstatus().setDescripcion(EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_RECHAZADA_FIRMANTE.getNombre());
            }
        }
        return pruebasPericialesValidar;
    }

    public List<FecetPruebasPericiales> getHistoricoPruebasPericiales(final BigDecimal idOrden) {
        List<FecetPruebasPericiales> historicoPruebasPericiales = getFecetPruebasPericialesDao().getHistoricoPruebasPericialesPorOrden(idOrden);

        for (FecetPruebasPericiales pruebaPericial : historicoPruebasPericiales) {
            if (pruebaPericial.getIdEstatus().equals(Constantes.ESTATUS_PRUEBAS_PERICIALES_NOTIFICADA_CONTRIBUYENTE)) {
                pruebaPericial.setDescripcionEstado(pruebaPericial.getFececEstatus().getDescripcion());
            } else {
                pruebaPericial.setDescripcionEstado(this.fececEstatusDao.findWhereIdEstatusEquals(pruebaPericial.getFlujoPruebasPericiales().getIdEstatus()).get(0).getDescripcion());
            }
        }

        return historicoPruebasPericiales;
    }

    protected void insertaFlujoProrrogaOficioAuditor(FecetFlujoProrrogaOficio flujoProrroga,
            final List<FecetAnexosProrrogaOficio> listaAnexos,
            final String pathAnexos,
            final String tipoAnexos) throws NegocioException {
        try {

            getFecetFlujoProrrogaOficioDao().insert(flujoProrroga);

            for (FecetAnexosProrrogaOficio anexo : listaAnexos) {
                String rutaCompleta = pathAnexos + anexo.getNombreArchivo();
                anexo.setIdAnexosProrrogaOficio(this.getFecetAnexosProrrogaOficioDao().getIdFecetAnexosProrrogaOficioPathDirectorio());
                anexo.setIdFlujoProrrogaOficio(flujoProrroga.getIdFlujoProrrogaOficio());
                anexo.setRutaArchivo(pathAnexos);
                anexo.setTipoAnexo(tipoAnexos);
                CargaArchivoUtil.guardarArchivoAnexoProrrogaOficio(anexo);
                anexo.setRutaArchivo(rutaCompleta);
                getFecetAnexosProrrogaOficioDao().insert(anexo);
            }

            getFecetFlujoProrrogaOficioDao().actualizarFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOficio(flujoProrroga.getIdProrrogaOficio());
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

    public EmpleadoDTO obtenerDatosAuditor(String rfcAuditor) {

        EmpleadoDTO auditor = new EmpleadoDTO();

        try {

            auditor = getEmpleadoCompleto(rfcAuditor);

            if (!validarExistenciaTipoEmpleado(auditor, TipoEmpleadoEnum.AUDITOR)) {
                auditor = null;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            auditor = null;
        }

        return auditor;
    }
    
    public boolean validaMetodoProrrogaOficio(AgaceOrden orden, FecetOficio oficio){
        return validarMetodoProrrogasRule.validaMetodoProrrogaOficio(orden, oficio);
    }

    public FecetPropuesta getPropuestaOrden(BigDecimal idPropuesta) {
        return getFecetPropuestaDao().findWhereIdPropuestaEquals(idPropuesta).get(0);
    }

    public List<AsociadoFuncionarioDTO> buscarAsociadoFuncionarioActivoByOrden(AsociadoFuncionarioDTO dto) {
        return getAsociadoFuncionarioDao().obtenerAsociadoActivoByIdPropuesta(dto);
    }

    public void actualizarAsociadoFuncionario(AsociadoFuncionarioDTO dto) {
        getAsociadoFuncionarioDao().update(dto);
    }

    public FecetOficio obtenerOficioById(final FecetOficio oficio) {
        return getFecetOficioDao().getOficioByIdOficio(oficio.getIdOficioPrincipal()).get(0);
    }
    
    public List<FecetPromocionAgenteAduanal> getPromocionAgenteAduanalContadorPruebasAlegatos(BigDecimal idOrden) {
        return getFecetPromocionAgenteAduanalDao().getPromocionAgenteAduanalContadorPruebasAlegatos(idOrden);
    }

    public FecetPromocionAgenteAduanalPk guardaDocumentoPromocionAgenteAduanal(FecetPromocionAgenteAduanal promocion) {
        return getFecetPromocionAgenteAduanalDao().insertarRegistroGuardarArchivoPromocionAgenteAduanal(promocion);
    }

    public void guardaDocumentoAlegatoAgenteAduanal(FecetAlegatoAgenteAduanal alegato) {
        getFecetAlegatoAgenteAduanalDao().insert(alegato);
    }

    public List<FecetAlegatoAgenteAduanal> getAlegatosAgenteAduanal(final BigDecimal idPromocion) {
        return getFecetAlegatoAgenteAduanalDao().findWhereIdPromocionEquals(idPromocion);
    }

    public AgaceOrdenDao getAgaceOrdenDao() {
        return agaceOrdenDao;
    }

    public FecetPromocionDao getFecetPromocionDao() {
        return fecetPromocionDao;
    }

    public FecetAlegatoDao getFecetAlegatoDao() {
        return fecetAlegatoDao;
    }

    public FecetDocProrrogaOrdenDao getFecetDocProrrogaOrdenDao() {
        return fecetDocProrrogaOrdenDao;
    }

    public FecetAnexosProrrogaOrdenDao getFecetAnexosProrrogaOrdenDao() {
        return fecetAnexosProrrogaOrdenDao;
    }

    public FeceaMetodoDao getFeceaMetodoDao() {
        return feceaMetodoDao;
    }

    public FecetPromocionOficioDao getFecetPromocionOficioDao() {
        return fecetPromocionOficioDao;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public FecetAnexosRechazoOficioDao getFecetAnexosRechazoOficioDao() {
        return fecetAnexosRechazoOficioDao;
    }

    public FecetFlujoProrrogaOrdenDao getFecetFlujoProrrogaOrdenDao() {
        return fecetFlujoProrrogaOrdenDao;
    }

    public FececEstatusDao getFececEstatusDao() {
        return fececEstatusDao;
    }

    public FecetDocProrrogaOficioDao getFecetDocProrrogaOficioDao() {
        return fecetDocProrrogaOficioDao;
    }

    public FecetAnexosProrrogaOficioDao getFecetAnexosProrrogaOficioDao() {
        return fecetAnexosProrrogaOficioDao;
    }

    public FecetFlujoProrrogaOficioDao getFecetFlujoProrrogaOficioDao() {
        return fecetFlujoProrrogaOficioDao;
    }

    public FecetCambioMetodoDao getFecetCambioMetodoDao() {
        return fecetCambioMetodoDao;
    }

    public PlazosServiceOrdenes getPlazosService() {
        return plazosService;
    }

    public SuspensionDAO getSuspensionDAO() {
        return suspensionDAO;
    }

    public FecetDocOrdenDao getFecetDocOrdenDao() {
        return fecetDocOrdenDao;
    }

    public FecetDocPruebasPericialesDao getFecetDocPruebasPericialesDao() {
        return fecetDocPruebasPericialesDao;
    }

    public FecetFlujoPruebasPericialesDao getFecetFlujoPruebasPericialesDao() {
        return fecetFlujoPruebasPericialesDao;
    }

    public FecetAnexoPruebasPericialesDao getFecetAnexoPruebasPericialesDao() {
        return fecetAnexoPruebasPericialesDao;
    }

    public FecetPromocionAgenteAduanalDao getFecetPromocionAgenteAduanalDao() {
        return fecetPromocionAgenteAduanalDao;
    }

    public FecetAlegatoAgenteAduanalDao getFecetAlegatoAgenteAduanalDao() {
        return fecetAlegatoAgenteAduanalDao;
    }

    public ValidarOficioAdministrable getValidarOficioAdministrable() {
        return validarOficioAdministrable;
    }

    public void setValidarOficioAdministrable(ValidarOficioAdministrable validarOficioAdministrable) {
        this.validarOficioAdministrable = validarOficioAdministrable;
    }

}
