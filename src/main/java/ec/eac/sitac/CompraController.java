package ec.eac.sitac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.bytecode.analysis.Util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Port;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ocsp.Request;
import org.hibernate.tool.hbmlint.Detector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itextpdf.text.DocumentException;

import ec.eac.sitac.dao.CompraHome;
import ec.eac.sitac.dao.DetallesCompraHome;
import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.EstadoSRIHome;
import ec.eac.sitac.dao.PaisHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.RetencionHome;
import ec.eac.sitac.dao.TipoCompraHome;
import ec.eac.sitac.dao.TipoComprobanteHome;
import ec.eac.sitac.dao.TipoPagoHome;
import ec.eac.sitac.dao.TipoPagoSegunLugarHome;
import ec.eac.sitac.dao.TipoTransaccionHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.esigef.Comprobante;
import ec.eac.sitac.esigef.ComprobanteRetencion;
import ec.eac.sitac.esigef.ESIGEF_XMLObject;
import ec.eac.sitac.esigef.Impuesto;
import ec.eac.sitac.esigef.Impuestos;
import ec.eac.sitac.esigef.InfoCompRetencion;
import ec.eac.sitac.esigef.InfoTributaria;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EstadoSRI;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.Pais;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Retencion;
import ec.eac.sitac.model.TarifaIva;
import ec.eac.sitac.model.TipoCompra;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoPago;
import ec.eac.sitac.model.TipoPagoSegunLugar;
import ec.eac.sitac.model.TipoPagoVsCompra;
import ec.eac.sitac.model.TipoTransaccion;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.model.VentaVsProducto;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.MimeMessegerSend;
import ec.eac.sitac.util.PDFGenerator;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.StringResponse;
import ec.eac.sitac.util.Utility;
import ec.eac.sitac.util.XMLManager;

