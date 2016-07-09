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
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Residencia;
import ec.eac.sitac.model.TipoAmbiente;

/**
 * Objeto Dao de la clase {@link Residencia}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Residencia
 * @since 2015
 */

@Repository
public class ResidenciaHome {
    private SessionFactory sessionFactory;
 
    public ResidenciaHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public ResidenciaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(ResidenciaHome.class);

	/**
	 * Devuelve una lista de {@link Residencia} de la Base de Datos.
	 *
	 * @return <code>List<Residencia></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<Residencia> list() {
        @SuppressWarnings("unchecked")
        List<Residencia> listaResidencias = (List<Residencia>) sessionFactory.getCurrentSession()
                .createCriteria(Residencia.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaResidencias;
    }
  
	/**
	 * Inserta los datos de una {@link Residencia} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Residencia transientInstance) {
		log.debug("persisting Residencia instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de una {@link Residencia} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Residencia instance) {
		log.debug("attaching dirty Residencia instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Residencia instance) {
		log.debug("attaching clean Residencia instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina una {@link Residencia} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Residencia persistentInstance) {
		log.debug("deleting Residencia instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Residencia merge(Residencia detachedInstance) {
		log.debug("merging Residencia instance");
		try {
			Residencia result = (Residencia) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Residencia de la Base de Datos, dado el Id. 
	 *
	 * @return <code>Residencia</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Residencia findById(int id) {
		log.debug("getting Residencia instance with id: " + id);
		try {
			Residencia instance = (Residencia) sessionFactory
					.getCurrentSession().get("Residencia", id);
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
	public List findByExample(Residencia instance) {
		log.debug("finding Residencia instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Residencia").add(Example.create(instance))
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
