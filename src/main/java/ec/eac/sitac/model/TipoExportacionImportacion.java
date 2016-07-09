// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_exportacion_importacion de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoExportacionImportacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idTipoExpImp;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Texto que describe el tipo de exportación importación
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de importaciones
	 *
	 * @since 2015
	 */
	private Set<Importacion> importacions = new HashSet<Importacion>(0);
	
	/**
	 * Lista de exportaciones
	 *
	 * @since 2015
	 */
	private Set<Exportacion> exportacions = new HashSet<Exportacion>(0);

	public TipoExportacionImportacion() {
	}

	public TipoExportacionImportacion(int id, String nombre) {
		this.idTipoExpImp = id;
		this.nombre = nombre;
	}

	public TipoExportacionImportacion(int id, String nombre,
			String descripcion, Set<Importacion> importacions, Set<Exportacion> exportacions) {
		this.idTipoExpImp = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.importacions = importacions;
		this.exportacions = exportacions;
	}

	public int getIdTipoExpImp() {
		return this.idTipoExpImp;
	}

	public void setIdTipoExpImp(int id) {
		this.idTipoExpImp = id;
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

	public Set<Importacion> getImportacions() {
		return this.importacions;
	}

	public void setImportacions(Set<Importacion> importacions) {
		this.importacions = importacions;
	}

	public Set<Exportacion> getExportacions() {
		return this.exportacions;
	}

	public void setExportacions(Set<Exportacion> exportacions) {
		this.exportacions = exportacions;
	}

}
