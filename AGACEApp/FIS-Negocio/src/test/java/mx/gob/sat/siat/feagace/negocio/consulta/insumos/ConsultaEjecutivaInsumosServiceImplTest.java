/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.insumos;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaInsumosBO;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro.FiltroConsultaInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaInsumosServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaEjecutivaInsumosServiceImplTest extends BaseTest {

    @Autowired
    @Qualifier("consultaEjecutivaInsumosService")
    private ConsultaEjecutivaInsumosService consultaEjecutivaInsumos;

    private EmpleadoDTO empleado;
    private ConsultaEjecutivaInsumosBO consultaInsumoBO;

    @Before
    public void init() {
        empleado = new EmpleadoDTO();
        empleado.setRfc("FIMA631230CL3");
        //empleado.setRfc("FIMA63");
    }

    @Test
    @Ignore
    public void getAccesoEmpleadoAConsultaInsumos() {
        try {
            assert consultaEjecutivaInsumos != null;
            //consultaInsumoBO = consultaEjecutivaInsumos.getAccesoEmpleadoAConsultaInsumos(empleado);
            assert consultaInsumoBO != null;
            FiltroConsultaInsumos filtro = new FiltroConsultaInsumos();

//            filtro.setEstatusFiltro(consultaInsumoBO.getLstEstatusValidos());
//            filtro.setEmpleadoConsultaFiltro(consultaInsumoBO.getEmpleadoConsulta());
//            filtro.setUnidadAdmtvaDesahogoFiltro(consultaInsumoBO.getLstUnidadesAdministrativasDesahogo());
//            filtro.setDiasConcluirPlazo(null);

            consultaEjecutivaInsumos.consultarInsumos(consultaInsumoBO, filtro);
            //consultaEjecutivaInsumos.getInsumosPlazoXConcluir(consultaInsumoBO, 1);
            consultaEjecutivaInsumos.getInsumosXSemaforoEstatus(consultaInsumoBO, SemaforoEnum.SEMAFORO_BLANCO, null);
            //consultaEjecutivaInsumos.getInsumosXSemaforoEstatus(consultaInsumoBO, null, TipoEstatusEnum.INSUMO_REGISTRADO);
            consultaEjecutivaInsumos.getInsumosXSemaforoEstatus(consultaInsumoBO, SemaforoEnum.SEMAFORO_VERDE, TipoEstatusEnum.INSUMO_REGISTRADO);
            System.err.println("Total Estatus Filtrados : " + consultaInsumoBO.getTotalXEstatusFiltrado());

            //consultaEjecutivaInsumos.getInsumosXSemaforoEstatus(consultaInsumoBO, SemaforoEnum.SEMAFORO_VERDE, TipoEstatusEnum.INSUMO_REGISTRADO);
            //System.err.println("Total Estatus Filtrados : " + consultaInsumoBO.getTotalXEstatusFiltrado());
//consultaEjecutivaInsumos.getInsumosXEstado(consultaInsumoBO, TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_RECHAZADO);
//            List<TipoEstatusEnum> lstFiltroEstatus = new ArrayList<TipoEstatusEnum>();
//            List<SemaforoEnum> lstSemaforosEstatus = new ArrayList<SemaforoEnum>();
//            lstFiltroEstatus.add(TipoEstatusEnum.INSUMO_REGISTRADO);
//            lstSemaforosEstatus.add(SemaforoEnum.SEMAFORO_AZUL);
//            try {
//                consultaEjecutivaInsumos.getInsumosXEmpleadoSemaforoEstadoPlazo(consultaInsumoBO, consultaInsumoBO.getLstUnidadesAdministrativasDesahogo(),
//                        consultaInsumoBO.getEmpleadoConsulta(), lstSemaforosEstatus, lstFiltroEstatus, null);
//
//            } catch (Exception e) {
//                logger.error(e);
//            }
            for (FecetInsumo insumo : consultaInsumoBO.getLstInsumoResult()) {
                if (insumo.getPlazoRestante() == 0) {
                    System.err.println("Plazo: " + insumo.getPlazoRestante());
                }
                if (insumo.getPlazoRestante() < 0) {
                    System.err.println("Plazo < a 0 : " + insumo.getPlazoRestante());
                }
            }

//            for (FecetInsumo col : consultaInsumoBO.getLstInsumoResult()) {
//                consultaEjecutivaInsumos.consultaHistoricoInsumos(consultaInsumoBO, col);
//                if (consultaInsumoBO.getInsumoDetalle() != null && consultaInsumoBO.getInsumoDetalle().getLstRetroalimentacion() != null && !consultaInsumoBO.getInsumoDetalle().getLstRetroalimentacion().isEmpty()) {
//                    if(consultaInsumoBO.getInsumoDetalle().getIdRegistro().equals("ES0120160021")){
//                        System.err.println("Es");                    
//                    }
//                }
//                if (consultaInsumoBO.getInsumoDetalle() != null && consultaInsumoBO.getInsumoDetalle().getRechazoInsumo() != null) {
//                    int a = consultaInsumoBO.getInsumoDetalle().getRechazoInsumo().getLstDocsRechazoInsumo().size();
//                }
//            }
//
//            for (FecetInsumo col : consultaInsumoBO.getLstInsumosXFiltro()) {
//                logger.info(col);
//            }
        } catch (ConsultaEjecutivaInsumosServiceException ex) {
            logger.error(ex.getMessage());
        }

    }

}
