package mx.gob.sat.siat.feagace.negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.UnidadDesahogoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.common.helper.EmpleadoServiceHelper;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompleto;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.INPC;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimo;
import mx.gob.sat.siat.ws.empleado.bean.Vacacion;
import mx.gob.sat.siat.ws.empleado.client.EmpleadoUnitedServiceClient;
import mx.gob.sat.siat.ws.empleado.exception.EmpleadoServiceClientException;

@Service("empleadoService")
public class EmpleadoServiceImpl extends BaseBusinessServices implements
        EmpleadoService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 8533662115692414570L;

    private static final int SUBORDINADOS = 1;
    private static final int JEFES_INMEDIATOS = 2;

    @Autowired
    @Qualifier("unidadDesahogoDao")
    private transient UnidadDesahogoDao unidadDesahogoDao;

    @Autowired
    @Qualifier("empleadoUnitedServiceClient")
    private transient EmpleadoUnitedServiceClient empleadoUnitedClient;

    @Override
    public List<Vacacion> traeVacaciones(Integer periodo) throws EmpleadoServiceException {
        try {
            return empleadoUnitedClient.traeVacaciones(periodo);
        } catch (EmpleadoServiceClientException e) {
            logger.error(e.getMessage(), e);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, e);
        }
    }

    @Override
    public List<FechasHabileseInhabiles> getCatalogoDiasHabilesInhabiles(Date fechaInicio, Date fechaFin) throws EmpleadoServiceException {
        try {
            return empleadoUnitedClient.getCatalogoDiasHabilesInhabiles(fechaInicio, fechaFin);
        } catch (EmpleadoServiceClientException e) {
            logger.error(e.getMessage(), e);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, e);
        }
    }

    @Override
    public SalarioMinimo traeSalario() throws EmpleadoServiceException {
        try {
            return empleadoUnitedClient.traeSalario();
        } catch (EmpleadoServiceClientException e) {
            logger.error(e.getMessage(), e);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, e);
        }
    }

    @Override
    public List<INPC> traeINPC(Integer periodo) throws EmpleadoServiceException {
        try {
            return empleadoUnitedClient.traeINPC(periodo);
        } catch (EmpleadoServiceClientException e) {
            logger.error(e.getMessage(), e);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, e);
        }
    }

    @Override
    public List<EmpleadoDTO> getEmpleadosXUnidadAdmva(Integer claveALAF,
            Integer clavePerfil,
            ClvSubModulosAgace claveModulo) throws EmpleadoServiceException {
        try {
            if (clavePerfil != null && claveALAF != null) {

                List<Empleado> lstEmpleado = empleadoUnitedClient.getEmpleadosXUnidadAdmva(empleadoUnitedClient.getTipoFiscalizacion(),
                        claveALAF,
                        clavePerfil,
                        claveModulo.getIdClvSubModulo());

                if (lstEmpleado != null) {
                    for (Empleado empleado : lstEmpleado) {
                        List<AraceDTO> lstUnidadAdmin = getUnidadesAdministrativas(empleado);
                        return getLstEmpleadoDtoFromLstEmpleado(lstEmpleado, getMapAraceDTOFromLstUnidadAdmin(lstUnidadAdmin, empleado), lstUnidadAdmin);
                    }

                } else {
                    return null;
                }
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new EmpleadoServiceException(MSG_ERROR_METODO_SERVICIO, " getEmpleadosXUnidadAdmva() ", ex);
        }
        return null;
    }

    private List<EmpleadoDTO> getLstEmpleadoDtoFromLstEmpleado(List<Empleado> lstEmpleado, Map<Integer, AraceDTO> mapUnidadAdmin, List<AraceDTO> lstUnidadAdmin) throws EmpleadoServiceClientException, EmpleadoServiceException {
        if (lstEmpleado != null) {
            List<EmpleadoDTO> lstEmpleadoDto = new ArrayList<EmpleadoDTO>();
            for (Empleado emp : lstEmpleado) {
                EmpleadoCompleto empCompleto = empleadoUnitedClient.getEmpleadoCompleto(empleadoUnitedClient.getTipoFiscalizacion(), emp.getRfc(), null);
                EmpleadoDTO empTmp = EmpleadoServiceHelper.getEmpleadoDTOfromEmpleadoCompleto(empCompleto,
                        getMapTipoEmpleado(),
                        mapUnidadAdmin);
                completarLstAracesDetalle(empTmp, mapUnidadAdmin, lstUnidadAdmin);
                lstEmpleadoDto.add(EmpleadoServiceHelper.getEmpleadoDTOfromEmpleadoCompleto(empCompleto,
                        getMapTipoEmpleado(),
                        getMapAraceDTOFromLstUnidadAdmin(empTmp)));

            }
            return lstEmpleadoDto;
        } else {
            return null;
        }
    }

    @Override
    public EmpleadoDTO getEmpleadoCompleto(Integer numeroEmpleado) throws EmpleadoServiceException {
        try {
            if (numeroEmpleado != null) {

                EmpleadoCompleto empCompleto = empleadoUnitedClient.getEmpleadoCompleto(empleadoUnitedClient.getTipoFiscalizacion(), null, numeroEmpleado);
                EmpleadoDTO empleadoDto = EmpleadoServiceHelper.getEmpleadoDTOfromEmpleadoCompleto(empCompleto,
                        getMapTipoEmpleado(),
                        getMapAraceDTOFromLstUnidadAdmin(empCompleto.getEmpleado()));

                List<AraceDTO> lstUnidadAdmin = getUnidadesAdministrativas(empleadoDto);
                Map<Integer, AraceDTO> mapUnidadAdmin = getMapAraceDTOFromLstUnidadAdmin(lstUnidadAdmin, empleadoDto);

                completarJefeYSubordinados(empleadoDto, empCompleto, getMapTipoEmpleado(), mapUnidadAdmin);
                completarLstAracesDetalle(empleadoDto, mapUnidadAdmin, lstUnidadAdmin);
                return empleadoDto;
            } else {
                return null;
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new EmpleadoServiceException(MSG_ERROR_METODO_SERVICIO, " getEmpleadoCompleto() ", ex);
        }
    }

    @Override
    public EmpleadoDTO getInformacionEmpleado(String rfcEmpleado) throws EmpleadoServiceException {
        try {
            if (rfcEmpleado != null && !rfcEmpleado.isEmpty()) {
                logger.error("metodo getInformacionEmpleado(" + rfcEmpleado + ")");

                EmpleadoCompleto empCompleto = empleadoUnitedClient.getInformacionEmpleado(empleadoUnitedClient.getTipoFiscalizacion(), rfcEmpleado, null);

                List<AraceDTO> lstUnidadAdmin = getUnidadesAdministrativas(empCompleto.getEmpleado());
                Map<Integer, AraceDTO> mapUnidadAdmin = getMapAraceDTOFromLstUnidadAdmin(lstUnidadAdmin, empCompleto.getEmpleado());

                EmpleadoDTO empleadoDto = EmpleadoServiceHelper.getEmpleadoDTOfromEmpleadoCompleto(empCompleto,
                        getMapTipoEmpleado(),
                        mapUnidadAdmin);
                logger.error("se completa de detalle del empleado completarJefeYSubordinados");
                completarJefeYSubordinados(empleadoDto, empCompleto, getMapTipoEmpleado(), mapUnidadAdmin);
                logger.error("se completa de detalle del empleado completarLstAracesDetalle");
                completarLstAracesDetalle(empleadoDto, mapUnidadAdmin, lstUnidadAdmin);
                return empleadoDto;
            } else {
                return null;
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new EmpleadoServiceException(MSG_ERROR_METODO_SERVICIO, " getEmpleadoCompleto() ", ex);
        }
    }

    @Override
    public EmpleadoDTO getEmpleadoCompleto(String rfc) throws EmpleadoServiceException {
        try {
            if (rfc != null && !rfc.isEmpty()) {
                EmpleadoCompleto empCompleto = empleadoUnitedClient.getInformacionEmpleado(empleadoUnitedClient.getTipoFiscalizacion(), rfc, null);

                if (empCompleto == null) {
                    logger.error("metodo getEmpleadoCompleto(" + rfc + ") : no se encontro informacion del empleado");
                    throw new EmpleadoServiceException(MSG_ERROR_EMPLEADO_NO_EXISTE);
                }

                List<AraceDTO> lstUnidadAdmin = getUnidadesAdministrativas(empCompleto.getEmpleado());
                Map<Integer, AraceDTO> mapUnidadAdmin = getMapAraceDTOFromLstUnidadAdmin(lstUnidadAdmin, empCompleto.getEmpleado());

                EmpleadoDTO empleadoDto = EmpleadoServiceHelper.getEmpleadoDTOfromEmpleadoCompleto(empCompleto,
                        getMapTipoEmpleado(),
                        mapUnidadAdmin);
                logger.error("se completa de detalle del empleado completarJefeYSubordinados");
                completarJefeYSubordinados(empleadoDto, empCompleto, getMapTipoEmpleado(), mapUnidadAdmin);
                logger.error("se completa de detalle del empleado completarLstAracesDetalle");
                completarLstAracesDetalle(empleadoDto, mapUnidadAdmin, lstUnidadAdmin);
                return empleadoDto;
            } else {
                return null;
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new EmpleadoServiceException(MSG_ERROR_METODO_SERVICIO, " getEmpleadoCompleto() ", ex);
        }
    }

    @Override
    public Map<Integer, TipoEmpleadoEnum> getMapTipoEmpleado() {
        Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado = new HashMap<Integer, TipoEmpleadoEnum>();
        for (TipoEmpleadoEnum tipo : TipoEmpleadoEnum.values()) {
            mapTipoEmpleado.put((int) tipo.getId(), tipo);
        }
        return mapTipoEmpleado;
    }

    @Override
    public List<EmpleadoDTO> informacionEmpleados(Integer[] numeroEmpleados) throws EmpleadoServiceException {
        try {
            List<Empleado> lstEmpleado = empleadoUnitedClient.informacionEmpleados(numeroEmpleados, empleadoUnitedClient.getTipoFiscalizacion());
            if (lstEmpleado != null) {
                for (Empleado empleado : lstEmpleado) {
                    List<AraceDTO> lstUnidadAdmin = getUnidadesAdministrativas(empleado);
                    return getLstEmpleadoDtoFromLstEmpleado(lstEmpleado, getMapAraceDTOFromLstUnidadAdmin(lstUnidadAdmin, empleado), lstUnidadAdmin);
                }
                return null;
            } else {
                return null;
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new EmpleadoServiceException(MSG_ERROR_METODO_SERVICIO, " informacionEmpleados() ", ex);
        }
    }

    @Override
    public List<AraceDTO> getUnidadesAdministrativas(EmpleadoDTO empCompleto) throws EmpleadoServiceException {
        try {

            if (!(empCompleto != null && empCompleto.getIdAdmGral() != null)) {
                throw new EmpleadoServiceException(MSG_ERROR_UNUDAD_ADMINISTRATIVA_NO_EXISTE);
            }
            return EmpleadoServiceHelper.getLstAracesDtoFromLstUnidadesAdmin(empleadoUnitedClient.getUnidadesAdministrativasPorGeneral(empCompleto.getIdAdmGral()));
        } catch (Exception ex) {
            logger.error("error al consultar empleadoUnitedClient.getUnidadesAdministrativas");
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        }
    }

    private List<AraceDTO> getUnidadesAdministrativas(Empleado empleado) throws EmpleadoServiceException {
        try {
            if (!(empleado != null && empleado.getIdAdmGral() != null)) {
                throw new EmpleadoServiceException(MSG_ERROR_UNUDAD_ADMINISTRATIVA_NO_EXISTE);
            }
            return EmpleadoServiceHelper.getLstAracesDtoFromLstUnidadesAdmin(empleadoUnitedClient.getUnidadesAdministrativasPorGeneral(empleado.getIdAdmGral()));
        } catch (Exception ex) {
            logger.error("error al consultar empleadoUnitedClient.getUnidadesAdministrativas");
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        }
    }

    @Override
    public List<AraceDTO> getUnidadesAdministrativasXGeneral(EmpleadoDTO empledo) throws EmpleadoServiceException {
        try {
            return EmpleadoServiceHelper.getLstAracesDtoFromLstUnidadesAdmin(empleadoUnitedClient.getUnidadesAdministrativasPorGeneral(empledo.getIdAdmGral()));
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
            throw new EmpleadoServiceException(MSG_ERROR_UNUDAD_ADMINISTRATIVA_NO_EXISTE, ex);
        }
    }

    @Override
    public Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(EmpleadoDTO empleado) throws EmpleadoServiceException {
        return EmpleadoServiceHelper.getMapAraceDTOFromLstUnidadAdmin(getUnidadesAdministrativas(empleado));
    }

    private Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(Empleado empleado) throws EmpleadoServiceException {
        return EmpleadoServiceHelper.getMapAraceDTOFromLstUnidadAdmin(getUnidadesAdministrativas(empleado));
    }

    @Override
    public Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(List<AraceDTO> lstUnidadAdmin, EmpleadoDTO empleado) throws EmpleadoServiceException {
        return EmpleadoServiceHelper.getMapAraceDTOFromLstUnidadAdmin(((lstUnidadAdmin != null) && (!lstUnidadAdmin.isEmpty()) ? lstUnidadAdmin : getUnidadesAdministrativas(empleado)));
    }

    private Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(List<AraceDTO> lstUnidadAdmin, Empleado empleado) throws EmpleadoServiceException {
        return EmpleadoServiceHelper.getMapAraceDTOFromLstUnidadAdmin(((lstUnidadAdmin != null) && (!lstUnidadAdmin.isEmpty()) ? lstUnidadAdmin : getUnidadesAdministrativas(empleado)));
    }

    @Override
    public List<String> getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum tipoEmpleado,
            BigDecimal idArace,
            ClvSubModulosAgace clvModulo) throws EmpleadoServiceException {
        try {
            List<Empleado> lstEmpleados = empleadoUnitedClient.getEmpleadosXUnidadAdmva(empleadoUnitedClient.getTipoFiscalizacion(),
                    idArace.intValue(),
                    (int) tipoEmpleado.getId(),
                    clvModulo.getIdClvSubModulo());

            List<String> lstCorreos = new ArrayList<String>();
            if (lstEmpleados != null) {
                for (Empleado emp : lstEmpleados) {
                    lstCorreos.add(emp.getCorreo());
                }
            }

            return lstCorreos;

        } catch (EmpleadoServiceClientException ex) {
            throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
        }
    }

    private void completarJefeYSubordinados(EmpleadoDTO empleadoDTO,
            EmpleadoCompleto empleadoCompleto,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) throws EmpleadoServiceException {

        if (empleadoCompleto != null) {
            if (isLstJefesOSubordinadosValida(empleadoCompleto, SUBORDINADOS)) {
                addEmpleadoToLstSubordinadoJefes(SUBORDINADOS, empleadoDTO, empleadoCompleto.getListaSubordinados(), mapTipoEmpleado, mapUnidadesAdministrativas);
            }
            if (isLstJefesOSubordinadosValida(empleadoCompleto, JEFES_INMEDIATOS)) {
                addEmpleadoToLstSubordinadoJefes(JEFES_INMEDIATOS, empleadoDTO, empleadoCompleto.getListaJefesSuperiores(), mapTipoEmpleado, mapUnidadesAdministrativas);
            }
        }
    }

    private boolean isLstJefesOSubordinadosValida(EmpleadoCompleto empleadoCompleto, int tipoLista) {
        List<Empleado> lstSubordinados;
        List<Empleado> lstJefes;

        switch (tipoLista) {
            case SUBORDINADOS:

                if (empleadoCompleto != null && empleadoCompleto.getListaSubordinados() != null) {
                    lstSubordinados = new ArrayList<Empleado>();
                    for (Empleado empleado : empleadoCompleto.getListaSubordinados()) {
                        if (lstSubordinados.isEmpty()) {
                            lstSubordinados.add(empleado);
                        } else if (!lstSubordinados.contains(empleado)) {
                            lstSubordinados.add(empleado);
                        }
                    }
                    empleadoCompleto.setListaSubordinados(lstSubordinados);
                    return true;
                }
                return false;

            case JEFES_INMEDIATOS:
                if (empleadoCompleto != null && empleadoCompleto.getListaJefesSuperiores() != null) {
                    lstJefes = new ArrayList<Empleado>();
                    for (Empleado empleado : empleadoCompleto.getListaJefesSuperiores()) {
                        if (lstJefes.isEmpty()) {
                            lstJefes.add(empleado);
                        } else if (!lstJefes.contains(empleado)) {
                            lstJefes.add(empleado);
                        }
                    }
                    empleadoCompleto.setListaJefesSuperiores(lstJefes);
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private void addEmpleadoToLstSubordinadoJefes(int tipoLista,
            EmpleadoDTO empleadoDTO,
            List<Empleado> lstEmpleados,
            Map<Integer, TipoEmpleadoEnum> mapTipoEmpleado,
            Map<Integer, AraceDTO> mapUnidadesAdministrativas) throws EmpleadoServiceException {

        if (empleadoDTO.getSubordinados() == null) {
            empleadoDTO.setSubordinados(new EnumMap<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>>(TipoEmpleadoEnum.class));
        }
        if (empleadoDTO.getLstJefesInmediatos() == null) {
            empleadoDTO.setLstJefesInmediatos(new EnumMap<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>>(TipoEmpleadoEnum.class));
        }

        for (Empleado emp : lstEmpleados) {
            try {

                for (DetalleEmpleadoDTO detalleEmpleado : empleadoDTO.getDetalleEmpleado()) {
                    EmpleadoDTO empleado = EmpleadoServiceHelper.getEmpleadoDTOfromEmpleadoCompleto(empleadoUnitedClient.getEmpleadoCompleto(empleadoUnitedClient.getTipoFiscalizacion(),
                            null,
                            emp.getNumeroEmpleado()),
                            mapTipoEmpleado,
                            mapUnidadesAdministrativas);

                    switch (tipoLista) {
                        case SUBORDINADOS:
                            EmpleadoServiceHelper.addSubordinadoOJefe(empleadoDTO.getSubordinados(), detalleEmpleado, empleado);
                            break;
                        case JEFES_INMEDIATOS:
                            EmpleadoServiceHelper.addSubordinadoOJefe(empleadoDTO.getLstJefesInmediatos(), detalleEmpleado, empleado);
                            break;
                        default:
                            logger.info("Sin subordinados");
                            break;
                    }
                }
            } catch (EmpleadoServiceClientException ex) {
                logger.error("Error al consultar empleado addEmpleadoDtoToMapTipoEmpleado");
                throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
            } catch (CloneNotSupportedException ex) {
                logger.error("Error al clonar objeto");
                throw new EmpleadoServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
            }
        }
    }

    @Override
    public List<EmpleadoDTO> getLstSubordinadosPorRelacionEmpleado(EmpleadoDTO empleadoDTO,
            TipoEmpleadoEnum tipoEmpleadoPatron,
            TipoEmpleadoEnum tipoEmpleadoSubordinado) throws EmpleadoServiceException {
        if (empleadoDTO != null && empleadoDTO.getSubordinados() != null) {
            Map<TipoEmpleadoEnum, List<EmpleadoDTO>> relacionSubordinados
                    = empleadoDTO.getSubordinados().containsKey(tipoEmpleadoPatron) ? empleadoDTO.getSubordinados().get(tipoEmpleadoPatron) : null;
            if (relacionSubordinados != null) {
                return relacionSubordinados.containsKey(tipoEmpleadoSubordinado) ? relacionSubordinados.get(tipoEmpleadoSubordinado) : null;
            }
            return null;
        }
        return null;
    }

    @Override
    public void completarLstAracesDetalle(EmpleadoDTO empleadoDto, Map<Integer, AraceDTO> mapUnidadesAdmin, List<AraceDTO> lstUnidadAdmin) {
        if (empleadoDto != null && empleadoDto.getDetalleEmpleado() != null && mapUnidadesAdmin != null) {
            for (DetalleEmpleadoDTO detalle : empleadoDto.getDetalleEmpleado()) {
                try {
                    List<AraceDTO> lstCompleta = new ArrayList<AraceDTO>();

                    for (AraceDTO arace : lstUnidadAdmin) {
                        lstCompleta.add(mapUnidadesAdmin.get(arace.getIdArace()));
                    }
                    if (!lstCompleta.isEmpty()) {
                        detalle.setLstAraces(lstCompleta);
                        return;
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
                detalle.setLstAraces(new ArrayList<AraceDTO>());
            }
        }
    }

    @Override
    public void completarAracesDetalle(Map<Integer, AraceDTO> mapUnidadesAdmin, List<AraceDTO> lstUnidadAdmin) {

        try {

            List<AraceDTO> lstNew = new ArrayList<AraceDTO>();
            if (lstUnidadAdmin != null) {

                Iterator<AraceDTO> iter = lstUnidadAdmin.iterator();
                while (iter.hasNext()) {
                    AraceDTO next = iter.next();

                    if (mapUnidadesAdmin != null && !mapUnidadesAdmin.isEmpty()) {
                        if (next.getIdArace() != null && mapUnidadesAdmin.containsKey(next.getIdArace())) {
                            lstNew.add(mapUnidadesAdmin.get(next.getIdArace()));
                        } else {
                            lstNew.add(next);
                        }
                    }

                }

                if (lstNew.size() == lstUnidadAdmin.size()) {
                    Collections.copy(lstUnidadAdmin, lstNew);
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    protected void completarLstDesahogo(EmpleadoDTO empleadoDto, Map<Integer, AraceDTO> mapUnidadesAdmin) {
        if (empleadoDto != null && empleadoDto.getDetalleEmpleado() != null && mapUnidadesAdmin != null) {
            for (DetalleEmpleadoDTO detalle : empleadoDto.getDetalleEmpleado()) {
                List<AraceDTO> lstAraces = unidadDesahogoDao.getLstUnidadesDesahogo(empleadoDto.getIdEmpleado().intValue(), detalle.getTipoEmpleado());
                List<AraceDTO> lstCompleta = new ArrayList<AraceDTO>();
                for (AraceDTO arace : lstAraces) {
                    lstCompleta.add(mapUnidadesAdmin.get(arace.getIdArace()));
                }
                if (!lstCompleta.isEmpty()) {
                    detalle.setLstAraces(lstCompleta);
                    return;
                }
                detalle.setLstAraces(new ArrayList<AraceDTO>());
            }
        }
    }

    @Override
    public boolean validarExistenciaTipoEmpleado(EmpleadoDTO empleado, TipoEmpleadoEnum tipoEmpleado) throws EmpleadoServiceException {
        boolean resultado = false;
        if (empleado != null && empleado.getDetalleEmpleado() != null && !empleado.getDetalleEmpleado().isEmpty()) {
            logger.error("Se valida ROL Empleado tipo esperado : " + tipoEmpleado.getDescripcion());
            DetalleEmpleadoDTO result = null;
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                if (detalle.getTipoEmpleado().equals(tipoEmpleado)) {
                    result = detalle;
                    resultado = true;
                    break;
                }
            }
            if (resultado) {
                empleado.getDetalleEmpleado().clear();
                empleado.getDetalleEmpleado().add(result);
            } else {
                logger.error("El rol del empleado no corresponde al esperado ");
                logger.error("El rol esperado : ", tipoEmpleado.getDescripcion());
            }
        }
        return resultado;
    }

}
