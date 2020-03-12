package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class BloquearProcessoCommand extends Command {

    private String nomeProcesso;

    public BloquearProcessoCommand(String nomeProcesso){
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public String executar() {
        getEscalonador().bloquearProcesso(nomeProcesso);
        return null;
    }
}
