package pruebas;


//Clase Enfermero
class Enfermero extends Personal {

	
	  public Enfermero(String dni, String nombre, String email, String telefono, Turno turno) {
	        super( dni, nombre, email, telefono, "Enfermero", turno);
	    }

	public String consultarDiagnostico(String id) {
		// Buscar diagnóstico por ID del paciente
		return "Diagnóstico encontrado para paciente: " + id;
	}

	public boolean asignarMedicacion(String id) {
		// Asignar medicación al paciente
		System.out.println("Medicación asignada al paciente: " + id);
		return true;
	}

	public boolean registrarPaciente(Paciente p) {
		// Registrar paciente en el sistema
		MainUI.listaPacientes.add(p);
		return true;
	}

	public HistorialMedico consultarHistorialMedico(String id) {
		// Buscar historial médico por ID
		for (Paciente p : MainUI.listaPacientes) {
			if (p.getId().equals(id)) {
				return p.getHistorialMedico();
			}
		}
		return null;
	}

	public void recibirPaciente(Paciente p) {
		System.out.println("Paciente recibido: " + p.getNombre() + " " + p.getApellidos());
	}
}