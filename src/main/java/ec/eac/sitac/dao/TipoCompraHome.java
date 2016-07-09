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
import ec.eac.sitac.model.TipoAmbiente;
import ec.eac.sitac.model.TipoCompra;

/**
 * Objeto Dao de la clase {@link TipoCompra}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoCompra
 * @since 2015
 */
@Repository
public class TipoCompraHome {
    private SessionFactory sessionFactory;
 
    public TipoCompraHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoCompraHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoCompraHome.class);

	/**
	 * Devuelve una lista de los {@link TipoCompra} de la Base de Datos.
	 *
	 * @return <code>List<TipoCompra></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<TipoCompra> list() {
        @SuppressWarnings("unchecked")
        List<TipoCompra> listaTiposCompra = (List<TipoCompra>) sessionFactory.getCurrentSession()
                .createCriteria(TipoCompra.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposCompra;
    }
    
	/**
	 * Inserta los datos de un {@link TipoCompra} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoCompra transientInstance) {
		log.debug("persisting TipoCompra instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoCompra} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoCompra instance) {
		log.debug("attaching dirty TipoCompra instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoCompra instance) {
		log.debug("attaching clean TipoCompra instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoCompra} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoCompra persistentInstance) {
		log.debug("deleting TipoCompra instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoCompra merge(TipoCompra detachedInstance) {
		log.debug("merging TipoCompra instance");
		try {
			TipoCompra result = (TipoCompra) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoCompra de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoCompra</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoCompra findById(java.lang.String id) {
		log.debug("getting TipoCompra instance with id: " + id);
		try {
			TipoCompra instance = (TipoCompra) sessionFactory
					.getCurrentSession().get(TipoCompra.class, id);
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
	public List findByExample(TipoCompra instance) {
		log.debug("finding TipoCompra instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoCompra").add(Example.create(instance))
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