/**
 * Maneja las peticiones que vienen por sitac/compra.
 * Controlador de Compras
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/compras")
public class CompraController {
    @Autowired
    ServletContext context; 
	@Autowired
	CompraHome compraDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	TipoComprobanteHome tipoComprobanteDao;
	@Autowired
	TipoTransaccionHome tipoTransaccionDao;
	@Autowired
	TipoPagoHome tipoPagoDao;
	@Autowired
	TipoPagoSegunLugarHome tipoPagoSegunLugarDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	@Autowired
	PaisHome paisDao;
	@Autowired
	TipoCompraHome tipoCompraDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	EmpresaHome empresaDao;
	@Autowired
	EstadoSRIHome estadoSRIDao;
	@Autowired
    JavaMailSender mailSender;
	@Autowired
	DetallesCompraHome detallesCompraDao;
	@Autowired
	RetencionHome retencionDao;
	
	String nombreArchivoXML = null;
	String directorioEmpresaActual = null;
	
	private static final Logger logger = Logger.getLogger(CompraController.class);
	
	/**
	 * Da el formato 'MM/dd/yyyy' a la fecha que se envió por el formulario.
	 *
	 * @since 1.0
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		
		//binder.setValidator(fileValidator);
	}

	/**
	 * Muestra una vista con la lista las compras.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(@PathVariable int idEmpresa, HttpServletRequest request, Model model) {
		model.addAttribute("compra", new Compra());
		
		ec.eac.sitac.util.CustomFile fileModel = new ec.eac.sitac.util.CustomFile();
		model.addAttribute("file", fileModel);
		
		System.out.println(request.getSession().getServletContext().getRealPath("/WEB-INF/files/"));
		System.out.println(context.getRealPath("/ESIGEF"));
		
		model.addAttribute("compras", compraDao.list(idEmpresa));
		
		return "compra/home";
	}

	/**
	 * Crea la vista para adicionar una nueva compra.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model, @PathVariable int idEmpresa, HttpServletRequest request) {
		
		cargarListasSelect(model, idEmpresa);
		model.addAttribute("compra", new Compra());
		model.addAttribute("tipoComprobante", new TipoComprobante());
		model.addAttribute("tipoComprobanteModificado", new TipoComprobante());
		model.addAttribute("tipoTransaccion", new TipoTransaccion());
		//model.addAttribute("tipoCompra",new TipoCompra());
		model.addAttribute("tipoPagoSegunLugar", new TipoPagoSegunLugar());
		model.addAttribute("tiposPago", tipoPagoDao.list());
		model.addAttribute("pais",new Pais());
		model.addAttribute("proveedor", new PersonalEmpresa());
		model.addAttribute("estado", new EstadoSRI());
		model.addAttribute("retencion", new Retencion());
		model.addAttribute("editandoCompra", new Boolean(false));
		//faltan el objeto ComprobanteModificado

		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		PuntoEmision puntoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		model.addAttribute("puntoEmision", puntoEmision);
		
		return "compra/datos";
	}

	/**
	 * Crea la vista para editar una compra.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") int compraId, Model model, @PathVariable int idEmpresa) {

		Compra compra = compraDao.findById(compraId);
		
		cargarListasSelect(model, idEmpresa);
		model.addAttribute("compra", compra);
		model.addAttribute("tipoComprobante", compra.getTipoComprobante());
		model.addAttribute("tipoCompra", compra.getTipoCompra());
		model.addAttribute("tipoPagoSegunLugar", compra.getTipoPagoSegunLugar());
		model.addAttribute("retencion", compra.getRetencion());
		model.addAttribute("detallesList", compra.getDetallesCompras());
		model.addAttribute("pais", compra.getPais());
		model.addAttribute("proveedor", compra.getPersonalEmpresa());
		model.addAttribute("estado", compra.getEstado());
		model.addAttribute("puntoEmision", compra.getPuntoEmision());
		model.addAttribute("editandoCompra", new Boolean(true));
		model.addAttribute("tiposPago", tipoCompraDao.list());
		
		Set<DetallesCompra> detalles = compra.getDetallesCompras();
		Set<String> tiposPagoSelected = new HashSet<String>();
		float totalDocumento = 0;
		
		if(compra.getTipoComprobanteModificado()== null){
			model.addAttribute("tipoComprobanteModificado", new TipoComprobante());
		}else{
			model.addAttribute("tipoComprobanteModificado", compra.getTipoComprobanteModificado());
		}

		for (DetallesCompra detalleCompra : detalles) {
			totalDocumento += detalleCompra.getBaseImpobible0() + detalleCompra.getBaseNoObjetoIva() + detalleCompra.getBaseImponible12() + detalleCompra.getRIvalorIva();
		}
		for (TipoPagoVsCompra tiposPagoVsCompra : compra.getTipoPagoVsCompras()) {
			tiposPagoSelected.add(tiposPagoVsCompra.getTipoPago().getCodigo());
		}
		
		model.addAttribute("tiposPagosSelected", tiposPagoSelected);
		model.addAttribute("totalDocumento", totalDocumento);
		model.addAttribute("valorAPagar", totalDocumento - compra.getRetencion().getTotalRetencion());
		
		return "compra/datos";
	}

	/**
	 * Elimina una compra.
	 *
	 * @return Redirecciona a la vista que muestra las compras actualizadas.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, HttpServletRequest request, @RequestParam("id") int compraId, Model model, RedirectAttributes redirectAttributes) {
		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);

		if (usuario.getRol().getIdRol() == 2) {
			redirectAttributes.addFlashAttribute("error", "Ups!, usted no tiene permisos para eliminar la compra.");
			return Utility.goToUrl(idEmpresa, "compras");
		}
		else {
			Compra compra = compraDao.findById(compraId);
			// elminando los detalles de la compra
			for (DetallesCompra detalle : compra.getDetallesCompras()) {
				try 
				{
					detallesCompraDao.delete(detalle);
				}
				catch (Exception ex)
				{
					logger.error("This is Error message", ex);
					redirectAttributes.addFlashAttribute("error", "Error al elminar la compra");
					return Utility.goToUrl(idEmpresa, "compras");
				}
			}

			try
			{
				compraDao.delete(compra);
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la compra");
				return Utility.goToUrl(idEmpresa, "compras");
			}

			try
			{
				retencionDao.delete(compra.getRetencion());
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la compra");
				return Utility.goToUrl(idEmpresa, "compras");
			}

			redirectAttributes.addFlashAttribute("ok", "La compra fue eliminada satisfactoriamente.");
			return Utility.goToUrl(idEmpresa, "compras");
		}

	}
	
	/**
	 * Elimina compras via AJAX.
	 *
	 * @return true si los elementos fueron eliminados correctamente
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		for (int i = 0; i < datos.size(); i++) {
			Compra compra = compraDao.findById(datos.get(i));
			// elminando los detalles de la compra
			for (DetallesCompra detalle : compra.getDetallesCompras()) {
				try 
				{
					detallesCompraDao.delete(detalle);
				}
				catch (Exception ex)
				{
					logger.error("This is Error message", ex);
					redirectAttributes.addFlashAttribute("error", "Error al elminar la compra");
					return false;
				}
			}

			try
			{
				compraDao.delete(compra);
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la compra");
				return false;
			}

			try
			{
				retencionDao.delete(compra.getRetencion());
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la compra");
				return false;
			}
		}
		return true;
	}

	/**
	 * Guarda los datos de la compra.
	 *
	 * @return Redirecciona a la vista que muestra las compras actualizadas.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, 
			HttpServletRequest request,
			@ModelAttribute("compra") Compra compra, 
			@ModelAttribute("proveedor") PersonalEmpresa proveedor, 
			@ModelAttribute("estado") EstadoSRI estado, 
			@ModelAttribute("retencion") Retencion retencion, 
			@ModelAttribute("editandoCompra") Boolean editandoCompra, RedirectAttributes redirectAttributes) {
		
		Set<DetallesCompra> detallesList = new HashSet<DetallesCompra>();
		Set<TipoPagoVsCompra> tipospagoList = new HashSet<TipoPagoVsCompra>();
		float RIRretIrTotal = 0;
		float RIretIvaTotal = 0;
		
		//String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		String tipoPagoSegunLugar = request.getParameter("tipoPagoSegunLugar.codigo");
		String tipoComprobante = request.getParameter("tipoComprobante.codigo");
		String pais = request.getParameter("pais.codigo");
		String tipoCompra = request.getParameter("tipoCompra.codigo");
		String[] tipoPago = request.getParameterValues("tipoPago");
		String tipoComprobanteModificado = request.getParameter("tipoComprobanteModificado.codigo");
		
		// llenando los detalles de la compra aqui se cogen los valores multivaluados
		// seleccionando los valores del producto q vienen de la tabla
		String[] tipoTransaccion = request.getParameterValues("codigo");
		String[] baseNoObjetoIva = request.getParameterValues("baseNoObjetoIva");
		String[] baseImpobible0 = request.getParameterValues("baseImpobible0");
		String[] baseImponible12 = request.getParameterValues("baseImponible12");
		String[] iva = request.getParameterValues("porcentajeIva");
		String[] RIRbaseNoObjIva = request.getParameterValues("RIRbaseNoObjIva");
		String[] RIRbaseImp0 = request.getParameterValues("RIRbaseImp0");
		String[] RIRbaseImp12 = request.getParameterValues("RIRbaseImp12");
		String[] RIRret = request.getParameterValues("RIRret");
		String[] RIRretIr = request.getParameterValues("RIRretIr"); // valor de la retencion total de impuesto a la renta
		String[] RIvalorIva = request.getParameterValues("RIvalorIva");
		String[] RIret = request.getParameterValues("RIret");
		String[] RIretIva = request.getParameterValues("RIretIva"); //valor de la retencion total de iva
		String[] idDetallesCompras = request.getParameterValues("idDetalleCompra"); //valor para saber la cantidad de detalles que pueden existir
		// Creando las relaciones entre compras y tipos de pago
		if(tipoPago != null){
			for (int i = 0; i < tipoPago.length; i++) {
				TipoPagoVsCompra tipoPagoVsCompra = new TipoPagoVsCompra(compra, tipoPagoDao.findById(tipoPago[i]));
				tipospagoList.add(tipoPagoVsCompra);
			}
		}
		// Detalles de las compras
		for (int i = 0; i < idDetallesCompras.length; i++) {
			TipoTransaccion tipoTransaccionNew = tipoTransaccionDao.findById(tipoTransaccion[i]);
				DetallesCompra detalle = detallesCompraDao.findById(Integer.valueOf(idDetallesCompras[i]));
				if (detalle == null) {
					detalle = new DetallesCompra(Integer.valueOf(idDetallesCompras[i]), compra, tipoTransaccionNew, Float.valueOf(baseNoObjetoIva[i]), Float.valueOf(baseImpobible0[i]), Float.valueOf(baseImponible12[i]), Float.valueOf(iva[i]), Float.valueOf(RIRbaseNoObjIva[i]), Float.valueOf(RIRbaseImp0[i]), Float.valueOf(RIRbaseImp12[i]), Float.valueOf(RIRret[i]), Float.valueOf(RIvalorIva[i]), Float.valueOf(RIret[i]));
				} else {
					detalle.setBaseImpobible0(Float.valueOf(baseImpobible0[i]));
					detalle.setBaseImponible12(Float.valueOf(baseImponible12[i]));
					detalle.setBaseNoObjetoIva(Float.valueOf(baseNoObjetoIva[i]));
					detalle.setPorcentajeIva(Float.valueOf(iva[i]));
					detalle.setRIRbaseImp0(Float.valueOf(RIRbaseImp0[i]));
					detalle.setRIRbaseImp12(Float.valueOf(RIRbaseImp12[i]));
					detalle.setRIRbaseNoObjIva(Float.valueOf(RIRbaseNoObjIva[i]));
					detalle.setRIRret(Float.valueOf(RIRret[i]));
					detalle.setRIret(Float.valueOf(RIret[i]));
					detalle.setRIvalorIva(Float.valueOf(RIvalorIva[i]));
					detalle.setTipoTransaccion(tipoTransaccionNew);
					detalle.setCompra(compra);
				}
			detallesList.add(detalle);
			RIRretIrTotal += Float.valueOf(RIRretIr[i]);	
			RIretIvaTotal += Float.valueOf(RIretIva[i]);
		}
		// Retenciones
		retencion.setRetencionIR(RIRretIrTotal);
		retencion.setRetencionIVA(RIretIvaTotal);
		
		EstadoSRI estadoSRi = estadoSRIDao.findById(1);
		compra.setEstado(estadoSRi);
		compra.setRegistroContable(compra.getFechaEmision());
		
		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		PuntoEmision puntoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		compra.setPuntoEmision(puntoEmision);
		
		if (editandoCompra) {
			try
			{
				compraDao.attachDirty(compra, retencion, tipoComprobante, tipoComprobanteModificado, proveedor.getId(), tipoPagoSegunLugar, tipospagoList, pais, tipoCompra, detallesList, idEmpresa);
			}
			catch (Exception ex) // error al guardar la compra
			{
				redirectAttributes.addFlashAttribute("error", "Error al guardar la compra");
				logger.error("This is Error message", ex);
				return Utility.goToUrl(idEmpresa, "compras");
			}

		} else { 
			// Creando una nueva compra
			try 
			{
				puntoEmision.setSecFactura(puntoEmision.getSecFactura()+1);
				compraDao.persist(compra, retencion, tipoComprobante, tipoComprobanteModificado, proveedor.getId(), tipoPagoSegunLugar, tipospagoList, pais, tipoCompra, detallesList, idEmpresa);
			}
			catch (DataIntegrityViolationException ex)
			{
				redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar la compra");
				logger.error("This is Error message", ex);
				return Utility.goToUrl(idEmpresa, "compras");
			}
		}

			// Se crea el PDF de retencion
			try 
			{
				PDFGenerator.generarPDFRetencion(compra, context);
			} 
			catch (FileNotFoundException | DocumentException e) {
				redirectAttributes.addFlashAttribute("error", "Ocurrió un error al enviar el correo al proveedor");
				logger.error("This is Error message", e);
				return Utility.goToUrl(idEmpresa, "compras");
			}

			// Enviando mensaje correo al proveedor
			try{
				MimeMessegerSend messengerSend = new MimeMessegerSend(mailSender, context);
				messengerSend.enviarCompRetencion(compra);
			}
			catch (Exception e){
				redirectAttributes.addFlashAttribute("error", "Ocurrió un error al enviar el correo al proveedor");
				logger.error("This is Error message", e);
				return Utility.goToUrl(idEmpresa, "compras");
			}
			// Eliminando el pdf de retencion una vez enviado
			String directorio = context.getRealPath("/resources");
			File file = new File(directorio + File.separator + compra.getIdCompra() + ".pdf");
			if (file.delete())
				   System.out.println("El fichero ha sido borrado satisfactoriamente");
				else
				   System.out.println("El fichero no puede ser borrado");
		
		redirectAttributes.addFlashAttribute("ok", "Los datos de la compra se guardaron correctamente");
		return Utility.goToUrl(idEmpresa, "compras");
	}
	
	/**
	 * Carga un XML generado por el ESIGEF dado el id la empresa que lo va a cargar. El xml es copiado en la carpeta de los XML correspondiente a la empresa.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/esigef", method = RequestMethod.GET)
	public String cargarXML(@PathVariable int idEmpresa, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("CastorConfig.xml");
		XMLManager converter = (XMLManager) appContext.getBean("XMLManager");
		
		Compra compra = new Compra();
		
		// Convirtiendo el XML a un objeto del tipo ESIGEF_XMLObject
		ESIGEF_XMLObject compraESIGEF = null;
		try {
			// El documento necesita set modificado para eliminar etiquetas innecesarias
			FileSystem system = FileSystems.getDefault();
			Path dirArchivo = system.getPath(directorioEmpresaActual + File.separator + nombreArchivoXML);
			// Haciendo una copia del XML para modificar solamente la copia
			Path dirNuevo = system.getPath(directorioEmpresaActual + File.separator + "Copia_"); // FIXME poner nombre unico al archivo o bloquear el acceso al mismo lock
			Files.deleteIfExists(dirNuevo);
			Files.copy(dirArchivo, dirNuevo);
			
			// Convirtiendo
			if ( !converter.initialize(dirNuevo.toString()) ) {
				redirectAttributes.addFlashAttribute("error", "El archivo no pudo ser leído. Por favor, verifique que el archivo es un archivo XML bien formado y que cumple con el formato requerido.");
				return Utility.goToUrl(idEmpresa, "compras");
			}
			
			// Eliminando las etiquetas innecesarias	
			converter.removeCDataTag();
			converter.removeTag("mensajes");
			converter.removeLastTag("comprobanteRetencion");
			
			try
			{
				converter.saveChanges();
			}
			catch (TransformerException e)
			{
				// Revisar mensaje y manipulacion de la excepcion
				redirectAttributes.addFlashAttribute("error", "El archivo fue cargado, pero no pudo ser leído. Por favor, verifique que el archivo XML está bien formado (well-formed).");
				logger.error("This is Error message", e);
				return Utility.goToUrl(idEmpresa, "compras");
			}
			
			compraESIGEF = (ESIGEF_XMLObject)converter.convertFromXMLToObject(dirNuevo.toString()); // FIXME el camino del XML debe estar en correpondencia con el nombre de la empresa
			
			if (compraESIGEF == null) {
				redirectAttributes.addFlashAttribute("error", "Error al cargar el archivo. Intente nuevamente, si el problema persiste póngase en contacto con el administrador del sistema.");
				return Utility.goToUrl(idEmpresa, "compras");
			}
			
			// Validando el XML convertido
			String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
			Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
			Empresa empresa = empresaDao.findById(idEmpresa);
			
			String error = validarESIGEFXMLInfo(compraESIGEF, idEmpresa, nombreUsuario);
			if ( !error.isEmpty()) {
				redirectAttributes.addFlashAttribute("error", error);
				return Utility.goToUrl(idEmpresa, "compras");
			}
			
			// Pasando los datos del objeto XML al objeto Compra del SITAC para despúes mostrarlo en la vista listo para editarse
			compra = transformarEnCompra(compraESIGEF, empresa, usuario);
			compra.setArchivoXMLAsociado(nombreArchivoXML);
			
			// eliminando del servidor la copia del archivo XML
			Files.deleteIfExists(dirNuevo);
			
		} catch (Exception e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("error", "Error al cargar el archivo XML");
			return Utility.goToUrl(idEmpresa, "compras");
		}
		
		cargarListasSelect(model,idEmpresa);
		float totalDocumento = 0;
		float totalRet = 0;
		int contador = 1;
		for (DetallesCompra detalleCompra : compra.getDetallesCompras()) {
			String atributo = "detalleCompra" + contador;
			model.addAttribute(atributo, detalleCompra);
			contador++;
			
			totalDocumento += detalleCompra.getBaseImpobible0() + detalleCompra.getBaseNoObjetoIva() + detalleCompra.getBaseImponible12() + detalleCompra.getRIvalorIva();
			totalRet += (detalleCompra.getRIRbaseImp0() + detalleCompra.getRIRbaseImp12() + detalleCompra.getRIRbaseNoObjIva())*detalleCompra.getRIRret()/100;
		}
		
		cargarListasSelect(model, idEmpresa);
		model.addAttribute("compra", compra);
		model.addAttribute("tipoComprobante", compra.getTipoComprobante());
		model.addAttribute("tipoComprobanteModificado", new TipoComprobante());
		model.addAttribute("tipoCompra", compra.getTipoCompra());
		model.addAttribute("tipoPagoSegunLugar",  new TipoPagoSegunLugar());
		model.addAttribute("pais", new Pais());
		model.addAttribute("proveedor", compra.getPersonalEmpresa());
		model.addAttribute("editandoCompra", new Boolean(true));
		model.addAttribute("estado", compra.getEstado());
		model.addAttribute("totalDocumento", totalDocumento);
		model.addAttribute("valorAPagar", totalDocumento - compra.getRetencion().getTotalRetencion());
		model.addAttribute("retencion", compra.getRetencion());
		model.addAttribute("detallesList", compra.getDetallesCompras());
		model.addAttribute("tiposPago", tipoPagoDao.list());
		model.addAttribute("puntoEmision", compra.getPuntoEmision());
		
		return "compra/datos";
	}
	
	/**
	 * Valida que la información contenida en el XML que viene del ESIGEF se correponda con la empresa que está cargando el fichero.
	 * 
	 * @return Error de validación o null en caso de que no haya ningún error.
	 * 
	 * @since 1.0
	 */
	private String validarESIGEFXMLInfo(ESIGEF_XMLObject xmlObject, int idEmpresa, String nombreUsuario) {
		
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		Empresa empresa = empresaDao.findById(idEmpresa);
		
		PuntoEmision ptoEmision =  puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		if (ptoEmision == null) {
			return "El punto de emisión especificado en el archivo XML no existe en el sistema. ";
		}
		
		InfoTributaria infoTributaria = xmlObject.getComprobante().getComprobanteRetencion().getInfoTributaria();
		if (infoTributaria == null) {
			return "El documento XML importado no posee la información tributaria correspondiente: etiqueta <infoTributaria>";
		}
		
		String puntoEmisionXml = infoTributaria.getEstab() + infoTributaria.getPtoEmi();
		
		if (!puntoEmisionXml.equals(ptoEmision.getSerie())) { // FIXME revisar si esto es lo que hay que comparar
			return "El punto de emisión del XML no coincide con el punto de emisión de la empresa en la que está tratando de importar dicho XML";
		}

		if (!infoTributaria.getRuc().equals(empresa.getRucContribuyente())) { // FIXME revisar si esto es lo que hay que comparar
			return "El RUC del XML no coincide con el RUC de la empresa en la que está tratando de importar dicho XML";
		}
		
		if (!infoTributaria.getAmbiente().equals(String.valueOf(empresa.getTipoAmbiente().getCodigo())) 
				|| !infoTributaria.getTipoEmision().equals(String.valueOf(empresa.getTipoEmision().getCodigo()))
				) {// FIXME revisar si esto es lo que hay que comparar
			return "El tipo de emisión o el tipo de ambiente del XML no coincide con el el tipo de emisión o el tipo de ambiente de la empresa en la que está tratando de importar el archivo XML";		
		}
		
		PersonalEmpresa proveedor = personalEmpresaDao.findById(xmlObject.getComprobante().getComprobanteRetencion().getInfoCompRetencion().getIdentificacionSujetoRetenido());
		if (proveedor == null) {
			return "El proveedor especificado en el archivo XML no existe en el sistema. Por favor, agregue el proveedor al sistema e intente nuevamente.";
		}
		
		return "";
	}
	
	/**
	 * Mapea el objeto XML del esigef a los objetos del sistema
	 * 
	 * @param xmlObject Objeto correspondiente al XML del ESIGEF
	 * @param empresa Empresa en la cual se cargó el XML
	 * @param usuario usuario que realizó la carga
	 * 
	 * @since 1.0
	 */
	private Compra transformarEnCompra(ESIGEF_XMLObject xmlObject, Empresa empresa, Usuario usuario) throws Exception {
		Compra compra = new Compra();
		Retencion retencion = new Retencion();
		Comprobante comprobante = xmlObject.getComprobante();
		ComprobanteRetencion comprobanteRetencion = comprobante.getComprobanteRetencion();
		InfoTributaria infoTributaria = comprobanteRetencion.getInfoTributaria();
		InfoCompRetencion infoCompRetencion = comprobanteRetencion.getInfoCompRetencion();
		Impuestos impuestos = comprobanteRetencion.getImpuestos();
		List<Impuesto> listaImpuestos = impuestos.getListaImpuestos();
		
		// retención
		retencion.setSerieRetencion(infoTributaria.getEstab() + infoTributaria.getPtoEmi());  // construyendo la serie de la retención del XML
		retencion.setSecuenciaRetencion(Integer.valueOf(infoTributaria.getSecuencial())); // secuencial de la retención del XML
		
		// punto de emision
		PuntoEmision ptoEmision =  puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), empresa.getIdEmpresa());
		ptoEmision.setSecRetencion(Integer.valueOf(infoTributaria.getSecuencial()));
		compra.setPuntoEmision(ptoEmision);
		
		retencion.setClaveAcceso(infoTributaria.getClaveAcceso());
		
		// tipo de comprobante----18 de agosto de 2016 se cambió por el codDocSustento
	/*	TipoComprobante tipoComprobante = tipoComprobanteDao.findById(infoTributaria.getCodDoc());
		if (tipoComprobante == null) {
			throw new Exception ("El código del tipo de comprobante del XML no existe en la BD del SITAC");
		}
		compra.setTipoComprobante(tipoComprobante); 
		*/
		
		// estado de la compra
		EstadoSRI estadoSRI = estadoSRIDao.findByName(xmlObject.getEstado());
		if (estadoSRI == null){
			throw new Exception ("No existe ningún estado con nombre: " + xmlObject.getEstado() + " en la BD del SITAC");
		}
		compra.setEstado(estadoSRI);

		// Proveedor
		PersonalEmpresa proveedor = personalEmpresaDao.findById(infoCompRetencion.getIdentificacionSujetoRetenido());
		compra.setPersonalEmpresa(proveedor);
		
		// Código de autorizacion
		retencion.setAutorizacionRetencion(xmlObject.getNumeroAutorizacion());
		
		// fecha de emision, de registro contable y fecha de anulacion
		String srtFechaEmision = infoCompRetencion.getFechaEmision();
		String srtFechaAutorizacion = xmlObject.getFechaAutorizacion().substring(0, 10);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			// se verifica en que formato viene la fecha
			if(srtFechaAutorizacion.contains("-")){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				date = format.parse(srtFechaAutorizacion);
				//s = dateFormat.format(date);
			 }else {
				 date = dateFormat.parse(srtFechaAutorizacion);
			 }
			
			retencion.setFechaRetencion(date);
			
			date = dateFormat.parse(srtFechaEmision);
			compra.setRegistroContable(date);
			compra.setFechaEmision(date);
		} 
		catch (ParseException ex)	{
			logger.error("This is Error message", ex);
			throw new Exception("Error al leer una de las fechas que contiene el archivo XML. Por favor, verifique que todas las fechas se encuentran en el formato: dd/MM/yyyy");
		}
		
		// se crea la lista de detalles de la compra
		Set<DetallesCompra> listaDetalles = new HashSet<DetallesCompra>(0) ;
		float iva = 0;
		int contador = 0;
		float riRet = 0;
		float valorIva = 0;
		float rirBaseImp12 = 0;
		float rirRet = 0;
		TipoTransaccion tipoTransaccion = null;
		String numFactura = null;
		int listaImpSize = listaImpuestos.size();
		
		for (Impuesto impuesto : listaImpuestos) {
			// si el codigo es 2 entonces los impuestos son de IVA
			if(impuesto.getCodigo().equals("2")) {
				valorIva = Float.valueOf(impuesto.getBaseImponible());
				riRet = Float.valueOf(impuesto.getPorcentajeRetener());
			} // sino de IR
			else if (impuesto.getCodigo().equals("1")) { 
				rirBaseImp12 = Float.valueOf(impuesto.getBaseImponible());
				rirRet = Float.valueOf(impuesto.getPorcentajeRetener());
				try {
					tipoTransaccion = tipoTransaccionDao.findById(impuesto.getCodigoRetencion());
				} catch (Exception e) {
					throw new Exception("No se encontró un tipo transacción con código "+ impuesto.getCodigoRetencion());
				}
			}
			contador ++;

			// comprobando que los detalles de la compra tengan guardados los impuestos de iva y de RIR
			if ((listaImpSize==4 || listaImpSize==2) && contador == 2) {
				iva = (rirBaseImp12*12)/100 == valorIva ? 12 : 14;
				DetallesCompra detalle = new DetallesCompra((float) 0, (float) 0, rirBaseImp12, iva, (float) 0, (float) 0, rirBaseImp12, rirRet, valorIva, riRet);
				detalle.setTipoTransaccion(tipoTransaccion);
				
				listaDetalles.add(detalle);
				contador = 0;
			}

			//guardar num de la factura
			numFactura = impuesto.getNumDocSustento();
		}
		
		float totalRetIR = 0;
		float totalRetIva = 0;
				
		for (DetallesCompra detallesCompra : listaDetalles) {
			 totalRetIR += ((detallesCompra.getRIRbaseImp0() + detallesCompra.getRIRbaseImp12() + detallesCompra.getRIRbaseNoObjIva()) * detallesCompra.getRIRret())/100;	
			 totalRetIva += (detallesCompra.getRIvalorIva() * detallesCompra.getRIret())/100;
		}
		
		retencion.setRetencionIR(totalRetIR);
		retencion.setRetencionIVA(totalRetIva);
		retencion.setTotalRetencion(totalRetIR + totalRetIva);
		compra.setDetallesCompras(listaDetalles);
		compra.setRetencion(retencion);
		compra.setSerieFactura(numFactura.substring(0, 6));
		compra.setSecFactura(Integer.valueOf(numFactura.substring(7, 15)));
		
		// 18 de agosto de 2016 - tipo de comprobante
	/*	TipoCompra tipoCompra = tipoCompraDao.findById(listaImpuestos.get(0).getCodDocSustento());
		if (tipoCompra == null) 
			throw new Exception ("No existe ningún tipo de compra con el código: " + listaImpuestos.get(1).getCodDocSustento() + " en la Base de Datos del sistema. Por favor, cambie el tipo de compra que viene en el archivo XML o agregue el nuevo tipo de compra a la Base de Datos.");
		compra.setTipoCompra(tipoCompra);
		*/
		TipoComprobante tipoComprobante = tipoComprobanteDao.findById(listaImpuestos.get(0).getCodDocSustento());
		if (tipoComprobante == null) 
			throw new Exception ("No existe ningún tipo de comprobante con el código: " + listaImpuestos.get(0).getCodDocSustento() + " en la Base de Datos del sistema. Por favor, cambie el tipo de compra que viene en el archivo XML o agregue el nuevo tipo de compra a la Base de Datos.");
		compra.setTipoComprobante(tipoComprobante);
		return compra;
	}
	
	/**
	 * Carga la información de los selects que se mostrarán en la vista
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(Model model, int idEmpresa) {
		model.addAttribute("tiposComprobante", tipoComprobanteDao.listForVistaCompra());
		model.addAttribute("tiposTransaccion", tipoTransaccionDao.list());
		model.addAttribute("proveedores", personalEmpresaDao.list(ClienteProveedorEnum.PROVEEDOR, idEmpresa));
		model.addAttribute("tiposPagoSegunLugar", tipoPagoSegunLugarDao.list());
		model.addAttribute("tiposPuntoEmision", puntoEmisionDao.list(idEmpresa)); 
		model.addAttribute("tiposCompra", tipoCompraDao.list());
		model.addAttribute("paises", paisDao.list());
	
	}
	
	/**
	 * Carga el archivo XML al servidor, se ejecuta al hacer clic en el botón Cargar de la vista de compras.
	 * 
	 * @param file Fichero a cargar
	 * @param idEmpresa Empresa en la que se registrará la carga
	 * 
	 * @since 1.0
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated ec.eac.sitac.util.CustomFile file, RedirectAttributes redirectAttributes,@PathVariable int idEmpresa,
			BindingResult result) {

		//String returnVal = "successFile";
		if (result.hasErrors()) {
			//returnVal = "file";
			redirectAttributes.addFlashAttribute("error", "Error al cargar el archivo. Intente nuevamente, si el problema persiste póngase en contacto con el administrador del sistema.");
			return Utility.goToUrl(idEmpresa, "compras");
		} else {			
			// Subiendo el archivo
			MultipartFile multipartFile = file.getFile();
			nombreArchivoXML = multipartFile.getOriginalFilename(); // guardando el nombre de archivo en uno de los atributos de la clase
			
			// Creando la carpeta para los XML, en el servidor
			String pathAux = "/ESIGEF" + "/Empresa" + String.valueOf(idEmpresa);
			directorioEmpresaActual = context.getRealPath(pathAux);
			File carpeta = new File(directorioEmpresaActual);
			carpeta.mkdirs();
			
			File localFile = new File(directorioEmpresaActual + File.separatorChar + nombreArchivoXML);
	    	FileOutputStream os = null;
	    	
	    	try {
	    		
	    		os = new FileOutputStream(localFile);
	    		os.write(multipartFile.getBytes());
	    		
	    	} 
	    	catch (Exception ex) {
	    		logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al cargar el archivo. Si el problema persiste póngase en contacto con el administrador del sistema.");
				return Utility.goToUrl(idEmpresa, "compras");
	    	}
	    	finally {
	    		if (os != null) {
	    			try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	    		}
	    	}
		}
		return Utility.goToUrl(idEmpresa, "compras/esigef");
	}
	
	/**
	 * Buscar compras por fecha.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String search (@PathVariable int idEmpresa, @ModelAttribute("fecha") String search, Model model) {
		
		String[] datosFecha = search.split(" ");
		int anno = Integer.parseInt(datosFecha[1]);
		int mes = Utility.getNumeroMes(datosFecha[0]); // buscar número del mes 
		
		model.addAttribute("compras", compraDao.list(idEmpresa, anno, mes));
		
		return "compra/home";
	}
	
	/**
	 * Controlador q recibe datos Json para calcular el porcentaje de retención en dependencia del tipo de transaccion
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value ={ "/datos/porcentajeIR", "/editar/porcentajeIR", "/esigef/porcentajeIR"}, method = RequestMethod.POST)
	public @ResponseBody Float inpuestoRenta(@RequestBody String datos, @PathVariable int idEmpresa) {

		String[] t = datos.split("=");
		String codigo = t[1];
		float porcentajeIR = tipoTransaccionDao.getPorcentajeById(codigo);

		return porcentajeIR;
	}
	
	
	/**
	 * Controlador q recibe datos Json para buscar en nombre del proveedor
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value ={ "/datos/BuscarNombreProveedor", "/editar/BuscarNombreProveedor", "/esigef/BuscarNombreProveedor"}, method = RequestMethod.POST)
	public @ResponseBody StringResponse BuscarNombreProveedor(@RequestBody String datos) {

		String[] t = datos.split("=");
		String idProveedor = t[1];
		String nombreProveedor = personalEmpresaDao.findNombreProveedorById(idProveedor);

		return new StringResponse(nombreProveedor);
	}
	
	
	/**
	 * Controlador q recibe datos Json para calcular el porcentaje de Iva
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value ={ "/datos/porcentajeIva", "/editar/porcentajeIva", "/esigef/porcentajeIva"}, method = RequestMethod.POST)
	public @ResponseBody int inpuestoIva(@RequestBody String datos, @PathVariable int idEmpresa) {

		String[] s = datos.split("&");
		String[] id = s[0].split("=");
		String idProveedor = id[1];
		String[] idct = s[1].split("=");
		String codigoTransaccion = idct[1];

		PersonalEmpresa proveedor = personalEmpresaDao.findById(idProveedor);
		Empresa empresa = empresaDao.findById(idEmpresa);
		TipoTransaccion tipoTransaccion = tipoTransaccionDao.findById(codigoTransaccion);
		//int porcentaje = tipoTransaccion.getPorcentaje(); 
		int porcentaje = 0;

		if(idProveedor.length() > 10 ){
			// si el 3er digito del ruc es 0, 1, 2, 3, 4 o 5 es Persona natural
			if(Integer.parseInt(idProveedor.substring(2, 3))>=0 && Integer.parseInt(idProveedor.substring(2, 3))<=5){
				if (codigoTransaccion.equals("303") || codigoTransaccion.equals("320")) {
				return	porcentaje = 100;
				} else {
					return	porcentaje = 70;
				}
			} 
		} // entre especiales
		else if(!empresa.getNoResolucionContribEspecial().equals("") && proveedor.getContribuyenteEspecial()){
			if(tipoTransaccion.getBien()){
				return	porcentaje = 10;
			} else {
				return porcentaje = 20;
			}
		}// especial->lleva cont->no lleva cont
		else if (!empresa.getNoResolucionContribEspecial().equals("")) {
			if(tipoTransaccion.getBien()){
				return porcentaje = 30;
			} else {
				return porcentaje = 70;
			}
		} // lleva cont->no lleva cont
		else if(empresa.getLlevaContabilidad() && !proveedor.getLlevaContabilidad()){
			if(tipoTransaccion.getBien()){
				return porcentaje = 30;
			} else {
				return porcentaje = 70;
			}
		} 

		return porcentaje;
	}
}
