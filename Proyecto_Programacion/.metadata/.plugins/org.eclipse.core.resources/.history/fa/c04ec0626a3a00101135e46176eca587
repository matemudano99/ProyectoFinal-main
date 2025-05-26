package pruebas;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Clase Paciente
class Paciente {
	private String id;
	private String dni;
	private String nombre;
	private String apellidos;
	private Genero genero;
	private String email;
	private int telefono;
	private String direccion;
	private String obraSocial;
	private HistorialMedico historialMedico;
	private boolean hospitalizado;

	public Paciente(String dni, String nombre, String apellidos, Genero genero, int telefono, String email,
			String obraSocial, HistorialMedico historialMedico, boolean hospitalizado) {
		this.id = "PAC_" + System.currentTimeMillis(); // ID único
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.telefono = telefono;
		this.email = email;
		this.obraSocial = obraSocial;
		this.historialMedico = historialMedico;
		this.hospitalizado = hospitalizado;
	}

	// Métodos específicos del paciente
	public HistorialMedico consultarHistorial(String id) {
		// Buscar historial por ID
		return this.historialMedico;
	}

	public boolean verEstadoCita(Cita c) {
		return c.getEstado().equals("Confirmada");
	}

	public boolean cancelarCita(Cita c) {
		c.setEstado("Cancelada");
		return true;
	}

	public Cita solicitarCita() {
		// Lógica para solicitar cita - retorna nueva cita
		return new Cita(new Date(), null, this, LocalTime.now(), "Pendiente");
	}

	public List<Examen> consultarExamenes() {
		// Retorna lista de exámenes del paciente
		return new ArrayList<Examen>();
	}

	public Receta verReceta(String id) {
		// Buscar receta por ID
		return null;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public HistorialMedico getHistorialMedico() {
		return historialMedico;
	}

	public void setHistorialMedico(HistorialMedico historialMedico) {
		this.historialMedico = historialMedico;
	}

	public boolean isHospitalizado() {
		return hospitalizado;
	}

	public void setHospitalizado(boolean hospitalizado) {
		this.hospitalizado = hospitalizado;
	}
}