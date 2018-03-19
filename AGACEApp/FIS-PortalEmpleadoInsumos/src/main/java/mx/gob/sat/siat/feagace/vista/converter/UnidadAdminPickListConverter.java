/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@FacesConverter(value = "unidadAdminPickListConverter")
public class UnidadAdminPickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object ret = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Object o : dl.getSource()) {
                String id = "" + ((AraceDTO) o).getIdArace();
                if (arg2.equals(id)) {
                    ret = o;
                    break;
                }
            }
            if (ret == null) {
                for (Object o : dl.getTarget()) {
                    String id = "" + ((AraceDTO) o).getIdArace();
                    if (arg2.equals(id)) {
                        ret = o;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String str = "";
        if (arg2 instanceof AraceDTO) {
            str = "" + ((AraceDTO) arg2).getIdArace();
        }
        return str;
    }
}
