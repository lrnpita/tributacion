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
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.AutorizarPermisoControlador;
import ec.eac.sitac.model.Banco;
import ec.eac.sitac.model.DenegarPermisoControlador;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.util.PersonalEmpresaEnum;

/**
 * Objeto Dao de la clase {@link DenegarPermisoControlador}
 *
 * @author Luis M. Teijon gdsram@gmail.com
 * @version 1.0
 *
 * @see DenegarPermisoControlador
 * @since 1.0
 */

@Repository
public class AutorizarPermisoControladorHome {
    private SessionFactory sessionFactory;
 
    public AutorizarPermisoControladorHome() {
         
    }
 
	public AutorizarPermisoControladorHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private static final Log log = LogFactory.getLog(AutorizarPermisoControladorHome.class);

	/**
	 * Devuelve una lista de {@link DenegarPermisoControlador} de la Base de Datos.
	 *
	 * @return <code>List<PermisoControlador></code>.
	 *
	 * @since 1.0
	 */
   @Transactional
    public List<AutorizarPermisoControlador> list() {
        @SuppressWarnings("unchecked")
        List<AutorizarPermisoControlador> listaPermisos = (List<AutorizarPermisoControlador>) sessionFactory.getCurrentSession()
                .createCriteria(AutorizarPermisoControlador.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listaPermisos;
    }
    

	/**
	 * Devuele los permisos de un determinado ROL. 
	 *
	 * @return Un objeto de tipo <code>Banco</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public List<AutorizarPermisoControlador> findByRolId(java.lang.Integer idRol) {

	   	String consulta = "SELECT * FROM autorizar_permiso_controlador WHERE id_role = :id_rol";
		   
	   	Session session = sessionFactory.getCurrentSession();
	   	Query query = session.createSQLQuery(consulta)
	   			.addEntity(AutorizarPermisoControlador.class)
	   			.setParameter("id_rol", idRol);
	   	
	   	List<AutorizarPermisoControlador> list = query.list();
	   
	    return list;
	}
}
