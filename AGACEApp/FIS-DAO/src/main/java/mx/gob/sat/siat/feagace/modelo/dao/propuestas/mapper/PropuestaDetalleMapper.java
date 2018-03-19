/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaDetalleMapper implements RowMapper<List<FecetPropuesta>> {

    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";
    private static final String COLUMN_ID_ARACE = "ID_ARACE";
    private static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";
    private static final String COLUMN_ID_METODO = "ID_METODO";
    private static final String COLUMN_ID_REVISION = "ID_REVISION";
    private static final String COLUMN_DESCRIPCION_REVISION = "DESCRIPCION_REVISION";
    private static final String COLUMN_ID_TIPO_PROPUESTA = "ID_TIPO_PROPUESTA";
    private static final String COLUMN_DESCRIPCION_TIPO_PROPUESTA = "DESCRIPCION_TIPO_PROPUESTA";
    private static final String COLUMN_ID_CAUSA_PROGRAMACION = "ID_CAUSA_PROGRAMACION";
    private static final String COLUMN_CAUSA_PROGRAMACION_DESCRIPCION = "DESCRIPCION_CAUSA_PROGRAMACION";
    private static final String COLUMN_ID_SECTOR = "ID_SECTOR";
    private static final String COLUMN_DESCRIPCION_SECTOR = "DESCRIPCION_SECTOR";
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";
    private static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";
    private static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";
    private static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";
    private static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";
    private static final String COLUMN_PRIORIDAD = "PRIORIDAD";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";
    private static final String COLUMN_RFC_CREACION = "RFC_CREACION";
    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";
    private static final String COLUMN_RFC_SUBADMINISTRADOR = "RFC_SUBADMINISTRADOR";
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";
    private static final String COLUMN_RFC_FIRMANTE = "RFC_FIRMANTE";
    private static final String COLUMN_RFC_AUDITOR = "RFC_AUDITOR";
    private static final String COLUMN_INFORME_PRESENTACION = "INFORME_PRESENTACION";
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";
    private static final String COLUM_ID_EXPEDIENTE = "ID_DOC";
    private static final String COLUM_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUM_FECHA_CREACION_ARCHIVO = "FECHA_CREACION_ARCHIVO";
    private static final String CONTRIBUYENTE_ID = "CONTRI_ID";
    private static final String CONTRIBUYENTE_RFC = "CONTRI_RFC";
    private static final String CONTRIBUYENTE_NOMBRE = "CONTRI_NOMBRE";
    private static final String CONTRIBUYENTE_REGIMEN = "CONTRI_REGIMEN";
    private static final String CONTRIBUYENTE_SITUACION = "CONTRI_SITUACION";
    private static final String CONTRIBUYENTE_CONTRI_TIPO = "CONTRI_TIPO";
    private static final String CONTRIBUYENTE_SITUACION_DOMICILIO = "CONTRI_SITUACION_DOMICILIO";
    private static final String CONTRIBUYENTE_DOMICILIO_FISCAL = "CONTRI_DOMICILIO_FISCAL";
    private static final String CONTRIBUYENTE_ACTIVIDAD_PREPONDERANTE = "CONTRI_ACTIVIDAD_PREPONDERANTE";
    private static final String CONTRIBUYENTE_ENTIDAD = "CONTRI_ENTIDAD";
    private static final String METODO_ID = "METODO_ID";
    private static final String METODO_ABREVIATURA = "METODO_ABREVIATURA";
    private static final String NOMBRE_METODO = "NOMBRE_METODO";
    private static final String SUBPROGRAMA_ID = "SUBPROGRAMA_ID";
    private static final String SUBPROGRAMA_CLAVE = "SUBPROGRAMA_CLAVE";
    private static final String DESCRIPCION_SUBPROGRAMA = "DESCRIPCION_SUBPROGRAMA";
    private static final String ID_IMPUESTO = "ID_IMPUESTO";
    private static final String ID_TIPO_IMPUESTO = "ID_TIPO_IMPUESTO";
    private static final String IMPUESTO_MONTO = "IMPUESTO_MONTO";
    private static final String ID_CONCEPTO = "ID_CONCEPTO";
    private static final String IMPUESTO_DESCRIPCION = "IMPUESTO_DESCRIPCION";
    private static final String IMPUESTO_ABREVIATURA = "IMPUESTO_ABREVIATURA";
    private static final String CONCEPTO_DESCRIPCION = "CONCEPTO_DESCRIPCION";
    private static final String DESCRIPCION_ESTATUS = "DESCRIPCION_ESTATUS";

    @Override
    public List<FecetPropuesta> mapRow(ResultSet rs, int i) throws SQLException {
        List<FecetPropuesta> lstPropuesta = new ArrayList<FecetPropuesta>();
        FecetPropuesta propuesta;
        do {
            if (validaDuplicidad(rs, lstPropuesta)) {
                propuesta = new FecetPropuesta();
                propuesta.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
                propuesta.setIdContribuyente(rs.getBigDecimal(COLUMN_ID_CONTRIBUYENTE));
                propuesta.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
                propuesta.setIdSubprograma(rs.getBigDecimal(COLUMN_ID_SUBPROGRAMA));
                propuesta.setIdMetodo(rs.getBigDecimal(COLUMN_ID_METODO));
                propuesta.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
                propuesta.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
                propuesta.setIdCausaProgramacion(rs.getBigDecimal(COLUMN_ID_CAUSA_PROGRAMACION));
                propuesta.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
                propuesta.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));
                propuesta.setFechaInicioPeriodo(rs.getDate(COLUMN_FECHA_INICIO_PERIODO));
                propuesta.setFechaFinPeriodo(rs.getDate(COLUMN_FECHA_FIN_PERIODO));
                propuesta.setFechaPresentacion(rs.getDate(COLUMN_FECHA_PRESENTACION));
                propuesta.setFechaInforme(rs.getDate(COLUMN_FECHA_INFORME));
                propuesta.setPrioridadSugerida(rs.getString(COLUMN_PRIORIDAD));
                propuesta.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
                propuesta.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
                propuesta.setRfcCreacion(rs.getString(COLUMN_RFC_CREACION));
                propuesta.setRfcAdministrador(rs.getString(COLUMN_RFC_ADMINISTRADOR));
                propuesta.setRfcSubadministrador(rs.getString(COLUMN_RFC_SUBADMINISTRADOR));
                propuesta.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
                propuesta.setRfcFirmante(rs.getString(COLUMN_RFC_FIRMANTE));
                propuesta.setRfcAuditor(rs.getString(COLUMN_RFC_AUDITOR));
                propuesta.setInformePresentacion(rs.getString(COLUMN_INFORME_PRESENTACION));
                propuesta.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
                propuesta.setPresuntiva(BigDecimal.ZERO);
                propuesta.setFececEstatus(obtenerEstatus(rs));
                propuesta.setFecetContribuyente(obtenerContribuyente(rs));
                propuesta.setFeceaMetodo(obtenerMetodo(rs));
                propuesta.setFececSector(obtenerSector(rs));
                propuesta.setFececSubprograma(obtenersSubprograma(rs));
                propuesta.setFececRevision(obtenerRevision(rs));
                propuesta.setFececTipoPropuesta(obtenerTipoPropuesta(rs));
                propuesta.setFececCausaProgramacion(obtenerCausaProgramacion(rs));
                propuesta.setListaDocumentos(new ArrayList<FecetDocExpediente>());
                propuesta.setLstImpuestos(new ArrayList<FecetImpuesto>());

                obtenerDocumento(rs, propuesta.getListaDocumentos());
                obtenerImpuesto(rs, propuesta.getLstImpuestos());

                propuesta.setPresuntiva(obtenerPresuntiva(propuesta));
                if (!lstPropuesta.contains(propuesta)) {
                    lstPropuesta.add(propuesta);
                }
                propuesta.setTipoComite(obtenerTipoComite(propuesta));
            }
        } while (rs.next());
        return lstPropuesta;
    }

    private TipoFechasComiteEnum obtenerTipoComite(FecetPropuesta propuesta) {
        TipoFechasComiteEnum resultado = null;
        boolean regional = false;
        if (propuesta.getUnidadAdministrativa() != null) {
            FececUnidadAdministrativa unidad = propuesta.getUnidadAdministrativa();
            regional = !Constantes.ACOECE.equals(unidad.getIdUnidadAdministrativa())
                    && !Constantes.ACAOCE.equals(unidad.getIdUnidadAdministrativa());
        }
        if (propuesta.getFechaInforme() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL
                    : TipoFechasComiteEnum.FECHA_INFORME_COMITE;
        } else if (propuesta.getFechaPresentacion() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL
                    : TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE;
        }

        return resultado;
    }

    private boolean validaDuplicidad(ResultSet rs, List<FecetPropuesta> lstPropuesta) throws SQLException {
        for (FecetPropuesta pro : lstPropuesta) {
            if (rs.getBigDecimal(COLUMN_ID_PROPUESTA).equals(pro.getIdPropuesta())) {
                obtenerDocumento(rs, pro.getListaDocumentos());
                obtenerImpuesto(rs, pro.getLstImpuestos());
                pro.setPresuntiva(obtenerPresuntiva(pro));
                return false;
            }
        }
        return true;
    }

    private void obtenerDocumento(ResultSet rs, List<FecetDocExpediente> lstDocExpedientes) throws SQLException {

        FecetDocExpediente doc = new FecetDocExpediente();

        doc.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        doc.setIdDocExpediente(rs.getBigDecimal(COLUM_ID_EXPEDIENTE));
        if (validaDocumento(lstDocExpedientes, doc)) {

            doc.setRutaArchivo(rs.getString(COLUM_RUTA_ARCHIVO));

            if (lstDocExpedientes != null && !lstDocExpedientes.contains(doc)) {
                doc.setNombre(UtileriasMapperDao.getNameFileFromPath((rs.getString(COLUM_RUTA_ARCHIVO))));
                doc.setFechaCreacion(rs.getTimestamp(COLUM_FECHA_CREACION_ARCHIVO));
                lstDocExpedientes.add(doc);
            }
        }
    }

    private FececEstatus obtenerEstatus(ResultSet rs) throws SQLException {
        FececEstatus estatus = new FececEstatus();
        estatus.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        estatus.setDescripcion(rs.getString(DESCRIPCION_ESTATUS));
        return estatus;
    }

    private FececSector obtenerSector(ResultSet rs) throws SQLException {
        FececSector sector = new FececSector();
        sector.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        sector.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SECTOR));
        return sector;
    }

    private FececTipoPropuesta obtenerTipoPropuesta(ResultSet rs) throws SQLException {
        FececTipoPropuesta tipoPropuesta = new FececTipoPropuesta();
        tipoPropuesta.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
        tipoPropuesta.setDescripcion(rs.getString(COLUMN_DESCRIPCION_TIPO_PROPUESTA));
        return tipoPropuesta;
    }

    private FecetContribuyente obtenerContribuyente(ResultSet rs) throws SQLException {

        FecetContribuyente contribuyente = new FecetContribuyente();

        contribuyente.setIdContribuyente(rs.getBigDecimal(CONTRIBUYENTE_ID));
        contribuyente.setRfc(rs.getString(CONTRIBUYENTE_RFC));
        contribuyente.setNombre(rs.getString(CONTRIBUYENTE_NOMBRE));
        contribuyente.setRegimen(rs.getString(CONTRIBUYENTE_REGIMEN));
        contribuyente.setSituacion(rs.getString(CONTRIBUYENTE_SITUACION));
        contribuyente.setTipo(rs.getString(CONTRIBUYENTE_CONTRI_TIPO));
        contribuyente.setSituacionDomicilio(rs.getString(CONTRIBUYENTE_SITUACION_DOMICILIO));
        contribuyente.setDomicilioFiscal(rs.getString(CONTRIBUYENTE_DOMICILIO_FISCAL));
        contribuyente.setActividadPreponderante(rs.getString(CONTRIBUYENTE_ACTIVIDAD_PREPONDERANTE));
        contribuyente.setEntidad(rs.getString(CONTRIBUYENTE_ENTIDAD));

        return contribuyente;

    }

    private FececMetodo obtenerMetodo(ResultSet rs) throws SQLException {

        FececMetodo metodo = new FececMetodo();

        metodo.setIdMetodo(rs.getBigDecimal(METODO_ID));
        metodo.setAbreviatura(rs.getString(METODO_ABREVIATURA));
        metodo.setNombre(rs.getString(NOMBRE_METODO));

        return metodo;

    }

    private FececSubprograma obtenersSubprograma(ResultSet rs) throws SQLException {

        FececSubprograma subPrograma = new FececSubprograma();

        subPrograma.setIdSubprograma(rs.getBigDecimal(SUBPROGRAMA_ID));
        subPrograma.setClave(rs.getString(SUBPROGRAMA_CLAVE));
        subPrograma.setDescripcion(rs.getString(DESCRIPCION_SUBPROGRAMA));

        return subPrograma;

    }

    private FececRevision obtenerRevision(ResultSet rs) throws SQLException {

        FececRevision revision = new FececRevision();
        revision.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
        revision.setDescripcion(rs.getString(COLUMN_DESCRIPCION_REVISION));

        return revision;

    }

    private void obtenerImpuesto(ResultSet rs, List<FecetImpuesto> lstImpuestos) throws SQLException {

        FecetImpuesto impuesto = new FecetImpuesto();
        FececConcepto concepto = new FececConcepto();
        FececTipoImpuesto tipoImpuesto = new FececTipoImpuesto();
        impuesto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        impuesto.setIdImpuesto(rs.getBigDecimal(ID_IMPUESTO));

        if (lstImpuestos != null && !lstImpuestos.contains(impuesto) && noEsImpuestoVacio(impuesto)) {
            impuesto.setIdTipoImpuesto(rs.getBigDecimal(ID_TIPO_IMPUESTO));
            impuesto.setIdConcepto(rs.getBigDecimal(ID_CONCEPTO));
            tipoImpuesto.setIdTipoImpuesto(rs.getBigDecimal(ID_TIPO_IMPUESTO));
            concepto.setDescripcion(rs.getString(CONCEPTO_DESCRIPCION));
            concepto.setIdConcepto(rs.getBigDecimal(ID_CONCEPTO).intValue());
            impuesto.setMonto(rs.getBigDecimal(IMPUESTO_MONTO));
            tipoImpuesto.setAbreviatura(rs.getString(IMPUESTO_ABREVIATURA));
            tipoImpuesto.setDescripcion(rs.getString(IMPUESTO_DESCRIPCION));
            impuesto.setFececTipoImpuesto(tipoImpuesto);
            impuesto.setFececConcepto(concepto);
            lstImpuestos.add(impuesto);
        }

    }

    private boolean noEsImpuestoVacio(FecetImpuesto impuesto) {
        return impuesto.getIdImpuesto() != null;
    }

    private BigDecimal obtenerPresuntiva(FecetPropuesta propuesta) {
        BigDecimal presuntiva = BigDecimal.ZERO;

        if (presuntiva.compareTo(BigDecimal.ZERO) == 0) {
            for (FecetImpuesto impuesto : propuesta.getLstImpuestos()) {
                presuntiva = presuntiva.add(impuesto.getMonto());
            }
            if (!(presuntiva.compareTo(BigDecimal.ZERO) == 0)) {
                return presuntiva;
            }
        } else {
            presuntiva = propuesta.getPresuntiva();
        }

        return presuntiva;
    }

    private FececCausaProgramacion obtenerCausaProgramacion(ResultSet rs) throws SQLException {
        FececCausaProgramacion causaProgramacion = new FececCausaProgramacion();

        causaProgramacion.setIdCausaProgramacion(rs.getBigDecimal(COLUMN_ID_CAUSA_PROGRAMACION));
        causaProgramacion.setDescripcion(rs.getString(COLUMN_CAUSA_PROGRAMACION_DESCRIPCION));

        return causaProgramacion;
    }

    private boolean validaDocumento(List<FecetDocExpediente> list, FecetDocExpediente docx) {

        for (FecetDocExpediente doc : list) {
            if (doc.getIdDocExpediente().equals(docx.getIdDocExpediente())) {
                return false;
            }
        }
        return true;

    }

}
