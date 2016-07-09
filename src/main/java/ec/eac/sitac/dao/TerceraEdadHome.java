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
import ec.eac.sitac.model.TerceraEdad;

/**
 * Objeto Dao de la clase {@link TerceraEdad}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see TerceraEdad
 * @since 1.0
 */

@Repository
public class TerceraEdadHome {
    private SessionFactory sessionFactory;
 
    public TerceraEdadHome() {
         
    }
 
	public TerceraEdadHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private static final Log log = LogFactory.getLog(TerceraEdadHome.class);

	/**
	 * Devuelve una lista de {@link TerceraEdad} de la Base de Datos.
	 *
	 * @return <code>List<TerceraEdad></code>.
	 *
	 * @since 1.0
	 */
   @Transactional
    public List<TerceraEdad> list() {
        @SuppressWarnings("unchecked")
        List<TerceraEdad> listaTerceraEdad = (List<TerceraEdad>) sessionFactory.getCurrentSession()
                .createCriteria(TerceraEdad.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTerceraEdad;
    }
    
	/**
	 * Inserta los datos de un {@link TerceraEdad} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(TerceraEdad transientInstance) {
		log.debug("persisting TerceraEdad instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TerceraEdad} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(TerceraEdad instance) {
		log.debug("attaching dirty TerceraEdad instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TerceraEdad instance) {
		log.debug("attaching clean TerceraEdad instance");
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
	public void delete(TerceraEdad persistentInstance) {
		log.debug("deleting TerceraEdad instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TerceraEdad merge(TerceraEdad detachedInstance) {
		log.debug("merging TerceraEdad instance");
		try {
			TerceraEdad result = (TerceraEdad) sessionFactory.getCurrentSession().merge(
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
	public TerceraEdad findById(java.lang.Integer id) {
		log.debug("getting TerceraEdad instance with id: " + id);
		try {
			TerceraEdad instance = (TerceraEdad) sessionFactory.getCurrentSession().get(TerceraEdad.class, id);
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
	public List findByExample(TerceraEdad instance) {
		log.debug("finding TerceraEdad instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TerceraEdad").add(Example.create(instance))
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
