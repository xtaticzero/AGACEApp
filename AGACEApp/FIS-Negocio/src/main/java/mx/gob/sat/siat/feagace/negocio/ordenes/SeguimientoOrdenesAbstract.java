/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.SeguimientoOrdenesCondicionesHelper;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class SeguimientoOrdenesAbstract extends SeguimientoOrdenesValidacionAbstract {

    private static final long serialVersionUID = -3355109238687671069L;

    private static final String FOLIO = "folio";
    private static final String METODO = "metodo";
    private static final String NUMERO_ORDEN = "numero_orden";
    private static final String AVISO_DE_OFICIO_PENDIENTE_DE_FIRMA = "Aviso de oficio pendiente de firma";
    private static final String AVISO_DE_RESOLUCION_DE_PRORROGA = "Aviso de Resolución de Prórroga";

    private static final String ERROR_NEGOCIO = "Negocio: ";

    public static final int CASE_PRUEBAS_PERICIALES = 1;
    public static final int CASE_SEGUNDO_REQUERIMIENTO = 2;
    public static final int CASE_RESOLUCION_DEFINITIVA = 3;
    public static final int CASE_PRUEBAS_DESAHOGO = 4;
    public static final int CASE_CONCLUSION_REVISION = 5;
    public static final int CASE_SIN_OBSERVACIONES = 6;
    public static final int CASE_CON_OBSERVACIONES = 7;
    public static final int CASE_REQUERIMIENTO_REINCIDENCIA = 8;
    public static final int CASE_COMP_INTERNACIONAL = 9;
    public static final int CASE_COMPULSA_TERCERO_OTRAS_AUTORIDADES = 10;
    public static final int CASE_COMPULSA_OTRAS_AUTORIDADES = 11;
    public static final int CASE_AVISO_CIRCUNSTANCIAL = 12;
    public static final int CASE_MULTA = 13;
    public static final int CASE_AVISO_CONTRIBUYENTE = 14;

    private SeguimientoOrdenesCondicionesHelper segOrdsCondicionesHelp = new SeguimientoOrdenesCondicionesHelper();
    private BigDecimal idOficioPapel;

    @Autowired
    private NotificacionService notificacionService;

    public void insertarOficioAnexosArchivos(FecetOficio oficio, final List<FecetOficioAnexos> listaAnexos) throws NegocioException {
        try {
            oficio.setDocumentoPdf("0");
            oficio.setSuspencionPlazo("0");

            CargaArchivoUtil.guardarArchivoOficio(oficio);

            String nombreArchivo = "", nombre = "", ruta = "";
            if (oficio.getRutaArchivo() != null) {
                nombreArchivo = oficio.getNombreArchivo();
                ruta = oficio.getRutaArchivo();
                oficio.setRutaArchivo(ruta.replace(nombreArchivo, "").concat(nombreArchivo));
            }

            if (oficio.getRutaAcuseNyv() != null) {
                nombre = oficio.getNombreAcuseNyv();
                ruta = oficio.getRutaAcuseNyv();
                oficio.setRutaAcuseNyv(ruta.replace(nombre, "").concat(nombre));
            }

            getFecetOficioDao().insert(oficio);

            for (FecetOficioAnexos anexo : listaAnexos) {
                anexo.setIdOficioAnexos(getFecetOficioAnexosDao().getIdFecetOficioAnexosPathDirectorio());
                anexo.setIdOficio(oficio.getIdOficio());
                anexo.setRutaArchivo(RutaArchivosUtil.armarRutaAnexosOficio(oficio.getRutaArchivo().replace(nombreArchivo, "")));

                CargaArchivoUtil.guardarArchivoOficioAnexo(anexo);

                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }

                getFecetOficioAnexosDao().insert(anexo);
            }
        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

    public void insertaCompulsa(AgaceOrden orden, FecetOficio oficio, FecetContribuyente contribuyente, final ColaboradorVO representanteLegal,
            final Integer tipoCompulsa) throws NegocioException {
        try {
            FecetCompulsas compulsa = new FecetCompulsas();

            compulsa.setFechaCreacion(new Date());
            compulsa.setIdOficio(oficio.getIdOficio());
            compulsa.setIdOrdenAuditada(orden.getIdOrden());
            compulsa.setTipoCompulsa(String.valueOf(tipoCompulsa));
            compulsa.setIdEstatus(Constantes.ESTATUS_COMPULSA_PENDIENTE_FIRMA);

            contribuyente.setIdContribuyente(getFecetContribuyenteDao().insert(contribuyente).getIdContribuyente());

            if (representanteLegal != null && (!validaStringNullVacio(representanteLegal.getRfc()) || !validaStringNullVacio(representanteLegal.getNombre())
                    || !validaStringNullVacio(representanteLegal.getCorreo()))) {
                FecetAsociado repLegal = new FecetAsociado();
                repLegal.setCorreo(representanteLegal.getCorreo());
                repLegal.setEstatus("1");
                repLegal.setFechaUltimaMod(new Date());
                repLegal.setIdTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
                repLegal.setNombre(representanteLegal.getNombre());
                repLegal.setRfc(representanteLegal.getRfc());
                repLegal.setTipoAsociado("0");
                repLegal.setRfcContribuyente(orden.getFecetContribuyente().getRfc());

                if (representanteLegal.isMedioContactoBoolean()) {
                    repLegal.setMedioContacto("1");
                } else {
                    repLegal.setMedioContacto("0");
                }

                repLegal.setIdAsociado(getFecetAsociadoDao().insert(repLegal).getIdAsociado());

                compulsa.setIdAsociado(repLegal.getIdAsociado());
            }

            compulsa.setIdContribuyenteCompulsado(contribuyente.getIdContribuyente());

            getFecetCompulsasDao().insert(compulsa);
        } catch (Exception e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

    public void enviarNotificacionCorreos(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {
            Calendar date = new GregorianCalendar();
            data.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
            data.put(Common.DAY.toString(), "" + date.get(Calendar.DAY_OF_MONTH));
            data.put(Common.MONTH.toString(), NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
            data.put(Common.HOUR.toString(), new SimpleDateFormat("HH:mm").format(date.getTime()));
            notificacionService.enviarNotificacion(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(),e);
        }

    }

    public void insertaFlujoProrrogaOrdenAuditor(FecetFlujoProrrogaOrdenDao fecetFlujoProrrogaOrdenDao, FecetAnexosProrrogaOrdenDao fecetAnexosProrrogaOrdenDao,
            FecetFlujoProrrogaOrden flujoProrroga, final List<FecetAnexosProrrogaOrden> listaAnexos, final String pathAnexos, final String tipoAnexos)
            throws NegocioException {
        try {

            fecetFlujoProrrogaOrdenDao.insert(flujoProrroga);

            for (FecetAnexosProrrogaOrden anexo : listaAnexos) {
                anexo.setIdAnexosProrrogaOrden(fecetAnexosProrrogaOrdenDao.getIdFecetAnexosProrrogaOrdenPathDirectorio());
                anexo.setIdFlujoProrrogaOrden(flujoProrroga.getIdFlujoProrrogaOrden());
                anexo.setRutaArchivo(pathAnexos);
                anexo.setTipoAnexo(tipoAnexos);

                CargaArchivoUtil.guardarArchivoAnexoProrrogaOrden(anexo);
                String nombre = "", ruta = "";
                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }
                fecetAnexosProrrogaOrdenDao.insert(anexo);
            }

            fecetFlujoProrrogaOrdenDao.actualizarFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOrden(flujoProrroga.getIdProrrogaOrden());
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

    public void actuallizarResolucion(FecetProrrogaOrden dto, final List<FecetAnexosProrrogaOrden> listaAnexos, final String pathAnexos) {
        try {
            for (FecetAnexosProrrogaOrden anexo : listaAnexos) {
                anexo.setRutaArchivo(pathAnexos);
                CargaArchivoUtil.guardarArchivoAnexoProrrogaOrden(anexo);
                String nombre = "", ruta = "";
                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }
                dto.setRutaResolucion(ruta.replace(nombre, "").concat(nombre));
                getFecetProrrogaOrdenDao().updateProrrogaResolucion(dto);
            }
            CargaArchivoUtil.guardarArchivo(dto.getArchivoResolucion(), dto.getRutaResolucion(), dto.getNombreResolucion());
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    public void actuallizarResolucionPruebaPericial(FecetPruebasPericiales dto, final List<FecetAnexoPruebasPericiales> listaAnexos, final String pathAnexos) {
        try {
            for (FecetAnexoPruebasPericiales anexo : listaAnexos) {
                anexo.setRutaArchivo(pathAnexos);
                CargaArchivoUtil.guardarArchivoAnexoPruebasPericiales(anexo);
                String nombre = "", ruta = "";
                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }
                dto.setRutaResolucion(ruta.replace(nombre, "").concat(nombre));
                getFecetPruebasPericialesDao().updatePruebasPericialesResolucion(dto);
            }
            CargaArchivoUtil.guardarArchivo(dto.getArchivoResolucion(), dto.getRutaResolucion(), dto.getNombreResolucion());
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    public void actuallizarResolucionOficio(FecetProrrogaOficio dto, final List<FecetAnexosProrrogaOficio> listaAnexos, final String pathAnexos) {
        try {
            for (FecetAnexosProrrogaOficio anexo : listaAnexos) {
                anexo.setRutaArchivo(pathAnexos);
                CargaArchivoUtil.guardarArchivoAnexoProrrogaOficio(anexo);
                String nombre = "", ruta = "";
                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }
                dto.setRutaResolucion(ruta.replace(nombre, "").concat(nombre));
                getFecetProrrogaOficioDao().updateProrrogaResolucion(dto);
            }
            CargaArchivoUtil.guardarArchivo(dto.getArchivoResolucion(), dto.getRutaResolucion(), dto.getNombreResolucion());
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    public SeguimientoOrdenesCondicionesHelper getSegOrdsCondicionesHelp() {
        return segOrdsCondicionesHelp;
    }

    public void setSegOrdsCondicionesHelp(SeguimientoOrdenesCondicionesHelper segOrdsCondicionesHelp) {
        this.segOrdsCondicionesHelp = segOrdsCondicionesHelp;
    }

    public void guardarPruebasPericiales(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaPruebasPericiales(reglasNegocioOrdenesBO.getListaOfPrincipal());
            validaCargaAvisoContribuyente(reglasNegocioOrdenesBO);

            FecetOficio oficioPruebasPer = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioPruebasPer.setIdOficio(idOficioPapel);
            oficioPruebasPer.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPruebasPericiales(reglasNegocioOrdenesBO.getOrden(), oficioPruebasPer));
            oficioPruebasPer.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioPruebasPer.setBlnActivo(true);
            insertarOficioAnexosArchivos(oficioPruebasPer, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioAvisoCont = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioAvisoCont.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioAvisoCont.setIdOficioPrincipal(oficioPruebasPer.getIdOficio());
                oficioAvisoCont.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoAvisoContribuyente(reglasNegocioOrdenesBO.getOrden(), oficioAvisoCont));
                oficioAvisoCont.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioAvisoCont.setBlnActivo(true);
                insertarOficioAnexosArchivos(oficioAvisoCont, reglasNegocioOrdenesBO.getListaAnexosDependiente1());

            }
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioPruebasPer);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarSegundoRequerimiento(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaSegundoRequerimiento(reglasNegocioOrdenesBO.getListaOfPrincipal());
            validaCargaImposicionMulta(reglasNegocioOrdenesBO);
            validaCargaSuspencionPadron(reglasNegocioOrdenesBO);

            FecetOficio oficioSegReq = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioSegReq.setIdOficio(idOficioPapel);
            oficioSegReq.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoSegundoRequerimiento(reglasNegocioOrdenesBO.getOrden(), oficioSegReq));
            oficioSegReq.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioSegReq.setBlnActivo(true);
            insertarOficioAnexosArchivos(oficioSegReq, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioMulta = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioMulta.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioMulta.setIdOficioPrincipal(oficioSegReq.getIdOficio());
                oficioMulta.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoMulta(reglasNegocioOrdenesBO.getOrden(), oficioMulta));
                oficioMulta.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioMulta.setBlnActivo(true);
                insertarOficioAnexosArchivos(oficioMulta, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }

            if (!reglasNegocioOrdenesBO.getListaOfDependiente2().isEmpty()) {
                FecetOficio oficioBajaPad = reglasNegocioOrdenesBO.getListaOfDependiente2().get(0);
                oficioBajaPad.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioBajaPad.setIdOficioPrincipal(oficioSegReq.getIdOficio());
                oficioBajaPad.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoBajaPadron(reglasNegocioOrdenesBO.getOrden(), oficioBajaPad));
                oficioBajaPad.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioBajaPad.setBlnActivo(true);
                insertarOficioAnexosArchivos(oficioBajaPad, reglasNegocioOrdenesBO.getListaAnexosDependiente2());
            }
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioSegReq);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void envioCorreoOficio(AgaceOrden orden, FecetOficio oficio) {
        Map<String, String> datos = new HashMap<String, String>();
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(orden.getIdFirmante().intValue(),
                Constantes.USUARIO_FIRMANTE, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
        datos.put(FOLIO, String.valueOf(oficio.getIdOficio()));
        datos.put(METODO, orden.getFeceaMetodo().getNombre());
        datos.put(NUMERO_ORDEN, orden.getNumeroOrden());
        datos.put("oficio", obtenerDescripcionOficio(oficio.getFecetTipoOficio().getIdTipoOficio()));
        datos.put(Common.SUBJECT.toString(), AVISO_DE_OFICIO_PENDIENTE_DE_FIRMA);
        enviarNotificacionCorreos(datos, ReportType.AVISO_ENVIO_OFICIO_FIRMANTE, destinatarios);
    }

    public void envioCorreoOficioMetodo(AgaceOrden orden, FecetOficio oficio, FecetCambioMetodo cambio) {
        Map<String, String> datos = new HashMap<String, String>();
        Set<String> destinatarios = new TreeSet<String>();
        EmpleadoDTO empleadoAuditor;
        try {
            empleadoAuditor = getEmpleadoCompleto(orden.getIdAuditor().intValue());
            notificacionService.obtenerCorreoEmpleado(orden.getIdFirmante().intValue(),
                    Constantes.USUARIO_FIRMANTE, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            datos.put(FOLIO, String.valueOf(oficio.getIdOficio()));
            datos.put(METODO, orden.getFeceaMetodo().getNombre());
            datos.put(NUMERO_ORDEN, orden.getNumeroOrden());
            datos.put("NOMBRE_AUDITOR", empleadoAuditor.getNombre());
            datos.put("NOMBRE_METODO", cambio.getNombreNuevoMetodo());
            datos.put(Common.SUBJECT.toString(), AVISO_DE_OFICIO_PENDIENTE_DE_FIRMA);
            enviarNotificacionCorreos(datos, ReportType.AVISO_ENVIO_OFICIO_FIRMANTE_METODO, destinatarios);
        } catch (EmpleadoServiceException e) {
            logger.error(ERROR_NEGOCIO.concat(e.getMessage()), e);
        }

    }

    public void envioCorreoOficioCompulsaTerceros(AgaceOrden orden, FecetOficio oficio, FecetContribuyente contribuyente) {
        Map<String, String> datos = new HashMap<String, String>();
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(orden.getIdFirmante().intValue(),
                Constantes.USUARIO_FIRMANTE, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
        datos.put(FOLIO, String.valueOf(oficio.getIdOficio()));
        datos.put(METODO, orden.getFeceaMetodo().getNombre());
        datos.put(NUMERO_ORDEN, orden.getNumeroOrden());
        datos.put("NOMBRE_TERCERO_COMPULSADO", contribuyente.getNombre());
        datos.put(Common.SUBJECT.toString(), AVISO_DE_OFICIO_PENDIENTE_DE_FIRMA);
        enviarNotificacionCorreos(datos, ReportType.AVISO_ENVIO_OFICIO_FIRMANTE_COMPULSA, destinatarios);
    }

    public void envioCorreoProrroga(AgaceOrden orden, BigDecimal idProrroga, String tipo) {
        Map<String, String> datos = new HashMap<String, String>();
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(orden.getIdFirmante().intValue(),
                Constantes.USUARIO_FIRMANTE, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
        datos.put(FOLIO, String.valueOf(idProrroga));
        datos.put(METODO, orden.getFeceaMetodo().getNombre());
        datos.put(NUMERO_ORDEN, orden.getNumeroOrden());
        datos.put("nombre_contribuyente", orden.getFecetContribuyente().getNombre());
        datos.put("APROBADA_RECHAZADA", tipo);
        datos.put(Common.SUBJECT.toString(), AVISO_DE_RESOLUCION_DE_PRORROGA);
        enviarNotificacionCorreos(datos, ReportType.AVISO_ENVIO_PRORROGA_FIRMANTE, destinatarios);
    }

    public void envioCorreoPruebaPericial(AgaceOrden orden, BigDecimal idPruebaPericial, String tipo) {
        Map<String, String> datos = new HashMap<String, String>();
        Set<String> destinatarios = new TreeSet<String>();
        EmpleadoDTO empleado = null;
        try {
            empleado = getEmpleadoCompleto(orden.getIdFirmante().intValue());
            destinatarios.add(empleado.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error(ERROR_NEGOCIO.concat(e.getMessage()), e);
        }
        datos.put(FOLIO, String.valueOf(idPruebaPericial));
        datos.put(METODO, orden.getFeceaMetodo().getNombre());
        datos.put(NUMERO_ORDEN, orden.getNumeroOrden());
        datos.put("APROBADA_RECHAZADA", tipo);
        datos.put(Common.SUBJECT.toString(), AVISO_DE_OFICIO_PENDIENTE_DE_FIRMA);
        enviarNotificacionCorreos(datos, ReportType.AVISO_ENVIO_PRORROGA_FIRMANTE, destinatarios);
    }

    public String obtenerDescripcionOficio(BigDecimal tipoOficio) {
        String descripcionOficio = "";
        for (TiposOficiosOrdenesEnum tipo : TiposOficiosOrdenesEnum.values()) {
            if (tipo.getBigIdTipoOficio().equals(tipoOficio)) {
                descripcionOficio = tipo.getNombre();
                break;
            }
        }
        return descripcionOficio;
    }

    public void guardarResolucionDefinitiva(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaResolucionDefinitiva(reglasNegocioOrdenesBO.getListaOfPrincipal());
            if (reglasNegocioOrdenesBO.getOrden().getIdMetodo().equals(Constantes.ORG)) {
                validaCargaResolucionDefinitivaObservaciones(reglasNegocioOrdenesBO.getOrden().getIdOrden());
            }
            FecetOficio oficioResolDef = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioResolDef.setIdOficio(idOficioPapel);
            oficioResolDef.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoResolucionDefinitiva(reglasNegocioOrdenesBO.getOrden(), oficioResolDef));
            oficioResolDef.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioResolDef.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioResolDef, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioResolDef);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarPruebasDesahogo(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaPruebasDesahogo(reglasNegocioOrdenesBO.getListaOfPrincipal());
            validaCargaAvisoContribuyente(reglasNegocioOrdenesBO);

            FecetOficio oficioPruebasDesahogo = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioPruebasDesahogo.setIdOficio(idOficioPapel);
            oficioPruebasDesahogo.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPruebasDesahogo(reglasNegocioOrdenesBO.getOrden(), oficioPruebasDesahogo));
            oficioPruebasDesahogo.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioPruebasDesahogo.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioPruebasDesahogo, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioAvisoCont = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioAvisoCont.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioAvisoCont.setIdOficioPrincipal(oficioPruebasDesahogo.getIdOficio());
                oficioAvisoCont.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoAvisoContribuyente(reglasNegocioOrdenesBO.getOrden(), oficioAvisoCont));
                oficioAvisoCont.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioAvisoCont.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioAvisoCont, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioPruebasDesahogo);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarConclusionRevision(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaConclusionRevision(reglasNegocioOrdenesBO.getListaOfPrincipal());
            validaCargaCancelacionOrden(reglasNegocioOrdenesBO.getListaOfDependiente3());
            validaCargaImposicionMulta(reglasNegocioOrdenesBO);
            validaCargaSuspencionPadron(reglasNegocioOrdenesBO);

            FecetOficio oficioConclusionRev = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioConclusionRev.setIdOficio(idOficioPapel);
            oficioConclusionRev.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoConclusionRevision(reglasNegocioOrdenesBO.getOrden(), oficioConclusionRev));
            oficioConclusionRev.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioConclusionRev.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioConclusionRev, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioMulta = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioMulta.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioMulta.setIdOficioPrincipal(oficioConclusionRev.getIdOficio());
                oficioMulta.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoMulta(reglasNegocioOrdenesBO.getOrden(), oficioMulta));
                oficioMulta.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioMulta.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioMulta, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }

            if (!reglasNegocioOrdenesBO.getListaOfDependiente2().isEmpty()) {
                FecetOficio oficioBajaPad = reglasNegocioOrdenesBO.getListaOfDependiente2().get(0);
                oficioBajaPad.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioBajaPad.setIdOficioPrincipal(oficioConclusionRev.getIdOficio());
                oficioBajaPad.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoBajaPadron(reglasNegocioOrdenesBO.getOrden(), oficioBajaPad));
                oficioBajaPad.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioBajaPad.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioBajaPad, reglasNegocioOrdenesBO.getListaAnexosDependiente2());
            }

            if (!reglasNegocioOrdenesBO.getListaOfDependiente3().isEmpty()) {
                FecetOficio oficioCancelOrden = reglasNegocioOrdenesBO.getListaOfDependiente3().get(0);
                oficioCancelOrden.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioCancelOrden.setIdOficioPrincipal(oficioConclusionRev.getIdOficio());
                oficioCancelOrden.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoCancelacionOrden(reglasNegocioOrdenesBO.getOrden(), oficioCancelOrden));
                oficioCancelOrden.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioCancelOrden.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioCancelOrden, reglasNegocioOrdenesBO.getListaAnexosDependiente3());
            }
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioConclusionRev);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarSinObservaciones(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaSinObservaciones(reglasNegocioOrdenesBO.getListaOfPrincipal());

            FecetOficio oficioSinObserv = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioSinObserv.setIdOficio(idOficioPapel);
            oficioSinObserv.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoSinObservaciones(reglasNegocioOrdenesBO.getOrden(), oficioSinObserv));
            oficioSinObserv.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioSinObserv.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioSinObserv, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioSinObserv);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarConObservaciones(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaConObservaciones(reglasNegocioOrdenesBO.getListaOfPrincipal());

            FecetOficio oficioConObserv = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioConObserv.setIdOficio(idOficioPapel);
            oficioConObserv.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoConObservaciones(reglasNegocioOrdenesBO.getOrden(), oficioConObserv));
            oficioConObserv.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioConObserv.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioConObserv, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioConObserv);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarRequerimientoReincidencia(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaRequerimientoReincidencia(reglasNegocioOrdenesBO.getListaOfPrincipal());
            validaCargaImposicionMulta(reglasNegocioOrdenesBO);
            validaCargaSuspencionPadron(reglasNegocioOrdenesBO);

            FecetOficio oficioReqReincidencia = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioReqReincidencia.setIdOficio(idOficioPapel);
            oficioReqReincidencia
                    .setRutaArchivo(RutaArchivosUtil.armarRutaDestinoRequerimientoReincidencia(reglasNegocioOrdenesBO.getOrden(), oficioReqReincidencia));
            oficioReqReincidencia.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioReqReincidencia.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioReqReincidencia, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioMulta = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioMulta.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioMulta.setIdOficioPrincipal(oficioReqReincidencia.getIdOficio());
                oficioMulta.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoMulta(reglasNegocioOrdenesBO.getOrden(), oficioMulta));
                oficioMulta.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioMulta.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioMulta, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }

            if (!reglasNegocioOrdenesBO.getListaOfDependiente2().isEmpty()) {
                FecetOficio oficioBajaPad = reglasNegocioOrdenesBO.getListaOfDependiente2().get(0);
                oficioBajaPad.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioBajaPad.setIdOficioPrincipal(oficioReqReincidencia.getIdOficio());
                oficioBajaPad.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoBajaPadron(reglasNegocioOrdenesBO.getOrden(), oficioBajaPad));
                oficioBajaPad.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioBajaPad.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioBajaPad, reglasNegocioOrdenesBO.getListaAnexosDependiente2());
            }
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioReqReincidencia);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarCompInternacional(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaAutoridadCompulsada(reglasNegocioOrdenesBO.getAutoridadCompulsada());

            validaCargaCompulsaInternacional(reglasNegocioOrdenesBO.getListaOfPrincipal());
            validaCargaAvisoContribuyente(reglasNegocioOrdenesBO);

            FecetOficio oficioCompInternacional = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioCompInternacional.setIdOficio(idOficioPapel);
            oficioCompInternacional
                    .setRutaArchivo(RutaArchivosUtil.armarRutaDestinoCompulsaInternacional(reglasNegocioOrdenesBO.getOrden(), oficioCompInternacional));
            oficioCompInternacional.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioCompInternacional.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            oficioCompInternacional.setNombreCompulsado(reglasNegocioOrdenesBO.getAutoridadCompulsada());
            insertarOficioAnexosArchivos(oficioCompInternacional, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioAvisoContribuyente = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioAvisoContribuyente.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioAvisoContribuyente.setIdOficioPrincipal(oficioCompInternacional.getIdOficio());
                oficioAvisoContribuyente.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoMulta(reglasNegocioOrdenesBO.getOrden(), oficioAvisoContribuyente));
                oficioAvisoContribuyente.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioAvisoContribuyente.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));

                insertarOficioAnexosArchivos(oficioAvisoContribuyente, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioCompInternacional);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarCompulsaTerceroOtrasAutoridades(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {
            validaContribuyenteCompulsado(reglasNegocioOrdenesBO.getContribuyente());

            validaCargaCompulsaTerceroOtrasAutoridades(reglasNegocioOrdenesBO.getListaOfPrincipal(), reglasNegocioOrdenesBO.getTipoCompulsa());

            validaCargaAvisoContribuyente(reglasNegocioOrdenesBO);

            FecetOficio oficioCompulsaTercero = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioCompulsaTercero.setIdOficio(idOficioPapel);

            if (reglasNegocioOrdenesBO.getTipoCompulsa() == 1) {
                oficioCompulsaTercero
                        .setRutaArchivo(RutaArchivosUtil.armarRutaDestinoOtrasAutoridades(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero));
            } else {
                oficioCompulsaTercero
                        .setRutaArchivo(RutaArchivosUtil.armarRutaDestinoCompulsaTercero(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero));
            }
            oficioCompulsaTercero.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioCompulsaTercero.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));

            insertarOficioAnexosArchivos(oficioCompulsaTercero, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioAvisoCont = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioAvisoCont.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioAvisoCont.setIdOficioPrincipal(oficioCompulsaTercero.getIdOficio());
                oficioAvisoCont.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoAvisoContribuyente(reglasNegocioOrdenesBO.getOrden(), oficioAvisoCont));
                oficioAvisoCont.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioAvisoCont.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
                insertarOficioAnexosArchivos(oficioAvisoCont, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }

            insertaCompulsa(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero, reglasNegocioOrdenesBO.getContribuyente(),
                    reglasNegocioOrdenesBO.getRepresentanteLegal(), reglasNegocioOrdenesBO.getTipoCompulsa());
            envioCorreoOficioCompulsaTerceros(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero, reglasNegocioOrdenesBO.getContribuyente());

        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarCompulsaOtrasAutoridades(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaAutoridadCompulsada(reglasNegocioOrdenesBO.getAutoridadCompulsada());

            validaCargaCompulsaTerceroOtrasAutoridades(reglasNegocioOrdenesBO.getListaOfPrincipal(), reglasNegocioOrdenesBO.getTipoCompulsa());

            validaCargaAvisoContribuyente(reglasNegocioOrdenesBO);

            FecetOficio oficioCompulsaTercero = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioCompulsaTercero.setIdOficio(idOficioPapel);

            if (reglasNegocioOrdenesBO.getTipoCompulsa() == 1) {
                oficioCompulsaTercero
                        .setRutaArchivo(RutaArchivosUtil.armarRutaDestinoOtrasAutoridades(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero));
            } else {
                oficioCompulsaTercero
                        .setRutaArchivo(RutaArchivosUtil.armarRutaDestinoCompulsaTercero(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero));
            }
            oficioCompulsaTercero.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioCompulsaTercero.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            oficioCompulsaTercero.setNombreCompulsado(reglasNegocioOrdenesBO.getAutoridadCompulsada());
            insertarOficioAnexosArchivos(oficioCompulsaTercero, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            if (!reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
                FecetOficio oficioAvisoCont = reglasNegocioOrdenesBO.getListaOfDependiente1().get(0);
                oficioAvisoCont.setIdOficio(this.getFecetOficioDao().getIdOficio());
                oficioAvisoCont.setIdOficioPrincipal(oficioCompulsaTercero.getIdOficio());
                oficioAvisoCont.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoAvisoContribuyente(reglasNegocioOrdenesBO.getOrden(), oficioAvisoCont));
                oficioAvisoCont.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
                oficioAvisoCont.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));

                insertarOficioAnexosArchivos(oficioAvisoCont, reglasNegocioOrdenesBO.getListaAnexosDependiente1());
            }
            // envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioCompulsaTercero)
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarAvisoCircunstancial(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaSinObservaciones(reglasNegocioOrdenesBO.getListaOfPrincipal());

            FecetOficio oficioAvsio = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioAvsio.setIdOficio(idOficioPapel);
            oficioAvsio.setRutaArchivo(RutaArchivosUtilOrdenes.armarRutaDestinoAvisoCircunstancial(reglasNegocioOrdenesBO.getOrden(), oficioAvsio));
            oficioAvsio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioAvsio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioAvsio, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioAvsio);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarMulta(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaMulta(reglasNegocioOrdenesBO.getListaOfPrincipal());
            FecetOficio oficioAvsio = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = this.getFecetOficioDao().getIdOficio();
            oficioAvsio.setIdOficio(idOficioPapel);
            oficioAvsio.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoMulta(reglasNegocioOrdenesBO.getOrden(), oficioAvsio));
            oficioAvsio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioAvsio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioAvsio, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            // envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioAvsio)
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public void guardarAvisoContribuyenteOrden(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {
            validaCargaAvisoContribuyenteOrden(reglasNegocioOrdenesBO.getListaOfPrincipal());
            FecetOficio oficioAvsio = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            idOficioPapel = getFecetOficioDao().getIdOficio();
            oficioAvsio.setIdOficio(idOficioPapel);
            oficioAvsio.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoAvisoContribuyente(reglasNegocioOrdenesBO.getOrden(), oficioAvsio));
            oficioAvsio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficioAvsio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficioAvsio, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            // envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioAvsio)
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public BigDecimal getIdOficioPapel() {
        return idOficioPapel;
    }

    public void setIdOficioPapel(BigDecimal idOficioPapel) {
        this.idOficioPapel = idOficioPapel;
    }

    public void insertaFlujoPruebaPericialAuditor(FecetFlujoPruebasPericialesDao fecetFlujoPruebasPericialesDao,
            FecetAnexoPruebasPericialesDao fecetAnexoPruebasPericialesDao, FecetFlujoPruebasPericiales flujoPruebasPericiales,
            final List<FecetAnexoPruebasPericiales> listaAnexos, final String pathAnexos, final String tipoAnexos) throws NegocioException {
        try {

            fecetFlujoPruebasPericialesDao.insert(flujoPruebasPericiales);

            for (FecetAnexoPruebasPericiales anexo : listaAnexos) {
                anexo.setIdAnexoPruebasPericiales(fecetAnexoPruebasPericialesDao.getIdFecetAnexoPruebasPericialesPathDirectorio());
                anexo.setIdFlujoPruebasPericiales(flujoPruebasPericiales.getIdFlujoPruebasPericiales());
                anexo.setRutaArchivo(pathAnexos);
                anexo.setTipoAnexo(tipoAnexos);

                CargaArchivoUtil.guardarArchivoAnexoPruebasPericiales(anexo);
                String nombre = "", ruta = "";
                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }
                fecetAnexoPruebasPericialesDao.insert(anexo);
            }

            fecetFlujoPruebasPericialesDao.actualizarFlujoConcluidoRechazadoPorFirmanteByIdPruebasPericiales(flujoPruebasPericiales.getIdPruebasPericiales());
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

}
