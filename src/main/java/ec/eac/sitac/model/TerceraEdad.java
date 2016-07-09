package ec.eac.sitac.model;
/**
 * Modelo correspondiente a la tabla Bnaco de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since
 */
public class TerceraEdad {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idTerceraEdad;
	
	private int anno;
	
	private int fraccionBasica;

	public TerceraEdad(int idTerceraEdad, int anno, int fraccionBasica) {
		super();
		this.idTerceraEdad = idTerceraEdad;
		this.anno = anno;
		this.fraccionBasica = fraccionBasica;
	}

	public TerceraEdad() {
		super();
	}

	public int getIdTerceraEdad() {
		return idTerceraEdad;
	}

	public void setIdTerceraEdad(int idTerceraEdad) {
		this.idTerceraEdad = idTerceraEdad;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getFraccionBasica() {
		return fraccionBasica;
	}

	public void setFraccionBasica(int fraccionBasica) {
		this.fraccionBasica = fraccionBasica;
	}
	
	
}
