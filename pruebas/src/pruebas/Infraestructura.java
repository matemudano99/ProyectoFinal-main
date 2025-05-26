package pruebas;

//Clase Infraestructura
class Infraestructura {
	private String id;
	private TipoInfraestructura tipo;
	private int numero;
	private boolean desinfectado;

	public Infraestructura(String id, TipoInfraestructura tipo, int numero, boolean desinfectado) {
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.desinfectado = desinfectado;
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

	public boolean isDesinfectado() {
		return desinfectado;
	}

	public void setDesinfectado(boolean desinfectado) {
		this.desinfectado = desinfectado;
	}
}
