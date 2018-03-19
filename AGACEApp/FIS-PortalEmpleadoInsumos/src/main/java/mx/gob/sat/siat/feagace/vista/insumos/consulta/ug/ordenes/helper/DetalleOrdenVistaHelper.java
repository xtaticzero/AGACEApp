package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper;

import java.io.Serializable;

public class DetalleOrdenVistaHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8323147267248527628L;

    private boolean pnlDocRequerida;
    private boolean pnlOficios;
    private boolean pnlSolContrib;
    private boolean pnlSolContribHist;
    private boolean pnlRepreLegal;
    private boolean pnlAgenteAdnl;
    private boolean pnlPapelesTrab;
    private boolean pnlOficiosFirm;
    private boolean pnlOficiosHist;

    public boolean isPnlDocRequerida() {
        return pnlDocRequerida;
    }

    public void setPnlDocRequerida(boolean pnlDocRequerida) {
        this.pnlDocRequerida = pnlDocRequerida;
    }

    public boolean isPnlOficios() {
        return pnlOficios;
    }

    public void setPnlOficios(boolean pnlOficios) {
        this.pnlOficios = pnlOficios;
    }

    public boolean isPnlSolContrib() {
        return pnlSolContrib;
    }

    public void setPnlSolContrib(boolean pnlSolContrib) {
        this.pnlSolContrib = pnlSolContrib;
    }

    public boolean isPnlRepreLegal() {
        return pnlRepreLegal;
    }

    public void setPnlRepreLegal(boolean pnlRepreLegal) {
        this.pnlRepreLegal = pnlRepreLegal;
    }

    public boolean isPnlAgenteAdnl() {
        return pnlAgenteAdnl;
    }

    public void setPnlAgenteAdnl(boolean pnlAgenteAdnl) {
        this.pnlAgenteAdnl = pnlAgenteAdnl;
    }

    public boolean isPnlPapelesTrab() {
        return pnlPapelesTrab;
    }

    public void setPnlPapelesTrab(boolean pnlPapelesTrab) {
        this.pnlPapelesTrab = pnlPapelesTrab;
    }

    public boolean isPnlOficiosFirm() {
        return pnlOficiosFirm;
    }

    public void setPnlOficiosFirm(boolean pnlOficiosFirm) {
        this.pnlOficiosFirm = pnlOficiosFirm;
    }

    public boolean isPnlOficiosHist() {
        return pnlOficiosHist;
    }

    public void setPnlOficiosHist(boolean pnlOficiosHist) {
        this.pnlOficiosHist = pnlOficiosHist;
    }

    public boolean isPnlSolContribHist() {
        return pnlSolContribHist;
    }

    public void setPnlSolContribHist(boolean pnlSolContribHist) {
        this.pnlSolContribHist = pnlSolContribHist;
    }

}
