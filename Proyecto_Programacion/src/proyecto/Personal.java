package proyecto;

public class Personal extends Persona {
	protected Turnos turno;
	protected Infraestructura sala;

	public Personal(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala) {
		super(dni, nombre, apellidos, genero);
		this.turno = turno;
		this.sala = sala;
	}

	public Turnos getTurno() {
		return turno;
	}

	public void setTurno(Turnos turno) {
		this.turno = turno;
	}

	public Infraestructura getSala() {
		return sala;
	}

	public void setSala(Infraestructura sala) {
		this.sala = sala;
	}

	public void mostrarDetalles() {
		System.out.println("DNI: " + dni);
		System.out.println("Nombre: " + nombre);
		System.out.println("Apellidos: " + apellidos);
		System.out.println("Genero: " + genero);
		System.out.println("Turno: " + turno);
		System.out.println("Sala Asignada: " + sala.getTipoInfraestructura());
		System.out.println("Numero de Asignada: " + sala.getNumero());
	}

}
