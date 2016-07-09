// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.ComprobanteReembolso;

/**
 * Objeto Dao de la clase {@link ComprobanteReembolso}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see ComprobanteReembolso
 * @since 2015
 */

@Repository
public class ComprobanteReembolsoHome {
    private SessionFactory sessionFactory;
    
    public ComprobanteReembolsoHome() {
        
    }
    
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public ComprobanteReembolsoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory
			.getLog(ComprobanteReembolsoHome.class);

	/**
	 * Inserta los datos de un {@link ComprobanteReembolso} en la Base de Datos .
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(ComprobanteReembolso transientInstance) {
		log.debug("persisting ComprobanteReembolso instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link ComprobanteReembolso} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(ComprobanteReembolso instance) {
		log.debug("attaching dirty ComprobanteReembolso instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(ComprobanteReembolso instance) {
		log.debug("attaching clean ComprobanteReembolso instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link ComprobanteReembolso} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(ComprobanteReembolso persistentInstance) {
		log.debug("deleting ComprobanteReembolso instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public ComprobanteReembolso merge(ComprobanteReembolso detachedInstance) {
		log.debug("merging ComprobanteReembolso instance");
		try {
			ComprobanteReembolso result = (ComprobanteReembolso) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un ComprobanteReembolso de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>ComprobanteReembolso</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public ComprobanteReembolso findById(int id) {
		log.debug("getting ComprobanteReembolso instance with id: " + id);
		try {
			ComprobanteReembolso instance = (ComprobanteReembolso) sessionFactory
					.getCurrentSession().get("ComprobanteReembolso", id);
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
	public List findByExample(ComprobanteReembolso instance) {
		log.debug("finding ComprobanteReembolso instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("ComprobanteReembolso")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
