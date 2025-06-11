package pruebas;

import java.util.Date;

class AtencionMedica {
	private String id;
	private Date fecha;
	private String pacienteId;
	private String medicoId;
	private String descripcion;

	public AtencionMedica(String id, Date fecha, String pacienteId, String medicoId, String descripcion) {
		this.id = id;
		this.fecha = fecha;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.descripcion = descripcion;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(String pacienteId) {
		this.pacienteId = pacienteId;
	}

	public String getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(String medicoId) {
		this.medicoId = medicoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
