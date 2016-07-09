package ec.eac.sitac.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Rol;
import ec.eac.sitac.model.Usuario;

/**
 * Objeto Dao de la clase {@link Usuario}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Usuario
 * @since 1.0
 */
@Repository
public class UsuarioHome {
    private SessionFactory sessionFactory;
	
    public UsuarioHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public UsuarioHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(Usuario.class);

	/**
	 * Devuelve una lista de {@link Usuario} de la Base de Datos.
	 *
	 * @return <code>List<Usuario></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Usuario> list() {
        @SuppressWarnings("unchecked")
        List<Usuario> listaUsuario = (List<Usuario>) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaUsuario;
    }
    
	/**
	 * Devuelve una lista de {@link Usuario} de la Base de Datos.
	 *
	 * @return <code>List<Usuario></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Usuario> list(int idEmpresa) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createSQLQuery("SELECT u.id, u.nombre, u.nombre_usuario, u.email, u.contrasena, u.activado, u.fecha_registro, u.activacion, u.id_rol, u.firma_electronica, u.clave_firma "
    			+ "FROM usuario u JOIN punto_emision pe ON u.id = pe.id_usuario WHERE pe.id_empresa = :idEmpresa")
    			.addEntity(Usuario.class)
    			.setParameter("idEmpresa", idEmpresa);
    	
        return query.list();
    }
    
	/**
	 * Inserta los datos de un {@link Usuario} en la Base de Datos .
	 *
	 * @since 1.0
	 */
    @Transactional
	public void persist(int idRol, int idEmpresa, PuntoEmision puntoEmision, Usuario usuario) {
		log.debug("persisting Usuario instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			prepararObjetoUsuario(idRol, idEmpresa, puntoEmision, usuario);
			
			session.persist(usuario);
			session.save(puntoEmision);
			usuario.getPuntosEmision().add(puntoEmision);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Usuario} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
    @Transactional
	public void attachDirty(int idRol, int idEmpresa, PuntoEmision puntoEmision, Usuario usuario) {
		log.debug("attaching dirty Usuario instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			prepararObjetoUsuario(idRol, idEmpresa, puntoEmision, usuario);
			
			session.saveOrUpdate(puntoEmision);
			session.saveOrUpdate(usuario);
			
			log.debug("attach successful");
		}
		catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Usuario} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
    @Transactional
	public void delete(Usuario persistentInstance) throws SQLException {
		log.debug("deleting Usuario instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    @Transactional
	public Usuario merge(Usuario detachedInstance) {
		log.debug("merging Usuario instance");
		try {
			Usuario result = (Usuario) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Usuario de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Usuario</code> 
	 * 
	 * @since 1.0
	 */
    @Transactional
	public Usuario findById(int id) {
		log.debug("getting Usuario instance with idUsuario: " + id);
		try {
			Usuario instance = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
	/**
	 * Devuele un Usuario de la Base de Datos, dado el username. 
	 *
	 * @return Un objeto de tipo <code>Usuario</code> 
	 * 
	 * @since 1.0
	 */
    @Transactional
	public Usuario findByUsername(String nombreUsuario) {
		log.debug("getting Usuario instance with username: " + nombreUsuario);
		try {

	    	Session session = sessionFactory.getCurrentSession();
	    	Usuario usuario = (Usuario) session.createSQLQuery("SELECT * FROM Usuario u WHERE nombre_usuario = :nombreUsuario")
	    			.addEntity(Usuario.class)
	    			.setParameter("nombreUsuario", nombreUsuario)
	    			.uniqueResult();
			
	    	return usuario;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
    /**
     * Crea los objetos Usuarios que serán almacenados o actualizados en la BD
     * 
     * @param idRol
     * 
     * @since 1.0
     */
    @Transactional
    public void prepararObjetoUsuario(int idRol, int idEmpresa, PuntoEmision puntoEmision, Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		
		Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
		puntoEmision.setEmpresa(empresa);
		
		puntoEmision.setSecFactura(puntoEmision.getNumInicialFactura());
		puntoEmision.setSecFactura(puntoEmision.getNumInicialFactura());
		puntoEmision.setUsuario(usuario);
		
		Rol rol = (Rol) session.get(Rol.class, idRol);
		usuario.setRol(rol);
    }
    
    /**
     * Devuelve verdadero si existe un usuario con nombre = nombreUsuario y que pertenece a la empresa con Id = idEmpresa
     * 
     * @param idEmpresa
     * @param nombreUsuario
     * 
     * @since 1.0
     */
    @Transactional
    public boolean existeUsuarioVsEmpresa(int idEmpresa, String nombreUsuario) {
    	
		Session session = sessionFactory.getCurrentSession();
		
    	Query query = session.createSQLQuery("SELECT u.id, u.nombre, u.nombre_usuario, u.email, u.contrasena, u.activado, u.fecha_registro, u.activacion, u.id_rol, u.firma_electronica, u.clave_firma "
    			+ "FROM usuario u JOIN punto_emision pe ON u.id = pe.id_usuario WHERE pe.id_empresa = :idEmpresa AND u.nombre_usuario = :name")
    			.addEntity(Usuario.class)
    			.setParameter("idEmpresa", idEmpresa)
    			.setParameter("name", nombreUsuario);
    	
        return (query.list().size() > 0) ? true : false;
    }
    
    /**
     * Devuelve verdadero si existe un usuario con nombre = nombreUsuario
     * 
     * @param nombreUsuario
     * 
     * @since 1.0
     */
    @Transactional
    public boolean existeUsuario(String nombreUsuario, int idUsuario) {
    	
		Session session = sessionFactory.getCurrentSession();
		
    	Query query = session.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.idUsuario <> :idUsuario")
    			.setParameter("idUsuario", idUsuario)
    			.setParameter("nombreUsuario", nombreUsuario);
    	
        return (query.list().size() > 0) ? true : false;
    }
    
    /**
	 * Devuelve la cantidad de usuarios que existen en la Base de datos dado una empresa.
	 *
	 * @param idEmpresa
	 * 
	 * @since 1.0
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM PuntoEmision c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
  
}
