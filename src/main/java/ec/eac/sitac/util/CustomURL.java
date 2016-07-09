package ec.eac.sitac.util;

public class CustomURL {
	String url;
	String elementos[];
	
	public CustomURL(String url) {
		this.url = url;
		this.elementos = url.split("/");
	}
	
	public int getIdEmpresa() {
		int idEmpresa = -1;

		if (esAdminURL()) {
			if (elementos.length > 4) {
				try
				{
					idEmpresa = Integer.parseInt(elementos[4]);
				}
				catch (NumberFormatException ex)
				{
					return idEmpresa; // id = -1
				}
			}
		} else {
			if (elementos.length > 3)
				idEmpresa = Integer.parseInt(elementos[3]);
		}
		
		return idEmpresa;
	}
	
	public String getNombreControlador() {
		String nombre = null;
		
		if (esAdminURL()) {
			if (elementos.length > 5) {
				try
				{
					nombre = elementos[5].toString();
				}
				catch (Exception ex)
				{
					return nombre; // NULL
				}
			}
		} else {
			if (elementos.length > 4) {
				try
				{
					nombre = elementos[4].toString();
				}
				catch (Exception ex)
				{
					return nombre; // NULL
				}
			}
		}
		
		return nombre;
	}
	
	public String getAccion() {
		String accion = "";
		
		if (esAdminURL()) {
			if (elementos.length > 6) {
				try
				{
					accion = elementos[6].toString();
				}
				catch (Exception ex)
				{
					return mapearAccion(accion); // NULL
				}
			}
		} else {
			if (elementos.length > 5) {
				try
				{
					accion = elementos[5].toString();
				}
				catch (Exception ex)
				{
					return mapearAccion(accion); // NULL
				}
			}
		}
		
		return mapearAccion(accion);
	}

	public boolean esAdminURL() {
		try  {
			if (!elementos[2].equalsIgnoreCase("Admin"))
				return false;
		} catch(IndexOutOfBoundsException ex) {
			return false;
		}

		return true;
	}
	
	private String mapearAccion(String accion) {
		String resultado = "";
		switch (accion) {
		case "datos": 
		case "guardar":
			resultado = "Insertar";
			break;
		case "eliminar":
			resultado = "Borrar";
		default:
			resultado = "Leer";
		}
		
		return resultado;
	}
}
