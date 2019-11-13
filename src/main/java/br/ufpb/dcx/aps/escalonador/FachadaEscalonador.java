package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;

public class FachadaEscalonador {

    private TipoEscalonador tipoEscalonador;
    private int quantum;
    private int quantumAtual;
	private int tick;
	private Processo rodando;
	private ArrayList<Processo> fila = new ArrayList<>();

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
	    this.tipoEscalonador = tipoEscalonador;
        this.tick = 0;
        this.quantumAtual = 0;
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
	    this.tipoEscalonador = roundrobin;
	    this.quantum = quantum;
	    this.tick = 0;
	}

	public String getStatus() {
		if(this.fila.isEmpty()) {
			return "Escalonador RoundRobin;Processos: {};Quantum: 3;Tick: " + this.tick;
		} else if((this.tick == 0) && (!fila.isEmpty()) ) {
			return "Escalonador RoundRobin;Processos: {Fila: " + this.fila.toString() + "};Quantum: 3;Tick: " + this.tick;
		} else if((this.tick > 0) && (!fila.isEmpty()) ) {
			return "Escalonador RoundRobin;Processos: {Rodando: " + this.fila.get(0).getName() + "};Quantum: 3;Tick: " + this.tick;
		} else  {
			return null;
		}
	}

	public void tick() {
		tick++;
		if(this.rodando != null && this.rodando.getTickFinal() != 0 &&this.rodando.getTickFinal() < (this.tick)){
			this.rodando = null;
			this.fila.remove(0);
        }else if(this.rodando == null && this.fila.size() > 0){
			this.rodando = this.fila.get(0);
		}
	}

	public void adicionarProcesso(String nomeProcesso) {
		Processo p = new Processo(nomeProcesso, this.tick);
		this.fila.add(p);
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
		for(Processo p : this.fila){
			if(p.getName().equalsIgnoreCase(nomeProcesso)){
				p.setTickFinal(this.tick);
				break;
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
