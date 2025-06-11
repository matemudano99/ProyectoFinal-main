package pruebas;

//Clase Mantenimiento
public class Mantenimiento extends Personal {



	public Mantenimiento(String dni, String nombre, String email, String telefono,
			Turno turno) {
		super( dni, nombre, email, telefono, "Mantenimiento", turno);
		
	}

	public boolean programarDesinfeccion(Infraestructura infraestructura) {
		infraestructura.setDisponible(true);
		System.out.println("Desinfección programada para: " + infraestructura.getId());
		return true;
	}
}
