// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Pais;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Retencion;
import ec.eac.sitac.model.TipoCompra;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoPago;
import ec.eac.sitac.model.TipoPagoSegunLugar;
import ec.eac.sitac.model.TipoPagoVsCompra;
import ec.eac.sitac.model.Venta;

/**
 * Objeto Dao de la clase {@link Compra}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see Compra
 * @since 1.0
 */
@Repository
public class CompraHome {
    private SessionFactory sessionFactory;
    
    public CompraHome() {
        
    }
     
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public CompraHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(CompraHome.class);

	/**
	 * Devuelve una lista de {@link Compra} de la Base de Datos.
	 *
	 * @return <code>List<Compra></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Compra> list() {
        @SuppressWarnings("unchecked")
        List<Compra> listaCompra = (List<Compra>) sessionFactory.getCurrentSession()
                .createCriteria(Compra.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaCompra;
    }
    
	/**
	 * Devuelve una lista de {@link Compra} de la Base de Datos dado el id de la empresa.
	 *
	 * @param idEmpresa
	 * @return <code>List<Compra></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Compra> list(int idEmpresa) {
        String queryString = "SELECT c FROM Compra c WHERE c.empresa.idEmpresa = :idEmpresa ORDER BY c.registroContable desc"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setParameter("idEmpresa", idEmpresa).setMaxResults(300);
        
        List<Compra> listaCompra = query.list();
 
        return listaCompra;
    }
    
	/**
	 * Devuelve una lista de  retenciones {@link Compra} de la Base de Datos dado el id de la empresa.
	 *
	 * @param idEmpresa
	 * @return <code>List<Compra></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Compra> listRetenciones(int idEmpresa) {
        String queryString = "SELECT c FROM Compra c WHERE c.empresa.idEmpresa = :idEmpresa ORDER BY c.retencion.fechaRetencion desc"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setParameter("idEmpresa", idEmpresa).setMaxResults(100);
        
        List<Compra> listaCompra = query.list();
 
        return listaCompra;
    }
    
	/**
	 * Devuelve una lista de {@link Compra} de la Base de Datos dado el id de la empresa y una fecha.
	 *
	 * @param idEmpresa
	 * @return <code>List<Compra></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Compra> list(int idEmpresa, int anno, int mes) {
        String queryString = "SELECT c FROM Compra c WHERE c.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM c.registroContable) = :anno AND EXTRACT(MONTH FROM c.registroContable) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        query.setParameter("idEmpresa", idEmpresa);
        query.setParameter("anno", anno);
        query.setParameter("mes", mes);
        
        List<Compra> listaCompra = query.list();
 
        return listaCompra;
    }
    
	/**
	 * Devuelve una lista de retenciones {@link Compra} de la Base de Datos dado el id de la empresa y una fecha.
	 *
	 * @param idEmpresa
	 * @return <code>List<Compra></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Compra> listRetenciones(int idEmpresa, int anno, int mes) {
        String queryString = "SELECT c FROM Compra c WHERE c.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM c.retencion.fechaRetencion) = :anno AND EXTRACT(MONTH FROM c.retencion.fechaRetencion) = :mes"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        query.setParameter("idEmpresa", idEmpresa);
        query.setParameter("anno", anno);
        query.setParameter("mes", mes);
        
        List<Compra> listaCompra = query.list();
 
        return listaCompra;
    }
    
	/**
	 * Inserta los datos de una {@link Compra} en la Base de Datos .
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(Compra compra,Retencion retencion, String tipoComprobante, String tipoComprobanteModificado, String proveedor, String tipoPagoSegunLugar, Set<TipoPagoVsCompra> tiposPago, String pais, String tipoCompra, Set<DetallesCompra> detallesCompra, int idEmpresa) {
		log.debug("persisting Compra instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			preparandoObjetoCompra(compra, retencion, tipoComprobante, tipoComprobanteModificado, proveedor, tipoPagoSegunLugar, tiposPago, pais, tipoCompra, detallesCompra, idEmpresa);
			
			session.persist(compra);
			session.persist(retencion);
			
			for (DetallesCompra detalles : detallesCompra) {
				session.persist(detalles);
			}
			
			if(tiposPago != null) {
				for (TipoPagoVsCompra tipoPago : tiposPago) {
					session.persist(tipoPago);
				}
			}
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link Compra} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(Compra compra,Retencion retencion, String tipoComprobante, String tipoComprobanteModificado, String proveedor, String tipoPagoSegunLugar, Set<TipoPagoVsCompra> tiposPagoVsCompra, String pais, String tipoCompra, Set<DetallesCompra> detallesCompra, int idEmpresa) {
		log.debug("attaching dirty Compra instance");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			preparandoObjetoCompra(compra, retencion, tipoComprobante, tipoComprobanteModificado, proveedor, tipoPagoSegunLugar, tiposPagoVsCompra, pais, tipoCompra, detallesCompra, idEmpresa);

			session.saveOrUpdate(compra);
			session.saveOrUpdate(retencion);
			
			for (DetallesCompra detalle : detallesCompra) {
				session.saveOrUpdate(detalle);
			}
				
			if(tiposPagoVsCompra != null) {
				for (TipoPagoVsCompra tipoPagoVsCompra : tiposPagoVsCompra) {
					session.saveOrUpdate(tipoPagoVsCompra);
				}
			}
						
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	/**
	 * Inserta los datos de una lista de ventas{@link Venta} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Set<Compra> comprasList) {
		log.debug("attaching dirty Compra instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			for (Compra compra: comprasList) {
				session.saveOrUpdate(compra);
			}
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Compra instance) {
		log.debug("attaching clean Compra instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Compra} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(Compra persistentInstance) {
		log.debug("deleting Compra instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Compra merge(Compra detachedInstance) {
		log.debug("merging Compra instance");
		try {
			Compra result = (Compra) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Compra de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Compra</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public Compra findById(int id) {
		log.debug("getting Compra instance with id: " + id);
		try {
			Compra instance = (Compra) sessionFactory.getCurrentSession().get(Compra.class, id);
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
	 * Devuele una Compra de la Base de Datos, dado el Id del proveedor. 
	 *
	 * @return Un objeto de tipo <code>Compra</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public boolean existsProveedor(String id) {
		log.debug("getting Compra instance with proveedor id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query consulta = session.createQuery("SELECT c FROM Compra c WHERE personalEmpresa = " + "'" + id + "'");
			;
			
			if (consulta.list().size() != 0) {
				log.debug("get successful, no instance found");
				return true;
			} else {
				
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		
		log.debug("get successful, instance found");
		return false;
	}

	@Transactional
	public List findByExample(Compra instance) {
		log.debug("finding Compra instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Compra").add(Example.create(instance))
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
	 * Devuelve la cantidad de compras que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM Compra c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    
	/**
	 * Devuelve la cantidad de compras que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Compra FindByAutorizacionRetencion(String autorizacion) {
        String queryString = "SELECT c FROM Compra c WHERE c.retencion.autorizacionRetencion = :autorizacion"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("autorizacion", autorizacion);
        
        Compra result = (Compra) query.uniqueResult();
 
        return result;
    }
    @Transactional
    public Compra FindByAutorizacion(String autorizacion) {
    	 String queryString = "SELECT c FROM Compra c WHERE c.codigoAutorizacion = :autorizacion"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString).setParameter("autorizacion", autorizacion);
         
         Compra result = (Compra) query.uniqueResult();
  
         return result;
    }
	
	/**
	 * Llenado los datos de la compra. 
	 *
	 * 
	 * @since 1.0
	 */
	@Transactional
	public void preparandoObjetoCompra(Compra compra,Retencion retencion, String tipoComprobante, String tipoComprobanteModificado, String proveedor, String tipoPagoSegunLugar, Set<TipoPagoVsCompra> tiposPagoVsCompra, String pais, String tipoCompra, Set<DetallesCompra> detallesCompra, int idEmpresa) {
		
		Session session = sessionFactory.getCurrentSession();
		
		TipoComprobante tipoComprobanteExistente = (TipoComprobante) session.get(TipoComprobante.class, tipoComprobante);
		compra.setTipoComprobante(tipoComprobanteExistente);
		
		if(tipoComprobanteModificado != null){
			TipoComprobante tipoComprobanteModificadoExistente = (TipoComprobante) session.get(TipoComprobante.class, tipoComprobanteModificado);
			compra.setTipoComprobanteModificado(tipoComprobanteModificadoExistente);
		}
		
		PersonalEmpresa proveedorExistente = (PersonalEmpresa) session.get(PersonalEmpresa.class, proveedor);
		compra.setPersonalEmpresa(proveedorExistente);
		
		TipoPagoSegunLugar tipoPagoSegunLugarExistente = (TipoPagoSegunLugar) session.get(TipoPagoSegunLugar.class, tipoPagoSegunLugar);
		compra.setTipoPagoSegunLugar(tipoPagoSegunLugarExistente);
		
		Pais paisExistente = (Pais) session.get(Pais.class, pais);
		compra.setPais(paisExistente);
		
		TipoCompra tipoCompraExistente = (TipoCompra) session.get(TipoCompra.class, tipoCompra);
		compra.setTipoCompra(tipoCompraExistente);
		
		compra.setDetallesCompras(detallesCompra);
		
		Empresa empresaExistente = (Empresa) session.get(Empresa.class, idEmpresa); 
		compra.setEmpresa(empresaExistente);
		
		compra.setRetencion(retencion);
		
		compra.setTipoPagoVsCompras(tiposPagoVsCompra);
	}
}
