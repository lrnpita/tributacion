package ec.eac.sitac.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Usuario;

public class Utility {

	
	public static String generarClaveAcceso(Date fecha, String codigoTipoComprobante,
											String serie, String secuencial, 
											String codigoNumrerico, char codidoTipoEmision, char digitoVerificador) {
		String clave = new String();
		
        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy");
        String cadenaFecha = formatoFecha.format(fecha);
        
        String ruc = "1710450055001"; // FIXME Extraer este ruc de la Session, es el ruc de la empresa
        char codigoTipoAmbiente = '1'; // FIXME Extraer este dato de la Session, es el tipo de ambiente, en este caso de prueba = 1
        
        clave += cadenaFecha + codigoTipoComprobante + ruc + codigoTipoAmbiente + serie + secuencial + codigoNumrerico + codidoTipoEmision + digitoVerificador;
        
		
		return clave;
	}
	
	// Modulo 11
    public static int generarDigitoVerificador(String cadena) {
    	// invirtiendo la cadena
        String cadenaInvertida = "";
        for (int x = cadena.length() - 1; x >= 0; x--) {
            cadenaInvertida = cadenaInvertida + cadena.charAt(x);
        }
        
        // aplicando metodo modulo 11
        int factorPonderacion = 2;
        int longitudCadena = cadenaInvertida.length();
        int cantidadTotal = 0;
        int b = 1;
        for (int i = 0; i < longitudCadena; i++) {
            if (factorPonderacion == 8) {
                factorPonderacion = 2;
            }
            int temporal = Integer.parseInt("" + cadenaInvertida.substring(i, b));
            b++;
            temporal *= factorPonderacion;
            factorPonderacion++;
            cantidadTotal += temporal;
        }
        cantidadTotal = 11 - cantidadTotal % 11;
        
        if (cantidadTotal == 10)
        	return 1;
        else if (cantidadTotal == 11)
        	return 0;
        
        return cantidadTotal;
    }
    
    /**
     * Construye la URL en base a la dirección del controlador que se pasa como parámetro, 
     * la lista de objetos que se muestras pertenecen a la empresaId
     * 
     * @param empresaId
     * @param dirControlador
     */
    public static String goToUrl(int empresaId, String dirControlador) {
    	
		StringBuilder redireccionar = new StringBuilder("redirect:/empresas/");
		redireccionar.append(empresaId);
		redireccionar.append("/");
		redireccionar.append(dirControlador);
		
		return redireccionar.toString();
    }
    
    /**
     * Construye la URL de admin en base a la dirección del controlador que se pasa como parámetro, 
     * la lista de objetos que se muestras pertenecen a la empresaId
     * 
     * @param empresaId
     * @param dirControlador
     */
    public static String goToUrlAdmin(int empresaId, String dirControlador) {
    	
		StringBuilder redireccionar = new StringBuilder("redirect:/admin/empresas/");
		redireccionar.append(empresaId);
		redireccionar.append("/");
		redireccionar.append(dirControlador);
		
		return redireccionar.toString();
    }
    
	/**
	 * Dado el nombre de un mes, devuelve el número correspondiente
	 * 
	 * @param mes
	 * @since 1.0
	 */	
	public static int getNumeroMes(String mes){
		
		mes = mes.toLowerCase();
		
		switch (mes) {
		case "january":
		case "enero":
			return 1;
		case "february":
		case "febrero":
			return 2;
		case "march":
		case "marzo":
			return 3;
		case "april":
		case "abril":
			return 4;
		case "may":
		case "mayo":
			return 5;
		case "june":
		case "junio":
			return 6;
		case "july":
		case "julio":
			return 7;
		case "august":
		case "agosto":
			return 8;
		case "september":
		case "septiembre":
			return 9;
		case "october":
		case "octubre":
			return 10;
		case "november":
		case "noviembre":
			return 11;
		case "december":
		case "diciembre":
			return 12;
		default:
			return -1;
		}
	}

	public static String rellenarCadenaConCeros(String string) {
		while (string.length() < 9) {
			string = "0" + string;
		}
		
		return string;
	}

}
