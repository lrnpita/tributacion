// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import com.google.common.collect.Lists;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.TipoIdentificacion;

/**
 * Objeto Dao de la clase {@link TipoIdentificacion}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoIdentificacion
 * @since 2015
 */
@Repository
public class TipoIdentificacionHome {
    private SessionFactory sessionFactory;
 
    public TipoIdentificacionHome() {
         
    }
   
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoIdentificacionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(TipoIdentificacionHome.class);

	/**
	 * Devuelve una lista de {@link TipoIdentificacion} de la Base de Datos.
	 *
	 * @return <code>List<TipoIdentificacion></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoIdentificacion> list() {
        @SuppressWarnings("unchecked")
        List<TipoIdentificacion> listTipoIdentificacion = (List<TipoIdentificacion>) sessionFactory.getCurrentSession()
                .createCriteria(TipoIdentificacion.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listTipoIdentificacion;
    }
    
	/**
	 * Devuelve una lista de {@link TipoIdentificacion}, excepto el consumidor final.
	 *
	 * @return <code>List<TipoIdentificacion></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoIdentificacion> listExcluyeConsumidorFinal() {
    	List<TipoIdentificacion> lstExcluyeConsumidorFinal = Lists.newArrayList(list());
    	lstExcluyeConsumidorFinal.removeIf(p -> p.getCodigo().equals("07"));
    	
    	return lstExcluyeConsumidorFinal;
    }
    
	/**
	 * Inserta los datos de un {@link TipoIdentificacion} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoIdentificacion transientInstance) {
		log.debug("persisting TipoIdentificacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoIdentificacion} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoIdentificacion instance) {
		log.debug("attaching dirty TipoIdentificacion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoIdentificacion instance) {
		log.debug("attaching clean TipoIdentificacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoIdentificacion} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoIdentificacion persistentInstance) {
		log.debug("deleting TipoIdentificacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoIdentificacion merge(TipoIdentificacion detachedInstance) {
		log.debug("merging TipoIdentificacion instance");
		try {
			TipoIdentificacion result = (TipoIdentificacion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoIdentificacion de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoIdentificacion</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoIdentificacion findById(java.lang.String id) {
		log.debug("getting TipoIdentificacion instance with id: " + id);
		try {
			TipoIdentificacion instance = (TipoIdentificacion) sessionFactory
					.getCurrentSession().get(TipoIdentificacion.class, id);
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
	public List findByExample(TipoIdentificacion instance) {
		log.debug("finding TipoIdentificacion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoIdentificacion")
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
