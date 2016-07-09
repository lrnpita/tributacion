// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;

/**
 * Modelo correspondiente a la tabla Importacion de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Importacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idImportacion;
	
	/**
	 * Empresa
	 *
	 * @see Empresa
	 * @since 2015
	 */
	private Empresa empresa;
	
	/**
	 * Identificación crédio gasto
	 *
	 * @see IdentificacionCreditoGasto
	 * @since 2015
	 */
	private IdentificacionCreditoGasto identificacionCreditoGasto;
	
	/**
	 * Cliente
	 *
	 * @see PersonalEmpresa
	 * @since 2015
	 */
	private PersonalEmpresa personalEmpresa;
	
	/**
	 * Tipo de comprobante
	 *
	 * @see TipoComprobante
	 * @since 2015
	 */
	private TipoComprobante tipoComprobante;
	
	/**
	 * Tipo de importación
	 *
	 * @see TipoExportacionImportacion
	 * @since 2015
	 */
	private TipoExportacionImportacion tipoExportacionImportacion;
	
	/**
	 * Tipo de transacción
	 *
	 * @see TipoTransaccion
	 * @since 2015
	 */
	private TipoTransaccion tipoTransaccion;
	
	
	/**
	 * Fecha de liquidación
	 *
	 * @since 2015
	 */
	private Date fechaLiquidacion;
	
	/**
	 * CIF
	 *
	 * @since 2015
	 */
	private Float cif;
	
	/**
	 * Base imponible
	 *
	 * @since 2015
	 */
	private Float baseImponible;
	
	/**
	 * Base imponible gravada
	 *
	 * @since 2015
	 */
	private Float baseImponibleGravada;
	
	/**
	 * Porcentaje IVA
	 *
	 * @since 2015
	 */
	private Float porcentajeIva;
	
	/**
	 * Base imponible ICE
	 *
	 * @since 2015
	 */
	private Float baseImponibleIce;
	
	/**
	 * Porcentaje ICE
	 *
	 * @since 2015
	 */
	private Float porcentajeIce;
	
	/**
	 * Base imponible RET
	 *
	 * @since 2015
	 */
	private Float baseImponibleRet;
	
	/**
	 * Porcentaje de retención
	 *
	 * @since 2015
	 */
	private Float porcentajeRetencion;
	
	/**
	 * Número refrendo de comprobante
	 *
	 * @since 2015
	 */
	private int noRefrendoComprobante;

	public Importacion() {
	}

	public Importacion(int idImportacion, Empresa empresa,
			IdentificacionCreditoGasto identificacionCreditoGasto,
			PersonalEmpresa personalEmpresa, TipoComprobante tipoComprobante,
			TipoExportacionImportacion tipoExportacionImportacion,
			TipoTransaccion tipoTransaccion) {
		this.idImportacion = idImportacion;
		this.empresa = empresa;
		this.identificacionCreditoGasto = identificacionCreditoGasto;
		this.personalEmpresa = personalEmpresa;
		this.tipoComprobante = tipoComprobante;
		this.tipoExportacionImportacion = tipoExportacionImportacion;
		this.tipoTransaccion = tipoTransaccion;
	}

	public Importacion(int idImportacion, Empresa empresa,
			IdentificacionCreditoGasto identificacionCreditoGasto,
			PersonalEmpresa personalEmpresa, TipoComprobante tipoComprobante,
			TipoExportacionImportacion tipoExportacionImportacion,
			TipoTransaccion tipoTransaccion, Date fechaLiquidacion,
			Float cif, Float baseImponible, Float baseImponibleGravada,
			Float porcentajeIva, Float baseImponibleIce, Float porcentajeIce,
			Float baseImponibleRet, Float porcentajeRetencion, int noRefrendoComprobante) {
		this.idImportacion = idImportacion;
		this.empresa = empresa;
		this.identificacionCreditoGasto = identificacionCreditoGasto;
		this.personalEmpresa = personalEmpresa;
		this.tipoComprobante = tipoComprobante;
		this.tipoExportacionImportacion = tipoExportacionImportacion;
		this.tipoTransaccion = tipoTransaccion;
		this.fechaLiquidacion = fechaLiquidacion;
		this.cif = cif;
		this.baseImponible = baseImponible;
		this.baseImponibleGravada = baseImponibleGravada;
		this.porcentajeIva = porcentajeIva;
		this.baseImponibleIce = baseImponibleIce;
		this.porcentajeIce = porcentajeIce;
		this.baseImponibleRet = baseImponibleRet;
		this.porcentajeRetencion = porcentajeRetencion;
		this.noRefrendoComprobante = noRefrendoComprobante;
	}

	public int getIdImportacion() {
		return this.idImportacion;
	}

	public void setIdImportacion(int idImportacion) {
		this.idImportacion = idImportacion;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public IdentificacionCreditoGasto getIdentificacionCreditoGasto() {
		return this.identificacionCreditoGasto;
	}

	public void setIdentificacionCreditoGasto(
			IdentificacionCreditoGasto identificacionCreditoGasto) {
		this.identificacionCreditoGasto = identificacionCreditoGasto;
	}

	public PersonalEmpresa getPersonalEmpresa() {
		return this.personalEmpresa;
	}

	public void setPersonalEmpresa(PersonalEmpresa personalEmpresa) {
		this.personalEmpresa = personalEmpresa;
	}

	public TipoComprobante getTipoComprobante() {
		return this.tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public TipoExportacionImportacion getTipoExportacionImportacion() {
		return this.tipoExportacionImportacion;
	}

	public void setTipoExportacionImportacion(
			TipoExportacionImportacion tipoExportacionImportacion) {
		this.tipoExportacionImportacion = tipoExportacionImportacion;
	}

	public TipoTransaccion getTipoTransaccion() {
		return this.tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public Float getCif() {
		return this.cif;
	}

	public void setCif(Float cif) {
		this.cif = cif;
	}

	public Float getBaseImponible() {
		return this.baseImponible;
	}

	public void setBaseImponible(Float baseImponible) {
		this.baseImponible = baseImponible;
	}

	public Float getBaseImponibleGravada() {
		return this.baseImponibleGravada;
	}

	public void setBaseImponibleGravada(Float baseImponibleGravada) {
		this.baseImponibleGravada = baseImponibleGravada;
	}

	public Float getPorcentajeIva() {
		return this.porcentajeIva;
	}

	public void setPorcentajeIva(Float porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public Float getBaseImponibleIce() {
		return this.baseImponibleIce;
	}

	public void setBaseImponibleIce(Float baseImponibleIce) {
		this.baseImponibleIce = baseImponibleIce;
	}

	public Float getPorcentajeIce() {
		return this.porcentajeIce;
	}

	public void setPorcentajeIce(Float porcentajeIce) {
		this.porcentajeIce = porcentajeIce;
	}

	public Float getBaseImponibleRet() {
		return this.baseImponibleRet;
	}

	public void setBaseImponibleRet(Float baseImponibleRet) {
		this.baseImponibleRet = baseImponibleRet;
	}

	public Float getPorcentajeRetencion() {
		return this.porcentajeRetencion;
	}

	public void setPorcentajeRetencion(Float porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}

	public int getNoRefrendoComprobante() {
		return noRefrendoComprobante;
	}

	public void setNoRefrendoComprobante(int noRefrendoComprobante) {
		this.noRefrendoComprobante = noRefrendoComprobante;
	}

}
