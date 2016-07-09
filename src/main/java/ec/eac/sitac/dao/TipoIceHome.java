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
import ec.eac.sitac.model.TipoExportacionImportacion;
import ec.eac.sitac.model.TipoIce;

/**
 * Objeto Dao de la clase {@link TipoIce}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoIce
 * @since 2015
 */
@Repository
public class TipoIceHome {
    private SessionFactory sessionFactory;
 
    public TipoIceHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoIceHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoIceHome.class);

	/**
	 * Devuelve una lista de {@link TipoIce} de la Base de Datos.
	 *
	 * @return <code>List<TipoIce></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoIce> list() {
        @SuppressWarnings("unchecked")
        List<TipoIce> listaTiposIce = (List<TipoIce>) sessionFactory.getCurrentSession()
                .createCriteria(TipoIce.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposIce;
    }
    
	/**
	 * Inserta los datos de un {@link TipoIce} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoIce transientInstance) {
		log.debug("persisting TipoIce instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoIce} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoIce instance) {
		log.debug("attaching dirty TipoIce instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoIce instance) {
		log.debug("attaching clean TipoIce instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Canton} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoIce persistentInstance) {
		log.debug("deleting TipoIce instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoIce merge(TipoIce detachedInstance) {
		log.debug("merging TipoIce instance");
		try {
			TipoIce result = (TipoIce) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoIce de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoIce</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoIce findById(int id) {
		log.debug("getting TipoIce instance with id: " + id);
		try {
			TipoIce instance = (TipoIce) sessionFactory.getCurrentSession()
					.get("TipoIce", id);
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
	public List findByExample(TipoIce instance) {
		log.debug("finding TipoIce instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoIce").add(Example.create(instance))
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
