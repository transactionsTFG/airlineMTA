package integration.entitymanagerfactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SingletonEntityManagerFactory {
    private static EntityManagerFactory instance;
	private static final String NAME_PERSISTANCE = "MtaAirline2";
    private SingletonEntityManagerFactory(){}

    public static synchronized  EntityManagerFactory getInstance() {
    	try {
    		if (instance == null) 
    			instance = Persistence.createEntityManagerFactory(NAME_PERSISTANCE);
    	} catch(Throwable e) {
    		String x =  e.getMessage();
    		String x2 = x;
    	}
		
		
		return instance;
	}
    
}
