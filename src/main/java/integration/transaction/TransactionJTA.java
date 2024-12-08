package integration.transaction;

import integration.environment.GlassFishEnv;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class TransactionJTA implements Transaction {

    @PersistenceContext(unitName = GlassFishEnv.NAME_PERSISTENCE)
    private EntityManager em;
    
    @Override
    public void start() { 
        TransactionManager.getInstancia().nuevaTransaccion(); 
    }

    @Override
    public void commit() {
        TransactionManager.getInstancia().eliminaTransaccion();
    }

    @Override
    public void rollback() {
        TransactionManager.getInstancia().eliminaTransaccion();
    }

    @Override
    public Object getResource() {
        return this.em;        
    }
    
}
