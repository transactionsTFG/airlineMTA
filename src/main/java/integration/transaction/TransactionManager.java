package integration.transaction;

public abstract class TransactionManager {
    protected TransactionManager(){}
	private static TransactionManager instancia;

	public static synchronized TransactionManager getInstancia() {
		if (instancia == null) 
            instancia = new TransactionManagerImpl();
		return instancia;
	}

	public abstract Transaction nuevaTransaccion();
	public abstract Transaction getTransaccion();
	public abstract void eliminaTransaccion();
}
