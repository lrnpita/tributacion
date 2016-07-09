package ec.eac.sitac;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.eac.sitac.dao.CiudadHome;
import ec.eac.sitac.dao.CompraHome;
import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.EmpresaVsProveedorHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.TipoIdentificacionHome;
import ec.eac.sitac.dao.TipoProveedorOClienteHome;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.EmpresaVsProveedor;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.TipoIdentificacion;
import ec.eac.sitac.model.TipoProveedorOCliente;
import ec.eac.sitac.util.CheckDigit;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/proveedores.
 * Controlador de Proveedores.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/proveedores")
public class ProveedorController {
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	TipoIdentificacionHome tipoIdentificacionDao;
	@Autowired
	CiudadHome ciudadDao;
	@Autowired
	CompraHome compraDao;
	@Autowired
	EmpresaVsProveedorHome empresaVsProveedorDao;
	@Autowired
	EmpresaHome empresaDao;
	@Autowired
	TipoProveedorOClienteHome tipoProveedorOClienteDao;
	
	private static final Logger logger = Logger.getLogger(ProveedorController.class);
	
	/**
	 * Muestra una vista con la lista los proveedores.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(@PathVariable int idEmpresa, Model model) {
		model.addAttribute("proveedor", new PersonalEmpresa());
		model.addAttribute("proveedores", personalEmpresaDao.list(ClienteProveedorEnum.PROVEEDOR, idEmpresa));
	
		return "proveedor/home";
	}
	
	/**
	 * Crea la vista para adicionar un nuevo proveedor.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model) {
		
		cargarListasSelect(model);
		
		model.addAttribute("proveedor", new PersonalEmpresa());
		model.addAttribute("ciudad", new Ciudad());
		model.addAttribute("tipoIdentificacion", new TipoIdentificacion());
		model.addAttribute("tipoProveedor", new TipoProveedorOCliente());
		model.addAttribute("editandoProveedor", new Boolean(false));
		
		return "proveedor/datos";
	}
	
	/**
	 * Crea la vista para editar un proveedor.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") String proveedorId, Model model) {
		
		cargarListasSelect(model);
		
		PersonalEmpresa proveedor = personalEmpresaDao.findById(proveedorId);
		model.addAttribute("proveedor", proveedor);
		model.addAttribute("ciudad", proveedor.getCiudad());
		model.addAttribute("tipoIdentificacion", proveedor.getTipoIdentificacion());
		model.addAttribute("tipoProveedor", new TipoProveedorOCliente());
		model.addAttribute("editandoProveedor", new Boolean(true));
		
		return "proveedor/datos";
	}
	
	/**
	 * Elimina un proveedor.
	 *
	 * @return Redirecciona a la vista que muestra los proveedores actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa,@RequestParam("id") String proveedorId, Model model, RedirectAttributes redirectAttributes) {

		PersonalEmpresa proveedor = personalEmpresaDao.findById(proveedorId);

		// verificando que el proveedor se pueda eliminar sin violar llaves foraneas
		if (compraDao.existsProveedor(proveedor.getId()) 
				|| proveedor.getImportacions().size() > 0) {

			redirectAttributes.addFlashAttribute("error", "No se puede eliminar al proveedor debido a que existe información asociada a él en el sistema.");
			return Utility.goToUrl(idEmpresa, "proveedores");
		}

		try {
			personalEmpresaDao.delete(proveedor);
		} catch (Exception e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("ok", "Error al eliminar el proveedor");
			return Utility.goToUrl(idEmpresa, "proveedores");
		}

		redirectAttributes.addFlashAttribute("ok", "El proveedor fue eliminado satisfactoriamente.");
		return Utility.goToUrl(idEmpresa, "proveedores");
	}
	
	/**
	 * Guarda los datos del proveedor.
	 *
	 * @return Redirecciona a la vista que muestra los proveedores actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa,
			@ModelAttribute("proveedor") PersonalEmpresa proveedor,
			@ModelAttribute("ciudad") Ciudad ciudad, 
			@ModelAttribute("tipoIdentificacion") TipoIdentificacion tipoIdentificacion,
			@ModelAttribute("tipoProveedor") TipoProveedorOCliente tipoProveedor,
			@ModelAttribute("editandoProveedor") boolean editandoProveedor,
			RedirectAttributes redirectAttributes) {
		
		String[] params = tipoIdentificacion.getCodigo().split(",");
		String tipoId = params[0];
		String codigoCiudad = params[1];
		
		// 0 == tipoId, 1 = ciudad
		
		// Validando ID del proveedor
		if (tipoId.equals("05")) { // 05 = Cédula
			if (!CheckDigit.isCedula(proveedor.getId())) {
				redirectAttributes.addFlashAttribute("error", "La cédula especificada para el proveedor no es válida");
				return Utility.goToUrl(idEmpresa, "proveedores/datos");
			}
		} else if (tipoId.equals("04")) { // 04 = RUC

			String thirdDigit = proveedor.getId().substring(2,3);

			if ( thirdDigit.equals("6") ) { // Sector público

				if (!CheckDigit.isRUCPublicSector(proveedor.getId())) {
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el proveedor no es válido para el sector público");
					return Utility.goToUrl(idEmpresa, "proveedores/datos");
				}

			} else if ( Integer.parseInt(thirdDigit) >= 0 &&  Integer.parseInt(thirdDigit) <= 5) { // Personas naturales
				String rucPN = proveedor.getId().substring(0,10);
				if (!CheckDigit.isCedula(rucPN)) { // el ruc de persona natural es la cédula con 001 al final
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el proveedor no es válido para una persona natural");
					return Utility.goToUrl(idEmpresa, "proveedores/datos");

				} 
			} else if ( thirdDigit.equals("9") ) { // Sector privado o personas extranjeras

				if (!CheckDigit.isRUCPrivateSector(proveedor.getId())) {
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el proveedor no es válido para el sector privado o para personas naturales extranjeras");
					return Utility.goToUrl(idEmpresa, "proveedores/datos");

				}
			} else {
				redirectAttributes.addFlashAttribute("error", "El tercer dígito del RUC especificado no es válido");
				return Utility.goToUrl(idEmpresa, "proveedores/datos");
			}
		}
		
		try
		{
			if (editandoProveedor) {
				personalEmpresaDao.attachDirtyProveedor(tipoId, codigoCiudad, tipoProveedor.getIdTipoProveedorOCliente(), idEmpresa, proveedor, ClienteProveedorEnum.PROVEEDOR);
			}
			else 
			{
				PersonalEmpresa proveedorExistente = personalEmpresaDao.findById(proveedor.getId());
				if (proveedorExistente == null) {
					personalEmpresaDao.persistProveedor(tipoId, codigoCiudad, tipoProveedor.getIdTipoProveedorOCliente(), idEmpresa, proveedor, ClienteProveedorEnum.PROVEEDOR);
				} else {
					// Si el proveedor existe, verificar que exista también en la empresa actual
					if (empresaVsProveedorDao.findById(idEmpresa, proveedor.getId()) == null) {

						Empresa empresaExistente = empresaDao.findById(idEmpresa);
						EmpresaVsProveedor object = new EmpresaVsProveedor(empresaExistente, proveedorExistente);
						empresaVsProveedorDao.persist(object);
						
					} else {
		
						if (!proveedorExistente.isProveedor()) {
							// la persona existe, pero no es un proveedor, así que lo inserto como proveedor
							proveedorExistente.setProveedor(true);
							personalEmpresaDao.attachDirtyProveedor(tipoId, codigoCiudad, tipoProveedor.getIdTipoProveedorOCliente(),idEmpresa, proveedor, ClienteProveedorEnum.PROVEEDOR);
						} else {
							redirectAttributes.addFlashAttribute("error", "El número de identificación especificado pertenece a otro proveedor del sistema");
							return Utility.goToUrl(idEmpresa, "proveedores/datos");
						}
					}
				}
			}
		}
		catch (Exception ex) // tratando de insertar un objeto que no es ni cliente ni proveedor
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", ex.getMessage());
			return Utility.goToUrl(idEmpresa, "proveedores");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos del proveedor se insertaron correctamente");
		return Utility.goToUrl(idEmpresa, "proveedores");
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getProveedor(@PathVariable int id, Model model) {

		
		return "proveedor/detalles";
	}
	
	/**
	 * Carga la informacion de los selects que se mostraran en la vista
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(Model model) {
		model.addAttribute("tiposIdentificacion", tipoIdentificacionDao.listExcluyeConsumidorFinal());
		model.addAttribute("ciudades", ciudadDao.list());
		model.addAttribute("tiposProveedor", tipoProveedorOClienteDao.list());
	}
}
