// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_emision de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoEmision implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private char codigo;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Texto que describe el tipo de emisión
	 *
	 * @since 2015
	 */
	private String description;
	
	/**
	 * Lista de empresas
	 *
	 * @since 2015
	 */
	private Set<Empresa> empresas = new HashSet<Empresa>(0);

	public TipoEmision() {
	}

	public TipoEmision(char codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TipoEmision(char codigo, String nombre, String description,
			Set<Empresa> empresas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.description = description;
		this.empresas = empresas;
	}

	public char getCodigo() {
		return this.codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

}
