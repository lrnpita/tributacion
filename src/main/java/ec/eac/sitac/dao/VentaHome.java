// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;
import java.util.Set;

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
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsProducto;
import ec.eac.sitac.model.EstadoSRI;
import ec.eac.sitac.model.Pais;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.Retencion;
import ec.eac.sitac.model.TipoCompra;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoPago;
import ec.eac.sitac.model.TipoPagoSegunLugar;
import ec.eac.sitac.model.TipoVenta;
import ec.eac.sitac.model.TipoVentaSegunPago;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.model.VentaVsProducto;

/**
 * Objeto Dao de la clase {@link Venta}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Venta
 * @since 1.0
 */

@Repository
public class VentaHome {
    private SessionFactory sessionFactory;
 
    public VentaHome() {
         
    }
    
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public VentaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(VentaHome.class);

	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id de empresa
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Venta> list(int idEmpresa) {
    	String queryString = "SELECT v FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa ORDER BY v.fechaRegistro desc"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setParameter("idEmpresa", idEmpresa).setMaxResults(300);
        
        List<Venta> listaVentas = query.list();
 
        return listaVentas;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id de empresa
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Venta> list() {
    	String queryString = "SELECT v FROM Venta v ORDER BY v.fechaRegistro desc"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString)
        		.setMaxResults(100);
        
        List<Venta> listaVentas = query.list();
 
        return listaVentas;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id empresa y una fecha
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Venta> list(int idEmpresa, int anno, int mes) {
    	 String queryString = "SELECT v FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM v.fechaRegistro) = :anno AND EXTRACT(MONTH FROM v.fechaRegistro) = :mes"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("idEmpresa", idEmpresa);
         query.setParameter("anno", anno);
         query.setParameter("mes", mes);
         
         List<Venta> listaVentas = query.list();
    	
        return listaVentas;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id empresa y una fecha
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Venta> listByPuntoEmision(int idEmpresa, int idPunto, int anno, int mes) {
    	 String queryString = "SELECT v FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa AND v.puntoEmision.id = :idPunto AND EXTRACT(YEAR FROM v.fechaRegistro) = :anno AND EXTRACT(MONTH FROM v.fechaRegistro) = :mes"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("idEmpresa", idEmpresa);
         query.setParameter("idPunto", idPunto);
         query.setParameter("anno", anno);
         query.setParameter("mes", mes);
         
         List<Venta> listaVentas = query.list();
    	
        return listaVentas;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id empresa y una fecha
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Venta> listOrderByIdCliente(int idEmpresa, int anno, int mes) {
    	 String queryString = "SELECT v FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM v.fechaRegistro) = :anno AND EXTRACT(MONTH FROM v.fechaRegistro) = :mes ORDER BY v.personalEmpresa.id"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("idEmpresa", idEmpresa);
         query.setParameter("anno", anno);
         query.setParameter("mes", mes);
         
         List<Venta> listaVentas = query.list();
    	
        return listaVentas;
    }
    
	/**
	 * Devuelve una lista de {@link Venta} de la Base de Datos dado un id empresa y una fecha
	 *
	 * @return <code>List<Venta></code>.
	 *
	 * @since 2015
	 */
    @Transactional
    public List<Venta> listOrderByIdPuntoEmision(int idEmpresa, int anno, int mes) {
    	 String queryString = "SELECT v FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa AND EXTRACT(YEAR FROM v.fechaRegistro) = :anno AND EXTRACT(MONTH FROM v.fechaRegistro) = :mes ORDER BY v.puntoEmision.id"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("idEmpresa", idEmpresa);
         query.setParameter("anno", anno);
         query.setParameter("mes", mes);
         
         List<Venta> listaVentas = query.list();
    	
        return listaVentas;
    }

	/**
	 * Inserta los datos de una {@link Venta} en la Base de Datos .
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Venta venta,Retencion retencion, String cliente, String tipoVentaSegunPago, String tipoVenta, String tipoComprobante, int estadoSRI, int idEmpresa, Set<VentaVsProducto> listVentaVsProductos) {
		
		log.debug("persisting Venta instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			preparandoObjetoVenta(venta, retencion, cliente, tipoVentaSegunPago, tipoVenta, tipoComprobante, estadoSRI, idEmpresa);
			
			venta.setVentaVsProductos(listVentaVsProductos);
			session.persist(venta);
			session.persist(retencion);
			
			for (VentaVsProducto ventaVSproducto : listVentaVsProductos) {
				ventaVSproducto.setVenta(venta);
				session.persist(ventaVSproducto);
			}
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de una {@link Venta} en la Base de Datos .
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Venta venta) {
		
		log.debug("persisting Venta instance");
		try {

			Session session = sessionFactory.getCurrentSession();
			session.persist(venta);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	
	/**
	 * Inserta los datos de una {@link Venta} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza .
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Venta venta,Retencion retencion, String cliente,String tipoVentaSegunPago, String tipoVenta, String tipoComprobante, int estadoSRI, int idEmpresa, Set<VentaVsProducto> listVentaVsProductos) {
		log.debug("attaching dirty Venta instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			preparandoObjetoVenta(venta, retencion, cliente,tipoVentaSegunPago, tipoVenta, tipoComprobante, estadoSRI, idEmpresa);
			
			session.saveOrUpdate(venta);
			session.saveOrUpdate(retencion);
			
			for (VentaVsProducto ventaVSproducto : listVentaVsProductos) {
				session.saveOrUpdate(ventaVSproducto);
			}
			venta.setVentaVsProductos(listVentaVsProductos);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Venta instance) {
		log.debug("attaching clean Venta instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina una {@link Venta} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Venta persistentInstance) {
		log.debug("deleting Venta instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Venta merge(Venta detachedInstance) {
		log.debug("merging Venta instance");
		try {
			Venta result = (Venta) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Venta de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Venta</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Venta findById(int id) {
		log.debug("getting Venta instance with id: " + id);
		try {
			Venta instance = (Venta) sessionFactory.getCurrentSession().get(Venta.class, id);
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
	public List findByExample(Venta instance) {
		log.debug("finding Venta instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Venta").add(Example.create(instance))
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
	 * Devuelve la cantidad de ventas que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    
    /**
	 * Devuelve la cantidad de ventas que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Venta findByAutorizacion(String aut) {
        String queryString = "SELECT v FROM Venta v WHERE v.codigoAutorizacionComprobante = :aut"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("aut", aut);
        
        Venta venta = (Venta) query.uniqueResult();
 
        return venta;
    }
    
	/**
	 * Devuelve la cantidad de ventas que existen en la Base de datos dado una empresa y un punto de emision.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa, int idPuntoEmision) {
        String queryString = "SELECT count(*) FROM Venta v WHERE v.empresa.idEmpresa = :idEmpresa AND v.puntoEmision.id = :idPuntoEmision "; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa)
        												.setParameter("idPuntoEmision", idPuntoEmision);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    
    /**
	 * Llenado los datos de la compra. 
	 *
	 * 
	 * @since 1.0
	 */
	@Transactional
	public void preparandoObjetoVenta(Venta venta,Retencion retencion, String cliente,String tipoVentaSegunPago, String tipoVenta,String tipoComprobante, int estadoSRI, int idEmpresa) {
		
		Session session = sessionFactory.getCurrentSession();
		
		TipoComprobante tipoComprobanteExistente = (TipoComprobante) session.get(TipoComprobante.class, tipoComprobante);
		venta.setTipoComprobante(tipoComprobanteExistente);
		
		PersonalEmpresa clienteExistente = (PersonalEmpresa) session.get(PersonalEmpresa.class, cliente);
		venta.setPersonalEmpresa(clienteExistente);
		
		TipoVenta tipoVentaExistente = (TipoVenta) session.get(TipoVenta.class, tipoVenta);
		venta.setTipoVenta(tipoVentaExistente);
		
		TipoVentaSegunPago tipoVentaSegunPagoExistente = (TipoVentaSegunPago) session.get(TipoVentaSegunPago.class, tipoVentaSegunPago);
		venta.setTipoVentaSegunPago(tipoVentaSegunPagoExistente);
		
		EstadoSRI estadoSRIExistente = (EstadoSRI) session.get(EstadoSRI.class, estadoSRI);
		venta.setEstado(estadoSRIExistente);
		
		Empresa empresaExistente = (Empresa) session.get(Empresa.class, idEmpresa); 
		venta.setEmpresa(empresaExistente);
		
		venta.setRetencion(retencion);
	}
}
