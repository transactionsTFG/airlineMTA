package integration.transaction;

import integration.entitymanagerfactory.SingletonEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TransactionResourceLocal implements Transaction {
    
    private EntityManager entityManager;
    
    public TransactionResourceLocal() {
        this.entityManager = SingletonEntityManagerFactory.getInstance().createEntityManager();
    }


    @Override
    public void start() {
        this.entityManager.getTransaction().begin();
    }

    @Override
    public void commit() {
        final EntityTransaction transaction = this.entityManager.getTransaction();
        if (transaction.isActive()) 
            transaction.commit();
        this.entityManager.close();
        TransactionManager.getInstancia().eliminaTransaccion();
    }

    @Override
    public void rollback() {
        final EntityTransaction transaction = this.entityManager.getTransaction();
        if (transaction.isActive()) 
            transaction.rollback();
        this.entityManager.close();
        TransactionManager.getInstancia().eliminaTransaccion();
    }

    @Override
    public Object getResource() {
        return this.entityManager;
    }

}
