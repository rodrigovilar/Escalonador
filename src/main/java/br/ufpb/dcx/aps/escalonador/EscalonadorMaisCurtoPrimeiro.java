package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class EscalonadorMaisCurtoPrimeiro extends EscalonadorBase {
	
	public EscalonadorMaisCurtoPrimeiro() {

		this.ListaDeProcessos = new ArrayList<Processo>();
		this.tipoEscalonador = TipoEscalonador.MaisCurtoPrimeiro;
		this.listaDeProcessosBloqueados = new ArrayList<Processo>();
		this.listaDeProcessosRetomados = new ArrayList<Processo>();
		this.quantum = 0;
	}

	public EscalonadorMaisCurtoPrimeiro(int quantum) {

		if (quantum <= 0)
			throw new EscalonadorException();

		if (rodando != null)
			throw new EscalonadorException();

		this.ListaDeProcessos = new ArrayList<Processo>();
		this.tipoEscalonador = TipoEscalonador.MaisCurtoPrimeiro;
		this.listaDeProcessosBloqueados = new ArrayList<Processo>();
		this.quantum = quantum;
	}

	public void tick() {

		tick = tick + (tick - (tick - 1));// Piada Interna entre os intregrantes

		if (rodando != null)
			rodando.addtempo();

		if (rodando != null)
			if (rodando.getAtual() >= rodando.getTempo() && rodando.getAtual() >= rodando.getTempo())
				finalizandoProcesso(rodando.getNome());

		if (this.rodando == null && !ListaDeProcessos.isEmpty() || ListaDeProcessos.isEmpty())
			adicionarProcessoRodando();
	}

	public void trocaParaFila() {

		boolean bloqueado = false;

		for (Processo processo : listaDeProcessosBloqueados) {

			if (rodando.getNome().equals(processo.getNome()))
				bloqueado = true;
			break;
		}

		if (rodando != null && bloqueado == false && !ListaDeProcessos.isEmpty()) {

			if (ListaDeProcessos.get(0).getPrioridade() > rodando.getPrioridade())
				return;

			rodando.setAtual(0);
			ListaDeProcessos.add(rodando);
			rodando = null;

		} else if (rodando != null) {

			if (bloqueado)
				rodando.setAtual(0);
			rodando = null;
		}
	}

	public void finalizandoProcesso(String nomeProcesso) {

		if (rodando != null && rodando.getNome().equals(nomeProcesso)) {
			rodando = null;

		} else {
			for (Processo processo : ListaDeProcessos) {
				if (processo.getNome().equals(nomeProcesso))
					ListaDeProcessos.remove(processo);
				break;
			}
		}
	}

	public void retomandoProcesso(List<Processo> retomar) {

		while (!retomar.isEmpty()) {

			for (int i = 0; i < listaDeProcessosBloqueados.size(); i++) {

				for (int k = 0; k < retomar.size(); k++) {

					if (retomar.get(k).getNome().equals(listaDeProcessosBloqueados.get(i).getNome())) {
						ListaDeProcessos.add(listaDeProcessosBloqueados.get(i));
						listaDeProcessosBloqueados.remove(i);
						retomar.remove(k);
						break;
					}
				}
			}
		}
	}
}