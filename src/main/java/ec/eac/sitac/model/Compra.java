// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo correspondiente a la tabla compra de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Compra implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private int idCompra;
	
	/**
	 * Empresa que realiza la compra
	 *
	 **@see Empresa
	 * @since 2015
	 */
	private Empresa empresa;
	
	/**
	 * País donde se realiza la compra
	 *
	 **@see Pais
	 * @since 2015
	 */
	private Pais pais;
	
	/**
	 * Proveedor de la compra
	 *
	 **@see PersonalEmpresa
	 * @since 1.0
	 */
	private PersonalEmpresa personalEmpresa;
	
	/**
	 * Punto de Emisión
	 *
	 **@see PuntoEmision
	 * @since 1.0
	 */
	private PuntoEmision puntoEmision;
	
	/**
	 * Tipo de compra
	 *
	 **@see TipoCompra
	 * @since 1.0
	 */
	private TipoCompra tipoCompra;
	
	/**
	 * Tipo de pago según el lugar
	 *
	 **@see TipoPagoSegunLugar
	 * @since 1.0
	 */
	private TipoPagoSegunLugar tipoPagoSegunLugar;
	
	/**
	 * Tipo de comprobante
	 *
	 **@see TipoComprobante
	 * @since 1.0
	 */
	private TipoComprobante tipoComprobante;
	
	/**
	 * Tipo de comprobante modificado en notas de credito y debito
	 *
	 **@see TipoComprobante
	 * @since 1.0
	 */
	private TipoComprobante tipoComprobanteModificado;
	
	/**
	 * Estado de la compra
	 *
	 **@see Estado
	 * @since 1.0
	 */
	private EstadoSRI estado;
	
	/**
	 * Retencion que realiza la Retencion
	 *
	 **@see Retencion
	 * @since 1.0
	 */
	private Retencion retencion;
	
	/**
	 * Fecha de emisión
	 *
	 * @since 1.0
	 */
	private Date fechaEmision;
	
	/**
	 * Fecha de emisión del comprobante modificado
	 *
	 * @since 1.0
	 */
	private Date fechaEmisionModificado;
	
	/**
	 * Registro contable
	 *
	 * @since 1.0
	 */
	private Date registroContable;
	
	/**
	 * Concepto
	 *
	 * @since 1.0
	 */
	private String concepto;
	
	/**
	 * comprobantePago
	 *
	 * @since 1.0
	 */
	private String comprobantePago;
	
	/**
	 * Devolucion IVA
	 *
	 * @since 1.0
	 */
	private boolean devolucionIva;
	
	/**
	 * ICE
	 *
	 * @since 1.0
	 */
	private Float iceImpuesto;
	
	/**
	 * Otros impuestos
	 *
	 * @since 1.0
	 */
	private Float otrosImpuestos;
	
	/**
	 * Convenio
	 *
	 * @since 2015
	 */
	private Boolean convenio;
	
	/**
	 * Norma Legal
	 *
	 * @since 2015
	 */
	private Boolean normaLegal;
	
	/**
	 * Código de autorización de la factura
	 *
	 * @since 2015
	 */
	private String codigoAutorizacion;
	
	/**
	 * Código de autorización del comprobante modifiado
	 *
	 * @since 2015
	 */
	private String codigoAutorizacionModificado;
	
	/**
	 * Serie del comprobante modificado
	 *
	 * @since 2015
	 */
	private int serieModificado;
	
	/**
	 * secuencia del comprobante modificado
	 *
	 * @since 2015
	 */
	private int secuenciaModificado;
	
	/**
	 * Nombre del archivo XML del que se cargó la compra
	 *
	 * @since 1.0
	 */
	private String archivoXMLAsociado;
	
	/**
	 * Lista de comprobantes de reembolso
	 *
	 * @since 2015
	 */
	
	private String serieFactura;
	
	private int secFactura;
	
	private Set<ComprobanteReembolso> comprobanteReembolsos = new HashSet<ComprobanteReembolso>(0);
	
	/**
	 * Lista de detalles de la compra
	 *
	 * @since 2015
	 */
	private Set<DetallesCompra> detallesCompras = new HashSet<DetallesCompra>(0);
	
	/**
	 * Lista de tipos de pagos
	 *
	 * @since 2015
	 */
	private Set<TipoPagoVsCompra> tiposPago = new HashSet<TipoPagoVsCompra>(0);

	public Compra() {
	}

	public Compra(int idCompra, Empresa empresa, PersonalEmpresa personalEmpresa,  String serieFactura, int secFacutura,
			PuntoEmision puntoEmision, Retencion retencion,
			TipoPagoSegunLugar tipoPagoSegunLugar, TipoComprobante tipoComprobante, EstadoSRI estado, Date fechaEmision,
			Date registroContable, boolean devolucionIva) {
		this.idCompra = idCompra;
		this.empresa = empresa;
		this.personalEmpresa = personalEmpresa;
		this.puntoEmision = puntoEmision;
		this.retencion = retencion;
		this.tipoPagoSegunLugar = tipoPagoSegunLugar;
		this.tipoComprobante = tipoComprobante;
		this.estado = estado;
		this.fechaEmision = fechaEmision;
		this.registroContable = registroContable;
		this.devolucionIva = devolucionIva;
		this.serieFactura = serieFactura;
		this.secFactura = secFacutura;
	}

	public Compra(int idCompra, Empresa empresa, Pais pais, Retencion retencion,
			PersonalEmpresa personalEmpresa,
			PuntoEmision puntoEmision, TipoCompra tipoCompra,  String serieFactura, int secFacutura,
			TipoPagoSegunLugar tipoPagoSegunLugar, TipoComprobante tipoComprobante, TipoComprobante tipoComprobanteModificado, EstadoSRI estado, Date fechaEmision,
			Date fechaEmisionModificado, Date registroContable, String concepto, boolean devolucionIva,
			Float iceImpuesto, Float otrosImpuestos, Boolean convenio, Boolean normaLegal, String codigoAutorizacion, String codigoAutorizacionModificado,
			int serieModificado, int secuenciaModificado, Set<ComprobanteReembolso> comprobanteReembolsos, String comprobantePago,
			Set<DetallesCompra> detallesCompras, Set<TipoPagoVsCompra> tiposPago, String archivoXMLAsociado) {
		this.idCompra = idCompra;
		this.empresa = empresa;
		this.pais = pais;
		this.retencion = retencion;
		this.personalEmpresa = personalEmpresa;
		this.puntoEmision = puntoEmision;
		this.tipoCompra = tipoCompra;
		this.tipoPagoSegunLugar = tipoPagoSegunLugar;
		this.tipoComprobante = tipoComprobante;
		this.tipoComprobanteModificado = tipoComprobanteModificado;
		this.estado = estado;
		this.fechaEmision = fechaEmision;
		this.fechaEmisionModificado = fechaEmisionModificado;
		this.registroContable = registroContable;
		this.concepto = concepto;
		this.devolucionIva = devolucionIva;
		this.iceImpuesto = iceImpuesto;
		this.otrosImpuestos = otrosImpuestos;
		this.convenio = convenio;
		this.normaLegal = normaLegal;
		this.codigoAutorizacion = codigoAutorizacion;
		this.codigoAutorizacionModificado = codigoAutorizacionModificado;
		this.serieModificado = serieModificado;
		this.secuenciaModificado = secuenciaModificado;
		this.comprobanteReembolsos = comprobanteReembolsos;
		this.detallesCompras = detallesCompras;
		this.tiposPago = tiposPago;
		this.archivoXMLAsociado = archivoXMLAsociado;
		this.comprobantePago = comprobantePago;
		this.serieFactura = serieFactura;
		this.secFactura = secFacutura;
	}

	public Retencion getRetencion() {
		return retencion;
	}

	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}

	@JsonIgnore
	public EstadoSRI getEstado() {
		return estado;
	}

	@JsonIgnore
	public void setEstado(EstadoSRI estado) {
		this.estado = estado;
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public String getSerieFactura() {
		return serieFactura;
	}

	public void setSerieFactura(String serieFactura) {
		this.serieFactura = serieFactura;
	}

	public int getSecFactura() {
		return secFactura;
	}

	public void setSecFactura(int secFactura) {
		this.secFactura = secFactura;
	}

	@JsonIgnore
	public Empresa getEmpresa() {
		return this.empresa;
	}

	@JsonIgnore
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@JsonIgnore
	public Pais getPais() {
		return this.pais;
	}

	@JsonIgnore
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@JsonIgnore
	public PersonalEmpresa getPersonalEmpresa() {
		return this.personalEmpresa;
	}

	@JsonIgnore
	public void setPersonalEmpresa(PersonalEmpresa personalEmpresa) {
		this.personalEmpresa = personalEmpresa;
	}

	@JsonIgnore
	public PuntoEmision getPuntoEmision() {
		return this.puntoEmision;
	}

	@JsonIgnore
	public void setPuntoEmision(PuntoEmision puntoEmision) {
		this.puntoEmision = puntoEmision;
	}

	@JsonIgnore
	public TipoCompra getTipoCompra() {
		return this.tipoCompra;
	}

	@JsonIgnore
	public void setTipoCompra(TipoCompra tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	@JsonIgnore
	public TipoPagoSegunLugar getTipoPagoSegunLugar() {
		return this.tipoPagoSegunLugar;
	}

	@JsonIgnore
	public void setTipoPagoSegunLugar(TipoPagoSegunLugar tipoPagoSegunLugar) {
		this.tipoPagoSegunLugar = tipoPagoSegunLugar;
	}
	
	@JsonIgnore
	public TipoComprobante getTipoComprobante() {
		return this.tipoComprobante;
	}

	@JsonIgnore
	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	
	@JsonIgnore
	public TipoComprobante getTipoComprobanteModificado() {
		return tipoComprobanteModificado;
	}

	@JsonIgnore
	public void setTipoComprobanteModificado(
			TipoComprobante tipoComprobanteModificado) {
		this.tipoComprobanteModificado = tipoComprobanteModificado;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getRegistroContable() {
		return this.registroContable;
	}

	public void setRegistroContable(Date registroContable) {
		this.registroContable = registroContable;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public boolean isDevolucionIva() {
		return this.devolucionIva;
	}

	public void setDevolucionIva(boolean devolucionIva) {
		this.devolucionIva = devolucionIva;
	}

	public Float getIceImpuesto() {
		return this.iceImpuesto;
	}

	public void setIceImpuesto(Float iceImpuesto) {
		this.iceImpuesto = iceImpuesto;
	}

	public Float getOtrosImpuestos() {
		return this.otrosImpuestos;
	}

	public void setOtrosImpuestos(Float otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	public Boolean getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Boolean convenio) {
		this.convenio = convenio;
	}

	public Boolean getNormaLegal() {
		return this.normaLegal;
	}

	public void setNormaLegal(Boolean normaLegal) {
		this.normaLegal = normaLegal;
	}

	public String getCodigoAutorizacion() {
		return this.codigoAutorizacion;
	}
	
	public Date getFechaEmisionModificado() {
		return fechaEmisionModificado;
	}

	public void setFechaEmisionModificado(Date fechaEmisionModificado) {
		this.fechaEmisionModificado = fechaEmisionModificado;
	}

	public String getCodigoAutorizacionModificado() {
		return codigoAutorizacionModificado;
	}

	public void setCodigoAutorizacionModificado(String codigoAutorizacionModificado) {
		this.codigoAutorizacionModificado = codigoAutorizacionModificado;
	}

	public int getSerieModificado() {
		return serieModificado;
	}

	public void setSerieModificado(int serieModificado) {
		this.serieModificado = serieModificado;
	}

	public int getSecuenciaModificado() {
		return secuenciaModificado;
	}

	public void setSecuenciaModificado(int secuenciaModificado) {
		this.secuenciaModificado = secuenciaModificado;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public String getComprobantePago() {
		return comprobantePago;
	}

	public void setComprobantePago(String comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	@JsonIgnore
	public Set<ComprobanteReembolso> getComprobanteReembolsos() {
		return this.comprobanteReembolsos;
	}

	@JsonIgnore
	public void setComprobanteReembolsos(Set<ComprobanteReembolso> comprobanteReembolsos) {
		this.comprobanteReembolsos = comprobanteReembolsos;
	}

	public Set<DetallesCompra> getDetallesCompras() {
		return this.detallesCompras;
	}

	@JsonIgnore
	public void setDetallesCompras(Set<DetallesCompra> detallesCompras) {
		this.detallesCompras = detallesCompras;
	}

	@JsonIgnore
	public Set<TipoPagoVsCompra> getTiposPago() {
		return this.tiposPago;
	}

	@JsonIgnore
	public void setTiposPago(Set<TipoPagoVsCompra> tipoPago) {
		this.tiposPago = tipoPago;
	}

	public String getArchivoXMLAsociado() {
		return archivoXMLAsociado;
	}

	public void setArchivoXMLAsociado(String archivoXMLAsociado) {
		this.archivoXMLAsociado = archivoXMLAsociado;
	}
}
