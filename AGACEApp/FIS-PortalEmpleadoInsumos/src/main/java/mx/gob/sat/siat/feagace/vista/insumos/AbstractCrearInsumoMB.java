/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FoliosProcesadosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaPrioridadService;
import mx.gob.sat.siat.feagace.negocio.insumos.CrearInsumoService;
import mx.gob.sat.siat.feagace.negocio.insumos.SeguimientoInsumosService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.AsignacionInsumoCentral;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.AvisoContribuyentePPEEAmparado;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.model.insumos.CrearInsumoDTO;

/**
 * @author sergio.vaca
 *
 */
public class AbstractCrearInsumoMB extends AbstractManagedBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final int SIZE_BUFFER = 1024;

    @ManagedProperty(value = "#{crearInsumoService}")
    private transient CrearInsumoService crearInsumoService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{consultaPrioridadService}")
    private transient ConsultaPrioridadService consultaPrioridadService;

    @ManagedProperty(value = "#{seguimientoInsumosService}")
    private transient SeguimientoInsumosService seguimientoInsumosService;

    private CrearInsumoDTO crearInsumoDTO;

    protected byte[] lecturaArchivo(InputStream archivoEntrada) {
        final ByteArrayOutputStream resultado = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[SIZE_BUFFER];
        try {
            while ((nRead = archivoEntrada.read(data, 0, data.length)) != -1) {
                resultado.write(data, 0, nRead);
            }
            resultado.flush();
        } catch (IOException e) {
            logger.error("Error al realizar la lectura de archivo para descarga", e);
        }
        return resultado.toByteArray();
    }

    protected ValidaMediosContactoBO checarMediosDeContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);
        validaMediosContactoBO = getConsultaMediosContactoService()
                .validaMediosContacto(validaMediosContactoBO);

        return validaMediosContactoBO;
    }

    protected void enviarCorreoPPEEAmparado(final FecetContribuyente contribuyente,
            ValidaMediosContactoBO validaMediosContactoBO) {

        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_AMPARADO);

        data.put(AvisoContribuyentePPEEAmparado.RFC.toString(), contribuyente.getRfc());
        data.put(AvisoContribuyentePPEEAmparado.NOMBRE.toString(), contribuyente.getNombre());
        data.put(AvisoContribuyentePPEEAmparado.AMPARADO.toString(), validaEstatusContribuyente(validaMediosContactoBO));

        Set<String> destinatarios = new TreeSet<String>();
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACPPCE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        try {
            getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISO_CONTRIBUYENTE_PPEE_AMPARADO, destinatarios);
        } catch (BusinessException ex) {
            logger.error("Error al enviar el correo [{}]", ex.getCause());
        }
    }

    protected void enviarCorreoCreacionInsumoCentral(final RegistroInsumosDto registroInsumosDto) {
        if (registroInsumosDto.getInsumosRegistrados() != null && !registroInsumosDto.getInsumosRegistrados().isEmpty()) {
            Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_ASIGNADO_CENTRAL);
            Set<String> destinatariosOriginal = new TreeSet<String>();
            Set<String> destinatariosAdminCentral = new TreeSet<String>();

            getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatariosOriginal, ClvSubModulosAgace.INSUMOS);
            StringBuilder table = new StringBuilder();
            Map<BigDecimal, FoliosProcesadosDto> folios = registroInsumosDto.getFolios();
            for (Entry<BigDecimal, FoliosProcesadosDto> folioLlave : folios.entrySet()) {
                destinatariosAdminCentral.clear();
                table.setLength(0);
                getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, folioLlave.getKey(), destinatariosAdminCentral, ClvSubModulosAgace.INSUMOS);
                table.append(Constantes.TABLA_SUPERIOR);
                for (String folio : folioLlave.getValue().getFolios()) {
                    table.append(Constantes.TABLA_UNO);
                    table.append(folio);
                    table.append(Constantes.TABLA_DOS);
                }
                table.append(Constantes.TABLA_INFERIOR);
                data.put(AsignacionInsumoCentral.ID_REGISTRO.toString(), table.toString());
                destinatariosAdminCentral.addAll(destinatariosOriginal);
                try {
                    getNotificacionService().enviarNotificacionGenerico(data, ReportType.INSUMO_TABLA_RESUMEN, destinatariosAdminCentral);
                } catch (BusinessException ex) {
                    logger.error("Error al enviar el correo [{}]", ex.getCause());
                }
            }
        }
    }

    /**
     * @param registroInsumosDto
     */
    protected void enviarCorreoCreacionInsumoAdministrador(final RegistroInsumosDto registroInsumosDto) {
        if (registroInsumosDto.getInsumosRegistrados() != null && !registroInsumosDto.getInsumosRegistrados().isEmpty()) {
            Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_ASIGNADO_ADMIN);
            Set<String> destinatarios = new TreeSet<String>();
            Map<BigDecimal, FoliosProcesadosDto> folios = registroInsumosDto.getFolios();
            String rfcs[];
            StringBuilder table = new StringBuilder();
            for (Entry<BigDecimal, FoliosProcesadosDto> folioLlave : folios.entrySet()) {
                for (Entry<String, List<String>> entry : folioLlave.getValue().getFoliosAdministrador().entrySet()) {
                    destinatarios.clear();
                    rfcs = entry.getKey().split("-");
                    getNotificacionService().obtenerCorreoEmpleado(rfcs[0], Constantes.USUARIO_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
                    getNotificacionService().obtenerCorreoEmpleado(rfcs[1], Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
                    getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
                    getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
                    table.setLength(0);
                    table.append(Constantes.TABLA_SUPERIOR);
                    for (String folio : entry.getValue()) {
                        table.append(Constantes.TABLA_UNO);
                        table.append(folio);
                        table.append(Constantes.TABLA_DOS);
                    }
                    table.append(Constantes.TABLA_INFERIOR);
                    data.put(AsignacionInsumoCentral.ID_REGISTRO.toString(), table.toString());
                    try {
                        getNotificacionService().enviarNotificacionGenerico(data, ReportType.INSUMO_TABLA_RESUMEN, destinatarios);
                    } catch (BusinessException ex) {
                        logger.error("Error al enviar el correo [{}]", ex.getCause());
                    }
                }
            }
        }
    }

    private String validaEstatusContribuyente(final ValidaMediosContactoBO validaMediosContactoBO) {
        StringBuilder estatus = new StringBuilder("");
        if (validaMediosContactoBO.isPPEE()) {
            estatus.append("PPEE");
            if (validaMediosContactoBO.isAmparado()) {
                estatus.append(" y Amparado");
            }
        } else {
            if (validaMediosContactoBO.isAmparado()) {
                estatus.append("Amparado");
            }
        }
        return estatus.toString();
    }

    public final CrearInsumoService getCrearInsumoService() {
        return crearInsumoService;
    }

    public final void setCrearInsumoService(CrearInsumoService crearInsumoService) {
        this.crearInsumoService = crearInsumoService;
    }

    public final ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public final void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public final ConsultaPrioridadService getConsultaPrioridadService() {
        return consultaPrioridadService;
    }

    public final void setConsultaPrioridadService(ConsultaPrioridadService consultaPrioridadService) {
        this.consultaPrioridadService = consultaPrioridadService;
    }

    public final SeguimientoInsumosService getSeguimientoInsumosService() {
        return seguimientoInsumosService;
    }

    public final void setSeguimientoInsumosService(SeguimientoInsumosService seguimientoInsumosService) {
        this.seguimientoInsumosService = seguimientoInsumosService;
    }

    public final CrearInsumoDTO getCrearInsumoDTO() {
        return crearInsumoDTO;
    }

    public final void setCrearInsumoDTO(CrearInsumoDTO crearInsumoDTO) {
        this.crearInsumoDTO = crearInsumoDTO;
    }
}
