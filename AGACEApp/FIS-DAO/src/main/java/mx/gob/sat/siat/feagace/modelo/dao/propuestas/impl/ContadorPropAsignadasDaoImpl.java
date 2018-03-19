package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ContadorPropAsignadasDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.ContadorPropAsignadasMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ContadorPropuestasAsignadas;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("contadorPropAsignadasDao")
public class ContadorPropAsignadasDaoImpl extends BaseJDBCDao<ContadorPropuestasAsignadas>
        implements ContadorPropAsignadasDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String getIdUnidadesAdministrativas() {
        StringBuilder sql = new StringBuilder();

        sql.append(Constantes.ACOECE).append(",");
        sql.append(Constantes.ACAOCE).append(",");
        sql.append(Constantes.ARACE_PACIFICO_NORTE).append(",");
        sql.append(Constantes.ARACE_NORTE_CENTRO).append(",");
        sql.append(Constantes.ARACE_NORESTE).append(",");
        sql.append(Constantes.ARACE_OCCIDENTE).append(",");
        sql.append(Constantes.ARACE_CENTRO).append(",");
        sql.append(Constantes.ARACE_SUR);

        return sql.toString();
    }

    private String getSqlResumenPropuestas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 69 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_ASIGNADAS_FIRMANTE, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS IN (74, 146) AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas())
                .append(") AND PRO.RFC_AUDITOR = ?) TOTAL_RETROALIMENTADAS_ATENDER, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 61 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_FIRMANTE_ATENDER, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 118 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_EMISION_ATENDER, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 147 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_CANCELADAS_ATENDER, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS IN (42) AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_TRANSFERIDAS_ATENDER, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 54 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_RECHAZADAS_ATENDER, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 145 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_RETRO_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 57 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_FIRMANTE_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS IN (51, 71) AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_EMISION_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 143 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_CANCELADAS_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 120 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas())
                .append(") AND PRO.RFC_AUDITOR = ?) TOTAL_TRANSFERIDAS_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 62 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas()).append(") AND PRO.RFC_AUDITOR = ?) TOTAL_RECHAZADAS_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO WHERE PRO.ID_ESTATUS = 72 AND PRO.ID_ARACE IN (");
        sql.append(getIdUnidadesAdministrativas())
                .append(") AND PRO.RFC_AUDITOR = ?) TOTAL_PROGRAMACION_VALIDACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO INNER JOIN FECEA_PROPUESTA_ACCION FPA ON FPA.ID_PROPUESTA = PRO.ID_PROPUESTA \n");
        sql.append("WHERE PRO.ID_ESTATUS = 144 AND PRO.ID_ARACE IN (").append(getIdUnidadesAdministrativas());
        sql.append(") AND FPA.ID_ACCION = 15 AND PRO.RFC_AUDITOR = ?) TOTAL_CONCLUIDAS_CANCELACION, \n");
        sql.append(
                "(SELECT COUNT(PRO.ID_ESTATUS) FROM FECET_PROPUESTA PRO INNER JOIN FECEA_PROPUESTA_ACCION FPA ON FPA.ID_PROPUESTA = PRO.ID_PROPUESTA \n");
        sql.append("WHERE PRO.ID_ESTATUS = 144 AND PRO.ID_ARACE IN (").append(getIdUnidadesAdministrativas());
        sql.append(") AND FPA.ID_ACCION = 19 AND PRO.RFC_AUDITOR = ?) TOTAL_CONCLUIDAS_RECHAZO \n");
        sql.append("FROM DUAL");

        return sql.toString();
    }

    @Override
    public List<ContadorPropuestasAsignadas> getResumenPropuestasAsignadas(String rfcAuditor) {

        Object[] params = new Object[Constantes.ENTERO_DIECISEIS];

        for (int i = 0; i < params.length; i++) {
            params[i] = rfcAuditor;
        }

        return getJdbcTemplateBase().query(getSqlResumenPropuestas(), params, new ContadorPropAsignadasMapper());
    }

}
