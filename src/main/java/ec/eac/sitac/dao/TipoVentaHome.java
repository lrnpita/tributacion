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
import ec.eac.sitac.model.TipoTransaccion;
import ec.eac.sitac.model.TipoVenta;

/**
 * Objeto Dao de la clase {@link TipoVenta}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoVenta
 * @since 2015
 */
@Repository
public class TipoVentaHome {
    private SessionFactory sessionFactory;
 
    public TipoVentaHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoVentaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoVentaHome.class);

	/**
	 * Devuelve una lista de los {@link TipoVenta} de la Base de Datos.
	 *
	 * @return <code>List<TipoVenta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoVenta> list() {
        @SuppressWarnings("unchecked")
        List<TipoVenta> listaTiposVenta = (List<TipoVenta>) sessionFactory.getCurrentSession()
                .createCriteria(TipoVenta.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposVenta;
    }
    
	/**
	 * Inserta los datos de un {@link TipoVenta} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoVenta transientInstance) {
		log.debug("persisting TipoVenta instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoVenta} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoVenta instance) {
		log.debug("attaching dirty TipoVenta instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoVenta instance) {
		log.debug("attaching clean TipoVenta instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoVenta} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoVenta persistentInstance) {
		log.debug("deleting TipoVenta instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoVenta merge(TipoVenta detachedInstance) {
		log.debug("merging TipoVenta instance");
		try {
			TipoVenta result = (TipoVenta) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoVenta de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>TipoVenta</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoVenta findById(java.lang.String id) {
		log.debug("getting TipoVenta instance with id: " + id);
		try {
			TipoVenta instance = (TipoVenta) sessionFactory.getCurrentSession()
					.get(TipoVenta.class, id);
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
	public List findByExample(TipoVenta instance) {
		log.debug("finding TipoVenta instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoVenta").add(Example.create(instance))
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
