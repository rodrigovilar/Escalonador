package br.ufpb.dcx.aps.escalonador;

import br.ufpb.dcx.aps.escalonador.command.Command;

import java.util.Collections;
import java.util.List;


public class EscalonadorBase {

	protected int tick, quantum, Proximo;
	protected List<Processo> ListaDeProcessos;
	protected List<Processo> listaDeProcessosBloqueados;
	protected List<Processo> listaDeProcessosRetomados;
	protected Processo rodando;
	protected boolean finalizado, processoBloqueado, retomar = false;
	protected String nomeFinalizado, nomeProcessoBloquado, processoRetomar;
	protected TipoEscalonador tipoEscalonador;

	public void execute(Command command) {

		command.setEscalonador(this);
		command.executar();
	}

	public String getStatus() {

		System.err.println("getstauts >");

		String resultado = "Escalonador " + getTipo() + ";Processos: {";

		if (rodando != null)
			resultado += "Rodando: " + rodando.toString();

		if (!ListaDeProcessos.isEmpty() && rodando == null)
			resultado += "Fila: " + this.ListaDeProcessos.toString();

		if (!ListaDeProcessos.isEmpty() && rodando != null)
			resultado += ", Fila: " + this.ListaDeProcessos.toString();

		if (!listaDeProcessosBloqueados.isEmpty())
			if (processoBloqueado == true)
				if (rodando != null)
					resultado += ", Bloqueados: " + this.listaDeProcessosBloqueados.toString();

		if (!listaDeProcessosBloqueados.isEmpty())
			if (processoBloqueado == true)
				if (rodando == null)
					resultado += "Bloqueados: " + this.listaDeProcessosBloqueados.toString();

		resultado += "};Quantum: " + this.quantum + ";Tick: " + tick;
		System.out.println(resultado);
		return resultado;
	}

	public void tick() {

		tick = tick + (tick - (tick - 1));// Piada Interna entre os intregrantes
	}

	public void adicionarProcesso(String nomeProcesso) {

		if ((processoExiste(nomeProcesso)) || (nomeProcesso == null))
			throw new EscalonadorException();

		if (quantum >= 0) {

			Processo processo = new Processo(nomeProcesso, tick);
			Proximo = quantum + processo.getInicial();
			ListaDeProcessos.add(processo);
		}
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {

		if (processoExiste(nomeProcesso) || (nomeProcesso == null))
			throw new EscalonadorException();

		if (this.quantum >= 0) {

			Processo processo = new Processo(nomeProcesso, tick, prioridade);
			Proximo = quantum + processo.getInicial();
			ListaDeProcessos.add(processo);
			Collections.sort(ListaDeProcessos);
		}
	}

	public void adicionarProcessoRodando() {

		if (!ListaDeProcessos.isEmpty()) {
			for (Processo processo : ListaDeProcessos) {
				rodando = processo;
				ListaDeProcessos.remove(processo);
				break;
			}
		}
	}

	public void finalizarProcesso(String nomeProcesso) {

		if (!processoExiste(nomeProcesso) && !isRodando(nomeProcesso))
			throw new EscalonadorException();

		this.finalizado = true;
		this.nomeFinalizado = nomeProcesso;
	}

	public void bloquearProcesso(String nomeProcesso) {

		if (!processoExiste(nomeProcesso))
			throw new EscalonadorException();

		if (!isRodando(nomeProcesso))
			throw new EscalonadorException();

		this.processoBloqueado = true;
		nomeProcessoBloquado = nomeProcesso;
	}

	public void retomarProcesso(String nomeProcesso) {

		if (!bloquear(nomeProcesso))
			throw new EscalonadorException();

		if (!processoExiste(nomeProcesso))
			throw new EscalonadorException();

		this.retomar = true;

		for (Processo processo : listaDeProcessosBloqueados)
			if (processo.getNome().equals(nomeProcesso))
				listaDeProcessosRetomados.add(processo);
	}

	public Boolean processoExiste(String nome) {

		if (!ListaDeProcessos.isEmpty())
			for (Processo processo : ListaDeProcessos)
				if (processo.getNome().equals(nome))
					return true;

		if (!listaDeProcessosBloqueados.isEmpty())
			for (Processo processo : listaDeProcessosBloqueados)
				if (processo.getNome().equals(nome))
					return true;

		if (rodando != null)
			if (rodando.getNome() == nome)
				return true;

		return false;
	}

	public boolean bloquear(String processoEntrada) {

		if (!listaDeProcessosBloqueados.isEmpty())
			for (Processo processo : listaDeProcessosBloqueados)
				if (processo.getNome().equals(processoEntrada))
					return true;

		return false;
	}

	public boolean isRodando(String p) {

		if (rodando != null)
			if (rodando.getNome().equals(p))
				return true;

		return false;
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {

		if (processoExiste(nomeProcesso))
			throw new EscalonadorException();

		if ((nomeProcesso == null) && (duracao <= 0))
			throw new EscalonadorException();

		Processo processo = new Processo(duracao, this.tick, nomeProcesso);
		ListaDeProcessos.add(processo);

		if (getTipo() == TipoEscalonador.MaisCurtoPrimeiro)
			Collections.sort(ListaDeProcessos);
	}

	public TipoEscalonador getTipo() {
		return tipoEscalonador;
	}

	public void setTipo(TipoEscalonador tipo) {
		this.tipoEscalonador = tipo;
	}
}
