package integration.transaction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TransactionManagerImpl extends TransactionManager {
    
    private ConcurrentMap<Thread, Transaction> transacciones;

    public TransactionManagerImpl() {
		this.transacciones = new ConcurrentHashMap<>();
	}

    @Override
    public Transaction nuevaTransaccion() {
        Thread thread = Thread.currentThread();
        if (!this.transacciones.containsKey(thread)) {
            Transaction transaction = TransactionFactory.getInstancia().getTransaccion();
            this.transacciones.put(thread, transaction);
        }
        return this.transacciones.get(thread);
    }

    @Override
    public Transaction getTransaccion() {
        return this.transacciones.get(Thread.currentThread());    
    }

    @Override
    public void eliminaTransaccion() {
        this.transacciones.remove(Thread.currentThread());
    }

}
