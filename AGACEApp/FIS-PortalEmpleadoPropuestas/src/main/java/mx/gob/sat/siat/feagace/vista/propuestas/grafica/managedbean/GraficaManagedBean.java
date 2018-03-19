package mx.gob.sat.siat.feagace.vista.propuestas.grafica.managedbean;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.poi.util.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.TextAnchor;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.vista.propuestas.grafica.GraficaPropuestasAbstractMB;

@ManagedBean(name = "graficaMB")
@SessionScoped

public class GraficaManagedBean extends GraficaPropuestasAbstractMB {

    private static final long serialVersionUID = -5480149578403079174L;

    private static final int ANCHO = 650;
    private static final int ALTO = 525;
    private static final float BACKGROUND = 0.2F;
    private Integer ejeYMaximo = 0;
    private Integer scalaEjeY = 1;
    private int tipoGraficaBarras;
    private int tipoGraficaCircular;
    private static final String CANTIDAD = "Cantidad";
    private int contarGrafica;

    public List<String> crearGraficaReporte(GraficaDTO graficaDTO) {
        List<String> urlImagenes = new ArrayList<String>();
        if (graficaDTO.getTipoGrafica().equals(ConstantesReportes.TIPO_GRAFICA_BAR)) {
            DefaultCategoryDataset dataSetBar;
            switch (graficaDTO.getNivelGrafica()) {
                case ConstantesReportes.NIVEL_0:
                    dataSetBar = generaDataSetNivel0Bar(graficaDTO);
                    urlImagenes = crearUnaGraficaBar(graficaDTO, dataSetBar);
                    break;
                case ConstantesReportes.NIVEL_1:
                    dataSetBar = generaDataSetNivelUnoBar(graficaDTO);
                    urlImagenes = crearUnaGraficaBar(graficaDTO, dataSetBar);
                    break;
                case ConstantesReportes.NIVEL_2:
                    dataSetBar = generaDataSetNivelDosBar(graficaDTO);
                    urlImagenes = crearUnaGraficaBar(graficaDTO, dataSetBar);
                    break;
                case ConstantesReportes.NIVEL_3:
                    urlImagenes = generaDataSetNivelTresBar(graficaDTO);
                    break;
                default:
                    logger.debug("No se puede generar la grafica, no existe el nivel de busqueda");
                    break;
            }
        }
        if (graficaDTO.getTipoGrafica().equals(ConstantesReportes.TIPO_GRAFICA_PIE)) {
            PieDataset dataSetPie;
            switch (graficaDTO.getNivelGrafica()) {
                case ConstantesReportes.NIVEL_0:
                    dataSetPie = generaDataSetNivel0Pie(graficaDTO.getListaValores());
                    urlImagenes = crearUnaGraficaPie(graficaDTO, dataSetPie);
                    break;
                case ConstantesReportes.NIVEL_1:
                    dataSetPie = generaDataSetNivel1Pie(graficaDTO.getListaValores());
                    urlImagenes = crearUnaGraficaPie(graficaDTO, dataSetPie);
                    break;
                case ConstantesReportes.NIVEL_4:
                    dataSetPie = generaDataSetNivel4Pie(graficaDTO);
                    urlImagenes = crearUnaGraficaPie(graficaDTO, dataSetPie);
                    break;
                case ConstantesReportes.NIVEL_5:
                    urlImagenes = crearUnaGraficaPieLista(graficaDTO);
                    break;
                default:
                    logger.debug("No se puede crear la grafica, error en nivel de busqueda");
                    break;
            }
        }
        return urlImagenes;
    }

