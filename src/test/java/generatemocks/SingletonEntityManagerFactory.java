package generatemocks;
import integration.environment.PersistenceConfig;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class SingletonEntityManagerFactory {
	private static EntityManagerFactory emf = null;
	private SingletonEntityManagerFactory() { /* SINGLETON PATTERN */}

	public static synchronized EntityManagerFactory getInstance(){
		if(emf == null)
			emf = Persistence.createEntityManagerFactory(PersistenceConfig.getInstance().getNamePersistance());
		return emf;
	}
	
}
