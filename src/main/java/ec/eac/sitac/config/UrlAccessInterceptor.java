package ec.eac.sitac.config;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ec.eac.sitac.dao.UsuarioHome;
import ec.eac.sitac.model.AutorizarPermisoControlador;
import ec.eac.sitac.model.DenegarPermisoControlador;
import ec.eac.sitac.model.Usuario;
import ec.eac.sitac.util.CustomURL;

public class UrlAccessInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UsuarioHome usuarioDao;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {

		CustomURL url = new CustomURL(request.getRequestURI().toString());
		int idEmpresa = url.getIdEmpresa();

		if (idEmpresa < 0)
			return;  

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombreUsuario = auth.getName(); //get logged in username

		// Si el usuario actual tiene acceso a la empresa actual
		Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
		if(usuario.getRol().getIdRol() != 1){
			if (!usuarioDao.existeUsuarioVsEmpresa(idEmpresa, nombreUsuario))
				response.sendRedirect("/sitac/errorPage");
		}
		
		String accion = url.getAccion();
		String controlador = url.getNombreControlador();
		
		if (controlador == null || (controlador.equals("empresas") && accion.equals("Leer")) )
			return;
	
		List<DenegarPermisoControlador> permisosDenegados = (List<DenegarPermisoControlador>)request.getSession().getAttribute("permisosDenegados");
		if (permisosDenegados != null && permisosDenegados.size() != 0) {

			// implementar permisos
			Class noparams[] = {};

			for (DenegarPermisoControlador permiso : permisosDenegados) {

				Class cls = Class.forName("ec.eac.sitac.model.DenegarPermisoControlador");
				Method methodAcccion = cls.getDeclaredMethod("is" + accion, noparams);
				Method methodControlador = cls.getDeclaredMethod("getControlador", noparams);

				if ( Boolean.parseBoolean(methodAcccion.invoke(permiso, null).toString()) && methodControlador.invoke(permiso, noparams).equals(controlador))
					response.sendRedirect("/sitac/errorPage");
			}

			
		} else {
			
			List<AutorizarPermisoControlador> permisosAutorizados = (List<AutorizarPermisoControlador>)request.getSession().getAttribute("permisosAutorizados");
			if ( permisosAutorizados != null && permisosAutorizados.size() != 0) {
			
				// implementar permisos
				// implementar permisos
				Class noparams[] = {};
				boolean acceso = false;

				for (AutorizarPermisoControlador permiso : permisosAutorizados) {

					Class cls = Class.forName("ec.eac.sitac.model.AutorizarPermisoControlador");
					Method methodAccion = cls.getDeclaredMethod("is" + accion, noparams);
					Method methodControlador = cls.getDeclaredMethod("getControlador", noparams);

					if ( methodControlador.invoke(permiso, noparams).equals(controlador) && Boolean.parseBoolean(methodAccion.invoke(permiso, null).toString()) )
						acceso = true;
				}
				
				if (!acceso)
					response.sendRedirect("/sitac/errorPage");
			}
		}
	}
}
