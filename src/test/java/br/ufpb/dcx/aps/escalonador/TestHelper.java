package br.ufpb.dcx.aps.escalonador;

import br.ufpb.dcx.aps.escalonador.command.GetStatusCommand;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import br.ufpb.dcx.aps.escalonador.command.TickCommand;

public class TestHelper {
	
	public static void ticks(FachadaEscalonador fachada, int vezes) {
		for (int i = 0; i < vezes; i++) {
			fachada.execute(new TickCommand());
		}
	}

	public static void checaStatusRodandoFila(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String rodando, String... fila) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Rodando: " + rodando + ", Fila: " + Arrays.toString(fila) + "};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}

	public static void checaStatusRodando(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String rodando) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Rodando: " + rodando + "};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}

	public static void checaStatusFila(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String... fila) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Fila: " + Arrays.toString(fila) + "};"  
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}
	
	public static void checaStatus(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}
	
	public static void checaStatusRodandoFilaBloqueio(FachadaEscalonador fachada, TipoEscalonador escalonador, 
			int quantum, int ticks, String rodando, String fila, String bloqueados) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Rodando: " + rodando + ", Fila: " + fila + ", Bloqueados: " + bloqueados + "};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}
	
	public static void checaStatusRodandoBloqueio(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String rodando, String... bloqueio) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Rodando: " + rodando + ", Bloqueados: " + Arrays.toString(bloqueio) + "};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}

	public static void checaStatusBloqueio(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String... bloqueio) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Bloqueados: " + Arrays.toString(bloqueio) + "};"  
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.execute(new GetStatusCommand()));
	}
	
}
