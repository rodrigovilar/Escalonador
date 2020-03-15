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
	    }

		public void trocaRodandoParaFila() {
			
		}

		public void finalizandoProcesso(String nomeProcesso) {
			
		}
		public void retomandoProcesso(List<Processo> retomar) {

			
		}
	

}
