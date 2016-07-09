package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla Bnaco de la base de datos
 *
 * @author Luis M. Teijon gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */

public class DenegarPermisoControlador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idPermiso;
	
	/**
	 * Nombre del controlador sobre el que se registrarán los permisos
	 *
	 * @since 2015
	 */
	private String controlador;

	/**
	 * Permiso de lectura
	 *
	 * @since 2015
	 */
	private boolean leer;
	
	/**
	 * Permiso de inserción
	 *
	 * @since 2015
	 */
	
	private boolean insertar;
	
	/**
	 * Permiso de actualización
	 *
	 * @since 2015
	 */
	private boolean actualizar;
	
	/**
	 * Permiso de eliminación
	 *
	 * @since 2015
	 */
	private boolean borrar;
	
	/**
	 * Rol sobre el cual se registrarán los permisos
	 *
	 * @since 2015
	 */
	private Rol rol;
	
	public DenegarPermisoControlador() {
		super();
	}


	public DenegarPermisoControlador(int idPermiso, String controlador, boolean leer, boolean insertar, boolean actualizar, boolean borrar, Rol rol) {
		super();
		this.idPermiso = idPermiso;
		this.controlador = controlador;
		this.leer = leer;
		this.insertar = insertar;
		this.actualizar = actualizar;
		this.borrar = borrar;
		this.rol = rol;
	}


	public int getIdPermiso() {
		return idPermiso;
	}


	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}


	public String getControlador() {
		return controlador;
	}


	public void setControlador(String controlador) {
		this.controlador = controlador;
	}


	public boolean isLeer() {
		return leer;
	}


	public void setLeer(boolean leer) {
		this.leer = leer;
	}


	public boolean isInsertar() {
		return insertar;
	}


	public void setInsertar(boolean insertar) {
		this.insertar = insertar;
	}


	public boolean isActualizar() {
		return actualizar;
	}


	public void setActualizar(boolean actualizar) {
		this.actualizar = actualizar;
	}


	public boolean isBorrar() {
		return borrar;
	}


	public void setBorrar(boolean borrar) {
		this.borrar = borrar;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
