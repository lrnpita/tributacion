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
import ec.eac.sitac.model.TipoPagoVsCompra;
import ec.eac.sitac.model.TipoSalario;

/**
 * Objeto Dao de la clase {@link TipoSalario}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoSalario
 * @since 2015
 */

@Repository
public class TipoSalarioHome {
    private SessionFactory sessionFactory;
 
    public TipoSalarioHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoSalarioHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoSalarioHome.class);

	/**
	 * Devuelve una lista de los {@link TipoSalario} de la Base de Datos.
	 *
	 * @return <code>List<TipoSalario></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoSalario> list() {
        @SuppressWarnings("unchecked")
        List<TipoSalario> listaTiposSalario = (List<TipoSalario>) sessionFactory.getCurrentSession()
                .createCriteria(TipoSalario.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposSalario;
    }
    
	/**
	 * Inserta los datos de un {@link TipoSalario} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoSalario transientInstance) {
		log.debug("persisting TipoSalario instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoSalario} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoSalario instance) {
		log.debug("attaching dirty TipoSalario instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoSalario instance) {
		log.debug("attaching clean TipoSalario instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoSalario} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoSalario persistentInstance) {
		log.debug("deleting TipoSalario instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoSalario merge(TipoSalario detachedInstance) {
		log.debug("merging TipoSalario instance");
		try {
			TipoSalario result = (TipoSalario) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoSalario de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>TipoSalario</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoSalario findById(int id) {
		log.debug("getting TipoSalario instance with id: " + id);
		try {
			TipoSalario instance = (TipoSalario) sessionFactory
					.getCurrentSession().get("TipoSalario", id);
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
	public List findByExample(TipoSalario instance) {
		log.debug("finding TipoSalario instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoSalario")
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
