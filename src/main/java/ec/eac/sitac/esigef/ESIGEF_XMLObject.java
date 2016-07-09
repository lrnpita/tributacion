package ec.eac.sitac.esigef;


/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teij�n gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class ESIGEF_XMLObject {
	/**
	 * Estado
	 *
	 * @since 1.0
	 */
	private String estado;
	/**
	 * N�mero de autorizaci�n
	 *
	 * @since 1.0
	 */
	private String numeroAutorizacion;
	/**
	 * Fecha de autorizaci�n
	 *
	 * @since 1.0
	 */
	private String fechaAutorizacion;
	/**
	 * Ambiente de emision
	 *
	 * @since 1.0
	 */
	private String ambiente;
	/**
	 * Comprobante electr�nico
	 *
	 * @since 1.0
	 */
	private Comprobante comprobante;

	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	public String getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	public void setFechaAutorizacion(String fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public Comprobante getComprobante() {
		return comprobante;
	}
	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	
    @Override
    public String toString() {
        return "Autorizacion [estado=" + estado + 
        		", numeroAutorizacion=" + numeroAutorizacion + 
        		", fechaAutorizacion=" + fechaAutorizacion +
        		", ambiente=" + ambiente + 
        		", comprobante=" + comprobante +  "]";
    }
}
