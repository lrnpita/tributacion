// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_ambiente de la base de datos
 *
 * @author Luis M. Teij�n gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoAmbiente implements java.io.Serializable {

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
	 * Texto que describe el tipo de ambiente
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de empresas
	 *
	 * @since 2015
	 */
	private Set<Empresa> empresas = new HashSet<Empresa>(0);

	public TipoAmbiente() {
	}

	public TipoAmbiente(char codigo) {
		this.codigo = codigo;
	}

	public TipoAmbiente(char codigo, String nombre, String descripcion,
			Set<Empresa> empresas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

}
