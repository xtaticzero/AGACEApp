package mx.gob.sat.siat.feagace.negocio.insumos;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

import org.junit.Ignore;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsultaInsumosAsignadosServiceTest extends BaseTest {

    @Autowired
    private ConsultaInsumosAsignadosService consultaInsumosAsignadosService;

    
    @Test
    @Ignore
    public void getContadorInsumosAsignados() {
        try{
        System.out.println(consultaInsumosAsignadosService
                .getContadorInsumosAsignados(new EmpleadoDTO()));
        }catch (Exception ex){
            System.err.println("Occurio un error"+ex);
        }
    }
}
