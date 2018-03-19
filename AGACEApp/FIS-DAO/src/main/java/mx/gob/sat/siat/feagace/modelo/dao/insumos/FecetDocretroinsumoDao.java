/**
 * Todos los Derechos Reservados 2013 SAT. Servicio de Administracion Tributaria
 * (SAT). Este software contiene informacion propiedad exclusiva del SAT
 * considerada Confidencial. Queda totalmente prohibido su uso o divulgacion en
 * forma parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumoPk;

public interface FecetDocretroinsumoDao {

    FecetDocretroinsumoPk insert(FecetDocretroinsumo dto, EmpleadoDTO empleadoDTO);

    void update(FecetDocretroinsumoPk pk, FecetDocretroinsumo dto);

    void delete(FecetDocretroinsumoPk pk);

    FecetDocretroinsumo findByPrimaryKey(BigDecimal idDocretroinsumo);

    List<FecetDocretroinsumo> findAll();

    List<FecetDocretroinsumo> findByFecetRetroalimentacionInsumo(
            BigDecimal idRetroalimentacionInsumo, BigDecimal idTipoEmpleado);

    List<FecetDocretroinsumo> findWhereIdDocretroinsumoEquals(BigDecimal idDocretroinsumo);

    List<FecetDocretroinsumo> findWhereIdRetroalimentacionInsumoEquals(
            BigDecimal idRetroalimentacionInsumo);

    List<FecetDocretroinsumo> findWhereRutaArchivoEquals(String rutaArchivo);

    List<FecetDocretroinsumo> findWhereFechaCreacionEquals(Date fechaCreacion);

    FecetDocretroinsumo findByPrimaryKey(FecetDocretroinsumoPk pk);

    List<FecetDocretroinsumo> obtenerRetroalimentacionByTipoEmpleado(BigDecimal idRetroalimentacionInsumo,
            BigDecimal tipoEmpleado);

    FecetDocretroinsumoPk insert(FecetDocretroinsumo documento);

}
