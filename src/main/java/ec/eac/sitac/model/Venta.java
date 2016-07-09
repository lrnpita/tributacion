// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Modelo correspondiente a la tabla venta de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class Venta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private int idVenta;
	
	/**
	 * Empresa
	 *
	 * @see Empresa
	 * @since 1.0
	 */
	private Empresa empresa;

	/**
	 * Retencion que pertenece a la Venta
	 *
	 **@see Retencion
	 * @since 1.0
	 */
	private Retencion retencion;
	
	/**
	 * Cliente
	 *
	 * @see PersonalEmpresa
	 * @since 1.0
	 */
	private PersonalEmpresa personalEmpresa;
	
	/**
	 * Punto de emisión
	 *
	 * @see PuntoEmision
	 * @since 1.0
	 */
	private PuntoEmision puntoEmision;
	
	/**
	 * Tipo de comprobante
	 *
	 * @see TipoComprobante
	 * @since 1.0
	 */
	private TipoComprobante tipoComprobante;
	
	/**
	 * Tipo de venta
	 *
	 * @see TipoVenta
	 * @since 1.0
	 */
	private TipoVenta tipoVenta;
	
	/**
	 * Tipo de venta según pago
	 *
	 * @see TipoVentaSegunPago
	 * @since 1.0
	 */
	private TipoVentaSegunPago tipoVentaSegunPago;
	
	/**
	 * Estado de la venta
	 *
	 **@see Estado
	 * @since 1.0
	 */
	private EstadoSRI estado;
	
	/**
	 * Fecha de emisión
	 *
	 * @since 1.0
	 */
	private Date fechaEmision;
	
	/**
	 * Fecha de registro
	 *
	 * @since 1.0
	 */
	private Date fechaRegistro;
	
	/**
	 * base imponible gravada 12%
	 *
	 * @since 1.0
	 */
	private Float baseImpGravada;
	
	/**
	 * Base imponible 0%
	 *
	 * @since 1.0
	 */
	private Float baseImp;
	
	/**
	 * Base imponible no gravada
	 *
	 * @since 1.0
	 */
	private Float baseImpNoGravada;
	
	/**
	 * Código de Autorización
	 *
	 * @since 1.0
	 */
	private String codigoAutorizacion;
	
	/**
	 * Clave de acceso
	 *
	 * @since 1.0
	 */
	private String claveAcceso;
	
	/**
	 * Descuento
	 *
	 * @since 1.0
	 */
	private Float descuento;
	
	/**
	 * Ice
	 *
	 * @since 1.0
	 */
	private Float ice;
	
	/**
	 * Monto Iva
	 *
	 * @since 1.0
	 */
	private Float montoIva;
	
	/**
	 * Porcentaje Iva
	 *
	 * @since 1.0
	 */
	private Float porcentajeIva;
	
	private String serieFactura;
	
	private int secFactura;
	
	/**
	 * Lista de productos
	 *
	 * @since 1.0
	 */
	private Set<VentaVsProducto> ventaVsProductos = new HashSet<VentaVsProducto>(0);

	public Venta() {
	}

	public Venta(int id, Empresa empresa, PersonalEmpresa personalEmpresa, Retencion retencion, String serieFactura, int secFactura,
			PuntoEmision puntoEmision, TipoComprobante tipoComprobante,
			TipoVenta tipoVenta,EstadoSRI estado, Date fechaEmision, 
			Date fechaRegistro, String codigoAutorizacion, String claveAcceso, Float descuento, Float montoIva, Float ice,
			Float baseImpGravada, Float baseImp, Float baseImpNoGravada, Float porcentajeIva) {
		this.idVenta = id;
		this.empresa = empresa;
		this.personalEmpresa = personalEmpresa;
		this.retencion = retencion;
		this.puntoEmision = puntoEmision;
		this.tipoComprobante = tipoComprobante;
		this.tipoVenta = tipoVenta;
		this.estado = estado;
		this.fechaEmision = fechaEmision;
		this.fechaRegistro = fechaRegistro;
		this.codigoAutorizacion = codigoAutorizacion;
		this.claveAcceso = claveAcceso;
		this.descuento = descuento;
		this.montoIva = montoIva;
		this.ice = ice;
		this.baseImp = baseImp;
		this.baseImpGravada = baseImpGravada;
		this.baseImpNoGravada = baseImpNoGravada;
		this.porcentajeIva = porcentajeIva;
		this.serieFactura = serieFactura;
		this.secFactura = secFactura;
	}

	public Venta(int id, Empresa empresa, PersonalEmpresa personalEmpresa, Retencion retencion, String serieFactura, int secFactura,
			PuntoEmision puntoEmision, TipoComprobante tipoComprobante,
			TipoVenta tipoVenta,TipoVentaSegunPago tipoVentaSegunPago,EstadoSRI estado, Date fechaEmision, int secuencia,
			Date fechaRegistro, Set<VentaVsProducto> ventaVsProductos, String codigoAutorizacion, String claveAcceso, Float descuento, 
			Float montoIva, Float ice,	Float baseImpGravada, Float baseImp, Float baseImpNoGravada, Float porcentajeIva) {
		this.idVenta = id;
		this.empresa = empresa;
		this.personalEmpresa = personalEmpresa;
		this.puntoEmision = puntoEmision;
		this.tipoComprobante = tipoComprobante;
		this.tipoVenta = tipoVenta;
		this.tipoVentaSegunPago = tipoVentaSegunPago;
		this.estado = estado;
		this.fechaEmision = fechaEmision;
		this.fechaRegistro = fechaRegistro;
		this.retencion = retencion;
		this.ventaVsProductos = ventaVsProductos;
		this.codigoAutorizacion = codigoAutorizacion;
		this.claveAcceso = claveAcceso;
		this.descuento = descuento;
		this.montoIva = montoIva;
		this.ice = ice;
		this.baseImp = baseImp;
		this.baseImpGravada = baseImpGravada;
		this.baseImpNoGravada = baseImpNoGravada;
		this.porcentajeIva = porcentajeIva;
		this.serieFactura = serieFactura;
		this.secFactura = secFactura;
	}

	public Retencion getRetencion() {
		return retencion;
	}

	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}

	public Float getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(Float porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int id) {
		this.idVenta = id;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public PersonalEmpresa getPersonalEmpresa() {
		return this.personalEmpresa;
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

	public void setPersonalEmpresa(PersonalEmpresa personalEmpresa) {
		this.personalEmpresa = personalEmpresa;
	}

	public PuntoEmision getPuntoEmision() {
		return this.puntoEmision;
	}

	public void setPuntoEmision(PuntoEmision puntoEmision) {
		this.puntoEmision = puntoEmision;
	}

	public TipoComprobante getTipoComprobante() {
		return this.tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public TipoVenta getTipoVenta() {
		return this.tipoVenta;
	}

	public void setTipoVenta(TipoVenta tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public TipoVentaSegunPago getTipoVentaSegunPago() {
		return this.tipoVentaSegunPago;
	}

	public void setTipoVentaSegunPago(TipoVentaSegunPago tipoVentaSegunPago) {
		this.tipoVentaSegunPago = tipoVentaSegunPago;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Set<VentaVsProducto> getVentaVsProductos() {
		return this.ventaVsProductos;
	}

	public void setVentaVsProductos(Set<VentaVsProducto> ventaVsProductos) {
		this.ventaVsProductos = ventaVsProductos;
	}

	public EstadoSRI getEstado() {
		return estado;
	}

	public void setEstado(EstadoSRI estado) {
		this.estado = estado;
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public Float getIce() {
		return ice;
	}

	public void setIce(Float ice) {
		this.ice = ice;
	}

	public Float getMontoIva() {
		return montoIva;
	}

	public void setMontoIva(Float montoIva) {
		this.montoIva = montoIva;
	}

	public Float getBaseImpGravada() {
		return baseImpGravada;
	}

	public void setBaseImpGravada(Float baseImpGravada) {
		this.baseImpGravada = baseImpGravada;
	}

	public Float getBaseImp() {
		return baseImp;
	}

	public void setBaseImp(Float baseImp) {
		this.baseImp = baseImp;
	}

	public Float getBaseImpNoGravada() {
		return baseImpNoGravada;
	}

	public void setBaseImpNoGravada(Float baseImpNoGravada) {
		this.baseImpNoGravada = baseImpNoGravada;
	}
}
