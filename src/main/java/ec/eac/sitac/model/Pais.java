// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla Pais de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Pais implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private String codigo;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Lista de compras
	 *
	 * @since 2015
	 */
	private Set<Compra> compras = new HashSet<Compra>(0);
	
	/**
	 * Lista de provincias
	 *
	 * @since 2015
	 */
	private Set<Provincia> provincias = new HashSet<Provincia>(0);
	
	/**
	 * Lista de trabajadores
	 *
	 * @since 2015
	 */
	private Set<PersonalEmpresa> personalEmpresas = new HashSet<PersonalEmpresa>(0);

	public Pais() {
	}

	public Pais(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Pais(String codigo, String nombre, Set<Compra> compras, Set<Provincia> provincias,
			Set<PersonalEmpresa> personalEmpresas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.compras = compras;
		this.provincias = provincias;
		this.personalEmpresas = personalEmpresas;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}

	public Set<PersonalEmpresa> getPersonalEmpresas() {
		return this.personalEmpresas;
	}

	public void setPersonalEmpresas(Set<PersonalEmpresa> personalEmpresas) {
		this.personalEmpresas = personalEmpresas;
	}

}
