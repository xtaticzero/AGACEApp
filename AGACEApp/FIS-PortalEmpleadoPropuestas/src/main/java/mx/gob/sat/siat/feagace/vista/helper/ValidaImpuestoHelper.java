package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

@Component
public class ValidaImpuestoHelper implements Serializable {

    @SuppressWarnings("compatibility:1041684673700951343")
    private static final long serialVersionUID = 1L;

    private static final String NA = "N/A";

    public CargaMasivaPropuestasDTO validaImpuesto(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            CargaMasivaPropuestasService cargaMasivaPropuestasService) {
        FecetImpuesto fecetImpuesto;
        List<FecetImpuesto> listaImpuestos = new ArrayList<FecetImpuesto>();
        int contInicial = Constantes.ENTERO_DIECISEIS;

        while (true) {
            HSSFCell impuesto = row.getCell(contInicial);
            HSSFCell concepto = row.getCell(contInicial + 1);
            HSSFCell monto = row.getCell(contInicial + 2);

            boolean[] condiciones = new boolean[] {
                    (impuesto != null && impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING
                            && impuesto.getCellType() != HSSFCell.CELL_TYPE_BLANK),
                    (monto != null && monto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                            && monto.getCellType() != HSSFCell.CELL_TYPE_BLANK),
                    (concepto != null && concepto.getCellType() == HSSFCell.CELL_TYPE_STRING
                            && concepto.getCellType() != HSSFCell.CELL_TYPE_BLANK) };

            if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
                if (validaConcepto(concepto, impuesto, cargaMasivaPropuestasService, cargaMasivaPropuestasDTO,
                        contInicial)) {
                    String montoStrt = fmt(monto.getNumericCellValue());
                    if (isNumeric(montoStrt, cargaMasivaPropuestasDTO, impuesto)) {
                        if (cargaMasivaPropuestasDTO.isValida()) {
                            try {
                                if (validaImpuestoAplicable(impuesto, monto, cargaMasivaPropuestasDTO, contInicial)) {
                                    fecetImpuesto = new FecetImpuesto();
                                    fecetImpuesto.setIdTipoImpuesto(
                                            cargaMasivaPropuestasService.validaImpuesto(impuesto.getStringCellValue()));
                                    fecetImpuesto.setMonto(BigDecimal.valueOf(monto.getNumericCellValue()));
                                    fecetImpuesto.setIdConcepto(cargaMasivaPropuestasService
                                            .getidConcepto(concepto.getStringCellValue().trim()));
                                    validaImpuestoNA(listaImpuestos, fecetImpuesto, cargaMasivaPropuestasDTO);
                                    if (cargaMasivaPropuestasDTO.getDescripcionAddImpuesto()
                                            .equals(ConstantesPropuestasMasivas.MSJ_IMPUESTO_VALIDO)) {
                                        if (validaConceptosRepetidos(listaImpuestos, fecetImpuesto)) {
                                            cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                                            cargaMasivaPropuestasDTO.setDescripcionError(
                                                    ConstantesPropuestasMasivas.MSJ_CAMPO_IMPUESTO_DUPLICADO);
                                            cargaMasivaPropuestasDTO.setValida(false);
                                            break;
                                        }
                                        listaImpuestos.add(fecetImpuesto);
                                    } else {
                                        cargaMasivaPropuestasDTO.setCell(contInicial);
                                        break;
                                    }
                                } else {
                                    cargaMasivaPropuestasDTO.setValida(false);
                                    break;
                                }
                            } catch (NegocioException e) {
                                cargaMasivaPropuestasDTO.setCell(contInicial);
                                cargaMasivaPropuestasDTO.setDescripcionError("Error al consultar el impuesto");
                                cargaMasivaPropuestasDTO.setValida(false);
                                break;
                            }
                        } else {
                            break;
                        }

                    } else {
                        cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                        cargaMasivaPropuestasDTO.setValida(false);
                        break;
                    }
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;
                }
            } else if (listaImpuestos.isEmpty()) {
                if (concepto == null || concepto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                } else if (monto == null || monto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                } else if (impuesto == null || impuesto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    cargaMasivaPropuestasDTO.setCell(contInicial);
                }

                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_OBLIGATORIO);
                cargaMasivaPropuestasDTO.setValida(false);
                break;
            } else {
                cargaMasivaPropuestasDTO.setValida(true);
                break;
            }

