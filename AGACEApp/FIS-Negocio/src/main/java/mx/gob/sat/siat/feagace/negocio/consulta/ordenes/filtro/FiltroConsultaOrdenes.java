package mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FiltroOrdenes;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.CifrasOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

public class FiltroConsultaOrdenes extends BO<FiltroConsultaOrdenes> implements Serializable {

    private static final long serialVersionUID = 56305159010632372L;

    private List<AraceDTO> unidadAdmtvaDesahogoFiltro;
    private List<TipoEstatusEnum> estatusFiltro;
    private List<EmpleadoDTO> lstEmpleadosSubordinados;
    private SemaforoEnum semaforoSeleccionado;
    private EmpleadoDTO empleadoConsultaFiltro;
    private AgaceOrden ordenFiltro;
    private TipoEmpleadoEnum tipoEmpleadoConsulta;
    private List<TipoMetodoEnum> metodoFiltro;
    private BigDecimal cifraDe;
    private BigDecimal cifraHasta;
    private List<CifrasOrdenesEnum> cifraFiltro;
    private boolean visibilidadACPPCE;
    private boolean consultaEmpleado;
    private boolean consultaCifras;
    private Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> gruposDeEstatusValidos;

    public List<AraceDTO> getUnidadAdmtvaDesahogoFiltro() {
        return unidadAdmtvaDesahogoFiltro;
    }

    public void setUnidadAdmtvaDesahogoFiltro(List<AraceDTO> unidadAdmtvaDesahogoFiltro) {
        this.unidadAdmtvaDesahogoFiltro = unidadAdmtvaDesahogoFiltro;
    }

    public List<TipoEstatusEnum> getEstatusFiltro() {
        return estatusFiltro;
    }

    public void setEstatusFiltro(List<TipoEstatusEnum> estatusFiltro) {
        this.estatusFiltro = estatusFiltro;
    }

    public List<EmpleadoDTO> getLstEmpleadosSubordinados() {
        return lstEmpleadosSubordinados;
    }

    public void setLstEmpleadosSubordinados(List<EmpleadoDTO> lstEmpleadosSubordinados) {
        this.lstEmpleadosSubordinados = lstEmpleadosSubordinados;
    }

    public EmpleadoDTO getEmpleadoConsultaFiltro() {
        return empleadoConsultaFiltro;
    }

    public void setEmpleadoConsultaFiltro(EmpleadoDTO empleadoConsultaFiltro) {
        this.empleadoConsultaFiltro = empleadoConsultaFiltro;
    }

    public AgaceOrden getOrdenFiltro() {
        return ordenFiltro;
    }

    public void setOrdenFiltro(AgaceOrden ordenFiltro) {
        this.ordenFiltro = ordenFiltro;
    }

    public TipoEmpleadoEnum getTipoEmpleadoConsulta() {
        return tipoEmpleadoConsulta;
    }

    public void setTipoEmpleadoConsulta(TipoEmpleadoEnum tipoEmpleadoConsulta) {
        this.tipoEmpleadoConsulta = tipoEmpleadoConsulta;
    }

    public void setVisibilidadACPPCE(boolean visibilidadACPPCE) {
        this.visibilidadACPPCE = visibilidadACPPCE;
    }

    public boolean isVisibilidadACPPCE() {
        return visibilidadACPPCE;
    }

    public SemaforoEnum getSemaforoSeleccionado() {
        return semaforoSeleccionado;
    }

    public void setSemaforoSeleccionado(SemaforoEnum semaforoSeleccionado) {
        this.semaforoSeleccionado = semaforoSeleccionado;
    }

    public List<TipoMetodoEnum> getMetodoFiltro() {
        return metodoFiltro;
    }

    public void setMetodoFiltro(List<TipoMetodoEnum> metodoFiltro) {
        this.metodoFiltro = metodoFiltro;
    }

    public BigDecimal getCifraDe() {
        return cifraDe;
    }

    public void setCifraDe(BigDecimal cifraDe) {
        this.cifraDe = cifraDe;
    }

    public BigDecimal getCifraHasta() {
        return cifraHasta;
    }

    public void setCifraHasta(BigDecimal cifraHasta) {
        this.cifraHasta = cifraHasta;
    }

    public List<CifrasOrdenesEnum> getCifraFiltro() {
        return cifraFiltro;
    }

    public void setCifraFiltro(List<CifrasOrdenesEnum> cifraFiltro) {
        this.cifraFiltro = cifraFiltro;
    }

    public boolean isConsultaEmpleado() {
        return consultaEmpleado;
    }

    public void setConsultaEmpleado(boolean consultaEmpleado) {
        this.consultaEmpleado = consultaEmpleado;
    }

    public boolean isConsultaCifras() {
        return consultaCifras;
    }

    public void setConsultaCifras(boolean consultaCifras) {
        this.consultaCifras = consultaCifras;
    }

    public Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> getGruposDeEstatusValidos() {
        return gruposDeEstatusValidos;
    }

    public void setGruposDeEstatusValidos(Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> gruposDeEstatusValidos) {
        this.gruposDeEstatusValidos = gruposDeEstatusValidos;
    }

    public FiltroOrdenes getFiltroDAO() {
        FiltroOrdenes filtroDAO = new FiltroOrdenes();

        filtroDAO.setUnidadAdmtvaDesahogoFiltro(unidadAdmtvaDesahogoFiltro);
        filtroDAO.setEstatusFiltro(estatusFiltro);
        filtroDAO.setLstEmpleadosSubordinados(lstEmpleadosSubordinados);
        filtroDAO.setCifraDe(cifraDe);
        filtroDAO.setCifraHasta(cifraHasta);
        filtroDAO.setCifraFiltro(cifraFiltro);
        filtroDAO.setEmpleadoConsultaFiltro(empleadoConsultaFiltro);
        filtroDAO.setOrdenFiltro(ordenFiltro);
        filtroDAO.setMetodoFiltro(metodoFiltro);
        filtroDAO.setVisibilidadACPPCE(visibilidadACPPCE);
        filtroDAO.setConsultaEmpleado(consultaEmpleado);
        filtroDAO.setConsultaCifras(consultaCifras);
        filtroDAO.setGruposDeEstatusValidos(gruposDeEstatusValidos);
        filtroDAO.setTipoEmpleadoConsulta(tipoEmpleadoConsulta);

        return filtroDAO;
    }

}
