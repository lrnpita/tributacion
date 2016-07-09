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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.TipoCompra;
import ec.eac.sitac.model.TipoComprobante;

/**
 * Objeto Dao de la clase {@link TipoComprobanteHome}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see TipoComprobanteHome
 * @since 2015
 */
@Repository
public class TipoComprobanteHome {
    private SessionFactory sessionFactory;
 
    public TipoComprobanteHome() {
         
    }
     
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public TipoComprobanteHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(TipoComprobanteHome.class);


	/**
	 * Devuelve una lista de los {@link TipoComprobante} de la Base de Datos que se muestran en la vista Exportación.
	 *
	 * @return <code>List<TipoComprobante></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoComprobante> listForVistaExportacion() {
        @SuppressWarnings("unchecked")
        List<TipoComprobante> listaTiposComprobante = (List<TipoComprobante>) sessionFactory.getCurrentSession()
                .createCriteria(TipoComprobante.class)
                .addOrder(Order.asc("codigo"))
                .add(Restrictions.eq("vistaExportacion", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposComprobante;
    }
    
	/**
	 * Devuelve una lista de los {@link TipoComprobante} de la Base de Datos que se muestran en la vista Importación.
	 *
	 * @return <code>List<TipoComprobante></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoComprobante> listForVistaImportacion() {
        @SuppressWarnings("unchecked")
        List<TipoComprobante> listaTiposComprobante = (List<TipoComprobante>) sessionFactory.getCurrentSession()
                .createCriteria(TipoComprobante.class)
                .addOrder(Order.asc("codigo"))
                .add(Restrictions.eq("vistaImportacion", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposComprobante;
    }
    
	/**
	 * Devuelve una lista de los {@link TipoComprobante} de la Base de Datos que se muestran en la vista Venta.
	 *
	 * @return <code>List<TipoComprobante></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoComprobante> listForVistaVenta() {
        @SuppressWarnings("unchecked")
        List<TipoComprobante> listaTiposComprobante = (List<TipoComprobante>) sessionFactory.getCurrentSession()
                .createCriteria(TipoComprobante.class)
                .addOrder(Order.asc("codigo"))
                .add(Restrictions.eq("vistaVenta", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposComprobante;
    }
    
	/**
	 * Devuelve una lista de los {@link TipoComprobante} de la Base de Datos que se muestran en la vista Compra.
	 *
	 * @return <code>List<TipoComprobante></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoComprobante> listForVistaCompra() {
        @SuppressWarnings("unchecked")
        List<TipoComprobante> listaTiposComprobante = (List<TipoComprobante>) sessionFactory.getCurrentSession()
                .createCriteria(TipoComprobante.class)
                .addOrder(Order.asc("codigo"))
                .add(Restrictions.eq("vistaCompra", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposComprobante;
    }
    
	/**
	 * Devuelve una lista de los {@link TipoComprobante} de la Base de Datos.
	 *
	 * @return <code>List<TipoComprobante></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<TipoComprobante> listForVistaAnulados() {
        @SuppressWarnings("unchecked")
        List<TipoComprobante> listaTiposComprobante = (List<TipoComprobante>) sessionFactory.getCurrentSession()
                .createCriteria(TipoComprobante.class)
                .addOrder(Order.asc("codigo"))
                 .add(Restrictions.eq("vistaAnulados", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaTiposComprobante;
    }
    
	/**
	 * Inserta los datos de un {@link TipoComprobante} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(TipoComprobante transientInstance) {
		log.debug("persisting TipoComprobante instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link TipoComprobante} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(TipoComprobante instance) {
		log.debug("attaching dirty TipoComprobante instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(TipoComprobante instance) {
		log.debug("attaching clean TipoComprobante instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link TipoComprobante} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(TipoComprobante persistentInstance) {
		log.debug("deleting TipoComprobante instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoComprobante merge(TipoComprobante detachedInstance) {
		log.debug("merging TipoComprobante instance");
		try {
			TipoComprobante result = (TipoComprobante) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un TipoComprobante de la Base de Datos, dado el Id. 
	 *
	 * @return <code>TipoComprobante</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public TipoComprobante findById(java.lang.String id) {
		log.debug("getting TipoComprobante instance with id: " + id);
		try {
			TipoComprobante instance = (TipoComprobante) sessionFactory
					.getCurrentSession().get(TipoComprobante.class, id);
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
	public List findByExample(TipoComprobante instance) {
		log.debug("finding TipoComprobante instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("TipoComprobante")
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
