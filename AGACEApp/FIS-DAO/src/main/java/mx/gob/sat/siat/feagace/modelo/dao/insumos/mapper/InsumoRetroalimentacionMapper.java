/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.PrioridadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RetroalimentacionInsumoDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class InsumoRetroalimentacionMapper implements RowMapper<RetroalimentacionInsumoDTO> {

    private static final String RETRO_INSUMO_ID_RETRO = "ID_RETRO";
    private static final String RETRO_INSUMO_ID_INSUMO = "ID_INSUMO";
    private static final String RETRO_INSUMO_ID_MOTIVO = "ID_MOTIVO";
    private static final String RETRO_INSUMO_MOTIVO_SUBADMINISTRADOR = "MOTIVO_SUBADMINISTRADOR";
    private static final String RETRO_INSUMO_FECHA_CREACION = "RET_FECHA_CREACION";
    private static final String RETRO_INSUMO_RFC_RETROALIMENTACION = "RET_RFC";
    private static final String RETRO_INSUMO_FECHA_RETROALIMENTACION = "RET_FECHA_RETRO";
    private static final String RETRO_INSUMO_ESTATUS = "RET_ESTATUS";
    private static final String RETRO_INSUMO_RFC_RECHAZO = "RET_RFC_RECHAZO";
    private static final String RETRO_INSUMO_FECHA_RECHAZO = "RET_FECHA_RECHAZO";
    private static final String RETRO_INSUMO_DESCRIPCION_RECHAZO = "RET_DESC_RECHAZO";
    private static final String RETRO_INSUMO_ID_UNIDAD_ADMINISTRATIVA = "RET_ID_UNIDAD_ADMIN";
    private static final String RETRO_INSUMO_ID_SUBPROGRAMA = "RET_ID_SUBPROGRAMA";
    private static final String RETRO_INSUMO_ID_SECTOR = "RET_ID_SECTOR";
    private static final String RETRO_INSUMO_FECHA_INICIO_PERIODO = "RET_FECHA_INICIO_PERIODO";
    private static final String RETRO_INSUMO_FECHA_FIN_PERIODO = "RET_FECHA_FIN_PERIODO";
    private static final String RETRO_INSUMO_ID_PRIORIDAD = "RET_ID_PRIORIDAD";
    private static final String RETRO_INSUMO_MOTIVO_ACIACE = "RET_MOTIVO_ACIACE";
    private static final String PRIORIDAD_VALOR = "PRIORIDAD_VALOR";
    private static final String MOTIVO_DESCRIPCION = "MOTIVO_DESCRIPCION";
    private static final String MOTIVO_TIPO_MOTIVO = "TIPO_MOTIVO";

    private static final String COLUM_SECTOR_DESCRIPCION = "SECTOR_DESCRIPCION";
    private static final String COLUM_SUBPROGRAMA_CLAVE = "SUBPROGRAMA_CLAVE";
    private static final String COLUM_SUBPROGRAMA_DESCRIPCION = "SUBPROGRAMA_DESCRIPCION";

    @Override
    public RetroalimentacionInsumoDTO mapRow(ResultSet rs, int i) throws SQLException {

        RetroalimentacionInsumoDTO retroalimentacion = new RetroalimentacionInsumoDTO();

        retroalimentacion.setIdRetroalimentacionInsumo(rs.getBigDecimal(RETRO_INSUMO_ID_RETRO));
        retroalimentacion.setIdInsumo(rs.getBigDecimal(RETRO_INSUMO_ID_INSUMO));
        retroalimentacion.setIdMotivo(rs.getBigDecimal(RETRO_INSUMO_ID_MOTIVO));
        retroalimentacion.setMotivoSubadministrador(rs.getString(RETRO_INSUMO_MOTIVO_SUBADMINISTRADOR));
        retroalimentacion.setFechaCreacion(rs.getTimestamp(RETRO_INSUMO_FECHA_CREACION));
        retroalimentacion.setRfcRetroalimentacion(rs.getString(RETRO_INSUMO_RFC_RETROALIMENTACION));
        retroalimentacion.setFechaRetroalimentacion(rs.getTimestamp(RETRO_INSUMO_FECHA_RETROALIMENTACION));
        retroalimentacion.setEstatus(rs.getString(RETRO_INSUMO_ESTATUS));
        retroalimentacion.setRfcRechazo(rs.getString(RETRO_INSUMO_RFC_RECHAZO));
        retroalimentacion.setFechaRechazo(rs.getTimestamp(RETRO_INSUMO_FECHA_RECHAZO));
        retroalimentacion.setDescripcionRechazo(rs.getString(RETRO_INSUMO_DESCRIPCION_RECHAZO));
        retroalimentacion.setIdUnidadAdministrativa(rs.getBigDecimal(RETRO_INSUMO_ID_UNIDAD_ADMINISTRATIVA));
        retroalimentacion.setIdSubprograma(rs.getBigDecimal(RETRO_INSUMO_ID_SUBPROGRAMA));
        retroalimentacion.setIdSector(rs.getBigDecimal(RETRO_INSUMO_ID_SECTOR));
        retroalimentacion.setFechaInicioPeriodo(rs.getTimestamp(RETRO_INSUMO_FECHA_INICIO_PERIODO));
        retroalimentacion.setFechaFinPeriodo(rs.getTimestamp(RETRO_INSUMO_FECHA_FIN_PERIODO));
        retroalimentacion.setIdPrioridad(rs.getBigDecimal(RETRO_INSUMO_ID_PRIORIDAD));
        retroalimentacion.setMotivoAciace(rs.getString(RETRO_INSUMO_MOTIVO_ACIACE));

        retroalimentacion.setPrioridadDto(getPrioridad(rs));
        retroalimentacion.setFececMotivo(getMotivo(rs));
        retroalimentacion.setSector(getSector(rs));
        retroalimentacion.setSubprograma(getSubPrograma(rs));

        return retroalimentacion;
    }

    private PrioridadDTO getPrioridad(ResultSet rs) throws SQLException {
        PrioridadDTO prioridad = new PrioridadDTO();

        prioridad.setIdPrioridad(rs.getBigDecimal(RETRO_INSUMO_ID_PRIORIDAD));
        prioridad.setValor(rs.getString(PRIORIDAD_VALOR));

        return prioridad;
    }

    private FececMotivo getMotivo(ResultSet rs) throws SQLException {
        FececMotivo motivo = new FececMotivo();

        motivo.setIdMotivo(rs.getBigDecimal(RETRO_INSUMO_ID_MOTIVO));
        motivo.setDescripcion(rs.getString(MOTIVO_DESCRIPCION));
        motivo.setTipoMotivo(rs.getString(MOTIVO_TIPO_MOTIVO));

        return motivo;
    }

    private FececSector getSector(ResultSet rs) throws SQLException {
        FececSector sector = new FececSector();

        sector.setIdSector(rs.getBigDecimal(RETRO_INSUMO_ID_SECTOR));
        sector.setDescripcion(rs.getString(COLUM_SECTOR_DESCRIPCION));

        return sector;
    }

    private FececSubprograma getSubPrograma(ResultSet rs) throws SQLException {
        FececSubprograma subprograma = new FececSubprograma();

        subprograma.setIdSubprograma(rs.getBigDecimal(RETRO_INSUMO_ID_SUBPROGRAMA));
        subprograma.setClave(rs.getString(COLUM_SUBPROGRAMA_CLAVE));
        subprograma.setDescripcion(rs.getString(COLUM_SUBPROGRAMA_DESCRIPCION));

        return subprograma;

    }

}
