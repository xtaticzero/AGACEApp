/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ComparatorXClaveItemFiltro implements Comparator<ItemCombo>, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(ItemCombo t, ItemCombo obj) {
        if (t != null && obj != null) {
            if (t.getDescripcion() != null && obj.getDescripcion() != null) {
                return t.getValor() - obj.getValor();
            } 
            return 0;
        } else {
            return -1;
        }
    }
}
