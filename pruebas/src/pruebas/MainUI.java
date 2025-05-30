package pruebas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;

//============ CLASE PRINCIPAL UI (MEJORADA CON ESTILOS DE BOTONES) ============

public class MainUI {
	static List<Paciente> listaPacientes = new ArrayList<>();
	static List<Personal> listaPersonal = new ArrayList<>();
	static List<Infraestructura> listaSalas = new ArrayList<>();
	static List<Cita> listaCitas = new ArrayList<>();
	static List<Receta> listaRecetas = new ArrayList<>();
	static List<Examen> listaExamenes = new ArrayList<>();

	// Colores simples
	private static final Color COLOR_AZUL = new Color(70, 130, 180);
	private static final Color COLOR_AZUL_HOVER = new Color(100, 149, 237);
	private static final Color COLOR_GRIS = new Color(108, 117, 125);
	private static final Color COLOR_GRIS_HOVER = new Color(134, 142, 150);
	private static final Color COLOR_GRIS_OSCURO = new Color(73, 80, 87);
	private static final Color COLOR_FONDO = new Color(248, 249, 250);
	private static final Font FUENTE_BOTON = new Font("Arial", Font.BOLD, 14);

	public static void main(String[] args) {
		inicializarDatos();
		mostrarLogin();
	}

	private static void inicializarDatos() {
		// Crear algunos empleados de ejemplo
		listaPersonal.add(new Medico("MED001", "Dr. García", "garcia@hospital.com", "123456789", "Cardiología"));
		listaPersonal.add(new Enfermero("ENF001", "Ana López", "ana@hospital.com", "987654321"));
		listaPersonal.add(new Administrativos("ADM001", "Carlos Ruiz", "carlos@hospital.com", "555666777"));
		listaPersonal.add(new Mantenimiento("MAN001", "José Pérez", "jose@hospital.com", "111222333"));

		// Crear algunas salas de ejemplo
		listaSalas.add(new Infraestructura("SALA001", TipoInfraestructura.CONSULTORIO, 101, true));
		listaSalas.add(new Infraestructura("SALA002", TipoInfraestructura.HABITACION, 201, false));
		listaSalas.add(new Infraestructura("SALA003", TipoInfraestructura.QUIROFANO, 301, true));
	}

