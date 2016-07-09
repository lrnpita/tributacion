// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Canton;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.EmpresaVsProducto;
import ec.eac.sitac.model.Irpnr;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.TarifaIva;
import ec.eac.sitac.model.TipoIce;
import ec.eac.sitac.model.TipoProducto;
import ec.eac.sitac.util.PersonalEmpresaEnum;

/**
 * Objeto Dao de la clase {@link Producto}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Producto
 * @since 2015
 */

@Repository
public class ProductoHome {
    private SessionFactory sessionFactory;
 
    public ProductoHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 2015
	 */
    public ProductoHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(ProductoHome.class);

	/**
	 * Devuelve una lista de {@link Producto} de la Base de Datos.
	 *
	 * @return <code>List<Producto></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<Producto> list() {
    	String consulta = "SELECT p FROM Producto p";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery(consulta);
    	List<Producto> list = query.list();
    
        return list;
    }
    
	/**
	 * Devuelve una lista de {@link Producto} de la Base de Datos dado una empresa.
	 *
	 * @return <code>List<Producto></code>.
	 *
	 * @since 2015
	 */	
    @Transactional
    public List<Producto> list(int idEmpresa) {
    	String consulta = "SELECT p.id_producto, p.codigo_principal, p.codigo_auxiliar, p.id_tarifa_iva, p.valor_unitario, p.atributo1, p.descripcion1, p.id_tipo_producto, p.nombre FROM producto p JOIN empresa_vs_producto ep ON p.id_producto = ep.id_producto WHERE ep.id_empresa = :id_empresa";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createSQLQuery(consulta).addEntity(Producto.class)
    			.setParameter("id_empresa", idEmpresa);
    	List<Producto> list = query.list();
    	
        return list;
    }
    
	/**
	 * Inserta los datos de un {@link Producto} en la Base de Datos.
	 *
	 * @since 2015
	 */
	@Transactional
	public void persist(Producto producto, int tipoProducto, short tarifaIva, int idEmpresa) {
		log.debug("persisting Producto instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			preparandoObjetoProducto(tipoProducto, tarifaIva, producto);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			
			// Relacion entre el producto y la empresa
			EmpresaVsProducto empresaVsProducto = new EmpresaVsProducto(empresa, producto);
			producto.getEmpresasVsProductos().add(empresaVsProducto);
			
			session.persist(producto);
			session.save(empresaVsProducto);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Se llena el objeto Producto 
	 *
	 * @since 2015
	 */
	@Transactional
	private void preparandoObjetoProducto(int tipoProducto, short tarifaIva, Producto producto) {
		
			Session session = sessionFactory.getCurrentSession();
	
			TipoProducto tipoProductoExistente = (TipoProducto)session.get(TipoProducto.class, tipoProducto);
			producto.setTipoProducto(tipoProductoExistente);
	
			TarifaIva tarifaIvaExistente = (TarifaIva) session.get(TarifaIva.class, tarifaIva);
			producto.setTarifaIva(tarifaIvaExistente);
	}

	/**
	 * Inserta los datos de un {@link Producto} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 2015
	 */
	@Transactional
	public void attachDirty(Producto producto, int tipoProducto, short tarifaIva, int idEmpresa) {
		log.debug("attaching dirty Producto instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			preparandoObjetoProducto(tipoProducto, tarifaIva, producto);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			
			// Relacion entre el producto y la empresa
			EmpresaVsProducto empresaVsProducto = new EmpresaVsProducto(empresa, producto);
			producto.getEmpresasVsProductos().add(empresaVsProducto);
			
			session.saveOrUpdate(producto);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional
	public void attachClean(Producto instance) {
		log.debug("attaching clean Producto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link Producto} de la Base de Datos. 
	 *
	 * @since 2015
	 */
	@Transactional
	public void delete(Producto persistentInstance) {
		log.debug("deleting Producto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public Producto merge(Producto detachedInstance) {
		log.debug("merging Producto instance");
		try {
			Producto result = (Producto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un Producto de la Base de Datos, dado el Id. 
	 *
	 * @return <code>Producto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Producto findById(int id) {
		log.debug("getting Producto instance with id: " + id);
		try {
			Producto instance = (Producto) sessionFactory.getCurrentSession()
					.get(Producto.class, id);
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
	 * Devuele un Producto de la Base de Datos, dado el codigo principal y el codigo auxiliar. 
	 *
	 * @return <code>Producto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public Producto findByCodigoPCodigoA(String codP, String codA) {
        String queryString = "SELECT p FROM Producto p WHERE p.codigoPrincipal = :codP AND p.codigoAuxiliar = :codA"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("codP", codP).setParameter("codA", codA);
        
        Producto producto = (Producto) query.uniqueResult();
 
        return producto;
	}
	
	/**
	 * Devuele un Producto de la Base de Datos, dado el codigo principal y el codigo auxiliar. 
	 *
	 * @return <code>Producto</code> 
	 * 
	 * @since 2015
	 */
	@Transactional
	public float findPrecioByCodigoPCodigoA(String codP, String codA) {
        String queryString = "SELECT p.valorUnitario FROM Producto p WHERE p.codigoPrincipal = :codP AND p.codigoAuxiliar = :codA"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("codP", codP).setParameter("codA", codA);
        
        try
        {
        	return Float.parseFloat(query.uniqueResult().toString());
        }
        catch (NumberFormatException ex)
        {
        	return -1;
        }
	}

	@Transactional
	public List findByExample(Producto instance) {
		log.debug("finding Producto instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Producto").add(Example.create(instance))
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
	 * Devuelve la cantidad de productos que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM EmpresaVsProducto e WHERE e.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
}
