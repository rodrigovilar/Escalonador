package br.ufpb.dcx.aps.escalonador.comando;

import br.ufpb.dcx.aps.escalonador.FachadaEscalonador;

public class ComandoTick implements ComandoEscalonador {
	
	private FachadaEscalonador escalonador;

	public ComandoTick(FachadaEscalonador escalonador) {
		this.escalonador = escalonador;
	}

	@Override
	public String executar() {
		return null;
	}

}
