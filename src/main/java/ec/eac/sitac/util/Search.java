package ec.eac.sitac.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
	@JsonProperty("fecha")
    private String fecha;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
