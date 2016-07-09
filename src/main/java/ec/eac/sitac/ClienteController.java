package ec.eac.sitac;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ec.eac.sitac.dao.CiudadHome;
import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.EmpresaVsClienteHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.TipoIdentificacionHome;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.TipoIdentificacion;
import ec.eac.sitac.util.CheckDigit;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/cliente.
 * Controlador de Clientes.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
@Controller
@SessionAttributes("cliente")
@RequestMapping("/empresas")
public class ClienteController {
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	TipoIdentificacionHome tipoIdentificacionDao;
	@Autowired
	CiudadHome ciudadDao;
	@Autowired
	EmpresaHome empresaDao;
	@Autowired
	EmpresaVsClienteHome empresaVsClienteDao;
	
	private static final Logger logger = Logger.getLogger(ClienteController.class);


	/**
	 * Muestra una vista con la lista los clientes con el nombre buscado.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping("/{empresaId}/clientes")
	public String home(@PathVariable int empresaId, Model model) {
		model.addAttribute("cliente", new PersonalEmpresa());
		
		model.addAttribute("clientes", personalEmpresaDao.list(ClienteProveedorEnum.CLIENTE, empresaId));

		return "cliente/home";
	}
	
	/**
	 * Crea la vista para adicionar un nuevo cliente.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/datos", method = RequestMethod.GET)
	public String nuevo(@PathVariable int empresaId, Model model) {
		// cargar los datos para los select
		cargarListasSelect(model);
		
		model.addAttribute("cliente", new PersonalEmpresa());
		model.addAttribute("ciudad", new Ciudad());
		model.addAttribute("tipoIdentificacion", new TipoIdentificacion());
		model.addAttribute("editandoCliente", new Boolean(false));
		model.addAttribute("empresaId", empresaId);

		return "cliente/datos";
	}

	/**
	 * Crea la vista para editar un cliente.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/editar", method = RequestMethod.GET)
	public String editar(@PathVariable int empresaId, @RequestParam("id") String clienteId, Model model) {

		// cargar los datos para los select
		cargarListasSelect(model);
		
		PersonalEmpresa cliente = personalEmpresaDao.findById(clienteId);
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudad", cliente.getCiudad());
		model.addAttribute("tipoIdentificacion", cliente.getTipoIdentificacion());
		model.addAttribute("editandoCliente", new Boolean(true));
		model.addAttribute("idEmpresa", empresaId);
		//model.addAttribute("tipoIdentificacion", new String(cliente.getTipoIdentificacion().getCodigo()));
		
		return "cliente/datos";
	}

	/**
	 * Elimina un cliente.
	 *
	 * @return Redirecciona a la vista que muestra los clientes actualizados.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int empresaId, @RequestParam("id") String clienteId, Model model,
			RedirectAttributes redirectAttributes) {

		PersonalEmpresa cliente = personalEmpresaDao.findById(clienteId);
		
		// verificando que el cliente se pueda eliminar sin violar llaves foraneas
		if (cliente.getExportacions().size() > 0 
				|| cliente.getVentas().size() > 0)  {
			
			redirectAttributes.addFlashAttribute("error", "No se puede eliminar al cliente debido a que existe información asociada a él en el sistema.");
			return Utility.goToUrl(empresaId, "clientes");
		}
		
		personalEmpresaDao.delete(cliente);
		redirectAttributes.addFlashAttribute("ok", "El cliente fue eliminado satisfactoriamente.");
		return Utility.goToUrl(empresaId, "clientes");
	}

	/**
	 * Elimina un clientes via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<String> datos) {

		for (int i = 0; i < datos.size(); i++) {
			PersonalEmpresa cliente = personalEmpresaDao.findById(datos.get(i));
			if (cliente.getExportacions().size() > 0 || cliente.getVentas().size() > 0)  {
				
				return false;
			}
			
			personalEmpresaDao.delete(cliente);
		}
		return true;
	}
	
	/**
	 * Guarda los datos del cliente.
	 *
	 * @return Redirecciona a la vista que muestra los clientes actualizados.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int empresaId, @ModelAttribute("cliente") @Valid PersonalEmpresa cliente, @ModelAttribute("ciudad") Ciudad ciudad, 
			@ModelAttribute("tipoIdentificacion") TipoIdentificacion tipoIdentificacion, @ModelAttribute("editandoCliente") boolean editar, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {

		// Manipulando valores que vienen en arreglos desde la vista
		String[] params = tipoIdentificacion.getCodigo().split(","); // 0 == tipoId, 1 = ciudad
		String tipoId = params[0];
		String idCiudad = params[1];
		
		// Validando ID del cliente
		if (tipoId.equals("05")) { // 05 = Cédula
			if (!CheckDigit.isCedula(cliente.getId())) {
				redirectAttributes.addFlashAttribute("error", "La cédula especificada para el cliente no es válida");
				
				return Utility.goToUrl(empresaId, "clientes/datos");
			}
		} else if (tipoId.equals("04")) { // 04 = RUC

			String thirdDigit = cliente.getId().substring(2,3);

			if ( thirdDigit.equals("6") ) { // Sector público

				if (!CheckDigit.isRUCPublicSector(cliente.getId())) {
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el cliente no es válido para el sector público");
					return Utility.goToUrl(empresaId, "clientes/datos");
				}

			} else if ( Integer.parseInt(thirdDigit) >= 0 &&  Integer.parseInt(thirdDigit) <= 5) { // Personas naturales
					String rucPN = cliente.getId().substring(0,10);
					if (!CheckDigit.isCedula(rucPN)) { // el ruc de persona natural es la cédula con 001 al final
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el cliente no es válido para una persona natural");
					return Utility.goToUrl(empresaId, "clientes/datos");
				} 
			} else if ( thirdDigit.equals("9") ) { // Sector privado o personas extranjeras

				if (!CheckDigit.isRUCPrivateSector(cliente.getId())) {
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el cliente no es válido para el sector privado o para personas naturales extranjeras");
					return Utility.goToUrl(empresaId, "clientes/datos");

				}
			} else {
				redirectAttributes.addFlashAttribute("error", "El tercer dígito del RUC especificado no es válido");
				return Utility.goToUrl(empresaId, "clientes/datos");
			}
		}

		// Guardando la información del cliente 
		try
		{
			if(editar) { // Editando el cliente
				personalEmpresaDao.attachDirtyCliente(tipoId, idCiudad, empresaId, cliente, ClienteProveedorEnum.CLIENTE);
			} else { 
				PersonalEmpresa clienteExistente = personalEmpresaDao.findById(cliente.getId());
				if ( clienteExistente == null) {
					// Insertando el cliente como nuevo
					personalEmpresaDao.persistCliente(tipoId, idCiudad, empresaId, cliente, ClienteProveedorEnum.CLIENTE);
				} else { 
					// Si el cliente existe, verificar que exista también en la empresa actual
					if (empresaVsClienteDao.findById(empresaId, cliente.getId()) == null) {

						Empresa empresaExistente = empresaDao.findById(empresaId);
						EmpresaVsCliente object = new EmpresaVsCliente(empresaExistente, clienteExistente);
						empresaVsClienteDao.persist(object);
						
					} else {
						if (!clienteExistente.isCliente()) {
							// la persona existe, pero no es un cliente, así que lo actualizo como cliente
							clienteExistente.setCliente(true);
							personalEmpresaDao.attachDirtyCliente(tipoId, idCiudad, empresaId, clienteExistente, ClienteProveedorEnum.CLIENTE);
						} else {
							redirectAttributes.addFlashAttribute("error", "El número de identificación especificado pertenece a otro cliente del sistema");
							return Utility.goToUrl(empresaId, "clientes/datos");
						}
					}
				}
			}
		}
		catch (Exception ex) // tratando de insertar un objeto que no es ni cliente ni proveedor
		{
			redirectAttributes.addFlashAttribute("error", "Error al guardar los datos");
			logger.error("This is Error message", ex);
			return Utility.goToUrl(empresaId, "clientes/datos");
		}

		// Mensaje OK
		redirectAttributes.addFlashAttribute("ok", "Los datos del cliente se insertaron correctamente");
		return Utility.goToUrl(empresaId, "clientes");
	}

	/**
	 * Muestra una vista con los detalles de un cliente, dado el id.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/{id}", method = RequestMethod.GET)
	public String getCliente(@PathVariable int id, Model model) {


		return "cliente/detalles";
	}
	
	/**
	 * Carga la informacion de los selects que se mostraran en la vista
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(Model model) {
		model.addAttribute("tiposIdentificacion", tipoIdentificacionDao.list());
		model.addAttribute("ciudades", ciudadDao.list());
	}
	
	/**
	 * Llena el modelo con los datos del cliente
	 * 
	 * @param model
	 * @param cliente
	 * @param empresaId
	 */
	private void prepararModelo(Model model, String tipoId, String idCiudad, int empresaId, PersonalEmpresa cliente, boolean editando) {
		
		model.addAttribute("cliente", cliente);
		Ciudad ciudadExistente = ciudadDao.findById(idCiudad);
		model.addAttribute("ciudad", ciudadExistente);
		TipoIdentificacion tipoIdentificacion = tipoIdentificacionDao.findById(tipoId);
		model.addAttribute("tipoIdentificacion", tipoIdentificacion);
		model.addAttribute("editandoCliente", editando);
		model.addAttribute("idEmpresa", empresaId);
		
	}
	
	/**
	 * Elimina un cliente.
	 *
	 * @return Redirecciona a la vista que muestra los clientes actualizados.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/{empresaId}/clientes/eliminarAjax", method = RequestMethod.POST)
	public @ResponseBody String eliminarAjax(@PathVariable int empresaId, @RequestBody String clienteId, Model model,
			RedirectAttributes redirectAttributes) {
		
		String[] id = clienteId.split("=");
		String idCliente = id[1];
		
		PersonalEmpresa cliente = personalEmpresaDao.findById(idCliente);
		
		// verificando que el cliente se pueda eliminar sin violar llaves foraneas
		if (cliente.getExportacions().size() > 0 
				|| cliente.getVentas().size() > 0)  {
			
			redirectAttributes.addFlashAttribute("error", "No se puede eliminar al cliente debido a que existe información asociada a él en el sistema.");
			return Utility.goToUrl(empresaId, "clientes");
		}
		
		personalEmpresaDao.delete(cliente);
		redirectAttributes.addFlashAttribute("ok", "El cliente fue eliminado satisfactoriamente.");
		return Utility.goToUrl(empresaId, "clientes");
	}

}
