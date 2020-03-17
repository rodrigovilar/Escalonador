package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class Fifo extends Escalonador {
	
		public Fifo () {
			this.listaBloqueados = new ArrayList<>();
			this.listaRetomar = new ArrayList<>();
			this.fila = new ArrayList<>();
			this.quantum = 0;
			this.tipoEscalonador = TipoEscalonador.Fifo;
		}

		public Fifo(int quantum) {
			if (quantum <= 0) {
				throw new EscalonadorException();
			}
			this.listaBloqueados = new ArrayList<Processo>();
			this.quantum = quantum;
			this.fila = new ArrayList<Processo>();
			this.tipoEscalonador = TipoEscalonador.MaisCurtoPrimeiro;
		}



		public void tick() {
	        tick++;

	        if (rodando != null) {
	            rodando.addTickRodando();
	        }
	        
	        if(rodando != null && (rodando.getTickRodando() >= rodando.getPrioridade()) ){
	        	if (rodando.getTickRodando() >= rodando.getTemp())  {
	        		finalizandoProcesso(rodando.getNome());
	        		
	        	}
	        	else if(rodando.getTickRodando() == rodando.getTemp()){
	        		rodando.addTickRodando();
	        	}
	        }

	        
	        if (this.rodando == null && !fila.isEmpty()) {
	            adicionarProcessoRodando();// chama o metodo para adicionar um processo a lista de rodando
	        }
	        
	        if (this.rodando == null && fila.isEmpty()) {
	            adicionarProcessoRodando();// chama o metodo para adicionar um processo a lista de rodando
	        }	       
	        

	        

	    }

		public void trocaRodandoParaFila() {

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
		

		
		public void retomandoProcesso(List<Processo> retomar) {
			
		}
	

}
