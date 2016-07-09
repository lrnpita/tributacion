package ec.eac.sitac.esigef;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para mapear los objetos que vienen en formato XML desde el ESIGEF
 *
 * @author Luis Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class Impuestos {
	/**
	 * Lista de impuestos
	 *
	 * @since 1.0
	 */
	List<Impuesto> listaImpuestos = new ArrayList<Impuesto>();
	
	public List<Impuesto> getListaImpuestos() {
		return listaImpuestos;
	}

	public void setListaImpuestos(List<Impuesto> listaImpuestos) {
		this.listaImpuestos = listaImpuestos;
	}
	
    @Override
    public String toString() {
        return "Impuestos [impuestos=" + listaImpuestos + "]";
    }
}
