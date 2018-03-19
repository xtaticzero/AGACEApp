package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececMotivoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasTransferidasPendValidService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Service("propuestasTransferidasPendValidService")
public class PropuestasTransferidasPendValidServiceImpl extends PropuestasServiceAbstract
        implements PropuestasTransferidasPendValidService {

    private static final long serialVersionUID = -4940923887805205004L;

    @Autowired
    private transient FecetTransferenciaDao fecetTransferenciaDao;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient FecetRechazoPropuestaDao fecetRechazoPropuestaDao;

    @Autowired
    private transient FececMotivoDao fececMotivoDao;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Override
    public List<FecetTransferencia> buscarTransferenciasPorIdPropuesta(BigDecimal idPropuesta) {
        return fecetTransferenciaDao.findWhereIdPropuestaEqualsTransfPendientes(idPropuesta);
    }

    @Override
    public List<FecetTransferencia> buscarTransferenciasPorIdTransferencia(BigDecimal idTransferencia) {
        return fecetTransferenciaDao.findDocWhereIdTransferenciaEquals(idTransferencia);
    }

    @Override
    public void actualizaEstatusPropuesta(BigDecimal idPropuesta) {
        fecetPropuestaDao.actualizaEstadoPropuesta(TipoEstatusEnum.PROPUESTA_TRANSFERIDA, new Date(), idPropuesta);
    }

    @Override
    public List<ProgramadorPropuestaAsignadaDTO> getProgramadorTransferencia(BigDecimal idArace) {

        try {
            List<EmpleadoDTO> programadoresUnidad = getEmpleadosXUnidadAdmva(idArace.intValue(), Constantes.ENTERO_TRES,
                    ClvSubModulosAgace.PROPUESTAS);

            if (programadoresUnidad != null && !programadoresUnidad.isEmpty()) {

                StringBuilder condicionBusqueda = new StringBuilder();

                for (int i = 0; i < programadoresUnidad.size(); i++) {
                    if (i == programadoresUnidad.size() - 1) {
                        condicionBusqueda.append(programadoresUnidad.get(i).getIdEmpleado());
                    } else {
                        condicionBusqueda.append(programadoresUnidad.get(i).getIdEmpleado());
                        condicionBusqueda.append(",");
                    }
                }

                List<ProgramadorPropuestaAsignadaDTO> programadorAsignado = fecetPropuestaDao
                        .buscarProgramadorAsignar(condicionBusqueda.toString(), idArace);
                List<ProgramadorPropuestaAsignadaDTO> programadorSeleccionado = determinaIdProgramadorAsignado(
                        programadorAsignado, programadoresUnidad);

                if (programadorSeleccionado != null && !programadorSeleccionado.isEmpty()) {
                    return programadorSeleccionado;
                } else {
                    return programadorAsignado;
                }
            }
        } catch (EmpleadoServiceException e) {
            logger.error("No se encontraron los usuarios");
        }

        return null;
    }

    @Override
    public void actualizarIdProgramador(final BigDecimal idPropuesta, final BigDecimal idProgramador) {
        fecetPropuestaDao.actualizarIdProgramador(idPropuesta, idProgramador);
    }

    @Override
    public void actualizaEstatusPropuestaRechazoT(BigDecimal idPropuesta, BigDecimal estatus) {
        fecetPropuestaDao.actualizarEstatusNoAprobadaTransfer(idPropuesta, estatus.intValue());
    }

    @Override
    public void rechazarPropuesta(FecetPropuesta propuesta, FecetRechazoPropuesta fecetRechazo,
            List<FecetRechazoPropuesta> listaRechazo) {
        fecetRechazoPropuestaDao.insert(fecetRechazo);
        for (FecetRechazoPropuesta fecetRechazoPropuesta : listaRechazo) {
            fecetRechazo.setIdDocRechazo(fecetRechazoPropuestaDao.getConsecutivoDocRechazo());
            fecetRechazo.setNombreArchivo(fecetRechazoPropuesta.getNombreArchivo());
            fecetRechazo.setRutaArchivo(RutaArchivosUtil.generarRutaArchivoValidaPropuestas(
                    RutaArchivosEnum.RUTA_DOCUMENTOS_RECHAZO_PROPUESTAS, propuesta) + fecetRechazo.getNombreArchivo());
            fecetRechazo.setArchivo(fecetRechazoPropuesta.getArchivo());
            fecetRechazo.setFechaRechazo(new Date());
            fecetRechazo.setFechaCreacion(new Date());
            fecetRechazo.setBlnActivo(new BigDecimal(1L));
            fecetRechazoPropuestaDao.insertDoc(fecetRechazo);
            try {
                CargaArchivoUtil.guardarArchivoPropuestaRechazada(fecetRechazo);
            } catch (IOException e) {
                logger.error("Error al guardar el archivo en la ruta {} : {}", fecetRechazo.getRutaArchivo(),
                        e.getMessage());
            }
        }
    }

    @Override
    public List<AgaceOrden> obtenerOrden(BigDecimal idPropuesta) {
        return agaceOrdenDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FececMotivo> buscarMotivos(String tipoMotivo) {
        return fececMotivoDao.findWhereTipoMotivoEquals(tipoMotivo);
    }

    @Override
    public void desactivaTransferencia(Long idTransferencia) {
        fecetTransferenciaDao.actualizarEstadoTransferencia(idTransferencia, EstadoBooleanodeRegistroEnum.INACTIVO);
    }

    @Override
    public void updateIdPropuestaNueva(BigDecimal idPropuestaNueva, BigDecimal idTransferencia) {
        fecetTransferenciaDao.updateIdPropuestaNueva(idPropuestaNueva, idTransferencia);
    }

    @Override
    public void updateFechaMotivoRechazo(Date fechaRechazo, BigDecimal idMotivo, BigDecimal idTransferencia) {
        fecetTransferenciaDao.updateTransferenciaRechazo(fechaRechazo, idMotivo, idTransferencia);
    }

    @Override
    public BigDecimal obtenIdProgramadorPropuesta(BigDecimal idPropuesta) {
        return fecetPropuestaDao.getIdProgramador(idPropuesta);
    }

    @Override
    public BigDecimal buscarTipoEmpleadoPropuesta(BigDecimal idPropuesta) {
        return fecetPropuestaDao.getTipoEmpleadoCreacionByPropuesta(idPropuesta);
    }

    public void setFecetTransferenciaDao(FecetTransferenciaDao fecetTransferenciaDao) {
        this.fecetTransferenciaDao = fecetTransferenciaDao;
    }

    public FecetTransferenciaDao getFecetTransferenciaDao() {
        return fecetTransferenciaDao;
    }

    public void setFecetPropuestaDao(FecetPropuestaDao fecetPropuestaDao) {
        this.fecetPropuestaDao = fecetPropuestaDao;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public void setFecetRechazoPropuestaDao(FecetRechazoPropuestaDao fecetRechazoPropuestaDao) {
        this.fecetRechazoPropuestaDao = fecetRechazoPropuestaDao;
    }

    public FecetRechazoPropuestaDao getFecetRechazoPropuestaDao() {
        return fecetRechazoPropuestaDao;
    }

    public void setFececMotivoDao(FececMotivoDao fececMotivoDao) {
        this.fececMotivoDao = fececMotivoDao;
    }

    public FececMotivoDao getFececMotivoDao() {
        return fececMotivoDao;
    }

    public void setAgaceOrdenDao(AgaceOrdenDao agaceOrdenDao) {
        this.agaceOrdenDao = agaceOrdenDao;
    }

    public AgaceOrdenDao getAgaceOrdenDao() {
        return agaceOrdenDao;
    }

    private List<ProgramadorPropuestaAsignadaDTO> determinaIdProgramadorAsignado(
            List<ProgramadorPropuestaAsignadaDTO> programadoresAsignados, List<EmpleadoDTO> programadoresUnidad) {

        List<ProgramadorPropuestaAsignadaDTO> programadorAsignado = new ArrayList<ProgramadorPropuestaAsignadaDTO>();
        ProgramadorPropuestaAsignadaDTO programador = new ProgramadorPropuestaAsignadaDTO();

        if (programadoresAsignados == null || programadoresAsignados.isEmpty()) {

            programador.setIdEmpleado(programadoresUnidad.get(0).getIdEmpleado());
            programador.setTotalPropuestas(Constantes.ENTERO_CERO);

            programadorAsignado.add(programador);
        } else {
            for (int i = 0; i < programadoresAsignados.size(); i++) {
                for (int j = 0; j < programadoresUnidad.size(); j++) {

                    if (programadoresAsignados.get(i).getIdEmpleado() != null && programadoresUnidad.get(j)
                            .getIdEmpleado().equals(programadoresAsignados.get(i).getIdEmpleado())) {
                        programadoresUnidad.remove(j);
                    }
                }
            }

            if (programadoresUnidad.size() > 0) {

                programador.setIdEmpleado(programadoresUnidad.get(0).getIdEmpleado());
                programador.setTotalPropuestas(Constantes.CERO);

                programadorAsignado.add(programador);
            }
        }

        return programadorAsignado;
    }
}
