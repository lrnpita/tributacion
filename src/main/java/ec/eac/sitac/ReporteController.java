package ec.eac.sitac;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.xml.sax.SAXException;

import ec.eac.sitac.dao.CompraHome;
import ec.eac.sitac.dao.EmpresaHome;
import ec.eac.sitac.dao.ExportacionHome;
import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.dao.VentaHome;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.util.Utility;
import ec.eac.sitac.util.XmlGenerator;


/**
 * Maneja las peticiones que vienen por sitac/reportes.
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
@RequestMapping("empresas/{idEmpresa}/reportes")
public class ReporteController extends AbstractController {
	@Autowired
	CompraHome compraDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	@Autowired
	VentaHome ventaDao;
	@Autowired
	ExportacionHome exportacionDao;
	@Autowired
	EmpresaHome empresaDao;
	
	/**
	 * Muestra una vista para generar reportes.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(Model model) {
	
		return "reporte/home";
	}
	
	
	/**
	 * Genera el reporte ATS.
	 *
	 * @return El nombre de la vista que mostrara el reporte
	 *
	 * @since 2015
	 */
	@RequestMapping("/ats")
	public ModelAndView generateATS(Model model, @PathVariable int idEmpresa,
			@ModelAttribute("fecha") String fecha	) {
		
		Empresa empresa = empresaDao.findById(idEmpresa);
		model.addAttribute("razonSocial", empresa.getNombre());
		model.addAttribute("ruc", empresa.getRucContribuyente());
		
	/*	DateFormat df = new SimpleDateFormat("yyyy-MM");
		String reportDate = df.format(fecha);
		String[] string = reportDate.split("-");
		int anno = Integer.valueOf(string[0]);
		int mes = Integer.valueOf(string[1]);*/
		
		//FIXME esto se hace porq no todos los navegadores reconocen un input tipo Date entonces hay q coger la fecha como String
		String[] string = fecha.split(" ");
		String m = string[0];
		String a = string[1];
		int mes = 0;
		
		mes = Utility.getNumeroMes(m);
		int anno = Integer.valueOf(a);
		
		List<Compra> compras = compraDao.list(idEmpresa, anno, mes);
		List<Venta> ventas = ventaDao.list(idEmpresa, anno, mes);
		List<Exportacion> exportaciones = exportacionDao.list(idEmpresa, anno, mes);
		
		model.addAttribute("listCompras", compras);
		model.addAttribute("listVentas", ventas);
		model.addAttribute("listExportaciones", exportaciones);
		model.addAttribute("mes", mes);
		model.addAttribute("anno", anno);
		
		return new ModelAndView("pdfATSView", "compras", model);
	}
	
	/**
	 * Genera el reporte 103.
	 *
	 * @return El nombre de la vista que mostrara el reporte en formato PDF
	 *
	 * @since 2015
	 */
	@RequestMapping("/103")
	public ModelAndView generate103(Model model, @PathVariable int idEmpresa, HttpServletRequest request,
			@ModelAttribute("fecha") String fecha) {
		
	/*	DateFormat df = new SimpleDateFormat("yyyy-MM");
		String reportDate = df.format(fecha);
		String[] string = reportDate.split("-");
		int anno = Integer.valueOf(string[0]);
		int mes = Integer.valueOf(string[1]);*/
		
		//FIXME esto se hace porq no todos los navegadores reconocen un input tipo Date entonces hay q coger la fecha como String
		String[] string = fecha.split(" ");
		String m = string[0];
		String a = string[1];
		int mes = 0;
		
		mes = Utility.getNumeroMes(m);
		int anno = Integer.valueOf(a);
		
		List<Compra> ListCompras = compraDao.list(idEmpresa, anno, mes);
		
		model.addAttribute("ListCompra", ListCompras);
		model.addAttribute("puntoEmision", puntoEmisionVSUsuario(idEmpresa, request));
		
		return new ModelAndView("pdf103View", "compras", model);
	}
	
	/**
	 * Genera el reporte 104.
	 *
	 * @return El nombre de la vista que mostrara el reporte 104 xml
	 * @throws Exception 
	 * @throws IOException 
	 * @throws SAXException 
	 * @since 2015
	 */
	@RequestMapping("/104")
	public String generate104(Model model) throws SAXException, IOException, Exception {
		
		
		return "redirect:/empresas/{idEmpresa}/reportes";
	}
	
	/**
	 * Genera el reporte 104.
	 *
	 * @return El nombre de la vista que mostrara el reporte 107 xml
	 * @throws Exception 
	 * @throws IOException 
	 * @throws SAXException 
	 * @since 2015
	 */
	
	@RequestMapping("/107")
	public String generate107 (Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
			       
		return "redirect:/empresas/{idEmpresa}/reportes";
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String output = ServletRequestUtils.getStringParameter(request, "output");
		
		//dummy data
		Map<String,String> revenueData = new HashMap<String,String>();
		revenueData.put("1/20/2010", "$100,000");
		revenueData.put("1/21/2010", "$200,000");
		revenueData.put("1/22/2010", "$300,000");
		revenueData.put("1/23/2010", "$400,000");
		revenueData.put("1/24/2010", "$500,000");
		
		if(output ==null || "".equals(output)){
			//return normal view
			return new ModelAndView("RevenueSummary","revenueData",revenueData);
			
		}else if("PDF".equals(output.toUpperCase())){
			//return excel view
			return new ModelAndView("PdfRevenueSummary","revenueData",revenueData);
			
		}else{
			//return normal view
			return new ModelAndView("RevenueSummary","revenueData",revenueData);
		}
		
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
	
}
