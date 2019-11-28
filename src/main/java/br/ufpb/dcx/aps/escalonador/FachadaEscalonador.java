package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FachadaEscalonador {

	private int tick;
	private int quantum;
	private List<Processo> fila;// Lista de processos na fila
	private Processo rodando;// Lista de processos rodando
	private String status;// Status da operação
	private int tickProximoFila;
    private boolean finalizado;
    private String nomeProcessoFinalizado;



	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		this.fila = new ArrayList<Processo>();
		this.quantum = 3;
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		this.quantum = quantum;
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


		return status += "};Quantum: " + this.quantum + ";Tick: " + tick;
	}

    public void tick() {
        tick++;

        if(finalizado) {
            finalizandoProcesso(rodando.getNome());
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

    }

	public void adicionarProcesso(String nomeProcesso) {
		Processo processo = new Processo(nomeProcesso,tick);// Cria um processo
		tickProximoFila = quantum+processo.getTickInicial();
		fila.add(processo);// Adiciona o processo na fila
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
		if (rodando != null) {
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
