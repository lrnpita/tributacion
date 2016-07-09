// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_comprobante de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoComprobante implements java.io.Serializable {

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
	 * Texto que describe el tipo de comprobante
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Campo para saber si el timpo de comprobante se muestra en la vista Exportación
	 *
	 * @since 2015
	 */
	private Boolean vistaExportacion;
	
	/**
	 * Campo para saber si el timpo de comprobante se muestra en la vista Importación
	 *
	 * @since 2015
	 */
	private Boolean vistaImportacion;
	
	/**
	 * Campo para saber si el timpo de comprobante se muestra en la vista Compra
	 *
	 * @since 2015
	 */
	private Boolean vistaCompra;
	
	/**
	 * Campo para saber si el timpo de comprobante se muestra en la vista Venta
	 *
	 * @since 2015
	 */
	private Boolean vistaVenta;
	
	/**
	 * Campo para saber si el timpo de comprobante se muestra en la vista Venta
	 *
	 * @since 2015
	 */
	private Boolean vistaAnulados;
	
	/**
	 * Lista de exportaciones
	 *
	 * @since 2015
	 */
	private Set<Exportacion> exportacions = new HashSet<Exportacion>(0);
	
	/**
	 * Lista de importaciones
	 *
	 * @since 2015
	 */
	private Set<Importacion> importacions = new HashSet<Importacion>(0);
	
	/**
	 * Lista de ventas
	 *
	 * @since 2015
	 */
	private Set<Venta> ventas = new HashSet<Venta>(0);

	public TipoComprobante() {
	}

	public TipoComprobante(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TipoComprobante(String codigo, String nombre, String descripcion,
			Boolean vistaExportacion, Boolean vistaImportacion,
			Boolean vistaCompra, Boolean vistaVenta, Boolean vistaAnulados,
			Set<Exportacion> exportacions, Set<Importacion> importacions,
			Set<Venta> ventas) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.vistaExportacion = vistaExportacion;
		this.vistaImportacion = vistaImportacion;
		this.vistaCompra = vistaCompra;
		this.vistaVenta = vistaVenta;
		this.vistaAnulados = vistaAnulados;
		this.exportacions = exportacions;
		this.importacions = importacions;
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

	public Set<Exportacion> getExportacions() {
		return this.exportacions;
	}

	public void setExportacions(Set<Exportacion> exportacions) {
		this.exportacions = exportacions;
	}

	public Set<Importacion> getImportacions() {
		return this.importacions;
	}

	public void setImportacions(Set<Importacion> importacions) {
		this.importacions = importacions;
	}

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Boolean isVistaExportacion() {
		return vistaExportacion;
	}

	public void setVistaExportacion(Boolean vistaExportacion) {
		this.vistaExportacion = vistaExportacion;
	}

	public Boolean isVistaCompra() {
		return vistaCompra;
	}

	public void setVistaCompra(Boolean vistaCompra) {
		this.vistaCompra = vistaCompra;
	}

	public Boolean isVistaVenta() {
		return vistaVenta;
	}

	public void setVistaVenta(Boolean vistaVenta) {
		this.vistaVenta = vistaVenta;
	}

	public Boolean getVistaImportacion() {
		return vistaImportacion;
	}

	public void setVistaImportacion(Boolean vistaImportacion) {
		this.vistaImportacion = vistaImportacion;
	}

	public Boolean getVistaAnulados() {
		return vistaAnulados;
	}

	public void setVistaAnulados(Boolean vistaAnulados) {
		this.vistaAnulados = vistaAnulados;
	}



}
