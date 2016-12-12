package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2AcessaBanco implements Callable<String> {

	private PrintStream saida;

	public ComandoC2AcessaBanco(PrintStream saida) {
		this.saida = saida;
	}
	
	@Override
	public String call() throws Exception {
		
		System.out.println("Servidor recebeu comando c2 - DB");
		saida.println("Processando comando c2 - DB");
		
		Thread.sleep(20000);
		
		int numero = new Random().nextInt(100) + 1;
		System.out.println("Servidor finalizdou comando c2 - DB");
		
		return Integer.toString(numero);
	}

}
