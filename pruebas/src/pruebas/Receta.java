package pruebas;

import java.util.Date;

class Receta {
	private String id;
	private String medicamento;
	private String dosis;
	private String frecuencia;
	private Date fecha;
	private String pacienteId;
	private String medicoId;

	public Receta(String id, String medicamento, String dosis, String frecuencia, Date fecha, String pacienteId,
			String medicoId) {
		this.id = id;
		this.medicamento = medicamento;
		this.dosis = dosis;
		this.frecuencia = frecuencia;
		this.fecha = fecha;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
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
}
