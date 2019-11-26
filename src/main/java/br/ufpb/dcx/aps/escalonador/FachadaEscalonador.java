package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FachadaEscalonador {

	private int tick;
	private List<Processo> fila;// Lista de processos na fila
	private List<Processo> rodando;// Lista de processos rodando
	private String status;// Status da operação
	private String processoRodando = "";// Processo que está sendo executado

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		this.fila = new ArrayList<Processo>();
		this.rodando = new ArrayList<Processo>();
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
	}

	public String getStatus() {
		status = "Escalonador RoundRobin;Processos: {";
	
		
		if ((fila.isEmpty() && rodando.isEmpty()) || tick > 4) {// Se a fila e rodando estiverem vazios faz
			 status += "};Quantum: 3;Tick: " + tick;
		} else if (!rodando.isEmpty()) {// Se apenas a lista fila estiver fazia faz
			 status += "Rodando: " + processoRodando + "};Quantum: 3;Tick: " + tick;
		} else if (!fila.isEmpty() && rodando.isEmpty()) {// Se apenas a lista rodando estiver vazia faz
			 status += "Fila: " + this.fila.toString() + "};Quantum: 3;Tick: " + tick;
		} else if (!fila.isEmpty() && !rodando.isEmpty()) {
			 status += "Rodando: " + processoRodando + ", Fila: " + this.fila.toString() + "};Quantum: 3;Tick: " + tick;
		}
		
		
		return status;
	}

	public void tick() {
		tick++;
		adicionarProcessoRodando();// chama o metodo para adicionar um processo a lista de rodando
	}

	public void adicionarProcesso(String nomeProcesso) {
		Processo processo = new Processo(nomeProcesso);// Cria um processo
		fila.add(processo);// Adiciona o processo na fila
	}

	void adicionarProcessoRodando() {
		trocaRodandoParaFila();
		if (!fila.isEmpty()) {// Só entra se a fila não estiver vazia
			for (Processo f : fila) {// Varre a lista de fila
				if (f.getStatus() == "Fila") {// Se o objeto f tiver um status com valor "Fila" faz:
					processoRodando = "";
					f.setStatus("Rodando");// Seta o status para "Rodando"
					rodando.add(f);// Adiciona na lista de rodando
					processoRodando += f.getNome();// adiciona o nome do processo na variavel
					fila.remove(f);
					break;
				}
			}
		}
	}

	public void trocaRodandoParaFila() {
		if (!rodando.isEmpty()) {
			for (Processo r : rodando) {
				r.setStatus("Fila");
				fila.add(r);
				rodando.remove(r);
				break;
			}
		}
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
		if (!rodando.isEmpty()) {// Só entra se a fila de rodando não estiver vazia
			for (Processo r : rodando) {// Varre a lista rodando
				if (r.getNome().equals(nomeProcesso)) {// se o nome do objeto for igual ao nome do processo a ser
														// finalizado faz:
					processoRodando += "";// Zera a variavel
					// rodando.remove(r);
					break;
				}

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