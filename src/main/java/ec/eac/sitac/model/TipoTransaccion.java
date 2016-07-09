// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * Modelo correspondiente a la tabla tipo_transaccion de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoTransaccion implements java.io.Serializable {

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
	 * Texto que describe el tipo de transacción
	 *
	 * @since 2015
	 */
	private int anno;
	
	/**
	 * Porcentaje para retención impuesto a la renta
	 *
	 * @since 2015
	 */
	private int porcentaje;
	
	/**
	 * Si el tipo de transacción es bien
	 *
	 * @since 2015
	 */
	private Boolean bien;
	
	/**
	 * Si el tipo de transacción es servicio
	 *
	 * @since 2015
	 */
	private Boolean servicio;
	
	/**
	 * Lista de importaciones
	 *
	 * @since 2015
	 */
	private Set<Importacion> importacions = new HashSet<Importacion>(0);
	
	/**
	 * Lista de detalles de las compras
	 *
	 * @since 2015
	 */
	private Set<DetallesCompra> detallesCompras = new HashSet<DetallesCompra>(0);

	public TipoTransaccion() {
	}

	public TipoTransaccion(String codigo, String nombre, int porcentaje) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.porcentaje = porcentaje;
	}

	public TipoTransaccion(String codigo, String nombre, int anno, int porcentaje, Boolean bien, Boolean servicio,
			Set<Importacion> importacions, Set<DetallesCompra> detallesCompras) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.anno = anno;
		this.porcentaje = porcentaje;
		this.bien = bien;
		this.servicio = servicio;
		this.importacions = importacions;
		this.detallesCompras = detallesCompras;
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

	public int getAnno() {
		return this.anno;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public Set<Importacion> getImportacions() {
		return this.importacions;
	}

	public void setImportacions(Set<Importacion> importacions) {
		this.importacions = importacions;
	}

	public Set<DetallesCompra> getDetallesCompras() {
		return this.detallesCompras;
	}

	public void setDetallesCompras(Set<DetallesCompra> detallesCompras) {
		this.detallesCompras = detallesCompras;
	}

	public Boolean getBien() {
		return bien;
	}

	public void setBien(Boolean bien) {
		this.bien = bien;
	}

	public Boolean getServicio() {
		return servicio;
	}

	public void setServicio(Boolean servicio) {
		this.servicio = servicio;
	}

}
