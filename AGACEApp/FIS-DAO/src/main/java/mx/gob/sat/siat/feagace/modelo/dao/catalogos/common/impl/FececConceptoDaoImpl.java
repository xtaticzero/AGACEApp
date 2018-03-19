package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececConceptoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececConceptoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;

@Repository("fececConceptoDao")
public class FececConceptoDaoImpl extends BaseJDBCDao<FececConcepto> implements FececConceptoDao {

    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = "SELECT ID_CONCEPTO, DESCRIPCION, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO FROM " + getTableName();
    private static final String SQL_SELECT_CONCEPTO_IMPUESTO = "SELECT con.ID_CONCEPTO, con.DESCRIPCION, con.FECHA_INICIO, con.FECHA_FIN, con.BLN_ACTIVO FROM " + getTableName() + " con";

    public static String getTableName() {
        return "FECEC_CONCEPTOS";
    }

    @Override
    public List<FececConcepto> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_CONCEPTO ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececConceptoMapper());
    }

    @Override
    public int insert(FececConcepto dto) {

        dto.setIdConcepto(getJdbcTemplateBase().queryForObject("SELECT FECEQ_CONCEPTOS.NEXTVAL FROM DUAL",
                BigDecimal.class).intValue());

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_CONCEPTO, DESCRIPCION, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO ) VALUES ( ?, ?, ?, ?, ? )");

        return getJdbcTemplateBase().update(query.toString(), dto.getIdConcepto(), dto.getDescripcion(),
                dto.getFechaInicio(), dto.getFechaFin(), dto.getBlnActivo());
    }

    @Override
    public List<FececConcepto> findByIdTipoImpuesto(BigDecimal idTipoImpuesto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_CONCEPTO_IMPUESTO);
        query.append(" INNER JOIN FECEA_IMPUESTO_CONCEPTO fcp ON (fcp.ID_CONCEPTO = con.ID_CONCEPTO) \n");
        query.append(" WHERE fcp.ID_TIPO_IMPUESTO = ").append(idTipoImpuesto).append(" ORDER BY con.ID_CONCEPTO ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececConceptoMapper());
    }

    @Override
    public List<FececConcepto> findByTipoImpuestoName(String impuesto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_CONCEPTO_IMPUESTO);
        query.append(" INNER JOIN FECEA_IMPUESTO_CONCEPTO fic ON (fic.ID_CONCEPTO = con.ID_CONCEPTO) \n");
        query.append(" INNER JOIN FECEC_TIPO_IMPUESTO fti ON (fti.ID_TIPO_IMPUESTO = fic.ID_TIPO_IMPUESTO) \n");
        query.append(" WHERE fti.ABREVIATURA = '").append(impuesto).append("'");

        return getJdbcTemplateBase().query(query.toString(), new FececConceptoMapper());
    }

    @Override
    public List<FececConcepto> findByNombreConcepto(String concepto) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = '").append(concepto).append("'");

        return getJdbcTemplateBase().query(query.toString(), new FececConceptoMapper());
    }

    @Override
    public List<FececConcepto> findRelacionImpuestoConcepto(BigDecimal idImpuesto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_CONCEPTO_IMPUESTO);
        query.append(" INNER JOIN FECEA_IMPUESTO_CONCEPTO fic ON (fic.ID_CONCEPTO = con.ID_CONCEPTO) \n");
        query.append(" INNER JOIN FECEC_TIPO_IMPUESTO fti ON (fti.ID_TIPO_IMPUESTO = fic.ID_TIPO_IMPUESTO) \n");
        query.append(" WHERE fti.ID_TIPO_IMPUESTO = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FececConceptoMapper(), idImpuesto);
    }

}
