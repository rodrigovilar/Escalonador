package br.ufpb.dcx.aps.escalonador;

import java.util.List;

public abstract class Escalonador {

    protected int tick;
    protected int quantum;
    protected int tickProximoFila;

    protected List<Processo> fila;// Lista de processos na fila
    protected List<Processo> listaBloqueados;
    protected List<Processo> listaRetomar;

    protected Processo rodando;// Lista de processos rodando

    protected boolean finalizado;
    protected boolean processoBloqueado;
    protected boolean retomar = false;

    protected String nomeProcessoFinalizado;
    protected String nomeProcessoBloquado;
    protected String processoRetomar;

    public String getStatus() {
        return "Escalonador RoundRobin;Processos: {};Quantum: 3;Tick: " + tick;
    }

    public void tick() {
    }

    public void adicionarProcesso(String nomeProcesso) {
    }

    public void adicionarProcesso(String nomeProcesso, int prioridade) {
    }

    public void finalizarProcesso(String nomeProcesso) {
    }

    public void bloquearProcesso(String nomeProcesso) {
    }

    public void retomarProcesso(String nomeProcesso) {

    }

    public void adicionarProcessoTempoFixo(String string, int duracao) {

    }

}
