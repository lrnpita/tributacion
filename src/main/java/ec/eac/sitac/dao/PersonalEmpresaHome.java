// Generated 07/05/2015 16:52:33 by Hibernate Tools 4.3.1
package ec.eac.sitac.dao;

import java.util.List;

import ognl.SetPropertyAccessor;

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
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.CondicionDeTrabajador;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.EmpresaVsProveedor;
import ec.eac.sitac.model.EmpresaVsTrabajador;
import ec.eac.sitac.model.Pais;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Provincia;
import ec.eac.sitac.model.Residencia;
import ec.eac.sitac.model.TipoIdentificacion;
import ec.eac.sitac.model.TipoProveedorOCliente;
import ec.eac.sitac.model.TipoSalario;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.IPersonalEmpresaEnum;
import ec.eac.sitac.util.PersonalEmpresaEnum;

/**
 * Objeto Dao de la clase {@link PersonalEmpresa}
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @see PersonalEmpresa
 * @since 1.0
 */

@Repository
public class PersonalEmpresaHome {
    private SessionFactory sessionFactory;
    static final String CONSULTA_PERSONAL_EMPRESA = "SELECT e.id,"
			+ " e.nombre,"
			+ " e.email,"
			+ " e.email2,"
			+ " e.telefono,"
			+ " e.id_canton,"
			+ " e.id_cargo,"
			+ " e.id_ciudad,"
			+ " e.id_condicion,"
			+ " e.id_pais_residencia,"
			+ " e.id_residencia_actual,"
			+ " e.id_ciudad,"
			+ " e.id_tipo_identificacion,"
			+ " e.id_tipo_identificacion_new_trabajador,"
			+ " e.id_tipo_proveedor,"
			+ " e.contador,"
			+ " e.cliente,"
			+ " e.proveedor,"
			+ " e.rep_legal,"
			+ " e.trabajador,"
			+ " e.direccion,"
			+ " e.identificacion_fiscal,"
			+ " e.numero_casa,"
			+ " e.ruc_extranjero,"
			+ " e.contribuyente_especial,"
			+ " e.honorarios,"
			+ " e.parte_relacionada,"
			+ " e.lleva_contabilidad,"
			+ " e.retencion,"
			+ " e.codigo_establecimiento,"
			+ " e.apellidos,"
			+ " e.convenio,"
			+ " e.porcentaje,"
			+ " e.calle,"
			+ " e.fecha_nacimiento,"
			+ " e.id_new_trabajador,"
			+ " e.decimo_tercer_sueldo,"
			+ " e.id_tipo_salario"
			+ " FROM personal_empresa e";
    
    public PersonalEmpresaHome() {
         
    }
  
