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
import ec.eac.sitac.model.TipoPagoSegunLugar;
import ec.eac.sitac.model.TipoPagoVsCompra;

/**
 * Objeto Dao de la clase {@link TipoPagoVsCompra}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoPagoVsCompra
 * @since 2015
 */

@Repository
public class TipoPagoVsCompraHome {
    private SessionFactory sessionFactory;
 
    public TipoPagoVsCompraHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoPagoVsCompraHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(TipoPagoVsCompraHome.class);

	/**
	 * Devuelve una lista de los {@link TipoPagoVsCompra} de la Base de Datos.
	 *
	 * @return <code>List<TipoPagoVsCompra></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<TipoPagoVsCompra> list() {
        @SuppressWarnings("unchecked")
        List<TipoPagoVsCompra> listaTiposPagoVsCompra = (List<TipoPagoVsCompra>) sessionFactory.getCurrentSession()
                .createCriteria(TipoPagoVsCompra.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposPagoVsCompra;
    }
    
	/**
	 * Inserta los datos de un {@link TipoPagoVsCompra} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoPagoVsCompra transientInstance) {
		log.debug("persisting TipoPagoVsCompra instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoPagoVsCompra} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoPagoVsCompra instance) {
		log.debug("attaching dirty TipoPagoVsCompra instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoPagoVsCompra instance) {
		log.debug("attaching clean TipoPagoVsCompra instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoPagoVsCompra} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoPagoVsCompra persistentInstance) {
		log.debug("deleting TipoPagoVsCompra instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoPagoVsCompra merge(TipoPagoVsCompra detachedInstance) {
		log.debug("merging TipoPagoVsCompra instance");
		try {
			TipoPagoVsCompra result = (TipoPagoVsCompra) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoPagoVsCompra de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoPagoVsCompra</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoPagoVsCompra findById(int id) {
		log.debug("getting TipoPagoVsCompra instance with id: " + id);
		try {
			TipoPagoVsCompra instance = (TipoPagoVsCompra) sessionFactory
					.getCurrentSession().get(TipoPagoVsCompra.class, id);
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
	public List findByExample(TipoPagoVsCompra instance) {
		log.debug("finding TipoPagoVsCompra instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria(TipoPagoVsCompra.class)
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
