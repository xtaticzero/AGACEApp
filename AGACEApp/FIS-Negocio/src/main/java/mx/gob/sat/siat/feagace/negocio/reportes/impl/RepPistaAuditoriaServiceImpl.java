package mx.gob.sat.siat.feagace.negocio.reportes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.RepPistaAuditoriaExtDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.RepPistaAuditoriaIntDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.reportes.RepPistaAuditoriaService;

@Service("repPistaAuditoriaService")
public class RepPistaAuditoriaServiceImpl extends BaseBusinessServices implements RepPistaAuditoriaService {

    @SuppressWarnings("compatibility:2524469710258145891")
    private static final long serialVersionUID = -6415845816558300464L;

    @Autowired
    private transient RepPistaAuditoriaIntDao repPistaAuditoriaIntDao;
    @Autowired
    private transient RepPistaAuditoriaExtDao repPistaAuditoriaExtDao;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;

    private static final String INICIO_REGISTRO_PROPUESTA = "EP";
    private static final String INICIO_REGISTRO_PROPUESTA2 = "EI";
    private static final String INICIO_REGISTRO_INSUMO = "ES";
    private static final BigDecimal AGENTE_ADUANAL = new BigDecimal(Constantes.ENTERO_CUATRO);
    private static final BigDecimal APO_LEGAL_REP_LEGAL = new BigDecimal(Constantes.ENTERO_TRES);
    private static final BigDecimal REPRESENTANTE_LEGAL = new BigDecimal(Constantes.ENTERO_DOS);
    private static final BigDecimal APODERADO_LEGAL = new BigDecimal(Constantes.ENTERO_UNO);

    public RepPistaAuditoriaServiceImpl() {
        super();
    }

    @Override
    public List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(
            ReportePistaAuditoriaInternaDTO pistaInterDTO) {
        return repPistaAuditoriaIntDao.buscaPistaAuditoriaInterna(pistaInterDTO);
    }

