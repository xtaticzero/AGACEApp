package mx.gob.sat.siat.feagace.vista.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

/**
 * @author sergio.vaca
 *
 */
@ManagedBean(name = "controlPaginacionMB")
@SessionScoped
public class ControlPaginacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int PAGINA_UNO = 1;
    
    private int pagina;
    
    public void init() {
        this.pagina = PAGINA_UNO;
    }
    
    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public void onPageChange(PageEvent event) {
        this.setPagina(((DataTable) event.getSource()).getFirst());
    }
}
