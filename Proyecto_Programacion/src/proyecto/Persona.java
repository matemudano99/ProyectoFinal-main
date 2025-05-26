package proyecto;

public abstract class Persona {
	protected String dni;
	protected String nombre;
	protected String apellidos;
	protected Genero genero;

	public Persona(String dni, String nombre, String apellidos, Genero genero) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
