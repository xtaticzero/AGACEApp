package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.AsociadoFuncionarioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.AsociadoFuncionarioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;

@Repository("asociadoFuncionarioDao")
public class AsociadoFuncionarioDaoImpl extends BaseJDBCDao<AsociadoFuncionarioDTO> implements AsociadoFuncionarioDao {

    private static final long serialVersionUID = 1L;

    @Override
    public void insertar(AsociadoFuncionarioDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO FECET_ASOCIADOS_FUNCIONARIOS (ID_ASOCIADO,ID_PROPUESTA,ID_TIPO_EMPLEADO,ID_ORDEN,ID_EMPLEADO,BLN_ACTIVO) VALUES (FECEQ_ASOCIADOS_FUNCIONARIOS.NEXTVAL,?,?,?,?,?)");
        getJdbcTemplateBase().update(sql.toString(), dto.getIdPropuesta(),
                dto.getIdTipoEmpleado(), dto.getIdOrden(), dto.getIdEmpleado(), dto.getBlnActivo());
    }

    @Override
    public void update(AsociadoFuncionarioDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE FECET_ASOCIADOS_FUNCIONARIOS SET ID_PROPUESTA = ?, ID_TIPO_EMPLEADO = ?, ID_ORDEN = ?, ID_EMPLEADO = ?, BLN_ACTIVO = ? WHERE ID_ASOCIADO = ?");
        getJdbcTemplateBase().update(sql.toString(), dto.getIdPropuesta(),
                dto.getIdTipoEmpleado(), dto.getIdOrden(), dto.getIdEmpleado(), dto.getBlnActivo(), dto.getIdAsociado());
    }

    @Override
    public List<AsociadoFuncionarioDTO> obtenerAsociadoActivoByIdPropuesta(AsociadoFuncionarioDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_ASOCIADO,ID_PROPUESTA,ID_TIPO_EMPLEADO,ID_ORDEN,ID_EMPLEADO,BLN_ACTIVO \n");
        sql.append("FROM FECET_ASOCIADOS_FUNCIONARIOS \n");
        sql.append("WHERE ID_PROPUESTA = ? AND ID_TIPO_EMPLEADO = ? AND BLN_ACTIVO = 1 \n");

        return getJdbcTemplateBase().query(sql.toString(), new AsociadoFuncionarioMapper(), dto.getIdPropuesta(), dto.getIdTipoEmpleado());

    }

    @Override
    public List<AsociadoFuncionarioDTO> obtenerAsociadoActivoByIdOrden(AsociadoFuncionarioDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_ASOCIADO,ID_PROPUESTA,ID_TIPO_EMPLEADO,ID_ORDEN,ID_EMPLEADO,BLN_ACTIVO \n");
        sql.append("FROM FECET_ASOCIADOS_FUNCIONARIOS \n");
        sql.append("WHERE ID_ORDEN = ? AND ID_TIPO_EMPLEADO = ? AND BLN_ACTIVO = 1 \n");

        return getJdbcTemplateBase().query(sql.toString(), new AsociadoFuncionarioMapper(), dto.getIdOrden(), dto.getIdTipoEmpleado());

    }
}
