package ec.eac.sitac;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import ec.eac.sitac.dao.DiscapacidadHome;
import ec.eac.sitac.dao.ImpuestoRentaHome;
import ec.eac.sitac.dao.MovimientoHome;
import ec.eac.sitac.dao.PersonalEmpresaHome;
import ec.eac.sitac.model.Discapacidad;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.Importacion;
import ec.eac.sitac.model.ImpuestoRenta;
import ec.eac.sitac.model.Movimiento;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.util.PersonalEmpresaEnum;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/movimientos.
 * Controlador de Movimientos de los Trabajadores.
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/movimientos")
public class MovimientoController {
	@Autowired
	MovimientoHome movimientoDao;
	@Autowired
	PersonalEmpresaHome personalEmpresaDao;
	@Autowired
	DiscapacidadHome discapacidadDao;
	@Autowired
	ImpuestoRentaHome impuestoRentaDao;
	
	private static final Logger logger = Logger.getLogger(MovimientoController.class);
	
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
		
		//binder.setValidator(fileValidator);
	}
	
	/**
	 * Muestra una vista con la lista los movimientos de la empresa.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(Model model, @PathVariable int idEmpresa) {
		
		model.addAttribute("movimientos", movimientoDao.list(idEmpresa));

		return "movimiento/home";
	}

	/**
	 * Crea la vista para adicionar un nuevo movimiento.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model, @PathVariable int idEmpresa) {
		
		model.addAttribute("movimiento", new Movimiento());
		model.addAttribute("trabajador", new PersonalEmpresa());
		model.addAttribute("editandoMovimiento", new Boolean(false));
		
		model.addAttribute("trabajadores", personalEmpresaDao.list(PersonalEmpresaEnum.TRABAJADOR, idEmpresa));

		return "movimiento/datos";
	}

	/**
	 * Crea la vista para editar un movimiento.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") int idMovimiento,	Model model, @PathVariable int idEmpresa) {

		Movimiento movimiento = movimientoDao.findById(idMovimiento);
		
		model.addAttribute("movimiento", movimiento);
		model.addAttribute("trabajador", movimiento.getPersonalEmpresa());
		model.addAttribute("editandoMovimiento", new Boolean(true));
		
		model.addAttribute("trabajadores", personalEmpresaDao.list(PersonalEmpresaEnum.TRABAJADOR, idEmpresa));

		return "movimiento/datos";
	}

	/**
	 * Elimina un movimiento.
	 *
	 * @return Redirecciona a la vista que muestra los movimientos actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") int movimientoId, Model model, RedirectAttributes redirectAttributes) {

		Movimiento movimiento = movimientoDao.findById(movimientoId);
		try {
			movimientoDao.delete(movimiento);
		} catch (Exception e) {
			logger.error("This is Error message", e);
			redirectAttributes.addFlashAttribute("error", "Error al eliminar el movimiento");
			return Utility.goToUrl(idEmpresa, "movimientos");
		}

		redirectAttributes.addFlashAttribute("ok", "El Momiviento del trabajador fue eliminado satisfactoriamente.");
		return Utility.goToUrl(idEmpresa, "movimientos");
	}
	
	/**
	 * Elimina movimientos via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, RedirectAttributes redirectAttributes) {

		for (int i = 0; i < datos.size(); i++) {
			Movimiento movimiento = movimientoDao.findById(datos.get(i));
			try {
				movimientoDao.delete(movimiento);
			} catch (Exception e) {
				logger.error("This is Error message", e);
				redirectAttributes.addFlashAttribute("error", "Error al eliminar el movimiento");
				return false;
			}
		}
		return true;
	}

	/**
	 * Guarda los datos del movimiento.
	 *
	 * @return Redirecciona a la vista que muestra los movimientos actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, RedirectAttributes redirectAttributes,
			@ModelAttribute("movimiento") Movimiento movimiento,
														@ModelAttribute("trabajador") PersonalEmpresa trabajador,
														@ModelAttribute("editandoMovimiento") Boolean editandoMovimiento) {
		
		if (editandoMovimiento) {
			movimientoDao.attachDirty(movimiento, trabajador.getId(), idEmpresa);
		} else{
			movimientoDao.persist(movimiento, trabajador.getId(), idEmpresa);
		}
			
		redirectAttributes.addFlashAttribute("ok", "Los datos del movimiento se guardaron correctamente");
		return Utility.goToUrl(idEmpresa, "movimientos");
	}
	
	
	/**
	 * Controlador q recibe datos Json para calcular exoneraciones
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value ={ "/datos/exoneracion", "/editar/exoneracion"}, method = RequestMethod.POST)
	public @ResponseBody Float[] exoneraciones(@RequestBody String trabajador, @PathVariable int idEmpresa) {

		String[] t = trabajador.split("=");
		String idTrabajador = t[1];
		boolean coicidencia = false;
		int cont = 0;
		float exoneracionDiscapacidad = 0;
		float exoneracionTerceraEdad = 0;
		int fraccionBasica = 10800;
		float baseImponible = 0;
		float valorRetenido = 0;

		PersonalEmpresa trabajadorExistente = personalEmpresaDao.findById(idTrabajador);
		if(trabajadorExistente != null){
			List<Discapacidad> discapacidades = discapacidadDao.list();

			// si la condicion del trabajador es 2 es porq tiene discapacidad
			if(trabajadorExistente.getCondicionDeTrabajador().getId() == 2){
				while (!coicidencia) {
					if(trabajadorExistente.getPorcentaje() >= discapacidades.get(cont).getPorcentajeDesde() && trabajadorExistente.getPorcentaje() <= discapacidades.get(cont).getPorcentajeHasta()){
						exoneracionDiscapacidad = ((fraccionBasica * 2) * discapacidades.get(cont).getAplicacionBeneficio())/100;
						coicidencia = true;
					}
					cont++;
				}
			}

			Date dt = trabajadorExistente.getFechaNacimiento();
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

			if(!trabajadorExistente.getMovimientos().isEmpty()) {
				baseImponible = (float) movimientoDao.sumBaseImponibleTrabajador(idEmpresa, idTrabajador);
				valorRetenido = (float) movimientoDao.sumValorRetenidoTrabajador(idEmpresa, idTrabajador);
			}
		}	
		Float[] result = {exoneracionDiscapacidad, exoneracionTerceraEdad, baseImponible, valorRetenido};
		return result;
	}
	
	
	/**
	 * Controlador q recibe datos Json para calcular exoneraciones
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value ={ "/datos/impuesto", "/editar/impuesto"}, method = RequestMethod.POST)
	public @ResponseBody Float inpuestoRenta(@RequestBody String datos, @PathVariable int idEmpresa) {

		String[] t = datos.split("=");
		float baseImponible = Float.valueOf(t[1]);
		float impuestoRentaCausado = 0;

		if(baseImponible > 0) {
		ImpuestoRenta impuestoRenta = impuestoRentaDao.getImpuestoRenta(baseImponible);
		impuestoRentaCausado = ((baseImponible - impuestoRenta.getFraccionBasica()) * impuestoRenta.getImpuestoFraccionExcedente())/100 + impuestoRenta.getImpuestoFraccionBasica();
		return impuestoRentaCausado;
		}
		else {
			return impuestoRentaCausado;
		}
		
	}

}
