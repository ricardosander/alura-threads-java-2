package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JuntaResultadosFutureWSFutureBanco implements Callable<Void> {

	private Future<String> futureWs;
	private Future<String> futureDb;
	private PrintStream saidaCliente;

	public JuntaResultadosFutureWSFutureBanco(Future<String> futureWs, Future<String> futureDb,
			PrintStream saidaCliente) {
		this.futureWs = futureWs;
		this.futureDb = futureDb;
		this.saidaCliente = saidaCliente;
	}

	@Override
	public Void call() throws Exception {

		try {
			String numeroMagico = this.futureWs.get(20, TimeUnit.SECONDS);
			String numeroMagico2 = this.futureDb.get(20, TimeUnit.SECONDS);

			this.saidaCliente.println("Resultado do comando c2: " + numeroMagico + ", " + numeroMagico2);

		} catch (InterruptedException | ExecutionException | TimeoutException e) {

			System.out.println("Timeout: Cancelando a execução do comando c2");
			this.futureDb.cancel(true);
			this.futureWs.cancel(true);
			
			this.saidaCliente.println("Timeout na execução do comando c2");
		}
		System.out.println("Finalizou JuntaResultadosFutureWSFutureBanco");

		return null;
	}

}
