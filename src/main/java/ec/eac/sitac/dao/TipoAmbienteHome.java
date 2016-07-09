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
import ec.eac.sitac.model.TarifaIva;
import ec.eac.sitac.model.TipoAmbiente;

/**
 * Objeto Dao de la clase {@link TipoAmbiente}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see TipoAmbiente
 * @since 1.0
 */

@Repository
public class TipoAmbienteHome {
    private SessionFactory sessionFactory;
 
    public TipoAmbienteHome() {
         
    }
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */     
    public TipoAmbienteHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoAmbienteHome.class);

	/**
	 * Devuelve una lista de {@link TipoAmbiente} de la Base de Datos.
	 *
	 * @return <code>List<TipoAmbiente></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<TipoAmbiente> list() {
        @SuppressWarnings("unchecked")
        List<TipoAmbiente> listaTiposAmbiente = (List<TipoAmbiente>) sessionFactory.getCurrentSession()
                .createCriteria(TipoAmbiente.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposAmbiente;
    }
   
	/**
	 * Inserta los datos de un {@link TipoAmbiente} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(TipoAmbiente transientInstance) {
		log.debug("persisting TipoAmbiente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoAmbiente} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(TipoAmbiente instance) {
		log.debug("attaching dirty TipoAmbiente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoAmbiente instance) {
		log.debug("attaching clean TipoAmbiente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoAmbiente} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(TipoAmbiente persistentInstance) {
		log.debug("deleting TipoAmbiente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@Transactional
	public TipoAmbiente merge(TipoAmbiente detachedInstance) {
		log.debug("merging TipoAmbiente instance");
		try {
			TipoAmbiente result = (TipoAmbiente) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoAmbiente de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoAmbiente</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public TipoAmbiente findById(char id) {
		log.debug("getting TipoAmbiente instance with id: " + id);
		try {
			TipoAmbiente instance = (TipoAmbiente) sessionFactory
					.getCurrentSession().get("TipoAmbiente", id);
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
	public List findByExample(TipoAmbiente instance) {
		log.debug("finding TipoAmbiente instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoAmbiente")
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
