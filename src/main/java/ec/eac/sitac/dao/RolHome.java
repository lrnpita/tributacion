package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Rol;

/**
 * Objeto Dao de la clase {@link Rol}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Rol
 * @since 1.0
 */
public class RolHome {
    private SessionFactory sessionFactory;
	
    public RolHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public RolHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(Rol.class);

	/**
	 * Devuelve una lista de {@link Rol} de la Base de Datos.
	 *
	 * @return <code>List<Rol></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Rol> list() {
        @SuppressWarnings("unchecked")
        List<Rol> listaRol = (List<Rol>) sessionFactory.getCurrentSession()
                .createCriteria(Rol.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaRol;
    }
    
	/**
	 * Inserta los datos de un {@link Rol} en la Base de Datos .
	 *
	 * @since 1.0
	 */
    @Transactional
	public void persist(Rol transientInstance) {
		log.debug("persisting Rol instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Rol} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
    @Transactional
	public void attachDirty(Rol instance) {
		log.debug("attaching dirty Rol instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Rol} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
    @Transactional
	public void delete(Rol persistentInstance) {
		log.debug("deleting Rol instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    @Transactional
	public Rol merge(Rol detachedInstance) {
		log.debug("merging Rol instance");
		try {
			Rol result = (Rol) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Rol de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Rol</code> 
	 * 
	 * @since 1.0
	 */
    @Transactional
	public Rol findById(int id) {
		log.debug("getting Rol instance with id: " + id);
		try {
			Rol instance = (Rol) sessionFactory.getCurrentSession().get(Rol.class, id);
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
}
