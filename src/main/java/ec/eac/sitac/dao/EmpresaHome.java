// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.TipoAmbiente;
import ec.eac.sitac.model.TipoEmision;

/**
 * Objeto Dao de la clase {@link Empresa}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Empresa
 * @since 1.0
 */

@Repository
public class EmpresaHome {
    private SessionFactory sessionFactory;
	
    public EmpresaHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public EmpresaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	private static final Log log = LogFactory.getLog(EmpresaHome.class);

	/**
	 * Devuelve una lista de {@link Empresa} de la Base de Datos.
	 *
	 * @return <code>List<Empresa></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<Empresa> list() {
        @SuppressWarnings("unchecked")
        List<Empresa> listaEmpresa = (List<Empresa>) sessionFactory.getCurrentSession()
                .createCriteria(Empresa.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaEmpresa;
    }
    
	/**
	 * Inserta los datos de una {@link Empresa} en la Base de Datos .
	 *
	 * @since 1.0
	 */
    @Transactional
	public void persist(PersonalEmpresa contador, PersonalEmpresa repLegal, char idTipoAmbiente, char idTipoEmision, String idCiudad, Empresa transientInstance) {
		log.debug("persisting Empresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			prepararObjetoEmpresa(contador, repLegal, idTipoAmbiente, idTipoEmision, idCiudad, transientInstance);
			
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de una {@link Empresa} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
    @Transactional
	public void attachDirty(Empresa instance) {
		log.debug("attaching dirty Empresa instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Transactional
	public void attachClean(Empresa instance) {
		log.debug("attaching clean Empresa instance");
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
	 * @since 1.0
	 */
    @Transactional
	public void delete(Empresa persistentInstance) {
		log.debug("deleting Empresa instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    @Transactional
	public Empresa merge(Empresa detachedInstance) {
		log.debug("merging Empresa instance");
		try {
			Empresa result = (Empresa) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele una Empresa de la Base de Datos, dado el Id. 
	 *
	 * @return Un objeto de tipo <code>Empresa</code> 
	 * 
	 * @since 1.0
	 */
    @Transactional
	public Empresa findById(int id) {
		log.debug("getting Empresa instance with id: " + id);
		try {
			Empresa instance = (Empresa) sessionFactory.getCurrentSession().get(Empresa.class, id);
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
     * Crea los objetos Empresa que serán almacenados o actualizados en la BD
     * 
     * @param contador
     * @param cepLegal
     * @param idTipoAmbiente
     * @param idTipoEmision
     * @param empresa
     * 
     * @since 1.0
     */
    @Transactional
    public void prepararObjetoEmpresa(PersonalEmpresa contador, PersonalEmpresa repLegal, char idTipoAmbiente, char idTipoEmision, String idCiudad, Empresa empresa) {
		Session session = sessionFactory.getCurrentSession();
		
		//PersonalEmpresa contador = (PersonalEmpresa) session.get(PersonalEmpresa.class, idContador);
		empresa.setPersonalEmpresaByIdContador(contador);
		
		//PersonalEmpresa repLegal = (PersonalEmpresa) session.get(PersonalEmpresa.class, idRepLegal);
		empresa.setPersonalEmpresaByIdRepLegal(repLegal);
		
		TipoAmbiente tipoAmbiente = (TipoAmbiente) session.get(TipoAmbiente.class, idTipoAmbiente);
		empresa.setTipoAmbiente(tipoAmbiente);
		
		TipoEmision tipoEmision = (TipoEmision) session.get(TipoEmision.class, idTipoEmision);
		empresa.setTipoEmision(tipoEmision);
		
		Ciudad ciudad = (Ciudad) session.get(Ciudad.class, idCiudad);
		empresa.setCiudad(ciudad);
    }
    
    
	/**
	 * Devuelve la cantidad de empresas que existen en la Base de datos.
	 *
	 * @since 1.0
	 */
    @Transactional
    public Long count() {
        String queryString = "SELECT count(*) FROM Empresa"; 
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        
        Long cantidad = (Long) query.uniqueResult();
 
        return cantidad;
    }
    

    
}
