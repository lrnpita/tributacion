package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.EstadoSRI;
import ec.eac.sitac.model.TipoAmbiente;

/**
 * Objeto Dao de la clase {@link TipoAmbiente}
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoAmbiente
 * @since 1.0
 */

@Repository
public class EstadoSRIHome {
    private SessionFactory sessionFactory;
 
    public EstadoSRIHome() {
         
    }
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */     
    public EstadoSRIHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoAmbienteHome.class);

	/**
	 * Devuelve una lista de {@link EstadoSRI} de la Base de Datos.
	 *
	 * @return <code>List<EstadoSRI></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<EstadoSRI> list() {
        @SuppressWarnings("unchecked")
        List<EstadoSRI> listaEstadosSRI = (List<EstadoSRI>) sessionFactory.getCurrentSession()
                .createCriteria(EstadoSRI.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaEstadosSRI;
    }
   
	/**
	 * Inserta los datos de un {@link EstadoSRI} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(EstadoSRI transientInstance) {
		log.debug("persisting EstadoSRI instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link EstadoSRI} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(EstadoSRI instance) {
		log.debug("attaching dirty EstadoSRI instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(EstadoSRI instance) {
		log.debug("attaching clean EstadoSRI instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link EstadoSRI} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(EstadoSRI persistentInstance) {
		log.debug("deleting EstadoSRI instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@Transactional
	public EstadoSRI merge(EstadoSRI detachedInstance) {
		log.debug("merging EstadoSRI instance");
		try {
			EstadoSRI result = (EstadoSRI) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un EstadoSRI de la Base de Datos, dado el Id. 
	 *
	 * @param id
	 * @return <code>EstadoSRI</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public EstadoSRI findById(int id) {
		log.debug("getting EstadoSRI instance with id: " + id);
		try {
			EstadoSRI instance = (EstadoSRI) sessionFactory
					.getCurrentSession().get(EstadoSRI.class, id);
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
	
	/**
	 * Devuele un EstadoSRI de la Base de Datos, dado el nombre. 
	 *
	 * @param name
	 * @return <code>EstadoSRI</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public EstadoSRI findByName(String name) {
		log.debug("getting EstadoSRI instance with nombre: " + name);
		EstadoSRI estado = null;
		try {
			String queryString = "SELECT e FROM EstadoSRI e WHERE LOWER(e.nombre) = :nombre"; 

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(queryString)
					.setParameter("nombre", name.toLowerCase());

			estado = (EstadoSRI)query.uniqueResult();

			if (estado == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return estado;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}