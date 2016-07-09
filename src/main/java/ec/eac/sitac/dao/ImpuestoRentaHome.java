package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.ImpuestoRenta;
import ec.eac.sitac.model.Movimiento;

/**
 * Objeto Dao de la clase {@link ImpuestoRenta}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see ImpuestoRenta
 * @since 1.0
 */
public class ImpuestoRentaHome {

	private SessionFactory sessionFactory;


	public ImpuestoRentaHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private static final Log log = LogFactory.getLog(DiscapacidadHome.class);
	
	/**
	 * Devuelve una lista de {@link ImpuestoRenta} de la Base de Datos.
	 *
	 * @return <code>List<ImpuestoRenta></code>.
	 *
	 * @since 1.0
	 */	
    @Transactional
    public List<ImpuestoRenta> list() {
        @SuppressWarnings("unchecked")
        List<ImpuestoRenta> impuestos = (List<ImpuestoRenta>) sessionFactory.getCurrentSession()
                .createCriteria(ImpuestoRenta.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return impuestos;
    }
    
	/**
	 * Devuelve un {@link ImpuestoRenta} de la Base de Datos.
	 *
	 * @return {@link ImpuestoRenta}
	 *
	 * @since 1.0
	 */	
    @Transactional
    public ImpuestoRenta getImpuestoRenta(float baseImponible) {
    	 String queryString = "SELECT c FROM ImpuestoRenta c WHERE :baseImponible BETWEEN c.fraccionBasica AND c.exceso"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("baseImponible", baseImponible);
         
         ImpuestoRenta impuesto = (ImpuestoRenta) query.uniqueResult();
  
         return impuesto;
    }
}
