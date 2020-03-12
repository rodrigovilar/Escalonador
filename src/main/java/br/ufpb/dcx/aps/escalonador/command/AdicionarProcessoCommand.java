package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class AdicionarProcessoCommand implements Command {

    private Escalonador escalonador;
    private String nomeProcesso;
    private int prioridade;

    public AdicionarProcessoCommand(String nomeProcesso){
        this.nomeProcesso = nomeProcesso;
    }

    public AdicionarProcessoCommand(String nomeProcesso, int prioridade){
        this.nomeProcesso = nomeProcesso;
        this.prioridade = prioridade;
    }

    @Override
    public void execute() {
        escalonador.adicionarProcesso(nomeProcesso);
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }


}
