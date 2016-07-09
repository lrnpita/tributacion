// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.TipoTransaccion;

/**
 * Objeto Dao de la clase {@link DetallesCompra}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see DetallesCompra
 * @since 2015
 */

@Repository
public class DetallesCompraHome {
    private SessionFactory sessionFactory;
    
    public DetallesCompraHome() {
        
    }
  
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public DetallesCompraHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(DetallesCompraHome.class);

	/**
	 * Inserta en la Base de Datos los detalles {@link DetallesCompra} de una {@link Compra} dada.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(int idCompra, int idTipoTransaccion, DetallesCompra transientInstance) {
		log.debug("persisting DetallesCompra instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			TipoTransaccion tipoTransaccionExistente = (TipoTransaccion) session.get(TipoTransaccion.class, idTipoTransaccion);
			transientInstance.setTipoTransaccion(tipoTransaccionExistente);
			
			Compra compraExistente = (Compra) session.get(Compra.class, idCompra);
			compraExistente.getDetallesCompras().add(transientInstance);
			
			session.persist(compraExistente);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Inserta en la Base de Datos los detalles {@link DetallesCompra} de una {@link Compra} dada, 
	 * en caso de existir algún detalle, existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(DetallesCompra instance) {
		log.debug("attaching dirty DetallesCompra instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(DetallesCompra instance) {
		log.debug("attaching clean DetallesCompra instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link DetallesCompra} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(DetallesCompra persistentInstance) { // FIXME eliminar un detalle dado una compra
		log.debug("deleting DetallesCompra instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public DetallesCompra merge(DetallesCompra detachedInstance) {
		log.debug("merging DetallesCompra instance");
		try {
			DetallesCompra result = (DetallesCompra) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Detalle de la Base de Datos, dado el Id del detalle. 
	 *
	 * @return Un objeto de tipo <code>DetallesCompra</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public DetallesCompra findById(int id) {
		log.debug("getting DetallesCompra instance with id: " + id);
		try {
			DetallesCompra instance = (DetallesCompra) sessionFactory
					.getCurrentSession().get(DetallesCompra.class, id);
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
	public List findByExample(DetallesCompra instance) {
		log.debug("finding DetallesCompra instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria(DetallesCompra.class)
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
