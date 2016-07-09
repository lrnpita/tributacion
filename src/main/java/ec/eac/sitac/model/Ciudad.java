// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla ciudad de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Ciudad implements java.io.Serializable {

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
	 * Provincia
	 *
	 * @since 2015
	 */
	private Provincia provincia;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Identificador del SRI
	 *
	 * @since 2015
	 */
	private String idSri;
	private Set<PersonalEmpresa> personalEmpresas = new HashSet<PersonalEmpresa>(0);

	public Ciudad() {
	}

	public Ciudad(String codigo, Provincia provincia, String nombre) {
		this.codigo = codigo;
		this.provincia = provincia;
		this.nombre = nombre;
	}

	public Ciudad(String codigo, Provincia provincia, String nombre,
			String idSri, Set<PersonalEmpresa> personalEmpresas) {
		this.codigo = codigo;
		this.provincia = provincia;
		this.nombre = nombre;
		this.idSri = idSri;
		this.personalEmpresas = personalEmpresas;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdSri() {
		return this.idSri;
	}

	public void setIdSri(String idSri) {
		this.idSri = idSri;
	}

	public Set<PersonalEmpresa> getPersonalEmpresas() {
		return this.personalEmpresas;
	}

	public void setPersonalEmpresas(Set<PersonalEmpresa> personalEmpresas) {
		this.personalEmpresas = personalEmpresas;
	}

}
