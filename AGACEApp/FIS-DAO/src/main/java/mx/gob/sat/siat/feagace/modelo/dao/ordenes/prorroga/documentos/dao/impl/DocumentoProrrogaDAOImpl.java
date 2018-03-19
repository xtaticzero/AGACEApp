package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.documentos.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetDocProrrogaOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.documentos.dao.DocumentoProrrogaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrdenPk;

import org.springframework.stereotype.Repository;

@Repository("documentoProrrogaDAO")
public class DocumentoProrrogaDAOImpl extends BaseJDBCDao<FecetDocProrrogaOrden> implements DocumentoProrrogaDAO {

    private static final long serialVersionUID = 456765456567888991L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_DOC_PRORROGA_ORDEN, ID_PRORROGA_ORDEN, NOMBRE_ARCHIVO, RUTA_ARCHIVO FROM ");

    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_ANEXOS_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    private static final StringBuilder SQL_DELETE = new StringBuilder("DELETE FROM ");

    public FecetAnexosProrrogaOrden insert(FecetAnexosProrrogaOrden dto) {

        if (dto.getIdAnexosProrrogaOrden() == null) {
            dto.setIdAnexosProrrogaOrden(getIdFecetDocProrrogaOrdenPathDirectorio());
        }

        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_ANEXOS_PRORROGA_ORDEN, ID_FLUJO_PRORROGA_ORDEN, NOMBRE_ARCHIVO, RUTA_ARCHIVO, FECHA_CREACION, TIPO_ANEXO ) VALUES (?, ?, ?, ?, ?, ?)");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexosProrrogaOrden(), dto.getIdFlujoProrrogaOrden(),
                dto.getNombreArchivo(), dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getTipoAnexo());
        return dto;

    }

    public void update(FecetDocProrrogaOrdenPk pk, FecetDocProrrogaOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_DOC_PRORROGA_ORDEN = ?, ").append(
                "ID_PRORROGA_ORDEN = ?, NOMBRE_ARCHIVO = ?, RUTA_ARCHIVO = ? WHERE ID_DOC_PRORROGA_ORDEN = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocProrrogaOrden(), dto.getIdProrrogaOrden(),
                dto.getNombreArchivo(), dto.getRutaArchivo(), pk.getIdDocProrrogaOrden());

    }

    public void delete(FecetDocProrrogaOrdenPk pk) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_DELETE).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdDocProrrogaOrden());

    }

    public BigDecimal getIdFecetDocProrrogaOrdenPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    public static String getTableName() {
        return "FECET_ANEXOS_PRORROGA_ORDEN";
    }

    public FecetDocProrrogaOrden findByPrimaryKey(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_ORDEN = ?");
        List<FecetDocProrrogaOrden> list = getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idProrroga);
        return list.size() == 0 ? null : list.get(0);

    }

    public List<FecetDocProrrogaOrden> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_DOC_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper());

    }

    public List<FecetDocProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ? ORDER BY ID_DOC_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idProrroga);

    }

    public List<FecetDocProrrogaOrden> findWhereIdDocProrrogaOrdenEquals(final BigDecimal idDocProrrogaOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_ORDEN = ? ORDER BY ID_DOC_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idDocProrrogaOrden);

    }

    public List<FecetDocProrrogaOrden> findWhereIdProrrogaOrdenEquals(final BigDecimal idProrrogaOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ? ORDER BY ID_PRORROGA_ORDEN");
        logger.debug("Obtener documentacion contribuyente");
        logger.debug(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idProrrogaOrden);

    }

    public List<FecetDocProrrogaOrden> findWhereNombreAcuseEquals(final String nombreArchivo) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE NOMBRE_ARCHIVO = ? ORDER BY NOMBRE_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), nombreArchivo);

    }

    public List<FecetDocProrrogaOrden> findWhereRutaAcuseEquals(final String rutaArchivo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), rutaArchivo);

    }

    public FecetDocProrrogaOrden findByPrimaryKey(final FecetDocProrrogaOrdenPk pk) {
        return findByPrimaryKey(pk.getIdDocProrrogaOrden());
    }

}
