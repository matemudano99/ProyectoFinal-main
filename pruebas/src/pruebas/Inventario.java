package pruebas;

import java.util.ArrayList;
import java.util.List;

class Inventario {
	private List<String> medicamentos;
	private List<String> equipoMedico;
	private List<String> suministros;

	public Inventario() {
		this.medicamentos = new ArrayList<>();
		this.equipoMedico = new ArrayList<>();
		this.suministros = new ArrayList<>();
	}

	// Getters y setters
	public List<String> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<String> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<String> getEquipoMedico() {
		return equipoMedico;
	}

	public void setEquipoMedico(List<String> equipoMedico) {
		this.equipoMedico = equipoMedico;
	}

	public List<String> getSuministros() {
		return suministros;
	}

	public void setSuministros(List<String> suministros) {
		this.suministros = suministros;
	}
}