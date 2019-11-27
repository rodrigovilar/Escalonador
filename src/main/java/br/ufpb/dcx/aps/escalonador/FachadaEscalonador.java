package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FachadaEscalonador {

	private int tick;
	private int quantum;
	private List<Processo> fila;// Lista de processos na fila
	private Processo rodando;// Lista de processos rodando
	private String status;// Status da operação

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		this.fila = new ArrayList<Processo>();
		this.quantum = 3;
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		this.quantum = quantum;
	}

	public String getStatus() {
		status = "Escalonador RoundRobin;Processos: {";

		if (rodando != null) {
			status += "Rodando: " + rodando.toString();
		}

		if (!fila.isEmpty() && rodando == null) {
			status += "Fila: " + this.fila.toString();
		} else if (!fila.isEmpty() && rodando != null) {
			status += ", Fila: " + this.fila.toString();
		}

		status += "};Quantum: " + this.quantum + ";Tick: " + tick;

		return status;
	}

	public void tick() {
		tick++;
		
		if(rodando != null) {
			rodando.addTickRodando();
		}
		
		if (rodando != null  && rodando.getTickRodando() == quantum) {
			trocaRodandoParaFila();// chama o metodo para trocar o processo que esta rodando para fila
		}

		if (this.rodando == null) {
			adicionarProcessoRodando();// chama o metodo para adicionar um processo a lista de rodando
		}

	}

	public void adicionarProcesso(String nomeProcesso) {
		Processo processo = new Processo(nomeProcesso);// Cria um processo
		fila.add(processo);// Adiciona o processo na fila
	}

	void adicionarProcessoRodando() {
		if (!fila.isEmpty()) {// Só entra se a fila não estiver vazia
			for (Processo f : fila) {// Varre a lista de fila
				if (f.getStatus() == Status.FILA) {// Se o objeto f tiver um status com valor "Fila" faz:
					f.setStatus(Status.RODANDO);// Seta o status para "Rodando"
					rodando = f;// adiciona o nome do processo na variavel
					fila.remove(f);
					break;
				}
			}
		}
	}

	public void trocaRodandoParaFila() {
		if (rodando != null) {
			rodando.setStatus(Status.FILA);
			fila.add(rodando);
			rodando = null;
		}
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
		if (rodando != null) {// Só entra se a fila de rodando não estiver vazia
			if (rodando.getNome().equals(nomeProcesso)) {
				rodando = null;

			}
		}
	}

	public void bloquearProcesso(String nomeProcesso) {
	}

	public void retomarProcesso(String nomeProcesso) {
	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
	}

}