	/**
	 * Constructor.
	 *
	 * @since 1.0
	 */
    public PersonalEmpresaHome(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	private static final Log log = LogFactory.getLog(PersonalEmpresaHome.class);

	/**
	 * Devuelve una lista de {@link PersonalEmpresa} de la Base de Datos dado el tipo de Personal.
	 *
	 * @param type
	 * @return <code>List<PersonalEmpresa></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<PersonalEmpresa> list(IPersonalEmpresaEnum type) {
    	String consulta = "SELECT p FROM PersonalEmpresa p WHERE ";
    	
    	if (type == ClienteProveedorEnum.CLIENTE)
    		consulta += "p.cliente";
    	else if (type == ClienteProveedorEnum.PROVEEDOR)
    		consulta += "p.proveedor";
    	else
    		consulta += "p.trabajador";
    	
    	consulta += " = true";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery(consulta);
    	List<PersonalEmpresa> list = query.list();
    
        return list;
    }
    
	/**
	 * Devuelve una lista de {@link PersonalEmpresa} de la Base de Datos  dado el tipo de Personal y el id de la empresa a la que personal pertenece.
	 *
	 * @param empresaId
	 * @return <code>List<PersonalEmpresa></code>.
	 *
	 * @since 1.0
	 */    
    @Transactional
    public List<PersonalEmpresa> list(IPersonalEmpresaEnum type, int empresaId) {
    	String consulta = CONSULTA_PERSONAL_EMPRESA;
    	
    	if (type == ClienteProveedorEnum.CLIENTE)
    		consulta += " JOIN empresa_vs_cliente ec ON ec.id_cliente = e.id WHERE ec.id_empresa = :id_empresa AND e.cliente";
    	else if (type == ClienteProveedorEnum.PROVEEDOR)
    		consulta += " JOIN empresa_vs_proveedor ep ON ep.id_proveedor = e.id WHERE ep.id_empresa = :id_empresa AND e.proveedor";
    	else
    		consulta += " JOIN empresa_vs_trabajador et ON et.id_trabajador = e.id WHERE et.id_empresa = :id_empresa AND e.trabajador";
    	
    	consulta += " = true";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createSQLQuery(consulta)
    			.addEntity(PersonalEmpresa.class)
    			.setParameter("id_empresa", empresaId);
    	
    	List<PersonalEmpresa> list = query.list();
    
        return list;
    }
    
	/**
	 * Devuelve una lista de {@link PersonalEmpresa} de la Base de Datos  dado el tipo de Personal, el nombre del mismo y el id de la empresa a la que pertenece.
	 *
	 * @param empresaId
	 * @param nombre
	 * @return <code>List<PersonalEmpresa></code>.
	 *
	 * @since 1.0
	 */
    @Transactional
    public List<PersonalEmpresa> list(IPersonalEmpresaEnum type, int empresaId, String nombrePersonal) {
    	String consulta = CONSULTA_PERSONAL_EMPRESA;
    	
    	if (type == ClienteProveedorEnum.CLIENTE)
    		consulta += " JOIN empresa_vs_cliente ec ON ec.id_cliente = e.id WHERE ec.id_empresa = :id_empresa AND e.cliente";
    	else if (type == ClienteProveedorEnum.PROVEEDOR)
    		consulta += " JOIN empresa_vs_proveedor ep ON ep.id_proveedor = e.id WHERE ep.id_empresa = :id_empresa AND e.proveedor";
    	else
    		consulta += " JOIN empresa_vs_trabajador et ON et.id_trabajador = e.id WHERE et.id_empresa = :id_empresa AND e.trabajador";
    	
    	consulta += " = true AND e.nombre ilike :nombre";
    	
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createSQLQuery(consulta)
    			.addEntity(PersonalEmpresa.class)
    			.setParameter("id_empresa", empresaId)
    			.setParameter("nombre", "%" + nombrePersonal + "%");
    	
    	List<PersonalEmpresa> list = query.list();
    
        return list;
    }
    
	/**
	 * Inserta los datos de un Proveedor {@link PersonalEmpresa} en la Base de Datos.
	 * @throws Exception 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persistProveedor(String codigoTipoIdentificacion, String codigoCiudad, int tipoProveedor, int idEmpresa, PersonalEmpresa instance, ClienteProveedorEnum tipo) {
		log.debug("persisting PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			createProveedorObject(codigoTipoIdentificacion, codigoCiudad, tipoProveedor, instance, tipo);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);

			// implementando la relacion intermedia entre clientes y/o proveedores VS empresa
			if (tipo == ClienteProveedorEnum.CLIENTE) {
				instance.setCliente(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsCliente clienteVsEmpresa = new EmpresaVsCliente(empresa, instance);
				instance.getEmpresasForCliente().add(clienteVsEmpresa);
				
				session.persist(instance);
				session.save(clienteVsEmpresa);
				log.debug("attach successful");
			}
			else if (tipo == ClienteProveedorEnum.PROVEEDOR) {
				instance.setProveedor(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsProveedor proveedorVsEmpresa = new EmpresaVsProveedor(empresa, instance);
				instance.getEmpresasForProveedor().add(proveedorVsEmpresa);
				
				session.persist(instance);
				session.save(proveedorVsEmpresa);
				log.debug("attach successful");
			}
			/*
			else
				throw new Exception("El objeto que trató de insertarse no es cliente ni proveedor, verificar que sea uno de estos dos tipos");
			*/
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Inserta los datos de un Proveedor {@link PersonalEmpresa} en la Base de Datos.
	 * @throws Exception 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persistCliente(String codigoTipoIdentificacion, String codigoCiudad, int idEmpresa, PersonalEmpresa instance, ClienteProveedorEnum tipo) {
		log.debug("persisting PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			createClienteObject(codigoTipoIdentificacion, codigoCiudad,  instance, tipo);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);

			// implementando la relacion intermedia entre clientes y/o proveedores VS empresa
			if (tipo == ClienteProveedorEnum.CLIENTE) {
				instance.setCliente(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsCliente clienteVsEmpresa = new EmpresaVsCliente(empresa, instance);
				instance.getEmpresasForCliente().add(clienteVsEmpresa);
				
				session.persist(instance);
				session.save(clienteVsEmpresa);
				log.debug("attach successful");
			}
			else if (tipo == ClienteProveedorEnum.PROVEEDOR) {
				instance.setProveedor(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsProveedor proveedorVsEmpresa = new EmpresaVsProveedor(empresa, instance);
				instance.getEmpresasForProveedor().add(proveedorVsEmpresa);
				
				session.persist(instance);
				session.save(proveedorVsEmpresa);
				log.debug("attach successful");
			}
			/*
			else
				throw new Exception("El objeto que trató de insertarse no es cliente ni proveedor, verificar que sea uno de estos dos tipos");
			*/
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Inserta los datos de un Trabajador {@link PersonalEmpresa} en la Base de Datos.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void persistTrabajador(String codigoTipoIdentificacion, String codigoTipoIdentificacionNewTrabajador,String codigoCiudad, int idResidencia, String codigoPais, int idCondicionDeTrabajador, int idTipoSalario, int idEmpresa, PersonalEmpresa trabajador) {
		log.debug("persisting PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
		
			prepararObjetoTrabajador(codigoTipoIdentificacion, codigoTipoIdentificacionNewTrabajador, codigoCiudad, idResidencia, codigoPais, idCondicionDeTrabajador, idTipoSalario, trabajador);
			
			// creando la relacion entre el trabajador y la empresa 
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			EmpresaVsTrabajador empresaVsTrabajador = new EmpresaVsTrabajador(empresa, trabajador);
			trabajador.getEmpresasForTrabajador().add(empresaVsTrabajador);
			
			session.persist(trabajador);
			session.save(empresaVsTrabajador);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Inserta los datos de un Trabajador en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirtyTrabajador(String codigoTipoIdentificacion, String codigoTipoIdentificacionNewTrabajador,String codigoCiudad, int idResidencia, String codigoPais, int idCondicionDeTrabajador, int idTipoSalario, int idEmpresa, PersonalEmpresa trabajador) {
		log.debug("attaching dirty PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			prepararObjetoTrabajador(codigoTipoIdentificacion, codigoTipoIdentificacionNewTrabajador, codigoCiudad, idResidencia, codigoPais, idCondicionDeTrabajador, idTipoSalario, trabajador);
			
			// creando la relacion entre el trabajador y la empresa 
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);
			EmpresaVsTrabajador empresaVsTrabajador = new EmpresaVsTrabajador(empresa, trabajador);
			trabajador.getEmpresasForTrabajador().add(empresaVsTrabajador);
			
			session.saveOrUpdate(trabajador);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Inserta los datos de un Proveedor {@link PersonalEmpresa} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 * @throws Exception 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirtyProveedor(String codigoTipoIdentificacion, String codigoCiudad, int tipoProveedor, int idEmpresa ,PersonalEmpresa instance, ClienteProveedorEnum tipo) throws Exception {
		log.debug("attaching dirty PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			createProveedorObject(codigoTipoIdentificacion, codigoCiudad, tipoProveedor, instance, tipo);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);

			// implementando la relacion intermedia entre clientes y/o proveedores VS empresa
			if (tipo == ClienteProveedorEnum.CLIENTE) {
				instance.setCliente(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsCliente clienteVsEmpresa = new EmpresaVsCliente(empresa, instance);
				instance.getEmpresasForCliente().add(clienteVsEmpresa);
				
				session.saveOrUpdate(instance);
				log.debug("attach successful");
			}
			else if (tipo == ClienteProveedorEnum.PROVEEDOR) {
				instance.setProveedor(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsProveedor proveedorVsEmpresa = new EmpresaVsProveedor(empresa, instance);
				instance.getEmpresasForProveedor().add(proveedorVsEmpresa);
				
				session.saveOrUpdate(instance);
				log.debug("attach successful");
			}
			/*
			else
				throw new Exception("El objeto que trató de insertarse no es cliente ni proveedor, verificar que sea uno de estos dos tipos");
			*/
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Inserta los datos de un Cliente {@link PersonalEmpresa} en la Base de Datos, 
	 * si el elemento a insertar ya existe entonces lo actualiza.
	 * @throws Exception 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void attachDirtyCliente(String codigoTipoIdentificacion, String codigoCiudad, int idEmpresa ,PersonalEmpresa instance, ClienteProveedorEnum tipo) throws Exception {
		log.debug("attaching dirty PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			createClienteObject(codigoTipoIdentificacion, codigoCiudad, instance, tipo);
			
			Empresa empresa = (Empresa) session.get(Empresa.class, idEmpresa);

			// implementando la relacion intermedia entre clientes y/o proveedores VS empresa
			if (tipo == ClienteProveedorEnum.CLIENTE) {
				instance.setCliente(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsCliente clienteVsEmpresa = new EmpresaVsCliente(empresa, instance);
				instance.getEmpresasForCliente().add(clienteVsEmpresa);
				
				session.saveOrUpdate(instance);
				log.debug("attach successful");
			}
			else if (tipo == ClienteProveedorEnum.PROVEEDOR) {
				instance.setProveedor(true);
				
				// implementando la relacion entre el cliente y la empresa
				EmpresaVsProveedor proveedorVsEmpresa = new EmpresaVsProveedor(empresa, instance);
				instance.getEmpresasForProveedor().add(proveedorVsEmpresa);
				
				session.saveOrUpdate(instance);
				log.debug("attach successful");
			}
			/*
			else
				throw new Exception("El objeto que trató de insertarse no es cliente ni proveedor, verificar que sea uno de estos dos tipos");
			*/
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Transactional
	public void attachDirty(PersonalEmpresa instance) {
		log.debug("updating PersonalEmpresa instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			session.saveOrUpdate(instance);
			
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	@Transactional
	public void attachClean(PersonalEmpresa instance) {
		log.debug("attaching clean PersonalEmpresa instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Elimina un {@link PersonalEmpresa} de la Base de Datos. 
	 *
	 * @since 1.0
	 */
	@Transactional
	public void delete(PersonalEmpresa persistentInstance) {
		log.debug("deleting PersonalEmpresa instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Transactional
	public PersonalEmpresa merge(PersonalEmpresa detachedInstance) {
		log.debug("merging PersonalEmpresa instance");
		try {
			PersonalEmpresa result = (PersonalEmpresa) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Devuele un PersonalEmpresa de la Base de Datos, dado el Id. 
	 *
	 * @return <code>PersonalEmpresa</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public PersonalEmpresa findById(java.lang.String id) {
		log.debug("getting PersonalEmpresa instance with id: " + id);
		try {
			PersonalEmpresa instance = (PersonalEmpresa) sessionFactory
					.getCurrentSession().get(PersonalEmpresa.class, id);
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
	 * Devuele un PersonalEmpresa (Cliente) de la Base de Datos, dado el Id. 
	 * 
	 *
	 * @return <code>PersonalEmpresa</code> 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public PersonalEmpresa findClienteById(java.lang.String id) {
		String consulta = "SELECT p FROM PersonalEmpresa p WHERE id = :id AND cliente=true";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(consulta);
		query.setParameter("id", id);
		
		PersonalEmpresa cliente = (PersonalEmpresa) query.uniqueResult();
		
		return cliente;
	}
	
	/**
	 * Devuele el nombre del (Cliente) de la Base de Datos, dado el Id. 
	 * 
	 *
	 * @since 1.0
	 */
	@Transactional
	public String findNombreClienteById(java.lang.String id) {
		String consulta = "SELECT p.nombre FROM PersonalEmpresa p WHERE id = :id AND cliente=true";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(consulta);
		query.setParameter("id", id);
		
		String nombre = (String) query.uniqueResult();
		
		return nombre;
	}
	
	/**
	 * Devuele el nombre del (Proveedor) de la Base de Datos, dado el Id. 
	 * 
	 * 
	 * @since 1.0
	 */
	@Transactional
	public String findNombreProveedorById(java.lang.String id) {
		String consulta = "SELECT p.nombre FROM PersonalEmpresa p WHERE id = :id AND proveedor=true";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(consulta)
				.setParameter("id", id);
		
		String nombre = (String) query.uniqueResult();
		
		return nombre;
	}
	

	/**
	 * Crea el objeto a ser insertado o actualizado en la BD
	 * 
	 * @param codigoTipoIdentificacion
	 * @param codigoTipoIdentificacionNewTrabajador
	 * @param codigoCiudad
	 * @param idResidencia
	 * @param codigoPais
	 * @param idCondicionDeTrabajador
	 * @param idTipoSalario
	 * @param trabajador
	 * 
	 * @since 1.0
	 */
	@Transactional
	private void prepararObjetoTrabajador(String codigoTipoIdentificacion, String codigoTipoIdentificacionNewTrabajador,String codigoCiudad, int idResidencia, String codigoPais, int idCondicionDeTrabajador, int idTipoSalario, PersonalEmpresa trabajador) {
		log.debug("persisting PersonalEmpresa instance");

		Session session = sessionFactory.getCurrentSession();

		Ciudad ciudadExistente = (Ciudad) session.get(Ciudad.class, codigoCiudad);
		trabajador.setCiudad(ciudadExistente);

		TipoIdentificacion tipoIdentificacionExistente = (TipoIdentificacion) session.get(TipoIdentificacion.class, codigoTipoIdentificacion);
		trabajador.setTipoIdentificacion(tipoIdentificacionExistente);

		if (codigoTipoIdentificacionNewTrabajador != null) {
			tipoIdentificacionExistente = (TipoIdentificacion) session.get(TipoIdentificacion.class, codigoTipoIdentificacionNewTrabajador);
			trabajador.setTipoIdentificacionNewTrabajador(tipoIdentificacionExistente);
		}

		Residencia residenciaExistente = (Residencia) session.get(Residencia.class, idResidencia);
		trabajador.setResidencia(residenciaExistente);

		Pais paisExistente = (Pais) session.get(Pais.class, codigoPais);
		trabajador.setPais(paisExistente);

		CondicionDeTrabajador condicionExistente = (CondicionDeTrabajador) session.get(CondicionDeTrabajador.class, idCondicionDeTrabajador);
		trabajador.setCondicionDeTrabajador(condicionExistente);

		TipoSalario tipoSalarioExistente = (TipoSalario) session.get(TipoSalario.class, idTipoSalario);
		trabajador.setTipoSalario(tipoSalarioExistente);
		
		trabajador.setTrabajador(true);
	}
	
	/**
	 * Crea los objetos cliente que seráninsertadoso actualizadosen la BD
	 * 
	 * @param codigoTipoIdentificacion
	 * @param codigoCiudad
	 * @param instance Objeto que será almacenado en la BD
	 * @param tipo Tipo de Objeto: Cliente o Proveedor
	 * 
	 * @since 1.0
	 */
	private void createClienteObject(String codigoTipoIdentificacion, String codigoCiudad, PersonalEmpresa instance, ClienteProveedorEnum tipo) {
		Session session = sessionFactory.getCurrentSession();

		Ciudad ciudadExistente = (Ciudad) session.get(Ciudad.class, codigoCiudad);
		instance.setCiudad(ciudadExistente);

		TipoIdentificacion tipoIdentificacionExistente = (TipoIdentificacion) session.get(TipoIdentificacion.class, codigoTipoIdentificacion);
		instance.setTipoIdentificacion(tipoIdentificacionExistente);
		
	}
	
	/**
	 * Crea los objeto proveedor que será insertado o actualizado en la BD
	 * 
	 * @param codigoTipoIdentificacion
	 * @param codigoCiudad
	 * @param tipoProveedor
	 * @param instance Objeto que será almacenado en la BD
	 * @param tipo Tipo de Objeto: Cliente o Proveedor
	 * 
	 * @since 1.0
	 */
	private void createProveedorObject(String codigoTipoIdentificacion, String codigoCiudad, int tipoProveedor, PersonalEmpresa instance, ClienteProveedorEnum tipo) {
		Session session = sessionFactory.getCurrentSession();

		Ciudad ciudadExistente = (Ciudad) session.get(Ciudad.class, codigoCiudad);
		instance.setCiudad(ciudadExistente);

		TipoIdentificacion tipoIdentificacionExistente = (TipoIdentificacion) session.get(TipoIdentificacion.class, codigoTipoIdentificacion);
		instance.setTipoIdentificacion(tipoIdentificacionExistente);
		
		TipoProveedorOCliente tipoProveedorExistente = (TipoProveedorOCliente) session.get(TipoProveedorOCliente.class, tipoProveedor);
		instance.setTipoProveedorOCliente(tipoProveedorExistente);
	}
}
