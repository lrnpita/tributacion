// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.sql.SQLException;
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
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Provincia;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.TipoEmision;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.util.PersonalEmpresaEnum;

/**
 * Objeto Dao de la clase {@link PuntoEmision}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see PuntoEmision
 * @since 2015
 */

@Repository
public class PuntoEmisionHome {
    private SessionFactory sessionFactory;
 
    public PuntoEmisionHome() {
         
    }
 
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public PuntoEmisionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(PuntoEmisionHome.class);

	/**
	 * Devuelve una lista de {@link PuntoEmision} de la Base de Datos.
	 *
	 * @return <code>List<PuntoEmision></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<PuntoEmision> list(int idEmpresa) {
    	String consulta = "SELECT p FROM PuntoEmision p where p.empresa = " + idEmpresa;
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery(consulta);
    	
    	List<PuntoEmision> list = query.list();
 
        return list;
    }
    
	/**
	 * Inserta los datos de un {@link PuntoEmision} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(PuntoEmision transientInstance) {
		log.debug("persisting PuntoEmision instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link PuntoEmision} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(PuntoEmision instance) {
		log.debug("attaching dirty PuntoEmision instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(PuntoEmision instance) {
		log.debug("attaching clean PuntoEmision instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link PuntoEmision} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(PuntoEmision persistentInstance) throws SQLException{
		log.debug("deleting PuntoEmision instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public PuntoEmision merge(PuntoEmision detachedInstance) {
		log.debug("merging PuntoEmision instance");
		try {
			PuntoEmision result = (PuntoEmision) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un PuntoEmision de la Base de Datos, dado el Id. 
	 *
	 * @return <code>PuntoEmision</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public PuntoEmision findById(int id) {
		log.debug("getting PuntoEmision instance with id: " + id);
		try {
			PuntoEmision instance = (PuntoEmision) sessionFactory
					.getCurrentSession().get(PuntoEmision.class, id);
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
	 * Devuele un PuntoEmision de la Base de Datos, dado el id del Usuario que lo utiliza y la empresa del Usuario. 
	 *
	 * @return <code>PuntoEmision</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public PuntoEmision findByUserIdAndEnterpriseId(int idUsuario, int idEmpresa) {
    	String consulta = "SELECT * FROM punto_emision pe WHERE pe.id_usuario = :idUsuario AND pe.id_empresa = :idEmpresa";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createSQLQuery(consulta)
    			.addEntity(PuntoEmision.class)
    			.setParameter("idUsuario", idUsuario)
    			.setParameter("idEmpresa", idEmpresa);
    	
    	PuntoEmision pto = (PuntoEmision) query.uniqueResult();
    	return pto;
	}
	
	/**
	 * Devuele un PuntoEmision de la Base de Datos, dada la serie y la empresa. 
	 *
	 * @return <code>PuntoEmision</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public PuntoEmision findBySerieAndEnterpriseId(String serie, int idEmpresa) {
    	String consulta = "SELECT * FROM punto_emision pe WHERE pe.serie = :serie AND pe.id_empresa = :idEmpresa";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createSQLQuery(consulta)
    			.addEntity(PuntoEmision.class)
    			.setParameter("serie", serie)
    			.setParameter("idEmpresa", idEmpresa);
    	
    	PuntoEmision pto = (PuntoEmision) query.uniqueResult();
    	return pto;
	}

	@Transactional
	public List findByExample(PuntoEmision instance) {
		log.debug("finding PuntoEmision instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("PuntoEmision")
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
