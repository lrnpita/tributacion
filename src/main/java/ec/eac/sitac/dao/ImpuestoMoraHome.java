package ec.eac.sitac.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ec.eac.sitac.model.ImpuestoMora;


public class ImpuestoMoraHome {

	private SessionFactory sessionFactory;


	public ImpuestoMoraHome(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private static final Log log = LogFactory.getLog(ImpuestoMoraHome.class);
	
	/**
	 * Devuelve una lista de {@link ImpuestoMora} de la Base de Datos.
	 *
	 * @return <code>List<ImpuestoMora></code>.
	 *
	 * @since 1.0
	 */	
    @Transactional
    public List<ImpuestoMora> list() {
        @SuppressWarnings("unchecked")
        List<ImpuestoMora> impuestos = (List<ImpuestoMora>) sessionFactory.getCurrentSession()
                .createCriteria(ImpuestoMora.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return impuestos;
    }
    
	/**
	 * Devuelve un {@link ImpuestoMora} de la Base de Datos.
	 *
	 * @return {@link ImpuestoMora}
	 *
	 * @since 1.0
	 */	
    @Transactional
    public ImpuestoMora getImpuestoMora(int mes, int anno) {
    	 String queryString = "SELECT c FROM ImpuestoMora c WHERE :mes between c.mesInicio AND c.mesFin AND c.anno = :anno"; 
         
         Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery(queryString);
         query.setParameter("mes", mes);
         query.setParameter("anno", anno);
         
         ImpuestoMora impuesto = (ImpuestoMora) query.uniqueResult();
  
         return impuesto;
    }
}
