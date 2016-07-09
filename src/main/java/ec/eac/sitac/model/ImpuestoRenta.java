package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla impuesto_renta de la base de datos. Esta table contiene las tarifas para aplicar a la base imponible
 * @author Lorena Pita Pérez lrnpita@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */

public class ImpuestoRenta implements java.io.Serializable{
	
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
	 * Fracción básica
	 *
	 * @since 2015
	 */

	private Float fraccionBasica;
	
	/**
	 * Exceso
	 *
	 * @since 1.0
	 */

	private Float exceso;
	
	/**
	 * Impuesto fracción básica
	 *
	 * @since 1.0
	 */

	private Float impuestoFraccionBasica;
	
	/**
	 * Impuesto fracción excedente
	 *
	 * @since 1.0
	 */
	
	/**
	 * anno
	 *
	 * @since 1.0
	 */

	private int anno;

	private int impuestoFraccionExcedente;

	public ImpuestoRenta(int id, Float fraccionBasica, Float exceso,
			Float impuestoFraccionBasica, int impuestoFraccionExcedente, int anno) {
		super();
		this.id = id;
		this.fraccionBasica = fraccionBasica;
		this.exceso = exceso;
		this.impuestoFraccionBasica = impuestoFraccionBasica;
		this.impuestoFraccionExcedente = impuestoFraccionExcedente;
		this.anno = anno;
	}

	public ImpuestoRenta() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getFraccionBasica() {
		return fraccionBasica;
	}

	public void setFraccionBasica(Float fraccionBasica) {
		this.fraccionBasica = fraccionBasica;
	}

	public Float getExceso() {
		return exceso;
	}

	public void setExceso(Float exceso) {
		this.exceso = exceso;
	}

	public Float getImpuestoFraccionBasica() {
		return impuestoFraccionBasica;
	}

	public void setImpuestoFraccionBasica(Float impuestoFraccionBasica) {
		this.impuestoFraccionBasica = impuestoFraccionBasica;
	}

	public int getImpuestoFraccionExcedente() {
		return impuestoFraccionExcedente;
	}

	public void setImpuestoFraccionExcedente(int impuestoFraccionExcedente) {
		this.impuestoFraccionExcedente = impuestoFraccionExcedente;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
	

}
