package ec.eac.sitac.model;

/**
 * Modelo correspondiente a la tabla ciudad de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Discapacidad implements java.io.Serializable {
	
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
	 * Porcentaje desde
	 *
	 * @since 1.0
	 */
	private int porcentajeDesde;
	
	/**
	 * Porcentaje hasta
	 *
	 * @since 1.0
	 */
	private int porcentajeHasta;
	
	/**
	 * Aplicacion Beneficio
	 *
	 * @since 1.0
	 */
	private int aplicacionBeneficio;
	
	/**
	 * anno
	 *
	 * @since 1.0
	 */
	private int anno;

	public Discapacidad() {
		super();
	}

	public Discapacidad(int id, int porcentajeDesde, int porcentajeHasta,
			int aplicacionBeneficio, int anno) {
		super();
		this.id = id;
		this.porcentajeDesde = porcentajeDesde;
		this.porcentajeHasta = porcentajeHasta;
		this.aplicacionBeneficio = aplicacionBeneficio;
		this.anno = anno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPorcentajeDesde() {
		return porcentajeDesde;
	}

	public void setPorcentajeDesde(int porcentajeDesde) {
		this.porcentajeDesde = porcentajeDesde;
	}

	public int getPorcentajeHasta() {
		return porcentajeHasta;
	}

	public void setPorcentajeHasta(int porcentajeHasta) {
		this.porcentajeHasta = porcentajeHasta;
	}

	public int getAplicacionBeneficio() {
		return aplicacionBeneficio;
	}

	public void setAplicacionBeneficio(int aplicacionBeneficio) {
		this.aplicacionBeneficio = aplicacionBeneficio;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	

}
