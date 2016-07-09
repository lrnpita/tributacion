package ec.eac.sitac;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ec.eac.sitac.dao.AutorizarPermisoControladorHome;
import ec.eac.sitac.dao.DenegarPermisoControladorHome;
import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.model.AutorizarPermisoControlador;
import ec.eac.sitac.model.DenegarPermisoControlador;
import ec.eac.sitac.model.Usuario;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	UsuarioHome usuarioDao;
	@Autowired
	DenegarPermisoControladorHome denegarPermisoControladorDao;
	@Autowired
	AutorizarPermisoControladorHome autorizarPermisoControladorDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "**/errorPage", method = RequestMethod.GET)
	public String errorPage( Model model) {
		return "errorPage";
	}
	
	  // Login form
	  @RequestMapping("/login")
	  public String login() {
	   return "login";
	  }
	  
	  // Login form
	  @RequestMapping("/loggedin")
	  public String successLogin(HttpServletRequest request) {

		  // obteniendo el nombre del usuario logueado
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  String name = auth.getName(); //get logged in username
		  request.getSession().setAttribute("nombreUsuario", name);
		  logger.info("El usuario " + name + " inició sesión.");

		  // guardando los permisos del usuario en la sesión
		  Usuario usuario = usuarioDao.findByUsername(name);
		  List<DenegarPermisoControlador> lstPermisosDenegados = denegarPermisoControladorDao.findByRolId(usuario.getRol().getIdRol());
		  List<AutorizarPermisoControlador> lstPermisosAutorizados = autorizarPermisoControladorDao.findByRolId(usuario.getRol().getIdRol());
		  
		  if (lstPermisosDenegados.size() > 0)
			  request.getSession().setAttribute("permisosDenegados", lstPermisosDenegados);
		  else if (lstPermisosAutorizados.size() > 0)
			  request.getSession().setAttribute("permisosAutorizados", lstPermisosAutorizados);
		  
		  return "redirect:/empresas";
	  }
	  
	  // Login form with error
	  @RequestMapping("/loginError")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);

	    return "login";
	  }
	  
}
