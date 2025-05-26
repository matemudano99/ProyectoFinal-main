package pruebas;

import java.time.LocalTime;
import java.util.Date;

//Clase Cita
class Cita {
	private Date fecha;
	private Medico medico;
	private Paciente paciente;
	private LocalTime duracion;
	private String estado;

	public Cita(Date fecha, Medico medico, Paciente paciente, LocalTime duracion, String estado) {
		this.fecha = fecha;
		this.medico = medico;
		this.paciente = paciente;
		this.duracion = duracion;
		this.estado = estado;
	}

	// Getters y setters
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}