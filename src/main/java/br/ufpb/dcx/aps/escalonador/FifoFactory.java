package br.ufpb.dcx.aps.escalonador;

public class FifoFactory  implements AbstractFactory {
	

		@Override
		public Escalonador criarEscalonador() {
			return new Fifo();
		}

		@Override
		public Escalonador criarEscalonador(int quantum) {
			return new Fifo(quantum);
		}

	


}
