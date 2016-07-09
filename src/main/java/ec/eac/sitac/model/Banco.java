package ec.eac.sitac.model;


/**
 * Modelo correspondiente a la tabla Bnaco de la base de datos
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Banco implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idBanco;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;

	
	public Banco() {
		super();
	}


	public Banco(int idBanco, String nombre) {
		super();
		this.idBanco = idBanco;
		this.nombre = nombre;
	}


	public int getIdBanco() {
		return idBanco;
	}


	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
