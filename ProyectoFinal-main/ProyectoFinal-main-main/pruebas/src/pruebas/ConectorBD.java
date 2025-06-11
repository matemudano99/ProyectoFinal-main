package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ConectorBD {

	private static Connection conn = null;

	public ConectorBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no instalado correctamente");
		}

		// Datos de conexión a la base de datos,
		String url = "jdbc:mysql://localhost:3306/definitiva";
		String login = "root";
		String password = "";

		try {
			conn = DriverManager.getConnection(url, login, password);
			if (conn != null) {
				System.out.println("Conexión a la base de datos de Carlos Haya");
			}
		} catch (SQLException e) {
			System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
		}

	}

	public Connection getConn() {
		return conn;
	}

	// Metodo para desconectar
	public void desconectar() {
		conn = null;
	}
	
	
	
	
	
	
	public static boolean validateUser(String dni, String password, String rol) {
	    try {
	        if (conn != null) {
	            String sql = "SELECT * FROM personal WHERE dni = ? AND password = ? AND rol = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);

	            pstmt.setString(1, dni);
	            pstmt.setString(2, password);
	            pstmt.setString(3, rol);

	            ResultSet rs = pstmt.executeQuery();

	            boolean isValid = rs.next(); // Si hay al menos un resultado, el usuario es válido

	            rs.close();
	            pstmt.close();

	            return isValid;
	        } else {
	            System.out.println("Conexión no establecida.");
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	//No se esta usando
	public static int createUser(String dni, String password, String role) {
		int resultado = 0;
		if (conn != null) {
			try {
				String sql = "INSERT INTO personal (dni, password, rol) VALUES (?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				//resultado = orden.executeUpdate(sql);
				 pstmt.setString(1, dni);
		            pstmt.setString(2, password);
		            pstmt.setString(3, role);

		            pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	
	
	
	
	
	
	public static  void savePaciente(Paciente paciente) {
       
        try {
			if (conn != null) {
				
      
			try {
				 String query = "INSERT INTO pacientes (id, dni, nombre, apellidos, genero, telefono, email, direccion, obra_social) "
			             +
			             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			    PreparedStatement pstmt = conn.prepareStatement(query);

			    pstmt.setString(1, paciente.getId());
			    pstmt.setString(2, paciente.getDni());
			    pstmt.setString(3, paciente.getNombre());
			    pstmt.setString(4, paciente.getApellidos());
			    pstmt.setString(5, paciente.getGenero().toString());
			    pstmt.setInt(6, paciente.getTelefono());
			    pstmt.setString(7, paciente.getEmail());
			    pstmt.setString(8, paciente.getDireccion());
			    pstmt.setString(9, paciente.getObraSocial());

			    pstmt.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace();
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static List<Paciente> getAllPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String query = "SELECT * FROM pacientes";

        if (conn!= null) {
        try (
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                Genero genero = Genero.valueOf(rs.getString("genero"));
                int telefono = rs.getInt("telefono");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String obraSocial = rs.getString("obra_social");

                Paciente paciente = new Paciente(dni, nombre, apellidos, genero, telefono, email, obraSocial, null,
                        false);
                paciente.setId(id);
                paciente.setDireccion(direccion);
                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        	
        }
        return pacientes;
    }

    public static void updatePaciente(Paciente paciente) {
        String query = "UPDATE pacientes SET dni = ?, nombre = ?, apellidos = ?, genero = ?, " +
                "telefono = ?, email = ?, direccion = ?, obra_social = ? WHERE id = ?";
        if (conn!= null) {
        	
      
        try (
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, paciente.getDni());
            pstmt.setString(2, paciente.getNombre());
            pstmt.setString(3, paciente.getApellidos());
            pstmt.setString(4, paciente.getGenero().toString());
            pstmt.setInt(5, paciente.getTelefono());
            pstmt.setString(6, paciente.getEmail());
            pstmt.setString(7, paciente.getDireccion());
            pstmt.setString(8, paciente.getObraSocial());
            pstmt.setString(9, paciente.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }

    public static  void deletePaciente(String id) {
        String query = "DELETE FROM pacientes WHERE id = ?";

        try (
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    }
	
	
	
	
	
	
	
	
	
	
