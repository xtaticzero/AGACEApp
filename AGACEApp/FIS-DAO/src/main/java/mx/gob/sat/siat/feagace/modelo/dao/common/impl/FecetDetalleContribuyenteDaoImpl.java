package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetDetalleContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyentePk;

import org.springframework.stereotype.Repository;

@Repository("fecetDetalleContribuyenteDao")
public class FecetDetalleContribuyenteDaoImpl extends BaseJDBCDao<FecetDetalleContribuyente> implements FecetDetalleContribuyenteDao {

    private static final long serialVersionUID = -5741811368853495328L;

    private static final String SQL_SELECT
            = "SELECT ID_DETALLE_CONTRIBUYENTE, RFC_CONTRIBUYENTE, FECHA_CONSULTA, AMPARADO, PPEE FROM ";

    @Override
    public BigDecimal getIdConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DETALLE_CONTRIBUYENTE.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }

    @Override
    public FecetDetalleContribuyente findByRfc(String rfc) {

        StringBuilder query = new StringBuilder(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC_CONTRIBUYENTE = ?");
        List<FecetDetalleContribuyente> list
                = getJdbcTemplateBase().query(query.toString(),
                        new FecetDetalleContribuyenteMapper(), rfc);
        return list.size() == 0 ? null : list.get(0);

    }

    @Override
    public void update(FecetDetalleContribuyente dto) {
        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET FECHA_CONSULTA = ?, AMPARADO = ?, PPEE = ? WHERE RFC_CONTRIBUYENTE = ?",
                new Date(), dto.getAmparado(), dto.getPpee(), dto.getRfcContribuyente());
    }

    @Override
    public FecetDetalleContribuyentePk insert(FecetDetalleContribuyente dto) {

        if (dto.getIdDetalleContribuyente() == null) {
            dto.setIdDetalleContribuyente(getIdConsecutivo());
        }
        getJdbcTemplateBase().update("INSERT INTO " + getTableName()
                + " ( ID_DETALLE_CONTRIBUYENTE, RFC_CONTRIBUYENTE, FECHA_CONSULTA, AMPARADO, PPEE) VALUES ( ?, ?, ?, ?, ? )",
                dto.getIdDetalleContribuyente(), dto.getRfcContribuyente(), new Date(),
                dto.getAmparado(), dto.getPpee());
        return dto.createPk();
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DETALLE_CONTRIBUYENTE";
    }
}
