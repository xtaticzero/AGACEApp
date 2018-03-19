package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.jdbc.core.RowMapper;

public class AsignarFrimanteAuditorMapper implements RowMapper<List<PropuestaOrigenCentralRegDTO>> {

    public static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    public static final String COLUMN_FOLIO = "FOLIO";
    public static final String COLUMN_RFC = "RFC";
    public static final String COLUMN_RFC_CREACION = "RFC_CREACION";
    public static final String COLUMN_RFC_FIRMANTE = "RFC_FIRMANTE";
    public static final String COLUMN_PRIORIDAD = "PRIORIDAD";
    public static final String COLUMN_ABREVIATURA = "ABREVIATURA";
    public static final String COLUMN_CLAVE = "CLAVE";
    public static final String COLUMN_NOMBRE = "NOMBRE";
    public static final String COLUMN_CENTRAL = "CENTRAL";
    public static final String COLUMN_TIPO_IMPUESTO = "ID_TIPO_IMPUESTO";
    public static final String COLUMN_MONTO = "MONTO";
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    public static final String COLUMN_SECTOR = "SECTOR_DESC";
    public static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";
    public static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";
    public static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";
    public static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";
    public static final String COLUMN_TIPO_PROPUESTA = "PROPUESTA_TIPO_DESC";
    public static final String COLUMN_ID_CONCEPTO = "ID_CONCEPTO";

    @Override
    public List<PropuestaOrigenCentralRegDTO> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<PropuestaOrigenCentralRegDTO> lstPropuesta = new ArrayList<PropuestaOrigenCentralRegDTO>();
        PropuestaOrigenCentralRegDTO dto = new PropuestaOrigenCentralRegDTO();
        FecetPropuesta propuesta = new FecetPropuesta();
        dto.setPropuesta(propuesta);

