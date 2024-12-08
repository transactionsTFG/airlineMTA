package integration.entitymanagerfactory;


import integration.environment.PersistenceConfig;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SingletonEntityManagerFactory {
    private static EntityManagerFactory instance;
    private SingletonEntityManagerFactory(){}

    public static synchronized EntityManagerFactory getInstance() {
		if (instance == null) 
			instance = Persistence.createEntityManagerFactory(PersistenceConfig.getInstance().getNamePersistance());
	
		return instance;
	}

	public static synchronized void remove(){
		if (instance != null)
			instance.close(); 
	}
    
}
