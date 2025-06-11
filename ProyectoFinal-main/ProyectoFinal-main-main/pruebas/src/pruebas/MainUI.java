package pruebas;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;

// Clase de utilidad para estilos
class UIUtils {
	// Paleta de colores moderna
	public static final Color PRIMARY_COLOR = new Color(121, 157, 198); // 41, 128, 185 Azul principal
	public static final Color SECONDARY_COLOR = new Color(47, 79, 116); // 52, 152, 219 Azul secundario
	public static final Color SUCCESS_COLOR = new Color(115, 161, 107); // 46, 204, 113Verde
	public static final Color WARNING_COLOR = new Color(178, 181, 136); // 241, 196, 15 Amarillo
	public static final Color DANGER_COLOR = new Color(238, 78, 75); // Rojo
	public static final Color LIGHT_BG = new Color(236, 240, 241); // Fondo claro
	public static final Color DARK_TEXT = new Color(44, 62, 80); // Texto oscuro
	public static final Color LIGHT_TEXT = new Color(236, 240, 241); // Texto claro

	// Fuentes
	public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
	public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 18);
	public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
	public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

	// Método para crear un botón estilizado
	public static JButton createStyledButton(String text, Color bgColor) {
		JButton button = new JButton(text);
		button.setFont(BUTTON_FONT);
		button.setForeground(LIGHT_TEXT);
		button.setBackground(bgColor);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(bgColor.darker(), 1),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Efecto hover
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				button.setBackground(bgColor.brighter());
			}

			public void mouseExited(MouseEvent e) {
				button.setBackground(bgColor);
			}
		});

		return button;
	}

	// Método para crear un panel con fondo degradado
	public static JPanel createGradientPanel() {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
				Color color1 = new Color(236, 240, 241);
				Color color2 = new Color(189, 195, 199);
				GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
	}

	// Método para crear una tabla estilizada
	public static void styleTable(JTable table) {
		table.setFont(TEXT_FONT);
		table.setRowHeight(25);
		table.setGridColor(LIGHT_BG);
		table.setSelectionBackground(SECONDARY_COLOR);
		table.setSelectionForeground(LIGHT_TEXT);

		// Estilo del encabezado
		JTableHeader header = table.getTableHeader();
		header.setFont(HEADER_FONT);
		header.setBackground(PRIMARY_COLOR);
		header.setForeground(LIGHT_TEXT);
		header.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR.darker()));
	}
}

// ============ CLASE PRINCIPAL UI (MEJORADA CON ESTILOS DE BOTONES)
// ============

public class MainUI {

	private static ConectorBD conn = new ConectorBD();
	static List<Paciente> listaPacientes = new ArrayList<>();
	static List<Personal> listaPersonal = new ArrayList<>();
	static List<Medico> listaMedicos = new ArrayList<>();
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

