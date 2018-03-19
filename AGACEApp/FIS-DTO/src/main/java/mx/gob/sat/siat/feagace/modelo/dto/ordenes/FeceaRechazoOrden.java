/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

public class FeceaRechazoOrden implements Serializable {
 
        /**
         * Este atributo mapea la columna ID_RECHAZO_ORDEN de la tabla FECET_RECHAZO_ORDEN
         */
        private BigDecimal idRechazoOrden;
        
        /**
         * Este atributo mapea la columna ID_ORDEN de la tabla FECET_RECHAZO_ORDEN
         */
        private BigDecimal idOrden;
        
        /**
         * Este atributo mapea la columna DESCRIPCION de la tabla FECET_RECHAZO_ORDEN
         */
        private String descripcion;
        
        
        /**
         * Este atributo mapea la columna RFC_FIRMANTE de la tabla FECET_RECHAZO_ORDEN
         */
        private String rfcFirmante;
        
        /**
         * Este atributo mapea la columna FECHA_INICIO de la tabla FECET_RECHAZO_ORDEN
         */
        private Date fechaInicio;
        
        /**
         * Este atributo mapea la columna FECHA_FIN de la tabla FECET_RECHAZO_ORDEN
         */
        private Date fechaFin;
        
        /**
         * Este atributo mapea la columna ESTATUS de la tabla FECET_RECHAZO_ORDEN
         */
        private String estatus;

       /**
        * Metodo setIdRechazoOrden
        * @param idRechazoOrden
        */
        public void setIdRechazoOrden(BigDecimal idRechazoOrden) {
            this.idRechazoOrden = idRechazoOrden;
        }
       
        /**
         * Metodo getIdRechazoOrden
         *  @return BigDecimal
         */

        public BigDecimal getIdRechazoOrden() {
            return idRechazoOrden;
        }

        /**
         * Metodo setIdOrden
         * @param idOrden
         */
        public void setIdOrden(BigDecimal idOrden) {
            this.idOrden = idOrden;
        }

        /**
         * Metodo getIdOrden
         *  @return BigDecimal
         */
        public BigDecimal getIdOrden() {
            return idOrden;
        }

        /**
         * Metodo setDescripcion
         * @param descripcion
         */
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        /**
         * Metodo getDescripcion
         *  @return String
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Metodo setRfcFirmante
         * @param rfcFirmante
         */
        public void setRfcFirmante(String rfcFirmante) {
            this.rfcFirmante = rfcFirmante;
        }

        /**
         * Metodo getRfcFirmante
         *  @return String
         */
        public String getRfcFirmante() {
            return rfcFirmante;
        }


        /**
         * Metodo setFechaInicio
         * @param fechaInicio
         */
        public void setFechaInicio(final Date fechaInicio) {
            this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
            
        }


        /**
         * Metodo getFechaInicio
         *  @return Date
         */
        public Date getFechaInicio() {
            return (fechaInicio != null) ? (Date)fechaInicio.clone() : null;
        }


        /**
         * Metodo setFechaFin
         * @param fechaFin
         */
        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
        }

        /**
         * Metodo getFechaFin
         *  @return Date
         */
        public Date getFechaFin() {
            return (fechaFin != null) ? (Date)fechaFin.clone() : null;
        }


        /**
         * Metodo setEstatus
         * @param estatus
         */
        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }


        /**
         * Metodo getEstatus
         *  @return String
         */
        public String getEstatus() {
            return estatus;
        }
}
