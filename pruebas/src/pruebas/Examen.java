package pruebas;

import java.util.Date;

class Examen {
	private String id;
	private String tipo;
	private Date fecha;
	private String resultado;
	private String pacienteId;

	public Examen(String id, String tipo, Date fecha, String resultado, String pacienteId) {
		this.id = id;
		this.tipo = tipo;
		this.fecha = fecha;
		this.resultado = resultado;
		this.pacienteId = pacienteId;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(String pacienteId) {
		this.pacienteId = pacienteId;
	}
}
