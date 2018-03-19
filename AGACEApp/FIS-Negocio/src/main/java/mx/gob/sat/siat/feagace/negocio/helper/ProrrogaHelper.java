package mx.gob.sat.siat.feagace.negocio.helper;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

/**
 * Clase Helper para las operaciones de las Prorrogas
 *
 * @author eolf
 */
@Component
public class ProrrogaHelper {

    private static final Logger LOG = Logger.getLogger(ProrrogaHelper.class.getName());

    private static final String ERROR = "no puede ser null.";

    /**
     * Validacion de una lista
     *
     * @author eolf
     * @param lista
     * @param extra
     */
    public void validarLista(final List<?> lista, final String extra) {
        LOG.debug("[validarLista]");
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("La lista de '" + extra + "' " + ERROR);
        }
    }

}
