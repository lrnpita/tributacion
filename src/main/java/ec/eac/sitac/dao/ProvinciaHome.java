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
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.Provincia;
import ec.eac.sitac.model.TipoVentaSegunPago;

/**
 * Objeto Dao de la clase {@link Provincia}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Provincia
 * @since 2015
 */

@Repository
public class ProvinciaHome {
    private SessionFactory sessionFactory;
 
    public ProvinciaHome() {
         
    }

	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public ProvinciaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(ProvinciaHome.class);

	/**
	 * Devuelve una lista de {@link Provincia} de la Base de Datos.
	 *
	 * @return <code>List<Provincia></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Provincia> list() {
        @SuppressWarnings("unchecked")
        List<Provincia> listaProvincias = (List<Provincia>) sessionFactory.getCurrentSession()
                .createCriteria(Provincia.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaProvincias;
    }
   
	/**
	 * Inserta los datos de una {@link Provincia} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Provincia transientInstance) {
		log.debug("persisting Provincia instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de una {@link Provincia} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Provincia instance) {
		log.debug("attaching dirty Provincia instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Provincia instance) {
		log.debug("attaching clean Provincia instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina una {@link Provincia} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Provincia persistentInstance) {
		log.debug("deleting Provincia instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Provincia merge(Provincia detachedInstance) {
		log.debug("merging Provincia instance");
		try {
			Provincia result = (Provincia) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Provincia de la Base de Datos, dado el Id. 
	 *
	 * @return <code>Provincia</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Provincia findById(int id) {
		log.debug("getting Provincia instance with id: " + id);
		try {
			Provincia instance = (Provincia) sessionFactory.getCurrentSession()
					.get("Provincia", id);
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
	public List findByExample(Provincia instance) {
		log.debug("finding Provincia instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Provincia").add(Example.create(instance))
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