		conn.getConn();
		inicializarDatos();
		mostrarLogin();
		// conn.desconectar();
	}

	public static void inicializarDatos() {
		// Cargar pacientes desde la base de datos
		listaPacientes = ConectorBD.getAllPacientes();

		// Crear algunos empleados de ejemplo (esto debería moverse a un DAO similar)
		listaPersonal.add(new Medico("12345678A", "Dr. García", "garcia@hospital.com", "123456789",
				"Cardiología", Turno.MAÑANA));
		listaPersonal
				.add(new Enfermero("12345678B", "Ana López", "ana@hospital.com", "987654321", Turno.TARDE));
		listaPersonal.add(new Administrativos("12345678C", "Carlos Ruiz", "carlos@hospital.com", "555666777",
				Turno.NOCHE));
		listaPersonal.add(
				new Mantenimiento("12345678D", "José Pérez", "jose@hospital.com", "111222333", Turno.MAÑANA));

		// Crear algunas salas de ejemplo (esto debería moverse a un DAO similar)
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
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con imagen de fondo
		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					// Cargar la imagen de fondo
					Image img = new ImageIcon(getClass().getResource("/pruebas/resources/hospital_bg.jpg")).getImage();
					// Dibujar la imagen escalada para cubrir todo el panel
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					// Si no se puede cargar la imagen, usar un fondo degradado
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					Color color1 = new Color(236, 240, 241);
					Color color2 = new Color(189, 195, 199);
					GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
					g2d.setPaint(gp);
					g2d.fillRect(0, 0, w, h);
				}
			}
		};
		mainPanel.setLayout(new BorderLayout());

		// Panel superior con logo y título
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		JLabel titleLabel = new JLabel("Sistema Hospitalario", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		topPanel.add(titleLabel);
		mainPanel.add(topPanel, BorderLayout.NORTH);

		// Panel central con el formulario
		JPanel formPanel = new JPanel();
		formPanel.setOpaque(false);
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para el formulario con fondo semi-transparente
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new GridBagLayout());
		formContainer.setBackground(new Color(255, 255, 255, 200));
		formContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Campos del formulario
		JLabel usuarioLabel = new JLabel("DNI:");
		usuarioLabel.setFont(UIUtils.TEXT_FONT);
		usuarioLabel.setForeground(UIUtils.DARK_TEXT);
		JTextField usuarioField = new JTextField(20);
		usuarioField.setFont(UIUtils.TEXT_FONT);
		usuarioField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(UIUtils.PRIMARY_COLOR),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(UIUtils.TEXT_FONT);
		passwordLabel.setForeground(UIUtils.DARK_TEXT);
		JPasswordField passwordField = new JPasswordField(20);
		passwordField.setFont(UIUtils.TEXT_FONT);
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(UIUtils.PRIMARY_COLOR), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel rolLabel = new JLabel("Rol:");
		rolLabel.setFont(UIUtils.TEXT_FONT);
		rolLabel.setForeground(UIUtils.DARK_TEXT);
		String[] roles = { "Administrador", "Administrativo", "Medico", "Enfermero", "Mantenimiento" };
		JComboBox<String> rolBox = new JComboBox<>(roles);
		rolBox.setFont(UIUtils.TEXT_FONT);
		rolBox.setBackground(Color.WHITE);
		rolBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(UIUtils.PRIMARY_COLOR),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		// Agregar componentes al panel del formulario
		gbc.gridx = 0;
		gbc.gridy = 0;
		formContainer.add(usuarioLabel, gbc);
		gbc.gridx = 1;
		formContainer.add(usuarioField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		formContainer.add(passwordLabel, gbc);
		gbc.gridx = 1;
		formContainer.add(passwordField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		formContainer.add(rolLabel, gbc);
		gbc.gridx = 1;
		formContainer.add(rolBox, gbc);

		formPanel.add(formContainer);
		mainPanel.add(formPanel, BorderLayout.CENTER);

		// Panel inferior con botón de login
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);
		JButton loginBtn = UIUtils.createStyledButton("Iniciar Sesión", UIUtils.PRIMARY_COLOR);
		buttonPanel.add(loginBtn);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		loginBtn.addActionListener(e -> {
			String dni = usuarioField.getText();
			String password = new String(passwordField.getPassword());
			String rolSeleccionado = (String) rolBox.getSelectedItem();

			if (dni.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Debe ingresar DNI y contraseña.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validar usuario contra la base de datos
			if (ConectorBD.validateUser(dni, password, rolSeleccionado)) {
				frame.dispose();
				menuPrincipal(rolSeleccionado);
				JOptionPane.showMessageDialog(null, "Acceso realizado con éxito.\nDNI: " + dni, "Bienvenido",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "DNI o contraseña incorrectos.", "Error de autenticación",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void menuPrincipal(String rol) {
		JFrame frame = new JFrame("Menú Principal - Rol: " + rol);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);

		// Panel principal con imagen de fondo
		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					// Cargar la imagen de fondo
					Image img = new ImageIcon(getClass().getResource("/pruebas/resources/hospital_menu_bg2.jpg"))
							.getImage();
					// Dibujar la imagen escalada para cubrir todo el panel
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					// Si no se puede cargar la imagen, usar un fondo degradado
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					Color color1 = new Color(236, 240, 241);
					Color color2 = new Color(189, 195, 199);
					GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
					g2d.setPaint(gp);
					g2d.fillRect(0, 0, w, h);
				}
			}
		};
		mainPanel.setLayout(new BorderLayout());

		// Panel superior con título
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		JLabel titleLabel = new JLabel("Sistema de Gestión Hospitalaria", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		topPanel.add(titleLabel);
		mainPanel.add(topPanel, BorderLayout.NORTH);

		// Panel central con botones
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para los botones con fondo semi-transparente
		JPanel buttonsContainer = new JPanel(new GridBagLayout());
		buttonsContainer.setBackground(new Color(255, 255, 255, 200));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbcButtons = new GridBagConstraints();
		gbcButtons.insets = new Insets(10, 10, 10, 10);
		gbcButtons.fill = GridBagConstraints.HORIZONTAL;

		// Crear botones según el rol
		int row = 0;
		if (rol.equals("Administrador")) {
			JButton btnPacientes = UIUtils.createStyledButton("Gestión de Pacientes", UIUtils.PRIMARY_COLOR);
			btnPacientes.addActionListener(e -> gestionPacientes(rol));
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnPacientes, gbcButtons);
		}

		if (rol.equals("Administrador") || rol.equals("Aministrativo")) {
			JButton btnEmpleados = UIUtils.createStyledButton("Gestión de Empleados", UIUtils.PRIMARY_COLOR);
			btnEmpleados.addActionListener(e -> gestionEmpleados(rol));
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnEmpleados, gbcButtons);
		}
		if (rol.equals("Administrador") || rol.equals("Administrativo")) {
			JButton btnCitas = UIUtils.createStyledButton("Gestión de Citas", UIUtils.PRIMARY_COLOR);
			btnCitas.addActionListener(e -> gestionCitas(rol));
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnCitas, gbcButtons);
		}
		if (rol.equals("Administrador")) {
			JButton btnSalas = UIUtils.createStyledButton("Gestión de Salas", UIUtils.PRIMARY_COLOR);
			btnSalas.addActionListener(e -> gestionSalas(rol));
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnSalas, gbcButtons);

			JButton btnInventario = UIUtils.createStyledButton("Consultar Inventario", UIUtils.PRIMARY_COLOR);
			btnInventario.addActionListener(e -> consultarInventario());
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnInventario, gbcButtons);
		}

		if (rol.equals("Medico")) {
			JButton btnHistoriales = UIUtils.createStyledButton("Historiales Médicos", UIUtils.PRIMARY_COLOR);
			btnHistoriales.addActionListener(e -> gestionHistoriales());
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnHistoriales, gbcButtons);

			JButton btnRecetas = UIUtils.createStyledButton("Gestión de Recetas", UIUtils.PRIMARY_COLOR);
			btnRecetas.addActionListener(e -> gestionRecetas());
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnRecetas, gbcButtons);
		}

		if (rol.equals("Enfermero")) {
			JButton btnPacientes = UIUtils.createStyledButton("Historial Pacientes", UIUtils.PRIMARY_COLOR);
			btnPacientes.addActionListener(e -> gestionHistorialesEnfermero());
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnPacientes, gbcButtons);

			JButton btnCamas = UIUtils.createStyledButton("Gestión de Habitaciones", UIUtils.PRIMARY_COLOR);
			btnCamas.addActionListener(e -> asignarHabitacion());
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnCamas, gbcButtons);
		}

		if (rol.equals("Mantenimiento")) {
			JButton btnPacientes = UIUtils.createStyledButton("Gestión de Habitaciones", UIUtils.PRIMARY_COLOR);
			btnPacientes.addActionListener(e -> gestionSalas(rol));
			gbcButtons.gridx = 0;
			gbcButtons.gridy = row++;
			buttonsContainer.add(btnPacientes, gbcButtons);
		}

		centerPanel.add(buttonsContainer);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botón de cerrar sesión
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnCerrarSesion = UIUtils.createStyledButton("Cerrar Sesión", UIUtils.DANGER_COLOR);
		btnCerrarSesion.addActionListener(e -> {
			frame.dispose();
			mostrarLogin();
		});
		bottomPanel.add(btnCerrarSesion);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void gestionPacientes(String rol) {
		JFrame frame = new JFrame("Gestión de Pacientes - " + rol);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con imagen de fondo
		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					// Cargar la imagen de fondo
					Image img = new ImageIcon(getClass().getResource("/pruebas/resources/hospital_pacientes_bg.jpg"))
							.getImage();
					// Dibujar la imagen escalada para cubrir todo el panel
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					// Si no se puede cargar la imagen, usar un fondo degradado
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					Color color1 = new Color(236, 240, 241);
					Color color2 = new Color(189, 195, 199);
					GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
					g2d.setPaint(gp);
					g2d.fillRect(0, 0, w, h);
				}
			}
		};
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Gestión de Pacientes", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel central con botones organizados en categorías
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para Registro y Consulta
		JPanel registroPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		registroPanel.setOpaque(false);
		registroPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.PRIMARY_COLOR),
				"Registro y Consulta", TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT,
				UIUtils.PRIMARY_COLOR));

		JButton btnRegistrar = UIUtils.createStyledButton("Registrar Nuevo Paciente", UIUtils.PRIMARY_COLOR);
		btnRegistrar.addActionListener(e -> registrarPaciente());

		JButton btnListar = UIUtils.createStyledButton("Listar Pacientes", UIUtils.SECONDARY_COLOR);
		btnListar.addActionListener(e -> listarPacientes());

		registroPanel.add(btnRegistrar);
		registroPanel.add(btnListar);

		// Panel para Historial Médico
		JPanel historialPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		historialPanel.setOpaque(false);
		historialPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.SUCCESS_COLOR),
				"Historial Médico", TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT, UIUtils.SUCCESS_COLOR));

		JButton btnHistorial = UIUtils.createStyledButton("Consultar Historial", UIUtils.SUCCESS_COLOR);
		btnHistorial.addActionListener(e -> consultarHistorialPaciente());

		JButton btnActualizarHistorial = UIUtils.createStyledButton("Actualizar Historial", UIUtils.SUCCESS_COLOR);
		btnActualizarHistorial.addActionListener(e -> actualizarHistorial());

		historialPanel.add(btnHistorial);
		historialPanel.add(btnActualizarHistorial);

		// Panel para Gestión de Habitaciones
		JPanel habitacionesPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		habitacionesPanel.setOpaque(false);
		habitacionesPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(UIUtils.WARNING_COLOR), "Gestión de Habitaciones", TitledBorder.CENTER,
				TitledBorder.TOP, UIUtils.HEADER_FONT, UIUtils.WARNING_COLOR));

		JButton btnHabitaciones = UIUtils.createStyledButton("Asignar Habitación", UIUtils.WARNING_COLOR);
		btnHabitaciones.addActionListener(e -> asignarHabitacion());

		JButton btnSolicitudes = UIUtils.createStyledButton("Solicitudes de Atención", UIUtils.WARNING_COLOR);
		btnSolicitudes.addActionListener(e -> solicitudesAtencion());

		habitacionesPanel.add(btnHabitaciones);
		habitacionesPanel.add(btnSolicitudes);

		// Agregar paneles al panel central
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(registroPanel, gbc);
		gbc.gridx = 1;
		centerPanel.add(historialPanel, gbc);
		gbc.gridx = 2;
		centerPanel.add(habitacionesPanel, gbc);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botón de volver
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnVolver = UIUtils.createStyledButton("Volver al Menú Principal", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});
		bottomPanel.add(btnVolver);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
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

				// Guardar en la base de datos
				ConectorBD.savePaciente(nuevo);

				// Agregar a la lista en memoria
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
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con fondo degradado
		JPanel mainPanel = UIUtils.createGradientPanel();
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Lista de Pacientes", SwingConstants.CENTER);
		titleLabel.setFont(UIUtils.TITLE_FONT);
		titleLabel.setForeground(UIUtils.DARK_TEXT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Tabla
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
		UIUtils.styleTable(table);

		// Personalizar el renderizador de celdas
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Panel de botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);

		JButton btnActualizar = UIUtils.createStyledButton("Actualizar", UIUtils.SUCCESS_COLOR);
		btnActualizar.addActionListener(e -> {
			frame.dispose();
			listarPacientes();
		});

		JButton btnVolver = UIUtils.createStyledButton("Volver", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> frame.dispose());

		buttonPanel.add(btnActualizar);
		buttonPanel.add(btnVolver);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
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
							.append(" - Desinfectada: ").append(sala.isDisponible() ? "Sí" : "No").append("\n");
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
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con fondo degradado
		JPanel mainPanel = UIUtils.createGradientPanel();
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Gestión de Empleados", SwingConstants.CENTER);
		titleLabel.setFont(UIUtils.TITLE_FONT);
		titleLabel.setForeground(UIUtils.DARK_TEXT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel central con botones organizados en categorías
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para Registro y Listado
		JPanel registroPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		registroPanel.setOpaque(false);
		registroPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.PRIMARY_COLOR),
				"Registro y Consulta", TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT,
				UIUtils.PRIMARY_COLOR));

		JButton btnRegistrar = UIUtils.createStyledButton("Registrar Nuevo Empleado", UIUtils.PRIMARY_COLOR);
		btnRegistrar.addActionListener(e -> registrarEmpleado());

		JButton btnListar = UIUtils.createStyledButton("Listar Empleados", UIUtils.SECONDARY_COLOR);
		btnListar.addActionListener(e -> listarEmpleados());

		registroPanel.add(btnRegistrar);
		registroPanel.add(btnListar);

		// Panel para Modificación
		JPanel modificacionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		modificacionPanel.setOpaque(false);
		modificacionPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(UIUtils.SUCCESS_COLOR), "Modificación de Datos", TitledBorder.CENTER,
				TitledBorder.TOP, UIUtils.HEADER_FONT, UIUtils.SUCCESS_COLOR));

		JButton btnModificar = UIUtils.createStyledButton("Modificar Empleado", UIUtils.SUCCESS_COLOR);
		btnModificar.addActionListener(e -> modificarEmpleado());

		JButton btnEliminar = UIUtils.createStyledButton("Eliminar Empleado", UIUtils.DANGER_COLOR);
		btnEliminar.addActionListener(e -> eliminarEmpleado());

		modificacionPanel.add(btnModificar);
		modificacionPanel.add(btnEliminar);

		// Panel para Estadísticas
		JPanel estadisticasPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		estadisticasPanel.setOpaque(false);
		estadisticasPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.WARNING_COLOR), "Estadísticas",
						TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT, UIUtils.WARNING_COLOR));

		JButton btnEstadisticas = UIUtils.createStyledButton("Ver Estadísticas", UIUtils.WARNING_COLOR);
		btnEstadisticas.addActionListener(e -> {
			// Aquí se podría implementar la visualización de estadísticas
			JOptionPane.showMessageDialog(frame, "Funcionalidad en desarrollo", "Estadísticas",
					JOptionPane.INFORMATION_MESSAGE);
		});

		JButton btnReportes = UIUtils.createStyledButton("Generar Reportes", UIUtils.WARNING_COLOR);
		btnReportes.addActionListener(e -> {
			// Aquí se podría implementar la generación de reportes
			JOptionPane.showMessageDialog(frame, "Funcionalidad en desarrollo", "Reportes",
					JOptionPane.INFORMATION_MESSAGE);
		});

		estadisticasPanel.add(btnEstadisticas);
		estadisticasPanel.add(btnReportes);

		// Agregar paneles al panel central
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(registroPanel, gbc);
		gbc.gridx = 1;
		centerPanel.add(modificacionPanel, gbc);
		gbc.gridx = 2;
		centerPanel.add(estadisticasPanel, gbc);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botón de volver
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnVolver = UIUtils.createStyledButton("Volver al Menú Principal", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});
		bottomPanel.add(btnVolver);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void registrarEmpleado() {
		JFrame frame = new JFrame("Registro de Empleado");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		String[] labels = { "DNI", "Nombre", "Email", "Teléfono", "Cargo", "Especialidad (solo para médicos)",
				"Turno(Mañana, Tarde, Noche)" };
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

			String dni = fields[0].getText();
			String nombre = fields[1].getText();
			String email = fields[2].getText();
			String telefono = fields[3].getText();
			String cargo = fields[4].getText();
			String especialidad = fields[5].getText();
			String turno = fields[6].getText();
			Turno turnos = Turno.valueOf(turno.toUpperCase());

			if (dni.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || cargo.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Todos los campos obligatorios deben ser completados.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Personal nuevoEmpleado;
			switch (cargo.toLowerCase()) {
				case "medico":
					nuevoEmpleado = new Medico(dni, nombre, email, telefono,
							especialidad.isEmpty() ? "General" : especialidad, turnos);
					break;
				case "enfermero":

					nuevoEmpleado = new Enfermero(dni, nombre, email, telefono, turnos);
					break;
				case "administrativo":

					nuevoEmpleado = new Administrativos(dni, nombre, email, telefono, turnos);
					break;
				case "mantenimiento":
					nuevoEmpleado = new Mantenimiento(dni, nombre, email, telefono, turnos);
					break;
				default:
					nuevoEmpleado = new Administrativos(dni, nombre, email, telefono, turnos); // Por defecto
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

		String[] columnNames = { "ID", "DNI", "Nombre", "Email", "Teléfono", "Cargo", "Especialidad", "Turno" };
		Object[][] data = new Object[listaPersonal.size()][columnNames.length];

		for (int i = 0; i < listaPersonal.size(); i++) {
			Personal p = listaPersonal.get(i);
			data[i][0] = p.getId();
			data[i][1] = p.getDni();
			data[i][2] = p.getNombre();
			data[i][3] = p.getEmail();
			data[i][4] = p.getTelefono();
			data[i][5] = p.getRol();
			data[i][6] = (p instanceof Medico) ? ((Medico) p).getEspecialidad() : "N/A";
			data[i][7] = p.getTurno();
		}

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void modificarEmpleado() {
		String idS = JOptionPane.showInputDialog("Ingrese el ID del empleado a modificar:");
		if (idS != null && !idS.isEmpty()) {
			int id = Integer.parseInt(idS);
			for (Personal p : listaPersonal) {
				if (p.getId() == id) {
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
		String idS = JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar:");
		if (idS != null && !idS.isEmpty()) {
			int id = Integer.parseInt(idS);
			for (int i = 0; i < listaPersonal.size(); i++) {
				if (listaPersonal.get(i).getId() == id) {
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
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con fondo degradado
		JPanel mainPanel = UIUtils.createGradientPanel();
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Gestión de Salas", SwingConstants.CENTER);
		titleLabel.setFont(UIUtils.TITLE_FONT);
		titleLabel.setForeground(UIUtils.DARK_TEXT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel central con botones organizados en categorías
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para Registro y Consulta
		JPanel registroPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		registroPanel.setOpaque(false);
		registroPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.PRIMARY_COLOR),
				"Registro y Consulta", TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT,
				UIUtils.PRIMARY_COLOR));

		JButton btnRegistrar = UIUtils.createStyledButton("Registrar Nueva Sala", UIUtils.PRIMARY_COLOR);
		btnRegistrar.addActionListener(e -> registrarSala());

		JButton btnListar = UIUtils.createStyledButton("Listar Salas", UIUtils.SECONDARY_COLOR);
		btnListar.addActionListener(e -> listarSalas());

		registroPanel.add(btnRegistrar);
		registroPanel.add(btnListar);

		// Panel para Mantenimiento
		JPanel mantenimientoPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		mantenimientoPanel.setOpaque(false);
		mantenimientoPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.SUCCESS_COLOR), "Mantenimiento",
						TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT, UIUtils.SUCCESS_COLOR));

		JButton btnModificar = UIUtils.createStyledButton("Modificar Sala", UIUtils.SUCCESS_COLOR);
		btnModificar.addActionListener(e -> modificarSala());

		JButton btnDesinfectar = UIUtils.createStyledButton("Programar Desinfección", UIUtils.SUCCESS_COLOR);
		btnDesinfectar.addActionListener(e -> programarDesinfeccion());

		mantenimientoPanel.add(btnModificar);
		mantenimientoPanel.add(btnDesinfectar);

		// Panel para Estadísticas
		JPanel estadisticasPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		estadisticasPanel.setOpaque(false);
		estadisticasPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIUtils.WARNING_COLOR), "Estadísticas",
						TitledBorder.CENTER, TitledBorder.TOP, UIUtils.HEADER_FONT, UIUtils.WARNING_COLOR));

		JButton btnOcupacion = UIUtils.createStyledButton("Ver Ocupación", UIUtils.WARNING_COLOR);
		btnOcupacion.addActionListener(e -> {
			// Aquí se podría implementar la visualización de estadísticas de ocupación
			JOptionPane.showMessageDialog(frame, "Funcionalidad en desarrollo", "Estadísticas de Ocupación",
					JOptionPane.INFORMATION_MESSAGE);
		});

		JButton btnReportes = UIUtils.createStyledButton("Generar Reportes", UIUtils.WARNING_COLOR);
		btnReportes.addActionListener(e -> {
			// Aquí se podría implementar la generación de reportes
			JOptionPane.showMessageDialog(frame, "Funcionalidad en desarrollo", "Reportes",
					JOptionPane.INFORMATION_MESSAGE);
		});

		estadisticasPanel.add(btnOcupacion);
		estadisticasPanel.add(btnReportes);

		// Agregar paneles al panel central
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(registroPanel, gbc);
		gbc.gridx = 1;
		centerPanel.add(mantenimientoPanel, gbc);
		gbc.gridx = 2;
		centerPanel.add(estadisticasPanel, gbc);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botón de volver
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnVolver = UIUtils.createStyledButton("Volver al Menú Principal", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});
		bottomPanel.add(btnVolver);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
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
			data[i][3] = s.isDisponible() ? "Sí" : "No";
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
					Mantenimiento personal = new Mantenimiento("12345678D", "José Pérez", "jose@hospital.com",
							"111222333", Turno.MAÑANA);
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(UIUtils.LIGHT_BG);

		// Panel de título
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(UIUtils.PRIMARY_COLOR);
		JLabel titleLabel = new JLabel("Gestión de Citas");
		titleLabel.setFont(UIUtils.TITLE_FONT);
		titleLabel.setForeground(UIUtils.LIGHT_TEXT);
		titlePanel.add(titleLabel);

		// Panel de botones
		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		buttonsPanel.setBackground(UIUtils.LIGHT_BG);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		int row = 0;

		if (rol.equals("Administrativo")) {
			// Botón para ver médicos
			JButton btnVerMedicos = UIUtils.createStyledButton("Ver Médicos Disponibles", UIUtils.PRIMARY_COLOR);
			btnVerMedicos.addActionListener(e -> mostrarMedicosDisponibles());
			gbc.gridx = 0;
			gbc.gridy = row++;
			buttonsPanel.add(btnVerMedicos, gbc);

			// Botón para ver pacientes
			JButton btnVerPacientes = UIUtils.createStyledButton("Ver Pacientes", UIUtils.PRIMARY_COLOR);
			btnVerPacientes.addActionListener(e -> listarPacientes());
			gbc.gridx = 0;
			gbc.gridy = row++;
			buttonsPanel.add(btnVerPacientes, gbc);

			// Botón para ver calendario
			JButton btnCalendario = UIUtils.createStyledButton("Ver Calendario de Disponibilidad",
					UIUtils.PRIMARY_COLOR);
			btnCalendario.addActionListener(e -> mostrarCalendarioDisponibilidad());
			gbc.gridx = 0;
			gbc.gridy = row++;
			buttonsPanel.add(btnCalendario, gbc);
		}

		// Botones comunes para ambos roles
		JButton btnCrearCita = UIUtils.createStyledButton("Crear Cita", UIUtils.SUCCESS_COLOR);
		btnCrearCita.addActionListener(e -> crearCita());
		gbc.gridx = 0;
		gbc.gridy = row++;
		buttonsPanel.add(btnCrearCita, gbc);

		JButton btnListarCitas = UIUtils.createStyledButton("Listar Citas", UIUtils.PRIMARY_COLOR);
		btnListarCitas.addActionListener(e -> listarCitas());
		gbc.gridx = 0;
		gbc.gridy = row++;
		buttonsPanel.add(btnListarCitas, gbc);

		JButton btnModificarCita = UIUtils.createStyledButton("Modificar Cita", UIUtils.WARNING_COLOR);
		btnModificarCita.addActionListener(e -> actualizarCita());
		gbc.gridx = 0;
		gbc.gridy = row++;
		buttonsPanel.add(btnModificarCita, gbc);

		JButton btnCancelarCita = UIUtils.createStyledButton("Cancelar Cita", UIUtils.DANGER_COLOR);
		btnCancelarCita.addActionListener(e -> cancelarCita());
		gbc.gridx = 0;
		gbc.gridy = row++;
		buttonsPanel.add(btnCancelarCita, gbc);

		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void mostrarMedicosDisponibles() {
		JFrame frame = new JFrame("Médicos Disponibles");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(UIUtils.LIGHT_BG);

		// Crear tabla de médicos
		String[] columnNames = { "ID", "Nombre", "Especialidad", "Horario" };
		Object[][] data = new Object[listaMedicos.size()][4];

		int row = 0;
		for (Medico medico : listaMedicos) {
			data[row][0] = medico.getId();
			data[row][1] = medico.getNombre();
			data[row][2] = medico.getEspecialidad();
			data[row][3] = "8:00 - 16:00"; // Horario por defecto
			row++;
		}

		JTable table = new JTable(data, columnNames);
		UIUtils.styleTable(table);
		JScrollPane scrollPane = new JScrollPane(table);

		panel.add(scrollPane, BorderLayout.CENTER);
		frame.add(panel);
		frame.setVisible(true);
	}

	/*
	 * private static void mostrarPacientes() { JFrame frame = new
	 * JFrame("Lista de Pacientes");
	 * frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); frame.setSize(600,
	 * 400); frame.setLocationRelativeTo(null);
	 * 
	 * JPanel panel = new JPanel(new BorderLayout());
	 * panel.setBackground(UIUtils.LIGHT_BG);
	 * 
	 * // Crear tabla de pacientes String[] columnNames = { "ID", "Nombre", "Edad",
	 * "Teléfono" }; Object[][] data = new Object[listaPacientes.size()][4];
	 * 
	 * int row = 0; for (Paciente paciente : listaPacientes) { data[row][0] =
	 * paciente.getId(); data[row][1] = paciente.getNombre(); data[row][2] =
	 * paciente.getEdad(); data[row][3] = paciente.getTelefono(); row++; }
	 * 
	 * JTable table = new JTable(data, columnNames); UIUtils.styleTable(table);
	 * JScrollPane scrollPane = new JScrollPane(table);
	 * 
	 * panel.add(scrollPane, BorderLayout.CENTER); frame.add(panel);
	 * frame.setVisible(true); }
	 * 
	 */
	public static void mostrarCalendarioDisponibilidad() {
		JFrame frame = new JFrame("Calendario de Disponibilidad");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(UIUtils.LIGHT_BG);

		// Panel superior para seleccionar médico
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(UIUtils.LIGHT_BG);

		JLabel lblMedico = new JLabel("Seleccionar Médico:");
		JComboBox<String> comboMedicos = new JComboBox<>();
		for (Medico medico : listaMedicos) {
			if (medico.getRol().equals("Medico")) {
				comboMedicos.addItem(medico.getNombre());
			}
		}

		topPanel.add(lblMedico);
		topPanel.add(comboMedicos);

		// Panel central para el calendario
		JPanel calendarPanel = new JPanel(new GridLayout(7, 8));
		calendarPanel.setBackground(UIUtils.LIGHT_BG);

		// Agregar días de la semana
		String[] dias = { "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom" };
		for (String dia : dias) {
			JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
			lblDia.setFont(UIUtils.TEXT_FONT);
			calendarPanel.add(lblDia);
		}

		// Agregar horas y slots
		String[] horas = { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00" };
		for (String hora : horas) {
			JLabel lblHora = new JLabel(hora, SwingConstants.CENTER);
			lblHora.setFont(UIUtils.TEXT_FONT);
			calendarPanel.add(lblHora);

			for (int i = 0; i < 7; i++) {
				JButton slot = new JButton("Disponible");
				slot.setBackground(UIUtils.SUCCESS_COLOR);
				slot.setForeground(UIUtils.LIGHT_TEXT);
				slot.setFont(UIUtils.TEXT_FONT);
				calendarPanel.add(slot);
			}
		}

		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(calendarPanel, BorderLayout.CENTER);
		frame.add(panel);
		frame.setVisible(true);
	}

	public static void cancelarCita() {
		JFrame frame = new JFrame("Cancelar Cita");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(UIUtils.LIGHT_BG);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblId = new JLabel("ID de la Cita:");
		JTextField txtId = new JTextField(20);
		JButton btnCancelar = UIUtils.createStyledButton("Cancelar Cita", UIUtils.DANGER_COLOR);

		btnCancelar.addActionListener(e -> {
			String id = txtId.getText();
			// Implementar lógica para cancelar la cita
			JOptionPane.showMessageDialog(frame, "Cita cancelada exitosamente");
			frame.dispose();
		});

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblId, gbc);
		gbc.gridx = 1;
		panel.add(txtId, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		panel.add(btnCancelar, gbc);

		frame.add(panel);
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
			String medicoIdStr = fields[1].getText();
			String fechaStr = fields[2].getText();
			String horaStr = fields[3].getText();

			if (pacienteId.isEmpty() || medicoIdStr.isEmpty() || fechaStr.isEmpty() || horaStr.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.");
				return;
			}

			try {

				int medicoId = Integer.parseInt(medicoIdStr);

				// Buscar paciente y médico
				Paciente paciente = null;
				Medico medico = null;

				for (Paciente p : listaPacientes) {
					if (p.getId().equalsIgnoreCase(pacienteId)) {
						paciente = p;
						break;
					}
				}

				for (Personal p : listaPersonal) {
					if (p.getId() == medicoId && p instanceof Medico) {
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

				// Crear nueva cita
				Cita nuevaCita = new Cita(new Date(), medico, paciente, LocalTime.of(1, 0), "Programada");
				listaCitas.add(nuevaCita);

				JOptionPane.showMessageDialog(frame, "Cita creada correctamente.");
				for (JTextField field : fields)
					field.setText("");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Los IDs deben ser números enteros.");
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
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con fondo degradado
		JPanel mainPanel = UIUtils.createGradientPanel();
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Inventario del Hospital", SwingConstants.CENTER);
		titleLabel.setFont(UIUtils.TITLE_FONT);
		titleLabel.setForeground(UIUtils.DARK_TEXT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

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
		tabbedPane.setFont(UIUtils.HEADER_FONT);
		tabbedPane.setBackground(UIUtils.LIGHT_BG);
		tabbedPane.setForeground(UIUtils.DARK_TEXT);

		// Tab de Medicamentos
		JPanel medicamentosPanel = new JPanel(new BorderLayout());
		medicamentosPanel.setBackground(UIUtils.LIGHT_BG);
		JTextArea medicamentosArea = new JTextArea();
		medicamentosArea.setFont(UIUtils.TEXT_FONT);
		medicamentosArea.setEditable(false);
		medicamentosArea.setBackground(UIUtils.LIGHT_BG);
		medicamentosArea.setForeground(UIUtils.DARK_TEXT);
		StringBuilder medicamentosText = new StringBuilder("MEDICAMENTOS:\n\n");
		for (String med : inventario.getMedicamentos()) {
			medicamentosText.append("• ").append(med).append("\n");
		}
		medicamentosArea.setText(medicamentosText.toString());
		medicamentosPanel.add(new JScrollPane(medicamentosArea), BorderLayout.CENTER);
		tabbedPane.addTab("Medicamentos", medicamentosPanel);

		// Tab de Equipo Médico
		JPanel equipoPanel = new JPanel(new BorderLayout());
		equipoPanel.setBackground(UIUtils.LIGHT_BG);
		JTextArea equipoArea = new JTextArea();
		equipoArea.setFont(UIUtils.TEXT_FONT);
		equipoArea.setEditable(false);
		equipoArea.setBackground(UIUtils.LIGHT_BG);
		equipoArea.setForeground(UIUtils.DARK_TEXT);
		StringBuilder equipoText = new StringBuilder("EQUIPO MÉDICO:\n\n");
		for (String equipo : inventario.getEquipoMedico()) {
			equipoText.append("• ").append(equipo).append("\n");
		}
		equipoArea.setText(equipoText.toString());
		equipoPanel.add(new JScrollPane(equipoArea), BorderLayout.CENTER);
		tabbedPane.addTab("Equipo Médico", equipoPanel);

		// Tab de Suministros
		JPanel suministrosPanel = new JPanel(new BorderLayout());
		suministrosPanel.setBackground(UIUtils.LIGHT_BG);
		JTextArea suministrosArea = new JTextArea();
		suministrosArea.setFont(UIUtils.TEXT_FONT);
		suministrosArea.setEditable(false);
		suministrosArea.setBackground(UIUtils.LIGHT_BG);
		suministrosArea.setForeground(UIUtils.DARK_TEXT);
		StringBuilder suministrosText = new StringBuilder("SUMINISTROS:\n\n");
		for (String suministro : inventario.getSuministros()) {
			suministrosText.append("• ").append(suministro).append("\n");
		}
		suministrosArea.setText(suministrosText.toString());
		suministrosPanel.add(new JScrollPane(suministrosArea), BorderLayout.CENTER);
		tabbedPane.addTab("Suministros", suministrosPanel);

		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		// Panel de botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);

		JButton btnActualizar = UIUtils.createStyledButton("Actualizar", UIUtils.SUCCESS_COLOR);
		btnActualizar.addActionListener(e -> {
			frame.dispose();
			consultarInventario();
		});

		JButton btnVolver = UIUtils.createStyledButton("Volver", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> frame.dispose());

		buttonPanel.add(btnActualizar);
		buttonPanel.add(btnVolver);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void gestionHistoriales() {
		JFrame frame = new JFrame("Gestión de Historiales Médicos");
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con imagen de fondo
		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					// Cargar la imagen de fondo
					Image img = new ImageIcon(getClass().getResource("/pruebas/resources/hospital_historial_bg.jpg"))
							.getImage();
					// Dibujar la imagen escalada para cubrir todo el panel
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					// Si no se puede cargar la imagen, usar un fondo degradado
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					Color color1 = new Color(236, 240, 241);
					Color color2 = new Color(189, 195, 199);
					GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
					g2d.setPaint(gp);
					g2d.fillRect(0, 0, w, h);
				}
			}
		};
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Gestión de Historiales Médicos", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel central con botones
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para los botones con fondo semi-transparente
		JPanel buttonsContainer = new JPanel(new GridLayout(3, 1, 10, 10));
		buttonsContainer.setBackground(new Color(255, 255, 255, 200));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JButton btnListar = UIUtils.createStyledButton("Listar Pacientes", UIUtils.SECONDARY_COLOR);
		btnListar.addActionListener(e -> listarPacientes());

		JButton btnConsultar = UIUtils.createStyledButton("Consultar Historial", UIUtils.PRIMARY_COLOR);
		btnConsultar.addActionListener(e -> consultarHistorialPaciente());

		JButton btnActualizar = UIUtils.createStyledButton("Actualizar Historial", UIUtils.SUCCESS_COLOR);
		btnActualizar.addActionListener(e -> actualizarHistorial());

		JButton btnAgregarImagen = UIUtils.createStyledButton("Agregar Imagen Médica", UIUtils.WARNING_COLOR);
		btnAgregarImagen.addActionListener(e -> agregarImagenMedica());

		buttonsContainer.add(btnListar);
		buttonsContainer.add(btnConsultar);
		buttonsContainer.add(btnActualizar);
		buttonsContainer.add(btnAgregarImagen);

		centerPanel.add(buttonsContainer);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botón de volver
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnVolver = UIUtils.createStyledButton("Volver al Menú Principal", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal("Medico");
		});
		bottomPanel.add(btnVolver);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void gestionHistorialesEnfermero() {
		JFrame frame = new JFrame("Gestión de Historiales Médicos");
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Panel principal con imagen de fondo
		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					// Cargar la imagen de fondo
					Image img = new ImageIcon(getClass().getResource("/pruebas/resources/hospital_historial_bg.jpg"))
							.getImage();
					// Dibujar la imagen escalada para cubrir todo el panel
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					// Si no se puede cargar la imagen, usar un fondo degradado
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					Color color1 = new Color(236, 240, 241);
					Color color2 = new Color(189, 195, 199);
					GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
					g2d.setPaint(gp);
					g2d.fillRect(0, 0, w, h);
				}
			}
		};
		mainPanel.setLayout(new BorderLayout());

		// Título
		JLabel titleLabel = new JLabel("Gestión de Historiales Médicos", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel central con botones
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Panel para los botones con fondo semi-transparente
		JPanel buttonsContainer = new JPanel(new GridLayout(3, 1, 10, 10));
		buttonsContainer.setBackground(new Color(255, 255, 255, 200));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JButton btnListar = UIUtils.createStyledButton("Listar Pacientes", UIUtils.SECONDARY_COLOR);
		btnListar.addActionListener(e -> listarPacientes());

		JButton btnConsultar = UIUtils.createStyledButton("Consultar Historial", UIUtils.PRIMARY_COLOR);
		btnConsultar.addActionListener(e -> consultarHistorialPaciente());

		JButton btnActualizar = UIUtils.createStyledButton("Actualizar Historial", UIUtils.SUCCESS_COLOR);
		btnActualizar.addActionListener(e -> actualizarHistorial());

		JButton btnAgregarImagen = UIUtils.createStyledButton("Agregar Imagen Médica", UIUtils.WARNING_COLOR);
		btnAgregarImagen.addActionListener(e -> agregarImagenMedica());

		buttonsContainer.add(btnListar);
		buttonsContainer.add(btnConsultar);
		buttonsContainer.add(btnActualizar);
		buttonsContainer.add(btnAgregarImagen);

		centerPanel.add(buttonsContainer);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botón de volver
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnVolver = UIUtils.createStyledButton("Volver al Menú Principal", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal("Enfermero");
		});
		bottomPanel.add(btnVolver);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
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
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		// Título
		JLabel titleLabel = new JLabel("Gestión de Historiales Médicos", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(UIUtils.PRIMARY_COLOR);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel central con botones
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel buttonsContainer = new JPanel(new GridLayout(3, 1, 10, 10));
		buttonsContainer.setBackground(new Color(255, 255, 255, 200));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JButton btnListarPacientes = UIUtils.createStyledButton("Listar Pacientes", UIUtils.PRIMARY_COLOR);
		btnListarPacientes.addActionListener(e -> listarPacientes());

		JButton btnListarMedicos = UIUtils.createStyledButton("Listar Medicos", UIUtils.PRIMARY_COLOR);
		btnListarMedicos.addActionListener(e -> listarEmpleados());
		JButton btnCrear = UIUtils.createStyledButton("Crear Receta", UIUtils.SECONDARY_COLOR);
		btnCrear.addActionListener(e -> crearReceta());

		JButton btnListar = UIUtils.createStyledButton("Listar Recetas", UIUtils.WARNING_COLOR);
		btnListar.addActionListener(e -> listarRecetas());

		JButton btnConsultar = UIUtils.createStyledButton("Consultar Receta", UIUtils.SUCCESS_COLOR);
		btnConsultar.addActionListener(e -> consultarReceta());

		buttonsContainer.add(btnListarPacientes);
		buttonsContainer.add(btnListarMedicos);
		buttonsContainer.add(btnCrear);
		buttonsContainer.add(btnListar);
		buttonsContainer.add(btnConsultar);

		centerPanel.add(buttonsContainer);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		JButton btnVolver = UIUtils.createStyledButton("Volver al Menú Principal", UIUtils.DANGER_COLOR);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal("Medico");
		});

		bottomPanel.add(btnVolver);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
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
