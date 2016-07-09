package ec.eac.sitac.dao;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsProducto;
import ec.eac.sitac.model.Producto;

/**
 * Objeto Dao de la clase {@link EmpresaVsProducto}
 *
 * @author Lorena Pita lrnipta@gmail.com
 * @version 1.0
 *
 * @see EmpresaVsProductoHome
 * @since 1.0
 */
public class EmpresaVsProductoHome {
	
	 private SessionFactory sessionFactory;

	public EmpresaVsProductoHome() {
	}

	public EmpresaVsProductoHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	private static final Log log = LogFactory.getLog(EmpresaVsProductoHome.class); 

	/**
	 * Inserta los datos de un {@link EmpresaVsProducto} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(EmpresaVsProducto transientInstance) {
		log.debug("persisting EmpresaVsProducto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Inserta los datos de un {@link EmpresaVsProducto} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(EmpresaVsProducto instance) {
		log.debug("attaching dirty EmpresaVsProductoHome instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Elimina un {@link EmpresaVsProducto} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(EmpresaVsProducto persistentInstance) {
		log.debug("deleting EmpresaVsProducto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/**
	 * Devuele un par Empresa,Cliente de la Base de Datos, dado los Ids. 
	 *
	 * @return <code>EmpresaVsCliente</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public EmpresaVsProducto findById(int idEmpresa, java.lang.String idProducto) {
		log.debug("getting EmpresaVsProducto instance with id: " + idEmpresa + " and " + idProducto);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			// buscando la fila de la BD que contenga el par idEmpresa, idProveedor
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			Producto producto = (Producto) session.get(Producto.class, idProducto);
			EmpresaVsProducto obj = new EmpresaVsProducto();
			obj.setEmpresa(empresa);
			obj.setProducto(producto);
			
			EmpresaVsProducto instance = (EmpresaVsProducto) sessionFactory.getCurrentSession().get(EmpresaVsProducto.class, (Serializable) obj);
			
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
	 * Devuelve la cantidad de clientes que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM EmpresaVsProducto c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
}
