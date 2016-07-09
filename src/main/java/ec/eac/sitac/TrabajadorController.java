package ec.eac.sitac;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import ec.eac.sitac.dao.CiudadHome;
import ec.eac.sitac.dao.CondicionDeTrabajadorHome;
import ec.eac.sitac.dao.MovimientoHome;
import ec.eac.sitac.dao.PaisHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.ProvinciaHome;
import ec.eac.sitac.dao.ResidenciaHome;
import ec.eac.sitac.dao.TipoIdentificacionHome;
import ec.eac.sitac.dao.TipoSalarioHome;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.CondicionDeTrabajador;
import ec.eac.sitac.model.Pais;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.Residencia;
import ec.eac.sitac.model.TipoIdentificacion;
import ec.eac.sitac.model.TipoSalario;
import ec.eac.sitac.util.CheckDigit;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/trabajador.
 * Controlador de Clientes.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/trabajadores")
public class TrabajadorController {
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	ResidenciaHome residenciaDao;
	@Autowired
	PaisHome paisDao;
	@Autowired
	CondicionDeTrabajadorHome condicionDeTrabajadorDao;
	@Autowired
	TipoIdentificacionHome tipoIdentificacionDao;
	@Autowired
	TipoSalarioHome tipoSalarioDao;
	@Autowired
	ProvinciaHome provinciaDao;
	@Autowired
	CiudadHome ciudadDao;
	@Autowired
	MovimientoHome movimientoDao;
	
