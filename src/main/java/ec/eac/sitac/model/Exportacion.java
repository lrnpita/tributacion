// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;

/**
 * Modelo correspondiente a la tabla Exportacion de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Exportacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idExportacion;
	
	/**
	 * Cliente
	 *
	 * @see PersonalEmpresa
	 * @since 2015
	 */
	private PersonalEmpresa personalEmpresa;
	
	/**
	 * Punto de emisión
	 *
	 * @see PuntoEmision
	 * @since 2015
	 */
	private PuntoEmision puntoEmision;
	
	/**
	 * Empresa
	 *
	 * @see Empresa
	 * @since 2015
	 */
	private Empresa empresa;
	
	/**
	 * Tipo de comprobante
	 *
	 * @see TipoComprobante
	 * @since 2015
	 */
	private TipoComprobante tipoComprobante;
	
	/**
	 * Tipo de exportación
	 *
	 * @see TipoExportacionImportacion
	 * @since 2015
	 */
	private TipoExportacionImportacion tipoExportacionImportacion;
	
	/**
	 * Estado de la exportacion
	 *
	 **@see Estado
	 * @since 2015
	 */
	private EstadoSRI estado;
	
	/**
	 * Número del documento de transporte
	 *
	 * @since 2015
	 */
	private String noDocumentoTransporte;
	
	/**
	 * Fecha de transacción
	 *
	 * @since 2015
	 */
	private Date fechaTransaccion;
	
	/**
	 * FOB
	 *
	 * @since 2015
	 */
	private Float fob;
	
	/**
	 * Devolución IVA
	 *
	 * @since 2015
	 */
	private boolean devolucionIva;
	
	/**
	 * Valor FOB del comprobante
	 *
	 * @since 2015
	 */
	private Float valorFobComprobante;
	
	/**
	 * Fecha del registro contable
	 *
	 * @since 2015
	 */
	private Date fechaRegistroContable;
	
	/**
	 * Fecha de emisión
	 *
	 * @since 2015
	 */
	private Date fechaEmision;

	/**
	 * Autorización del SRI
	 *
	 * @since 2015
	 */
	
	private String autorizacionSri;
	/**
	 * Número refrendo del comprobante
	 *
	 * @since 2015
	 */
	private int noRefrendoComprobante;
	
	public Exportacion() {
	}

	public Exportacion(int idExportacion, 
			PersonalEmpresa personalEmpresa, PuntoEmision puntoEmision,
			TipoComprobante tipoComprobante,
			TipoExportacionImportacion tipoExportacionImportacion,EstadoSRI estado,
			Empresa empresa,
			boolean devolucionIva) {
		this.idExportacion = idExportacion;
		this.personalEmpresa = personalEmpresa;
		this.puntoEmision = puntoEmision;
		this.tipoComprobante = tipoComprobante;
		this.tipoExportacionImportacion = tipoExportacionImportacion;
		this.estado = estado;
		this.empresa = empresa;
		this.devolucionIva = devolucionIva;
	}

	public Exportacion(int idExportacion,
			PersonalEmpresa personalEmpresa, PuntoEmision puntoEmision,
			TipoComprobante tipoComprobante,
			TipoExportacionImportacion tipoExportacionImportacion,
			Empresa empresa, EstadoSRI estado,
			String noDocumentoTransporte, Date fechaTransaccion, Float fob,
			boolean devolucionIva, Float valorFobComprobante,
			Date fechaRegistroContable, Date fechaEmision, String autorizacionSri, int noRefrendoComprobante) {
		this.idExportacion = idExportacion;
		this.personalEmpresa = personalEmpresa;
		this.puntoEmision = puntoEmision;
		this.tipoComprobante = tipoComprobante;
		this.tipoExportacionImportacion = tipoExportacionImportacion;
		this.empresa = empresa;
		this.estado =  estado;
		this.noDocumentoTransporte = noDocumentoTransporte;
		this.fechaTransaccion = fechaTransaccion;
		this.fob = fob;
		this.devolucionIva = devolucionIva;
		this.valorFobComprobante = valorFobComprobante;
		this.fechaRegistroContable = fechaRegistroContable;
		this.fechaEmision = fechaEmision;
		this.autorizacionSri = autorizacionSri;
		this.noRefrendoComprobante = noRefrendoComprobante;
	}

	public int getIdExportacion() {
		return this.idExportacion;
	}

	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}

	public PersonalEmpresa getPersonalEmpresa() {
		return this.personalEmpresa;
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

	public TipoExportacionImportacion getTipoExportacionImportacion() {
		return this.tipoExportacionImportacion;
	}

	public void setTipoExportacionImportacion(
			TipoExportacionImportacion tipoExportacionImportacion) {
		this.tipoExportacionImportacion = tipoExportacionImportacion;
	}

	public String getNoDocumentoTransporte() {
		return this.noDocumentoTransporte;
	}

	public void setNoDocumentoTransporte(String noDocumentoTransporte) {
		this.noDocumentoTransporte = noDocumentoTransporte;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Float getFob() {
		return this.fob;
	}

	public void setFob(Float fob) {
		this.fob = fob;
	}

	public boolean isDevolucionIva() {
		return this.devolucionIva;
	}

	public void setDevolucionIva(boolean devolucionIva) {
		this.devolucionIva = devolucionIva;
	}

	public Float getValorFobComprobante() {
		return this.valorFobComprobante;
	}

	public void setValorFobComprobante(Float valorFobComprobante) {
		this.valorFobComprobante = valorFobComprobante;
	}

	public Date getFechaRegistroContable() {
		return this.fechaRegistroContable;
	}

	public void setFechaRegistroContable(Date fechaRegistroContable) {
		this.fechaRegistroContable = fechaRegistroContable;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getAutorizacionSri() {
		return autorizacionSri;
	}

	public void setAutorizacionSri(String autorizacionSri) {
		this.autorizacionSri = autorizacionSri;
	}

	public int getNoRefrendoComprobante() {
		return noRefrendoComprobante;
	}

	public void setNoRefrendoComprobante(int noRefrendoComprobante) {
		this.noRefrendoComprobante = noRefrendoComprobante;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public EstadoSRI getEstado() {
		return estado;
	}

	public void setEstado(EstadoSRI estado) {
		this.estado = estado;
	}
	
	

}
