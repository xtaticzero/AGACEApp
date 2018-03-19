/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.PrioridadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class InsumoDetalleMapper implements RowMapper<List<FecetInsumo>> {

    private static final String COLUM_ID_INSUMO = "ID_INSUMO";
    private static final String COLUM_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";
    private static final String COLUM_ID_UNIDAD_ADMINISTRATIVA = "ID_UNIDAD_ADMINISTRATIVA";
    private static final String COLUM_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";
    private static final String COLUM_ID_SECTOR = "ID_SECTOR";
    private static final String COLUM_ID_REGISTRO = "ID_REGISTRO";
    private static final String COLUM_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";
    private static final String COLUM_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";
    private static final String COLUM_PRIORIDAD = "PRIORIDAD";
    private static final String COLUM_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUM_FECHA_BAJA = "FECHA_BAJA";
    private static final String COLUM_RFC_CREACION = "RFC_CREACION";
    private static final String COLUM_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";
    private static final String COLUM_RFC_SUBADMINISTRADOR = "RFC_SUBADMINISTRADOR";
    private static final String COLUM_ID_ESTATUS = "ID_ESTATUS";
    private static final String COLUM_ID_PRIORIDAD = "ID_PRIORIDAD";
    private static final String COLUM_FECHA_INICIO_PLAZO = "FECHA_INICIO_PLAZO";
    private static final String COLUM_CONTRIBUYENTE_RFC = "CONTRIBUYENTE_RFC";
    private static final String COLUM_CONTRIBUYENTE_NOMBRE = "CONTRIBUYENTE_NOMBRE";
    private static final String COLUM_CONTRIBUYENTE_REGIMEN = "CONTRIBUYENTE_REGIMEN";
    private static final String COLUM_CONTRIBUYENTE_SITUACION = "CONTRIBUYENTE_SITUACION";
    private static final String COLUM_CONTRIBUYENTE_TIPO = "CONTRIBUYENTE_TIPO";
    private static final String COLUM_SITUACION_DOMICILIO = "SITUACION_DOMICILIO";
    private static final String COLUM_DOMICILIO_FISCAL = "DOMICILIO_FISCAL";
    private static final String COLUM_ACTIVIDAD_PREPONDERANTE = "ACTIVIDAD_PREPONDERANTE";
    private static final String COLUM_CONTRIBUYENTE_ENTIDAD = "CONTRIBUYENTE_ENTIDAD";
    private static final String COLUM_PRIORIDAD_VALOR = "PRIORIDAD_VALOR";
    private static final String COLUM_PRIORIDAD_FECHA_INICIO = "PRIORIDAD_FECHA_INICIO";
    private static final String COLUM_PRIORIDAD_FECHA_FIN = "PRIORIDAD_FECHA_FIN";
    private static final String COLUM_ESTATUS_DESCRIPCION = "ESTATUS_DESCRIPCION";
    private static final String COLUM_ESTATUS_MODULO = "ESTATUS_MODULO";
    private static final String COLUM_SECTOR_DESCRIPCION = "SECTOR_DESCRIPCION";
    private static final String COLUM_SUBPROGRAMA_CLAVE = "SUBPROGRAMA_CLAVE";
    private static final String COLUM_SUBPROGRAMA_DESCRIPCION = "SUBPROGRAMA_DESCRIPCION";
    private static final String COLUM_DOC_INSUMO_ID = "DOC_INSUMO_ID";
    private static final String COLUM_DOC_INSUMO_RUTA_ARCHIVO = "DOC_INSUMO_RUTA_ARCHIVO";
    private static final String COLUM_DOC_INSUMO_FECHA_CREACION = "DOC_INSUMO_FECHA_CREACION";

    @Override
    public List<FecetInsumo> mapRow(ResultSet rs, int i) throws SQLException {
        List<FecetInsumo> lstInsumos = new ArrayList<FecetInsumo>();
        FecetInsumo insumo = new FecetInsumo();

        do {
            if (rs.getBigDecimal(COLUM_ID_INSUMO).equals(insumo.getIdInsumo())) {
                obtenerDocumento(rs, insumo.getListaDocumentos());
            } else {
                if (insumo.getIdInsumo() != null) {
                    lstInsumos.add(insumo);
                }
                insumo = new FecetInsumo();

                insumo.setIdInsumo(rs.getBigDecimal(COLUM_ID_INSUMO));
                insumo.setIdContribuyente(rs.getBigDecimal(COLUM_ID_CONTRIBUYENTE));
                insumo.setIdUnidadAdministrativa(rs.getBigDecimal(COLUM_ID_UNIDAD_ADMINISTRATIVA));
                insumo.setIdSubprograma(rs.getBigDecimal(COLUM_ID_SUBPROGRAMA));
                insumo.setIdSector(rs.getBigDecimal(COLUM_ID_SECTOR));
                insumo.setIdRegistro(rs.getString(COLUM_ID_REGISTRO));
                insumo.setFechaInicioPeriodo(rs.getTimestamp(COLUM_FECHA_INICIO_PERIODO));
                insumo.setFechaFinPeriodo(rs.getTimestamp(COLUM_FECHA_FIN_PERIODO));
                int flg = rs.getInt(COLUM_PRIORIDAD);
                insumo.setPrioridad((flg != 0));
                insumo.setFechaCreacion(rs.getTimestamp(COLUM_FECHA_CREACION));
                insumo.setFechaBaja(rs.getTimestamp(COLUM_FECHA_BAJA));
                insumo.setRfcCreacion(rs.getString(COLUM_RFC_CREACION));
                insumo.setRfcAdministrador(rs.getString(COLUM_RFC_ADMINISTRADOR));
                insumo.setRfcSubadministrador(rs.getString(COLUM_RFC_SUBADMINISTRADOR));
                insumo.setIdEstatus(rs.getBigDecimal(COLUM_ID_ESTATUS));
                insumo.setIdPrioridad(rs.getBigDecimal(COLUM_ID_PRIORIDAD));
                insumo.setFechaInicioPlazo(rs.getTimestamp(COLUM_FECHA_INICIO_PLAZO));

                insumo.setPrioridadDto(getPrioridad(rs));
                insumo.setFececEstatus(getEstatus(rs));
                insumo.setFecetContribuyente(getContribuyente(rs));
                insumo.setFececSector(getSector(rs));
                insumo.setFececSubprograma(getSubPrograma(rs));

                insumo.setListaDocumentos(new ArrayList<FecetDocExpInsumo>());
                obtenerDocumento(rs, insumo.getListaDocumentos());
            }
        } while (rs.next());
        lstInsumos.add(insumo);
        return lstInsumos;
    }

    private PrioridadDTO getPrioridad(ResultSet rs) throws SQLException {
        PrioridadDTO prioridad = new PrioridadDTO();

        prioridad.setIdPrioridad(rs.getBigDecimal(COLUM_ID_PRIORIDAD));
        prioridad.setValor(rs.getString(COLUM_PRIORIDAD_VALOR));
        prioridad.setFechaInicio(rs.getTimestamp(COLUM_PRIORIDAD_FECHA_INICIO));
        prioridad.setFechaFin(rs.getTimestamp(COLUM_PRIORIDAD_FECHA_FIN));

        return prioridad;
    }

    private FececEstatus getEstatus(ResultSet rs) throws SQLException {
        FececEstatus estatus = new FececEstatus();
        estatus.setIdEstatus(rs.getBigDecimal(COLUM_ID_ESTATUS));
        estatus.setDescripcion(rs.getString(COLUM_ESTATUS_DESCRIPCION));
        estatus.setModulo(rs.getString(COLUM_ESTATUS_MODULO));
        return estatus;
    }

    private FecetContribuyente getContribuyente(ResultSet rs) throws SQLException {
        FecetContribuyente contribuyente = new FecetContribuyente();

        contribuyente.setIdContribuyente(rs.getBigDecimal(COLUM_ID_CONTRIBUYENTE));
        contribuyente.setRfc(rs.getString(COLUM_CONTRIBUYENTE_RFC));
        contribuyente.setNombre(rs.getString(COLUM_CONTRIBUYENTE_NOMBRE));
        contribuyente.setRegimen(rs.getString(COLUM_CONTRIBUYENTE_REGIMEN));
        contribuyente.setSituacion(rs.getString(COLUM_CONTRIBUYENTE_SITUACION));
        contribuyente.setTipo(rs.getString(COLUM_CONTRIBUYENTE_TIPO));
        contribuyente.setSituacionDomicilio(rs.getString(COLUM_SITUACION_DOMICILIO));
        contribuyente.setDomicilioFiscal(rs.getString(COLUM_DOMICILIO_FISCAL));
        contribuyente.setActividadPreponderante(rs.getString(COLUM_ACTIVIDAD_PREPONDERANTE));
        contribuyente.setEntidad(rs.getString(COLUM_CONTRIBUYENTE_ENTIDAD));

        return contribuyente;
    }

    private FececSector getSector(ResultSet rs) throws SQLException {
        FececSector sector = new FececSector();

        sector.setIdSector(rs.getBigDecimal(COLUM_ID_SECTOR));
        sector.setDescripcion(rs.getString(COLUM_SECTOR_DESCRIPCION));

        return sector;
    }

    private FececSubprograma getSubPrograma(ResultSet rs) throws SQLException {
        FececSubprograma subprograma = new FececSubprograma();

        subprograma.setIdSubprograma(rs.getBigDecimal(COLUM_ID_SUBPROGRAMA));
        subprograma.setClave(rs.getString(COLUM_SUBPROGRAMA_CLAVE));
        subprograma.setDescripcion(rs.getString(COLUM_SUBPROGRAMA_DESCRIPCION));

        return subprograma;

    }

    private void obtenerDocumento(ResultSet rs, List<FecetDocExpInsumo> listaDocumentos) throws SQLException {
        FecetDocExpInsumo doc = new FecetDocExpInsumo();

        doc.setIdInsumo(rs.getBigDecimal(COLUM_ID_INSUMO));
        doc.setIdDocExpInsumo(rs.getBigDecimal(COLUM_DOC_INSUMO_ID));
        doc.setRutaArchivo(rs.getString(COLUM_DOC_INSUMO_RUTA_ARCHIVO));

        if (listaDocumentos != null && !listaDocumentos.contains(doc)) {
            doc.setNombre(UtileriasMapperDao.getNameFileFromPath((rs.getString(COLUM_DOC_INSUMO_RUTA_ARCHIVO))));
            doc.setFechaCreacion(rs.getTimestamp(COLUM_DOC_INSUMO_FECHA_CREACION));

            listaDocumentos.add(doc);
        }
    }

}
