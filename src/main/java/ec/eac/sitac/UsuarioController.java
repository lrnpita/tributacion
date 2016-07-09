package ec.eac.sitac;


import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import ec.eac.sitac.dao.PuntoEmisionHome;
import ec.eac.sitac.dao.RolHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.Rol;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.util.Utility;

/**
 * Maneja las peticiones que vienen por sitac/empresa/#empresa/usuario.
 * Controlador de Usuario.
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
@Controller
@RequestMapping("admin/empresas/{idEmpresa}/usuarios")
public class UsuarioController {
	@Autowired
	RolHome rolDao;
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	PuntoEmisionHome puntoEmisionDao;
	
	private static final Logger logger = Logger.getLogger(UsuarioController.class);

	/**
	 * Muestra una vista con la lista los usuarios.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping("")
	public String home(@PathVariable int idEmpresa, Model model) {
		model.addAttribute("usuarios", usuarioDao.list(idEmpresa));

		return "usuario/home";
	}

	/**
	 * Crea la vista para adicionar un nuevo usuario.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public String nuevo(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("rol", new Rol());
		model.addAttribute("roles", rolDao.list());
		model.addAttribute("puntoEmision", new PuntoEmision());
		model.addAttribute("editandoUsuario", new Boolean(false));
		
		return "usuario/datos";
	}

	/**
	 * Crea la vista para editar un usuario.
	 *
	 * @return El nombre de la vista.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(@PathVariable int idEmpresa, @RequestParam("id") int usuarioId, Model model) {
		
		Usuario usuario = usuarioDao.findById(usuarioId);
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("rol", usuario.getRol());
		model.addAttribute("roles", rolDao.list());
		model.addAttribute("puntoEmision", usuario.getPuntoEmision(idEmpresa));
		model.addAttribute("editandoUsuario", new Boolean(true));
		
		return "usuario/datos";
	}

	/**
	 * Elimina un usuario.
	 *
	 * @return Redirecciona a la vista que muestra los usuarios actualizados.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idEmpresa, @RequestParam("id") int usuarioId, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioDao.findById(usuarioId);
		try
		{
			if (usuario.getRol().getIdRol() == 1) {

				redirectAttributes.addFlashAttribute("error", "El usuario Aministrador del Sistema no puede ser eliminado");
				return Utility.goToUrlAdmin(idEmpresa, "usuarios");

			} else {
				Set<PuntoEmision> ptosEmision = usuario.getPuntosEmision();
				for (PuntoEmision puntoEmision : ptosEmision) {
					puntoEmisionDao.delete(puntoEmision);
				}

				try
				{
					usuarioDao.delete(usuario);
				}
				catch ( SQLException sqlEx)
				{
					logger.error("This is Error message", sqlEx);
					redirectAttributes.addFlashAttribute("error", "No se puede eliminar al usuario debido a que existe información asociada a él en el sistema.");
					return Utility.goToUrlAdmin(idEmpresa, "usuarios");
				}
			}
		}
		catch (Exception ex) // tratando de insertar un objeto que no es ni cliente ni proveedor
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error", "Error al elimianr el usuario");
			return Utility.goToUrlAdmin(idEmpresa, "usuarios");
		}

		redirectAttributes.addFlashAttribute("ok", "El usuario fue elminado correctamente");
		return Utility.goToUrlAdmin(idEmpresa, "usuarios");
	}
	
	/**
	 * Elimina usuarios via AJAX.
	 *
	 * @return true si los elementos fueron eliminados satisfactoriamente.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public @ResponseBody boolean eliminar(@RequestBody List<Integer> datos) {
		boolean ok = true; 
		for (int i = 0; i < datos.size(); i++) {
			Usuario usuario = usuarioDao.findById(datos.get(i));

			// si el usuario es admin no se puede eliminar
			if (usuario.getRol().getIdRol() == 1) {
				ok = false;
			} else {
				Set<PuntoEmision> ptosEmision = usuario.getPuntosEmision();
				for (PuntoEmision puntoEmision : ptosEmision) {
					try {
						puntoEmisionDao.delete(puntoEmision);
					} catch (SQLException e) {
						logger.error("This is Error message", e);
						ok = false;
					} catch (Exception e) {
						logger.error("This is Error message", e);
						ok = false;
					}
				}
				try {
					if(ok){
						usuarioDao.delete(usuario);
					}
				} catch (SQLException e) {
					logger.error("This is Error message", e);
					ok = false;
				}
			}
		}
		return ok;
	}

	/**
	 * Guarda los datos del usuario.
	 *
	 * @return Redirecciona a la vista que muestra los usuarios actualizados.
	 *
	 * @since 1.0
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@PathVariable int idEmpresa, @ModelAttribute("usuario") Usuario usuario, @ModelAttribute("rol") Rol rol, 
			@ModelAttribute("puntoEmision") PuntoEmision puntoEmision, @ModelAttribute("editandoUsuario") boolean editarUsuario, RedirectAttributes redirectAttributes) {
		try
		{
			if(usuarioDao.existeUsuario(usuario.getNombreUsuario(), usuario.getIdUsuario())){
				redirectAttributes.addFlashAttribute("error", "Ya existe un usuario con nombre de usuario: " + usuario.getNombreUsuario());
				return Utility.goToUrlAdmin(idEmpresa, "usuarios/datos");
			}
			// encriptando la contraseña del usuario
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
			usuario.setContrasena(contrasenaEncriptada);
			
			if (editarUsuario) {
				usuarioDao.attachDirty(rol.getIdRol(), idEmpresa, puntoEmision, usuario);
			}
			else{
				usuarioDao.persist(rol.getIdRol(), idEmpresa, puntoEmision, usuario);
			}
		}
		catch (HibernateJdbcException ex)
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error","Error al guardar el usuario");
			return Utility.goToUrlAdmin(idEmpresa, "usuarios");
		}
		catch (Exception ex) // tratando de insertar un objeto que no es ni cliente ni proveedor
		{
			logger.error("This is Error message", ex);
			redirectAttributes.addFlashAttribute("error","Error al guardar el usuario");
			return Utility.goToUrlAdmin(idEmpresa, "usuarios");
		}

		redirectAttributes.addFlashAttribute("ok", "Los datos del usuario se insertaron correctamente");
		return Utility.goToUrlAdmin(idEmpresa, "usuarios");
	}
}
