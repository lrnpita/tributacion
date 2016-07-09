package ec.eac.sitac;


import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.eac.sitac.dao.CiudadHome;
import ec.eac.sitac.dao.CompraHome;
import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.EmpresaVsClienteHome;
import ec.eac.sitac.dao.EmpresaVsProductoHome;
import ec.eac.sitac.dao.EmpresaVsProveedorHome;
import ec.eac.sitac.dao.EmpresaVsTrabajadorHome;
import ec.eac.sitac.dao.ExportacionHome;
import ec.eac.sitac.dao.ImportacionHome;
import ec.eac.sitac.dao.MovimientoHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.TipoAmbienteHome;
import ec.eac.sitac.dao.TipoEmisionHome;
import ec.eac.sitac.dao.TipoIdentificacionHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.dao.VentaHome;
import ec.eac.sitac.model.Ciudad;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.TipoAmbiente;
import ec.eac.sitac.model.TipoEmision;
import ec.eac.sitac.model.TipoIdentificacion;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/empresa. 
 * Controlador de Clientes.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
@Controller
@RequestMapping("")
public class EmpresaController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	EmpresaHome empresaDao;
	@Autowired
	TipoIdentificacionHome tipoIdentificacionDao;
	@Autowired
	TipoAmbienteHome tipoAmbienteDao;
	@Autowired
	TipoEmisionHome tipoEmisionDao;
	@Autowired
	CiudadHome ciudadDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	VentaHome ventaDao;
	@Autowired
	CompraHome compraDao;
	@Autowired
	ImportacionHome importacionDao;
	@Autowired
	ExportacionHome exportacionDao;
	@Autowired
	MovimientoHome movimientoDao;
	@Autowired
	EmpresaVsClienteHome empresaVsClienteDao;
	@Autowired
	EmpresaVsProveedorHome empresaVsProveedorDao;
	@Autowired
	EmpresaVsTrabajadorHome empresaVsTrabajadorDao;
	@Autowired
	EmpresaVsProductoHome empresaVsProductoDao;
	
	private static final Logger logger = Logger.getLogger(EmpresaController.class);
	
	
	/**
	 * Muestra una vista con el espacio de trabajo de cada empresa. De esta vista se podrá acceder a todo lo relacionado con la empresa.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = {"/admin/empresas/{idEmpresa}", "/empresas/{idEmpresa}"})
	public String empresa(@PathVariable int idEmpresa, HttpServletRequest request, Model model) {

		  request.getSession().setAttribute("idEmpresa", idEmpresa);
		  
		  model.addAttribute("cantidadEmpresas", new Long(empresaDao.count()));
		  model.addAttribute("cantidadVentas", new Long(ventaDao.count(idEmpresa)));
		  model.addAttribute("cantidadCompras", new Long(compraDao.count(idEmpresa)));
		  model.addAttribute("cantidadImportaciones", new Long(importacionDao.count(idEmpresa)));
		  model.addAttribute("cantidadExportaciones", new Long(exportacionDao.count(idEmpresa)));
		  model.addAttribute("cantidadMovimientos", new Long(movimientoDao.count(idEmpresa)));
		  model.addAttribute("cantidadUsuarios", new Long(usuarioDao.count(idEmpresa)));
		  model.addAttribute("cantidadClientes", new Long(empresaVsClienteDao.count(idEmpresa)));
		  model.addAttribute("cantidadProveedores", new Long(empresaVsProveedorDao.count(idEmpresa)));
		  model.addAttribute("cantidadProductos", new Long(empresaVsProductoDao.count(idEmpresa)));
		  model.addAttribute("cantidadTrabajadores", new Long(empresaVsTrabajadorDao.count(idEmpresa)));
		  
		//return "redirect:/empresas/" + idEmpresa;
		  return "home";
	}
	
	/**
	 * Muestra una vista con la lista las empresas.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/empresas")
	public String home(Model model) {
		model.addAttribute("empresas", empresaDao.list());
	
		return "empresa/lista";
	}
	
	/**
	 * Muestra una vista con la lista las empresas para el admin.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/admin/empresas")
	public String homeAdmin(Model model) {
		model.addAttribute("empresas", empresaDao.list());
	
		return "empresa/home";
	}
	
	
	/**
	 * Crea la vista para adicionar una nueva empresa.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/admin/empresas/datos", method = RequestMethod.GET)
	public String nuevo(Model model) {
		
		cargarListasSelect(model);
		
		model.addAttribute("empresa", new Empresa());
		model.addAttribute("contador", new PersonalEmpresa());
		model.addAttribute("repLegal", new PersonalEmpresa());
		model.addAttribute("tipoAmbiente", new TipoAmbiente());
		model.addAttribute("tipoEmision", new TipoEmision());
		model.addAttribute("tipoIdentificacion", new TipoIdentificacion());
		model.addAttribute("ciudad", new Ciudad());
		model.addAttribute("editandoEmpresa", new Boolean(false));
		
		return "empresa/datos";
	}
	
	/**
	 * Crea la vista para editar una empresa.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/admin/empresas/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") int empresaId, Model model) {
		
		cargarListasSelect(model);
		
		Empresa empresa = empresaDao.findById(empresaId);
		model.addAttribute("empresa", empresa);
		
		model.addAttribute("contador", empresa.getPersonalEmpresaByIdContador());
		model.addAttribute("repLegal", empresa.getPersonalEmpresaByIdRepLegal());
		model.addAttribute("tipoAmbiente", empresa.getTipoAmbiente());
		model.addAttribute("tipoEmision", empresa.getTipoEmision());
		model.addAttribute("ciudad", empresa.getCiudad());
		model.addAttribute("editandoEmpresa", new Boolean(true));
		
		return "empresa/datos";
	}
	
	/**
	 * Elimina una empresa.
	 *
	 * @return Redirecciona a la vista que muestra las empresas actualizadas.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/admin/empresas/eliminar", method = RequestMethod.GET)
	public String eliminar(@RequestParam("id") int empresaId, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Empresa empresa = empresaDao.findById(empresaId);
		int idEmpresa = (int) request.getSession().getAttribute("idEmpresa");

		// verificando que el cliente se pueda eliminar sin violar llaves foraneas
		if (empresa.getPuntoEmisions().size() > 0 || empresa.getClientes().size() > 0 || empresa.getCompras().size() > 0 || empresa.getProveedores().size() > 0 || empresa.getMovimientos().size() > 0
				|| empresa.getVentas().size() > 0 || empresa.getImportacions().size() > 0 || empresa.getTrabajadores().size() > 0 || empresa.getExportaciones().size() > 0 || empresa.getProductos().size() > 0)  {

			redirectAttributes.addFlashAttribute("error", "No se puede eliminar la empresa debido a que existe información asociada en el sistema.");
			return "redirect:/admin/empresas";

		} else if(idEmpresa == empresaId){ // si el id pasado por parámetro es igual el id de la empresa enla q se encuentra logueado el usuario
			redirectAttributes.addFlashAttribute("error", "No se puede eliminar la empresa debido a que el usuario está logueado en ella");
			return "redirect:/admin/empresas";

		} else { // se puede eliminar la empresa
			try
			{
				empresaDao.delete(empresa);
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al eliminar la empresa");
				return "redirect:/admin/empresas";
			}
		}
		
		redirectAttributes.addFlashAttribute("ok", "La empresa se eliminó satisfactoriamente");
		return "redirect:/admin/empresas";
	}
	
	/**
	 * Elimina empresas via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/admin/empresas/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int idEmpresa = (int) request.getSession().getAttribute("idEmpresa");
		// Eliminar una o varias empresas
		for (int i = 0; i < datos.size(); i++) {
			Empresa empresa = empresaDao.findById(datos.get(i));

			// verificando que el cliente se pueda eliminar sin violar llaves foraneas
			if (empresa.getPuntoEmisions().size() > 0 || empresa.getClientes().size() > 0 || empresa.getCompras().size() > 0 || empresa.getProveedores().size() > 0 || empresa.getMovimientos().size() > 0
					|| empresa.getVentas().size() > 0 || empresa.getImportacions().size() > 0 || empresa.getTrabajadores().size() > 0 || empresa.getExportaciones().size() > 0 || empresa.getProductos().size() > 0)  {
				return false;
			} else if(idEmpresa == datos.get(i)){ // si el id pasado por parámetro es igual el id de la empresa enla q se encuentra logueado el usuario
				return false;
			}
			try
			{
				empresaDao.delete(empresa);
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Guarda los datos de la empresa.
	 *
	 * @return Redirecciona a la vista que muestra las empresas actualizadas.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/admin/empresas/guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute Empresa empresa, 
			@ModelAttribute("contador") PersonalEmpresa contador,
			@ModelAttribute("repLegal") PersonalEmpresa repLegal,
			//@ModelAttribute("tipoAmbiente") TipoAmbiente tipoAmbiente,
			//@ModelAttribute("tipoEmision") TipoEmision tipoEmision,
			@ModelAttribute("ciudad") Ciudad ciudad,
			@ModelAttribute("editandoEmpresa") boolean editandoEmpresa,
			final RedirectAttributes redirectAttributes) {
		
		// transformando los valores que vienen por post
		String[] codigoParams = ciudad.getCodigo().split(","); // ciudad, ambiente, emision, identificacion
		String idCiudad = codigoParams[0];
		char idTipoAmbiente = codigoParams[1].charAt(0);
		char idTipoEmision = codigoParams[2].charAt(0);
		
		
		String[] idParams = String.valueOf(contador.getId()).split(","); // contador, repLegal
		
		String[] nombreParams = empresa.getNombre().split(","); // empresa, contador, representante legal
		String nuevoNombreContador = nombreParams[1];
		String nuevoNombreRepLegal = nombreParams[2];
		
		empresa.setNombre(nombreParams[0]);
		
		PersonalEmpresa contadorCompleto = personalEmpresaDao.findById(idParams[0]);
		PersonalEmpresa repLegalCompleto = personalEmpresaDao.findById(idParams[1]);
		
		if(contadorCompleto == null){
			contadorCompleto = new PersonalEmpresa();
			contadorCompleto.setId(idParams[0]);
			contadorCompleto.setNombre(nuevoNombreContador);
			TipoIdentificacion tipoIdContador = tipoIdentificacionDao.findById("04");
			contadorCompleto.setTipoIdentificacion(tipoIdContador);
		}
		
		if(repLegalCompleto == null){
			repLegalCompleto = new PersonalEmpresa();
			repLegalCompleto.setId(idParams[1]);
			repLegalCompleto.setNombre(nuevoNombreRepLegal);
			TipoIdentificacion tipoIdRep = tipoIdentificacionDao.findById(repLegal.getTipoIdentificacion().getCodigo());
			repLegalCompleto.setTipoIdentificacion(tipoIdRep);
		}
		
		// el contador y el representante legal solo pueden ser actualizados si pertenecen a una sola empresa
		if (editandoEmpresa) { 
			if (!contadorCompleto.getNombre().equalsIgnoreCase(nuevoNombreContador) && contadorCompleto.getEmpresasForIdContador().size() > 1) {
					redirectAttributes.addFlashAttribute("error","Este contador pertenece también a otra empresa.");
					return "redirect:/admin/empresas";
				}
			
			if (!repLegalCompleto.getNombre().equalsIgnoreCase(nuevoNombreRepLegal) && repLegalCompleto.getEmpresasForIdRepLegal().size() > 1) {
				redirectAttributes.addFlashAttribute("error","Este representante legal pertenece también a otra empresa.");
				return "redirect:/admin/empresas";
			}
		}
		
		// guardando la informacion
		empresaDao.prepararObjetoEmpresa(contadorCompleto, repLegalCompleto, idTipoAmbiente, idTipoEmision, idCiudad, empresa);
		
		contadorCompleto.getEmpresasForIdContador().add(empresa);
		repLegalCompleto.getEmpresasForIdRepLegal().add(empresa);

		personalEmpresaDao.attachDirty(contadorCompleto);
		personalEmpresaDao.attachDirty(repLegalCompleto);
		empresaDao.attachDirty(empresa);
		
		redirectAttributes.addFlashAttribute("ok","Datos guardados satisfactoriamente.");
		return "redirect:/admin/empresas";
	}
	
	
	/**
	 * Carga la informacion de los selects que se mostraran en las vistas de crear/editar una empresa
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(Model model) {
		model.addAttribute("tiposIdentificacion", tipoIdentificacionDao.listExcluyeConsumidorFinal());
		model.addAttribute("tiposEmision", tipoEmisionDao.list());
		model.addAttribute("tiposAmbiente", tipoAmbienteDao.list());
		model.addAttribute("ciudades", ciudadDao.list());
	}
	
}
