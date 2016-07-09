package ec.eac.sitac;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.EmpresaVsClienteHome;
import ec.eac.sitac.dao.EstadoSRIHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.ProductoHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.RetencionHome;
import ec.eac.sitac.dao.TipoComprobanteHome;
import ec.eac.sitac.dao.TipoVentaHome;
import ec.eac.sitac.dao.TipoVentaSegunPagoHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.dao.VentaHome;
import ec.eac.sitac.dao.VentaVsProductoHome;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.Discapacidad;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.EmpresaVsCliente;
import ec.eac.sitac.model.EstadoSRI;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Retencion;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoVenta;
import ec.eac.sitac.model.TipoVentaSegunPago;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.model.VentaVsProducto;
import ec.eac.sitac.util.ClienteProveedorEnum;
import ec.eac.sitac.util.ExcelManager;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.StringResponse;
import ec.eac.sitac.util.Utility;

/**
 * Mapeo de las columnas del excel con los campos de la clase venta
 * @author Luis M. Teijón gdsram@gmail.com
 *
 */
enum VentaMappingEnum {
	RUC_CEDULA_PASAPORTE(0),
			CLIENTE(1),
			TIPO_ID(2),
			PARTE_RELACIONADA(3),
			FECHA_EMISION(4),
			FECHA_REGISTRO(5),
			CODIGO_TIPO_COMPROBANTE(6),
			BASE_NO_OBJETO_IVA(7),
			BASE_IMPONIBLE_0(8),
			BASE_IMPONIBLE_12(9),
			PORCENTAJE_IVA(10),
			VALOR_IVA(11),
			DOCUMENTOS_EMITIDOS_AL_CLIENTE(12),
			TIPO_VENTA(13),
			SERIE_COMPROBANTE_VENTA(14),
			SECUENCIA_COMPROBANTE_VENTA(15),
			CLAVE_ACCESO(16),
			AUTORIZACION_COMPROBANTE_VENTA(17),
			RETENCION_IMPUESTO_A_LA_RENTA(18),
			RETENCION_IVA(19),
			SERIE_RETENCION(20),
			SECUENCIA_RETENCION(21),
			AUTORIZACION_RETENCION(22),
			FECHA_RETENCION(23),
			FORMA_PAGO(24),
			MAIL_CLIENTE(25),
			CANTIDAD_PRODUCTO(26),
			CODIGO_PRIMARIO_PRODUCTO(27),
			CODIGO_AUXILIAR_PRODUCTO(28),
			NOMBRE_PRODUCTO(29),
			DESCUENTO(30),
			PRECIO_UNITARIO_PRODUCTO(31),
			IVA(32);
			
	public final int value;
	
