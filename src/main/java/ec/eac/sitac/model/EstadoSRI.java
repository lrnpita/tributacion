package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla EstadoSRI de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class EstadoSRI {
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idEstado;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Descripcion
	 *
	 * @since 2015
	 */
	private String descripcion;
	
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
	private Set<Venta> ventas = new HashSet<Venta>(0);
	
	
	/**
	 * Lista de exportaciones
	 *
	 * @since 1.0
	 */
	private Set<Exportacion> exportaciones = new HashSet<Exportacion>(0);

	public EstadoSRI(){
		
	}
	
	public EstadoSRI(int idEstado, String nombre) {
		this.idEstado = idEstado;
		this.nombre = nombre;
	}
	
	public EstadoSRI(int idEstado, String nombre, String descripcion) {
		this.idEstado = idEstado;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Exportacion> getExportaciones() {
		return exportaciones;
	}

	public void setExportaciones(Set<Exportacion> exportaciones) {
		this.exportaciones = exportaciones;
	}
	
	
	

}
