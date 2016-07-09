package ec.eac.sitac.esigef;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class InfoTributaria {
	/**
	 * Tipo de ambiente para la emisión de comprobantes: prueba o producción
	 *
	 * @since 1.0
	 */
	private String ambiente;
	/**
	 * Tipo de emisión: normal o contingencia
	 *
	 * @since 1.0
	 */
	private String tipoEmision;
	/**
	 * Razón social
	 *
	 * @since 1.0
	 */
	private String razonSocial;
	/**
	 * Ruc
	 *
	 * @since 1.0
	 */
	private String ruc;
	/**
	 * Clave de acceso otorgada por el SRI
	 *
	 * @since 1.0
	 */
	private String claveAcceso;
	/**
	 * Código del documento
	 *
	 * @since 1.0
	 */
	private String codDoc;
	/**
	 * Estab
	 *
	 * @since 1.0
	 */
	private String estab;
	/**
	 * Punto de emisión
	 *
	 * @since 1.0
	 */
	private String ptoEmi;
	/**
	 * Número secuencial del documento
	 *
	 * @since 1.0
	 */
	private String secuencial;
	/**
	 * Dirección Matriz
	 *
	 * @since 1.0
	 */
	private String dirMatriz;
	
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public String getTipoEmision() {
		return tipoEmision;
	}
	public void setTipoEmision(String tipoEmision) {
		this.tipoEmision = tipoEmision;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getClaveAcceso() {
		return claveAcceso;
	}
	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}
	public String getCodDoc() {
		return codDoc;
	}
	public void setCodDoc(String codDoc) {
		this.codDoc = codDoc;
	}
	public String getEstab() {
		return estab;
	}
	public void setEstab(String estab) {
		this.estab = estab;
	}
	public String getPtoEmi() {
		return ptoEmi;
	}
	public void setPtoEmi(String ptoEmi) {
		this.ptoEmi = ptoEmi;
	}
	public String getSecuencial() {
		return secuencial;
	}
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	public String getDirMatriz() {
		return dirMatriz;
	}
	public void setDirMatriz(String dirMatriz) {
		this.dirMatriz = dirMatriz;
	}
	
    @Override
    public String toString() {
        return "InfoTributaria [ambiente=" + ambiente + 
        		", tipoEmision=" + tipoEmision + 
        		", razonSocial=" + razonSocial +
        		", ruc=" + ruc +
        		", claveAcceso=" + claveAcceso +
        		", codDoc=" + codDoc + 
        		", estab=" + estab +
        		", ptoEmi=" + ptoEmi + 
        		", secuencial=" + secuencial + 
        		", dirMatriz=" + dirMatriz + "]";
    }
}
