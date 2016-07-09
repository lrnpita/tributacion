package ec.eac.sitac.esigef;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teij�n gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class ComprobanteRetencion {
	/**
	 * Id
	 *
	 * @since 1.0
	 */
	private String id;
	/**
	 * Versi�n
	 *
	 * @since 1.0
	 */
	private String version;
	/**
	 * Objeto con la informaci�n tributaria
	 *
	 * @since 1.0
	 */
	InfoTributaria infoTributaria;
	/**
	 * Objeto con la informaci�n del comprobante de retenci�n
	 *
	 * @since 1.0
	 */
	InfoCompRetencion infoCompRetencion;
	
	/**
	 * Objeto con la informaci�n de los impuestos
	 *
	 * @since 1.0
	 */
	Impuestos impuestos;

	/**
	 * Objeto con informaci�n adicional
	 *
	 * @since 1.0
	 */
	InfoAdicional infoAdicional;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public InfoTributaria getInfoTributaria() {
		return infoTributaria;
	}

	public void setInfoTributaria(InfoTributaria infoTributaria) {
		this.infoTributaria = infoTributaria;
	}

	public InfoCompRetencion getInfoCompRetencion() {
		return infoCompRetencion;
	}

	public void setInfoCompRetencion(InfoCompRetencion infoCompRetencion) {
		this.infoCompRetencion = infoCompRetencion;
	}

	public Impuestos getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Impuestos impuestos) {
		this.impuestos = impuestos;
	}

	public InfoAdicional getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(InfoAdicional infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
    @Override
    public String toString() {
        return "ComprobanteRetencion [id=" + id + 
        		", version=" + version + 
        		", infoTributaria=" + infoTributaria +
        		", infoCompRetencion=" + infoCompRetencion +
        		", impuestos=" + impuestos +
        		", infoAdicional=" + infoAdicional + "]";
    }
}
