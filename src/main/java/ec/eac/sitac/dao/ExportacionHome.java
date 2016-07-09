// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.runtime.internal.PerObjectMap;
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
import ec.eac.sitac.model.Cargo;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoExportacionImportacion;
import ec.eac.sitac.model.TipoProveedorOCliente;

/**
 * Objeto Dao de la clase {@link Exportacion}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Exportacion
 * @since 1.0
 */

@Repository
public class ExportacionHome {
    private SessionFactory sessionFactory;
 
    public ExportacionHome() {
         
    }
   
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public ExportacionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(ExportacionHome.class);
	
	/**
	 * Devuelve una lista de {@link Exportacion} de la Base de Datos.
	 *
	 * @return <code>List<Canton></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Exportacion> list() {
        @SuppressWarnings("unchecked")
        List<Exportacion> listaExportaciones = (List<Exportacion>) sessionFactory.getCurrentSession()
                .createCriteria(Exportacion.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaExportaciones;
    }
    
	/**
	 * Devuelve una lista de {@link Exportacion} de la Base de Datos dado el id de la Empresa a la que pertenecen.
	 *
	 * @return <code>List<Canton></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Exportacion> list(int idEmpresa) {
    	Session session = sessionFactory.getCurrentSession(); 
    	
    	org.hibernate.Query query = session.createQuery("SELECT e FROM Exportacion e WHERE id_empresa = " + idEmpresa);
    	List<Exportacion> list = query.list();
 
        return list;
    }
    
	/**
	 * Devuelve una lista de {@link Exportacion} de la Base de Datos dado el id de una Empresa y una fecha.
	 *
	 * @return <code>List<Canton></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Exportacion> list(int idEmpresa, int anno, int mes) {
    	String queryString = "SELECT e FROM Exportacion e WHERE e.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM e.fechaRegistroContable) = :anno AND EXTRACT(MONTH FROM e.fechaRegistroContable) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query query = session.createQuery(queryString);
        query.setParameter("idEmpresa", idEmpresa);
        query.setParameter("anno", anno);
        query.setParameter("mes", mes);
        
        List<Exportacion> listaExportaciones = query.list();
 
        return listaExportaciones;
 
    }
    
	/**
	 * Inserta los datos de una {@link Exportacion} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(int idTipoExportacionImportacion, String idTipoComprobante, int idEmpresa, Exportacion exportacion) {
		log.debug("persisting Exportacion instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			prepararObjetoExportacion(idTipoExportacionImportacion, idTipoComprobante, idEmpresa, exportacion);
			
			session.persist(exportacion);

			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Se llena el objeto Exportación.
	 *
	 * @since 2015
	 */
	private void prepararObjetoExportacion(int idTipoExportacionImportacion, String tipoComprobante, int idEmpresa, Exportacion exportacion) {
		log.debug("persisting Exportacion instance");
		
		Session session = sessionFactory.getCurrentSession();

		TipoExportacionImportacion tipoExportacionImportacionExistente = (TipoExportacionImportacion) session.get(TipoExportacionImportacion.class, idTipoExportacionImportacion);
		exportacion.setTipoExportacionImportacion(tipoExportacionImportacionExistente);
		
		TipoComprobante tipoComprobanteExistente = (TipoComprobante) session.get(TipoComprobante.class, tipoComprobante);
		exportacion.setTipoComprobante(tipoComprobanteExistente);
		
		Empresa empresaExistente = (Empresa) session.get(Empresa.class, idEmpresa);
		exportacion.setEmpresa(empresaExistente);
	}

	/**
	 * Inserta los datos de una {@link Exportacion} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(int idTipoExportacionImportacion, String idTipoComprobante, int idEmpresa, Exportacion exportacion) {
		log.debug("attaching dirty Exportacion instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			prepararObjetoExportacion(idTipoExportacionImportacion, idTipoComprobante, idEmpresa, exportacion);
			
			session.saveOrUpdate(exportacion);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Exportacion instance) {
		log.debug("attaching clean Exportacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina una {@link Empresa} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Exportacion persistentInstance) {
		log.debug("deleting Exportacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Exportacion merge(Exportacion detachedInstance) {
		log.debug("merging Exportacion instance");
		try {
			Exportacion result = (Exportacion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Exportacion de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Exportacion</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Exportacion findById(int id) {
		log.debug("getting Exportacion instance with idExportacion: " + id);
		try {
			Exportacion instance = (Exportacion) sessionFactory
					.getCurrentSession().get(Exportacion.class, id);
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
	public List findByExample(Exportacion instance) {
		log.debug("finding Exportacion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Exportacion")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Devuelve la cantidad de exportaciones que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM Exportacion c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
}
