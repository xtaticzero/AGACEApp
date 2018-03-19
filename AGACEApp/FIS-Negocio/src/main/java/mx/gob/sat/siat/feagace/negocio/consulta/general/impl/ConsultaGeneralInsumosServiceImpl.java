package mx.gob.sat.siat.feagace.negocio.consulta.general.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocretroinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.consultageneral.ConsultaGeneralInsumosDAO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RetroalimentacionInsumoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralInsumosService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

@Component("consultaGeneralInsumosService")
public class ConsultaGeneralInsumosServiceImpl extends ConsultaGeneralService implements ConsultaGeneralInsumosService {

    private static final long serialVersionUID = 1L;

    public static final int REGISTRADO = 1;
    public static final int NOAPROBADA = 11;
    public static final int RETROALIMENTADA = 12;
    public static final int ACEPTADO = 14;

    @Autowired
    private transient ConsultaGeneralInsumosDAO consultaDAO;

    @Autowired
    private PlazosServiceInsumos plazos;

    @Autowired
    @Qualifier("fecetInsumoDao")
    private transient FecetInsumoDao insumoDao;

    @Autowired
    @Qualifier("fecetDocretroinsumoDao")
    private transient FecetDocretroinsumoDao docRetroDao;

    @Override
    public List<FecetInsumo> obtenerDetalleInsumos(BigDecimal idUnidadAdmonRegistro, BigDecimal idUnidadAdmonRecepcion, int tipoConsulta) {
        List<FecetInsumo> insumos = null;
        List<EmpleadoDTO> empleados = null;
        try {
            if (idUnidadAdmonRegistro != null
                    && !idUnidadAdmonRegistro.equals(new BigDecimal(-1))) {
                empleados = obtenerEmpleadosUnidad(idUnidadAdmonRegistro,
                        (int) TipoEmpleadoEnum.USUARIO_INSUMOS.getId());
            }
            insumos = consultaDAO.consultarInsumosEstatusInicial(empleados, idUnidadAdmonRecepcion);
            if (insumos == null) {
                insumos = new ArrayList<FecetInsumo>();
            }

            if (tipoConsulta == Constantes.ENTERO_UNO) {
                insumos = filtraEstatusValidos(insumos);
            }

            obtenerUnidadRegistro(insumos);
            fillUnidadAdministrativa(insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return insumos;
    }

    private boolean isValidList(List<?> lista) {
        return (lista != null);
    }

    @Override
    public void consultaHistoricoInsumos(FecetInsumo insumoAConsultar) {
        boolean flgIsValid = insumoAConsultar != null && insumoAConsultar.getIdInsumo() != null;
        if (flgIsValid) {
            insumoDao.getRetroalimemtacionesDeInsumo(insumoAConsultar);
            if (insumoAConsultar != null && isValidList(insumoAConsultar.getLstRetroalimentacion())) {
                List<FecetDocretroinsumo> lstDoctos;
                for (RetroalimentacionInsumoDTO retro : insumoAConsultar.getLstRetroalimentacion()) {
                    lstDoctos = docRetroDao.obtenerRetroalimentacionByTipoEmpleado(retro.getIdRetroalimentacionInsumo(),
                            BigDecimal.valueOf(TipoEmpleadoEnum.USUARIO_INSUMOS.getId()));
                    retro.setLstDocsRetroalimentacion(lstDoctos != null ? lstDoctos : new ArrayList<FecetDocretroinsumo>());
                    lstDoctos = docRetroDao.obtenerRetroalimentacionByTipoEmpleado(retro.getIdRetroalimentacionInsumo(),
                            BigDecimal.valueOf(TipoEmpleadoEnum.VALIDADOR_INSUMOS.getId()));
                    retro.setLstDocsSolicitudRetroalimentacion(lstDoctos != null ? lstDoctos : new ArrayList<FecetDocretroinsumo>());
                    retro.setNombreUnidadAdministrativa(insumoAConsultar.getFececUnidadAdministrativa().getNombre());
                }
            }
            insumoDao.getRechazoDeInsumo(insumoAConsultar);
        }
    }

    @Override
    public List<FecetInsumo> obtenerDetalleInsumosAdmon(List<EmpleadoDTO> empleados, TipoEstatusEnum estatus, String condicion, int tipoConsulta) {
        List<FecetInsumo> insumos = null;
        try {
            insumos = consultaDAO.consultarInsumosEstatusAdmon(empleados, obtenerEstatus(estatus, tipoConsulta), condicion);
            obtenerUnidadRegistro(insumos);
            fillUnidadAdministrativa(insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return insumos;
    }

    @Override
    public List<FecetInsumo> obtenerDetalleInsumosSubAdmon(EmpleadoDTO admon, List<EmpleadoDTO> empleados, TipoEstatusEnum estatus, String condicion, int tipoConsulta) {
        List<FecetInsumo> insumos = null;
        try {
            insumos = consultaDAO.consultarInsumosEstatusSubAdmon(admon, empleados, obtenerEstatus(estatus, tipoConsulta), condicion);
            obtenerUnidadRegistro(insumos);
            fillUnidadAdministrativa(insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return insumos;
    }

    private String obtenerEstatus(TipoEstatusEnum estatus, int tipoConsulta) {
        StringBuilder sb = new StringBuilder();
        int x = (int) estatus.getId();

        if (tipoConsulta != Constantes.ENTERO_UNO) {
            return sb.append(obtenerEstatus2(estatus)).toString();
        }

        switch (x) {
            case REGISTRADO:
                sb.append(String.valueOf(TipoEstatusEnum.ACIACE_INSUMO_RETROALIMENTADO.getId())).append(",");
                sb.append(String.valueOf(TipoEstatusEnum.ADMINISTRADOR_INSUMO_ASIGNADOS_SUBADMINISTRADOR.getId())).append(",");
                sb.append(String.valueOf(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADO_ADMINISTRADOR.getId())).append(",");
                sb.append(String.valueOf(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_ACEPTADOS.getId())).append(",");
                sb.append(String.valueOf(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_NO_APROBADOS.getId()));
                break;
            case NOAPROBADA:
                sb.append(String.valueOf(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO.getId()));
                break;
            case RETROALIMENTADA:
                sb.append(String.valueOf(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION.getId()));
                break;

            case ACEPTADO:
                sb.append(String.valueOf(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO.getId()));
                break;
            default:
                sb.append(obtenerEstatus2(estatus));
                break;
        }
        return sb.toString();
    }

    private String obtenerEstatus2(TipoEstatusEnum estatus) {
        return String.valueOf(estatus.getId());
    }

    @Override
    public Map<TipoEstatusEnum, Integer> obtenerResumenInsumos(List<FecetInsumo> insumos) {
        Map<TipoEstatusEnum, Integer> resultados = new HashMap<TipoEstatusEnum, Integer>();
        try {
            resultados = obtenerAgrupacionEstatus(insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }

        return resultados;
    }

    @Override
    public Map<TipoEstatusEnum, Integer> obtenerResumenInsumosAsignados(List<FecetInsumo> insumos) {

        Map<TipoEstatusEnum, Integer> resultados = new HashMap<TipoEstatusEnum, Integer>();
        try {
            resultados = obtenerAgrupacionEstatus2(insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }

        return resultados;
    }

    @Override
    public Map<EmpleadoDTO, Integer> obtenerResumenInsumosAdmon(List<EmpleadoDTO> empleados, List<FecetInsumo> insumos) {
        Map<EmpleadoDTO, Integer> resultados = new HashMap<EmpleadoDTO, Integer>();
        try {
            if (insumos != null && !insumos.isEmpty()) {
                plazos.inicializarInsumoConPlazos(insumos);
                resultados = obtenerAgrupacionEmpleadoAdmon(empleados, insumos);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return resultados;
    }

    @Override
    public Map<EmpleadoDTO, Integer> obtenerResumenInsumosSubAdmon(List<EmpleadoDTO> empleados, List<FecetInsumo> insumos) {
        Map<EmpleadoDTO, Integer> resultados = new HashMap<EmpleadoDTO, Integer>();
        try {
            plazos.inicializarInsumoConPlazos(insumos);
            resultados = obtenerAgrupacionEmpleadoSubAdmon(empleados, insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return resultados;
    }

    @Override
    public Map<SemaforoEnum, Integer> obtenerResumenInsumosSemaforos(List<FecetInsumo> insumos) {
        Map<SemaforoEnum, Integer> resultados = new HashMap<SemaforoEnum, Integer>();
        try {
            plazos.inicializarInsumoConPlazos(insumos);
            resultados = obtenerAgrupacionSemaforo(insumos);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return resultados;
    }

    @Override
    public List<FecetInsumo> filtrarXEstatus(List<FecetInsumo> insumos, TipoEstatusEnum estatus) {
        List<FecetInsumo> lstFiltradaInsumos = new ArrayList<FecetInsumo>();
        if (insumos != null && !insumos.isEmpty() && estatus != null) {
            if (estatus != TipoEstatusEnum.INSUMO_REGISTRADO) {
                for (FecetInsumo insumo : insumos) {
                    if (insumo.getIdEstatus().intValue() == estatus.getId()) {
                        lstFiltradaInsumos.add(insumo);
                    }
                }
            } else {
                for (FecetInsumo insumo : insumos) {
                    if (insumo.getIdEstatus().intValue() != TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO.getId()
                            && insumo.getIdEstatus().intValue() != TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO.getId()
                            && insumo.getIdEstatus().intValue() != TipoEstatusEnum.ACIACE_INSUMO_RETROALIMENTADO.getId()) {
                        lstFiltradaInsumos.add(insumo);
                    }
                }
            }
            return lstFiltradaInsumos;
        }
        return lstFiltradaInsumos;
    }

    @Override
    public List<FecetInsumo> filtrarXSemaforo(List<FecetInsumo> insumos, SemaforoEnum semaforo) {
        List<FecetInsumo> lstFiltradaInsumos = new ArrayList<FecetInsumo>();
        if (insumos != null && !insumos.isEmpty() && semaforo != null) {
            for (FecetInsumo insumo : insumos) {
                if (insumo.getSemaforo() == semaforo.getValor()) {
                    lstFiltradaInsumos.add(insumo);
                }
            }
            return lstFiltradaInsumos;
        }
        return lstFiltradaInsumos;
    }

    @Override
    public List<FecetInsumo> filtroPlazoParaConcluir(List<FecetInsumo> insumos, Integer plazoXConcluir) {
        List<FecetInsumo> lstFiltradaInsumos = new ArrayList<FecetInsumo>();
        if (insumos != null && !insumos.isEmpty() && plazoXConcluir != null) {
            for (FecetInsumo insumo : insumos) {
                if (insumo.getPlazoRestante() == plazoXConcluir) {
                    lstFiltradaInsumos.add(insumo);
                }
            }
            return lstFiltradaInsumos;
        }
        return lstFiltradaInsumos;
    }

    @Override
    public List<EmpleadoDTO> obtenerAdmonXUnidadAdmon(BigDecimal idUnidadAmon) {
        List<EmpleadoDTO> empleados = null;
        try {
            empleados = obtenerEmpleadosUnidad(idUnidadAmon, (int) TipoEmpleadoEnum.ASIGNADOR_INSUMOS.getId());
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return empleados;
    }

    @Override
    public List<EmpleadoDTO> obtenerSubAdmonXUnidadAdmon(BigDecimal idUnidadAmon) {
        List<EmpleadoDTO> empleados = null;
        try {
            empleados = obtenerEmpleadosUnidad(idUnidadAmon, (int) TipoEmpleadoEnum.VALIDADOR_INSUMOS.getId());
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return empleados;
    }

    private List<EmpleadoDTO> obtenerEmpleadosUnidad(BigDecimal idUnidadAdmonRegistro, Integer tipoEmpleado) {
        List<EmpleadoDTO> empleados = null;
        try {
            empleados = getEmpleadosXUnidadAdmva(
                    idUnidadAdmonRegistro.intValue(), tipoEmpleado,
                    ClvSubModulosAgace.INSUMOS);
        } catch (EmpleadoServiceException e) {
            logger.debug(e.getMessage(), e);
        }
        return empleados;
    }

    private Map<TipoEstatusEnum, Integer> obtenerAgrupacionEstatus(List<FecetInsumo> insumos) {
        Map<TipoEstatusEnum, Integer> resultados = new HashMap<TipoEstatusEnum, Integer>();
        int registrados = 0;
        int noAprobados = 0;
        int aceptado = 0;
        int retroalimentacion = 0;
        for (FecetInsumo insumo : insumos) {
            switch (insumo.getIdEstatus().intValue()) {
                case Constantes.ENTERO_ONCE:
                    noAprobados++;
                    break;
                case Constantes.ENTERO_DOCE:
                    retroalimentacion++;
                    break;
                case Constantes.ENTERO_CATORCE:
                    aceptado++;
                    break;
                default:
                    registrados++;
                    break;
            }
        }
        resultados.put(TipoEstatusEnum.INSUMO_REGISTRADO, registrados);
        resultados.put(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO, noAprobados);
        resultados.put(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO, aceptado);
        resultados.put(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION, retroalimentacion);
        return resultados;
    }

    private Map<TipoEstatusEnum, Integer> obtenerAgrupacionEstatus2(List<FecetInsumo> insumos) {
        Map<TipoEstatusEnum, Integer> resultados = new HashMap<TipoEstatusEnum, Integer>();
        int registrados = 0;
        int noAprobados = 0;
        int aceptado = 0;
        int retroalimentacion = 0;
        int retroAtendida = 0;
        int asignadoSubadmin = 0;
        int pendienteAprobacion = 0;
        int asignadoAdministrador = 0;
        int enviados = 0;

        for (FecetInsumo insumo : insumos) {
            switch (insumo.getIdEstatus().intValue()) {
                case Constantes.ENTERO_ONCE:
                    noAprobados++;
                    break;
                case Constantes.ENTERO_DOCE:
                    retroalimentacion++;
                    break;
                case Constantes.ENTERO_CATORCE:
                    aceptado++;
                    break;
                case Constantes.ENTERO_TRES:
                    retroAtendida++;
                    break;
                case Constantes.ENTERO_CUATRO:
                    asignadoSubadmin++;
                    break;
                case Constantes.ENTERO_CINCO:
                    pendienteAprobacion++;
                    break;
                case Constantes.ENTERO_SEIS:
                    asignadoAdministrador++;
                    break;
                case Constantes.ENTERO_SIETE:
                    enviados++;
                    break;
                case Constantes.ENTERO_UNO:
                    registrados++;
                    break;
                default:
                    break;
            }
        }
        resultados.put(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO, noAprobados);
        resultados.put(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_ACEPTADO, aceptado);
        resultados.put(TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION, retroalimentacion);
        resultados.put(TipoEstatusEnum.ACIACE_INSUMO_RETROALIMENTADO, retroAtendida);
        resultados.put(TipoEstatusEnum.ADMINISTRADOR_INSUMO_ASIGNADOS_SUBADMINISTRADOR, asignadoSubadmin);
        resultados.put(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADO_ADMINISTRADOR, pendienteAprobacion);
        resultados.put(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_ACEPTADOS, asignadoAdministrador);
        resultados.put(TipoEstatusEnum.ADMINISTRADOR_INSUMO_REASIGNADOS_NO_APROBADOS, enviados);
        resultados.put(TipoEstatusEnum.INSUMO_REGISTRADO, registrados);

        return resultados;
    }

    private Map<EmpleadoDTO, Integer> obtenerAgrupacionEmpleadoAdmon(List<EmpleadoDTO> empleados, List<FecetInsumo> insumos) {
        Map<EmpleadoDTO, Integer> resultados = new HashMap<EmpleadoDTO, Integer>();
        for (EmpleadoDTO empleado : empleados) {
            int insumosXEmpleado = 0;
            for (FecetInsumo insumo : insumos) {
                if (insumo.getRfcAdministrador().equals(empleado.getRfc())) {
                    insumosXEmpleado++;
                }
            }
            resultados.put(empleado, insumosXEmpleado);
        }
        return resultados;
    }

    private Map<EmpleadoDTO, Integer> obtenerAgrupacionEmpleadoSubAdmon(List<EmpleadoDTO> empleados, List<FecetInsumo> insumos) {
        Map<EmpleadoDTO, Integer> resultados = new HashMap<EmpleadoDTO, Integer>();
        for (EmpleadoDTO empleado : empleados) {
            int insumosXEmpleado = 0;
            for (FecetInsumo insumo : insumos) {
                if (insumo.getRfcSubadministrador() != null && insumo.getRfcSubadministrador().equals(empleado.getRfc())) {
                    insumosXEmpleado++;
                }
            }
            resultados.put(empleado, insumosXEmpleado);
        }
        return resultados;
    }

    private Map<SemaforoEnum, Integer> obtenerAgrupacionSemaforo(List<FecetInsumo> insumos) {
        Map<SemaforoEnum, Integer> resultados = new HashMap<SemaforoEnum, Integer>();
        int verde = 0;
        int amarillo = 0;
        int naranja = 0;
        int rojo = 0;
        int cafe = 0;
        int azul = 0;
        int gris = 0;
        int negro = 0;
        int beige = 0;
        int blanco = 0;
        int morado = 0;
        for (FecetInsumo insumo : insumos) {
            switch (insumo.getSemaforo()) {
                case 1:
                    verde++;
                    break;
                case 2:
                    amarillo++;
                    break;
                case Constantes.ENTERO_TRES:
                    naranja++;
                    break;
                case Constantes.ENTERO_CUATRO:
                    rojo++;
                    break;
                case Constantes.ENTERO_CINCO:
                    cafe++;
                    break;
                case Constantes.ENTERO_SEIS:
                    azul++;
                    break;
                case Constantes.ENTERO_SIETE:
                    gris++;
                    break;
                case Constantes.ENTERO_OCHO:
                    negro++;
                    break;
                case Constantes.ENTERO_NUEVE:
                    beige++;
                    break;
                case Constantes.ENTERO_DIEZ:
                    blanco++;
                    break;
                case Constantes.ENTERO_ONCE:
                    morado++;
                    break;
                default:
                    break;
            }
        }
        resultados.put(SemaforoEnum.SEMAFORO_VERDE, verde);
        resultados.put(SemaforoEnum.SEMAFORO_AMARILLO, amarillo);
        resultados.put(SemaforoEnum.SEMAFORO_NARANJA, naranja);
        resultados.put(SemaforoEnum.SEMAFORO_ROJO, rojo);
        resultados.put(SemaforoEnum.SEMAFORO_CAFE, cafe);
        resultados.put(SemaforoEnum.SEMAFORO_AZUL, azul);
        resultados.put(SemaforoEnum.SEMAFORO_GRIS, gris);
        resultados.put(SemaforoEnum.SEMAFORO_NEGRO, negro);
        resultados.put(SemaforoEnum.SEMAFORO_BEIGE, beige);
        resultados.put(SemaforoEnum.SEMAFORO_BLANCO, blanco);
        resultados.put(SemaforoEnum.SEMAFORO_MORADO, morado);
        return resultados;
    }

    private void obtenerUnidadRegistro(List<FecetInsumo> insumos) {
        List<EmpleadoDTO> empleadosACIACE = obtenerEmpleadosUnidad(new BigDecimal(TipoAraceEnum.ACIACE.getId()),
                (int) TipoEmpleadoEnum.USUARIO_INSUMOS.getId());
        List<EmpleadoDTO> empleadosACPPCE = obtenerEmpleadosUnidad(new BigDecimal(TipoAraceEnum.ACPPCE.getId()),
                (int) TipoEmpleadoEnum.USUARIO_INSUMOS.getId());
        for (FecetInsumo insumo : insumos) {
            for (EmpleadoDTO empleado : empleadosACIACE) {
                if (insumo.getRfcCreacion().equals(empleado.getRfc())) {
                    insumo.setRegistradoPor("ACIACE");
                    break;
                }
            }
            for (EmpleadoDTO empleado : empleadosACPPCE) {
                if (insumo.getRfcCreacion().equals(empleado.getRfc())) {
                    insumo.setRegistradoPor("ACPPCE");
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void fillUnidadAdministrativa(List<?> lstDtoAgace) {
        if (lstDtoAgace != null) {
            for (Object dtoAgace : lstDtoAgace) {
                if (dtoAgace instanceof FecetInsumo) {
                    List<FecetInsumo> listaRegistros = (List<FecetInsumo>) lstDtoAgace;
                    for (FecetInsumo fecetInsumo : listaRegistros) {
                        fecetInsumo.setFececUnidadAdministrativa(fillUnidadAdministrativa(fecetInsumo.getIdUnidadAdministrativa().intValue()));
                    }
                    break;
                } else if (dtoAgace instanceof FecetPropuesta) {
                    List<FecetPropuesta> lstPropuestas = (List<FecetPropuesta>) lstDtoAgace;
                    for (FecetPropuesta propuesta : lstPropuestas) {
                        AraceDTO unidadAdministrativa = MAP_UNIDAD_ADMINISTRATIVA.get(propuesta.getIdArace().intValue());
                        if (unidadAdministrativa != null) {
                            propuesta.setFececArace(getAraceFromAraceDTO(unidadAdministrativa));
                        }
                    }
                    break;
                }
                break;
            }
        }
    }

    public List<FecetInsumo> filtraEstatusValidos(List<FecetInsumo> insumos) {
        List<FecetInsumo> insumosEstatusValidos = new ArrayList<FecetInsumo>();
        for (FecetInsumo insumo : insumos) {
            switch (insumo.getIdEstatus().intValue()) {
                case Constantes.ENTERO_ONCE:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_DOCE:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_CATORCE:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_TRES:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_CUATRO:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_CINCO:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_SEIS:
                    insumosEstatusValidos.add(insumo);
                    break;
                case Constantes.ENTERO_SIETE:
                    insumosEstatusValidos.add(insumo);
                    break;
                default:
                    break;
            }
        }
        return insumosEstatusValidos;
    }

    @Override
    public List<EmpleadoDTO> obtenerSubAdmin(EmpleadoDTO empleado) {

        List<EmpleadoDTO> lstSubadmin = new ArrayList<EmpleadoDTO>();

        try {
            EmpleadoDTO empleadoCompleto = getEmpleadoCompleto(empleado.getRfc());
            Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinadosAdmin = empleadoCompleto.getSubordinados();
            Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordinadosSubAdmin = subordinadosAdmin.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
            lstSubadmin = subordinadosSubAdmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS);

            if (lstSubadmin == null || lstSubadmin.isEmpty()) {
                for (DetalleEmpleadoDTO detalleEmpleado : empleado.getDetalleEmpleado()) {
                    if (detalleEmpleado.getTipoEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) {
                        lstSubadmin = new ArrayList<EmpleadoDTO>();
                        lstSubadmin.add(empleado);
                        break;
                    }
                }
            }
        } catch (EmpleadoServiceException e) {
            logger.error("ERROR: " + e.getMessage());
        }

        return lstSubadmin;
    }
}