    @Override
    public List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaInterDTO, List<EmpleadoDTO> lstEmpleados) {
        List<ReportePistaAuditoriaInternaDTO> lstPistas;

        BigDecimal numeroEmpleado = BigDecimal.ZERO;

        if (pistaInterDTO.getNomUsuario() != null) {
            numeroEmpleado = buscaNumeroEmpleado(pistaInterDTO.getNomUsuario(), lstEmpleados);
        }
        BigDecimal idRegistro = buscaIdRegistro(pistaInterDTO);
        lstPistas = repPistaAuditoriaIntDao.buscaPistaAuditoriaInterna(pistaInterDTO, numeroEmpleado, idRegistro);
        llenarEmpleadoPista(lstPistas);

        return lstPistas;
    }

    private void llenarEmpleadoPista(List<ReportePistaAuditoriaInternaDTO> lstPistas) {

        Map<BigDecimal, EmpleadoDTO> lstEmpleado = new HashMap<BigDecimal, EmpleadoDTO>();

        if (lstPistas != null && !lstPistas.isEmpty()) {
            for (ReportePistaAuditoriaInternaDTO pista : lstPistas) {
                EmpleadoDTO empleado = null;
                if (!lstEmpleado.containsKey(pista.getIdEmpleado())) {
                    try {
                        if (pista.getIdEmpleado() != null) {
                            empleado = empleadoService.getEmpleadoCompleto(pista.getIdEmpleado().intValue());
                            pista.setNomUsuario(empleado.getNombreCompleto());
                            pista.setRfcUsuario(empleado.getRfc());
                            lstEmpleado.put(empleado.getIdEmpleado(), empleado);
                        } else {
                            pista.setNomUsuario("N/A");
                            pista.setRfcUsuario("N/A");
                        }
                    } catch (EmpleadoServiceException e) {
                        logger.error("Error al obtener datos del servicio de empleado agace", e);
                        pista.setRfcUsuario("");
                        pista.setRfcUsuario("");
                    }
                } else {
                    empleado = lstEmpleado.get(pista.getIdEmpleado());
                    pista.setNomUsuario(empleado.getNombreCompleto());
                    pista.setRfcUsuario(empleado.getRfc());
                }

            }
        }
    }

    private BigDecimal buscaNumeroEmpleado(String nombre, List<EmpleadoDTO> lstEmpleados) {
        BigDecimal numeroEmpleado = BigDecimal.ZERO;

        for (EmpleadoDTO emp : lstEmpleados) {
            if (emp.getNombreCompleto().equals(nombre)) {
                return emp.getIdEmpleado();
            }
        }

        return numeroEmpleado;
    }

    private BigDecimal buscaIdRegistro(ReportePistaAuditoriaInternaDTO pistaInterDTO) {

        BigDecimal id = null;
        AgaceOrden orden;
        List<FecetPropuesta> lstPropuesta;
        FecetInsumo insumo;

        if (pistaInterDTO.getIdRegistro() != null && (pistaInterDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA)
                || pistaInterDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA2))) {
            lstPropuesta = fecetPropuestaDao.findWhereIdRegistroEquals(pistaInterDTO.getIdRegistro());
            id = (lstPropuesta != null && !lstPropuesta.isEmpty() ? lstPropuesta.get(0).getIdPropuesta() : null);
        }

        if (pistaInterDTO.getIdRegistro() != null && pistaInterDTO.getIdRegistro().contains(INICIO_REGISTRO_INSUMO)) {
            insumo = fecetInsumoDao.findByIdRegistro(pistaInterDTO.getIdRegistro());
            id = insumo != null ? insumo.getIdInsumo() : null;
        }

        if (pistaInterDTO.getNumOreden() != null) {
            orden = agaceOrdenDao.findByNumeroOrden(pistaInterDTO.getNumOreden());
            id = orden != null ? orden.getIdOrden() : null;
        }
        return id;
    }

    @Override
    public List<EmpleadoDTO> obtenerTodosEmpleados() {
        
        return new ArrayList<EmpleadoDTO>();
    }

    @Override
    public List<ReportePistaAuditoriaExternaDTO> buscaPistaAuditoriaExterna(ReportePistaAuditoriaExternaDTO pistaExterDTO) {

        List<ReportePistaAuditoriaExternaDTO> lstPistasExternas = repPistaAuditoriaExtDao.buscaPistaAuditoriaExterna(pistaExterDTO, buscaIdRegistroPistaExterna(pistaExterDTO));

        if (pistaExterDTO.getIdRegistro() != null || pistaExterDTO.getNumOreden() != null) {
            filtrarXRfcs(lstPistasExternas);
        } else {
            filtarXRfcAsociado(pistaExterDTO, lstPistasExternas);
        }

        return lstPistasExternas;
    }

    private void filtarXRfcAsociado(ReportePistaAuditoriaExternaDTO pistaExterDTO, List<ReportePistaAuditoriaExternaDTO> lstPistasExternas) {
        List<FecetAsociado> lstOrdenesAsociado = null;

        if (pistaExterDTO.getRfcContribuyente() != null) {
            lstOrdenesAsociado = fecetAsociadoDao.getAsociadosByRfcAndFechaBaja(pistaExterDTO.getRfcContribuyente(), tipoAsociadoXBuscar(pistaExterDTO));
            filtraXContribuyente(lstPistasExternas, lstOrdenesAsociado);
        } else {
            if (pistaExterDTO.getRfcAgentAduanal() != null) {
                lstOrdenesAsociado = fecetAsociadoDao.getAsociadosByRfcAndFechaBaja(pistaExterDTO.getRfcAgentAduanal(), tipoAsociadoXBuscar(pistaExterDTO));
            }

            if (pistaExterDTO.getRfcApodLegal() != null) {
                lstOrdenesAsociado = fecetAsociadoDao.getAsociadosByRfcAndFechaBaja(pistaExterDTO.getRfcApodLegal(), tipoAsociadoXBuscar(pistaExterDTO));
            }

            if (pistaExterDTO.getRfcApodLegalRepLegal() != null) {
                lstOrdenesAsociado = fecetAsociadoDao.getAsociadosByRfcAndFechaBaja(pistaExterDTO.getRfcApodLegalRepLegal(), tipoAsociadoXBuscar(pistaExterDTO));
            }

            if (pistaExterDTO.getRfcRepLegal() != null) {
                lstOrdenesAsociado = fecetAsociadoDao.getAsociadosByRfcAndFechaBaja(pistaExterDTO.getRfcRepLegal(), tipoAsociadoXBuscar(pistaExterDTO));
            }

            filtraXTipoAsociado(lstPistasExternas, lstOrdenesAsociado);
        }

    }

    private void filtraXTipoAsociado(List<ReportePistaAuditoriaExternaDTO> lstPistasExternas, List<FecetAsociado> lstOrdenesAsociado) {

        List<ReportePistaAuditoriaExternaDTO> lstPistasContribuyente = new ArrayList<ReportePistaAuditoriaExternaDTO>();

        for (ReportePistaAuditoriaExternaDTO pista : lstPistasExternas) {
            for (FecetAsociado asociado : lstOrdenesAsociado) {
                List<AgaceOrden> lstOrden = agaceOrdenDao.obtenerOrden(asociado.getIdOrden());
                String numeroOrden = lstOrden != null ? lstOrden.get(0).getNumeroOrden() : "";
                if ((!pista.getIdRegistro().equals(asociado.getIdOrden().toString()))
                        && (!(pista.getIdRegistro().equals(numeroOrden)))) {
                    lstPistasContribuyente.add(pista);
                    break;
                }
            }
        }

        lstPistasExternas.removeAll(lstPistasContribuyente);
    }

    private void filtraXContribuyente(List<ReportePistaAuditoriaExternaDTO> lstPistasExternas, List<FecetAsociado> lstOrdenesAsociado) {
        List<ReportePistaAuditoriaExternaDTO> lstPistasAsociado = new ArrayList<ReportePistaAuditoriaExternaDTO>();

        for (ReportePistaAuditoriaExternaDTO pista : lstPistasExternas) {
            for (FecetAsociado asociado : lstOrdenesAsociado) {
                List<AgaceOrden> lstOrden = agaceOrdenDao.obtenerOrden(asociado.getIdOrden());
                String numeroOrden = lstOrden != null ? lstOrden.get(0).getNumeroOrden() : "";
                if ((pista.getIdRegistro().equals(asociado.getIdOrden().toString()))
                        || ((pista.getIdRegistro().equals(numeroOrden)))) {
                    lstPistasAsociado.add(pista);
                    break;
                }
            }
        }

        lstPistasExternas.removeAll(lstPistasAsociado);
    }

    private String tipoAsociadoXBuscar(ReportePistaAuditoriaExternaDTO pistaExterDTO) {
        StringBuilder tipoAsociado = new StringBuilder();

        if (pistaExterDTO.getRfcAgentAduanal() != null) {
            tipoAsociado.append(AGENTE_ADUANAL.toString());
        }

        if (pistaExterDTO.getRfcRepLegal() != null) {
            tipoAsociado.append(REPRESENTANTE_LEGAL.toString());
        }

        if (pistaExterDTO.getRfcApodLegal() != null) {
            tipoAsociado.append(APODERADO_LEGAL.toString());
        }

        if (pistaExterDTO.getRfcApodLegalRepLegal() != null) {
            tipoAsociado.append(APO_LEGAL_REP_LEGAL.toString());
        }

        if (pistaExterDTO.getRfcContribuyente() != null) {
            tipoAsociado.append(AGENTE_ADUANAL.toString()).append(",");
            tipoAsociado.append(REPRESENTANTE_LEGAL.toString()).append(",");
            tipoAsociado.append(APODERADO_LEGAL.toString()).append(",");
            tipoAsociado.append(APO_LEGAL_REP_LEGAL.toString());
        }

        return tipoAsociado.toString();
    }

    private void filtrarXRfcs(List<ReportePistaAuditoriaExternaDTO> lstPistas) {

        try {
            for (ReportePistaAuditoriaExternaDTO pista : lstPistas) {
                List<FecetAsociado> asociado = fecetAsociadoDao.getAsociadosByIdOrdenAndRfc(pista.getRfcContribuyente(), new BigDecimal(pista.getIdRegistro()));
                if (asociado != null && !asociado.isEmpty()) {
                    //Validar que tipo de asociado creo la pista
                    validarRfcCreacionPista(pista, asociado);
                } else {
                    pista.setRfcAgentAduanal(null);
                    pista.setRfcApodLegal(null);
                    pista.setRfcApodLegalRepLegal(null);
                    pista.setRfcRepLegal(null);
                }
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al filtar las pistas por el rfc que creo la pista : " + e.getMessage());
        }

    }

    private BigDecimal buscaIdRegistroPistaExterna(ReportePistaAuditoriaExternaDTO pistaExternaDTO) {

        BigDecimal id = null;
        AgaceOrden orden;
        List<FecetPropuesta> lstPropuesta;
        FecetInsumo insumo;

        if (pistaExternaDTO.getIdRegistro() != null && (pistaExternaDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA)
                || pistaExternaDTO.getIdRegistro().contains(INICIO_REGISTRO_PROPUESTA2))) {
            lstPropuesta = fecetPropuestaDao.findWhereIdRegistroEquals(pistaExternaDTO.getIdRegistro());
            id = (lstPropuesta != null && !lstPropuesta.isEmpty() ? lstPropuesta.get(0).getIdPropuesta() : null);
        }

        if (pistaExternaDTO.getIdRegistro() != null && pistaExternaDTO.getIdRegistro().contains(INICIO_REGISTRO_INSUMO)) {
            insumo = fecetInsumoDao.findByIdRegistro(pistaExternaDTO.getIdRegistro());
            id = insumo != null ? insumo.getIdInsumo() : null;
        }

        if (pistaExternaDTO.getNumOreden() != null) {
            orden = agaceOrdenDao.findByNumeroOrden(pistaExternaDTO.getNumOreden());
            id = orden != null ? orden.getIdOrden() : null;
        }
        return id;
    }

    private void validarRfcCreacionPista(ReportePistaAuditoriaExternaDTO pista, List<FecetAsociado> asociado) {
        if (asociado.get(0).getIdTipoAsociado().equals(Constantes.ID_AGENTE_ADUANAL)) {
            pista.setRfcAgentAduanal(pista.getRfcContribuyente());
            pista.setRfcContribuyente(null);
        }

        if (asociado.get(0).getIdTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            pista.setRfcApodLegalRepLegal(pista.getRfcContribuyente());
            pista.setRfcContribuyente(null);
        }

        if (asociado.get(0).getIdTipoAsociado().equals(Constantes.ID_REPRESENTANTE_LEGAL)) {
            pista.setRfcRepLegal(pista.getRfcContribuyente());
            pista.setRfcContribuyente(null);
        }

        if (asociado.get(0).getIdTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL)) {
            pista.setRfcApodLegal(pista.getRfcContribuyente());
            pista.setRfcContribuyente(null);
        }
    }

    public void setRepPistaAuditoriaIntDao(RepPistaAuditoriaIntDao repPistaAuditoriaIntDao) {
        this.repPistaAuditoriaIntDao = repPistaAuditoriaIntDao;
    }

    public RepPistaAuditoriaIntDao getRepPistaAuditoriaIntDao() {
        return repPistaAuditoriaIntDao;
    }

    public void setRepPistaAuditoriaExtDao(RepPistaAuditoriaExtDao repPistaAuditoriaExtDao) {
        this.repPistaAuditoriaExtDao = repPistaAuditoriaExtDao;
    }

    public RepPistaAuditoriaExtDao getRepPistaAuditoriaExtDao() {
        return repPistaAuditoriaExtDao;
    }
}
