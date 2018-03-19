package mx.gob.sat.siat.feagace.vista.propuestas;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.RechazoPropuestaService;


import mx.gob.sat.siat.feagace.vista.common.InformeRechazoManagedBean;


import mx.gob.sat.siat.feagace.vista.helper.InformeRechazoHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ViewScoped
@ManagedBean(name="rechazoPropuestaManagedBean")
public class RechazoPropuestaManagedBean extends InformeRechazoManagedBean {

    private List<FececEstatus> listaFececEstatus;
    private List<ConsultaInformeComiteRechazoPropuesta> listaRechazosPropuesta;
    private BigDecimal idEstatus;
    private StreamedContent xlsReporte;

    private boolean mostrarComboEntidad;
    
    @ManagedProperty(value = "#{informeRechazoHelper}")
    private InformeRechazoHelper helper;

    @ManagedProperty(value = "#{rechazoPropuestaService}")
    private RechazoPropuestaService rechazoPropuestaService;



    @PostConstruct
    public void init() {
        listaRechazosPropuesta = Collections.<ConsultaInformeComiteRechazoPropuesta>emptyList();
        setMostrarExportar(true);
        setMostrarComboEntidad(false);        
        setListaEntidades(new ArrayList<String>());
        setMostrarExportar(true);
        setRfc("");
        setIdEntidad(Constantes.COMBO_SELECCIONA_CADENA);
        setIdActividadPreponderante(Constantes.COMBO_SELECCIONA.toString());            

        if(helper.listaRolesAceptadosRechazosPropuesta().contains(getEmpleado().getFececTipoEmpleado().getIdTipoEmpleado()) &&  
            helper.listaIdAraceRegional().contains(getEmpleado().getFecetDetalleEmpleado().getIdCentral())){
                setMostrarComboEntidad(true);
                cargarComboEntidad(getEmpleado());
        }        
            cargarComboActividadPreponderante();
            listaFececEstatus = getRechazoPropuestaService().construyeComboEstatus();
    }
    



    public void buscarRechazos() {
        listaRechazosPropuesta = getRechazoPropuestaService().buscarRechazosPropuesta( getRfc(), getIdEntidad(), getIdActividadPreponderante(), idEstatus,getEmpleado());        
        if(!listaRechazosPropuesta.isEmpty()){
            setMostrarExportar(false);
        }
        else{
            setMostrarExportar(true);
        }
    }

    public void limpiarRechazos() {
        setMostrarExportar(true);
        listaRechazosPropuesta.clear();
        setRfc("");
        setIdEntidad(Constantes.COMBO_SELECCIONA_CADENA);
        setIdActividadPreponderante(Constantes.COMBO_SELECCIONA.toString());
        idEstatus = Constantes.COMBO_SELECCIONA;        
    }

    public void setXlsReporte(StreamedContent xlsReporte) {
        this.xlsReporte = xlsReporte;
    }

    public StreamedContent getXlsReporte() {
        File file = null;
        HSSFWorkbook workbook = null;
        workbook = getRechazoPropuestaService().exportarRechazosPropuesta(listaRechazosPropuesta);
        FileOutputStream out = null;
        try {
            file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            xlsReporte = new DefaultStreamedContent(new FileInputStream(file), "application/xls", "Propuestas No aprobadas.xls");
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

    public void setRechazoPropuestaService(RechazoPropuestaService rechazoPropuestaService) {
        this.rechazoPropuestaService = rechazoPropuestaService;
    }

    public RechazoPropuestaService getRechazoPropuestaService() {
        return rechazoPropuestaService;
    }

    public void setListaFececEstatus(List<FececEstatus> listaFececEstatus) {
        this.listaFececEstatus = listaFececEstatus;
    }

    public List<FececEstatus> getListaFececEstatus() {
        return listaFececEstatus;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setListaRechazosPropuesta(List<ConsultaInformeComiteRechazoPropuesta> listaRechazosPropuesta) {
        this.listaRechazosPropuesta = listaRechazosPropuesta;
    }

    public List<ConsultaInformeComiteRechazoPropuesta> getListaRechazosPropuesta() {
        return listaRechazosPropuesta;
    }

    public void setMostrarComboEntidad(boolean mostrarComboEntidad) {
        this.mostrarComboEntidad = mostrarComboEntidad;
    }

    public boolean isMostrarComboEntidad() {
        return mostrarComboEntidad;
    }

    public void setHelper(InformeRechazoHelper helper) {
        this.helper = helper;
    }

    public InformeRechazoHelper getHelper() {
        return helper;
    }
}
