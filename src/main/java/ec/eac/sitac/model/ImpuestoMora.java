package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla impuestomora de la base de datos. Esta table contiene los impuestos para aplicar al impuesto por mora
 * @author Lorena Pita Pérez lrnpita@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */

public class ImpuestoMora implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 1.0
	 */
	private int id;

	/**
	 * Mes Inicio
	 *
	 * @since 1.0
	 */
	private int mesInicio;
	
	/**
	 * Mes Fin
	 *
	 * @since 1.0
	 */
	private int mesFin;
	
	/**
	 * Anno
	 *
	 * @since 1.0
	 */
	private int anno;
	
	/**
	 * Impuesto a aplicar
	 *
	 * @since 1.0
	 */
	private float impuesto;

	public ImpuestoMora() {
		super();
	}

	public ImpuestoMora(int id, int mesInicio, int mesFin, int anno,
			float impuesto) {
		super();
		this.id = id;
		this.mesInicio = mesInicio;
		this.mesFin = mesFin;
		this.anno = anno;
		this.impuesto = impuesto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	public int getMesFin() {
		return mesFin;
	}

	public void setMesFin(int mesFin) {
		this.mesFin = mesFin;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}
	
	
	
}
