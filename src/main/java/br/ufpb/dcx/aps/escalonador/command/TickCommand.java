package br.ufpb.dcx.aps.escalonador.command;


public class TickCommand extends Command {

    public TickCommand(){}

    @Override
    public String executar() {
        getEscalonador().tick();
        return null;
    }

}
