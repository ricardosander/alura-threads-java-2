package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

	private static int contador = 1;
	
	@Override
	public Thread newThread(Runnable r) {
		
		Thread thread = new Thread(r, "Thread Servidor Tarefas " + contador++);
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
		thread.setDaemon(true);
		
		return thread;
	}

}
