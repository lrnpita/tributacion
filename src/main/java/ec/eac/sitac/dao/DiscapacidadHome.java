package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Discapacidad;

/**
 * Objeto Dao de la clase {@link Discapacidad}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see Discapacidad
 * @since 1.0
 */
public class DiscapacidadHome {
	
	private SessionFactory sessionFactory;

	public DiscapacidadHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private static final Log log = LogFactory.getLog(DiscapacidadHome.class);
	
	/**
	 * Devuelve una lista de {@link Discapacidad} de la Base de Datos.
	 *
	 * @return <code>List<Discapacidad></code>.
	 *
	 * @since 1.0
	 */	
    @Transactional
    public List<Discapacidad> list() {
        @SuppressWarnings("unchecked")
        List<Discapacidad> discapacidades = (List<Discapacidad>) sessionFactory.getCurrentSession()
                .createCriteria(Discapacidad.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return discapacidades;
    }
			
}
