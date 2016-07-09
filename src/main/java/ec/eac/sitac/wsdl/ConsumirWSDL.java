package ec.eac.sitac.wsdl;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import recepcion.ws.sri.gob.ec.*;

public class ConsumirWSDL {

    private static RespuestaSolicitud validarComprobante(byte[] xml) throws ServiceException, RemoteException {
    	RecepcionComprobantesService service = null;
		RecepcionComprobantes port = service.getRecepcionComprobantesPort();
        return port.validarComprobante(xml);
    }
 
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "C:\\Program Files\\Java\\jdk1.8.0_40\\jre\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.8.0_40\\jre\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        RespuestaSolicitud respuesta = null;
        
		try {
			respuesta = validarComprobante(null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(respuesta.getEstado());
    }

}
