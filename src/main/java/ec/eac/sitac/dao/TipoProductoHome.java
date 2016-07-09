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
import ec.eac.sitac.model.TipoProducto;

/**
 * Objeto Dao de la clase {@link TipoProducto}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoProducto
 * @since 2015
 */

@Repository
public class TipoProductoHome {
    private SessionFactory sessionFactory;
 
    public TipoProductoHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoProductoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoProductoHome.class);

	/**
	 * Devuelve una lista de {@link TipoProducto} de la Base de Datos.
	 *
	 * @return <code>List<TipoProducto></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoProducto> list() {
        @SuppressWarnings("unchecked")
        List<TipoProducto> listaTiposProducto = (List<TipoProducto>) sessionFactory.getCurrentSession()
                .createCriteria(TipoProducto.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposProducto;
    }
    
	/**
	 * Inserta los datos de un {@link TipoProducto} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoProducto transientInstance) {
		log.debug("persisting TipoProducto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoProducto} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoProducto instance) {
		log.debug("attaching dirty TipoProducto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoProducto instance) {
		log.debug("attaching clean TipoProducto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoProducto} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoProducto persistentInstance) {
		log.debug("deleting TipoProducto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoProducto merge(TipoProducto detachedInstance) {
		log.debug("merging TipoProducto instance");
		try {
			TipoProducto result = (TipoProducto) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoProducto de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>TipoProducto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoProducto findById(int id) {
		log.debug("getting TipoProducto instance with id: " + id);
		try {
			TipoProducto instance = (TipoProducto) sessionFactory
					.getCurrentSession().get("TipoProducto", id);
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
	public List findByExample(TipoProducto instance) {
		log.debug("finding TipoProducto instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoProducto")
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
