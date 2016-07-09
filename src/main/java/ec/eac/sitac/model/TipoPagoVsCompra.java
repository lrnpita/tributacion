// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla tipo_pago_vs_compra de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class TipoPagoVsCompra implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Compra
	 *
	 * @see Compra
	 * @since 2015
	 */
	private Compra compra;
	
	/**
	 * Tipo de pago
	 *
	 * @see TipoPago
	 * @since 2015
	 */
	private TipoPago tipoPago;

	public TipoPagoVsCompra() {
	}

	public TipoPagoVsCompra(Compra compra, TipoPago tipoPago) {
		this.compra = compra;
		this.tipoPago = tipoPago;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public TipoPago getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

}
