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

import ec.eac.sitac.dao.DocumentosAnuladosHome;
import ec.eac.sitac.dao.ExportacionHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.TipoComprobanteHome;
import ec.eac.sitac.dao.TipoExportacionImportacionHome;
import ec.eac.sitac.dao.TipoProveedorOClienteHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.model.DocumentosAnulados;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoExportacionImportacion;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.model.Venta;
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
@RequestMapping("empresas/{idEmpresa}/anulados")
public class DocumentosAnuladosController {
	@Autowired
	DocumentosAnuladosHome documentosAnuladosDao;
	@Autowired
	TipoComprobanteHome tipoComprobanteDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	
	private static final Logger logger = Logger.getLogger(DocumentosAnuladosController.class);
	
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
	 * Muestra una vista con la lista de anulados.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(@PathVariable int idEmpresa, Model model) {
		model.addAttribute("documentoAnulado", new DocumentosAnulados());
		model.addAttribute("anulados", documentosAnuladosDao.list(idEmpresa));

		return "anulados/home";
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
		
		model.addAttribute("documentoAnulado", new DocumentosAnulados());
		model.addAttribute("tiposComprobante", tipoComprobanteDao.listForVistaAnulados());
		model.addAttribute("tipoComprobante", new TipoComprobante());
		model.addAttribute("editandoDocumentoAnulado", new Boolean(false));
		
		return "anulados/datos";
	}

	/**
	 * Crea la vista para editar una exportación.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@PathVariable int idEmpresa, @RequestParam("id") int anuladoId, Model model) {
		
		DocumentosAnulados documentoAnulado = documentosAnuladosDao.findById(anuladoId);
		model.addAttribute("documentoAnulado",documentoAnulado);
		model.addAttribute("tipoComprobante", documentoAnulado.getTipoComprobante());
		model.addAttribute("tiposComprobante", tipoComprobanteDao.listForVistaAnulados());
		model.addAttribute("editandoDocumentoAnulado", new Boolean(true));

		return "anulados/datos";
	}

	/**
	 * Elimina una exportación.
	 *
	 * @return Redirecciona a la vista que muestra las exportaciones actualizadas.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") int anuladoId, Model model, RedirectAttributes redirectAttributes) {

		DocumentosAnulados documentoAnulado = documentosAnuladosDao.findById(anuladoId);
		documentosAnuladosDao.delete(documentoAnulado);
		
		redirectAttributes.addFlashAttribute("ok", "El documento fue eliminado satisfactoriamente.");
		return Utility.goToUrl(idEmpresa, "anulados");
	}
	
	/**
	 * Elimina documentos anulados via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, HttpServletRequest request) {
		
			for (int i = 0; i < datos.size(); i++) {
				DocumentosAnulados documentoAnulado = documentosAnuladosDao.findById(datos.get(i));
				documentosAnuladosDao.delete(documentoAnulado);
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
	public String guardar(@PathVariable int idEmpresa, @ModelAttribute("documentoAnulado") DocumentosAnulados documentoAnulado,
							@ModelAttribute("tipoComprobante") TipoComprobante tipoComprobante,
							@ModelAttribute("secuencia") String secuenciaIni,
							@ModelAttribute("secuenciaFinal") String secuenciaFin,
							@ModelAttribute("editandoDocumentoAnulado") Boolean editandoDocumentoAnulado,
							RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);

		int secuenciaInicial = Integer.valueOf(secuenciaIni);
		int secuenciaFinal = 0;
		if(!secuenciaFin.equals(""))
			secuenciaFinal = Integer.valueOf(secuenciaFin);
		//si no hay secuenciaFinal enotnces se guarda un solo documento anulado
		if(secuenciaFinal == 0){
			PuntoEmision ptoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
			//si el tipo de comprobante es 01 Factura, 07 Retencion, 04 nota de credito 
			if(tipoComprobante.getCodigo().equals("01")){
					if(ptoEmision.getSecFactura() == secuenciaInicial){
						documentoAnulado.setSecuencia(Integer.valueOf(ptoEmision.getSecFactura()));
						ptoEmision.setSecFactura(ptoEmision.getSecFactura()+1);
					}
				
			} else if(tipoComprobante.getCodigo().equals("07")){
				if(ptoEmision.getSecRetencion() == secuenciaInicial){
					documentoAnulado.setSecuencia(Integer.valueOf(ptoEmision.getSecRetencion()));
					ptoEmision.setSecRetencion(ptoEmision.getSecRetencion()+1);
				}
				
			}else if(tipoComprobante.getCodigo().equals("04")){
				if(ptoEmision.getSecNotaCredito() == secuenciaInicial){
					documentoAnulado.setSecuencia(Integer.valueOf(ptoEmision.getSecNotaCredito()));
					ptoEmision.setSecNotaCredito(ptoEmision.getSecNotaCredito()+1);
				}
			}
			
			documentoAnulado.setPuntoEmision(ptoEmision);
			try
			{

			if (editandoDocumentoAnulado) {
				documentosAnuladosDao.attachDirty(tipoComprobante.getCodigo(), idEmpresa, documentoAnulado);
			}
			else
			{
				documentosAnuladosDao.persist(tipoComprobante.getCodigo(), idEmpresa, documentoAnulado);
			}
			}
			catch (Exception ex) // error al guardar la exportacion
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al guardar el documento anulado");
				return Utility.goToUrl(idEmpresa, "anulados");
			}
			
		}else if(secuenciaFinal >0){
			
			for (int i = secuenciaInicial; i <= secuenciaFinal; i++) {
				PuntoEmision puntoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
				//si el tipo de comprobante es 01 Factura, 07 Retencion, 04 nota de credito 
				if(tipoComprobante.getCodigo().equals("01")){
						if(puntoEmision.getSecFactura() == i){
							puntoEmision.setSecFactura(puntoEmision.getSecFactura()+1);
						}
					
				} else if(tipoComprobante.getCodigo().equals("07")){
					if(puntoEmision.getSecRetencion() == i){
						puntoEmision.setSecRetencion(puntoEmision.getSecRetencion()+1);
					}
					
				}else if(tipoComprobante.getCodigo().equals("04")){
					if(puntoEmision.getSecNotaCredito() == i){
						puntoEmision.setSecNotaCredito(puntoEmision.getSecNotaCredito()+1);
					}
				}
				
				documentoAnulado.setPuntoEmision(puntoEmision);
				try
				{

				if (editandoDocumentoAnulado) {
					documentosAnuladosDao.attachDirty(tipoComprobante.getCodigo(), idEmpresa, documentoAnulado);
				}
				else
				{
					documentosAnuladosDao.persist(tipoComprobante.getCodigo(), idEmpresa, documentoAnulado);
				}
				}
				catch (Exception ex) // error al guardar el documento anulado
				{
					logger.error("This is Error message", ex);
					redirectAttributes.addFlashAttribute("error", "Error al guardar el documento anulado");
					return Utility.goToUrl(idEmpresa, "anulados");
				}
				
				documentoAnulado.setIdAnulado(0);
			}
			
		}
		
		redirectAttributes.addFlashAttribute("ok", "Los datos del documento anulado se guardaron correctamente");
		
		return Utility.goToUrl(idEmpresa, "anulados");
	}

	}
	