	VentaMappingEnum(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
/**
 * Maneja las peticiones que vienen por sitac/ventas.
 * Controlador de Ventas
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/ventas")
public class VentaController {
	
    @Autowired
    ServletContext context; 
	@Autowired
	VentaHome ventaDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	TipoVentaHome tipoVentaDao;
	@Autowired
	TipoComprobanteHome tipoComprobanteDao;
	@Autowired
	ProductoHome productoDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	@Autowired
	EstadoSRIHome estadoSRIDao;
	@Autowired
	TipoVentaSegunPagoHome tipoVentaSegunPagoDao;
	@Autowired
	EmpresaHome empresaDao;
	@Autowired
	EmpresaVsClienteHome empresaVsClienteDao;
	@Autowired
	VentaVsProductoHome ventaVsProductoDao;
	@Autowired
	RetencionHome retencionDao;
	
	
	String nombreArchivoEXCEL = null;
	boolean errorCargandoVentas = false;
	private static final Logger logger = Logger.getLogger(VentaController.class);
	
	/**
	 * Da el formato 'yyyy-MM-dd' a la fecha que se envió por el formulario.
	 *
	 * @since 2015
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * Muestra una vista con la lista las ventas.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(@PathVariable int idEmpresa, Model model) {
		
		model.addAttribute("ventas", ventaDao.list(idEmpresa));

		return "venta/home";
	}
	

	/**
	 * Crea la vista para adicionar una nueva venta.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model, @PathVariable int idEmpresa, HttpServletRequest request) {
		
		cargarListasSelect(model, idEmpresa);
		
		model.addAttribute("venta", new Venta());
		model.addAttribute("cliente", new PersonalEmpresa());
		model.addAttribute("retencion", new Retencion());
		model.addAttribute("tipoVenta", new TipoVenta());
		model.addAttribute("tipoVentaSegunPago", new TipoVentaSegunPago());
		model.addAttribute("tipoComprobante", new TipoComprobante());
		model.addAttribute("editandoVenta", new Boolean(false));
		
		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		PuntoEmision puntoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		model.addAttribute("puntoEmision", puntoEmision);

		return "venta/datos";
	}

	/**
	 * Crea la vista para editar una venta.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") int ventaId, Model model, @PathVariable int idEmpresa) {

		cargarListasSelect(model, idEmpresa);
		
		Venta venta = ventaDao.findById(ventaId);
		model.addAttribute("venta", venta);
		model.addAttribute("cliente", venta.getPersonalEmpresa());
		model.addAttribute("retencion", venta.getRetencion());
		model.addAttribute("tipoVenta", venta.getTipoVenta());
		model.addAttribute("tipoVentaSegunPago", venta.getTipoVentaSegunPago());
		model.addAttribute("tipoComprobante", venta.getTipoComprobante());
		model.addAttribute("editandoVenta", new Boolean(true));
		model.addAttribute("ventaVsProductos", venta.getVentaVsProductos());
		model.addAttribute("puntoEmision", venta.getPuntoEmision());
		
		return "venta/datos";
	}

	/**
	 * Elimina una venta.
	 *
	 * @return Redirecciona a la vista que muestra las ventas actualizadas.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@RequestParam("id") int ventaId, Model model, @PathVariable int idEmpresa, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);

		if (usuario.getRol().getIdRol() == 2) {
			redirectAttributes.addFlashAttribute("error", "Ups!, usted no tiene permisos para eliminar la venta.");
			return Utility.goToUrl(idEmpresa, "ventas");
		}
		else {
			Venta venta = ventaDao.findById(ventaId);
			for (VentaVsProducto ventaVsProd : venta.getVentaVsProductos()) {
				ventaVsProductoDao.delete(ventaVsProd);
			}

			try
			{
				ventaDao.delete(venta);
			}
			catch (Exception ex) // error al guardar la exportacion
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la venta");
				return Utility.goToUrl(idEmpresa, "ventas");
			}

			try
			{
				retencionDao.delete(venta.getRetencion());
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la venta");
				return Utility.goToUrl(idEmpresa, "ventas");
			}
			redirectAttributes.addFlashAttribute("ok", "La venta fue eliminada satisfactoriamente.");
			return Utility.goToUrl(idEmpresa, "ventas");
		}
	}
	
	/**
	 * Elimina ventas via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, @PathVariable int idEmpresa, HttpServletRequest request,  RedirectAttributes redirectAttributes) {

		for (int i = 0; i < datos.size(); i++) {
			Venta venta = ventaDao.findById(datos.get(i));
			for (VentaVsProducto ventaVsProd : venta.getVentaVsProductos()) {
				ventaVsProductoDao.delete(ventaVsProd);
			}
			try
			{
				ventaDao.delete(venta);
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la venta");
				return false;
			}

			try
			{
				retencionDao.delete(venta.getRetencion());
			}
			catch (Exception ex)
			{
				logger.error("This is Error message", ex);
				redirectAttributes.addFlashAttribute("error", "Error al elminar la venta");
				return false;
			}
		}
		return true;
	}

	/**
	 * Guarda los datos de la venta.
	 *
	 * @return Redirecciona a la vista que muestra las ventas actualizadas.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("venta") Venta venta, @PathVariable int idEmpresa,
			HttpServletRequest request,
			@ModelAttribute("tipoComprobante") TipoComprobante tipoComprobante, 
			@ModelAttribute("tipoVenta") TipoVenta tipoVenta, 
			@ModelAttribute("retencion") Retencion retencion, 
			@ModelAttribute("tipoVentaSegunPago") TipoVentaSegunPago tipoVentaSegunPago, 
			@ModelAttribute("cliente") PersonalEmpresa cliente,
			@ModelAttribute("editandoVenta") Boolean editandoVenta, RedirectAttributes redirectAttributes) {
		
		Set<VentaVsProducto> ListVentaVsProducto = new HashSet<VentaVsProducto>();
		
		// separando todos los elentos codigo q vienen del formulario
		String[] codigoParams = tipoVentaSegunPago.getCodigo().split(","); 
		String tipoVentaSegunPagoExistente = codigoParams[0];
		String tipoVentaExistente = codigoParams[1];
		String tipoComprobanteExistente = codigoParams[2];
		
		// seleccionando los valores del producto q vienen de la tabla
		String[] cantidad = request.getParameterValues("cantidad");
		String[] codigoPrincipalProducto = request.getParameterValues("codigoPrincipalProducto");
		String[] codigoAuxiliarProducto = request.getParameterValues("codigoAuxiliarProducto");
		String[] precioTotal = request.getParameterValues("precioTotal");
		String[] tice = request.getParameterValues("tIce");
		String[] tdescuento = request.getParameterValues("tDescuento");
		String[] tidVP = request.getParameterValues("tidVP");
		
		float subtotal12  = 0;
		float subtotal = 0 ;
		float noObjIva = 0;
		float ice = 0;
		float desc = 0;	
		float montoIva = 0;
		
		// se recorren todos los codigos de los productos insertados en la venta y se obtienen los productos
		int cantidadProductos = codigoPrincipalProducto.length;
		
		if(cantidadProductos == 1){
			redirectAttributes.addFlashAttribute("error", "La venta debe tener al menos un producto asociado");
			return Utility.goToUrl(idEmpresa, "ventas/datos");
		}
		
		for (int i = 1; i < cantidadProductos; ++i) { // comienza en 1 debido al producto oculto en la vista
			Producto producto = productoDao.findByCodigoPCodigoA(codigoPrincipalProducto[i], codigoAuxiliarProducto[i]);
			
			if(producto.getTarifaIva().getCodigo() == 0){
				subtotal += Float.valueOf(precioTotal[i]);
			}else
			
			if(producto.getTarifaIva().getCodigo() == 2){
				subtotal12 += Float.valueOf(precioTotal[i]);
			}else
			
			if(producto.getTarifaIva().getCodigo() == 6){
				noObjIva += Float.valueOf(precioTotal[i]);
			}
			
			ice += Float.valueOf(tice[i]);
			desc += Float.valueOf(tdescuento[i]);
			
			VentaVsProducto vVSp = new VentaVsProducto(Integer.valueOf(tidVP[i]), Integer.valueOf(cantidad[i]), Float.valueOf(tdescuento[i]), Float.valueOf(tice[i]), producto, venta);
			ListVentaVsProducto.add(vVSp);
		}
		
		venta.setIce(ice);
		venta.setDescuento(desc);
		venta.setBaseImp(subtotal);
		venta.setBaseImpGravada(subtotal12);
		venta.setBaseImpNoGravada(noObjIva);
		
		for (int i = 1; i < precioTotal.length; i++) { // comienza en 1 debido al producto oculto en la vista
			montoIva += Float.valueOf(precioTotal[i]);
		}
		venta.setMontoIva(montoIva * (float)0.12);

		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		PuntoEmision ptoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		venta.setFechaRegistro(venta.getFechaEmision());
		venta.setPuntoEmision(ptoEmision);
		
		int estadoSRI = 2;
		if(!venta.getCodigoAutorizacion().isEmpty()) {
			estadoSRI = 1;
		}
		
		try
		{
			if (editandoVenta) {
				ventaDao.attachDirty(venta, retencion, cliente.getId(), tipoVentaSegunPagoExistente, tipoVentaExistente, tipoComprobanteExistente, estadoSRI, idEmpresa, ListVentaVsProducto);
			}
			else
			{
				ptoEmision.setSecFactura(ptoEmision.getSecFactura()+1);
				ventaDao.persist(venta, retencion, cliente.getId(), tipoVentaSegunPagoExistente, tipoVentaExistente, tipoComprobanteExistente, estadoSRI, idEmpresa, ListVentaVsProducto);
			}
		}
		catch (Exception ex) // error al guardar la exportacion
		{
			redirectAttributes.addFlashAttribute("error", "Error al resgistrar la venta.");
			logger.error("This is Error message", ex);
			return Utility.goToUrl(idEmpresa, "ventas");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos de la venta se guardaron satisfactoriamente");
		return Utility.goToUrl(idEmpresa, "ventas");
	}

	
	/**
	 * Carga la información de los selects que se mostraran en la vista
	 * 
	 * @param model
	 * 
	 * @since 1.0
	 */
	private void cargarListasSelect(Model model, int idEmpresa) {
		model.addAttribute("tiposComprobante", tipoComprobanteDao.listForVistaVenta());
		model.addAttribute("clientes", personalEmpresaDao.list(ClienteProveedorEnum.CLIENTE));
		model.addAttribute("tiposVenta", tipoVentaDao.list());
		model.addAttribute("productos", productoDao.list(idEmpresa));
		model.addAttribute("tiposVentaSegunPago", tipoVentaSegunPagoDao.list());
	
	}
	
	/**
	 * Buscar ventas por fecha.
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
		
		model.addAttribute("ventas", ventaDao.list(idEmpresa, anno, mes));
		
		return "venta/home";//Utility.goToUrl(idEmpresa, "retenciones");
	}
	
	/**
	 * Carga el archivo EXCEL con las ventas.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated ec.eac.sitac.util.CustomFile file, RedirectAttributes redirectAttributes,@PathVariable int idEmpresa,
			BindingResult result) {

		//String returnVal = "successFile";
		if (result.hasErrors()) {
			//returnVal = "file";
			redirectAttributes.addFlashAttribute("error", "El archivo no se pudo cargar");
			return Utility.goToUrl(idEmpresa, "compras");
		} else {			
			// subiendo el archivo
			MultipartFile multipartFile = file.getFile();
			nombreArchivoEXCEL = multipartFile.getOriginalFilename(); // guardando el nombre de archivo en uno de los atributos de la clase

			// creando la carpeta temporal para los documentos Excel, en el servidor
			String directorioTemporalPath = context.getRealPath("/EXCEL");
			File carpeta = new File(directorioTemporalPath);
			carpeta.mkdirs();

			String filePath = directorioTemporalPath + File.separator + nombreArchivoEXCEL;
			File localFile = new File(filePath);

	    	FileOutputStream os = null;
	    	try {
	    		
	    		os = new FileOutputStream(localFile);
	    		os.write(multipartFile.getBytes());
	    		
	    	} 
	    	catch (Exception ex) {
	    		redirectAttributes.addFlashAttribute("error", "El archivo no se pudo cargar");
	    		logger.error("This is Error message", ex);
	    		return Utility.goToUrl(idEmpresa, "ventas");
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
	    	
    		// Cargando las ventas del EXCEL
	    	List<Venta> ventas;
	    	try 
	    	{
				ventas = getVentasFromExcel(filePath, idEmpresa);
			} 
	    	catch (Exception e) 
	    	{
				redirectAttributes.addFlashAttribute("error", "El archivo no se pudo cargar, asegúrese que tenga el formato correcto");
				logger.error("This is Error message", e);
				return Utility.goToUrl(idEmpresa, "ventas");
			}
	    	// Guardando las ventas en la BD
	    	try
	    	{
				for (Venta venta : ventas) {
					ventaDao.persist(venta);
				}
	    	}
	    	catch (Exception e)
	    	{
	    		redirectAttributes.addFlashAttribute("error", "Error al guardar los datos de la venta");
				logger.error("This is Error message", e);
				return Utility.goToUrl(idEmpresa, "ventas");
	    	}
		}
		
		if ( errorCargandoVentas ) {
			redirectAttributes.addFlashAttribute("error", "Algunas ventas ya habían sido cargadas al sistema con anterioridad.");
			errorCargandoVentas = false;
		} else {
			redirectAttributes.addFlashAttribute("ok", "Archivo cargado satisfactoriamente");
		}
		
		return Utility.goToUrl(idEmpresa, "ventas");
	}
	

	/**
	 * Devuelve una lista de ventas a partir del documento Excel de Ventas
	 *
	 * @param filaExcel
	 * 
	 * @return Venta
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	private List<Venta> getVentasFromExcel(String rutaExcel, int idEmpresa) throws Exception  {
		List<Venta> resultado = new ArrayList<Venta>();
		List<Map<Integer, String>> valoresExcel = new ArrayList<Map<Integer, String>>();

		// Preparando el archivo para leerlo
		try 
		{
			ExcelManager.initialize(rutaExcel);
		}
		catch (Exception e)
		{
			logger.error("This is Error message", e);
			throw e;
		}

		// extrayendo información del archivo

		List<Integer> totalesXVentas = getCantidadVentasFromExcel(rutaExcel);
		int cantidadVentas = totalesXVentas.size();

		totalesXVentas.add(0, 2); // los productos comienzan en la segunda fila del excel
		int leerFilasDesde = totalesXVentas.get(0);

		// extraer la info del excel como lista de Objects
		for (int i = 1; i <= cantidadVentas; i++) {
			valoresExcel = ExcelManager.readFile(rutaExcel, leerFilasDesde, totalesXVentas.get(i));
			leerFilasDesde += totalesXVentas.get(i);

			// mapear la venta y agregarla al resultado
			Venta venta;
			try 
			{
				venta = mapearVenta(valoresExcel, idEmpresa);
			}
			catch (Exception e) 
			{
				logger.error("This is Error message", e);
				throw e;
			}

			// la venta existía en la BD
			if (venta == null) {
				errorCargandoVentas = true;
				continue;
			}
			resultado.add(venta); 
		} 

		ExcelManager.closeFile();


		return resultado;
	}
	
	/**
	 * Mapea una Venta con la información del "excel de ventas". La venta es mapeada validando sus datos con los valores que están en la BD. Devuevle NULL en caso de error.
	 *
	 * @param listaMapas Lista de mapas con los pares "atributos, valor" 
	 * @param idEmpresa
	 * 
	 * @return Venta
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	private Venta mapearVenta(List<Map<Integer, String>> listaMapas, int idEmpresa) throws Exception {
		Venta venta = new Venta();
		VentaVsProducto ventaVsProducto = new VentaVsProducto();
		
		int contador = 0;
		StringBuilder detallesVenta = new StringBuilder(" Datos de la venta: serie del comprobante = ");
		
		for (Map<Integer, String> mapColumnaValor : listaMapas ) {
			contador++;
			if (contador == 1) {
				// detalles para mostrar en caso de error
				detallesVenta.append(mapColumnaValor.get(VentaMappingEnum.SERIE_COMPROBANTE_VENTA.getValue()));
				detallesVenta.append(", secuencia del comprobante = ");
				detallesVenta.append(mapColumnaValor.get(VentaMappingEnum.SECUENCIA_COMPROBANTE_VENTA.getValue()));
				detallesVenta.append(" y correspondiente al cliente ");
				detallesVenta.append(mapColumnaValor.get(VentaMappingEnum.MAIL_CLIENTE.getValue()));

				// Insertando datos de la venta
				Empresa empresa = empresaDao.findById(idEmpresa);
				venta.setEmpresa(empresa);
				
				// Verificando que la venta no haya sido mapeada antes
				if ( ventaDao.findByAutorizacion(mapColumnaValor.get(VentaMappingEnum.AUTORIZACION_COMPROBANTE_VENTA.getValue())) != null )
					return null;
				
				// Punto de emisión
				PuntoEmision ptoEmision = puntoEmisionDao.findBySerieAndEnterpriseId(mapColumnaValor.get(VentaMappingEnum.SERIE_COMPROBANTE_VENTA.getValue()), idEmpresa);
				if (ptoEmision == null) {
					throw new Exception("La empresa actual no posee ningún punto de emisión con la serie: " + mapColumnaValor.get(VentaMappingEnum.SERIE_COMPROBANTE_VENTA.getValue()) + "." + detallesVenta);
				} else {
					venta.setPuntoEmision(ptoEmision);
					venta.setSerieFactura(ptoEmision.getSerie());
				}
				
				// Cliente
				PersonalEmpresa cliente = personalEmpresaDao.findById(mapColumnaValor.get(VentaMappingEnum.RUC_CEDULA_PASAPORTE.getValue()));
				if (cliente == null) {
					PersonalEmpresa clienteNuevo = new PersonalEmpresa();
					clienteNuevo.setEmail(mapColumnaValor.get(VentaMappingEnum.MAIL_CLIENTE.getValue()));
					clienteNuevo.setId(String.valueOf(mapColumnaValor.get(VentaMappingEnum.RUC_CEDULA_PASAPORTE.getValue())));
					personalEmpresaDao.persistCliente(String.valueOf(mapColumnaValor.get(VentaMappingEnum.TIPO_ID.getValue())), "21701", idEmpresa, clienteNuevo, ClienteProveedorEnum.CLIENTE);
					venta.setPersonalEmpresa(clienteNuevo);
					//throw new Exception("El cliente especificado en una de las ventas es incorrecto." + detallesVenta);
				} else {
					if(!cliente.isCliente()) {
						cliente.setCliente(true);
						EmpresaVsCliente empresaVsCliente = new EmpresaVsCliente(empresa, cliente);
						empresaVsClienteDao.persist(empresaVsCliente);
					}
					venta.setPersonalEmpresa(cliente);
				}
				
				SimpleDateFormat parseador = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
					// SimpleDateFormat parseador = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);

				try 
				{
					Date fechaEmision;
					fechaEmision = parseador.parse(mapColumnaValor.get(VentaMappingEnum.FECHA_EMISION.getValue()));
					venta.setFechaEmision(fechaEmision);

					Date fechaRegistro = parseador.parse(mapColumnaValor.get(VentaMappingEnum.FECHA_REGISTRO.getValue()));
					venta.setFechaRegistro(fechaRegistro);

					Date fechaComprobanteRetencion = parseador.parse(mapColumnaValor.get(VentaMappingEnum.FECHA_RETENCION.getValue()));
					venta.getRetencion().setFechaRetencion(fechaComprobanteRetencion);

				} 
				catch (ParseException e) 
				{
					logger.error("This is Error message", e);
					throw e;
				}
				
				// Atributos de la venta
				venta.setBaseImp(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.BASE_IMPONIBLE_0.getValue())));
				
				if (mapColumnaValor.containsKey(VentaMappingEnum.BASE_IMPONIBLE_12.getValue()))
					venta.setBaseImpGravada(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.BASE_IMPONIBLE_12.getValue())));
				
				if (mapColumnaValor.containsKey(VentaMappingEnum.PORCENTAJE_IVA.getValue()))
					venta.setPorcentajeIva(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.PORCENTAJE_IVA.getValue()))); // valor de IVA
				
				if (mapColumnaValor.containsKey(VentaMappingEnum.SECUENCIA_COMPROBANTE_VENTA.getValue())) {
					Integer valor = Integer.valueOf(mapColumnaValor.get(VentaMappingEnum.SECUENCIA_COMPROBANTE_VENTA.getValue()));
					venta.getPuntoEmision().setSecFactura(valor);
					venta.setSecFactura(valor);
				}
				
				if (mapColumnaValor.containsKey(VentaMappingEnum.CLAVE_ACCESO.getValue()))
					venta.setClaveAcceso(mapColumnaValor.get(VentaMappingEnum.CLAVE_ACCESO.getValue()));
				
				if (mapColumnaValor.containsKey(VentaMappingEnum.AUTORIZACION_COMPROBANTE_VENTA.getValue()))
					venta.setCodigoAutorizacion(mapColumnaValor.get(VentaMappingEnum.AUTORIZACION_COMPROBANTE_VENTA.getValue()));
				
				if (mapColumnaValor.containsKey(VentaMappingEnum.RETENCION_IMPUESTO_A_LA_RENTA.getValue()))
					venta.getRetencion().setRetencionIR(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.RETENCION_IMPUESTO_A_LA_RENTA.getValue())));
			
				if (mapColumnaValor.containsKey(VentaMappingEnum.RETENCION_IVA.getValue()))
					venta.getRetencion().setRetencionIVA(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.RETENCION_IVA.getValue()))); 

				// serie de la retencion
				// secuencia de la retencion
				//autorizacion de la retencion
				// Buscar forma de pago
				Float valorTipoDatoFloat = Float.valueOf(mapColumnaValor.get(VentaMappingEnum.FORMA_PAGO.getValue())); 
				TipoVentaSegunPago formaPago = tipoVentaSegunPagoDao.findById(String.valueOf(valorTipoDatoFloat.intValue()));
				if (formaPago == null) {
					throw new Exception("La forma de pago especificada en una de las ventas es incorrecta." + detallesVenta);
				} else {
					venta.setTipoVentaSegunPago(formaPago);
				}

				// venta vs producto con la cantidad de productos venidos
				// Buscar tipo de venta
				//valorTipoDatoFloat = Float.valueOf(mapColumnaValor.get(VentaMappingEnum.TIPO_VENTA.getValue())); 
				TipoVenta tipoVenta = tipoVentaDao.findById(mapColumnaValor.get(VentaMappingEnum.TIPO_VENTA.getValue()));
				if (tipoVenta == null) {
					throw new Exception("El tipo de venta especificado en una de las ventas es incorrecto." + detallesVenta);
				} else {
					venta.setTipoVenta(tipoVenta);
				}

				// Buscar tipo comprobante tipoComprobante
				//valorTipoDatoFloat = Float.valueOf(mapColumnaValor.get(VentaMappingEnum.CODIGO_TIPO_COMPROBANTE.getValue())); 
				TipoComprobante tipoComprobante = tipoComprobanteDao.findById(mapColumnaValor.get(VentaMappingEnum.CODIGO_TIPO_COMPROBANTE.getValue()));
				if (tipoComprobante == null) {
					throw new Exception("El codigo del tipo de comprobante especificado en una de las ventas es incorrecto." + detallesVenta);
				} else {
					venta.setTipoComprobante(tipoComprobante);
				}
				
				EstadoSRI estado = estadoSRIDao.findById(1); // ACEPTADA
				if(estado == null) {
					throw new Exception("En la base de datos, en la tabla estado_sri, debe existir la tupla 'Aceptada', con el código '1'.");
				} else {
					venta.setEstado(estado);
				}
			}

			// los productos de la venta
			Producto producto = productoDao.findByCodigoPCodigoA(mapColumnaValor.get(VentaMappingEnum.CODIGO_PRIMARIO_PRODUCTO.getValue()), mapColumnaValor.get(VentaMappingEnum.CODIGO_AUXILIAR_PRODUCTO.getValue()));
			if (producto == null) {
				Producto productoNuevo = new Producto();
				productoNuevo.setCodigoAuxiliar(mapColumnaValor.get(VentaMappingEnum.CODIGO_AUXILIAR_PRODUCTO.getValue()));
				productoNuevo.setCodigoPrincipal(mapColumnaValor.get(VentaMappingEnum.CODIGO_PRIMARIO_PRODUCTO.getValue()));
				productoNuevo.setNombre(mapColumnaValor.get(VentaMappingEnum.NOMBRE_PRODUCTO.getValue()));
				productoNuevo.setValorUnitario(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.PRECIO_UNITARIO_PRODUCTO.getValue())));
				
				if (mapColumnaValor.get(VentaMappingEnum.IVA.getValue()) == null)
					throw new Exception("Error en una de las ventas. Valor ICE no puede ser NULO: " + detallesVenta);
				
				if( Float.valueOf(mapColumnaValor.get(VentaMappingEnum.IVA.getValue())) == 12){
					productoDao.persist(productoNuevo, 2, Short.valueOf("2"), idEmpresa);
				} else {
					productoDao.persist(productoNuevo, 2, Short.valueOf("0"), idEmpresa);
				}
				
				ventaVsProducto.setProducto(productoNuevo);
				ventaVsProducto.setVenta(venta);
				
				if (mapColumnaValor.get(VentaMappingEnum.CANTIDAD_PRODUCTO.getValue()) == null)
					throw new Exception("Error en la cantidad de productos: " + detallesVenta);
                	
				Float valorTipoDatoReal = Float.valueOf(mapColumnaValor.get(VentaMappingEnum.CANTIDAD_PRODUCTO.getValue()));
				ventaVsProducto.setCantidad(Integer.valueOf(valorTipoDatoReal.intValue()));
				
				ventaVsProducto.setDescuento(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.DESCUENTO.getValue())));
				venta.getVentaVsProductos().add(ventaVsProducto);
				//throw new Exception("No existe ningún producto con el código principal = " + mapColumnaValor.get(VentaMappingEnum.CODIGO_PRIMARIO_PRODUCTO.getValue()) + " y el código auxiliar = " + mapColumnaValor.get(VentaMappingEnum.CODIGO_AUXILIAR_PRODUCTO.getValue()) + "." + detallesVenta);
			} else {
				ventaVsProducto.setProducto(producto);
				ventaVsProducto.setVenta(venta);
				
				Float valorTipoDatoReal = Float.valueOf(mapColumnaValor.get(VentaMappingEnum.CANTIDAD_PRODUCTO.getValue()));
				ventaVsProducto.setCantidad(Integer.valueOf(valorTipoDatoReal.intValue()));
				
				ventaVsProducto.setDescuento(Float.valueOf(mapColumnaValor.get(VentaMappingEnum.DESCUENTO.getValue())));
				venta.getVentaVsProductos().add(ventaVsProducto);
			}
		}
		return venta;
	}
	
	/**
	 * Devuelve una lista con la cantidad de productos x cada venta del documento excel que se pasa por parámetro
	 * @param rutaExcel
	 * 
	 * @return List<Integer>
	 * 
	 * @since 1.0
	 */
	private List<Integer> getCantidadVentasFromExcel(String rutaExcel) {
		List<Integer> cantidadesXVentas = new LinkedList<Integer>();

		String valorCelda = "-1"; // length != 0 .. not empty string
		int contador = 1; // el contador comienza en 1 por la cabecera 

		// calculando el total de productos del archivo
		while (valorCelda != null) {
			contador++;
			valorCelda = ExcelManager.getCellValue("AC", contador);

		}
		
		int totalProductos = contador - 2; 
		// calculando el total de productos x cada venta
		int cantidad = 0;
		valorCelda = ExcelManager.getCellValue("A", 2); // nombre del primer producto
		String valorCeldaTemp = new String();
		for (int i = 0; i < totalProductos; i++) { 
			valorCeldaTemp = ExcelManager.getCellValue("A", i + 2); // productos están a partir de segunda fila
			if (!valorCelda.equals(valorCeldaTemp) /*&& !valorCelda.equals("") && cantidad != 0*/) {
				valorCelda = valorCeldaTemp;
				cantidadesXVentas.add(cantidad);
				cantidad = 1;
			} else {
				cantidad++;
				// guardando cantidad de productos de la última venta
				if (i == totalProductos - 1)
					cantidadesXVentas.add(cantidad);
			}
		}
		return cantidadesXVentas;
	}
	
	
	/**
	 * Controlador q recibe datos Json para obtener el precio de los productos
	 *
	 * @return float
	 *
	 * @since 1.0
	 */
	@RequestMapping(value ={"/datos/precioProducto", "/editar/precioProducto" }, method = RequestMethod.POST)
	public @ResponseBody float precioProducto(@RequestBody String codigos) {
		
		String[] s = codigos.split("&");
		String[] cp = s[0].split("=");
		String codigoPrincipal = cp[1];
		String[] ca = s[1].split("=");
		String codigoAuxiliar = ca[1];
		
		float precio = productoDao.findPrecioByCodigoPCodigoA(codigoPrincipal, codigoAuxiliar);
		return precio;
    }
	
	
	/**
	 * Controlador q recibe datos Json para buscar en nombre del cliente
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value ={ "/datos/BuscarNombreCliente", "/editar/BuscarNombreCliente" }, method = RequestMethod.POST)
	public @ResponseBody StringResponse BuscarNombreProveedor(@RequestBody String datos) {

		String[] t = datos.split("=");
		String idliente = t[1];
		String nombreCliente = personalEmpresaDao.findNombreClienteById(idliente);
		
		return new StringResponse(nombreCliente);
	}
}
