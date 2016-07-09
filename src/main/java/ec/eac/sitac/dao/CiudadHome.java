// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.TipoIdentificacion;

/**
 * Objeto Dao de la clase {@link Ciudad}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Ciudad
 * @since 1.0
 */

@Repository
public class CiudadHome {
    private SessionFactory sessionFactory;
    
    public CiudadHome() {
        
    }
    
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */ 
    public CiudadHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(CiudadHome.class);

	/**
	 * Devuelve una lista de {@link Ciudad} de la Base de Datos.
	 *
	 * @return <code>List<Ciudad></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<Ciudad> list() {
        @SuppressWarnings("unchecked")
        List<Ciudad> listaCiudades = (List<Ciudad>) sessionFactory.getCurrentSession()
                .createCriteria(Ciudad.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaCiudades;
    }
    
	/**
	 * Inserta los datos de una {@link Ciudad} en la Base de Datos .
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Ciudad transientInstance) {
		log.debug("persisting Ciudad instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Ciudad} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Ciudad instance) {
		log.debug("attaching dirty Ciudad instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Ciudad instance) {
		log.debug("attaching clean Ciudad instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Ciudad} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Ciudad persistentInstance) {
		log.debug("deleting Ciudad instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Ciudad merge(Ciudad detachedInstance) {
		log.debug("merging Ciudad instance");
		try {
			Ciudad result = (Ciudad) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Ciudad de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Ciudad</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Ciudad findById(java.lang.String id) {
		log.debug("getting Ciudad instance with id: " + id);
		try {
			Ciudad instance = (Ciudad) sessionFactory.getCurrentSession().get(
					Ciudad.class, id);
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

	@Transactional
	public List findByExample(Ciudad instance) {
		log.debug("finding Ciudad instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Ciudad").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
