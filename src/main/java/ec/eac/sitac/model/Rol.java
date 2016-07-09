package ec.eac.sitac.model;

import java.util.HashSet;

import java.util.Set;
/**
 * Modelo correspondiente a la tabla Rol de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Rol implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idRol;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Descripción del Rol
	 * 
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de usuarios
	 *
	 * @since 2015
	 */
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	
	/**
	 * Lista de permisos denegados
	 *
	 * @since 2015
	 */
	private Set<DenegarPermisoControlador> permisosDenegados = new HashSet<DenegarPermisoControlador>(0);
	
	/**
	 * Lista de permisos concendidos
	 *
	 * @since 2015
	 */
	private Set<AutorizarPermisoControlador> permisosAutorizados = new HashSet<AutorizarPermisoControlador>(0);
	
	public Rol() {
		
	}
	
	public Rol(int id, String nombre) {
		this.idRol = id;
		this.nombre = nombre;
	}
	
	public Rol(int id, String nombre, String descripcion, Set<Usuario> usuarios, Set<DenegarPermisoControlador> permisosDenegados, Set<AutorizarPermisoControlador> permisosAutorizados) {
		this.idRol = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usuarios = usuarios;
		this.permisosDenegados = permisosDenegados;
		this.permisosAutorizados = permisosAutorizados;
	}
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int id) {
		this.idRol = id;
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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<DenegarPermisoControlador> getPermisosDenegados() {
		return permisosDenegados;
	}

	public void setPermisosDenegados(Set<DenegarPermisoControlador> permisosDenegados) {
		this.permisosDenegados = permisosDenegados;
	}
	
	public Set<AutorizarPermisoControlador> getPermisosAutorizados() {
		return permisosAutorizados;
	}

	public void setPermisosAutorizados(Set<AutorizarPermisoControlador> permisosAutorizados) {
		this.permisosAutorizados = permisosAutorizados;
	}
}
