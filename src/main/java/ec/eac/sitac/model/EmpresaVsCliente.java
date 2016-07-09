package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla intermedia empresa_cliente de la base de datos. Esta table es el resultado de la relación muchos a muchos
 * que existe entre la tabla empresa y la tabla personal_empresa (cliente)
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class EmpresaVsCliente implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Empresa a la que pertenece el cliente
	 *
	 * @since 1.0
	 */
	private Empresa empresa;
	
	/**
	 * Cliente que pertenece a la empresa
	 *
	 * @since 1.0
	 */
	private PersonalEmpresa cliente;

	
	public EmpresaVsCliente() {
	}
	
	public EmpresaVsCliente(Empresa empresa, PersonalEmpresa cliente) {
		this.empresa = empresa;
		this.cliente = cliente;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public PersonalEmpresa getCliente() {
		return cliente;
	}

	public void setCliente(PersonalEmpresa cliente) {
		this.cliente = cliente;
	}
}
