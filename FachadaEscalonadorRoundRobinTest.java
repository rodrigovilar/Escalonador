package br.ufpb.dcx.aps.escalonador;

import static br.ufpb.dcx.aps.escalonador.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

import br.ufpb.dcx.aps.escalonador.command.*;
import org.junit.jupiter.api.*;

public class FachadaEscalonadorRoundRobinTest {
	
	private FachadaEscalonador fachada;
	
	@BeforeEach
	public void inicializar() {
		fachada = new FachadaEscalonador(TipoEscalonador.RoundRobin);
	}

	@Test
	public void t01_statusAposCriacao() {
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 0);
	}

	@Test
	public void t02_avancarTempo() {
		fachada.execute(new TickCommand());
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 1);
	}

	@Test
	public void t03_processoSemConcorrencia() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		checaStatusFila(fachada, TipoEscalonador.RoundRobin, 3, 0, "P1");
		
		fachada.execute(new TickCommand());
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1");
		
		//Estoura o quantum mas não tira o processo P1 da CPU, pois não há concorrência
		ticks(fachada, 3);
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 4, "P1");
	}
	
	@Test
	public void t04_finalizarProcessoEmExecução() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		ticks(fachada, 4);

		fachada.execute(new FinalizarProcessoCommand("P1"));
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 4, "P1");

		fachada.execute(new TickCommand());//Só efetua a ação no próximo tick
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 5);
	}

	@Test
	public void t05_alternarDoisProcessosEmExecução(){
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 3, "P1", "P2");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 4, "P2", "P1");
		
		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 6, "P2", "P1");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 7, "P1", "P2");
	}
	

	@Test
	public void t06_alternarDoisProcessosEmExecução(){

		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));
		fachada.execute(new AdicionarProcessoCommand("P3"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 3, "P1", "P2", "P3");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 4, "P2", "P3", "P1");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 6, "P2", "P3", "P1");
		
		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 7, "P3", "P1", "P2");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 9, "P3", "P1", "P2");
		
		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 10, "P1", "P2", "P3");
	}

	
	@Test
	public void t07_alternarDoisProcessosEmExecuçãoInicioDiferente(){
		fachada.execute(new AdicionarProcessoCommand("P1"));

		ticks(fachada, 2);
		fachada.execute(new AdicionarProcessoCommand("P2"));
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 2, "P1", "P2");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 3, "P1", "P2");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 5, "P1", "P2");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 6, "P2", "P1");
		
		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 8, "P2", "P1");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 9, "P1", "P2");
	}
	

	@Test
	public void t08_finalizarProcessoEmExecução() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));
		fachada.execute(new AdicionarProcessoCommand("P3"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		fachada.execute(new FinalizarProcessoCommand("P1"));
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 2, "P2", "P3");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 4, "P2", "P3");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 5, "P3", "P2");

		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 8, "P2", "P3");
	}
	
	@Test
	public void t09a_finalizarProcessoEsperando() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2");

		fachada.execute(new FinalizarProcessoCommand("P2"));
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2");

		fachada.execute(new TickCommand());
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 2, "P1");

		ticks(fachada, 2);
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 4, "P1");
	}

	@Test
	public void t09b_finalizarProcessoEsperando() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));
		fachada.execute(new AdicionarProcessoCommand("P3"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		fachada.execute(new FinalizarProcessoCommand("P2"));
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 2, "P1", "P3");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 4, "P3", "P1");

		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 7, "P1", "P3");
	}
	
	@Test
	public void t10_quantumNaoDefault() {
		fachada = new FachadaEscalonador(TipoEscalonador.RoundRobin, 5);
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 5, 1, "P1", "P2");

		ticks(fachada, 4);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 5, 5, "P1", "P2");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 5, 6, "P2", "P1");
		
		ticks(fachada, 4);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 5, 10, "P2", "P1");

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 5, 11, "P1", "P2");
	}
	
	@Test
	public void t11_intervaloEntreProcessos() {
		fachada.execute(new AdicionarProcessoCommand("P1"));

		fachada.execute(new TickCommand());
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1");

		ticks(fachada, 6);
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 7, "P1");

		fachada.execute(new FinalizarProcessoCommand("P1"));
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 7, "P1");

		fachada.execute(new TickCommand());
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 8);

		ticks(fachada, 2);
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 10);

		fachada.execute(new AdicionarProcessoCommand("P2"));
		checaStatusFila(fachada, TipoEscalonador.RoundRobin, 3, 10, "P2");

		fachada.execute(new TickCommand());
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 11, "P2");

		ticks(fachada, 4);
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 15, "P2");
	}
	
	@Test
	public void t12_bloqueioProcessoEmExecução() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));
		fachada.execute(new AdicionarProcessoCommand("P3"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		fachada.execute(new BloquearProcessoCommand("P1"));
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2", "P3");

		fachada.execute(new TickCommand());
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 2, "P2", "[P3]", "[P1]");
		
		ticks(fachada, 3);
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 5, "P3", "[P2]", "[P1]");
		
		ticks(fachada, 3);
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 8, "P2", "[P3]", "[P1]");
	}
	
	@Test
	public void t13_retornoProcessoBloqueado() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));
		fachada.execute(new AdicionarProcessoCommand("P3"));

		fachada.execute(new TickCommand());
		fachada.execute(new BloquearProcessoCommand("P1"));

		ticks(fachada, 4);
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 5, "P3", "[P2]", "[P1]");

		fachada.execute(new RetomarProcessoCommand("P1"));
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 5, "P3", "[P2]", "[P1]");
		
		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 6, "P3", "P2", "P1");
		
		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 9, "P2", "P1", "P3");
		
		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 12, "P1", "P3", "P2");

		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 15, "P3", "P2", "P1");
	}
	
	@Test
	public void t14_modificarOrdemDosProcessosNaRetomada() {
		fachada.execute(new AdicionarProcessoCommand("P1"));
		fachada.execute(new AdicionarProcessoCommand("P2"));
		fachada.execute(new AdicionarProcessoCommand("P3"));

		fachada.execute(new TickCommand());
		fachada.execute(new BloquearProcessoCommand("P1"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 2, "P2", "[P3]", "[P1]");

		fachada.execute(new BloquearProcessoCommand("P2"));
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 2, "P2", "[P3]", "[P1]");
		
		fachada.execute(new TickCommand());
		checaStatusRodandoBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 3, "P3", "P1", "P2");

		fachada.execute(new BloquearProcessoCommand("P3"));
		checaStatusRodandoBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 3, "P3", "P1", "P2");

		fachada.execute(new TickCommand());
		checaStatusBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 4, "P1", "P2", "P3");

		fachada.execute(new RetomarProcessoCommand("P2"));
		fachada.execute(new RetomarProcessoCommand("P1"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 5, "P2", "[P1]", "[P3]");

		fachada.execute(new RetomarProcessoCommand("P3"));
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 5, "P2", "[P1]", "[P3]");
		
		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 6, "P2", "P1", "P3");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 8, "P1", "P3", "P2");

		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 11, "P3", "P2", "P1");

		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 14, "P2", "P1", "P3");
	}

	@Test
	public void t15_validacoes() {
		fachada.execute(new AdicionarProcessoCommand("P"));
		assertThrows(EscalonadorException.class, () -> fachada.execute(new AdicionarProcessoCommand("P")),
				"Já existe um processo com o nome P" );

		assertThrows(EscalonadorException.class, () -> fachada.execute(new FinalizarProcessoCommand("A")),
				"Não existe um processo com o nome A" );

		assertThrows(EscalonadorException.class, () -> fachada = new FachadaEscalonador(TipoEscalonador.RoundRobin, -1), 
				"O quantum deve ser maior que zero" );

		assertThrows(EscalonadorException.class, () -> fachada = new FachadaEscalonador(TipoEscalonador.RoundRobin, 0), 
				"O quantum deve ser maior que zero" );

		assertThrows(EscalonadorException.class, () -> fachada = new FachadaEscalonador(null), 
				"O tipo do escalonador é obrigatório" );

		assertThrows(EscalonadorException.class, () -> fachada.execute(new BloquearProcessoCommand("A")),
				"Não existe um processo com o nome A" );

		assertThrows(EscalonadorException.class, () -> fachada.execute(new AdicionarProcessoCommand(null)),
				"O nome do processo é obrigatório" );

		assertThrows(EscalonadorException.class, () -> fachada.execute(new RetomarProcessoCommand("A")),
				"Não existe um processo com o nome A" );

		fachada.execute(new AdicionarProcessoCommand("Q"));

		fachada.execute(new TickCommand());
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P", "Q");

		assertThrows(EscalonadorException.class, () -> fachada.execute(new BloquearProcessoCommand("Q")),
				"O processo Q não pode ser bloqueado pois não está rodando" );

		fachada.execute(new BloquearProcessoCommand("P"));
		fachada.execute(new AdicionarProcessoCommand("R"));
		fachada.execute(new TickCommand());
		checaStatusRodandoFilaBloqueio(fachada, TipoEscalonador.RoundRobin, 3, 2, "Q", "[R]", "[P]");
		
		assertThrows(EscalonadorException.class, () -> fachada.execute(new RetomarProcessoCommand("Q")),
				"O processo Q não pode ser retomado pois não está bloqueado" );

		assertThrows(EscalonadorException.class, () -> fachada.execute(new RetomarProcessoCommand("R")),
				"O processo R não pode ser retomado pois não está bloqueado" );

		assertThrows(EscalonadorException.class, () -> fachada.execute(new AdicionarProcessoCommand("P", 1)),
				"O Escalonador RoundRobin não permite que os processos tenham uma prioridade definida na adição" );

	}

}
