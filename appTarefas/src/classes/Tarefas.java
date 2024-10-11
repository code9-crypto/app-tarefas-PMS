package classes;

public class Tarefas {
	private String tarefas;
	private String solicitante;
	private String destino;
	private String data;
	
	public Tarefas(String tarefas, String solicitante, String destino, String data) {		
		this.tarefas = tarefas;
		this.solicitante = solicitante;
		this.destino = destino;
		this.data = data;
	}

	public String getTarefas() {
		return tarefas;
	}

	public void setTarefas(String tarefas) {
		this.tarefas = tarefas;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
