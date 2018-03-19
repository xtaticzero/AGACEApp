package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.ReportesDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.ReporteEjecutivoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.ReporteGerencialMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;

import org.springframework.jdbc.core.JdbcTemplate;

public class ReportesDaoImpl implements ReportesDao {

    private JdbcTemplate jdbcTemplate;

    private StringBuilder getSqlConsultaGerencial() {
        StringBuilder sqlConsultaGerencial = new StringBuilder();

        sqlConsultaGerencial.append("SELECT FECET_ORDEN.CVE_ORDEN , FECET_ORDEN.CVE_REGISTRO , FECEC_METODO.NOMBRE METODO, FECET_ORDEN.RFC_CONTRIBUYENTE  \n");
        sqlConsultaGerencial.append("     , FECET_ORDEN.NOMBRE_CONTRIBUYENTE , FECEC_REP_LEGAL.RFC RFC_REP_LEGAL , FECEC_REP_LEGAL.NOMBRE NOMBRE_REP_LEGAL\n");
        sqlConsultaGerencial.append("     , FECEC_APOD_LEGAL.RFC RFC_APOD_LEGAL, FECEC_APOD_LEGAL.NOMBRE NOMBRE_APOD_LEGAL, FECEC_AGTE_ADUANAL.RFC RFC_AGTE_ADUANAL\n");
        sqlConsultaGerencial.append("     , FECEC_AGTE_ADUANAL.NOMBRE NOMBRE_AGTE_ADUANAL , FECEC_UNIDAD_ADMINISTRATIVA.DESCRIPCION AREA , FECEC_AUDITOR.NOMBRE NOMBRE_AUDITOR, FECET_ORDEN.ID_ESTATUS\n");
        sqlConsultaGerencial.append("     , FECET_ORDEN.ID_REQUERIMIENTO , FECET_ORDEN.ID_AMPL_PLAZO , FECET_ORDEN.ID_CARTA_INV , FECET_ORDEN.ID_COMPLEMENT\n");
        sqlConsultaGerencial.append("     , FECET_ORDEN.ID_OBSERV , FECET_ORDEN.ID_CONCLUSION, FECET_ORDEN.FECHA_CARGA , FECET_ORDEN.FECHA_NOTIF_NYV , FECET_ORDEN.FECHA_NOTIF_CONT\n");
        sqlConsultaGerencial.append("     , FECET_ORDEN.ID_ORDEN\n");
        sqlConsultaGerencial.append("FROM FECET_ORDEN\n");
        sqlConsultaGerencial.append("LEFT JOIN FECEC_METODO ON FECEC_METODO.ID_METODO = FECET_ORDEN.ID_METODO\n");
        sqlConsultaGerencial.append("LEFT JOIN FECEC_REP_LEGAL ON FECEC_REP_LEGAL.ID_REP_LEGAL = FECET_ORDEN.ID_REP_LEGAL\n");
        sqlConsultaGerencial.append("LEFT JOIN FECEC_APOD_LEGAL ON FECEC_APOD_LEGAL.RFC_CONTRIBUYENTE = FECET_ORDEN.RFC_CONTRIBUYENTE\n");
        sqlConsultaGerencial.append("LEFT JOIN FECEC_AGTE_ADUANAL ON FECEC_AGTE_ADUANAL.ID_AGTE_ADUANAL = FECET_ORDEN.ID_AGTE_ADUANAL\n");
        sqlConsultaGerencial.append("LEFT JOIN FECEC_AUDITOR ON FECEC_AUDITOR.RFC = FECET_ORDEN.RFC_AUDITOR\n");
        sqlConsultaGerencial.append("LEFT JOIN FECEC_UNIDAD_ADMINISTRATIVA ON FECEC_AUDITOR.ID_ARACE = FECEC_UNIDAD_ADMINISTRATIVA.ID_UNIDAD_ADMINISTRATIVA\n");
        sqlConsultaGerencial.append("WHERE FECET_ORDEN.ID_METODO = ?\n");
        sqlConsultaGerencial.append("  AND FECET_ORDEN.ID_ESTATUS = ?\n");
        sqlConsultaGerencial.append("  AND FECEC_UNIDAD_ADMINISTRATIVA.ID_UNIDAD_ADMINISTRATIVA = ?");

        return sqlConsultaGerencial;
    }

