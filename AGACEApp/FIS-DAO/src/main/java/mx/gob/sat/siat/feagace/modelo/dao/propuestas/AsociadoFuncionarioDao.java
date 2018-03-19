package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;

public interface AsociadoFuncionarioDao {

    void insertar(AsociadoFuncionarioDTO dto);

    void update(AsociadoFuncionarioDTO dto);

    List<AsociadoFuncionarioDTO> obtenerAsociadoActivoByIdPropuesta(AsociadoFuncionarioDTO dto);

    List<AsociadoFuncionarioDTO> obtenerAsociadoActivoByIdOrden(AsociadoFuncionarioDTO dto);
}
