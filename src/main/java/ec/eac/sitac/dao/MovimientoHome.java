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
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.Irpnr;
import ec.eac.sitac.model.Movimiento;
import ec.eac.sitac.model.PersonalEmpresa;

/**
 * Objeto Dao de la clase {@link Movimiento}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Movimiento
 * @since 2015
 */

@Repository
public class MovimientoHome {
    private SessionFactory sessionFactory;
 
    public MovimientoHome() {
         
    }
 
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public MovimientoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(MovimientoHome.class);

	/**
	 * Devuelve una lista de {@link Movimiento} de la Base de Datos.
	 *
	 * @return <code>List<Movimiento></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Movimiento> list() {
        @SuppressWarnings("unchecked")
        List<Movimiento> listaMovimientos = (List<Movimiento>) sessionFactory.getCurrentSession()
                .createCriteria(Movimiento.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaMovimientos;
    }
    
	/**
	 * Devuelve una lista de {@link Movimiento} de la Base de Datos.
	 *
	 * @return <code>List<Movimiento></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Movimiento> list(int idEmpresa) {
       String queryString = "SELECT m FROM Movimiento m WHERE m.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setParameter("idEmpresa", idEmpresa).setMaxResults(300);
        
        List<Movimiento> listaMovimiento = query.list();
 
        return listaMovimiento;
    }
    
	/**
	 * Devuelve una lista de {@link Movimiento} de la Base de Datos.
	 *
	 * @return <code>List<Movimiento></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Movimiento> list(int idEmpresa, int anno, int mes) {
       String queryString = "SELECT m FROM Movimiento m WHERE m.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM m.date) = :anno AND EXTRACT(MONTH FROM m.date) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setParameter("idEmpresa", idEmpresa).setParameter("anno", anno).setParameter("mes", mes);
        
        List<Movimiento> listaMovimiento = query.list();
 
        return listaMovimiento;
    }
    
	/**
	 * Inserta los datos de un {@link Movimiento} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Movimiento movimiento, String idTrabajador, int idEmpresa) {
		log.debug("persisting Movimiento instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			PersonalEmpresa trabajadorExistente = (PersonalEmpresa) session.get(PersonalEmpresa.class, idTrabajador);
			movimiento.setPersonalEmpresa(trabajadorExistente);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			movimiento.setEmpresa(empresa);
			
			session.persist(movimiento);
			
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Movimiento} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Movimiento movimiento, String idTrabajador, int idEmpresa) {
		log.debug("attaching dirty Movimiento instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			PersonalEmpresa trabajadorExistente = (PersonalEmpresa) session.get(PersonalEmpresa.class, idTrabajador);
			movimiento.setPersonalEmpresa(trabajadorExistente);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			movimiento.setEmpresa(empresa);
			
			session.saveOrUpdate(movimiento);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Movimiento instance) {
		log.debug("attaching clean Movimiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Movimiento} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Movimiento persistentInstance) {
		log.debug("deleting Movimiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Movimiento merge(Movimiento detachedInstance) {
		log.debug("merging Movimiento instance");
		try {
			Movimiento result = (Movimiento) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Movimiento de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Movimiento</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Movimiento findById(int idMovimiento) {
		log.debug("getting Movimiento instance with idMovimiento: " + idMovimiento);
		try {
			Movimiento instance = (Movimiento) sessionFactory
					.getCurrentSession().get(Movimiento.class, idMovimiento);
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
	 * Devuele un Movimiento de la Base de Datos, dado el Id del trabajador, el mes y el año. 
	 *
	 * @return Un objeto de tipo <code>Movimiento</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public Movimiento findByIdTrabajador(String idTrabajador, int mes, int anno) {
        String queryString = "SELECT c FROM Movimiento c WHERE c.personalEmpresa.id = :idTrabajador AND EXTRACT(YEAR FROM c.date) = :anno AND EXTRACT(MONTH FROM c.date) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        query.setParameter("idTrabajador", idTrabajador);
        query.setParameter("anno", anno);
        query.setParameter("mes", mes);
        
        Movimiento movimiento = (Movimiento) query.uniqueResult();
 
        return movimiento;
	}
	
	/**
	 * Devuele un Movimiento de la Base de Datos, dado el Id del trabajador. 
	 *
	 * @return Un objeto de tipo <code>Movimiento</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public List<Movimiento> findByIdTrabajador(String idTrabajador) {
        String queryString = "SELECT c FROM Movimiento c WHERE c.personalEmpresa.id = :idTrabajador"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        query.setParameter("idTrabajador", idTrabajador);
        
        List<Movimiento> movimiento = (List<Movimiento>) query.list();
 
        return movimiento;
	}

	@Transactional
	public List findByExample(Movimiento instance) {
		log.debug("finding Movimiento instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Movimiento").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Devuelve la cantidad de importaciones que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM Movimiento c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    
	/**
	 * Devuelve la suma de baseImponible de todos los movimientos de un trabajador 
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public double sumBaseImponibleTrabajador(int idEmpresa, String idTrabajador) {
        String queryString = "SELECT sum(c.baseImponibleGravada) FROM Movimiento c WHERE c.empresa.idEmpresa = :idEmpresa AND c.personalEmpresa.id = :idTrabajador"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa).setParameter("idTrabajador", idTrabajador);
        
        double suma = (Double) query.uniqueResult();
 
        return suma;
    }
    
	/**
	 * Devuelve la suma de valorRetenido de todos los movimientos de un trabajador 
	 * Se suman todos los impuestoRentaCausado para calcular el valorRetenido
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public double sumValorRetenidoTrabajador(int idEmpresa, String idTrabajador) {
        String queryString = "SELECT sum(c.impuestoRentaCausado) FROM Movimiento c WHERE c.empresa.idEmpresa = :idEmpresa AND c.personalEmpresa.id = :idTrabajador"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa).setParameter("idTrabajador", idTrabajador);
        
        double suma = (Double) query.uniqueResult();
 
        return suma;
    }
}