            contInicial = monto.getColumnIndex() + 1;

        } // While
        cargaMasivaPropuestasDTO.setFecetImpuestos(listaImpuestos);
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaImpuestoCI(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            CargaMasivaPropuestasService cargaMasivaPropuestasService) {
        FecetImpuesto fecetImpuesto;
        List<FecetImpuesto> listaImpuestos = new ArrayList<FecetImpuesto>();
        int contInicial = Constantes.ENTERO_TRECE;

        while (true) {
            HSSFCell impuesto = row.getCell(contInicial);
            HSSFCell concepto = row.getCell(contInicial + 1);
            HSSFCell monto = row.getCell(contInicial + 2);

            boolean[] condiciones = new boolean[] {
                    (impuesto != null && impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING
                            && impuesto.getCellType() != HSSFCell.CELL_TYPE_BLANK),
                    (monto != null && monto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                            && monto.getCellType() != HSSFCell.CELL_TYPE_BLANK),
                    (concepto != null && concepto.getCellType() == HSSFCell.CELL_TYPE_STRING
                            && concepto.getCellType() != HSSFCell.CELL_TYPE_BLANK) };

            if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
                if (validaConcepto(concepto, impuesto, cargaMasivaPropuestasService, cargaMasivaPropuestasDTO,
                        contInicial)) {
                    String montoStrt = fmt(monto.getNumericCellValue());
                    if (isNumeric(montoStrt, cargaMasivaPropuestasDTO, impuesto)) {
                        if (cargaMasivaPropuestasDTO.isValida()) {
                            try {
                                if (validaImpuestoAplicable(impuesto, monto, cargaMasivaPropuestasDTO, contInicial)) {
                                    fecetImpuesto = new FecetImpuesto();
                                    fecetImpuesto.setIdTipoImpuesto(
                                            cargaMasivaPropuestasService.validaImpuesto(impuesto.getStringCellValue()));
                                    fecetImpuesto.setMonto(BigDecimal.valueOf(monto.getNumericCellValue()));
                                    fecetImpuesto.setIdConcepto(cargaMasivaPropuestasService
                                            .getidConcepto(concepto.getStringCellValue().trim()));

                                    validaImpuestoNA(listaImpuestos, fecetImpuesto, cargaMasivaPropuestasDTO);
                                    if (cargaMasivaPropuestasDTO.getDescripcionAddImpuesto()
                                            .equals(ConstantesPropuestasMasivas.MSJ_IMPUESTO_VALIDO)) {
                                        if (validaConceptosRepetidos(listaImpuestos, fecetImpuesto)) {
                                            cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                                            cargaMasivaPropuestasDTO.setDescripcionError(
                                                    ConstantesPropuestasMasivas.MSJ_CAMPO_IMPUESTO_DUPLICADO);
                                            cargaMasivaPropuestasDTO.setValida(false);
                                            break;
                                        }
                                        listaImpuestos.add(fecetImpuesto);
                                    } else {
                                        cargaMasivaPropuestasDTO.setCell(contInicial);
                                        break;
                                    }
                                } else {
                                    cargaMasivaPropuestasDTO.setValida(false);
                                    break;
                                }
                            } catch (NegocioException e) {
                                cargaMasivaPropuestasDTO.setCell(contInicial);
                                cargaMasivaPropuestasDTO.setDescripcionError("Error al consultar el impuesto");
                                cargaMasivaPropuestasDTO.setValida(false);
                                break;
                            }
                        } else {

                            break;
                        }

                    } else {
                        cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                        cargaMasivaPropuestasDTO.setValida(false);
                        break;
                    }
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;
                }
            } else if (listaImpuestos.isEmpty()) {
                if (concepto == null || concepto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                } else if (monto == null || monto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                } else if (impuesto == null || impuesto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    cargaMasivaPropuestasDTO.setCell(contInicial);
                }

                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_OBLIGATORIO);
                cargaMasivaPropuestasDTO.setValida(false);
                break;
            } else {
                cargaMasivaPropuestasDTO.setValida(true);
                break;
            }
            contInicial = monto.getColumnIndex() + 1;
        } // While
        cargaMasivaPropuestasDTO.setFecetImpuestos(listaImpuestos);
        return cargaMasivaPropuestasDTO;
    }

    public static String fmt(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }

    private static boolean isNumeric(String cadena, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            HSSFCell impuesto) {
        boolean isMontoValido = false;

        if (!impuesto.getStringCellValue().equals(NA)) {
            try {
                Integer monto = Integer.parseInt(cadena);
                if (monto <= 0) {
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_MAYOR_CERO);
                } else {
                    isMontoValido = true;
                }
            } catch (NumberFormatException nfe) {
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_ENTEROS);
            }
        } else {
            isMontoValido = true;
        }

        return isMontoValido;
    }

    private static boolean validaConceptosRepetidos(List<FecetImpuesto> lista, FecetImpuesto impuesto) {
        for (FecetImpuesto imp : lista) {
            if (imp.getIdTipoImpuesto().equals(impuesto.getIdTipoImpuesto())
                    && imp.getIdConcepto().equals(impuesto.getIdConcepto())) {
                return true;
            }
        }
        return false;
    }

    private boolean validaImpuestoAplicable(HSSFCell impuesto, HSSFCell monto,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int contInicial) {

        boolean isImpuestoAplicable = false;

        if (monto != null && impuesto != null) {
            String montoStr = fmt(monto.getNumericCellValue());
            Integer montoTmp = Integer.parseInt(montoStr);
            String impuestoTmp = impuesto.getStringCellValue();

            if (!impuestoTmp.equals(NA)) {
                if (montoTmp > 0) {
                    isImpuestoAplicable = true;
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                    isImpuestoAplicable = false;
                }
            } else {
                if (montoTmp == 0) {
                    isImpuestoAplicable = true;
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                    isImpuestoAplicable = false;
                }
            }
        }

        return isImpuestoAplicable;
    }

    private boolean validaConcepto(HSSFCell concepto, HSSFCell impuesto,
            CargaMasivaPropuestasService cargaMasivaPropuestasService,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int contInicial) {

        boolean isValido = false;

        try {

            BigDecimal idImpuesto = cargaMasivaPropuestasService.validaImpuesto(impuesto.getStringCellValue().trim());

            if (idImpuesto != null) {
                if (cargaMasivaPropuestasService.validaConceptoImpuesto(impuesto.getStringCellValue().trim(),
                        concepto.getStringCellValue().trim())) {
                    isValido = true;
                } else {
                    cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                    isValido = false;
                }
            }

        } catch (NegocioException e) {
            cargaMasivaPropuestasDTO.setCell(contInicial);
            cargaMasivaPropuestasDTO.setValida(false);
        }

        return isValido;
    }

    private void validaImpuestoNA(List<FecetImpuesto> listaImpuestos, FecetImpuesto impuestoNuevo,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        if (listaImpuestos == null || listaImpuestos.isEmpty()) {
            cargaMasivaPropuestasDTO.setValida(true);
            cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(ConstantesPropuestasMasivas.MSJ_IMPUESTO_VALIDO);
        } else {
            for (FecetImpuesto impuestoAnterior : listaImpuestos) {
                if (impuestoAnterior.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)
                        && impuestoNuevo.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO
                            .setDescripcionAddImpuesto(ConstantesPropuestasMasivas.MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO
                            .setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_IMPUESTO_DUPLICADO);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (impuestoAnterior.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)
                        && !impuestoNuevo.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO
                            .setDescripcionAddImpuesto(ConstantesPropuestasMasivas.MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_ERROR_IMPUESTO_NA);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (!impuestoAnterior.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)
                        && impuestoNuevo.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO
                            .setDescripcionAddImpuesto(ConstantesPropuestasMasivas.MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_ERROR_IMPUESTO_NA);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (!impuestoAnterior.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)
                        && !impuestoNuevo.getIdTipoImpuesto().equals(ConstantesPropuestasMasivas.IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO.setValida(true);
                    cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(ConstantesPropuestasMasivas.MSJ_IMPUESTO_VALIDO);
                }
            }
        }
    }
}
