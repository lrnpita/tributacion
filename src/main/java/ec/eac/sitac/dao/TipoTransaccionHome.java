// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javassist.runtime.Desc;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.ImpuestoRenta;
import ec.eac.sitac.model.TipoSalario;
import ec.eac.sitac.model.TipoTransaccion;

/**
 * Objeto Dao de la clase {@link TipoTransaccion}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoTransaccion
 * @since 2015
 */

@Repository
public class TipoTransaccionHome {
    private SessionFactory sessionFactory;
 
    public TipoTransaccionHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoTransaccionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoTransaccionHome.class);

	/**
	 * Devuelve una lista de los {@link TipoTransaccion} de la Base de Datos.
	 *
	 * @return <code>List<TipoTransaccion></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoTransaccion> list() {
        @SuppressWarnings("unchecked")
        List<TipoTransaccion> listaTiposTransaccion = (List<TipoTransaccion>) sessionFactory.getCurrentSession()
                .createCriteria(TipoTransaccion.class)
                .addOrder(Order.asc("codigo"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposTransaccion;
    }
	/**
	 * Inserta los datos de un {@link TipoTransaccion} en la Base de Datos.
	 *
	 * @since 2015
	 */ 
	@Transactional
	public void persist(TipoTransaccion transientInstance) {
		log.debug("persisting TipoTransaccion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoTransaccion} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoTransaccion instance) {
		log.debug("attaching dirty TipoTransaccion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoTransaccion instance) {
		log.debug("attaching clean TipoTransaccion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoTransaccion} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoTransaccion persistentInstance) {
		log.debug("deleting TipoTransaccion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoTransaccion merge(TipoTransaccion detachedInstance) {
		log.debug("merging TipoTransaccion instance");
		try {
			TipoTransaccion result = (TipoTransaccion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoTransaccion de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>TipoTransaccion</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoTransaccion findById(java.lang.String id) {
		log.debug("getting TipoTransaccion instance with id: " + id);
		try {
			TipoTransaccion instance = (TipoTransaccion) sessionFactory
					.getCurrentSession().get(TipoTransaccion.class, id);
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
	public List findByExample(TipoTransaccion instance) {
		log.debug("finding TipoTransaccion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoTransaccion")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Devuelve el porcentaje del tipo de transacción de la Base de Datos. Devuelve -1 en caso de error.
	 *
	 * @since 1.0
	 */	
    @Transactional
    public int getPorcentajeById(String codigo) {
    	 String queryString = "SELECT c.porcentaje FROM TipoTransaccion c WHERE c.codigo = :codigo"; 
         Session session = sessionFactory.getCurrentSession();
    	 
         Query query = session.createQuery(queryString);
         query.setParameter("codigo", codigo);
         
         try
         {
        	 return Integer.parseInt(query.uniqueResult().toString());
         }
         catch (NumberFormatException ex)
         {
        	 return -1;
         }
    }
}
