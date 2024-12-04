package integration.transaction;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction getTransaccion() {
		return new TransactionJPA();
	}

}
