package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class FachadaEscalonador {

	private int tick;
	private int quantum;
	private List<Processo> fila;// Lista de processos na fila
	private Processo rodando;// Lista de processos rodando
	private String status;// Status da operação
	private int tickProximoFila;
	private boolean finalizado;
	private String nomeProcessoFinalizado;
	private List<Processo> listaBloqueados;
	private boolean processoBloqueado;
	private List <Processo> processosRetomar = new ArrayList<Processo>();
	private String nomeProcessoBloquado;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		this.listaBloqueados = new ArrayList<Processo>();
		this.fila = new ArrayList<Processo>();
		this.quantum = 3;
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		if (quantum <= 0) {
			throw new EscalonadorException();
		}
		this.listaBloqueados = new ArrayList<Processo>();
		this.quantum = quantum;
		this.fila = new ArrayList<Processo>();
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
		
		if (!listaBloqueados.isEmpty() && processoBloqueado == true) {
			status += ", Bloqueados: " + this.listaBloqueados.toString();
		}
		
		return status += "};Quantum: " + this.quantum + ";Tick: " + tick;
	}

	public void tick() {
		tick++;

		if (finalizado) {
			finalizandoProcesso(this.nomeProcessoFinalizado);
			finalizado = false;
		}

		if (rodando != null) {
			rodando.addTickRodando();
		}
		
		if (rodando != null && rodando.getTickRodando() >= quantum && tickProximoFila < tick) {
			trocaRodandoParaFila();// chama o metodo para trocar o processo que esta rodando para fila
		}

		if (this.rodando == null) {
			adicionarProcessoRodando();// chama o metodo para adicionar um processo a lista de rodando
		}

		if (!listaBloqueados.isEmpty()) {
			processoBloqueado= true;
			trocaRodandoParaFila();
			adicionarProcessoRodando();
		}
		
		if (!processosRetomar.isEmpty()) {
			
			trocaRodandoParaFila();
			adicionarProcessoRodando();
			retomandoProcesso();

			

		}


	}
	
	public void adicionarProcesso(String nomeProcesso) {
		if (this.quantum >= 0) {
			Processo processo = new Processo(nomeProcesso, tick);// Cria um processo
			tickProximoFila = quantum + processo.getTickInicial();
			fila.add(processo);// Adiciona o processo na fila

		}
	}

	public void adicionarProcessoRodando() {
		if (!fila.isEmpty()) {// Só entra se a fila não estiver vazia
			for (Processo f : fila) {// Varre a lista de fila
				rodando = f;// adiciona o nome do processo na variavel
				fila.remove(f);
				break;
			}
		}
	}

	public void trocaRodandoParaFila() {
		boolean bloqueado= false;
		
		for (Processo p : listaBloqueados) {
			if (rodando.getNome().equals(p.getNome())) {
				bloqueado = true;
			}
		}
		if (rodando != null && bloqueado==false) {
			fila.add(rodando);
			rodando = null;
		}
	}
	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
		this.finalizado = true;
		this.nomeProcessoFinalizado = nomeProcesso;
	}

	public void finalizandoProcesso(String nomeProcesso) {
		if (rodando != null) {
			if (rodando.getNome().equals(nomeProcesso)) {
				rodando = null;
			} else {
				for (Processo f : fila) {
					if (f.getNome().equals(nomeProcesso)) {
						fila.remove(f);
						break;
					}
				}
			}
		}
	}

	public void bloquearProcesso(String nomeProcesso) {
		if (rodando != null) {
			if (rodando.getNome().equals(nomeProcesso)) {
				listaBloqueados.add(rodando);
				nomeProcessoBloquado = rodando.getNome();
			}	
		}
	}

	public void retomarProcesso(String nomeProcesso) {
		for (Processo p: listaBloqueados) {
			if (p.getNome().equals(nomeProcesso)) {
				processosRetomar.add(p);
				break;
			}
			
		}
	
	}
	
	
	public void retomandoProcesso() {
		
		for(Processo p: processosRetomar) {
			if(p.getNome().equals(nomeProcessoBloquado)) {
				fila.add(p);
				processosRetomar.remove(p);
				listaBloqueados.remove(p);
				break;
			}
		}

	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
	}

}
