package ec.eac.sitac.dao;

import java.util.List;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Banco;

/**
 * Objeto Dao de la clase {@link Banco}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Banco
 * @since 1.0
 */

@Repository
public class BancoHome {
    private SessionFactory sessionFactory;
 
    public BancoHome() {
         
    }
 
	public BancoHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private static final Log log = LogFactory.getLog(BancoHome.class);

	/**
	 * Devuelve una lista de {@link Banco} de la Base de Datos.
	 *
	 * @return <code>List<Banco></code>.
	 *
	 * @since 1.0
	 */
   @Transactional
    public List<Banco> list() {
        @SuppressWarnings("unchecked")
        List<Banco> listaBancos = (List<Banco>) sessionFactory.getCurrentSession()
                .createCriteria(Banco.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaBancos;
    }
    
	/**
	 * Inserta los datos de un {@link Banco} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(Banco transientInstance) {
		log.debug("persisting Banco instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Banco} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(Banco instance) {
		log.debug("attaching dirty Banco instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Banco instance) {
		log.debug("attaching clean Banco instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Pais} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Banco persistentInstance) {
		log.debug("deleting Banco instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Banco merge(Banco detachedInstance) {
		log.debug("merging Banco instance");
		try {
			Banco result = (Banco) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Banco de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Banco</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public Banco findById(java.lang.Integer id) {
		log.debug("getting Banco instance with id: " + id);
		try {
			Banco instance = (Banco) sessionFactory.getCurrentSession().get(Banco.class, id);
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
	public List findByExample(Banco instance) {
		log.debug("finding Banco instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Pais").add(Example.create(instance))
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
