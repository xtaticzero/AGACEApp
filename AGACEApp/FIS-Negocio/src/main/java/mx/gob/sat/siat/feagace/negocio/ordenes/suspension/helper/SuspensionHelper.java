package mx.gob.sat.siat.feagace.negocio.ordenes.suspension.helper;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;


/**
 * Clase Helper para las operaciones de Suspensiones
 * @author eolf
 */
@Component
public class SuspensionHelper {

    private static final Logger LOG = Logger.getLogger(SuspensionHelper.class.getName());

    private static final String ERROR = "no puede ser null.";

    /**
     * Metodo para validar una lista de objetos
     * @author eolf
     * @param lista
     * @throws IllegalArgumentException
     */
    public void validarLista(final List<?> lista, final String extra) {
        LOG.debug("[validarLista]");
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("La lista de '" + extra + "' " + ERROR);
        }
    }
}
