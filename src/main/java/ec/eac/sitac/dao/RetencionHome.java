// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import ec.eac.sitac.model.Retencion;


/**
 * Objeto Dao de la clase {@link Retencion}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Pais
 * @since 2015
 */

@Repository
public class RetencionHome {
    private SessionFactory sessionFactory;
 
    public RetencionHome() {
         
    }
 
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public RetencionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(RetencionHome.class);

	/**
	 * Devuelve una lista de {@link Pais} de la Base de Datos.
	 *
	 * @return <code>List<Pais></code>.
	 *
	 * @since 2015
	 */
   @Transactional
    public List<Retencion> list() {
        @SuppressWarnings("unchecked")
        List<Retencion> listaRetenciones = (List<Retencion>) sessionFactory.getCurrentSession()
                .createCriteria(Retencion.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaRetenciones;
    }
    
	/**
	 * Inserta los datos de un {@link Retencion} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(Retencion transientInstance) {
		log.debug("persisting Retencion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de una {@link Retencion} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(Retencion instance) {
		log.debug("attaching dirty Retencion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Retencion instance) {
		log.debug("attaching clean Retencion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Pais} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Retencion persistentInstance) {
		log.debug("deleting Retencion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Retencion merge(Retencion detachedInstance) {
		log.debug("merging Retencion instance");
		try {
			Retencion result = (Retencion) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Pais de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Pais</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Retencion findById(int id) {
		log.debug("getting Retencion instance with id: " + id);
		try {
			Retencion instance = (Retencion) sessionFactory.getCurrentSession().get(Retencion.class, id);
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
	public List findByExample(Retencion instance) {
		log.debug("finding Retencion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria(Retencion.class).add(Example.create(instance))
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
