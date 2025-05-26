package proyecto;

import java.util.Date;

public class HistorialMedico {
	private TipoHistorial tipo;
	private Date fecha;
	private String diagnostico;
	private String tratamiento;

	public HistorialMedico(TipoHistorial tipo, Date fecha, String diagnostico, String tratamiento) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
	}

	public TipoHistorial getTipo() {
		return tipo;
	}

	public void setTipo(TipoHistorial tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	public void mostrarDetalles() {
		System.out.println("Tipo: " + tipo);
		System.out.println("Fecha: " + fecha);
		System.out.println("Diagnostico: " + diagnostico);
		System.out.println("Tratamiento: " + tratamiento);
	}
	
}
