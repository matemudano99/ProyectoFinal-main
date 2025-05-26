package proyecto;

public class Mantenimiento extends Personal {
	private String rol;

	public Mantenimiento(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala,
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
		return "Mantenimiento [rol=" + rol + ", turno=" + turno + ", sala=" + sala + ", dni=" + dni + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", genero=" + genero + "]";
	}
	
	public boolean cambioDisponibilidadHabitacion(Infraestructura i) {
		boolean comprobar = false;
		
		if (i.isDisponible()) {
			i.setDisponible(false);
			comprobar = true;
		} else {
			i.setDisponible(true);
			comprobar = true;
		}
		
		return comprobar;
	}

}
