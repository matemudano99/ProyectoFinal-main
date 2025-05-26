package proyecto;

import java.sql.Date;
import java.util.ArrayList;

public class Medico extends Personal {
	private String rol;
	private ArrayList<Paciente> pacientes;
	
	public Medico(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala, String rol) {
		super(dni, nombre, apellidos, genero, turno, sala);
		this.rol = rol;
		this.pacientes = new ArrayList<>();
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	@Override
	public String toString() {
		return "Medico [rol=" + rol + ", pacientes=" + pacientes + ", turno=" + turno + ", sala=" + sala + ", dni="
				+ dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero + "]";
	}
	
	public boolean crearDiagnostico(TipoHistorial tipo, Date fecha, String diagnostico, String tratamiento, Paciente p) {
		boolean comprobar = false;
		
		HistorialMedico hm = new HistorialMedico(tipo, fecha, diagnostico, tratamiento);
		if (p.getHistorialMedico().add(hm)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public void consultarHistorialMedico(Paciente p) {
		for (HistorialMedico elemento : p.getHistorialMedico()) {
			elemento.mostrarDetalles();
			
		}
	}
	
	public void consultarPacientes() {
		for (Paciente elemento : pacientes) {
			elemento.mostrarDetalles();
			
		}
	}

}
