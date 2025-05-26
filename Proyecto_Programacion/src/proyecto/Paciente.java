package proyecto;

import java.util.ArrayList;

public class Paciente extends Persona {
	private int telefono;
	private String email;
	private String obraSocial;
	private ArrayList<HistorialMedico> historialMedico;
	private Infraestructura habitacion;
	private boolean solicitudAtencionMedica;
	private ArrayList<Cita> citas;
	private boolean alta;

	public Paciente(String dni, String nombre, String apellidos, Genero genero, int telefono, String email,
			String obraSocial, Infraestructura habitacion,
			boolean solicitudAtencionMedica) {
		super(dni, nombre, apellidos, genero);
		this.telefono = telefono;
		this.email = email;
		this.obraSocial = obraSocial;
		this.historialMedico = new ArrayList<>();
		this.habitacion = habitacion;
		this.solicitudAtencionMedica = solicitudAtencionMedica;
		this.citas = new ArrayList<>();
		this.alta = false;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public ArrayList<HistorialMedico> getHistorialMedico() {
		return historialMedico;
	}

	public void setHistorialMedico(ArrayList<HistorialMedico> historialMedico) {
		this.historialMedico = historialMedico;
	}

	public Infraestructura getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Infraestructura habitacion) {
		this.habitacion = habitacion;
	}

	public boolean isSolicitudAtencionMedica() {
		return solicitudAtencionMedica;
	}

	public void setSolicitudAtencionMedica(boolean solicitudAtencionMedica) {
		this.solicitudAtencionMedica = solicitudAtencionMedica;
	}

	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	public void mostrarDetalles() {
		System.out.println("DNI: " + dni);
		System.out.println("Nombre: + nombre");
		System.out.println("Apellidos: + apellidos");
		System.out.println("Genero" + genero);
		System.out.println("Telefono: " + telefono);
		System.out.println("Email: " + email);
		System.out.println("Obra Social: " + obraSocial);
		System.out.println("Habitacion: " + habitacion);
		System.out.println("Solicitud de Atencion Medica: " + solicitudAtencionMedica);
		System.out.println("Citas: " );
		for(Cita elemento : citas) {
			System.out.println(elemento.toString());
		}
		System.out.println("Alta: " + alta);
	}

	public void consultarHistorial() {
		System.out.println("Historial Medico de " + nombre);
		for(HistorialMedico historial : historialMedico) {
			historial.mostrarDetalles();
		}
	}

	public boolean solicitudAtencionMedica() {
		if(solicitudAtencionMedica) {
			solicitudAtencionMedica=false;
		}else {
			solicitudAtencionMedica=true; 
		}
		return solicitudAtencionMedica;
	}

}
