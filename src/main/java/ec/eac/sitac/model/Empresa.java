// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla empresa de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Empresa implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private int idEmpresa;
	
	/**
	 * Contador de la empresa
	 *
	 * @see PersonalEmpresa
	 * @since 1.0
	 */
	private PersonalEmpresa personalEmpresaByIdContador;
	
	/**
	 * Representante legal de la empresa
	 *
	 * @see PersonalEmpresa
	 * @since 1.0
	 */
	private PersonalEmpresa personalEmpresaByIdRepLegal;
	
	/**
	 * Tipo de ambiente
	 *
	 * @see TipoAmbiente
	 * @since 1.0
	 */
	private TipoAmbiente tipoAmbiente;
	
	/**
	 * Tipo de emisión
	 *
	 * @see PersonalEmpresa
	 * @since 1.0
	 */
	private TipoEmision tipoEmision;
	
	/**
	 * Nombre
	 *
	 * @since 1.0
	 */
	private String nombre;
	
	/**
	 * Texto que describe la empresa
	 *
	 * @since 1.0
	 */
	private String descripcion;
	
	/**
	 * Ruc del contribuyente
	 *
	 * @since 1.0
	 */
	private String rucContribuyente;
	
	/**
	 * Nombre del contribuyente
	 *
	 * @since 1.0
	 */
	private String nombreContribuyente;
	
	/**
	 * Nombre comercial
	 *
	 * @since 1.0
	 */
	private String nombreComercial;
	
	/**
	 * Dirección matriz
	 *
	 * @since 1.0
	 */
	private String direccionMatriz;
	
	/**
	 * Número
	 *
	 * @since 1.0
	 */
	private String numero;
	
	/**
	 * Dirección sucursal
	 *
	 * @since 1.0
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad
	 *
	 * @since 1.0
	 */
	private Ciudad ciudad;
	
	/**
	 * Teléfono
	 *
	 * @since 1.0
	 */
	private String telefono;
	
	/**
	 * Email
	 *
	 * @since 1.0
	 */
	private String email;
	
	/**
	 * Lleva contabilidad
	 *
	 * @since 1.0
	 */
	private Boolean llevaContabilidad;
	
	/**
	 * Contribuyente especial
	 *
	 * @since 1.0
	 */
	private String noResolucionContribEspecial;
	
	/**
	 * Logo
	 *
	 * @since 1.0
	 */
	private String logo;
	
	
	/**
	 * Partido político
	 *
	 * @since 1.0
	 */
	private boolean partidoPolitico;
	
	/**
	 * Exportador
	 *
	 * @since 1.0
	 */
	private boolean exportador;
	
	/**
	 * Lista de compras
	 *
	 * @since 1.0
	 */
	private Set<Compra> compras = new HashSet<Compra>(0);
	
	/**
	 * Lista de movimientos
	 *
	 * @since 1.0
	 */
	private Set<Movimiento> movimientos = new HashSet<Movimiento>(0);
	
	/**
	 * Lista de punto de emisión
	 *
	 * @since 1.0
	 */
	private Set<PuntoEmision> puntoEmisions = new HashSet<PuntoEmision>(0);
	
	/**
	 * Lista de ventas
	 *
	 * @since 1.0
	 */
	private Set<Venta> ventas = new HashSet<Venta>(0);
	
	/**
	 * Lista de importaciones
	 *
	 * @since 1.0
	 */
	private Set<Importacion> importacions = new HashSet<Importacion>(0);
	
	/**
	 * Lista de importaciones
	 *
	 * @since 1.0
	 */
	private Set<Exportacion> exportaciones = new HashSet<Exportacion>(0);
	
	/**
	 * Lista de clientes que tiene la empresa
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsCliente> clientes = new HashSet<EmpresaVsCliente>(0);
	
	/**
	 * Lista de productos que tiene la empresa
	 *
	 * @since 1.0
	 */
	private Set<Producto> productos = new HashSet<Producto>(0);
	
	/**
	 * Lista de clientes que tiene la empresa
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsProveedor> proveedores = new HashSet<EmpresaVsProveedor>(0);
	
	/**
	 * Lista de trabajadores que tiene la empresa
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsTrabajador> trabajadores = new HashSet<EmpresaVsTrabajador>(0);
	
	public Empresa() {
	}

	public Empresa(int idEmpresa, PersonalEmpresa personalEmpresaByIdContador,
			PersonalEmpresa personalEmpresaByIdRepLegal,
			TipoAmbiente tipoAmbiente, TipoEmision tipoEmision, String nombre,
			String rucContribuyente, String nombreContribuyente, String nombreComercial,
			String direccionMatriz, String numero, String telefono,
			String email,
			boolean partidoPolitico, boolean exportador) {
		this.idEmpresa = idEmpresa;
		this.personalEmpresaByIdContador = personalEmpresaByIdContador;
		this.personalEmpresaByIdRepLegal = personalEmpresaByIdRepLegal;
		this.tipoAmbiente = tipoAmbiente;
		this.tipoEmision = tipoEmision;
		this.nombre = nombre;
		this.rucContribuyente = rucContribuyente;
		this.nombreContribuyente = nombreContribuyente;
		this.nombreComercial = nombreComercial;
		this.direccionMatriz = direccionMatriz;
		this.numero = numero;
		this.telefono = telefono;
		this.email = email;
		this.partidoPolitico = partidoPolitico;
		this.exportador = exportador;
	}

	public Empresa(int idEmpresa, PersonalEmpresa personalEmpresaByIdContador,
			PersonalEmpresa personalEmpresaByIdRepLegal,
			TipoAmbiente tipoAmbiente, TipoEmision tipoEmision, String nombre,
			String descripcion, String rucContribuyente, String nombreContribuyente,
			String nombreComercial, String direccionMatriz, String numero,
			String direccionSucursal, Ciudad ciudad, String telefono,
			String email, Boolean llevaContabilidad,
			String noResolucionContribEspecial, String logo, 
			boolean partidoPolitico, boolean exportador, Set<Compra> compras, Set<Movimiento> movimientos,
			Set<PuntoEmision> puntoEmisions, Set<Venta> ventas, Set<Importacion> importacions, Set<Exportacion> exportaciones,
			Set<EmpresaVsCliente> clientes, Set<EmpresaVsProveedor> proveedores,
			Set<EmpresaVsTrabajador> trabajadores, Set<Producto> productos) {
		this.idEmpresa = idEmpresa;
		this.personalEmpresaByIdContador = personalEmpresaByIdContador;
		this.personalEmpresaByIdRepLegal = personalEmpresaByIdRepLegal;
		this.tipoAmbiente = tipoAmbiente;
		this.tipoEmision = tipoEmision;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.rucContribuyente = rucContribuyente;
		this.nombreComercial = nombreComercial;
		this.direccionMatriz = direccionMatriz;
		this.numero = numero;
		this.direccionSucursal = direccionSucursal;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.email = email;
		this.llevaContabilidad = llevaContabilidad;
		this.noResolucionContribEspecial = noResolucionContribEspecial;
		this.logo = logo;
		this.partidoPolitico = partidoPolitico;
		this.exportador = exportador;
		this.compras = compras;
		this.puntoEmisions = puntoEmisions;
		this.ventas = ventas;
		this.importacions = importacions;
		this.exportaciones = exportaciones;
		this.clientes = clientes;
		this.proveedores = proveedores;
		this.trabajadores = trabajadores;
		this.movimientos = movimientos;
		this.productos = productos;
	}

	public int getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public PersonalEmpresa getPersonalEmpresaByIdContador() {
		return this.personalEmpresaByIdContador;
	}

	public void setPersonalEmpresaByIdContador(
			PersonalEmpresa personalEmpresaByIdContador) {
		this.personalEmpresaByIdContador = personalEmpresaByIdContador;
	}

	public PersonalEmpresa getPersonalEmpresaByIdRepLegal() {
		return this.personalEmpresaByIdRepLegal;
	}

	public void setPersonalEmpresaByIdRepLegal(
			PersonalEmpresa personalEmpresaByIdRepLegal) {
		this.personalEmpresaByIdRepLegal = personalEmpresaByIdRepLegal;
	}

	public TipoAmbiente getTipoAmbiente() {
		return this.tipoAmbiente;
	}

	public void setTipoAmbiente(TipoAmbiente tipoAmbiente) {
		this.tipoAmbiente = tipoAmbiente;
	}

	public TipoEmision getTipoEmision() {
		return this.tipoEmision;
	}

	public void setTipoEmision(TipoEmision tipoEmision) {
		this.tipoEmision = tipoEmision;
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

	public String getRucContribuyente() {
		return this.rucContribuyente;
	}

	public void setRucContribuyente(String rucContribuyente) {
		this.rucContribuyente = rucContribuyente;
	}

	public String getNombreContribuyente() {
		return nombreContribuyente;
	}

	public void setNombreContribuyente(String nombreContribuyente) {
		this.nombreContribuyente = nombreContribuyente;
	}

	public String getNombreComercial() {
		return this.nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getDireccionMatriz() {
		return this.direccionMatriz;
	}

	public void setDireccionMatriz(String direccionMatriz) {
		this.direccionMatriz = direccionMatriz;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDireccionSucursal() {
		return this.direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getLlevaContabilidad() {
		return this.llevaContabilidad;
	}

	public void setLlevaContabilidad(Boolean llevaContabilidad) {
		this.llevaContabilidad = llevaContabilidad;
	}

	public String getNoResolucionContribEspecial() {
		return this.noResolucionContribEspecial;
	}

	public void setNoResolucionContribEspecial(String noResolucionContribEspecial) {
		this.noResolucionContribEspecial = noResolucionContribEspecial;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isPartidoPolitico() {
		return this.partidoPolitico;
	}

	public void setPartidoPolitico(boolean partidoPolitico) {
		this.partidoPolitico = partidoPolitico;
	}

	public boolean isExportador() {
		return this.exportador;
	}

	public void setExportador(boolean exportador) {
		this.exportador = exportador;
	}

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<PuntoEmision> getPuntoEmisions() {
		return this.puntoEmisions;
	}

	public void setPuntoEmisions(Set<PuntoEmision> puntoEmisions) {
		this.puntoEmisions = puntoEmisions;
	}

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Importacion> getImportacions() {
		return this.importacions;
	}

	public void setImportacions(Set<Importacion> importacions) {
		this.importacions = importacions;
	}

	public Set<EmpresaVsCliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<EmpresaVsCliente> clientes) {
		this.clientes = clientes;
	}

	public Set<EmpresaVsProveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(Set<EmpresaVsProveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Set<EmpresaVsTrabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(Set<EmpresaVsTrabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	public Set<Exportacion> getExportaciones() {
		return exportaciones;
	}

	public void setExportaciones(Set<Exportacion> exportaciones) {
		this.exportaciones = exportaciones;
	}
	
	
}
