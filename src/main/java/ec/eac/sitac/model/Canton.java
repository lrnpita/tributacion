// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Modelo correspondiente a la tabla canton de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class Canton implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private String id;
	
	/**
	 * Nombre
	 *
	 * @since 1.0
	 */
	private String nombre;
	
	/**
	 * Texto que describe el Canton
	 *
	 * @since 1.0
	 */
	private String descripcion;
	
	/**
	 * Lista de trabajadores relacionados con canton
	 *
	 * @since 1.0
	 */
	private Set<PersonalEmpresa> personalEmpresas = new HashSet<PersonalEmpresa>(0);

	public Canton() {
	}

	public Canton(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Canton(String id, String nombre, String descripcion,
			Set<PersonalEmpresa> personalEmpresas) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.personalEmpresas = personalEmpresas;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public Set<PersonalEmpresa> getPersonalEmpresas() {
		return this.personalEmpresas;
	}

	public void setPersonalEmpresas(Set<PersonalEmpresa> personalEmpresas) {
		this.personalEmpresas = personalEmpresas;
	}

}
