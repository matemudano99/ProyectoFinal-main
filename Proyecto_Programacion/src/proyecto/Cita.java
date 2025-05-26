package proyecto;

import java.util.Date;

public class Cita {
	private Date fecha;
	private Medico medico;
	private TipoHistorial tipo;
	private boolean estado;

	public Cita(Date fecha, Medico medico, TipoHistorial tipo) {
		this.fecha = fecha;
		this.medico = medico;
		this.tipo = tipo;
		this.estado = true;
	}

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

	public TipoHistorial getTipo() {
		return tipo;
	}

	public void setTipo(TipoHistorial tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cita [fecha=" + fecha + ", medico=" + medico + ", tipo=" + tipo + ", estado=" + estado + "]";
	}
	
}
