package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class FinalizarProcessoCommand implements Command {

    private Escalonador escalonador;
    private String nomeProcesso;

    public FinalizarProcessoCommand(String nomeProcesso){
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public void execute() {
        escalonador.finalizarProcesso(nomeProcesso);
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }
}
