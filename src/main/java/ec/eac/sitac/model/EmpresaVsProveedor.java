package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla intermedia empresa_proveedor de la base de datos. Esta table es el resultado de la relación muchos a muchos
 * que existe entre la tabla empresa y la tabla personal_empresa(proveedor)
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class EmpresaVsProveedor implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Empresa a la que pertenece el proveedor
	 *
	 * @since 1.0
	 */
	private Empresa empresa;

	/**
	 * Proveedor que pertenece a la empresa
	 *
	 * @since 1.0
	 */
	private PersonalEmpresa proveedor;


	public EmpresaVsProveedor() {
	}

	public EmpresaVsProveedor(Empresa empresa, PersonalEmpresa proveedor) {
		this.empresa = empresa;
		this.proveedor = proveedor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public PersonalEmpresa getProveedor() {
		return proveedor;
	}

	public void setProveedor(PersonalEmpresa proveedor) {
		this.proveedor = proveedor;
	}
}
