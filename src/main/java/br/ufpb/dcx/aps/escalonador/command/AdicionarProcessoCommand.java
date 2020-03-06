package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class AdicionarProcessoCommand implements Command {

    private Escalonador escalonador;
    private String nomeProcesso;
    private int prioridade;

    public AdicionarProcessoCommand(Escalonador escalonador, String nomeProcesso){
        this.escalonador = escalonador;
        this.nomeProcesso = nomeProcesso;
    }

    public AdicionarProcessoCommand(Escalonador escalonador, String nomeProcesso, int prioridade){
        this.escalonador = escalonador;
        this.nomeProcesso = nomeProcesso;
        this.prioridade = prioridade;
    }

    @Override
    public void execute() {
        escalonador.adicionarProcesso(nomeProcesso);
    }


}
