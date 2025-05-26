package pruebas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Clase abstracta Administradores
abstract class Administradores {
	protected String id;
	protected String email;
	protected String contrasena;

	public Administradores(String id, String email, String contrasena) {
		this.id = id;
		this.email = email;
		this.contrasena = contrasena;
	}

	public void registrarAdministrador(Administradores admin) {
		System.out.println("Administrador registrado: " + admin.getId());
	}

	public List<Turnos> filtrarTurnos(Date fecha) {
		return new ArrayList<Turnos>();
	}

	public void notificarPersonal(String msg) {
		System.out.println("Notificación enviada: " + msg);
	}

	public Inventario consultarInventario() {
		return new Inventario();
	}

	public List<AtencionMedica> visualizarAtencionMedica(Medico medico) {
		return new ArrayList<AtencionMedica>();
	}

	public void eliminarPersonal(String id) {
		System.out.println("Personal eliminado: " + id);
	}

	public void modificarPersonal(String id) {
		System.out.println("Personal modificado: " + id);
	}

	public void registrarSala(TipoInfraestructura tipo) {
		System.out.println("Sala registrada: " + tipo);
	}

	public void eliminarSala(String id) {
		System.out.println("Sala eliminada: " + id);
	}

	public void modificarSala(String id) {
		System.out.println("Sala modificada: " + id);
	}

	public void consultarHistorial(String id) {
		System.out.println("Consultando historial: " + id);
	}

	public List<Consulta> filtrarConsultas(Medico medico) {
		return new ArrayList<Consulta>();
	}

	public void registrarTurno(int num, String dia, String hora) {
		System.out.println("Turno registrado: " + num + " - " + dia + " " + hora);
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
