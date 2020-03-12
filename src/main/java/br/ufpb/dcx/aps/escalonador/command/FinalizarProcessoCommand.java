package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class FinalizarProcessoCommand extends Command {

    private String nomeProcesso;

    public FinalizarProcessoCommand(String nomeProcesso){
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public String executar() {
        getEscalonador().finalizarProcesso(nomeProcesso);
        return null;
    }
}
