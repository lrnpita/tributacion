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
import ec.eac.sitac.model.Irpnr;
import ec.eac.sitac.model.TarifaIva;

/**
 * Objeto Dao de la clase {@link Irpnr}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Irpnr
 * @since 2015
 */

@Repository
public class IrpnrHome {
    private SessionFactory sessionFactory;
 
    public IrpnrHome() {
         
    }
    
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public IrpnrHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(IrpnrHome.class);

	/**
	 * Devuelve una lista de {@link Irpnr} de la Base de Datos.
	 *
	 * @return <code>List<Irpnr></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<Irpnr> list() {
        @SuppressWarnings("unchecked")
        List<Irpnr> listaIrpnr = (List<Irpnr>) sessionFactory.getCurrentSession()
                .createCriteria(Irpnr.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaIrpnr;
    }
	
	/**
	 * Inserta los datos de un {@link Irpnr} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Irpnr transientInstance) {
		log.debug("persisting Irpnr instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Irpnr} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Irpnr instance) {
		log.debug("attaching dirty Irpnr instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Irpnr instance) {
		log.debug("attaching clean Irpnr instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Irpnr} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Irpnr persistentInstance) {
		log.debug("deleting Irpnr instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Irpnr merge(Irpnr detachedInstance) {
		log.debug("merging Irpnr instance");
		try {
			Irpnr result = (Irpnr) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Irpnr de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Irpnr</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Irpnr findById(int id) {
		log.debug("getting Irpnr instance with id: " + id);
		try {
			Irpnr instance = (Irpnr) sessionFactory.getCurrentSession().get(
					"Irpnr", id);
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
	public List findByExample(Irpnr instance) {
		log.debug("finding Irpnr instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Irpnr").add(Example.create(instance))
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
