// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.DocumentosAnulados;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.VentaVsProducto;

/**
 * Objeto Dao de la clase {@link VentaVsProducto}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Canton
 * @since 2015
 */
@Repository
public class VentaVsProductoHome {
    private SessionFactory sessionFactory;
 
    public VentaVsProductoHome() {
         
    }

	/**
	 * Constructor.
	 *
	 * @since 2015
	 */    
    public VentaVsProductoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(VentaVsProductoHome.class);
	
	/**
	 * Devuelve una lista de {@link VentaVsProducto} de la Base de Datos.
	 *
	 * @return <code>List<Canton></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<VentaVsProducto> list() {
        @SuppressWarnings("unchecked")
        List<VentaVsProducto> ventaVsProducto = (List<VentaVsProducto>) sessionFactory.getCurrentSession()
                .createCriteria(VentaVsProducto.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return ventaVsProducto;
    }
    
	/**
	 * Devuele un Producto de la Base de Datos, dado el codigo principal y el codigo auxiliar. 
	 *
	 * @return <code>VentaVsProducto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public List<VentaVsProducto> findByProductoId(int id) {
        String queryString = "SELECT v FROM VentaVsProducto v WHERE v.producto.idProducto = :id"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("id", id);
        
        List<VentaVsProducto> ventaVsproducto = (List<VentaVsProducto>) query.list();
 
        return ventaVsproducto;
	}

	/**
	 * Inserta  en la Base de Datos los datos correspondientes a la venta de un producto específico.
	 *
	 * @since 2015
	 */	
	@Transactional
	public void persist(VentaVsProducto transientInstance) {
		log.debug("persisting VentaVsProducto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta  en la Base de Datos los datos correspondientes a la venta de un producto específico, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(VentaVsProducto instance) {
		log.debug("attaching dirty VentaVsProducto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(VentaVsProducto instance) {
		log.debug("attaching clean VentaVsProducto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina de la Base de Datos la venta correspondiente a un producto. 
	 *
	 * @since 2015
	 */	
	@Transactional
	public void delete(VentaVsProducto persistentInstance) {
		log.debug("deleting VentaVsProducto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public VentaVsProducto merge(VentaVsProducto detachedInstance) {
		log.debug("merging VentaVsProducto instance");
		try {
			VentaVsProducto result = (VentaVsProducto) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una venta correspondiente a un producto de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>VentaVsProducto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public VentaVsProducto findById(int id) {
		log.debug("getting VentaVsProducto instance with id: " + id);
		try {
			VentaVsProducto instance = (VentaVsProducto) sessionFactory
					.getCurrentSession().get("VentaVsProducto", id);
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
	public List findByExample(VentaVsProducto instance) {
		log.debug("finding VentaVsProducto instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria(VentaVsProducto.class)
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
