/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.propuestas;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEstatusAccionesBO;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaPropuestasServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaEjecutivaPropuestasServiceTest extends BaseTest {

    @Autowired
    @Qualifier("consultaEjecutivaPropuestasService")
    private ConsultaEjecutivaPropuestasService consultaEjecutivaPropuestas;

    private EmpleadoDTO empleado;
    private ConsultaEjecutivaPropuestasBO consultaPropuestasBO;

    @Before
    public void init() {
        empleado = new EmpleadoDTO();
        //Central
        //empleado.setRfc("BAVA650912QD7");
        //Firmante
        empleado.setRfc("MIAA590116HY5");
        //empleado.setRfc("MARA750609L63");
        //empleado.setRfc("AUGY800716AJ4");
    }

    @Test
    @Ignore
    public void testAccesoUsuario() {
        try {
            assert consultaEjecutivaPropuestas != null;
            consultaPropuestasBO = consultaEjecutivaPropuestas.getAccesoEmpleadoAConsultaPropuestas(empleado);
            assert consultaPropuestasBO != null;
            for (Entry<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO> grupo : consultaPropuestasBO.getGruposDeEstatusValidos().entrySet()) {
                System.err.println("Grupo : " + grupo.getKey().getDescripcion());
                for (TipoEstatusEnum estatusGrupo : grupo.getValue().getEstatus()) {
                    System.err.println("Estatus grupo : " + estatusGrupo.getDescripcion());
                }
                for (TipoAccionPropuesta accion : grupo.getValue().getAcciones()) {
                    System.err.println("Accion propuesta : " + accion.getDescriopcion());
                }
            }
            String query = PropuestasSQL.SQL_DETALLE_PROPUESTA_HEDER;
            System.err.println(query);
            query = PropuestasSQL.SQL_INNER_DETALLE_PROPUESTA;
            System.err.println(query);

        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Test
    @Ignore
    public void getAccesoEmpleadoAConsultaPropuestas() {
        try {
            assert consultaEjecutivaPropuestas != null;
            consultaPropuestasBO = consultaEjecutivaPropuestas.getAccesoEmpleadoAConsultaPropuestas(empleado);
            assert consultaPropuestasBO != null;

            FiltroConsultaPropuestas filtro = new FiltroConsultaPropuestas();

            filtro.setUnidadAdmtvaDesahogoFiltro(consultaPropuestasBO.getLstUnidadesAdministrativasDesahogo());
            filtro.setEmpleadoConsultaFiltro(consultaPropuestasBO.getEmpleadoConsulta());

            logger.debug("=====================================================================");
            consultaEjecutivaPropuestas.consultarPropuestas(consultaPropuestasBO, filtro);

//            for (AraceDTO arace : consultaPropuestasBO.getLstUnidadesAdministrativasDesahogo()) {
//                AraceDTO[] araceArray = {arace};
//                filtro.setUnidadAdmtvaDesahogoFiltro(Arrays.asList(araceArray));
//
//                consultaEjecutivaPropuestas.consultarPropuestas(consultaPropuestasBO, filtro);
//
//                logger.debug("TotalXEmpleados " + consultaPropuestasBO.getTotalXEmpleados());
//                getDetalleEstatus();
//                logger.debug("TotalXEstatus " + consultaPropuestasBO.getTotalXEstatus());
//                logger.debug("TotalXEstatusFiltrado " + consultaPropuestasBO.getTotalXEstatusFiltrado());
//            }
            logger.debug("=====================================================================");
            filtro.setUnidadAdmtvaDesahogoFiltro(consultaPropuestasBO.getLstUnidadesAdministrativasDesahogo());
            consultaEjecutivaPropuestas.consultarPropuestasXEstatusoGrupo(consultaPropuestasBO, filtro, AgrupadorEstatusPropuestasEnum.PROPUESTA_ASIGNADA_A_FIRMANTE);

            logger.debug("=====================================================================");
            for (EmpleadoDTO subordinado : consultaPropuestasBO.getLstSubordinados()) {
                consultaEjecutivaPropuestas.consultarPropuestasXEmpleadoSubordinado(consultaPropuestasBO, subordinado);
                logger.debug("Lista X Subordinado : " + subordinado.getNombre() + " RFC " + subordinado.getRfc() + consultaPropuestasBO.getLstPropuestasXFiltro().size());
                for (FecetPropuesta col : consultaPropuestasBO.getLstPropuestasXFiltro()) {
                    System.err.println(col.getIdPropuesta());
                }
            }

        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage());
        }

    }

    private void getDetalleEstatus() {
        if (consultaPropuestasBO.getDetalleXEstatus() != null) {
            for (Map.Entry<AgrupadorEstatusPropuestasEnum, Integer> grupo : consultaPropuestasBO.getDetalleXEstatus().entrySet()) {
                logger.debug("Grupo" + grupo.getKey().getDescripcion() + " : " + grupo.getValue());
            }
        }

    }
}
