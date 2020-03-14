package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin extends Escalonador{

    public RoundRobin() {
            this.listaBloqueados = new ArrayList<>();
            this.listaRetomar = new ArrayList<>();
            this.fila = new ArrayList<>();
            this.quantum = 3;
            this.tipoEscalonador = TipoEscalonador.RoundRobin;
    }

    public RoundRobin(int quantum) {
        if (quantum <= 0) {
            throw new EscalonadorException();
        }
        this.listaBloqueados = new ArrayList<Processo>();
        this.quantum = quantum;
        this.fila = new ArrayList<Processo>();
        this.tipoEscalonador = TipoEscalonador.RoundRobin;
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
            rodando.setTickRodando(0);
            fila.add(rodando);
            rodando = null;
        } else if (rodando != null && bloqueado) {
            rodando.setTickRodando(0);
            rodando = null;
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


    public void bloqueandoProcesso(String nomeProcessoBloq) {
        this.processoBloqueado = false;
        if (rodando != null) {
            if (rodando.getNome().equals(nomeProcessoBloq)) {
                listaBloqueados.add(rodando);
                this.nomeProcessoBloquado = rodando.getNome();
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
}
