package br.ufpb.dcx.aps.escalonador;

public class FifoBase  implements CriadorTipo {
	
	@Override
		public EscalonadorBase criarEscalonador() {
			return new EscalonadorFifo();
		}
		@Override
		public EscalonadorBase criarEscalonador(int quantum) {
			return new EscalonadorFifo(quantum);
		}
	
}
