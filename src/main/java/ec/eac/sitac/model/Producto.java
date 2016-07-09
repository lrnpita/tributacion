// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla Producto de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class Producto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * codigo principal del producto
	 *
	 * @since 2015
	 */
	private int idProducto;
	
	/**
	 * codigo principal del producto
	 *
	 * @since 1.0
	 */
	private String codigoPrincipal;
	
	/**
	 * Rarifa IVA
	 *
	 * @see TarifaIva
	 * @since 1.0
	 */
	private TarifaIva tarifaIva;
	
	/**
	 * Tipo de producto
	 *
	 * @see TipoProducto
	 * @since 1.0
	 */
	private TipoProducto tipoProducto;
	
	/**
	 * Identificador auxiliar
	 *
	 * @since 1.0
	 */
	private String codigoAuxiliar;
	
	/**
	 * Nombre
	 *
	 * @since 1.0
	 */
	private String nombre;
	
	/**
	 * Valor unitario
	 *
	 * @since 1.0
	 */
	private float valorUnitario;
	
	/**
	 * Atributo opcional 1
	 *
	 * @since 1.0
	 */
	private String atributo1;
	
	/**
	 * Descripción del atributo opcional 1
	 *
	 * @since 1.0
	 */
	private String descripcion1;
	
	/**
	 * Lista de compras
	 *
	 * @since 1.0
	 */
	private Set<Compra> compras = new HashSet<Compra>(0);
	
	/**
	 * Lista de ventas
	 *
	 * @since 1.0
	 */
	private Set<VentaVsProducto> ventaVsProductos = new HashSet<VentaVsProducto>(0);
	
	/**
	 * Lista de empresas a las que pertenece el producto.
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsProducto> empresasVsProductos = new HashSet<EmpresaVsProducto>(0);

	public Producto() {
	}

	public Producto(int idProducto, String codigoPrincipal, TarifaIva tarifaIva,
			TipoProducto tipoProducto, String codigoAuxiliar, String nombre,
			float valorUnitario) {
		this.idProducto = idProducto;
		this.codigoPrincipal = codigoPrincipal;
		this.tarifaIva = tarifaIva;
		this.tipoProducto = tipoProducto;
		this.codigoAuxiliar = codigoAuxiliar;
		this.nombre = nombre;
		this.valorUnitario = valorUnitario;
	}

	public Producto(int idProducto, String codigoPrincipal,TarifaIva tarifaIva,
			TipoProducto tipoProducto, String codigoAuxiliar,
			String nombre, float valorUnitario, String atributo1,
			String descripcion1, Set<Compra> compras, Set<EmpresaVsProducto> empresas,
			Set<VentaVsProducto> ventaVsProductos) {
		this.idProducto = idProducto;
		this.codigoPrincipal = codigoPrincipal;
		this.tarifaIva = tarifaIva;
		this.tipoProducto = tipoProducto;
		this.codigoAuxiliar = codigoAuxiliar;
		this.nombre = nombre;
		this.valorUnitario = valorUnitario;
		this.atributo1 = atributo1;
		this.descripcion1 = descripcion1;
		this.compras = compras;
		this.ventaVsProductos = ventaVsProductos;
		this.empresasVsProductos = empresas;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoPrincipal() {
		return this.codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		this.codigoPrincipal = codigoPrincipal;
	}

	public TarifaIva getTarifaIva() {
		return this.tarifaIva;
	}

	public void setTarifaIva(TarifaIva tarifaIva) {
		this.tarifaIva = tarifaIva;
	}

	public TipoProducto getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getCodigoAuxiliar() {
		return this.codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getAtributo1() {
		return this.atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getDescripcion1() {
		return this.descripcion1;
	}

	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<VentaVsProducto> getVentaVsProductos() {
		return this.ventaVsProductos;
	}

	public void setVentaVsProductos(Set<VentaVsProducto> ventaVsProductos) {
		this.ventaVsProductos = ventaVsProductos;
	}

	public Set<EmpresaVsProducto> getEmpresasVsProductos() {
		return empresasVsProductos;
	}

	public void setEmpresasVsProductos(Set<EmpresaVsProducto> empresasVsProductos) {
		this.empresasVsProductos = empresasVsProductos;
	}

	
}
