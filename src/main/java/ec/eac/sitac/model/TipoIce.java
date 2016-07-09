// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_ice de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoIce implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idTipoIce;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Texto que describe el tipo ICE
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Procentaje
	 *
	 * @since 2015
	 */
	private Float porcentaje;
	
	public TipoIce() {
	}

	public TipoIce(int idTipoIce, String nombre) {
		this.idTipoIce = idTipoIce;
		this.nombre = nombre;
	}

	public TipoIce(int idTipoIce, String nombre, String descripcion, Float porcentaje) {
		this.idTipoIce = idTipoIce;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.porcentaje = porcentaje;
	}

	public int getIdTipoIce() {
		return this.idTipoIce;
	}

	public void setIdTipoIce(int idTipoIce) {
		this.idTipoIce = idTipoIce;
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

	public Float getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

}
