package ec.eac.sitac.dao;
// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.Ciudad;

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

/**
 * Objeto Dao de la clase {@link Canton}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Canton
 * @since 1.0
 */
@Repository
public class CantonHome {
    private SessionFactory sessionFactory;
    
    public CantonHome() {
        
    }
    
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public CantonHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(CantonHome.class);

	/**
	 * Devuelve una lista de {@link Canton} de la Base de Datos.
	 *
	 * @return <code>List<Canton></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Canton> list() {
        @SuppressWarnings("unchecked")
        List<Canton> listaCantones = (List<Canton>) sessionFactory.getCurrentSession()
                .createCriteria(Canton.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaCantones;
    }
    
	/**
	 * Inserta los datos de un {@link Canton} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(Canton transientInstance) {
		log.debug("persisting Canton instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Canton} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(Canton instance) {
		log.debug("attaching dirty Canton instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Canton instance) {
		log.debug("attaching clean Canton instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Canton} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(Canton persistentInstance) {
		log.debug("deleting Canton instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Canton merge(Canton detachedInstance) {
		log.debug("merging Canton instance");
		try {
			Canton result = (Canton) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Canton de la Base de Datos, dado el Id. 
	 *
	 * @return <code>Canton</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public Canton findById(java.lang.String id) {
		log.debug("getting Canton instance with id: " + id);
		try {
			Canton instance = (Canton) sessionFactory.getCurrentSession().get(
					Canton.class, id);
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
	public List<Canton> findByExample(Canton instance) {
		log.debug("finding Canton instance by example");
		try {
			List<Canton> results = sessionFactory.getCurrentSession()
					.createCriteria("Canton").add(Example.create(instance))
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
