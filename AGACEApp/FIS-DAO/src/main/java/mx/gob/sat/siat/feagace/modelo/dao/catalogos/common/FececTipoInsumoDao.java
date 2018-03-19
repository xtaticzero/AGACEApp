/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;

/**
 *
 * @author jose.aguilar
 */
public interface FececTipoInsumoDao {

    List<FececTipoInsumo> findActivos(BigDecimal idGeneral);

}
