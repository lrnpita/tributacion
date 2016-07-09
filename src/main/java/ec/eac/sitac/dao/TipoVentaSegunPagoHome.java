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
import ec.eac.sitac.model.TipoVenta;
import ec.eac.sitac.model.TipoVentaSegunPago;

/**
 * Objeto Dao de la clase {@link TipoVentaSegunPago}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see TipoVentaSegunPago
 * @since 2015
 */

@Repository
public class TipoVentaSegunPagoHome {
    private SessionFactory sessionFactory;
 
    public TipoVentaSegunPagoHome() {
         
    }
    
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoVentaSegunPagoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(TipoVentaSegunPagoHome.class);

	/**
	 * Devuelve una lista de los {@link TipoVentaSegunPago} de la Base de Datos.
	 *
	 * @return <code>List<TipoVentaSegunPago></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoVentaSegunPago> list() {
        @SuppressWarnings("unchecked")
        List<TipoVentaSegunPago> listaTiposVentaSegunPago = (List<TipoVentaSegunPago>) sessionFactory.getCurrentSession()
                .createCriteria(TipoVentaSegunPago.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposVentaSegunPago;
    }
    
	/**
	 * Inserta los datos de un {@link TipoVentaSegunPago} en la Base de Datos.
	 *
	 * @since 2015
	 */    
	@Transactional
	public void persist(TipoVentaSegunPago transientInstance) {
		log.debug("persisting TipoVentaSegunPago instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoVentaSegunPago} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoVentaSegunPago instance) {
		log.debug("attaching dirty TipoVentaSegunPago instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoVentaSegunPago instance) {
		log.debug("attaching clean TipoVentaSegunPago instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Elimina un {@link TipoVentaSegunPago} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoVentaSegunPago persistentInstance) {
		log.debug("deleting TipoVentaSegunPago instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoVentaSegunPago merge(TipoVentaSegunPago detachedInstance) {
		log.debug("merging TipoVentaSegunPago instance");
		try {
			TipoVentaSegunPago result = (TipoVentaSegunPago) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoVentaSegunPago de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>TipoVentaSegunPago</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoVentaSegunPago findById(java.lang.String id) {
		log.debug("getting TipoVentaSegunPago instance with id: " + id);
		try {
			TipoVentaSegunPago instance = (TipoVentaSegunPago) sessionFactory
					.getCurrentSession().get(TipoVentaSegunPago.class, id);
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
	public List findByExample(TipoVentaSegunPago instance) {
		log.debug("finding TipoVentaSegunPago instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria(TipoVentaSegunPago.class)
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
