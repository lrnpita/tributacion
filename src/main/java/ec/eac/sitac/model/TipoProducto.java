// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_producto de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoProducto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idTipoProducto;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Texto que describe el tipo de producto
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de productos
	 *
	 * @since 2015
	 */
	private Set<Producto> productos = new HashSet<Producto>(0);

	public TipoProducto() {
	}

	public TipoProducto(int idTipoProducto, String nombre) {
		this.idTipoProducto = idTipoProducto;
		this.nombre = nombre;
	}

	public TipoProducto(int idTipoProducto, String nombre, String descripcion, Set<Producto> productos) {
		this.idTipoProducto = idTipoProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.productos = productos;
	}

	public int getIdTipoProducto() {
		return this.idTipoProducto;
	}

	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
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

	public Set<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

}
