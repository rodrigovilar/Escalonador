package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class BloquearProcessoCommand implements Command {

    private Escalonador escalonador;
    private String nomeProcesso;

    public BloquearProcessoCommand(Escalonador escalonador, String nomeProcesso){
        this.escalonador = escalonador;
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public void execute() {
        escalonador.bloquearProcesso(nomeProcesso);
    }
}
