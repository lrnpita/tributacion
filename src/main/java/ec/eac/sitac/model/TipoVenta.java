// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_venta de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoVenta implements java.io.Serializable {

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
	 * Texto que describe el tipo de venta
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de ventas
	 *
	 * @since 2015
	 */
	private Set<Venta> ventas = new HashSet<Venta>(0);

	public TipoVenta() {
	}

	public TipoVenta(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TipoVenta(String codigo, String nombre, String descripcion,
			Set<Venta> ventas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ventas = ventas;
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

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

}
