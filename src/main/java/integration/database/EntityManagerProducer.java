package integration.database;

import integration.environment.GlassFishEnv;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
    
    @Produces
    @PersistenceContext(unitName=GlassFishEnv.NAME_PERSISTENCE)
    private EntityManager entityManager;

}
