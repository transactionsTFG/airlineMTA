package integration.transaction;


public abstract class TransactionFactory {
	private static TransactionFactory instancia;
	
	protected TransactionFactory(){}

	public static synchronized TransactionFactory getInstancia() {
		if (instancia == null) instancia = new TransactionFactoryImp();
		return instancia;
	}
	public abstract Transaction getTransaccion();
}
