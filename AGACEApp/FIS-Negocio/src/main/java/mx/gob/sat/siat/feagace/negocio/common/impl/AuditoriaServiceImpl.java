package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;

@Component("auditoriaContribuyente")
public class AuditoriaServiceImpl extends BaseBusinessServices implements AuditoriaService {
    
    private static final String MSG_LOG = "aprobarProrrogaOrden";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PistaAuditoria
    public String accesarPerfilContribuyente(PerfilContribuyenteVO perfil) {
        logger.debug("accesarPerfilContribuyente");
        return perfil.getRfc();
    }

    @PistaAuditoria
    public BigDecimal accesarPerfilAsociado(PerfilContribuyenteVO perfil) {
        logger.debug("accesarPerfilAsociado");
        return perfil.getIdAsociado();
    }

    @PistaAuditoria
    public BigDecimal detalleOrden(AgaceOrden orden) {
        logger.debug("detalleOrden");
        return orden.getId();
    }

    @PistaAuditoria
    public BigDecimal detalleCompulsa(FecetCompulsas compulsa) {
        logger.debug("detalleCompulsa");
        return compulsa.getIdOrdenCompulsa();
    }

    @PistaAuditoria
    public String dasociarApoderadoLegal(ColaboradorVO colaborador) {
        logger.debug("dasociarApoderadoLegal");
        return colaborador.getRfc();
    }

    @PistaAuditoria
    public BigDecimal asociarRepresentanteLegal(AgaceOrden orden) {
        logger.debug("asociarRepresentanteLegal");
        return orden.getIdOrden();
    }

    @PistaAuditoria
    public BigDecimal asociarAgenteAduanal(AgaceOrden orden) {
        logger.debug("asociarAgenteAduanal");
        return orden.getIdOrden();
    }

    @PistaAuditoria
    public BigDecimal asociarApoderadoRepresentante(AgaceOrden orden) {
        logger.debug("asociarApoderadoRepresentante");
        return orden.getIdOrden();
    }

    @PistaAuditoria
    public BigDecimal cargarPromocionOrden(AgaceOrden orden) {
        logger.debug("cargarPromocionOrden");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = orden.getId();
        } catch (Exception e) {
            logger.error("cargarPromocionOrden " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal cargarProrrogaOrden(AgaceOrden orden) {
        logger.debug("cargarProrrogaOrden");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = orden.getId();
        } catch (Exception e) {
            logger.error("cargarProrrogaOrden " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal cargarPromocionOficio(FecetOficio oficio) {
        logger.debug("cargarPromocionOficio");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("cargarPromocionOficio " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal cargarProrrogaOficio(FecetOficio oficio) {
        logger.debug("cargarProrrogaOficio");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("cargarProrrogaOficio " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal cargarPruebasPericialesOficio(AgaceOrden oficio) {
        logger.debug("cargarPruebasPericialesOficio");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("cargarPruebasPericialesOficio " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal aprobarProrrogaOrdenAuditor(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = orden.getId();
        } catch (Exception e) {
            logger.error(MSG_LOG + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal noAprobarProrrogaOrdenAuditor(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = orden.getId();
        } catch (Exception e) {
            logger.error(MSG_LOG + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal aprobarProrrogaOficioAuditor(FecetOficio oficio) {
        logger.debug(MSG_LOG);
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("aprobarProrrogaOrden " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    @Override
    public BigDecimal noAprobarProrrogaOficioAuditor(FecetOficio oficio) {
        logger.debug("noAprobarProrrogaOrdenAuditor");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("noAprobarProrrogaOrdenAuditor " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    @Override
    public BigDecimal asignarConsulta(AgaceOrden orden) {
        logger.debug("asignarConsulta");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = orden.getIdOrden();
        } catch (Exception e) {
            logger.error("asignarConsulta " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal crearPistaAuditoriaOficio(FecetOficio oficio) {
        logger.debug("firmarOficio");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("firmarOficio " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal aprobarProrrogaOrdenFirmante(AgaceOrden orden) {
        logger.debug("firmarProrrogaOrdenFirmante");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = orden.getId();
        } catch (Exception e) {
            logger.error("firmarProrrogaOrdenFirmante " + e.getMessage());
        }
        return id;
    }

    @PistaAuditoria
    public BigDecimal aprobarProrrogaOficioFirmante(FecetOficio oficio) {
        logger.debug("firmarProrrogaOficioFirmante");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = oficio.getIdOrden();
        } catch (Exception e) {
            logger.error("firmarProrrogaOficioFirmante " + e.getMessage());
        }
        return id;
    }

    @Override
    @PistaAuditoria
    public BigDecimal firmarPropuesta(FecetPropuesta propuesta) {
        logger.debug("firmarOrden");
        BigDecimal id = BigDecimal.ZERO;
        try {
            id = propuesta.getIdPropuesta();
        } catch (Exception e) {
            logger.error("firmarOrden " + e.getMessage());
        }
        return id;
    }

    @Override
    @PistaAuditoria
    public String generarReporteGerencialInsumos(String rfcEmpleado) {
        logger.debug("generarReporteGerencialInsumos");
        return rfcEmpleado;
    }

    @Override
    @PistaAuditoria
    public String generarReporteGerencialPropuestas(String rfcEmpleado) {
        logger.debug("generarReporteGerencialPropuestas");
        return rfcEmpleado;
    }

    @Override
    @PistaAuditoria
    public String generarReporteGerencialOrdenes(String rfcEmpleado) {
        logger.debug("generarReporteGerencialOrdenes");
        return rfcEmpleado;
    }

    @Override
    @PistaAuditoria
    public String generarReporteEjecutivoInsumos(String rfcEmpleado) {
        logger.debug("generarReporteEjecutivoInsumos");
        return rfcEmpleado;
    }

    @Override
    @PistaAuditoria
    public String generarReporteEjecutivoPropuestas(String rfcEmpleado) {
        logger.debug("generarReporteEjecutivoPropuestas");
        return rfcEmpleado;
    }

    @Override
    @PistaAuditoria
    public String generarReporteEjecutivoOrdenes(String rfcEmpleado) {
        logger.debug("generarReporteEjecutivoOrdenes");
        return rfcEmpleado;
    }
}
