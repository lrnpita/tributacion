package ec.eac.sitac.model;

import java.util.Date;

/**
 * Modelo correspondiente a la tabla retencion de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Retencion implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private int idRetencion;
	
	private float retencionIR;
	
	private float retencionIVA;
	
	private float totalRetencion;
	
	private Date fechaRetencion;
	
	private String autorizacionRetencion;
	
	private String serieRetencion;
	
	private int secuenciaRetencion;
	
	/**
	 * Clave de acceso
	 *
	 * @since 1.0
	 */
	private String claveAcceso;

	public Retencion(float retencionIR, float retencionIVA,
			float totalRetencion, Date fechaRetencion,
			String autorizacionRetencion, String serieRetencion,
			int secuenciaRetencion) {
		super();
		this.retencionIR = retencionIR;
		this.retencionIVA = retencionIVA;
		this.totalRetencion = totalRetencion;
		this.fechaRetencion = fechaRetencion;
		this.autorizacionRetencion = autorizacionRetencion;
		this.serieRetencion = serieRetencion;
		this.secuenciaRetencion = secuenciaRetencion;
	}
	
	

	public Retencion(int idRetencion, float retencionIR, float retencionIVA,
			float totalRetencion, Date fechaRetencion,
			String autorizacionRetencion, String serieRetencion,
			int secuenciaRetencion, String claveAcceso) {
		super();
		this.idRetencion = idRetencion;
		this.retencionIR = retencionIR;
		this.retencionIVA = retencionIVA;
		this.totalRetencion = totalRetencion;
		this.fechaRetencion = fechaRetencion;
		this.autorizacionRetencion = autorizacionRetencion;
		this.serieRetencion = serieRetencion;
		this.secuenciaRetencion = secuenciaRetencion;
		this.claveAcceso = claveAcceso;
	}

	public Retencion() {
		super();
	}

	public int getIdRetencion() {
		return idRetencion;
	}

	public void setIdRetencion(int idRetencion) {
		this.idRetencion = idRetencion;
	}

	public float getRetencionIR() {
		return retencionIR;
	}

	public void setRetencionIR(float retencionIR) {
		this.retencionIR = retencionIR;
	}

	public float getRetencionIVA() {
		return retencionIVA;
	}

	public void setRetencionIVA(float retencionIVA) {
		this.retencionIVA = retencionIVA;
	}

	public float getTotalRetencion() {
		return totalRetencion;
	}

	public void setTotalRetencion(float totalRetencion) {
		this.totalRetencion = totalRetencion;
	}

	public Date getFechaRetencion() {
		return fechaRetencion;
	}

	public void setFechaRetencion(Date fechaRetencion) {
		this.fechaRetencion = fechaRetencion;
	}

	public String getAutorizacionRetencion() {
		return autorizacionRetencion;
	}

	public void setAutorizacionRetencion(String autorizacionRetencion) {
		this.autorizacionRetencion = autorizacionRetencion;
	}

	public String getSerieRetencion() {
		return serieRetencion;
	}

	public void setSerieRetencion(String serieRetencion) {
		this.serieRetencion = serieRetencion;
	}

	public int getSecuenciaRetencion() {
		return secuenciaRetencion;
	}

	public void setSecuenciaRetencion(int secuenciaRetencion) {
		this.secuenciaRetencion = secuenciaRetencion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}
	
	
}
