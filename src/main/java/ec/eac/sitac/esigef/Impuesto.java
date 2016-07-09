package ec.eac.sitac.esigef;
/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class Impuesto {
	/**
	 * Codigo del impuesto
	 *
	 * @since 1.0
	 */
	private String codigo;
	/**
	 * Codigo de retención
	 *
	 * @since 1.0
	 */
	private String codigoRetencion;
	/**
	 * Base imponible
	 *
	 * @since 1.0
	 */
	private String baseImponible;
	/**
	 * Porcentaje a retener en la compra
	 *
	 * @since 1.0
	 */
	private String porcentajeRetener;
	/**
	 * Valor retenido
	 *
	 * @since 1.0
	 */
	private String valorRetenido;
	/**
	 * Código del documento de sustento
	 *
	 * @since 1.0
	 */
	private String codDocSustento;
	/**
	 * Numero del documento de sustento
	 *
	 * @since 1.0
	 */
	private String numDocSustento;
	/**
	 * Fecha de emisión del documento de sustento
	 *
	 * @since 1.0
	 */
	private String fechaEmisionDocSustento;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoRetencion() {
		return codigoRetencion;
	}
	public void setCodigoRetencion(String codigoRetencion) {
		this.codigoRetencion = codigoRetencion;
	}
	public String getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(String baseImponible) {
		this.baseImponible = baseImponible;
	}
	public String getPorcentajeRetener() {
		return porcentajeRetener;
	}
	public void setPorcentajeRetener(String porcentajeRetener) {
		this.porcentajeRetener = porcentajeRetener;
	}
	public String getValorRetenido() {
		return valorRetenido;
	}
	public void setValorRetenido(String valorRetenido) {
		this.valorRetenido = valorRetenido;
	}
	public String getCodDocSustento() {
		return codDocSustento;
	}
	public void setCodDocSustento(String codDocSustento) {
		this.codDocSustento = codDocSustento;
	}
	public String getNumDocSustento() {
		return numDocSustento;
	}
	public void setNumDocSustento(String numDocSustento) {
		this.numDocSustento = numDocSustento;
	}
	public String getFechaEmisionDocSustento() {
		return fechaEmisionDocSustento;
	}
	public void setFechaEmisionDocSustento(String fechaEmisionDocSustento) {
		this.fechaEmisionDocSustento = fechaEmisionDocSustento;
	}
	
    @Override
    public String toString() {
        return "Impuesto [codigo=" + codigo + 
        		", codigoRetencion=" + codigoRetencion + 
        		", baseImponible=" + baseImponible +
        		", porcentajeRetener=" + porcentajeRetener +
        		", valorRetenido=" + valorRetenido +
        		", codDocSustento=" + codDocSustento + 
        	
        		", numDocSustento=" + numDocSustento +
        		", fechaEmisionDocSustento=" + fechaEmisionDocSustento + "]";
    }
}
