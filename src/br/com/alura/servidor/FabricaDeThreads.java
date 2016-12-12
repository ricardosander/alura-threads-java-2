package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

	private static int contador = 1;
	
	private ThreadFactory defaultFactory;
	
	public FabricaDeThreads(ThreadFactory defaultFactory) {
		this.defaultFactory = defaultFactory;
	}
	
	@Override
	public Thread newThread(Runnable r) {

		Thread thread = this.defaultFactory.newThread(r);
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
		thread.setDaemon(true);
		
		return thread;
	}

}
