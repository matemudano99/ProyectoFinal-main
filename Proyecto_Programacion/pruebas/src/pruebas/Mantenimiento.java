package pruebas;

//Clase Mantenimiento
class Mantenimiento extends Personal {

	public Mantenimiento(String id, String nombre, String email, String telefono) {
		super(id, nombre, email, telefono, "Mantenimiento");
	}

	public boolean programarDesinfeccion(Infraestructura infraestructura) {
		infraestructura.setDesinfectado(true);
		System.out.println("Desinfección programada para: " + infraestructura.getId());
		return true;
	}
}
