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
import ec.eac.sitac.model.TipoEmision;
import ec.eac.sitac.model.TipoExportacionImportacion;

/**
 * Objeto Dao de la clase {@link TipoExportacionImportacion}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoExportacionImportacion
 * @since 2015
 */

@Repository
public class TipoExportacionImportacionHome {
    private SessionFactory sessionFactory;
 
    public TipoExportacionImportacionHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoExportacionImportacionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(TipoExportacionImportacionHome.class);

	/**
	 * Devuelve una lista de {@link TipoExportacionImportacion} de la Base de Datos.
	 *
	 * @return <code>List<TipoExportacionImportacion></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoExportacionImportacion> list() {
        @SuppressWarnings("unchecked")
        List<TipoExportacionImportacion> listaTiposExpImport = (List<TipoExportacionImportacion>) sessionFactory.getCurrentSession()
                .createCriteria(TipoExportacionImportacion.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposExpImport;
    }
    
	/**
	 * Inserta los datos de un {@link TipoExportacionImportacion} en la Base de Datos.
	 *
	 * @since 2015
	 */
    @Transactional
	public void persist(TipoExportacionImportacion transientInstance) {
		log.debug("persisting TipoExportacionImportacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoExportacionImportacion} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoExportacionImportacion instance) {
		log.debug("attaching dirty TipoExportacionImportacion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoExportacionImportacion instance) {
		log.debug("attaching clean TipoExportacionImportacion instance");
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
	public void delete(TipoExportacionImportacion persistentInstance) {
		log.debug("deleting TipoExportacionImportacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoExportacionImportacion merge(
			TipoExportacionImportacion detachedInstance) {
		log.debug("merging TipoExportacionImportacion instance");
		try {
			TipoExportacionImportacion result = (TipoExportacionImportacion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoExportacionImportacion de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoExportacionImportacion</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoExportacionImportacion findById(int id) {
		log.debug("getting TipoExportacionImportacion instance with id: " + id);
		try {
			TipoExportacionImportacion instance = (TipoExportacionImportacion) sessionFactory
					.getCurrentSession().get("TipoExportacionImportacion", id);
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
	public List findByExample(TipoExportacionImportacion instance) {
		log.debug("finding TipoExportacionImportacion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoExportacionImportacion")
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
