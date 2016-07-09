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
import ec.eac.sitac.model.TipoIce;
import ec.eac.sitac.model.TipoPago;

/**
 * Objeto Dao de la clase {@link TipoPago}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoPago
 * @since 2015
 */
@Repository
public class TipoPagoHome {
    private SessionFactory sessionFactory;
 
    public TipoPagoHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoPagoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoPagoHome.class);

	/**
	 * Devuelve una lista de los {@link TipoPago} de la Base de Datos.
	 *
	 * @return <code>List<TipoPago></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoPago> list() {
        @SuppressWarnings("unchecked")
        List<TipoPago> listaTiposPago = (List<TipoPago>) sessionFactory.getCurrentSession()
                .createCriteria(TipoPago.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposPago;
    }
    
	/**
	 * Inserta los datos de un {@link TipoPago} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoPago transientInstance) {
		log.debug("persisting TipoPago instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoPago} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoPago instance) {
		log.debug("attaching dirty TipoPago instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoPago instance) {
		log.debug("attaching clean TipoPago instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoPago} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoPago persistentInstance) {
		log.debug("deleting TipoPago instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoPago merge(TipoPago detachedInstance) {
		log.debug("merging TipoPago instance");
		try {
			TipoPago result = (TipoPago) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoPago de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoPago</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoPago findById(java.lang.String id) {
		log.debug("getting TipoPago instance with id: " + id);
		try {
			TipoPago instance = (TipoPago) sessionFactory.getCurrentSession()
					.get(TipoPago.class, id);
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
	public List findByExample(TipoPago instance) {
		log.debug("finding TipoPago instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria(TipoPago.class).add(Example.create(instance))
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
