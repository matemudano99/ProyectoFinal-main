package pruebas;

//Estructura para Turnos
class Turnos {
	private String dia;
	private String hora;
	private String modalidad;
	private int años;
	private int meses;

	public Turnos(String dia, String hora, String modalidad, int años, int meses) {
		this.dia = dia;
		this.hora = hora;
		this.modalidad = modalidad;
		this.años = años;
		this.meses = meses;
	}

	// Getters y setters
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public int getAños() {
		return años;
	}

	public void setAños(int años) {
		this.años = años;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}
}