/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface FecetDocRetroPropuestaDao {

    int insert(DocRetroalimentacionDTO docRetro);

    List<DocRetroalimentacionDTO> obtenerDocumentoByIdRetro(BigDecimal idRetro);
}
