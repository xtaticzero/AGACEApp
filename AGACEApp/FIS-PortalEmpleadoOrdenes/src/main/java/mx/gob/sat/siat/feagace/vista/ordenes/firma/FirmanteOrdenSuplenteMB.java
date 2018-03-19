/**
 * 
 */
package mx.gob.sat.siat.feagace.vista.ordenes.firma;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.gob.sat.siat.feagace.vista.FirmanteSuplenteMB;

/**
 * @author sergio.vaca
 *
 */
@ManagedBean(name = "firmanteSuplenteOrden")
@SessionScoped
public class FirmanteOrdenSuplenteMB extends FirmanteSuplenteMB {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
    *
    * @return
    */
   public String firmarDocumentos() {
       String redirect = super.firmarDocumentos();
       return redirect != null && !redirect.isEmpty() ? "indexValidarFirmar?faces-redirect=true" : redirect;
   }

}
