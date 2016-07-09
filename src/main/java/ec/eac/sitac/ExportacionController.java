package ec.eac.sitac;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import ec.eac.sitac.dao.ExportacionHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.TipoComprobanteHome;
import ec.eac.sitac.dao.TipoExportacionImportacionHome;
import ec.eac.sitac.dao.TipoProveedorOClienteHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoExportacionImportacion;
import ec.eac.sitac.model.TipoProveedorOCliente;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/exportaciones.
 * Controlador de Exportacion.
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/exportaciones")
public class ExportacionController {
	@Autowired
	ExportacionHome exportacionDao;
	@Autowired
	TipoExportacionImportacionHome tipoExportacionImportacionDao;
	@Autowired
	TipoComprobanteHome tipoComprobanteDao;
	@Autowired
	TipoProveedorOClienteHome tipoProveedorOClienteDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	UsuarioHome usuarioDao;
	
	private static final Logger logger = Logger.getLogger(ExportacionController.class);


	/**
	 * Da el formato 'yyyy-MM-dd' a la fecha que se envió por el formulario.
	 *
	 * @since 2015
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	/**
	 * Muestra una vista con la lista las exportaciones.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(@PathVariable int idEmpresa, Model model) {
		model.addAttribute("exportacion", new Exportacion());
		model.addAttribute("exportaciones", exportacionDao.list(idEmpresa));

		return "exportacion/home";
	}

	/**
	 * Crea la vista para registrar una nueva exportacion.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(@PathVariable int idEmpresa, Model model) {
		
		cargarListasSelect(idEmpresa, model);
		
		model.addAttribute("exportacion", new Exportacion());
		
		model.addAttribute("tipoExportacionImportacion", new TipoExportacionImportacion());
		model.addAttribute("tipoComprobante", new TipoComprobante());
		model.addAttribute("editandoExportacion", new Boolean(false));
		//model.addAttribute("tipoCliente", new TipoProveedorOCliente());
		model.addAttribute("personalEmpresa", new PersonalEmpresa());
		
		return "exportacion/datos";
	}

	/**
	 * Crea la vista para editar una exportación.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@PathVariable int idEmpresa, @RequestParam("id") int exportacionId, Model model) {
		
		cargarListasSelect(idEmpresa, model);
		Exportacion exportacion = exportacionDao.findById(exportacionId);
		
		model.addAttribute("exportacion", exportacion);
		model.addAttribute("tipoExportacionImportacion", exportacion.getTipoExportacionImportacion());
		model.addAttribute("tipoComprobante", exportacion.getTipoComprobante());
		model.addAttribute("editandoExportacion", new Boolean(true));
		model.addAttribute("personalEmpresa", exportacion.getPersonalEmpresa());

		return "exportacion/datos";
	}

	/**
	 * Elimina una exportación.
	 *
	 * @return Redirecciona a la vista que muestra las exportaciones actualizadas.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") int exportacionId, Model model, RedirectAttributes redirectAttributes) {

		Exportacion exportacion = exportacionDao.findById(exportacionId);
		try
		{
			exportacionDao.delete(exportacion);
		}
		catch (Exception ex)
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al eliminar la exportación");
			return Utility.goToUrl(idEmpresa, "exportaciones");
		}

		return Utility.goToUrl(idEmpresa, "exportaciones");
	}
	
	/**
	 * Elimina exportaciones via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, RedirectAttributes redirectAttributes) {

		for (int i = 0; i < datos.size(); i++) {
			Exportacion exportacion = exportacionDao.findById(datos.get(i));
			try
			{
				exportacionDao.delete(exportacion);
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al eliminar la exportación");
				return false;
			}
		}
		return true;
	}

	/**
	 * Guarda los datos de la exportación.
	 *
	 * @return Redirecciona a la vista que muestra las exportaciones actualizadas.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, @ModelAttribute("exportacion") Exportacion exportacion,
							@ModelAttribute("tipoExportacionImportacion") TipoExportacionImportacion tipoExportacionImportacion,
							@ModelAttribute("tipoComprobante") TipoComprobante tipoComprobante,
							@ModelAttribute("editandoExportacion") Boolean editandoExportacion,
							//@ModelAttribute("tipoCliente") TipoProveedorOCliente tipoCliente,
							@ModelAttribute("personalEmpresa") PersonalEmpresa cliente,
							RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		// validando que la persona sea un proveedor valido
	/**	PersonalEmpresa persona = personalEmpresaDao.findById(exportacion.getPersonalEmpresa().getId());
		
		if (persona == null || !persona.isProveedor()) {
			redirectAttributes.addFlashAttribute("error", "La exportación no se pudo insertar porque debe ingresar un proveedor existente");
			return Utility.goToUrl(idEmpresa, "exportaciones");
		}*/
		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		PuntoEmision ptoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		
		exportacion.setPuntoEmision(ptoEmision);
		exportacion.setPersonalEmpresa(cliente);
		
		try
		{

		if (editandoExportacion) {
			exportacionDao.attachDirty(tipoExportacionImportacion.getIdTipoExpImp(), tipoComprobante.getCodigo(), idEmpresa, exportacion);
		}
		else
		{
			exportacionDao.persist(tipoExportacionImportacion.getIdTipoExpImp(), tipoComprobante.getCodigo(), idEmpresa, exportacion);
		}
		}
		catch (Exception ex) // error al guardar la exportacion
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al guardar la exportación");
			return Utility.goToUrl(idEmpresa, "exportaciones");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos de la exportación se guardaron correctamente");
		
		return Utility.goToUrl(idEmpresa, "exportaciones");
	}

	/**
	 * Muestra una vista con los detalles de una exportación, dado el id.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "exportacion/{id}", method = RequestMethod.GET)
	public String getExportacion(@PathVariable int id, Model model) {


		return "exportacion/detalles";
	}
	
	/**
	 * Carga la informacion de los selects que se mostraran en la vista
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(int idEmpresa, Model model) {
		model.addAttribute("tiposExportacionImportacion", tipoExportacionImportacionDao.list());
		model.addAttribute("tiposComprobante", tipoComprobanteDao.listForVistaExportacion());
		model.addAttribute("tiposCliente", tipoProveedorOClienteDao.list());
		model.addAttribute("clientes", personalEmpresaDao.list(ClienteProveedorEnum.CLIENTE, idEmpresa));
	}

}