	private static final Logger logger = Logger.getLogger(TrabajadorController.class);

	
	/**
	 * Da el formato 'yyyy-MM-dd' a la fecha que se envió por el formulario.
	 *
	 * @since 1.0
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	/**
	 * Muestra una vista con la lista los trabajadores.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(@PathVariable int idEmpresa, Model model) {
		model.addAttribute("trabajador", new PersonalEmpresa());
		model.addAttribute("trabajadores", personalEmpresaDao.list(PersonalEmpresaEnum.TRABAJADOR, idEmpresa));
	
		return "trabajador/home";
	}
	
	/**
	 * Crea la vista para adicionar un nuevo trabajador.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model) {
		
		cargarListasSelect(model);
		
		model.addAttribute("trabajador", new PersonalEmpresa());
		model.addAttribute("residencia", new Residencia());
		model.addAttribute("pais", new Pais());
		model.addAttribute("condicionDeTrabajador", new CondicionDeTrabajador());
		model.addAttribute("tipoSalario", new TipoSalario());
		model.addAttribute("ciudad", new Ciudad());
		model.addAttribute("tipoIdentificacion", new TipoIdentificacion());
		model.addAttribute("tipoIdentificacionNewTrabajador",new TipoIdentificacion());
		model.addAttribute("editandoTrabajador", new Boolean(false));
		
		return "trabajador/datos";
	}
	
	/**
	 * Crea la vista para editar un trabajador.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") String trabajadorId, Model model) {
		
		cargarListasSelect(model);
		
		PersonalEmpresa trabajador = personalEmpresaDao.findById(trabajadorId);
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("residencia", trabajador.getResidencia());
		model.addAttribute("pais", trabajador.getPais());
		model.addAttribute("condicionDeTrabajador", trabajador.getCondicionDeTrabajador());
		model.addAttribute("tipoSalario", trabajador.getTipoSalario());
		model.addAttribute("ciudad", trabajador.getCiudad());
		model.addAttribute("tipoIdentificacion", trabajador.getTipoIdentificacion());
		
		if (trabajador.getTipoIdentificacionNewTrabajador() != null) {
			model.addAttribute("tipoIdentificacionNewTrabajador", trabajador.getTipoIdentificacionNewTrabajador());
		} else {
			model.addAttribute("tipoIdentificacionNewTrabajador", new TipoIdentificacion());
		}
		
		model.addAttribute("editandoTrabajador", new Boolean(true));
	
		return "trabajador/datos";
	}
	
	/**
	 * Elimina un trabajador.
	 *
	 * @return Redirecciona a la vista que muestra los trabajadores actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") String trabajadorId, Model model, RedirectAttributes redirectAttributes) {
		
		PersonalEmpresa trabajador = personalEmpresaDao.findById(trabajadorId);
		
		// verificando que el trabajador se pueda eliminar sin violar llaves foraneas
		if (trabajador.getMovimientos().size() > 0) {
					
				redirectAttributes.addFlashAttribute("error", "No se puede eliminar al trabajador debido a que existe información asociada a él en el sistema.");
				return Utility.goToUrl(idEmpresa, "trabajadores");
		}
		
		try {
			personalEmpresaDao.delete(trabajador);
		} catch (Exception e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("error", "Error al eliminar el trabajador");
			return Utility.goToUrl(idEmpresa, "trabajadores");
		}
		
		redirectAttributes.addFlashAttribute("ok", "El trabajador fue eliminado satisfactoriamente.");
		return Utility.goToUrl(idEmpresa, "trabajadores");
	}
	
	/**
	 * Guarda los datos del trabajador.
	 *
	 * @return Redirecciona a la vista que muestra los trabajadores actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, @ModelAttribute("trabajador") PersonalEmpresa trabajador, 
						@ModelAttribute("ciudad") Ciudad customCiudad, 
						@ModelAttribute("tipoIdentificacion") TipoIdentificacion customTipoIdentificacion, 
						@ModelAttribute("tipoIdentificacionNewTrabajador") TipoIdentificacion customTipoIdentificacionNewTrabajador, 
						@ModelAttribute("pais") Pais customPais,
						@ModelAttribute("editandoTrabajador") boolean editandoTrabajador,
						RedirectAttributes redirectAttributes) {

		// convirtiendo el arreglos los valores compuestos que vienen seperados por comas desde el formulario
		String[] codigoParams = customTipoIdentificacion.getCodigo().split(","); // 0 == tipoId,  1 = pais, 2 = ciudad, 3 = tipoIdNewTrab
		String[] idParams = trabajador.getId().split(","); // 0 = trabajador,  1 = residencia, 2 = tipo salario, 3 = condicion trabajador
		
		
		int idCondicionDeTrabajador = Integer.parseInt(idParams[3]);
		int idResidencia = Integer.parseInt(idParams[1]);
		int idTipoSalario = Integer.parseInt(idParams[2]);
		
		String codigoTipoIdentificacionNewTrabajador = (idCondicionDeTrabajador == 3 || idCondicionDeTrabajador == 4) ? codigoTipoIdentificacionNewTrabajador = codigoParams[3] : null; 
		String codigoTipoIdentificacion = codigoParams[0];
		String codigoPais = codigoParams[1];
		String codigoCiudad = codigoParams[2];
		trabajador.setId(idParams[0]);
		
		// Validando ID del trabajador
		if (codigoTipoIdentificacion.equals("05")) { // 05 = Cédula
			if (!CheckDigit.isCedula(trabajador.getId())) {
				redirectAttributes.addFlashAttribute("error", "La cédula especificada para el trabjador no es válida");
				return Utility.goToUrl(idEmpresa, "trabajadores/datos");
			}
		} else if (codigoTipoIdentificacion.equals("04")) { // 04 = RUC

			String thirdDigit = trabajador.getId().substring(2,3);

			if ( thirdDigit.equals("6") ) { // Sector público

				if (!CheckDigit.isRUCPublicSector(trabajador.getId())) {
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el trabajador no es válido para el sector público");
					return Utility.goToUrl(idEmpresa, "trabajadores/datos");
				}

			} else if ( Integer.parseInt(thirdDigit) >= 0 &&  Integer.parseInt(thirdDigit) <= 5) { // Personas naturales
				String rucPN = trabajador.getId().substring(0,10);
				if (!CheckDigit.isCedula(rucPN)) { // el ruc de persona natural es la cédula con 001 al final
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el trabajador no es válido para una persona natural");
					return Utility.goToUrl(idEmpresa, "trabajadores/datos");

				} 
			} else if ( thirdDigit.equals("9") ) { // Sector privado o personas extranjeras

				if (!CheckDigit.isRUCPrivateSector(trabajador.getId())) {
					redirectAttributes.addFlashAttribute("error", "El RUC especificado para el trabajador no es válido para el sector privado o para personas naturales extranjeras");
					return Utility.goToUrl(idEmpresa, "trabajadores/datos");

				}
			} else {
				redirectAttributes.addFlashAttribute("error", "El tercer dígito del RUC especificado no es válido");
				return Utility.goToUrl(idEmpresa, "trabajadores/datos");
			}
		}
		
		try
		{
		if (editandoTrabajador) {
			personalEmpresaDao.attachDirtyTrabajador(codigoTipoIdentificacion, codigoTipoIdentificacionNewTrabajador, codigoCiudad, idResidencia, codigoPais, idCondicionDeTrabajador, idTipoSalario, idEmpresa, trabajador);
		}
		else
		{
			if (personalEmpresaDao.findById(trabajador.getId()) != null) {
				redirectAttributes.addFlashAttribute("error", "Ya existe un trabajador con esa identificación");
				return Utility.goToUrl(idEmpresa, "trabajadores");
			}
			personalEmpresaDao.persistTrabajador(codigoTipoIdentificacion, codigoTipoIdentificacionNewTrabajador, codigoCiudad, idResidencia, codigoPais, idCondicionDeTrabajador, idTipoSalario, idEmpresa, trabajador);
		}
		}
		catch (Exception ex) // tratando de insertar un objeto que no es ni cliente ni proveedor
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al guardar el trabajador");
			return Utility.goToUrl(idEmpresa, "trabajadores");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos del trabajador se insertaron correctamente");
		return Utility.goToUrl(idEmpresa, "trabajadores");
	}
	
	/**
	 * Muestra una vista con los detalles de un trabajador, dado el id.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/trabajador/{id}", method = RequestMethod.GET)
	public String getTrabajador(@PathVariable int id, Model model) {

		
		return "trabajador/detalles";
	}
	
	/**
	 * Carga la informacion de los selects que se mostraran en la vista de agregar/editar un trabajador 
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(Model model) {
		List<TipoIdentificacion> listTipoIdent = Lists.newArrayList(tipoIdentificacionDao.listExcluyeConsumidorFinal());
		listTipoIdent.removeIf(p -> p.getCodigo().equals("04"));
		model.addAttribute("tiposIdentificacion", listTipoIdent);
		model.addAttribute("ciudades", ciudadDao.list());
		model.addAttribute("tiposSalario", tipoSalarioDao.list());
		model.addAttribute("condicionesDeTrabajador", condicionDeTrabajadorDao.list());
		model.addAttribute("paises", paisDao.list());
		model.addAttribute("residencias", residenciaDao.list());
	}
	
	/**
	 * Elimina trabajadores via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<String> datos, RedirectAttributes redirectAttributes) {

		boolean ok = true; 
		for (int i = 0; i < datos.size(); i++) {

			int cantidadTrabajadores = movimientoDao.findByIdTrabajador(datos.get(i)).size();
			if( cantidadTrabajadores != 0){
				ok = false;
			} else {
				PersonalEmpresa trabajador = personalEmpresaDao.findById(datos.get(i));
				try {
					personalEmpresaDao.delete(trabajador);
				} catch (Exception e) {
					logger.error("This is Error message", e);
					redirectAttributes.addFlashAttribute("error", "Error al eliminar el trabajador");
					return false;
				}
				
			}
		}
		return ok;
	}
}
