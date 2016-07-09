/**
 * RecepcionComprobantesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package recepcion.ws.sri.gob.ec;

public class RecepcionComprobantesServiceLocator extends org.apache.axis.client.Service implements recepcion.ws.sri.gob.ec.RecepcionComprobantesService {

    public RecepcionComprobantesServiceLocator() {
    }


    public RecepcionComprobantesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RecepcionComprobantesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RecepcionComprobantesPort
    private java.lang.String RecepcionComprobantesPort_address = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes";

    public java.lang.String getRecepcionComprobantesPortAddress() {
        return RecepcionComprobantesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecepcionComprobantesPortWSDDServiceName = "RecepcionComprobantesPort";

    public java.lang.String getRecepcionComprobantesPortWSDDServiceName() {
        return RecepcionComprobantesPortWSDDServiceName;
    }

    public void setRecepcionComprobantesPortWSDDServiceName(java.lang.String name) {
        RecepcionComprobantesPortWSDDServiceName = name;
    }

    public recepcion.ws.sri.gob.ec.RecepcionComprobantes getRecepcionComprobantesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecepcionComprobantesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecepcionComprobantesPort(endpoint);
    }

    public recepcion.ws.sri.gob.ec.RecepcionComprobantes getRecepcionComprobantesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            recepcion.ws.sri.gob.ec.RecepcionComprobantesServiceSoapBindingStub _stub = new recepcion.ws.sri.gob.ec.RecepcionComprobantesServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getRecepcionComprobantesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecepcionComprobantesPortEndpointAddress(java.lang.String address) {
        RecepcionComprobantesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (recepcion.ws.sri.gob.ec.RecepcionComprobantes.class.isAssignableFrom(serviceEndpointInterface)) {
                recepcion.ws.sri.gob.ec.RecepcionComprobantesServiceSoapBindingStub _stub = new recepcion.ws.sri.gob.ec.RecepcionComprobantesServiceSoapBindingStub(new java.net.URL(RecepcionComprobantesPort_address), this);
                _stub.setPortName(getRecepcionComprobantesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RecepcionComprobantesPort".equals(inputPortName)) {
            return getRecepcionComprobantesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RecepcionComprobantesPort".equals(portName)) {
            setRecepcionComprobantesPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
