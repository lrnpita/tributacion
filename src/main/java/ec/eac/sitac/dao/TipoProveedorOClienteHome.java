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
import ec.eac.sitac.model.TipoProducto;
import ec.eac.sitac.model.TipoProveedorOCliente;

/**
 * Objeto Dao de la clase {@link TipoProveedorOCliente}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoProveedorOCliente
 * @since 2015
 */
@Repository
public class TipoProveedorOClienteHome {
    private SessionFactory sessionFactory;
 
    public TipoProveedorOClienteHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoProveedorOClienteHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory
			.getLog(TipoProveedorOClienteHome.class);

	/**
	 * Devuelve una lista de los {@link TipoProveedorOCliente} de la Base de Datos.
	 *
	 * @return <code>List<TipoProveedorOCliente></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoProveedorOCliente> list() {
        @SuppressWarnings("unchecked")
        List<TipoProveedorOCliente> listaTiposProveedorOCliente = (List<TipoProveedorOCliente>) sessionFactory.getCurrentSession()
                .createCriteria(TipoProveedorOCliente.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposProveedorOCliente;
    }
    
	/**
	 * Inserta los datos de un {@link TipoProveedorOCliente} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoProveedorOCliente transientInstance) {
		log.debug("persisting TipoProveedorOCliente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoProveedorOCliente} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoProveedorOCliente instance) {
		log.debug("attaching dirty TipoProveedorOCliente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoProveedorOCliente instance) {
		log.debug("attaching clean TipoProveedorOCliente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoProveedorOCliente} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoProveedorOCliente persistentInstance) {
		log.debug("deleting TipoProveedorOCliente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoProveedorOCliente merge(TipoProveedorOCliente detachedInstance) {
		log.debug("merging TipoProveedorOCliente instance");
		try {
			TipoProveedorOCliente result = (TipoProveedorOCliente) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoProveedorOCliente de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>TipoProveedorOCliente</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoProveedorOCliente findById(int id) {
		log.debug("getting TipoProveedorOCliente instance with id: " + id);
		try {
			TipoProveedorOCliente instance = (TipoProveedorOCliente) sessionFactory
					.getCurrentSession().get("TipoProveedorOCliente", id);
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
	public List findByExample(TipoProveedorOCliente instance) {
		log.debug("finding TipoProveedorOCliente instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoProveedorOCliente")
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
