package ec.eac.sitac;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.eac.sitac.dao.BancoHome;
import ec.eac.sitac.dao.CompraHome;
import ec.eac.sitac.dao.DiscapacidadHome;
import ec.eac.sitac.dao.DocumentosAnuladosHome;
import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.ExportacionHome;
import ec.eac.sitac.dao.ImportacionHome;
import ec.eac.sitac.dao.ImpuestoMoraHome;
import ec.eac.sitac.dao.MovimientoHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.TipoPagoHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.dao.VentaHome;
import ec.eac.sitac.model.Banco;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.Discapacidad;
import ec.eac.sitac.model.DocumentosAnulados;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.Importacion;
import ec.eac.sitac.model.ImpuestoMora;
import ec.eac.sitac.model.ImpuestoRenta;
import ec.eac.sitac.model.Movimiento;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.TipoComprobante;
import ec.eac.sitac.model.TipoPago;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.model.VentaVsProducto;
import ec.eac.sitac.util.PDFGenerator;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;
import ec.eac.sitac.util.XmlGenerator;
import ec.eac.sitac.util.formatoAnexo104;

/**
 * Maneja las peticiones que vienen por sitac/anexos.
 * Controlador de Reportes.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
/**
 * @author Lorena Pita lrnpita@gmail.com
 *
 *@since 1.0
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/anexos")
public class AnexoController {
	@Autowired
	CompraHome compraDao;
	@Autowired
	VentaHome ventaDao;
	@Autowired
	DocumentosAnuladosHome documentosAnuladosDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	@Autowired
	EmpresaHome empresaDao;
	@Autowired
	MovimientoHome movimientoDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	DiscapacidadHome discapacidadDao;
	@Autowired
	ExportacionHome exportacionDao;
	@Autowired
	TipoPagoHome tipoPagoDao;
	@Autowired
	BancoHome bancoDao;
	@Autowired
	ImportacionHome importacionDao;
	@Autowired
	ImpuestoMoraHome impuestoMoraDao;	
	@Autowired
    ServletContext context; 
	
	private static final Logger logger = Logger.getLogger(AnexoController.class);
	
	/**
	 * Muestra una vista para generar anexos.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(Model model) {
	
		return "anexo/home";
	}
	
	
	/**
	 * Genera el anexo ATS.
	 *
	 * @return El nombre de la vista que mostrará el anexo ATS
	 * @throws Exception 
	 *
	 * @since 1.0
	 */
	@RequestMapping("/ats")
	public String generateATS(Model model, @PathVariable int idEmpresa,
			@ModelAttribute("fecha") String fecha, HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		//FIXME esto se hace porq no todos los navegadores reconocen un input tipo Date entonces hay q coger la fecha como String
		String[] string = fecha.split(" ");
		String m = string[0];
		String a = string[1];
		int mes = 0;
		
		mes = Utility.getNumeroMes(m);
		int anno = Integer.valueOf(a);
		
		Empresa empresa = empresaDao.findById(idEmpresa);
        List<Compra> listCompras = compraDao.list(idEmpresa, anno, mes);
        List<Venta> listVentas = ventaDao.listOrderByIdCliente(idEmpresa, anno, mes);
        List<DocumentosAnulados> anulados = documentosAnuladosDao.listOrderBySecuencia(idEmpresa, anno, mes);

        String directorioEmpresaActual = context.getRealPath("/resources");
	    try {
			XmlGenerator.generateAnexoATS(listCompras, listVentas, anulados, puntoEmisionVSUsuario(idEmpresa, request), empresa, anno, mes, context, ventaDao);
		} catch (Exception e1) {
			logger.error("This is Error message", e1);
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al exportar el anexo xml");
			return Utility.goToUrl(idEmpresa, "anexos");
		}
	    
	    try {
			descargarArchivo(directorioEmpresaActual + "\\ATS_"+ mes + "_" + anno + ".xml", request, response);
		} catch (IOException e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al exportar el anexo xml");
			return Utility.goToUrl(idEmpresa, "anexos");
		}
	    
	    File file = new File(directorioEmpresaActual + "\\ATS_" + mes + "_" + anno + ".xml");
		if (file.delete())
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no puede ser borrado");
	       
	   // redirectAttributes.addFlashAttribute("ok", "El anexo ATS se generó exitosamente");
		return Utility.goToUrl(idEmpresa, "anexos");
	}
	
	/**
	 * Genera el reporte 103.
	 *
	 * @return El nombre de la vista que mostrara el anexo 103 en formato html
	 *
	 * @since 2015
	 */
	@RequestMapping("/103")
	public String generate103(Model model, @PathVariable int idEmpresa, HttpServletRequest request,
			@ModelAttribute("fecha") String fecha) {
		
		//FIXME esto se hace porq no todos los navegadores reconocen un input tipo Date entonces hay q coger la fecha como String
		String[] string = fecha.split(" ");
		String m = string[0];
		String a = string[1];
		int mes = 0;
		mes = Utility.getNumeroMes(m);
		int anno = Integer.valueOf(a);
		float baseImp,rir,c346,c349, n399,noSBD, siSBD, baseDesgravada, c352, retAnuladas, n498, c429, c499, c903, c904;
		baseDesgravada = 900;
		baseImp=rir=c346 = c349= noSBD =siSBD = c352= n498 = c429= c499=0;
		float c303,c304,c307,c308,c309,c310,c311,c312,c314,c319,c320,c322,c323,c324,c325,c326,c327,c328,c329,c330,c331,c332,c333,c334,c335,c336,c337,c338,c339,c340,c341,c342,c343,c344,c345;
		c303=c304=c307=c308=c309=c310=c311=c312=c314=c319=c320=c322=c323=c324=c325=c326=c327=c328=c329=c330=c331=c332=c333=c334=c335=c336=c337=c338=c339=c340=c341=c342=c343=c344=c345 = 0;
		float n303,n304,n307,n308,n309,n310,n311,n312,n314,n319,n320,n322,n323,n324,n325,n326,n327,n328,n329,n330,n331,n332,n333,n334,n335,n336,n337,n338,n339,n340,n341,n342,n343,n344,n345,n346;
		n303=n304=n307=n308=n309=n310=n311=n312=n314=n319=n320=n322=n323=n324=n325=n326=n327=n328=n329=n330=n331=n332=n333=n334=n335=n336=n337=n338=n339=n340=n341=n342=n343=n344=n345=n346 = 0;
		
		float c406,c405,c425,c427,c426,c428,c410,c421,c431,c409,c420,c430,c412,c407,c404,c411;
		c406=c405=c425=c427=c426=c428=c410=c421=c431=c409=c420=c430=c412=c407=c404=c411=0;
		float n406,n405,n425,n427,n426,n428,n410,n421,n431,n409,n420,n430,n412,n407,n404,n411;
		n406=n405=n425=n427=n426=n428=n410=n421=n431=n409=n420=n430=n412=n407=n404=n411=0;
		
		retAnuladas = documentosAnuladosDao.count(idEmpresa, anno, mes);
		List<Compra> ListCompras = compraDao.list(idEmpresa, anno, mes);
		List<Movimiento> movimientos = movimientoDao.list(idEmpresa, anno, mes);
		Empresa empresa = empresaDao.findById(idEmpresa);
		Float[] impuestoMora = calcularImpuestoMora(mes, anno, empresa.getRucContribuyente());
		c903 = impuestoMora[0];
		c904 = (float) (impuestoMora[1] *0.3);
		
		for (Movimiento movimiento : movimientos) {
			if(movimiento.getBaseImponibleGravada() <= baseDesgravada){
				noSBD += movimiento.getBaseImponibleGravada();
			}else{
				siSBD += movimiento.getBaseImponibleGravada();
			}
			c352 = movimiento.getImpuestoRentaCausado();
		}
		
		for (Compra compra : ListCompras) {
			Set<DetallesCompra> detalles = compra.getDetallesCompras();
			// si el tipo de comprbante no es nota de crédito
			if(!compra.getTipoComprobante().getCodigo().equals("04")){
				for (DetallesCompra detalle : detalles) {
					// tipoT = tipo de trasacción
					String tipoT = detalle.getTipoTransaccion().getCodigo();
					baseImp = detalle.getRIRbaseImp12();
					rir = baseImp*detalle.getRIret()/100;
					if(tipoT.equals("303")){
						c303 += baseImp; n303 += rir;
					} else if(tipoT.equals("304") || tipoT.equals("304A") || tipoT.equals("304B")|| tipoT.equals("304C")|| tipoT.equals("304D")|| tipoT.equals("304E")){
						c304 += baseImp; n304 += rir;
					} else	if(tipoT.equals("307")){
						c307 += baseImp; n307 += rir;
					} else	if(tipoT.equals("308")){
						c308 += baseImp; n308 += rir;
					} else if(tipoT.equals("309")){
						c309 += baseImp; n309 += rir;
					} else if(tipoT.equals("310")){
						c310 += baseImp; n310 += rir;
					} else	if(tipoT.equals("311")){
						c311 += baseImp; n311 += rir;
					} else	if(tipoT.equals("312") || tipoT.equals("312A")){
						c312 += baseImp; n312 += rir;
					} else	if(tipoT.equals("314") || tipoT.equals("314A") || tipoT.equals("314B")|| tipoT.equals("314C")|| tipoT.equals("314D")){
						c314 += baseImp; n314 += rir;
					} else	if(tipoT.equals("319")){
						c319 += baseImp; n319 += rir;
					} else if(tipoT.equals("320")){
						c320 += baseImp; n320 += rir;
					} else if(tipoT.equals("322")){
						c322 += baseImp; n322 += rir;
					} else if(tipoT.equals("323") || tipoT.equals("323A") || tipoT.equals("323B1")|| tipoT.equals("323E")|| tipoT.equals("323E2")|| tipoT.equals("323F")|| 
							tipoT.equals("323G") || tipoT.equals("323H")|| tipoT.equals("323I")|| tipoT.equals("323 M")|| tipoT.equals("323 N")|| 
							tipoT.equals("323 O") || tipoT.equals("323 P")|| tipoT.equals("323Q")|| tipoT.equals("323R")){
						c323 += baseImp; n323 += rir;
					} else if(tipoT.equals("324A")|| tipoT.equals("324B") ){
						c324 += baseImp; n324 += rir;
					} else if(tipoT.equals("325") || tipoT.equals("325A")){
						c325 += baseImp; n325 += rir;
					} else if(tipoT.equals("326")){
						c326 += baseImp; n326 += rir;
					} else if(tipoT.equals("327")){
						c327 += baseImp; n327 += rir;
					} else if(tipoT.equals("328")){
						c328 += baseImp; n328 += rir;
					} else if(tipoT.equals("329")){
						c329 += baseImp; n329 += rir;
					} else if(tipoT.equals("330")){
						c330 += baseImp; n330 += rir;
					} else if(tipoT.equals("331")){
						c331 += baseImp; n331 += rir;
					} else if(tipoT.equals("332") || tipoT.equals("332A") || tipoT.equals("332B")|| tipoT.equals("332C")|| tipoT.equals("332D")|| tipoT.equals("332E")|| 
							tipoT.equals("332F") || tipoT.equals("332G")|| tipoT.equals("332H")){
						c332 += baseImp; n332 += rir;
					} else if(tipoT.equals("334")){
						c334 += baseImp; n334 += rir;
					} else if(tipoT.equals("335")){
						c335 += baseImp; n335 += rir;
					} else if(tipoT.equals("336")){
						c336 += baseImp; n336 += rir;
					} else if(tipoT.equals("337")){
						c337 += baseImp; n337 += rir;
					} else if(tipoT.equals("338")){
						c338 += baseImp; n338 += rir;
					} else if(tipoT.equals("339")){
						c339 += baseImp; n339 += rir;
					} else if(tipoT.equals("340")){
						c340 += baseImp; n340 += rir;
					} else if(tipoT.equals("341")){
						c341 += baseImp; n341 += rir;
					} else if(tipoT.equals("342")){
						c342 += baseImp; n342 += rir;
					} else if(tipoT.equals("343")){
						c343 += baseImp; n343 += rir;
					} else if(tipoT.equals("344") || tipoT.equals("344A")){
						c344 += baseImp; n344 += rir;
					} else if(tipoT.equals("346")){
						c346 += baseImp; n346 += rir;
					} else if(tipoT.equals("504A")){
						c406 += baseImp; n406 += rir;
					} else if(tipoT.equals("504")){
						c405 += baseImp; n405 += rir;
					} else if(tipoT.equals("504G")){
						c425 += baseImp; n425 += rir;
					} else if(tipoT.equals("504F")){
						c427 += baseImp; n427 += rir;
					} else if(tipoT.equals("504E")){
						c426 += baseImp; n426 += rir;
					} else if(tipoT.equals("504D")){
						c407 += baseImp; n407 += rir;
					} else if(tipoT.equals("504C") || tipoT.equals("504B")){
						c404 += baseImp; n404 += rir;
					} else if(tipoT.equals("522B")){
						c421 += baseImp; n421 += rir;
					} else if(tipoT.equals("522A")){
						c410 += baseImp; n410 += rir;
					} else  if(tipoT.equals("504H")){
						c428 += baseImp; n428 += rir;
					} else if(tipoT.equals("524")){
						c412 += baseImp; n412 += rir;
					} else if(tipoT.equals("523C")){
						c430 += baseImp; n430 += rir;
					} else if(tipoT.equals("523B")){
						c420 += baseImp; n420 += rir;
					} else if(tipoT.equals("523A")){
						c409 += baseImp; n409 += rir;
					} else if(tipoT.equals("522C")){
						c431 += baseImp; n431 += rir;
					} else{// sino es ninguno de los anteriores es porque son el resto
						c411 += baseImp; n411 += rir;
					}

				}	
			} 
		}
		
		c349 = c303+c304+c307+c308+c309+c310+c311+c312+c314+c319+c320+c322+c323+c324+c325+c326+c327+c328+c329+c330+c331+c332+c333+c334+c335+c336+c337+c338+c339+c340+c341+c342+c343+c344+c345+c346;
		n399 = n303+n304+n307+n308+n309+n310+n311+n312+n314+n319+n320+n322+n323+n324+n325+n326+n327+n328+n329+n330+n331+n332+n333+n334+n335+n336+n337+n338+n339+n340+n341+n342+n343+n344+n345+n346;
		
		c429 = c406*2+c405+c425+c427+c426+c428+c410+c421+c431+c409+c420+c430+c412*3+c407*2+c404*2+c411*3;
		n498 = n406*2+n405+n425+n427+n426+n428+n410+n421+n431+n409+n420+n430+n412*3+n407*2+n404*2+n411*3;
		
		BigDecimal bigc349 = new BigDecimal(c349);
		bigc349 = bigc349.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal bign399 = new BigDecimal(n399+c352);
		bign399 = bign399.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal bigc429 = new BigDecimal(c429);
		bigc349 = bigc429.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal bign498 = new BigDecimal(n498);
		bign498 = bign498.setScale(2, RoundingMode.HALF_UP);
		
		c499 = n399+n498;
		BigDecimal bigc499 = new BigDecimal(c499);
		bigc499 = bigc499.setScale(2, RoundingMode.HALF_UP);
		
		model.addAttribute("rucEmpresa", new String(empresa.getRucContribuyente()));
		model.addAttribute("razonSocialEmpresa", new String(empresa.getNombre()));
		model.addAttribute("idContadorEmpresa", new String(empresa.getPersonalEmpresaByIdContador().getId()));
		model.addAttribute("tipoPago", new TipoPago());	model.addAttribute("tiposPago", tipoPagoDao.list());
		model.addAttribute("fecha", fecha);	model.addAttribute("banco", new Banco());
		model.addAttribute("bancos", bancoDao.list());model.addAttribute("c352", c352);
		model.addAttribute("noEmpleados", movimientos.size()); model.addAttribute("retAnuladas", retAnuladas);
		
		model.addAttribute("noSBD", noSBD); model.addAttribute("siSBD", siSBD);
		model.addAttribute("c303", c303); model.addAttribute("n303", n303);
		model.addAttribute("c304", c304); model.addAttribute("n304", n304);
		model.addAttribute("c307", c307); model.addAttribute("n307", n307);
		model.addAttribute("c308", c308); model.addAttribute("n308", n308);
		model.addAttribute("c309", c309); model.addAttribute("n309", n309);
		model.addAttribute("c310", c310); model.addAttribute("n310", n310);
		model.addAttribute("c311", c311); model.addAttribute("n311", n311);
		model.addAttribute("c312", c312); model.addAttribute("n312", n312);
		model.addAttribute("c314", c314); model.addAttribute("n314", n314);
		model.addAttribute("c319", c319); model.addAttribute("n319", n319);
		model.addAttribute("c320", c320); model.addAttribute("n320", n320);
		model.addAttribute("c322", c322); model.addAttribute("n322", n322);
		model.addAttribute("c323", c323); model.addAttribute("n323", n323);
		model.addAttribute("c324", c324); model.addAttribute("n324", n324);
		model.addAttribute("c325", c325); model.addAttribute("n325", n325);
		model.addAttribute("c326", c326); model.addAttribute("n326", n326);
		model.addAttribute("c327", c327); model.addAttribute("n327", n327);
		model.addAttribute("c328", c328); model.addAttribute("n328", n328);
		model.addAttribute("c329", c329); model.addAttribute("n329", n329);
		model.addAttribute("c330", c330); model.addAttribute("n330", n330);
		model.addAttribute("c331", c331); model.addAttribute("n331", n331);
		model.addAttribute("c332", c332); model.addAttribute("n332", n332);
		model.addAttribute("c334", c334); model.addAttribute("n334", n334);
		model.addAttribute("c335", c335); model.addAttribute("n335", n335);
		model.addAttribute("c336", c336); model.addAttribute("n336", n336);
		model.addAttribute("c337", c337); model.addAttribute("n337", n337);
		model.addAttribute("c338", c338); model.addAttribute("n338", n338);
		model.addAttribute("c339", c339); model.addAttribute("n339", n339);
		model.addAttribute("c340", c340); model.addAttribute("n340", n340);
		model.addAttribute("c341", c341); model.addAttribute("n341", n341);
		model.addAttribute("c342", c342); model.addAttribute("n342", n342);
		model.addAttribute("c343", c343); model.addAttribute("n343", n343);
		model.addAttribute("c344", c344); model.addAttribute("n344", n344);
		model.addAttribute("c346", c346); model.addAttribute("n346", n346);
		model.addAttribute("c406", c406); model.addAttribute("n406", c406);
		model.addAttribute("c417", c406); model.addAttribute("n417", n406);
		model.addAttribute("c405", c405); model.addAttribute("n405", n405);
		model.addAttribute("c416", c405); model.addAttribute("n416", n405);
		model.addAttribute("c411", c411); model.addAttribute("n411", n411);
		model.addAttribute("c422", c411); model.addAttribute("n422", n411);
		model.addAttribute("c432", c411); model.addAttribute("n432", n411);
		model.addAttribute("c425", c425); model.addAttribute("n425", n425);
		model.addAttribute("c427", c427); model.addAttribute("n427", n427);
		model.addAttribute("c426", c426); model.addAttribute("n426", n426);
		model.addAttribute("c421", c421); model.addAttribute("n421", n421);
		model.addAttribute("c410", c410); model.addAttribute("n410", n410);
		model.addAttribute("c428", c428); model.addAttribute("n428", n428);
		model.addAttribute("c407", c407); model.addAttribute("n407", n407);
		model.addAttribute("c418", c407); model.addAttribute("n418", n407);
		model.addAttribute("c404", c404); model.addAttribute("n404", n404);
		model.addAttribute("c415", c404); model.addAttribute("n415", n404);
		model.addAttribute("c412", c412); model.addAttribute("n412", n412);
		model.addAttribute("c423", c412); model.addAttribute("n423", n412);
		model.addAttribute("c433", c412); model.addAttribute("n433", n412);
		model.addAttribute("c430", c430); model.addAttribute("n430", n430);
		model.addAttribute("c420", c420); model.addAttribute("n420", n420);
		model.addAttribute("c409", c409); model.addAttribute("n409", n409);
		model.addAttribute("c431", c431); model.addAttribute("n431", n431);
		model.addAttribute("c349", bigc349); model.addAttribute("n399", bign399);
		model.addAttribute("c429", bigc429); model.addAttribute("n498", bign498);
		model.addAttribute("c499", bigc499); model.addAttribute("c902", c499);
		model.addAttribute("c903", c903); model.addAttribute("c904", c904 + c499);
		model.addAttribute("c999", c904 + c499 + c903);
		
		return "anexo/103";
	}
	
	/**
	 * Genera el reporte 103.
	 *
	 * @return El nombre de la vista que mostrara el anexo 103 en formato pdf
	 * @throws IOException 
	 *
	 * @since 2015
	 */
	@RequestMapping("/103/pdf")
	public String generate103PDF(Model model, @PathVariable int idEmpresa, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@ModelAttribute("tipoPago") TipoPago tipopago,
			@ModelAttribute("fecha") String fecha,
			@ModelAttribute("302") String c302,
			@ModelAttribute("303") String c303,
			@ModelAttribute("324") String c324,
			@ModelAttribute("349") String c349,
			@ModelAttribute("353") String c353,
			@ModelAttribute("374") String c374,
			@ModelAttribute("399") String c399,
			@ModelAttribute("499") String c499,
			@ModelAttribute("902") String c902,
			@ModelAttribute("905") String c905,
			@ModelAttribute("banco") String banco){
		
		Empresa empresa = empresaDao.findById(idEmpresa);
		
		PDFGenerator pdfGenerator = new PDFGenerator();
	/*	pdfGenerator.pdfAnexo103("31", fecha, empresa.getPersonalEmpresaByIdRepLegal().getId(), empresa.getPersonalEmpresaByIdContador().getId(), empresa.getRucContribuyente(), empresa.getNombre(),
				c302, c303, c324, c349, c353, c374, c399, c499, c902, c905, banco);
				*/
		
		try {
			descargarArchivo("D:\\anexo103.pdf", request, response);
		} catch (IOException e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al exportar en anexo pdf");
			e.printStackTrace();
		}
		
		File file = new File("D:\\anexo103.pdf");
		if (file.delete())
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no puede ser borrado");
		
		return "anexo/home";
	}
	
	/**
	 * Genera el reporte 104.
	 *
	 * @return El nombre de la vista que mostrara el anexo 104 html
	 * @since 1.0
	 */
	@RequestMapping("/104")
	public String generate104(Model model, @ModelAttribute("fecha") String fecha,@PathVariable int idEmpresa ) {
		
		String[] string = fecha.split(" ");
		String m = string[0];
		String a = string[1];
		int mes = 0;
		mes = Utility.getNumeroMes(m);
		int anno = Integer.valueOf(a);
		// la f es de factura, la n de nota de credito
		float c401, c403, c405, c407, c408, c431, c434, c506, c505, c404, n414, c402, n412, c421,c422,c429,c609, c721, c723, c725, c727, c729, nTotal, c903, c904, c563;
		c401 = c403 = c405= c405= c407= c408= c431= c434=c506= c505 = c404= n414=c402= n412 =c421=c422= c429= c609=c721= c723= c725= c727= c729 = nTotal=c563= 0;
		
		float n411, n413, n415, n417, n418, n441, n444, n514, n516, n513, n515, c480, n481, n543, n544, n442, n443;
		n411= n413= n415= n417= n418= n441= n444= n514= n516= n513= n515= c480= n481 = n543= n544= n442= n443= 0;
		
		float c500,c501,c502,c503,c504,c507,c508,n518,c531,c535,n510,n531,n511,n512,n545,c509,n519,n517,c406,n416;
		c500=c501=c502=c503=c504=c507=c508=n518=c531=c535=n510=n531=n511=n512=n545=c509=n519=n517=c406=n416= 0;
		Empresa empresa = empresaDao.findById(idEmpresa);
		List<Venta> listVentas = ventaDao.listOrderByIdCliente(idEmpresa, anno, mes);
		List<Exportacion> listExportaciones =  exportacionDao.list(idEmpresa, anno, mes);
		List<Compra> listCompras = compraDao.list(idEmpresa, anno, mes);
		List<Importacion> listImportaciones = importacionDao.list(idEmpresa, anno, mes);
		Float[] impuestoMora = calcularImpuestoMora(mes, anno, empresa.getRucContribuyente());
		c903 = impuestoMora[0];
		c904 = (float) (impuestoMora[1] *0.3);
		
		for (Venta venta : listVentas) {
			c609 += venta.getRetencion().getRetencionIVA() !=0 ? venta.getRetencion().getRetencionIVA() : 0;
			// el tipo de comprobante no es nota de crédito
			if(!venta.getTipoComprobante().getCodigo().equals("04")){
				c431 += venta.getBaseImpNoGravada();
				if(venta.getTipoVenta().getCodigo().equals("06")){
					c405 += venta.getBaseImp();
				} else {
					if(venta.getTipoVenta().getCodigo().equals("05")){
						for (VentaVsProducto ventaVSproducto : venta.getVentaVsProductos() ) {
							c434 = ventaVSproducto.getProducto().getValorUnitario() * ventaVSproducto.getCantidad() - ventaVSproducto.getDescuento();
						}
					} else if(venta.getTipoVenta().getCodigo().equals("07")){
						c406 += venta.getBaseImp();
					} else if(venta.getTipoVenta().getCodigo().equals("02")){
						c404 += venta.getBaseImp();
						c402 += venta.getBaseImpGravada();
					} else if(venta.getTipoVenta().getCodigo().equals("01")){
						c401 += venta.getBaseImpGravada();
						c403 += venta.getBaseImp();
					}
				}
			} else { 
				//tipo de comprobante 04 es una nota de crédito
					n441 += venta.getBaseImpNoGravada();
					if(venta.getTipoVenta().getCodigo().equals("06")){
						n415 += venta.getBaseImp();
					} else {
						if(venta.getTipoVenta().getCodigo().equals("05")){
							for (VentaVsProducto ventaVSproducto : venta.getVentaVsProductos() ) {
								n444 = ventaVSproducto.getProducto().getValorUnitario() * ventaVSproducto.getCantidad() - ventaVSproducto.getDescuento();
							}
						} else if(venta.getTipoVenta().getCodigo().equals("07")){
							n416 += venta.getBaseImp();
						} else if(venta.getTipoVenta().getCodigo().equals("02")){
							n414 += venta.getBaseImp();
							n412 += venta.getBaseImpGravada();
						} else if(venta.getTipoVenta().getCodigo().equals("01")){
							n411 += venta.getBaseImpGravada();
							n413 += venta.getBaseImp();
						}
					}
			}
			// se calculan los totales de ventas de forma de pago 0 ó 3
			if (venta.getTipoVentaSegunPago().getCodigo().equals("0")) {
				for (VentaVsProducto ventaVSproducto : venta.getVentaVsProductos() ) {
					c480 += ventaVSproducto.getProducto().getValorUnitario() * ventaVSproducto.getCantidad() - ventaVSproducto.getDescuento();
				}
			} else {
				if (venta.getTipoVentaSegunPago().getCodigo().equals("3")) {
					for (VentaVsProducto ventaVSproducto : venta.getVentaVsProductos() ) {
						n481 += ventaVSproducto.getProducto().getValorUnitario() * ventaVSproducto.getCantidad() - ventaVSproducto.getDescuento();
					}
				}
				
			}
		}
		
		for (Exportacion exportacion : listExportaciones) {
			// el tipo de comprobante no es nota de crédito
			if(!exportacion.getTipoComprobante().getCodigo().equals("04")) {
				// el tipo de exp 1 es un bien
				if(exportacion.getTipoExportacionImportacion().getIdTipoExpImp() == 1) {
					c407 += exportacion.getFob();
				} else {
					c408 += exportacion.getFob();
				}
			} else { //tipo de comprobante 04 es una nota de crédito
					if (exportacion.getTipoExportacionImportacion().getIdTipoExpImp() == 1) {
						n417 += exportacion.getFob();
					} else {
						n418 += exportacion.getFob();
					}
			}
		}
		
		for (Compra compra : listCompras) {
			float retencionIva = 0;
			// el tipo de comprobante no es nota de crédito
			String codCompra = compra.getTipoCompra().getCodigo();
			if(!compra.getTipoComprobante().getCodigo().equals("04")) {
				for (DetallesCompra detalle : compra.getDetallesCompras()) {
					 c531 = detalle.getBaseNoObjetoIva();
					 retencionIva = detalle.getRIvalorIva()*detalle.getRIret()/100;
					 if(detalle.getRIret() == 10){
						c721 += retencionIva; 
					 } else if(detalle.getRIret() == 20){
						 c723 += retencionIva;
					 } else if(detalle.getRIret() == 30){
						 c725 += retencionIva;
					 } else if(detalle.getRIret() == 70){
						 c727 += retencionIva;
					 } else if(detalle.getRIret() == 100){
						 c729 += retencionIva;
					 }
					 
					if(detalle.getPorcentajeIva() == 12 && (codCompra.equals("01") || codCompra.equals("05") || codCompra.equals("06"))){
						c500 += detalle.getBaseImponible12();
					} else {
						if(detalle.getPorcentajeIva() == 12 && codCompra.equals("03")){
							c501 += detalle.getBaseImponible12();
						} else {
							if(detalle.getPorcentajeIva() == 12 && (codCompra.equals("02") || codCompra.equals("04") || codCompra.equals("07"))){
								c502 += detalle.getBaseImponible12();
							}
						}
					}
					if(codCompra.equals("08") || codCompra.equals("09")){
						c535 += detalle.getBaseImponible12();
					}
				}
				
			} else { //tipo de comprobante 04 es una nota de crédito
				if(compra.getTipoComprobante().getCodigo().equals("04")){
					for (DetallesCompra detalle : compra.getDetallesCompras()) {
						 if(detalle.getRIret() == 10){
								c721 += retencionIva; 
							 } else if(detalle.getRIret() == 20){
								 c723 += retencionIva;
							 } else if(detalle.getRIret() == 30){
								 c725 += retencionIva;
							 } else if(detalle.getRIret() == 70){
								 c727 += retencionIva;
							 } else if(detalle.getRIret() == 100){
								 c729 += retencionIva;
							 }
						
						if(compra.getTipoComprobanteModificado().getCodigo().equals("02")){
							n518 += detalle.getBaseImponible12();
						}
						n531 += detalle.getBaseNoObjetoIva();
						if(detalle.getPorcentajeIva() == 12 && (codCompra.equals("01") || codCompra.equals("05") || codCompra.equals("06"))){
							n510 += detalle.getBaseImponible12();
						} else {
							if(detalle.getPorcentajeIva() == 12 && codCompra.equals("03")){
								n511 += detalle.getBaseImponible12();
							} else {
								if(detalle.getPorcentajeIva() == 12 && (codCompra.equals("02") || codCompra.equals("04") || codCompra.equals("07"))){
									n512 += detalle.getBaseImponible12();
								}
							}
						}
						if(codCompra.equals("08") || codCompra.equals("09")){
							n545 += detalle.getBaseImponible12();
						}
					}
				}
			}
		}
		
		for (Importacion importacion : listImportaciones) {
			// el tipo de comprobante no es nota de crédito
			if(!importacion.getTipoComprobante().getCodigo().equals("04")) {
				// el tipo de exp 1 es un bien
				if(importacion.getTipoExportacionImportacion().getIdTipoExpImp() == 1) {
					//verificar si no es un activo fijo
					if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("01") || importacion.getIdentificacionCreditoGasto().getCodigo().equals("06")){
						c504 += importacion.getBaseImponibleGravada();
					} else{
						if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("03")){
							c505 += importacion.getBaseImponibleGravada();
						} else {
							if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("02") || importacion.getIdentificacionCreditoGasto().getCodigo().equals("07")){
								c506 += importacion.getBaseImponibleGravada();
							} else {
								if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("04")){
									c507 += importacion.getBaseImponibleGravada();
								}
							}
						}	
					}
				} else {
					if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("01")){
						c503 += importacion.getBaseImponibleGravada();
					}
				}
			} // sino es una nota de crédito 
			else { 
				if(importacion.getTipoExportacionImportacion().getIdTipoExpImp() == 1) {
					if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("01") || importacion.getIdentificacionCreditoGasto().getCodigo().equals("06")){
						n514 += importacion.getBaseImponibleGravada();
					}else{
						if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("03")){
							n515 += importacion.getBaseImponibleGravada();
						} else {
							if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("02") || importacion.getIdentificacionCreditoGasto().getCodigo().equals("07")){
								n516 += importacion.getBaseImponibleGravada();
							} else {
								if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("04")){
									n517 += importacion.getBaseImponibleGravada();
								}
							}
						}
					}
				} else {
					if(importacion.getIdentificacionCreditoGasto().getCodigo().equals("01")){
						n513 += importacion.getBaseImponibleGravada();
					}
				}
			}
		}
		
		c509 = c500+c501+c502+c503+c504+c505+c506+c507+c508;
		n519 = (c500-n510)+(c501-n511)+(c502-n512)+(c503-n513)+(c504-n514)+(c505-n515)+(c506-n516)+n517+n518;
		float c520 = (c500-n510)*12/100;
		float c521 = (c501-n511)*12/100;
		float c522 = (c502-n512)*12/100;
		float c523 = (c503 - n513)*12/100;
		float c524 = (c504 - n514)*12/100;
		float c525 = (c505 - n515)*12/100;
		c563 = (c401 - n411) + (c402 - n412) + (c403 - n413) + (c405 - n415) + (c406 - n416) + (c407 - n417) + (c408 - n418);
		
		model.addAttribute("rucEmpresa", new String(empresa.getRucContribuyente()));
		model.addAttribute("razonSocialEmpresa", new String(empresa.getNombre()));
		model.addAttribute("idContadorEmpresa", new String(empresa.getPersonalEmpresaByIdContador().getId()));
		model.addAttribute("idRepLegalEmpresa", new String(empresa.getPersonalEmpresaByIdRepLegal().getId()));
		if(n411>c401){
			c421 = c401*12/100;
			model.addAttribute("c401", c401); model.addAttribute("c411", c401); model.addAttribute("c421", c421);
			n443 += n411-c401;
			nTotal += c401;
		} else { 
			c421 = (c401 - n411)*12/100;
			model.addAttribute("c401", c401); model.addAttribute("c411", c401 - n411); model.addAttribute("c421", c421); nTotal += (c401 - n411);
		}
		
		if(n412>c402){
			c422 = c402*12/100;
			model.addAttribute("c402", c402); model.addAttribute("c412", c402);  model.addAttribute("c422", c422);
			n443 += n412-c402;
			nTotal += c402;
		} else {
			c422 = (c402-n412)*12/100;
			model.addAttribute("c402", c402); model.addAttribute("c412", c402-n412); model.addAttribute("c422", c422); nTotal += (c402-n412);
			}
		
		if(n413>c403){
			model.addAttribute("c403", c403); model.addAttribute("c413", c403);
			n442 += n413-c403;
			nTotal += c403;
		} else {model.addAttribute("c403", c403); model.addAttribute("c413", c403 - n413); nTotal += (c403 - n413);} 
		
		if(n414>c404){
			model.addAttribute("c404", c404); model.addAttribute("c414", c404); 
			n442 += n414-c404;
			nTotal += c404;
		} else {model.addAttribute("c404", c404); model.addAttribute("c414", c404-n414); nTotal += (c404-n414);} 
		 
		if (n415>c405){
			model.addAttribute("c405", c405); model.addAttribute("c415", c405);  
			n442 += n415-c405;
			nTotal += c405;
		} else {model.addAttribute("c405", c405); model.addAttribute("c415", c405 - n415); nTotal += (c405 - n415);} 
		
		if(n416>c406){
			model.addAttribute("c406", c406); model.addAttribute("c416", c406);
			n442 += n416-c406;
			nTotal = c406;
		} else {model.addAttribute("c406", c406); model.addAttribute("c416", c406-n416); nTotal += (c406-n416);}
		  
		if(n417>c407){
			model.addAttribute("c407", c407); model.addAttribute("c417", c407);
			nTotal += c407;
		} else {model.addAttribute("c407", c407); model.addAttribute("c417", c407 - n417); nTotal += (c407 - n417);}
		  
		if(n418>c408){
			model.addAttribute("c408", c408); model.addAttribute("c418", c408);  
			nTotal += c408;
		} else {model.addAttribute("c408", c408); model.addAttribute("c418", c408 - n418);   nTotal += (c408 - n418);}
		
		float fTotal = c401 + c403 + c405 + c407 + c408;
		model.addAttribute("c409", fTotal); model.addAttribute("c419", nTotal);  model.addAttribute("c429", c422+c421);
		model.addAttribute("c431", c431); model.addAttribute("c441", c431 - n441);  
		model.addAttribute("c442", n442); model.addAttribute("c443", n443); model.addAttribute("c453", n443*12/100);  
		model.addAttribute("c434", c434); model.addAttribute("c444", c434 - n444);
		model.addAttribute("c480", c480);	model.addAttribute("c481", n481); 
		model.addAttribute("tipoPago", new TipoPago());
		model.addAttribute("tiposPago", tipoPagoDao.list());
		model.addAttribute("banco", new Banco());
		model.addAttribute("bancos", bancoDao.list());
		model.addAttribute("formato104", new formatoAnexo104());
		model.addAttribute("c102", anno);
		model.addAttribute("c101", mes);
		
		if(n510>c500){
			model.addAttribute("c500", c500); model.addAttribute("c510", c500); model.addAttribute("c520", c500*12/100);
			n544 += n510-c500;
		} else {model.addAttribute("c500", c500); model.addAttribute("c510", c500-n510); model.addAttribute("c520", c520);}
		
		if(n511>c501){
			model.addAttribute("c501", c501); model.addAttribute("c511", c501); model.addAttribute("c521", c501*12/100);
			n544 += n511-c501;
		} else {model.addAttribute("c501", c501); model.addAttribute("c511", c501-n511); model.addAttribute("c521", c521);}
		
		if(n512>c502){
			model.addAttribute("c502", c502); model.addAttribute("c512", c502); model.addAttribute("c522", c502*12/100);
			n544 += n512-c502;
		} else {model.addAttribute("c502", c502); model.addAttribute("c512", c502-n512); model.addAttribute("c522", c522);}
		
		if(n513>c503){
			model.addAttribute("c503", c503); model.addAttribute("c513", c503); model.addAttribute("c523", c503*12/100);
			n544 += n513-c503;
		} else {model.addAttribute("c503", c503); model.addAttribute("c513", c503 - n513); model.addAttribute("c523", c523);}
		
		if(n514>c504){
			model.addAttribute("c504", c504); model.addAttribute("c514", c504); model.addAttribute("c524", c504*12/100);
			n544 += n514-c504;
		} else {model.addAttribute("c504", c504); model.addAttribute("c514", c504 - n514); model.addAttribute("c524", c524);}
		
		if(n515>c505){
			model.addAttribute("c505", c505); model.addAttribute("c515", c505); model.addAttribute("c525", c505*12/100);
			n544 += n515-c505;
		} else {model.addAttribute("c505", c505); model.addAttribute("c515", c505 - n515); model.addAttribute("c525", c525);}
		
		if(n516>c506){
			model.addAttribute("c506", c506); model.addAttribute("c516", c506);
			n543 += n516-c506;
		} else {model.addAttribute("c506", c506); model.addAttribute("c516", c506 - n516);}
		
		if(n517>c507){
			model.addAttribute("c507", c507); model.addAttribute("c517", c507);
			n543 += n517-c507;
		} else {model.addAttribute("c507", c507); model.addAttribute("c517", c507-n517);}
		
		model.addAttribute("c508", c508); model.addAttribute("c518",c508-n518);
		model.addAttribute("c509",c509 ); model.addAttribute("c519",n519 ); model.addAttribute("c529", c520 + c521 + c522 + c523 + c524 + c525);
		model.addAttribute("c531", c531); model.addAttribute("c541", c531-n531);
		model.addAttribute("c543", n543); model.addAttribute("c544", n544);  model.addAttribute("c554", n544*12/100);
		model.addAttribute("c535", c535); model.addAttribute("c545", c535-n545); model.addAttribute("c555",(c535-n545)*12/100);
		model.addAttribute("c563", c563/nTotal > 0 ? c563/nTotal : 0.0);//563 = 411 + 412 + 415 + 416 + 417 + 418 /419
		model.addAttribute("c564", (c520 + c521 + c523 + c524 + c525) *c563);//564 = 520 + 521 + 523 + 524 + 525 *563
		model.addAttribute("c609", c609); model.addAttribute("c721", c721);
		model.addAttribute("c723", c723); model.addAttribute("c725", c725);
		model.addAttribute("c727", c727); model.addAttribute("c729", c729);
		model.addAttribute("c799", c721+c723+c725+c727+c729);
		model.addAttribute("c903", c903); model.addAttribute("c904", c904);
		
		
		return "anexo/104";
	}
	
	/**
	 * Genera el reporte 104 en formato xml.
	 *
	 * @return El nombre de la vista que mostrara el anexo 104 xml
	 * @since 1.0
	 */
	
	@RequestMapping(value = "/104/generarXml", method = RequestMethod.POST)
	public String generate104Xml(Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable int idEmpresa,RedirectAttributes redirectAttributes,
			@ModelAttribute("formato104") formatoAnexo104 formato104, 
			@ModelAttribute("rucEmpresa") String rucEmpresa,
			@ModelAttribute("razonSocialEmpresa") String nombreEmpresa,
			@ModelAttribute("idRepLegalEmpresa") String rucRepLegal,
			@ModelAttribute("banco") Banco banco) {

		String directorioEmpresaActual = context.getRealPath("/resources");
		try {
			XmlGenerator.generateXmlFormulario104(rucEmpresa, nombreEmpresa, rucRepLegal, banco.getIdBanco(), formato104, context);
		} catch (Exception e1) {
			logger.error("This is Error message", e1);
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al generar el xml");
			return Utility.goToUrl(idEmpresa, "anexos/104");
		}
		
		try {
			descargarArchivo(directorioEmpresaActual + "\\Formulario104.xml", request, response);
		} catch (IOException e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al exportar el anexo xml");
			return Utility.goToUrl(idEmpresa, "anexos/104");
		}
		File file = new File(directorioEmpresaActual + "\\Formulario104.xml");
		if (file.delete())
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no puede ser borrado");
			
		return "anexo/home";
	}
	
	/**
	 * Genera el reporte 107.
	 *
	 * @return El nombre de la vista que mostrara el anexo 107 html
	 * @since 1.0
	 */
	
	@RequestMapping(value = "/107", method = RequestMethod.GET)
	public String generate107(Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable int idEmpresa) {

		Empresa empresa = empresaDao.findById(idEmpresa);
		model.addAttribute("movimiento", new Movimiento());
		model.addAttribute("rucEmpresa", new String(empresa.getRucContribuyente()));
		model.addAttribute("razonSocialEmpresa", new String(empresa.getNombre()));
		model.addAttribute("idContadorEmpresa", new String(empresa.getPersonalEmpresaByIdContador().getId()));
		model.addAttribute("trabajadores", personalEmpresaDao.list(PersonalEmpresaEnum.TRABAJADOR, idEmpresa));
	       
		return "anexo/107";
	}
	
	@RequestMapping(value = "/107", method = RequestMethod.POST)
	public String generate(Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable int idEmpresa, @ModelAttribute("idTrabajador") String idTrabajador,
			@ModelAttribute("fecha") String fecha, RedirectAttributes redirectAttributes) {

		//FIXME esto se hace porq no todos los navegadores reconocen un input tipo Date entonces hay q coger la fecha como String
		String[] string = fecha.split(" ");
		String m = string[0];
		String a = string[1];
		int mes = 0;
		int exoneracionDiscapacidad = 0;
		int exoneracionTerceraEdad = 0;
		int fraccionBasica = 10800;
		boolean coicidencia = false;
		int cont = 0;
		
		mes = Utility.getNumeroMes(m);
		int anno = Integer.valueOf(a);
		
		Movimiento movimiento = movimientoDao.findByIdTrabajador(idTrabajador, mes, anno);
		if(movimiento == null){
			redirectAttributes.addFlashAttribute("info", "No existen movimientos en la fecha seleccionada para este trabajador");
			return Utility.goToUrl(idEmpresa, "anexos/107");
		}
		
		PersonalEmpresa trabajador = personalEmpresaDao.findById(idTrabajador);
		List<Discapacidad> discapacidades = discapacidadDao.list();
		
		// si la condicion del trabajador es 2 es porq tiene discapacidad
		if(trabajador.getCondicionDeTrabajador().getId() == 2){
			while (!coicidencia) {
				if(trabajador.getPorcentaje() >= discapacidades.get(cont).getPorcentajeDesde() && trabajador.getPorcentaje() <= discapacidades.get(cont).getPorcentajeHasta()){
					exoneracionDiscapacidad = ((fraccionBasica * 2) * discapacidades.get(cont).getAplicacionBeneficio())/100;
					coicidencia = true;
				}
				cont++;
			}
		}
	
		Date dt = trabajador.getFechaNacimiento();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String fechaNacimiento = df.format(dt);
		String[] s = fechaNacimiento.split("-");
		int annoNac = Integer.valueOf(s[0]);
		int mesNac = Integer.valueOf(s[1]);
		
		Calendar c1 = Calendar.getInstance();
		int annoActual = c1.get(Calendar.YEAR);
		// se adiciona 1 al mes porq el calendario q se utiliza comienza en la valor de los meses en 0
		int mesActual = c1.get(Calendar.MONTH) +1;
		
		if(annoActual - annoNac >= 65){
			if(mesActual >= mesNac){
				exoneracionTerceraEdad = fraccionBasica * 2;
			}
		}
	
		Float baseImponibleGrav = movimiento.getSueldosYSalarios() + movimiento.getSobresueldos() + movimiento.getParticipacionUtilidades() + movimiento.getIngresosOtrosEmpleadores() + movimiento.getAportePersonalEmpleadorActual()
				+ movimiento.getIessOtrosEmpleadores() + movimiento.getVivienda() + movimiento.getSalud() + movimiento.getEducacion() + movimiento.getAlimentacion() + movimiento.getVestimenta() + movimiento.getIrAsumidoEmpleadorActual()
				+ exoneracionDiscapacidad + exoneracionTerceraEdad;

		Empresa empresa = empresaDao.findById(idEmpresa);
		model.addAttribute("movimiento", movimiento);
		model.addAttribute("rucEmpresa", new String(empresa.getRucContribuyente()));
		model.addAttribute("razonSocialEmpresa", new String(empresa.getNombre()));
		model.addAttribute("idContadorEmpresa", new String(empresa.getPersonalEmpresaByIdContador().getId()));
		model.addAttribute("baseImponibleGrav", baseImponibleGrav);
		model.addAttribute("exoneracionDisc", exoneracionDiscapacidad/12);
		model.addAttribute("exoneracionTercEdad", exoneracionTerceraEdad/12);
		model.addAttribute("trabajadores", personalEmpresaDao.list(PersonalEmpresaEnum.TRABAJADOR, idEmpresa));
		
		return "anexo/107";
	}
	
	
	/**
	 * Calcular el interés por mora para los formulario 103 y 104
	 *
	 * @return Retorna un arreglo con el impuesto calculado y los meses atrasados
	 * @since 1.0
	 */
	
	public Float[] calcularImpuestoMora(int mesDeclara, int annoDeclara, String rucEmpresa) {

		float valorImpuesto = 0;
		float difMes = 0;
		
		Calendar c1 = Calendar.getInstance();
		int annoActual = c1.get(Calendar.YEAR);
		// se adiciona 1 al mes porq el calendario q se utiliza comienza en la valor de los meses en 0
		int mesActual = c1.get(Calendar.MONTH) +1;
		int diaActual = c1.get(Calendar.DAY_OF_MONTH);
		
 		String string = rucEmpresa;
	    String digitoDeclara = string.substring(8, 9);
		int diaxRuc = getfechaDeclararxDigitoRuc(Integer.valueOf(digitoDeclara));
		
		if(mesActual >= mesDeclara){ // se calcula el impuesto con los valores del anno q declara (anno actual)
			if((mesActual-mesDeclara) == 1){
				if(diaActual > diaxRuc){
					ImpuestoMora impuesto = buscarImpuestoMora(mesDeclara, annoActual);
					valorImpuesto = impuesto.getImpuesto();
				}
			} else {
				if((mesActual-mesDeclara) > 1){
					if(diaActual <= diaxRuc){
						ImpuestoMora impuesto = buscarImpuestoMora(mesDeclara, annoActual);
						difMes = mesActual-mesDeclara;
						valorImpuesto = impuesto.getImpuesto() * (difMes);
					} else {
						difMes = (mesActual-mesDeclara) + 1;
						ImpuestoMora impuesto = buscarImpuestoMora(mesDeclara, annoActual);
						valorImpuesto = impuesto.getImpuesto() * difMes;
					}
				}
			}
		} else{ // se calcula el impuesto con los valores del anno q declara (anno anterior)
			if((mesDeclara-mesActual) == 11){
				if(diaActual > diaxRuc){
					ImpuestoMora impuesto = buscarImpuestoMora(mesDeclara, annoDeclara);
					valorImpuesto = impuesto.getImpuesto();
				}
			} else {
				if((mesDeclara-mesActual) < 11){
					difMes = (12 - mesDeclara) + mesActual;
					
					if(diaActual <= diaxRuc){
						ImpuestoMora impuesto = buscarImpuestoMora(mesDeclara, annoDeclara);
						valorImpuesto = impuesto.getImpuesto() * difMes;
					} else {
						ImpuestoMora impuesto = buscarImpuestoMora(mesDeclara, annoDeclara);
						difMes += 1;
						valorImpuesto = impuesto.getImpuesto() * (difMes);
					}
				}
			}
		}
		
		Float[] valores = {valorImpuesto, difMes};
		return valores;
	}
	
	private ImpuestoMora buscarImpuestoMora(int mesDeclara, int anno) {
		ImpuestoMora impuesto = impuestoMoraDao.getImpuestoMora(mesDeclara, anno);
		if(impuesto == null){
			ImpuestoMora impuestoVacio = new ImpuestoMora(0, 0, 0, 0, 0);
			return impuestoVacio;
		}
		return impuesto;
	}
	
	private PuntoEmision puntoEmisionVSUsuario(@PathVariable int idEmpresa, HttpServletRequest request) {
		
		String nombreUsuario = request.getSession().getAttribute("nombreUsuario").toString();
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		PuntoEmision ptoEmision = puntoEmisionDao.findByUserIdAndEnterpriseId(usuario.getIdUsuario(), idEmpresa);
		
		return ptoEmision;
	}
	
	/**
	 * Se crea el archivo para descargar el reporte.
	 *
	 * @throws IOException 
	 * @since 1.0
	 */
	private void descargarArchivo(String pathArchivo, HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			File downloadFile = new File(pathArchivo);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	 
	        // set content attributes for the response
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        int BUFFER_SIZE = 4096;
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
		
	}
	
	/**
	 * Dado el digito de ruc devuelve el dia del mes q declara
	 * 
	 * @param mes
	 * @since 1.0
	 */	
	public static int getfechaDeclararxDigitoRuc(int digito){
		
		switch (digito) {
		case 0:
			return 28;
		case 1:
			return 10;
		case 2:
			return 12;
		case 3:
			return 14;
		case 4:
			return 16;
		case 5:
			return 18;
		case 6:
			return 20;
		case 7:
			return 22;
		case 8:
			return 24;
		case 9:
			return 26;
		default:
			return -1;
		}
	}

	

}
