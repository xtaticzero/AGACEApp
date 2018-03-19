package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetReasignacionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.ConsultaReasignacionInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetReasignacionInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumoPk;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("fecetReasignacionInsumoDao")
public class FecetReasignacionInsumoDaoImpl extends BaseJDBCDao<FecetReasignacionInsumo>
        implements FecetReasignacionInsumoDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -7625880621403467190L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetReasignacionInsumoPk
     */
    @Transactional
    public FecetReasignacionInsumoPk insert(FecetReasignacionInsumo dto) {

        if (dto.getIdReasignacion() == null) {

            dto.setIdReasignacion(getJdbcTemplateBase()
                    .queryForObject("SELECT FECEQ_REASIGNACION_INSUMO.NEXTVAL FROM DUAL", BigDecimal.class));

        }

        getJdbcTemplateBase().update(
                "INSERT INTO " + getTableName()
                + " ( ID_REASIGNACION, ID_INSUMO, RFC_ADMINISTRADOR_ORIGEN, RFC_ADMINISTRADOR_DESTINO, MOTIVO, MOTIVO_RECHAZO, ESTATUS, BLN_ACTIVO ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )",
                dto.getIdReasignacion(), dto.getIdInsumo(), dto.getRfcAdministradorOrigen(),
                dto.getRfcAdministradorDestino(), dto.getMotivo(), dto.getMotivoRechazo(), dto.getEstatus(),
                dto.getBlnActivo());
        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_REASIGNACION_INSUMO table.
     */
    public void update(FecetReasignacionInsumoPk pk, FecetReasignacionInsumo dto) {
        getJdbcTemplateBase().update(
                "UPDATE " + getTableName()
                + " SET ID_REASIGNACION = ?, ID_INSUMO = ?, RFC_ADMINISTRADOR_ORIGEN = ?, RFC_ADMINISTRADOR_DESTINO = ?, MOTIVO = ?, MOTIVO_RECHAZO = ?, ESTATUS = ? WHERE ID_REASIGNACION = ?",
                dto.getIdReasignacion(), dto.getIdInsumo(), dto.getRfcAdministradorOrigen(),
                dto.getRfcAdministradorDestino(), dto.getMotivo(), dto.getMotivoRechazo(), dto.getEstatus(),
                pk.getIdReasignacion());
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "FECET_REASIGNACION_INSUMO";
    }

    @Override
    public List<FecetReasignacionInsumo> findByAdministradorEstatus(String administradorDestino, BigDecimal estatus) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT RI.*, INS.ID_REGISTRO ID_REGISTRO_INSUMO\n"
                + "FROM FECET_REASIGNACION_INSUMO RI, FECET_INSUMO INS\n");
        query.append(" WHERE INS.ID_INSUMO = RI.ID_INSUMO");
        query.append(" AND RI.ESTATUS = ?");
        query.append(" AND RI.RFC_ADMINISTRADOR_DESTINO = ?");
        List<FecetReasignacionInsumo> list = getJdbcTemplateBase().query(query.toString(),
                new FecetReasignacionInsumoMapper(), estatus, administradorDestino);
        return list;

    }

    @Override
    public List<FecetInsumo> findReasingacionByAdministradorEstatus(String administradorDestino, BigDecimal estatus) {

        StringBuilder query = new StringBuilder();
        query.append(
                " SELECT FECET_INSUMO.*, FECEC_SUBPROGRAMA.*, FECET_CONTRIBUYENTE.*, FECEC_SECTOR.*, FECET_REASIGNACION_INSUMO.*, FECEC_SECTOR.DESCRIPCION SECTOR_DESCRIPCION \n");
        query.append(
                " FROM FECET_INSUMO, FECEC_SUBPROGRAMA, FECET_CONTRIBUYENTE, FECEC_SECTOR, FECET_REASIGNACION_INSUMO \n");
        query.append(" WHERE FECEC_SUBPROGRAMA.ID_SUBPROGRAMA = FECET_INSUMO.ID_SUBPROGRAMA AND \n");
        query.append(" FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE = FECET_INSUMO.ID_CONTRIBUYENTE AND \n");
        query.append(" FECEC_SECTOR.ID_SECTOR = FECET_INSUMO.ID_SECTOR AND \n");
        query.append(" FECET_REASIGNACION_INSUMO.ID_INSUMO = FECET_INSUMO.ID_INSUMO AND \n");
        query.append(
                " FECET_REASIGNACION_INSUMO.ESTATUS = ? AND FECET_REASIGNACION_INSUMO.RFC_ADMINISTRADOR_DESTINO = ?  AND \n");
        query.append(" FECET_INSUMO.RFC_SUBADMINISTRADOR IS NULL \n");
        query.append(" ORDER BY FECET_INSUMO.PRIORIDAD DESC, FECET_INSUMO.FECHA_CREACION ");
        List<FecetInsumo> list = getJdbcTemplateBase().query(query.toString(), new ConsultaReasignacionInsumoMapper(),
                estatus, administradorDestino);
        return list;

    }

    @Override
    public BigDecimal update(FecetReasignacionInsumo dto) {
        return getJdbcTemplateBase().update(
                "UPDATE " + getTableName()
                + " SET ID_REASIGNACION = ?, ID_INSUMO = ?, RFC_ADMINISTRADOR_ORIGEN = ?, RFC_ADMINISTRADOR_DESTINO = ?, MOTIVO = ?, MOTIVO_RECHAZO = ?, ESTATUS = ?, BLN_ACTIVO = ? WHERE ID_REASIGNACION = ?",
                dto.getIdReasignacion(), dto.getIdInsumo(), dto.getRfcAdministradorOrigen(),
                dto.getRfcAdministradorDestino(), dto.getMotivo(), dto.getMotivoRechazo(), dto.getEstatus(),
                dto.getBlnActivo(), dto.getIdReasignacion()) <= 0 ? Constantes.BIG_DECIMAL_CERO : dto.getIdInsumo();
    }

    @Override
    public List<FecetReasignacionInsumo> findByIdMetodo(BigDecimal idInsumo) {
        StringBuilder query = new StringBuilder();
        query.append(
                "SELECT ID_REASIGNACION, ID_INSUMO,  RFC_ADMINISTRADOR_ORIGEN, RFC_ADMINISTRADOR_DESTINO, MOTIVO, MOTIVO_RECHAZO, ESTATUS, BLN_ACTIVO \n");
        query.append("FROM FECET_REASIGNACION_INSUMO \n");
        query.append("WHERE ID_INSUMO = ? AND BLN_ACTIVO = 1 \n");

        return getJdbcTemplateBase().query(query.toString(), new FecetReasignacionInsumoMapper(), idInsumo);
    }

}
