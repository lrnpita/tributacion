// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.taskdefs.Tar;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.Residencia;
import ec.eac.sitac.model.TarifaIva;
import ec.eac.sitac.model.TipoCompra;

/**
 * Objeto Dao de la clase {@link TarifaIva}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see TarifaIva
 * @since 2015
 */

@Repository
public class TarifaIvaHome {
    private SessionFactory sessionFactory;
 
    public TarifaIvaHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TarifaIvaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TarifaIvaHome.class);

	/**
	 * Devuelve una lista de {@link TarifaIva} de la Base de Datos.
	 *
	 * @return <code>List<TarifaIva></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TarifaIva> list() {
        @SuppressWarnings("unchecked")
        List<TarifaIva> listaTarifas = (List<TarifaIva>) sessionFactory.getCurrentSession()
                .createCriteria(TarifaIva.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTarifas;
    }
  
	/**
	 * Inserta los datos de una {@link TarifaIva} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TarifaIva transientInstance) {
		log.debug("persisting TarifaIva instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de una {@link TarifaIva} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TarifaIva instance) {
		log.debug("attaching dirty TarifaIva instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TarifaIva instance) {
		log.debug("attaching clean TarifaIva instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina una {@link TarifaIva} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TarifaIva persistentInstance) {
		log.debug("deleting TarifaIva instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TarifaIva merge(TarifaIva detachedInstance) {
		log.debug("merging TarifaIva instance");
		try {
			TarifaIva result = (TarifaIva) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una TarifaIva de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TarifaIva</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TarifaIva findById(short id) {
		log.debug("getting TarifaIva instance with id: " + id);
		try {
			TarifaIva instance = (TarifaIva) sessionFactory.getCurrentSession()
					.get(TarifaIva.class, id);
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
	
	/**
	 * Devuele el código de la TarifaIva de la Base de Datos, dado el porcentaje. 
	 *
	 * @return <code>TarifaIva</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public float findByPorcentaje(int porcentaje) {
	       String queryString = "SELECT t.codigo FROM TarifaIva t WHERE t.porcentaje = :porcentaje"; 
	        
	        Session session = sessionFactory.getCurrentSession();
	        Query query = session.createQuery(queryString).setParameter("porcentaje", porcentaje);
	        
	        float codigo = (float) query.uniqueResult();
	 
	        return codigo;
	}

	@Transactional
	public List findByExample(TarifaIva instance) {
		log.debug("finding TarifaIva instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TarifaIva").add(Example.create(instance))
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
