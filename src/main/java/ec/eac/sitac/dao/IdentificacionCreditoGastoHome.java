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
import ec.eac.sitac.model.IdentificacionCreditoGasto;
import ec.eac.sitac.model.TipoExportacionImportacion;

/**
 * Objeto Dao de la clase {@link IdentificacionCreditoGasto}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see IdentificacionCreditoGasto
 * @since 2015
 */

@Repository
public class IdentificacionCreditoGastoHome {
    private SessionFactory sessionFactory;
 
    public IdentificacionCreditoGastoHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public IdentificacionCreditoGastoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(IdentificacionCreditoGastoHome.class);

	/**
	 * Inserta los datos de un {@link IdentificacionCreditoGasto} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(IdentificacionCreditoGasto transientInstance) {
		log.debug("persisting IdentificacionCreditoGasto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link IdentificacionCreditoGasto} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(IdentificacionCreditoGasto instance) {
		log.debug("attaching dirty IdentificacionCreditoGasto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(IdentificacionCreditoGasto instance) {
		log.debug("attaching clean IdentificacionCreditoGasto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Elimina un {@link IdentificacionCreditoGasto} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(IdentificacionCreditoGasto persistentInstance) {
		log.debug("deleting IdentificacionCreditoGasto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public IdentificacionCreditoGasto merge(
			IdentificacionCreditoGasto detachedInstance) {
		log.debug("merging IdentificacionCreditoGasto instance");
		try {
			IdentificacionCreditoGasto result = (IdentificacionCreditoGasto) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un IdentificacionCreditoGasto de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>IdentificacionCreditoGasto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public IdentificacionCreditoGasto findById(java.lang.String id) {
		log.debug("getting IdentificacionCreditoGasto instance with id: " + id);
		try {
			IdentificacionCreditoGasto instance = (IdentificacionCreditoGasto) sessionFactory
					.getCurrentSession().get("IdentificacionCreditoGasto", id);
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
	public List findByExample(IdentificacionCreditoGasto instance) {
		log.debug("finding IdentificacionCreditoGasto instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("IdentificacionCreditoGasto")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Transactional
	public List<IdentificacionCreditoGasto> list() {
        @SuppressWarnings("unchecked")
        List<IdentificacionCreditoGasto> list = (List<IdentificacionCreditoGasto>) sessionFactory.getCurrentSession()
                .createCriteria(IdentificacionCreditoGasto.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return list;
	}
}