        do {
            dto = new PropuestaOrigenCentralRegDTO();
            FecetPropuesta fecetPropuesta = new FecetPropuesta();
            FecetContribuyente contribuyente = new FecetContribuyente();
            FececMetodo metodo = new FececMetodo();
            FececSubprograma subprograma = new FececSubprograma();
            FececArace arace = new FececArace();
            FecetImpuesto fecetImpuesto = new FecetImpuesto();
            FececSector fececSector = new FececSector();
            FececTipoPropuesta tipoPropuesta = new FececTipoPropuesta();
            List<FecetImpuesto> listaImpuestos = new ArrayList<FecetImpuesto>();

            if (rs.getBigDecimal(COLUMN_TIPO_IMPUESTO) == null) {
                BigDecimal tipoImpuesto = new BigDecimal(Constantes.CERO);
                fecetImpuesto.setIdTipoImpuesto(tipoImpuesto);
            } else {
                fecetImpuesto.setIdTipoImpuesto(rs.getBigDecimal(COLUMN_TIPO_IMPUESTO));
            }
            if (rs.getBigDecimal(COLUMN_MONTO) == null) {
                BigDecimal monto = new BigDecimal(Constantes.CERO);
                fecetImpuesto.setMonto(monto);
                fecetPropuesta.setPresuntiva(monto);
            } else {
                fecetImpuesto.setMonto(rs.getBigDecimal(COLUMN_MONTO));
                fecetPropuesta.setPresuntiva(rs.getBigDecimal(COLUMN_MONTO));
            }
            if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_CONCEPTO)) {
                fecetImpuesto.setIdConcepto(rs.getBigDecimal(COLUMN_ID_CONCEPTO));
            }

            listaImpuestos.add(fecetImpuesto);

            fecetPropuesta.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
            fecetPropuesta.setIdRegistro(rs.getString(COLUMN_FOLIO));
            fecetPropuesta.setPrioridadSugerida(rs.getString(COLUMN_PRIORIDAD));
            fecetPropuesta.setFechaInforme(rs.getDate(COLUMN_FECHA_INFORME));
            fecetPropuesta.setFechaPresentacion(rs.getDate(COLUMN_FECHA_PRESENTACION));
            fecetPropuesta.setFechaInicioPeriodo(rs.getDate(COLUMN_FECHA_INICIO_PERIODO));
            fecetPropuesta.setFechaFinPeriodo(rs.getDate(COLUMN_FECHA_FIN_PERIODO));
            fecetPropuesta.setLstImpuestos(listaImpuestos);
            contribuyente.setRfc(rs.getString(COLUMN_RFC));

            metodo.setAbreviatura(rs.getString(COLUMN_ABREVIATURA));

            subprograma.setClave(rs.getString(COLUMN_CLAVE));
            subprograma.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

            if (UtileriasMapperDao.existeColumna(rs, COLUMN_CENTRAL)) {
                arace.setNombre(rs.getString(COLUMN_NOMBRE));
                arace.setCentral(rs.getBoolean(COLUMN_CENTRAL));
                dto.setArace(arace);
            }

            if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC_CREACION)) {
                fecetPropuesta.setRfcCreacion(rs.getString(COLUMN_RFC_CREACION));
            }

            if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC_FIRMANTE)) {
                fecetPropuesta.setRfcFirmante(rs.getString(COLUMN_RFC_FIRMANTE));
            }

            fececSector.setDescripcion(rs.getString(COLUMN_SECTOR));

            tipoPropuesta.setDescripcion(rs.getString(COLUMN_TIPO_PROPUESTA));

            dto.setPropuesta(fecetPropuesta);

            dto.setContribuyente(contribuyente);
            dto.setMetodo(metodo);
            dto.setSubprograma(subprograma);
            dto.setSector(fececSector);
            dto.setTipoPropuesta(tipoPropuesta);
            fecetPropuesta.setTipoComite(obtenerTipoComite(fecetPropuesta));
            lstPropuesta.add(dto);
        } while (rs.next());

        obtenerPropuestasSinRepetir(lstPropuesta);

        return lstPropuesta;
    }

    private TipoFechasComiteEnum obtenerTipoComite(FecetPropuesta propuesta) {
        TipoFechasComiteEnum resultado = null;
        boolean regional = false;
        if (propuesta.getUnidadAdministrativa() != null) {
            FececUnidadAdministrativa unidad = propuesta.getUnidadAdministrativa();
            regional = !Constantes.ACOECE.equals(unidad.getIdUnidadAdministrativa()) && !Constantes.ACAOCE.equals(unidad.getIdUnidadAdministrativa());
        }
        if (propuesta.getFechaInforme() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL : TipoFechasComiteEnum.FECHA_INFORME_COMITE;
        } else if (propuesta.getFechaPresentacion() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL : TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE;
        }

        return resultado;
    }

    private boolean obtenerImpuestos(BigDecimal idTipoImpuesto, BigDecimal idConcepto, PropuestaOrigenCentralRegDTO propuesta) {
        Boolean flgBandera = false;
        for (FecetImpuesto impuesto : propuesta.getPropuesta().getLstImpuestos()) {
            if (impuesto.getIdTipoImpuesto().equals(idTipoImpuesto) && impuesto.getIdConcepto().equals(idConcepto)) {
                flgBandera = true;
                break;
            }
        }
        return flgBandera;
    }

    private void obtenerPresuntiva(PropuestaOrigenCentralRegDTO propuesta, BigDecimal monto) {
        BigDecimal presuntiva = propuesta.getPropuesta().getPresuntiva();
        presuntiva = presuntiva.add(monto);
        propuesta.getPropuesta().setPresuntiva(presuntiva.plus());
    }

    private List<PropuestaOrigenCentralRegDTO> obtenerPropuestasSinRepetir(List<PropuestaOrigenCentralRegDTO> listaPropuestas) {
        for (int i = 0; listaPropuestas.size() > i; i++) {
            for (int j = i + 1; listaPropuestas.size() > j; j++) {
                if (listaPropuestas.get(i).getPropuesta().getIdPropuesta().equals(listaPropuestas.get(j).getPropuesta().getIdPropuesta())) {
                    if (!obtenerImpuestos(listaPropuestas.get(j).getPropuesta().getLstImpuestos().get(Constantes.CERO).getIdTipoImpuesto(), listaPropuestas.get(j).getPropuesta().getLstImpuestos().get(Constantes.CERO).getIdConcepto(), listaPropuestas.get(i))) {
                        listaPropuestas.get(i).getPropuesta().getLstImpuestos().add(listaPropuestas.get(j).getPropuesta().getLstImpuestos().get(Constantes.CERO));
                        obtenerPresuntiva(listaPropuestas.get(i), listaPropuestas.get(j).getPropuesta().getPresuntiva());
                    }
                    listaPropuestas.remove(j);
                    --j;
                }
            }
        }
        return listaPropuestas;
    }

}
