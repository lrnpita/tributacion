// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;

/**
 * Modelo correspondiente a la tabla ComprobanteReembolso de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class ComprobanteReembolso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int id;
	
	/**
	 * Compra
	 *
	 *@see Compra
	 * @since 2015
	 */
	private Compra compra;
	
	/**
	 * Serie
	 *
	 * @since 2015
	 */
	private String serie;
	
	/**
	 * Secuencia
	 *
	 * @since 2015
	 */
	private String secuencia;
	
	/**
	 * Fecha
	 *
	 * @since 2015
	 */
	private Date fecha;
	
	/**
	 * Autorización
	 *
	 * @since 2015
	 */
	private String autorizacion;
	
	/**
	 * Base no objeto de IVA
	 *
	 * @since 2015
	 */
	private Float baseNoObjetoIva;
	
	/**
	 * Base 0
	 *
	 * @since 2015
	 */
	private Float base0;
	
	/**
	 * Base IVA
	 *
	 * @since 2015
	 */
	private Float baseIva;
	
	/**
	 * IVA
	 *
	 * @since 2015
	 */
	private Float iva;
	
	/**
	 * IEC
	 *
	 * @since 2015
	 */
	private Float ice;

	public ComprobanteReembolso() {
	}

	public ComprobanteReembolso(int id, Compra compra, String serie) {
		this.id = id;
		this.compra = compra;
		this.serie = serie;
	}

	public ComprobanteReembolso(int id, Compra compra, String serie,
			String secuencia, Date fecha, String autorizacion,
			Float baseNoObjetoIva, Float base0, Float baseIva, Float iva,
			Float ice) {
		this.id = id;
		this.compra = compra;
		this.serie = serie;
		this.secuencia = secuencia;
		this.fecha = fecha;
		this.autorizacion = autorizacion;
		this.baseNoObjetoIva = baseNoObjetoIva;
		this.base0 = base0;
		this.baseIva = baseIva;
		this.iva = iva;
		this.ice = ice;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getSecuencia() {
		return this.secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAutorizacion() {
		return this.autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Float getBaseNoObjetoIva() {
		return this.baseNoObjetoIva;
	}

	public void setBaseNoObjetoIva(Float baseNoObjetoIva) {
		this.baseNoObjetoIva = baseNoObjetoIva;
	}

	public Float getBase0() {
		return this.base0;
	}

	public void setBase0(Float base0) {
		this.base0 = base0;
	}

	public Float getBaseIva() {
		return this.baseIva;
	}

	public void setBaseIva(Float baseIva) {
		this.baseIva = baseIva;
	}

	public Float getIva() {
		return this.iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public Float getIce() {
		return this.ice;
	}

	public void setIce(Float ice) {
		this.ice = ice;
	}

}
