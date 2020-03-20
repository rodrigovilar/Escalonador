package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;

public class EscalonadorFifo extends EscalonadorBase {
	
	public EscalonadorFifo () {
		
		this.ListaDeProcessos = new ArrayList<Processo>();
		this.tipoEscalonador = TipoEscalonador.Fifo;
		this.listaDeProcessosBloqueados = new ArrayList<Processo>();
		this.listaDeProcessosRetomados = new ArrayList<>();
		this.quantum = 0;
	}

	public EscalonadorFifo(int quantum) {
		
		if (quantum <= 0) 
			throw new EscalonadorException();
		
		if (rodando != null) 
			throw new EscalonadorException();  
		
		this.ListaDeProcessos = new ArrayList<Processo>();
		this.tipoEscalonador = TipoEscalonador.Fifo;
		this.listaDeProcessosBloqueados = new ArrayList<Processo>();
		this.quantum = quantum;
	}
	
	
}
