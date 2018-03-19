/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.client.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfEmpleado;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfFechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfINPC;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfUnidadAdministrativa;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfVacacion;
import mx.gob.sat.siat.ws.empleado.bean.DetalleEmpleado;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompleto;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompletoXml;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabilesXml;
import mx.gob.sat.siat.ws.empleado.bean.INPC;
import mx.gob.sat.siat.ws.empleado.bean.INPCXml;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimo;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimoXml;
import mx.gob.sat.siat.ws.empleado.bean.UnidadAdministrativa;
import mx.gob.sat.siat.ws.empleado.bean.UnidadAdministrativaXml;
import mx.gob.sat.siat.ws.empleado.bean.Vacacion;
import mx.gob.sat.siat.ws.empleado.bean.VacacionXml;
import mx.gob.sat.siat.ws.empleado.client.EmpleadoUnitedServiceClient;
import mx.gob.sat.siat.ws.empleado.constantes.TipoSistemaFiscalizacion;
import mx.gob.sat.siat.ws.empleado.exception.EmpleadoServiceClientException;
import mx.gob.sat.siat.ws.empleado.repository.EmpleadoClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("empleadoUnitedServiceClient")
@Scope("prototype")
public class EmpleadoUnitedServiceClientImpl implements Serializable, EmpleadoUnitedServiceClient {

    private static final String ERROR_CONSULTA_METODO = "Error al consultar el metodo del servicio : ";
    private static final String ERROR_TIPO_DE_MODULO = "El módulo no es un tipo válido : ";
    private static final String ERROR_FORMATO_PARAMETROS = "El formato de los parametros no es válido : ";
    private static final String ERROR_PARAMETROS = "Los parametros son null : ";

    private static final long serialVersionUID = 7634058849395723893L;

    @Value("${id.sistema.fiscalizacion}")
    private Integer idSistemaFiscalizacion;
    private TipoSistemaFiscalizacion tipoFiscalizacion;

    @PostConstruct
    private void init() {
        if (idSistemaFiscalizacion != null && tipoFiscalizacion == null) {
            tipoFiscalizacion = TipoSistemaFiscalizacion.getTipoSistemaFiscalizacion(idSistemaFiscalizacion);
        }
    }

    @Autowired
    @Qualifier("empleadoClientRepository")
    private EmpleadoClientRepository empleadoClientRepository;

    @Override
    public List<Vacacion> traeVacaciones(Integer periodo) throws EmpleadoServiceClientException {
        try {
            init();
            ArrayOfVacacion arrVacacion = empleadoClientRepository.traeVacaciones(periodo);
            if (arrVacacion != null) {
                List<Vacacion> lstVacaciones = new ArrayList<Vacacion>();
                for (VacacionXml varXml : arrVacacion.getVacacion()) {
                    Vacacion diaVacaciones = new Vacacion();
                    diaVacaciones.setDiaDescripcion(varXml.getDDescripcion().getValue());
                    diaVacaciones.setFechaFinal(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(varXml.getFFinal()));
                    diaVacaciones.setFechaInicial(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(varXml.getFInicial()));
                    diaVacaciones.setFechaPublicacion(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(varXml.getFPublicacion()));
                    diaVacaciones.setFechaPublicacionDof(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(varXml.getFechaPublicacionDof()));
                    diaVacaciones.setNombreResolucionMiscelanea(varXml.getNombreResolucionMiscelanea().getValue());
                    diaVacaciones.setNumeroRegla(varXml.getNumeroRegla().getValue());
                    diaVacaciones.setNumeroResolucionModif(varXml.getNumeroResolucionModif().getValue());

                    lstVacaciones.add(diaVacaciones);
                }
                return lstVacaciones;
            }
            return new ArrayList<Vacacion>();
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public List<FechasHabileseInhabiles> getCatalogoDiasHabilesInhabiles(Date fechaInicio, Date fechaFin) throws EmpleadoServiceClientException {

        try {
            init();
            List<FechasHabileseInhabiles> lstFechas;
            ArrayOfFechasHabileseInhabiles arrOfFechas = empleadoClientRepository.getCatalogoDiasHabilesInhabiles(
                    EmpleadoClientHelper.getXMLGregorianCalendarFromDate(fechaInicio),
                    EmpleadoClientHelper.getXMLGregorianCalendarFromDate(fechaFin));

            if (arrOfFechas != null) {
                lstFechas = new ArrayList<FechasHabileseInhabiles>();

                for (FechasHabileseInhabilesXml diasInhabiles : arrOfFechas.getFechasHabileseInhabiles()) {
                    FechasHabileseInhabiles diaInhabil = new FechasHabileseInhabiles();
                    diaInhabil.setFecha(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(diasInhabiles.getFecha()));
                    lstFechas.add(diaInhabil);
                }

                return lstFechas;

            }

            return new ArrayList<FechasHabileseInhabiles>();
        } catch (DatatypeConfigurationException ex) {
            throw new EmpleadoServiceClientException(ERROR_FORMATO_PARAMETROS, ex);
        } catch (Exception ex) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, ex);
        }

    }

