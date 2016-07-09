package recepcion.ws.sri.gob.ec;

public class RecepcionComprobantesProxy implements recepcion.ws.sri.gob.ec.RecepcionComprobantes {
  private String _endpoint = null;
  private recepcion.ws.sri.gob.ec.RecepcionComprobantes recepcionComprobantes = null;
  
  public RecepcionComprobantesProxy() {
    _initRecepcionComprobantesProxy();
  }
  
  public RecepcionComprobantesProxy(String endpoint) {
    _endpoint = endpoint;
    _initRecepcionComprobantesProxy();
  }
  
  private void _initRecepcionComprobantesProxy() {
    try {
      recepcionComprobantes = (new recepcion.ws.sri.gob.ec.RecepcionComprobantesServiceLocator()).getRecepcionComprobantesPort();
      if (recepcionComprobantes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)recepcionComprobantes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)recepcionComprobantes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (recepcionComprobantes != null)
      ((javax.xml.rpc.Stub)recepcionComprobantes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public recepcion.ws.sri.gob.ec.RecepcionComprobantes getRecepcionComprobantes() {
    if (recepcionComprobantes == null)
      _initRecepcionComprobantesProxy();
    return recepcionComprobantes;
  }
  
  public recepcion.ws.sri.gob.ec.RespuestaSolicitud validarComprobante(byte[] xml) throws java.rmi.RemoteException{
    if (recepcionComprobantes == null)
      _initRecepcionComprobantesProxy();
    return recepcionComprobantes.validarComprobante(xml);
  }
  
  
}