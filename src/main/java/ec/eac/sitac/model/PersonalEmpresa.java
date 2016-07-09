// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ec.eac.sitac.dao.TipoProveedorOClienteHome;

/**
 * Modelo correspondiente a la tabla personal_empresa de la base de datos. 
 * La tabla personal_empresa guarda informacion de los clientes, proveedores, trabajadores, contadores
 * y representantes legales. De esa manera se evitó una generalización que obligara a la utilización de JOINs.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */

public class PersonalEmpresa implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private String id;
	
	/**
	 * {@link Canton} asociado al trabajador. Este atributo se asocia solamente al trabajador.
	 *
	 * @see Canton
	 * @since 1.0
	 */
	private Canton canton;
	
	/**
	 * {@link Cargo} asociado al trabajador. Este atributo se asocia solamente al trabajador.
	 *
	 * @see Cargo
	 * @since 1.0
	 */
	private Cargo cargo;
	
	/**
	 * {@link Ciudad} donde radica actualmente la persona.
	 * Este atributo no aplica para los contadores y representantes legales.
	 *
	 * @see Ciudad
	 * @since 1.0
	 */
	private Ciudad ciudad;
	
	/**
	 * Condición actual del trabajador. Este atributo se asocia solamente al trabajador.
	 *
	 * @see CondicionDeTrabajador
	 * @since 1.0
	 */
	private CondicionDeTrabajador condicionDeTrabajador;
	
	/**
	 * País de residencia del trabajador. Este atributo se asocia solamente al trabajador.
	 *
	 * @see Pais
	 * @since 1.0
	 */
	private Pais pais;
	
	/**
	 * Residencia actual del trabajador, esta puede ser: Local o en el Extranjero. 
	 * Este atributo se asocia solamente al trabajador.
	 *
	 * @see Residencia
	 * @since 1.0
	 */
	private Residencia residencia;
	
	/**
	 * Tipo de identificación de la persona.
	 *
	 * @see TipoIdentificacion
	 * @since 1.0
	 */
	private TipoIdentificacion tipoIdentificacion;
	
	/**
	 * Tipo de identificación de la persona que está sustituyendo temporalmente al trabajador, si aplica.
	 * Este atributo se asocia solamente al trabajador.
	 *
	 * @see TipoIdentificacion
	 * @since 1.0
	 */
	private TipoIdentificacion tipoIdentificacionNewTrabajador;
	
	/**
	 * Tipo de persona: Natural o Jurídica.
	 * Este atributo se asocia solamente al proveedor o al cliente.
	 *
	 * @see TipoProveedorOClienteHome
	 * @since 1.0
	 */
	private TipoProveedorOCliente tipoProveedorOCliente;
	
	/**
	 * Tipo de salario actual del trabajador. Este atributo se asocia solamente al trabajador.
	 *
	 * @see TipoSalario
	 * @since 1.0
	 */
	private TipoSalario tipoSalario;
	
	/**
	 * Nombre de la persona.
	 *
	 * @since 1.0
	 */
	private String nombre;
	
	/**
	 * Verdadero si la persona es un contador.
	 *
	 * @since 1.0
	 */
	private boolean contador;
	
	/**
	 * Verdadero si la persona es un representante legal.
	 *
	 * @since 1.0
	 */
	private boolean repLegal;
	
	/**
	 * Verdadero si la persona es un cliente.
	 *
	 * @since 1.0
	 */
	private boolean cliente;
	
	/**
	 * La direccion completa de la persona. Este atributo aplica solamente para los clientes y los proveedores.
	 *
	 * @since 1.0
	 */
	private String direccion;
	
	/**
	 * Teléfono de la persona. Este atributo no aplica para los contadores y representantes legales.
	 *
	 * @since 1.0
	 */
	private String telefono;
	
	/**
	 * Email de la persona. Este atributo aplica solamente para los clientes y los proveedores.
	 *
	 * @since 1.0
	 */
	private String email;
	
	/**
	 * Verdadero si la persona es un proveedor.
	 *
	 * @since 1.0
	 */
	private boolean proveedor;
	
	/**
	 * 
	 *
	 * @since 1.0
	 */
	private String identificacionFiscal;
	
	/**
	 * Email alternativo de la persona. Este atributo aplica solamente para los clientes.
	 *
	 * @since 1.0
	 */
	private String email2;
	
	/**
	 * Numero de la casa de la persona. Este atributo aplica solamente para los trabajadores y los proveedores.
	 *
	 * @since 1.0
	 */
	private String numeroCasa;
	
	/**
	 * Verdadero si el proveedor tiene RUC de Extranjero.
	 * Este atributo aplica solamente para los proveedores.
	 *
	 * @since 1.0
	 */
	private Boolean rucExtranjero;
	
	/**
	 * Verdadero si el proveedor es contribuyente especial.
	 * Este atributo aplica solamente para los proveedores.
	 *
	 * @since 1.0
	 */
	private Boolean contribuyenteEspecial;
	
	/**
	 * Verdadero si el proveedor posee Título Superior.
	 * Este atributo aplica solamente para los proveedores.
	 *
	 * @since 1.0
	 */
	private Boolean honorarios;
	
	/**
	 * Verdadero si el proveedor lleva contabilidad.
	 * Este atributo aplica solamente para los proveedores. 
	 *
	 * @since 1.0
	 */
	private Boolean llevaContabilidad;
	
	/**
	 * Verdadero si se le retiene el impuesto a la Renta.
	 * Este atributo aplica solamente para los proveedores. 
	 *
	 * @since 1.0
	 */
	private Boolean retencionIr;
	
	/**
	 * Verdadero si el cliente es parte relacionada.
	 * Este atributo aplica solamente para los clientes. 
	 *
	 * @since 1.0
	 */
	private Boolean parteRelacionada;
	
	/**
	 * Verdadero si la persona es un trabajador.
	 *
	 * @since 1.0
	 */
	private boolean trabajador;
	
	/**
	 * Código de establecimiento donde trabaja la persona.
	 * Este atributo aplica solamente para los trabajadores. 
	 *
	 * @since 1.0
	 */
	private String codigoEstablecimiento;
	
	/**
	 * Apellidos del trabajador.
	 *
	 * @since 1.0
	 */
	private String apellidos;
	
	/**
	 * Verdadero si el trabajador tiene convenio.
	 * Este atributo aplica solamente para los trabajadores. 
	 *
	 * @since 1.0
	 */
	private Boolean convenio;
	
	/**
	 * Porcentaje.
	 * Este atributo aplica solamente para los trabajadores. 
	 *
	 * @since 1.0
	 */
	private Float porcentaje;
	
	/**
	 * Nombre de la calle en donde vive el trabajador.
	 * Este atributo aplica solamente para los trabajadores. 
	 *
	 * @since 1.0
	 */
	private String calle;

	/**
	 * Identificador de la persona que sustituirá al trabajador, si aplica.
	 * Este atributo aplica solamente para los trabajadores. 
	 *
	 * @since 1.0
	 */
	private String idNewTrabajador;
	
	/**
	 * Décimo tercer sueldo.
	 *
	 * @since 1.0
	 */
	private Boolean decimoTercerSueldo;
	
	/**
	 * Fecha de nacimiento
	 *
	 * @since 1.0
	 */
	private Date fechaNacimiento;
	
	/**
	 * Lista de compras.
	 *
	 * @since 1.0
	 */
	private Set<Compra> compras = new HashSet<Compra>(0);
	
	/**
	 * Lista de exportaciones.
	 *
	 * @since 1.0
	 */
	private Set<Exportacion> exportacions = new HashSet<Exportacion>(0);
	
	/**
	 * Lista de importaciones.
	 *
	 * @since 1.0
	 */
	private Set<Importacion> importacions = new HashSet<Importacion>(0);
	
	/**
	 * Lista de contadores.
	 *
	 * @since 1.0
	 */
	private Set<Empresa> empresasForIdContador = new HashSet<Empresa>(0);
	
	/**
	 * Lista de ventas.
	 *
	 * @since 1.0
	 */
	private Set<Venta> ventas = new HashSet<Venta>(0);
	
	/**
	 * Lista de representantes legales.
	 *
	 * @since 1.0
	 */
	private Set<Empresa> empresasForIdRepLegal = new HashSet<Empresa>(0);
	
	/**
	 * Lista de movimientos.
	 *
	 * @since 1.0
	 */
	private Set<Movimiento> movimientos = new HashSet<Movimiento>(0);
	
	/**
	 * Lista de empresas a las que pertenece el cliente.
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsCliente> empresasForCliente = new HashSet<EmpresaVsCliente>(0);
	
	/**
	 * Lista de empresas a las que pertenece el proveedor.
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsProveedor> empresasForProveedor = new HashSet<EmpresaVsProveedor>(0);
	
	/**
	 * Lista de empresas a las que pertenece el trabajador.
	 *
	 * @since 1.0
	 */
	private Set<EmpresaVsTrabajador> empresasForTrabajador = new HashSet<EmpresaVsTrabajador>(0);

	public PersonalEmpresa() {
	}

	public PersonalEmpresa(String id, TipoIdentificacion tipoIdentificacion,
			boolean contador, boolean repLegal, boolean cliente,
			boolean proveedor, boolean trabajador , Boolean decimoTercerSueldo ) {
		this.id = id;
		this.tipoIdentificacion = tipoIdentificacion;
		this.contador = contador;
		this.repLegal = repLegal;
		this.cliente = cliente;
		this.proveedor = proveedor;
		this.trabajador = trabajador;
		this.decimoTercerSueldo = decimoTercerSueldo;
	}

	public PersonalEmpresa(String id, Canton canton, Cargo cargo,
			Ciudad ciudad, CondicionDeTrabajador condicionDeTrabajador,
			Pais pais, Residencia residencia,
			TipoIdentificacion tipoIdentificacion,
			TipoIdentificacion tipoIdentificacionNewTrabajador,
			TipoProveedorOCliente tipoProveedorOCliente,
			TipoSalario tipoSalario, String nombre, boolean contador,
			boolean repLegal, boolean cliente, String direccion,
			String telefono, String email, boolean proveedor,
			String identificacionFiscal, String email2, String numeroCasa,
			Boolean rucExtranjero,
			Boolean contribuyenteEspecial, Boolean honorarios, Boolean parteRelacionada,
			Boolean llevaContabilidad, Boolean retencionIr, boolean trabajador,
			String codigoEstablecimiento, String apellidos, Boolean convenio,
			Float porcentaje, String calle, Date fechaNacimiento,
			String idNewTrabajador, boolean decimoTercerSueldo, Set<Compra> compras, Set<Exportacion> exportacions,
			Set<Importacion> importacions, Set<Empresa> empresasForIdContador, Set<Venta> ventas,
			Set<Empresa> empresasForIdRepLegal, Set<Movimiento> movimientos,
			Set<EmpresaVsCliente> empresaForCliente, Set<EmpresaVsProveedor> empresaForProveedor,
			Set<EmpresaVsTrabajador> empresaForTrabajador) {
		this.id = id;
		this.canton = canton;
		this.cargo = cargo;
		this.ciudad = ciudad;
		this.condicionDeTrabajador = condicionDeTrabajador;
		this.pais = pais;
		this.residencia = residencia;
		this.tipoIdentificacion = tipoIdentificacion;
		this.tipoIdentificacionNewTrabajador = tipoIdentificacionNewTrabajador;
		this.tipoProveedorOCliente = tipoProveedorOCliente;
		this.tipoSalario = tipoSalario;
		this.nombre = nombre;
		this.contador = contador;
		this.repLegal = repLegal;
		this.cliente = cliente;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.proveedor = proveedor;
		this.identificacionFiscal = identificacionFiscal;
		this.email2 = email2;
		this.numeroCasa = numeroCasa;
		this.rucExtranjero = rucExtranjero;
		this.contribuyenteEspecial = contribuyenteEspecial;
		this.honorarios = honorarios;
		this.parteRelacionada = parteRelacionada;
		this.llevaContabilidad = llevaContabilidad;
		this.retencionIr = retencionIr;
		this.trabajador = trabajador;
		this.codigoEstablecimiento = codigoEstablecimiento;
		this.apellidos = apellidos;
		this.convenio = convenio;
		this.porcentaje = porcentaje;
		this.calle = calle;
		this.fechaNacimiento = fechaNacimiento;
		this.idNewTrabajador = idNewTrabajador;
		this.decimoTercerSueldo = decimoTercerSueldo;
		this.compras = compras;
		this.exportacions = exportacions;
		this.importacions = importacions;
		this.empresasForIdContador = empresasForIdContador;
		this.ventas = ventas;
		this.empresasForIdRepLegal = empresasForIdRepLegal;
		this.movimientos = movimientos;
		this.empresasForCliente = empresaForCliente;
		this.empresasForProveedor = empresaForProveedor;
		this.empresasForTrabajador = empresaForTrabajador;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public CondicionDeTrabajador getCondicionDeTrabajador() {
		return this.condicionDeTrabajador;
	}

	public void setCondicionDeTrabajador(
			CondicionDeTrabajador condicionDeTrabajador) {
		this.condicionDeTrabajador = condicionDeTrabajador;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Residencia getResidencia() {
		return this.residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public TipoIdentificacion getTipoIdentificacionNewTrabajador() {
		return this.tipoIdentificacionNewTrabajador;
	}
	
	public void setTipoIdentificacionNewTrabajador(TipoIdentificacion tipoIdentificacionNewTrabajador) {
		this.tipoIdentificacionNewTrabajador = tipoIdentificacionNewTrabajador;
	}

	public TipoProveedorOCliente getTipoProveedorOCliente() {
		return this.tipoProveedorOCliente;
	}

	public void setTipoProveedorOCliente(
			TipoProveedorOCliente tipoProveedorOCliente) {
		this.tipoProveedorOCliente = tipoProveedorOCliente;
	}

	public TipoSalario getTipoSalario() {
		return this.tipoSalario;
	}

	public void setTipoSalario(TipoSalario tipoSalario) {
		this.tipoSalario = tipoSalario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getContador() {
		return contador;
	}

	public void setContador(boolean contador) {
		this.contador = contador;
	}

	public boolean isRepLegal() {
		return this.repLegal;
	}

	public void setRepLegal(boolean repLegal) {
		this.repLegal = repLegal;
	}

	public boolean isCliente() {
		return this.cliente;
	}

	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public boolean isProveedor() {
		return this.proveedor;
	}

	public void setProveedor(boolean proveedor) {
		this.proveedor = proveedor;
	}

	public String getIdentificacionFiscal() {
		return this.identificacionFiscal;
	}

	public void setIdentificacionFiscal(String identificacionFiscal) {
		this.identificacionFiscal = identificacionFiscal;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getNumeroCasa() {
		return this.numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public Boolean getRucExtranjero() {
		return rucExtranjero;
	}

	public void setRucExtranjero(Boolean rucExtranjero) {
		this.rucExtranjero = rucExtranjero;
	}

	public Boolean getContribuyenteEspecial() {
		return contribuyenteEspecial;
	}

	public void setContribuyenteEspecial(Boolean contribuyenteEspecial) {
		this.contribuyenteEspecial = contribuyenteEspecial;
	}

	public Boolean getHonorarios() {
		return honorarios;
	}

	public void setHonorarios(Boolean honorarios) {
		this.honorarios = honorarios;
	}

	public Boolean getLlevaContabilidad() {
		return llevaContabilidad;
	}

	public void setLlevaContabilidad(Boolean llevaContabilidad) {
		this.llevaContabilidad = llevaContabilidad;
	}

	public Boolean getRetencionIr() {
		return retencionIr;
	}

	public void setRetencionIr(Boolean retencionIr) {
		this.retencionIr = retencionIr;
	}

	public boolean isTrabajador() {
		return this.trabajador;
	}

	public void setTrabajador(boolean trabajador) {
		this.trabajador = trabajador;
	}

	public String getCodigoEstablecimiento() {
		return this.codigoEstablecimiento;
	}

	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Boolean getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Boolean convenio) {
		this.convenio = convenio;
	}

	public Float getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Boolean getParteRelacionada() {
		return parteRelacionada;
	}

	public void setParteRelacionada(Boolean parteRelacionada) {
		this.parteRelacionada = parteRelacionada;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getIdNewTrabajador() {
		return this.idNewTrabajador;
	}

	public void setIdNewTrabajador(String idNewTrabajador) {
		this.idNewTrabajador = idNewTrabajador;
	}

	public Boolean getDecimoTercerSueldo() {
		return decimoTercerSueldo;
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

	public Set<Importacion> getImportacions() {
		return this.importacions;
	}

	public void setImportacions(Set<Importacion> importacions) {
		this.importacions = importacions;
	}

	public Set<Empresa> getEmpresasForIdContador() {
		return this.empresasForIdContador;
	}

	public void setEmpresasForIdContador(Set<Empresa> empresasForIdContador) {
		this.empresasForIdContador = empresasForIdContador;
	}

	public Set<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Set<Empresa> getEmpresasForIdRepLegal() {
		return this.empresasForIdRepLegal;
	}

	public void setEmpresasForIdRepLegal(Set<Empresa> empresasForIdRepLegal) {
		this.empresasForIdRepLegal = empresasForIdRepLegal;
	}

	public Set<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Set<EmpresaVsCliente> getEmpresasForCliente() {
		return empresasForCliente;
	}

	public void setEmpresasForCliente(Set<EmpresaVsCliente> empresasForCliente) {
		this.empresasForCliente = empresasForCliente;
	}

	public Set<EmpresaVsProveedor> getEmpresasForProveedor() {
		return empresasForProveedor;
	}

	public void setEmpresasForProveedor(Set<EmpresaVsProveedor> empresasForProveedor) {
		this.empresasForProveedor = empresasForProveedor;
	}

	public void setDecimoTercerSueldo(Boolean decimoTercerSueldo) {
		this.decimoTercerSueldo = decimoTercerSueldo;
	}

	public Set<EmpresaVsTrabajador> getEmpresasForTrabajador() {
		return empresasForTrabajador;
	}

	public void setEmpresasForTrabajador(
			Set<EmpresaVsTrabajador> empresasForTrabajador) {
		this.empresasForTrabajador = empresasForTrabajador;
	}
}
