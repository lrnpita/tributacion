package ec.eac.sitac.model;

import java.util.Date;

/**
 * Modelo correspondiente a la tabla Documentos_anuldos de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since
 */
public class DocumentosAnulados implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idAnulado;
	
	/**
	 * secuencia del documento
	 *
	 * @since 1.0
	 */
	private int secuencia;
	
	/**
	 * Autorizacion
	 *
	 * @since 2015
	 */
	private String autorizacion;
	
	/**
	 * Fecha de anulacion
	 *
	 * @since 2015
	 */
	private Date fechaAnulacion;
	
	/**
	 * Punto de emision
	 *
	 * @since 2015
	 */
	private PuntoEmision puntoEmision;
	
	/**
	 * Tipo de comprobante
	 *
	 * @since 2015
	 */
	private TipoComprobante tipoComprobante;
	
	/**
	 * Empresa
	 *
	 * @see Empresa
	 * @since 2015
	 */
	private Empresa empresa;

	public DocumentosAnulados() {
		super();
	}

	public DocumentosAnulados(int idAnulado, String autorizacion,
			Date fechaAnulacion, PuntoEmision puntoEmision,
			TipoComprobante tipoComprobante, Empresa empresa, int secuencia) {
		super();
		this.idAnulado = idAnulado;
		this.autorizacion = autorizacion;
		this.fechaAnulacion = fechaAnulacion;
		this.puntoEmision = puntoEmision;
		this.tipoComprobante = tipoComprobante;
		this.empresa = empresa;
		this.secuencia = secuencia;
	}

	public int getIdAnulado() {
		return idAnulado;
	}

	public void setIdAnulado(int idAnulado) {
		this.idAnulado = idAnulado;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public PuntoEmision getPuntoEmision() {
		return puntoEmision;
	}

	public void setPuntoEmision(PuntoEmision puntoEmision) {
		this.puntoEmision = puntoEmision;
	}

	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	} 

	
}
