package mx.gob.sat.siat.feagace.vista.ordenes.helper;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenFirmada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;

import org.springframework.stereotype.Component;

@Component
public class FirmadoCadenasOriginalesHelper extends BaseHelper {

    private static final long serialVersionUID = 2006260054090132580L;

    public List<FecetDocOrden> creaListaDocumentosPdfOrden(OrdenFirmada orden) {
        List<FecetDocOrden> listaDoc = new ArrayList<FecetDocOrden>();
        FecetDocOrden docOrden = new FecetDocOrden();
        docOrden.setFechaCreacion(orden.getFechaDocOrden());
        docOrden.setNombreArchivo(orden.getNombreArchivo());
        docOrden.setRutaArchivo(orden.getRutaArchivo());
        listaDoc.add(docOrden);
        return listaDoc;
    }

}
