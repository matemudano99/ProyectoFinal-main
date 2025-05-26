package proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Administrador extends Personal {
	private String rol;

	public Administrador(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala,
			String rol) {
		super(dni, nombre, apellidos, genero, turno, sala);
		this.rol = rol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Administrador [rol=" + rol + ", turno=" + turno + ", sala=" + sala + ", dni=" + dni + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", genero=" + genero + "]";
	}
	
	public boolean registrarPaciente(String dni, String nombre, String apellidos, Genero genero, int telefono, String email, String obraSocial, Infraestructura sala, boolean solicitudAtencionMedica, ArrayList<Paciente> pacientes) {
		boolean comprobar = false;

		Paciente p = new Paciente(dni, nombre, apellidos, genero, telefono, email, obraSocial, sala, solicitudAtencionMedica);
		if (pacientes.add(p)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean registrarAdministrador(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala, String rol, ArrayList<Personal> personal) {
		boolean comprobar = false;

		Administrador a = new Administrador(dni, nombre, apellidos, genero, turno, sala, rol);
		if (personal.add(a)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean registrarAdministrativo(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala, String rol, ArrayList<Personal> personal) {
		boolean comprobar = false;

		Administrativo a = new Administrativo(dni, nombre, apellidos, genero, turno, sala, rol);
		if (personal.add(a)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean registrarMantenimiento(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala, String rol, ArrayList<Personal> personal) {
		boolean comprobar = false;

		Mantenimiento m = new Mantenimiento(dni, nombre, apellidos, genero, turno, sala, rol);
		if (personal.add(m)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean registrarMedico(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala, String rol, ArrayList<Personal> personal) {
		boolean comprobar = false;

		Medico m = new Medico(dni, nombre, apellidos, genero, turno, sala, rol);
		if (personal.add(m)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean registrarEnfermero(String dni, String nombre, String apellidos, Genero genero, Turnos turno, Infraestructura sala, String rol, ArrayList<Personal> personal) {
		boolean comprobar = false;

		Enfermero e = new Enfermero(dni, nombre, apellidos, genero, turno, sala, rol);
		if (personal.add(e)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean eliminarPersona(String dni, ArrayList<Personal> personal, ArrayList<Paciente> pacientes) {
		boolean comprobar = false;
		
		for (Personal elemento : personal) {
			if (elemento.getDni() == dni) {
				personal.remove(elemento);
				comprobar = true;
			}
			
		}
		
		if (!comprobar) {
			for (Paciente elemento : pacientes) {
				if (elemento.getDni() == dni) {
					pacientes.remove(elemento);
					comprobar = true;
				}
				
			}
			
		}
		
		return comprobar;
	}
	
	public void modificarPersonal(ArrayList<Infraestructura> salas, Personal p) {
		BufferedReader snoopy = new BufferedReader(new InputStreamReader(System.in));
		
		String dni = "", nombre = "", apellidos = "", opcion = "", generoValor = "", turnoValor = "", tipoValor = "";
		int numero = 0;
		Genero genero;
		Turnos turno;
		TipoInfraestructura tipo;
		
		try {
			System.out.println("¿Desea cambiar el dni?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo dni:");
				dni = snoopy.readLine();
				p.setDni(dni);
			}
			
			System.out.println("¿Desea cambiar el nombre?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo nombre:");
				nombre = snoopy.readLine();
				p.setNombre(nombre);
			}
			
			System.out.println("¿Desea cambiar los apellidos?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca los nuevos apellidos:");
				apellidos = snoopy.readLine();
				p.setApellidos(apellidos);
			}
			
			System.out.println("¿Desea cambiar el genero?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo genero:");
				generoValor = snoopy.readLine();
				genero = Genero.valueOf(generoValor.toUpperCase());
				p.setGenero(genero);
			}
			
			System.out.println("¿Desea cambiar el turno?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo turno:");
				turnoValor = snoopy.readLine();
				turno = Turnos.valueOf(turnoValor.toUpperCase());
				p.setTurno(turno);
			}
			
			System.out.println("¿Desea cambiar la sala?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el tipo de sala:");
				tipoValor = snoopy.readLine();
				tipo = TipoInfraestructura.valueOf(tipoValor.toUpperCase());
				System.out.println("Introduzca el numero de sala:");
				numero = Integer.parseInt(snoopy.readLine());
				for (Infraestructura elemento : salas) {
					if (elemento.getTipoInfraestructura() == tipo && elemento.getNumero() == numero) {
						elemento.setTipoInfraestructura(tipo);
						elemento.setNumero(numero);	
					}
					
				}
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			snoopy.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarPaciente(ArrayList<Infraestructura> salas, Paciente p) {
		BufferedReader snoopy = new BufferedReader(new InputStreamReader(System.in));
		
		String dni = "", nombre = "", apellidos = "", opcion = "", generoValor = "", email = "", obraSocial = "", tipoValor = "";
		int telefono = 0, numero = 0;
		Genero genero;
		TipoInfraestructura tipo;
		
		try {
			System.out.println("¿Desea cambiar el dni?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo dni:");
				dni = snoopy.readLine();
				p.setDni(dni);
			}
			
			System.out.println("¿Desea cambiar el nombre?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo nombre:");
				nombre = snoopy.readLine();
				p.setNombre(nombre);
			}
			
			System.out.println("¿Desea cambiar los apellidos?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca los nuevos apellidos:");
				apellidos = snoopy.readLine();
				p.setApellidos(apellidos);
			}
			
			System.out.println("¿Desea cambiar el genero?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo genero:");
				generoValor = snoopy.readLine();
				genero = Genero.valueOf(generoValor.toUpperCase());
				p.setGenero(genero);
			}
			
			System.out.println("¿Desea cambiar el telefono?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo telefono:");
				telefono = Integer.parseInt(snoopy.readLine());
				p.setTelefono(telefono);
			}
			
			System.out.println("¿Desea cambiar el email?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el nuevo email:");
				email = snoopy.readLine();
				p.setEmail(email);
				
			}
			
			System.out.println("¿Desea cambiar la obra social?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca la nueva obra social:");
				obraSocial = snoopy.readLine();
				p.setEmail(obraSocial);
				
			}
			
			System.out.println("¿Desea cambiar la sala?: s/n");
			opcion = snoopy.readLine();
			if (opcion.equalsIgnoreCase("s")) {
				System.out.println("Introduzca el tipo de sala:");
				tipoValor = snoopy.readLine();
				tipo = TipoInfraestructura.valueOf(tipoValor.toUpperCase());
				System.out.println("Introduzca el numero de sala:");
				numero = Integer.parseInt(snoopy.readLine());
				for (Infraestructura elemento : salas) {
					if (elemento.getTipoInfraestructura() == tipo && elemento.getNumero() == numero) {
						elemento.setTipoInfraestructura(tipo);
						elemento.setNumero(numero);	
					}
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			snoopy.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean modificarPaciente() {
		boolean comprobar = false;
		
		
		
		return comprobar;
	}
	
	public boolean anadirSala(ArrayList<Infraestructura> salas, TipoInfraestructura tipo, int numero) {
		boolean comprobar = false;
		
		Infraestructura i = new Infraestructura(tipo, numero, false);
		if (salas.add(i)) {
			comprobar = true;
		}
		
		return comprobar;
	}
	
	public boolean eliminarSala(ArrayList<Infraestructura> salas, TipoInfraestructura tipo, int numero) {
		boolean comprobar = false;
		
		for (Infraestructura elemento : salas) {
			if (elemento.getTipoInfraestructura() == tipo && elemento.getNumero() == numero) {
				salas.remove(elemento);
				comprobar = true;
			}
			
		}
		
		return comprobar;
	}

}
