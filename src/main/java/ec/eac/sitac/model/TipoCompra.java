// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_compra de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoCompra implements java.io.Serializable {

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
	 * Texto que describe el tipo de compra
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de compras
	 *
	 * @since 2015
	 */
	private Set<Compra> compras = new HashSet<Compra>(0);
	
	/**
	 * Lista de compras 1
	 *
	 * @since 2015
	 */
	private Set<Compra> compras_1 = new HashSet<Compra>(0);

	public TipoCompra() {
	}

	public TipoCompra(String codigo) {
		this.codigo = codigo;
	}

	public TipoCompra(String codigo, String nombre, String descripcion,
			Set<Compra> compras, Set<Compra> compras_1) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.compras = compras;
		this.compras_1 = compras_1;
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

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<Compra> getCompras_1() {
		return this.compras_1;
	}

	public void setCompras_1(Set<Compra> compras_1) {
		this.compras_1 = compras_1;
	}

}
