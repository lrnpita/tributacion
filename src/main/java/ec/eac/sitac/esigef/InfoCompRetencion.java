package ec.eac.sitac.esigef;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class InfoCompRetencion {
	/**
	 * Fecha de emisión
	 *
	 * @since 1.0
	 */
	private String fechaEmision;
	/**
	 * Direccion del establecimiento
	 *
	 * @since 1.0
	 */
	private String dirEstablecimiento;
	/**
	 * Contribuyente especial
	 *
	 * @since 1.0
	 */
	private String contribuyenteEspecial;
	/**
	 * Tipo de identificación del sujeto retenido
	 *
	 * @since 1.0
	 */
	private String tipoIdentificacionSujetoRetenido;
	/**
	 * Razón social del sujeto retenido
	 *
	 * @since 1.0
	 */
	private String razonSocialSujetoRetenido;
	/**
	 * Identificación del sujeto retenido
	 *
	 * @since 1.0
	 */
	private String identificacionSujetoRetenido;
	/**
	 * Período fiscal
	 *
	 * @since 1.0
	 */
	private String periodoFiscal;
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getDirEstablecimiento() {
		return dirEstablecimiento;
	}
	public void setDirEstablecimiento(String dirEstablecimiento) {
		this.dirEstablecimiento = dirEstablecimiento;
	}
	public String getContribuyenteEspecial() {
		return contribuyenteEspecial;
	}
	public void setContribuyenteEspecial(String contribuyenteEspecial) {
		this.contribuyenteEspecial = contribuyenteEspecial;
	}
	public String getTipoIdentificacionSujetoRetenido() {
		return tipoIdentificacionSujetoRetenido;
	}
	public void setTipoIdentificacionSujetoRetenido(
			String tipoIdentificacionSujetoRetenido) {
		this.tipoIdentificacionSujetoRetenido = tipoIdentificacionSujetoRetenido;
	}
	public String getRazonSocialSujetoRetenido() {
		return razonSocialSujetoRetenido;
	}
	public void setRazonSocialSujetoRetenido(String razonSocialSujetoRetenido) {
		this.razonSocialSujetoRetenido = razonSocialSujetoRetenido;
	}
	public String getIdentificacionSujetoRetenido() {
		return identificacionSujetoRetenido;
	}
	public void setIdentificacionSujetoRetenido(String identificacionSujetoRetenido) {
		this.identificacionSujetoRetenido = identificacionSujetoRetenido;
	}
	public String getPeriodoFiscal() {
		return periodoFiscal;
	}
	public void setPeriodoFiscal(String periodoFiscal) {
		this.periodoFiscal = periodoFiscal;
	}
	
    @Override
    public String toString() {
        return "InfoCompRetencion [fechaEmision=" + fechaEmision + 
        		", dirEstablecimiento=" + dirEstablecimiento + 
        		", contribuyenteEspecial=" + contribuyenteEspecial +
        		", tipoIdentificacionSujetoRetenido=" + tipoIdentificacionSujetoRetenido +
        		", razonSocialSujetoRetenido=" + razonSocialSujetoRetenido +
        		", identificacionSujetoRetenido=" + identificacionSujetoRetenido +
        		", periodoFiscal=" + periodoFiscal + "]";
    }
}
