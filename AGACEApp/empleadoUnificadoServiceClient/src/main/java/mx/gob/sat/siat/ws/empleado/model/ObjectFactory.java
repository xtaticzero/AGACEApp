package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import mx.gob.sat.siat.ws.empleado.serialization.arrays.ArrayOfint;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfEmpleado;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfFechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfINPC;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfUnidadAdministrativa;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfVacacion;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoXml;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompletoXml;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimoXml;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.tempuri package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetEmpleadoResponseGetEmpleadoResult_QNAME = new QName("http://tempuri.org/", "getEmpleadoResult");
    private final static QName _TraeINPCResponseTraeINPCResult_QNAME = new QName("http://tempuri.org/", "TraeINPCResult");
    private final static QName _GetEmpleadoEmpleado_QNAME = new QName("http://tempuri.org/", "Empleado");
    private final static QName _TraeVacacionesResponseTraeVacacionesResult_QNAME = new QName("http://tempuri.org/", "TraeVacacionesResult");
    private final static QName _GetEmpleadosXUnidadAdmvaResponseGetEmpleadosXUnidadAdmvaResult_QNAME = new QName("http://tempuri.org/", "getEmpleadosXUnidadAdmvaResult");
    private final static QName _InformacionEmpleadosResponseInformacionEmpleadosResult_QNAME = new QName("http://tempuri.org/", "InformacionEmpleadosResult");
    private final static QName _TraeSalarioResponseTraeSalarioResult_QNAME = new QName("http://tempuri.org/", "TraeSalarioResult");
    private final static QName _GetEmpleadoCompletoRFC_QNAME = new QName("http://tempuri.org/", "RFC");
    private final static QName _GetCatalogoDiasHabilesInhabilesResponseGetCatalogoDiasHabilesInhabilesResult_QNAME = new QName("http://tempuri.org/", "getCatalogoDiasHabilesInhabilesResult");
    private final static QName _GetUnidadesAdministrativasPorGeneralResponseGetUnidadesAdministrativasPorGeneralResult_QNAME = new QName("http://tempuri.org/", "getUnidadesAdministrativasPorGeneralResult");
    private final static QName _GetInformacionEmpleadoResponseGetInformacionEmpleadoResult_QNAME = new QName("http://tempuri.org/", "getInformacionEmpleadoResult");
    private final static QName _GetUnidadesAdministrativasResponseGetUnidadesAdministrativasResult_QNAME = new QName("http://tempuri.org/", "getUnidadesAdministrativasResult");
    private final static QName _InformacionEmpleadosNumeroEmpleados_QNAME = new QName("http://tempuri.org/", "NumeroEmpleados");
    private final static QName _GetEmpleadoCompletoResponseGetEmpleadoCompletoResult_QNAME = new QName("http://tempuri.org/", "getEmpleadoCompletoResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: org.tempuri
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TraeINPCResponse }
     *
     */
    public TraeINPCResponse createTraeINPCResponse() {
        return new TraeINPCResponse();
    }

    /**
     * Create an instance of {@link TraeINPC }
     *
     */
    public TraeINPC createTraeINPC() {
        return new TraeINPC();
    }

    /**
     * Create an instance of {@link TraeVacacionesResponse }
     *
     */
    public TraeVacacionesResponse createTraeVacacionesResponse() {
        return new TraeVacacionesResponse();
    }

    /**
     * Create an instance of {@link GetEmpleadosXUnidadAdmvaResponse }
     *
     */
    public GetEmpleadosXUnidadAdmvaResponse createGetEmpleadosXUnidadAdmvaResponse() {
        return new GetEmpleadosXUnidadAdmvaResponse();
    }

    /**
     * Create an instance of {@link InformacionEmpleadosResponse }
     *
     */
    public InformacionEmpleadosResponse createInformacionEmpleadosResponse() {
        return new InformacionEmpleadosResponse();
    }

    /**
     * Create an instance of {@link GetEmpleadoCompleto }
     *
     */
    public GetEmpleadoCompleto createGetEmpleadoCompleto() {
        return new GetEmpleadoCompleto();
    }

    /**
     * Create an instance of {@link TraeVacaciones }
     *
     */
    public TraeVacaciones createTraeVacaciones() {
        return new TraeVacaciones();
    }

    /**
     * Create an instance of {@link GetCatalogoDiasHabilesInhabilesResponse }
     *
     */
    public GetCatalogoDiasHabilesInhabilesResponse createGetCatalogoDiasHabilesInhabilesResponse() {
        return new GetCatalogoDiasHabilesInhabilesResponse();
    }

    /**
     * Create an instance of {@link GetUnidadesAdministrativasPorGeneralResponse
     * }
     *
     */
    public GetUnidadesAdministrativasPorGeneralResponse createGetUnidadesAdministrativasPorGeneralResponse() {
        return new GetUnidadesAdministrativasPorGeneralResponse();
    }

    /**
     * Create an instance of {@link TraeSalario }
     *
     */
    public TraeSalario createTraeSalario() {
        return new TraeSalario();
    }

    /**
     * Create an instance of {@link GetUnidadesAdministrativasPorGeneral }
     *
     */
    public GetUnidadesAdministrativasPorGeneral createGetUnidadesAdministrativasPorGeneral() {
        return new GetUnidadesAdministrativasPorGeneral();
    }

    /**
     * Create an instance of {@link GetCatalogoDiasHabilesInhabiles }
     *
     */
    public GetCatalogoDiasHabilesInhabiles createGetCatalogoDiasHabilesInhabiles() {
        return new GetCatalogoDiasHabilesInhabiles();
    }

    /**
     * Create an instance of {@link GetEmpleadoResponse }
     *
     */
    public GetEmpleadoResponse createGetEmpleadoResponse() {
        return new GetEmpleadoResponse();
    }

    /**
     * Create an instance of {@link GetEmpleado }
     *
     */
    public GetEmpleado createGetEmpleado() {
        return new GetEmpleado();
    }

    /**
     * Create an instance of {@link GetUnidadesAdministrativas }
     *
     */
    public GetUnidadesAdministrativas createGetUnidadesAdministrativas() {
        return new GetUnidadesAdministrativas();
    }

    /**
     * Create an instance of {@link TraeSalarioResponse }
     *
     */
    public TraeSalarioResponse createTraeSalarioResponse() {
        return new TraeSalarioResponse();
    }

    /**
     * Create an instance of {@link GetInformacionEmpleadoResponse }
     *
     */
    public GetInformacionEmpleadoResponse createGetInformacionEmpleadoResponse() {
        return new GetInformacionEmpleadoResponse();
    }

    /**
     * Create an instance of {@link GetInformacionEmpleado }
     *
     */
    public GetInformacionEmpleado createGetInformacionEmpleado() {
        return new GetInformacionEmpleado();
    }

    /**
     * Create an instance of {@link GetUnidadesAdministrativasResponse }
     *
     */
    public GetUnidadesAdministrativasResponse createGetUnidadesAdministrativasResponse() {
        return new GetUnidadesAdministrativasResponse();
    }

    /**
     * Create an instance of {@link GetEmpleadosXUnidadAdmva }
     *
     */
    public GetEmpleadosXUnidadAdmva createGetEmpleadosXUnidadAdmva() {
        return new GetEmpleadosXUnidadAdmva();
    }

    /**
     * Create an instance of {@link GetEmpleadoCompletoResponse }
     *
     */
    public GetEmpleadoCompletoResponse createGetEmpleadoCompletoResponse() {
        return new GetEmpleadoCompletoResponse();
    }

    /**
     * Create an instance of {@link InformacionEmpleados }
     *
     */
    public InformacionEmpleados createInformacionEmpleados() {
        return new InformacionEmpleados();
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link EmpleadoXml }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getEmpleadoResult", scope = GetEmpleadoResponse.class)
    public JAXBElement<EmpleadoXml> createGetEmpleadoResponseGetEmpleadoResult(EmpleadoXml value) {
        return new JAXBElement<EmpleadoXml>(_GetEmpleadoResponseGetEmpleadoResult_QNAME, EmpleadoXml.class, GetEmpleadoResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfINPC }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "TraeINPCResult", scope = TraeINPCResponse.class)
    public JAXBElement<ArrayOfINPC> createTraeINPCResponseTraeINPCResult(ArrayOfINPC value) {
        return new JAXBElement<ArrayOfINPC>(_TraeINPCResponseTraeINPCResult_QNAME, ArrayOfINPC.class, TraeINPCResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Empleado", scope = GetEmpleado.class)
    public JAXBElement<String> createGetEmpleadoEmpleado(String value) {
        return new JAXBElement<String>(_GetEmpleadoEmpleado_QNAME, String.class, GetEmpleado.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfVacacion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "TraeVacacionesResult", scope = TraeVacacionesResponse.class)
    public JAXBElement<ArrayOfVacacion> createTraeVacacionesResponseTraeVacacionesResult(ArrayOfVacacion value) {
        return new JAXBElement<ArrayOfVacacion>(_TraeVacacionesResponseTraeVacacionesResult_QNAME, ArrayOfVacacion.class, TraeVacacionesResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getEmpleadosXUnidadAdmvaResult", scope = GetEmpleadosXUnidadAdmvaResponse.class)
    public JAXBElement<ArrayOfEmpleado> createGetEmpleadosXUnidadAdmvaResponseGetEmpleadosXUnidadAdmvaResult(ArrayOfEmpleado value) {
        return new JAXBElement<ArrayOfEmpleado>(_GetEmpleadosXUnidadAdmvaResponseGetEmpleadosXUnidadAdmvaResult_QNAME, ArrayOfEmpleado.class, GetEmpleadosXUnidadAdmvaResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InformacionEmpleadosResult", scope = InformacionEmpleadosResponse.class)
    public JAXBElement<ArrayOfEmpleado> createInformacionEmpleadosResponseInformacionEmpleadosResult(ArrayOfEmpleado value) {
        return new JAXBElement<ArrayOfEmpleado>(_InformacionEmpleadosResponseInformacionEmpleadosResult_QNAME, ArrayOfEmpleado.class, InformacionEmpleadosResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link SalarioMinimoXml }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "TraeSalarioResult", scope = TraeSalarioResponse.class)
    public JAXBElement<SalarioMinimoXml> createTraeSalarioResponseTraeSalarioResult(SalarioMinimoXml value) {
        return new JAXBElement<SalarioMinimoXml>(_TraeSalarioResponseTraeSalarioResult_QNAME, SalarioMinimoXml.class, TraeSalarioResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RFC", scope = GetEmpleadoCompleto.class)
    public JAXBElement<String> createGetEmpleadoCompletoRFC(String value) {
        return new JAXBElement<String>(_GetEmpleadoCompletoRFC_QNAME, String.class, GetEmpleadoCompleto.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfFechasHabileseInhabiles }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getCatalogoDiasHabilesInhabilesResult", scope = GetCatalogoDiasHabilesInhabilesResponse.class)
    public JAXBElement<ArrayOfFechasHabileseInhabiles> createGetCatalogoDiasHabilesInhabilesResponseGetCatalogoDiasHabilesInhabilesResult(ArrayOfFechasHabileseInhabiles value) {
        return new JAXBElement<ArrayOfFechasHabileseInhabiles>(_GetCatalogoDiasHabilesInhabilesResponseGetCatalogoDiasHabilesInhabilesResult_QNAME, ArrayOfFechasHabileseInhabiles.class, GetCatalogoDiasHabilesInhabilesResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfUnidadAdministrativa }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getUnidadesAdministrativasPorGeneralResult", scope = GetUnidadesAdministrativasPorGeneralResponse.class)
    public JAXBElement<ArrayOfUnidadAdministrativa> createGetUnidadesAdministrativasPorGeneralResponseGetUnidadesAdministrativasPorGeneralResult(ArrayOfUnidadAdministrativa value) {
        return new JAXBElement<ArrayOfUnidadAdministrativa>(_GetUnidadesAdministrativasPorGeneralResponseGetUnidadesAdministrativasPorGeneralResult_QNAME, ArrayOfUnidadAdministrativa.class, GetUnidadesAdministrativasPorGeneralResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link EmpleadoCompletoXml }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getInformacionEmpleadoResult", scope = GetInformacionEmpleadoResponse.class)
    public JAXBElement<EmpleadoCompletoXml> createGetInformacionEmpleadoResponseGetInformacionEmpleadoResult(EmpleadoCompletoXml value) {
        return new JAXBElement<EmpleadoCompletoXml>(_GetInformacionEmpleadoResponseGetInformacionEmpleadoResult_QNAME, EmpleadoCompletoXml.class, GetInformacionEmpleadoResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RFC", scope = GetInformacionEmpleado.class)
    public JAXBElement<String> createGetInformacionEmpleadoRFC(String value) {
        return new JAXBElement<String>(_GetEmpleadoCompletoRFC_QNAME, String.class, GetInformacionEmpleado.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfUnidadAdministrativa }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getUnidadesAdministrativasResult", scope = GetUnidadesAdministrativasResponse.class)
    public JAXBElement<ArrayOfUnidadAdministrativa> createGetUnidadesAdministrativasResponseGetUnidadesAdministrativasResult(ArrayOfUnidadAdministrativa value) {
        return new JAXBElement<ArrayOfUnidadAdministrativa>(_GetUnidadesAdministrativasResponseGetUnidadesAdministrativasResult_QNAME, ArrayOfUnidadAdministrativa.class, GetUnidadesAdministrativasResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "NumeroEmpleados", scope = InformacionEmpleados.class)
    public JAXBElement<ArrayOfint> createInformacionEmpleadosNumeroEmpleados(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_InformacionEmpleadosNumeroEmpleados_QNAME, ArrayOfint.class, InformacionEmpleados.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link EmpleadoCompletoXml }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "getEmpleadoCompletoResult", scope = GetEmpleadoCompletoResponse.class)
    public JAXBElement<EmpleadoCompletoXml> createGetEmpleadoCompletoResponseGetEmpleadoCompletoResult(EmpleadoCompletoXml value) {
        return new JAXBElement<EmpleadoCompletoXml>(_GetEmpleadoCompletoResponseGetEmpleadoCompletoResult_QNAME, EmpleadoCompletoXml.class, GetEmpleadoCompletoResponse.class, value);
    }

}