    private StringBuilder getSqlConsultaGerencialOrden() {
        StringBuilder sqlConsultaGerencialOrden = new StringBuilder();

        sqlConsultaGerencialOrden.append("SELECT FECET_ORDEN.CVE_ORDEN , FECET_ORDEN.CVE_REGISTRO , FECEC_METODO.NOMBRE METODO, FECET_ORDEN.RFC_CONTRIBUYENTE  \n");
        sqlConsultaGerencialOrden.append("     , FECET_ORDEN.NOMBRE_CONTRIBUYENTE , FECEC_REP_LEGAL.RFC RFC_REP_LEGAL , FECEC_REP_LEGAL.NOMBRE NOMBRE_REP_LEGAL\n");
        sqlConsultaGerencialOrden.append("     , FECEC_APOD_LEGAL.RFC RFC_APOD_LEGAL, FECEC_APOD_LEGAL.NOMBRE NOMBRE_APOD_LEGAL, FECEC_AGTE_ADUANAL.RFC RFC_AGTE_ADUANAL\n");
        sqlConsultaGerencialOrden.append("     , FECEC_AGTE_ADUANAL.NOMBRE NOMBRE_AGTE_ADUANAL , FECEC_UNIDAD_ADMINISTRATIVA.DESCRIPCION AREA , FECEC_AUDITOR.NOMBRE NOMBRE_AUDITOR, FECET_ORDEN.ESTADO\n");
        sqlConsultaGerencialOrden.append("     , FECET_ORDEN.ID_REQUERIMIENTO , FECET_ORDEN.ID_AMPL_PLAZO , FECET_ORDEN.ID_CARTA_INV , FECET_ORDEN.ID_COMPLEMENT\n");
        sqlConsultaGerencialOrden.append("     , FECET_ORDEN.ID_OBSERV , FECET_ORDEN.ID_CONCLUSION, FECET_ORDEN.FECHA_CARGA , FECET_ORDEN.FECHA_NOTIF_NYV , FECET_ORDEN.FECHA_NOTIF_CONT\n");
        sqlConsultaGerencialOrden.append("     , FECET_ORDEN.ID_ORDEN\n");
        sqlConsultaGerencialOrden.append("FROM FECET_ORDEN\n");
        sqlConsultaGerencialOrden.append("LEFT JOIN FECEC_METODO ON FECEC_METODO.ID_METODO = FECET_ORDEN.ID_METODO\n");
        sqlConsultaGerencialOrden.append("LEFT JOIN FECEC_REP_LEGAL ON FECEC_REP_LEGAL.ID_REP_LEGAL = FECET_ORDEN.ID_REP_LEGAL\n");
        sqlConsultaGerencialOrden.append("LEFT JOIN FECEC_APOD_LEGAL ON FECEC_APOD_LEGAL.RFC_CONTRIBUYENTE = FECET_ORDEN.RFC_CONTRIBUYENTE\n");
        sqlConsultaGerencialOrden.append("LEFT JOIN FECEC_AGTE_ADUANAL ON FECEC_AGTE_ADUANAL.ID_AGTE_ADUANAL = FECET_ORDEN.ID_AGTE_ADUANAL\n");
        sqlConsultaGerencialOrden.append("LEFT JOIN FECEC_AUDITOR ON FECEC_AUDITOR.RFC = FECET_ORDEN.RFC_AUDITOR\n");
        sqlConsultaGerencialOrden.append("LEFT JOIN FECEC_UNIDAD_ADMINISTRATIVA ON FECEC_AUDITOR.ID_ARACE = FECEC_UNIDAD_ADMINISTRATIVA.ID_UNIDAD_ADMINISTRATIVA\n");
        sqlConsultaGerencialOrden.append("WHERE FECET_ORDEN.CVE_ORDEN = ?\n");

        return sqlConsultaGerencialOrden;
    }

