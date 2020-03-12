package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;
import br.ufpb.dcx.aps.escalonador.FachadaEscalonador;
import br.ufpb.dcx.aps.escalonador.TipoEscalonador;

public class GetStatusCommand extends Command {

    public GetStatusCommand(){}

    @Override
    public String executar() {
        return this.getEscalonador().getStatus();
    }

}