    @Override
    public SalarioMinimo traeSalario() throws EmpleadoServiceClientException {
        try {
            init();
            SalarioMinimoXml salario = empleadoClientRepository.traeSalario();
            if (salario != null) {
                SalarioMinimo salarioMinimo = new SalarioMinimo();
                salarioMinimo.setPublicacion(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(salario.getPublicacion()));
                salarioMinimo.setValor(salario.getValor());
                return salarioMinimo;
            }
            return null;
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public List<INPC> traeINPC(Integer anio) throws EmpleadoServiceClientException {
        try {
            init();
            List<INPC> lstInpc;
            ArrayOfINPC arrInpc = empleadoClientRepository.traeINPC(anio);

            if (arrInpc != null) {
                lstInpc = new ArrayList<INPC>();

                for (INPCXml inpcXml : arrInpc.getINPC()) {
                    INPC inpc = new INPC();

                    inpc.setAnio(inpcXml.getAnio());
                    inpc.setIndice(inpcXml.getIndice());
                    inpc.setMes(inpcXml.getMes());
                    inpc.setPublicacion(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(inpcXml.getPublicacion()));

                    lstInpc.add(inpc);
                }

                return lstInpc;
            }
            return null;
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public List<Empleado> getEmpleadosXUnidadAdmva(TipoSistemaFiscalizacion tipoSistema,
            Integer claveALAF,
            Integer clavePerfil,
            Integer claveModulo) throws EmpleadoServiceClientException {
        try {
            init();
            if (tipoSistema == null) {
                throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
            }

            ArrayOfEmpleado arrEmpleado = empleadoClientRepository.getEmpleadosXUnidadAdmva(tipoSistema.getIdSistemaFiscalizacion(), claveALAF, clavePerfil, claveModulo);

            if (arrEmpleado != null) {
                return EmpleadoClientHelper.getLstEmpleadoFromLstEmpleadoXml(arrEmpleado.getEmpleado());
            }
            return null;
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public List<Empleado> informacionEmpleados(Integer[] numeroEmpleados, TipoSistemaFiscalizacion tipoSistema) throws EmpleadoServiceClientException {
        try {
            init();
            if (tipoSistema == null) {
                throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
            }

            if (numeroEmpleados == null) {
                throw new EmpleadoServiceClientException(ERROR_PARAMETROS);
            }

            ArrayOfEmpleado arrEmpleado = empleadoClientRepository.informacionEmpleados(EmpleadoClientHelper.getArrayOfintFromArrayInt(numeroEmpleados), tipoSistema.getIdSistemaFiscalizacion());

            if (arrEmpleado != null) {
                return EmpleadoClientHelper.getLstEmpleadoFromLstEmpleadoXml(arrEmpleado.getEmpleado());
            }
            return null;
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public List<UnidadAdministrativa> getUnidadesAdministrativas(TipoSistemaFiscalizacion tipoSistema) throws EmpleadoServiceClientException {
        try {
            init();
            if (tipoSistema != null) {
                ArrayOfUnidadAdministrativa lstAraces = empleadoClientRepository.getUnidadesAdministrativas(tipoSistema.getIdSistemaFiscalizacion());
                if (lstAraces != null) {
                    List<UnidadAdministrativa> lstUnidadesAdministrativas = new ArrayList<UnidadAdministrativa>();
                    for (UnidadAdministrativaXml arace : lstAraces.getUnidadAdministrativa()) {
                        UnidadAdministrativa unidadAdmin = new UnidadAdministrativa();

                        unidadAdmin.setId(arace.getID());
                        unidadAdmin.setClave(arace.getCLAVE().getValue());
                        unidadAdmin.setNombre(arace.getNOMBRE().getValue());
                        unidadAdmin.setSede(arace.getSEDE().getValue());

                        lstUnidadesAdministrativas.add(unidadAdmin);

                    }
                    return lstUnidadesAdministrativas;
                }
            }
            throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public List<UnidadAdministrativa> getUnidadesAdministrativasPorGeneral(Integer idAdmGral) throws EmpleadoServiceClientException {
        try {
            init();
            if (idAdmGral != null) {
                ArrayOfUnidadAdministrativa lstAraces = empleadoClientRepository.getUnidadesAdministrativasPorGeneral(idAdmGral);
                if (lstAraces != null) {
                    List<UnidadAdministrativa> lstUnidadesAdministrativas = new ArrayList<UnidadAdministrativa>();
                    for (UnidadAdministrativaXml arace : lstAraces.getUnidadAdministrativa()) {
                        UnidadAdministrativa unidadAdmin = new UnidadAdministrativa();

                        unidadAdmin.setId(arace.getID());
                        unidadAdmin.setClave(arace.getCLAVE().getValue());
                        unidadAdmin.setNombre(arace.getNOMBRE().getValue());
                        unidadAdmin.setSede(arace.getSEDE().getValue());
                        unidadAdmin.setCentral(arace.isCENTRAL());

                        lstUnidadesAdministrativas.add(unidadAdmin);

                    }
                    return lstUnidadesAdministrativas;
                }
            }
            throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public Empleado getEmpleado(Integer numeroEmpleado,
            String empleado,
            TipoSistemaFiscalizacion tipoSistema) throws EmpleadoServiceClientException {
        try {
            init();
            if (tipoSistema == null) {
                throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
            }
            return EmpleadoClientHelper.getEmpleadoFromEmpleadoXml(
                    empleadoClientRepository.getEmpleado(numeroEmpleado,
                            empleado,
                            tipoSistema.getIdSistemaFiscalizacion()));
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    @Override
    public EmpleadoCompleto getEmpleadoCompleto(TipoSistemaFiscalizacion tipoSistema, String rfc, Integer numeroEmpleado) throws EmpleadoServiceClientException {
        try {
            init();
            if (tipoSistema == null) {
                throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
            }

            EmpleadoCompletoXml empleadoCompletoXml = empleadoClientRepository.getEmpleadoCompleto(tipoSistema.getIdSistemaFiscalizacion(), rfc, numeroEmpleado);

            if (empleadoCompletoXml != null) {
                EmpleadoCompleto empleadoCompleto = new EmpleadoCompleto();
                empleadoCompleto.setEmpleado(EmpleadoClientHelper.getEmpleadoFromEmpleadoXml(empleadoCompletoXml.getEmpleado().getValue()));

                empleadoCompleto.setListaJefesSuperiores((empleadoCompletoXml.getListaJefesSuperiores() != null)
                        ? EmpleadoClientHelper.getLstEmpleadoFromLstEmpleadoXml(empleadoCompletoXml.getListaJefesSuperiores().getValue().getEmpleado())
                        : new ArrayList<Empleado>());

                empleadoCompleto.setListaSubordinados((empleadoCompletoXml.getListaSubordinados() != null)
                        ? EmpleadoClientHelper.getLstEmpleadoFromLstEmpleadoXml(empleadoCompletoXml.getListaSubordinados().getValue().getEmpleado())
                        : new ArrayList<Empleado>());

                empleadoCompleto.setListaDetalleEmpleado((empleadoCompletoXml.getListaDetalleEmpleado() != null)
                        ? EmpleadoClientHelper.getLstDetalleEmpleadoFromLstDetalleXml(empleadoCompletoXml.getListaDetalleEmpleado().getValue().getDetalleEmpleado())
                        : new ArrayList<DetalleEmpleado>());

                return empleadoCompleto;
            }
            return null;
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

    public EmpleadoClientRepository getEmpleadoClientRepository() {
        return empleadoClientRepository;
    }

    public void setEmpleadoClientRepository(EmpleadoClientRepository empleadoClientRepository) {
        this.empleadoClientRepository = empleadoClientRepository;
    }

    public Integer getIdSistemaFiscalizacion() {
        return idSistemaFiscalizacion;
    }

    public void setIdSistemaFiscalizacion(Integer idSistemaFiscalizacion) {
        this.idSistemaFiscalizacion = idSistemaFiscalizacion;
    }

    @Override
    public TipoSistemaFiscalizacion getTipoFiscalizacion() {
        return tipoFiscalizacion;
    }

    @Override
    public EmpleadoCompleto getInformacionEmpleado(TipoSistemaFiscalizacion tipoSistema, String rfc, Integer numeroEmpleado) throws EmpleadoServiceClientException {
        try {
            init();
            if (tipoSistema == null) {
                throw new EmpleadoServiceClientException(ERROR_TIPO_DE_MODULO);
            }

            EmpleadoCompletoXml empleadoCompletoXml = empleadoClientRepository.getInformacionEmpleado(tipoSistema.getIdSistemaFiscalizacion(), rfc, null);

            if (empleadoCompletoXml != null) {
                EmpleadoCompleto empleadoCompleto = new EmpleadoCompleto();
                empleadoCompleto.setEmpleado(EmpleadoClientHelper.getEmpleadoFromEmpleadoXml(empleadoCompletoXml.getEmpleado().getValue()));

                empleadoCompleto.setListaJefesSuperiores((empleadoCompletoXml.getListaJefesSuperiores() != null)
                        ? EmpleadoClientHelper.getLstEmpleadoFromLstEmpleadoXml(empleadoCompletoXml.getListaJefesSuperiores().getValue().getEmpleado())
                        : new ArrayList<Empleado>());

                empleadoCompleto.setListaSubordinados((empleadoCompletoXml.getListaSubordinados() != null)
                        ? EmpleadoClientHelper.getLstEmpleadoFromLstEmpleadoXml(empleadoCompletoXml.getListaSubordinados().getValue().getEmpleado())
                        : new ArrayList<Empleado>());

                empleadoCompleto.setListaDetalleEmpleado((empleadoCompletoXml.getListaDetalleEmpleado() != null)
                        ? EmpleadoClientHelper.getLstDetalleEmpleadoFromLstDetalleXml(empleadoCompletoXml.getListaDetalleEmpleado().getValue().getDetalleEmpleado())
                        : new ArrayList<DetalleEmpleado>());

                return empleadoCompleto;
            }
            return null;
        } catch (Exception e) {
            throw new EmpleadoServiceClientException(ERROR_CONSULTA_METODO, e);
        }
    }

}
