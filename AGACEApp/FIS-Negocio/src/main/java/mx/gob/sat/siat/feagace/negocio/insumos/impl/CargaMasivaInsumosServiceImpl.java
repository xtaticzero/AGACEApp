/**
 *
 */
package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.UtilidadesDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FoliosProcesadosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ReporteIncorrectoDto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesInsumoService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.ServiceCatGrupoDeUnidadAdmin;
import mx.gob.sat.siat.feagace.negocio.common.reportes.JaspertReportsService;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.JaspertReportsServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.helper.CargaMasivaInsumosHelper;
import mx.gob.sat.siat.feagace.negocio.insumos.CargaMasivaInsumosService;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaPrioridadService;
import mx.gob.sat.siat.feagace.negocio.insumos.CrearInsumoService;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.AvisoContribuyentePPEEAmparado;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author sergio.vaca
 *
 */
@Service("cargaMasivaInsumosService")
public class CargaMasivaInsumosServiceImpl extends ServicioInsumosAbstract implements CargaMasivaInsumosService {

    private static final Logger LOGGER = Logger.getLogger(CargaMasivaInsumosServiceImpl.class);

    private static final long serialVersionUID = 1L;

    private static final int RFC_CONTIBUYENTE = 0;
    private static final int UNIDAD_ADMINISTRATIVA = 1;
    private static final int SUBPROGRAMA = 2;
    private static final int TIPO_INSUMO = 3;
    private static final int SECTOR = 4;
    private static final int PRIORIDAD = 5;
    private static final int FECHA_INICIO_PERIODO = 6;
    private static final int FECHA_FIN_PERIODO = 7;
    private static final int DOCUMENTOS = 8;
    private static final String STRCEROSIZQ = "%03d";
    private static final String STRCEROSIZQ_5 = "%05d";
    private static final String LABEL_REGISTROS_CONSULTA_ANTECEDENTES = "Se encontraron coincidencias del RFC y per\u00edodo en los sistemas: %s.";
    private static final String LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES = "No se pudo validar la informaci\u00f3n en los sistemas %s.";
    private static final String LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES = "Se encontraron coincidencias con la Propuesta registrada %s.";
    private static final String LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES = "Se encontraron coincidencias del RFC y periodo propuesto en un insumo registrado por otra Autoridad.";

    @Autowired
    private transient ContribuyenteService contribuyenteService;

    @Autowired
    private transient ConsultaMediosContactoService consultaMediosContactoService;

    @Autowired
    private transient CargaMasivaInsumosHelper cargaMasivaInsumosHelper;

    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    private transient CrearInsumoService crearInsumoService;

    @Autowired
    private transient ConsultaPrioridadService consultaPrioridadService;

    @Autowired
    private transient ConsultaAntecedentesInsumoService consultaAntecedentesInsumoService;

    @Autowired
    private transient UtilidadesDao utilidadesDao;

    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;

    @Autowired
    @Qualifier("jaspertReportsService")
    private JaspertReportsService jaspertReportsService;

    @Autowired
    @Qualifier("serviceCatGrupoDeUnidadAdmin")
    private ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin;