    private StringBuilder getSqlConsultaEjecutivoFechas() {
        StringBuilder sqlConsultaEjecutivoFechas = new StringBuilder();

        sqlConsultaEjecutivoFechas.append("SELECT FECET_ORDEN.ID_METODO, FECEC_METODO.NOMBRE METODO, COUNT(*) NUMERO_ORDENES from FECET_ORDEN\n");
        sqlConsultaEjecutivoFechas.append("LEFT JOIN FECEC_AUDITOR ON FECET_ORDEN.RFC_AUDITOR = FECEC_AUDITOR.RFC\n");
        sqlConsultaEjecutivoFechas.append("LEFT JOIN FECEC_UNIDAD_ADMINISTRATIVA ON FECEC_AUDITOR.ID_ARACE = FECEC_UNIDAD_ADMINISTRATIVA.ID_UNIDAD_ADMINISTRATIVA\n");
        sqlConsultaEjecutivoFechas.append("LEFT JOIN FECEC_METODO ON FECET_ORDEN.ID_METODO = FECEC_METODO.ID_METODO\n");
        sqlConsultaEjecutivoFechas.append("WHERE FECHA_CARGA BETWEEN ? AND ?\n");

        return sqlConsultaEjecutivoFechas;

    }

    private static final StringBuilder WHERE_CONSULTA_EJECUTIVO_METODO
            = new StringBuilder(" AND FECEC_METODO.ID_METODO = ?\n");

    private static final StringBuilder WHERE_CONSULTA_EJECUTIVO_AREA
            = new StringBuilder(" AND FECEC_UNIDAD_ADMINSTRATVA.ID_UNIDAD_ADMINISTRATIVA = ?\n");

    private static final StringBuilder WHERE_CONSULTA_EJECUTIVO_ESTADO
            = new StringBuilder(" AND FECET_ORDEN.ESTADO = ?\n");

    private static final StringBuilder SQL_CONSULTA_EJECUTIVO_FECHAS_FIN
            = new StringBuilder("GROUP BY FECET_ORDEN.ID_METODO, FECEC_METODO.NOMBRE, FECEC_UNIDAD_ADMINSTRATVA.DESCRIPCION as NOMBRE, FECET_ORDEN.ESTADO\n"
                    + "ORDER BY FECET_ORDEN.ID_METODO");

    @Override
    public List<ReporteEjecutivoDTO> consultaReporteEjecutivo(Date rangoInicio, Date rangoFin, BigDecimal idMetodo,
            BigDecimal idArea,
            BigDecimal idEstatus) {

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        List<Object> lista = new ArrayList<Object>();
        lista.add(rangoInicio);
        lista.add(rangoFin);
        StringBuilder sql = new StringBuilder();
        sql.append(getSqlConsultaEjecutivoFechas());
        if (idMetodo.intValue() != -1) {
            sql = sql.append(WHERE_CONSULTA_EJECUTIVO_METODO);
            lista.add(idMetodo);
        }
        if (idArea.intValue() != -1) {
            sql = sql.append(WHERE_CONSULTA_EJECUTIVO_AREA);
            lista.add(idArea);
        }
        if (idEstatus.intValue() != -1) {
            sql = sql.append(WHERE_CONSULTA_EJECUTIVO_ESTADO);
            lista.add(idEstatus);
        }
        sql = sql.append(SQL_CONSULTA_EJECUTIVO_FECHAS_FIN);
        return jdbcTemplate.query(sql.toString(), new ReporteEjecutivoMapper(), lista.toArray());

    }

    @Override
    public List<ReporteGerencialDTO> consultaReporteGerencial(BigDecimal metodo, BigDecimal status,
            BigDecimal area) {

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate.query(getSqlConsultaGerencial().toString(),
                new ReporteGerencialMapper(), metodo, status, area);

    }

    @Override
    public List<ReporteGerencialDTO> consultaReporteGerencialOrden(String cveOrden) {

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate.query(getSqlConsultaGerencialOrden().toString(), new ReporteGerencialMapper(), cveOrden);

    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
