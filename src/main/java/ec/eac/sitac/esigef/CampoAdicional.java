package ec.eac.sitac.esigef;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class CampoAdicional {
	/**
	 * Correo
	 *
	 * @since 1.0
	 */
	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    @Override
    public String toString() {
        return "InfoAdicional [correo=" + nombre + "]";
    }
}
