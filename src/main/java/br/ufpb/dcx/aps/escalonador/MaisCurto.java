package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaisCurto extends Escalonador {

	public MaisCurto() {
		this.listaBloqueados = new ArrayList<>();
		this.listaRetomar = new ArrayList<>();
		this.fila = new ArrayList<>();
		this.quantum = 0;
	}

	public MaisCurto(int quantum) {
		if (quantum <= 0) {
			throw new EscalonadorException();
		}
		this.listaBloqueados = new ArrayList<Processo>();
		this.quantum = quantum;
		this.fila = new ArrayList<Processo>();
	}

	public String getStatus() {
		String status = "Escalonador " + "MaisCurtoPrimeiro" + ";Processos: {";

		if (rodando != null) {
			status += "Rodando: " + rodando.toString();
		}

		if (!fila.isEmpty() && rodando == null) {
			status += "Fila: " + this.fila.toString();
		} else if (!fila.isEmpty() && rodando != null) {
			status += ", Fila: " + this.fila.toString();
		}

		if (!listaBloqueados.isEmpty() && processoBloqueado == true && rodando != null) {
			status += ", Bloqueados: " + this.listaBloqueados.toString();
		}

		if (!listaBloqueados.isEmpty() && processoBloqueado == true && rodando == null) {
			status += "Bloqueados: " + this.listaBloqueados.toString();
		}

		return status += "};Quantum: " + this.quantum + ";Tick: " + tick;
	}

	public void tick() {
		tick++;

		if (finalizado) {
			finalizandoProcesso(this.nomeProcessoFinalizado);
			finalizado = false;
		}

		if (processoBloqueado) {
			bloqueandoProcesso(nomeProcessoBloquado);
		}

		if (retomar) {
			retomandoProcesso(listaRetomar);
			retomar = false;
			Collections.sort(fila);
		}

		if (rodando != null) {
			rodando.addTickRodando();
		}

		if (rodando != null && rodando.getTickRodando() >= quantum && tickProximoFila < tick) {
			trocaRodandoParaFila();// chama o metodo para trocar o processo que esta rodando para fila
		}

		if (this.rodando == null && !fila.isEmpty()) {
			adicionarProcessoRodando();// chama o metodo para adicionar um processo a lista de rodando
		}

		if (!listaBloqueados.isEmpty() && retomar == false) {
			processoBloqueado = true;
			trocaRodandoParaFila();
			adicionarProcessoRodando();
		}

		if (!fila.isEmpty() && fila.get(0).getPrioridade() < rodando.getPrioridade()) {
			if (rodando != null) {
				trocaRodandoParaFila();
				adicionarProcessoRodando();
				Collections.sort(fila);
			}
		}

	}

	public void adicionarProcesso(String nomeProcesso) {

		if (existsProcessoByName(nomeProcesso)) {
			throw new EscalonadorException();
		}

		if (nomeProcesso == null) {
			throw new EscalonadorException();
		}

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
		boolean bloqueado = false;

		for (Processo p : listaBloqueados) {
			if (rodando.getNome().equals(p.getNome())) {
				bloqueado = true;
				break;
			}
		}

		if (rodando != null && bloqueado == false) {
			if (!fila.isEmpty()) {
				if (rodando.getPrioridade() < fila.get(0).getPrioridade()) {
					return;
				}
			}
			rodando.setTickRodando(0);
			fila.add(rodando);
			rodando = null;
		} else if (rodando != null && bloqueado) {
			rodando.setTickRodando(0);
			rodando = null;
		}
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {

		if (existsProcessoByName(nomeProcesso)) {
			throw new EscalonadorException();
		}

		if (nomeProcesso == null) {
			throw new EscalonadorException();
		}

		if (this.quantum >= 0) {
			Processo processo = new Processo(nomeProcesso, tick, prioridade);// Cria um processo
			tickProximoFila = quantum + processo.getTickInicial();
			fila.add(processo);// Adiciona o processo na fila
			Collections.sort(fila);
		}
	}

	public void finalizarProcesso(String nomeProcesso) {

		if (!existsProcessoByName(nomeProcesso) && !isRodando(nomeProcesso)) {
			throw new EscalonadorException();
		} else {
			this.finalizado = true;
			this.nomeProcessoFinalizado = nomeProcesso;
		}
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
		if (!existsProcessoByName(nomeProcesso) || !isRodando(nomeProcesso)) {
			throw new EscalonadorException();
		}
		this.processoBloqueado = true;
		nomeProcessoBloquado = nomeProcesso;
	}

	public void bloqueandoProcesso(String nomeProcessoBloq) {
		this.processoBloqueado = false;
		if (rodando != null) {
			if (rodando.getNome().equals(nomeProcessoBloq)) {
				listaBloqueados.add(rodando);
				this.nomeProcessoBloquado = rodando.getNome();
			}
		}
	}

	public void retomarProcesso(String nomeProcesso) {

		if (!isBloqueado(nomeProcesso) || !existsProcessoByName(nomeProcesso)) {
			throw new EscalonadorException();
		}

		this.retomar = true;
		for (Processo p : listaBloqueados) {
			if (p.getNome().equals(nomeProcesso)) {
				listaRetomar.add(p);
			}
		}
	}

	public void retomandoProcesso(List<Processo> retomar) {

		while (!retomar.isEmpty()) {
			for (int i = 0; i < listaBloqueados.size(); i++) {
				for (int j = 0; j < retomar.size(); j++) {
					if (listaBloqueados.get(i).getNome().equals(retomar.get(j).getNome())) {
						fila.add(listaBloqueados.get(i));
						listaBloqueados.remove(i);
						retomar.remove(j);
					}
				}
			}
		}
	}

	public Boolean existsProcessoByName(String nome) {
		if (!fila.isEmpty()) {
			for (Processo p : fila) {
				if (p.getNome().equals(nome)) {
					return true;
				}
			}
		}
		if (!listaBloqueados.isEmpty()) {
			for (Processo b : listaBloqueados) {
				if (b.getNome().equals(nome)) {
					return true;
				}
			}
		}
		if (rodando != null) {
			if (rodando.getNome() == nome) {
				return true;
			}
		}

		return false;
	}

	public boolean isBloqueado(String p) {
		if (!listaBloqueados.isEmpty()) {
			for (Processo q : listaBloqueados) {
				if (q.getNome().equals(p)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isRodando(String p) {
		if (rodando != null) {
			if (rodando.getNome().equals(p)) {
				return true;
			}
		}
		return false;
	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
	}

}