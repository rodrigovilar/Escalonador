package br.ufpb.dcx.aps.escalonador.command;


public class AdicionarProcessoCommand extends Command {

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
    public String executar() {
        getEscalonador().adicionarProcesso(nomeProcesso);
        return null;
    }


}
