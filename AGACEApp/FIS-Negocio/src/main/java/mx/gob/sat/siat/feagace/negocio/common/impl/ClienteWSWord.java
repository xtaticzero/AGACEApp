package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.rmi.RemoteException;
import java.util.Calendar;

import mx.gob.sat.siat.feagace.negocio.util.Propiedades;

import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.Columna;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.Esquema;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.EsquemaCampo;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.Plantillas;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.RespuestaArchivo;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.RespuestaEsquemaCampo;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.RespuestaInformacionEsquema;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.RespuestaInformacionPlantilla;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.RespuestaPlantillas;
import org.datacontract.schemas._2004._07.Asf_Sat_Admin_Plantillas_Entidades_Objetos.Resultado;
import org.tempuri.IPlantillador;
import org.tempuri.IPlantilladorProxy;


public class ClienteWSWord implements IPlantillador {   
    
    /**
     * Proxy del servicio web.
     */
    private IPlantilladorProxy proxy;

    public ClienteWSWord() {
        this.proxy = new IPlantilladorProxy();
        proxy.setEndpoint(Propiedades.get("plantillador.url"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaPlantillas getPlantillas(final Integer sistemaId) throws RemoteException {
        return this.proxy.getPlantillas(sistemaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaInformacionPlantilla getInfoPlantilla(final Integer plantillaId) throws RemoteException {
        return this.proxy.getInfoPlantilla(plantillaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaEsquemaCampo getInfoEsquemaCampo(final Integer esquemaId) throws RemoteException {
        return this.proxy.getInfoEsquemaCampo(esquemaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaInformacionEsquema getInfoEsquema(final Integer esquemaId) throws RemoteException {
        return this.proxy.getInfoEsquema(esquemaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaInformacionEsquema getInfoEsquemaError(final Integer esquemaId) throws RemoteException {
        return this.proxy.getInfoEsquemaError(esquemaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaArchivo getArchivoPrueba(final Integer plantillaId,
                                                   final Integer tipoId) throws RemoteException {
        return this.proxy.getArchivoPrueba(plantillaId, tipoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RespuestaArchivo getArchivo(final Integer plantillaId, final Integer tipoId,
                                             final EsquemaCampo[] contenido) throws RemoteException {
        return this.proxy.getArchivo(plantillaId, tipoId, contenido);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Resultado addEsquema(final Esquema esquema) throws RemoteException {
        return this.proxy.addEsquema(esquema);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Resultado addEsquemaColumna(final Columna[] columna) throws RemoteException {
        return this.proxy.addEsquemaColumna(columna);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespuestaEsquemaCampo getPlantillaCampo(Integer plantillaId) throws RemoteException {
        return this.proxy.getPlantillaCampo(plantillaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespuestaEsquemaCampo getPlantillaCampoFecha(Integer plantillaId, Calendar date) throws RemoteException {
        return this.proxy.getPlantillaCampoFecha(plantillaId, date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespuestaArchivo getOriginalWord(Integer plantillaId) throws RemoteException {
        return this.proxy.getOriginalWord(plantillaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespuestaArchivo convertToPdf(Plantillas documento) throws RemoteException {
        return this.proxy.convertToPdf(documento);
    }

    @Override
    public RespuestaArchivo getArchivoContent(Integer plantillaId, Integer tipoId,
                                              EsquemaCampo[] contenido) throws RemoteException {
        // TODO Auto-generated method stub
        return this.proxy.getArchivo(plantillaId, tipoId, contenido);
    }

    @Override
    public RespuestaArchivo convertToPdfContent(Plantillas documento) throws RemoteException {
        // TODO Auto-generated method stub
        return this.proxy.convertToPdfContent(documento);
    }   
    
}
