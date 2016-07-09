package ec.eac.sitac;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.runtime.internal.PerObjectMap;
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

import ec.eac.sitac.dao.IdentificacionCreditoGastoHome;
import ec.eac.sitac.dao.ImportacionHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.TipoComprobanteHome;
import ec.eac.sitac.dao.TipoExportacionImportacionHome;
import ec.eac.sitac.dao.TipoTransaccionHome;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.IdentificacionCreditoGasto;
import ec.eac.sitac.model.Importacion;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoExportacionImportacion;
import ec.eac.sitac.model.TipoTransaccion;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/importaciones.
 * Controlador de Importaciones.
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/importaciones")
public class ImportacionController {
	@Autowired
	ImportacionHome importacionDao;
	@Autowired
	IdentificacionCreditoGastoHome identificacionCreditoGastoDao;
	@Autowired
	TipoExportacionImportacionHome tipoExportacionImportacionDao;
	@Autowired
	TipoComprobanteHome tipoComprobanteDao;
	@Autowired
	TipoTransaccionHome tipoTransaccionDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	
	private static final Logger logger = Logger.getLogger(ImportacionController.class);

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
	 * Muestra una vista con la lista las importaciones.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home( @PathVariable int idEmpresa, Model model) {
		model.addAttribute("importacion", new Importacion());
		model.addAttribute("importaciones", importacionDao.list(idEmpresa));

		return "importacion/home";
	}

	/**
	 * Crea la vista para registrar una nueva importación.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model, @PathVariable int idEmpresa) {
		
		cargarListasSelect(model, idEmpresa);
		
		model.addAttribute("importacion", new Importacion());
		model.addAttribute("identificacionCreditoGasto", new IdentificacionCreditoGasto());
		model.addAttribute("tipoExportacionImportacion", new TipoExportacionImportacion());
		model.addAttribute("tipoComprobante", new TipoComprobante());
		model.addAttribute("tipoTransaccion", new TipoTransaccion());
		model.addAttribute("personalEmpresa", new PersonalEmpresa());
		model.addAttribute("editandoImportacion", new Boolean(false));

		return "importacion/datos";
	}

	/**
	 * Crea la vista para editar una importación.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") int importacionId, Model model, @PathVariable int idEmpresa) {

		cargarListasSelect(model, idEmpresa);
		
		Importacion importacion = importacionDao.findById(importacionId);
		model.addAttribute("importacion", importacion);
		
		model.addAttribute("identificacionCreditoGasto",importacion.getIdentificacionCreditoGasto());
		model.addAttribute("tipoExportacionImportacion", importacion.getTipoExportacionImportacion());
		model.addAttribute("tipoComprobante", importacion.getTipoComprobante());
		model.addAttribute("tipoTransaccion", importacion.getTipoTransaccion());
		model.addAttribute("personalEmpresa", importacion.getPersonalEmpresa());
		model.addAttribute("editandoImportacion", new Boolean(true));
		
		return "importacion/datos";
	}

	/**
	 * Elimina una importación.
	 *
	 * @return Redirecciona a la vista que muestra las importaciones actualizadas.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") int importacionId, Model model, RedirectAttributes redirectAttributes) {

		Importacion importacion = importacionDao.findById(importacionId);
		try
		{
			importacionDao.delete(importacion);
		}
		catch (Exception ex){
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al eliminar el importación");
			return Utility.goToUrl(idEmpresa, "importaciones");
		}

		return Utility.goToUrl(idEmpresa, "importaciones");
	}
	
	/**
	 * Elimina importaciones via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, RedirectAttributes redirectAttributes) {
		
			for (int i = 0; i < datos.size(); i++) {
				Importacion importacion = importacionDao.findById(datos.get(i));
				try
				{
					importacionDao.delete(importacion);
				}
				catch (Exception ex){
					logger.error("This is Error message", ex);
					redirectAttributes.addFlashAttribute("error", "Error al eliminar el importación");
					return false;
				}
			}
			return true;
	}

	/**
	 * Guarda los datos de la importación.
	 *
	 * @return Redirecciona a la vista que muestra las importaciones actualizadas.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, @ModelAttribute("importacion") Importacion importacion,
						@ModelAttribute("identificacionCreditoGasto") IdentificacionCreditoGasto identificacionCreditoGasto,
						@ModelAttribute("tipoExportacionImportacion") TipoExportacionImportacion tipoExportacionImportacion,
						@ModelAttribute("personalEmpresa") PersonalEmpresa proveedor,
						@ModelAttribute("editandoImportacion") Boolean editandoImportacion, RedirectAttributes redirectAttributes) {
		
		String[] params = identificacionCreditoGasto.getCodigo().split(","); // 0 == idCreditoGasto, 1 = tipoComprobante, 2 = tipoTransaccion
		String idCreditoGasto = params[0];
		String tipoComprobante = params[1];
		String tipoTransaccion = params[2];
		
		importacion.setPersonalEmpresa(proveedor);
		
		// validando que la persona sea un proveedor valido--lo quité poruqe liste los proveedores directamente
	/**	PersonalEmpresa persona = personalEmpresaDao.findById(importacion.getPersonalEmpresa().getId());
		
		if (persona == null || !persona.isProveedor()) {
			redirectAttributes.addFlashAttribute("error", "La importación no se pudo insertar porque debe ingresar un proveedor existente");
			return Utility.goToUrl(idEmpresa, "importaciones");
		}	*/
		
		try
		{
			if (editandoImportacion) {
				importacionDao.attachDirty(tipoExportacionImportacion.getIdTipoExpImp(), tipoComprobante, tipoTransaccion, idCreditoGasto, idEmpresa, importacion);
			}
			else
			{
				importacionDao.persist(tipoExportacionImportacion.getIdTipoExpImp(), tipoComprobante, tipoTransaccion, idCreditoGasto, idEmpresa, importacion);
			}
		}
		catch (Exception ex) // error al guardar la exportacion
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", ex.getMessage());
			return Utility.goToUrl(idEmpresa, "importaciones");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos de la importación se guardaron correctamente");
		return Utility.goToUrl(idEmpresa, "importaciones");

	}

	/**
	 * Muestra una vista con los detalles de una importación, dado el id.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "importacion/{id}", method = RequestMethod.GET)
	public String getImportacion(@PathVariable int id, Model model) {


		return "importacion/detalles";
	}
	
	private void cargarListasSelect(Model model, int idEmpresa) {
		model.addAttribute("identificacionesCreditoGasto", identificacionCreditoGastoDao.list());
		model.addAttribute("tiposExportacionImportacion", tipoExportacionImportacionDao.list());
		model.addAttribute("tiposComprobante", tipoComprobanteDao.listForVistaImportacion());
		model.addAttribute("tiposTransaccion", tipoTransaccionDao.list());
		model.addAttribute("proveedores", personalEmpresaDao.list(ClienteProveedorEnum.PROVEEDOR, idEmpresa));
	}
	
	/**
	 * Controlador q recibe datos Json para calcular el porcentaje de retención en dependencia del tipo de transaccion
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value ={ "/datos/porcentajeIR", "/editar/porcentajeIR"}, method = RequestMethod.POST)
	public @ResponseBody Float inpuestoRenta(@RequestBody String datos, @PathVariable int idEmpresa) {

		String[] t = datos.split("=");
		String codigo = t[1];
		float porcentaje = tipoTransaccionDao.getPorcentajeById(codigo);

		return porcentaje;
	}
}
