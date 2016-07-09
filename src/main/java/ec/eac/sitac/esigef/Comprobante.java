package ec.eac.sitac.esigef;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class Comprobante {
	/**
	 * Versión
	 *
	 * @since 1.0
	 */
	private String version;
	/**
	 * Versión
	 *
	 * @since 1.0
	 */
	private String encoding;
	/**
	 * Objeto con toda la información del comprobante
	 *
	 * @since 1.0
	 */
	private ComprobanteRetencion comprobanteRetencion;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public ComprobanteRetencion getComprobanteRetencion() {
		return comprobanteRetencion;
	}
	public void setComprobanteRetencion(ComprobanteRetencion comprobanteRetencion) {
		this.comprobanteRetencion = comprobanteRetencion;
	}
	
    @Override
    public String toString() {
        return "Comprobante [version=" + version + 
        		", encoding=" + encoding + 
        		", comprobanteRetencion=" + comprobanteRetencion + "]";
    }
}
