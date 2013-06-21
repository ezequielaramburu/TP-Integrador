package T4P;

public class Usuario {
	
	private String IdUsuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	
	public String getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