    @Override
    public String getConsecutivoArchivo() {
        Formatter fmt = new Formatter();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("CDM");
            sb.append(new SimpleDateFormat("MMddyyyy").format(new Date()));
            sb.append(fmt.format(STRCEROSIZQ, this.utilidadesDao.getConsecutivoDoctoCargaMasivaInsumos().longValue()).toString());
        } finally {
            fmt.close();
        }
        return sb.toString();
    }

    @Override
    @PistaAuditoria
    public String cargaDocumento(String destino, InputStream is, String nombreArchivo) throws NegocioException {
        File fileToDirectory = new File(destino);
        boolean creado = fileToDirectory.mkdirs();
        OutputStream out = null;
        try {
            if (!fileToDirectory.exists() || !creado) {
                creado = fileToDirectory.mkdirs();
            }
            out = new FileOutputStream(new File(destino + nombreArchivo));
            if (is != null && !creado) {
                byte bytes[] = new byte[Constantes.BYTE];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                is.close();
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_ARCHIVO.concat(String.valueOf(e.getCause())), e);
        } catch (IOException ioe) {
            throw new NegocioException(ConstantesError.ERROR_ESCRIBIR_ARCHIVO.concat(String.valueOf(ioe.getCause())), ioe);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOGGER.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error(ConstantesError.ERROR_CERRAR_INPUTSTREAM, e);
            }
        }
        String ruta = destino.substring(0, destino.length() - 1);
        return ruta.substring(ruta.lastIndexOf('/') + 1);
    }

    @Override
    public void validarRegistros(UploadedFile archivoCarga, List<FecetInsumo> listRegistrosCorrectos,
            List<FecetInsumo> listRegistrosIncorrectos, Map<Integer, List<ReporteIncorrectoDto>> mapReporte, EmpleadoDTO empleadoDTO, String rutaDocumetos) throws NegocioException {

        HSSFWorkbook workbook = validarCargaInicial(archivoCarga, empleadoDTO);
        int index = -1;
        Iterator<Row> rows = workbook.getSheetAt(0).rowIterator();
        FecetInsumo insumo;
        ReporteIncorrectoDto registro;
        List<FececUnidadAdministrativa> listaUnidadAdministrativa = new ArrayList<FececUnidadAdministrativa>();
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
                    listaUnidadAdministrativa.add(fececUnidadAdministrativa);
                }
            }
        } catch (CatalogosServiceException ex) {
            logger.error("No se puede cargar unidades administrativas", ex);
        }

        List<FececSubprograma> subprogramas = crearInsumoService.getCatalogoSubprograma(new BigDecimal(empleadoDTO.getIdAdmGral()));
        List<FececSector> sectores = crearInsumoService.getCatalogoSector(new BigDecimal(empleadoDTO.getIdAdmGral()));
        List<FececPrioridad> prioridades = consultaPrioridadService.findActivos(new BigDecimal(empleadoDTO.getIdAdmGral()));
        List<FececTipoInsumo> tipoInsumos = crearInsumoService.getCatalogoTipoInsumo(new BigDecimal(empleadoDTO.getIdAdmGral()));
        int numeroRegistro = 0;
        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();
            if (++index == 0) {
                continue;
            }

            if (isRowBlank(row)) {
                continue;
            }
            Map<Integer, Object> mapParametrosValidacion = new HashMap<Integer, Object>();
            insumo = new FecetInsumo();
            registro = new ReporteIncorrectoDto();
            registro.setError("");
            boolean flgRowCompleto = false;
            int col = RFC_CONTIBUYENTE;
            int numRegCorrectos = RFC_CONTIBUYENTE;

            int numeroError = 0;
            while (!flgRowCompleto) {
                insumo.setRow(row.getRowNum());
                mapParametrosValidacion.put(Constantes.ENTERO_CERO, row);
                mapParametrosValidacion.put(Constantes.ENTERO_UNO, col);
                mapParametrosValidacion.put(Constantes.ENTERO_DOS, insumo);
                mapParametrosValidacion.put(Constantes.ENTERO_TRES, rutaDocumetos);
                mapParametrosValidacion.put(Constantes.ENTERO_CUATRO, registro);

                if (!validaColumnasInsumos(mapParametrosValidacion, listaUnidadAdministrativa, subprogramas, sectores, prioridades, tipoInsumos)) {
                    ++numeroError;
                    registro.setRegistroIncorrecto(true);
                    String mensajeError = registro.getError();
                    mensajeError = mensajeError + numeroError + ".- " + insumo.getDescripcionRechazo() + "\n";
                    registro.setError(mensajeError);
                    listRegistrosIncorrectos.add(insumo);
                    col++;
                    insumo = new FecetInsumo();
                    insumo.setRow(row.getRowNum());
                } else if (numRegCorrectos < DOCUMENTOS) {
                    col++;
                    numRegCorrectos++;
                    logger.info("Seguir validando insumo");
                } else if (cargaMasivaInsumosHelper.existeInsumoFormato(insumo, listRegistrosCorrectos)) {
                    ++numeroError;
                    registro.setRegistroIncorrecto(true);
                    String mensajeError = registro.getError();
                    mensajeError = mensajeError + numeroError + ".- " + insumo.getDescripcionRechazo() + "\n";
                    registro.setError(mensajeError);
                    listRegistrosIncorrectos.add(insumo);
                    col++;
                    insumo = new FecetInsumo();
                    insumo.setRow(row.getRowNum());
                } else {
                    if (numRegCorrectos == DOCUMENTOS) {
                        insumo.setFechaCreacion(new Date());
                        insumo.setRfcCreacion(empleadoDTO.getRfc());
                        insumo.setNumeroRegistro(++numeroRegistro);
                        validaAntecedentes(insumo);
                        listRegistrosCorrectos.add(insumo);
                    }
                    break;
                }
                flgRowCompleto = (col > DOCUMENTOS);
            }
            if (registro.isRegistroIncorrecto()) {
                mapReporte.get(Constantes.ENTERO_DOS).add(registro);
            } else {
                mapReporte.get(Constantes.ENTERO_UNO).add(cargaMasivaInsumosHelper.llenarRegistroReporte(insumo));
            }
        }
    }

    @Override
    public RegistroInsumosDto insertarRegistrosMasivos(List<FecetInsumo> insumos) {
        RegistroInsumosDto resultado = new RegistroInsumosDto();
        resultado.setInsumosNoRegistrados(new ArrayList<FecetInsumo>());
        resultado.setInsumosRegistrados(new ArrayList<FecetInsumo>());
        resultado.setFolios(new HashMap<BigDecimal, FoliosProcesadosDto>());
        for (FecetInsumo insumo : insumos) {
            crearInsumoService.insertarRegistrosMasivos(insumo, resultado);
        }
        return resultado;
    }

    @Override
    @PistaAuditoria
    public String insertaResumen(ResumenCargaMasivaDTO resumen) {
        Formatter fmt = new Formatter();
        StringBuilder identificador = new StringBuilder();
        try {
            identificador.append("INS");
            identificador.append(fmt.format(STRCEROSIZQ_5, this.utilidadesDao.getConsecutivoCargaMasivaInsumos().longValue()).toString());
            identificador.append("-");
            identificador.append(new SimpleDateFormat("yy").format(new Date()));
            fecetInsumoDao.insertaResumenMasiva(resumen, identificador.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            fmt.close();
        }
        return identificador.toString();
    }

    @Override
    public byte[] generarReporte(String rfcSession, Date fechaActual, String folioCarga, List<FecetInsumo> registros)
            throws NoSeGeneroReporteException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("folioCreacion", folioCarga);
        parametros.put("rfcCreacion", rfcSession);
        parametros.put("fechaCarga", fechaActual);
        parametros.put("totalRegistros", "" + registros.size());
        List<Map<String, Object>> elementos = new ArrayList<Map<String, Object>>();
        Map<String, Object> elemento;
        int index = 0;
        for (FecetInsumo insumo : registros) {
            elemento = new HashMap<String, Object>();
            elemento.put("contador", "" + (++index));
            elemento.put("idRegistro", insumo.getIdRegistro());
            elemento.put("rfc", insumo.getFecetContribuyente().getRfc());
            elemento.put("unidadAdmin", insumo.getFececUnidadAdministrativa().getNombre());
            elemento.put("subPrograma", insumo.getFececSubprograma().getDescripcion());
            elemento.put("tipoInsumo", insumo.getFececTipoInsumo().getIdTipoInsumo().toString());
            elemento.put("sector", insumo.getFececSector().getDescripcion());
            elemento.put("prioridad", insumo.getIdPrioridad().toString());
            elemento.put("fechaInicio", insumo.getFechaInicioPeriodo());
            elemento.put("fechaFin", insumo.getFechaFinPeriodo());
            elemento.put("observaciones", insumo.getDescripcionInfo());
            elementos.add(elemento);
        }
        byte[] reporte;
        try {
            String rutaAbsoluta = ConstantesReportes.PATH_REPORTE.concat(ConstantesReportes.REPORTE_INSUMOS).concat(ConstantesReportes.REPORTE_CARGA_MASIVA_EXCEL);
            reporte = jaspertReportsService.makeReport(rutaAbsoluta, "ConsultaEstatus.xlsx", parametros, elementos);
        } catch (Exception e) {
            throw new NoSeGeneroReporteException("Error al generar el reporte", e);
        }
        return reporte;
    }

    private void validaAntecedentes(FecetInsumo insumo) {
        List<String> listaAntecedentes = new ArrayList<String>();
        List<FecetPropuesta> resultadoSistema;
        String rfc;
        if (insumo.getFecetContribuyente() != null) {
            rfc = insumo.getFecetContribuyente().getRfc();
        } else {
            return;
        }
        Date periodoInicial = insumo.getFechaInicioPeriodo();
        Date periodoFinal = insumo.getFechaFinPeriodo();
        try {
            resultadoSistema = consultaAntecedentesInsumoService.consultaSICSEP(rfc, periodoInicial, periodoFinal);
            if (resultadoSistema != null && !resultadoSistema.isEmpty()) {
                listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_ANTECEDENTES, ConstantesPropuestas.SICSEP));
            }
        } catch (NegocioException e) {
            listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES, ConstantesPropuestas.SICSEP));
        }
        try {
            resultadoSistema = consultaAntecedentesInsumoService.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
            if (resultadoSistema != null && !resultadoSistema.isEmpty()) {
                listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_ANTECEDENTES, ConstantesPropuestas.SIUIEFI));
            }
        } catch (NegocioException e) {
            listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES, ConstantesPropuestas.SIUIEFI));
        }
        try {
            resultadoSistema = consultaAntecedentesInsumoService.consultaAGAFF(rfc, periodoInicial, periodoFinal);
            if (resultadoSistema != null && !resultadoSistema.isEmpty()) {
                listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_ANTECEDENTES, ConstantesPropuestas.AGAFF_DESCRIPCION));
            }
        } catch (Exception e) {
            listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES, ConstantesPropuestas.AGAFF_DESCRIPCION));
        }
        try {
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setFechaInicioPeriodo(periodoInicial);
            propuesta.setFechaFinPeriodo(periodoFinal);
            propuesta.setIdSubprograma(insumo.getIdSubprograma());
            propuesta.setIdArace(insumo.getIdUnidadAdministrativa());
            resultadoSistema = consultaAntecedentesInsumoService.consultaAGACEPropuestasPeriodoExacto(rfc, propuesta);
            if (resultadoSistema != null && !resultadoSistema.isEmpty()) {
                FecetPropuesta propuestaBD = resultadoSistema.get(0);
                listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES, propuestaBD.getIdRegistro()));
            }

            List<FecetInsumo> insumos = consultaAntecedentesInsumoService.consultaAGACEInsumosPeriodoExacto(rfc, propuesta);
            if (insumos != null && !insumos.isEmpty()) {
                listaAntecedentes.add(LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES);
            }

        } catch (NegocioException e) {
            listaAntecedentes.add(String.format(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES, ConstantesPropuestas.AGACE_DESCRIPCION));
        }
        insumo.setDescripcionInfo(cargaMasivaInsumosHelper.obtenerMensajeAntecedente(listaAntecedentes));
    }

    private HSSFWorkbook validarCargaInicial(UploadedFile archivoCarga, EmpleadoDTO empleadoDTO)
            throws NegocioException {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(archivoCarga.getInputstream());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new NegocioException("Archivo inv\u00e1lido - No se adjunt\u00f3 el archivo formato de carga correctamente, favor de verificar", e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new NegocioException("Archivo inv\u00E1lido - Existe un error al procesar el archivo", e);
        }
        if (empleadoDTO == null) {
            throw new NegocioException("Empleado-Error al obtener la informaci\u00f3n del empleado");
        }
        return workbook;
    }

    private boolean validaColumnasInsumos(Map<Integer, Object> mapParametrosValidacion, List<FececUnidadAdministrativa> listaUnidadAdministrativa, List<FececSubprograma> subprogramas, List<FececSector> sectores, List<FececPrioridad> prioridades,
            List<FececTipoInsumo> tipoInsumos) {

        if (!(mapParametrosValidacion != null && !mapParametrosValidacion.isEmpty())) {
            return false;
        }
        HSSFRow row = ((HSSFRow) mapParametrosValidacion.get(Constantes.ENTERO_CERO));
        ReporteIncorrectoDto registro = (ReporteIncorrectoDto) mapParametrosValidacion.get(Constantes.ENTERO_CUATRO);
        HSSFCell cell = row.getCell(RFC_CONTIBUYENTE);
        boolean resultado = false;
        try {
            Integer col = (Integer) mapParametrosValidacion.get(Constantes.ENTERO_UNO);
            FecetInsumo insumo = (FecetInsumo) mapParametrosValidacion.get(Constantes.ENTERO_DOS);
            String rutaDocumetos = (String) mapParametrosValidacion.get(Constantes.ENTERO_TRES);

            switch (col) {
                case RFC_CONTIBUYENTE:
                    try {
                        insumo.setCell(RFC_CONTIBUYENTE);
                        registro.setRfc(cargaMasivaInsumosHelper.valorCelda(cell).trim().toUpperCase());
                        cargaMasivaInsumosHelper.checkArgument(!validaRFC(cargaMasivaInsumosHelper.valorCelda(cell).trim().toUpperCase(), insumo));
                    } catch (Exception exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("RFC");
                        return false;
                    }
                    break;

                case UNIDAD_ADMINISTRATIVA:
                    try {
                        insumo.setCell(UNIDAD_ADMINISTRATIVA);
                        registro.setUnidadAdministrativa(cargaMasivaInsumosHelper.valorCelda(row.getCell(UNIDAD_ADMINISTRATIVA)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaUnidadAdministrativa(cargaMasivaInsumosHelper.valorCelda(row.getCell(UNIDAD_ADMINISTRATIVA)), insumo, listaUnidadAdministrativa));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Unidad Administrativa");
                        return false;
                    }
                    break;
                case SUBPROGRAMA:
                    try {
                        insumo.setCell(SUBPROGRAMA);
                        registro.setSubprograma(cargaMasivaInsumosHelper.valorCelda(row.getCell(SUBPROGRAMA)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaSubprograma(cargaMasivaInsumosHelper.valorCelda(row.getCell(SUBPROGRAMA)), insumo, subprogramas));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Suprograma");
                        return false;
                    }
                    break;
                case TIPO_INSUMO:
                    try {
                        insumo.setCell(TIPO_INSUMO);
                        registro.setTipoInsumo(cargaMasivaInsumosHelper.valorCelda(row.getCell(TIPO_INSUMO)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaTipoInsumo(cargaMasivaInsumosHelper.valorCelda(row.getCell(TIPO_INSUMO)), insumo, tipoInsumos));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Tipo Insumo");
                        return false;
                    }
                    break;
                case SECTOR:
                    try {
                        insumo.setCell(SECTOR);
                        registro.setSector(cargaMasivaInsumosHelper.valorCelda(row.getCell(SECTOR)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaSector(cargaMasivaInsumosHelper.valorCelda(row.getCell(SECTOR)), insumo, sectores));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Sector");
                        return false;
                    }
                    break;
                case PRIORIDAD:
                    try {
                        insumo.setCell(PRIORIDAD);
                        registro.setPrioridad(cargaMasivaInsumosHelper.valorCelda(row.getCell(PRIORIDAD)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaPrioridad(cargaMasivaInsumosHelper.valorCelda(row.getCell(PRIORIDAD)), insumo, prioridades));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Prioridad");
                        return false;
                    }
                    break;
                case FECHA_INICIO_PERIODO:
                    try {
                        insumo.setCell(FECHA_INICIO_PERIODO);
                        registro.setFechaInicio(cargaMasivaInsumosHelper.valorCelda(row.getCell(FECHA_INICIO_PERIODO)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaFormatoFecha(cargaMasivaInsumosHelper.valorCelda(row.getCell(FECHA_INICIO_PERIODO)), insumo, true));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Fecha Inicio Periodo");
                        return false;
                    }
                    break;
                case FECHA_FIN_PERIODO:
                    try {
                        insumo.setCell(FECHA_FIN_PERIODO);
                        registro.setFechaFin(cargaMasivaInsumosHelper.valorCelda(row.getCell(FECHA_FIN_PERIODO)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaFormatoFecha(cargaMasivaInsumosHelper.valorCelda(row.getCell(FECHA_FIN_PERIODO)), insumo, false));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validaPeriodoCorrecto(insumo));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Fecha Fin Periodo");
                        return false;
                    }
                    break;
                case DOCUMENTOS:
                    try {
                        insumo.setCell(DOCUMENTOS);
                        registro.setDocumento(cargaMasivaInsumosHelper.valorCelda(row.getCell(DOCUMENTOS)));
                        cargaMasivaInsumosHelper.checkArgument(!cargaMasivaInsumosHelper.validarDocumentos(cargaMasivaInsumosHelper.valorCelda(row.getCell(DOCUMENTOS)), insumo, rutaDocumetos));
                    } catch (NegocioException exception) {
                        logger.error(exception.getMessage(), exception);
                        insumo.setNombreColumna("Documentos");
                        return false;
                    }
                    break;
            }
            resultado = true;
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
        }
        return resultado;
    }

    private boolean validaRFC(String rfcContribuyente, FecetInsumo insumo) {
        cargaMasivaInsumosHelper.validarRFCComposicion(rfcContribuyente, insumo);

        if (insumo.getDescripcionRechazo() == null) {
            try {
                insumo.setFecetContribuyente(contribuyenteService.getContribuyenteIDC(rfcContribuyente));
                ValidaMediosContactoBO medios = checarMediosDeContacto(rfcContribuyente);
                //quitar
                medios.setAmparado(false);
                medios.setpPEE(false);
                medios.setFlag(true);

                //hasta aqui
                if (!medios.isFlag() && medios.getMessage().equals(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO)) {
                    insumo.setDescripcionRechazo(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO);
                } else if (!medios.isFlag() && !medios.getMessage().equals(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO)) {
                    insumo.setDescripcionRechazo(medios.getMessage());
                    if (!medios.getMediosComunicacion().isEmpty() && medios.getMediosComunicacion().get(Constantes.CERO).getTipoMedio() == 1) {
                        enviarCorreoPPEEAmparado(insumo.getFecetContribuyente(), medios);
                    }
                }
            } catch (NoExisteContribuyenteException e) {
                logger.error("Error al obtener la informacion del RFC solicitado.", e);
                insumo.setDescripcionRechazo(String.format("No se encuentra registrada informaci\u00f3n para el RFC: %s. Favor de verificar.", rfcContribuyente));
            } catch (Exception e) {
                logger.error("Error al consultar servicio de IDC.", e);
                insumo.setDescripcionRechazo("No se pudo consultar la informaci\u00f3n del Contribuyente");
            }
        }
        return insumo.getDescripcionRechazo() == null;
    }

    private ValidaMediosContactoBO checarMediosDeContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);
        validaMediosContactoBO = consultaMediosContactoService.validaMediosContacto(validaMediosContactoBO);
        return validaMediosContactoBO;
    }

    private void enviarCorreoPPEEAmparado(final FecetContribuyente contribuyente,
            ValidaMediosContactoBO validaMediosContactoBO) {
        Map<String, String> data = notificacionService.obtenerDatosCorreo(Constantes.LEYENDA_AMPARADO);

        data.put(AvisoContribuyentePPEEAmparado.RFC.toString(), contribuyente.getRfc());
        data.put(AvisoContribuyentePPEEAmparado.NOMBRE.toString(), contribuyente.getNombre());
        data.put(AvisoContribuyentePPEEAmparado.AMPARADO.toString(), validaEstatusContribuyente(validaMediosContactoBO));

        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACPPCE, destinatarios, ClvSubModulosAgace.INSUMOS);
        notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        try {
            notificacionService.enviarNotificacionGenerico(data, ReportType.AVISO_CONTRIBUYENTE_PPEE_AMPARADO, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause());
        }
    }

    private String validaEstatusContribuyente(final ValidaMediosContactoBO validaMediosContactoBO) {
        StringBuilder estatus = new StringBuilder("");
        if (validaMediosContactoBO.isPPEE()) {
            estatus.append("PPEE");
            if (validaMediosContactoBO.isAmparado()) {
                estatus.append(" y Amparado");
            }
        } else if (validaMediosContactoBO.isAmparado()) {
            estatus.append("Amparado");
        }
        return estatus.toString();
    }

    private boolean isRowBlank(HSSFRow row) {
        int maxIter = DOCUMENTOS;
        boolean flgDatoValido = false;
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            if (cell == null) {
                if ((maxIter == 0)) {
                    return true;
                }
                maxIter--;
            } else if (((cell.getCellType() == Cell.CELL_TYPE_BLANK))) {
                if ((maxIter == 0)) {
                    return true;
                }
                maxIter--;
            } else {
                flgDatoValido = true;
            }

            if (flgDatoValido) {
                return false;
            }
        }
        return false;
    }

    @Override
    public byte[] generarReporteExcel(String jasper, String nombreExcel, List<ReporteIncorrectoDto> listaErrores) {
        try {
            return jaspertReportsService.makeReport(jasper, nombreExcel, null, listaErrores);
        } catch (JaspertReportsServiceException ex) {
            java.util.logging.Logger.getLogger(CargaMasivaInsumosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
