package proyecto;

public class Infraestructura {
	private TipoInfraestructura tipoInfraestructura;
	private int numero;
	private boolean disponible;

	public Infraestructura(TipoInfraestructura tipoInfraestructura, int numero, boolean disponible) {
		this.tipoInfraestructura = tipoInfraestructura;
		this.numero = numero;
		this.disponible = disponible;
	}

	public TipoInfraestructura getTipoInfraestructura() {
		return tipoInfraestructura;
	}

	public void setTipoInfraestructura(TipoInfraestructura tipoInfraestructura) {
		this.tipoInfraestructura = tipoInfraestructura;
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
	
	public void mostrarDetalles() {
		System.out.println("Tipo de Sala: " + tipoInfraestructura);
		System.out.println("Numero de Sala: " + numero);
		System.out.println("Disponibilidad de Sala: " + disponible);
	}

}
