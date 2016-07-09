package ec.eac.sitac;

import java.util.List;

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

import ec.eac.sitac.dao.IrpnrHome;
import ec.eac.sitac.dao.ProductoHome;
import ec.eac.sitac.dao.TarifaIvaHome;
import ec.eac.sitac.dao.TipoIceHome;
import ec.eac.sitac.dao.TipoProductoHome;
import ec.eac.sitac.dao.VentaVsProductoHome;
import ec.eac.sitac.model.Irpnr;
import ec.eac.sitac.model.Movimiento;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.Producto;
import ec.eac.sitac.model.TarifaIva;
import ec.eac.sitac.model.TipoIce;
import ec.eac.sitac.model.TipoProducto;
import ec.eac.sitac.model.VentaVsProducto;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/producto.
 * Controlador de Productos.
 *
 * @author Lorena Pita lrnpita@gmail.com
 * @version 1.0
 *
 * @since 2015
 */
@Controller
@RequestMapping("empresas/{idEmpresa}/productos")
public class ProductoController {
	@Autowired
	ProductoHome productoDao;
	@Autowired
	TipoProductoHome tipoProductoDao;
	@Autowired
	TipoIceHome tipoIceDao;
	@Autowired
	TarifaIvaHome tarifaIvaDao;
	@Autowired
	IrpnrHome irpnrDao;
	@Autowired
	VentaVsProductoHome ventaVsProductoDao;
	
	private static final Logger logger = Logger.getLogger(ProductoController.class);
	
	/**
	 * Muestra una vista con la lista los productos.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("/admin/empresas/productos")
	public String homeAdmin(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("productos", productoDao.list());
	
		return "producto/home";
	}
	
	/**
	 * Muestra una vista con la lista los productos.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping("")
	public String home(Model model, @PathVariable int idEmpresa) {
		
		model.addAttribute("producto", new Producto());
		model.addAttribute("productos", productoDao.list(idEmpresa));
	
		return "producto/home";
	}
	
	/**
	 * Crea la vista para adicionar un nuevo producto.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model) {
		
		createProductoObject(model);
		
		model.addAttribute("producto", new Producto());
		model.addAttribute("tipoProducto", new TipoProducto());
		model.addAttribute("tarifaIva", new TarifaIva());
		model.addAttribute("editandoProducto", new Boolean(false));
		
		return "producto/datos";
	}
	
	/**
	 * Crea la vista para editar un producto.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@RequestParam("id") int productoId, Model model) {
		
		createProductoObject(model);
		
		Producto producto = productoDao.findById(productoId);
		model.addAttribute("producto", producto);
		
		model.addAttribute("tipoProducto", producto.getTipoProducto());
		model.addAttribute("tarifaIva", producto.getTarifaIva());
		model.addAttribute("editandoProducto", new Boolean(true));
	
		return "producto/datos";
	}
	
	/**
	 * Elimina un movimiento.
	 *
	 * @return Redirecciona a la vista que muestra los producto actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") int productoId, Model model, RedirectAttributes redirectAttributes) {
		
		Producto producto = productoDao.findById(productoId);
		try
		{
		productoDao.delete(producto);
		}
		catch (Exception ex) // error al eliminar el producto
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto");
			return Utility.goToUrl(idEmpresa, "productos");
		}

		redirectAttributes.addFlashAttribute("ok", "El producto se eliminó correctamente");
		return Utility.goToUrl(idEmpresa, "productos");
	}
	
	/**
	 * Elimina productos via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos, RedirectAttributes redirectAttributes) {

		boolean ok = true; 
		for (int i = 0; i < datos.size(); i++) {

			if(ventaVsProductoDao.findByProductoId(datos.get(i)).size() != 0){
				ok = false;
			} else {
				Producto producto = productoDao.findById(datos.get(i));
				
				try
				{
				productoDao.delete(producto);
				}
				catch (Exception ex) // error al eliminar el producto
				{
					logger.error("This is Error message", ex);
					redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto");
					return false;
				}
			}
		}
		return ok;
	}
	
	/**
	 * Guarda los datos del producto.
	 *
	 * @return Redirecciona a la vista que muestra los productos actualizados.
	 *
	 * @since 2015
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, @ModelAttribute("producto") Producto producto, 
						@ModelAttribute("tipoProducto") TipoProducto tipoProducto, 
						@ModelAttribute("tarifaIva") TarifaIva tarifaIva,
						@ModelAttribute("editandoProducto") Boolean editandoProducto, RedirectAttributes redirectAttributes) {
		try
		{
			if (editandoProducto) {
				productoDao.attachDirty(producto, tipoProducto.getIdTipoProducto(), tarifaIva.getCodigo(), idEmpresa);
			}
			else {
				productoDao.persist(producto, tipoProducto.getIdTipoProducto(), tarifaIva.getCodigo(), idEmpresa);
			}
		}
		catch (Exception ex) // error al guardar el producto
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al guardar el producto"); // FIXME Mostrar mensaje amigable al usuario
			return Utility.goToUrl(idEmpresa, "productos");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos del producto de guardaron correctamente");
		return Utility.goToUrl(idEmpresa, "productos");
	}
	
	
	/**
	 * Crea el objeto Producto que será almacenado o editado en la BD
	 * 
	 * @param model
	 */
	private void createProductoObject(Model model) {
		model.addAttribute("tarifasIva", tarifaIvaDao.list());
		model.addAttribute("tiposProducto", tipoProductoDao.list());
	}
	
}
