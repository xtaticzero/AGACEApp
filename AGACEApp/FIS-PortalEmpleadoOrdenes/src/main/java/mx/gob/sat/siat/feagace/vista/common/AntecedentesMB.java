package mx.gob.sat.siat.feagace.vista.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@ManagedBean(name = "antecedentesMB")
@ViewScoped
public class AntecedentesMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 8784526765801153795L;
    private static final String CADENA = "No se encontro el usuario logueado: ";
    private StreamedContent xlsReporte;
    private String statusMediosContacto = "";
    private boolean visibleExportar = false;
    private boolean visibleGridInsumos = false;
    private boolean visibleGridPropuestas = true;

    private String rfc;
    private Date periodoInicial;
    private Date periodoFinal;

    @ManagedProperty(value = "#{consultaAntecedentesService}")
    private transient ConsultaAntecedentesPropuestasService consultaAntecedentesService;

    private List<FecetPropuesta> revisionesAgace;
    private List<FecetPropuesta> revisionesAgaff;
    private List<FecetPropuesta> revisionesSUIEFIs;
    private List<FecetPropuesta> revisionesSICSEP;
    private List<FecetInsumo> revisionesInsumos;
    private List<FecetImpuestoDescripcion> listaImpuestos;
    private FecetPropuesta propuestaSelecionada;

    private BigDecimal presuntiva;

    private boolean muestraEmptyMsj = false;

    private boolean error = false;

    public void init() throws NoExisteEmpleadoException {
        try {
            error = false;
            consultaAntecedentesService.getEmpleadoProgramador(getRFCSession());
        } catch (NegocioException e) {
            logger.error(CADENA + getRFCSession());
            addErrorMessage(null, CADENA, getRFCSession());
            error = true;
        }
    }

    public void buscarAntecedentes() {
        revisionesAgace = new ArrayList<FecetPropuesta>();
        revisionesAgaff = new ArrayList<FecetPropuesta>();
        revisionesSICSEP = new ArrayList<FecetPropuesta>();
        revisionesSUIEFIs = new ArrayList<FecetPropuesta>();
        revisionesInsumos = new ArrayList<FecetInsumo>();
        listaImpuestos = new ArrayList<FecetImpuestoDescripcion>();
        propuestaSelecionada = new FecetPropuesta();
        FecetPropuesta prop = new FecetPropuesta();
        prop.setFechaInicioPeriodo(periodoInicial);
        prop.setFechaFinPeriodo(periodoFinal);
        if (validarFormulario()) {
            procesaMediosDeContacto();
            if (Constantes.MSG_CON_MEDIO.equalsIgnoreCase(statusMediosContacto)) {
                muestraEmptyMsj = true;
                try {
                    revisionesInsumos = consultaAntecedentesService.consultaAGACEInsumos(rfc, prop);
                } catch (Exception e) {
                    logger.error(MessageFormat.format(ConstantesError.ERROR_CONSULTAR_INFO, Constantes.AGACE,
                            e.getMessage()));
                }
                try {
                    revisionesAgace = consultaAntecedentesService.consultaAGACE(rfc, periodoInicial, periodoFinal);
                } catch (Exception e) {
                    logger.error(MessageFormat.format(ConstantesError.ERROR_CONSULTAR_INFO, "INSUMOS", e.getMessage()));
                }
                try {
                    revisionesSICSEP = consultaAntecedentesService.consultaSICSEP(rfc, periodoInicial, periodoFinal);
                } catch (Exception e) {
                    logger.error(MessageFormat.format(ConstantesError.ERROR_CONSULTAR_INFO, Constantes.SICSEP,
                            e.getMessage()));
                }
                try {
                    revisionesSUIEFIs = consultaAntecedentesService.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
                } catch (Exception e) {
                    logger.error(MessageFormat.format(ConstantesError.ERROR_CONSULTAR_INFO, Constantes.SUIEFI,
                            e.getMessage()));
                }
                try {
                    revisionesAgaff = consultaAntecedentesService.consultaAGAFF(rfc, periodoInicial, periodoFinal);
                } catch (Exception e) {
                    logger.error(MessageFormat.format(ConstantesError.ERROR_CONSULTAR_INFO, Constantes.AGAFF,
                            e.getMessage()));
                }
            }
        }
        validaInformacionMostrada();

        boolean[] condiciones = new boolean[]{(revisionesAgace != null && !revisionesAgace.isEmpty()),
            (revisionesAgaff != null && !revisionesAgaff.isEmpty()),
            (revisionesSICSEP != null && !revisionesSICSEP.isEmpty()),
            (revisionesSUIEFIs != null && !revisionesSUIEFIs.isEmpty()),
            (revisionesInsumos != null && !revisionesInsumos.isEmpty())};

        if (ValidacionParametrosUtil.seCumpleAlgunaCondicion(condiciones)) {
            setVisibleExportar(true);
        } else {
            setVisibleExportar(false);
        }
    }

    private void validaInformacionMostrada() {
        boolean existePropuesta = (revisionesAgace != null && !revisionesAgace.isEmpty());
        boolean existeInsumo = (revisionesInsumos != null && !revisionesInsumos.isEmpty());

        if (existePropuesta && existeInsumo) {
            setVisibleGridInsumos(false);
            setVisibleGridPropuestas(true);
        } else if (existePropuesta && !existeInsumo) {
            setVisibleGridInsumos(false);
            setVisibleGridPropuestas(true);
        } else if (!existePropuesta && existeInsumo) {
            setVisibleGridInsumos(true);
            setVisibleGridPropuestas(false);
        }
    }

    private boolean validarFormulario() {
        boolean validaFormulario = true;

        if (this.rfc == null || this.rfc.equals("")) {
            validaFormulario = false;
            addErrorMessage("formAntecedentes:msjErrorRfc", getMessageResourceString("error.campo.obligatorio.rfc"));
        } else {
            rfc = rfc.toUpperCase();
        }

        if (this.periodoInicial == null) {
            validaFormulario = false;
            addErrorMessage("formAntecedentes:msjErrorFechaInicial",
                    getMessageResourceString("error.campo.obligatorio.periodo.inicial"));
        }

        if (this.periodoFinal == null) {
            validaFormulario = false;
            addErrorMessage("formAntecedentes:msjErrorFechaFinal",
                    getMessageResourceString("error.campo.obligatorio.periodo.final"));
        }

        return validaFormulario;
    }

    private void procesaMediosDeContacto() {
        statusMediosContacto = consultaAntecedentesService.msgMediosContacto(rfc);
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getPeriodoInicial() {
        return (periodoInicial != null) ? (Date) periodoInicial.clone() : null;
    }

    public void setPeriodoInicial(final Date periodoInicial) {
        this.periodoInicial = (periodoInicial != null) ? (Date) periodoInicial.clone() : null;
    }

    public Date getPeriodoFinal() {
        return (periodoFinal != null) ? (Date) periodoFinal.clone() : null;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = (periodoFinal != null) ? (Date) periodoFinal.clone() : null;
    }

    public List<FecetPropuesta> getRevisionesAgace() {
        return revisionesAgace;
    }

    public void setRevisionesAgace(List<FecetPropuesta> revisionesAgace) {
        this.revisionesAgace = revisionesAgace;
    }

    public List<FecetPropuesta> getRevisionesAgaff() {
        return revisionesAgaff;
    }

    public void setRevisionesAgaff(List<FecetPropuesta> revisionesAgaff) {
        this.revisionesAgaff = revisionesAgaff;
    }

    public List<FecetPropuesta> getRevisionesSUIEFIs() {
        return revisionesSUIEFIs;
    }

    public void setRevisionesSUIEFIs(List<FecetPropuesta> revisionesSUIEFIs) {
        this.revisionesSUIEFIs = revisionesSUIEFIs;
    }

    public List<FecetPropuesta> getRevisionesSICSEP() {
        return revisionesSICSEP;
    }

    public void setRevisionesSICSEP(List<FecetPropuesta> revisionesSICSEP) {
        this.revisionesSICSEP = revisionesSICSEP;
    }

    public void setConsultaAntecedentesService(ConsultaAntecedentesPropuestasService consultaAntecedentesService) {
        this.consultaAntecedentesService = consultaAntecedentesService;
    }

    public ConsultaAntecedentesPropuestasService getConsultaAntecedentesService() {
        return consultaAntecedentesService;
    }

    public void setXlsReporte(StreamedContent xlsReporte) {
        this.xlsReporte = xlsReporte;
    }

    public StreamedContent getXlsReporte() {
        File file = null;
        HSSFWorkbook workbook = consultaAntecedentesService.creaExcel(revisionesAgace, revisionesAgaff,
                revisionesSUIEFIs, revisionesSICSEP, revisionesInsumos, isVisibleGridInsumos());

        FileOutputStream out = null;

        try {
            file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            xlsReporte = new DefaultStreamedContent(new FileInputStream(file), "application/xls", "reporte.xls");
            file.deleteOnExit();
        } catch (IOException e) {
            logger.error("Error: ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("No se pudo limpiar la memoria", e);
                }
            }
        }

        return xlsReporte;
    }

    public void buscarImpuestosPropuesta() {
        presuntiva = BigDecimal.ZERO;
        BigDecimal presuntivaConvertida;
        setListaImpuestos(consultaAntecedentesService.getImpuestos(propuestaSelecionada.getIdPropuesta()));

        for (FecetImpuestoDescripcion impuestoSumar : getListaImpuestos()) {
            presuntivaConvertida = impuestoSumar.getFecetImpuesto().getMonto();
            setPresuntiva(getPresuntiva().add(presuntivaConvertida));
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgImpuestos').show();");
    }

    public void setVisibleExportar(boolean visibleExportar) {
        this.visibleExportar = visibleExportar;
    }

    public boolean isVisibleExportar() {
        return visibleExportar;
    }

    public void setStatusMediosContacto(String statusMediosContacto) {
        this.statusMediosContacto = statusMediosContacto;
    }

    public String getStatusMediosContacto() {
        return statusMediosContacto;
    }

    public void setMuestraEmptyMsj(boolean muestraEmptyMsj) {
        this.muestraEmptyMsj = muestraEmptyMsj;
    }

    public boolean isMuestraEmptyMsj() {
        return muestraEmptyMsj;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isVisibleGridInsumos() {
        return visibleGridInsumos;
    }

    public void setVisibleGridInsumos(boolean visibleGridInsumos) {
        this.visibleGridInsumos = visibleGridInsumos;
    }

    public boolean isVisibleGridPropuestas() {
        return visibleGridPropuestas;
    }

    public void setVisibleGridPropuestas(boolean visibleGridPropuestas) {
        this.visibleGridPropuestas = visibleGridPropuestas;
    }

    public List<FecetInsumo> getRevisionesInsumos() {
        return revisionesInsumos;
    }

    public void setRevisionesInsumos(List<FecetInsumo> revisionesInsumos) {
        this.revisionesInsumos = revisionesInsumos;
    }

    public List<FecetImpuestoDescripcion> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<FecetImpuestoDescripcion> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public FecetPropuesta getPropuestaSelecionada() {
        return propuestaSelecionada;
    }

    public void setPropuestaSelecionada(FecetPropuesta propuestaSelecionada) {
        this.propuestaSelecionada = propuestaSelecionada;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

}
