package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CrearInsumoService {

    List<FececSubprograma> getCatalogoSubprograma(BigDecimal idGeneral);

    List<FececSector> getCatalogoSector(BigDecimal idGeneral);
    
    List<FececTipoInsumo> getCatalogoTipoInsumo(BigDecimal idGeneral);
    
    RegistroInsumosDto guardarInsumos(final List<FecetInsumo> listaInsumos) throws NegocioException;

    RegistroInsumosDto agregaInsumo(FecetInsumo fecetInsumo) throws NegocioException;

    RegistroInsumosDto actualizaInsumo(FecetInsumo fecetInsumo,
            List<FecetDocExpInsumo> documentosBorrar, List<FecetDocumento> documentoBorrarJustificacion) throws NegocioException;

    boolean validaExistenciaTemporal(final FecetInsumo fecetInsumoReferencia,
            final FecetInsumo fecetInsumo);

    List<FececUnidadAdministrativa> getUnidadesAdministritativas(EmpleadoDTO empleadoDTO);
    
    void eliminarInsumo(FecetInsumo insumo) ;

    String insertarRegistrosMasivos(FecetInsumo insumo, RegistroInsumosDto resultado);
    
    List<FecetDocumento> bucarDocumentoJustificacionById(BigDecimal idInsumo);
    
}
