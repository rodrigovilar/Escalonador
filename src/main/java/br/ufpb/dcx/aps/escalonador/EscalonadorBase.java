package br.ufpb.dcx.aps.escalonador;

import java.util.Collections;
import java.util.List;


public class EscalonadorBase {
	protected int tick, quantum, tickProximoFila;
	protected List<Processo> fila;
	protected List<Processo> listaBloqueados;
	protected List<Processo> listaRetomar;
	protected Processo rodando;
	protected boolean finalizado, processoBloqueado, retomar = false;
	protected String nomeProcessoFinalizado, nomeProcessoBloquado, processoRetomar;
	protected TipoEscalonador tipoEscalonador;

	
	public String getStatus() {
		return null;
		
	}

	public void adicionarProcessoRodando() {

	}

	public void finalizarProcesso(String nomeProcesso) {

	}

	public void bloquearProcesso(String nomeProcesso) {

		
	}

	public void retomarProcesso(String nomeProcesso) {

		
	}

	public Boolean existsProcessoByName(String nome) {
		return null;
		
	}

	public boolean isBloqueado(String p) {
		return false;
		
	}

	public boolean isRodando(String p) {
		return false;
		
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {

		
	}
}

	