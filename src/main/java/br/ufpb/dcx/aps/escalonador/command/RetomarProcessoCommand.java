package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class RetomarProcessoCommand extends Command {

    private String nomeProcesso;

    public RetomarProcessoCommand(String nomeProcesso){
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public String executar() {
        getEscalonador().retomarProcesso(nomeProcesso);
        return null;
    }
}
