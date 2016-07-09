package ec.eac.sitac.util;

import org.springframework.beans.factory.annotation.Autowired;

import ec.eac.sitac.dao.UsuarioHome;

public class CustomSeguridad {
	@Autowired
	static UsuarioHome usuarioDao;
	
	public static boolean usuarioTienePermiso(int idEmpresa, String nombreUsuario)
	{
		if (!usuarioDao.existeUsuarioVsEmpresa(idEmpresa, nombreUsuario))
			return false;
		
		return true;
	}
	
}
