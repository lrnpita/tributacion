// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_pago de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoPago implements java.io.Serializable {

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
	 * Texto de que describe el tipo de pago
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de compras
	 *
	 * @since 2015
	 */
	private Set<TipoPagoVsCompra> tipoPagoVsCompras = new HashSet<TipoPagoVsCompra>(0);
	
	/**
	 * lista de ventas
	 *
	 * @since 2015
	 */
	private Set<Venta> ventas = new HashSet<Venta>(0);

	public TipoPago() {
	}

	public TipoPago(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TipoPago(String codigo, String nombre, String descripcion,
			Set<TipoPagoVsCompra> tipoPagoVsCompras, Set<Venta> ventas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoPagoVsCompras = tipoPagoVsCompras;
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

	public Set<TipoPagoVsCompra> getTipoPagoVsCompras() {
		return this.tipoPagoVsCompras;
	}

	public void setTipoPagoVsCompras(Set<TipoPagoVsCompra> tipoPagoVsCompras) {
		this.tipoPagoVsCompras = tipoPagoVsCompras;
	}

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

}
