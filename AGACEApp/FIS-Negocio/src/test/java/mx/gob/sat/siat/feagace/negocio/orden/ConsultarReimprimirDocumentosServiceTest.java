package mx.gob.sat.siat.feagace.negocio.orden;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.negocio.ordenes.ConsultarReimprimirDocumentosService;
import org.junit.Ignore;

public class ConsultarReimprimirDocumentosServiceTest extends BaseTest {

	
	@Autowired
	private ConsultarReimprimirDocumentosService consultarReimprimirDocumentosService;
	
	@Test
        @Ignore
	public void buscarPromocionPorOrden(){
		Assert.notNull(consultarReimprimirDocumentosService.buscarPromocionPorOrden(BigDecimal.valueOf(999)));
	}
	
	@Test
        @Ignore
	public void buscarOficioPorOrden(){
		Assert.notNull(consultarReimprimirDocumentosService.buscarOficioPorOrden(BigDecimal.valueOf(999)));
	}
	
	@Test
        @Ignore
	public void buscarProrrogaPorOrden(){
		Assert.notNull(consultarReimprimirDocumentosService.buscarProrrogaPorOrden(BigDecimal.valueOf(999)));
	}
	
	@Test
        @Ignore
	public void buscarAlegatosPorPromocion(){
		Assert.notNull(consultarReimprimirDocumentosService.buscarAlegatosPorPromocion(BigDecimal.valueOf(1000)));
	}
}
