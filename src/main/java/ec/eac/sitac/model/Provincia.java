// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla Exportacion de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Provincia implements java.io.Serializable {

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
	 * País
	 *
	 * @see Pais
	 * @since 2015
	 */
	private Pais pais;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Lista de ciudades
	 *
	 * @since 2015
	 */
	private Set<Ciudad> ciudads = new HashSet<Ciudad>(0);

	public Provincia() {
	}

	public Provincia(int id, Pais pais, String nombre) {
		this.id = id;
		this.pais = pais;
		this.nombre = nombre;
	}

	public Provincia(int id, Pais pais, String nombre, Set<Ciudad> ciudads) {
		this.id = id;
		this.pais = pais;
		this.nombre = nombre;
		this.ciudads = ciudads;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(Set<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

}
