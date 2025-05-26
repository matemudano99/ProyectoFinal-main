package pruebas;

import java.util.Date;

class Consulta {
	private String id;
	private Date fecha;
	private String pacienteId;
	private String medicoId;
	private String motivo;
	private String diagnostico;

	public Consulta(String id, Date fecha, String pacienteId, String medicoId, String motivo) {
		this.id = id;
		this.fecha = fecha;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.motivo = motivo;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
}
