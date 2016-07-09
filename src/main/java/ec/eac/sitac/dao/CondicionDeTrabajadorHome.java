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
import ec.eac.sitac.model.CondicionDeTrabajador;
import ec.eac.sitac.model.Pais;

/**
 * Objeto Dao de la clase {@link CondicionDeTrabajador}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see CondicionDeTrabajador
 * @since 2015
 */
@Repository
public class CondicionDeTrabajadorHome {
    private SessionFactory sessionFactory;
    
    public CondicionDeTrabajadorHome() {
        
    }
     
    public CondicionDeTrabajadorHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
	private static final Log log = LogFactory
			.getLog(CondicionDeTrabajadorHome.class);

	/**
	 * Devuelve una lista de {@link CondicionDeTrabajador} de la Base de Datos.
	 *
	 * @return <code>List<CondicionDeTrabajador></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<CondicionDeTrabajador> list() {
        @SuppressWarnings("unchecked")
        List<CondicionDeTrabajador> listaCondicionDeTrabajador = (List<CondicionDeTrabajador>) sessionFactory.getCurrentSession()
                .createCriteria(CondicionDeTrabajador.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaCondicionDeTrabajador;
    }
    
	/**
	 * Inserta los datos de {@link CondicionDeTrabajador} en la Base de Datos .
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(CondicionDeTrabajador transientInstance) {
		log.debug("persisting CondicionDeTrabajador instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de {@link CondicionDeTrabajador} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(CondicionDeTrabajador instance) {
		log.debug("attaching dirty CondicionDeTrabajador instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(CondicionDeTrabajador instance) {
		log.debug("attaching clean CondicionDeTrabajador instance");
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
	public void delete(CondicionDeTrabajador persistentInstance) {
		log.debug("deleting CondicionDeTrabajador instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public CondicionDeTrabajador merge(CondicionDeTrabajador detachedInstance) {
		log.debug("merging CondicionDeTrabajador instance");
		try {
			CondicionDeTrabajador result = (CondicionDeTrabajador) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele la condición de un trabajador de la Base de Datos, dado el Id de la condición. 
	 *
	 * @return Un objeto de tipo <code>CondicionDeTrabajador</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public CondicionDeTrabajador findById(int id) {
		log.debug("getting CondicionDeTrabajador instance with id: " + id);
		try {
			CondicionDeTrabajador instance = (CondicionDeTrabajador) sessionFactory
					.getCurrentSession().get("CondicionDeTrabajador", id);
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
	public List findByExample(CondicionDeTrabajador instance) {
		log.debug("finding CondicionDeTrabajador instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("CondicionDeTrabajador")
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
