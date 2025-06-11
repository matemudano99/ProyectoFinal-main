package pruebas;

//Clase Infraestructura
public class Infraestructura {
	private String id;
	private TipoInfraestructura tipo;
	private int numero;
	private boolean disponible;

	public Infraestructura(String id, TipoInfraestructura tipo, int numero, boolean disponible) {
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.disponible = disponible;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoInfraestructura getTipo() {
		return tipo;
	}

	public void setTipo(TipoInfraestructura tipo) {
		this.tipo = tipo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