    private List<String> crearUnaGraficaBar(GraficaDTO graficaDTO, DefaultCategoryDataset dataSetBar) {
        List<String> urlImagenes = new ArrayList<String>();
        setListaIdGraficas(new ArrayList<BigDecimal>());

        JFreeChart jfreechart = ChartFactory.createBarChart3D(graficaDTO.getTituloGrafica(), "", CANTIDAD, dataSetBar, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
        NumberAxis axis = new NumberAxis(CANTIDAD);
        valorMaximoFinalEjeY();
        axis.setRange(0.0, ejeYMaximo);
        axis.setTickUnit(new NumberTickUnit(scalaEjeY));
        plot.setRangeAxis(axis);
        CategoryAxis axisX = (CategoryAxis) plot.getDomainAxis();
        axisX.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        StandardCategoryItemLabelGenerator labelGen = new StandardCategoryItemLabelGenerator();
        renderer.setBaseItemLabelGenerator(labelGen);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        contarGrafica = 0;
        guardarGraficaLista(jfreechart);
        return urlImagenes;
    }

    private DefaultCategoryDataset generaDataSetNivel0Bar(GraficaDTO graficaDTO) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        StringBuilder periodo = new StringBuilder();
        ejeYMaximo = 0;
        periodo.append(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, graficaDTO.getFechaInicio()));
        periodo.append(" - ");
        periodo.append(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, graficaDTO.getFechaFinal()));
        dataSet.addValue(graficaDTO.getListaValores().get(0).getCantidad(), graficaDTO.getTituloGrafica(), periodo.toString());
        ejeYMaximo = graficaDTO.getListaValores().get(0).getCantidad();
        return dataSet;
    }

    private DefaultCategoryDataset generaDataSetNivelUnoBar(GraficaDTO graficaDTO) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        ejeYMaximo = 0;
        for (GraficaValoresDTO dto : graficaDTO.getListaValores()) {
            dataSet.addValue(dto.getCantidad(), dto.getMes(), "");
            valorMaximoEjeY(dto.getCantidad());
        }
        return dataSet;
    }

    private DefaultCategoryDataset generaDataSetNivelDosBar(GraficaDTO graficaDTO) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        ejeYMaximo = 0;
        tipoGraficaBarras(graficaDTO);
        for (GraficaValoresDTO dto : graficaDTO.getListaValores()) {
            llenarDataSetBarras(dataSet, dto);
            valorMaximoEjeY(dto.getCantidad());
        }
        return dataSet;
    }

    private List<String> generaDataSetNivelTresBar(GraficaDTO graficaDTO) {
        List<String> urlImagenes = new ArrayList<String>();
        setListaIdGraficas(new ArrayList<BigDecimal>());
        DefaultCategoryDataset dataSet;
        tipoGraficaBarras(graficaDTO);
        contarGrafica = 0;

        for (int i = 0; i < graficaDTO.getListaValores().size(); i++) {
            ejeYMaximo = 0;
            valorMaximoEjeY(graficaDTO.getListaValores().get(i).getCantidad());
            dataSet = llenarDataSetBarras(graficaDTO.getListaValores().get(i));
            for (int j = i + 1; j < graficaDTO.getListaValores().size(); j++) {
                if (graficaDTO.getListaValores().get(i).getMes().equals(graficaDTO.getListaValores().get(j).getMes())) {
                    dataSet = llenarDataSetBarras(dataSet, graficaDTO.getListaValores().get(j));
                    valorMaximoEjeY(graficaDTO.getListaValores().get(j).getCantidad());
                    graficaDTO.getListaValores().remove(graficaDTO.getListaValores().get(j));
                    j--;
                }
            }
            JFreeChart jfreechart = ChartFactory.createBarChart3D(graficaDTO.getListaValores().get(i).getMes(), graficaDTO.getTituloGrafica(), CANTIDAD, dataSet, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
            NumberAxis axis = new NumberAxis(CANTIDAD);
            valorMaximoFinalEjeY();
            axis.setRange(0.0, ejeYMaximo);
            axis.setTickUnit(new NumberTickUnit(scalaEjeY));
            plot.setRangeAxis(axis);
            CategoryAxis axisX = (CategoryAxis) plot.getDomainAxis();
            axisX.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

            BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
            StandardCategoryItemLabelGenerator labelGen = new StandardCategoryItemLabelGenerator();
            renderer.setBaseItemLabelGenerator(labelGen);
            renderer.setBaseItemLabelsVisible(true);
            renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
            guardarGraficaLista(jfreechart);
        }
        return urlImagenes;
    }

    private void valorMaximoEjeY(Integer cantidadBarra) {
        if (ejeYMaximo < cantidadBarra) {
            ejeYMaximo = cantidadBarra;
        }
    }

    private void valorMaximoFinalEjeY() {
        Integer moduloEjeYMaximo = ejeYMaximo % ConstantesReportes.N_10;
        Integer numeroMaximo = ejeYMaximo / ConstantesReportes.N_10;
        if (moduloEjeYMaximo != 0 && moduloEjeYMaximo != ConstantesReportes.N_5) {
            if (moduloEjeYMaximo < ConstantesReportes.N_5) {
                numeroMaximo = numeroMaximo * ConstantesReportes.N_10;
                numeroMaximo = numeroMaximo + ConstantesReportes.N_5;
            } else {
                numeroMaximo = numeroMaximo + ConstantesReportes.N_1;
                numeroMaximo = numeroMaximo * ConstantesReportes.N_10;
            }
            ejeYMaximo = numeroMaximo;
        }

        if (ejeYMaximo % ConstantesReportes.N_6 != 0) {
            boolean tagModulo = true;
            while (tagModulo) {
                if (ejeYMaximo % ConstantesReportes.N_6 == 0) {
                    tagModulo = false;
                } else {
                    ejeYMaximo = ejeYMaximo + 1;
                }
            }
            scalaEjeY = ejeYMaximo / ConstantesReportes.N_6;
        } else {
            scalaEjeY = ejeYMaximo / ConstantesReportes.N_6;
            ejeYMaximo = ejeYMaximo + ConstantesReportes.N_10;
        }

    }

    private void tipoGraficaBarras(GraficaDTO graficaDTO) {
        if (graficaDTO.isPresentaMetodo() && graficaDTO.isPresentaEntidad()) {
            tipoGraficaBarras = ConstantesReportes.N_1;
        }
        if (graficaDTO.isPresentaMetodo() && graficaDTO.isPresentaEstatus()) {
            tipoGraficaBarras = ConstantesReportes.N_2;
        }
        if (graficaDTO.isPresentaMetodo() && graficaDTO.isPresentaUnidadAdministrativa()) {
            tipoGraficaBarras = ConstantesReportes.N_3;
        }
        if (graficaDTO.isPresentaEntidad() && graficaDTO.isPresentaEstatus()) {
            tipoGraficaBarras = ConstantesReportes.N_4;
        }
        if (graficaDTO.isPresentaEntidad() && graficaDTO.isPresentaUnidadAdministrativa()) {
            tipoGraficaBarras = ConstantesReportes.N_5;
        }
        if (graficaDTO.isPresentaEstatus() && graficaDTO.isPresentaUnidadAdministrativa()) {
            tipoGraficaBarras = ConstantesReportes.N_6;
        }
    }

    private DefaultCategoryDataset llenarDataSetBarras(GraficaValoresDTO dto) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        llenarDataSetBarras(dataSet, dto);
        return dataSet;
    }

    private DefaultCategoryDataset llenarDataSetBarras(DefaultCategoryDataset dataSet, GraficaValoresDTO dto) {
        switch (tipoGraficaBarras) {
            case ConstantesReportes.N_1:
                dataSet.addValue(dto.getCantidad(), dto.getEntidad(), dto.getMetodo());
                break;
            case ConstantesReportes.N_2:
                dataSet.addValue(dto.getCantidad(), dto.getEstatus(), dto.getMetodo());
                break;
            case ConstantesReportes.N_3:
                dataSet.addValue(dto.getCantidad(), dto.getUnidad(), dto.getMetodo());
                break;
            case ConstantesReportes.N_4:
                dataSet.addValue(dto.getCantidad(), dto.getEstatus(), dto.getEntidad());
                break;
            case ConstantesReportes.N_5:
                dataSet.addValue(dto.getCantidad(), dto.getEntidad(), dto.getUnidad());
                break;
            case ConstantesReportes.N_6:
                dataSet.addValue(dto.getCantidad(), dto.getUnidad(), dto.getEstatus());
                break;
            default:
                break;
        }
        return dataSet;
    }

    private List<String> crearUnaGraficaPie(GraficaDTO graficaDTO, PieDataset dataSetPie) {
        List<String> urlImagenes = new ArrayList<String>();
        setListaIdGraficas(new ArrayList<BigDecimal>());
        contarGrafica = 0;
        JFreeChart jfreechart = ChartFactory.createPieChart3D(graficaDTO.getTituloGrafica(), dataSetPie, true, true, false);
        PiePlot3D plot = (PiePlot3D) jfreechart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
        plot.setBackgroundAlpha(BACKGROUND);
        plot.setBackgroundPaint(Color.WHITE);
        contarGrafica = 0;
        guardarGraficaLista(jfreechart);
        return urlImagenes;
    }

    private List<String> crearUnaGraficaPieLista(GraficaDTO graficaDTO) {
        List<String> urlImagenes = new ArrayList<String>();
        setListaIdGraficas(new ArrayList<BigDecimal>());
        contarGrafica = 0;
        DefaultPieDataset data;
        tipoGraficaCircular(graficaDTO);
        listaGraficaCircular(graficaDTO.getListaValores());
        for (int i = 0; i < graficaDTO.getListaValores().size(); i++) {
            data = llenarDataSetCircular(graficaDTO.getListaValores().get(i));
            for (int j = i + 1; j < graficaDTO.getListaValores().size(); j++) {
                if (graficaDTO.getListaValores().get(i).getMes().equals(graficaDTO.getListaValores().get(j).getMes())) {
                    data = llenarDataSetCircular(data, graficaDTO.getListaValores().get(j));
                    graficaDTO.getListaValores().remove(graficaDTO.getListaValores().get(j));
                    j--;
                }

            }
            JFreeChart jfreechart = ChartFactory.createPieChart3D(graficaDTO.getListaValores().get(i).getMes(), data, true, true, false);
            PiePlot3D plot = (PiePlot3D) jfreechart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
            plot.setBackgroundAlpha(BACKGROUND);
            plot.setBackgroundPaint(Color.WHITE);
            guardarGraficaLista(jfreechart);
        }
        return urlImagenes;
    }

    private PieDataset generaDataSetNivel0Pie(List<GraficaValoresDTO> lista) {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Insumos", lista.get(0).getCantidad());
        return data;
    }

    private PieDataset generaDataSetNivel1Pie(List<GraficaValoresDTO> lista) {
        DefaultPieDataset data = new DefaultPieDataset();
        for (GraficaValoresDTO graDTO : lista) {
            data.setValue(graDTO.getMes(), graDTO.getCantidad());
        }
        return data;
    }

    private PieDataset generaDataSetNivel4Pie(GraficaDTO graficaDTO) {
        DefaultPieDataset data = new DefaultPieDataset();
        tipoGraficaCircular(graficaDTO);
        listaGraficaCircular(graficaDTO.getListaValores());
        for (GraficaValoresDTO graDTO : graficaDTO.getListaValores()) {
            llenarDataSetCircular(data, graDTO);
        }
        return data;
    }

    private DefaultPieDataset llenarDataSetCircular(GraficaValoresDTO graDTO) {
        DefaultPieDataset data = new DefaultPieDataset();
        data = llenarDataSetCircular(data, graDTO);
        return data;
    }

    private DefaultPieDataset llenarDataSetCircular(DefaultPieDataset data, GraficaValoresDTO graDTO) {
        switch (tipoGraficaCircular) {
            case ConstantesReportes.N_1:
                data.setValue(graDTO.getMetodo(), graDTO.getCantidad());
                break;
            case ConstantesReportes.N_2:
                data.setValue(graDTO.getEstatus(), graDTO.getCantidad());
                break;
            case ConstantesReportes.N_3:
                data.setValue(graDTO.getEntidad(), graDTO.getCantidad());
                break;
            case ConstantesReportes.N_4:
                data.setValue(graDTO.getUnidad(), graDTO.getCantidad());
                break;
            default:
                break;
        }
        return data;
    }

    private void tipoGraficaCircular(GraficaDTO graficaDTO) {
        if (graficaDTO.isPresentaMetodo()) {
            tipoGraficaCircular = ConstantesReportes.N_1;
        }
        if (graficaDTO.isPresentaEstatus()) {
            tipoGraficaCircular = ConstantesReportes.N_2;
        }
        if (graficaDTO.isPresentaEntidad()) {
            tipoGraficaCircular = ConstantesReportes.N_3;
        }
        if (graficaDTO.isPresentaUnidadAdministrativa()) {
            tipoGraficaCircular = ConstantesReportes.N_4;
        }
    }

    private void listaGraficaCircular(List<GraficaValoresDTO> lista) {
        if (tipoGraficaCircular == ConstantesReportes.N_1) {
            sumarCantidadMetodo(lista);
        }
        if (tipoGraficaCircular == ConstantesReportes.N_2) {
            sumarCantidadEstatus(lista);
        }
        if (tipoGraficaCircular == ConstantesReportes.N_3) {
            sumarCantidadEntidad(lista);
        }
        if (tipoGraficaCircular == ConstantesReportes.N_4) {
            sumarCantidadUnidad(lista);
        }
    }

    private void sumarCantidadMetodo(List<GraficaValoresDTO> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getMetodo().equals(lista.get(j).getMetodo()) && (lista.get(i).getMes() == null || lista.get(i).getMes().equals(lista.get(j).getMes()))) {
                    Integer suma = lista.get(i).getCantidad();
                    suma = suma + lista.get(j).getCantidad();
                    lista.get(i).setCantidad(suma);
                    lista.remove(lista.get(j));
                    j--;
                }
            }
        }
    }

    private void sumarCantidadEstatus(List<GraficaValoresDTO> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getEstatus().equals(lista.get(j).getEstatus()) && (lista.get(i).getMes() == null || lista.get(i).getMes().equals(lista.get(j).getMes()))) {
                    Integer suma = lista.get(i).getCantidad();
                    suma = suma + lista.get(j).getCantidad();
                    lista.get(i).setCantidad(suma);
                    lista.remove(lista.get(j));
                    j--;
                }
            }
        }
    }

    private void sumarCantidadEntidad(List<GraficaValoresDTO> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getEntidad().equals(lista.get(j).getEntidad()) && (lista.get(i).getMes() == null || lista.get(i).getMes().equals(lista.get(j).getMes()))) {
                    Integer suma = lista.get(i).getCantidad();
                    suma = suma + lista.get(j).getCantidad();
                    lista.get(i).setCantidad(suma);
                    lista.remove(lista.get(j));
                    j--;
                }
            }
        }
    }

    private void sumarCantidadUnidad(List<GraficaValoresDTO> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getUnidad().equals(lista.get(j).getUnidad()) && (lista.get(i).getMes() == null || lista.get(i).getMes().equals(lista.get(j).getMes()))) {
                    Integer suma = lista.get(i).getCantidad();
                    suma = suma + lista.get(j).getCantidad();
                    lista.get(i).setCantidad(suma);
                    lista.remove(lista.get(j));
                    j--;
                }
            }
        }
    }

    private void guardarGraficaLista(JFreeChart jfreechart) {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Date fecha = new Date();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ChartUtilities.writeChartAsJPEG(outputStream, jfreechart, ANCHO, ALTO);
            ByteArrayInputStream in = new ByteArrayInputStream(outputStream.toByteArray());
            byte[] bytes = IOUtils.toByteArray(in);
            archivoTemp.setSessionUUID(getRFCSession());
            archivoTemp.setArchivoByte(bytes);
            archivoTemp.setFecha(fecha);
            getListaIdGraficas().add(getArchivoTempService().insertaArchivoTemp(archivoTemp));            
        } catch (IOException e) {
            addErrorMessage(ConstantesReportes.MSG_REPORTES, "" + e);
            logger.debug(e.getMessage());
        }
    }

    public StreamedContent getGrafica() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            return new DefaultStreamedContent(getListaGraficas().get(contarGrafica++), "image/png");
        }
    }
}
