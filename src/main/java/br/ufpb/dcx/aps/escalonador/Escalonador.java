package br.ufpb.dcx.aps.escalonador;

import java.util.List;

public abstract class Escalonador {

    private int tick;
    private int quantum;
    private int tickProximoFila;

    private List<Processo> fila;// Lista de processos na fila
    private List<Processo> listaBloqueados;
    private List<Processo> listaRetomar;

    private Processo rodando;// Lista de processos rodando

    private boolean finalizado;
    private boolean processoBloqueado;
    private boolean retomar = false;

    private String nomeProcessoFinalizado;
    private String nomeProcessoBloquado;
    private String processoRetomar;

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
