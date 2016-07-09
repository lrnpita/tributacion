package ec.eac.sitac.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.EmpresaVsProveedor;
import ec.eac.sitac.model.PersonalEmpresa;

/**
 * Objeto Dao de la clase {@link EmpresaVsProveedor}
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @see EmpresaVsProveedorHome
 * @since 1.0
 */
@Repository
public class EmpresaVsProveedorHome {
    private SessionFactory sessionFactory;
    
    public EmpresaVsProveedorHome() {
        
    }
    
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public EmpresaVsProveedorHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(EmpresaVsProveedorHome.class);


	/**
	 * Inserta los datos de un {@link EmpresaVsProveedor} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(EmpresaVsProveedor transientInstance) {
		log.debug("persisting EmpresaVsProveedor instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un {@link EmpresaVsProveedor} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirty(EmpresaVsProveedor instance) {
		log.debug("attaching dirty EmpresaVsProveedor instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link EmpresaVsProveedor} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(EmpresaVsProveedor persistentInstance) {
		log.debug("deleting EmpresaVsProveedor instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un par Empresa,Proveedor de la Base de Datos, dado los Ids. 
	 *
	 * @return <code>EmpresaVsProveedor</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public EmpresaVsProveedor findById(int idEmpresa, java.lang.String idProveedor) {
		log.debug("getting EmpresaVsProveedor instance with id: " + idEmpresa + " and " + idProveedor);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			// buscando la fila de la BD que contenga el par idEmpresa, idProveedor
			Empresa emp = (Empresa) session.get(Empresa.class, idEmpresa);
			PersonalEmpresa prov = (PersonalEmpresa) session.get(PersonalEmpresa.class, idProveedor);
			EmpresaVsProveedor obj = new EmpresaVsProveedor();
			obj.setEmpresa(emp);
			obj.setProveedor(prov);
			
			EmpresaVsProveedor instance = (EmpresaVsProveedor) sessionFactory.getCurrentSession().get(EmpresaVsProveedor.class, obj);
			
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
	 * Devuelve la cantidad de proveedores que existen en la Base de datos dado una empresa.
	 *
	 * @since 1.0
	 */
    /**
     * @param idEmpresa
     */
    @Transactional
    public Long count(int idEmpresa) {
        String queryString = "SELECT count(*) FROM EmpresaVsProveedor c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    
}
