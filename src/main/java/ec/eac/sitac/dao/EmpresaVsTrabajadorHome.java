package ec.eac.sitac.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.EmpresaVsProveedor;
import ec.eac.sitac.model.EmpresaVsTrabajador;
import ec.eac.sitac.model.PersonalEmpresa;

/**
 * Objeto Dao de la clase {@link EmpresaVsCliente}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see EmpresaVsTrabajadorHome
 * @since 1.0
 */
public class EmpresaVsTrabajadorHome {
	
	private SessionFactory sessionFactory;

	public EmpresaVsTrabajadorHome() {
	}

	public EmpresaVsTrabajadorHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private static final Log log = LogFactory.getLog(EmpresaVsTrabajadorHome.class);
	
	/**
	 * Inserta los datos de un {@link EmpresaVsTrabajador} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persist(EmpresaVsTrabajador transientInstance) {
		log.debug("persisting EmpresaVsTrabajador instance");
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
	public void attachDirty(EmpresaVsTrabajador instance) {
		log.debug("attaching dirty EmpresaVsTrabajadorHome instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link EmpresaVsCliente} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(EmpresaVsTrabajador persistentInstance) {
		log.debug("deleting EmpresaVsTrabajador instance");
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
	public EmpresaVsTrabajador findById(int idEmpresa, java.lang.String idTrabajador) {
		log.debug("getting EmpresaVsCliente instance with id: " + idEmpresa + " and " + idTrabajador);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			// buscando la fila de la BD que contenga el par idEmpresa, idProveedor
			Empresa emp = (Empresa) session.get(Empresa.class, idEmpresa);
			PersonalEmpresa trabajador = (PersonalEmpresa) session.get(PersonalEmpresa.class, idTrabajador);
			EmpresaVsTrabajador obj = new EmpresaVsTrabajador();
			obj.setEmpresa(emp);
			obj.setTrabajador(trabajador);
			
			EmpresaVsTrabajador instance = (EmpresaVsTrabajador) sessionFactory.getCurrentSession().get(EmpresaVsTrabajador.class, obj);
			
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
        String queryString = "SELECT count(*) FROM EmpresaVsTrabajador c WHERE c.empresa.idEmpresa = :idEmpresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString).setParameter("idEmpresa", idEmpresa);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }

	
}
