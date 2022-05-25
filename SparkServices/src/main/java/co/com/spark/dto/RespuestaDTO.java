package co.com.spark.dto;

public class RespuestaDTO {

	private String nombre;
	private String descripcion;
	private String homepage;
	
	public RespuestaDTO(String nombre, String descripcion, String homepage) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.homepage = homepage;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
}
