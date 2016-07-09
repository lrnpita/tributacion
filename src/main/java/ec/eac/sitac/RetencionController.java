package ec.eac.sitac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.itextpdf.text.DocumentException;
import ec.eac.sitac.dao.CompraHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.util.MimeMessegerSend;
import ec.eac.sitac.util.PDFGenerator;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/retenciones.
 * Controlador de Retenciones
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/retenciones")
public class RetencionController {
	
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
    JavaMailSender mailSender;
	@Autowired
    CompraHome compraDao;
	@Autowired
    ServletContext context;
	
	private static final Logger logger = Logger.getLogger(RetencionController.class);
	
	/**
	 * Se muestra un listado de retenciones.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model, @PathVariable int idEmpresa) {
	
		model.addAttribute("retenciones", compraDao.listRetenciones(idEmpresa));
		model.addAttribute("json", true);
		
		return "retencion/home";
	}
	
	/**
	 * Envía un correo con una retención
	 *
	 * @return El nombre de la vista
	 * @throws IOException 
	 * @throws DocumentException 
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/enviarRetencion", method = RequestMethod.GET)
	public String getCompra(@PathVariable int idEmpresa, @RequestParam("autorizacion") String autorizacion, Model model, RedirectAttributes redirectAttributes) {
		
		Compra compra = compraDao.FindByAutorizacionRetencion(autorizacion);
		// Se crea el PDF de retencion
		PDFGenerator pdfGenerator  = new PDFGenerator();
		try 
		{
			pdfGenerator.generarPDFRetencion(compra, context);
		} 
		catch (FileNotFoundException | DocumentException e) {
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error y no se pudo enviar el correo al proveedor");
			logger.error("This is Error message", e);
			return Utility.goToUrl(idEmpresa, "retenciones");
		}
		
		// se envía el correo al proveedor
		MimeMessegerSend messengerSend = new MimeMessegerSend(mailSender, context);
		messengerSend.enviarCompRetencion(compra);
		
		String directorio = context.getRealPath("/resources");
		File file = new File(directorio + File.separatorChar + compra.getIdCompra() + ".pdf");
		if (file.delete())
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no puede ser borrado");
	
		redirectAttributes.addFlashAttribute("ok", "El comprobante de retención fue enviado correctamente al proveedor");
		return Utility.goToUrl(idEmpresa, "retenciones");
	}

	
	/**
	 * Buscar retenciones por fecha.
	 *
	 * @return El nombre de la vista
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String search (@PathVariable int idEmpresa, @ModelAttribute("fecha") String search, Model model) {
		String[] datosFecha = search.split(" ");
		int anno = Integer.parseInt(datosFecha[1]);
		int mes = Utility.getNumeroMes(datosFecha[0]); // buscar número del mes 
		
		model.addAttribute("retenciones", compraDao.listRetenciones(idEmpresa, anno, mes));
		
		return "retencion/home";
	}
	
}
