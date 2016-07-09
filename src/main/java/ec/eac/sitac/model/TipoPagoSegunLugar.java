// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_pago_segun_lugar de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoPagoSegunLugar implements java.io.Serializable {

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
	 * Texto que describe
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

	public TipoPagoSegunLugar() {
	}

	public TipoPagoSegunLugar(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TipoPagoSegunLugar(String codigo, String nombre, String descripcion,
			Set<Compra> compras) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.compras = compras;
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

}
