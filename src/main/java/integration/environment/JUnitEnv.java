package integration.environment;

public class JUnitEnv extends PersistenceConfig {

	@Override
	public boolean isRunningJUnit() {
		return true;
	}

	@Override
	public String getNamePersistance() {
		return "MtaAirlineTest";
	}
	
	
	
}
