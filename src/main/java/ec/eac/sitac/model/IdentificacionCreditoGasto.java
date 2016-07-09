// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla IdentificacionCreditoGasto de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class IdentificacionCreditoGasto implements java.io.Serializable {

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
	 * Texto que describe el IdentificacionCreditoGasto 
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de importaciones
	 *
	 * @since 2015
	 */
	private Set<Importacion> importacions = new HashSet<Importacion>(0);

	public IdentificacionCreditoGasto() {
	}

	public IdentificacionCreditoGasto(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public IdentificacionCreditoGasto(String codigo, String nombre,
			String descripcion, Set<Importacion> importacions) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.importacions = importacions;
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Importacion> getImportacions() {
		return this.importacions;
	}

	public void setImportacions(Set<Importacion> importacions) {
		this.importacions = importacions;
	}

}
