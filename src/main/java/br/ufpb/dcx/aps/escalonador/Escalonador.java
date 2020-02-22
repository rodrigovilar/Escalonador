package br.ufpb.dcx.aps.escalonador;

import java.util.Collections;
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
    protected TipoEscalonador tipoEscalonador;

    public TipoEscalonador getTipo() {
    	return tipoEscalonador;
    }

    public String getStatus() {
        String status = "Escalonador " + getTipo() + ";Processos: {";

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
    public void adicionarProcessoRodando() {
        if (!fila.isEmpty()) {// Só entra se a fila não estiver vazia
            for (Processo f : fila) {// Varre a lista de fila
                rodando = f;// adiciona o nome do processo na variavel
                fila.remove(f);
                break;
            }
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

    public void bloquearProcesso(String nomeProcesso) {
        if (!existsProcessoByName(nomeProcesso) || !isRodando(nomeProcesso)) {
            throw new EscalonadorException();
        }
        this.processoBloqueado = true;
        nomeProcessoBloquado = nomeProcesso;
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

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
        if(existsProcessoByName(nomeProcesso) || nomeProcesso == null || duracao <= 0){
            throw new EscalonadorException();
        }else{
            Processo processo = new Processo(duracao, this.tick, nomeProcesso);
            fila.add(processo);
            Collections.sort(fila);
        }
    }
}
