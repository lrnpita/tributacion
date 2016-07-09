// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla PuntoEmision de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class PuntoEmision implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int id;
	
	/**
	 * Empresa
	 *
	 * @see Empresa
	 * @since 2015
	 */
	private Empresa empresa;
	
	/**
	 * Usuario
	 *
	 * @see Usuario
	 * @since 2015
	 */
	private Usuario usuario;
	
	/**
	 * Serie
	 *
	 * @since 2015
	 */
	private String serie;
	
	/**
	 * Secuencia del documento de facturación a generar
	 *
	 * @since 2015
	 */
	private int secFactura;
	
	/**
	 * Secuencia del documento de nota de crédito a generar
	 *
	 * @since 2015
	 */
	private int secRetencion;
	
	/**
	 * Número inicial a partir del cual el punto de emisión comenzará a generar facturas
	 *
	 * @since 1.0
	 */
	private int numInicialFactura;
	
	/**
	 * Número inicial a partir del cual el punto de emisión comenzará a generar notas de créditos
	 *
	 * @since 1.0
	 */
	private int numInicialRetencion;
	
	/**
	 * Texto que describe el punto de emisión
	 *
	 * @since 2015
	 */
	private String descripcion;
	private Set<Venta> ventas = new HashSet<Venta>(0);
	private Set<Compra> compras = new HashSet<Compra>(0);
	private Set<Exportacion> exportacions = new HashSet<Exportacion>(0);

	public PuntoEmision() {
	}

	public PuntoEmision(int id, Empresa empresa, Usuario usuario, String serie) {
		this.id = id;
		this.empresa = empresa;
		this.usuario = usuario;
		this.serie = serie;
	}

	public PuntoEmision(int id, Empresa empresa, Usuario usuario, String serie, int secFactura, int secRetencion,
			String descripcion, int numInicialFactura, int numInicialRetencion,
			Set<Venta> ventas, Set<Compra> compras, Set<Exportacion> exportacions) {
		this.id = id;
		this.empresa = empresa;
		this.usuario = usuario;
		this.serie = serie;
		this.secFactura = secFactura;
		this.secRetencion = secRetencion;
		this.descripcion = descripcion;
		this.numInicialFactura = numInicialFactura;
		this.numInicialRetencion = numInicialRetencion;
		this.ventas = ventas;
		this.compras = compras;
		this.exportacions = exportacions;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	
	public int getSecFactura() {
		return this.secFactura;
	}

	public void setSecFactura(int secFactura) {
		this.secFactura = secFactura;
	}
	

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumInicialFactura() {
		return this.numInicialFactura;
	}

	public void setNumInicialFactura(int numInicialFactura) {
		this.numInicialFactura = numInicialFactura;
	}
	
	public int getSecRetencion() {
		return secRetencion;
	}

	public void setSecRetencion(int secRetencion) {
		this.secRetencion = secRetencion;
	}

	public int getNumInicialRetencion() {
		return numInicialRetencion;
	}

	public void setNumInicialRetencion(int numInicialRetencion) {
		this.numInicialRetencion = numInicialRetencion;
	}

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<Exportacion> getExportacions() {
		return this.exportacions;
	}

	public void setExportacions(Set<Exportacion> exportacions) {
		this.exportacions = exportacions;
	}

}
