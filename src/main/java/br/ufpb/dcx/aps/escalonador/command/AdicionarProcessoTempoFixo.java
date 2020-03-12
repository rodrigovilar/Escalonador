package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class AdicionarProcessoTempoFixo implements Command {

    private Escalonador escalonador;
    private String nomeProcesso;
    private int duracao;

    public AdicionarProcessoTempoFixo(String nomeProcesso, int duracao){
        this.nomeProcesso = nomeProcesso;
        this.duracao = duracao;
    }

    @Override
    public void execute() {
        escalonador.adicionarProcessoTempoFixo(nomeProcesso, duracao);
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }
}
