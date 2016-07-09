// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.IdentificacionCreditoGasto;
import ec.eac.sitac.model.Importacion;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoExportacionImportacion;
import ec.eac.sitac.model.TipoTransaccion;

/**
 * Objeto Dao de la clase {@link Importacion}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Importacion
 * @since 2015
 */

@Repository
public class ImportacionHome {
    private SessionFactory sessionFactory;
 
    public ImportacionHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public ImportacionHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(ImportacionHome.class);

	/**
	 * Devuelve una lista de {@link Importacion} de la Base de Datos.
	 *
	 * @return <code>List<Importacion></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Importacion> list(int idEmpresa) {
    	Session session = sessionFactory.getCurrentSession(); 
    	
    	org.hibernate.Query query = session.createQuery("SELECT e FROM Importacion e WHERE id_empresa = " + idEmpresa);
    	query.setMaxResults(300);
    	List<Importacion> list = query.list();
 
        return list;
    }
    
	/**
	 * Devuelve una lista de {@link Importacion} de la Base de Datos dado el id de la empresa y una fecha.
	 *
	 * @param idEmpresa
	 * @return <code>List<Importacion></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Importacion> list(int idEmpresa, int anno, int mes) {
        String queryString = "SELECT c FROM Importacion c WHERE c.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM c.fechaLiquidacion) = :anno AND EXTRACT(MONTH FROM c.fechaLiquidacion) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        query.setParameter("idEmpresa", idEmpresa);
        query.setParameter("anno", anno);
        query.setParameter("mes", mes);
        
        List<Importacion> listaImportacion = query.list();
 
        return listaImportacion;
    }
    
	/**
	 * Inserta los datos de una {@link Importacion} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(int tipoImportacion, String idTipoComprobante, String idTipoTransaccion, String idCreditoGasto, int idEmpresa, Importacion importacion) {
		log.debug("persisting Importacion instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			prepararObjetoImportacion(tipoImportacion, idTipoComprobante, idTipoTransaccion, idCreditoGasto, idEmpresa, importacion);
			
			session.persist(importacion);

			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Se llena el objeto Importación.
	 *
	 * @since 2015
	 */
	@Transactional
	private void prepararObjetoImportacion( int idTipoExportacionImportacion, String idtipoComprobante,String idTipoTransaccion, String idCreditoGasto, int idEmpresa, Importacion importacion) {
		log.debug("persisting Importacion instance");
		
		Session session = sessionFactory.getCurrentSession();
		
		TipoExportacionImportacion tipoExportacionImportacionExistente = (TipoExportacionImportacion) session.get(TipoExportacionImportacion.class, idTipoExportacionImportacion);
		importacion.setTipoExportacionImportacion(tipoExportacionImportacionExistente);
		
		TipoComprobante tipoComprobanteExistente = (TipoComprobante) session.get(TipoComprobante.class, idtipoComprobante);
		importacion.setTipoComprobante(tipoComprobanteExistente);
		
		TipoTransaccion tipoTransaccionExistente = (TipoTransaccion) session.get(TipoTransaccion.class, idTipoTransaccion);
		importacion.setTipoTransaccion(tipoTransaccionExistente);
		
		IdentificacionCreditoGasto idCreditogastoExistente = (IdentificacionCreditoGasto) session.get(IdentificacionCreditoGasto.class, idCreditoGasto);
		importacion.setIdentificacionCreditoGasto(idCreditogastoExistente);		
		
		Empresa empresaExistente = (Empresa) session.get(Empresa.class, idEmpresa);
		importacion.setEmpresa(empresaExistente);
	}

	/**
	 * Inserta los datos de una {@link Importacion} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(int tipoImportacion, String idTipoComprobante, String idTipoTransaccion, String idCreditoGasto, int idEmpresa, Importacion importacion) {
		log.debug("attaching dirty Importacion instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			prepararObjetoImportacion(tipoImportacion, idTipoComprobante, idTipoTransaccion, idCreditoGasto, idEmpresa, importacion);
			
			session.saveOrUpdate(importacion);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Importacion instance) {
		log.debug("attaching clean Importacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina una {@link Importacion} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Importacion persistentInstance) {
		log.debug("deleting Importacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Importacion merge(Importacion detachedInstance) {
		log.debug("merging Importacion instance");
		try {
			Importacion result = (Importacion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Importacion de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Importacion</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Importacion findById(int id) {
		log.debug("getting Importacion instance with id: " + id);
		try {
			Importacion instance = (Importacion) sessionFactory
					.getCurrentSession().get(Importacion.class, id);
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
	public List findByExample(Importacion instance) {
		log.debug("finding Importacion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Importacion")
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
	 * Devuelve la cantidad de importaciones que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM Importacion c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
}
