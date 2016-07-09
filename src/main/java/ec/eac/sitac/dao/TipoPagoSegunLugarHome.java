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
import ec.eac.sitac.model.TipoPago;
import ec.eac.sitac.model.TipoPagoSegunLugar;

/**
 * Objeto Dao de la clase {@link TipoPagoSegunLugar}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoPagoSegunLugar
 * @since 2015
 */
@Repository
public class TipoPagoSegunLugarHome {
    private SessionFactory sessionFactory;
 
    public TipoPagoSegunLugarHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoPagoSegunLugarHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(TipoPagoSegunLugarHome.class);

	/**
	 * Devuelve una lista de los {@link TipoPagoSegunLugar} de la Base de Datos.
	 *
	 * @return <code>List<TipoPagoSegunLugar></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoPagoSegunLugar> list() {
        @SuppressWarnings("unchecked")
        List<TipoPagoSegunLugar> listaTiposPagoSegunLugar = (List<TipoPagoSegunLugar>) sessionFactory.getCurrentSession()
                .createCriteria(TipoPagoSegunLugar.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposPagoSegunLugar;
    }
    
	/**
	 * Inserta los datos de un {@link TipoPagoSegunLugar} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoPagoSegunLugar transientInstance) {
		log.debug("persisting TipoPagoSegunLugar instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoPagoSegunLugar} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoPagoSegunLugar instance) {
		log.debug("attaching dirty TipoPagoSegunLugar instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoPagoSegunLugar instance) {
		log.debug("attaching clean TipoPagoSegunLugar instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Elimina un {@link TipoPagoSegunLugar} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoPagoSegunLugar persistentInstance) {
		log.debug("deleting TipoPagoSegunLugar instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoPagoSegunLugar merge(TipoPagoSegunLugar detachedInstance) {
		log.debug("merging TipoPagoSegunLugar instance");
		try {
			TipoPagoSegunLugar result = (TipoPagoSegunLugar) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoPagoSegunLugar de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoPagoSegunLugar</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoPagoSegunLugar findById(java.lang.String id) {
		log.debug("getting TipoPagoSegunLugar instance with id: " + id);
		try {
			TipoPagoSegunLugar instance = (TipoPagoSegunLugar) sessionFactory
					.getCurrentSession().get("TipoPagoSegunLugar", id);
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
	public List findByExample(TipoPagoSegunLugar instance) {
		log.debug("finding TipoPagoSegunLugar instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoPagoSegunLugar")
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
