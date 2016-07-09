package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla intermedia empresa_producto de la base de datos. Esta table es el resultado de la relación muchos a muchos
 * que existe entre la tabla empresa y la tabla producto
 *
 * @author Lorena Pita Pérez lrnpita@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class EmpresaVsProducto implements java.io.Serializable {
	
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
	private Producto producto;
	
	public EmpresaVsProducto() {
	}

	public EmpresaVsProducto( Empresa empresa, Producto producto) {
		this.empresa = empresa;
		this.producto = producto;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

}
