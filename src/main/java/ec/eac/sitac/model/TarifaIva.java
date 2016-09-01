// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla tarifa_retencion_iva de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TarifaIva implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private short codigo;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private int tarifa;
	
	/**
	 * Lista de productos
	 *
	 * @since 2015
	 */
	private Set<Producto> productos = new HashSet<Producto>(0);

	public TarifaIva() {
	}

	public TarifaIva(short codigo, int porcentaje) {
		this.codigo = codigo;
		this.tarifa = porcentaje;
	}

	public TarifaIva(short codigo, int porcentaje,
			Set<Producto> productos) {
		this.codigo = codigo;
		this.tarifa = porcentaje;
		this.productos = productos;
	}

	public short getCodigo() {
		return this.codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public int getTarifa() {
		return tarifa;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}

	public Set<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

}
