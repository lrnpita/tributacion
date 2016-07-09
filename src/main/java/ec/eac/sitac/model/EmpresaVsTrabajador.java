package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla intermedia empresa_trabajador de la base de datos. Esta table es el resultado de la relación muchos a muchos
 * que existe entre la tabla empresa y la tabla personal_empresa(trabajador)
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class EmpresaVsTrabajador implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Empresa a la que pertenece a la empresa
	 *
	 * @since 1.0
	 */
	private Empresa empresa;

	/**
	 * Proveedor que pertenece al proveedor
	 *
	 * @since 1.0
	 */
	private PersonalEmpresa trabajador;


	public EmpresaVsTrabajador() {
	}

	public EmpresaVsTrabajador(Empresa empresa, PersonalEmpresa trabajador) {
		this.empresa = empresa;
		this.trabajador = trabajador;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public PersonalEmpresa getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(PersonalEmpresa trabajador) {
		this.trabajador = trabajador;
	}
}
