package ec.eac.sitac.esigef;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF. Información adicional del
 * comprobante de retención
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class InfoAdicional {
	/**
	 * Campo adicional
	 *
	 * @since 1.0
	 */
	String campoAdicional;

	public String getCampoAdicional() {
		return campoAdicional;
	}

	public void setCampoAdicional(String campoAdicional) {
		this.campoAdicional = campoAdicional;
	}

    @Override
    public String toString() {
        return "InfoAdicional [campoAdicional=" + campoAdicional + "]";
    }
}
