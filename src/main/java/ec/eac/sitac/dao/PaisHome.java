// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Pais;

/**
 * Objeto Dao de la clase {@link Pais}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Pais
 * @since 2015
 */

@Repository
public class PaisHome {
    private SessionFactory sessionFactory;
 
    public PaisHome() {
         
    }
 
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public PaisHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(PaisHome.class);

	/**
	 * Devuelve una lista de {@link Pais} de la Base de Datos.
	 *
	 * @return <code>List<Pais></code>.
	 *
	 * @since 2015
	 */
   @Transactional
    public List<Pais> list() {
        @SuppressWarnings("unchecked")
        List<Pais> listaPaises = (List<Pais>) sessionFactory.getCurrentSession()
                .createCriteria(Pais.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaPaises;
    }
    
	/**
	 * Inserta los datos de un {@link Pais} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Pais transientInstance) {
		log.debug("persisting Pais instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Pais} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Pais instance) {
		log.debug("attaching dirty Pais instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Pais instance) {
		log.debug("attaching clean Pais instance");
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
	public void delete(Pais persistentInstance) {
		log.debug("deleting Pais instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Pais merge(Pais detachedInstance) {
		log.debug("merging Pais instance");
		try {
			Pais result = (Pais) sessionFactory.getCurrentSession().merge(
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
	public Pais findById(java.lang.String id) {
		log.debug("getting Pais instance with id: " + id);
		try {
			Pais instance = (Pais) sessionFactory.getCurrentSession().get(
					"Pais", id);
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
	public List findByExample(Pais instance) {
		log.debug("finding Pais instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Pais").add(Example.create(instance))
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
