package integration.transaction;

import javax.naming.Context;
import javax.naming.InitialContext;

import integration.environment.PersistenceConfig;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction getTransaccion() {
		try {
			if (PersistenceConfig.getInstance().isRunningJUnit()) 
				return new TransactionResourceLocal();
			else {
				Context context = new InitialContext();
				return (Transaction) context.lookup("java:module/TransactionJTA");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
