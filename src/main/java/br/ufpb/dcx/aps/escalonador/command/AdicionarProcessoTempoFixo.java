package br.ufpb.dcx.aps.escalonador.command;


public class AdicionarProcessoTempoFixo extends Command {

    private String nomeProcesso;
    private int duracao;

    public AdicionarProcessoTempoFixo(String nomeProcesso, int duracao){
        this.nomeProcesso = nomeProcesso;
        this.duracao = duracao;
    }

    @Override
    public String executar() {
        getEscalonador().adicionarProcessoTempoFixo(nomeProcesso, duracao);
        return null;
    }
}
