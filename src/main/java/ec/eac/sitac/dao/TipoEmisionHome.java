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
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoEmision;

/**
 * Objeto Dao de la clase {@link TipoEmision}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoEmision
 * @since 2015
 */

@Repository
public class TipoEmisionHome {
    private SessionFactory sessionFactory;
 
    public TipoEmisionHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoEmisionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoEmisionHome.class);

	/**
	 * Devuelve una lista de {@link TipoEmision} de la Base de Datos.
	 *
	 * @return <code>List<TipoEmision></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoEmision> list() {
        @SuppressWarnings("unchecked")
        List<TipoEmision> listaTiposEmision = (List<TipoEmision>) sessionFactory.getCurrentSession()
                .createCriteria(TipoEmision.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposEmision;
    }
    
	/**
	 * Inserta los datos de un {@link TipoEmision} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoEmision transientInstance) {
		log.debug("persisting TipoEmision instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoEmision} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoEmision instance) {
		log.debug("attaching dirty TipoEmision instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoEmision instance) {
		log.debug("attaching clean TipoEmision instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoEmision} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoEmision persistentInstance) {
		log.debug("deleting TipoEmision instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoEmision merge(TipoEmision detachedInstance) {
		log.debug("merging TipoEmision instance");
		try {
			TipoEmision result = (TipoEmision) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoEmision de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoEmision</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoEmision findById(char id) {
		log.debug("getting TipoEmision instance with id: " + id);
		try {
			TipoEmision instance = (TipoEmision) sessionFactory
					.getCurrentSession().get("TipoEmision", id);
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
	public List findByExample(TipoEmision instance) {
		log.debug("finding TipoEmision instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoEmision")
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
