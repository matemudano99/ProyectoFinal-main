package proyecto;

import java.sql.Date;
import java.util.ArrayList;

public class Administrativo extends Personal {
	private String rol;

	public Administrativo(String dni, String nombre, String apellidos, Genero genero, Turnos turno,
			Infraestructura sala, String rol) {
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
		return "Administrativo [rol=" + rol + ", turno=" + turno + ", sala=" + sala + ", dni=" + dni + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", genero=" + genero + "]";
	}
	
	public void consultarSalas(ArrayList<Infraestructura> salas) {
		for (Infraestructura elemento : salas) {
			elemento.mostrarDetalles();
			
		}
	}
	
	public void consultarPersonal(ArrayList<Personal> personal) {
		for (Personal elemento : personal) {
			elemento.mostrarDetalles();
			
		}
	}
	
	public void consultarPacientes(ArrayList<Paciente> pacientes) {
		for (Paciente elemento : pacientes) {
			elemento.mostrarDetalles();
			
		}
	}
	
	public boolean asignarPaciente(Paciente p, Medico m) {
		boolean comprobar = false;
		
		if (m.getPacientes().add(p)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean crearCita(Paciente p, Date fecha, Medico m, TipoHistorial tipo) {
		boolean comprobar = false;
		
		Cita c = new Cita(fecha, m, tipo);
		if (p.getCitas().add(c)) {
			comprobar = true;
		}
		
		return comprobar;
	}

}
