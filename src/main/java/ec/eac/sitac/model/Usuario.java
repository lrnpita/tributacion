package ec.eac.sitac.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Modelo correspondiente a la tabla usuario de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idUsuario;
	
	/**
	 * Nombre
	 *
	 * @since 2015
	 */
	private String nombre;
	
	/**
	 * Nombre de usuario
	 *
	 * @since 2015
	 */
	private String nombreUsuario;
	
	/**
	 * Email
	 *
	 * @since 2015
	 */
	private String email;
	
	/**
	 * Contraseña
	 *
	 * @since 2015
	 */
	private String contrasena;
	
	/**
	 * Bloqueado
	 *
	 * @since 2015
	 */
	private boolean activado;
	
	/**
	 * Fecha de registro
	 *
	 * @since 2015
	 */
	private Date fechaRegistro;
	
	/**
	 * Activación
	 *
	 * @since 2015
	 */
	private String activacion;
	
	/**
	 * Firma electrónica que usuará el usuario para firmar los xml que se enviarán al SRI
	 *
	 * @since 1.0
	 */
	private String firmaElectronica;
	
	/**
	 * Clave de la firma electrónica
	 *
	 * @since 1.0
	 */
	private String claveFirma;
	
	/**
	 * Rol
	 *
	 * @since 2015
	 */
	private Rol rol;
	
	/**
	 * Puntos de emisión
	 *
	 * @since 2015
	 */
	//@Cascade({CascadeType.DELETE})
	private Set<PuntoEmision> puntosEmision = new HashSet<PuntoEmision>(0);
	
	public Usuario() {
		
	}
	
	public Usuario (int idUsuario, String nombreUsuario, String email, String contrasena, boolean activado, Date fechaRegistro, Rol rol, Set<PuntoEmision> puntosEmision) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contrasena = contrasena;
		this.activado = activado;
		this.fechaRegistro = fechaRegistro;
		this.rol = rol;
		this.puntosEmision = puntosEmision;
	}
	
	public Usuario (int idUsuario, String nombre, String nombreUsuario, String email, String contrasena, 
			boolean activado, Date fechaRegistro, String firmaElectronica,
			String claveFirma, Rol rol, String activacion, Set<PuntoEmision> puntosEmision) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contrasena = contrasena;
		this.activado = activado;
		this.fechaRegistro = fechaRegistro;
		this.activacion = activacion;
		this.firmaElectronica = firmaElectronica;
		this.claveFirma = claveFirma;
		this.rol = rol;
		this.puntosEmision = puntosEmision;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getActivacion() {
		return activacion;
	}

	public void setActivacion(String activacion) {
		this.activacion = activacion;
	}
	
	public String getFirmaElectronica() {
		return this.firmaElectronica;
	}

	public void setFirmaElectronica(String firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}

	public String getClaveFirma() {
		return this.claveFirma;
	}

	public void setClaveFirma(String claveFirma) {
		this.claveFirma = claveFirma;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	/**
	 * Devuelve el punto de emisión asociado al usuario dentro de la empresa cuyo id es IdEmpresa
	 * 
	 * @param idEmpresa
	 * @return
	 */
	public PuntoEmision getPuntoEmision(int idEmpresa) {
		
		for (PuntoEmision obj : this.getPuntosEmision()) {
			if (obj.getEmpresa().getIdEmpresa() == idEmpresa)
				return obj;
		}
		return null;
	}

	public Set<PuntoEmision> getPuntosEmision() {
		return puntosEmision;
	}

	public void setPuntosEmision(Set<PuntoEmision> puntosEmision) {
		this.puntosEmision = puntosEmision;
	}	
	
}
