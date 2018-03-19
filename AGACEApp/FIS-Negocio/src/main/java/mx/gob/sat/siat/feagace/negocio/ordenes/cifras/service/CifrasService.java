package mx.gob.sat.siat.feagace.negocio.ordenes.cifras.service;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.TotalCifrasDTO;

public interface CifrasService {
    
    int CIFRAS_COBRADAS = 1;
            
    int CIFRAS_VIRTUALES = 2;
    
    int CIFRAS_LIQUIDADAS = 3;
    
    String DOCUMENTOS_COBRADAS = "cifras_cobradas/";
    
    String DOCUMENTOS_VIRTUALES = "cifras_virtuales/";
    
    String DOCUMENTOS_LIQUIDADAS = "cifras_liquidadas/";

    List<FececConcepto> getConceptoByImpuesto(BigDecimal idImpuesto);

    List<FececTipoImpuesto> getCatalogoImpuesto();

    List<FeceaCifraTipoCifraDTO> obtenerCifrasPorTipo(int tipoCifra);

    BigDecimal eliminaCifra(FecetCifraDTO cifra, BigDecimal idOrden);

    BigDecimal eliminaImpuesto(FeceaCifraImpuestoDTO cifraImpuesto);

    List<FececTipoParcialidadDTO> obtenerParcialidades();    

    BigDecimal insertarCifra(FecetCifraDTO cifras, AgaceOrden orden);

    List<TotalCifrasDTO> obtenerEncabezadoCifras(BigDecimal idOrden);

    boolean existeCifra(BigDecimal idOrden, BigDecimal tipoCifra, BigDecimal impuesto, int concepto);

    List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraImpuestoConcepto(BigDecimal idOrden, BigDecimal tipoCifra, BigDecimal impuesto, BigDecimal concepto);

    List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraTipoCifra(BigDecimal idOrden, BigDecimal tipoCifraTipoCifra);

    List<FecetCifraDTO> obtenerCifrasPorOrden(BigDecimal idOrden, BigDecimal tipoCifra);

    List<FecetDocCifraDTO> obtenerDocumentos(BigDecimal idCifraImpuesto);

    BigDecimal actualizarCifra(AgaceOrden orden, FecetCifraDTO cifras);

    FecetParcialidadCifraDTO obtenerParcialidadCifraImpuesto(BigDecimal idCifraImpuesto);
    
    List<FecetCifraDTO> obtenerEncabezadoCifrasHistorico(BigDecimal idTipoCifra, BigDecimal idOrden);
    
    List<FeceaCifraImpuestoDTO> obtenerImpuestosCifrasHistorico(BigDecimal idOrden,
            BigDecimal tipoCifraTipoCifra, BigDecimal consecutivo);
    
    FileInputStream getReporteConsulta(BigDecimal idOrden, BigDecimal tipoCifra);
}
