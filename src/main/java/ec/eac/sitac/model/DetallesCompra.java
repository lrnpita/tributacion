// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla DetallesCompra de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class DetallesCompra implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idDetalleCompra;
	
	/**
	 * Compra
	 *
	 *@see Compra
	 * @since 2015
	 */
	private Compra compra;
	
	/**
	 * Tipo de transacción
	 *
	 *@see TipoTransaccion
	 * @since 2015
	 */
	private TipoTransaccion tipoTransaccion;
	
	/**
	 * Base no objeto de IVA
	 *
	 * @since 2015
	 */
	private Float baseNoObjetoIva;
	
	/**
	 * Base imponible 0
	 *
	 * @since 2015
	 */
	private Float baseImpobible0;
	
	/**
	 * Base imponible 12
	 *
	 * @since 2015
	 */
	private Float baseImponible12;
	
	/**
	 * IVA
	 *
	 * @since 2015
	 */
	private Float porcentajeIva;
	
	/**
	 * Retencion Impuesto a la Renta baseNoObjIva
	 *
	 * @since 2015
	 */
	private Float RIRbaseNoObjIva;
	
	/**
	 *  Retencion Impuesto a la Renta baseImp0%
	 *
	 * @since 2015
	 */
	private Float RIRbaseImp0;
	
	/**
	 *  Retencion Impuesto a la Renta baseImp12%
	 *
	 * @since 2015
	 */
	private Float RIRbaseImp12;
	
	/**
	 *  Retencion Impuesto a la Renta %ret
	 *
	 * @since 2015
	 */
	private Float RIRret;
	
	/**
	 * Retencion Iva valorIva
	 *
	 * @since 2015
	 */
	private Float RIvalorIva;
	
	/**
	 * Retencion Iva %ret
	 *
	 * @since 2015
	 */
	private Float RIret;

	public DetallesCompra() {
	}

	public DetallesCompra(int idDetalleCompra, Compra compra, TipoTransaccion tipoTransaccion) {
		this.idDetalleCompra = idDetalleCompra;
		this.compra = compra;
		this.tipoTransaccion = tipoTransaccion;
	}

	public DetallesCompra(int idDetalleCompra, Compra compra,
			TipoTransaccion tipoTransaccion, Float baseNoObjetoIva,
			Float baseImpobible0, Float baseImponible12, Float iva, Float RIRBaseNoObjIva, Float RIRbaseImp0, Float RIRbaseImp12, Float RIRret,
			Float RIvalorIva, Float RIret) {
		this.idDetalleCompra = idDetalleCompra;
		this.compra = compra;
		this.tipoTransaccion = tipoTransaccion;
		this.baseNoObjetoIva = baseNoObjetoIva;
		this.baseImpobible0 = baseImpobible0;
		this.baseImponible12 = baseImponible12;
		this.porcentajeIva = iva;
		this.RIRbaseNoObjIva = RIRBaseNoObjIva;
		this.RIRbaseImp0 = RIRbaseImp0;
		this.RIRbaseImp12 = RIRbaseImp12;
		this.RIRret = RIRret;
		this.RIvalorIva = RIvalorIva;
		this.RIret = RIret;
	}

	public DetallesCompra(Float baseNoObjetoIva, Float baseImpobible0,
			Float baseImponible12, Float iva, Float rIRbaseNoObjIva,
			Float rIRbaseImp0, Float rIRbaseImp12, Float rIRret,
			Float rIvalorIva, Float rIret) {
		super();
		this.baseNoObjetoIva = baseNoObjetoIva;
		this.baseImpobible0 = baseImpobible0;
		this.baseImponible12 = baseImponible12;
		this.porcentajeIva = iva;
		RIRbaseNoObjIva = rIRbaseNoObjIva;
		RIRbaseImp0 = rIRbaseImp0;
		RIRbaseImp12 = rIRbaseImp12;
		RIRret = rIRret;
		RIvalorIva = rIvalorIva;
		RIret = rIret;
	}

	public int getIdDetalleCompra() {
		return this.idDetalleCompra;
	}

	public void setIdDetalleCompra(int idDetalleCompra) {
		this.idDetalleCompra = idDetalleCompra;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public TipoTransaccion getTipoTransaccion() {
		return this.tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public Float getBaseNoObjetoIva() {
		return this.baseNoObjetoIva;
	}

	public void setBaseNoObjetoIva(Float baseNoObjetoIva) {
		this.baseNoObjetoIva = baseNoObjetoIva;
	}

	public Float getBaseImpobible0() {
		return this.baseImpobible0;
	}

	public void setBaseImpobible0(Float baseImpobible0) {
		this.baseImpobible0 = baseImpobible0;
	}

	public Float getBaseImponible12() {
		return this.baseImponible12;
	}

	public void setBaseImponible12(Float baseImponible12) {
		this.baseImponible12 = baseImponible12;
	}

	public Float getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(Float porcentajeRetIva) {
		this.porcentajeIva = porcentajeRetIva;
	}

	public Float getRIRbaseNoObjIva() {
		return RIRbaseNoObjIva;
	}

	public void setRIRbaseNoObjIva(Float rIRbaseNoObjIva) {
		RIRbaseNoObjIva = rIRbaseNoObjIva;
	}

	public Float getRIRbaseImp0() {
		return RIRbaseImp0;
	}

	public void setRIRbaseImp0(Float rIRbaseImp0) {
		RIRbaseImp0 = rIRbaseImp0;
	}

	public Float getRIRbaseImp12() {
		return RIRbaseImp12;
	}

	public void setRIRbaseImp12(Float rIRbaseImp12) {
		RIRbaseImp12 = rIRbaseImp12;
	}

	public Float getRIRret() {
		return RIRret;
	}

	public void setRIRret(Float rIRret) {
		RIRret = rIRret;
	}

	public Float getRIvalorIva() {
		return RIvalorIva;
	}

	public void setRIvalorIva(Float rIvalorIva) {
		RIvalorIva = rIvalorIva;
	}

	public Float getRIret() {
		return RIret;
	}

	public void setRIret(Float rIret) {
		RIret = rIret;
	}

}
