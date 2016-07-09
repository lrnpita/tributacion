// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla venta_vs_producto de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class VentaVsProducto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private int id;
	
	/**
	 * cantidad
	 *
	 * @since 1.0
	 */
	private int cantidad;
	
	/**
	 * descuento
	 *
	 * @since 1.0
	 */
	private float descuento;
	
	/**
	 * ice
	 *
	 * @since 1.0
	 */
	private float ice;
	
	/**
	 * Producto
	 *
	 * @see Producto
	 * @since 1.0
	 */
	private Producto producto;
	
	/**
	 * Venta
	 * 
	 * @see Venta
	 * @since 1.0
	 */
	private Venta venta;

	public VentaVsProducto() {
	}

	public VentaVsProducto(int catidad, float descuento, float ice, Producto producto, Venta venta) {
		super();
		this.producto = producto;
		this.venta = venta;
		this.cantidad = catidad;
		this.descuento = descuento;
		this.ice = ice;
	}

	public VentaVsProducto(int id, int cantidad, float descuento, float ice, Producto producto, Venta venta) {
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
		this.venta = venta;
		this.descuento = descuento;
		this.ice = ice;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getIce() {
		return ice;
	}

	public void setIce(float ice) {
		this.ice = ice;
	}

}
