/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface FecetDocTransferenciaDao {

    int insert(FecetDocTransferencia docTransferencia);

    List<FecetDocTransferencia> getDocTransferenciaXPropuestaTransferencia(Long idPropuesta, Long idTrnsferencia);

    List<FecetDocTransferencia> obtenerDocumentosByIdTransferencia(BigDecimal idTransferencia, BigDecimal blnEstatus);

    List<FecetDocTransferencia> obtenerDocumentosAllByIdTransferencia(BigDecimal idTransferencia);
}
