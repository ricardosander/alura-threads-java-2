package br.com.alura.servidor;

import java.io.PrintStream;

public class ComandoC2 implements Runnable {

	private PrintStream saida;

	public ComandoC2(PrintStream saida) {
		this.saida = saida;
	}

	@Override
	public void run() {

		System.out.println("\nExecutando comando c2\n");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("\nFim do comando c2\n");

		this.saida.println("Comando c2 executado com sucesso.");
	}

}
