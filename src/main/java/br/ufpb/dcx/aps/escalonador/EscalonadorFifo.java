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
	public void tick() {
		
    	tick = tick + (tick - (tick - 1));//Piada Interna entre os intregrantes

        if (rodando != null) rodando.addtempo();      
        
        if(rodando != null) {
        	
        	if(rodando.getAtual() >= rodando.getPrioridade() && rodando.getAtual() >= rodando.getTempo()) {
        			finalizandoProcesso(rodando.getNome());
        			
        	 } else if (rodando.getAtual() == rodando.getTempo()){
        		 
        		rodando.addtempo();
        	} 
        }
        if (ListaDeProcessos.isEmpty() || this.rodando == null && !ListaDeProcessos.isEmpty()) 
        	adicionarProcessoRodando();
    }

	public void finalizandoProcesso(String nomeProcesso) {
		
		if (rodando != null && nomeProcesso.equals(rodando.getNome())) 
			rodando = null;
				
		for (Processo processo : ListaDeProcessos) {
			
			if (nomeProcesso.equals(processo.getNome())) {
				ListaDeProcessos.remove(processo); break;
				
			}else {
				continue;
			}
		} 
	 }	
}
	
	
}
