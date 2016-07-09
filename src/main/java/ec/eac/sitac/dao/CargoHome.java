// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

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
import ec.eac.sitac.model.Cargo;

/**
 * Objeto Dao de la clase {@link Cargo}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Cargo
 * @since 2015
 */

@Repository
public class CargoHome {
    private SessionFactory sessionFactory;
    
    public CargoHome() {
        
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public CargoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(CargoHome.class);

	/**
	 * Devuelve una lista de {@link Cargo} de la Base de Datos.
	 *
	 * @return <code>List<Cargo></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<Cargo> list() {
        @SuppressWarnings("unchecked")
        List<Cargo> listaCargos = (List<Cargo>) sessionFactory.getCurrentSession()
                .createCriteria(Cargo.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaCargos;
    }
    
	/**
	 * Inserta los datos de un {@link Cargo} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Cargo transientInstance) {
		log.debug("persisting Cargo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Cargo} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Cargo instance) {
		log.debug("attaching dirty Cargo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Cargo instance) {
		log.debug("attaching clean Cargo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Cargo} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Cargo persistentInstance) {
		log.debug("deleting Cargo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Cargo merge(Cargo detachedInstance) {
		log.debug("merging Cargo instance");
		try {
			Cargo result = (Cargo) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Cargo de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Cargo</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Cargo findById(int id) {
		log.debug("getting Cargo instance with id: " + id);
		try {
			Cargo instance = (Cargo) sessionFactory.getCurrentSession().get(
					Cargo.class, id);
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
	public List findByExample(Cargo instance) {
		log.debug("finding Cargo instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Cargo").add(Example.create(instance))
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
