package proyecto;

public class Enfermero extends Personal {
	private String rol;

	public Enfermero(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala,
			String rol) {
		super(dni, nombre, apellidos, genero, turno, sala);
		this.rol = rol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Enfermero [rol=" + rol + ", turno=" + turno + ", sala=" + sala + ", dni=" + dni + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", genero=" + genero + "]";
	}
	
	public boolean asignarHabitacion(Infraestructura i, Paciente p) {
		boolean comprobar = false;
		
		if (i.isDisponible()) {
			p.setHabitacion(i);
			i.setDisponible(false);
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean darAlta(Paciente p) {
		boolean comprobar = false;
		
		if (!p.isAlta()) {
			p.setAlta(true);
			comprobar = true;
		}
		
		return comprobar;
	}

}
