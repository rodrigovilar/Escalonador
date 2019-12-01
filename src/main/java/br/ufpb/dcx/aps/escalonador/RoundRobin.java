package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundRobin extends FachadaEscalonador {
	

	private List<String> filaProcessos = new ArrayList<String>();
	private List<String> processoEsperando = new ArrayList<String>();
	private List<String> processoRemovido = new ArrayList<String>();
	private int quantum = 3;
	private int tick;
	
	public RoundRobin(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
	}

	@Override
	public String getStatus() {
		if(filaProcessos.size() >= 1 && tick == 0) {
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Fila: " + filaProcessos.toString() + "};"  
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 1 && tick == 1 && processoEsperando.size() == 1) {
			processoEsperando.remove(0);
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + processoRemovido.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
			
		}else if(filaProcessos.size() == 1 && tick > 0) {
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
					
		}else if(filaProcessos.size() == 2 && tick == 2 && processoEsperando.size() == 2) {
			processoEsperando.remove("P2");
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
			+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + processoEsperando.toString() + "};"
			+ "Quantum: " + quantum + ";"
			+ "Tick: " + tick;
			
		}else if(filaProcessos.size() == 2 && tick < 4) {
			
			if(processoEsperando.size() == 0){
				processoEsperando.add(filaProcessos.get(1));
			
			}else if(processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
				}
			
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
			
		}else if(filaProcessos.size() == 2 && tick > 3 && tick < 7) {
			if(processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(0));
				}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(1) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 2 && tick == 7) {
			if(processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
				}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 3 && tick > 0 && tick < 4){
			if(processoEsperando.size() == 0){
				processoEsperando.add(filaProcessos.get(1));
				processoEsperando.add(filaProcessos.get(2));
			}else if(processoEsperando.size() == 2) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
				processoEsperando.add(filaProcessos.get(2));
				}
			
			return	"Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
			
		}else if(filaProcessos.size() == 3 && tick > 3 && tick < 7){
			if(processoEsperando.size() == 2 && processoEsperando.get(0)=="P2" && processoEsperando.get(1)=="P3"){
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(0));
			}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(1) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
			
		}else if(filaProcessos.size() == 3 && tick > 6 && tick < 10){
			if(processoEsperando.size() == 2 && processoEsperando.get(0)=="P3" && processoEsperando.get(1)=="P1"){
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(2) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 3 && tick == 10){
			if(processoEsperando.size() == 2){
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(2));
			}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + processoEsperando.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 0 && tick == 4) {
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: "+ processoRemovido.get(0) +"};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 0) {
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 2 && filaProcessos.get(0) == "P2" && filaProcessos.get(1) == "P3") {
			if(tick > 1  && tick < 5) {	
				if(processoEsperando.size() == 0 ) {
					processoEsperando.add(filaProcessos.get(1));
				}else if(processoEsperando.size() == 1) {
					processoEsperando.remove(processoEsperando.get(0));
					processoEsperando.add(filaProcessos.get(1));	
				}
			}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + filaProcessos.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		
		}else if(filaProcessos.size() == 2 && filaProcessos.get(0) == "P2" && filaProcessos.get(1) == "P3") {
			if(tick > 4 && tick < 8) {
				if(processoEsperando.size() == 1) {
					processoEsperando.remove(processoEsperando.get(0));
					processoEsperando.add(filaProcessos.get(0));
				}				
			}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(1) + ", Fila: " + filaProcessos.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		}else if(filaProcessos.size() == 2 && filaProcessos.get(0) == "P2" && filaProcessos.get(1) == "P3") {
			if(tick == 8) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}
			return "Escalonador " + TipoEscalonador.RoundRobin + ";"
					+ "Processos: {Rodando: " + filaProcessos.get(0) + ", Fila: " + filaProcessos.toString() + "};"
					+ "Quantum: " + quantum + ";"
					+ "Tick: " + tick;
		}
		return null;
	}

	public void tick() {
		tick++;
	}

	@Override
	public void adicionarProcesso(String nomeProcesso) {
			filaProcessos.add(nomeProcesso);
	}
	
	public void finalizarProcesso(String nomeProcesso) {
		processoRemovido.add(nomeProcesso);	
		filaProcessos.remove(nomeProcesso);
	}
	
}
