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
    public String executar() {
    	if(this.prioridade == 0) {
    		getEscalonador().adicionarProcesso(nomeProcesso);    		
    	}else {
    		getEscalonador().adicionarProcesso(nomeProcesso, prioridade);
    	}
        return null;
    }

    @Override
    public Escalonador getEscalonador() {
        return this.escalonador;
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }


}
