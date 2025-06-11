package pruebas;

import java.time.LocalTime;
import java.util.Date;

//Clase Administrativos
public class Administrativos extends Personal {

	

	 public Administrativos( String dni, String nombre, String email, String telefono, Turno turno) {
	        super(dni, nombre, email, telefono, "Administrativo", turno);
	    }

	public void consultarAgenda(Infraestructura infraestructura) {
		System.out.println("Consultando agenda para: " + infraestructura.getId());
	}

	public void consultarPersonal(Personal personal) {
		System.out.println("Consultando información de: " + personal.getNombre());
	}

	public void consultarRecetas(Receta receta) {
		System.out.println("Consultando receta: " + receta.getId());
	}

	public void registrarPaciente(Paciente paciente) {
		MainUI.listaPacientes.add(paciente);
		System.out.println("Paciente registrado: " + paciente.getNombre());
	}

	public boolean crearCitas(Paciente paciente, Medico medico, TipoInfraestructura tipo) {
		Cita nuevaCita = new Cita(new Date(), medico, paciente, LocalTime.of(1, 0), "Programada");
		System.out.println("Cita creada para " + paciente.getNombre() + " con " + medico.getNombre());
		return true;
	}
}