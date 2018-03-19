package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececOrigenPropuesta;

public interface FececOrigenPropuestaDao {

    void insert(FececOrigenPropuesta dto);

    void update(FececOrigenPropuesta dto);

    void delete(FececOrigenPropuesta dto);

    FececOrigenPropuesta findByPrimaryKey(BigDecimal idOrigenPropuesta);

    List<FececOrigenPropuesta> findAll();

    List<FececOrigenPropuesta> findWhereIdOrigenPropuestaEquals(BigDecimal idOrigenPropuesta);

    List<FececOrigenPropuesta> findWhereDescripcionEquals(String descripcion);

    FececOrigenPropuesta findByPrimaryKey(FececOrigenPropuesta pk);
}
