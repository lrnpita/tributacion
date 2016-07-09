package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DocumentosAnulados;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Importacion;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.Venta;

/**
 * Objeto Dao de la clase {@link DocumentosAnulados}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see DocumentosAnulados
 * @since 1.0
 */
@Repository
public class DocumentosAnuladosHome {
	private SessionFactory sessionFactory;

	public DocumentosAnuladosHome() {
		super();
	}

	public DocumentosAnuladosHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	private static final Log log = LogFactory.getLog(DocumentosAnuladosHome.class);
	
	/**
	 * Devuelve una lista de {@link DocumentosAnulados} de la Base de Datos.
	 *
	 * @return <code>List<Canton></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<DocumentosAnulados> list() {
        @SuppressWarnings("unchecked")
        List<DocumentosAnulados> listaAnulados = (List<DocumentosAnulados>) sessionFactory.getCurrentSession()
                .createCriteria(DocumentosAnulados.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaAnulados;
    }
    
	/**
	 * Devuelve una lista de {@link DocumentosAnulados} de la Base de Datos dado el id de la empresa.
	 *
	 * @param idEmpresa
	 * @return <code>List<DocumentosAnulados></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<DocumentosAnulados> list(int idEmpresa) {
        String queryString = "SELECT c FROM DocumentosAnulados c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setParameter("idEmpresa", idEmpresa).setMaxResults(200);
        
        List<DocumentosAnulados> listaAnulados = query.list();
 
        return listaAnulados;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id empresa y una fecha
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<DocumentosAnulados> list(int idEmpresa, int anno, int mes) {
    	 String queryString = "SELECT c FROM DocumentosAnulados c WHERE c.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM c.fechaAnulacion) = :anno AND EXTRACT(MONTH FROM c.fechaAnulacion) = :mes"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("idEmpresa", idEmpresa);
         query.setParameter("anno", anno);
         query.setParameter("mes", mes);
         
         List<DocumentosAnulados> listaAnulados = query.list();
    	
        return listaAnulados;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id empresa y una fecha
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<DocumentosAnulados> listOrderBySecuencia(int idEmpresa, int anno, int mes) {
    	 String queryString = "SELECT c FROM DocumentosAnulados c WHERE c.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM c.fechaAnulacion) = :anno AND EXTRACT(MONTH FROM c.fechaAnulacion) = :mes ORDER BY c.secuencia"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("idEmpresa", idEmpresa);
         query.setParameter("anno", anno);
         query.setParameter("mes", mes);
         
         List<DocumentosAnulados> listaAnulados = query.list();
    	
        return listaAnulados;
    }

    /**
	 * Inserta los datos de una {@link DocumentosAnulados} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(String codigoTipoComprobante, int idEmpresa, DocumentosAnulados documentoAnulado) {
		log.debug("persisting DocumentosAnulados instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			Empresa empresaExistente = (Empresa) session.get(Empresa.class, idEmpresa); 
			documentoAnulado.setEmpresa(empresaExistente);
			
			TipoComprobante comprobanteExistente = (TipoComprobante) session.get(TipoComprobante.class, codigoTipoComprobante); 
			documentoAnulado.setTipoComprobante(comprobanteExistente);
			
			session.saveOrUpdate(documentoAnulado);

			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	
	/**
	 * Inserta los datos de una {@link DocumentosAnulados} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(String codigoTipoComprobante, int idEmpresa, DocumentosAnulados documentoAnulado) {
		log.debug("attaching dirty DocumentosAnulados instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			Empresa empresaExistente = (Empresa) session.get(Empresa.class, idEmpresa); 
			documentoAnulado.setEmpresa(empresaExistente);
			
			TipoComprobante comprobanteExistente = (TipoComprobante) session.get(TipoComprobante.class, codigoTipoComprobante); 
			documentoAnulado.setTipoComprobante(comprobanteExistente);
			
			session.saveOrUpdate(documentoAnulado);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Elimina una {@link DocumentosAnulados} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(DocumentosAnulados persistentInstance) {
		log.debug("deleting DocumentosAnulados instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/**
	 * Devuele un DocumentoAnulado de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>DocumentosAnulados</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public DocumentosAnulados findById(int id) {
		log.debug("getting DocumentosAnulados instance with id_anulado: " + id);
		try {
			DocumentosAnulados instance = (DocumentosAnulados) sessionFactory.getCurrentSession().get(DocumentosAnulados.class, id);
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
	 * Devuelve la cantidad de importaciones que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM DocumentosAnulados c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    
	/**
	 * Devuelve la cantidad de documenentos anulados que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa, int anno, int mes) {
        String queryString = "SELECT count(*) FROM DocumentosAnulados c WHERE c.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM c.fechaAnulacion) = :anno AND EXTRACT(MONTH FROM c.fechaAnulacion) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa).setParameter("anno", anno).setParameter("mes", mes);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
}
