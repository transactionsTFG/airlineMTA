package generatemocks;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import business.flight.SAAFlight;
import business.flight.SAAFlightImpl;
import business.reservation.SAAReservation;
import business.reservation.SAAReservationImpl;
import integration.environment.PersistenceConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class SAManagerForUnitTest {
	protected static SAAFlight SERVICE_FLIGHT;
	protected static SAAReservation SERVICE_RESERVATION;
	protected static ConsultMocks GET_MOCK; //Importante que sea estatico para que cree nueva clasess
	protected EntityManager em;
    protected static EntityManagerFactory emf;

	@BeforeClass
	public static void setUp() {
		GenerateEntityForUnitTest.init();
		emf = Persistence.createEntityManagerFactory(PersistenceConfig.getInstance().getNamePersistance());
	}
	
	@AfterClass
    public static void closeEntityManagerFactory() {
		if (emf.isOpen()) 
	        emf.close();			
    }
	
	@Before
	public void beginTransaction() {
	   em = emf.createEntityManager();
	   em.getTransaction().begin();
	   GET_MOCK = new ConsultMocks(em);
	   SERVICE_FLIGHT = new SAAFlightImpl(em);
	   SERVICE_RESERVATION = new SAAReservationImpl(em);
	   
	}
	
	@After
    public void rollbackTransaction() {   
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
        
        if (em.isOpen()) 
            em.close();
    }
	
	public void commit() {
		if (em.getTransaction().isActive()) 
			this.em.getTransaction().commit();
	}
	
}
