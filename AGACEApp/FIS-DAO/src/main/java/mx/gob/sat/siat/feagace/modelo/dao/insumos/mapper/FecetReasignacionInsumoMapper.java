package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetReasignacionInsumoMapper implements ParameterizedRowMapper<FecetReasignacionInsumo> {

    private static final String COLUMN_ID_REASIGNACION = "ID_REASIGNACION";
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";
    private static final String COLUMN_ID_REGISTRO_INSUMO = "ID_REGISTRO_INSUMO";
    private static final String COLUMN_RFC_ADMINISTRADOR_ORIGEN = "RFC_ADMINISTRADOR_ORIGEN";
    private static final String COLUMN_RFC_ADMINISTRADOR_DESTINO = "RFC_ADMINISTRADOR_DESTINO";
    private static final String COLUMN_MOTIVO = "MOTIVO";
    private static final String COLUMN_MOTIVO_RECHAZO = "MOTIVO_RECHAZO";
    private static final String COLUMN_ESTATUS = "ESTATUS";

    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public FecetReasignacionInsumo mapRow(ResultSet resultSet, int i) throws SQLException {
        FecetReasignacionInsumo fecetReasignacionInsumo = new FecetReasignacionInsumo();
        fecetReasignacionInsumo.setEstatus(resultSet.getBigDecimal(COLUMN_ESTATUS));
        fecetReasignacionInsumo.setIdInsumo(resultSet.getBigDecimal(COLUMN_ID_INSUMO));
        fecetReasignacionInsumo.setIdReasignacion(resultSet.getBigDecimal(COLUMN_ID_REASIGNACION));
        fecetReasignacionInsumo.setMotivo(resultSet.getString(COLUMN_MOTIVO));
        fecetReasignacionInsumo.setMotivoRechazo(resultSet.getString(COLUMN_MOTIVO_RECHAZO));
        fecetReasignacionInsumo.setRfcAdministradorDestino(resultSet.getString(COLUMN_RFC_ADMINISTRADOR_DESTINO));
        fecetReasignacionInsumo.setRfcAdministradorOrigen(resultSet.getString(COLUMN_RFC_ADMINISTRADOR_ORIGEN));
        fecetReasignacionInsumo.setBlnActivo(resultSet.getInt(COLUMN_BLN_ACTIVO));

        if (UtileriasMapperDao.existeColumna(resultSet, COLUMN_ID_REGISTRO_INSUMO)) {
            fecetReasignacionInsumo.setIdRegistroInsumo(resultSet.getString(COLUMN_ID_REGISTRO_INSUMO));
        }

        return fecetReasignacionInsumo;
    }
}
