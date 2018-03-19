package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

/**
 *
 * @author Ing. Juan Adrian Cuervo Zarate
 *
 */
public class FecetPropuestaCambioMetodoOrden implements RowMapper<List<FecetPropuesta>> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBPROGRAMA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";

    /**
     * Este atributo corresponde al nombre de la columna ID_METODO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_METODO = "ID_METODO";

    /**
     * Este atributo corresponde al nombre de la columna ID_REVISION en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_REVISION = "ID_REVISION";
    private static final String COLUMN_DESCRIPCION_REVISION = "DESCRIPCION_REVISION";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_PROPUESTA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_TIPO_PROPUESTA = "ID_TIPO_PROPUESTA";
    private static final String COLUMN_DESCRIPCION_TIPO_PROPUESTA = "DESCRIPCION_TIPO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_CAUSA_PROGRAMACION
     * en la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_CAUSA_PROGRAMACION = "ID_CAUSA_PROGRAMACION";
    private static final String COLUMN_CAUSA_PROGRAMACION_DESCRIPCION = "DESCRIPCION_CAUSA_PROGRAMACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_SECTOR en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_SECTOR = "ID_SECTOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_REGISTRO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO_PERIODO en
     * la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN_PERIODO en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_PRESENTACION en
     * la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INFORME en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";

    /**
     * Este atributo corresponde al nombre de la columna PRIORIDAD en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_PRIORIDAD = "PRIORIDAD";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CREACION en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_CREACION = "RFC_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_ADMINISTRADOR en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna RFC_SUBADMINISTRADOR en
     * la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_SUBADMINISTRADOR = "RFC_SUBADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna RFC_FIRMANTE en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_FIRMANTE = "RFC_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna RFC_AUDITOR en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_AUDITOR = "RFC_AUDITOR";
    /**
     * Este atributo corresponde al nombre de la columna COMITE_REGIONAL en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_INFORME_PRESENTACION = "INFORME_PRESENTACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    private static final String COLUM_ID_EXPEDIENTE = "ID_DOC";

    private static final String COLUM_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUM_FECHA_CREACION_ARCHIVO = "FECHA_CREACION_ARCHIVO";

    /**
     * CAMPOS PARA DETALLE CONTRIBUYENTE
     */
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

    /**
     * CAMPOS PARA FECEC_METODO
     */
    private static final String METODO_ID = "METODO_ID";
    private static final String METODO_ABREVIATURA = "METODO_ABREVIATURA";
    private static final String NOMBRE_METODO = "NOMBRE_METODO";

    /**
     * CAMPOS PARA FECEC_SUBPROGRAMA
     */
    private static final String SUBPROGRAMA_ID = "SUBPROGRAMA_ID";
    private static final String SUBPROGRAMA_CLAVE = "SUBPROGRAMA_CLAVE";
    private static final String DESCRIPCION_SUBPROGRAMA = "DESCRIPCION_SUBPROGRAMA";

    /**
     * CAMPOS PARA FECET_IMPUESTO
     */
    private static final String ID_IMPUESTO = "ID_IMPUESTO";
    private static final String ID_TIPO_IMPUESTO = "ID_TIPO_IMPUESTO";
    private static final String IMPUESTO_MONTO = "IMPUESTO_MONTO";

    /**
     * CAMPOS PARA FECEC_TIPO_IMPUESTO
     */
    private static final String IMPUESTO_DESCRIPCION = "IMPUESTO_DESCRIPCION";
    private static final String IMPUESTO_ABREVIATURA = "IMPUESTO_ABREVIATURA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna NUMERO_ORDEN en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_NUMERO_ORDEN = "NUMERO_ORDEN";

    /**
     * CAMPOS PARA FECEC_METODO
     */
    private static final String METODO_ID_ORDEN = "ID_METODO_ORDEN";
    private static final String METODO_ABREVIATURA_ORDEN = "METODOS_ABREVIATURA_ORDEN";
    private static final String NOMBRE_METODO_ORDEN = "METODO_DESCRIPCION_ORDEN";

    /**
     * CAMPOS PARA FECET_TIPO_IMPUESTO
     *
     */
    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECET_PROPUESTA de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public List<FecetPropuesta> mapRow(ResultSet rs, int i) throws SQLException {
        List<FecetPropuesta> lstPropuesta = new ArrayList<FecetPropuesta>();
        FecetPropuesta propuesta;

        propuesta = new FecetPropuesta();

        do {
            if (rs.getBigDecimal(COLUMN_ID_PROPUESTA).equals(propuesta.getIdPropuesta())) {
                obtenerDocumento(rs, propuesta.getListaDocumentos());
                obtenerImpuesto(rs, propuesta.getLstImpuestos());
            } else {
                if (propuesta.getIdPropuesta() != null) {
                    propuesta.setPresuntiva(obtenerPresuntiva(propuesta));
                    lstPropuesta.add(propuesta);
                }
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

                propuesta.setFecetContribuyente(obtenerContribuyente(rs));
                propuesta.setFeceaMetodo(obtenerMetodo(rs));
                propuesta.setFececSubprograma(obtenersSubprograma(rs));
                propuesta.setFececRevision(obtenerRevision(rs));
                propuesta.setFececTipoPropuesta(obtenerTipoPropuesta(rs));
                propuesta.setFececCausaProgramacion(obtenerCausaProgramacion(rs));
                propuesta.setAgaceOrden(obteneOrden(rs));
                propuesta.setCambioMetodoOrden(obtenerCambioMetodoOrden(rs));
                propuesta.setListaDocumentos(new ArrayList<FecetDocExpediente>());
                propuesta.setLstImpuestos(new ArrayList<FecetImpuesto>());

                obtenerDocumento(rs, propuesta.getListaDocumentos());
                obtenerImpuesto(rs, propuesta.getLstImpuestos());

            }
        } while (rs.next());
        propuesta.setPresuntiva(obtenerPresuntiva(propuesta));
        lstPropuesta.add(propuesta);
        return lstPropuesta;
    }

    private void obtenerDocumento(ResultSet rs, List<FecetDocExpediente> lstDocExpedientes) throws SQLException {

        FecetDocExpediente doc = new FecetDocExpediente();

        doc.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        doc.setRutaArchivo(rs.getString(COLUM_RUTA_ARCHIVO));
        doc.setNombre(UtileriasMapperDao.getNameFileFromPath((rs.getString(COLUM_RUTA_ARCHIVO))));
        doc.setIdDocExpediente(rs.getBigDecimal(COLUM_ID_EXPEDIENTE));
        doc.setFechaCreacion(rs.getDate(COLUM_FECHA_CREACION_ARCHIVO));

        boolean flgIsRepetido = false;
        for (FecetDocExpediente expediente : lstDocExpedientes) {
            flgIsRepetido = expediente.equals(doc);
            if (flgIsRepetido) {
                break;
            }
        }

        if (lstDocExpedientes.isEmpty()) {
            lstDocExpedientes.add(doc);
        } else if (!flgIsRepetido) {
            lstDocExpedientes.add(doc);
        }
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

    private FececMetodo obtenerCambioMetodoOrden(ResultSet rs) throws SQLException {

        FececMetodo metodo = new FececMetodo();

        metodo.setIdMetodo(rs.getBigDecimal(METODO_ID_ORDEN));
        metodo.setAbreviatura(rs.getString(METODO_ABREVIATURA_ORDEN));
        metodo.setNombre(rs.getString(NOMBRE_METODO_ORDEN));

        return metodo;

    }

    private FececSubprograma obtenersSubprograma(ResultSet rs) throws SQLException {

        FececSubprograma subPrograma = new FececSubprograma();

        subPrograma.setIdSubprograma(rs.getBigDecimal(SUBPROGRAMA_ID));
        subPrograma.setClave(rs.getString(SUBPROGRAMA_CLAVE));
        subPrograma.setDescripcion(rs.getString(DESCRIPCION_SUBPROGRAMA));

        return subPrograma;

    }

    private AgaceOrden obteneOrden(ResultSet rs) throws SQLException {

        AgaceOrden orden = new AgaceOrden();

        orden.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        orden.setNumeroOrden(rs.getString(COLUMN_NUMERO_ORDEN));

        return orden;

    }

    private FececRevision obtenerRevision(ResultSet rs) throws SQLException {

        FececRevision revision = new FececRevision();
        revision.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
        revision.setDescripcion(rs.getString(COLUMN_DESCRIPCION_REVISION));

        return revision;

    }

    private void obtenerImpuesto(ResultSet rs, List<FecetImpuesto> lstImpuestos) throws SQLException {

        FecetImpuesto impuesto = new FecetImpuesto();
        FececTipoImpuesto tipoImpuesto = new FececTipoImpuesto();
        impuesto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        impuesto.setIdImpuesto(rs.getBigDecimal(ID_IMPUESTO));
        impuesto.setIdTipoImpuesto(rs.getBigDecimal(ID_TIPO_IMPUESTO));
        tipoImpuesto.setIdTipoImpuesto(rs.getBigDecimal(ID_TIPO_IMPUESTO));

        impuesto.setMonto(rs.getBigDecimal(IMPUESTO_MONTO));
        tipoImpuesto.setAbreviatura(rs.getString(IMPUESTO_ABREVIATURA));
        tipoImpuesto.setDescripcion(rs.getString(IMPUESTO_DESCRIPCION));
        impuesto.setFececTipoImpuesto(tipoImpuesto);

        boolean flgIsRepetido = false;

        for (FecetImpuesto imp : lstImpuestos) {
            flgIsRepetido = imp.equals(impuesto);
            if (flgIsRepetido) {
                break;
            }
        }

        if (lstImpuestos.isEmpty() && noEsImpuestoVacio(impuesto)) {
            lstImpuestos.add(impuesto);
        } else if (!flgIsRepetido && noEsImpuestoVacio(impuesto)) {
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

}
