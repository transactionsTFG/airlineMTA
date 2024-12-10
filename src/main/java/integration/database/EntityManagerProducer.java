package integration.database;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
    
    @Produces
    @PersistenceContext(unitName="MtaAirline")
    private EntityManager entityManager;

}
