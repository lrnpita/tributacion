// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla CondicionDeTrabajador de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class CondicionDeTrabajador implements java.io.Serializable {

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
	 * Texto de que describe la condición del trabajador
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de trabajadores
	 *
	 * @since 2015
	 */
	private Set<PersonalEmpresa> personalEmpresas = new HashSet<PersonalEmpresa>(0);

	public CondicionDeTrabajador() {
	}

	public CondicionDeTrabajador(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public CondicionDeTrabajador(int id, String nombre, String descripcion,
			Set<PersonalEmpresa> personalEmpresas) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.personalEmpresas = personalEmpresas;
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

	public Set<PersonalEmpresa> getPersonalEmpresas() {
		return this.personalEmpresas;
	}

	public void setPersonalEmpresas(Set<PersonalEmpresa> personalEmpresas) {
		this.personalEmpresas = personalEmpresas;
	}

}
