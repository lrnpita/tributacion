// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tipo_proveedor_o_cliente de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoProveedorOCliente implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idTipoProveedorOCliente;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Texto que describe el tipo de proveedor o cliente
	 *
	 * @since 2015
	 */
	private String descripcion;
	
	/**
	 * Lista de proveedores o clientes
	 *
	 * @since 2015
	 */
	private Set<PersonalEmpresa> personalEmpresas = new HashSet<PersonalEmpresa>(0);

	public TipoProveedorOCliente() {
	}

	public TipoProveedorOCliente(int id, String nombre) {
		this.idTipoProveedorOCliente = id;
		this.nombre = nombre;
	}

	public TipoProveedorOCliente(int id, String nombre, String descripcion,
			Set<PersonalEmpresa> personalEmpresas) {
		this.idTipoProveedorOCliente = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.personalEmpresas = personalEmpresas;
	}

	public int getIdTipoProveedorOCliente() {
		return idTipoProveedorOCliente;
	}

	public void setIdTipoProveedorOCliente(int idTipoProveedorOCliente) {
		this.idTipoProveedorOCliente = idTipoProveedorOCliente;
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

	public Set<PersonalEmpresa> getPersonalEmpresas() {
		return this.personalEmpresas;
	}

	public void setPersonalEmpresas(Set<PersonalEmpresa> personalEmpresas) {
		this.personalEmpresas = personalEmpresas;
	}

}