	/**
	 * Método para aplicar estilo azul a los botones principales
	 */
	private static void aplicarEstiloBotonAzul(JButton boton) {
		boton.setFont(FUENTE_BOTON);
		boton.setForeground(Color.WHITE);
		boton.setBackground(COLOR_AZUL);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	/**
	 * Método para aplicar estilo gris a los botones secundarios
	 */
	private static void aplicarEstiloBotonGris(JButton boton) {
		boton.setFont(FUENTE_BOTON);
		boton.setForeground(Color.WHITE);
		boton.setBackground(COLOR_GRIS);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	/**
	 * Método para aplicar estilo gris oscuro a botones como "Cerrar Sesión"
	 */
	private static void aplicarEstiloBotonGrisOscuro(JButton boton) {
		boton.setFont(FUENTE_BOTON);
		boton.setForeground(Color.WHITE);
		boton.setBackground(COLOR_GRIS_OSCURO);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	public static void mostrarLogin() {
		JFrame frame = new JFrame("Login Hospital");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 350);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 10, 40));
		panel.setBackground(COLOR_FONDO);

		JLabel usuarioLabel = new JLabel("Usuario:");
		usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		JTextField usuarioField = new JTextField();
		usuarioField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));

		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));

		JLabel rolLabel = new JLabel("Rol:");
		rolLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		String[] roles = { "Administrador", "Doctor", "Administrativo", "Mantenimiento" };
		JComboBox<String> rolBox = new JComboBox<>(roles);
		rolBox.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

		panel.add(usuarioLabel);
		panel.add(usuarioField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(rolLabel);
		panel.add(rolBox);
		panel.add(new JLabel());
		panel.add(new JLabel());

		frame.add(panel, BorderLayout.CENTER);

		JButton loginBtn = new JButton("Entrar");
		aplicarEstiloBotonAzul(loginBtn);

		JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		botonPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));
		botonPanel.setBackground(COLOR_FONDO);
		botonPanel.add(loginBtn);
		frame.add(botonPanel, BorderLayout.SOUTH);

		loginBtn.addActionListener(e -> {
			String usuario = usuarioField.getText();
			String password = new String(passwordField.getPassword());
			String rolSeleccionado = (String) rolBox.getSelectedItem();

			if (usuario.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Debe ingresar usuario y contraseña.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			frame.dispose();
			menuPrincipal(rolSeleccionado);
			JOptionPane.showMessageDialog(null, "Acceso realizado con éxito.\nUsuario: " + usuario);
		});

		frame.getContentPane().setBackground(COLOR_FONDO);
		frame.setVisible(true);
	}

	public static void menuPrincipal(String rol) {
		JFrame frame = new JFrame("Menú Principal - Rol: " + rol);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(0, 1, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
		panel.setBackground(COLOR_FONDO);

		// Título
		JLabel titulo = new JLabel("Sistema de Gestión Hospitalaria", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(COLOR_GRIS_OSCURO);
		titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		panel.add(titulo);

		if (rol.equals("Administrador") || rol.equals("Doctor")) {
			JButton btnPacientes = new JButton("Gestión de Pacientes");
			aplicarEstiloBotonAzul(btnPacientes);
			btnPacientes.addActionListener(e -> gestionPacientes(rol));
			panel.add(btnPacientes);
		}

		if (rol.equals("Administrador") || rol.equals("Recepcionista")) {
			JButton btnEmpleados = new JButton("Gestión de Empleados");
			aplicarEstiloBotonAzul(btnEmpleados);
			btnEmpleados.addActionListener(e -> gestionEmpleados(rol));
			panel.add(btnEmpleados);
		}

		if (rol.equals("Administrador")) {
			JButton btnSalas = new JButton("Gestión de Salas");
			aplicarEstiloBotonAzul(btnSalas);
			btnSalas.addActionListener(e -> gestionSalas(rol));
			panel.add(btnSalas);

			JButton btnCitas = new JButton("Gestión de Citas");
			aplicarEstiloBotonAzul(btnCitas);
			btnCitas.addActionListener(e -> gestionCitas(rol));
			panel.add(btnCitas);

			JButton btnInventario = new JButton("Consultar Inventario");
			aplicarEstiloBotonAzul(btnInventario);
			btnInventario.addActionListener(e -> consultarInventario());
			panel.add(btnInventario);
		}

		if (rol.equals("Doctor")) {
			JButton btnHistoriales = new JButton("Historiales Médicos");
			aplicarEstiloBotonAzul(btnHistoriales);
			btnHistoriales.addActionListener(e -> gestionHistoriales());
			panel.add(btnHistoriales);

			JButton btnRecetas = new JButton("Gestión de Recetas");
			aplicarEstiloBotonAzul(btnRecetas);
			btnRecetas.addActionListener(e -> gestionRecetas());
			panel.add(btnRecetas);
		}

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		aplicarEstiloBotonGrisOscuro(btnCerrarSesion);
		btnCerrarSesion.addActionListener(e -> {
			frame.dispose();
			mostrarLogin();
		});
		panel.add(btnCerrarSesion);

		frame.add(panel);
		frame.setVisible(true);
	}

	public static void gestionPacientes(String rol) {
		JFrame frame = new JFrame("Gestión de Pacientes - " + rol);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(6, 1, 10, 10));

		JButton btnRegistrar = new JButton("Registrar Paciente");
		btnRegistrar.addActionListener(e -> registrarPaciente());

		JButton btnListar = new JButton("Listar Pacientes");
		btnListar.addActionListener(e -> listarPacientes());

		JButton btnHistorial = new JButton("Consultar Historial Médico");
		btnHistorial.addActionListener(e -> consultarHistorialPaciente());

		JButton btnHabitaciones = new JButton("Asignación de Habitaciones");
		btnHabitaciones.addActionListener(e -> asignarHabitacion());

		JButton btnSolicitudes = new JButton("Solicitudes de Atención Médica");
		btnSolicitudes.addActionListener(e -> solicitudesAtencion());

		JButton btnVolver = new JButton("Volver al Menú Principal");
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});

		frame.add(btnRegistrar);
		frame.add(btnListar);
		frame.add(btnHistorial);
		frame.add(btnHabitaciones);
		frame.add(btnSolicitudes);
		frame.add(btnVolver);

		frame.setVisible(true);
	}

	public static void registrarPaciente() {
		JFrame frame = new JFrame("Registro de Paciente");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "DNI", "Nombre", "Apellidos", "Género (M/F/O)", "Teléfono", "Email", "Dirección",
				"Obra Social" };
		JTextField[] fields = new JTextField[labels.length];

		JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < labels.length; i++) {
			form.add(new JLabel(labels[i] + ":"));
			fields[i] = new JTextField();
			form.add(fields[i]);
		}

		JButton btnGuardar = new JButton("Registrar Paciente");
		btnGuardar.addActionListener(e -> {
			try {
				String dni = fields[0].getText();
				String nombre = fields[1].getText();
				String apellidos = fields[2].getText();
				String generoStr = fields[3].getText().toUpperCase();
				Genero genero;
				switch (generoStr) {
				case "M":
					genero = Genero.MASCULINO;
					break;
				case "F":
					genero = Genero.FEMENINO;
					break;
				default:
					genero = Genero.OTRO;
					break;
				}
				int telefono = Integer.parseInt(fields[4].getText());
				String email = fields[5].getText();
				String direccion = fields[6].getText();
				String obraSocial = fields[7].getText();

				// Crear historial médico inicial
				HistorialMedico historial = new HistorialMedico("Registro inicial", TipoHistorial.CONSULTA, new Date(),
						"", "");

				Paciente nuevo = new Paciente(dni, nombre, apellidos, genero, telefono, email, obraSocial, historial,
						false);
				nuevo.setDireccion(direccion);
				listaPacientes.add(nuevo);

				JOptionPane.showMessageDialog(frame, "Paciente registrado correctamente.\nID: " + nuevo.getId());
				for (JTextField field : fields)
					field.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error al registrar paciente: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		frame.add(form, BorderLayout.CENTER);
		frame.add(btnGuardar, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void listarPacientes() {
		JFrame frame = new JFrame("Lista de Pacientes");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);

		String[] columnNames = { "ID", "DNI", "Nombre", "Apellidos", "Género", "Teléfono", "Email", "Obra Social" };
		Object[][] data = new Object[listaPacientes.size()][columnNames.length];

		for (int i = 0; i < listaPacientes.size(); i++) {
			Paciente p = listaPacientes.get(i);
			data[i][0] = p.getId();
			data[i][1] = p.getDni();
			data[i][2] = p.getNombre();
			data[i][3] = p.getApellidos();
			data[i][4] = p.getGenero();
			data[i][5] = p.getTelefono();
			data[i][6] = p.getEmail();
			data[i][7] = p.getObraSocial();
		}

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void consultarHistorialPaciente() {
		String id = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
		if (id != null && !id.isEmpty()) {
			for (Paciente p : listaPacientes) {
				if (p.getId().equals(id)) {
					HistorialMedico historial = p.getHistorialMedico();
					if (historial != null) {
						String info = "Paciente: " + p.getNombre() + " " + p.getApellidos() + "\n" + "Consulta: "
								+ historial.getConsulta() + "\n" + "Tipo: " + historial.getTipo() + "\n" + "Fecha: "
								+ historial.getFecha() + "\n" + "Diagnóstico: " + historial.getDiagnostico() + "\n"
								+ "Tratamiento: " + historial.getTratamiento();
						JOptionPane.showMessageDialog(null, info, "Historial Médico", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No hay historial médico disponible para este paciente.");
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
		}
	}

	public static void asignarHabitacion() {
		String pacienteId = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
		if (pacienteId != null && !pacienteId.isEmpty()) {
			// Mostrar habitaciones disponibles
			StringBuilder habitaciones = new StringBuilder("Habitaciones disponibles:\n");
			for (Infraestructura sala : listaSalas) {
				if (sala.getTipo() == TipoInfraestructura.HABITACION) {
					habitaciones.append("ID: ").append(sala.getId()).append(" - Número: ").append(sala.getNumero())
							.append(" - Desinfectada: ").append(sala.isDesinfectado() ? "Sí" : "No").append("\n");
				}
			}

			String salaId = JOptionPane.showInputDialog(habitaciones.toString() + "\nIngrese el ID de la habitación:");
			if (salaId != null) {
				JOptionPane.showMessageDialog(null, "Habitación " + salaId + " asignada al paciente " + pacienteId);
			}
		}
	}

	public static void solicitudesAtencion() {
		JFrame frame = new JFrame("Solicitudes de Atención Médica");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "ID Paciente", "Tipo de Atención", "Prioridad (Alta/Media/Baja)", "Observaciones" };
		JTextField[] fields = new JTextField[labels.length];

		JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < labels.length; i++) {
			form.add(new JLabel(labels[i] + ":"));
			fields[i] = new JTextField();
			form.add(fields[i]);
		}

		JButton btnCrear = new JButton("Crear Solicitud");
		btnCrear.addActionListener(e -> {
			String pacienteId = fields[0].getText();
			String tipoAtencion = fields[1].getText();
			String prioridad = fields[2].getText();
			String observaciones = fields[3].getText();

			if (!pacienteId.isEmpty() && !tipoAtencion.isEmpty()) {
				JOptionPane.showMessageDialog(frame,
						"Solicitud de atención creada correctamente para el paciente: " + pacienteId);
				for (JTextField field : fields)
					field.setText("");
			} else {
				JOptionPane.showMessageDialog(frame,
						"Debe completar al menos el ID del paciente y el tipo de atención.");
			}
		});

		frame.add(form, BorderLayout.CENTER);
		frame.add(btnCrear, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void gestionEmpleados(String rol) {
		JFrame frame = new JFrame("Gestión de Empleados - " + rol);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(5, 1, 10, 10));

		JButton btnRegistrar = new JButton("Registrar Empleado");
		btnRegistrar.addActionListener(e -> registrarEmpleado());

		JButton btnListar = new JButton("Listar Empleados");
		btnListar.addActionListener(e -> listarEmpleados());

		JButton btnModificar = new JButton("Modificar Empleado");
		btnModificar.addActionListener(e -> modificarEmpleado());

		JButton btnEliminar = new JButton("Eliminar Empleado");
		btnEliminar.addActionListener(e -> eliminarEmpleado());

		JButton btnVolver = new JButton("Volver al Menú Principal");
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});

		frame.add(btnRegistrar);
		frame.add(btnListar);
		frame.add(btnModificar);
		frame.add(btnEliminar);
		frame.add(btnVolver);

		frame.setVisible(true);
	}

	public static void registrarEmpleado() {
		JFrame frame = new JFrame("Registro de Empleado");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "ID Empleado", "Nombre", "Email", "Teléfono", "Cargo", "Especialidad (solo para médicos)" };
		JTextField[] fields = new JTextField[labels.length];
		JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < labels.length; i++) {
			form.add(new JLabel(labels[i] + ":"));
			fields[i] = new JTextField();
			form.add(fields[i]);
		}

		JButton btnGuardar = new JButton("Registrar Empleado");
		btnGuardar.addActionListener(e -> {
			String id = fields[0].getText();
			String nombre = fields[1].getText();
			String email = fields[2].getText();
			String telefono = fields[3].getText();
			String cargo = fields[4].getText();
			String especialidad = fields[5].getText();

			if (id.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || cargo.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Todos los campos obligatorios deben ser completados.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Personal nuevoEmpleado;
			switch (cargo.toLowerCase()) {
			case "medico":
			case "médico":
			case "doctor":
				nuevoEmpleado = new Medico(id, nombre, email, telefono,
						especialidad.isEmpty() ? "General" : especialidad);
				break;
			case "enfermero":
			case "enfermera":
				nuevoEmpleado = new Enfermero(id, nombre, email, telefono);
				break;
			case "administrativo":
			case "administrativa":
				nuevoEmpleado = new Administrativos(id, nombre, email, telefono);
				break;
			case "mantenimiento":
				nuevoEmpleado = new Mantenimiento(id, nombre, email, telefono);
				break;
			default:
				nuevoEmpleado = new Administrativos(id, nombre, email, telefono); // Por defecto
				break;
			}

			listaPersonal.add(nuevoEmpleado);
			JOptionPane.showMessageDialog(frame, "Empleado registrado correctamente.");
			for (JTextField field : fields)
				field.setText("");
		});

		frame.add(form, BorderLayout.CENTER);
		frame.add(btnGuardar, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void listarEmpleados() {
		JFrame frame = new JFrame("Lista de Empleados");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);

		String[] columnNames = { "ID", "Nombre", "Email", "Teléfono", "Cargo", "Especialidad" };
		Object[][] data = new Object[listaPersonal.size()][columnNames.length];

		for (int i = 0; i < listaPersonal.size(); i++) {
			Personal p = listaPersonal.get(i);
			data[i][0] = p.getId();
			data[i][1] = p.getNombre();
			data[i][2] = p.getEmail();
			data[i][3] = p.getTelefono();
			data[i][4] = p.getCargo();
			data[i][5] = (p instanceof Medico) ? ((Medico) p).getEspecialidad() : "N/A";
		}

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void modificarEmpleado() {
		String id = JOptionPane.showInputDialog("Ingrese el ID del empleado a modificar:");
		if (id != null && !id.isEmpty()) {
			for (Personal p : listaPersonal) {
				if (p.getId().equals(id)) {
					String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", p.getNombre());
					String nuevoEmail = JOptionPane.showInputDialog("Nuevo email:", p.getEmail());
					String nuevoTelefono = JOptionPane.showInputDialog("Nuevo teléfono:", p.getTelefono());

					if (nuevoNombre != null)
						p.setNombre(nuevoNombre);
					if (nuevoEmail != null)
						p.setEmail(nuevoEmail);
					if (nuevoTelefono != null)
						p.setTelefono(nuevoTelefono);

					JOptionPane.showMessageDialog(null, "Empleado modificado correctamente.");
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
		}
	}

	public static void eliminarEmpleado() {
		String id = JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar:");
		if (id != null && !id.isEmpty()) {
			for (int i = 0; i < listaPersonal.size(); i++) {
				if (listaPersonal.get(i).getId().equals(id)) {
					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de eliminar el empleado " + listaPersonal.get(i).getNombre() + "?",
							"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

					if (confirmacion == JOptionPane.YES_OPTION) {
						listaPersonal.remove(i);
						JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
		}
	}

	public static void gestionSalas(String rol) {
		JFrame frame = new JFrame("Gestión de Salas - " + rol);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(5, 1, 10, 10));

		JButton btnRegistrar = new JButton("Registrar Sala");
		btnRegistrar.addActionListener(e -> registrarSala());

		JButton btnListar = new JButton("Listar Salas");
		btnListar.addActionListener(e -> listarSalas());

		JButton btnModificar = new JButton("Modificar Sala");
		btnModificar.addActionListener(e -> modificarSala());

		JButton btnDesinfectar = new JButton("Programar Desinfección");
		btnDesinfectar.addActionListener(e -> programarDesinfeccion());

		JButton btnVolver = new JButton("Volver al Menú Principal");
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});

		frame.add(btnRegistrar);
		frame.add(btnListar);
		frame.add(btnModificar);
		frame.add(btnDesinfectar);
		frame.add(btnVolver);

		frame.setVisible(true);
	}

	public static void registrarSala() {
		JFrame frame = new JFrame("Registro de Sala");
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "ID Sala", "Tipo (HABITACION/QUIROFANO/CONSULTORIO)", "Número" };
		JTextField[] fields = new JTextField[labels.length];
		JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < labels.length; i++) {
			form.add(new JLabel(labels[i] + ":"));
			fields[i] = new JTextField();
			form.add(fields[i]);
		}

		JButton btnGuardar = new JButton("Registrar Sala");
		btnGuardar.addActionListener(e -> {
			String id = fields[0].getText();
			String tipoStr = fields[1].getText().toUpperCase();
			String numeroStr = fields[2].getText();

			if (id.isEmpty() || tipoStr.isEmpty() || numeroStr.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				TipoInfraestructura tipo = TipoInfraestructura.valueOf(tipoStr);
				int numero = Integer.parseInt(numeroStr);

				Infraestructura nuevaSala = new Infraestructura(id, tipo, numero, false);
				listaSalas.add(nuevaSala);

				JOptionPane.showMessageDialog(frame, "Sala registrada correctamente.");
				for (JTextField field : fields)
					field.setText("");
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(frame, "Tipo de sala inválido. Use: HABITACION, QUIROFANO, o CONSULTORIO",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		frame.add(form, BorderLayout.CENTER);
		frame.add(btnGuardar, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void listarSalas() {
		JFrame frame = new JFrame("Lista de Salas");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);

		String[] columnNames = { "ID", "Tipo", "Número", "Desinfectada" };
		Object[][] data = new Object[listaSalas.size()][columnNames.length];

		for (int i = 0; i < listaSalas.size(); i++) {
			Infraestructura s = listaSalas.get(i);
			data[i][0] = s.getId();
			data[i][1] = s.getTipo();
			data[i][2] = s.getNumero();
			data[i][3] = s.isDesinfectado() ? "Sí" : "No";
		}

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void modificarSala() {
		String id = JOptionPane.showInputDialog("Ingrese el ID de la sala a modificar:");
		if (id != null && !id.isEmpty()) {
			for (Infraestructura s : listaSalas) {
				if (s.getId().equals(id)) {
					String nuevoNumero = JOptionPane.showInputDialog("Nuevo número de sala:", s.getNumero());

					if (nuevoNumero != null && !nuevoNumero.isEmpty()) {
						try {
							s.setNumero(Integer.parseInt(nuevoNumero));
							JOptionPane.showMessageDialog(null, "Sala modificada correctamente.");
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "El número debe ser un valor numérico.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Sala no encontrada.");
		}
	}

	public static void programarDesinfeccion() {
		String id = JOptionPane.showInputDialog("Ingrese el ID de la sala para programar desinfección:");
		if (id != null && !id.isEmpty()) {
			for (Infraestructura s : listaSalas) {
				if (s.getId().equals(id)) {
					// Simular personal de mantenimiento
					Mantenimiento personal = new Mantenimiento("MAN001", "José Pérez", "jose@hospital.com",
							"111222333");
					personal.programarDesinfeccion(s);
					JOptionPane.showMessageDialog(null, "Desinfección programada para la sala: " + id);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Sala no encontrada.");
		}
	}

	public static void gestionCitas(String rol) {
		JFrame frame = new JFrame("Gestión de Citas - " + rol);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(4, 1, 10, 10));

		JButton btnCrear = new JButton("Crear Cita");
		btnCrear.addActionListener(e -> crearCita());

		JButton btnListar = new JButton("Listar Citas");
		btnListar.addActionListener(e -> listarCitas());

		JButton btnActualizar = new JButton("Actualizar Estado de Cita");
		btnActualizar.addActionListener(e -> actualizarCita());

		JButton btnVolver = new JButton("Volver al Menú Principal");
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});

		frame.add(btnCrear);
		frame.add(btnListar);
		frame.add(btnActualizar);
		frame.add(btnVolver);

		frame.setVisible(true);
	}

	public static void crearCita() {
		JFrame frame = new JFrame("Crear Cita");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "ID Paciente", "ID Médico", "Fecha (dd/mm/yyyy)", "Hora (HH:mm)" };
		JTextField[] fields = new JTextField[labels.length];

		JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < labels.length; i++) {
			form.add(new JLabel(labels[i] + ":"));
			fields[i] = new JTextField();
			form.add(fields[i]);
		}

		JButton btnCrear = new JButton("Crear Cita");
		btnCrear.addActionListener(e -> {
			String pacienteId = fields[0].getText();
			String medicoId = fields[1].getText();
			String fechaStr = fields[2].getText();
			String horaStr = fields[3].getText();

			if (pacienteId.isEmpty() || medicoId.isEmpty() || fechaStr.isEmpty() || horaStr.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.");
				return;
			}

			// Buscar paciente y médico
			Paciente paciente = null;
			Medico medico = null;

			for (Paciente p : listaPacientes) {
				if (p.getId().equals(pacienteId)) {
					paciente = p;
					break;
				}
			}

			for (Personal p : listaPersonal) {
				if (p.getId().equals(medicoId) && p instanceof Medico) {
					medico = (Medico) p;
					break;
				}
			}

			if (paciente == null) {
				JOptionPane.showMessageDialog(frame, "Paciente no encontrado.");
				return;
			}

			if (medico == null) {
				JOptionPane.showMessageDialog(frame, "Médico no encontrado.");
				return;
			}

			try {
				// Crear nueva cita
				Cita nuevaCita = new Cita(new Date(), medico, paciente, LocalTime.of(1, 0), "Programada");
				listaCitas.add(nuevaCita);

				JOptionPane.showMessageDialog(frame, "Cita creada correctamente.");
				for (JTextField field : fields)
					field.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error al crear la cita: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		frame.add(form, BorderLayout.CENTER);
		frame.add(btnCrear, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void listarCitas() {
		JFrame frame = new JFrame("Lista de Citas");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);

		String[] columnNames = { "Fecha", "Paciente", "Médico", "Estado", "Duración" };
		Object[][] data = new Object[listaCitas.size()][columnNames.length];

		for (int i = 0; i < listaCitas.size(); i++) {
			Cita c = listaCitas.get(i);
			data[i][0] = c.getFecha();
			data[i][1] = c.getPaciente().getNombre() + " " + c.getPaciente().getApellidos();
			data[i][2] = c.getMedico().getNombre();
			data[i][3] = c.getEstado();
			data[i][4] = c.getDuracion();
		}

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void actualizarCita() {
		if (listaCitas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay citas registradas.");
			return;
		}

		// Mostrar lista de citas para seleccionar
		StringBuilder citasInfo = new StringBuilder("Citas disponibles:\n");
		for (int i = 0; i < listaCitas.size(); i++) {
			Cita c = listaCitas.get(i);
			citasInfo.append(i + 1).append(". ").append(c.getPaciente().getNombre()).append(" - ")
					.append(c.getMedico().getNombre()).append(" - ").append(c.getEstado()).append("\n");
		}

		String seleccion = JOptionPane.showInputDialog(citasInfo.toString() + "\nSeleccione el número de cita:");
		if (seleccion != null && !seleccion.isEmpty()) {
			try {
				int index = Integer.parseInt(seleccion) - 1;
				if (index >= 0 && index < listaCitas.size()) {
					String[] estados = { "Programada", "Confirmada", "Completada", "Cancelada" };
					String nuevoEstado = (String) JOptionPane.showInputDialog(null, "Seleccione el nuevo estado:",
							"Actualizar Estado", JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);

					if (nuevoEstado != null) {
						listaCitas.get(index).setEstado(nuevoEstado);
						JOptionPane.showMessageDialog(null, "Estado de cita actualizado a: " + nuevoEstado);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Número de cita inválido.");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
			}
		}
	}

	public static void consultarInventario() {
		JFrame frame = new JFrame("Inventario del Hospital");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Crear inventario de ejemplo
		Inventario inventario = new Inventario();
		inventario.getMedicamentos().add("Paracetamol - 500 unidades");
		inventario.getMedicamentos().add("Ibuprofeno - 300 unidades");
		inventario.getMedicamentos().add("Aspirina - 200 unidades");
		inventario.getMedicamentos().add("Antibióticos - 150 unidades");

		inventario.getEquipoMedico().add("Estetoscopios - 25 unidades");
		inventario.getEquipoMedico().add("Tensiómetros - 15 unidades");
		inventario.getEquipoMedico().add("Termómetros - 50 unidades");
		inventario.getEquipoMedico().add("Oxímetros - 20 unidades");

		inventario.getSuministros().add("Jeringas - 1000 unidades");
		inventario.getSuministros().add("Gasas - 500 paquetes");
		inventario.getSuministros().add("Guantes - 2000 pares");
		inventario.getSuministros().add("Mascarillas - 1500 unidades");

		JTabbedPane tabbedPane = new JTabbedPane();

		// Tab de Medicamentos
		JTextArea medicamentosArea = new JTextArea();
		medicamentosArea.setEditable(false);
		StringBuilder medicamentosText = new StringBuilder("MEDICAMENTOS:\n\n");
		for (String med : inventario.getMedicamentos()) {
			medicamentosText.append("• ").append(med).append("\n");
		}
		medicamentosArea.setText(medicamentosText.toString());
		tabbedPane.addTab("Medicamentos", new JScrollPane(medicamentosArea));

		// Tab de Equipo Médico
		JTextArea equipoArea = new JTextArea();
		equipoArea.setEditable(false);
		StringBuilder equipoText = new StringBuilder("EQUIPO MÉDICO:\n\n");
		for (String equipo : inventario.getEquipoMedico()) {
			equipoText.append("• ").append(equipo).append("\n");
		}
		equipoArea.setText(equipoText.toString());
		tabbedPane.addTab("Equipo Médico", new JScrollPane(equipoArea));

		// Tab de Suministros
		JTextArea suministrosArea = new JTextArea();
		suministrosArea.setEditable(false);
		StringBuilder suministrosText = new StringBuilder("SUMINISTROS:\n\n");
		for (String suministro : inventario.getSuministros()) {
			suministrosText.append("• ").append(suministro).append("\n");
		}
		suministrosArea.setText(suministrosText.toString());
		tabbedPane.addTab("Suministros", new JScrollPane(suministrosArea));

		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public static void gestionHistoriales() {
		JFrame frame = new JFrame("Gestión de Historiales Médicos");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(4, 1, 10, 10));

		JButton btnConsultar = new JButton("Consultar Historial");
		btnConsultar.addActionListener(e -> consultarHistorialPaciente());

		JButton btnActualizar = new JButton("Actualizar Historial");
		btnActualizar.addActionListener(e -> actualizarHistorial());

		JButton btnAgregarImagen = new JButton("Agregar Imagen Médica");
		btnAgregarImagen.addActionListener(e -> agregarImagenMedica());

		JButton btnVolver = new JButton("Volver al Menú Principal");
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal("Doctor");
		});

		frame.add(btnConsultar);
		frame.add(btnActualizar);
		frame.add(btnAgregarImagen);
		frame.add(btnVolver);

		frame.setVisible(true);
	}

	public static void actualizarHistorial() {
		String pacienteId = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
		if (pacienteId != null && !pacienteId.isEmpty()) {
			for (Paciente p : listaPacientes) {
				if (p.getId().equals(pacienteId)) {
					JFrame frame = new JFrame("Actualizar Historial - " + p.getNombre());
					frame.setSize(600, 500);
					frame.setLocationRelativeTo(null);
					frame.setLayout(new BorderLayout());

					String[] labels = { "Consulta", "Diagnóstico", "Tratamiento" };
					JTextArea[] areas = new JTextArea[labels.length];
					JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
					form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

					for (int i = 0; i < labels.length; i++) {
						form.add(new JLabel(labels[i] + ":"));
						areas[i] = new JTextArea(3, 20);
						areas[i].setLineWrap(true);
						areas[i].setWrapStyleWord(true);
						form.add(new JScrollPane(areas[i]));
					}

					JButton btnGuardar = new JButton("Actualizar Historial");
					btnGuardar.addActionListener(e -> {
						HistorialMedico historial = p.getHistorialMedico();
						if (historial != null) {
							historial.setConsulta(areas[0].getText());
							historial.setDiagnostico(areas[1].getText());
							historial.setTratamiento(areas[2].getText());
							historial.setFecha(new Date());

							JOptionPane.showMessageDialog(frame, "Historial actualizado correctamente.");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(frame, "No se pudo acceder al historial del paciente.");
						}
					});

					frame.add(form, BorderLayout.CENTER);
					frame.add(btnGuardar, BorderLayout.SOUTH);
					frame.setVisible(true);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
		}
	}

	public static void agregarImagenMedica() {
		String pacienteId = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
		if (pacienteId != null && !pacienteId.isEmpty()) {
			for (Paciente p : listaPacientes) {
				if (p.getId().equals(pacienteId)) {
					JFrame frame = new JFrame("Agregar Imagen Médica - " + p.getNombre());
					frame.setSize(500, 300);
					frame.setLocationRelativeTo(null);
					frame.setLayout(new BorderLayout());

					String[] labels = { "Nombre de la imagen", "Ruta/Descripción" };
					JTextField[] fields = new JTextField[labels.length];
					JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
					form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

					for (int i = 0; i < labels.length; i++) {
						form.add(new JLabel(labels[i] + ":"));
						fields[i] = new JTextField();
						form.add(fields[i]);
					}

					JButton btnAgregar = new JButton("Agregar Imagen");
					btnAgregar.addActionListener(e -> {
						String nombre = fields[0].getText();
						String ruta = fields[1].getText();

						if (!nombre.isEmpty() && !ruta.isEmpty()) {
							Imagen nuevaImagen = new Imagen(nombre, ruta, new Date());
							p.getHistorialMedico().getImagenes().add(nuevaImagen);

							JOptionPane.showMessageDialog(frame, "Imagen agregada al historial correctamente.");
							for (JTextField field : fields)
								field.setText("");
						} else {
							JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.");
						}
					});

					frame.add(form, BorderLayout.CENTER);
					frame.add(btnAgregar, BorderLayout.SOUTH);
					frame.setVisible(true);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
		}
	}

	public static void gestionRecetas() {
		JFrame frame = new JFrame("Gestión de Recetas");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(4, 1, 10, 10));

		JButton btnCrear = new JButton("Crear Receta");
		btnCrear.addActionListener(e -> crearReceta());

		JButton btnListar = new JButton("Listar Recetas");
		btnListar.addActionListener(e -> listarRecetas());

		JButton btnConsultar = new JButton("Consultar Receta");
		btnConsultar.addActionListener(e -> consultarReceta());

		JButton btnVolver = new JButton("Volver al Menú Principal");
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal("Doctor");
		});

		frame.add(btnCrear);
		frame.add(btnListar);
		frame.add(btnConsultar);
		frame.add(btnVolver);

		frame.setVisible(true);
	}

	public static void crearReceta() {
		JFrame frame = new JFrame("Crear Receta Médica");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "ID Paciente", "ID Médico", "Medicamento", "Dosis", "Frecuencia" };
		JTextField[] fields = new JTextField[labels.length];

		JPanel form = new JPanel(new GridLayout(labels.length, 2, 5, 5));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < labels.length; i++) {
			form.add(new JLabel(labels[i] + ":"));
			fields[i] = new JTextField();
			form.add(fields[i]);
		}

		JButton btnCrear = new JButton("Crear Receta");
		btnCrear.addActionListener(e -> {
			String pacienteId = fields[0].getText();
			String medicoId = fields[1].getText();
			String medicamento = fields[2].getText();
			String dosis = fields[3].getText();
			String frecuencia = fields[4].getText();

			if (pacienteId.isEmpty() || medicoId.isEmpty() || medicamento.isEmpty() || dosis.isEmpty()
					|| frecuencia.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.");
				return;
			}

			// Generar ID único para la receta
			String recetaId = "REC_" + System.currentTimeMillis();

			Receta nuevaReceta = new Receta(recetaId, medicamento, dosis, frecuencia, new Date(), pacienteId, medicoId);
			listaRecetas.add(nuevaReceta);

			JOptionPane.showMessageDialog(frame, "Receta creada correctamente.\nID: " + recetaId);
			for (JTextField field : fields)
				field.setText("");
		});

		frame.add(form, BorderLayout.CENTER);
		frame.add(btnCrear, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void listarRecetas() {
		JFrame frame = new JFrame("Lista de Recetas");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);

		String[] columnNames = { "ID", "Medicamento", "Dosis", "Frecuencia", "Fecha", "ID Paciente", "ID Médico" };
		Object[][] data = new Object[listaRecetas.size()][columnNames.length];

		for (int i = 0; i < listaRecetas.size(); i++) {
			Receta r = listaRecetas.get(i);
			data[i][0] = r.getId();
			data[i][1] = r.getMedicamento();
			data[i][2] = r.getDosis();
			data[i][3] = r.getFrecuencia();
			data[i][4] = r.getFecha();
			data[i][5] = r.getPacienteId();
			data[i][6] = r.getMedicoId();
		}

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void consultarReceta() {
		String recetaId = JOptionPane.showInputDialog("Ingrese el ID de la receta:");
		if (recetaId != null && !recetaId.isEmpty()) {
			for (Receta r : listaRecetas) {
				if (r.getId().equals(recetaId)) {
					String info = "RECETA MÉDICA\n\n" + "ID: " + r.getId() + "\n" + "Medicamento: " + r.getMedicamento()
							+ "\n" + "Dosis: " + r.getDosis() + "\n" + "Frecuencia: " + r.getFrecuencia() + "\n"
							+ "Fecha: " + r.getFecha() + "\n" + "ID Paciente: " + r.getPacienteId() + "\n"
							+ "ID Médico: " + r.getMedicoId();

					JOptionPane.showMessageDialog(null, info, "Información de Receta", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Receta no encontrada.");
		}
	}
}
