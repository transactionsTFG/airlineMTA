package integration.entitymanagerfactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SingletonEntityManagerFactory {
    private static EntityManagerFactory instance;
	private static final String NAME_PERSISTANCE = "MtaAirline2";
    private SingletonEntityManagerFactory(){}

    public static synchronized  EntityManagerFactory getInstance() {
		if (instance == null) 
			instance = Persistence.createEntityManagerFactory(NAME_PERSISTANCE);
    
		return instance;
	}
    
}
