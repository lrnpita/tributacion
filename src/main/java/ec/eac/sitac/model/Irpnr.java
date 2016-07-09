// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;


/**
 * Modelo correspondiente a la tabla Irpnr de la base de datos
 *
 * @author Luis M. Teij�n gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Irpnr implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */

	private int id;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */

	private String nombre;
	
	/**
	 * Texto que describe el Irpnr
	 *
	 * @since 2015
	 */

	private String descripcion;
	

	public Irpnr() {
	}

	public Irpnr(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Irpnr(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
