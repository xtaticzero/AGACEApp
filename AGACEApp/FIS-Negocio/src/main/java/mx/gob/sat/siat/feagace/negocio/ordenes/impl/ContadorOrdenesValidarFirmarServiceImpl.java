package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ValidarFirmarDoctoDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ContadorOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenPropuestasFirmante;
import mx.gob.sat.siat.feagace.negocio.ordenes.ContadorOrdenesValidarFirmarService;

@Service("contadorOrdenesValidarFirmarService")
public class ContadorOrdenesValidarFirmarServiceImpl extends OrdenesServiceBase implements ContadorOrdenesValidarFirmarService {

    private static final long serialVersionUID = 8710344543345115699L;

    @Autowired
    private transient ValidarFirmarDoctoDao validarFirmarDoctoDao;

    /**
     * Regresa una lista con los totales de las ordenes por validar y firmar, de
     * acuerdo al metodo asignado
     *
     * @param idEmpleado
     * @param rfcFirmante
     * @return
     */
    @Override
    public List<ContadorOrdenes> ordenesPorValidarFirmar(BigDecimal idEmpleado, String rfcFirmante) {
        return getAgaceOrdenDao().numeroOrdenesValidarFirmar(idEmpleado, rfcFirmante);
    }

    @Override
    public ResumenPropuestasFirmante obtenerResumenPropuestasFirmante(String rfcFirmante) {
        return validarFirmarDoctoDao.obtenerResumenPropuestasFirmante(rfcFirmante).get(0);
    }

}
