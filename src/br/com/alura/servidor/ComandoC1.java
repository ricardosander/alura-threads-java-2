package br.com.alura.servidor;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

	private PrintStream saida;

	public ComandoC1(PrintStream saida) {
		this.saida = saida;
	}
	
	@Override
	public void run() {
	
		System.out.println("\nExecutando comando c1\n");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("\nFim do comando c1\n");
		
		this.saida.println("Comando c1 executado com sucesso.");
	}

}
